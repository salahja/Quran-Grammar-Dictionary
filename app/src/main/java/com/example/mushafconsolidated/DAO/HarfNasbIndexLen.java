package com.example.mushafconsolidated.DAO;

public class HarfNasbIndexLen {

    private String verse;
    private int surah;
    private int ayah;
    private String versedetails;


    public HarfNasbIndexLen(String verse, int surah, int ayah, String versedetails) {
        this.verse = verse;
        this.surah = surah;
        this.ayah = ayah;
        this.versedetails = versedetails;
    }

    public HarfNasbIndexLen() {
    }

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public int getSurah() {
        return surah;
    }

    public void setSurah(int surah) {
        this.surah = surah;
    }

    public int getAyah() {
        return ayah;
    }

    public void setAyah(int ayah) {
        this.ayah = ayah;
    }

    public String getVersedetails() {
        return versedetails;
    }

    public void setVersedetails(String versedetails) {
        this.versedetails = versedetails;
    }
}
