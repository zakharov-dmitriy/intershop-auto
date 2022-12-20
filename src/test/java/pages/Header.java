package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {

    @FindBy(xpath = ("//*[@id='menu-item-46']/a"))
    public WebElement catalogLink;

    @FindBy(xpath = ("//*[@id='menu-item-30']/a"))
    public WebElement myAccountLink;

    @FindBy(xpath = ("//*[@id='menu-item-29']/a"))
    public WebElement cartLink;

    @FindBy(xpath = "//*[@id='menu-item-31']/a")
    public WebElement makingOrederLink;

    @FindBy(xpath = "//*[@id='menu-item-363']/a")
    public WebElement bonusLink;

    @FindBy(xpath = "//*[@class='login-woocommerce']//a")
    public WebElement logInLink;

    @FindBy(xpath = "//*[@class='search-field']")
    public WebElement searchInput;

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement searchButton;

    private final WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Page clickOnCatalog() {
        catalogLink.click();
        return new Page(driver);
    }

    public Page clickOnMyAccount() {
        myAccountLink.click();
        return new Page(driver);
    }

    public Page clickOnCart() {
        cartLink.click();
        return new Page(driver);
    }

    public Page clickMakingOreder() {
        makingOrederLink.click();
        return new Page(driver);
    }
}
