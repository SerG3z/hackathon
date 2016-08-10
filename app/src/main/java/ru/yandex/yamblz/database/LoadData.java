package ru.yandex.yamblz.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.model.Word;

/**
 * Created by user on 23.07.16.
 */

public class LoadData extends AsyncTask<Context, Void, Void> {

    private WordsDataSource wordsDataSource = null;

    @Override
    protected Void doInBackground(Context... params) {

        wordsDataSource = new WordsDataSource(params[0]);
        wordsDataSource.open();
        JSONArray english = LoadDatabaseFromJson(params[0], R.raw.words, "en");
        writeToBasedata(english, "en-ru");
        JSONArray russian = LoadDatabaseFromJson(params[0], R.raw.words, "ru");
        writeToBasedata(russian, "ru-en");
        wordsDataSource.close();
        return null;
    }

    private void writeToBasedata(JSONArray array, String direction) {
        for (int i = 0; i < array.length(); i++) {
            try {
                if (direction.equals("en-ru")) {
                    String wordEn = array.getString(i);
                    if (wordsDataSource.getWordObjByWord(wordEn, "en").size() == 0) {
                        String wordRu = translateWord(wordEn, direction);
                        wordsDataSource.createWord(0, wordRu, wordEn);
                    }
                }
                else if (direction.equals("ru-en")) {
                    String wordRu = array.getString(i);
                    if (wordsDataSource.getWordObjByWord(wordRu, "ru").size() == 0) {
                        String wordEn = translateWord(wordRu, direction);
                        wordsDataSource.createWord(0, wordRu, wordEn);
                    }
                }
            } catch (JSONException e) {
                // Не бойтесь бросать RuntimeException. Пускай оно лучше упадет, чем случится
                // неясное состояние, которое надо будет обрабатывать.
                e.printStackTrace();
            }
        }
    }


    private String translateWord(String word, String direction) {

        Log.d("POST", "start");

        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20160723T105512Z.707c0ef3894f6961.4d3180562b03bbdd1901a574907a0cd2ac0959a5&text="+word+"&lang="+direction;
        String translatedWord = null;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            Log.d("POST", "prop");

            int responseCode = con.getResponseCode();
            Log.d("POST Response Code :: ", responseCode + "");
            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Log.d("REsult = ", response.toString());

                JSONObject object = new JSONObject(response.toString());
                translatedWord = (String) object.getJSONArray("text").get(0);
                Log.d("translating = ", translatedWord);
            } else {
                System.out.println("POST request not worked");
            }
        } catch(Exception e) {
            // Пропускать исключения вообще запрещено :)
        }
        return translatedWord;
    }

    // Методы следует обзывать в lowerCamelCase
    private JSONArray LoadDatabaseFromJson(Context context, int id, String lang) {
        InputStream inputStream = context.getResources().openRawResource(id);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject object = new JSONObject(stringBuilder.toString());
            if (lang.equals("en")) {
                return object.getJSONArray("en");
            }
            if (lang.equals("ru")) {
                return object.getJSONArray("ru");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}