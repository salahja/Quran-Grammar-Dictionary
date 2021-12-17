package com.example.mushafconsolidated.model;

import android.text.SpannableStringBuilder;

/**
 * Created by Sadmansamee on 7/19/15.
 */
public class IdhafaIntegarsPairs {
    SpannableStringBuilder verse;
    int surahid,ayahid,wordid,fromwordno,towordno;
    int firstinnastart;
    int firstInnaIsmEnd;
    int firstInnaKhabarStart;
    int firstInnaKhabarEND;

    public int getFromwordno() {
        return fromwordno;
    }

    public void setFromwordno(int fromwordno) {
        this.fromwordno = fromwordno;
    }

    public int getTowordno() {
        return towordno;
    }

    public void setTowordno(int towordno) {
        this.towordno = towordno;
    }

    int secondInnaStart;
    int secondInnaIsmEnd;
    int secondInnaKhabarStart;
    int secondInnaKhabarEND;

    public int getWordid() {
        return wordid;
    }

    public void setWordid(int wordid) {
        this.wordid = wordid;
    }

    String idhaf;

    public int getFirstInnaKhabarStart() {
        return firstInnaKhabarStart;
    }

    public void setFirstInnaKhabarStart(int firstInnaKhabarStart) {
        this.firstInnaKhabarStart = firstInnaKhabarStart;
    }

    public int getFirstInnaKhabarEND() {
        return firstInnaKhabarEND;
    }

    public void setFirstInnaKhabarEND(int firstInnaKhabarEND) {
        this.firstInnaKhabarEND = firstInnaKhabarEND;
    }

    public int getSecondInnaKhabarStart() {
        return secondInnaKhabarStart;
    }

    public void setSecondInnaKhabarStart(int secondInnaKhabarStart) {
        this.secondInnaKhabarStart = secondInnaKhabarStart;
    }

    public int getSecondInnaKhabarEND() {
        return secondInnaKhabarEND;
    }

    public void setSecondInnaKhabarEND(int secondInnaKhabarEND) {
        this.secondInnaKhabarEND = secondInnaKhabarEND;
    }

    public int getSecondInnaStart() {
        return secondInnaStart;
    }

    public void setSecondInnaStart(int secondInnaStart) {
        this.secondInnaStart = secondInnaStart;
    }

    public int getSecondInnaIsmEnd() {
        return secondInnaIsmEnd;
    }

    public void setSecondInnaIsmEnd(int secondInnaIsmEnd) {
        this.secondInnaIsmEnd = secondInnaIsmEnd;
    }



    public IdhafaIntegarsPairs(SpannableStringBuilder verse, int surahid, int ayahid, int firstinnastart, int firstInnaIsmEnd, String idhaf) {
        this.verse = verse;
        this.surahid = surahid;
        this.ayahid = ayahid;
        this.firstinnastart = firstinnastart;
        this.firstInnaIsmEnd = firstInnaIsmEnd;
        this.idhaf = idhaf;
    }

    public IdhafaIntegarsPairs() {

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

    public int getFirstinnastart() {
        return firstinnastart;
    }

    public void setFirstinnastart(int firstinnastart) {
        this.firstinnastart = firstinnastart;
    }

    public int getFirstInnaIsmEnd() {
        return firstInnaIsmEnd;
    }

    public void setFirstInnaIsmEnd(int firstInnaIsmEnd) {
        this.firstInnaIsmEnd = firstInnaIsmEnd;
    }

    public String getIdhaf() {
        return idhaf;
    }

    public void setIdhaf(String idhaf) {
        this.idhaf = idhaf;
    }

    @Override
    public String toString() {
        return "IdhafaIntegarsPairs{" +
                "verse='" + verse + '\'' +
                ", surahid=" + surahid +
                ", ayahid=" + ayahid +
                ", startindex=" + firstinnastart +
                ", endindex=" + firstInnaIsmEnd +
                ", idhaf='" + idhaf + '\'' +
                '}';
    }
}
