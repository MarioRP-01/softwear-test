package com.softwear.webapp5.data;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.model.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TransactionView {

    private List<TransactionViewEntry> transactionEntries= new ArrayList<>();
    private String type;
    private String date;
    private double totalPrice;

    public static class TransactionViewEntry {

        private Product product;
        private int quantity;
        private double totalPrice;

        private void updateTotalPrice() {
            totalPrice = quantity * product.getPrice();
        }

        public TransactionViewEntry(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
            updateTotalPrice();
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
            updateTotalPrice();
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
            updateTotalPrice();
        }

        public double getTotalPrice() {
            updateTotalPrice();
            return totalPrice;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TransactionViewEntry that = (TransactionViewEntry) o;
            return product.equals(that.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(product);
        }
    }

    public TransactionView() {}

    public TransactionView(Transaction transaction) {
        for(Product product: transaction.getProducts()) {
            if (!transactionEntries.contains(new TransactionViewEntry(product, 0))) {
                transactionEntries.add(new TransactionViewEntry(product, Collections.frequency(transaction.getProducts(), product)));
            }
        }
        date = transaction.getDate();
        totalPrice = transaction.getTotalPrice();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TransactionViewEntry> getTransactionEntries() {
        return transactionEntries;
    }

    public void setTransactionEntries(List<TransactionViewEntry> transactionEntries) {
        this.transactionEntries = transactionEntries;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
