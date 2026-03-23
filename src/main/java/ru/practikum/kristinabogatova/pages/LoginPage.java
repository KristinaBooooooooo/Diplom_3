package ru.practikum.kristinabogatova.pages;

import io.qameta.allure.Step;
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

    @Step("Дождаться открытия страницы")
    public boolean waitForPage() {
        return isDisplayed(LOGIN_BUTTON);
    }

    @Step("Войти в аккаунт")
    public void login(String email, String password) {
        type(EMAIL_INPUT, email);
        type(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
    }

    @Step("Перейти к регистрации")
    public void clickRegisterLink() {
        click(REGISTER_LINK);
    }

    @Step("Перейти к восстановлению пароля")
    public void clickForgotPasswordLink() {
        click(FORGOT_PASSWORD_LINK);
    }
}