import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
            Thread.sleep(300); // wait for element spawn
            String productPrice = getProductPrice();
            String productName = getProductName();
            addCart();
            goCart();

            String cartPrice = elements.firstProductInCart()
                    .findElement(By.className("total-price"))
                    .findElement(By.tagName("strong"))
                    .getAttribute("innerHTML");

            if (cartPrice.equals(productPrice)) // check if price in product page and price in cart are same
                System.out.println("Ürün sayfasındaki fiyat ile sepetteki fiyat aynı.");
            else
                System.out.println("Ürün sayfasındaki fiyat ile sepetteki fiyat aynı değil.");

            // Yazdır
            System.out.println("ürün sayfası fiyat => " + productPrice);
            System.out.println(
                    "cart fiyat => " +
                    elements.firstProductInCart()
                            .findElement(By.className("total-price"))
                            .findElement(By.tagName("strong"))
                            .getAttribute("innerHTML")
            );

            elements.selectInCart().click(); // make product order count 2
            Thread.sleep(1000); // wait 1 second
            emptyCart();
            Thread.sleep(1000); // wait for element deletion

            int countInCart = elements.productsInCart().toArray().length;

            if(countInCart > 0) // check if cart is empty
                System.out.println("Sepet boş değil");
            else
                System.out.println("Sepet boş");

            System.out.println("product number in cart => " + countInCart);
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
        Thread.sleep(500); // wait for the CAPTCHA not to come out
        elements.userName().sendKeys(userName);
        Thread.sleep(1000);
        elements.password().sendKeys(password);
        Thread.sleep(1000);
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

    public void goCart() throws Exception{
        elements.myCartButton().click();

    }

    public String getProductPrice() {
        String strPrice = elements.productPrice().getAttribute("innerHTML");
        return strPrice;
    }

    public String getProductName() {
        return elements.productName().getAttribute("innerHTML");
    }

    public void emptyCart() throws Exception{
        Thread.sleep(2000);
        elements.cartDeleteButton().click();
    }

    public void closePopups() {
        try{
            Thread.sleep(300); // wait a while
            elements.popUpFestival().click();
            elements.popUpCookie().click();
        } catch (Exception e) {
            // continue
        }
    }
}
