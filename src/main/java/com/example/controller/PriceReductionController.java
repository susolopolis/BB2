package com.example.controller;

import com.example.entities.PriceReduction;
import com.example.repositories.PriceReductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/price")
@Controller
public class PriceReductionController {
    @Autowired
    PriceReductionRepository priceReductionRepository;

    @PostMapping("/createPrice/{creator:.+}")
    public String createPrice(@RequestParam("reduced") String reduced, @RequestParam("start") String start, @RequestParam("end") String end, @PathVariable("creator") String creator, ModelMap model){
        if(priceReductionRepository.findByReducedPrice(reduced) == null) {
            Date startDate = Date.valueOf(start);
            Date endDate = Date.valueOf(end);
            PriceReduction priceReduction = new PriceReduction();
            priceReduction.setReducedPrice(reduced);
            priceReduction.setEndDate(endDate);
            priceReduction.setStartDate(startDate);
            priceReductionRepository.save(priceReduction);
            model.addAttribute("message", "Price Reduction Created");
        }
        else{
            model.addAttribute("message", "Price Reduction Already Exist");
        }
        model.addAttribute("userName", creator);
        return "User";
    }
    @GetMapping("/getPriceView/{username:.+}")
    public String getSupplierView(@PathVariable("username") String username, ModelMap model){
        model.addAttribute(username);
        return "createPriceReduction";
    }
}
