package ru.practikum.kristinabogatova;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.kristinabogatova.pages.Header;
import ru.practikum.kristinabogatova.pages.LoginPage;
import ru.practikum.kristinabogatova.pages.MainPage;
import ru.practikum.kristinabogatova.pages.ProfilePage;
import ru.practikum.kristinabogatova.pages.RegisterPage;
import ru.practikum.kristinabogatova.utils.GlobalConst;
import ru.practikum.kristinabogatova.utils.WebDriverUtils;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegisterTest {

    private WebDriver driver;
    private MainPage mainPage;
    private Header header;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ProfilePage profilePage;

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
        driver = WebDriverUtils.create(this.browser);
        mainPage = new MainPage(driver);
        header = new Header(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        profilePage = new ProfilePage(driver);

        mainPage.open();
    }

    /**
     * Успешная регистрация:
     * - регистрация
     * - ожидание редиректа на страницу логина (URL содержит 'login')
     * - ожидание загрузки страницы логина
     */
    @Test
    public void successfulRegistrationTest() {
        header.clickPersonalAccount();
        loginPage.clickRegisterLink();

        String unique = UUID.randomUUID().toString().substring(0, 8);
        String name = "auto" + unique;
        String email = "auto_" + unique + "@mail.com";
        String password = "Password1"; // >= 6 символов

        registerPage.register(name, email, password);

        // Ждём редиректа на страницу логина
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("login"));

        assertTrue("Ожидаем редирект на страницу логина после регистрации", driver.getCurrentUrl().contains("login"));

        // Дополнительно ждём загрузки элементов страницы логина
        loginPage.waitForPage();
    }

    /**
     * Попытка зарегистрироваться с коротким паролем:
     * - ожидаем появление ошибки под полем пароля и проверяем её текст.
     */
    @Test
    public void shortPasswordRegistrationShouldFailTest() {
        header.clickPersonalAccount();
        loginPage.clickRegisterLink();
        String unique = UUID.randomUUID().toString().substring(0, 8);
        String name = "auto" + unique;
        String email = "auto_" + unique + "@mail.com";
        String shortPassword = "123"; // короче 6

        registerPage.register(name, email, shortPassword);

        boolean errorShown = registerPage.isPasswordErrorDisplayed();
        assertTrue("Ожидаем отображение ошибки при коротком пароле", errorShown);
        String errorText = registerPage.getPasswordErrorText();
        assertTrue("Текст ошибки должен сообщать о проблеме с паролем. Был получен текст: " + errorText,
                errorText != null && errorText.toLowerCase().contains("парол"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}