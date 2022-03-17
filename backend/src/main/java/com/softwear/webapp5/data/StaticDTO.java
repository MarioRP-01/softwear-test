package com.softwear.webapp5.data;

public class StaticDTO {

    private String productName;
    private double earns; 
    private Long sales;

    public StaticDTO(String productName, double earns, Long sales) {
        this.productName = productName;
        this.earns = earns;
        this.sales = sales;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getEarns() {
        return earns;
    }

    public void setEarns(double earns) {
        this.earns = earns;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }  
}