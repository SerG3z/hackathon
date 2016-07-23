package ru.yandex.yamblz.model;

/**
 * Created by user on 23.07.16.
 */

public class Word {

    private int id;
    private int count;
    private String word_ru;
    private String word_en;

    public Word(int id, int count, String word_ru, String word_en) {
        this.id = id;
        this.count = count;
        this.word_ru = word_ru;
        this.word_en = word_en;
    }

    public long getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public String getWord_ru() {
        return word_ru;
    }

    public String getWord_en() {
        return word_en;
    }
}
