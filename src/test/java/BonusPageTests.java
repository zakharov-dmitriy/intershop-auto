import org.junit.Assert;
import org.junit.Test;
import pages.BonusPage;

public class BonusPageTests extends TestBase {
    private final String assertMessage = "Нет сообщения о валидации полей";

    @Test
    public void registrationOfBonusCardEnteringValidValuesTest() {
        //act
        var page = new BonusPage(driver)
                .open()
                .fillName()
                .fillPhone()
                .clickButton();
        //assert
        Assert.assertEquals("Нет сообщения об успешном оформлении карты",
                "Ваша карта оформлена!", page.getMessageSuccess());
    }

    @Test
    public void attemptToRegisterWithEmptyFieldsTest() {
        //act
        var page = new BonusPage(driver)
                .open()
                .clickButton();
        //assert
        System.out.println(page.getValidationMessage());
        Assert.assertTrue(assertMessage, page.getValidationMessage());
    }

    @Test
    public void attemptToRegisterWithIncorrectPhoneTest() {
        //act
        var page = new BonusPage(driver)
                .open()
                .fillName()
                .fillIncorrectPhone()
                .clickButton();
        //assert
        System.out.println(page.getValidationMessage());
        Assert.assertEquals(assertMessage,
                "Введен неверный формат телефона", page.getValidPhoneMessage());
    }

    @Test
    public void attemptToRegisterWithoutNameTest() {
        //act
        var page = new BonusPage(driver)
                .open()
                .fillPhone()
                .clickButton();
        //assert
        System.out.println(page.getValidationMessage());
        Assert.assertTrue(assertMessage, page.getValidationMessage());
    }

    @Test
    public void attemptToRegisterWithoutPhoneTest() {
        //act
        var page = new BonusPage(driver)
                .open()
                .fillName()
                .clickButton();
        //assert
        System.out.println(page.getValidationMessage());
        Assert.assertTrue(assertMessage, page.getValidationMessage());
    }
}
