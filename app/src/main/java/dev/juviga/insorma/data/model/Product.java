package dev.juviga.insorma.data.model;

public class Product {

    private final String name;
    private final double rating;
    private final int price;
    private final int imageId;
    private final String description;

    public Product(String name, double rating, int price, int imageId, String description) {
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.imageId = imageId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public int getPrice() {
        return price;
    }

    public int getImageId() {
        return imageId;
    }

    public String getDescription() {
        return description;
    }

}
