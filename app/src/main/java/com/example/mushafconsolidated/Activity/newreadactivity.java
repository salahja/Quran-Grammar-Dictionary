package com.example.mushafconsolidated.Activity;


import static android.text.TextUtils.concat;
import static com.example.Constant.AYAHNUMBER;
import static com.example.Constant.CHAPTER;
import static com.example.Constant.CHAPTERORPART;
import static com.example.Constant.MAKKI_MADANI;
import static com.example.Constant.QURAN_VERB_ROOT;
import static com.example.Constant.RUKUCOUNT;
import static com.example.Constant.SURAH_ARABIC_NAME;
import static com.example.Constant.SURAH_ID;
import static com.example.Constant.VERSESCOUNT;




import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SwitchCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JustJava.InputFilterMinMax;
import com.example.mushafconsolidated.Adapters.CustomAdapter;
import com.example.mushafconsolidated.Adapters.HarbNasbMarkupFlowAyahWordAdapter;
import com.example.mushafconsolidated.Adapters.OrignalFlowAyahWordAdapter;
import com.example.mushafconsolidated.Entities.BookMarks;
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity;
import com.example.mushafconsolidated.Entities.CorpusExpandWbwPOJO;
import com.example.mushafconsolidated.Entities.QuranEntity;
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
import com.example.utility.SharedPref;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.shreyaspatil.MaterialDialog.MaterialDialog;

import org.sj.conjugator.activity.ConjugatorAct;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;

public class newreadactivity extends BaseActivity implements PassdataInterface, OnItemClickListenerOnLong {
  ChipNavigationBar chipNavigationBar;
  FloatingActionButton btnBottomSheet;
  private OrignalFlowAyahWordAdapter flowAyahWordAdapter;



  String partname;
  boolean jumptostatus = false;
  int surah_id = 0;
  private boolean mausoof, mudhaf, harfnasb, shart;
  private ArrayList<ChaptersAnaEntity> soraList;
  private EditText ayahIndex;

  public int getRukucount() {
    return rukucount;
  }

  public void setRukucount(int rukucount) {
    this.rukucount = rukucount;
  }

  int verseNumber, suraNumber;

  int rukucount,totalverses;
  String surahname;

  private boolean kana;
  private List<QuranEntity> allofQuran;
  private ArrayList<QuranEntity> colorerab;
  private SharedPreferences shared;
  // --Commented out by Inspection (14/08/21, 7:26 PM):ChipNavigationBar chipNavigationBar;

  public void setSurahorpart(int surahorpart) {
    this.surahorpart = surahorpart;
  }



  int surahorpart = 0;


  private ArrayList<CorpusAyahWord> corpusayahWordArrayList;

  Button settingchip, bookmarkchip, conjugatorchip, searchchip, shartchip;
  TextView tvsurah, tvayah, tvrukus;
  int currentSelectSurah;
  RelativeLayout layoutBottomSheet;

  public int getCurrentSelectSurah() {
    return currentSelectSurah;
  }

  public void setCurrentSelectSurah(int currentSelectSurah) {
    this.currentSelectSurah = currentSelectSurah;
  }

  RelativeLayout surapartview;

  BottomSheetBehavior sheetBehavior;


  //  private OnClickListener onClickListener;




  private Utils utils;



  public String getPartname() {
    return partname;
  }

  public void setPartname(String partname) {
    this.partname = partname;
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

  public String getSuraharabicname() {
    return suraharabicname;
  }

  public void setSuraharabicname(String suraharabicname) {
    this.suraharabicname = suraharabicname;
  }

  private int isMakkiMadani;
  int versescount = 0;

  public void setIsMakkiMadani(int isMakkiMadani) {
    this.isMakkiMadani = isMakkiMadani;
  }

  boolean chapterorpart;
  int verse_no = 0;


  public void setChapterorpart(boolean chapterorpart) {
    this.chapterorpart = chapterorpart;
  }

  String suraharabicname;
  private static final String TAG = "fragment";


  final boolean isPartfragmentshowing = false;




  private int chapterno;


  CoordinatorLayout coordinatorLayout;
  private RecyclerView parentRecyclerView;


  public int getChapterno() {
    return chapterno;
  }

  public void setChapterno(int chapterno) {
    this.chapterno = chapterno;
  }


  @Override
  public void onBackPressed() {
    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
    if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
      super.onBackPressed();
    }

    //  finish();


  }


  @RequiresApi(api = Build.VERSION_CODES.Q)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    shared =
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(newreadactivity.this);

    String preferences = shared.getString("themePref", "dark");
    if (preferences.equals("white")) {
      switchTheme("white");
    } else if (preferences.equals("dark")) {
      switchTheme("dark");

    } else if (preferences.equals("blue")) {
      switchTheme("blue");


    } else if (preferences.equals("green")) {
      switchTheme("green");


    } else if (preferences.equals("brwon")) {
      switchTheme("brown");


    }
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.new_fragment_reading);

    if (isFirstTime()) {

      Intent intents = new Intent(newreadactivity.this, ActivitySettings.class);
      startActivity(intents);

    }

    getpreferences();
    chipNavigationBar = findViewById(R.id.bottom_nav_bar);
    tvsurah = findViewById(R.id.tvRukus);
    tvayah = findViewById(R.id.tvVerses);
    tvrukus = findViewById(R.id.tvSura);
    int selectedItemId = chipNavigationBar.getSelectedItemId();

    chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
      @Override
      public void onItemSelected(int i) {

        if(i==R.id.settingnav){
          chipNavigationBar.setVisibility(View.GONE);
          Intent intents = new Intent(newreadactivity.this, ActivitySettings.class);
          startActivity(intents);
        }else if(i==R.id.surahnav){
          chipNavigationBar.setVisibility(View.GONE);
          NewSurahDisplayFrag frag = new NewSurahDisplayFrag();






          FragmentTransaction transactions = getSupportFragmentManager().beginTransaction()
                  .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
          transactions.replace(R.id.frame_container, frag).addToBackStack("mujarrad");
          transactions.commit();

        }else if(i==R.id.conjugationnav){
          chipNavigationBar.setVisibility(View.GONE);
          //  Intent conjugatorintent = new Intent(newreadactivity.this, VerbConjugationAct.class);
          Intent conjugatorintent = new Intent(newreadactivity.this, ConjugatorAct.class);
          //   Intent conjugatorintent = new Intent(newreadactivity.this, VerbConjugationAct.class);


          finish();
          startActivity(conjugatorintent);

        }else if(i==R.id.bookmarksnav){
          chipNavigationBar.setVisibility(View.GONE);
          BookmarkFragment bookmarkFragment = new BookmarkFragment();

          FragmentTransaction transactions = getSupportFragmentManager().beginTransaction()
                  .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
          transactions.replace(R.id.frame_container, bookmarkFragment).addToBackStack("mujarrad");
          transactions.commit();

        }
      }
    });
    PreferenceManager.setDefaultValues(this, R.xml.preferences, false);






    mausoof = shared.getBoolean("mausoof", true);
    mudhaf = shared.getBoolean("mudhaf", true);
    harfnasb = shared.getBoolean("harfnasb", true);
    shart = shared.getBoolean("shart", true);
    kana = shared.getBoolean("kana", true);









    Intent bundle = getIntent();
    if (!(bundle.getExtras() == null)) {
      Bundle bundles = getIntent().getExtras();
      //   if (bundle != null) {
      String lastread = bundles.getString(QURAN_VERB_ROOT);
      if (null != lastread && lastread.equals("quran")) {
        getpreferences();
        setChapterorpart(true);
      } else {
        final int chapter = bundle.getIntExtra(CHAPTER, 1);
        //     final int chapter = 1;
        final boolean chapterorpartb = bundle.getBooleanExtra(CHAPTERORPART, true);
        initView();
        bundle.getStringExtra("partname");
        setChapterno(chapter);
        setChapterorpart(chapterorpartb);

        setPartname(bundle.getStringExtra(SURAH_ARABIC_NAME));
        //   setChapterno( bundle.getIntExtra(SURAH_ID,2));
        setVerse_no(bundle.getIntExtra(AYAHNUMBER, 1));
        setVersescount(bundle.getIntExtra(VERSESCOUNT, 286));
        setIsMakkiMadani(bundle.getIntExtra(MAKKI_MADANI, 1));
        setSuraharabicname(bundle.getStringExtra(SURAH_ARABIC_NAME));
        setRukucount(bundle.getIntExtra(RUKUCOUNT, 1));

        setUpOnCLicks();

        ArrayList<ChaptersAnaEntity> surahArray;

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
    chapterno = pref.getInt("surah", 1);
    verse_no = pref.getInt("ayah", 1);


  }


  @RequiresApi(api = Build.VERSION_CODES.Q)
  private void setUpOnCLicks() {



  }



  public void initDialogComponents() {


    Dialog jumpDialog;


    Spinner suraNames, verses;
    EditText surahIndex;

    Button ok;
    TextView ayahlabel;

    Utils util;

    List<String> sorasShow;


    //   jumpDialog = new Dialog(this,R.style.Base_Theme_AppCompat_Dialog);
    jumpDialog = new Dialog(this) ;

    jumpDialog.setContentView(R.layout.backupjumb_to_popup);
    suraNames = jumpDialog.findViewById(R.id.suras);
    verses = jumpDialog.findViewById(R.id.verses);
    util = new Utils(this);
    surahIndex = jumpDialog.findViewById(R.id.suraIndex);
    ayahIndex= jumpDialog.findViewById(R.id.ayahInput);
    ayahlabel=jumpDialog.findViewById(R.id.ayahlabel);

    jumpDialog.show();
    int maxLengthofEditText = 3;
    surahIndex.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLengthofEditText)});

    surahIndex.setFilters(new InputFilter[]{new InputFilterMinMax(1, 114)});
    ayahIndex.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLengthofEditText)});



    ok = jumpDialog.findViewById(R.id.ok);
    //    ArrayList<ChaptersAnaEntity> surahArray = utils.getSingleChapter(surah_id);

    sorasShow = new ArrayList<>();
    verseNumber = verse_no;
    int currentsurah = surah_id;
    int count = 0;
    soraList = util.getAllAnaChapters();

    for (ChaptersAnaEntity entity : soraList) {
      //  sorasShow.add(((++count) + " - " + (Locale.getDefault().getDisplayLanguage().equals("العربية") ? entity.getNamearabic() : entity.getAbjadname()).replace("$$$", "'")));
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
    setSuraharabicname(show[getCurrentSelectSurah()]);

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

        verseAdapter = new ArrayAdapter<>(newreadactivity.this,
                R.layout.spinner_layout_larg, numbers);

        verses.setAdapter(verseAdapter);
        if (verseNumber <= numbers.length) {
          verses.setSelection(verseNumber - 1);
        } else {
          verses.setSelection(numbers.length - 1);
        }
        ayahIndex.setFilters(new InputFilter[]{new InputFilterMinMax(1, numbers.length )});


        StringBuilder max=new StringBuilder();
        max.append("Max (").append(numbers.length-1).append(")");
        ayahlabel.setText(max.toString());
        surahIndex.setHint((suraNumber) + "");

        sora.getNamearabic();


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

      if(! ayahIndex.getText().toString().trim().equals("") ) {
        jumpDialog.dismiss();
        soraList.get(suraNumber).getAbjadname();

        StringBuilder sb=new StringBuilder();
        sb.append(suraNumber).append("-").append(soraList.get(suraNumber-1).getNameenglish()).append("-").append(soraList.get(suraNumber-1).getAbjadname());
        setSuraharabicname(sb.toString());
        setPartname(soraList.get(suraNumber-1).getAbjadname());
        //  ayahIndex.getInputType();
        Editable text = ayahIndex.getText();
        setVerse_no(Integer.parseInt(String.valueOf(text)));

        setVersescount(soraList.get(suraNumber-1).getVersescount());
        setIsMakkiMadani(soraList.get(suraNumber-1).getIsmakki());
        setRukucount(soraList.get(suraNumber-1).getRukucount());

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


      }else   if (          surahIndex.getText().toString().trim().equals("") || surahIndex.getText() == null) {
        jumpDialog.dismiss();
        soraList.get(suraNumber).getAbjadname();

        StringBuilder sb=new StringBuilder();
        sb.append(suraNumber).append("-").append(soraList.get(suraNumber-1).getNameenglish()).append("-").append(soraList.get(suraNumber-1).getAbjadname());
        setSuraharabicname(sb.toString());
        setPartname(soraList.get(suraNumber-1).getAbjadname());
        setVerse_no(verseNumber);
        setVersescount(soraList.get(suraNumber-1).getVersescount());
        setIsMakkiMadani(soraList.get(suraNumber-1).getIsmakki());
        setRukucount(soraList.get(suraNumber-1).getRukucount());

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


    allofQuran = Utils.getQuranbySurah(chapterno);
    shared.getBoolean("prefs_show_erab", true);


    ExecuteSurahWordByWord();


  }







  public void ExecuteSurahWordByWord() {

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(false); // if you want user to wait for some process to finish,
    builder.setView(R.layout.layout_loading_dialog);
    AlertDialog dialog = builder.create();
    corpusayahWordArrayList = new ArrayList<>();


    ExecutorService ex = Executors.newSingleThreadExecutor();

    ex.execute(() -> {
      //do inbackground

      newreadactivity.this.bysurah(dialog, ex);


    });


  }

  private void bysurah(AlertDialog dialog, ExecutorService ex) {


    runOnUiThread(() -> {

      dialog.show();
    });


    int versesnumbers;

    versesnumbers = getVersescount();

    ArrayList<CorpusExpandWbwPOJO> wbw = utils.getCorpusWbwBySurah(chapterno);
    int verseglobal = 0;
    int tempVerseWord;
    int verseexit = wbw.size();
    int currentProgressCount = 10;

    for (int indexv = 1; indexv <= versesnumbers; indexv++) {
      tempVerseWord = indexv;
      currentProgressCount++;
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
          StringBuilder sb = new StringBuilder();
          sb.append(wbw.get(verseglobal).getAraone()).append(wbw.get(verseglobal).getAratwo());
          CharSequence sequence = concat(wbw.get(verseglobal).getAraone() + wbw.get(verseglobal).getAratwo() +
                  wbw.get(verseglobal).getArathree() + wbw.get(verseglobal).getArafour());
          //   Object o4 = wbw.get(verseglobal).getWord();
          Object en = wbw.get(verseglobal).getEn();
          Object bn = wbw.get(verseglobal).getBn();
          Object ind = wbw.get(verseglobal).getIn();
          String ur = wbw.get(verseglobal).getUr();
          word.setSurahId(o1);
          word.setVerseId(o2);
          word.setWordno((int) Long.parseLong(o3.toString()));
          word.setWordcount(wcount);
          if (o1 == 6 && o2 == 56) {

            //    //System.out.println("CHECK");
          }
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

    if (mausoof) {


      corpus.SetMousufSifaDB(corpusayahWordArrayList, chapterno);
    }
    if (mudhaf) {

      corpus.setMudhafFromDB(corpusayahWordArrayList, chapterno);

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
      header.add(getPartname());


      flowAyahWordAdapter = new OrignalFlowAyahWordAdapter(header,colorerab, allofQuran, corpusayahWordArrayList, newreadactivity.this, surah_id, partname, isMakkiMadani, listener);







      flowAyahWordAdapter.addContext(newreadactivity.this);

      parentRecyclerView.setHasFixedSize(true);

      parentRecyclerView.setAdapter(flowAyahWordAdapter);
      flowAyahWordAdapter.notifyDataSetChanged();
      parentRecyclerView.post(() -> parentRecyclerView.scrollToPosition(verse_no));


    });
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

    View overflow=                   view.findViewById(R.id.ivActionOverflow);
    View jumptosurah = view.findViewById(R.id.jumpto);
    View bookmark = view.findViewById(R.id.bookmarkView);


    View qurantext = view.findViewById(R.id.quran_textView);

    if(overflow!=null){
      //popupMenu(overflow);
      boolean colortag = shared.getBoolean("colortag", true);
      boolean issentencesanalysis = shared.getBoolean("grammarsentence", true);

      AlertDialog.Builder builder = new AlertDialog.Builder(newreadactivity.this );
      LayoutInflater factory = LayoutInflater.from(newreadactivity.this);
      view = factory.inflate(R.layout.popup_layout, null);
      View fopen = view.findViewById(R.id.fileopen);
      View bookmarkview = view.findViewById(R.id.bookmark);
      SwitchCompat colorized= view.findViewById(R.id.colorized);
      SwitchCompat sentencesana= view.findViewById(R.id.sentences);
      View gotobookmarsk=                          view.findViewById(R.id.gotobookmarks);

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
      wmlp.y = location[1] ;  //y position

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

          if(colortag){
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(newreadactivity.this).edit();
            //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
            editor.putBoolean("colortag",false);

            editor.apply();
            RefreshActivity();
          }else{
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(newreadactivity.this).edit();
            //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
            editor.putBoolean("colortag",true);

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

          if(issentencesanalysis){
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(newreadactivity.this).edit();
            //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
            editor.putBoolean("grammarsentence",false);

            editor.apply();
          }else{
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(newreadactivity.this).edit();
            //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
            editor.putBoolean("grammarsentence",true);

            editor.apply();

          }

          dialog.dismiss();
        }
      });

    }

    else if(jumptosurah!=null){
      initDialogComponents();

    }
    else if(bookmark!=null){
      bookMarkSelected();

    }else if(qurantext!=null) {
      QuranEntity word = allofQuran.get(position);
      Bundle dataBundle = new Bundle();
      dataBundle.putInt(SURAH_ID, word.getSurah());
      dataBundle.putInt(AYAHNUMBER, Math.toIntExact(word.getAyah()));

      LoadItemList(dataBundle, word);
    }

  }

  private void bookMarkSelected() {
    int position;
    position = flowAyahWordAdapter.getAdapterposition();
    int  chapter_no = corpusayahWordArrayList.get(position).getWord().get(0).getSurahId();
    int   verse = corpusayahWordArrayList.get(position).getWord().get(0).getVerseId();


    BookMarks en = new BookMarks();


    en.setChapterno(String.valueOf(chapter_no));
    en.setVerseno(String.valueOf(verse));
    en.setSurahname(getSuraharabicname());
    //     Utils utils = new Utils(ReadingSurahPartActivity.this);
    utils.insertBookMark(en);

    Snackbar snackbar = Snackbar
            .make(coordinatorLayout, "BookMark Created", Snackbar.LENGTH_LONG);
    snackbar.setActionTextColor(Color.BLUE);
    snackbar.setTextColor(Color.MAGENTA);
    snackbar.setBackgroundTint(Color.BLACK);
    snackbar.show();
  }

  private void popupMenu(View overflow) {
    PopupMenu popupMenu = new PopupMenu(this, overflow);
    MenuInflater inflater = popupMenu.getMenuInflater();
    inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());

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
          // Toast message on menu item clicked
          Toast.makeText(newreadactivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
          return true;
        }
      });

    } catch (Exception e) {

    }
    popupMenu.show();
  }


  @Override
  public void onItemLongClick(int position, View v) {
    //    Toast.makeText(this, "longclick", Toast.LENGTH_SHORT).show();

  }


  public interface IOnBackPressed {
    /**
     * If you return true the back press will not be taken into account, otherwise the activity will act naturally
     *
     * @return true if your processing has priority if not false
     */
    boolean onBackPressed();
  }


  private void initView() {
    utils = new Utils(getApplication());

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());


    btnBottomSheet = findViewById(R.id.fab);
    layoutBottomSheet = findViewById(R.id.bottom_sheet);
    //    btnBottomSheet=findViewById(R.id.btn_bottom_sheet);
    conjugatorchip = findViewById(R.id.conjugationnav);
    settingchip = findViewById(R.id.settingnav);
    bookmarkchip = findViewById(R.id.bookmarksnav);
    searchchip = findViewById(R.id.searchnav);

    shartchip = findViewById(R.id.surahnav);
    tvsurah = findViewById(R.id.tvRukus);
    tvayah = findViewById(R.id.tvVerses);
    tvrukus = findViewById(R.id.tvSura);



    coordinatorLayout = findViewById(R.id.coordinatorLayout);


    RecyclerView verlayViewRecyclerView = findViewById(R.id.overlayViewRecyclerView);

    verlayViewRecyclerView.setLayoutManager(linearLayoutManager);


    conjugatorchip.setOnClickListener(v -> {
      //   storepreferences();
      Intent conjugatorintent = new Intent(newreadactivity.this, ConjugatorAct.class);
      //   Intent conjugatorintent = new Intent(newreadactivity.this, VerbConjugationAct.class);


      finish();
      startActivity(conjugatorintent);

    });


    settingchip.setOnClickListener(v -> {
      //   storepreferences();
      Intent intents = new Intent(newreadactivity.this, ActivitySettings.class);
      startActivity(intents);



    });


    // bookmarkchip.setOnClickListener(v -> CheckStringLENGTHS());


    searchchip.setOnClickListener(v -> {
      Intent intents = new Intent(newreadactivity.this, SearchActivity.class);
      startActivity(intents);


    });
    bookmarkchip.setOnClickListener(v -> {
      bookmarkchip = findViewById(R.id.bookmarksnav);

    });

    shartchip.setOnClickListener(v -> {


      NewSurahDisplayFrag frag = new NewSurahDisplayFrag();





      FragmentTransaction transactions = getSupportFragmentManager().beginTransaction()
              .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
      transactions.replace(R.id.frame_container, frag).addToBackStack("mujarrad");
      transactions.commit();


    });


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
    if(visibility==0) {
      chipNavigationBar.setVisibility(View.GONE);
    }if(visibility==8){
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



}




