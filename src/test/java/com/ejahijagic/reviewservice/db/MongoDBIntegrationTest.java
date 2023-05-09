package com.ejahijagic.reviewservice.db;

import org.junit.AfterClass;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class MongoDBIntegrationTest {

    @Container
    public static MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:4.4.0").withExposedPorts(27017);

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        mongoDbContainer.start();
        String replicaSetUrl = mongoDbContainer.getReplicaSetUrl();
        registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
    }

    @AfterClass
    public static void down() {
        mongoDbContainer.stop();
    }
}
