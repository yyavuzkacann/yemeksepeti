package pages;

import base.BaseMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;

public class RestaurantDetailPage extends BaseMethods {
    final static Logger logger = Logger.getLogger(RestaurantDetailPage.class);

    By BTN_ADD_FAVORITE = By.xpath("//b[text()='Favorilere Ekle']");
    By BTN_REMOVE_FAVORITE = By.xpath("//b[text()='Favorilerden Çıkar']");

    public void addToFavorites() {
        logger.info("Clicks add to favorite button.");
        Reporter.log("Clicks add to favorite button.");
        if (isElementDisplayed(BTN_ADD_FAVORITE))
            clickElement(BTN_ADD_FAVORITE);
        else if (isElementDisplayed(BTN_REMOVE_FAVORITE)) {
            clickElement(BTN_REMOVE_FAVORITE);
            clickElement(BTN_ADD_FAVORITE);
        } else
            Assert.fail("Add or remove favorite buttons couldn't find!!");
    }
}
