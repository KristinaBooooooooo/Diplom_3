package ru.practikum.kristinabogatova.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private static final By NAME_INPUT = By.xpath("//label[text()='Имя']/following-sibling::input");
    private static final By EMAIL_INPUT = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By LOGIN_LINK = By.xpath("//a[text()='Войти']");
    private static final By PASSWORD_ERROR = By.xpath("//p[contains(@class,'input__error') and (contains(.,'пароль') or contains(.,'Пароль'))]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Дождаться открытия страницы")
    public boolean waitForPage() {
        return isDisplayed(REGISTER_BUTTON);
    }

    @Step("Зарегистрировать пользователя")
    public void register(String name, String email, String password) {
        type(NAME_INPUT, name);
        type(EMAIL_INPUT, email);
        type(PASSWORD_INPUT, password);
        click(REGISTER_BUTTON);
    }

    public String getPasswordErrorText() {
        return getText(PASSWORD_ERROR);
    }

    public boolean isPasswordErrorDisplayed() {
        return isDisplayed(PASSWORD_ERROR);
    }

    @Step("Перейти на страницу входа")
    public void clickLoginLink() {
        click(LOGIN_LINK);
    }
}