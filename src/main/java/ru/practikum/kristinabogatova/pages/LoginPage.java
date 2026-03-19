package ru.practikum.kristinabogatova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // локаторы
    private static final By EMAIL_INPUT = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Войти']");
    private static final By REGISTER_LINK = By.xpath("//a[text()='Зарегистрироваться']");
    private static final By FORGOT_PASSWORD_LINK = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ожидание загрузки страницы логина (ждём видимости кнопки входа).
     */
    public boolean waitForPage() {
        return isDisplayed(LOGIN_BUTTON);
    }

    /**
     * Выполнить логин: заполнить email, пароль и кликнуть Войти.
     */
    public void login(String email, String password) {
        type(EMAIL_INPUT, email);
        type(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
    }

    /**
     * Перейти по ссылке "Зарегистрироваться".
     */
    public void clickRegisterLink() {
        click(REGISTER_LINK);
    }

    /**
     * Перейти по ссылке "Восстановить пароль".
     */
    public void clickForgotPasswordLink() {
        click(FORGOT_PASSWORD_LINK);
    }
}