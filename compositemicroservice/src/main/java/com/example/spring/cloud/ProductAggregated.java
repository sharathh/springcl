package com.example.spring.cloud;

import java.util.List;
import java.util.stream.Collectors;

public class ProductAggregated {
    private int productId;
    private String name;
    private int weight;
    private List<RecommendationSummary> recommendations;
  
    public ProductAggregated(Product product, List<Recommendation> recommendations) {

        // 1. Setup product info
        this.productId = product.getProductId();
        this.name = product.getName();
      
        System.out.println("recomm: "+recommendations);
        // 2. Copy summary recommendation info, if available
        if (recommendations != null)
            this.recommendations = recommendations.stream()
                .map(r -> new RecommendationSummary(r.getRecommendationId(), r.getAuthor(), r.getRate()))
                .collect(Collectors.toList());

 
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public List<RecommendationSummary> getRecommendations() {
        return recommendations;
    }

  
}
