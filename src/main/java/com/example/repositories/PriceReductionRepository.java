package com.example.repositories;

import com.example.entities.Item;
import com.example.entities.PriceReduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceReductionRepository extends JpaRepository<PriceReduction, Long> {
    PriceReduction findByIdpricereduction(Long id);
    PriceReduction findByReducedPrice(String reducedPrice);
}
