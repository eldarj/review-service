package com.ejahijagic.reviewservice.service;

import com.ejahijagic.reviewservice.entity.ProductReviewDocument;
import com.ejahijagic.reviewservice.entity.ProductReviewRepository;
import com.hazelcast.map.IMap;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ProductReviewService {

    private ProductReviewRepository productReviewRepository;

    private IMap<String, ProductReviewDocument> cachedProductReviews;

    @PostConstruct
    public void init() {
        productReviewRepository.findAll().forEach(productReview -> cachedProductReviews.put(productReview.getId(), productReview));
    }

    public Collection<ProductReviewDocument> findAll() {
        return cachedProductReviews.values();
    }

    public ProductReviewDocument findProductById(String productId) {
        var productReview = cachedProductReviews.get(productId);

        if (productReview == null) {
            throw new NoSuchElementException();
        }

        return productReview;
    }

    public void create(ProductReviewDocument request) {
        ProductReviewDocument productReview = productReviewRepository.save(request);
        cachedProductReviews.put(productReview.getId(), productReview);
    }

    public void delete(String productId) {
        productReviewRepository.deleteById(productId);
        cachedProductReviews.remove(productId);
    }

    public void update(String id, ProductReviewDocument request) {
        var document = new ProductReviewDocument(id, request.getAverageReviewScore(), request.getNumberOfReviews());
        productReviewRepository.save(document);
        cachedProductReviews.put(id, document);
    }
}
