import org.junit.Assert;
import org.junit.Test;
import pages.LogInPage;

public class AuthorizationPageTests extends TestBase {

    private final String message = "Нет сообщения об ошибке";

    @Test
    //регистрация нового пользователя
    public void registrationNewAccountPageTest() {
        //arrange
        var page = new LogInPage(driver);
        page.open();
        //act
        page.header.logInLink.click();
        page.registrationButton.click();
        page.fillingFieldsForRegistration();
        page.submitRegBtn.click();
        //assert
        Assert.assertEquals("Сообщения об успешной регистрации нет",
                "Регистрация завершена", page.registerMessage.getText());
    }

    @Test
    //успешная авторизация
    public void correctAuthorizationPageTest() {
        //arrange
        var page = new LogInPage(driver);
        page.open();
        //act
        page.header.logInLink.click();
        page.fillingFieldsForLogIn();
        page.submitLogInBtn.click();
        //assert
        Assert.assertTrue("Авторизация не прошла", page.checkoutGreetingMessage());
    }

    @Test
    //Ввод неверного имени при авторизации. Предупреждающее сообщение
    public void invalidNameAuthorizationPageTest() {
        //arrange
        var page = new LogInPage(driver);
        page.open();
        //act
        page.header.logInLink.click();
        page.logInWithIncorrectName();
        //assert
        Assert.assertTrue(message, page.checkoutErrorMessage());
        Assert.assertEquals(message,
                "Неизвестное имя пользователя. Попробуйте еще раз или укажите адрес почты.",
                page.errorMessage.getText());
    }

    @Test
    //Ввод неверного пароля при авторизации. Предупреждающе сообщение
    public void invalidPasswordAuthorizationPageTest() {
        //arrange
        var page = new LogInPage(driver);
        page.open();
        //act
        page.header.logInLink.click();
        page.logInWithIncorrectPassword();
        //assert
        Assert.assertTrue(message, page.checkoutErrorMessage());
        Assert.assertTrue(message, page.checkoutTextMessage());
    }

    @Test
    //проверка страницы для сброса пароля
    public void checkThePasswordResetPageTest() {
        //arrange
        var page = new LogInPage(driver);
        page.open();
        //act
        page.header.logInLink.click();
        page.forgotPasswordLink.click();
        //assert
        Assert.assertEquals("Нет страницы для восстановления пароля",
                "ВОССТАНОВЛЕНИЕ ПАРОЛЯ", page.recoveryPasswordTitle.getText());
    }

    @Test
    //сброс пароля
    public void passwordResetTest() {
        //arrange
        var page = new LogInPage(driver);
        page.open();
        //act
        page.header.logInLink.click();
        page.forgotPasswordLink.click();
        page.inputReset.sendKeys(page.username);
        page.resetButton.click();
        //assert
        Assert.assertTrue("Сообщения нет на странице", page.successMessage.isDisplayed());
    }
}
