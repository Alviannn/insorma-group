package dev.juviga.insorma.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import dev.juviga.insorma.data.db.DatabaseHelper;
import dev.juviga.insorma.data.model.Product;
import dev.juviga.insorma.utils.Closer;

public class ProductRepository extends AbstractRepository<Product> {

    public static final String NAME_COL = "ProductName";
    public static final String RATING_COL = "ProductRating";
    public static final String PRICE_COL = "ProductPrice";
    public static final String IMAGE_URL_COL = "ProductImage";
    public static final String DESCRIPTION_COL = "ProductDescription";

    @Override
    public Product save(@NonNull Product obj) {
        try (Closer closer = new Closer()) {
            SQLiteDatabase db = closer.add(helper.getWritableDatabase());

            ContentValues values = new ContentValues();
            values.put(NAME_COL, obj.getName());
            values.put(RATING_COL, obj.getRating());
            values.put(PRICE_COL, obj.getPrice());
            values.put(IMAGE_URL_COL, obj.getImageUrl());
            values.put(DESCRIPTION_COL, obj.getDescription());

            db.insertOrThrow(DatabaseHelper.PRODUCTS_TABLE, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @NonNull
    @Override
    public Product mapResult(Cursor cursor) {
        String name = this.getString(cursor, NAME_COL);
        String imageUrl = this.getString(cursor, IMAGE_URL_COL);
        String description = this.getString(cursor, DESCRIPTION_COL);

        double rating = this.getDouble(cursor, RATING_COL);
        int price = this.getInt(cursor, PRICE_COL);

        return new Product(name, rating, price, imageUrl, description);
    }

    @Nullable
    public Product findByName(String name) {
        Product product = null;

        try (Closer closer = new Closer()) {
            String selection = String.format("%s = ?", NAME_COL);
            String[] selectionArgs = {name};

            SQLiteDatabase db = closer.add(helper.getReadableDatabase());
            Cursor cursor = closer.add(db.query(DatabaseHelper.PRODUCTS_TABLE, null, selection, selectionArgs, null, null, null));

            product = this.mapResult(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @NonNull
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();

        try (Closer closer = new Closer()) {
            SQLiteDatabase db = closer.add(helper.getReadableDatabase());
            Cursor cursor = closer.add(db.query(DatabaseHelper.PRODUCTS_TABLE, null, null, null, null, null, null));

            while (cursor.moveToNext()) {
                Product product = this.mapResult(cursor);
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
