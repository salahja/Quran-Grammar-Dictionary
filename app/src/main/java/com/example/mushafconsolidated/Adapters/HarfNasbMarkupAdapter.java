package com.example.mushafconsolidated.Adapters;


import static com.example.Constant.AYAHNUMBER;
import static com.example.Constant.SURAH_ARABIC_NAME;
import static com.example.Constant.SURAH_ID;
import static com.example.Constant.WORDNUMBER;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Config;
import com.example.mushafconsolidated.Entities.QuranEntity;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.mushafconsolidated.model.AyahWord;
import com.example.mushafconsolidated.model.CorpusAyahWord;
import com.example.mushafconsolidated.model.CorpusWbwWord;
import com.example.mushafconsolidated.model.HarfNasbIndex;
import com.example.mushafconsolidated.model.HarfNasbIndexArrays;
import com.example.utility.AnimationUtility;
import com.example.utility.DarkThemeApplication;
import com.example.utility.FlowLayout;
import com.example.utility.PreferenceUtil;
import com.example.utility.SharedPref;
import com.google.android.material.chip.Chip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

;
//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;

public class HarfNasbMarkupAdapter extends RecyclerView.Adapter<HarfNasbMarkupAdapter.AyahViewHolder> implements OnItemClickListener {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private final List<QuranEntity> allofquran;
    ArrayList<Integer> thisindices;
    ArrayList<Integer> phraseindeces;
    static boolean showTranslation;
    static boolean wordByWord;
    boolean done = false;
    int surahid, ayahid;
    ArrayList<Integer> windex = new ArrayList<Integer>();
    private SpannableString quranverses;

    public int getInnawithism() {
        return innawithism;
    }

    public void setInnawithism(int innawithism) {
        this.innawithism = innawithism;
    }

    public int getInnawithoutism() {
        return innawithoutism;
    }

    public void setInnawithoutism(int innawithoutism) {
        this.innawithoutism = innawithoutism;
    }

    public int getIsminna() {
        return isminna;
    }

    public void setIsminna(int isminna) {
        this.isminna = isminna;
    }

    public int getKhabar() {
        return khabar;
    }

    public void setKhabar(int khabar) {
        this.khabar = khabar;
    }

    int innawithism, innawithoutism, isminna, khabar;

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

    @Override
    public void onViewAttachedToWindow(@NonNull AyahViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.quran_textView.setEnabled(false);
        holder.quran_textView.setEnabled(true);


    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public ArrayList<Integer> getPhraseindeces() {
        return phraseindeces;
    }

    public void setPhraseindeces(ArrayList<Integer> phraseindeces) {
        this.phraseindeces = phraseindeces;
    }

    public ArrayList<Integer> getThisindices() {
        return thisindices;
    }

    public void setThisindices(ArrayList<Integer> thisindices) {
        this.thisindices = thisindices;
    }

    //   private final List<VerseDataTranslationPojo> translations;
    //  privviewate final List<VerseDataTranslationPojo> erab;
    private View.OnClickListener onItemClickListener;

    OnItemClickListener mItemClickListener;
    int phraselenone;
    int phraselentwo;
    static int fontSizeArabic;
    static int fontSizeTranslation;

    public int getPhraselenone() {
        return phraselenone;
    }

    public int getPhraselentwo() {
        return phraselentwo;
    }

    public void setPhraselentwo(int phraselentwo) {
        this.phraselentwo = phraselentwo;
    }

    public void setPhraselenone(int phraselenone) {
        this.phraselenone = phraselenone;
    }

    private HarfNasbIndexArrays harfNasbIndex;
    private HarfNasbIndex harfNasbIndexorig;
    private CorpusAyahWord ayahWord;

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    int clicks = 0;
    static Typeface corpusTypeface;

    public Context context;
    final long surah_id;
    private ArrayList<CorpusAyahWord> ayahWordArrayList;

    public int adapterposition;
    ArrayList<HarfNasbIndexArrays> harfNasbIndexArrayList;
    ArrayList<HarfNasbIndex> harfNasbIndexArrayListorig;

    public int getAdapterposition() {
        return adapterposition;
    }

    public void setAdapterposition(int adapterposition) {
        this.adapterposition = adapterposition;
    }

    PreferenceUtil pref;
    private String SurahName;
    private int isMakkiMadani;
    public Chip wordindex, wordlength;

    private Chip arabic;


    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    public HarfNasbMarkupAdapter(List<QuranEntity>  allofquran ,
                                 ArrayList<CorpusAyahWord> ayahWordArrayList, Context context, long surah_id, String surahName, int ismakki, OnItemClickListener listener) {
        this.allofquran = allofquran;

        this.ayahWordArrayList = ayahWordArrayList;
        this.context = context;
        this.surah_id = surah_id;
        this.SurahName = surahName;
        this.isMakkiMadani = ismakki;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        //   showTranslation =
        //        sharedPreferences.getBoolean(Config.SHOW_TRANSLATION, Config.defaultShowTranslation);
        sharedPreferences.getBoolean(Config.SHOW_Erab, Config.defaultShowErab);
        //   wordByWord = sharedPreferences.getBoolean(Config.WORD_BY_WORD, Config.defaultWordByWord);
        // fontSizeArabic =
        //   Integer.parseInt(
        //      sharedPreferences.getString(Config.FONT_SIZE_ARABIC, Config.defaultFontSizeArabic));
        //  fontSizeTranslation =
        //    Integer.parseInt(
        //  sharedPreferences.getString(
        //     Config.FONT_SIZE_TRANSLATION, Config.defaultFontSizeTranslation));
        //  fontSizeErab = Integer.parseInt(
        //     sharedPreferences.getString(
        //   Config.FONT_SIZE_ERAB, Config.defaultFontSizeErab));


        //   corpusTypeface = Typeface.createFromAsset(context.getResources().getAssets(), "amiri.ttf");
        corpusTypeface = Typeface.createFromAsset(DarkThemeApplication.getInstance().getResources().getAssets(), "amiri.ttf");
        //   prefs = new SharedPref(context);

        SharedPreferences sharedPref =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        //     pref = new PreferenceUtil(sharedPref);
        mItemClickListener = listener;

    }


    public void addContext(Context context) {
        this.context = context;
        harfNasbIndexArrayList = new ArrayList<>();
        harfNasbIndexArrayListorig = new ArrayList<>();
    }


    public void setItAll(ArrayList<CorpusAyahWord> ayahWordArrayList, String suraharabicname, int isMakkiMadani, Context context) {
        this.ayahWordArrayList = ayahWordArrayList;
        this.context = context;
        SharedPreferences sharedPreferences =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        pref = new PreferenceUtil(sharedPreferences);
        //  PrefUtils(ayahadapterContext);
        SharedPref prefs = new SharedPref(context);
        this.SurahName = suraharabicname;
        this.isMakkiMadani = isMakkiMadani;
    }

    public void setItAll(ArrayList<CorpusAyahWord> ayahWordArrayList, String suraharabicname, int isMakkiMadani, Context context, OnItemClickListener listener) {
        this.ayahWordArrayList = ayahWordArrayList;
        this.context = context;
        SharedPreferences sharedPreferences =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        pref = new PreferenceUtil(sharedPreferences);
        //  PrefUtils(ayahadapterContext);
        SharedPref prefs = new SharedPref(context);
        this.SurahName = suraharabicname;
        this.isMakkiMadani = isMakkiMadani;
        OnItemClickListener amItemClickListener = listener;
    }


    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        return ayahWordArrayList.size();
        //     return  quran.size();
    }

    @Override
    public long getItemId(int position) {

        CorpusAyahWord ayahWord = ayahWordArrayList.get(position);
        long itemId = 1;

        for (CorpusWbwWord word : ayahWord.getWord()) {
            itemId = word.getVerseId();
        }
        return itemId;
    }

    @Override
    public HarfNasbMarkupAdapter.AyahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shart_flow_row_ayah_word, parent, false);
        //     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.back_row_ayah_word, parent, false);
        HarfNasbMarkupAdapter.AyahViewHolder viewHolder = new HarfNasbMarkupAdapter.AyahViewHolder(view);

        return viewHolder;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(HarfNasbMarkupAdapter.AyahViewHolder holder, int position) {
        //    ArrayList<Integer> windex = new ArrayList<Integer>();
        harfNasbIndex = new HarfNasbIndexArrays();
        harfNasbIndexorig = new HarfNasbIndex();

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),
                SharedPref.arabicFontSelection());
        boolean showErab = SharedPref.showErab();
        boolean showTransliteration = SharedPref.showTransliteration();
        boolean showJalalayn = SharedPref.showJalalayn();
        boolean showTranslation = SharedPref.showTranslation();
        boolean showWordByword = SharedPref.showWordByword();
        holder.quran_textView.setEnabled(false);
        holder.quran_textView.setEnabled(true);
        QuranEntity entity = allofquran.get(position);
        ayahWord = ayahWordArrayList.get(position);

        //   String quranverses = String.valueOf(ayahWordArrayList.get(position).getSpannableverse());
        quranverses = ayahWordArrayList.get(position).getSpannableverse();
        holder.quran_textView.setText(quranverses);
        //   holder.translate_textView.setText(ayahWord.getQuranTranslate());

        holder.quran_textView.setTextSize(SharedPref.SeekarabicFontsize());
        holder.quran_textView.setTypeface(custom_font);


        setChapterInfo(holder, ayahWord);

        setAdapterposition(position);
        //  setStartText(holder,ayahWord);


        if (showWordByword) {
            final LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder.flow_word_by_word.removeAllViews();
            for (final CorpusWbwWord word : ayahWord.getWord()) {
                final View view = inflater.inflate(R.layout.flow_word_by_word, null);
                //   final TextView arabic = view.findViewById(R.id.word_arabic_textView);
                arabic = view.findViewById(R.id.word_arabic_textView);

                final TextView translation = view.findViewById(R.id.word_trans_textView);
                wordlength = view.findViewById(R.id.wordlen);
                wordindex = view.findViewById(R.id.wordindex);

                //  arabic.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeArabic);
                //  translation.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeTranslation);
                //  final String arabicFontSelection = arabicFontSelection();
                arabic.setText(fixArabic(word.getWordsAr()));
                //        arabic.setChecked(true);
                arabic.setTextSize(SharedPref.SeekarabicFontsize());
                arabic.setTypeface(custom_font);

                //      translation.setText(word.getWordsId());
                //   translation.setTextSize(SharedPref.SeekarabicFontsize());
                final int length = word.getWordsAr().length();
                final int wordindex1 = word.getWordindex();

                StringBuilder sbs = new StringBuilder();
                sbs.append(length);
                wordlength.setText(sbs.toString());
                wordlength.setTextSize(SharedPref.SeekarabicFontsize());

                wordindex.setText(String.valueOf(word.getWordindex()));
                wordindex.setTextSize(SharedPref.SeekarabicFontsize());
                holder.flow_word_by_word.addView(view);

                if (SharedPref.themePreferences().equals("dark")) {
                    arabic.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.white));
                    translation.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.white));

                } else {
                    arabic.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.burntamber));
                    translation.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.burntamber));
                }


                clicks = 0;
                arabic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SetHarfNasbIntegerArrays();


                    }

                    private void SetHarfNasbIntegerArrays() {
                        final int wordindex2 = word.getWordindex();
                        final int length1 = word.getWordsAr().length();
                        StringBuilder ss = new StringBuilder();
                        ss.append(wordindex2).append(",").append(length1);

                        final Integer integer = Integer.valueOf(word.getWordindex());
                        final Integer integerlen = Integer.valueOf(word.getWordsAr().length());


                        windex.add(integer);
                        windex.add(integerlen);

                        setThisindices(windex);
                        setSurahid(word.getSurahId());
                        setAyahid(word.getVerseId());

                        System.out.println(harfNasbIndex);
                    }
                });


                view.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View view) {
                        clicks++;


                    }

                    private void setDataBundle() {
                        final Dialog dialog = new Dialog(context);


                        dialog.setTitle(fixArabic(word.getWordsAr()));


                        Bundle dataBundle = new Bundle();
                        dataBundle.putInt(SURAH_ID, word.getSurahId());
                        dataBundle.putInt(AYAHNUMBER, Math.toIntExact(word.getVerseId()));
                        dataBundle.putInt(WORDNUMBER, Math.toIntExact(word.getWordno()));
                        dataBundle.putString(SURAH_ARABIC_NAME, SurahName);
                        LoadItemList(dataBundle);
                    }

                    private void LoadItemList(Bundle dataBundle) {
                        WordAnalysisBottomSheet item = new WordAnalysisBottomSheet();
                        //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
                        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();

                        item.setArguments(dataBundle);
                        String data[] = {String.valueOf(word.getSurahId()), String.valueOf(word.getVerseId()), word.getTranslateEn(), String.valueOf((word.getWordno()))};
                        FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
                        //   transactions.show(item);
                        WordAnalysisBottomSheet.newInstance(data).show(((AppCompatActivity) context).getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);


                    }


                });

            }

            holder.flow_word_by_word.setVisibility(View.VISIBLE);
            holder.arabic_textView.setVisibility(View.GONE);
            ;
            //    holder.arabic_textView.setText(ayahWord.getQuranArabic());

        } else {
            holder.flow_word_by_word.setVisibility(View.GONE);
            //  holder.arabic_textView.setText(entity.getArabic());
            holder.arabic_textView.setText(ayahWord.getQuranArabic());

            // holder.arabic_textView.setTypeface(typeface);
            holder.arabic_textView.setTextSize(SharedPref.SeekarabicFontsize());
            holder.arabic_textView.setVisibility(View.VISIBLE);
        }

        //   holder.quran_textView.setText(ayahWord.getQuranArabic());
        //  holder.quran_textView.setTextSize(SharedPref.SeekarabicFontsize());
        //  holder.quran_textView.setTypeface(custom_font);
        //  holder.quran_textView.setVisibility(View.VISIBLE);

        if (showTransliteration) {
            holder.quran_transliteration.setText(Html.fromHtml(entity.getTranslation(), Html.FROM_HTML_MODE_LEGACY));
            holder.quran_transliteration.setTextSize(SharedPref.SeekarabicFontsize());
            holder.quran_transliteration.setTextSize(SharedPref.SeekarabicFontsize());
            holder.quran_transliteration.setVisibility(View.VISIBLE);


        }
        if (showJalalayn) {
          //  holder.quran_jalalaynnote.setText(enjalalayn.getAuthor_name());
            holder.quran_jalalayn.setText(entity.getTranslation());
            holder.quran_jalalayn.setTextSize(SharedPref.SeekarabicFontsize());
            holder.quran_jalalayn.setTextSize(SharedPref.SeekarabicFontsize());
            holder.quran_jalalayn.setVisibility(View.VISIBLE);
            holder.quran_jalalaynnote.setVisibility(View.VISIBLE);
        }
        if (showTranslation) {

      //      holder.translate_textViewnote.setText(translationPojo.getAuthor_name());
            holder.translate_textView.setText(entity.getTranslation());
            //   holder.translate_textView.setText(ayahWord.getQuranTranslate());
            holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            holder.translate_textView.setVisibility(View.VISIBLE);
            holder.translate_textViewnote.setVisibility(View.VISIBLE);

        }

        if (showErab) {


            //  holder.erab_textView.setText(ayahWord.getErabDisplayl());
           // holder.erab_textViewnote.setText(erabp.getAuthor_name());
           // holder.erab_textView.setText(erabp.getErabspannable());
            holder.erab_textView.setTextSize(SharedPref.SeekarabicFontsize());
            holder.erab_textView.setTypeface(custom_font);
            holder.erab_textView.setVisibility(View.VISIBLE);
            holder.erab_textViewnote.setVisibility(View.VISIBLE);


            if (SharedPref.themePreferences().equals("dark")) {
                holder.erab_textView.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.white));
                ;

            } else {
                holder.erab_textView.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.burntamber));

            }

        }


    }


    private void setStartText(AyahViewHolder holder, AyahWord ayahWord) {

        if (ayahWord.getWord().get(0).getVerseId() == 1) {//|| ayahWord.getWord().get(0).getSurahId() == 9 ||ayahWord.getWord().get(0).getVerseId() != 1) {
            holder.bismilla.setVisibility(View.VISIBLE);

        } else {

            holder.bismilla.setVisibility(View.GONE);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setChapterInfo(AyahViewHolder holder, CorpusAyahWord verse) {


        StringBuilder surahInfo = new StringBuilder();
//        surahInfo.append(surahName+".");
        surahInfo.append(verse.getWord().get(0).getSurahId()).append(".");
        surahInfo.append(verse.getWord().get(0).getVerseId());

        surahInfo.append(SurahName);
        final String isNightmode = SharedPref.themePreferences();
        if (isMakkiMadani == 1) {
            holder.surah_info.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_makkah_48, 0, 0, 0);
            if (isNightmode.equals("dark")) {


                holder.surah_info.setCompoundDrawableTintList(ColorStateList.valueOf(Color.WHITE));
            } else {
                holder.surah_info.setCompoundDrawableTintList(ColorStateList.valueOf(Color.BLACK));
            }


        } else {
            holder.surah_info.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_madinah_48, 0, 0, 0);

            if (isNightmode.equals("dark")) {
                holder.surah_info.setCompoundDrawableTintList(ColorStateList.valueOf(Color.WHITE));
            } else {
                holder.surah_info.setCompoundDrawableTintList(ColorStateList.valueOf(Color.BLACK));
            }
        }

        holder.surah_info.setText(surahInfo);
    //    holder.surah_info.setTextColor(context.getResources().getColor(R.color.colorOnPrimary));


    }


    private String fixArabic(String s) {
        // Add sukun on mem | nun
        s = s.replaceAll("([\u0645\u0646])([ \u0627-\u064A]|$)", "$1\u0652$2");
        // Tatweel + Hamza Above (joining chairless hamza) => Yeh With Hamza Above
        s = s.replaceAll("\u0640\u0654", "\u0626");
        return s;
    }

    private int getStringInt(SharedPreferences sp, String key, int defValue) {
        return Integer.parseInt(sp.getString(key, Integer.toString(defValue)));
    }

    @Override
    public void onItemClick(View v, int position) {

    }

    public void setListener(OnItemClickListener listener) {

        mItemClickListener = listener;
    }

    public void getItem(int position) {
    }


    public class AyahViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView verse_idTextView;
        public final TextView quran_jalalayn, setlengthtwo;
        public final TextView quran_transliteration;
        public final FlowLayout flow_word_by_word;
        public final TextView translate_textView;
        public final TextView arabic_textView;
        public final TextView erab_textView;
        public final TextView surah_info;
        public final TextView bismilla;
        public final EditText quran_textView;
        public final TextView quran_transliterationnote;
        public final TextView quran_jalalaynnote;
        public final TextView erab_textViewnote;
        public final EditText translate_textViewnote, translate_textViewnotetwo;
        public final ImageView bookmark;
        public final ImageView expandImageButton;
        public final Chip chips_group;
        public Button finalize, doneb, reset, innawithism, innaseprateism;
        TextView setlength;


        public AyahViewHolder(View view) {
            super(view);
            view.setTag(this);
            setlengthtwo = view.findViewById(R.id.setlengthtwo);
            translate_textViewnotetwo = view.findViewById(R.id.translate_textViewnotetwo);
            innawithism = view.findViewById(R.id.kanawithism);
            innaseprateism = view.findViewById(R.id.kanaseprateism);
            reset = view.findViewById(R.id.reset);

            bismilla = view.findViewById(R.id.bismillah);
            setlength = view.findViewById(R.id.setlength);

            chips_group = view.findViewById(R.id.chips_group);
            quran_transliterationnote = view.findViewById(R.id.quran_transliterationnote);
            quran_jalalaynnote = view.findViewById(R.id.quran_jalalaynnote);
            translate_textViewnote = view.findViewById(R.id.translate_textViewnote);
            erab_textViewnote = view.findViewById(R.id.erab_textViewnote);
            quran_transliteration = view.findViewById(R.id.quran_transliteration);
            quran_jalalayn = view.findViewById(R.id.quran_jalalayn);
            surah_info = view.findViewById(R.id.chaptername);
            //    verse_idTextView = view.findViewById(R.id.verse_id_textView);
            arabic_textView = view.findViewById(R.id.arabic_textView);
            flow_word_by_word = view.findViewById(R.id.flow_word_by_word);
            translate_textView = view.findViewById(R.id.translate_textView);
            erab_textView = view.findViewById(R.id.erab_textView);
            quran_textView = view.findViewById(R.id.quran_textView);
            bookmark = view.findViewById(R.id.bookmarkView);
            expandImageButton = view.findViewById(R.id.expandImageButton);
            view.setOnClickListener(this);
            quran_textView.setEnabled(true);
            quran_textView.setOnClickListener(this);
            translate_textViewnote.setEnabled(true);
            translate_textViewnote.setOnClickListener(this);
            translate_textViewnotetwo.setEnabled(true);
            translate_textViewnotetwo.setOnClickListener(this);


            quran_textView.setTextIsSelectable(true);
            quran_textView.setEnabled(true);
            innaseprateism.setOnClickListener(this);
            innawithism.setOnClickListener(this);
            //   translate_textViewnote.setKeyListener(null);
            quran_textView.setKeyListener(null);
            translate_textViewnote.setInputType(InputType.TYPE_NULL);
            translate_textViewnotetwo.setInputType(InputType.TYPE_NULL);
            //   translate_textViewnote.setFocusable(false);


            innawithism.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getPhraselenone() > 0) {
                        final Integer integer = Integer.valueOf(getPhraselenone());
                        windex.add(integer);
                        getThisindices().size();
                        if (getThisindices().size() == 5) {
                            getThisindices().remove(3);

                        } else if (getThisindices().size() == 4) {
                            getThisindices().remove(3);
                            getThisindices().add(3, integer);
                        } else if (getThisindices().size() == 10) {
                            getThisindices().remove(8);
                            getThisindices().add(8, integer);

                        }

                    }


                    for (int in = 0; in < windex.size(); in++) {


                        if (in == 0) {
                            harfNasbIndexorig.setFirstinna(windex.get(in));


                        } else if (in == 1) {
                            harfNasbIndexorig.setFirstinnalen(windex.get(in));


                        } else if (in == 2) {
                            harfNasbIndexorig.setFirstkhabar(windex.get(in));

                        } else if (in == 3) {
                            harfNasbIndexorig.setFirstkhabarlen(windex.get(in));

                        }

                    }
                    harfNasbIndexorig.setSurahid(getSurahid());
                    harfNasbIndexorig.setAyahid(getAyahid());
                    harfNasbIndexArrayListorig.add(harfNasbIndexorig);
                    newwritefile("NASB", harfNasbIndexArrayListorig);
                    Toast.makeText(context, "inserted", Toast.LENGTH_SHORT).show();
                    resetArrays();

                }
            });

            innaseprateism.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getPhraselenone() > 0) {
                        final Integer integer = Integer.valueOf(getPhraselenone());
                        windex.add(integer);


                    } else {
                        Toast.makeText(context, "inserted", Toast.LENGTH_SHORT).show();
                        resetArrays();

                    }
                    if (getPhraselentwo() > 0) {
                        final Integer integer = Integer.valueOf(getPhraselentwo());
                        windex.add(integer);


                    }
                   if(windex.size()==8) {
                       harfNasbIndexorig.setFirstinna(windex.get(0));
                       ;
                       harfNasbIndexorig.setFirstinnalen(windex.get(0) + windex.get(1));

                       harfNasbIndexorig.setFirstism(windex.get(2));
                       //      harfNasbIndexorig.setFirstimslen(windex.get(3)+windex.get(6));

                       //   quranverses.subSequence(8,8+46)
                       harfNasbIndexorig.setFirstimslen(windex.get(2) + windex.get(6));

                       harfNasbIndexorig.setFirstkhabar(windex.get(4));
                       harfNasbIndexorig.setFirstkhabarlen(windex.get(4) + windex.get(7));



                   }else {
                       harfNasbIndexorig.setFirstinna(windex.get(0));
                       harfNasbIndexorig.setFirstinnalen(windex.get(0) + windex.get(1));
                       harfNasbIndexorig.setFirstism(windex.get(2));

                       //      harfNasbIndexorig.setFirstimslen(windex.get(3)+windex.get(6));

                       //   quranverses.subSequence(8,8+46)
                       harfNasbIndexorig.setFirstimslen(windex.get(2) + windex.get(4));


                   }
                    harfNasbIndexorig.setSurahid(getSurahid());
                    harfNasbIndexorig.setAyahid(getAyahid());
                    harfNasbIndexArrayListorig.add(harfNasbIndexorig);

                    newwritefile("shart", harfNasbIndexArrayListorig);
                    Toast.makeText(context, "inserted", Toast.LENGTH_SHORT).show();
                    resetArrays();

                }
            });
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetArrays();

                }
            });

            translate_textViewnotetwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Editable text = translate_textViewnotetwo.getText();
                    final int length = text.length();
                    StringBuilder sb = new StringBuilder();
                    sb.append(length);
                    setlengthtwo.setText(sb.toString());
                    setPhraselentwo(length);
                    translate_textViewnotetwo.setText("b");
                    //  translate_textViewnote.clearComposingText();

                }
            });

            translate_textViewnote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Editable text = translate_textViewnote.getText();
                    final int length = text.length();
                    StringBuilder sb = new StringBuilder();
                    sb.append(length);
                    setlength.setText(sb.toString());
                    setPhraselenone(length);
                    translate_textViewnote.setText("a");
                    //  translate_textViewnote.clearComposingText();

                }
            });

         /*
            translate_textViewnote.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    s.length();
                    setPhraselen(s.length());
                    StringBuilder sb=new StringBuilder();
                    sb.append(s.length());

                    setlength.setText(sb.toString());
                    System.out.println(s);

                }
            });
          */

            ;


            expandImageButton.setOnClickListener(view1 -> {

                final int visibility = erab_textView.getVisibility();

                AnimationUtility.slide_down(context, expandImageButton);
                if (erab_textView.getVisibility() == View.GONE)
                    erab_textView.setVisibility(View.VISIBLE);
                else {
                    erab_textView.setVisibility(View.GONE);
                    AnimationUtility.AnimateArrow(-90.0f, expandImageButton);
                    //    Fader.slide_down(context,expandImageButton);
                }


            });

            flow_word_by_word.setOnClickListener(view1 -> {

                if (translate_textView.getVisibility() == View.GONE)
                    translate_textView.setVisibility(View.VISIBLE);
                else
                    translate_textView.setVisibility(View.VISIBLE);


            });

            translate_textView.setOnClickListener(view1 -> {

                if (translate_textView.getVisibility() == View.VISIBLE)
                    translate_textView.setVisibility(View.GONE);
                else
                    translate_textView.setVisibility(View.VISIBLE);


            });

            erab_textView.setOnClickListener(view1 -> {

                if (erab_textView.getVisibility() == View.VISIBLE)
                    erab_textView.setVisibility(View.GONE);
                else
                    erab_textView.setVisibility(View.VISIBLE);


            });
            if (done) {
                writefile("INDEXES", harfNasbIndexArrayList);

            }

        }

        private void resetArrays() {
            harfNasbIndexArrayList = new ArrayList<>();

            harfNasbIndex = new HarfNasbIndexArrays();

            harfNasbIndexArrayListorig = new ArrayList<>();

            harfNasbIndexorig = new HarfNasbIndex();
            windex = new ArrayList<Integer>();
            setPhraselenone(0);
            setPhraselentwo(0);
            setlength.clearComposingText();
        }

        private void newwritefile(String filename, ArrayList<HarfNasbIndex> harfNasbIndexArrayListorig) {
            ArrayList<String> ammended = new ArrayList<>();
            String state = Environment.getExternalStorageState();
            for (HarfNasbIndex arrays : harfNasbIndexArrayListorig) {
                StringBuilder ss = new StringBuilder();
                ss.append(arrays.getSurahid()).append(",").append(arrays.getAyahid()).append(",").append(arrays.getFirstinna()).
                        append(",").append(arrays.getFirstinnalen()).append(",").append(arrays.getFirstism()).append(",").append(arrays.getFirstimslen()).append(",").
                        append(arrays.getFirstkhabar()).append(",").append(arrays.getFirstkhabarlen()).append(",").
                        append(arrays.getSecondinna()).append(",").append(arrays.getSecondinnalen()).append(",").append(arrays.getSecondism()).append(",").append(arrays.getSecondismlen()).append(",").
                        append(arrays.getSecondkhabar()).append(",").append(arrays.getSecondkhabarlen()).
                        append(",").append(arrays.getThirdinna()).append(",").
                        append(arrays.getThirdinnalen()).append(",").append(arrays.getThirdism()).append(",").append(arrays.getThirdismlen()).append(",").
                        append(arrays.getThirdhabar()).append(",").append(arrays.getThirdkhabarlen());

                final String data = ss.toString().replaceAll("\\[|\\]", "");
                //  final int surahid = arrays.getSurahid();
                // final ArrayList<Integer> indices = arrays.getIndices();
                //  final int ayahid = arrays.getAyahid();
                ammended.add(data);


            }


            if (Environment.MEDIA_MOUNTED.equals(state)) {
                FileWriter myWriter;
                String s;
                if (checkPermission()) {
                    File sdcard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdcard.getAbsolutePath() + "/text/");
                    dir.mkdir();
                    File file = new File(dir, filename);
                    FileOutputStream os;
                    try {
                        os = new FileOutputStream(file, true);
                        //      os.write(harfNasbIndexArrayList.toString().getBytes());

                        for (Object str : ammended) {
                            os.write(str.toString().getBytes());
                            String newline = "\n";
                            os.write(newline.getBytes());
                        }

                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    requestPermission(); // Code for permission
                }
            }


        }

        private void writefile(String filename, ArrayList<HarfNasbIndexArrays> harfNasbIndexArrayList) {
            ArrayList<String> ammended = new ArrayList<>();
            String state = Environment.getExternalStorageState();
            for (HarfNasbIndexArrays arrays : harfNasbIndexArrayList) {
                StringBuilder ss = new StringBuilder();
                ss.append(arrays.getSurahid()).append(",").append(arrays.getAyahid()).append(",").append(arrays.getIndices());
                final String data = ss.toString().replaceAll("\\[|\\]", "");
                //  final int surahid = arrays.getSurahid();
                // final ArrayList<Integer> indices = arrays.getIndices();
                //  final int ayahid = arrays.getAyahid();
                ammended.add(data);


            }


            if (Environment.MEDIA_MOUNTED.equals(state)) {
                FileWriter myWriter;
                String s;
                if (checkPermission()) {
                    File sdcard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdcard.getAbsolutePath() + "/text/");
                    dir.mkdir();
                    File file = new File(dir, filename);
                    FileOutputStream os;
                    try {
                        os = new FileOutputStream(file);
                        //      os.write(harfNasbIndexArrayList.toString().getBytes());

                        for (Object str : ammended) {
                            os.write(str.toString().getBytes());
                            String newline = "\n";
                            os.write(newline.getBytes());
                        }

                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    requestPermission(); // Code for permission
                }
            }


        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission((Activity) context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText((Activity) DarkThemeApplication.getContext(), "Write External Storage permission allows us to create files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
}























