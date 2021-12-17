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
import com.example.mushafconsolidated.intrface.OnItemClickListenerOnLong;
import com.example.mushafconsolidated.model.AyahWord;
import com.example.mushafconsolidated.model.CorpusAyahWord;
import com.example.mushafconsolidated.model.CorpusWbwWord;
import com.example.mushafconsolidated.model.HarfNasbIndex;
import com.example.mushafconsolidated.model.HarfNasbIndexArrays;
import com.example.mushafconsolidated.model.ShartMarkupPojo;
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

public class HarbNasbMarkupFlowAyahWordAdapter extends RecyclerView.Adapter<HarbNasbMarkupFlowAyahWordAdapter.AyahViewHolder> implements OnItemClickListener {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private final List<QuranEntity> allofQuran;
    private final ArrayList<QuranEntity> colorerab;
    ArrayList<Integer> thisindices;
    ArrayList<Integer> phraseindeces;
    static boolean showTranslation;
    static boolean wordByWord;
    boolean done = false;
    int surahid, ayahid;
int noclicks;
    ArrayList<ShartMarkupPojo> shartindexArray =new ArrayList<>();
    ArrayList<Integer> windex = new ArrayList<Integer>();
    private ShartMarkupPojo shart;

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

    OnItemClickListenerOnLong mItemClickListener;
    int shartphraselen;
    int jawabphraselen;
    static int fontSizeArabic;
    static int fontSizeTranslation;

    public int getJawabphraselen() {
        return jawabphraselen;
    }

    public void setJawabphraselen(int jawabphraselen) {
        this.jawabphraselen = jawabphraselen;
    }

    public int getShartphraselen() {
        return shartphraselen;
    }

    public void setShartphraselen(int shartphraselen) {
        this.shartphraselen = shartphraselen;
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
    ArrayList<ShartMarkupPojo> harfNasbIndexArrayListorig;

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

    public HarbNasbMarkupFlowAyahWordAdapter(ArrayList<QuranEntity> colorerab, List<QuranEntity> allofQuran, ArrayList<CorpusAyahWord> ayahWordArrayList, Context context, long surah_id, String surahName, int ismakki, OnItemClickListenerOnLong listener) {
        this.allofQuran = allofQuran;
 this.colorerab=colorerab;
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


    public void SetOnItemClickListener(final OnItemClickListenerOnLong mItemClickListener) {
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
    public HarbNasbMarkupFlowAyahWordAdapter.AyahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

      //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_row_ayah_word, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_shart_flow_row_ayah_word, parent, false);


        //     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.back_row_ayah_word, parent, false);
        HarbNasbMarkupFlowAyahWordAdapter.AyahViewHolder viewHolder = new HarbNasbMarkupFlowAyahWordAdapter.AyahViewHolder(view);

        return viewHolder;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(HarbNasbMarkupFlowAyahWordAdapter.AyahViewHolder holder, int position) {
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
        QuranEntity entity = allofQuran.get(position);
        ayahWord = ayahWordArrayList.get(position);
        QuranEntity erab = colorerab.get(position);
        //   String quranverses = String.valueOf(ayahWordArrayList.get(position).getSpannableverse());
        final SpannableString quranverses = ayahWordArrayList.get(position).getSpannableverse();
        holder.quran_textView.setText(quranverses);
        //   holder.translate_textView.setText(ayahWord.getQuranTranslate());

        holder.quran_textView.setTextSize(SharedPref.SeekarabicFontsize());
        holder.quran_textView.setTypeface(custom_font);

        setChapterInfo(holder, ayahWord);

        setAdapterposition(position);
        //  setStartText(holder,ayahWord);
        shart=new ShartMarkupPojo();
 noclicks=1;
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
                        final int harflength = word.getWordsAr().length();
                        if(noclicks==1) {
                            shart.setHarfstartindex(word.getWordindex());
                            shart.setHarfendindex(word.getWordindex()+harflength);
                            shart.setHarfwordno(word.getWordno());

                        }else if(noclicks==2){
                            shart.setShartstartindex(word.getWordindex());
                            shart.setShartendindex(harflength);
                            shart.setShartstartwordno(word.getWordno());


                        }else if(noclicks==3){
                            shart.setJawabstartindex(word.getWordindex());
                            shart.setJawabendindex(harflength);
                            shart.setJawabstartwordno(word.getWordno());

                        }
                        noclicks++;
                        StringBuilder ss = new StringBuilder();

                         int harfwordno=word.getWordno();
                        final Integer integer = Integer.valueOf(word.getWordindex());
                        final Integer integerlen = Integer.valueOf(word.getWordsAr().length());


                        windex.add(integer);
                        windex.add(integerlen);
                        windex.add(harfwordno);

                        setThisindices(windex);
                        shart.setSurahid(word.getSurahId());
                        shart.setAyahid(word.getVerseId());
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
         //   holder.quran_jalalaynnote.setText(enjalalayn.getAuthor_name());
            holder.quran_jalalayn.setText(entity.getTranslation());
            holder.quran_jalalayn.setTextSize(SharedPref.SeekarabicFontsize());
            holder.quran_jalalayn.setTextSize(SharedPref.SeekarabicFontsize());
            holder.quran_jalalayn.setVisibility(View.VISIBLE);
            holder.quran_jalalaynnote.setVisibility(View.VISIBLE);
        }
        if (showTranslation) {

      //      holder.translate_textViewnote.setText(entity.getAuthor_name());
            holder.translate_textView.setText(entity.getTranslation());
            //   holder.translate_textView.setText(ayahWord.getQuranTranslate());
            holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            holder.translate_textView.setVisibility(View.VISIBLE);
            holder.phraseone.setVisibility(View.VISIBLE);

        }

        if (showErab) {


              holder.erab_textView.setText(erab.getErabspnabble());
        //    holder.erab_textViewnote.setText(entity.getAuthor_name());
         //   holder.erab_textView.setText(entity.getErabspannable());
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
  //      holder.surah_info.setTextColor(context.getResources().getColor(R.color.colorOnPrimary));


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

    public void setListener(OnItemClickListenerOnLong listener) {

        mItemClickListener = listener;
    }

    public void getItem(int position) {
    }


    public class AyahViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView verse_idTextView;
        public final TextView quran_jalalayn;
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
        public final EditText phraseone,phrasetwo;
        public final ImageView bookmark;
        public final ImageView expandImageButton;
        public final Chip chips_group;
        public Button finalize, doneb, reset, harfonlyshart, harfshartandjawab;
        TextView setlength,setlengthtwo;


        public AyahViewHolder(View view) {
            super(view);
            view.setTag(this);
            harfonlyshart = view.findViewById(R.id.harfonlyshart);
            harfshartandjawab = view.findViewById(R.id.harfshartandjawab);
            reset = view.findViewById(R.id.reset);
            finalize = view.findViewById(R.id.finalize);
            bismilla = view.findViewById(R.id.bismillah);
            setlength = view.findViewById(R.id.setlength);
            setlengthtwo=view.findViewById(R.id.setlengthtwo);
            doneb = view.findViewById(R.id.doneb);
            chips_group = view.findViewById(R.id.chips_group);
            quran_transliterationnote = view.findViewById(R.id.quran_transliterationnote);
            quran_jalalaynnote = view.findViewById(R.id.quran_jalalaynnote);
            phraseone = view.findViewById(R.id.phraseone);
            phrasetwo = view.findViewById(R.id.phrasetwo);
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
            phraseone.setEnabled(true);
            phraseone.setOnClickListener(this);
            phrasetwo.setEnabled(true);
            phrasetwo.setOnClickListener(this);


            doneb.setOnClickListener(this);
            finalize.setOnClickListener(this);

            quran_textView.setTextIsSelectable(true);
            quran_textView.setEnabled(true);
            harfshartandjawab.setOnClickListener(this);
            harfonlyshart.setOnClickListener(this);
      //   translate_textViewnote.setKeyListener(null);
            quran_textView.setKeyListener(null);
     phraseone.setInputType(InputType.TYPE_NULL);
     phrasetwo.setInputType(InputType.TYPE_NULL);
         //   translate_textViewnote.setFocusable(false);


            harfonlyshart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                   shartindexArray.add(shart);

                    newwritefile("shart", shartindexArray);
                    Toast.makeText(context, "inserted", Toast.LENGTH_SHORT).show();
                    resetArrays();

                }
            });

            harfshartandjawab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    shartindexArray.add(shart);
                    newwritefile("shart", shartindexArray);
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

            doneb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDone(true);
                    //       writefile("NASB", harfNasbIndexArrayList);
                    newwritefile("NASB", harfNasbIndexArrayListorig);
                   resetArrays();
                }
            });
            finalize.setOnClickListener(new View.OnClickListener() {
                private View chips_group;

                @Override
                public void onClick(View v) {


                    //   getHarfNasbIndexesArrays();


                }

                private void getHarfNasbIndexesArrays() {
                    //  harfNasbIndex.setIndices(windex);
                    ArrayList<Integer> windex = new ArrayList<Integer>();
                    if (getShartphraselen() > 0) {
                        final Integer integer = Integer.valueOf(getShartphraselen());
                        windex.add(integer);
                        getThisindices().size();
                        if (getThisindices().size() == 6) {
                            getThisindices().remove(5);
                            getThisindices().add(5, integer);
                        } else if (getThisindices().size() == 4) {
                            getThisindices().remove(3);
                            getThisindices().add(3, integer);
                        } else if (getThisindices().size() == 10) {
                            getThisindices().remove(9);
                            getThisindices().add(9, integer);

                        }

                    }


                    harfNasbIndex.setIndices(getThisindices());
                    harfNasbIndex.setSurahid(getSurahid());
                    harfNasbIndex.setAyahid(getAyahid());
                    harfNasbIndexArrayList.add(harfNasbIndex);
                    harfNasbIndex = new HarfNasbIndexArrays();
                    setShartphraselen(0);
                }
            });
            phraseone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Editable text = phraseone.getText();
                    String[] split = text.toString().split("\\s");
                     int nowords=split.length;
                    final int length = text.length();
                    if(length>1) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(length);

                        setlength.setText(sb.toString());
                        setShartphraselen(length);
                        shart.setShartendindex(shart.getShartstartindex() + length);
                        shart.setShartendwordno(shart.getShartstartwordno() + (nowords - 1));
                        phraseone.setText("a");
                    }
                    //  translate_textViewnote.clearComposingText();

                }
            });

            phrasetwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Editable text = phrasetwo.getText();
                    final int length = text.length() ;
                    String[] split = text.toString().split("\\s");
                    int nowords=split.length;
                    if(length>1) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(length);
                        setlengthtwo.setText(sb.toString());
                        setJawabphraselen(length);

                        shart.setJawabendindex(shart.getJawabstartindex() + length);
                        shart.setJawabendwordno(shart.getJawabstartwordno() + (nowords - 1));
                        phrasetwo.setText("b");
                    }
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
noclicks=1;
shart=new ShartMarkupPojo();
            shartindexArray=new ArrayList<>();
            harfNasbIndexArrayListorig = new ArrayList<>();

            harfNasbIndexorig = new HarfNasbIndex();
            windex = new ArrayList<Integer>();
            setShartphraselen(0);
        }

        private void newwritefile(String filename, ArrayList<ShartMarkupPojo> harfNasbIndexArrayListorig) {
            ArrayList<String> ammended = new ArrayList<>();
            String state = Environment.getExternalStorageState();
            for (ShartMarkupPojo arrays : harfNasbIndexArrayListorig) {
                StringBuilder ss = new StringBuilder();
                ss.append(arrays.getSurahid()).append(",").append(arrays.getAyahid()).append(",")
                      .append(arrays.getHarfstartindex()). append(",").append(arrays.getHarfendindex())
                      .append(",").append(arrays.getShartstartindex()).append(",").append(arrays.getShartendindex()).append(",").
                        append(arrays.getJawabstartindex()).append(",").append(arrays.getJawabendindex()).append(",").
                        append(arrays.getHarfwordno()).append(",").append(arrays.getShartstartwordno()).append(",")
                      .append(arrays.getShartendwordno()).append(",").append(arrays.getJawabstartwordno()).append(",").
                        append(arrays.getJawabendwordno()).append(",")
                         .append("comment" );

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
                        os = new FileOutputStream(file,true);
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























