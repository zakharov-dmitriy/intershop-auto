package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CatalogPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static String url = "http://intershop6.skillbox.ru/";

    @FindBy(css = ".entry-title")
    public WebElement title;

    @FindBy(xpath = "//*[@class='search-field']")
    public WebElement searchInput;

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@class='price-cart']//a")
    public WebElement buttonInCart;

    @FindBy(xpath = "//*[@class='price-cart']//a[contains(@class,'added_to_cart')]")
    public WebElement buttonMore;

    @FindBy(xpath = "//*[@class='woocommerce-cart-form']")
    public WebElement formInTheCart;

    @FindBy(xpath = "//*[contains(@class,'columns-4')]/li[1]//h3")
    public WebElement nameProductInCatalog;

    @FindBy(xpath = "//*[@class='woocommerce-cart-form']//*[@data-title='Товар']/a")
    public WebElement nameProductInCart;

    public String searchText = "холодильник";

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(url);
    }

//    @Override
//    protected String getPageUrl() {
//        return url;
//    }

    private void waitForVisibility(WebElement element) throws Error {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void searchProduct() {
        searchInput.sendKeys(searchText);
        searchButton.click();
    }

    public boolean checkoutContains() {
        return title.getText().toLowerCase().contains(searchText);
    }

    public void clickButton() {
        buttonInCart.click();
        waitForVisibility(buttonMore);
    }

    public void addToCart() {
        buttonInCart.click();
        waitForVisibility(buttonMore);
        buttonMore.click();
    }

    public String getNameProductInCatalog() {
        return nameProductInCatalog.getText();
    }

    public String getNameProductInCart() {
        return nameProductInCart.getText();
    }
}
