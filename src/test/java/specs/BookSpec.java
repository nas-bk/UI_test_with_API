package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class BookSpec {

    public static RequestSpecification bookStoreRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON)
            .basePath("/BookStore/v1");

    private static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(ALL)
                .build();
    }

    public static ResponseSpecification bookStoreResponseSpec200 = responseSpec(200);
    public static ResponseSpecification bookStoreResponseSpec201 = responseSpec(201);
    public static ResponseSpecification bookStoreResponseSpec204 = responseSpec(204);

}
