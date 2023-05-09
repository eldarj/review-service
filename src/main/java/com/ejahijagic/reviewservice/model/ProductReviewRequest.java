package com.ejahijagic.reviewservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewRequest {

    private Double averageReviewScore;

    private Integer numberOfReviews;
}
