package api.Authorization;

import io.qameta.allure.Step;
import model.AuthDataModel;
import model.AuthResponseModel;

import static data.LoginData.PASSWORD;
import static data.LoginData.USER_NAME;
import static io.restassured.RestAssured.given;
import static specs.AuthSpec.authRequestSpec;
import static specs.AuthSpec.successfulAuthResponseSpec200;

public class AuthorizationApi {

    @Step("Авторизация по API")
    public static AuthResponseModel login() {
        AuthDataModel authData = new AuthDataModel();
        authData.setUserName(USER_NAME);
        authData.setPassword(PASSWORD);

        return given(authRequestSpec)
                .body(authData)

                .when()
                .post("/Login")

                .then()
                .spec(successfulAuthResponseSpec200)
                .extract().as(AuthResponseModel.class);
    }

}
