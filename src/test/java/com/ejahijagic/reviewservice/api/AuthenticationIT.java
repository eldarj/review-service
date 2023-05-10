package com.ejahijagic.reviewservice.api;

import com.ejahijagic.reviewservice.entity.ProductReviewDocument;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AuthenticationIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void up() {
        RestAssured.port = port;
    }

    @Test
    public void verify_that_unauthorized_requests_fail() {
        var request = new ProductReviewDocument("ABC123", 4.5, 100);

        given().body(request).contentType(JSON)
                .post("/api/product-reviews")
                .then()
                .statusCode(401);

        given().get("/api/product-reviews/ABC123")
                .then()
                .statusCode(401);

        given().body(request).contentType(JSON)
                .put("/api/product-reviews/ABC123")
                .then()
                .statusCode(401);

        given().body(request).contentType(JSON)
                .delete("/api/product-reviews/ABC123")
                .then()
                .statusCode(401);
    }
}
