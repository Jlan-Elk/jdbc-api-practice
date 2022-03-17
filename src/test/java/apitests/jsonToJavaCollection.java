package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class jsonToJavaCollection {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void SpartanToMap() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);

        //we will convert json response to java map
        Map<String, Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);

        String name = (String) jsonDataMap.get("name");
        assertEquals(name, "Meta");

        BigDecimal phone = new BigDecimal(String.valueOf(jsonDataMap.get("phone")));
        System.out.println("phone = " + phone);
    }
    @Test
    public void allSpartansToListOfMap(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(),200);

        //we need to de-serialize to Json response to list of Maps
        List<Map<String,Object>>allSpartansList = response.body().as(List.class);
        System.out.println(allSpartansList);

        //print the second spartan first name
        System.out.println(allSpartansList.get(1).get("name"));   //first get points to one of the Map which belongs to List Map !! 2nd get belongs to Map!!

        //save spartan 3 in a map
        Map<String,Object> spartan3 = allSpartansList.get(2); //this get(2) returns to Map !!
        System.out.println(spartan3);

    }
    @Test
    public void regionWithMap(){
        Response response = given().accept(ContentType.JSON)
                .when().get("http://34.227.112.9:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);

        //we need to de-serialize to Json response to Map
        Map<String,Object> regionMap = response.body().as(Map.class);

        System.out.println(regionMap.get("count"));
        System.out.println(regionMap.get("hasMore"));
        System.out.println(regionMap.get("items")); //this items is object in here

        //we need to de-serialize to Json response to list of Maps and casting to the object
        List<Map<String,Object>> itemsList = (List<Map<String, Object>>) regionMap.get("items");
         System.out.println(itemsList.get(1).get("region_name"));
         System.out.println(itemsList.get(1).get("region_id"));




    }

}