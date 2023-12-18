package test;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AuthPage;
import page.FiltrationPage;
import page.SearchPage;

import java.time.Duration;

public class FiltrationTest extends CommonConditions {
    @Test
    public void testSearchProductWithSomeStats() throws InterruptedException {

        FiltrationPage filtrationPage = new FiltrationPage(driver);
        filtrationPage.open();
        filtrationPage.searchProductWithSomeStats();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[4]/div/div[2]/div[2]/h1")));

        String elementText = element.getText();
        Assert.assertTrue(elementText.toLowerCase().contains("ультрабук"), "Элемент не содержит слово 'ультрабук'");



    }
}
