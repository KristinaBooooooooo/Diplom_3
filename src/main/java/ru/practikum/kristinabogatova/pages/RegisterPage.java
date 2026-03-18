package ru.practikum.kristinabogatova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private static final By NAME_INPUT = By.xpath("//label[text()='Имя']/following-sibling::input");
    private static final By EMAIL_INPUT = By.xpath("//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");

    private static final By PASSWORD_ERROR = By.xpath("//p[contains(@class,'input__error') and (contains(.,'пароль') or contains(.,'Пароль'))]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Заполнить поля и нажать "Зарегистрироваться".
     */
    public void register(String name, String email, String password) {
        type(NAME_INPUT, name);
        type(EMAIL_INPUT, email);
        type(PASSWORD_INPUT, password);
        click(REGISTER_BUTTON);
    }

    /**
     * Получить текст ошибки, связанной с вводом пароля.
     * Если элемент отсутствует — метод выбросит исключение через getText() из BasePage,
     * либо можно предварительно вызывать isPasswordErrorDisplayed().
     */
    public String getPasswordErrorText() {
        return getText(PASSWORD_ERROR);
    }

    public boolean isPasswordErrorDisplayed() {
        return isDisplayed(PASSWORD_ERROR);
    }
}