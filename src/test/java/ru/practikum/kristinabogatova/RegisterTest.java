package ru.practikum.kristinabogatova;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practikum.kristinabogatova.pages.Header;
import ru.practikum.kristinabogatova.pages.LoginPage;
import ru.practikum.kristinabogatova.pages.MainPage;
import ru.practikum.kristinabogatova.pages.RegisterPage;
import ru.practikum.kristinabogatova.utils.GlobalConst;
import ru.practikum.kristinabogatova.utils.WebDriverUtils;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegisterTest {

    private MainPage mainPage;
    private Header header;
    private LoginPage loginPage;
    private RegisterPage registerPage;

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
        var driver = WebDriverUtils.create(browser);
        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        header = new Header(driver);
        mainPage.openPage();
    }

    @Test
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
        registerPage.close();
        loginPage.close();
        header.close();
    }
}