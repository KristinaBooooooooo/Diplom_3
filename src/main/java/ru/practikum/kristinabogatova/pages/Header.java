package ru.practikum.kristinabogatova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public ExpectedCondition<WebElement> isVisibleAccount() {
        return ExpectedConditions.visibilityOfElementLocated(Header.PERSONAL_ACCOUNT);
    }
}