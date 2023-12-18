package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CallPage extends BasePage {
    private final Logger log = LogManager.getLogger();


    @FindBy(xpath = "/html/body/div[2]/div[1]/div[6]/div/div[3]/ul/li[1]/div")
    private WebElement callForm;
    @FindBy(xpath = "(//input[@id='recall-name'])[2]")
    private WebElement nameInput;

    @FindBy(xpath = "(//input[@id='recall-phone'])[2]")
    private WebElement phoneInput;

    @FindBy(xpath = "(//input[@value='Жду звонка'])[2]")
    private WebElement submitButton;
    @FindBy(xpath = "(//input[@name='accepted_policy'])[3]")
    private WebElement checkBox;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div[1]/div/div/span/div[1]")
    private WebElement captchaButton;
    public CallPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public CallPage open() {
        driver.navigate().to("https://7745.by/");
        log.info("Main page is opened");
        return this;
    }

    public void fillCallForm(String name, String phone) throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", callForm);
        callForm.click();
        nameInput.sendKeys(name);
        log.info("Name send");
        phoneInput.sendKeys(phone);
        log.info("Phone send");
        checkBox.click();
        log.info("CheckBox click");
        submitButton.click();
        log.info("Submit button click");
        Thread.sleep(1000);
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));
        WebElement captchaCheckbox = driver.findElement(By.xpath("//span[@id='recaptcha-anchor']/div[@class='recaptcha-checkbox-border']"));
        captchaCheckbox.click();
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
        submitButton.click();
        log.info("Submit button click");
    }
}
