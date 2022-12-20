package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BonusPage extends Page {

    private static String url = "http://intershop6.skillbox.ru/bonus/";

    @FindBy(xpath = "//*[@id='bonus_main']//*[@id='bonus_username']")
    public WebElement inputFieldName;

    @FindBy(xpath = "//*[@id='bonus_main']//*[@id='bonus_phone']")
    public WebElement inputFieldPhone;

    @FindBy(xpath = "//*[@id='bonus_main']//*[@type='submit']")
    public WebElement buttonSubmit;

    @FindBy(xpath = "//*[@id='bonus_main']//h3")
    public WebElement messageSuccess;

    @FindBy(xpath = "//*[@id='bonus_main']//*[@id='bonus_content']")
    public WebElement validMessage;


    public BonusPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    private void waitForVisibility(WebElement element) throws Error {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public BonusPage open() {
        driver.navigate().to(url);
        return this;
    }

    public BonusPage fillName() {
        inputFieldName.sendKeys("Chaplin");
        return this;
    }

    public BonusPage fillPhone() {
        inputFieldPhone.sendKeys("78003025797");
        return this;
    }

    public BonusPage fillIncorrectPhone() {
        inputFieldPhone.sendKeys("003025797");
        return this;
    }

    public BonusPage clickButton() {
        buttonSubmit.click();
        return this;
    }

    public String getMessageSuccess() {
        waitForVisibility(messageSuccess);
        return messageSuccess.getText();
    }

    public boolean getValidationMessage() {
        return validMessage.getText().contains("обязательно для заполнения");
    }

    public String getValidPhoneMessage() {
        return validMessage.getText();
    }

}
