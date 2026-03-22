package ru.practikum.kristinabogatova.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage {

    // локаторы
    private static final By CONSTRUCTOR = By.xpath("//p[text()='Конструктор']");
    private static final By PERSONAL_ACCOUNT = By.xpath("//p[text()='Личный Кабинет']");

    public Header(WebDriver driver) {
        super(driver);
    }

    @Step("Перейти в личный кабинет")
    public void clickPersonalAccount() {
        click(PERSONAL_ACCOUNT);
    }

    @Step("Перейти в конструктор")
    public void clickConstructor() {
        click(CONSTRUCTOR);
    }
}