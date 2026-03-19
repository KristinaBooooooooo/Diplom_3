package ru.practikum.kristinabogatova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.practikum.kristinabogatova.utils.GlobalConst;

public class MainPage extends BasePage {

    // локаторы
    private static final By BUNS_TAB = By.xpath("//span[text()='Булки']");
    private static final By SAUCES_TAB = By.xpath("//span[text()='Соусы']");
    private static final By FILLINGS_TAB = By.xpath("//span[text()='Начинки']");
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By ORDER_BUTTON = By.xpath("//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(GlobalConst.BASE_URL);
    }

    public void clickBuns() {
        click(BUNS_TAB);
    }

    public void clickSauces() {
        click(SAUCES_TAB);
    }

    public void clickFillings() {
        click(FILLINGS_TAB);
    }

    public void clickLoginButton() {
        click(LOGIN_BUTTON);
    }

    // отображается кнопка входа
    public boolean isDisplayedLoginButton() {
        return isDisplayed(LOGIN_BUTTON);
    }

    // отображается кнопка оформления заказа
    public boolean isDisplayedOrderButton() {
        return isDisplayed(ORDER_BUTTON);
    }
}