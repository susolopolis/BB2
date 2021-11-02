package com.example.controller;


import com.example.entities.*;
import com.example.repositories.itemRepository;
import com.example.repositories.PriceReductionRepository;
import com.example.repositories.supplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/item")
@Controller
public class ItemController {

    @Autowired
    itemRepository itemRepository;
    @Autowired
    supplierRepository supplierRepository;
    @Autowired
    PriceReductionRepository reductionRepository;


    @GetMapping("/getItemsUser/{username:.+}")
    public String getItemsUser(@PathVariable("username") String username, ModelMap model){
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("list", itemList);
        model.addAttribute("userName",username);
        return "User";

    }
    @PostMapping("/listItems")
    public String listItems(@RequestParam("itemCode") String itemCode, @RequestParam("MaxPrice") String MaxPrice, @RequestParam("MinPrice") String MinPrice, @RequestParam("itemEnum") String itemEnum, ModelMap model){
        List<Item> itemList = itemRepository.findAll();
        List<Item >finalItemList = new ArrayList<Item>();

        int maxPrice;
        int minPrice;

        if(MaxPrice == ""){
            maxPrice = 10000000;
        }
        else{
            maxPrice = Integer.parseInt(MaxPrice);
        }
        if(MinPrice == ""){
            minPrice = -1;
        }
        else {
            minPrice = Integer.parseInt(MinPrice);
        }

        for(int i=0;i<itemList.size();i++){
            if(((itemList.get(i).getPrice() <= maxPrice)&&(itemList.get(i).getPrice() >= minPrice))){
                if((itemList.get(i).getItemCode().equals(itemCode))||(itemCode.isEmpty())){
                    if((itemList.get(i).getItemEnum().getEstado().equals(itemEnum))||(itemEnum.isEmpty())){
                        finalItemList.add(itemList.get(i));
                    }
                }
            }
        }
        model.addAttribute("list", finalItemList);
        return "items";
    }

    @GetMapping("/getCreateView/{username:.+}")
    public String getCreateView( @PathVariable("username") String username, ModelMap model){
        model.addAttribute(username);
        return "createItem";
    }

    @PostMapping("/createItem/{creator:.+}")
    public String createItem(@RequestParam("itemcode") String itemcode, @RequestParam("description")String description, @RequestParam("price") String Price, @PathVariable("creator") String creator, @RequestParam("suppliers") String Suppliers, @RequestParam("reductions") String reductions, ModelMap model){
        Item newItem = new Item();
        String[] suppliersIds = Suppliers.split(",");
        String[] reductionsList = reductions.split(",");

        for(int i=0;i<suppliersIds.length;i++){
            Supplier supplier = supplierRepository.findSupplierByIdsupplier(Long.parseLong(suppliersIds[i]));
            newItem.addSupplier(supplier);
        }

        for(int i=0;i<reductionsList.length;i++){
            PriceReduction priceReduction = reductionRepository.findByIdpricereduction(Long.parseLong(reductionsList[i]));
            priceReduction.setItem(newItem);
            newItem.addPriceReduction(priceReduction);
        }

        itemEnum itemEnum = com.example.entities.itemEnum.ACTIVE;
        newItem.setItemCode(itemcode);
        newItem.setDescription(description);
        newItem.setItemEnum(itemEnum);
        newItem.setCreationDate();
        int PriceInt = Integer.parseInt(Price);
        newItem.setPrice(PriceInt);
        newItem.setCreatorUser(creator);
        itemRepository.save(newItem);
        model.addAttribute("userName", creator);
        model.addAttribute("message","The product has been created!");
        return "User";
    }

    @GetMapping("/getInfo/{item:.+}")
    public String getItemInfo(@PathVariable("item") String itemCode, ModelMap model){
        Item item = itemRepository.findByItemCode(itemCode);
        model.addAttribute("item",item);
        return "itemInfo";
    }

    @GetMapping("/changeActive/{itemCode:.+}")
    public String changeActive(@PathVariable("itemCode")String itemCode, ModelMap model){
        Item item = itemRepository.findByItemCode(itemCode);
        itemEnum itemEnum = com.example.entities.itemEnum.ACTIVE;
        item.setItemEnum(itemEnum);
        model.addAttribute("item",item);
        return "itemInfo";
    }

    @GetMapping("/changeInactive/{itemCode:.+}")
    public String changeInactive(@PathVariable("itemCode")String itemCode, ModelMap model){
        Item item = itemRepository.findByItemCode(itemCode);
        itemEnum itemEnum = com.example.entities.itemEnum.NO_ACTIVE;
        item.setItemEnum(itemEnum);
        model.addAttribute("item",item);
        return "itemInfo";
    }

    @GetMapping("/saveChanges")
    public String saveChanges(@RequestParam("itemCode") String itemCode, @RequestParam("Description") String Description, @RequestParam("price") String Price,@RequestParam("date") String date, @RequestParam("creator") String creator, @RequestParam("state") String state, ModelMap model){
        Item item = itemRepository.findByItemCode(itemCode);
        item.setCreatorUser(creator);
        item.setDescription(Description);
        item.setCreationDate();
        item.setPrice(Integer.parseInt(Price));
        if(state == "Active"){
            item.setDefaultEnum();
        }
        else{
            itemEnum itemEnum = com.example.entities.itemEnum.NO_ACTIVE;
            item.setItemEnum(itemEnum);
        }

        Timestamp newDate = Timestamp.valueOf(date);
        item.setCreationDate(newDate);
        model.addAttribute("message","Item " + itemCode + " correctly edited");
        model.addAttribute("userName", creator);
        itemRepository.save(item);
        return "User";
    }

}
