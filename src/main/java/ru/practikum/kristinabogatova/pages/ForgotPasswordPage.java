package ru.practikum.kristinabogatova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private static final By LOGIN_LINK = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginLink() {
        click(LOGIN_LINK);
    }
}