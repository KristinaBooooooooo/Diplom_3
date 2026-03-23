package ru.practikum.kristinabogatova.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.practikum.kristinabogatova.utils.GlobalConst;

public class MainPage extends BasePage {

    // локаторы
    private static final By BUNS_TAB = By.xpath("//span[text()='Булки']");
    private static final By BUNS_TAB_CONTAINER = By.xpath("//span[text()='Булки']/parent::div");
    private static final By SAUCES_TAB = By.xpath("//span[text()='Соусы']");
    private static final By SAUCES_TAB_CONTAINER = By.xpath("//span[text()='Соусы']/parent::div");
    private static final By FILLINGS_TAB = By.xpath("//span[text()='Начинки']");
    private static final By FILLINGS_TAB_CONTAINER = By.xpath("//span[text()='Начинки']/parent::div");
    private static final By LOGIN_BUTTON = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By ORDER_BUTTON = By.xpath("//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть главную страницу")
    public void openPage() {
        driver.get(GlobalConst.BASE_URL);
    }

    @Step("Открыть вкладку Булки")
    public void clickBuns() {
        click(BUNS_TAB);
    }

    @Step("Открыть вкладку Соусы")
    public void clickSauces() {
        click(SAUCES_TAB);
    }

    @Step("Открыть вкладку Начинки")
    public void clickFillings() {
        click(FILLINGS_TAB);
    }

    @Step("Нажать кнопку Войти в аккаунт")
    public void clickLoginButton() {
        click(LOGIN_BUTTON);
    }

    public boolean isDisplayedLoginButton() {
        return isDisplayed(LOGIN_BUTTON);
    }

    public boolean isDisplayedOrderButton() {
        return isDisplayed(ORDER_BUTTON);
    }

    public boolean isBunsTabActive() {
        return waitForAttributeContains(BUNS_TAB_CONTAINER, "class", "current");
    }

    public boolean isSaucesTabActive() {
        return waitForAttributeContains(SAUCES_TAB_CONTAINER, "class", "current");
    }

    public boolean isFillingsTabActive() {
        return waitForAttributeContains(FILLINGS_TAB_CONTAINER, "class", "current");
    }
}