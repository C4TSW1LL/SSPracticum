package com.globalsqa;

import config.ConfigTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class TestsSetUp {

    protected WebDriver driver;
    public ChromeOptions options;

    private final ConfigTests config = ConfigFactory.create(ConfigTests.class, System.getenv());
    @Before
    public void setUp() {
        // Установка драйвера GoogleChrome
        WebDriverManager.chromedriver().setup();

        // Создание экземпляра настройки браузера
        options = new ChromeOptions();

        // Установка фикса открытия страницы "data.." вместо указанного url
        options.addArguments("--remote-allow-origins=*");

        // Создание экземпляра драйвера с примененным фиксом
        driver = new ChromeDriver(options);

        // Открытие браузера на полный экран
        driver.manage().window().maximize();

        // Открытие страницы по url
        driver.get(config.url());

        //Ожидание появления элемента на экране
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    /**
     * Общие настройки тестов после выполнения
     */
    @After
    public void shutDown() {
        // Закрытие браузера
        driver.quit();

    }
}
