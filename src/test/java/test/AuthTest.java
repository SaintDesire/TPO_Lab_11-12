package test;

import model.User;
import org.testng.annotations.Test;
import page.AuthPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class AuthTest extends CommonConditions{
    @Test
    public void testAuthWithCorrectData() throws InterruptedException {
        User user = new AuthPage(driver)
                .open()
                .fillNumberAndPassword()
                .getUser();

        assertThat("User is authorized", !user.getNumber().isEmpty());
    }
}
