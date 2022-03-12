package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertTrue;

public class hrApiWithJsonPath {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("hr_api_url");

    }
    @Test
    public void test1(){
        Response response = get("/countries");
        
        //assign to jsonPath
        JsonPath jsonPath = response.jsonPath();
        
        String firstCountryName = jsonPath.getString("items.country_name[0]");
        System.out.println("firstCountryName = " + firstCountryName);

        //get all countryID
        List<String> AllCountryIDs = jsonPath.getList("items.country_id");
        System.out.println("AllCountryIDs = " + AllCountryIDs);

        //get all regionIDs
        List<Integer> AllRegionIDs = jsonPath.getList("items.region_id");
        System.out.println("AllRegionIDs = " + AllRegionIDs);

        //get all country names
        List<String> AllCountryNames = jsonPath.getList("items.country_name");
        System.out.println("AllCountryNames = " + AllCountryNames);

        //get all country_names where theri regions_id is equal to number 2

        List<String> allCountryNameWithRegionId2 = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println("allCountryNameWithRegionId2 = " + allCountryNameWithRegionId2);

    }
    @Test
    public void test2(){
        Response response = given().queryParam("limit",107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<String> firstNames = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(firstNames);

        //get me all firstname of emplyoees who is making more than 10000
        List<String> firstNames2 = jsonPath.getList("items.findAll {it.salary >10000}.first_name");
        System.out.println(firstNames2);

        //get me first name of who is making highest salary
        String kingName = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("kingName = " + kingName);
    }

}
