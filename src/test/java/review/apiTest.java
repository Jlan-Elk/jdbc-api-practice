package review;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class apiTest {
      /*
Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
    post code is 22031
    country  is United States
    country abbreviation is US
    place name is Fairfax
    state is Virginia
    latitude is 38.8604
     */

    String zipUrl = "http://api.zippopotam.us";
    @Test
    public void pathTest(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get(zipUrl+"/us/{zip}");

      //  response.prettyPrint();

        //assertion

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //And Server header is cloudflare
        //And Report-To header exists
        assertTrue(response.header("Server").equalsIgnoreCase("cloudflare"));
        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        assertEquals(response.path("\"post code\""),"22031");
      //  assertEquals(response.path("country"),"United States");
        Assert.assertTrue(response.body().asString().contains("United States"));
      //Assert.assertTrue(response.body().asString().contains("\"state abbreviation\""),"VA");

    }
    @Test
    public void HamcrestWithZipAPI(){

        given().log().all().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get(zipUrl+"/us/{zip}")
                .then().assertThat().statusCode(200).and().contentType("application/json")
                .and().assertThat().header("Server",equalTo("cloudflare")).header("Report-To",notNullValue())
                .body("country", equalTo("United States"),
                        "'post code'",equalTo("22031"),
                        "places[0].state",equalTo("Virginia"),
                        "'country abbreviation'",equalTo("US"),
                        "places[0].'place name'",equalTo("Fairfax"),
                        "places[0].latitude",equalTo("38.8604"));

    }
    @Test
    public void NegativeTestCase(){
        /* Given Accept application/json
And path zipcode is 50000
When I send a GET request to /us endpoint
Then status code must be 404
And content type must be application/json */

        given().accept(ContentType.JSON)
                .and().pathParam("zip",5000)
                .when().get(zipUrl+"/us/{zip}")
                .then().assertThat().statusCode(400).and().contentType("application/json");
    }



}

