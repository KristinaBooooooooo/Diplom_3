package ru.practikum.kristinabogatova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    // Поля и кнопки на странице логина
    private static final By EMAIL_INPUT = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Войти']");

    // Ссылки на странице логина
    private static final By REGISTER_LINK = By.xpath("//a[text()='Зарегистрироваться']");
    private static final By FORGOT_PASSWORD_LINK = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        super(driver);

    }

    /**
     * Ожидание загрузки страницы логина (ждём видимости кнопки входа).
     */
    public void waitForPage() {
        isDisplayed(LOGIN_BUTTON);
    }

    /**
     * Выполнить логин: заполнить email, пароль и кликнуть Войти.
     */
    public void login(String email, String password) {
        type(EMAIL_INPUT, email);
        type(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
        // Ждём результата: либо редирект на /account, либо появление "Личный Кабинет" в хэдере
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("account")
        ));
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