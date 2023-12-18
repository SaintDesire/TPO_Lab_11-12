package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {
    private final Logger log = LogManager.getLogger();

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage openCart() {
        WebElement cartIcon = driver.findElement(By.xpath("//a[contains(@class, 'cart-icon')]")); // Пример локатора иконки корзины
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        return this;
    }


    public boolean isProductInTheCart() throws InterruptedException {
        Thread.sleep(2000);
        WebElement productTitle = driver.findElement(By.xpath("//div[@class='product-item__title']/a[contains(text(),'Лакомство для собак API-SAN Шампанское Chateau Dog 375 мл')]"));
        return productTitle.isDisplayed();
    }

    public String getCartUrl() {
        return driver.getCurrentUrl();
    }
}
