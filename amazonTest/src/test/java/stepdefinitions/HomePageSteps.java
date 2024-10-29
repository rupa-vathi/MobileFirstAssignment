package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class HomePageSteps {
    WebDriver driver;
    WebDriverWait wait;
    public HomePageSteps(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Given("I am logged into Facebook")
    public void iAmLoggedIntoFacebook() {
        driver.get("https://www.facebook.com/login");
        driver.findElement(By.id("email")).sendKeys("ursrupavathi@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Roopa@100");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleContains("Facebook"));
    }

    @When("I update my profile details")
    public void updateProfileDetails() {
        driver.get("https://www.facebook.com/profile");
        driver.findElement(By.xpath("//span[text()='Edit profile']")).click();
        WebElement bio = driver.findElement(By.xpath("//span[text()='Bio']"));
        bio.clear();
        bio.sendKeys("Updated bio information");
        driver.findElement(By.xpath("//span[text()='Save']")).click();
    }

    @Then("I should see a confirmation message for profile update")
    public void verifyProfileUpdateConfirmation() {
        WebElement bio = driver.findElement(By.xpath("//span[text()='Bio']"));
        assertEquals(bio.getText(),"Updated bio information","The updated message not texted exactly");
    }

    @When("I add a new profile picture")
    public void addNewProfilePicture() {
        driver.get("https://www.facebook.com/profile");
        driver.findElement(By.xpath("//div[@aria-label='Profile picture actions']")).click();
        WebElement uploadButton = driver.findElement(By.xpath("//input[@type='file']"));
        uploadButton.sendKeys("");
        driver.findElement(By.xpath("//button[text()='Save']")).click();
    }

    @Then("I should see the new profile picture on my profile")
    public void verifyNewProfilePicture() {
        WebElement profilePicture = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//img[contains(@src, 'new_profile_picture')]")));
        assertTrue(profilePicture.isDisplayed());
    }

    @When("I post a new status with the text {string}")
    public void postStatusUpdate(String statusText) {
        WebElement statusBox = driver.findElement(By.xpath("//textarea[@name='status']"));
        statusBox.click();
        statusBox.sendKeys(statusText);
        driver.findElement(By.xpath("//button[text()='Post']")).click();
    }
    @Then("I should see the status {string} on my timeline")
    public void verifyStatusUpdate(String expectedStatus) {
        WebElement postedStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='" + expectedStatus + "']")));
        assertTrue(postedStatus.isDisplayed());
    }
    @When("I like the first post on my feed")
    public void likeFirstPost() {
        WebElement firstPostLikeButton = driver.findElement(By.xpath("(//div[@aria-label='Like'])[1]"));
        firstPostLikeButton.click();
    }
    @Then("I should see that the post is marked as liked")
    public void verifyPostIsLiked() {
        WebElement likedIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@aria-label='Like'])[1][@aria-pressed='true']")));
        assertTrue(likedIcon.isDisplayed());
    }

    @When("I unlike the first post on my feed")
    public void unlikeFirstPost() {
        WebElement firstPostLikeButton = driver.findElement(By.xpath("(//div[@aria-label='Like'])[1]"));

        // Check if the post is already liked, then unlike it
        if (firstPostLikeButton.getAttribute("aria-pressed").equals("true")) {
            firstPostLikeButton.click();
        }
    }

    @Then("I should see that the post is not marked as liked")
    public void verifyPostIsUnliked() {
        WebElement unlikedIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@aria-label='Like'])[1][@aria-pressed='false']")));
        assertTrue(unlikedIcon.isDisplayed());
    }

    @When("I comment {string} on the first post")
    public void commentOnFirstPost(String commentText) {
        WebElement firstPostCommentBox = driver.findElement(By.xpath("(//div[@aria-label='Comment'])[1]"));
        firstPostCommentBox.click();

        WebElement commentInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@aria-label='Write a comment']")));
        commentInput.sendKeys(commentText);
        commentInput.sendKeys(Keys.RETURN);  // Press Enter to submit the comment
    }

    @Then("I should see the comment {string} under the post")
    public void verifyCommentOnPost(String expectedComment) {
        WebElement comment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), '" + expectedComment + "')]")));
        assertTrue(comment.isDisplayed());
    }

    @When("I delete the post with text {string}")
    public void deletePost(String postText) {
        WebElement postMenuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(), '" + postText + "')]/ancestor::div[@aria-label='Actions for this post']")));
        postMenuButton.click();

        WebElement deleteOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Delete']")));
        deleteOption.click();

        // Confirm deletion if prompted
        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Delete']")));
        confirmDeleteButton.click();
    }

    @Then("I should not see the post with text {string} on my timeline")
    public void verifyPostDeletion(String postText) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//span[contains(text(), '" + postText + "')]")));

        boolean postExists = driver.findElements(By.xpath("//span[contains(text(), '" + postText + "')]")).size() > 0;
        assertFalse("The post was not deleted successfully.", postExists);
    }


}
