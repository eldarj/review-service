package com.ejahijagic.reviewservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reviews")
public class ProductReviewDocument implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Indexed
    private String id;

    private Double averageReviewScore;

    private Integer numberOfReviews;
}
