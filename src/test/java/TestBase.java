import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver");
        var options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().setSize(new Dimension(1920,1080));
//        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action = new Actions(driver);
    }

    @After
    public void tearDown() throws IOException {
        var srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("/Users/dz/Documents/QA/Autotesting_Java/screens/screen.png"));
        driver.quit();
    }
}
