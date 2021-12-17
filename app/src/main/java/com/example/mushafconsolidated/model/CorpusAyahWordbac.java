package com.example.mushafconsolidated.model;

import android.text.SpannableStringBuilder;

import java.util.ArrayList;


public class CorpusAyahWordbac {
    private ArrayList<CorpusWbwWord> word;

    private SpannableStringBuilder spannableverse;

    public ArrayList<CorpusWbwWord> getWord() {
        return word;
    }

    public void setWord(ArrayList<CorpusWbwWord> word) {
        this.word = word;
    }

    public SpannableStringBuilder getSpannableverse() {
        return spannableverse;
    }

    public void setSpannableverse(SpannableStringBuilder spannableverse) {
        this.spannableverse = spannableverse;
    }

    public CorpusAyahWordbac(ArrayList<CorpusWbwWord> word, SpannableStringBuilder spannableverse) {
        this.word = word;
        this.spannableverse = spannableverse;
    }
}

