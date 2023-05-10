package com.ejahijagic.reviewservice.api;

import com.ejahijagic.reviewservice.db.MongoDBIntegrationTest;
import com.ejahijagic.reviewservice.entity.ProductReviewDocument;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProductReviewsFlowIT extends MongoDBIntegrationTest {

    @LocalServerPort
    private int port;

    private RequestSpecification req;

    @BeforeEach
    public void up() {
        RestAssured.port = port;
        req = given().auth().basic("root", "toor");
    }

    @Test
    public void when_creating_a_review_then_get_returns_it() {
        var request = new ProductReviewDocument("ABC123", 4.5, 100);

        req.body(request).contentType(JSON)
                .post("/api/product-reviews")
                .then()
                .statusCode(200);

        var response = req.get("/api/product-reviews/ABC123")
                .then()
                .statusCode(200)
                .extract().body().as(ProductReviewDocument.class);

        assertEquals(request, response);
    }

    @Test
    public void when_a_review_has_been_updated_then_get_returns_expected_data() {
        var request = new ProductReviewDocument("ABC123", 3.0, 1);

        req.body(request).contentType(JSON).post("/api/product-reviews");

        request.setAverageReviewScore(4.0);
        request.setNumberOfReviews(2);
        req.body(request).contentType(JSON)
                .put("/api/product-reviews/ABC123")
                .then()
                .statusCode(200);

        var response = req.get("/api/product-reviews/ABC123").then()
                .statusCode(200)
                .extract().body().as(ProductReviewDocument.class);

        assertEquals(request, response);
    }

    @Test
    public void when_a_review_has_been_deleted_then_get_returns_not_found() {
        var request = new ProductReviewDocument("ABC123", 4.5, 100);

        req.body(request).contentType(JSON)
                .post("/api/product-reviews")
                .then()
                .statusCode(200);

        req.delete("/api/product-reviews/ABC123").then().statusCode(200);

        req.get("/api/product-reviews/ABC123").then().statusCode(404);
    }
}
