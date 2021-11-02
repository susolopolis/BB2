package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idcountry;
    private String countryCode;
    private String countryName;

    public String getCountryName() {
        return countryName;
    }
}
