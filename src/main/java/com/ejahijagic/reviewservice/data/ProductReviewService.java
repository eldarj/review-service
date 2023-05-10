package com.ejahijagic.reviewservice.data;

import com.ejahijagic.reviewservice.entity.ProductReviewDocument;
import com.ejahijagic.reviewservice.entity.ProductReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductReviewService {

    private ProductReviewRepository productReviewRepository;

    public List<ProductReviewDocument> findAll() {
        return productReviewRepository.findAll();
    }

    public ProductReviewDocument findProductById(String productId) {
        return productReviewRepository.findById(productId).orElseThrow();
    }

    public void create(ProductReviewDocument productReviewDto) {
        productReviewRepository.save(productReviewDto);
    }

    public void delete(String productId) {
        productReviewRepository.deleteById(productId);
    }

    public void update(String id, ProductReviewDocument request) {
        var document = new ProductReviewDocument(id, request.getAverageReviewScore(), request.getNumberOfReviews());
        productReviewRepository.save(document);
    }
}
