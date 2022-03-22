package review;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class Deserialization {
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
    public void pathTest() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get(zipUrl + "/us/{zip}");

        //  response.prettyPrint();
        Map<String,Object> postCode = response.body().as(Map.class);
        System.out.println("postCode = " + postCode);

        assertEquals(postCode.get("country"),"United States");
        assertEquals(postCode.get("post code"),"22031");

        List<Map<String,Object>> placeList = (List<Map<String,Object>>) postCode.get("places");
        System.out.println("placeList = " + placeList);

        assertEquals(placeList.get(0).get("state"),"Virginia");
        assertEquals(placeList.get(0).get("place name"),"Fairfax");

        double expectedAttitude = 38.8604;
        double actualAttitude = Double.parseDouble((String) placeList.get(0).get("latitude"));
        assertEquals(expectedAttitude,actualAttitude);




    }

}
