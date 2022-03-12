package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class spartanTestWithPath {

    @BeforeClass
    public void beforeclass() {
        baseURI = "http://34.227.112.9:8000";
    }

    /*
        Given accept type is json
        And path param spartan id is 11
        When user sends a get request to /spartans/{id}
       Then status code is 200
       And content type is Json
       And   {
{
  "id": 11,
  "name": "Nona",
  "gender": "Female",
  "phone": 7959094216
}
  */
    @Test
    public void getOneSpartan_path() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", "11")
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");


        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        int idJson = jsonPath.getInt("id");
        String nameJson = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");


        //print the values
        System.out.println("idJson = " + idJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //verification
        assertEquals(idJson, 11);
        assertEquals(nameJson, "Nona");
        assertEquals(gender, "Female");
        assertEquals(phone, 7959094216L);

    }

    @Test
    public void getAllSpartanWithPath(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");


        assertEquals(response.statusCode(),200);

        //verify content type
        assertEquals(response.getHeader("Content-Type"),"application/json");

       // System.out.println(response.path("id[0]").toString()); //index [0]--get brings the 1st element

        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String firstname = response.path("name[0]");
        System.out.println("firstname = " + firstname);

        String lastname = response.path("lastname[0]");
        System.out.println("lastname = " + lastname);

        int lastid = response.path("id[-1]");
        System.out.println("lastid = " + lastid);


    //HOW WE GONNA GET ALL IN A ONE SHOT ?
        // print all spartans
        //if you have more than one string --> we gonna use List OF STRING for getting all the elements of array.

        List<String> names= response.path("name");
        System.out.println("names = " + names);

        List<String>genders = response.path("gender");
        System.out.println("genders = " + genders);

        List<Object>phones = response.path("phone");
        for (Object phone : phones) {
            System.out.println("phones = " + phones);

        }


    }

}