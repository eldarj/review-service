package com.ejahijagic.reviewservice.api;

import com.ejahijagic.reviewservice.db.MongoDBIntegrationTest;
import com.ejahijagic.reviewservice.model.ProductReviewRequest;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProductReviewsIntegrationTestRequest extends MongoDBIntegrationTest {

    @LocalServerPort
    private int port;

    @Before
    public void up() {
        RestAssured.port = port;
    }

    @Test
    public void when_create_product_review_then_receive_ok() {
        var productReview = new ProductReviewRequest(4.5, 100);

        var response = RestAssured.given()
                .body(productReview)
                .post("/api/product-reviews");

        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void when_get_list_of_product_reviews_then_receive_ok() {
        var response = RestAssured.get("/api/product-reviews");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void when_get_product_review_by_id_then_receive_ok() {
        var response = RestAssured.get("/api/product-reviews");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void when_delete_product_review_then_receive_ok() {

    }

    @Test
    public void when_update_product_review_then_receive_ok() {

    }
}
