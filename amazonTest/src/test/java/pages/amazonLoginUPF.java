package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class amazonLoginUPF {
WebDriver driver;
WebDriverWait wait;
public amazonLoginUPF(WebDriver driver){
    this.driver=driver;
    this.wait=new WebDriverWait(driver, Duration.ofSeconds(30));
    PageFactory.initElements(driver,this);
}
}
