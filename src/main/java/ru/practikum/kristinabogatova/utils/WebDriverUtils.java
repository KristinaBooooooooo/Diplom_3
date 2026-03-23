package ru.practikum.kristinabogatova.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebDriverUtils {

    // Метод для создания драйвера в зависимости от браузера
    public static WebDriver create() {
        return create(resolveBrowser());
    }

    private static WebDriver create(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case GlobalConst.CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case GlobalConst.YANDEX:
                driver = yandexDriver();
                break;

            default:
                throw new IllegalArgumentException("Браузер не поддерживается: " + browser);
        }
        // Настройка таймаутов
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return driver;
    }

    private static String resolveBrowser() {
        String browser = System.getProperty("browser");
        if (browser != null && !browser.isBlank()) {
            return browser;
        }
        browser = System.getenv("BROWSER");
        if (browser != null && !browser.isBlank()) {
            return browser;
        }
        return GlobalConst.CHROME;
    }

    private static WebDriver yandexDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/Kristik/Documents/Diplom/Diplom_3/src/test/resources/yandexdriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
}