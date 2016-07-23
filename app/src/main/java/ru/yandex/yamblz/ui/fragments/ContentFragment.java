package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.yandex.yamblz.R;

public class ContentFragment extends BaseFragment {
    @BindView(R.id.btn_card)
    Button buttonCard;
    @BindView(R.id.btn_construct_word)
    Button buttonConstructWord;
    @BindView(R.id.btn_search_pair)
    Button buttonSearchPair;
    @BindView(R.id.btn_select_translate)
    Button buttonSelectTranslate;
    @BindView(R.id.btn_speech_word)
    Button buttonSpeechWord;
    @BindView(R.id.btn_translate_image)
    Button buttonTranslateImage;
    @BindView(R.id.btn_true_false)
    Button buttonTrueFalse;
    @BindView(R.id.btn_word_listing)
    Button buttonWordListing;
    @BindView(R.id.btn_speed_training)
    Button buttonSpeedTraining;

    OnClickListenerButton listenerButton;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_screen, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public interface OnClickListenerButton {
        void onClickButton(int indexButton);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listenerButton = (OnClickListenerButton) getContext();
    }

    @OnClick(R.id.btn_card)
    void onClickCard() {
        listenerButton.onClickButton(R.id.btn_card);
    }
    @OnClick(R.id.btn_construct_word)
    void onClickConstruct() {
        listenerButton.onClickButton(R.id.btn_construct_word);
    }
    @OnClick(R.id.btn_search_pair)
    void onClickSearch() {
        listenerButton.onClickButton(R.id.btn_search_pair);
    }
    @OnClick(R.id.btn_select_translate)
    void onClickSelect() {
        listenerButton.onClickButton(R.id.btn_select_translate);
    }
    @OnClick(R.id.btn_speech_word)
    void onClickSpeech() {
        listenerButton.onClickButton(R.id.btn_speech_word);
    }
    @OnClick(R.id.btn_translate_image)
    void onClickTranslate() {
        listenerButton.onClickButton(R.id.btn_translate_image);
    }
    @OnClick(R.id.btn_true_false)
    void onClickTrue() {
        listenerButton.onClickButton(R.id.btn_true_false);
    }
    @OnClick(R.id.btn_word_listing)
    void onClickWord() {
        listenerButton.onClickButton(R.id.btn_word_listing);
    }
    @OnClick(R.id.btn_speed_training)
    void onClickSpeed() {
        listenerButton.onClickButton(R.id.btn_speed_training);
    }

}
