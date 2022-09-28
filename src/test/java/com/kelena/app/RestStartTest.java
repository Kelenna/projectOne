package com.kelena.app;

import com.kelena.app.Rest.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestStartTest {

    static String getPlaceResponse;

    @Test
    public void testAPI_1() {
        baseURI = "https://rahulshettyacademy.com/";
        String response = given()
                .log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .header("server", containsString("Apache"))
                .body("scope", equalTo("APP"))
                .extract().response().asString();

        JsonPath path = new JsonPath(response);
        String placeId = path.getString("place_id");
        System.out.println(placeId);

        String newAddress = "GG street";

        //UPDATE
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\r\n" +
                        "\"place_id\":\"" + placeId + "\",\r\n" +
                        "\"address\":\"" + newAddress + "\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all()
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"));


        //Get Place
        getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
        JsonPath js1 = new JsonPath(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newAddress);


        //parsing complex json
        JsonPath path2 = new JsonPath(payload.coursePrice());
        int count = path2.getInt("courses.size()");
        System.out.println(count);

        int purchAmount = path2.getInt("dashboard.purchaseAmount");
        System.out.println(purchAmount);

        String titleFirstCourse=path2.get("courses[2].title");
        System.out.println(titleFirstCourse);

        for(int i=0;i<count;i++)
        {
            String courseTitles=path2.get("courses["+i+"].title");
            System.out.println(path2.get("courses["+i+"].price").toString());
            System.out.println(courseTitles);
        }
    }

    @Test
    public void sumOfCourses()
    {
        int sum = 0;
        JsonPath js=new JsonPath(payload.coursePrice());
        int count=	js.getInt("courses.size()");
        for(int i=0;i<count;i++)
        {
            int price=js.getInt("courses["+i+"].price");
            int copies=js.getInt("courses["+i+"].copies");
            int amount = price * copies;
            System.out.println(amount);
            sum = sum + amount;

        }
        System.out.println(sum);
        int purchaseAmount =js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, purchaseAmount);
    }
}
