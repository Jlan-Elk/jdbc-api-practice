package apitests;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class hrApiWithPath {

    @BeforeClass
    public void beforeclass(){

        baseURI= ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void getCountriesWithPath(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":\"2\"}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);

        //print limit value
        System.out.println(response.path("limit").toString()); //-->25
        //print hasMore
        System.out.println(response.path("hasMore").toString()); //-->false
        //print offset value
        System.out.println(response.path("offset").toString());  //-->0
        //print count value
        System.out.println(response.path("count").toString());  //-->5


      //get the specific info from items
        String firstCountryid = response.path("items.country_id[0]") ; //get the first country_id
        System.out.println("firstCountryid = " + firstCountryid);

        String firstCountryName = response.path("items.country_name[0]");
        System.out.println("firstCountryName = " + firstCountryName);

        String secondCountryid = response.path("items.country_id[2]") ; //get the second country_id
        System.out.println("secondCountryid = " + secondCountryid);

        String secondCountryName = response.path("items.country_name[2]");
        System.out.println("secondCountryName = " + secondCountryName);


        String links = response.path("items.links[2].href[0]"); // http://34.227.112.9:1000/ords/hr/countries/CA
        System.out.println("links = " + links);

        //get all country names
       List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);


       //assert that all region ids are equal to 2
       List<Object> regionIDs = (response.path("items.region_id"));
        for (Object regionID : regionIDs) {
            System.out.println("regionID = " + regionID);
            assertEquals(regionID, 2);
        }

    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));

   //make sure we have pnly IT_PROG as a job_id
       List<String> jobIDs = response.path("items.job_id");
        for (String JOBid : jobIDs) {
            System.out.println("JOBid = " + JOBid);
            assertEquals(JOBid,"IT_PROG");

        }


    }

}
