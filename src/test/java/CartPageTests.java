import org.junit.Assert;
import org.junit.Test;
import pages.CartPage;

public class CartPageTests extends TestBase {

    @Test
    //добавление товара в корзину
    public void addProductToCartTest() {
        //arrange
        var page = new CartPage(driver);
        page.open();
        //act
        page.categoryProduct.click();
        page.productBtn.click();
        page.clickMoreDetailed();
        //assert
        Assert.assertTrue("Форма с товаром отсутствует на странице",
                page.formInTheCart.isDisplayed());
    }

    @Test
    //удаление товара из корзины
    public void deleteProductTest() {
        //arrange
        var page = new CartPage(driver);
        page.open();
        page.addProductToCart();
        //act
        page.deleteButton.click();
        //assert
        Assert.assertTrue("Нет подтверждения об удалении товара", page.messageAboutDeleteProduct());
    }

    @Test
    //вернуть товар после удаления
    public void returnTheProductAfterRemoval() {
        //arrange
        var page = new CartPage(driver);
        page.open();
        //act
        page.returnProduct();
        //assert
        Assert.assertTrue("Товара нет в корзине", page.nameProductInTable());
    }

    @Test
    //ввод купона
    public void enteringTheCouponTest() {
        //arrange
        var page = new CartPage(driver);
        page.open();
        page.addProductToCart();
        //act
        page.addCoupon();
        //assert
        Assert.assertEquals("Неверное сообщение",
                "Coupon code applied successfully.", page.getMessageText());
        Assert.assertTrue("Скидка не применилась", page.applySale());
    }

    @Test
    //удаление купона
    public void deleteTheCouponTest() {
        //arrange
        var page = new CartPage(driver);
        page.open();
        page.addProductToCart();
        page.addCoupon();
        //act
        page.removeCoupon();
        //assert
        Assert.assertEquals(page.messageIncorrectCoupon,
                "Coupon has been removed.", page.getMessageText());
    }

    @Test
    //ввод некорректного купона
    public void enteringIncorrectCouponTest() {
        //arrange
        var page = new CartPage(driver);
        page.open();
        page.addProductToCart();
        //act
        page.addIncorrectCoupon();
        //assert
        Assert.assertEquals(page.messageIncorrectCoupon,
                "Неверный купон.", page.messageError.getText());
    }

    @Test
    //нажать на кнопку "оформление заказа" - переход на страницу
    public void clickArrangeButtonTest() {
        //arrange
        var page = new CartPage(driver);
        page.open();
        page.addProductToCart();
        //act
        page.payButton.click();
        //assert
        Assert.assertEquals(page.messageIncorrectPage,
                "оформление заказа", page.getTitleOrderPage());
    }

    @Test
    //нажать на "оформление заказа" в меню сайта - переход на страницу
    public void clickOrderOnMenuTest() {
        //arrange
        var page = new CartPage(driver);
        page.open();
        page.addProductToCart();
        //act
        var menuLinkText = page.header.makingOrederLink.getText();
        page.header.makingOrederLink.click();
        //assert
        Assert.assertEquals(page.messageIncorrectPage, page.getTitleOrderPage().toUpperCase(), menuLinkText);
    }

    @Test
    //проверка применения скидки к акционному товару
    public void checkingTheApplicationOfTheDiscountToThePromotionalProductTest() {
        //arrange
        var page = new CartPage(driver);
        page.open();
        //act
        page.clickProductWithLabelSale();
        page.addProductInCart();
        page.addCoupon();
        //assert
        Assert.assertEquals("К цене на товар с акцией не применилась скидка по купону",
                page.getPriceAfterDiscount(),
                page.getAmountTotalPriceAndDiscount());
    }
}
