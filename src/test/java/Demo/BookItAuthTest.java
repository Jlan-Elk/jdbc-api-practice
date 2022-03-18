package Demo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;

public class BookItAuthTest {

    @BeforeClass
    public void beforeclass() {
        baseURI = "https://cybertek-reservation-api-qa2.herokuapp.com";


    }

    @Test
    public void getAllCampuses(){

        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNDIwIiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.jGs3jHQtTzXgRYVyIgciD_FdCEVpHyx05sp4m6t8wR8";


        Response response = given().header("Authorization",accessToken).
                            when().get("api/campuses");

        response.prettyPrint();
        System.out.println(response.statusCode());
    }
}
