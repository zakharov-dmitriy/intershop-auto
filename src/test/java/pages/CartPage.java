package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public Header header;
    private static String url = "http://intershop6.skillbox.ru/shop";

    public CartPage(WebDriver driver) {
        this.driver = driver;
        header = new Header(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='product-categories']//*[.='Холодильники']")
    public WebElement categoryProduct;

    @FindBy(xpath = "//*[contains(@class,'columns-4')]/li[2]//*[@class='price-cart']//a")
    public WebElement productBtn;

    @FindBy(xpath = "//*[contains(@class,'wc-forward')]")
    public WebElement moreAboutSelectProduct;

    @FindBy(xpath = "//*[@class='woocommerce-cart-form']")
    public WebElement formInTheCart;

    @FindBy(xpath = "//*[@class='product-remove']/a")
    public WebElement deleteButton;

    @FindBy(xpath = "//*[@class='restore-item']")
    public WebElement returnLink;

    @FindBy(xpath = "//*[@class='woocommerce-message']")
    public WebElement messageAboutDelete;

    @FindBy(xpath = "//*[contains(@class,'cart_item')]//*[@class='product-name']")
    public WebElement nameProductInTable;

    @FindBy(css = "[name='coupon_code']")
    public WebElement inputCoupon;

    @FindBy(css = "[name='apply_coupon']")
    public WebElement applyCouponButton;

    @FindBy(xpath = "//*[@class='woocommerce-message']")
    public WebElement messageText;

    @FindBy(css = ".cart-discount")
    public WebElement sale;

    @FindBy(xpath = "//*[@class='woocommerce-remove-coupon']")
    public WebElement deleteCouponLink;

    @FindBy(xpath = "//*[@class='woocommerce-error']")
    public WebElement messageError;

    @FindBy(xpath = "//*[@class='wc-proceed-to-checkout']//a")
    public WebElement payButton;

    @FindBy(xpath = "//*[@class='post-title']")
    public WebElement makingOrderTitle;

    @FindBy(xpath = "//*[contains(@class,'columns-4')]/li[3]//*[@class='inner-img']//a")
    public WebElement productWithLabelSale;

    @FindBy(xpath = "//*[@name='add-to-cart']")
    public WebElement buttonAddToCart;

    @FindBy(xpath = "//*[@class='woocommerce-message']//a")
    public WebElement moreLink;

    @FindBy(xpath = "//*[@class='cart-subtotal']//bdi")
    public WebElement totalPrice;

    @FindBy(xpath = "//*[@class='order-total']//bdi")
    public WebElement totalOrderPrice;

    @FindBy(xpath = "//*[contains(@class,'cart-discount')]//*[contains(@class,'amount')]")
    public WebElement discountAmount;

    private final String couponText = "sert500";
    public final String messageIncorrectCoupon = "Неверное действие с купоном";
    public final String messageIncorrectPage = "Неверная страница";

    public void open() {
        driver.navigate().to(url);
    }

    private void waitForVisibility(WebElement element) throws Error {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForInvisibility(WebElement element) throws Error {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void addProductToCart() {
        categoryProduct.click();
        productBtn.click();
        waitForVisibility(moreAboutSelectProduct);
        moreAboutSelectProduct.click();
    }

    public void clickMoreDetailed() {
        waitForVisibility(moreAboutSelectProduct);
        moreAboutSelectProduct.click();
    }

    public boolean messageAboutDeleteProduct() {
        waitForVisibility(messageAboutDelete);
        return messageAboutDelete.getText().contains("удален");
    }

    public void returnProduct() {
        addProductToCart();
        deleteButton.click();
        waitForVisibility(returnLink);
        returnLink.click();
    }

    public boolean nameProductInTable() {
        waitForVisibility(nameProductInTable);
        return nameProductInTable.isDisplayed();
    }

    public void addCoupon() {
        inputCoupon.sendKeys(couponText);
        applyCouponButton.click();
        waitForVisibility(sale);
    }

    public void addCoupon(String coupon) {
        inputCoupon.sendKeys(coupon);
        applyCouponButton.click();
    }

    public String getMessageText() {
        waitForVisibility(messageText);
        return messageText.getText();
    }

    public boolean applySale() {
        waitForVisibility(sale);
        return sale.isDisplayed();
    }

    public void removeCoupon() {
        waitForVisibility(deleteCouponLink);
        deleteCouponLink.click();
        waitForInvisibility(sale);
    }

    public void addIncorrectCoupon() {
        addCoupon(couponText + "5");
        waitForVisibility(messageError);
    }

    public String getTitleOrderPage() {
        return makingOrderTitle.getText().toLowerCase();
    }

    public void clickProductWithLabelSale() {
        categoryProduct.click();
        productWithLabelSale.click();
    }

    public void addProductInCart() {
        buttonAddToCart.click();
        moreLink.click();
    }

    public static String getNumber(String number) {
        int end = number.lastIndexOf(",00₽");
        return number.substring(0, end);
    }

    public int getAmountTotalPriceAndDiscount() {
        return Integer.parseInt(getNumber(totalOrderPrice.getText())) +
                Integer.parseInt(getNumber(discountAmount.getText()));
    }

    public int getPriceAfterDiscount() {
        return Integer.parseInt(getNumber(totalPrice.getText()));
    }
}
