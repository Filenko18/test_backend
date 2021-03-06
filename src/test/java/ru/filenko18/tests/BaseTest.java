package ru.filenko18.tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

public abstract class BaseTest {
    static ResponseSpecification positiveRequestSpecification;
    static ResponseSpecification negativeRequestSpecification;
    static RequestSpecification requestSpecificationWithAuth;
    static RequestSpecification requestSpecificationWithoutAuth;
    static Properties properties = new Properties();
    static String token;
    static String username;


    @BeforeAll
    static void beforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());
        RestAssured.baseURI = "https://api.imgur.com/3";
        getProperties();
        token = properties.getProperty("token");
        username = properties.getProperty("username");

        RestAssured.responseSpecification = positiveRequestSpecification;
        RestAssured.responseSpecification = negativeRequestSpecification;

        positiveRequestSpecification = new ResponseSpecBuilder()
                .expectBody("status", equalTo(200))
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectBody("success", is(true))
                .build();

        negativeRequestSpecification = new ResponseSpecBuilder()
                .expectBody("status", equalTo(401))
                .expectContentType(ContentType.JSON)
                .expectStatusCode(401)
                .expectBody("success", is(false))
                .build();


        requestSpecificationWithAuth = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .build();
        requestSpecificationWithoutAuth = new RequestSpecBuilder()
                .build();
    }


    private static void getProperties() {
        try (InputStream output = new FileInputStream("src/test/resources/configuration.properties")) {
            properties.load(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}