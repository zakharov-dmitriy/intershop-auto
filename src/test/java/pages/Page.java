package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public Header header;

    @FindBy(css = ".entry-title")
    public WebElement title;

    @FindBy(xpath = "//*[@class='ak-container']//span")
    public WebElement breadcrumbs;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        header = new Header(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getBreadcrumbs() {
        return breadcrumbs.getText();
    }

//    public void open() {
//        driver.navigate().to(getPageUrl());
//    }
//
//    protected String getPageUrl() {
//        return "http://intershop6.skillbox.ru/";
//    }
}
