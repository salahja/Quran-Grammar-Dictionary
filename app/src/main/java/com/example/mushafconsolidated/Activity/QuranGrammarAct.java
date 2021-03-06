package com.example.mushafconsolidated.Activity;


import static android.text.TextUtils.concat;
import static com.example.Constant.AYAHNUMBER;
import static com.example.Constant.AYAH_ID;
import static com.example.Constant.CHAPTER;
import static com.example.Constant.MAKKI_MADANI;
import static com.example.Constant.QURAN_VERB_ROOT;
import static com.example.Constant.RUKUCOUNT;
import static com.example.Constant.SURAH_ARABIC_NAME;
import static com.example.Constant.SURAH_ID;
import static com.example.Constant.VERSESCOUNT;
import static com.example.mushafconsolidated.R.drawable.activatedbackgroundbrown;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SwitchCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JustJava.InputFilterMinMax;
import com.example.mushafconsolidated.Adapters.FlowAyahWordAdapter;
import com.example.mushafconsolidated.Entities.BadalErabNotesEnt;
import com.example.mushafconsolidated.Entities.BookMarks;
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity;
import com.example.mushafconsolidated.Entities.CorpusExpandWbwPOJO;
import com.example.mushafconsolidated.Entities.HalEnt;
import com.example.mushafconsolidated.Entities.LiajlihiEnt;
import com.example.mushafconsolidated.Entities.MafoolBihi;

import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt;
import com.example.mushafconsolidated.Entities.QuranEntity;
import com.example.mushafconsolidated.Entities.TameezEnt;
import com.example.mushafconsolidated.ParticleColorScheme;
import com.example.mushafconsolidated.R;

import com.example.mushafconsolidated.Utils;
import com.example.mushafconsolidated.fragments.BookmarkFragment;
import com.example.mushafconsolidated.fragments.GrammerFragmentsBottomSheet;
import com.example.mushafconsolidated.fragments.NewSurahDisplayFrag;
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet;
import com.example.mushafconsolidated.intrface.OnItemClickListenerOnLong;
import com.example.mushafconsolidated.intrface.PassdataInterface;
import com.example.mushafconsolidated.model.CorpusAyahWord;
import com.example.mushafconsolidated.model.CorpusWbwWord;
import com.example.utility.CorpusUtilityorig;
import com.example.utility.QuranGrammarApplication;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.sj.conjugator.activity.ConjugatorAct;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;

public class QuranGrammarAct extends BaseActivity implements PassdataInterface, OnItemClickListenerOnLong {
    private static final String TAG = "fragment";

    ChipNavigationBar chipNavigationBar;
    FloatingActionButton btnBottomSheet;
    String surahArabicName;
    boolean jumptostatus = false;
    int surah_id = 0;
    int verseNumber, suraNumber;
    int rukucount;
    String surahname;
    int mudhafColoragainstBlack, mausofColoragainstBlack, sifatColoragainstBlack, brokenPlurarColoragainstBlack,shartagainstback;

    int surahorpart = 0;
    TextView tvsurah, tvayah, tvrukus;
    int currentSelectSurah;
    RelativeLayout layoutBottomSheet;
    BottomSheetBehavior sheetBehavior;
    int versescount = 0;
    boolean chapterorpart;
    // --Commented out by Inspection (14/08/21, 7:26 PM):ChipNavigationBar chipNavigationBar;
    int verse_no = 0;
    CoordinatorLayout coordinatorLayout;
  private FlowAyahWordAdapter flowAyahWordAdapter;
 // private UpdateMafoolFlowAyahWordAdapter flowAyahWordAdapter;
   private boolean mausoof, mudhaf, harfnasb, shart;
    private ArrayList<ChaptersAnaEntity> soraList;
    private EditText ayahIndex;
    private boolean kana;
    private List<QuranEntity> allofQuran;

    private SharedPreferences shared;


    //  private OnClickListener onClickListener;
    private ArrayList<CorpusAyahWord> corpusayahWordArrayList;
    private ArrayList<MafoolBihi> mafoolbihiwords;
    private ArrayList<HalEnt> Jumlahaliya;
    private ArrayList<TameezEnt> Tammezent;
    private ArrayList<MafoolMutlaqEnt> Mutlaqent;
    private ArrayList<LiajlihiEnt> Liajlihient;
    private ArrayList<BadalErabNotesEnt> BadalErabNotesEnt;


    private Utils utils;
    private int isMakkiMadani;
    private int chapterno;
    private RecyclerView parentRecyclerView;
    private final ArrayList<String> det=new ArrayList<>() ;

    public int getRukucount() {
        return rukucount;
    }

    public void setRukucount(int rukucount) {
        this.rukucount = rukucount;
    }

    public void setSurahorpart(int surahorpart) {
        this.surahorpart = surahorpart;
    }

    public int getCurrentSelectSurah() {
        return currentSelectSurah;
    }

    public void setCurrentSelectSurah(int currentSelectSurah) {
        this.currentSelectSurah = currentSelectSurah;
    }

    public String getSurahArabicName() {
        return surahArabicName;
    }

    public void setSurahArabicName(String surahArabicName) {
        this.surahArabicName = surahArabicName;
    }

    public void setSurah_id(int surah_id) {
        this.surah_id = surah_id;
    }

    public int getVersescount() {
        return versescount;
    }

    public void setVersescount(int versescount) {
        this.versescount = versescount;
    }

    public void setVerse_no(int verse_no) {
        this.verse_no = verse_no;
    }

    public void setIsMakkiMadani(int isMakkiMadani) {
        this.isMakkiMadani = isMakkiMadani;
    }

    public void setChapterorpart(boolean chapterorpart) {
        this.chapterorpart = chapterorpart;
    }

    public int getChapterno() {
        return chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }


    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
        if (!(fragment instanceof com.example.mushafconsolidated.fragments.IOnBackPressed) || !((com.example.mushafconsolidated.fragments.IOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }

        //  finish();


    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        shared =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(QuranGrammarAct.this);

        String preferences = shared.getString("themePref", "dark");
        switch (preferences) {
            case "white":
                switchTheme("white");
                break;
            case "dark":
                switchTheme("dark");

                break;
            case "blue":
                switchTheme("blue");


                break;
            case "green":
                switchTheme("green");


                break;
            case "brwon":
                switchTheme("brown");


                break;
        }
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.new_fragment_reading);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.getContext());
        if (preferences.equals("dark")|| preferences.equals("dark blue")) {
            shartagainstback=prefs.getInt("shartback",Color.GREEN);
            mausofColoragainstBlack = prefs.getInt("mausoofblack", Color.RED);
            mudhafColoragainstBlack = prefs.getInt("mudhafblack", Color.CYAN);
            sifatColoragainstBlack = prefs.getInt("sifatblack", Color.YELLOW);
            brokenPlurarColoragainstBlack = prefs.getInt("brokenblack", Color.GREEN);


        } else {
            shartagainstback=prefs.getInt("shartback",Color.DKGRAY);
            mudhafColoragainstBlack = prefs.getInt("mausoofwhite", 0xFF000000);
            mausofColoragainstBlack = prefs.getInt("mudhafwhite", 0xFF000000);
            sifatColoragainstBlack = prefs.getInt("sifatwhite", 0xFF000000);
            brokenPlurarColoragainstBlack = prefs.getInt("brokenwhite", 0xFF000000);


        }
        if (isFirstTime()) {

            Intent intents = new Intent(QuranGrammarAct.this, ActivitySettings.class);
            startActivity(intents);

        }

        getpreferences();
        chipNavigationBar = findViewById(R.id.bottom_nav_bar);
        tvsurah = findViewById(R.id.tvRukus);
        tvayah = findViewById(R.id.tvVerses);
        tvrukus = findViewById(R.id.tvSura);
        chipNavigationBar.setBackgroundColor(Color.LTGRAY);
        int selectedItemId = chipNavigationBar.getSelectedItemId();
        chipNavigationBar.setBackground(getResources().getDrawable(activatedbackgroundbrown));

        Resources.Theme theme = getTheme();
        try {
            int themeResource = getPackageManager().getActivityInfo(getComponentName(), 0).getThemeResource();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        chipNavigationBar.setOnItemSelectedListener(i -> {
            if (i == R.id.searchnav) {
                chipNavigationBar.setVisibility(View.GONE);
             //   Intent searchintent = new Intent(QuranGrammarAct.this, SearchActivity.class);
                 Intent searchintent = new Intent(QuranGrammarAct.this, SearchKeyBoardAct.class);
              //  Intent searchintent = new Intent(QuranGrammarAct.this, AjlihiQuranGrammarAct.class);

                startActivity(searchintent);
                chipNavigationBar.setItemSelected(R.id.searchnav,false);

            }
            if (i == R.id.settingnav) {
                chipNavigationBar.setVisibility(View.GONE);
                Intent intents = new Intent(QuranGrammarAct.this, ActivitySettings.class);
                startActivity(intents);
            } else if (i == R.id.surahnav) {
                chipNavigationBar.setVisibility(View.GONE);
                NewSurahDisplayFrag frag = new NewSurahDisplayFrag();


                FragmentTransaction transactions = getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transactions.replace(R.id.frame_container, frag).addToBackStack("mujarrad");
                transactions.commit();

            } else if (i == R.id.conjugationnav) {
                chipNavigationBar.setVisibility(View.GONE);
                //  Intent conjugatorintent = new Intent(newreadactivity.this, VerbConjugationAct.class);
                Intent conjugatorintent = new Intent(QuranGrammarAct.this, ConjugatorAct.class);
                //   Intent conjugatorintent = new Intent(newreadactivity.this, VerbConjugationAct.class);


            //    finish();
                startActivity(conjugatorintent);

            } else if (i == R.id.grammar) {
                chipNavigationBar.setVisibility(View.GONE);
                //  Intent conjugatorintent = new Intent(newreadactivity.this, VerbConjugationAct.class);
                Intent conjugatorintent = new Intent(QuranGrammarAct.this, GrammarRuleDetailHostActivity.class);
                //   Intent conjugatorintent = new Intent(newreadactivity.this, VerbConjugationAct.class);


                finish();
                startActivity(conjugatorintent);

            } else if (i == R.id.bookmarksnav) {
                chipNavigationBar.setVisibility(View.GONE);
                 BookmarkFragment bookmarkFragment = new BookmarkFragment();
            //  TameezDisplayFrag bookmarkFragment=new TameezDisplayFrag();
                FragmentTransaction transactions = getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transactions.replace(R.id.frame_container, bookmarkFragment).addToBackStack("mujarrad");
                transactions.commit();

            }
        });
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);


        mausoof = shared.getBoolean("mausoof", true);
        mudhaf = shared.getBoolean("mudhaf", true);
        harfnasb = shared.getBoolean("harfnasb", true);
        shart = shared.getBoolean("shart", true);
        kana = shared.getBoolean("kana", true);

        getpreferences();
        Intent bundle = getIntent();
        if (!(bundle.getExtras() == null)) {
            Bundle bundles = getIntent().getExtras();
            //   if (bundle != null) {
            String lastread = bundles.getString(QURAN_VERB_ROOT);
            if (null != lastread && lastread.equals("quran")) {
                getpreferences();
                setChapterorpart(true);
            } else {
                int chapter = bundle.getIntExtra(CHAPTER, 1);

                Utils util = new Utils(this);
                ArrayList<ChaptersAnaEntity> list = util.getAllAnaChapters();
                //    final boolean chapterorpartb = bundle.getBooleanExtra(CHAPTERORPART, true);
                initView();


                setChapterno(chapter);
                //  setChapterorpart(chapterorpartb);

                setSurahArabicName(list.get(chapter - 1).getAbjadname());
                //   setChapterno( bundle.etIntExtra(SURAH_ID,2));
                setVerse_no(bundle.getIntExtra(AYAH_ID, 1));
                setVersescount(list.get(chapter - 1).getVersescount());
                setIsMakkiMadani(list.get(chapter - 1).getIsmakki());
                setRukucount(list.get(chapter - 1).getRukucount());

                setUpOnCLicks();



                SetTranslation();
            }

        } else {
            initView();
//      tvHeader.setVisibility(View.GONE);
            NewSurahDisplayFrag frag = new NewSurahDisplayFrag();
            FragmentTransaction transactions = getSupportFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transactions.replace(R.id.frame_container, frag).addToBackStack("mujarrad");
            transactions.commit();


        }


    }

    private boolean isFirstTime() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {

            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.apply();
        }

        return !ranBefore;
    }

    private void getpreferences() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("lastread", MODE_PRIVATE);
        chapterno = pref.getInt(CHAPTER, 1);
        verse_no = pref.getInt(AYAH_ID, 1);
        surahname = pref.getString(SURAH_ARABIC_NAME, "");
        setSurahArabicName(surahname);


    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void setUpOnCLicks() {


    }


    public void initDialogComponents(int readposition) {
        QuranEntity quranEntity = allofQuran.get(readposition);

        Dialog jumpDialog;


        Spinner suraNames, verses;
        EditText surahIndex;

        Button ok;
        TextView ayahlabel;

        Utils util;

        List<String> sorasShow;


        //   jumpDialog = new Dialog(this,R.style.Base_Theme_AppCompat_Dialog);
        jumpDialog = new Dialog(this);

        jumpDialog.setContentView(R.layout.backupjumb_to_popup);
        suraNames = jumpDialog.findViewById(R.id.suras);
        verses = jumpDialog.findViewById(R.id.verses);
        util = new Utils(this);
        surahIndex = jumpDialog.findViewById(R.id.suraIndex);
        ayahIndex = jumpDialog.findViewById(R.id.ayahInput);
        ayahlabel = jumpDialog.findViewById(R.id.ayahlabel);

        jumpDialog.show();
        int maxLengthofEditText = 3;
        surahIndex.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLengthofEditText)});

        surahIndex.setFilters(new InputFilter[]{new InputFilterMinMax(1, 114)});
        ayahIndex.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLengthofEditText)});


        ok = jumpDialog.findViewById(R.id.ok);
        //    ArrayList<ChaptersAnaEntity> surahArray = utils.getSingleChapter(surah_id);

        int currentsurah=      quranEntity.getSurah();
        verseNumber=  quranEntity.getAyah();
        sorasShow = new ArrayList<>();
       // verseNumber = verse_no;
      //  int currentsurah = chapterno;
        int count = 0;
        soraList = util.getAllAnaChapters();

        for (ChaptersAnaEntity entity : soraList) {
            //  sorasShow.add(((++count) + " - " + (Locale.getDefault().getDisplayLanguage().equals("??????????????") ? entity.getNamearabic() : entity.getAbjadname()).replace("$$$", "'")));
            String english = entity.getNameenglish();
            String abjad = entity.getAbjadname();
            sorasShow.add(((++count) + " - " + english + "-" + abjad));

        }


        final String[] show = sorasShow.toArray(new String[0]);
        ArrayAdapter<String> adapter;
        ArrayAdapter<String> verseAdapter;

        adapter = new ArrayAdapter<>(this,
                R.layout.myspinner, show);
        verseAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout_larg, verse_no);

        suraNames.setAdapter(adapter);

        suraNames.setSelection(currentsurah - 1);
        setSurahArabicName(show[getCurrentSelectSurah()]);

        verses.setAdapter(verseAdapter);
        //   verses.setSelection(verseNumber);
        suraNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ChaptersAnaEntity sora;
                suraNumber = position + 1;

                sora = soraList.get(position);
                surahIndex.setInputType(suraNumber);


                ArrayAdapter<String> verseAdapter;
                int versesNumbers = suraNumber == 1 ? sora.versescount + 1 : sora.versescount;
                String[] numbers = new String[versesNumbers];
                for (int i = 1; i <= versesNumbers; i++) {
                    numbers[i - 1] = (i + "");
                }

                verseAdapter = new ArrayAdapter<>(QuranGrammarAct.this,
                        R.layout.spinner_layout_larg, numbers);

                verses.setAdapter(verseAdapter);
                if (verseNumber <= numbers.length) {
                    verses.setSelection(verseNumber - 1);
                } else {
                    verses.setSelection(numbers.length - 1);
                }
                ayahIndex.setFilters(new InputFilter[]{new InputFilterMinMax(1, numbers.length)});


                ayahlabel.setText("Max (" + (numbers.length - 1) + ")");
                surahIndex.setHint((suraNumber) + "");

             //   sora.getNamearabic();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        verses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                verseNumber = position + 1;
                ayahIndex.setInputType(verseNumber);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ok.setOnClickListener(view -> {

            if (!ayahIndex.getText().toString().trim().equals("")) {
                jumpDialog.dismiss();
            //    soraList.get(suraNumber).getAbjadname();

                setSurahArabicName(suraNumber + "-" + soraList.get(suraNumber - 1).getNameenglish() + "-" + soraList.get(suraNumber - 1).getAbjadname());
                setSurahArabicName(soraList.get(suraNumber - 1).getAbjadname());
                //  ayahIndex.getInputType();
                Editable text = ayahIndex.getText();
                setVerse_no(Integer.parseInt(String.valueOf(text)));

                setVersescount(soraList.get(suraNumber - 1).getVersescount());
                setIsMakkiMadani(soraList.get(suraNumber - 1).getIsmakki());
                setRukucount(soraList.get(suraNumber - 1).getRukucount());

                setCurrentSelectSurah(suraNumber);
                //  setVerse_no(verseNumber);
                setChapterno(suraNumber);
                parentRecyclerView = findViewById(R.id.overlayViewRecyclerView);
                if (currentSelectSurah == surah_id) {
                    parentRecyclerView.post(() -> parentRecyclerView.scrollToPosition(verse_no));


                } else {
                    jumptostatus = true;
                    setSurahorpart(currentSelectSurah);
                    setSurah_id(currentSelectSurah);
                    ExecuteSurahWordByWord();
                    //     asyncTaskcorpus = new refactoringcurrentSurahSyncWordByWord().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }


            } else if (surahIndex.getText().toString().trim().equals("") || surahIndex.getText() == null) {
                jumpDialog.dismiss();
           //     soraList.get(suraNumber).getAbjadname();

                setSurahArabicName(suraNumber + "-" + soraList.get(suraNumber - 1).getNameenglish() + "-" + soraList.get(suraNumber - 1).getAbjadname());
                setSurahArabicName(soraList.get(suraNumber - 1).getAbjadname());
                setVerse_no(verseNumber);
                setVersescount(soraList.get(suraNumber - 1).getVersescount());
                setIsMakkiMadani(soraList.get(suraNumber - 1).getIsmakki());
                setRukucount(soraList.get(suraNumber - 1).getRukucount());

                setCurrentSelectSurah(suraNumber);

                setChapterno(suraNumber);
                parentRecyclerView = findViewById(R.id.overlayViewRecyclerView);
                if (currentSelectSurah == surah_id) {
                    parentRecyclerView.post(() -> parentRecyclerView.scrollToPosition(verse_no));


                } else {
                    jumptostatus = true;
                    setSurahorpart(currentSelectSurah);
                    setSurah_id(currentSelectSurah);
                    ExecuteSurahWordByWord();
                    //     asyncTaskcorpus = new refactoringcurrentSurahSyncWordByWord().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }


            }

        });


    }


    private void SetTranslation() {


        //     allofQuran = Utils.getQuranbySurah(chapterno);
        shared.getBoolean("prefs_show_erab", true);


        ExecuteSurahWordByWord();


    }


    public void ExecuteSurahWordByWord() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        AlertDialog dialog = builder.create();
        corpusayahWordArrayList = new ArrayList<>();
        mafoolbihiwords =new ArrayList<>();
        Jumlahaliya=new ArrayList<>();
       Tammezent=new ArrayList<>();
       Liajlihient=new ArrayList<>();
        Jumlahaliya=utils.getHaliaErabBysurah(chapterno);
        Liajlihient=   utils.getMafoolLiajlihisurah(chapterno);
      //  mafoolbihiwords =utils.getMafoolBihiErabSurah(chapterno);
        mafoolbihiwords =    utils.getMafoolBySurah(chapterno);
        Tammezent=   utils.getTameezsurah(chapterno);
        Mutlaqent=utils.getMutlaqsurah(chapterno);
        BadalErabNotesEnt=utils.getBadalrabSurah(chapterno);
        ExecutorService ex = Executors.newSingleThreadExecutor();

        ex.execute(() -> {
            //do inbackground

         QuranGrammarAct.this.bysurah(dialog, ex);
        

        });


    }




    private void bysurah(AlertDialog dialog, ExecutorService ex) {


        runOnUiThread(dialog::show);


        int versesnumbers;

        versesnumbers = getVersescount();

        ArrayList<CorpusExpandWbwPOJO> wbw = utils.getCorpusWbwBySurah(chapterno);
      //  ArrayList<MafoolBihi> mafoolbihiquran = utils.getMafoolbihiquran();
        int verseglobal = 0;
        int tempVerseWord;
        int verseexit = wbw.size();

        for (int indexv = 1; indexv <= versesnumbers; indexv++) {
            tempVerseWord = indexv;
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

                    StringBuilder sb = new StringBuilder();
                    sb.append(wbw.get(verseglobal).getAraone()).append(wbw.get(verseglobal).getAratwo());
                    CharSequence sequence = concat(wbw.get(verseglobal).getAraone() + wbw.get(verseglobal).getAratwo() +
                            wbw.get(verseglobal).getArathree() + wbw.get(verseglobal).getArafour());
                    //   Object o4 = wbw.get(verseglobal).getWord();
                    Object en = wbw.get(verseglobal).getEn();
                    Object bn = wbw.get(verseglobal).getBn();
                    Object ind = wbw.get(verseglobal).getIn();
                    String ur = wbw.get(verseglobal).getUr();

                    word.setRootword(wbw.get(verseglobal).getRoot_a());
                    word.setSurahId(wbw.get(verseglobal).getSurah());
                    word.setVerseId(wbw.get(verseglobal).getAyah());
                    word.setWordno(wbw.get(verseglobal).getWordno());
                    word.setWordcount(wbw.get(verseglobal).getWordcount());

                    word.setWordsAr(sequence.toString());
                    //  word.setWordindex(getIndex(wbw.get(verseglobal).getQuranverses()));
                    word.setTranslateEn(en.toString());
                    word.setTranslateBn(bn.toString());
                    word.setTranslateIndo(ind.toString());
                    word.setTranslationUrdu(ur);

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


        CorpusUtilityorig corpus = new CorpusUtilityorig(this);


        //      corpus.highLightVerbs(corpusayahWordArrayList,surah_id);
        if (kana) {
            corpus.setKana(corpusayahWordArrayList, chapterno);

        }
        if (shart) {
            corpus.setShart(corpusayahWordArrayList, chapterno);
        }

        if (mudhaf) {

            corpus.setMudhafFromDB(corpusayahWordArrayList, chapterno);

        }
        if (mausoof) {
            corpus.SetMousufSifaDB(corpusayahWordArrayList, chapterno);
          //  corpus.NewMAOUSOOFSIFA(corpusayahWordArrayList);

        }

        if (harfnasb) {

            corpus.newnewHarfNasbDb(corpusayahWordArrayList, chapterno);
        }
        //     corpus.highLightVerbs(corpusayahWordArrayList,surah_id);


        //post
        runOnUiThread(() -> {
            dialog.dismiss();
            ex.shutdown();

            parentRecyclerView = findViewById(R.id.overlayViewRecyclerView);

            allofQuran = Utils.getQuranbySurah(chapterno);


            if (jumptostatus) {


                setSurahorpart(chapterno);

            }
            OnItemClickListenerOnLong listener = this;
            ArrayList<String> header = new ArrayList<>();
            header.add(String.valueOf(getRukucount()));
            header.add(String.valueOf(getVersescount()));
            header.add(String.valueOf(getChapterno()));
            header.add(getSurahArabicName());


             HightLightKeyWord();

       //   GetSelectedPhrases();
         //  FileUtility fl=new FileUtility(this);
        // fl.writetofile("mafoolraw.csv",det);
   // flowAyahWordAdapter = new UpdateMafoolFlowAyahWordAdapter(header, allofQuran, corpusayahWordArrayList, QuranGrammarAct.this, surah_id, surahArabicName, isMakkiMadani, listener);

  flowAyahWordAdapter = new FlowAyahWordAdapter(Mutlaqent, Tammezent, BadalErabNotesEnt, Liajlihient, Jumlahaliya, mafoolbihiwords,header, allofQuran, corpusayahWordArrayList, QuranGrammarAct.this, surah_id, surahArabicName, isMakkiMadani, listener);

            flowAyahWordAdapter.addContext(QuranGrammarAct.this);

            parentRecyclerView.setHasFixedSize(true);

            parentRecyclerView.setAdapter(flowAyahWordAdapter);
            flowAyahWordAdapter.notifyDataSetChanged();
            parentRecyclerView.post(() -> parentRecyclerView.scrollToPosition(verse_no));


        });
    }

    private void GetSelectedPhrases() {
        List<QuranEntity> quran = Utils.getQuran();

        ArrayList<String> ajilihi=new ArrayList();
       // ajilihi.add("?????????? ??????????");
        ajilihi.add("??????????");
       // ajilihi.add("??????????.");
        ArrayList<String> tameez=new ArrayList();
        tameez.add("??????????");
        tameez.add("??????????.");
        tameez.add("??????????????");
        tameez.add("??????????:");
        ArrayList<String> mafoolbihi=new ArrayList();
       mafoolbihi.add("?????????? ????");
        mafoolbihi.add("?????????? ????.");
        mafoolbihi.add("???????????? ????.");
        mafoolbihi.add("???????????? ????");
        ArrayList<String> hal=new ArrayList();
        ArrayList<String> halnew=new ArrayList();
        halnew.add("???? ?????? ?????? ??????");
        halnew.add("???? ?????? ?????? ??????.");
        halnew.add("?????????????? ??????????");
        halnew.add("?????????????? ??????????.");
        hal.add("??????????");
        hal.add("??????????.");
        hal.add("??????????:");
        hal.add("??????");
        hal.add("??????:");
        hal.add("??????.");
        hal.add("?????????? ??????????");
        ArrayList<String> badal=new ArrayList();
        badal.add("??????");
        badal.add("??????.");
        ArrayList<String> mutlaq=new ArrayList();
        mutlaq.add("????????????  ????????");
        mutlaq.add("???????????? ??????????");
        mutlaq.add("???????????? ????????????");
        mutlaq.add("????????.");  mutlaq.add("");
        Trie ajilihitrie = Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(mafoolbihi).build();
   //     Collection<Emit> ajilihiemit = ajilihitrie.parseText(ar_irab_two);
        for (QuranEntity quranEntity : quran) {
            Collection<Emit> ajilihiemit = ajilihitrie.parseText(quranEntity.getAr_irab_two());


            for (Emit emit : ajilihiemit) {
                String sbs = quranEntity.getSurah() + "|" +
                        quranEntity.getAyah() + "|" +
                        emit.getStart() + "|" +
                        emit.getEnd() + "|" + emit.getKeyword();

               det.add(sbs);


            }

        }



    }

    private void HightLightKeyWord() {

      String inshartiastr="???????????? ??????????";
        String izazarfshartsrt="???????? ?????? ?????????? ???????? ??????????";
        String izashartiastr="???????????? ?????? ?????????? ???????? ??????????";
        String jawabshartstr="???????? ??????";
        String jawabsharttwostr="?????????? ??????????";
        String jawabalshart="???????? ??????????";
        String jawab="????????" ;
        ArrayList<String> shart=new ArrayList();
        ArrayList<String> mutlaq=new ArrayList();
        mutlaq.add("????????");
        mutlaq.add("???????????? ??????????");
        mutlaq.add("???????????? ????????????");
        mutlaq.add("????????.");  mutlaq.add("");

        shart.add(inshartiastr);
        shart.add(izazarfshartsrt);
        shart.add(izashartiastr);
        shart.add(jawabshartstr);
        shart.add(jawabsharttwostr);
        shart.add(jawabalshart);
        shart.add(jawab);
        shart.add("??????????");
        shart.add("??????????.");
        shart.add("?????? ?????????? ???????? ??????????");
        shart.add("???????? ?????? ???????? ?????????? ???????? ??????????");
        shart.add("?????? ???????? ?????????? ???????? ??????????");
        shart.add("?????? ?????? ?????? ?????? ????????");
        shart.add("?????? ?????? ?????? ????????");
        shart.add("?????????? ?????????? ???? ???????? ????");
        shart.add("?????? ?????? ????????");
        shart.add("??????????????");


        String mudhafilahistr = "???????? ????????";
        String sifastr = "??????";

        int mudhaflenght = mudhafilahistr.length();
        int sifalength = sifastr.length();
        ArrayList<String> hal=new ArrayList();




        hal.add("???? ?????? ?????? ??????");
        hal.add("???? ?????? ?????? ??????.");
        hal.add("?????????????? ??????????");
        hal.add("?????????????? ??????????.");
        hal.add("??????????");
        hal.add("??????????.");
        hal.add("??????????:");
        hal.add("??????");
        hal.add("??????:");
        hal.add("??????.");
        hal.add("?????????? ??????????");
        ArrayList<String> tameez=new ArrayList();
        tameez.add("??????????");
        tameez.add("??????????.");
        tameez.add("??????????????");
        ArrayList<String> badal=new ArrayList();
        badal.add("??????");
        badal.add("??????.");


        ArrayList<String> ajilihi=new ArrayList();
        ajilihi.add("?????????? ??????????");
        ajilihi.add("??????????");
        ajilihi.add("??????????.");
        ArrayList<String> mafoolbihi=new ArrayList();
        mafoolbihi.add("?????????? ????");
        mafoolbihi.add("?????????? ????.");
        mafoolbihi.add("?????????? ????.(");
        mafoolbihi.add("???? ?????? ?????? ??????????");
        mafoolbihi.add("??????????");
        for (QuranEntity pojo : allofQuran) {
          //  String ar_irab_two = pojo.getAr_irab_two();
            String ar_irab_two = pojo.getAr_irab_two();
            SpannableStringBuilder str = new SpannableStringBuilder(ar_irab_two);

            Trie mudhaftrie = Trie.builder().onlyWholeWords().addKeywords(mudhafilahistr).build();
            Collection<Emit> mudhafemit = mudhaftrie.parseText(ar_irab_two);

            Trie sifatrie = Trie.builder().onlyWholeWords().addKeywords(sifastr).build();
            Collection<Emit> sifaemit = sifatrie.parseText(ar_irab_two);




            Trie jawabsharttwotrie = Trie.builder().onlyWholeWords().addKeywords(jawabsharttwostr).build();
            Collection<Emit> jawabsharttwoemit = jawabsharttwotrie.parseText(ar_irab_two);

            Trie trieBuilder = Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(shart).build();
            Collection<Emit> emits = trieBuilder.parseText(ar_irab_two);

            Trie haltrie = Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(hal).build();
            Collection<Emit> halemits = haltrie.parseText(ar_irab_two);
            Trie tameeztrie = Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(tameez).build();
            Collection<Emit> tameezemit = tameeztrie.parseText(ar_irab_two);

            Trie badaltrie = Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(badal).build();
            Collection<Emit> badalemit = badaltrie.parseText(ar_irab_two);






            Trie ajilihitrie = Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(ajilihi).build();
            Collection<Emit> ajilihiemit = ajilihitrie.parseText(ar_irab_two);


            Trie mafoolbihitri = Trie.builder().onlyWholeWordsWhiteSpaceSeparated().addKeywords(mafoolbihi).build();
            Collection<Emit> mafoolbihiemit = mafoolbihitri.parseText(ar_irab_two);

            for (Emit emit : mafoolbihiemit) {


                str.setSpan(new ForegroundColorSpan(sifatColoragainstBlack),
                        emit.getStart(),
                        emit.getStart() + emit.getKeyword().length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            }

            for (Emit emit : ajilihiemit) {


                str.setSpan(new ForegroundColorSpan(sifatColoragainstBlack),
                        emit.getStart(),
                        emit.getStart() + emit.getKeyword().length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            }

            for (Emit emit : tameezemit) {


                str.setSpan(new ForegroundColorSpan(sifatColoragainstBlack),
                        emit.getStart(),
                        emit.getStart() + emit.getKeyword().length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            }
            for (Emit emit : badalemit) {


                str.setSpan(new ForegroundColorSpan(sifatColoragainstBlack),
                        emit.getStart(),
                        emit.getStart() + emit.getKeyword().length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            }

            for (Emit emit : halemits) {


                str.setSpan(new ForegroundColorSpan(shartagainstback),
                        emit.getStart(),
                        emit.getStart() + emit.getKeyword().length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            }

            for (Emit emit : emits) {

               
                str.setSpan(new ForegroundColorSpan(shartagainstback),
                        emit.getStart(),
                        emit.getStart() + emit.getKeyword().length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            }
 
            for (Emit emit : mudhafemit) {


                str.setSpan(new ForegroundColorSpan(mausofColoragainstBlack),
                        emit.getStart(),
                        emit.getStart() + mudhaflenght,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            }

            for (Emit emit : sifaemit) {
                

                str.setSpan(new ForegroundColorSpan(mudhafColoragainstBlack),
                        emit.getStart(),
                        emit.getStart() + sifalength,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            }
            //    colorerab.get(0).setErabspnabble(str);

            pojo.setErabspnabble(str);


        }
    }


    private void LoadItemList(Bundle dataBundle, QuranEntity word) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);


        GrammerFragmentsBottomSheet item = new GrammerFragmentsBottomSheet();

        FragmentManager fragmentManager = getSupportFragmentManager();

        item.setArguments(dataBundle);
        String[] data = {String.valueOf(word.getSurah()), String.valueOf(word.getAyah()), word.getTranslation(), String.valueOf((1))};
        FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
        transactions.show(item);

        GrammerFragmentsBottomSheet.newInstance(data).show(getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);

    }


    @Override
    public void onItemClick(View view, int position) {
        Object tag = view.getTag();
        QuranEntity quranEntity;
        View overflow = view.findViewById(R.id.ivActionOverflow);
        try{
              quranEntity = allofQuran.get(position);
        }catch (IndexOutOfBoundsException e){
              quranEntity = allofQuran.get(position-1);
        }



       SwitchCompat colorsentence = view.findViewById(R.id.colorized);
        boolean colortag = shared.getBoolean("colortag", true);

        View qurantext = view.findViewById(R.id.quran_textView);
        if (tag.equals("arrowforward")) {
            int currentsurah=      quranEntity.getSurah();
            if(currentsurah!=114) {
                soraList = utils.getSingleChapter(currentsurah + 1);


                verseNumber = quranEntity.getAyah();


                final Intent intent = getIntent().putExtra("chapter", soraList.get(0).getChapterid()).putExtra("chapterorpart", chapterorpart).
                        putExtra(SURAH_ARABIC_NAME, soraList.get(0).abjadname)
                        .putExtra(VERSESCOUNT, soraList.get(0).getVersescount()).putExtra(RUKUCOUNT, soraList.get(0).getRukucount()).putExtra(MAKKI_MADANI, soraList.get(0).getIsmakki());
                overridePendingTransition(0, 0);

                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            }
        }else  if (tag.equals("arrowback")) {
            int currentsurah=      quranEntity.getSurah();
            if(currentsurah!=1){
                soraList = utils.getSingleChapter(currentsurah-1);



            verseNumber=  quranEntity.getAyah();


            final Intent intent = getIntent().putExtra("chapter", soraList.get(0).getChapterid()).putExtra("chapterorpart", chapterorpart).
                    putExtra(SURAH_ARABIC_NAME, soraList.get(0).abjadname)
                    .putExtra(VERSESCOUNT, soraList.get(0).getVersescount()).putExtra(RUKUCOUNT, soraList.get(0).getRukucount()).putExtra(MAKKI_MADANI, soraList.get(0).getIsmakki());
            overridePendingTransition(0, 0);

            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            }
        }else     if (tag.equals("colorize")) {
            if (colortag) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(QuranGrammarAct.this).edit();
                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putBoolean("colortag", false);

                editor.apply();

                RefreshActivity(colorsentence);

            } else {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(QuranGrammarAct.this).edit();
                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putBoolean("colortag", true);

                editor.apply();

               RefreshActivity(colorsentence);



            }
        } else if (tag.equals("bookmark")) {
            bookMarkSelected();

        } else if (tag.equals("jumpto")) {
            initDialogComponents(position);
        } else if (tag.equals("overflow_img")) {

            boolean issentencesanalysis = shared.getBoolean("grammarsentence", true);

            AlertDialog.Builder builder = new AlertDialog.Builder(QuranGrammarAct.this);
            LayoutInflater factory = LayoutInflater.from(QuranGrammarAct.this);
            view = factory.inflate(R.layout.popup_layout, null);
            View fopen = view.findViewById(R.id.fileopen);
            View bookmarkview = view.findViewById(R.id.bookmark);
            SwitchCompat colorized = view.findViewById(R.id.colorized);
            View helpview = view.findViewById(R.id.help);

            builder.setView(view);
            colorized.setChecked(colortag);

            //  sentencesana.setChecked(issentencesanalysis);


            builder.setNeutralButton("Exit", (dlg, sumthin) -> {


            });
            int[] location = new int[2];
            overflow.getLocationOnScreen(location);

            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.setTitle("my dialog");
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
            wmlp.x = location[0];   //x position
            wmlp.y = location[1];  //y position

            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //style id

            dialog.show();
            helpview.setOnClickListener(v -> {
                ParticleColorScheme item = new ParticleColorScheme();
                //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
                FragmentManager fragmentManager = QuranGrammarAct.this.getSupportFragmentManager();
                String sample = "???????????? ?????????????? ???????????????????????? ????????????????????";

                String[] data = {sample, sample, sample};
                FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
                //   transactions.show(item);
                ParticleColorScheme.newInstance(data).show(QuranGrammarAct.this.getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);

            });

            fopen.setOnClickListener(view12 -> {
                initDialogComponents(position);
                dialog.dismiss();
            });
            bookmarkview.setOnClickListener(view1 -> {
                bookMarkSelected();
                dialog.dismiss();
            });

            colorized.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //colortag

                    if (colortag) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(QuranGrammarAct.this).edit();
                        //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                        editor.putBoolean("colortag", false);

                        editor.apply();
                        RefreshActivity();
                    } else {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(QuranGrammarAct.this).edit();
                        //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                        editor.putBoolean("colortag", true);

                        editor.apply();
                        RefreshActivity();
                    }

                    dialog.dismiss();
                }

                private void RefreshActivity() {
                    Log.e(TAG, "onClick called");
                    final Intent intent = getIntent().putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(SURAH_ARABIC_NAME, surahArabicName)
                            .putExtra(VERSESCOUNT, getVersescount()).putExtra(RUKUCOUNT, rukucount).putExtra(MAKKI_MADANI, isMakkiMadani);
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                }
            });

            System.out.println("check");
        } else if (tag.equals("help_img")) {
            System.out.println("check");
            ParticleColorScheme item = new ParticleColorScheme();
            //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
            FragmentManager fragmentManager = QuranGrammarAct.this.getSupportFragmentManager();
            String sample = "???????????? ?????????????? ???????????????????????? ????????????????????";

            String[] data = {sample, sample, sample};
            FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
            //   transactions.show(item);
            ParticleColorScheme.newInstance(data).show(QuranGrammarAct.this.getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);
        }

        //   if (overflow != null) {
        //  popupMenu(overflow);
        //  popupWindow(overflow);
        //    }  else
        else if (tag.equals("qurantext")) {
            QuranEntity word ;
            if (position != 0) {
                word = allofQuran.get(position - 1);
            } else {
                word = allofQuran.get(position);

            }

            Bundle dataBundle = new Bundle();
            dataBundle.putInt(SURAH_ID, word.getSurah());
            dataBundle.putInt(AYAHNUMBER, Math.toIntExact(word.getAyah()));

            LoadItemList(dataBundle, word);
        }


    }

    private void bookMarkSelected() {
        int position;
        position = flowAyahWordAdapter.getAdapterposition();
        int chapter_no = corpusayahWordArrayList.get(position).getWord().get(0).getSurahId();
        int verse = corpusayahWordArrayList.get(position).getWord().get(0).getVerseId();


        BookMarks en = new BookMarks();


        en.setChapterno(String.valueOf(chapter_no));
        en.setVerseno(String.valueOf(verse));
        en.setSurahname(getSurahArabicName());
        //     Utils utils = new Utils(ReadingSurahPartActivity.this);
        utils.insertBookMark(en);

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "BookMark Created", Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.BLUE);
        snackbar.setTextColor(Color.CYAN);
        snackbar.setBackgroundTint(Color.BLACK);
        snackbar.show();
    }


    private void popupWindow(View overflow) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View popupView = layoutInflater.inflate(R.layout.popup_filter_layout, null);
        SwitchCompat colorSwitch = popupView.findViewById(R.id.mySwitch);
        final View jumpto = popupView.findViewById(R.id.jumpto);
        View bookmarview = popupView.findViewById(R.id.bookmark);
        boolean colortag = shared.getBoolean("colortag", true);

        SwitchCompat colorized = popupView.findViewById(R.id.mySwitch);
        colorized.setChecked(colortag);
        colorSwitch.setOnClickListener(v -> {

            //   Toast.makeText(QuranGrammarAct.this, "You Clicked " , Toast.LENGTH_SHORT).show();
            boolean colortag1 = shared.getBoolean("colortag", true);
            boolean issentencesanalysis = shared.getBoolean("grammarsentence", true);
            if (colortag1) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(QuranGrammarAct.this).edit();
                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putBoolean("colortag", false);

                editor.apply();
           //     RefreshActivity(colorsentence);
            } else {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(QuranGrammarAct.this).edit();
                //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                editor.putBoolean("colortag", true);

                editor.apply();
           //     RefreshActivity(colorsentence);
            }

        });
        PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(() -> {
            //TODO do sth here on dismiss
        });

        popupWindow.showAsDropDown(overflow);

    }

    private void popupMenu(View overflow) {

        PopupMenu popupMenu = new PopupMenu(this, overflow);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
        SwitchCompat switcher;
        boolean isChecked = false;
        SwitchCompat colorSwitch = overflow.findViewById(R.id.colorized);

        Object menuHelper;


        Class[] argTypes;
        try {
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popupMenu);
            argTypes = new Class[]{boolean.class};
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    popupMenuAction(menuItem.getItemId());


                    //      Toast.makeText(QuranGrammarAct.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                    return true;
                }

                private void popupMenuAction(int itemId) {


                    if (itemId == R.id.jumpto) {
                        initDialogComponents(2);

                    } else if (itemId == R.id.bookmark) {
                        bookMarkSelected();


                    } else if (itemId == R.id.colorized) {
                        boolean colortag = shared.getBoolean("colortag", true);
                        boolean issentencesanalysis = shared.getBoolean("grammarsentence", true);
                        if (colortag) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(QuranGrammarAct.this).edit();
                            //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                            editor.putBoolean("colortag", false);

                            editor.apply();
                       //     RefreshActivity(colorsentence);
                        } else {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(QuranGrammarAct.this).edit();
                            //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                            editor.putBoolean("colortag", true);

                            editor.apply();
                       //     RefreshActivity(colorsentence);
                        }
                    }


                }
            });

        } catch (Exception e) {

        }

        popupMenu.show();
    }

    private void RefreshActivity(SwitchCompat colorsentence) {
        Log.e(TAG, "onClick called");
        final Intent intent = getIntent().putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(SURAH_ARABIC_NAME, surahArabicName)
                .putExtra(VERSESCOUNT, getVersescount()).putExtra(RUKUCOUNT, rukucount).putExtra(MAKKI_MADANI, isMakkiMadani);
        overridePendingTransition(0, 0);

        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
      //  colorsentence.setOnCheckedChangeListener (null);
      //  colorsentence.setChecked(true);
    }

    @Override
    public void onItemLongClick(int position, View v) {
        //    Toast.makeText(this, "longclick", Toast.LENGTH_SHORT).show();

    }

    private void initView() {
        utils = new Utils(getApplication());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());


        btnBottomSheet = findViewById(R.id.fab);
        layoutBottomSheet = findViewById(R.id.bottom_sheet);
        //    btnBottomSheet=findViewById(R.id.btn_bottom_sheet);


        tvsurah = findViewById(R.id.tvRukus);
        tvayah = findViewById(R.id.tvVerses);
        tvrukus = findViewById(R.id.tvSura);


        coordinatorLayout = findViewById(R.id.coordinatorLayout);


        RecyclerView verlayViewRecyclerView = findViewById(R.id.overlayViewRecyclerView);

        verlayViewRecyclerView.setLayoutManager(linearLayoutManager);


        // bookmarkchip.setOnClickListener(v -> CheckStringLENGTHS());


        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        btnBottomSheet.setOnClickListener(v -> {
            //     toggleBottomSheet();
            toggleHideSeek();
        });
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        //       btnBottomSheet.setText("Close Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        //      btnBottomSheet.setText("Expand Sheet");
                    }
                    break;
                    default:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void toggleHideSeek() {
        //  chipNavigationBar
        int visibility = chipNavigationBar.getVisibility();
        if (visibility == 0) {
            chipNavigationBar.setVisibility(View.GONE);
        }
        if (visibility == 8) {
            chipNavigationBar.setVisibility(View.VISIBLE);
        }

    }

    public void toggleBottomSheet() {
        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            //    btnBottomSheet.setText("Close sheet");
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            //    btnBottomSheet.setText("Expand sheet");
        }
    }

    @Override
    public void ondatarecevied(int chapterno, String partname, int totalverses, int rukucount, int makkimadani) {


        Log.e(TAG, "onClick called");
        final Intent intent = getIntent().putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(SURAH_ARABIC_NAME, partname)
                .putExtra(VERSESCOUNT, totalverses).putExtra(RUKUCOUNT, rukucount).putExtra(MAKKI_MADANI, makkimadani);


        overridePendingTransition(0, 0);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);

    }


    public interface IOnBackPressed {
        /**
         * If you return true the back press will not be taken into account, otherwise the activity will act naturally
         *
         * @return true if your processing has priority if not false
         */
        boolean onBackPressed();
    }


}


/*
     boolean colortag = shared.getBoolean("colortag", true);
            boolean issentencesanalysis = shared.getBoolean("grammarsentence", true);

            AlertDialog.Builder builder = new AlertDialog.Builder(newreadactivity.this);
            LayoutInflater factory = LayoutInflater.from(newreadactivity.this);
            view = factory.inflate(R.layout.popup_layout, null);
            View fopen = view.findViewById(R.id.fileopen);
            View bookmarkview = view.findViewById(R.id.bookmark);
            SwitchCompat colorized = view.findViewById(R.id.colorized);
            SwitchCompat sentencesana = view.findViewById(R.id.sentences);
            View gotobookmarsk = view.findViewById(R.id.gotobookmarks);

            builder.setView(view);
            colorized.setChecked(colortag);

            sentencesana.setChecked(issentencesanalysis);


            builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dlg, int sumthin) {


                }
            });
            int[] location = new int[2];
            overflow.getLocationOnScreen(location);

            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.setTitle("my dialog");
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
            wmlp.gravity = Gravity.TOP | Gravity.LEFT;
            wmlp.x = location[0];   //x position
            wmlp.y = location[1];  //y position

            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //style id

            dialog.show();
            gotobookmarsk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BookmarkFragment bookmarkFragment = new BookmarkFragment();

                    FragmentTransaction transactions = getSupportFragmentManager().beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transactions.replace(R.id.frame_container, bookmarkFragment).addToBackStack("mujarrad");
                    transactions.commit();
                    dialog.dismiss();
                }
            });
            fopen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initDialogComponents();
                    dialog.dismiss();
                }
            });
            bookmarkview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bookMarkSelected();
                    dialog.dismiss();
                }
            });

            colorized.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //colortag

                    if (colortag) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(newreadactivity.this).edit();
                        //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                        editor.putBoolean("colortag", false);

                        editor.apply();
                        RefreshActivity();
                    } else {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(newreadactivity.this).edit();
                        //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                        editor.putBoolean("colortag", true);

                        editor.apply();
                        RefreshActivity();
                    }

                    dialog.dismiss();
                }

                private void RefreshActivity() {
                    Log.e(TAG, "onClick called");
                    final Intent intent = getIntent().putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(SURAH_ARABIC_NAME, partname)
                            .putExtra(VERSESCOUNT, getVersescount()).putExtra(RUKUCOUNT, rukucount).putExtra(MAKKI_MADANI, isMakkiMadani);
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                }
            });
            sentencesana.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //colortag

                    if (issentencesanalysis) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(newreadactivity.this).edit();
                        //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                        editor.putBoolean("grammarsentence", false);

                        editor.apply();
                    } else {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(newreadactivity.this).edit();
                        //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
                        editor.putBoolean("grammarsentence", true);

                        editor.apply();

                    }

                    dialog.dismiss();
                }
            });

        } else if (jumptosurah != null) {
            initDialogComponents();

        } else if (bookmark != null) {
            bookMarkSelected();

        } else if (qurantext != null) {
            QuranEntity word = allofQuran.get(position);
            Bundle dataBundle = new Bundle();
            dataBundle.putInt(SURAH_ID, word.getSurah());
            dataBundle.putInt(AYAHNUMBER, Math.toIntExact(word.getAyah()));

            LoadItemList(dataBundle, word);
        }
 */

