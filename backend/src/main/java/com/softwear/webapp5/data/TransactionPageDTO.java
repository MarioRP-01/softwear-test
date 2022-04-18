package com.softwear.webapp5.data;

import java.util.List;

import com.softwear.webapp5.model.Transaction;

public class TransactionPageDTO {

    private List<Transaction> transactions;
    private int totalPages;
 
    public TransactionPageDTO(List<Transaction> transactions, int totalPages) {
        this.transactions = transactions;
        this.totalPages = totalPages;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
   
}
