package pages;

import base.BaseMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

import static base.Constants.expectedTitle;

public class HomePage extends BaseMethods {
    final static Logger logger = Logger.getLogger(HomePage.class);

    By INP_USERNAME = By.xpath("//input[@id='UserName']");
    By INP_PASSWORD = By.xpath("//input[@id='password']");
    By BTN_LOGIN = By.xpath("//button[@id='ys-fastlogin-button']");
    By LBL_ERROR_MESSAGE = By.xpath("//span[@class='ys-xl inner']");
    By BTN_SUPER_RESTAURANTS = By.xpath("//a[@data-content-id='super-restaurants']");
    By LST_SUPER_RESTAURANTS = By.xpath("//div[@id='super-restaurants']//div[@class='ys-po-item po-item-line']");
    By BTN_HOMEPAGE_LOGO = By.xpath("//div[contains(@class,'logoSection')]/a");
    By BTN_FAVORITES = By.xpath("//a[@data-content-id='favorites']");
    By LST_FAVORITES = By.xpath("//div[@id='favorites']//div[@class='ys-po-item']");
    By BTN_PROFILE = By.xpath("//div[@data-yslinktracking='anasayfa:profilim:avatar']");

    public HomePage checkPageLoad() {
        logger.info("Checking page is load.");
        Reporter.log("Checking page is load.");
        Assert.assertTrue(driver.getTitle().contains(expectedTitle), "Page couldn't load!!");
        return this;
    }

    public HomePage selectCity(String cityName) {
        logger.info("Selecting city.");
        Reporter.log("Selecting city.");
        driver.findElement(By.xpath("//span[text()='" + cityName + "']")).click();
        return this;
    }

    public HomePage writeUsername(String userName) {
        logger.info("Writing username.");
        Reporter.log("Writing username.");
        sendKeys(INP_USERNAME, userName);
        return this;
    }

    public HomePage writePass(String pass) {
        logger.info("Writing password.");
        Reporter.log("Writing password.");
        sendKeys(INP_PASSWORD, pass);
        return this;
    }

    public HomePage clickLogin() {
        logger.info("Login button is clicked.");
        Reporter.log("Login button is clicked.");
        clickElement(BTN_LOGIN);
        return this;
    }

    public HomePage checkErrorMessage() {
        logger.info("Checking error message.");
        Reporter.log("Checking error message.");
        if (isElementPresent(LBL_ERROR_MESSAGE))
            Assert.fail(getText(LBL_ERROR_MESSAGE));
        else
            logger.info("User logged in successfully.");
        return this;
    }

    public void clickSuperRestaurantsMenu() {
        logger.info("Clicks Super restaurants menu.");
        Reporter.log("Clicks Super restaurants menu.");
        clickElement(BTN_SUPER_RESTAURANTS);
    }

    public String getSuperRestaurants() {
        logger.info("Gets Super restaurants list.");
        Reporter.log("Gets Super restaurants list.");
        boolean find = false;
        String restaurant = "";
        List<WebElement> list = getList(LST_SUPER_RESTAURANTS);
        for (WebElement element : list) {
            double point = Double.parseDouble(element.findElement(By.tagName("span")).getText().replace(",", "."));
            if (point > 9.0) {
                restaurant = element.findElement(By.xpath("//span[contains(@class,'mainpage-tabs-super-restaurant restaurant-item-length')]"))
                        .getText();
                logger.info("Restaurant Name : " + restaurant);
                Reporter.log("Restaurant Name : " + restaurant);
                element.findElement(By.tagName("a")).click();
                find = true;
                break;
            }
        }
        if (!find)
            Assert.fail("Couldn't find any restaurant that points greater than 9.");
        return restaurant;
    }

    public HomePage goToHomepage() {
        logger.info("Homepage loads.");
        Reporter.log("Homepage loads.");
        clickElement(BTN_HOMEPAGE_LOGO);
        return this;
    }

    public HomePage clickFavoritesMenu() {
        logger.info("Clicks favorites menu.");
        Reporter.log("Clicks favorites menu.");
        clickElement(BTN_FAVORITES);
        return this;
    }

    public void readFavorites(String restaurant) {
        logger.info("Gets favorites.");
        Reporter.log("Gets favorites.");
        boolean find = false;
        List<WebElement> list = getList(LST_FAVORITES);
        for (WebElement element : list) {
            String currentRestaurant = element.findElement(By.xpath("//span[@class='mainpage-tabs-fav-restaurant']")).getText();
            if (currentRestaurant.equalsIgnoreCase(restaurant)) {
                find = true;
                logger.info("Restaurant listed under favorites.");
                Reporter.log("Restaurant listed under favorites.");
                break;
            }
        }

        if (!find)
            Assert.fail("Added restaurant couldn't find under favorites!!");
    }

    public HomePage clickProfile() {
        logger.info("Clicks profile.");
        Reporter.log("Clicks profile.");
        clickElement(BTN_PROFILE);
        return this;
    }

    public void selectMenu(String menu) {
        logger.info("Selects menu.");
        Reporter.log("Selects menu.");
        clickElement(By.xpath("//a[text()='" + menu + "']"));
    }
}