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
// Вам же дали BaseFragment, обратите внимание, что там есть как bind, так и unbind. Вспомните лекцию по фрагментам, зачем
// это нужно (спойлер: чтобы не утекали вьюхи, когда фрагмент лежит во FragmentManager после того, как у него вызвался
// onDestroyView)
public class CardFragment extends Fragment {

    private WordsDataSource wordsDataSource;

    public static CardFragment newInstance() {

        Bundle args = new Bundle();

        CardFragment fragment = new CardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        wordsDataSource = new WordsDataSource(getContext());
        wordsDataSource.open();
        Log.d("onViewCreated: ", "zxc");
        for (Word word:wordsDataSource.getWordObjByWord("start", "en")){
            Log.d("onViewCreated: ", word.getWord_en() + " " + word.getWord_ru());
        }
        wordsDataSource.close();
    }
}
