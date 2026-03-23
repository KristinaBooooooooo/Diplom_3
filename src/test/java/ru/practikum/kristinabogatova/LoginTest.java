package ru.practikum.kristinabogatova;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import ru.practikum.kristinabogatova.api.UserClient;
import ru.practikum.kristinabogatova.generator.UserDataGenerator;
import ru.practikum.kristinabogatova.pages.ForgotPasswordPage;
import ru.practikum.kristinabogatova.pages.Header;
import ru.practikum.kristinabogatova.pages.LoginPage;
import ru.practikum.kristinabogatova.pages.MainPage;
import ru.practikum.kristinabogatova.pages.RegisterPage;
import ru.practikum.kristinabogatova.utils.WebDriverUtils;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private Header header;
    private UserClient userClient;

    private String email;
    private String password;
    private String name;
    private String accessToken;

    @Before
    public void setUp() {
        email = UserDataGenerator.getEmail();
        password = UserDataGenerator.getPassword();
        name = UserDataGenerator.getName();
        userClient = new UserClient();
        Response resp = userClient.createUser(email, password, name);
        accessToken = resp.path("accessToken");
        boolean userCreated = resp.statusCode() == 200 || resp.statusCode() == 201;
        Assume.assumeTrue(userCreated);

        var driver = WebDriverUtils.create();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        header = new Header(driver);
        mainPage.openPage();
    }

    @Test
    @DisplayName("Логин через кнопку Войти в аккаунт")
    @Description("Проверяет вход в аккаунт через кнопку Войти в аккаунт на главной странице")
    public void loginViaMainButtonTest() {
        mainPage.clickLoginButton();
        loginPage.waitForPage();

        loginPage.login(email, password);

        assertTrue("Отображается кнопка Оформить заказ", mainPage.isDisplayedOrderButton());
    }

    @Test
    @DisplayName("Логин через кнопку Личный кабинет")
    @Description("Проверяет вход в аккаунт через кнопку Личный кабинет")
    public void loginViaPersonalAccountTest() {
        header.clickPersonalAccount();
        loginPage.waitForPage();

        loginPage.login(email, password);

        assertTrue("Отображается кнопка Оформить заказ", mainPage.isDisplayedOrderButton());
    }

    @Test
    @DisplayName("Логин через кнопку в форме регистрации")
    @Description("Проверяет вход в аккаунт через кнопку Войти в форме регистрации")
    public void loginViaRegisterTest() {
        header.clickPersonalAccount();
        loginPage.waitForPage();
        loginPage.clickRegisterLink();
        registerPage.waitForPage();
        registerPage.clickLoginLink();
        loginPage.waitForPage();

        loginPage.login(email, password);

        assertTrue("Отображается кнопка Оформить заказ", mainPage.isDisplayedOrderButton());
    }

    @Test
    @DisplayName("Логин через кнопку в форме восстановления пароля")
    @Description("Проверяет вход в аккаунт через кнопку Войти в форме восстановления пароля")
    public void loginViaForgotPasswordFlowTest() {
        header.clickPersonalAccount();
        loginPage.waitForPage();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.waitForPage();
        forgotPasswordPage.clickLoginLink();
        loginPage.waitForPage();

        loginPage.login(email, password);

        assertTrue("Отображается кнопка Оформить заказ", mainPage.isDisplayedOrderButton());
    }

    @Test
    @DisplayName("Логин через прямое открытие страницы восстановления пароля")
    @Description("Проверяет вход в аккаунт через прямое открытие страницы восстановления пароля")
    public void loginViaForgotPasswordOpenPageTest() {
        forgotPasswordPage.openPage();
        forgotPasswordPage.waitForPage();
        forgotPasswordPage.clickLoginLink();
        loginPage.waitForPage();

        loginPage.login(email, password);

        assertTrue("Отображается кнопка Оформить заказ", mainPage.isDisplayedOrderButton());
    }

    @After
    public void tearDown() {
        if (userClient != null && accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        if (mainPage != null) {
            mainPage.close();
        }
    }
}