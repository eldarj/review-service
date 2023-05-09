package com.ejahijagic.reviewservice.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductReviewRepository extends MongoRepository<ProductReviewDocument, String> {
}
