package ru.practikum.kristinabogatova;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practikum.kristinabogatova.pages.MainPage;
import ru.practikum.kristinabogatova.utils.WebDriverUtils;

import java.util.Collection;
import java.util.List;

import static ru.practikum.kristinabogatova.utils.GlobalConst.*;

@RunWith(Parameterized.class)
public class ConstructorTest {

    private MainPage mainPage;

    @Parameterized.Parameter(0) public String browser;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return List.<Object[]>of(
                new Object[] {CHROME},
                new Object[] {YANDEX}
        );
    }

    @Before
    public void setUp() {
        mainPage = new MainPage(WebDriverUtils.create(this.browser));
        mainPage.openPage();
    }

    @Test
    public void constructorTabsTest() {
        mainPage.clickSauces();
        mainPage.clickFillings();
        mainPage.clickBuns();
    }

    @After
    public void tearDown() {
        mainPage.close();
    }
}