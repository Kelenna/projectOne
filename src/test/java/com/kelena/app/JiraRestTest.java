package com.kelena.app;

import com.kelena.app.Rest.RestSpecs;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.testng.annotations.Test;

import java.io.File;

import static com.kelena.app.Rest.RestSpecs.RestRespSpecAddComment;
import static io.restassured.RestAssured.given;

public class JiraRestTest {

    @Test
    public static void test1() {

        RestAssured.baseURI = "http://localhost:8080";

        SessionFilter session = new SessionFilter();

        given().header("Content-Type", "application/json")
                .body("{ \"username\": \"xiamiamx\", \"password\": \"iygs6J4!a8mveKA\" }")
                .filter(session)
                .when().post("/rest/auth/1/session")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().body().asString();

        given().pathParam("idCustom", "10002")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"body\": \"Lorem ipsum dolor sit amet elit.\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}")
                .filter(session)
                .when().post("/rest/api/2/issue/{idCustom}/comment")
                .then().log().all().assertThat().statusCode(201);

        given().header("X-Atlassian-Token", "no-check")
                .header("Content-Type", "multipart/form-data")
                .pathParam("idCustom", "10002")
                .filter(session)
                .multiPart("file", new File("C:\\Resources\\projects\\projectOne\\src\\test\\java\\resources\\jsons\\addBookJson.json"))
                .when().post("/rest/api/2/issue/{idCustom}/attachments")
                .then().log().all().assertThat().statusCode(200);

        String issueDetails = given()
                .filter(session).spec(RestSpecs.RestReqSpecAddComment()).log().all()
                .when().get("/rest/api/2/issue/{key}")
                .then().spec(RestRespSpecAddComment()).extract().response().asString();
        System.out.println(issueDetails);
    }
}
