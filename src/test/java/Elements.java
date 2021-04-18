import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Elements {
    public static WebDriver driver;

    public Elements(WebDriver driver) {
        this.driver = driver;
    }

    public String title() {
        return driver.getTitle();
    }

    public WebElement loginButton() {
        return driver.findElement(By.cssSelector("a[href='https://www.gittigidiyor.com/uye-girisi']"));
    }

    public WebElement hoverLoginButton() {
        return driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div" +
                "/div/div/div[3]/div/div[1]/div"));
    }

    public WebElement userName() {
        return driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div" +
                "/div/div[2]/form/div[1]/div[1]/div[1]/div[2]/input"));

    }

    public WebElement password() {
        return driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div" +
                "/div/div[2]/form/div[1]/div[2]/div/div[2]/input"));

    }

    public WebElement loginSubmitButton() {
        return driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div" +
                "/div/div[2]/form/div[2]/div/div[2]/div[1]/input[13]"));
    }

    public WebElement inputSearch() {
        return driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div" +
                "/div/div/div[2]/form/div/div[1]/div[2]/input"));

    }

    public WebElement pageTwo(){
        return driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]" +
                "/div[5]/ul/li[2]"));
    }

    public WebElement popUpFestival(){
        return driver.findElement(By.xpath("/html/body/div[6]/div/img[1]"));
    }

    public WebElement popUpCookie(){
        return driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/div/a"));
    }

    public WebElement randomProduct() {
        List<WebElement> list = new ArrayList();
        list = driver.findElements(By.cssSelector("li[product-index]"));
        int listCount = list.toArray().length;

        Random random = new Random();
        int result = random.nextInt(listCount) + 1; //first product id = 1
        return list.get(result);
    }

    public WebElement addCartButton() {
        return driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]" +
                "/div[1]/div[2]/div[2]/div[1]/div/div[3]/div[3]/section/div/div/div[2]/form/button"));
    }

    public WebElement productPrice(){
        return driver.findElement(By.id("sp-price-discountPrice"));
    }


}
