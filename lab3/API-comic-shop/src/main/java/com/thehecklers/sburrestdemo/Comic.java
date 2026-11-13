package com.thehecklers.sburrestdemo;

import java.util.UUID;

class Comic {
    private String id;
    private String name;
    private String publisher;
    private double price;

    public Comic() {
        this.id = UUID.randomUUID().toString();
    }

    public Comic(String id, String name, String publisher, double price) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.price = price;
    }

    public Comic(String name, String publisher, double price) {
        this(UUID.randomUUID().toString(), name, publisher, price);
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
