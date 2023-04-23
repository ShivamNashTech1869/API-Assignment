import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddMultivaluedHeadersInGETRequest {
    @Test
    public void byUsingHasMap() {
           Map<String,String> requestHeader = new HashMap<>();
           requestHeader.put("Header1","Value1");
           requestHeader.put("Header2","Value2");
           requestHeader.put("Header3","Value3");


           RequestSpecification requestSpecification = RestAssured.given();

           //Add header
           requestSpecification.headers(requestHeader);

           requestSpecification.log().headers();

           //specify url
           requestSpecification.baseUri("https://reqres.in/api/users?page=1");

           //perform get request
           Response response = requestSpecification.get();

           //validate response Code
           Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void byUsingHeaderClass() {
        Header header1 = new Header("Header1", "Value1");
        Header header2 = new Header("Header2", "Value2");
        Header header3 = new Header("Header3", "Value3");

        List<Header> headerList = new ArrayList<Header>();
        headerList.add(header1);
        headerList.add(header2);
        headerList.add(header3);

        Headers headersObject = new Headers(headerList);

        RequestSpecification requestSpecification = RestAssured.given();

        //Add header
        requestSpecification.headers(headersObject);
        requestSpecification.log().headers();

        //specify url
        requestSpecification.baseUri("https://reqres.in/api/users?page=1");

        //perform get request
        Response response = requestSpecification.get();

        //validate response Code
        Assert.assertEquals(response.statusCode(), 200);

    }
       @Test
       public void byUsingHeaderClassBDDFormat() {
              Header header1 = new Header("Header1", "Value1");
              Header header2 = new Header("Header2", "Value2");
              Header header3 = new Header("Header3", "Value3");

              List<Header> headerList = new ArrayList<Header>();
              headerList.add(header1);
              headerList.add(header2);
              headerList.add(header3);

              Headers headersObject = new Headers(headerList);

             //BDD format-
              RestAssured
                      .given()
                         .headers(headersObject)
                         .log().headers()
                      .when()
                          .get("https://reqres.in/api/users?page=1")
                      .then()
                          .statusCode(200)
                          .log().body();
       }
}
