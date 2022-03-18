package Demo;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PATCH_Request {


    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void Patch(){

        //create a map for the put request json body

        Map<String,Object> patchRequestMap = new HashMap<>();

        patchRequestMap.put("name","TJ");
        patchRequestMap.put("gender","Female");
        patchRequestMap.put("phone",1231223243);

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id",90)
                .and()
                .body(patchRequestMap).
                when().put("api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);


    }
}
