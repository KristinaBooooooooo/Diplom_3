package ru.practikum.kristinabogatova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.practikum.kristinabogatova.utils.GlobalConst;

public class MainPage extends BasePage {

    // вкладки конструктора
    private static final By BUNS_TAB = By.xpath("//span[text()='Булки']");
    private static final By SAUCES_TAB = By.xpath("//span[text()='Соусы']");
    private static final By FILLINGS_TAB = By.xpath("//span[text()='Начинки']");

    // кнопка "Войти в аккаунт" на главной
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // открыть главную страницу
    public void open() {
        driver.get(GlobalConst.BASE_URL);
    }

    // вкладки конструктора
    public void clickBuns() {
        click(BUNS_TAB);
    }

    public void clickSauces() {
        click(SAUCES_TAB);
    }

    public void clickFillings() {
        click(FILLINGS_TAB);
    }

    // вход с главной страницы
    public void clickLoginButton() {
        click(loginButton);
    }
}