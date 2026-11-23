package com.coffee;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SaleId implements Serializable {
    @Column(name = "receipt_number", nullable = false)
    private int receiptNumber;

    @Column(name = "menu_item_name", length = 100, nullable = false)
    private String menuItemName;

    public SaleId() {
    }

    public SaleId(int receiptNumber, String menuItemName) {
        this.receiptNumber = receiptNumber;
        this.menuItemName = menuItemName;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleId saleId = (SaleId) o;
        return receiptNumber == saleId.receiptNumber && Objects.equals(menuItemName, saleId.menuItemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiptNumber, menuItemName);
    }
}