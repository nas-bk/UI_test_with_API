package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.bookStore.BookStorePage;
import pages.cart.CartPage;
import io.qameta.allure.selenide.AllureSelenide;


public class TestBase {
    CartPage cartPage = new CartPage();
    BookStorePage bookStorePage = new BookStorePage();

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("result screenshot");
        Attach.browserConsoleLogs();
        Attach.pageSource();
        Selenide.closeWebDriver();
    }
}

