package page;

import model.User;
import org.openqa.selenium.By;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import service.TestDataReader;
import service.UserFactory;

public class AuthPage extends BasePage {
    private final Logger log = LogManager.getLogger();

    @FindBy(xpath = "//div[@class='open-logon open-login']//div//input[@id='login-modal-input-login']")
    private WebElement numberInput;
    @FindBy(xpath = "//div[@class='open-logon open-login']//div//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//div[@class='open-logon open-login']//div//input[@value='Войти']")
    private WebElement continueButton;

    public AuthPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public AuthPage open() {
        driver.navigate().to("https://7745.by/");
        log.info("Main page is opened");
        driver.findElement(By.xpath("//*[@id=\"logon-link\"]")).click();
        log.info("Auth page is opened");
        return this;
    }

    public User getUser() {
        return UserFactory.getUserInfo();
    }

    public AuthPage fillNumberAndPassword() throws InterruptedException {
        String number = TestDataReader.getTestData("number");
        String password = TestDataReader.getTestData("password");

        for (int i = 0; i < number.length(); i++) {
            char digit = number.charAt(i);
            wait.until(ExpectedConditions.elementToBeClickable(numberInput)).sendKeys(Character.toString(digit));
        }
        log.info("Number is filled");

        Thread.sleep(500);
        for (int i = 0; i < number.length() + 1; i++) {
            char digit = password.charAt(i);
            wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(Character.toString(digit));
        }
        log.info("Password is filled");

        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        log.info("Continue button is clicked");
        Thread.sleep(10000);
        // Проверка успешной авторизации
        java.time.Duration timeout = java.time.Duration.ofSeconds(3);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.withTimeout(timeout)
                    .ignoring(TimeoutException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='header-icon__text']")));
            log.info("User login failed");
        } catch (TimeoutException e) {
            log.info("User is successfully logged in");
            // Добавим проверку, перенаправлены ли мы на главную страницу после авторизации
            try {
                wait.until(ExpectedConditions.urlToBe("https://7745.by/"));
                log.info("Redirected to the main page. User is authenticated.");
            } catch (TimeoutException ex) {
                log.error("Redirect to the main page failed. Authentication might have an issue.");
            }
            return this;
        }
        throw new AssertionError("User login failed");
    }
}
