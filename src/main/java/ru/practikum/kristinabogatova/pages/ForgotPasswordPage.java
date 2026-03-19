package ru.practikum.kristinabogatova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.practikum.kristinabogatova.utils.GlobalConst;

public class ForgotPasswordPage extends BasePage {

    // локаторы
    private static final By LOGIN_LINK = By.xpath("//a[text()='Войти']");
    private static final By RECOVER_BUTTON = By.xpath("//button[text()='Восстановить']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(GlobalConst.BASE_URL + "/forgot-password");
    }

    public boolean waitForPage() {
        return isDisplayed(RECOVER_BUTTON);
    }

    public void clickLoginLink() {
        click(LOGIN_LINK);
    }
}