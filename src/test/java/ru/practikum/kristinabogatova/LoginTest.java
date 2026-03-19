package ru.practikum.kristinabogatova;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practikum.kristinabogatova.api.UserClient;
import ru.practikum.kristinabogatova.generator.UserDataGenerator;
import ru.practikum.kristinabogatova.pages.ForgotPasswordPage;
import ru.practikum.kristinabogatova.pages.Header;
import ru.practikum.kristinabogatova.pages.LoginPage;
import ru.practikum.kristinabogatova.pages.MainPage;
import ru.practikum.kristinabogatova.pages.RegisterPage;
import ru.practikum.kristinabogatova.utils.GlobalConst;
import ru.practikum.kristinabogatova.utils.WebDriverUtils;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private Header header;

    private String email;
    private String password;
    private String name;

    @Parameterized.Parameter(0)
    public String browser;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return List.<Object[]>of(
                new Object[] { GlobalConst.CHROME },
                new Object[] { GlobalConst.YANDEX }
        );
    }

    @Before
    public void setUp() {
        email = UserDataGenerator.getEmail();
        password = UserDataGenerator.getPassword();
        name = UserDataGenerator.getName();
        UserClient userClient = new UserClient();
        Response resp = userClient.createUser(email, password, name);
        boolean userCreated = resp.statusCode() == 200 || resp.statusCode() == 201;
        Assume.assumeTrue(userCreated);

        var driver = WebDriverUtils.create(browser);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        header = new Header(driver);
        mainPage.openPage();
    }

    @Test
    public void loginViaMainButtonTest() {
        mainPage.clickLoginButton();
        loginPage.waitForPage();

        loginPage.login(email, password);

        assertTrue("Отображается кнопка Оформления заказа", mainPage.isDisplayedOrderButton());
    }

    @Test
    public void loginViaPersonalAccountTest() {
        header.clickPersonalAccount();
        loginPage.waitForPage();

        loginPage.login(email, password);

        assertTrue("Отображается кнопка Оформления заказа", mainPage.isDisplayedOrderButton());
    }

    @Test
    public void loginViaRegisterTest() {
        header.clickPersonalAccount();
        loginPage.waitForPage();
        loginPage.clickRegisterLink();
        registerPage.waitForPage();
        registerPage.clickLoginLink();
        loginPage.waitForPage();

        loginPage.login(email, password);

        assertTrue("Отображается кнопка Оформления заказа", mainPage.isDisplayedOrderButton());
    }

    @Test
    public void loginViaForgotPasswordFlowTest() {
        header.clickPersonalAccount();
        loginPage.waitForPage();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.waitForPage();
        forgotPasswordPage.clickLoginLink();
        loginPage.waitForPage();

        loginPage.login(email, password);

        assertTrue("Отображается кнопка Оформления заказа", mainPage.isDisplayedOrderButton());
    }

    @Test
    public void loginViaForgotPasswordOpenPageTest() {
        forgotPasswordPage.openPage();
        forgotPasswordPage.waitForPage();
        forgotPasswordPage.clickLoginLink();
        loginPage.waitForPage();

        loginPage.login(email, password);

        assertTrue("Отображается кнопка Оформления заказа", mainPage.isDisplayedOrderButton());
    }

    @After
    public void tearDown() {
        mainPage.close();
        loginPage.close();
        registerPage.close();
        forgotPasswordPage.close();
        header.close();
    }
}