package com.myapp.camel.rest.api;

public class OrderRequest {

    private String name;
    private String item;

    public OrderRequest() {
        // Required by JAXB or Jackson for unmarshalling
    }

    // Optional constructor for convenience
    public OrderRequest(String name, String item) {
        this.name = name;
        this.item = item;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }
}
