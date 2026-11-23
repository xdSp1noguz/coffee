package com.coffee;

import javax.persistence.*;

@Entity
@Table(name = "menu_item")
public class MenuItem {
    @Id
    @Column(name = "menu_item_name", length = 100, nullable = false)
    private String menuItemName;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private DrinkCategory drinkCategory;

    @Column(name = "volume", nullable = false)
    private int volume;

    public MenuItem() {
    }

    public MenuItem(String menuItemName, DrinkCategory drinkCategory, int volume) {
        this.menuItemName = menuItemName;
        this.drinkCategory = drinkCategory;
        this.volume = volume;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public DrinkCategory getDrinkCategory() {
        return drinkCategory;
    }

    public void setDrinkCategory(DrinkCategory drinkCategory) {
        this.drinkCategory = drinkCategory;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}