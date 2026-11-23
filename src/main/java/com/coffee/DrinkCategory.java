package com.coffee;

import javax.persistence.*;

@Entity
@Table(name = "drink_category")
public class DrinkCategory {
    @Id
    @Column(name = "category", length = 100, nullable = false)
    private String category;

    @Column(name = "vessel", length = 100, nullable = false)
    private String vessel;

    public DrinkCategory() {
    }

    public DrinkCategory(String category, String vessel) {
        this.category = category;
        this.vessel = vessel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVessel() {
        return vessel;
    }

    public void setVessel(String vessel) {
        this.vessel = vessel;
    }
}