package org.example.shopyearb.Entity;

public class Product {
private int id;
private double price;
private String name;
private String color;

    public Product(int id, double price, String name, String color) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product{" +
                "barcode=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}
