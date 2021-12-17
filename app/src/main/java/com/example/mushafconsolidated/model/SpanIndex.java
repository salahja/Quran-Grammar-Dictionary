package com.example.mushafconsolidated.model;

import java.util.ArrayList;


public class SpanIndex {
    private ArrayList<IdhafaIntegarsPairs> word;
    private ArrayList<HarfNasbIndex> HarfNasbIndex;
    private String idhafatype;


    public SpanIndex(ArrayList<IdhafaIntegarsPairs> word, String idhafatype) {
        this.word = word;
        this.idhafatype = idhafatype;
    }

    public SpanIndex() {

    }

    public SpanIndex(ArrayList<HarfNasbIndex> HarfNasbIndex) {
        this.HarfNasbIndex = HarfNasbIndex;
    }

    public ArrayList<HarfNasbIndex> getHarfNasbIndex() {
        return HarfNasbIndex;
    }

    public void setHarfNasbIndex(ArrayList<HarfNasbIndex> harfNasbIndex) {
        this.HarfNasbIndex = harfNasbIndex;
    }

    public ArrayList<IdhafaIntegarsPairs> getWord() {
        return word;
    }

    public void setWord(ArrayList<IdhafaIntegarsPairs> word) {
        this.word = word;
    }

    public String getIdhafatype() {
        return idhafatype;
    }

    public void setIdhafatype(String idhafatype) {
        this.idhafatype = idhafatype;
    }


    @Override
    public String toString() {
        return "SpanIndex{" +
                "word=" + word +
                ", idhafatype='" + idhafatype + '\'' +
                '}';
    }
}


