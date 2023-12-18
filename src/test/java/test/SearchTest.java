package test;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AuthPage;
import page.CallPage;
import page.SearchPage;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;

public class SearchTest extends CommonConditions {

    @Test
    public void testSearch() throws InterruptedException {

        String expTerm = "ноутбук";
        String searchTerm = "ноутбук";

        SearchPage searchPage = new SearchPage(driver);
        searchPage.open();
        searchPage.searchForProduct(searchTerm);

        By searchResultLocator = By.xpath("/html/body/div[2]/div[1]/div[4]/div/div[2]/div[2]/div[6]/div[1]/div[1]/div/div[2]/div[1]/div[1]/div/a");
        WebElement searchResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(searchResultLocator));

        String productTitle = searchResult.getText();
        Assert.assertTrue(productTitle.toLowerCase().contains(expTerm), "Товар с названием не содержит слово 'ноутбук'");
    }
}
