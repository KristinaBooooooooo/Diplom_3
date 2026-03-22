package ru.practikum.kristinabogatova;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.practikum.kristinabogatova.pages.Header;
import ru.practikum.kristinabogatova.pages.LoginPage;
import ru.practikum.kristinabogatova.pages.MainPage;
import ru.practikum.kristinabogatova.pages.RegisterPage;
import ru.practikum.kristinabogatova.utils.WebDriverUtils;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest {

    private MainPage mainPage;
    private Header header;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        var driver = WebDriverUtils.create();
        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        header = new Header(driver);
        mainPage.openPage();
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверяет, что пользователь успешно регистрируется с корректными данными")
    public void successfulRegistrationTest() {
        header.clickPersonalAccount();
        loginPage.clickRegisterLink();
        registerPage.waitForPage();
        String unique = UUID.randomUUID().toString().substring(0, 8);
        String name = "auto" + unique;
        String email = "auto_" + unique + "@mail.com";
        String password = "Password1";

        registerPage.register(name, email, password);

        assertTrue(loginPage.waitForPage());
    }

    @Test
    @DisplayName("Ошибка при коротком пароле")
    @Description("Проверяет, что при пароле меньше шести символов отображается ошибка")
    public void shortPasswordShouldFailTest() {
        header.clickPersonalAccount();
        loginPage.clickRegisterLink();
        registerPage.waitForPage();
        String unique = UUID.randomUUID().toString().substring(0, 8);
        String name = "auto" + unique;
        String email = "auto_" + unique + "@mail.com";
        String shortPassword = "123"; // короче 6

        registerPage.register(name, email, shortPassword);

        assertTrue(registerPage.isPasswordErrorDisplayed());
        assertEquals("Некорректный пароль", registerPage.getPasswordErrorText());
    }

    @After
    public void tearDown() {
        mainPage.close();
    }
}