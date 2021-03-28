package org.myself.DAL;

public class Product {
    private int id;
    private String name;
    private String description;
    private int stock;
    private String picture;
    private float price;
    //private Rubrique rubrique;

    public Product() {
    }

    public Product(int id, String name, String description, int stock, String picture, float price//, Rubrique rubrique
                   ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.picture = picture;
        this.price = price;
        //this.rubrique = rubrique;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

//    public Rubrique getRubrique() {
//        return rubrique;
//    }
//
//    public void setRubrique(Rubrique rubrique) {
//        this.rubrique = rubrique;
//    }
}
