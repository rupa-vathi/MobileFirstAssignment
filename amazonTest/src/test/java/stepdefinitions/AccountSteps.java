package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class AccountSteps {
    WebDriver driver;
    WebDriverWait wait;
    public AccountSteps(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I update my account settings")
    public void updateAccountSettings() {
        WebElement nameField = driver.findElement(By.id("nameId"));
        nameField.clear();
        nameField.sendKeys("RupaV");
        driver.findElement(By.xpath("//button[text()='Save Changes']")).click();
    }

    @Then("I should see a confirmation message for settings update")
    public void verifySettingsUpdateConfirmation() {
        WebElement confirmationMessage = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), 'Your settings have been updated')]")));
        assertTrue(confirmationMessage.isDisplayed());
    }

    @When("I search for the user {string}")
    public void searchForUser(String userName) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(userName);
        searchBox.submit();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), '" + userName + "')]")));
    }

    @When("I send a friend request to {string}")
    public void sendFriendRequest(String userName) {
        WebElement userResult = driver.findElement(By.xpath("//span[contains(text(), '" + userName + "')]"));
        userResult.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[text()='Add Friend']"))).click();
    }

    @Then("I should see a message saying {string} to {string}")
    public void verifyFriendRequestSentMessage(String expectedMessage, String userName) {
        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), '" + expectedMessage + "')]")));
        assertTrue(message.isDisplayed());
    }

    @When("I go to friend requests")
    public void goToFriendRequests() {
        driver.findElement(By.xpath("//span[text()='Friends']")).click();
        driver.findElement(By.xpath("//span[text()='Friend Requests']")).click();
    }

    @When("I accept the friend request from {string}")
    public void acceptFriendRequest(String requesterName) {
        WebElement friendRequest = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), '" + requesterName + "')]/ancestor::div//button[text()='Confirm']")));
        friendRequest.click();
    }

    @Then("I should see {string} in my friend list")
    public void verifyFriendInList(String friendName) {
        driver.findElement(By.xpath("//span[text()='Friends']")).click();
        WebElement friend = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), '" + friendName + "')]")));
        assertTrue(friend.isDisplayed());
    }

    @When("I block the user {string}")
    public void blockUser(String userName) {
        searchForUser(userName);
        WebElement userResult = driver.findElement(By.xpath("//span[contains(text(), '" + userName + "')]"));
        userResult.click();
        driver.findElement(By.xpath("//button[text()='...']")).click(); // Options menu
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Block']"))).click();
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();
    }

    @Then("I should see a confirmation message saying {string}")
    public void verifyBlockConfirmationMessage(String expectedMessage) {
        WebElement confirmationMessage = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), '" + expectedMessage + "')]")));
        assertTrue(confirmationMessage.isDisplayed());
    }

    @When("I search for {string}")
    public void searchFunction(String query) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    @Then("I should see search results related to {string}")
    public void verifySearchResults(String query) {
        WebElement result = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), '" + query + "')]")));
        assertTrue(result.isDisplayed());
    }

    @When("I click on the latest notification")
    public void clickOnLatestNotification() {
        driver.findElement(By.id("notifications_jewel")).click();
        WebElement latestNotification = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@aria-label='Notifications'])[1]")));
        latestNotification.click();
    }

    @Then("I should be taken to the notification's page")
    public void verifyNotificationPage() {
        assertTrue(driver.getCurrentUrl().contains("notifications"));
    }

}
