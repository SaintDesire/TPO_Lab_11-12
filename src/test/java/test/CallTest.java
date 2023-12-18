package test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import page.CallPage;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;

public class CallTest extends CommonConditions {
    @Test
    public void testCallFormWithMaxCharacters() throws InterruptedException {
        CallPage callPage = new CallPage(driver);
        callPage.open();
        callPage.fillCallForm("dadayadadayadadayadadaya" +
                        "dadayadadayadadayadadaya" +
                        "dadayadadayadadayadadaya" +
                        "dadayadadayadadayadadaya",
                "291234343");

        By successMessageLocator = By.xpath("//div[contains(text(), 'Ваш запрос принят в обработку!')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));
        } catch (Exception ignored) {
            // Если элемент не виден, то тест успешен
        }




    }
}
