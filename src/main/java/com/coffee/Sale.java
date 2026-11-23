package com.coffee;

import javax.persistence.*;

@Entity
@Table(name = "sale")
public class Sale {
    @EmbeddedId
    private SaleId id;

    @ManyToOne
    @MapsId("receiptNumber")
    @JoinColumn(name = "receipt_number", nullable = false)
    private CoffeeOrder coffeeOrder;

    @ManyToOne
    @MapsId("menuItemName")
    @JoinColumn(name = "menu_item_name", nullable = false)
    private MenuItem menuItem;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private int price;

    public Sale() {
    }

    public Sale(CoffeeOrder coffeeOrder, MenuItem menuItem, int quantity, int price) {
        this.id = new SaleId(coffeeOrder.getReceiptNumber(), menuItem.getMenuItemName());
        this.coffeeOrder = coffeeOrder;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.price = price;
    }

    public SaleId getId() {
        return id;
    }

    public void setId(SaleId id) {
        this.id = id;
    }

    public CoffeeOrder getCoffeeOrder() {
        return coffeeOrder;
    }

    public void setCoffeeOrder(CoffeeOrder coffeeOrder) {
        this.coffeeOrder = coffeeOrder;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}