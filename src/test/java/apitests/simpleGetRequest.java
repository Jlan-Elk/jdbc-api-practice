package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class simpleGetRequest {

    String hrUrl = "http://34.227.112.9:1000/ords/hr/regions";


    @Test
    public void test1() {

        Response response = RestAssured.get(hrUrl);

        //print the status code
        System.out.println(response.statusCode());

        //print the json body
        response.prettyPrint();


    }

    /*Given accept type of json
    *when user sends get request to regions endpoint
    *Then response status code must be 200
    *And body json format
    *  */

    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                                       .when().get(hrUrl);

       //verify the status code is 200
        Assert.assertEquals(response.statusCode(), 200);

       //verify content-type is application/Json
        System.out.println(response.contentType());
        Assert.assertEquals(response.contentType(), "application/json");
    }


    //another way to execute the testcase which is the easiest way;)
    @Test
    public void test3(){

     RestAssured.given().accept(ContentType.JSON)
                .when().get(hrUrl)
                .then().assertThat().statusCode(200)
                .and().contentType("application/json");

    }
    /*Given accept type is Json
    When user send get request to regions/2
    Then response status code must be 200
    and body is json format
    and response body contains Americas
    * */
    @Test
    public void test4(){

        Response response = given().accept(ContentType.JSON)
                            .when().get(hrUrl+"/2");

        Assert.assertEquals(response.getStatusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json");

        //verify that body contains Americas
        Assert.assertTrue(response.body().asString().contains("Americas"));

    }


}
