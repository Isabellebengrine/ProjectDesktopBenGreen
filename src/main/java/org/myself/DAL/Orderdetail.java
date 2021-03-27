package org.myself.DAL;

public class Orderdetail {
    private int id;
    private Float price;
    private Float quantity;
    private Totalorder totalorder;
    private Product product;

    public Orderdetail() {
    }

    public Orderdetail(int id, Float price, Float quantity, Totalorder totalorder, Product product) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.totalorder = totalorder;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Totalorder getTotalorder() {
        return totalorder;
    }

    public void setTotalorder(Totalorder totalorder) {
        this.totalorder = totalorder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
