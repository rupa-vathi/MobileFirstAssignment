package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class facebookLoginSteps {

    WebDriver driver = Hooks.getDriver();
    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));

    @Given("I am on the Facebook login page")
    public void iAmOnTheLoginPage() {
        driver.get("https://www.facebook.com/login");
    }

    @When("I enter valid email and password")
    public void enterValidCredentials() {
        driver.findElement(By.id("email")).sendKeys("ursrupavathi@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Roopa@100");
    }

    @When("I click Login")
    public void clickLogin() {
        driver.findElement(By.name("login")).click();
    }

    @Then("I should be redirected to the Facebook homepage")
    public void verifyHomePage() {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifications");
        assertEquals(driver.getTitle(),"Log in to Facebook","The home page title is not as expected");
        System.out.println(driver.getTitle());
        assertTrue(driver.getTitle().contains("Log in to Facebook"));
    }

    @When("I enter a valid email and an incorrect password")
    public void enterInvalidPassword() {
        driver.findElement(By.id("email")).sendKeys("ursrupavathi@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("automation@123");
    }

    @Then("I should see an error message saying {string}")
    public void verifyErrorMessage(String expectedMessage) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._9ay7")));
        String actualMessage = driver.findElement(By.cssSelector("div._9ay7")).getText();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @When("I enter an unregistered email and valid password")
    public void enterUnregisteredEmail(){
        driver.findElement(By.id("email")).sendKeys("rupavathi1234@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Roopa@100");
    }
    @When("I click Forgot Password")
    public void clickForgotPassword() {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Forgotten password?")));
        driver.findElement(By.linkText("Forgotten password?")).click();
    }
    @When("I leave the email and password fields blank")
    public void withoutDetailsEntered(){
        driver.findElement(By.id("email")).sendKeys("");
        driver.findElement(By.id("pass")).sendKeys("");
    }
    @When("I click on the Account dropdown menu")
    public void clickAccountDropdown() {
       WebElement dropdown= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Your profile']")));
       dropdown.click();
       WebElement logoutOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Log Out']")));
       logoutOption.click();
    }
    @When("I click Log Out")
    public void clickLogOut() {
        driver.findElement(By.xpath("//span[text()='Log Out']")).click();
    }
    @Then("I should be redirected to the Facebook login page")
    public void verifyRedirectToLoginPage() {
        wait.until(ExpectedConditions.urlToBe("facebook.com/login"));
        assertTrue(driver.getCurrentUrl().contains("facebook.com/login"));
    }
    @When("I attempt to login with invalid credentials {int} times")
    public void attemptLoginWithInvalidCredentials(int attempts) {
        for (int i = 0; i < attempts; i++) {
            // Enter invalid credentials
            driver.findElement(By.id("email")).sendKeys("ursrupavathi@gmail.com");
            driver.findElement(By.id("pass")).sendKeys("Roopa@1654300");
            driver.findElement(By.name("login")).click();

            // Wait for error message to display
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._9ay7")));

            // Clear fields for the next attempt
            driver.findElement(By.id("email")).clear();
            driver.findElement(By.id("pass")).clear();
        }
    }

    @Then("I should see a message saying {string} or {string}")
    public void verifyAccountRestrictionMessage(String messageOption1, String messageOption2) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div._9ay7"))).getText();
        assertTrue(actualMessage.contains(messageOption1) || actualMessage.contains(messageOption2), "");
    }
}
