package pages.cart;

import api.Book.BooksApi;
import model.AuthResponseModel;

public class CartPage {

    public void deleteAllBooksApi(AuthResponseModel authResponse) {
        BooksApi.deleteBooks(authResponse);
    }
}
