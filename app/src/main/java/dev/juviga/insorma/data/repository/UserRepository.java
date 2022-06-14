package dev.juviga.insorma.data.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dev.juviga.insorma.data.db.DatabaseHelper;
import dev.juviga.insorma.data.model.User;
import dev.juviga.insorma.utils.Closer;

public class UserRepository extends AbstractRepository<User> {

    public static final String ID_COL = "UserID";
    public static final String EMAIL_COL = "UserEmailAddress";
    public static final String USERNAME_COL = "UserUsername";
    public static final String PHONE_COL = "UserPhoneNumber";
    public static final String PASSWORD_COL = "UserPassword";

    @Override
    public User save(User value) {
        return null;
    }

    @NonNull
    @Override
    public User mapResult(Cursor cursor) {
        int id = this.getInt(cursor, ID_COL);

        String email = this.getString(cursor, EMAIL_COL);
        String username = this.getString(cursor, USERNAME_COL);
        String phone = this.getString(cursor, PHONE_COL);
        String password = this.getString(cursor, PASSWORD_COL);

        return new User(id, username, email, phone, password);
    }

    @Nullable
    public User findUserByEmail(String email) {
        User user = null;

        try (Closer closer = new Closer()) {
            String selection = String.format("%s = ?", EMAIL_COL);
            String[] selectionArgs = {email};

            SQLiteDatabase db = closer.add(helper.getReadableDatabase());
            Cursor cursor = closer.add(db.query(DatabaseHelper.USERS_TABLE, null, selection, selectionArgs, null, null, null));

            user = this.mapResult(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Nullable
    public User findUserByUsernameAndEmail(String username, String email) {
        User user = null;

        try (Closer closer = new Closer()) {
            String selection = String.format("%s = ? AND %s = ?", USERNAME_COL, EMAIL_COL);
            String[] selectionArgs = {username, email};

            SQLiteDatabase db = closer.add(helper.getReadableDatabase());
            Cursor cursor = closer.add(db.query(DatabaseHelper.USERS_TABLE, null, selection, selectionArgs, null, null, null));

            user = this.mapResult(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

}
