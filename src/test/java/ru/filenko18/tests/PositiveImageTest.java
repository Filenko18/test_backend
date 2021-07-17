package ru.filenko18.tests;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.filenko18.dto.PostImageResponse;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static ru.filenko18.Endpoints.deleteHash;
import static ru.filenko18.Endpoints.upload_Image;

public class PositiveImageTest extends BaseTest {
    private final String PATH_TO_IMAGE = "src/test/resources/480x360.jpg";
    static String encodedFile;
    String uploadedImageId;
    MultiPartSpecification base64MultiPartSpec;
    MultiPartSpecification multiPartSpecWithFile;
    MultiPartSpecification multiPartSpecWithFileHD;
    static RequestSpecification requestSpecificationWithAuthAndMultipartImage;
    static RequestSpecification requestSpecificationWithAuthWithBase64;
    static RequestSpecification requestSpecUpdateInfo;
    static RequestSpecification requestSpecificationWithAuthAndMultipartImageHD;


    @BeforeEach
    void beforeTest() {
        byte[] byteArray = getFileContent();
        encodedFile = Base64.getEncoder().encodeToString(byteArray);

        base64MultiPartSpec = new MultiPartSpecBuilder(encodedFile)
                .controlName("image")
                .build();

        multiPartSpecWithFile = new MultiPartSpecBuilder(new File("src/test/resources/1x1.png"))
                .controlName("image")
                .build();
        multiPartSpecWithFileHD = new MultiPartSpecBuilder(new File("src/test/resources/HD.jpg"))
                .controlName("image")
                .build();

        requestSpecificationWithAuthAndMultipartImageHD = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .addFormParam("title", "image")
                .addFormParam("type", "file")
                .addMultiPart(multiPartSpecWithFile)
                .build();

        requestSpecificationWithAuthAndMultipartImage = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .addFormParam("title", "image")
                .addFormParam("type", "file")
                .addMultiPart(multiPartSpecWithFileHD)
                .build();

        requestSpecificationWithAuthWithBase64 = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .addMultiPart(base64MultiPartSpec)
                .build();

//Request для изменения данных
        requestSpecUpdateInfo = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .addFormParam("title", "image2")
                .addMultiPart(multiPartSpecWithFile)
                .build();


    }

    @Test
    void uploadBase64Test() {
        uploadedImageId = given(requestSpecificationWithAuthWithBase64, positiveRequestSpecification)
                .post(upload_Image)
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData().getDeletehash();
    }


    @Test
    void uploadFileImageTest() {
        uploadedImageId = given(requestSpecificationWithAuthAndMultipartImage, positiveRequestSpecification)
                .post(upload_Image)
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData().getDeletehash();

    }

    @Test
    void uploadFileHDTest() {
        uploadedImageId = given(requestSpecificationWithAuthAndMultipartImageHD, positiveRequestSpecification)
                .post(upload_Image)
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(PostImageResponse.class)
                .getData().getDeletehash();

    }

    @AfterEach
    void tearDown() {
        given(requestSpecificationWithAuth, positiveRequestSpecification)
                .delete(deleteHash, username, uploadedImageId)
                .prettyPeek();
    }

    private byte[] getFileContent() {
        byte[] byteArray = new byte[0];
        try {
            byteArray = FileUtils.readFileToByteArray(new File(PATH_TO_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }
}