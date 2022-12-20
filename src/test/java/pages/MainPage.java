package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage extends Page {
//    private final WebDriver driver;
//    public Header header;
    private WebDriverWait wait;
    public Actions action;
    private static String url = "http://intershop6.skillbox.ru/";

    @FindBy(css = ".entry-title")
    public WebElement title;

    @FindBy(xpath = "//*[@class='ak-container']//span")
    public WebElement breadcrumbs;

    @FindBy(xpath = "//*[contains(@class,'product_title')]")
    public WebElement productName;

    @FindBy(xpath = "//form[@method='post']")
    public WebElement authorizationForm;

    @FindBy(xpath = "//*[@id='accesspress-breadcrumb']//span")
    public WebElement bc;

    @FindBy(xpath = "//*[@id='accesspress_storemo-2']//*[contains(@class, 'widget-title')]")
    public WebElement booksTab;

    @FindBy(xpath = "//*[@id='accesspress_storemo-3']//*[contains(@class, 'widget-title')]")
    public WebElement tabletsTab;

    @FindBy(xpath = "//*[@id='accesspress_storemo-4']//*[contains(@class, 'widget-title')]")
    public WebElement camerasTab;

    @FindBy(xpath = "//*[@id='product1']//*[@class='onsale']")
    private List<WebElement> allLabelsSale;

    @FindBy(xpath = "//*[@id='product2']//*[@class='label-new']")
    private List<WebElement> allLabelsNew;

    @FindBy(xpath = "//*[@class='promo-desc-title']")
    public WebElement promoProduct;

    @FindBy(xpath = "//*[@id='pages-2']//a[.='Регистрация']")
    public WebElement registrationLink;

    @FindBy(xpath = "//*[@class='post-title']")
    public WebElement registrationPage;

    @FindBy(xpath = "//*[@class='product_list_widget']")
    public List<WebElement> listViewedProducts;

    @FindBy(xpath = "//*[@class='cat-list-wrap']//*[@class='widget-title']")
    public WebElement titleSection;

    @FindBy(className = "site-logo")
    public WebElement siteLogo;

    public String searchText = "яблоко";

    public MainPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage open() {
        driver.navigate().to(url);
        return this;
    }

    private void waitForVisibility(WebElement element) throws Error {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollToElement(WebElement element) {
        action.scrollToElement(element).build().perform();
    }

    public String titlePromoProduct() {
        scrollToElement(promoProduct);
        waitForVisibility(promoProduct);
        return promoProduct.getText().toLowerCase();
    }

    public String getProductName() {
        return productName.getText();
    }

    public boolean hasProductsWithDiscountLabelOnSale(int index) {
        return allLabelsSale.get(index).getAttribute("class").contains("onsale");
    }

    public boolean hasProductsWithDiscountLabelNew(int index) {
        return allLabelsNew.get(index).getAttribute("class").contains("label-new");
    }

    public String getTitle() {
        return title.getText();
    }

    public String getBreadcrumbs() {
        return breadcrumbs.getText();
    }

    public List<WebElement> getListSale() {
        return allLabelsSale;
    }

    public List<WebElement> getListNew() {
        return allLabelsNew;
    }

    public void inputText() {
        header.searchInput.sendKeys(searchText);
    }

    public boolean checkoutContainsText() {
        return getTitle().toLowerCase().contains(searchText);
    }

    public boolean checkoutListViewedProducts() {
        return listViewedProducts.isEmpty();
    }

    public MainPage clickOnPromo() {
        scrollToElement(promoProduct);
        promoProduct.click();
        return this;
    }

    public String getTitleSection() {
        scrollToElement(titleSection);
        return titleSection.getText().toLowerCase();
    }
    /*
    private void assertSaleLabelsOnProducts() {
        var saleElement = driver.findElements(allLabelsSaleLocator);
        for (var productsLabel : saleElement) {
            Assert.assertTrue("Не на всех карточках товара есть лэйбл 'Скидка!'",
                    productsLabel.getAttribute("class").contains("onsale"));
        }
    }

    public void assertNewLabelsOnProducts() {
        var newElement = driver.findElements(allLabelsNewLocator);
        for (var productsLabel : newElement) {
            Assert.assertTrue("Не на всех карточках товара есть лэйбл 'Новый!'",
                    productsLabel.getAttribute("class").contains("label-new"));
        }
    }
    */
}
