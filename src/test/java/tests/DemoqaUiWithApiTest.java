package tests;

import helpers.LoginExtension;
import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.BooksData.ISBN;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoqaUiWithApiTest extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Успешное добавление книги в корзину")
    void successfulAddBooksToCartTest() {

        step("Удалить все книги из корзины", () ->
                cartPage.deleteAllBooksApi(LoginExtension.getSession()));

        step("Добавить книгу в корзину", () ->
                bookStorePage.addBooksToCartApi(LoginExtension.getSession(), ISBN));

        step("Открыть страницу профиля", () ->
                open("/profile"));

        step("Проверить, что книга добавлена в корзину", () ->
                assertTrue($("[id='see-book-Designing Evolvable Web APIs with ASP.NET']").isDisplayed()));
    }
}
