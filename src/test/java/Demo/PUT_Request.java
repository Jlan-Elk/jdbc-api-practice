package Demo;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PUT_Request {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void Put() {

        //create a map for the put request json body

        Map<String, Object> putRequestMap = new HashMap<>();

        putRequestMap.put("name", "PutName");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 1231223243);

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 90)
                .and()
                .body(putRequestMap).
                when().put("api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);


        //send get request to verify the body
    }

}