package ru.yandex.yamblz.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Named;

import ru.yandex.yamblz.App;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.database.LoadData;
import ru.yandex.yamblz.developer_settings.DeveloperSettingsModule;
import ru.yandex.yamblz.ui.fragments.CardFragment;
import ru.yandex.yamblz.ui.fragments.ConstructWordFragment;
import ru.yandex.yamblz.ui.fragments.ContentFragment;
import ru.yandex.yamblz.ui.other.ViewModifier;

public class MainActivity extends BaseActivity implements ContentFragment.OnClickListenerButton{

    @Inject @Named(DeveloperSettingsModule.MAIN_ACTIVITY_VIEW_MODIFIER)
    ViewModifier viewModifier;

    @SuppressLint("InflateParams") // It's okay in our case.
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(this).applicationComponent().inject(this);

        setContentView(viewModifier.modify(getLayoutInflater().inflate(R.layout.activity_main, null)));

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame_layout, new ContentFragment())
                    .commit();
        }

        LoadData loadData = new LoadData();
        loadData.execute(getApplicationContext());
        try {
            loadData.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("asd", "asd");
        }
    }


    @Override
    public void onClickButton(int indexButton) {
        switch (indexButton) {
            case R.id.btn_speed_training:
                break;
            case R.id.btn_card:
                CardFragment cardFragment = CardFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame_layout, cardFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_construct_word:
                ConstructWordFragment constructWordFragment = ConstructWordFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame_layout, constructWordFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_search_pair:
                break;
            case R.id.btn_select_translate:
                break;
            case R.id.btn_speech_word:
                break;
            case R.id.btn_translate_image:
                break;
            case R.id.btn_true_false:
                break;
            case R.id.btn_word_listing:
                break;
        }
    }
}
