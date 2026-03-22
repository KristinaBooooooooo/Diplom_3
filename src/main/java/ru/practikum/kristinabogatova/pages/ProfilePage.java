package ru.practikum.kristinabogatova.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {

    // локаторы
    private static final By LOGOUT_BUTTON = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Дождаться открытия страницы")
    public boolean waitForPage() {
        return isDisplayed(LOGOUT_BUTTON);
    }

    @Step("Выйти из аккаунта")
    public void clickLogout() {
        click(LOGOUT_BUTTON);
    }
}