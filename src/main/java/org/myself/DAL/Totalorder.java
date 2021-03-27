package org.myself.DAL;

import java.sql.Date;

public class Totalorder {
    private int id;
    private Date date;
    private String billaddress;
    private String deliveraddress;
    private Float discount;
    private int invoicenb;
    private Date invoicedate;
    private String status;
    private Date updatedAt;
    private Customer customer;

    public Totalorder() {
    }

    public Totalorder(int id, Date date, String billaddress, String deliveraddress, Float discount, int invoicenb, Date invoicedate, String status, Date updatedAt, Customer customer) {
        this.id = id;
        this.date = date;
        this.billaddress = billaddress;
        this.deliveraddress = deliveraddress;
        this.discount = discount;
        this.invoicenb = invoicenb;
        this.invoicedate = invoicedate;
        this.status = status;
        this.updatedAt = updatedAt;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBilladdress() {
        return billaddress;
    }

    public void setBilladdress(String billaddress) {
        this.billaddress = billaddress;
    }

    public String getDeliveraddress() {
        return deliveraddress;
    }

    public void setDeliveraddress(String deliveraddress) {
        this.deliveraddress = deliveraddress;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public int getInvoicenb() {
        return invoicenb;
    }

    public void setInvoicenb(int invoicenb) {
        this.invoicenb = invoicenb;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
