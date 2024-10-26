package tests;

import helpers.LoginExtension;
import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static data.BooksData.ISBN;
import static data.LoginData.USER_NAME;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static pages.BookStorePage.bookTable;
import static pages.BookStorePage.verifyUserName;

public class DemoqaUiWithApiTest extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Успешное удаление книги из магазина")
    void successfulDeleteBookToCartTest() {

        step("Удалить все книги из корзины", () ->
                bookStorePage.deleteAllBooksApi(LoginExtension.getSession()));

        step("Добавить книгу в корзину", () -> {
            bookStorePage.addBooksToCartApi(LoginExtension.getSession(), ISBN);
            bookTitle = bookStorePage.getBooksTitle(ISBN);
        });

        step("Открыть страницу профиля", () ->
                open("/profile"));

        step("Проверить, что открыта авторизованная страница profile", () ->
                assertThat(verifyUserName.getText())
                        .isEqualTo(USER_NAME));

        step("Проверить, что книга добавлена в корзину", () ->
                bookTable
                        .shouldHave(text(bookTitle))
        );

        step("Удалить книгу из карзины", () ->
            bookStorePage
                    .deleteBookToCartUi(bookTitle));

        step("Проверить, что книга удалена", () ->
            assertThat(bookTable)
                    .isNotEqualTo(text(bookTitle)));

    }
}
