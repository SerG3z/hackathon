package ru.yandex.yamblz.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 23.07.16.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    public static final String TABLE_WORDS = "table_words";
    public static final String WORD_ID = "id";
    public static final String WORD_COUNT = "count";
    public static final String WORD_RU = "word_ru";
    public static final String WORD_EN = "word_en";

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SqliteHelper(Context context) {
        super(context, TABLE_WORDS, null, 5);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_WORDS +
                " (" +
                WORD_ID + " integer primary key not null, " +
                WORD_COUNT + " integer, " +
                WORD_RU +" text , "+
                WORD_EN + " text );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS+";");
        onCreate(db);
    }

    public void deleteBase(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS+";");
    }
}
