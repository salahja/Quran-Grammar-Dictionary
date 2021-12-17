package com.example.mushafconsolidated.Activity;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.example.Constant;
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance;
import com.example.mushafconsolidated.Entities.CorpusVerbWbwOccurance;
import com.example.mushafconsolidated.Entities.QuranEntity;
import com.example.mushafconsolidated.fragments.WordMorphologyDetails;
import com.example.mushafconsolidated.model.AyahWord;
import com.example.mushafconsolidated.model.CorpusAyahWord;
import com.example.mushafconsolidated.model.Word;
import com.example.mushafconsolidated.model.WordSpan;
import com.example.utility.CorpusUtilityorig;

import java.util.ArrayList;
import java.util.List;

public class SplitQuranVerses {
    private final Context context;
    private List<CorpusAyahWord> corpusayahWordArrayList;

    public SplitQuranVerses(Context context) {
        this.context=context;
    }

    public   ArrayList<Word> splitSingleVerse(String quraverses) {


        ArrayList<Word> ayahWordArrayList = new ArrayList<>();


        ArrayList<Word> wordArrayList;
        String[] split = quraverses.split("\\s");


            AyahWord ayahWord = new AyahWord();

            String[] s = quraverses.split(" ");

            for (int i = 0; i < s.length; i++) {
                Word word = new Word();
                word.setWordsAr(s[i]);
               // word.setSurahId(vers.getSurah());
              //  word.setVerseId(vers.getAyah());
                word.setWordno(i + 1);
                ayahWordArrayList.add(word);
            }








        return ayahWordArrayList;

        //     return ayahWords;
    }
    public ArrayList<AyahWord> newwordbywordSplitQuran(List<QuranEntity> quraverses) {


        ArrayList<AyahWord> ayahWordArrayList = new ArrayList<AyahWord>();


        ArrayList<Word> wordArrayList;


        // ArrayList<AyahWord> wordbywords = new ArrayList<>();
        for (QuranEntity vers : quraverses) {

            AyahWord ayahWord = new AyahWord();
            wordArrayList = new ArrayList<Word>();
            String[] s = vers.getQurantext().split(" ");

            for (int i = 0; i < s.length; i++) {
                Word word = new Word();
                word.setWordsAr(s[i]);
                word.setSurahId(vers.getSurah());
                word.setVerseId(vers.getAyah());
                word.setWordno(i + 1);
                wordArrayList.add(word);
            }
            ayahWord.setWord(wordArrayList);
            ayahWord.setQuranArabic(vers.getQurantext());


            ayahWordArrayList.add(ayahWord);
        }


        return ayahWordArrayList;

        //     return ayahWords;
    }


    public ArrayList<CorpusVerbWbwOccurance> newwordbywordSplitQuran(ArrayList<CorpusVerbWbwOccurance> verblist) {
        ArrayList<CorpusVerbWbwOccurance> ayahWordArrayList = new ArrayList<CorpusVerbWbwOccurance>();
        ArrayList<Word> wordArrayList;

        for (CorpusVerbWbwOccurance vers : verblist) {
            CorpusVerbWbwOccurance ayahword=new CorpusVerbWbwOccurance();
         //   AyahWord ayahWord = new AyahWord();
            wordArrayList = new ArrayList<Word>();
            String[] s = vers.getQurantext().split(" ");


            for (int i = 0; i < s.length; i++) {
                Word word = new Word();
                word.setWordsAr(s[i]);
                word.setSurahId(vers.getSurah());
                word.setVerseId(vers.getAyah());
                word.setWordno(i + 1);
                wordArrayList.add(word);
            }

            ayahword.setWord(wordArrayList);

            ayahword.setForm(vers.getForm());
            ayahword.setEn(vers.getEn());
            ayahword.setGendernumber(vers.getGendernumber());
            ayahword.setMood_kananumbers(vers.getKana_mood());
            ayahword.setKana_mood(vers.getMood_kananumbers());
            ayahword.setRoot_a(vers.getRoot_a());

            ayahword.setSpannedarabicverb(vers.getSpannedarabicverb());
            ayahword.setSurah( (vers.getSurah()));


            ayahword.setTense(vers.getTense());
            ayahword.setThulathibab(vers.getThulathibab());
            ayahword.setTranslation(vers.getTranslation());
           ayahword.setVerses(vers.getVerses());
           ayahword.setVoice(vers.getVoice());
            ayahword.setWordcount( (vers.getWordcount()));
            ayahword.setWordno((vers.getWordno()));















            ayahWordArrayList.add(ayahword);
        }




        return ayahWordArrayList;
    }

    public ArrayList<CorpusVerbWbwOccurance> spanablelenewwordbywordSplitQuran(ArrayList<CorpusVerbWbwOccurance> verblist) {
        ArrayList<CorpusVerbWbwOccurance> ayahWordArrayList = new ArrayList<CorpusVerbWbwOccurance>();
        ArrayList<WordSpan> wordArrayList;
        CorpusUtilityorig versespannable=new CorpusUtilityorig(context);

        for (CorpusVerbWbwOccurance vers : verblist) {
            CorpusVerbWbwOccurance ayahword=new CorpusVerbWbwOccurance();
            //   AyahWord ayahWord = new AyahWord();
            wordArrayList = new ArrayList<WordSpan>();
            SpannableString spannable = vers.getQuranversesSpannable();
            String[] s = vers.getQurantext().split(" ");


            for (int i = 0; i < s.length; i++) {
                WordSpan word = new WordSpan();
                if(s[i].equals(vers.getArabicword()))  {
                    int firstcolortat= Constant.BYELLOW;
                    SpannableStringBuilder str=new SpannableStringBuilder(vers.getArabicword());

                    str.setSpan(new ForegroundColorSpan(firstcolortat), 0, vers.getArabicword().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                     word.setWordsAr( CorpusUtilityorig.NewSetWordSpan(  vers.getTagone(), vers.getTagtwo(), vers.getTagthree(), vers.getTagfour(), vers.getTagfive(),
                             vers.getAraone(), vers.getAratwo(), vers.getArathree(), vers.getArafour(), vers.getArafive()));

                }else {
                    word.setWordsAr(SpannableString.valueOf(s[i]));
                }
                word.setSurahId(vers.getSurah());
                word.setVerseId(vers.getAyah());
                word.setWordno(i + 1);




                wordArrayList.add(word);
            }

            ayahword.setWordspan(wordArrayList);

            ayahword.setForm(vers.getForm());
            ayahword.setEn(vers.getEn());
            ayahword.setGendernumber(vers.getGendernumber());
            ayahword.setMood_kananumbers(vers.getKana_mood());
            ayahword.setKana_mood(vers.getMood_kananumbers());
            ayahword.setRoot_a(vers.getRoot_a());

            ayahword.setSpannedarabicverb(vers.getSpannedarabicverb());
            ayahword.setSurah( (vers.getSurah()));
            ayahword.setAyah(vers.getAyah());


            ayahword.setTense(vers.getTense());
            ayahword.setThulathibab(vers.getThulathibab());
            ayahword.setTranslation(vers.getTranslation());
            ayahword.setVerses(vers.getVerses());
            ayahword.setVoice(vers.getVoice());
            ayahword.setWordcount( (vers.getWordcount()));
            ayahword.setWordno((vers.getWordno()));
            ayahword.setSpannedverb( CorpusUtilityorig.NewSetWordSpan(  vers.getTagone(), vers.getTagtwo(), vers.getTagthree(), vers.getTagfour(), vers.getTagfive(),
                  vers.getAraone(), vers.getAratwo(), vers.getArathree(), vers.getArafour(), vers.getArafive()));


            ayahWordArrayList.add(ayahword);
        }




        return ayahWordArrayList;
    }
    public ArrayList<CorpusNounWbwOccurance> NounspanablelenewwordbywordSplitQuran(ArrayList<CorpusNounWbwOccurance> verblist) {
        ArrayList<CorpusNounWbwOccurance> ayahWordArrayList = new ArrayList<CorpusNounWbwOccurance>();
        ArrayList<WordSpan> wordArrayList;
     //   CorpusUtility versespannable=new CorpusUtility(context );
        WordMorphologyDetails wm=new WordMorphologyDetails();
        for (CorpusNounWbwOccurance vers : verblist) {
            CorpusNounWbwOccurance ayahword= new CorpusNounWbwOccurance();
            //   AyahWord ayahWord = new AyahWord();
            wordArrayList = new ArrayList<WordSpan>();



            String[] s = vers.getQurantext().split(" ");


            for (int i = 0; i < s.length; i++) {
                WordSpan word = new WordSpan();
                if(s[i].equals(vers.getArabicword()))  {
                    int firstcolortat= Constant.BYELLOW;
                    SpannableStringBuilder str=new SpannableStringBuilder(vers.getArabicword());

                    str.setSpan(new ForegroundColorSpan(firstcolortat), 0, vers.getArabicword().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    word.setWordsAr( CorpusUtilityorig.NewSetWordSpan(  vers.getTagone(), vers.getTagtwo(), vers.getTagthree(), vers.getTagfour(), vers.getTagfive(),
                            vers.getAraone(), vers.getAratwo(), vers.getArathree(), vers.getArafour(), vers.getArafive()));

                }else {
                    word.setWordsAr(SpannableString.valueOf(s[i]));
                }
                word.setSurahId(vers.getSurah());
                word.setVerseId(vers.getAyah());
                word.setWordno(i + 1);




                wordArrayList.add(word);
            }

            ayahword.setWordspan(wordArrayList);
           ayahword.setPropone(vers.getPropone());ayahword.setProptwo(vers.getProptwo());
            ayahword.setForm(vers.getForm());
            ayahword.setCases(vers.getCases());
            ayahword.setEn(vers.getEn());
            StringBuilder genderNumberdetails = wm.getGenderNumberdetails(vers.getGendernumber());
            ayahword.setGendernumber(genderNumberdetails.toString());
                  ayahword.setRoot_a(vers.getRoot_a());
          //  ayahword.setSpannableNoun(vers.getSpannableNoun());
            ayahword.setSurah( (vers.getSurah()));
            ayahword.setAyah(vers.getAyah());


           // ayahword.setTense(vers.getTense());
           // ayahword.setThulathibab(vers.getThulathibab());
            ayahword.setForm(vers.getForm());
            ayahword.setTranslation(vers.getTranslation());
            ayahword.setVerses(SpannableString.valueOf(vers.getQurantext()));
          //  ayahword.setVoice(vers.getVoice());
            ayahword.setWordcount( (vers.getWordcount()));
            ayahword.setWordno((vers.getWordno()));
            ayahword.setSpannableNoun( CorpusUtilityorig.NewSetWordSpan(  vers.getTagone(), vers.getTagtwo(), vers.getTagthree(), vers.getTagfour(), vers.getTagfive(),
                  vers.getAraone(), vers.getAratwo(), vers.getArathree(), vers.getArafour(), vers.getArafive()));


            ayahWordArrayList.add(ayahword);
        }




        return ayahWordArrayList;
    }

}