package com.coffee;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "coffee_order")
public class CoffeeOrder {
    @Id
    @Column(name = "receipt_number", nullable = false)
    private int receiptNumber;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "cafe", length = 100, nullable = false)
    private String cafe;

    public CoffeeOrder() {
    }

    public CoffeeOrder(int receiptNumber, LocalDate orderDate, String cafe) {
        this.receiptNumber = receiptNumber;
        this.orderDate = orderDate;
        this.cafe = cafe;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCafe() {
        return cafe;
    }

    public void setCafe(String cafe) {
        this.cafe = cafe;
    }
}