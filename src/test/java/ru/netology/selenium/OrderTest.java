package ru.netology.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldFormOnSheet() {
        WebElement form = driver.findElement(By.cssSelector("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Петр Петров");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79245679845");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button_view_extra span.button__text")).click();
        WebElement resultElement = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        assertTrue(resultElement.isDisplayed());
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", resultElement.getText().trim());
    }

//    @Test
//    void shouldExceptionNotValidPhoneOnForm() {
//        WebElement form = driver.findElement(By.cssSelector("form"));
//        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Петр Петров");
//        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7924567984");
//        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
//        driver.findElement(By.cssSelector("button.button_view_extra .button__text")).click();
////        driver.findElement(By.cssSelector("[data-test-id='submit']")).click();
//        WebElement resultElement = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub"));
//        assertTrue(resultElement.isDisplayed());
//        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", resultElement.getText().trim());
//    }
}
