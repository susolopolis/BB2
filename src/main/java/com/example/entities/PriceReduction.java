package com.example.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class PriceReduction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idpricereduction;

    @ManyToOne
    @JoinColumn(name="iditem")
    private Item item;

    private String reducedPrice;
    private Date startDate;
    private Date endDate;

    public void setItem(Item item) {
        this.item = item;
    }

    public String getReducedPrice() {
        return reducedPrice;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setReducedPrice(String reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
