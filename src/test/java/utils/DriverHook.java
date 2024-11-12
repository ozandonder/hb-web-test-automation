package utils;

import configs.BaseConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.BasePage;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static io.qameta.allure.Allure.addAttachment;

public class DriverHook {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static BaseConfig baseConfig;
    private static BasePage basePage;

    private static BaseConfig getBaseConfig() {
        return baseConfig == null ? new BaseConfig() : baseConfig;
    }

    private static BasePage getBasePage() {
        return basePage == null ? new BasePage() : basePage;
    }

    private DriverHook() {
        String browser = getBaseConfig().getBrowser();
        switch (browser) {
            case "chrome" -> {
                try {
                    ChromeOptions options = new ChromeOptions();
                    if (getBaseConfig().getHeadless()) options.addArguments("--headless=new");
                    options.addArguments("-incognito");
                    options.setBrowserVersion("stable");
                    driver.set(new ChromeDriver(options));
                } catch (Exception e) {
                    LoggingUtil.logError(browser + " not run. e -> ", e);
                }
            }
            case "remote-chrome" -> {
                try {
                    ChromeOptions options = new ChromeOptions();
                    if (getBaseConfig().getHeadless()) {
                        options.addArguments("--headless=new");
                    }
                    options.addArguments("-incognito");
                    Map<String, Object> chrome_prefs = new HashMap<String, Object>();
                    chrome_prefs.put("intl.accept_languages", "tr");
                    options.setExperimentalOption("prefs", chrome_prefs);
                    driver.set(new RemoteWebDriver(new URL("http://localhost:4444/"), options));
                } catch (Exception e) {
                    LoggingUtil.logError(browser + " not run. e -> ", e);
                }
            }
            case "remote-firefox" -> {
                try {
                    FirefoxOptions options = new FirefoxOptions();
                    if (getBaseConfig().getHeadless()) {
                        options.addArguments("--headless=new");
                    }
                    FirefoxProfile firefoxProfile = new FirefoxProfile();
                    firefoxProfile.setPreference("intl.accept_languages", "tr");
                    options.setProfile(firefoxProfile);
                    options.addArguments("-incognito");
                    driver.set(new RemoteWebDriver(new URL("http://localhost:4444/"), options));
                } catch (Exception e) {
                    LoggingUtil.logError(browser + " not run. e -> ", e);
                }
            }
            default -> LoggingUtil.logWarning(browser + " not found");
        }
        configureDriver();
    }

    private void configureDriver() {
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(BaseConfig.SHORT_WAIT_TIMEOUT_SECONDS));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(BaseConfig.MODERATE_WAIT_TIMEOUT_SECONDS));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(BaseConfig.MODERATE_WAIT_TIMEOUT_SECONDS));
        getDriver().manage().window().maximize();
        getDriver().get(getBaseConfig().baseUrl());
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Before
    public static void setUpDriver(Scenario scenario) {
        LoggingUtil.logInfo(scenario.getName() + " -> scenario started");
        UtilityMethods.resetAllGlobal();
        new DriverHook();
        getBasePage().acceptOneTrust();
    }

    @After
    public static void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggingUtil.logInfo("Scenario failed => " + scenario.getName());
            try {
                File screenshotAs = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
                String scenarioName = scenario.getName().replace(" ", "_");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mmss");
                LocalDateTime timestamp = LocalDateTime.now();
                String filename = scenarioName + "_" + timestamp.format(formatter) + ".png";
                addAttachment(filename, FileUtils.openInputStream(screenshotAs));
            } catch (Exception var) {
                LoggingUtil.logError("Fail scenario screenshot failed", var);
            }
        } else {
            LoggingUtil.logInfo("Scenario success => " + scenario.getName());
        }
        if (getDriver() != null) {
            getDriver().close();
            getDriver().quit();
        }
    }
}
