package com.example.capstoneprojectdemo;

public class Production {
    private int id;
    private int sallerid;
    private double price;
    private int quantity;
    private int selled_quantity;
    private String saledate;

    public Production() {
    }

    public Production(int id, int sallerid, double price, int quantity, int selled_quantity, String saledate) {
        this.id = id;
        this.sallerid = sallerid;
        this.price = price;
        this.quantity = quantity;
        this.selled_quantity = selled_quantity;
        this.saledate = saledate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSallerid() {
        return sallerid;
    }

    public void setSallerid(int sallerid) {
        this.sallerid = sallerid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSelled_quantity() {
        return selled_quantity;
    }

    public void setSelled_quantity(int selled_quantity) {
        this.selled_quantity = selled_quantity;
    }

    public String getSaledate() {
        return saledate;
    }

    public void setSaledate(String saledate) {
        this.saledate = saledate;
    }
}
