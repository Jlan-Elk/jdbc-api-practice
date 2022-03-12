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
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class spartanTestWithJsonPath {

    @BeforeClass
    public void beforeclass() {

        baseURI = ConfigurationReader.get("spartan_api_url");
    }
    /*
            Given accept type is json
            And path param spartan id is 19
            When user sends a get request to /spartans/{id}
           Then status code is 200
           And content type is Json
           And {
    "id": 19,
    "name": "Bunnie",
    "gender": "Female",
    "phone": 7662992732
}
      */
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",19)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //verify with id and name with path();

        int id = response.path("id");
        String name = response.path("name");

        assertEquals(id,19);
        assertEquals(name,"Bunnie");


        //verify with id and name with Jsonpath();
         JsonPath jsonPath = response.jsonPath();
         int idJson = jsonPath.getInt("id");
         String nameJson = jsonPath.getString("name");
         long phoneJson = jsonPath.getLong("phone");
         String genderJson = jsonPath.getString("gender");

        System.out.println("idJson = " + idJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("phoneJson = " + phoneJson);
        System.out.println("genderJson = " + genderJson);

        //verify that info
        assertEquals(idJson,19);
        assertEquals(nameJson,"Bunnie");
        assertEquals(phoneJson,7662992732l);
        assertEquals(genderJson,"Female");


    }
}