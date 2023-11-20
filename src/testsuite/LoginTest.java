package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login ";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        //Enter username
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        //Enter password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        //Click on login button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //Verify the text
        String expectedText = "Secure Area";
        String actualText = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //Enter wrong username
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        //Enter password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        //Click on login button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //Verify the text
        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMsg = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //Enter username
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        //Enter wrong password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        //Click on login button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //Verify the error message
        String expectedErrorMessage = "Your password is invalid!";
        String actualErrorMsg = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
