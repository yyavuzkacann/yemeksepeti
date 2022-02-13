import base.BaseMethods;
import org.testng.annotations.Test;
import pages.HomePage;

public class loginCases extends BaseMethods {
    @Test()
    public void loginWithCorrectUser() {
        HomePage homePage = new HomePage();
        homePage
                .checkPageLoad()
                .selectCity("İstanbul")
                .writeUsername("ticaf97078@balaket.com")
                .writePass("Test1234.")
                .clickLogin()
                .checkErrorMessage();
    }

    @Test()
    public void loginWithWrongUsername() {
        HomePage homePage = new HomePage();
        homePage
                .checkPageLoad()
                .selectCity("İstanbul")
                .writeUsername("yemeksepetiticaf97078@balaket.com")
                .writePass("Test1234.")
                .clickLogin()
                .checkErrorMessage();
    }

    @Test()
    public void loginWithWrongPassword() {
        HomePage homePage = new HomePage();
        homePage
                .checkPageLoad()
                .selectCity("İstanbul")
                .writeUsername("ticaf97078@balaket.com")
                .writePass("Test1234.asda")
                .clickLogin()
                .checkErrorMessage();
    }

    @Test()
    public void loginWithWrongUsernameAndPassword() {
        HomePage homePage = new HomePage();
        homePage
                .checkPageLoad()
                .selectCity("İstanbul")
                .writeUsername("yemeksepetiticaf97078@balaket.com")
                .writePass("Test1234.asda")
                .clickLogin()
                .checkErrorMessage();
    }
}
