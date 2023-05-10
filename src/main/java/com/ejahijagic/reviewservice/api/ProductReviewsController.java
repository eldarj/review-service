package com.ejahijagic.reviewservice.api;

import com.ejahijagic.reviewservice.data.ProductReviewService;
import com.ejahijagic.reviewservice.entity.ProductReviewDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-reviews")
@RequiredArgsConstructor
public class ProductReviewsController {

    private final ProductReviewService productReviewService;

    @PostMapping
    public void create(@RequestBody ProductReviewDocument productReviewDto) {
        productReviewService.create(productReviewDto);
    }

    @GetMapping
    public List<ProductReviewDocument> findAll() {
        return productReviewService.findAll();
    }

    @GetMapping("/{productId}")
    public ProductReviewDocument findById(@PathVariable String productId) {
        return productReviewService.findProductById(productId);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable String productId) {
        productReviewService.delete(productId);
    }

    @PutMapping("/{productId}")
    public void update(@PathVariable String productId, @RequestBody ProductReviewDocument request) {
        productReviewService.update(productId, request);
    }
}
