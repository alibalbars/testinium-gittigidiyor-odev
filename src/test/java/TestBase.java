import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TestBase {

    public static WebDriver driver;
    private static String baseUrl;
    private static String driverPath;
    public static Elements elements;

    public static String userName;
    public static String password;


    @Before
    public void setUp() throws Exception {
        driverPath = "C:/Users/user/Downloads/chromedriver_win32/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        baseUrl = "https://gittigidiyor.com";
        elements = new Elements(driver);
        userName = "testinium_selenium_test@gmail.com";
        password = "testinium123";
    }

    @Test
    public void test() throws Exception {
        driver.get(baseUrl);
        if (isPageLoaded("gittigidiyor") == true){ // is page loading successfull?
            login();
            search("bilgisayar");
            System.out.println(getProductPrice());
//            addCart();

        }
    }

    public boolean isPageLoaded(String expectedTitle) {

        if (driver.getTitle() != null && driver.getTitle().
                toLowerCase().contains(expectedTitle)){
            return true;

        } else { return false; }
    }

    public void login() throws Exception{
        Actions action = new Actions(driver);
        action.moveToElement(elements.hoverLoginButton());
        action.perform();
        Thread.sleep(500); // for element hover affect

        elements.loginButton().click();
        elements.userName().sendKeys(userName);
        elements.password().sendKeys(password);
        elements.loginSubmitButton().click();

    }

    public void search(String query) throws Exception{
        elements.inputSearch().sendKeys(query);
        elements.inputSearch().sendKeys(Keys.ENTER);

        Thread.sleep(500);

        closePopups();
        elements.pageTwo().click();

        if (isPageLoaded("bilgisayar") == true) {
            elements.randomProduct().click();
        }

    }

    public void addCart() {
        elements.addCartButton().click();
    }

    public String getProductPrice() {
        String strPrice = elements.productPrice().getAttribute("innerHTML");
        String[] priceAndCur = strPrice.split(" ");
        return priceAndCur[0];
    }

    public void closePopups(){
        elements.popUpFestival().click();
        elements.popUpCookie().click();
    }

}
