package com.example.controller;


import com.example.entities.Country;
import com.example.entities.Item;
import com.example.entities.Supplier;
import com.example.repositories.countryRepository;
import com.example.repositories.itemRepository;
import com.example.repositories.supplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/supplier")
@Controller
public class SupplierController {

    @Autowired
    supplierRepository supplierRepository;
    @Autowired
    countryRepository countryRepository;
    @Autowired
    itemRepository itemRepository;

    @PostMapping("/createSupplier/{creator:.+}")
    public String createSupplier(@RequestParam("name") String name, @RequestParam("country") String country, @RequestParam("itemCode") String itemCode, @PathVariable("creator") String creator,ModelMap model){
        Supplier supplier = new Supplier();
        Item item = new Item();
        Country countryToAdd = new Country();
        countryToAdd = countryRepository.findCountryByCountryName(country);
        supplier.setName(name);
        supplier.setCountry(countryToAdd.getCountryName());

        if(itemCode != null){
            item = itemRepository.findByItemCode(itemCode);
            List<Supplier> supplierList = item.getSuppliers();
            boolean equal = false;

            for(int i=0;i<supplierList.size();i++){
                if(supplierList.get(i).getName().equals(supplier.getName())&&(supplierList.get(i).getCountry().equals(supplier.getCountry()))){
                    equal = true;
                }
            }
            if(equal == false) {
                item.addSupplier(supplier);
                supplierRepository.save(supplier);
                itemRepository.save(item);
                model.addAttribute("message","Supplier created with item relationship");
                model.addAttribute("userName", creator);
                return "User";
            }
            else {
                model.addAttribute("message","Error: The supplier is already associated to the item.");
                model.addAttribute("userName", creator);
                return "User";
            }
        }

        supplierRepository.save(supplier);
        model.addAttribute("message","Supplier created");
        model.addAttribute("userName", creator);
        return "User";
    }
    @GetMapping("/getSupplierView/{username:.+}")
    public String getSupplierView(@PathVariable("username") String username, ModelMap model){
        model.addAttribute("userName",username);
        return "createSupplier";
    }

    @GetMapping("/getSupplierViewItem/{username}/{itemCode}")
    public String getSupplierViewwithItem(@PathVariable("username")String username, @PathVariable("itemCode") String itemCode, ModelMap model){
        model.addAttribute("username", username);
        model.addAttribute("itemCode", itemCode);
        return "createSupplier";
    }
}
