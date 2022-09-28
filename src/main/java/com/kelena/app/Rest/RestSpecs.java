package com.kelena.app.Rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestSpecs {

    private static String url = "http://localhost:8080";

    public static RequestSpecification RestReqSpecAddComment() {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addPathParam("key", "10002")
                .addQueryParam("fields", "comment")
                .build();
    }

    public static ResponseSpecification RestRespSpecAddComment() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

}
