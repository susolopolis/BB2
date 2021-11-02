package com.example.controller;


import com.example.entities.Country;
import com.example.entities.Supplier;
import com.example.repositories.countryRepository;
import com.example.repositories.supplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/supplier")
@Controller
public class SupplierController {

    @Autowired
    supplierRepository supplierRepository;
    @Autowired
    countryRepository countryRepository;

    @PostMapping("/createSupplier/{creator:.+}")
    public String createSupplier(@RequestParam("name") String name, @RequestParam("country") String country, @PathVariable("creator") String creator,ModelMap model){
        Supplier supplier = new Supplier();
        Country countryToAdd = new Country();
        countryToAdd = countryRepository.findCountryByCountryName(country);
        supplier.setName(name);
        supplier.setCountry(countryToAdd.getCountryName());
        supplierRepository.save(supplier);
        model.addAttribute("message","Supplier created");
        model.addAttribute("userName", creator);
        return "User";
    }
    @GetMapping("/getSupplierView/{username:.+}")
    public String getSupplierView(@PathVariable("username") String username, ModelMap model){
        model.addAttribute(username);
        return "createSupplier";
    }

}
