package dev.juviga.insorma.data.repository;

import android.database.Cursor;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import dev.juviga.insorma.data.db.DatabaseHelper;
import dev.juviga.insorma.data.shared.SharedData;

public abstract class AbstractRepository<T> {

    protected final DatabaseHelper helper;

    public AbstractRepository() {
        this.helper = SharedData.DATABASE_HELPER;
    }

    public abstract T save(T value);

    @NonNull
    public abstract T mapResult(Cursor cursor);

    public String getString(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndexOrThrow(column));
    }

    public int getInt(Cursor cursor, String column) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(column));
    }

    public double getDouble(Cursor cursor, String column) {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(column));
    }

    public Date getDate(Cursor cursor, String column) {
        DateFormat formatter = SharedData.SQL_DATE_FORMATTER;
        String value = cursor.getString(cursor.getColumnIndexOrThrow(column));

        try {
            return formatter.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}