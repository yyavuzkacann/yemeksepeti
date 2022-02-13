import base.BaseMethods;
import org.testng.annotations.Test;
import pages.FavoritesPage;
import pages.HomePage;
import pages.RestaurantDetailPage;

public class addingToFavorites extends BaseMethods {
    @Test()
    public void checkCorrectRestaurant() {
        HomePage homePage = new HomePage();
        RestaurantDetailPage restaurantDetailPage = new RestaurantDetailPage();
        homePage
                .checkPageLoad()
                .selectCity("İstanbul")
                .writeUsername("ticaf97078@balaket.com")
                .writePass("Test1234.")
                .clickLogin()
                .checkErrorMessage()
                .clickSuperRestaurantsMenu();
        String restaurant = homePage
                .getSuperRestaurants();
        restaurantDetailPage
                .addToFavorites();
        homePage
                .goToHomepage()
                .clickFavoritesMenu()
                .readFavorites(restaurant);
    }

    /*@Test()
    public void checkWrongRestaurant() {
        HomePage homePage = new HomePage();
        RestaurantDetailPage restaurantDetailPage = new RestaurantDetailPage();
        homePage
                .checkPageLoad()
                .selectCity("İstanbul")
                .writeUsername("ticaf97078@balaket.com")
                .writePass("Test1234.")
                .clickLogin()
                .checkErrorMessage()
                .clickSuperRestaurantsMenu();
        String restaurant = homePage
                .getSuperRestaurants();
        restaurantDetailPage
                .addToFavorites();
        homePage
                .goToHomepage()
                .clickFavoritesMenu()
                .readFavorites("yemeksepeti");
    }

    @Test()
    public void deleteFavorites() {
        HomePage homePage = new HomePage();
        FavoritesPage favoritesPage = new FavoritesPage();
        homePage
                .checkPageLoad()
                .selectCity("İstanbul")
                .writeUsername("ticaf97078@balaket.com")
                .writePass("Test1234.")
                .clickLogin()
                .checkErrorMessage()
                .clickProfile()
                .selectMenu("Favorilerim");
        favoritesPage
                .selectAllFavorites()
                .delete();
    }*/
}