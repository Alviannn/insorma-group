package dev.juviga.insorma.data.model;

public class Transaction {

    private int id;
    private final int userId;
    private final String productId;
    private final String transactionDate;
    private final int quantity;

    public Transaction(int id, int userId, String productId, String transactionDate, int quantity) {
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

    public String getTransactionDate() {
        return transactionDate;
    }

    public int getQuantity() {
        return quantity;
    }
}
