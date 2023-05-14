package com.ejahijagic.reviewservice.api;

import com.ejahijagic.reviewservice.entity.ProductReviewDocument;
import com.ejahijagic.reviewservice.service.ProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ProductReviewsController {

    private final ProductReviewService productReviewService;

    @PostMapping
    public void create(@RequestBody ProductReviewDocument productReviewDto) {
        productReviewService.create(productReviewDto);
    }

    @GetMapping
    public Collection<ProductReviewDocument> findAll() {
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
