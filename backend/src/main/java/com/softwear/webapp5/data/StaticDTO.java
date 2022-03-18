package com.softwear.webapp5.data;

public class StaticDTO {

    private String productName;
    private double incomes; 
    private Long sales;

    public StaticDTO(String productName, double incomes, Long sales) {
        this.productName = productName;
        this.incomes = incomes;
        this.sales = sales;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getIncomes() {
        return incomes;
    }

    public void setIncomes(double incomes) {
        this.incomes = incomes;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }  
}