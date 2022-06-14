package dev.juviga.insorma.data.model;

import java.util.Date;

public class Transaction {

    private int id;
    private final int userId;
    private final String productId;
    private final Date transactionDate;
    private final int quantity;

    public Transaction(int id, int userId, String productId, Date transactionDate, int quantity) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.transactionDate = transactionDate;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public int getQuantity() {
        return quantity;
    }

}
