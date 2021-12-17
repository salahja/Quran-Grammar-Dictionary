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
  CustomAdapter customAdapter;
  private HarbNasbMarkupFlowAyahWordAdapter nasbMarkupFlowAyahWordAdapter;

  String partname;
  boolean jumptostatus = false;
  int surah_id = 0;
  private boolean mausoof, mudhaf, harfnasb, shart;
  private ImageView surahImage;
  private View tvHeader;
  private ArrayList<ChaptersAnaEntity> soraList;
  private EditText ayahIndex;

  public int getTotalversus() {
    return totalversus;
  }

  public void setTotalversus(int totalversus) {
    this.totalversus = totalversus;
  }

  public int getRukucount() {
    return rukucount;
  }

  public void setRukucount(int rukucount) {
    this.rukucount = rukucount;
  }

  int verseNumber, suraNumber, pageNumber = 2;
  int rukucount,totalverses;
  String surahname;
  private ImageView mushafview;
  private boolean kana;
  private List<QuranEntity> allofQuran;
  private ArrayList<QuranEntity> colorerab;
  private String[] surahDetails;
  private int currentsurah, totalversus;
  private SharedPreferences shared;
  // --Commented out by Inspection (14/08/21, 7:26 PM):ChipNavigationBar chipNavigationBar;

  public void setSurahorpart(int surahorpart) {
    this.surahorpart = surahorpart;
  }

  boolean harfnasbmarkup, harfkanamarku;
  // --Commented out by Inspection (14/08/21, 7:23 PM):private List<QuranEntity> allVerses;

  boolean ismausoofbrokenplural;
  int surahorpart = 0;

  public void setIsmousoofsifa(boolean ismousoofsifa) {
    this.ismousoofsifa = ismousoofsifa;
  }

  private View newarrow;

  public void setIsmausoofbrokenplural(boolean ismausoofbrokenplural) {
    this.ismausoofbrokenplural = ismausoofbrokenplural;
  }

  private ArrayList<CorpusAyahWord> corpusayahWordArrayList;
  private boolean ismousoofsifa;

  Button settingchip, bookmarkchip, conjugatorchip, searchchip, shartchip;
  TextView tvsurah, tvayah, tvrukus;
  int currentSelectSurah;
  ImageView ivLocationmakki, ivLocationmadani;
  RelativeLayout layoutBottomSheet;

  public int getCurrentSelectSurah() {
    return currentSelectSurah;
  }

  public void setCurrentSelectSurah(int currentSelectSurah) {
    this.currentSelectSurah = currentSelectSurah;
  }

  RelativeLayout surapartview;

  BottomSheetBehavior sheetBehavior;


  private StringBuilder surahdetails;
  private View bookmarkView;
  //  private OnClickListener onClickListener;


  private View deleteAllBookmarks;

  private ImageView chapterTypeImageView;
  private ImageView devIndicatorView;
  private View jumpto;
  private Utils utils;
  private View surahpartview;
  private ArrayList<String> values;

  public String getPartname() {
    return partname;
  }

  public void setPartname(String partname) {
    this.partname = partname;
  }


  public int getSurah_id() {
    return surah_id;
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

  public int getVerse_no() {
    return verse_no;
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

  String chapterdetails;
  public int adapterposition;

  public int getAdapterposition() {
    return adapterposition;
  }

  public void setAdapterposition(int adapterposition) {
    this.adapterposition = adapterposition;
  }

  public String getChapterdetails() {
    return chapterdetails;
  }

  public void setChapterdetails(String chapterdetails) {
    this.chapterdetails = chapterdetails;
  }


  boolean isSurahfragmentshowing = true;
  final boolean isPartfragmentshowing = false;
  // private View overlayView;
  private TextView chapterview, partview, chapternameview, chapterDetailsView;
  private View cardview;

  private int chapterno;
  private boolean wordByWord;
  int currentsura;
  private Spinner filterspinner;

  public boolean isPartfragmentshowing() {
    return isPartfragmentshowing;
  }

  public static final int STARTUP_DELAY = 300;

  public void setCurrentsura(int currentsura) {
    this.currentsura = currentsura;
  }

  private FragmentManager fragmentManager;
  //  private float overlayViewHiddenPosition;


  public boolean isSurahfragmentshowing() {
    return isSurahfragmentshowing;
  }

  public void setSurahfragmentshowing(boolean surahfragmentshowing) {
    this.isSurahfragmentshowing = surahfragmentshowing;
  }


  CoordinatorLayout coordinatorLayout;
  private RecyclerView parentRecyclerView;

  NewSurahDisplayFrag surahFragment;


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
    getpreferences();
    chipNavigationBar = findViewById(R.id.bottom_nav_bar);
    tvsurah = findViewById(R.id.tvRukus);
    tvayah = findViewById(R.id.tvVerses);
    tvrukus = findViewById(R.id.tvSura);
    int selectedItemId = chipNavigationBar.getSelectedItemId();

    chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
      @Override
      public void onItemSelected(int i) {
        int selectedItemId = chipNavigationBar.getSelectedItemId();
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


   // PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

    //  SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());

    harfnasbmarkup = shared.getBoolean("kanamarkup", false);

    harfkanamarku = shared.getBoolean("nasabmarkup", false);


    mausoof = shared.getBoolean("mausoof", true);
    mudhaf = shared.getBoolean("mudhaf", true);
    harfnasb = shared.getBoolean("harfnasb", true);
    shart = shared.getBoolean("shart", true);
    kana = shared.getBoolean("kana", true);

    SharedPref sharedPref = new SharedPref(newreadactivity.this);
    wordByWord = SharedPref.showWordByword();

    boolean showTranslation = SharedPref.showTranslation();
    //  int fsie = prefs.getInt("SeekarabicFontsize", 20);

    if (SharedPref.quranFont().length() == 1) {
      SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(newreadactivity.this).edit();
      //     SharedPreferences.Editor editor = getActivity().getSharedPreferences("properties", 0).edit();
      editor.putString("quranFont", "me_quran.ttf");
      editor.apply();
    }

/*

    final View decor = getWindow().getDecorView();
    decor.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
      @Override
      public boolean onPreDraw() {
        decor.getViewTreeObserver().removeOnPreDrawListener(this);
        startPostponedEnterTransition();
        return true;
      }
    });

 */

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
        //   StringBuilder vc=new StringBuilder();
        ///   vc.append("aya's:").append(getVersescount());
//        tvayah.setText(vc.toString());
        //    tvsurah.setText(getSuraharabicname());
        //    StringBuilder vvccc=new StringBuilder();
        //     vvccc.append("Ruku's: ").append(getRukucount());
        //  tvrukus.setText(vvccc);
        //  ivLocationmakki=findViewById(R.id.ivLocationmakki);
        //  ivLocationmadani=findViewById(R.id.ivLocationmadani);
        //  surahImage = findViewById(R.id.ivSurahIcon);
        //  ivLocationmadani.setColorFilter(BCYAN);
        //  ivLocationmakki.setColorFilter(BCYAN);


        //  TypedArray imgs = getResources().obtainTypedArray(R.array.sura_imgs);


        // final Drawable drawable = imgs.getDrawable(cno);
        //    final int id = imgs.getResourceId((getChapterno()- 1), 2);
        //    tvHeader.setVisibility(View.VISIBLE);
        //  SetHeaderSurahImagr(id);
        //   surahImage.setImageTintList(ColorStateList.valueOf(Color.WHITE));
        //   imgs.recycle();
        //  SetDropDownChapter(getSuraharabicname());
        //   if(isMakkiMadani==1)
        //    {
        //  ivLocationmakki.setVisibility(View.VISIBLE);
        //    ivLocationmadani.setVisibility(View.GONE);
        //   }else{
        //   ivLocationmadani.setVisibility(View.VISIBLE);
        //   ivLocationmakki.setVisibility(View.GONE);
        //  }


        setUpOnCLicks(utils);

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

  private void getpreferences() {
    SharedPreferences pref = getApplicationContext().getSharedPreferences("lastread", MODE_PRIVATE);
    chapterno = pref.getInt("surah", 1);
    verse_no = pref.getInt("ayah", 1);


  }


  @RequiresApi(api = Build.VERSION_CODES.Q)
  private void setUpOnCLicks(Utils utils) {

 /*
    bookmarkView.setOnClickListener(new OnClickListener() {
      int position;
      int chapter_no;
      int verse;
      // --Commented out by Inspection (14/08/21, 7:28 PM):String name;

      @Override
      public void onClick(View v) {
        if (!wordByWord || !chapterorpart) {
          position = flowAyahWordAdapter.getAdapterposition();
          //      name = getSuraharabicname();

        }
        position = flowAyahWordAdapter.getAdapterposition();
        chapter_no = corpusayahWordArrayList.get(position).getWord().get(0).getSurahId();
        verse = corpusayahWordArrayList.get(position).getWord().get(0).getVerseId();


        BookMarks en = new BookMarks();

        if (wordByWord && chapterorpart) {
          en.setHeader(WBWSURAHHEADER);

        } else if (wordByWord) {
          en.setHeader(WBWPARTHEADER);
        } else if (chapterorpart) {
          en.setHeader(NOWBWSURAHHEADER);
        } else {
          en.setHeader(NOWBWPARTHEADER);
        }

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
    });




    filterspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {


          case 1:
            // Whatever you want to happen when the second item gets selected
            break;
          case 2:
            utils.getCorpusWbwBySurah(2);
            // Whatever you want to happen when the thrid item gets selected
            break;
          default:
            break;

        }

      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
    jumpto.setOnClickListener(v -> initDialogComponents());

   */

  }



  public void initDialogComponents() {


    Dialog jumpDialog;
    MaterialDialog materialDialog;

    Spinner suraNames, verses;
    EditText surahIndex;

    Button ok;
    TextView ayahlabel;

    Utils util;

    List<String> sorasShow;


 //   jumpDialog = new Dialog(this,R.style.Base_Theme_AppCompat_Dialog);
   jumpDialog = new Dialog(this) ;

    jumpDialog.setContentView(R.layout.backupjumb_to_popup);
    suraNames = (Spinner) jumpDialog.findViewById(R.id.suras);
    verses = (Spinner) jumpDialog.findViewById(R.id.verses);
    util = new Utils(this);
    surahIndex = (EditText) jumpDialog.findViewById(R.id.suraIndex);
    ayahIndex=(EditText) jumpDialog.findViewById(R.id.ayahInput);
    ayahlabel=jumpDialog.findViewById(R.id.ayahlabel);

    jumpDialog.show();
    int maxLengthofEditText = 3;
    surahIndex.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLengthofEditText)});

    surahIndex.setFilters(new InputFilter[]{new InputFilterMinMax(1, 114)});
    ayahIndex.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLengthofEditText)});



    ok = (Button) jumpDialog.findViewById(R.id.ok);
    //    ArrayList<ChaptersAnaEntity> surahArray = utils.getSingleChapter(surah_id);

    sorasShow = new ArrayList<>();
    verseNumber = verse_no;
    currentsurah = surah_id;
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
        ;
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
        ;
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

      String verser = String.valueOf(ayahWord.getSpannableverse());


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
     //   chapternameview.setText(getSuraharabicname());
      //  chapterDetailsView.setText(getChapterdetails());
      //  final String[] surahArray = getResources().getStringArray(R.array.suraharabic);
      //  final String namearabic = surahArray[getCurrentSelectSurah()];
      //  setSuraharabicname(namearabic);
//        chapternameview.setText(getSuraharabicname());
      //  chapterDetailsView.setText(getChapterdetails());

      //  TypedArray imgs = getResources().obtainTypedArray(R.array.sura_imgs);

       setSurahorpart(chapterno);
        // final Drawable drawable = imgs.getDrawable(cno);
       // final int id = imgs.getResourceId((getCurrentSelectSurah() - 1), 2);

       // SetHeaderSurahImagr(id);
       // devIndicatorView.setImageTintList(ColorStateList.valueOf(Color.WHITE));
       // imgs.recycle();
      //  SetDropDownChapter(getSuraharabicname());
      }
      OnItemClickListenerOnLong listener = this;
      ArrayList<String> header = new ArrayList<>();
      header.add(String.valueOf(getRukucount()));
      header.add(String.valueOf(getVersescount()));
      header.add(String.valueOf(getChapterno()));
      header.add(getPartname());
/*
 customAdapter = new CustomAdapter(header, colorerab, allofQuran, corpusayahWordArrayList, newreadactivity.this, surah_id, suraharabicname, isMakkiMadani, listener);

      parentRecyclerView.setHasFixedSize(true);

      parentRecyclerView.setAdapter(customAdapter);
      customAdapter.notifyDataSetChanged();
      parentRecyclerView.post(() -> parentRecyclerView.scrollToPosition(verse_no));
 */

      flowAyahWordAdapter = new OrignalFlowAyahWordAdapter(header,colorerab, allofQuran, corpusayahWordArrayList, newreadactivity.this, surah_id, partname, isMakkiMadani, listener);







        flowAyahWordAdapter.addContext(newreadactivity.this);

        parentRecyclerView.setHasFixedSize(true);

         parentRecyclerView.setAdapter(flowAyahWordAdapter);
       flowAyahWordAdapter.notifyDataSetChanged();
       parentRecyclerView.post(() -> parentRecyclerView.scrollToPosition(verse_no));


    });
  }


  public void GetSurahAyahWOrd(ArrayList<CorpusExpandWbwPOJO> wbw, int versesnumbers) {
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
          int o1 = wbw.get(verseglobal).getSurah();
          int o2 = wbw.get(verseglobal).getAyah();
          Object o3 = wbw.get(verseglobal).getWordno();
          int wcount = wbw.get(verseglobal).getWordcount();
          CharSequence sequence = concat(wbw.get(verseglobal).getAraone() + wbw.get(verseglobal).getAratwo() +
                wbw.get(verseglobal).getArathree() + wbw.get(verseglobal).getArafour());
          //   Object o4 = wbw.get(verseglobal).getWord();
          Object en = wbw.get(verseglobal).getEn();
          Object bn = wbw.get(verseglobal).getBn();
          Object ind = wbw.get(verseglobal).getIn();
          String ur = wbw.get(verseglobal).getUr();
          wbw.get(verseglobal).getUr();
          word.setSurahId(o1);
          word.setVerseId(o2);
          word.setWordno((int) Long.parseLong(o3.toString()));
          word.setWordcount(wcount);
          if (o1 == 6 && o2 == 56) {

            //       //System.out.println("CHECK");
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

      String verser = String.valueOf(ayahWord.getSpannableverse());
      if (harfnasbmarkup) {
        String[] words = verser.split("\\s+");
        int lastindex = 0;
        for (int i = 0; i < words.length; i++) {

          String ss = words[i];
          int indexof;
          if (i == 0) {
            indexof = verser.indexOf(ss);
          } else {

            indexof = verser.indexOf(ss, lastindex + 1);

          }


          lastindex = indexof;
          try {
            ayahWord.getWord().get(i).setWordindex(indexof);
          } catch (IndexOutOfBoundsException e) {
            System.out.println("indexoutofbound");
          }


        }
      }


      corpusayahWordArrayList.add(ayahWord);

    }
  }


  private void LoadItemList(Bundle dataBundle, QuranEntity word) {

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(false); // if you want user to wait for some process to finish,
    builder.setView(R.layout.layout_loading_dialog);
    AlertDialog dialog = builder.create();

    GrammerFragmentsBottomSheet item = new GrammerFragmentsBottomSheet();

    FragmentManager fragmentManager = getSupportFragmentManager();

    item.setArguments(dataBundle);
    String data[] = {String.valueOf(word.getSurah()), String.valueOf(word.getAyah()), word.getTranslation(), String.valueOf((1))};
    FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
    transactions.show(item);

    GrammerFragmentsBottomSheet.newInstance(data).show(getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);

  }



  private View.OnClickListener onItemClickListener;
   public void setItemClickListener(View.OnClickListener clickListener) {
    onItemClickListener = clickListener;
  }


  @Override
  public void onItemClick(View view, int position) {

    View overflow=                   view.findViewById(R.id.ivActionOverflow);
    View jumptosurah = view.findViewById(R.id.jumpto);
    View bookmark = view.findViewById(R.id.bookmarkView);
    Object tag1 = view.getTag(R.id.bookmarkView);
    //  View viewWithTag = v.findViewWithTag(bookmark);
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
      if(colortag){
        colorized.setChecked(true);

      }else{
        colorized.setChecked(false);
      }

      if(issentencesanalysis){
        sentencesana.setChecked(true);

      }else{
        sentencesana.setChecked(false);
      }


      builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dlg, int sumthin) {


        }
      });
      int[] location = new int[2];
      overflow.getLocationOnScreen(location);
      ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.Theme_MaterialDialog );
      AlertDialog dialog = builder.create();
      dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

      dialog.setTitle("my dialog");
      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
      wmlp.gravity = Gravity.TOP | Gravity.LEFT;
      wmlp.x = location[0];   //x position
      wmlp.y = location[1] ;  //y position
      AlertDialog alertDialog = builder.create();
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

      // dataBundle.putInt(WORDNUMBER, Math.toIntExact(word.getw()));
      //  dataBundle.putString(SURAH_ARABIC_NAME, SurahName);
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
    tvHeader = findViewById(R.id.tvHeader);


    coordinatorLayout = findViewById(R.id.coordinatorLayout);

    bookmarkView = findViewById(R.id.bookmarkView);

    jumpto = findViewById(R.id.jumpto);
    //  ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
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

      // NewSurahDisplayFragOrig sIfaDisplayFrag = new NewSurahDisplayFragOrig();
      //fragmentManager.beginTransaction().add(R.id.frame_container, sIfaDisplayFrag).addToBackStack(null).commit();

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


  private void loadFragment(Fragment fragment) {
    // load fragment
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
          .setCustomAnimations(R.anim.left_slide, android.R.anim.fade_out);
    transaction.replace(R.id.frame_container, fragment);
    transaction.addToBackStack(com.example.Constant.BOOKMARKTAG);
    transaction.commit();
  }


  @Override
  public void ondatarecevied(int chapterno, String partname, int totalverses, int rukucount, int makkimadani) {


    Log.e(TAG, "onClick called");
    final Intent intent = getIntent().putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(SURAH_ARABIC_NAME, partname)
          .putExtra(VERSESCOUNT, totalverses).putExtra(RUKUCOUNT, rukucount).putExtra(MAKKI_MADANI, makkimadani);


    //   ReadingSurahPartActivity activity = ReadingSurahPartActivity.this;
    //  activity.recreate();
    //  overridePendingTransition(0, 0);
    //  intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    //  finish();
    overridePendingTransition(0, 0);
    startActivity(intent);
    finish();
    overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);

  }


}




