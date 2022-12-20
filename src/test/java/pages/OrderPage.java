package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public Header header;
    private final static String url = "http://intershop6.skillbox.ru/shop";

    @FindBy(xpath = "//*[contains(@class,'columns-4')]/li[3]//*[@class='price-cart']/a")
    public WebElement buttonInCart;

    @FindBy(xpath = "//*[@class='price-cart']//a[contains(@class,'added_to_cart')]")
    public WebElement buttonAboutMore;

    @FindBy(xpath = "//*[@class='wc-proceed-to-checkout']//a")
    public WebElement toBePaidButton;

    @FindBy(css = ".showlogin")
    public WebElement logInLink;

    @FindBy(css = "#username")
    public WebElement inputUsernameForLogIn;

    @FindBy(css = "#password")
    public WebElement inputPasswordForLogIn;

    @FindBy(xpath = "//*[@name='login']")
    public WebElement logInButton;

    @FindBy(xpath = "//*[@class='post-title']")
    public WebElement titleOrderSection;

    @FindBy(xpath = "//*[contains(@class,'order_details')]")
    public WebElement orderDetails;

    @FindBy(css = "#billing_first_name")
    public WebElement fieldName;

    @FindBy(css = "#billing_last_name")
    public WebElement fieldSurname;

    @FindBy(css = "#billing_address_1")
    public WebElement fieldAddress;

    @FindBy(css = "#billing_city")
    public WebElement fieldCity;

    @FindBy(css = "#billing_state")
    public WebElement fieldRegion;

    @FindBy(css = "#billing_postcode")
    public WebElement fieldZipCode;

    @FindBy(css = "#billing_phone")
    public WebElement fieldPhone;

    @FindBy(css = "#place_order")
    public WebElement buttonForOrder;

    @FindBy(xpath = "//*[@for='payment_method_cod']")
    public WebElement paymentUponDelivery;

    @FindBy(xpath = "//*[contains(@class,'method')]/strong")
    public WebElement orderMethod;

    private final String username = "test-12.36";
    private final String password = "123";
    private final String nameText = "Petr";
    private final String surnameText = "Petrov";
    private final String addressText = "Mira";
    private final String cityText = "Moscow";
    private final String regionText = "Moscow";
    private final String zipCode = "337788";
    private final String phoneNumber = "84958007733";

    @FindBy(xpath = "//*[@class='showcoupon']")
    public WebElement showCouponLink;

    @FindBy(xpath = "//*[@class='input-text']")
    public WebElement inputCoupon;

    @FindBy(xpath = "//*[@class='button']")
    public WebElement couponAddBtn;

    @FindBy(xpath = "//*[@class='cart-subtotal']//bdi")
    public WebElement totalAmountOrder;

    @FindBy(xpath = "//*[contains(@class,'cart-discount')]//*[contains(@class,'amount')]")
    public WebElement discount;

    @FindBy(xpath = "//*[@class='order-total']//bdi")
    public WebElement amountToPaid;

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--orders > a")
    public WebElement ordersLink;

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--edit-account > a")
    public WebElement editAccountLink;


    public OrderPage(WebDriver driver) {
        this.driver = driver;
        header = new Header(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(url);
    }

    private void waitForVisibility(WebElement element) throws Error {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void authorizationWhenOrdering() {
        buttonInCart.click();
        waitForVisibility(buttonAboutMore);
        buttonAboutMore.click();
        toBePaidButton.click();
        logInLink.click();
        waitForVisibility(logInButton);
        inputUsernameForLogIn.sendKeys(username);
        inputPasswordForLogIn.sendKeys(password);
        logInButton.click();
    }

    public void logIn() {
        header.logInLink.click();
        inputUsernameForLogIn.sendKeys(username);
        inputPasswordForLogIn.sendKeys(password);
        logInButton.click();
    }

    public void fillingInTheFields() {
        fieldName.clear();
        fieldName.sendKeys(nameText);
        fieldSurname.clear();
        fieldSurname.sendKeys(surnameText);
        fieldAddress.clear();
        fieldAddress.sendKeys(addressText);
        fieldCity.clear();
        fieldCity.sendKeys(cityText);
        fieldRegion.clear();
        fieldRegion.sendKeys(regionText);
        fieldZipCode.clear();
        fieldZipCode.sendKeys(zipCode);
        fieldPhone.clear();
        fieldPhone.sendKeys(phoneNumber);
    }

    public void clickButtonForOrder() {
        buttonForOrder.click();
        waitForVisibility(orderDetails);
    }

    public String getTitle() {
        return titleOrderSection.getText();
    }

    public String getNumber(String number) {
        int end = number.lastIndexOf(",00₽");
        return number.substring(0, end);
    }

    public void clickCouponLink() {
        showCouponLink.click();
        waitForVisibility(inputCoupon);
    }

    public void applyCoupon() {
        inputCoupon.sendKeys("sert500");
        couponAddBtn.click();
        waitForVisibility(discount);
    }

    public int getDifferencePrice() {
        return Integer.parseInt(getNumber(totalAmountOrder.getText())) -
                Integer.parseInt(getNumber(discount.getText()));
    }

    public int getAmountToPaid() {
       return Integer.parseInt(getNumber(amountToPaid.getText()));
    }

}
