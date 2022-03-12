package apitests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class spartanGetRequest {

      String spartanUrl = "http://34.227.112.9:8000";

      @Test
    public void  test1(){

          Response response = when().get(spartanUrl+"/api/spartans");

          System.out.println(response.statusCode() );
          response.prettyPrint();
    }

    /*TASK
    * When user send a get request to /api/spartans/3
    * Then status code should be 200
    * And content type shoulde be application/json:charset=UFT-8
    * and Json body should contain Fidole
    * */

    @Test
    public void test2(){

        Response response = when().get(spartanUrl+"/api/spartans/3");

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json");

        //verify Json body
        Assert.assertTrue(response.body().asString().contains("Fidole"));

    }

    /*Given no headers provided
    When users sends GET Request to api/hello
    Then response status code should be 200
    And Content type header should be "text/plain:charset=UTF-8"
    And Content-Length shoulde be 17
    And body should be "Hello from Sparta"
    * */
@Test
    public void helloSparta(){

    Response response = when().get(spartanUrl + "/api/hello");

    Assert.assertEquals(response.statusCode(),200);

    Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");

   //verify that we have headers  named date
    Assert.assertTrue(response.headers().hasHeaderWithName("Date"));

    //get the any header name or date
    System.out.println(response.header("Content-Type"));
    System.out.println(response.header("Date"));

    //verify content type lenght is 17
    Assert.assertEquals(response.header("Content-Length"),"17");

    //verify that hello from sparta
    Assert.assertEquals(response.getBody().asString(),"Hello from Sparta");
}

}
