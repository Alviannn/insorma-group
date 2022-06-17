package dev.juviga.insorma.data.repository;

import android.content.ContentValues;
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
    public void insert(@NonNull User obj) {
        try (Closer closer = new Closer()) {
            SQLiteDatabase db = closer.add(helper.getWritableDatabase());

            ContentValues values = new ContentValues();
            values.put(EMAIL_COL, obj.getEmailAddress());
            values.put(USERNAME_COL, obj.getUsername());
            values.put(PHONE_COL, obj.getPhoneNumber());
            values.put(PASSWORD_COL, obj.getPassword());

            long rowId = db.insertOrThrow(DatabaseHelper.USERS_TABLE, null, values);
            obj.setId((int) rowId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUsernameById(int userId, String username) {
        try (Closer closer = new Closer()) {
            SQLiteDatabase db = closer.add(helper.getWritableDatabase());

            ContentValues values = new ContentValues();
            values.put(USERNAME_COL, username);

            db.update(DatabaseHelper.USERS_TABLE, values,
                    ID_COL + " = ?",
                    new String[]{userId + ""}
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        if (userId <= 0) {
            throw new IllegalStateException("user isn't inserted to db yet");
        }

        try (Closer closer = new Closer()) {
            SQLiteDatabase db = closer.add(helper.getWritableDatabase());
            db.delete(DatabaseHelper.USERS_TABLE,
                    ID_COL + " = ?",
                    new String[]{userId + ""}
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public User findByEmail(String email) {
        User user = null;

        try (Closer closer = new Closer()) {
            String selection = String.format("%s = ?", EMAIL_COL);
            String[] selectionArgs = {email};

            SQLiteDatabase db = closer.add(helper.getReadableDatabase());
            Cursor cursor = closer.add(db.query(DatabaseHelper.USERS_TABLE, null, selection, selectionArgs, null, null, null));

            if (cursor.moveToNext()) {
                user = this.mapResult(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Nullable
    public User findByUsername(String username) {
        User user = null;

        try (Closer closer = new Closer()) {
            String selection = String.format("%s = ?", USERNAME_COL);
            String[] selectionArgs = {username};

            SQLiteDatabase db = closer.add(helper.getReadableDatabase());
            Cursor cursor = closer.add(db.query(DatabaseHelper.USERS_TABLE, null, selection, selectionArgs, null, null, null));

            if (cursor.moveToNext()) {
                user = this.mapResult(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

}
