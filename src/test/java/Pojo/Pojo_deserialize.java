package Pojo;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class Pojo_deserialize {

    @Test
    public void oneSpartanPojo(){
      Response response=  given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("http://34.227.112.9:8000/api/spartans/{id}");
        assertEquals(response.statusCode(),200);


        //Json POJO to de-serialize to java collection class
        //Json to our Spartan class object

        Spartan spartan15 = response.body().as(Spartan.class);
        System.out.println(spartan15);

        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());

        //assertion
        assertEquals(spartan15.getGender(),"Female");
        assertEquals(spartan15.getName(),"Meta");


    }
    @Test
    public void regionToPoJO(){
        Response response = when().get("http://34.227.112.9:1000/ords/hr/regions");
        assertEquals(response.statusCode(),200);

       //Jsn to Pojo (region class)
        Regions regions = response.body().as(Regions.class);

        System.out.println("regions.getCount() = " + regions.getCount());;
        System.out.println("regions.getHasMore() = " + regions.getHasMore());

        System.out.println(regions.getItems().get(0).getRegionName());

        List<Item> items = regions.getItems();

        System.out.println(items.get(0).getRegionId());
    }

    @Test
    public void gson_example(){

        Gson gson = new Gson();

        //JSON to JAVA Collection or POJO -->DE serialization

        String myJsonData ="{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        Map<String,Object> map = gson.fromJson(myJsonData, Map.class);
        System.out.println(map);

        Spartan spartan15 = gson.fromJson(myJsonData, Spartan.class);
        System.out.println(spartan15);

        //----------------SERIALIZATION-----------THE OPPOSITE ONE!!
        //JAVA collection or POJO to Json

        Spartan spartanEU = new Spartan(200,"Mike","Male",123123123);

        String jsonSpartanEU = gson.toJson(spartanEU);
        System.out.println(jsonSpartanEU);
    }

    }



