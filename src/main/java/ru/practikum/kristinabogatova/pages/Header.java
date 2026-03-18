package ru.practikum.kristinabogatova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage {

    private static final By CONSTRUCTOR = By.xpath("//p[text()='Конструктор']");
    private static final By PERSONAL_ACCOUNT = By.xpath("//p[text()='Личный Кабинет']");

    public Header(WebDriver driver) {
        super(driver);
    }

    public void clickPersonalAccount() {
        click(PERSONAL_ACCOUNT);
    }

    public void clickConstructor() {
        click(CONSTRUCTOR);
    }

    public boolean isPersonalAccountDisplayed() {
        return isDisplayed(PERSONAL_ACCOUNT);
    }
}