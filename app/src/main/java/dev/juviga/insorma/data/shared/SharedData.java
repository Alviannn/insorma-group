package dev.juviga.insorma.data.shared;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import dev.juviga.insorma.data.db.DatabaseHelper;
import dev.juviga.insorma.data.repository.ProductRepository;
import dev.juviga.insorma.data.repository.TransactionRepository;
import dev.juviga.insorma.data.repository.UserRepository;

public class SharedData {

    public static DatabaseHelper DATABASE_HELPER;

    public static UserRepository USER_REPOSITORY;
    public static ProductRepository PRODUCT_REPOSITORY;
    public static TransactionRepository TRANSACTION_REPOSITORY;

    public final static DateFormat SQL_DATE_FORMATTER;
    public final static String EMULATOR_PHONE_NUMBER = "+1-555-521-5554";

    static {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        SQL_DATE_FORMATTER = new SimpleDateFormat(dateFormat, Locale.getDefault());
    }

}
