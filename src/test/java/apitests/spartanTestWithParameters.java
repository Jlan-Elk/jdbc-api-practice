package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class spartanTestWithParameters {

    @BeforeClass
    public void beforeclass() {
        baseURI = "http://34.227.112.9:8000";
    }

    /*
          Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json;charset=UTF-8
          And "Blythe" should be in response payload
       */
    @Test
    public void getSpartanID_Positive_PathParam() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("/api/spartans/{id}");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(response.contentType(), "application/json");

        assertTrue(response.body().asString().contains("Blythe"));

    }

     /*
        TASK
        Given accept content type is Json
        And Id path parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json;charset=UTF-8
        And "Spartan Not Found" message should be in response payload
     */

    @Test
    public void getSpartanId_Negative_PathParam(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");

     Assert.assertEquals(response.statusCode(), 404);

     Assert.assertEquals(response.contentType(), "application/json");

     Assert.assertTrue(response.body().asString().contains("Not Found"));

    }
       /*
        Given accept type is Json
        And query parameter values are :
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json;charset=UTF-8
        And "Female" should be in response payload(body)
        And "Janette" should be in response payload
     */
    @Test
    public void  spartanPositiveTestWithQueryParam() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "female")
                .and().queryParam("nameContains", "e")
                .when().get("api/spartans/search");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(response.contentType(),"application/json");

        Assert.assertTrue(response.body().asString().contains("Female"));
        Assert.assertTrue(response.body().asString().contains("Janette"));

    }

    @Test
    public void positiveTestWithQueryParamWithMaps(){

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("api/spartans/search");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertEquals(response.contentType(),"application/json");

        Assert.assertTrue(response.body().asString().contains("Female"));
        Assert.assertTrue(response.body().asString().contains("Janette"));



    }
}

