package dev.juviga.insorma.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_FILENAME = "insorma-group.db";
    public static final int DATABASE_VERSION = 1;

    private final String[] TABLE_CREATIONS = {
            "CREATE TABLE IF NOT EXISTS Users (" +
            "    UserID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    UserEmailAddress VARCHAR(255) NOT NULL," +
            "    UserUsername VARCHAR(255) NOT NULL," +
            "    UserPhoneNumber VARCHAR(255) NOT NULL," +
            "    UserPassword VARCHAR(255) NOT NULL" +
            ")",

            "CREATE TABLE IF NOT EXISTS Product (" +
            "    ProductName VARCHAR(255) PRIMARY KEY," +
            "    ProductRating DOUBLE NOT NULL," +
            "    ProductPrice INT NOT NULL," +
            "    ProductImage VARCHAR(255) NOT NULL," +
            "    ProductDescription VARCHAR(255) NOT NULL" +
            ")",

            "CREATE TABLE IF NOT EXISTS \"Transaction\" (" +
            "    TransactionID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    UserID INT NOT NULL," +
            "    ProductID INT NOT NULL," +
            "    TransactionDate DATE NOT NULL," +
            "    Quantity INT NOT NULL," +
            "" +
            "    FOREIGN KEY (UserID) REFERENCES Users (UserID)," +
            "    FOREIGN KEY (ProductID) REFERENCES Product (ProductID)" +
            ")"
    };

    private final String[] TABLE_DELETIONS = {
            "DROP TABLE IF EXISTS \"Transaction\"",
            "DROP TABLE IF EXISTS Users",
            "DROP TABLE IF EXISTS Product",
    };

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String query : TABLE_CREATIONS) {
            db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String query : TABLE_DELETIONS) {
            db.execSQL(query);
        }

        this.onCreate(db);
        this.close();
    }

}
