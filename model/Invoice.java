package model;

import java.util.Date;

public class Invoice {
    private String id;
    private String name;
    private String payingCustomer;
    private int number;
    private int price;
    private Date payedDate;

    public Invoice() {
    }

    public Invoice(String name, String payingCustomer, int price, Date payedDate) {
        this.name = name;
        this.payingCustomer = payingCustomer;
        this.price = price;
        this.payedDate = payedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayingCustomer() {
        return payingCustomer;
    }

    public void setPayingCustomer(String payingCustomer) {
        this.payingCustomer = payingCustomer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }
}
