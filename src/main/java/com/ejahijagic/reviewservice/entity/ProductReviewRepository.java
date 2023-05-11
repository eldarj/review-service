package com.ejahijagic.reviewservice.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductReviewRepository extends MongoRepository<ProductReviewDocument, String> {
}
