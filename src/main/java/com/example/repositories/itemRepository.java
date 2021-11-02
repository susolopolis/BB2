package com.example.repositories;

import com.example.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface itemRepository extends JpaRepository<Item, Long> {
    Item findByItemCode(String itemCode);
}
