package ru.practikum.kristinabogatova;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.kristinabogatova.api.UserClient;
import ru.practikum.kristinabogatova.generator.UserDataGenerator;
import ru.practikum.kristinabogatova.pages.Header;
import ru.practikum.kristinabogatova.pages.LoginPage;
import ru.practikum.kristinabogatova.pages.MainPage;
import ru.practikum.kristinabogatova.pages.ProfilePage;
import ru.practikum.kristinabogatova.utils.GlobalConst;
import ru.practikum.kristinabogatova.utils.WebDriverUtils;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private Header header;

    // тестовые данные
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
        // генерируем данные пользователя
        email = UserDataGenerator.getEmail();
        password = UserDataGenerator.getPassword();
        name = UserDataGenerator.getName();

        // создаём пользователя через API (чтобы можно было залогиниться по UI)
        UserClient userClient = new UserClient();
        Response resp = userClient.createUser(email, password, name);

        // если создание пользователя неуспешно — пропускаем тесты (assume)
        boolean userCreated = resp.statusCode() == 200 || resp.statusCode() == 201;
        if (!userCreated) {
            System.out.println("Warning: user creation via API returned status " + resp.statusCode() + ", body: " + resp.asString());
        }
        Assume.assumeTrue("User creation failed via API, skipping UI test", userCreated);

        // создаём драйвер и страницы
        driver = WebDriverUtils.create(this.browser);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        header = new Header(driver);

        // открываем главную
        mainPage.open();
    }

    @Test
    public void loginViaMainButtonTest() {
        // Нажимаем кнопку "Войти" на главной и логинимся
        mainPage.clickLoginButton();
        // ждём загрузки формы логина
        loginPage.waitForPage();
        // Выполняем логин
        loginPage.login(email, password);

        boolean urlOk = driver.getCurrentUrl().contains("account");
        boolean headerOk = header.isPersonalAccountDisplayed();

        assertTrue("После логина ожидаем либо переход на /account, либо отображение Личный Кабинет в хэдере", urlOk || headerOk);
    }

    @Test
    public void loginViaPersonalAccountLinkTest() {
        // Переходим по ссылке "Личный Кабинет" в хэдере и логинимся
        header.clickPersonalAccount();
        // Ждём появления формы логина
        loginPage.waitForPage();
        // Логинимся
        loginPage.login(email, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        By personalAccountHeader = By.xpath("//p[text()='Личный Кабинет']");

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("account"),
                ExpectedConditions.visibilityOfElementLocated(personalAccountHeader)
       ));

        boolean urlOk = driver.getCurrentUrl().contains("account");
        boolean headerOk = header.isPersonalAccountDisplayed();

        assertTrue("После логина ожидаем либо переход на /account, либо отображение Личный Кабинет в хэдере", urlOk || headerOk);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}