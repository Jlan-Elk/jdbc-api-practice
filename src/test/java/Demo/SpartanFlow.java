package Demo;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanFlow {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("spartan_api_url");

    }

    //create a new spartan and delete it at the same time !!
    // but how u can run all tests in order? --> put priority for each test!!

    @Test(priority = 1)
    public void POSTNewSpartan(){

    }
    @Test(priority = 2)
    public void PUTExistingNewSpartan(){

    }
    @Test
    public void PATCHExistingNewSpartan(){

    }
    @Test
    public void GETThatNewSpartan(){

    }
    @Test
    public void DELETEExistingNewSpartan(){

    }

}
