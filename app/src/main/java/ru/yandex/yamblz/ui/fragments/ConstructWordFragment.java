package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.database.WordsDataSource;
import ru.yandex.yamblz.model.Word;

/**
 * Created by user on 23.07.16.
 */

public class ConstructWordFragment extends Fragment {

    public static ConstructWordFragment newInstance() {

        Bundle args = new Bundle();

        ConstructWordFragment fragment = new ConstructWordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.construct_word_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
