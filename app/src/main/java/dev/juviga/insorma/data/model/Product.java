package dev.juviga.insorma.data.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {

    private final String name;
    private final double rating;
    private final int price;
    private final String imageUrl;
    private final String description;

    public Product(String name, double rating, int price, String imageUrl, String description) {
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public static Product fromJsonObject(JSONObject json) throws JSONException {
        String name = json.getString("product_name");
        String image = json.getString("image");
        String description = json.getString("description");

        int price = json.getInt("price");
        double rating = json.getDouble("rating");

        return new Product(name, rating, price, image, description);
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

}
