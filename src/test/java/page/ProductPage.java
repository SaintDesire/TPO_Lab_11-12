package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    private final Logger log = LogManager.getLogger();

    @FindBy(xpath = "/html/body/div[2]/div[1]/div[4]/div/div[2]/div/div[1]/div[3]/div[3]/div[3]/div[2]/div/div[4]/button")
    private WebElement addToCartButton;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[2]/div/div[3]/div[5]")
    private WebElement cartButton;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[2]/div/div[1]/a")
    private WebElement mainPageButton;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ProductPage addToCart() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        log.info("Add to cart button clicked");
        return this;
    }

    public CartPage goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        log.info("Cart button clicked");
        return new CartPage(driver);
    }


    public ProductPage open() {
        driver.get("https://7745.by/product/705868");
        log.info("Product page opened");
        return this;
    }
}