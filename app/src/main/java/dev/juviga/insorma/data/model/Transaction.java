package dev.juviga.insorma.data.model;

import androidx.annotation.Nullable;

import java.util.Date;

import dev.juviga.insorma.data.repository.TransactionRepository;

public class Transaction {

    private int id;
    private final int userId;
    private final String productId;
    private final Date transactionDate;
    private final int quantity;

    /**
     * Will definitely be {@code null} if the repo doesn't include {@link Product} object.
     * Make sure to use {@link TransactionRepository} properly
     */
    @Nullable
    private Product product;

    public Transaction(int id, int userId, String productId, Date transactionDate, int quantity) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.transactionDate = transactionDate;
        this.quantity = quantity;
    }

    public Transaction(int userId, String productId, Date transactionDate, int quantity) {
        this(0, userId, productId, transactionDate, quantity);
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

    @Nullable
    public Product getProduct() {
        return product;
    }

    public void setProduct(@Nullable Product product) {
        this.product = product;
    }

}
