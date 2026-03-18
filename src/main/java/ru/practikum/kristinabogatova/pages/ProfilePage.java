package ru.practikum.kristinabogatova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {

    private static final By LOGOUT_BUTTON = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoutDisplayed() {
        return isDisplayed(LOGOUT_BUTTON);
    }
}