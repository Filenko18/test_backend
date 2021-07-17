package ru.filenko18.tests;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static ru.filenko18.Endpoints.upload_Image;

public class NegativeImageTest extends BaseTest {
    MultiPartSpecification multiPartSpecNegative;
    static RequestSpecification negativeRequestSpecificationWithoutAuth;


    @BeforeEach
    void beforeTest(){

        multiPartSpecNegative = new MultiPartSpecBuilder(new File("src/test/resources/1x1.png"))
                .controlName("image")
                .build();

        negativeRequestSpecificationWithoutAuth = new RequestSpecBuilder()
                .addMultiPart(multiPartSpecNegative)
                .build();
    }

    @Test
    void uploadWithoutAuthTest() {
         given(negativeRequestSpecificationWithoutAuth,negativeRequestSpecification)
                .post(upload_Image)
                .prettyPeek();

    }
}
