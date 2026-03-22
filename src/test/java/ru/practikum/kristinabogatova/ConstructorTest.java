package ru.practikum.kristinabogatova;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.practikum.kristinabogatova.pages.MainPage;
import ru.practikum.kristinabogatova.utils.WebDriverUtils;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    private MainPage mainPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(WebDriverUtils.create());
        mainPage.openPage();
    }

    @Test
    @DisplayName("Раздел Конструктор, переход на вкладку Булки")
    @Description("Проверяет, что вкладка Булки становится активной после перехода")
    public void constructorBunsTabTest() {
        mainPage.clickSauces();
        mainPage.clickBuns();

        assertTrue("Вкладка Булки должна быть активной", mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Раздел Конструктор, переход на вкладку Соусы")
    @Description("Проверяет, что вкладка Соусы становится активной после перехода")
    public void constructorSaucesTabTest() {
        mainPage.clickSauces();

        assertTrue("Вкладка Соусы должна быть активной", mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Раздел Конструктор, переход на вкладку Начинки")
    @Description("Проверяет, что вкладка Начинки становится активной после перехода")
    public void constructorFillingsTabTest() {
        mainPage.clickFillings();

        assertTrue("Вкладка Начинки должна быть активной", mainPage.isFillingsTabActive());
    }

    @After
    public void tearDown() {
        mainPage.close();
    }
}