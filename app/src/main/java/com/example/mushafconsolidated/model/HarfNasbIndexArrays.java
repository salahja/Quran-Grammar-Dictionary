package com.example.mushafconsolidated.model;

import android.text.SpannableStringBuilder;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

/**
 * Created by Sadmansamee on 7/19/15.
 */
public class HarfNasbIndexArrays {
    SpannableStringBuilder verse;
    int surahid,ayahid;

    String arabicword;

    ArrayList<Integer> indices;
    ArrayList<String> words;
    public HarfNasbIndexArrays(SpannableStringBuilder verse, int surahid, int ayahid, String arabicword, ArrayList<Integer> indices, ArrayList<String> words) {
        this.verse = verse;
        this.surahid = surahid;
        this.ayahid = ayahid;
        this.arabicword = arabicword;
        this.indices = indices;
        this.words = words;
    }

    public HarfNasbIndexArrays() {

    }


    public SpannableStringBuilder getVerse() {
        return verse;
    }

    public void setVerse(SpannableStringBuilder verse) {
        this.verse = verse;
    }

    public int getSurahid() {
        return surahid;
    }

    public void setSurahid(int surahid) {
        this.surahid = surahid;
    }

    public int getAyahid() {
        return ayahid;
    }

    public void setAyahid(int ayahid) {
        this.ayahid = ayahid;
    }

    public String getArabicword() {
        return arabicword;
    }

    public void setArabicword(String arabicword) {
        this.arabicword = arabicword;
    }

    public ArrayList<Integer> getIndices() {
        return indices;
    }

    public void setIndices(ArrayList<Integer> indices) {
        this.indices = indices;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }


    @Override
    public String toString() {
        return "HarfNasbIndexArrays{" +
                "verse=" + verse +
                ", surahid=" + surahid +
                ", ayahid=" + ayahid +
                ", arabicword='" + arabicword + '\'' +
                ", indices=" + indices +
                ", words=" + words +
                '}';
    }
}
