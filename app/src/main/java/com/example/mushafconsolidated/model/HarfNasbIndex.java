package com.example.mushafconsolidated.model;

import android.text.SpannableStringBuilder;

/**
 * Created by Sadmansamee on 7/19/15.
 */
public class HarfNasbIndex {
    SpannableStringBuilder verse;
    int surahayahretrevalindex;
    int surahid,ayahid,wordid;
    int harfwordno, ismstartword, khabarstartword,ismendword,khabarendword;
    String arabicword;

    public int getHarfwordno() {
        return harfwordno;
    }

    public int getIsmendword() {
        return ismendword;
    }

    public void setIsmendword(int ismendword) {
        this.ismendword = ismendword;
    }

    public int getKhabarendword() {
        return khabarendword;
    }

    public void setKhabarendword(int khabarendword) {
        this.khabarendword = khabarendword;
    }

    public void setHarfwordno(int harfwordno) {
        this.harfwordno = harfwordno;
    }

    public int getIsmstartword() {
        return ismstartword;
    }

    public void setIsmstartword(int ismstartword) {
        this.ismstartword = ismstartword;
    }

    public int getKhabarstartword() {
        return khabarstartword;
    }

    public void setKhabarstartword(int khabarstartword) {
        this.khabarstartword = khabarstartword;
    }

    public int getSurahayahretrevalindex() {
        return surahayahretrevalindex;
    }

    public void setSurahayahretrevalindex(int surahayahretrevalindex) {
        this.surahayahretrevalindex = surahayahretrevalindex;
    }

    int firstinna,firstinnalen;

    public int getWordid() {
        return wordid;
    }

    public void setWordid(int wordid) {
        this.wordid = wordid;
    }

    int firstism,firstimslen;
    int firstkhabar,firstkhabarlen;


    int secondinna,secondinnalen;
    int secondism,secondismlen;
    int secondkhabar,secondkhabarlen;

    int thirdinna,thirdinnalen;
    int thirdism,thirdismlen;




    public HarfNasbIndex(String arabicword, int firstinna, int firstinnalen) {
        this.arabicword = arabicword;
        this.firstinna = firstinna;
        this.firstinnalen = firstinnalen;
    }

    public String getArabicword() {
        return arabicword;
    }

    public void setArabicword(String arabicword) {
        this.arabicword = arabicword;
    }

    int thirdhabar,thirdkhabarlen;

    public HarfNasbIndex() {

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

    public int getFirstinna() {
        return firstinna;
    }

    public void setFirstinna(int firstinna) {
        this.firstinna = firstinna;
    }

    public int getFirstinnalen() {
        return firstinnalen;
    }

    public void setFirstinnalen(int firstinnalen) {
        this.firstinnalen = firstinnalen;
    }

    public int getFirstism() {
        return firstism;
    }

    public void setFirstism(int firstism) {
        this.firstism = firstism;
    }

    public int getFirstimslen() {
        return firstimslen;
    }

    public void setFirstimslen(int firstimslen) {
        this.firstimslen = firstimslen;
    }

    public int getFirstkhabar() {
        return firstkhabar;
    }

    public void setFirstkhabar(int firstkhabar) {
        this.firstkhabar = firstkhabar;
    }

    public int getFirstkhabarlen() {
        return firstkhabarlen;
    }

    public void setFirstkhabarlen(int firstkhabarlen) {
        this.firstkhabarlen = firstkhabarlen;
    }

    public int getSecondinna() {
        return secondinna;
    }

    public void setSecondinna(int secondinna) {
        this.secondinna = secondinna;
    }

    public int getSecondinnalen() {
        return secondinnalen;
    }

    public void setSecondinnalen(int secondinnalen) {
        this.secondinnalen = secondinnalen;
    }

    public int getSecondism() {
        return secondism;
    }

    public void setSecondism(int secondism) {
        this.secondism = secondism;
    }

    public int getSecondismlen() {
        return secondismlen;
    }

    public void setSecondismlen(int secondismlen) {
        this.secondismlen = secondismlen;
    }

    public int getSecondkhabar() {
        return secondkhabar;
    }

    public void setSecondkhabar(int secondkhabar) {
        this.secondkhabar = secondkhabar;
    }

    public int getSecondkhabarlen() {
        return secondkhabarlen;
    }

    public void setSecondkhabarlen(int secondkhabarlen) {
        this.secondkhabarlen = secondkhabarlen;
    }

    public int getThirdinna() {
        return thirdinna;
    }

    public void setThirdinna(int thirdinna) {
        this.thirdinna = thirdinna;
    }

    public int getThirdinnalen() {
        return thirdinnalen;
    }

    public void setThirdinnalen(int thirdinnalen) {
        this.thirdinnalen = thirdinnalen;
    }

    public int getThirdism() {
        return thirdism;
    }

    public void setThirdism(int thirdism) {
        this.thirdism = thirdism;
    }

    public int getThirdismlen() {
        return thirdismlen;
    }

    public void setThirdismlen(int thirdismlen) {
        this.thirdismlen = thirdismlen;
    }

    public int getThirdhabar() {
        return thirdhabar;
    }

    public void setThirdhabar(int thirdhabar) {
        this.thirdhabar = thirdhabar;
    }

    public int getThirdkhabarlen() {
        return thirdkhabarlen;
    }

    public void setThirdkhabarlen(int thirdkhabarlen) {
        this.thirdkhabarlen = thirdkhabarlen;
    }

    public HarfNasbIndex(SpannableStringBuilder verse, int surahid, int ayahid, String arabicword, int firstinna, int firstinnalen, int firstism, int firstimslen, int firstkhabar, int firstkhabarlen, int secondinna, int secondinnalen, int secondism, int secondismlen, int secondkhabar, int secondkhabarlen, int thirdinna, int thirdinnalen, int thirdism, int thirdismlen, int thirdhabar, int thirdkhabarlen) {
        this.verse = verse;
        this.surahid = surahid;
        this.ayahid = ayahid;
        this.arabicword = arabicword;
        this.firstinna = firstinna;
        this.firstinnalen = firstinnalen;
        this.firstism = firstism;
        this.firstimslen = firstimslen;
        this.firstkhabar = firstkhabar;
        this.firstkhabarlen = firstkhabarlen;
        this.secondinna = secondinna;
        this.secondinnalen = secondinnalen;
        this.secondism = secondism;
        this.secondismlen = secondismlen;
        this.secondkhabar = secondkhabar;
        this.secondkhabarlen = secondkhabarlen;
        this.thirdinna = thirdinna;
        this.thirdinnalen = thirdinnalen;
        this.thirdism = thirdism;
        this.thirdismlen = thirdismlen;
        this.thirdhabar = thirdhabar;
        this.thirdkhabarlen = thirdkhabarlen;
    }

    @Override
    public String toString() {
        return "HarfNasbIndex{" +
                "verse=" + verse +
                ", surahid=" + surahid +
                ", ayahid=" + ayahid +
                ", arabicword='" + arabicword + '\'' +
                ", firstinna=" + firstinna +
                ", firstinnalen=" + firstinnalen +
                ", firstism=" + firstism +
                ", firstimslen=" + firstimslen +
                ", firstkhabar=" + firstkhabar +
                ", firstkhabarlen=" + firstkhabarlen +
                ", secondinna=" + secondinna +
                ", secondinnalen=" + secondinnalen +
                ", secondism=" + secondism +
                ", secondismlen=" + secondismlen +
                ", secondkhabar=" + secondkhabar +
                ", secondkhabarlen=" + secondkhabarlen +
                ", thirdinna=" + thirdinna +
                ", thirdinnalen=" + thirdinnalen +
                ", thirdism=" + thirdism +
                ", thirdismlen=" + thirdismlen +
                ", thirdhabar=" + thirdhabar +
                ", thirdkhabarlen=" + thirdkhabarlen +
                '}';
    }
}
