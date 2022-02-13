package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static base.Constants.URL;
import static base.Constants.waitTime;

public class BaseMethods {
    public static WebDriver driver;

    @BeforeMethod
    public static WebDriver setDriver() {
        String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//drivers//chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//drivers//geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "opera":
                System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "//drivers//operadriver.exe");
                driver = new OperaDriver();
            default:
                Assert.fail("Browser couldn't find!!");
        }

        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
            LocalDateTime now = LocalDateTime.now();
            String fileName = testResult.getName() + "_" + dtf.format(now);
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./screenshots/" + fileName + ".png"));
        }
    }

    @AfterTest
    public void close() {
        driver.quit();
    }

    public void clickElement(By locator) {
        waitElementVisible(locator);
        driver.findElement(locator).click();
    }

    public void sendKeys(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public void waitElementVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception ignored) {
        }
    }

    public boolean isElementPresent(By locator) {
        waitElementVisible(locator);
        return driver.findElements(locator).size() > 0;
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public List<WebElement> getList(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public boolean isElementDisplayed(By locator) {
        waitElementVisible(locator);
        return driver.findElement(locator).isDisplayed();
    }
}