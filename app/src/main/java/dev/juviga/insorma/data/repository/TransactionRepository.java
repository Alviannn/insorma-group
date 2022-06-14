package dev.juviga.insorma.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.juviga.insorma.data.db.DatabaseHelper;
import dev.juviga.insorma.data.model.Transaction;
import dev.juviga.insorma.data.shared.SharedData;
import dev.juviga.insorma.utils.Closer;

public class TransactionRepository extends AbstractRepository<Transaction> {

    public static final String ID_COL = "TransactionID";
    public static final String TRANSACTION_DATE_COL = "TransactionDate";
    public static final String QUANTITY_COL = "Quantity";

    public static final String USER_ID_COL = "UserID";
    public static final String PRODUCT_ID_COL = "ProductID";

    @Override
    public void insert(@NonNull Transaction obj) {
        try (Closer closer = new Closer()) {
            SQLiteDatabase db = closer.add(helper.getWritableDatabase());
            DateFormat formatter = SharedData.SQL_DATE_FORMATTER;

            ContentValues values = new ContentValues();
            values.put(QUANTITY_COL, obj.getQuantity());
            values.put(TRANSACTION_DATE_COL, formatter.format(obj.getTransactionDate()));
            values.put(USER_ID_COL, obj.getUserId());
            values.put(PRODUCT_ID_COL, obj.getProductId());

            db.insertOrThrow(DatabaseHelper.TRANSACTIONS_TABLE, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Transaction mapResult(Cursor cursor) {
        int id = this.getInt(cursor, ID_COL);

        int quantity = this.getInt(cursor, QUANTITY_COL);
        Date transactionDate = this.getDate(cursor, TRANSACTION_DATE_COL);

        int userId = this.getInt(cursor, USER_ID_COL);
        String productId = this.getString(cursor, PRODUCT_ID_COL);

        return new Transaction(id, userId, productId, transactionDate, quantity);
    }

    @NonNull
    public List<Transaction> findAllByUserId(int userId) {
        List<Transaction> list = new ArrayList<>();

        try (Closer closer = new Closer()) {
            SQLiteDatabase db = closer.add(helper.getReadableDatabase());
            Cursor cursor = closer.add(db.query(DatabaseHelper.TRANSACTIONS_TABLE, null, null, null, null, null, null));

            while (cursor.moveToNext()) {
                Transaction transaction = this.mapResult(cursor);
                list.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
