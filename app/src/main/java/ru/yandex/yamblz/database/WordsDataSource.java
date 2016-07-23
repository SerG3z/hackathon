package ru.yandex.yamblz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.meta.When;

import ru.yandex.yamblz.model.Word;

import static ru.yandex.yamblz.database.SqliteHelper.TABLE_WORDS;
import static ru.yandex.yamblz.database.SqliteHelper.WORD_COUNT;
import static ru.yandex.yamblz.database.SqliteHelper.WORD_EN;
import static ru.yandex.yamblz.database.SqliteHelper.WORD_ID;
import static ru.yandex.yamblz.database.SqliteHelper.WORD_RU;

/**
 * Created by user on 23.07.16.
 */

public class WordsDataSource {

    private SQLiteDatabase database;
    private SqliteHelper dbHelper;

    private String[] allColumns = new String[]{
            WORD_ID,
            WORD_COUNT,
            WORD_RU,
            WORD_EN };

    public WordsDataSource(Context context) {
        dbHelper = new SqliteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Word createWord(int count, String word_ru, String word_en) {
        ContentValues values = new ContentValues();
        values.put(WORD_COUNT, count);
        values.put(WORD_RU, word_ru);
        values.put(WORD_EN, word_en);
        long insertId = database.insert(TABLE_WORDS, null,
                values);
        Cursor cursor = database.query(TABLE_WORDS,
                allColumns, WORD_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Word word = cursorToWord(cursor);
        cursor.close();
        return word;
    }

    public void deleteWordById(long id) {
        database.delete(TABLE_WORDS, "id = " + Long.toString(id), null);
    }

    public void deleteBase() {
        dbHelper.deleteBase(database);
    }

    public Word getWordById(long id) {
        Cursor cursor = database.query(TABLE_WORDS, null, "id = " + Long.toString(id), null, null, null, null);
        Word word = cursorToWord(cursor);
        cursor.close();
        return word;
    }

    public List<Word> getWordObjByWord(String name, String lang) {
        String whereClause = "word_" + lang + " = ?";
        String[] whereArgs = new String[] {name};
        Cursor cursor = database.query(TABLE_WORDS, allColumns, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();
        List<Word> words = cursorToWords(cursor);
        cursor.close();
        return words;
    }

//    public List<Word> getAllArtists() {
//        List<Artist> comments = new ArrayList<Artist>();
//
//
//        Cursor cursor = database.query(MySQLiteHelper.TABLE_ARTISTS,
//                allColumns, null, null, null, null, null);
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            Artist artist = cursorToWord(cursor);
//            comments.add(artist);
//            if (comments.size() == 317) {
//                break;                    ////продолжает писать в бд по кругу?! wtf(поэтому здесь break)
//            }
//            cursor.moveToNext();
//        }
//        // make sure to close the cursor
//        cursor.close();
//        return comments;
//    }

    private Word cursorToWord(Cursor cursor) {
        return new Word(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getString(3));
    }

    private List<Word> cursorToWords(Cursor cursor) {
        List<Word> words = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            words.add(new Word(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3)));
            cursor.moveToNext();
        }
        return words;
    }
}
