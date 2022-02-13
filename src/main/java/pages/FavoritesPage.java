package pages;

import base.BaseMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

public class FavoritesPage extends BaseMethods {
    final static Logger logger = Logger.getLogger(RestaurantDetailPage.class);

    By LST_FAVORITES = By.xpath("//div[@class='favorites panel-body']//div");
    By BTN_DELETE = By.xpath("//button[@class='ys-btn ys-btn-white delete-favourites']");

    public FavoritesPage selectAllFavorites() {
        logger.info("Selects all favorites");
        Reporter.log("Selects all favorites");
        List<WebElement> list = getList(LST_FAVORITES);

        for (WebElement element : list) {
            element.findElement(By.tagName("input")).click();
        }
        return this;
    }

    public void delete() {
        logger.info("Delete favorites");
        Reporter.log("Delete favorites");
        clickElement(BTN_DELETE);
    }
}
