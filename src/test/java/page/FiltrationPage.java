package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FiltrationPage extends BasePage {
    private final Logger log = LogManager.getLogger();

    @FindBy(xpath = "/html/body/div[2]/div[1]/div[2]/div/div[2]/a/span[2]")
    private WebElement catalogButton;

    @FindBy(xpath = "/html/body/div[2]/div[1]/div[3]/div/ul/li[3]")
    private WebElement targetElement;

    @FindBy(xpath = "/html/body/div[2]/div[1]/div[3]/div/div[3]/div[1]/ul[1]/li/ul/li[1]/a")
    private WebElement colorSelect;

    @FindBy(xpath = "//*[@id=\'content\']/div[4]/div[3]/div/noindex/div/form/div[15]/div[2]/div[1]/label[4]/span")
    private WebElement greenColor;

    @FindBy(xpath = "//*[@id=\'modef\']/a")
    private WebElement submitButton;


    public FiltrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public FiltrationPage open() {
        driver.navigate().to("https://7745.by/");
        log.info("Main page is opened");
        return this;
    }

    public void searchProductWithSomeStats() throws InterruptedException {
       catalogButton.click();
        targetElement.click();
        log.info("Target element is clicked");
        colorSelect.click();
        log.info("Color select is clicked");
    }
}
