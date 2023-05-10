package com.ejahijagic.reviewservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reviews")
public class ProductReviewDocument {

    @Id
    @Indexed
    private String id;

    private Double averageReviewScore;

    private Integer numberOfReviews;
}
