package ru.practikum.kristinabogatova.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.practikum.kristinabogatova.utils.Endpoints;
import ru.practikum.kristinabogatova.utils.GlobalConst;

public class ForgotPasswordPage extends BasePage {

    // локаторы
    private static final By LOGIN_LINK = By.xpath("//a[text()='Войти']");
    private static final By RECOVER_BUTTON = By.xpath("//button[text()='Восстановить']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу восстановления пароля")
    public void openPage() {
        driver.get(GlobalConst.BASE_URL + Endpoints.FORGOT_PASSWORD);
    }

    @Step("Дождаться открытия страницы")
    public boolean waitForPage() {
        return isDisplayed(RECOVER_BUTTON);
    }

    @Step("Перейти на страницу входа")
    public void clickLoginLink() {
        click(LOGIN_LINK);
    }
}