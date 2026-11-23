package com.coffee;

import java.time.LocalDate;

public class CoffeeSaleDTO {
    private int receiptNumber;
    private LocalDate orderDate;
    private String cafe;
    private String menuItemName;
    private int volume;
    private String category;
    private String vessel;
    private int quantity;
    private int price;

    public CoffeeSaleDTO(int receiptNumber, LocalDate orderDate, String cafe,
                         String menuItemName, int volume, String category,
                         String vessel, int quantity, int price) {
        this.receiptNumber = receiptNumber;
        this.orderDate = orderDate;
        this.cafe = cafe;
        this.menuItemName = menuItemName;
        this.volume = volume;
        this.category = category;
        this.vessel = vessel;
        this.quantity = quantity;
        this.price = price;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getCafe() {
        return cafe;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public int getVolume() {
        return volume;
    }

    public String getCategory() {
        return category;
    }

    public String getVessel() {
        return vessel;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}