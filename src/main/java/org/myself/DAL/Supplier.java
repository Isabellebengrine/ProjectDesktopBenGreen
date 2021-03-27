package org.myself.DAL;

public class Supplier {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String phone;
    private Supplierstype supplierstype;

    public Supplier() {
    }

    public Supplier(int id, String name, String address, String zipcode, String city, String phone, Supplierstype supplierstype) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.phone = phone;
        this.supplierstype = supplierstype;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Supplierstype getSupplierstype() {
        return supplierstype;
    }

    public void setSupplierstype(Supplierstype supplierstype) {
        this.supplierstype = supplierstype;
    }
}
