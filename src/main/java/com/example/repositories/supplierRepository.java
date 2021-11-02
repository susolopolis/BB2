package com.example.repositories;

import com.example.entities.Item;
import com.example.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface supplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findSupplierByIdsupplier(Long id);
    Supplier findSupplierByName(String name);
}
