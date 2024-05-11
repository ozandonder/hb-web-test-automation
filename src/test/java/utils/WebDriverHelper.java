package utils;

import configs.BaseConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

public class WebDriverHelper {
    WebDriver driver = DriverHook.getDriver();

    public void click(By by) {
        waitUntilElementClickable(by).click();
    }

    public WebElement findElement(By by) {
        return waitUntilElementPresent(by);
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public String getText(By by) {
        return waitUntilElementVisible(by).getText();
    }

    public String getText(WebElement wb) {
        return wb.getText();
    }

    public boolean isElementPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }

    public void scrollUntilElementVisible(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(by);
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }

    public void switchToTab(String condition) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        switch (condition) {
            case "last" -> driver.switchTo().window(tabs.get(tabs.size() - 1));
            case "first" -> driver.switchTo().window(tabs.get(0));
            default -> driver.switchTo().window(tabs.get(Integer.parseInt(condition) - 1));
        }
    }

    public void type(By by, String text, boolean clear) {
        WebElement webElement = findElement(by);
        if (clear) {
            webElement.clear();
        }
        webElement.sendKeys(text);
    }

    public WebElement waitUntilElementClickable(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BaseConfig.GLOBAL_WAIT_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitUntilElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BaseConfig.GLOBAL_WAIT_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitUntilElementInvisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BaseConfig.GLOBAL_WAIT_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitUntilElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BaseConfig.GLOBAL_WAIT_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public By xpathByVar(String locator, Object... vars) {
        return By.xpath(String.format(locator, vars));
    }
}