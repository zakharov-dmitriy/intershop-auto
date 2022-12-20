package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
    private final WebDriver driver;
    public Header header;
    private final static String url = "http://intershop6.skillbox.ru/";

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        header = new Header(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='custom-register-button']")
    public WebElement registrationButton;

    @FindBy(css = "#reg_username")
    public WebElement inputNameRegistration;

    @FindBy(css = "#reg_email")
    public WebElement inputEmailRegistration;

    @FindBy(css = "#reg_password")
    public WebElement inputPasswordRegistration;

    @FindBy(xpath = "//*[@name='register']")
    public WebElement submitRegBtn;

    @FindBy(xpath = "//*[@class='content-page']")
    public WebElement registerMessage;

    public String username = "test-070";
    public String password = "123";

    @FindBy(xpath = "//*[@class='logout']")
    public WebElement logOutLink;

    @FindBy(css = "#username")
    public WebElement inputNameLogIn;

    @FindBy(css = "#password")
    public WebElement inputPasswordLogIn;

    @FindBy(xpath = "//*[@name='login']")
    public WebElement submitLogInBtn;

    @FindBy(xpath = "//*[@class='woocommerce-MyAccount-content']/p[1]")
    public WebElement greetingMessage;

    @FindBy(xpath = "//*[@class='woocommerce-error']/li")
    public WebElement errorMessage;

    @FindBy(xpath = "//*[contains(@class,'lost_password')]/a")
    public WebElement forgotPasswordLink;

    @FindBy(xpath = "//*[@class='post-title']")
    public WebElement recoveryPasswordTitle;

    @FindBy(css = "#user_login")
    public WebElement inputReset;

    @FindBy(css = "[value='Reset password']")
    public WebElement resetButton;

    @FindBy(css = ".woocommerce-message")
    public WebElement successMessage;

    public void open() {
        driver.navigate().to(url);
    }

    public void fillingFieldsForRegistration() {
        driver.get("http://intershop6.skillbox.ru/register/");
        inputNameRegistration.sendKeys("test-0123.36");
        inputEmailRegistration.sendKeys("test0123.36@skill.box");
        inputPasswordRegistration.sendKeys(password);
    }

    public void fillingFieldsForLogIn() {
        inputNameLogIn.sendKeys(username);
        inputPasswordLogIn.sendKeys(password);
    }

    public void logInWithIncorrectName() {
        inputNameLogIn.sendKeys(username + "9");
        inputPasswordLogIn.sendKeys(password);
        submitLogInBtn.click();
    }

    public void logInWithIncorrectPassword() {
        inputNameLogIn.sendKeys(username);
        inputPasswordLogIn.sendKeys(password + 4);
        submitLogInBtn.click();
    }

    public boolean checkoutGreetingMessage() {
        return greetingMessage.getText().contains("Привет " + username);
    }

    public boolean checkoutTextMessage() {
        return errorMessage.getText().contains("Веденный пароль для пользователя " + username + " неверный.");
    }

    public boolean checkoutErrorMessage() {
        return errorMessage.isDisplayed();
    }
}
