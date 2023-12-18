package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {
    private final Logger log = LogManager.getLogger();

    @FindBy(xpath= "/html/body/div[2]/div[1]/div[2]/div/div[3]/div[1]/div/form/input")
    private WebElement searchInput;

    @FindBy(xpath = "/html/body/div[2]/div[1]/div[2]/div/div[3]/div[1]/div/form/button")
    private WebElement searchBtn;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public SearchPage open() {
        driver.navigate().to("https://7745.by/");
        log.info("Main page is opened");
        return this;
    }
    public void searchForProduct(String searchTerm) {
        searchInput.sendKeys(searchTerm);
        log.info("What search is input");
        searchBtn.click();
        log.info("Search");
    }
}
