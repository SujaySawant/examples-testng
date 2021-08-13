package com.browserstack.app.utils;

import com.browserstack.local.Local;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("unchecked")
@Listeners({ScreenshotListener.class})
public class TestBase {
    public static final String PATH_TO_TEST_CAPS_JSON = "src/test/resources/conf/capabilities/test_caps.json";
    // ThreadLocal gives the ability to store data individually for the current thread
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    private static final String DOCKER_SELENIUM_HUB_URL = "http://localhost:4444/wd/hub";
    private static final String BROWSERSTACK_HUB_URL = "https://hub.browserstack.com/wd/hub";
    private static final LocalDateTime TIMESTAMP = LocalDateTime.now();
    private Local local;

    public static WebDriver getDriverThread() {
        return driverThread.get();
    }

    @BeforeMethod
    @Parameters(value = {"environment", "testType", "env_cap_id"})
    public void setUp(@Optional("on-prem") String environment, @Optional("single") String testType, @Optional("0") int env_cap_id, ITestResult tr) throws Exception {
        WebDriver driver;
        JSONParser parser = new JSONParser();
        JSONObject testCapsConfig = (JSONObject) parser.parse(new FileReader(PATH_TO_TEST_CAPS_JSON));
        String url = (String) testCapsConfig.get("application_endpoint");
        switch (environment) {
            case "on-prem":
                DriverUtil.setDriverPathVariable();
                driver = new ChromeDriver();
                break;
            case "remote":
                JSONObject profilesJson = (JSONObject) testCapsConfig.get("tests");
                JSONObject envs = (JSONObject) profilesJson.get(testType);
                Map<String, String> commonCapabilities = (Map<String, String>) envs.get("common_caps");
                commonCapabilities.put("name", tr.getMethod().getDescription());
                commonCapabilities.put("build", commonCapabilities.get("build") + " - " + TIMESTAMP);
                Map<String, String> envCapabilities = (Map<String, String>) ((org.json.simple.JSONArray) envs.get("env_caps")).get(env_cap_id);
                Map<String, String> localCapabilities = (Map<String, String>) envs.get("local_binding_caps");
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.merge(new DesiredCapabilities(commonCapabilities));
                caps.merge(new DesiredCapabilities(envCapabilities));
                if (testType.equals("local")) {
                    url = (String) envs.get("application_endpoint");
                    caps.merge(new DesiredCapabilities(localCapabilities));
                }
                caps.setCapability("browserstack.user", getUsername(testCapsConfig));
                caps.setCapability("browserstack.key", getAccessKey(testCapsConfig));
                createSecureTunnelIfNeeded(caps, testCapsConfig);
                driver = new RemoteWebDriver(new URL(BROWSERSTACK_HUB_URL), caps);
                break;
            case "docker":
                DesiredCapabilities dc = new DesiredCapabilities();
                dc.setBrowserName("chrome");
                dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                driver = new RemoteWebDriver(new URL(DOCKER_SELENIUM_HUB_URL), dc);
                break;
            default:
                throw new RuntimeException("Incorrect Environment Specified.");
        }
        driverThread.set(driver);
        driverThread.get().get(url);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        getDriverThread().quit();
        if (local != null) local.stop();
    }

    private String getUsername(JSONObject testCapsConfig) {
        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) username = testCapsConfig.get("user").toString();
        return username;
    }

    private String getAccessKey(JSONObject testCapsConfig) {
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) accessKey = testCapsConfig.get("key").toString();
        return accessKey;
    }

    private void createSecureTunnelIfNeeded(DesiredCapabilities caps, JSONObject testCapsConfig) throws Exception {
        if (caps.getCapability("browserstack.local") != null
                && caps.getCapability("browserstack.local").equals("true")) {
            local = new Local();
            UUID uuid = UUID.randomUUID();
            caps.setCapability("browserstack.localIdentifier", uuid.toString());
            Map<String, String> options = new HashMap<>();
            options.put("key", getAccessKey(testCapsConfig));
            options.put("localIdentifier", uuid.toString());
            local.start(options);
        }
    }

}
