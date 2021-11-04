package com.example.controller;

import com.example.entities.Item;
import com.example.entities.PriceReduction;
import com.example.entities.Supplier;
import com.example.repositories.PriceReductionRepository;
import com.example.repositories.itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/price")
@Controller
public class PriceReductionController {
    @Autowired
    PriceReductionRepository priceReductionRepository;

    @Autowired
    itemRepository itemRepository;

    @PostMapping("/createPrice/{creator:.+}")
    public String createPrice(@RequestParam("reduced") String reduced, @RequestParam("start") String start, @RequestParam("end") String end, @RequestParam("itemCode")String itemCode,@PathVariable("creator") String creator, ModelMap model){
        Item item = new Item();
        PriceReduction priceReduction = new PriceReduction();

        if(priceReductionRepository.findByReducedPrice(reduced) == null) {
            Date startDate = Date.valueOf(start);
            Date endDate = Date.valueOf(end);
            priceReduction.setReducedPrice(reduced);
            priceReduction.setEndDate(endDate);
            priceReduction.setStartDate(startDate);
            priceReductionRepository.save(priceReduction);
            model.addAttribute("message", "Price Reduction Created");
        }
        else{
            model.addAttribute("message", "Price Reduction Already Exist");
        }

        if(itemCode != null) {
            item = itemRepository.findByItemCode(itemCode);
            List<PriceReduction> priceList = item.getPriceReduction();
            boolean equal = false;

            for (int i = 0; i < priceList.size(); i++) {
                if (priceList.get(i).getReducedPrice().equals(priceReduction.getReducedPrice())) {
                    equal = true;
                }
            }
            if (equal == false) {
                item.addPriceReduction(priceReduction);
                priceReductionRepository.save(priceReduction);
                itemRepository.save(item);
                model.addAttribute("message", "Price Reduction created with item relationship");
                model.addAttribute("userName", creator);
                return "User";
            } else {
                model.addAttribute("message", "Error: The price reduction is already associated to the item.");
                model.addAttribute("userName", creator);
                return "User";
            }
        }

        priceReductionRepository.save(priceReduction);
        model.addAttribute("userName", creator);
        model.addAttribute("message", "Price Reduction created");
        return "User";
    }

    @GetMapping("/getPriceView/{username:.+}")
    public String getSupplierView(@PathVariable("username") String username, ModelMap model){
        model.addAttribute(username);
        return "createPriceReduction";
    }

    @GetMapping("/getPriceViewItem/{username}/{itemCode}")
    public String getPriceViewItem(@PathVariable("username")String username, @PathVariable("itemCode") String itemCode, ModelMap model){
        model.addAttribute("username", username);
        model.addAttribute("itemCode", itemCode);
        return "createPriceReduction";
    }
}
