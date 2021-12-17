package com.example.mushafconsolidated.Adapters;


public class SurahPartCombinedArray {



    private int chapter_no;

    private String name;
    private int type;
    private int part_no;
    private int has_bismillah;

    private String alpha_arabic_name;
    private String english_name;

    private int revelation_order;
    private int total_passages;
    private int total_verses;
    private int ppartid;
    private String pname;
    int display_part;



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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPart_no() {
        return part_no;
    }

    public void setPart_no(int part_no) {
        this.part_no = part_no;
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

    public int getPpartid() {
        return ppartid;
    }

    public void setPpartid(int ppartid) {
        this.ppartid = ppartid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getDisplay_part() {
        return display_part;
    }

    public void setDisplay_part(int display_part) {
        this.display_part = display_part;
    }

    public SurahPartCombinedArray(int chapter_no, String name, int type, int part_no, String alpha_arabic_name, String english_name, int revelation_order, int total_passages, int total_verses, int ppartid, String pname, int display_part) {
        this.chapter_no = chapter_no;
        this.name = name;
        this.type = type;
        this.part_no = part_no;

        this.alpha_arabic_name = alpha_arabic_name;
        this.english_name = english_name;
        this.revelation_order = revelation_order;
        this.total_passages = total_passages;
        this.total_verses = total_verses;
        this.ppartid = ppartid;
        this.pname = pname;
        this.display_part = display_part;
    }
}