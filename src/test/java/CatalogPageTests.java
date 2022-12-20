import org.junit.Assert;
import org.junit.Test;
import pages.CatalogPage;

public class CatalogPageTests extends TestBase {

    @Test
    //перейти в каталог используя поиск по сайту
    public void goToTheCatalogUsingTheSearchTest() {
        //arrange
        var page = new CatalogPage(driver);
        page.open();
        //act
        page.searchProduct();
        //assert
        Assert.assertTrue("Неверный поиск, не совпадает с результатом", page.checkoutContains());
    }

    @Test
    //проверить кнопку "подробнее"
    public void checkTheButtonOnProductCardTest() {
        //arrange
        var page = new CatalogPage(driver);
        page.open();
        page.searchProduct();
        //act
        page.clickButton();
        //assert
        Assert.assertTrue("Кнопка 'Подробнее' не отображается", page.buttonMore.isDisplayed());
    }

    @Test
    //добавить товар в корзину
    public void addProductToTheCartTest() {
        //arrange
        var page = new CatalogPage(driver);
        page.open();
        page.searchProduct();
        //act
        var nameProductInCatalog = page.getNameProductInCatalog();
        page.addToCart();
        var nameProductInCart = page.getNameProductInCart();
        //assert
        Assert.assertEquals("Название товара не совпадает",
                nameProductInCart, nameProductInCatalog);
        Assert.assertTrue("Форма с товаром отсутствует на странице",
                page.formInTheCart.isDisplayed());
    }
}
