package com.example.repositories;

import com.example.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface countryRepository extends JpaRepository<Country, Long> {
    Country findCountryByCountryName(String countryName);
}
