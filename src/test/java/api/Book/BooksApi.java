package api.Book;

import model.AddListOfBooksModel;
import model.AuthResponseModel;

import static io.restassured.RestAssured.given;
import static specs.BookSpec.*;

public class BooksApi {

    public static void deleteBooks(AuthResponseModel authResponse) {
        given(bookStoreRequestSpec)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .queryParam("UserId", authResponse.getUserId())

                .when()
                .delete("/Books")

                .then()
                .spec(bookStoreResponseSpec204);
    }

    public static void addBooks(AuthResponseModel authResponse, AddListOfBooksModel bookData) {
        given(bookStoreRequestSpec)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(bookData)

                .when()
                .post("/Books")

                .then()
                .spec(bookStoreResponseSpec201);
    }
}
