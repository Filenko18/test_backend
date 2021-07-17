package ru.filenko18.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static ru.filenko18.Endpoints.get_ACCOUNT;


public class AccountTests extends BaseTest {

    @Test
    void getAccountInfoTest() {
        given(requestSpecificationWithAuth,positiveRequestSpecification)
                .get(get_ACCOUNT, username)
                .prettyPeek();
    }

    @Test
    void getAccountInfoWithoutTest(){
        given(requestSpecificationWithoutAuth,negativeRequestSpecification)
                .get(get_ACCOUNT, username)
                .prettyPeek();
    }



}