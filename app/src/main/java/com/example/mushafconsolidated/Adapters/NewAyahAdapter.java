package com.example.mushafconsolidated.Adapters;


import static com.example.Constant.AYAHNUMBER;
import static com.example.Constant.SURAH_ARABIC_NAME;
import static com.example.Constant.SURAH_ID;
import static com.example.Constant.WORDNUMBER;
import static com.example.utility.SharedPref.arabicFontSelection;
import static com.example.utility.SharedPref.showErab;
import static com.example.utility.SharedPref.showJalalayn;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Activity.newreadactivity;
import com.example.mushafconsolidated.Config;
import com.example.mushafconsolidated.Entities.CorpusAndWbwTranslation;
import com.example.mushafconsolidated.Entities.QuranEntity;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.mushafconsolidated.model.AyahWord;
import com.example.mushafconsolidated.model.Word;
import com.example.utility.AnimationUtility;
import com.example.utility.DarkThemeApplication;
import com.example.utility.FlowLayout;
import com.example.utility.PreferenceUtil;
import com.example.utility.SharedPref;

import java.util.ArrayList;
import java.util.List;
//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;

public class NewAyahAdapter extends RecyclerView.Adapter<NewAyahAdapter.AyahViewHolder> implements OnItemClickListener{

    static boolean showTranslation;
    static boolean wordByWord;
    private   List<QuranEntity> translations;
    private List<QuranEntity> erab;
    private List<QuranEntity> transliteration;
    private List<QuranEntity> jalalayn;
    OnItemClickListener mItemClickListener;
    static int fontSizeArabic;
    static int fontSizeTranslation;
    static int fontSizeErab;
    static Typeface corpusTypeface;
    static String[] corpusArabicTypeArray;
    public Context context;
    long surah_id;
    private ArrayList<AyahWord> ayahWordArrayList;

    public int adapterposition;

    public NewAyahAdapter(Context context) {
        this.context=context;

    }

    public int getAdapterposition() {
        return adapterposition;
    }

    public void setAdapterposition(int adapterposition) {
        this.adapterposition = adapterposition;
    }

    private SharedPref prefs;
    PreferenceUtil pref;
    private String SurahName;
    private int isMakkiMadani;
    public TextView arabic;

    public NewAyahAdapter(List<QuranEntity> transliteration, List<QuranEntity> jalalayn, List<QuranEntity> translations, List<QuranEntity> erab,ArrayList<AyahWord> ayahWordArrayList, Context context, long surah_id, String surahName, int ismakki, OnItemClickListener listener) {
        this.transliteration=transliteration;
        this.jalalayn=jalalayn;
        this.translations=translations;
        this.erab=erab;
        this.ayahWordArrayList = ayahWordArrayList;
        this.context = context;
        this.surah_id = surah_id;
        this.SurahName = surahName;
        this.isMakkiMadani = ismakki;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
          sharedPreferences.getBoolean(Config.SHOW_Erab, Config.defaultShowErab);

        SharedPreferences sharedPref =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
   //     pref = new PreferenceUtil(sharedPref);
        mItemClickListener=listener;

    }


 //   public void addContext(Context context){
   //     this.context=context;

 //   }


    public void setItAll(            ArrayList<ArrayList<CorpusAndWbwTranslation>> arrayListsayahWordArrayList, String suraharabicname, int isMakkiMadani, Context context) {
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

    public void setItAll(            ArrayList<ArrayList<CorpusAndWbwTranslation>> arrayLists, String suraharabicname, int isMakkiMadani, Context context,OnItemClickListener listener) {
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


// --Commented out by Inspection START (24/6/21 4:14 PM):
//    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
//        this.mItemClickListener = mItemClickListener;
//    }
// --Commented out by Inspection STOP (24/6/21 4:14 PM)

    @Override
    public int getItemCount() {
        return ayahWordArrayList.size();
        //     return  quran.size();
    }

    @Override
    public long getItemId(int position) {

        AyahWord ayahWord = ayahWordArrayList.get(position);
        long itemId = 1;
        for (Word word : ayahWord.getWord()) {
            word.getWordno();
        itemId=    word.getSurahId();
        }


        return itemId;
    }

    @Override
    public NewAyahAdapter.AyahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ayah_word, parent, false);
   //     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.back_row_ayah_word, parent, false);
        return new AyahViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(NewAyahAdapter.AyahViewHolder holder, int position) {
        QuranEntity translationPojo=null;
      QuranEntity erabp = null;
        QuranEntity transliterations = null;
      QuranEntity enjalalayn = null;
        boolean showErab = SharedPref.showErab();
        boolean showTransliteration=SharedPref.showTransliteration();
        boolean showJalalayn=SharedPref.showJalalayn();
        boolean showTranslation=SharedPref.showTranslation();
        boolean showWordByword=SharedPref.showWordByword();
        final AyahWord ayahWord = ayahWordArrayList.get(position);
        if(showTranslation) {
              translationPojo = translations.get(position);
        }
        if(showErab()) {
            erabp = erab.get(position);
        }
        if(SharedPref.showTransliteration()) {
       transliterations = transliteration.get(position);
        }
        if(showJalalayn()) {
             enjalalayn = jalalayn.get(position);
        }
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),
                SharedPref.arabicFontSelection());
        setChapterInfo(holder, ayahWord);

        setAdapterposition(position);
        showWordByword=true;
        if (showWordByword) {
            final LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder.flow_word_by_word.removeAllViews();
            for (Word word: ayahWord.getWord()) {
                final View view = inflater.inflate(R.layout.word_by_word,  null, false);
                //   final TextView arabic = view.findViewById(R.id.word_arabic_textView);
                arabic = view.findViewById(R.id.word_arabic_textView);

                final TextView translation = view.findViewById(R.id.word_trans_textView);
                arabic.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeArabic);
                translation.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeTranslation);
                final String arabicFontSelection = arabicFontSelection();
             //   arabic.setText(fixArabic(word.getAraone()+word.getAratwo()+word.getArathree()+word.getArafour()+word.getArafive()));
                arabic.setText(word.getWordsAr());
                arabic.setTextSize(SharedPref.SeekarabicFontsize());
                arabic.setTypeface(custom_font);
                translation.setText(word.getTranslateEn());
              if(SharedPref.themePreferences().equals("dark")){
                arabic.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.white));
                  translation.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.white));

                }else{
                  arabic.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.burntamber));
                  translation.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.burntamber));
                 }


                translation.setTextSize(SharedPref.SeekarabicFontsize());
                holder.flow_word_by_word.addView(view);

                view.setOnClickListener( new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(context);


                        Bundle dataBundle = new Bundle();
                        dataBundle.putInt(SURAH_ID, Math.toIntExact(surah_id));dataBundle.putInt(AYAHNUMBER, Math.toIntExact(word.getSurahId()));
                        dataBundle.putInt(WORDNUMBER, Math.toIntExact(word.getWordno()));
                        dataBundle.putString(SURAH_ARABIC_NAME, SurahName);
                        LoadItemList(dataBundle);


                        //     ItemListDialogFragment.newInstance().show((AppCompatActivity)context).getSupportFragmentManager(), ItemListDialogFragment.TAG);
                        //   Intent wordintent=new Intent(context, WordWordActivity.class);
                        //  wordintent.putExtras(dataBundle);
                        //  context.startActivity(wordintent);
                        //   loaddialog(dialog, dataBundle);
                    }

                    private void LoadItemList(Bundle dataBundle) {
                        WordAnalysisBottomSheet item=new WordAnalysisBottomSheet();
                        //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
                        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();

                        item.setArguments(dataBundle);
                        String[] data = {String.valueOf(word.getSurahId()), String.valueOf(word.getVerseId()),word.getTranslateEn(), String.valueOf((word.getWordno()))};
                        FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
                        transactions.show(item);
                        WordAnalysisBottomSheet.newInstance(data).show(((AppCompatActivity)context).getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);
                    }


                });

            }

            holder.flow_word_by_word.setVisibility(View.VISIBLE);
            holder.arabic_textView.setVisibility(View.GONE);
            //    holder.arabic_textView.setText(ayahWord.getQuranArabic());

        } else {
            holder.flow_word_by_word.setVisibility(View.GONE);
            //  holder.arabic_textView.setText(entity.getArabic());
       //     holder.arabic_textView.setText(ayahWord.getQuranArabic());

            // holder.arabic_textView.setTypeface(typeface);
            holder.arabic_textView.setTextSize(SharedPref.SeekarabicFontsize());
            holder.arabic_textView.setVisibility(View.VISIBLE);
        }

      //  holder.quran_textView.setText(ayahWord.getQuranArabic());
        holder.quran_textView.setTextSize(SharedPref.SeekarabicFontsize());
        holder.quran_textView.setTypeface(custom_font);
        holder.quran_textView.setVisibility(View.VISIBLE);

        if(showTransliteration){
            holder.quran_transliteration.setText(Html.fromHtml(transliterations.getTranslation(), Html.FROM_HTML_MODE_LEGACY));
            holder.quran_transliteration.setTextSize(SharedPref.SeekarabicFontsize());
            holder.quran_transliteration.setTextSize(SharedPref.SeekarabicFontsize());
            holder.quran_transliteration.setVisibility(View.VISIBLE);


        }
        if(showJalalayn){
          //  holder.quran_jalalaynnote.setText(enjalalayn.ge());
            holder.quran_jalalayn.setText(enjalalayn.getTranslation());

            holder.quran_jalalayn.setTextSize(SharedPref.SeekarabicFontsize());
            holder.quran_jalalayn.setVisibility(View.VISIBLE);
            holder.quran_jalalaynnote.setVisibility(View.VISIBLE);
        }
        if (showTranslation) {

          //  holder.translate_textViewnote.setText(translationPojo.getAuthor_name());
            holder.translate_textView.setText(translationPojo.getTranslation());
            //   holder.translate_textView.setText(ayahWord.getQuranTranslate());
            holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());

            holder.translate_textView.setVisibility(View.VISIBLE);
            holder.translate_textViewnote.setVisibility(View.VISIBLE);

        }

        if (showErab) {

            //  holder.erab_textView.setText(ayahWord.getErabDisplayl());
         //   holder.erab_textViewnote.setText(erabp.getAuthor_name());
            holder.erab_textView.setText(erabp.getTranslation());
            holder.erab_textView.setTextSize(SharedPref.SeekarabicFontsize());
            holder.erab_textView.setTypeface(custom_font);
            holder.erab_textView.setVisibility(View.VISIBLE);
            holder.erab_textViewnote.setVisibility(View.VISIBLE);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setChapterInfo(AyahViewHolder holder, AyahWord verse) {


        StringBuilder surahInfo = new StringBuilder();
//        surahInfo.append(surahName+".");
        surahInfo.append(verse.getWord().get(0).getSurahId()).append(".");
        surahInfo.append(verse.getWord().get(0).getVerseId());
        surahInfo.append(SurahName);
        final String isNightmode = SharedPref.themePreferences();
        if (isMakkiMadani == 1) {
            holder.surah_info.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_makkah_48, 0, 0, 0);


        } else {
            holder.surah_info.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_madinah_48, 0, 0, 0);

        }
        if (isNightmode.equals("dark")) {
            holder.surah_info.setCompoundDrawableTintList(ColorStateList.valueOf(Color.GREEN));
        //    holder.surah_info.TextViewCompat.setCompoundDrawableTintList(Color.GREEN);
        } else {
            holder.surah_info.setCompoundDrawableTintList(ColorStateList.valueOf(Color.BLACK));
        }

        holder.surah_info.setText(surahInfo);


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

        mItemClickListener=listener;
    }

  //  public void getItem(int position) {

   // }

    public AyahWord getItem(int position) {
        return ayahWordArrayList.get(position);
    }

    public void setItAll(List<QuranEntity> transliteration, List<QuranEntity> jalalayn, ArrayList<AyahWord> ayahWords, ArrayList<QuranEntity> translations, List<QuranEntity> erab, String suraharabicname, int isMakkiMadani, newreadactivity readingSurahPartActivity, newreadactivity readingSurahPartActivity1) {
    this.transliteration=transliteration;
    this.jalalayn=jalalayn;
        this.ayahWordArrayList = ayahWords;
this.erab=erab;
        SharedPreferences sharedPreferences =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        pref = new PreferenceUtil(sharedPreferences);
        //  PrefUtils(ayahadapterContext);
        this.translations=translations;
        SharedPref prefs = new SharedPref(context);
        this.SurahName = suraharabicname;
        this.isMakkiMadani = isMakkiMadani;


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
        public final TextView quran_textView;
        public final TextView quran_transliterationnote;
        public final TextView quran_jalalaynnote;
        public final TextView erab_textViewnote;
        public final TextView translate_textViewnote;
        public final ImageView bookmark;
        public final ImageView expandImageButton;



        public AyahViewHolder(View view) {
            super(view);
            view.setTag(this);

            quran_transliterationnote=view.findViewById(R.id.quran_transliterationnote);
            quran_jalalaynnote=view.findViewById(R.id.quran_jalalaynnote);
            translate_textViewnote = view.findViewById(R.id.translate_textViewnote);
            erab_textViewnote = view.findViewById(R.id.erab_textViewnote);
            quran_transliteration=view.findViewById(R.id.quran_transliteration);
            quran_jalalayn=view.findViewById(R.id.quran_jalalayn);
            surah_info = view.findViewById(R.id.chaptername);
          //  verse_idTextView = view.findViewById(R.id.verse_id_textView);
            arabic_textView = view.findViewById(R.id.arabic_textView);
            flow_word_by_word = view.findViewById(R.id.flow_word_by_word);
            translate_textView = view.findViewById(R.id.translate_textView);
            erab_textView = view.findViewById(R.id.erab_textView);
            quran_textView = view.findViewById(R.id.quran_textView);
            bookmark=view.findViewById(R.id.bookmarkView);
            expandImageButton=view.findViewById(R.id.expandImageButton);
            view.setOnClickListener(this);



/*
            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"this",Toast.LENGTH_LONG).show();

                }
            });
            */

            expandImageButton.setOnClickListener(view1 -> {

                final int visibility = erab_textView.getVisibility();

                AnimationUtility.slide_down(context,expandImageButton);
                if (erab_textView.getVisibility() == View.GONE)
                    erab_textView.setVisibility(View.VISIBLE);
                else {
                    erab_textView.setVisibility(View.GONE);
                    AnimationUtility.AnimateArrow(-90.0f,expandImageButton);
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


        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }
}























