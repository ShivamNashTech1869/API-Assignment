import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ValidateResponse {

    @Test
    public void statusCodeValidation() {
        given().
                when().
                get("https://reqres.in/api/users").
                then().
                statusCode(200)
                .log().all();
    }

    @Test
    public void responseBodyValidation() {
        given().
                when().
                get("https://reqres.in/api/users").
                then().
                body("page", equalTo(1)).
                body("data.id[0]", equalTo(1)).
                header("Content-Type", equalTo("application/json; charset=utf-8"))
                .log().all();
    }

    @Test
    public void responseTimeValidation() {
        given().
                when().
                get("https://reqres.in/api/users").
                then().
                time(lessThan(5000L))
                .log().all();
    }

    @Test
    public void responseHeadersValidation() {
        given().
                when().
                get("https://reqres.in/api/users").
                then().
                header("Content-Type", containsString("application/json")).
                header("Server", equalTo("cloudflare"))
                .log().all();
    }

}
