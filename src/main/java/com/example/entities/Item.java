package com.example.entities;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long iditem;

    @Column(unique=true)
    private String itemCode;
    private String Description;
    private int Price;
    private itemEnum itemEnum;

    @OneToMany
    @JoinTable(name = "item_suppliers",
            joinColumns = @JoinColumn(name = "idsupplier"),
            inverseJoinColumns = @JoinColumn(name = "iditem"))
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "item")
    private List<PriceReduction> priceReduction;

    private Timestamp creationDate;
    private String creatorUser;

    public String getItemCode() {
        return itemCode;
    }

    public itemEnum getItemEnum() {
        return itemEnum;
    }

    public int getPrice() {
        return Price;
    }

    public Long getIditem() {
        return iditem;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public String getDescription() {
        return Description;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public List<PriceReduction> getPriceReduction() {
        return priceReduction;
    }

    public void setCreationDate() {
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public void setIditem(Long iditem) {
        this.iditem = iditem;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemEnum(itemEnum itemEnum) {
        this.itemEnum = itemEnum;
    }

    public void setDefaultEnum(){
        this.itemEnum.setEstado("Active");
        this.itemEnum.setVal(1);
    }

    public void setPrice(int price) {
        Price = price;
    }


    public void setPriceReduction(List<PriceReduction> priceReduction) {
        this.priceReduction = priceReduction;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public void addSupplier(Supplier supplier){
        if(this.suppliers == null){
            this.suppliers = new ArrayList<Supplier>();
        }
        this.suppliers.add(supplier);
    }

    public void addPriceReduction(PriceReduction priceR){
        if(this.priceReduction == null){
            this.priceReduction = new ArrayList<PriceReduction>();
        }
        this.priceReduction.add(priceR);
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
