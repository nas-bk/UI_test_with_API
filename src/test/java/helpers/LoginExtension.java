package helpers;

import api.Authorization.AuthorizationApi;
import lombok.Getter;
import model.AuthResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {


    @Getter
    public static AuthResponseModel session = new AuthResponseModel();

    private static void setSession(AuthResponseModel authResponse) {
        session = authResponse;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        AuthResponseModel authResponse = AuthorizationApi.login();
        setSession(authResponse);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
    }
}
