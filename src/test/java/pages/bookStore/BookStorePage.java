package pages.bookStore;

import api.Book.BooksApi;
import model.AddListOfBooksModel;
import model.AuthResponseModel;
import model.IsbnModel;

import java.util.ArrayList;
import java.util.List;

public class BookStorePage {
    IsbnModel isbn = new IsbnModel();
    List<IsbnModel> isbnCollection = new ArrayList<>();
    AddListOfBooksModel bookData = new AddListOfBooksModel();

    public void addBooksToCartApi(AuthResponseModel authResponse, String bookIsbn) {
        isbn.setIsbn(bookIsbn);
        isbnCollection.add(isbn);

        bookData.setUserId(authResponse.getUserId());
        bookData.setCollectionOfIsbns(isbnCollection);

        BooksApi.addBooks(authResponse, bookData);
    }
}
