package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.BookStorePage;
import io.qameta.allure.selenide.AllureSelenide;


public class TestBase {
    BookStorePage bookStorePage = new BookStorePage();
    String bookTitle;

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
  //      Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.browser = "chrome";

    }

    @BeforeEach
    void beforeEach(){
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

