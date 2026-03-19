package ru.practikum.kristinabogatova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {

    // локаторы
    private static final By LOGOUT_BUTTON = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    // Отображается кнопка выхода
    public boolean waitForPage() {
        return isDisplayed(LOGOUT_BUTTON);
    }

    public void clickLogout() {
        click(LOGOUT_BUTTON);
    }
}