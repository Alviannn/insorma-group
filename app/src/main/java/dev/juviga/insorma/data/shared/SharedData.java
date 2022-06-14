package dev.juviga.insorma.data.shared;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import dev.juviga.insorma.data.db.DatabaseHelper;

public class SharedData {

    public static DatabaseHelper DATABASE_HELPER;
    public final static DateFormat SQL_DATE_FORMATTER;

    static {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        SQL_DATE_FORMATTER = new SimpleDateFormat(dateFormat, Locale.getDefault());
    }

}
