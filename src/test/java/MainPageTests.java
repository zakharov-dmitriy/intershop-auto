import org.junit.Assert;
import org.junit.Test;
import pages.MainPage;

public class MainPageTests extends TestBase {
    private final String messageInCaseOfFailedTests = "Неверное название раздела сайта";
    private final String messageInCaseOfFailedTestsOnBreadCrumbs = "Неверное название раздела по 'хлебным крошкам'";

    @Test
    public void clickCatalogOnMenuTest() {
        //arrange
        var page = new MainPage(driver).open();
        //act
        page.header.clickOnCatalog();
        //assert
        Assert.assertEquals(messageInCaseOfFailedTests, "КАТАЛОГ", page.getTitle());
        Assert.assertEquals(messageInCaseOfFailedTestsOnBreadCrumbs,
                page.getBreadcrumbs().toUpperCase(), page.getTitle());
    }

    @Test
    public void clickMyAccountOnMenuTest() {
        //arrange
        var page = new MainPage(driver).open();
        //act
        page.header.clickOnMyAccount();
        //assert
        Assert.assertTrue("Формы авторизации нет на странице", page.authorizationForm.isDisplayed());
        Assert.assertEquals(messageInCaseOfFailedTests, "Мой Аккаунт", page.getBreadcrumbs());
    }

    @Test
    public void clickCartOnMenuTest() {
        //arrange
        var page = new MainPage(driver).open();
        //act
        page.header.clickOnCart();
        //assert
        Assert.assertEquals(messageInCaseOfFailedTests, "Корзина", page.getBreadcrumbs());
    }

    @Test
    public void clickOnBooksCategoryOnPromoSectionTest() {
        //arrange
        var page = new MainPage(driver).open();
        //act
        page.booksTab.click();
        //assert
        Assert.assertEquals(messageInCaseOfFailedTests, "КНИГИ", page.getTitle());
        Assert.assertEquals(messageInCaseOfFailedTestsOnBreadCrumbs,
                page.getTitle(), page.getBreadcrumbs().toUpperCase());
    }

    @Test
    public void clickOnTabletsCategoryOnPromoSectionTest() {
        //arrange
        var page = new MainPage(driver).open();
        //act
        page.tabletsTab.click();
        //assert
        Assert.assertEquals(messageInCaseOfFailedTests, "ПЛАНШЕТЫ", page.getTitle());
        Assert.assertEquals(messageInCaseOfFailedTestsOnBreadCrumbs,
                page.getTitle(), page.getBreadcrumbs().toUpperCase());
    }

    @Test
    public void clickOnCamerasCategoryOnPromoSectionTest() {
        //arrange
        var page = new MainPage(driver).open();
        //act
        page.camerasTab.click();
        //assert
        Assert.assertEquals(messageInCaseOfFailedTests, "ФОТО/ВИДЕО", page.getTitle());
        Assert.assertEquals(messageInCaseOfFailedTestsOnBreadCrumbs,
                page.getTitle(), page.getBreadcrumbs().toUpperCase());
    }

    @Test
    //ссылка на промо товар открывает верную страницу с товаром
    public void goToPromoProductTest() {
        //arrange
        var page = new MainPage(driver).open();
        //act
        var titlePromoProduct = page.titlePromoProduct();
        page.promoProduct.click();
        //assert
        Assert.assertEquals("Товар не совпадает",
                page.getProductName().toLowerCase().contains("ipad"),
                titlePromoProduct.contains("ipad"));
    }

    @Test
    public void clickToLinkLoginTest() {
        //arrange
        var page = new MainPage(driver).open();
        //act
        page.header.logInLink.click();
        //assert
        Assert.assertTrue("Формы авторизации нет на странице", page.authorizationForm.isDisplayed());
    }

    @Test
    public void searchOnThePageTest() {
        //arrange
        var page = new MainPage(driver).open();
        //act
        page.inputText();
        page.header.searchButton.click();
        //assert
        Assert.assertTrue("Неверный поиск", page.checkoutContainsText());
    }

    @Test
    public void checkingLabelsOnThePageTest() {
        //act
        var page = new MainPage(driver).open();
        //assert
        assertSaleLabelsOnProducts(page);
        assertNewLabelsOnProducts(page);
    }

    private void assertSaleLabelsOnProducts(MainPage page) {
        for (int i = 0; i < page.getListSale().size(); i++) {
            Assert.assertTrue("У товара нет лейбла 'Скидка!'", page.hasProductsWithDiscountLabelOnSale(i));
        }
    }

    private void assertNewLabelsOnProducts(MainPage page) {
        for (int i = 0; i < page.getListNew().size(); i++) {
            Assert.assertTrue("У товара нет лейбла 'Новый!'", page.hasProductsWithDiscountLabelNew(i));
        }
    }

    @Test
    //нажать на ссылку Регистрация в футере, убедиться в правильном переходе
    public void goToRegistrationPageTest() {
        //arrange
        var page = new MainPage(driver).open();
        //act
        page.registrationLink.click();
        //assert
        Assert.assertEquals("Страница не для регистрации",
                page.registrationPage.getText(), page.registrationLink.getText().toUpperCase());
    }

    @Test
    public void havingSectionViewedProductsTest() {
        //arrange
        var page = new MainPage(driver).open().clickOnPromo();
        page.siteLogo.click();
        //assert
        Assert.assertFalse("Такого раздела нет на странице", page.checkoutListViewedProducts());
        Assert.assertEquals("Такого заголовка нет", "просмотренные товары", page.getTitleSection());
    }
}