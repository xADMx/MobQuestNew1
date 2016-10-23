package com.example.adm.mobquestnew;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ADM on 26.07.2016.
 */
public class dbhalper {

    public final static String DB_Name = "DB";
    private DatabaseHelper mOpenHelper;
    SQLiteDatabase mDB;

    private final Context mCtx;

    public static class Tasks {
        public final String TABLE_NAME = "Tasks";
        public final String KEY_ID = "id_kod_close";
        public final String KEY_PARENT = "parent";
        public final String KEY_TYPE_WORK = "id_type_work";
        public final String KEY_TB = "tb";
        public final String KEY_TITLE = "title";
        public final String KEY_VALUE = "val";
        public final String KEY_ACTION = "action_table";
    }

    public static class news {
        public final String TABLE_NAME = "news";
        public final String KEY_ID = "id_news";
        public final String KEY_TB = "tb";
        public final String KEY_TITLE = "title";
        public final String KEY_TEXT = "news_text";
        public final String KEY_ACTION = "action_table";
    }

    public static class settings {
        public final String KEY_ID = "id_settings";
        public final String TABLE_NAME = "settings";
        public final String KEY_FIO = "fio";
        public final String KEY_TB = "tb";
        public final String KEY_EMAIL = "email";
    }

    public dbhalper(Context ctx) {
        this.mCtx = ctx;
    }

    public Cursor Query(String sqlQuery, String[] selectionArgs) {
        Cursor cursor = null;
        if (selectionArgs != null) {
            for (int i = 0; i < selectionArgs.length; i++) {
                sqlQuery = sqlQuery.replace("/{" + i + "}/", selectionArgs[i]);
                sqlQuery = sqlQuery.replace("{" + i + "}", "`" + selectionArgs[i] + "`");
            }
        }

        try {
            cursor = mDB.rawQuery(sqlQuery, null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return cursor;
    }


    public Boolean QueryInsert(String sqlQuery, String[] selectionArgs) {
        if (selectionArgs != null) {
            for (int i = 0; i < selectionArgs.length; i++) {
                sqlQuery = sqlQuery.replace("/{" + i + "}/", selectionArgs[i]);
                sqlQuery = sqlQuery.replace("{" + i + "}", "`" + selectionArgs[i] + "`");
            }
        }

        try {
            mDB.execSQL(sqlQuery);
            return true;
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return false;
    }

    public dbhalper open() throws SQLException {
        mOpenHelper = new DatabaseHelper(mCtx);
        mDB = mOpenHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mOpenHelper.close();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DB_Name, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // создаем таблицу с полями
     /*       db.execSQL("create table " + kod_close_fields.TABLE_NAME + " ("
                    + kod_close_fields.KEY_ID + " integer primary key,"
                    + kod_close_fields.KEY_TITLE + " VARCHAR(255),"
                    + kod_close_fields.KEY_PARENT + " integer,"
                    + kod_close_fields.KEY_TYPE_WORK + " integer,"
                    + kod_close_fields.KEY_ACTION + " integer,"
                    + kod_close_fields.KEY_TB + " integer(5),"
                    + kod_close_fields.KEY_VALUE + " varchar (5)" + "); "
                    + "CREATE INDEX index_of" + kod_close_fields.KEY_PARENT + " ON "
                    + kod_close_fields.TABLE_NAME + " ("
                    + kod_close_fields.KEY_PARENT + "); "
                    + "CREATE INDEX index_of" + kod_close_fields.KEY_TYPE_WORK + " ON "
                    + kod_close_fields.TABLE_NAME + " ("
                    + kod_close_fields.KEY_TYPE_WORK + "); ");

            db.execSQL("create table " + type_work_fields.TABLE_NAME + " ("
                    + type_work_fields.KEY_ID + " integer primary key,"
                    + type_work_fields.KEY_ACTION + " integer,"
                    + type_work_fields.KEY_TITLE + " VARCHAR(50)); ");

            db.execSQL("create table " + settings_fields.TABLE_NAME + " ("
                    + settings_fields.KEY_ID + " integer primary key,"
                    + settings_fields.KEY_FIO + " VARCHAR(255),"
                    + settings_fields.KEY_EMAIL + " VARCHAR(255),"
                    + settings_fields.KEY_TB + " integer(5)); ");

            db.execSQL("create table " + tasks_fields.TABLE_NAME + " ("
                    + tasks_fields.KEY_ID + " integer primary key,"
                    + tasks_fields.KEY_TITLE + " text,"
                    + tasks_fields.KEY_ADDRESS + " text,"
                    + tasks_fields.KEY_DESCRIPTION + " text,"
                    + tasks_fields.KEY_CH_TIME + " integer(11),"
                    + tasks_fields.KEY_ACTION + " integer); ");

            db.execSQL("create table " + check_list_head_fields.TABLE_NAME + " ("
                    + check_list_head_fields.KEY_ID + " integer primary key,"
                    + check_list_head_fields.KEY_TB + " integer(5),"
                    + check_list_head_fields.KEY_ACTION + " integer,"
                    + check_list_head_fields.KEY_TITLE + " VARCHAR(255)); ");

            db.execSQL("create table " + check_list_item_fields.TABLE_NAME + " ("
                    + check_list_item_fields.KEY_ID + " integer primary key,"
                    + check_list_item_fields.KEY_ACTION + " integer,"
                    + check_list_item_fields.KEY_TITLE + " VARCHAR(255),"
                    + check_list_item_fields.KEY_ID_TYPE_CHECK_LIST + " integer(11),"
                    + check_list_item_fields.KEY_ID_HEAD + " integer,"
                    + check_list_item_fields.KEY_VALUE + " integer ); "
                    + "CREATE INDEX index_of" + check_list_item_fields.KEY_ID_HEAD + " ON "
                    + check_list_item_fields.TABLE_NAME + " ("
                    + check_list_item_fields.KEY_ID_HEAD + "); ");

            db.execSQL("create table " + news_fields.TABLE_NAME + " ("
                    + news_fields.KEY_ID + " integer primary key,"
                    + news_fields.KEY_ACTION + " integer,"
                    + news_fields.KEY_TITLE + " VARCHAR(150),"
                    + news_fields.KEY_TEXT + " text,"
                    + news_fields.KEY_TB + " integer ); "
                    + "CREATE INDEX index_of" + check_list_item_fields.KEY_ID_HEAD + " ON "
                    + check_list_item_fields.TABLE_NAME + " ("
                    + check_list_item_fields.KEY_ID_HEAD + "); ");*/
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
