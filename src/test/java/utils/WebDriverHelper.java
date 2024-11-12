package utils;

import configs.BaseConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class WebDriverHelper {
    private WebDriver driver;
    private WebDriverWait shortWait;
    private WebDriverWait moderateWait;
    private WebDriverWait longWait;
    private Actions actions;
    private JavascriptExecutor jsExecutor;
    private static BaseConfig baseConfig;

    private WebDriver getDriver() {
        return driver == null ? DriverHook.getDriver() : driver;
    }

    private WebDriverWait getShortWait() {
        return shortWait == null ? new WebDriverWait(getDriver(), Duration.ofSeconds(BaseConfig.SHORT_WAIT_TIMEOUT_SECONDS)) : shortWait;
    }

    private WebDriverWait getModerateWait() {
        return moderateWait == null ? new WebDriverWait(getDriver(), Duration.ofSeconds(BaseConfig.MODERATE_WAIT_TIMEOUT_SECONDS)) : moderateWait;
    }

    private WebDriverWait getLongWait() {
        return longWait == null ? new WebDriverWait(getDriver(), Duration.ofSeconds(BaseConfig.LONG_WAIT_TIMEOUT_SECONDS)) : longWait;
    }

    private Actions getActions() {
        return actions == null ? new Actions(getDriver()) : actions;
    }

    private JavascriptExecutor getJsExecutor() {
        return jsExecutor == null ? (JavascriptExecutor) getDriver() : jsExecutor;
    }

    private static BaseConfig getBaseConfig() {
        return baseConfig == null ? new BaseConfig() : baseConfig;
    }

    public void click(By by) {
        waitUntilElementClickable(by).click();
    }

    public void clickKeyEnterButton() {
        getActions().sendKeys(Keys.ENTER).perform();
    }

    public WebElement findElement(By by) {
        return waitUntilElementPresent(by);
    }

    public List<WebElement> findElements(By by) {
        return getDriver().findElements(by);
    }

    public String getText(By by) {
        return waitUntilElementVisible(by).getText();
    }

    public String getText(WebElement wb) {
        return wb.getText();
    }

    public List<String> getAllWebElementText(By by) {
        List<WebElement> elementList = findElements(by);
        return elementList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void hoverWebElement(By by) {
        getActions().moveToElement(getDriver().findElement(by)).perform();
    }

    public boolean isElementPresent(By by) {
        return !getDriver().findElements(by).isEmpty();
    }

    public void navigateWithPath(String path) {
        getDriver().get(getBaseConfig().baseUrl() + path);
    }

    public void scrollUntilElementVisible(By by) {
        WebElement element = findElement(by);
        getJsExecutor().executeScript("arguments[0].scrollIntoView();", element);
    }

    public void switchToTab(String condition) {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        switch (condition) {
            case "last" -> getDriver().switchTo().window(tabs.get(tabs.size() - 1));
            case "first" -> getDriver().switchTo().window(tabs.get(0));
            default -> getDriver().switchTo().window(tabs.get(Integer.parseInt(condition) - 1));
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
        return getModerateWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitUntilElementPresent(By by) {
        return getModerateWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitUntilElementInvisible(By by) {
        getModerateWait().until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitUntilElementVisible(By by) {
        return getModerateWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitUntilElementContainsText(By by, String expectedText) {
        int attempts = 0;
        final int maxAttempts = BaseConfig.LONG_WAIT_TIMEOUT_SECONDS;
        while (attempts < maxAttempts) {
            try {
                String elementText = getDriver().findElement(by).getText();
                if (elementText.contains(expectedText)) {
                    return;
                }
            } catch (NoSuchElementException ignored) {
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted during sleep.", e);
            }
            attempts++;
        }
        throw new RuntimeException("Text did not match within the specified attempts for: " + by);
    }

    public By xpathByVar(String locator, Object... vars) {
        return By.xpath(String.format(locator, vars));
    }

    public By cssSelectorByVar(String selector, Object... vars) {
        return By.cssSelector(String.format(selector, vars));
    }
}