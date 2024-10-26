package pages;

import api.Book.BooksApi;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import model.AddListOfBooksModel;
import model.AuthResponseModel;
import model.IsbnModel;

import java.util.ArrayList;
import java.util.List;

import static api.Book.BooksApi.addBooks;
import static api.Book.BooksApi.getBooks;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BookStorePage {
    public static final SelenideElement
            verifyUserName = $("#userName-value"),
            bookTable = $(".ReactTable");

    private static final SelenideElement
            modalWindow = $(".modal-content"),
            modalWindowOkButton = $("button[id='closeSmallModal-ok']");

    private static final String
            bookRowClass = ".rt-tr -odd",
            deleteIcon = "#delete-record-undefined";

    IsbnModel isbn = new IsbnModel();
    List<IsbnModel> isbnCollection = new ArrayList<>();
    AddListOfBooksModel bookData = new AddListOfBooksModel();

    public void deleteAllBooksApi(AuthResponseModel authResponse) {
        BooksApi.deleteBooks(authResponse);
    }

    public void addBooksToCartApi(AuthResponseModel authResponse, String bookIsbn) {
        isbn.setIsbn(bookIsbn);
        isbnCollection.add(isbn);

        bookData.setUserId(authResponse.getUserId());
        bookData.setCollectionOfIsbns(isbnCollection);

        addBooks(authResponse, bookData);
    }

    public String getBooksTitle(String bookIsbn) {
        return getBooks(bookIsbn).getTitle();
    }

    public void deleteBookToCartUi(String title) {
        bookTable.find(byText(title)).ancestor(bookRowClass).$(deleteIcon).click();
        modalWindow.shouldBe(visible);
        modalWindowOkButton.click();
        Selenide.switchTo().alert().dismiss();
    }
}
