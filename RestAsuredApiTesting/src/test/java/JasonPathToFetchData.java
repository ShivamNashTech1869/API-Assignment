import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JasonPathToFetchData {

    @Test
    void fetchEmpId1To10(){
        List<Integer> employeeIds = given()
                .when()
                .get("https://reqres.in/api/users?page=1")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("data.findAll{it.id >= 1 && it.id <= 10}.id", Integer.class);
        System.out.print("list :"+employeeIds);
    }
}
