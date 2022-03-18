package com.example.JustJava;


import static android.text.TextUtils.concat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Adapters.FlowAyahWordAdapter;
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity;
import com.example.mushafconsolidated.Entities.CorpusExpandWbwPOJO;
import com.example.mushafconsolidated.Entities.QuranEntity;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.Utils;
import com.example.mushafconsolidated.intrface.OnItemClickListenerOnLong;
import com.example.mushafconsolidated.model.CorpusAyahWord;
import com.example.mushafconsolidated.model.CorpusWbwWord;
import com.example.utility.CorpusUtilityorig;
import com.example.utility.SharedPref;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SurahDisplay  {

    private final Activity context;
    final int surahid;
    private boolean mausoof;
    private boolean mudhaf;
    private boolean harfnasb;
    private ArrayList<CorpusAyahWord> corpusayahWordArrayList;
    private RecyclerView parentRecyclerView;
    private String suraharabicname;
    private int ismakki;
    private boolean shart,kana;
    private AlertDialog dialog;

    public SurahDisplay(Activity context, int surahid) {
        this.context = context;
        this.surahid=surahid;
    }


    public ArrayList<CorpusAyahWord> display() {

        SharedPreferences shared =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);

        mausoof = shared.getBoolean("mausoof", true);
        mudhaf = shared.getBoolean("mudhaf", true);
        harfnasb = shared.getBoolean("harfnasb", true);
        shart = shared.getBoolean("shart", true);
        kana = shared.getBoolean("kana", true);
        SharedPref sharedPref = new SharedPref(context);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        dialog = builder.create();

        Utils utils=new Utils(context);

        ArrayList<ChaptersAnaEntity> singleChapter = utils.getSingleChapter(surahid);
        corpusayahWordArrayList = new ArrayList<>();
        ArrayList<CorpusExpandWbwPOJO> wbw;

        new ArrayList<>();
        int versesnumbers;
        //
        versesnumbers = singleChapter.get(0).getVersescount();
        ismakki = singleChapter.get(0).getIsmakki();
        suraharabicname = singleChapter.get(0).getNamearabic();
        wbw = utils.getCorpusWbwBySurah(surahid);

        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.execute(() -> {
            //do inbackground

            int verseglobal = 0;
            int tempVerseWord;
            int verseexit = wbw.size();

            context. runOnUiThread(() -> dialog.show());



            for (int indexv = 1; indexv <= versesnumbers; indexv++) {
                tempVerseWord = indexv;
             //   currentProgressCount++;
                CorpusAyahWord ayahWord = new CorpusAyahWord();


                ArrayList<CorpusWbwWord> wordArrayList = new ArrayList<>();
                while (tempVerseWord == indexv) {

                    if (verseexit == verseglobal) {
                        break;
                    }

                    for (; verseglobal < wbw.size(); verseglobal++) {
                        CorpusWbwWord word = new CorpusWbwWord();


                        tempVerseWord = wbw.get(verseglobal).getAyah();

                        if (tempVerseWord != indexv) {
                            break;
                        }


                        //    final Object o6 = wbwa.get(verseglobal).get(0);
                        int o1 = wbw.get(verseglobal).getSurah();
                        int o2 = wbw.get(verseglobal).getAyah();
                        Object o3 = wbw.get(verseglobal).getWordno();
                        int wcount = wbw.get(verseglobal).getWordcount();
                        StringBuilder sb=new StringBuilder();
                        sb.append(wbw.get(verseglobal).getAraone()).append(wbw.get(verseglobal).getAratwo());
                        CharSequence sequence = concat(wbw.get(verseglobal).getAraone() + wbw.get(verseglobal).getAratwo() +
                                wbw.get(verseglobal).getArathree() + wbw.get(verseglobal).getArafour());
                        //   Object o4 = wbw.get(verseglobal).getWord();
                        Object en = wbw.get(verseglobal).getEn();
                        Object bn = wbw.get(verseglobal).getBn();
                        Object ind = wbw.get(verseglobal).getIn();
                        word.setSurahId(o1);
                        word.setVerseId(o2);
                        word.setWordno((int) Long.parseLong(o3.toString()));
                        word.setWordcount(wcount);

                        word.setWordsAr(sequence.toString());
                        //  word.setWordindex(getIndex(wbw.get(verseglobal).getQuranverses()));
                        word.setTranslateEn(en.toString());
                        word.setTranslateBn(bn.toString());
                        word.setTranslateIndo(ind.toString());

                        word.setAraone(wbw.get(verseglobal).getAraone());
                        word.setAratwo(wbw.get(verseglobal).getAratwo());
                        word.setArathree(wbw.get(verseglobal).getArathree());
                        word.setArafour(wbw.get(verseglobal).getArafour());
                        word.setArafive(wbw.get(verseglobal).getArafive());

                        word.setTagone(wbw.get(verseglobal).getTagone());
                        word.setTagtwo(wbw.get(verseglobal).getTagtwo());
                        word.setTagthree(wbw.get(verseglobal).getTagthree());
                        word.setTagfour(wbw.get(verseglobal).getTagfour());
                        word.setTagfive(wbw.get(verseglobal).getTagfive());

                        word.setDetailsone(wbw.get(verseglobal).getDetailsone());
                        word.setDetailstwo(wbw.get(verseglobal).getDetailstwo());
                        word.setDetailsthree(wbw.get(verseglobal).getDetailsthree());
                        word.setDetailsfour(wbw.get(verseglobal).getDetailsfour());
                        word.setDetailsfive(wbw.get(verseglobal).getDetailsfive());

                        word.setCorpusSpnnableQuranverse(SpannableStringBuilder.valueOf(wbw.get(verseglobal).getQurantext()));
                        //    word.setQuranversestr(wbw.get(verseglobal).getQuranverses());
                        word.setQuranversestr(wbw.get(verseglobal).getQurantext());
                        word.setTranslations(wbw.get(verseglobal).getTranslation());
                        word.setSurahId((wbw.get(verseglobal).getSurah()));
                        word.setVerseId((wbw.get(verseglobal).getAyah()));
                        word.setWordno(wbw.get(verseglobal).getWordno());
                        word.setWordcount((wbw.get(verseglobal).getWordcount()));

                        //  ayahWord.setSpannableverse(SpannableStringBuilder.valueOf(wbw.get(verseglobal).getQuranverses()));
                        ayahWord.setSpannableverse(SpannableString.valueOf(wbw.get(verseglobal).getQurantext()));
                        wordArrayList.add(word);

                    }


                }

                ayahWord.setWord(wordArrayList);






                corpusayahWordArrayList.add(ayahWord);

            }
            CorpusUtilityorig corpus=new CorpusUtilityorig(context);
            //      corpus.highLightVerbs(corpusayahWordArrayList,surah_id);
            if (kana) {
                corpus.setKana(corpusayahWordArrayList, surahid);

            }
            if (shart) {
                corpus.setShart(corpusayahWordArrayList, surahid);
            }

            if (mausoof) {


                corpus.SetMousufSifaDB(corpusayahWordArrayList, surahid);
            }
            if (mudhaf) {

                corpus.setMudhafFromDB(corpusayahWordArrayList, surahid);

            }
            if (harfnasb) {

                corpus.newnewHarfNasbDb(corpusayahWordArrayList, surahid);
            }
            //     corpus.highLightVerbs(corpusayahWordArrayList,surah_id);








            //post


            context.runOnUiThread(() -> {
                ex.shutdown();
                dialog.dismiss();


                List<QuranEntity> allofQuran= Utils.getQuranbySurah(surahid);



                OnItemClickListenerOnLong listener = null;

                ArrayList<String> header = new ArrayList<>();
                ArrayList<QuranEntity> colorerab = new ArrayList<>();
                FlowAyahWordAdapter flowAyahWordAdapter = new FlowAyahWordAdapter(header, allofQuran, corpusayahWordArrayList, context, surahid, suraharabicname, ismakki, listener);
                flowAyahWordAdapter.addContext(context);
                parentRecyclerView =context.findViewById(R.id.overlayViewRecyclerView);
                parentRecyclerView.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                parentRecyclerView.setLayoutManager(linearLayoutManager);
                parentRecyclerView.setAdapter(flowAyahWordAdapter);
                flowAyahWordAdapter.notifyDataSetChanged();
                //      parentRecyclerView.post(() -> parentRecyclerView.scrollToPosition(verse_no));




            });



        });
        ex.shutdown();


        return corpusayahWordArrayList;
    }


}