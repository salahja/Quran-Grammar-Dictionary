package com.example.mushafconsolidated.model;

import android.text.SpannableString;

import java.util.ArrayList;


public class CorpusAyahWord {
    private ArrayList<CorpusWbwWord> word;

    private boolean hasProstration;
    private String quranArabic;
    private String quranTranslate;

    private SpannableString spannableverse;

    public CorpusAyahWord() {

    }

    public ArrayList<CorpusWbwWord> getWord() {
        return word;
    }

    public void setWord(ArrayList<CorpusWbwWord> word) {
        this.word = word;
    }

    public boolean isHasProstration() {
        return hasProstration;
    }

    public void setHasProstration(boolean hasProstration) {
        this.hasProstration = hasProstration;
    }

    public String getQuranArabic() {
        return quranArabic;
    }

    public void setQuranArabic(String quranArabic) {
        this.quranArabic = quranArabic;
    }

    public String getQuranTranslate() {
        return quranTranslate;
    }

    public void setQuranTranslate(String quranTranslate) {
        this.quranTranslate = quranTranslate;
    }

    public SpannableString getSpannableverse() {
        return spannableverse;
    }

    public void setSpannableverse(SpannableString spannableverse) {
        this.spannableverse = spannableverse;
    }

    public CorpusAyahWord(ArrayList<CorpusWbwWord> word, boolean hasProstration, String quranArabic, String quranTranslate, SpannableString spannableverse) {
        this.word = word;
        this.hasProstration = hasProstration;
        this.quranArabic = quranArabic;
        this.quranTranslate = quranTranslate;
        this.spannableverse = spannableverse;
    }
}
