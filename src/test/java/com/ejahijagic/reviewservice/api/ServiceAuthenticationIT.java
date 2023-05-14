package com.ejahijagic.reviewservice.api;

import com.ejahijagic.reviewservice.entity.ProductReviewDocument;
import com.ejahijagic.reviewservice.security.SecurityProperties;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ServiceAuthenticationIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void up() {
        RestAssured.port = port;
    }

    @Test
    public void verify_request_is_not_authorized_with_invalid_service_token() {
        var invalidToken = "1234567890";
        var serviceHeader = new Header("X-Service-Token", invalidToken);

        var request = new ProductReviewDocument("ABC123", 4.5, 100);
        given().header(serviceHeader).body(request).contentType(JSON)
                .post("/api/review")
                .then()
                .statusCode(401);
    }

    @Test
    public void verify_request_is_authorized_with_valid_service_token() {
        var validSignedToken = "PPn1bOpkch0cfb3b527e01a03d1b7445b8edf940b94a97e310";
        var serviceHeader = new Header("X-Service-Token", validSignedToken);

        var request = new ProductReviewDocument("ABC123", 4.5, 100);
        given().header(serviceHeader).body(request).contentType(JSON)
                .post("/api/review")
                .then()
                .statusCode(200);
    }
}
