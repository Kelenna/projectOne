package com.kelena.app;

import com.kelena.app.Rest.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestDynJsonsTests {

    @Test
    public void testAddBook() {
        RestAssured.baseURI = "http://216.10.245.166";
        String payloadJson = payload.addBook(RandomStringUtils.randomAlphabetic(3),
                RandomStringUtils.randomNumeric(5));
        String addResponse =
                given()
                        .body(payloadJson)
                        .header("Content-Type", "application/json").log().all()
                        .when().post("Library/Addbook.php")
                        .then().assertThat()
                        .statusCode(200)
                        .body("Msg", equalTo("successfully added"))
                        .extract().response().asString();
        System.out.println(addResponse);

        JsonPath pathAct = new JsonPath(addResponse);
        String idAct = pathAct.get("ID");

        JsonPath pathExp = new JsonPath(payloadJson);
        Assert.assertEquals(pathExp.getString("isbn") + pathExp.getString("aisle"), idAct);
    }

    @Test(dataProvider = "bookIds")
    public void testAddBookDataProvider(String isbn, String aisle) {
        String payloadJson = payload.addBook(isbn, aisle);
        String addResponse =
                given()
                        .body(payloadJson)
                        .header("Content-Type", "application/json").log().all()
                        .when().post("Library/Addbook.php")
                        .then().assertThat()
                        .statusCode(200)
                        .extract().response().asString();

        JsonPath pathAct = new JsonPath(addResponse);
        String idAct = pathAct.get("ID");

        JsonPath pathExp = new JsonPath(payloadJson);
        Assert.assertEquals(pathExp.getString("isbn") + pathExp.getString("aisle"), idAct);
    }

    @DataProvider(name = "bookIds")
    public Object[][] testData() {
        return new Object[][]{{"aad", "11"}, {"bbd", "22"}, {"ccd", "33"}};
    }

    @Test
    public void testAddBookFile() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        byte[] payloadJsonBytes = Files.readAllBytes(Paths.get("C:\\Resources\\projects\\projectOne\\src\\test\\java\\resources\\jsons\\addBookJson.json"));
        String payloadJsonString = new String(payloadJsonBytes);
        String addResponse =
                given()
                        .body(payloadJsonString)
                        .header("Content-Type", "application/json").log().all()
                        .when().post("Library/Addbook.php")
                        .then().assertThat()
                        .statusCode(200)
                        .extract().response().asString();

        JsonPath pathAct = new JsonPath(addResponse);
        String idAct = pathAct.get("ID");

        JsonPath pathExp = new JsonPath(payloadJsonString);
        Assert.assertEquals(pathExp.getString("isbn") + pathExp.getString("aisle"), idAct);
    }
}
