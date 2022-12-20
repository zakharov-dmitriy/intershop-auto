import org.junit.Assert;
import org.junit.Test;
import pages.OrderPage;

public class OrderRegistrationPageTests extends TestBase{
    private final String invalidSection = "Открыт неверный раздел";
    private final String invalidMessage = "Неверное сообщение о заказе";

    @Test
    //оформление заказа, заполнение всех полей
    public void fieldsAreFullTest() {
        //arrange
        var page = new OrderPage(driver);
        page.open();
        page.authorizationWhenOrdering();
        //act
        page.fillingInTheFields();
        page.clickButtonForOrder();
        //assert
        Assert.assertEquals(invalidMessage, "ЗАКАЗ ПОЛУЧЕН", page.getTitle());
    }

    @Test
    //оформление заказа, когда оплата при получении товара
    public void makingAnOrderWithPaymentUponReceiptTest() {
        //arrange
        var page = new OrderPage(driver);
        page.open();
        page.authorizationWhenOrdering();
        //act
        page.fillingInTheFields();
        var methodPaymentText = page.paymentUponDelivery.getText();
        page.paymentUponDelivery.click();
        page.clickButtonForOrder();
        //assert
        Assert.assertEquals(invalidMessage, "ЗАКАЗ ПОЛУЧЕН", page.getTitle());
        Assert.assertEquals("Неверный тип оплаты заказа", page.orderMethod.getText(), methodPaymentText);
    }

    @Test
    //проверка корректного применения купона и пересчета итоговой суммы
    public void addedCouponTest() {
        //arrange
        var page = new OrderPage(driver);
        page.open();
        page.authorizationWhenOrdering();
        //act
        page.clickCouponLink();
        page.applyCoupon();
        //assert
        Assert.assertEquals("Итоговая сумма к оплате неверна",
                page.getDifferencePrice(), page.getAmountToPaid());
    }

    @Test
    //проверка при нажатии на ссылку "Заказы" открывается раздел с заказами
    public void checkOrderSectionOnMyAccountTest() {
        //arrange
        var page = new OrderPage(driver);
        page.open();
        page.logIn();
        //act
        page.ordersLink.click();
        //assert
        Assert.assertEquals(invalidSection, "ЗАКАЗЫ", page.getTitle());
    }

    @Test
    //проверка при нажатии на ссылку "Данные аккаунта" открывается соответствующий раздел
    public void checkEditAccountOnMyAccountTest() {
        //arrange
        var page = new OrderPage(driver);
        page.open();
        page.logIn();
        //act
        var expectedResult = page.editAccountLink.getText();
        page.editAccountLink.click();
        //assert
        Assert.assertEquals(invalidSection,
                expectedResult.contains("Данные аккаунта"),
                page.getTitle().contains("ДАННЫЕ УЧЕТНОЙ ЗАПИСИ"));
    }
}
