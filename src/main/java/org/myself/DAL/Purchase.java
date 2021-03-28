package org.myself.DAL;

import java.sql.Date;

public class Purchase {
    private int id;
    private String suppliersRef;
    private Date date;
    private Float price;
    private int quantity;
    //private Supplier supplier;
    //private Product product;

    public Purchase() {
    }

    public Purchase(int id, String suppliersRef, Date date, Float price, int quantity//, Supplier supplier, Product product
                     ) {
        this.id = id;
        this.suppliersRef = suppliersRef;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        //this.supplier = supplier;
        //this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuppliersRef() {
        return suppliersRef;
    }

    public void setSuppliersRef(String suppliersRef) {
        this.suppliersRef = suppliersRef;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public Supplier getSupplier() {
//        return supplier;
//    }
//
//    public void setSupplier(Supplier supplier) {
//        this.supplier = supplier;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
}
