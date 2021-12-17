package com.example.mushafconsolidated.fragments;





public class Surah {


    private int chapterID;
    private int chapter_no;
    private String name;
    private String type;
    private int has_bismillah;
    private String alpha_arabic_name;
    private String english_name;
    private int revelation_order;
    private int total_passages;
    private int total_verses;
    private int part_no;

    public Surah(int chapterID, int chapter_no, String name, String type, int has_bismillah, String alpha_arabic_name, String english_name, int revelation_order, int total_passages, int total_verses, int part_no) {
        this.chapterID = chapterID;
        this.chapter_no = chapter_no;
        this.name = name;
        this.type = type;
        this.has_bismillah = has_bismillah;
        this.alpha_arabic_name = alpha_arabic_name;
        this.english_name = english_name;
        this.revelation_order = revelation_order;
        this.total_passages = total_passages;
        this.total_verses = total_verses;
        this.part_no = part_no;
    }

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }

    public int getChapter_no() {
        return chapter_no;
    }

    public void setChapter_no(int chapter_no) {
        this.chapter_no = chapter_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHas_bismillah() {
        return has_bismillah;
    }

    public void setHas_bismillah(int has_bismillah) {
        this.has_bismillah = has_bismillah;
    }

    public String getAlpha_arabic_name() {
        return alpha_arabic_name;
    }

    public void setAlpha_arabic_name(String alpha_arabic_name) {
        this.alpha_arabic_name = alpha_arabic_name;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public int getRevelation_order() {
        return revelation_order;
    }

    public void setRevelation_order(int revelation_order) {
        this.revelation_order = revelation_order;
    }

    public int getTotal_passages() {
        return total_passages;
    }

    public void setTotal_passages(int total_passages) {
        this.total_passages = total_passages;
    }

    public int getTotal_verses() {
        return total_verses;
    }

    public void setTotal_verses(int total_verses) {
        this.total_verses = total_verses;
    }

    public int getPart_no() {
        return part_no;
    }

    public void setPart_no(int part_no) {
        this.part_no = part_no;
    }
}


