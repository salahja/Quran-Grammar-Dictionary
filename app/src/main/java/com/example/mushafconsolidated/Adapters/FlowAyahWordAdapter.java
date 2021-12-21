package com.example.mushafconsolidated.Adapters;


import static android.content.Context.MODE_PRIVATE;
import static com.example.Constant.AYAHNUMBER;
import static com.example.Constant.SURAH_ARABIC_NAME;
import static com.example.Constant.SURAH_ID;
import static com.example.Constant.WORDNUMBER;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Config;
import com.example.mushafconsolidated.Entities.QuranEntity;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.fragments.GrammerFragmentsBottomSheet;
import com.example.mushafconsolidated.fragments.SentenceAnalysisBottomSheet;
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet;
import com.example.mushafconsolidated.fragments.WordMorphologyDetails;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.mushafconsolidated.intrface.OnItemClickListenerOnLong;
import com.example.mushafconsolidated.model.CorpusAyahWord;
import com.example.mushafconsolidated.model.CorpusWbwWord;
import com.example.utility.AnimationUtility;
import com.example.utility.CorpusUtilityorig;
import com.example.utility.DarkThemeApplication;
import com.example.utility.FlowLayout;
import com.example.utility.PreferenceUtil;
import com.example.utility.SharedPref;
import com.tooltip.Tooltip;

import java.util.ArrayList;
import java.util.List;

//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
//public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemClickListenerOnLong {
public class FlowAyahWordAdapter extends RecyclerView.Adapter<FlowAyahWordAdapter.ItemViewAdapter> implements OnItemClickListenerOnLong {
  private static final int TYPE_HEADER = 0;
  private static final int TYPE_ITEM = 1;
  public static Object ItemViewAdapter;
  static boolean showTranslation;
  static boolean wordByWord;

  private final boolean issentence, isfragmentlisting;
  private final ArrayList<QuranEntity> colorerab;
  private final SharedPreferences sharedPreferences;

  OnItemClickListenerOnLong mItemClickListener;

  static int fontSizeArabic;
  static int fontSizeTranslation;

  static Typeface corpusTypeface;

  public Context context;
  final long surah_id;
  private ArrayList<CorpusAyahWord> ayahWordArrayList;
  private final List<QuranEntity> allofQuran;

  public int adapterposition;
  private OnItemClickListenerOnLong onclicklistner;
  private ArrayList<String> header;
  private String isNightmode;

  public int getAdapterposition() {
    return adapterposition;
  }

  public void setAdapterposition(int adapterposition) {
    this.adapterposition = adapterposition;
  }

  PreferenceUtil pref;
  private String SurahName;
  private int isMakkiMadani;
  public TextView arabic;


  public void setItemClickListener(View.OnClickListener clickListener) {
    //   private final List<QuranEntity> translations;
    //  privviewate final List<QuranEntity> erab;
  }

  public FlowAyahWordAdapter(ArrayList<String> header, ArrayList<QuranEntity> colorerab, List<QuranEntity> allofQuran,


                             ArrayList<CorpusAyahWord> ayahWordArrayList, Context context, long surah_id, String surahName, int ismakki, OnItemClickListenerOnLong listener) {
    this.header = header;
    this.allofQuran = allofQuran;
    this.colorerab = colorerab;
    this.ayahWordArrayList = ayahWordArrayList;
    this.context = context;
    this.surah_id = surah_id;
    this.SurahName = surahName;
    this.isMakkiMadani = ismakki;
  sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    //   showTranslation =
    //        sharedPreferences.getBoolean(Config.SHOW_TRANSLATION, Config.defaultShowTranslation);
    sharedPreferences.getBoolean(Config.SHOW_Erab, Config.defaultShowErab);

    issentence = sharedPreferences.getBoolean("grammarsentence", false);
    isfragmentlisting = sharedPreferences.getBoolean("fragments", false);




    mItemClickListener = listener;

  }


  public void addContext(Context context) {
    this.context = context;

  }






  public void SetOnItemClickListener(final OnItemClickListenerOnLong mItemClickListener) {
    this.mItemClickListener = mItemClickListener;
  }


  @Override
  public int getItemViewType(int position) {
    if (position == 0) {
      return TYPE_HEADER;
    }
    return TYPE_ITEM;
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


  @NonNull
  @Override
  public ItemViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view;
    if (viewType == 0) {
      view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_header, parent, false);
      return new ItemViewAdapter(view, viewType);
    } else {

      view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ayah_word, parent, false);
      //   view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewer_aya_cardview, parent, false);
      return new ItemViewAdapter(view, viewType);
    }


  }


  public class ItemViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener, OnLongClickListener {
    TextView tvSura, tvRukus, tvVerses;
    ImageView ivSurahIcon, ivLocationmakki, ivLocationmadani;

    public TextView verse_idTextView;
    public TextView quran_jalalayn;
    public TextView quran_transliteration;
    //  public   com.nex3z.flowlayout.FlowLayout  flow_word_by_word;
    com.example.utility.FlowLayout flow_word_by_word;

    public TextView translate_textView;

    public TextView erab_textView;
    public TextView surah_info;
    public TextView bismilla;
    public TextView quran_textView;
    public TextView quran_transliterationnote;
    public TextView quran_jalalaynnote;
    public TextView erab_textViewnote;
    public TextView translate_textViewnote;
    public ImageView bookmark, jumpto, makkimadaniicon;
    public ImageView expandImageButton, ivBismillah;

    // public MaterialCardView cardview;
    public FlowLayout cardview;


    ItemViewAdapter(View view, int viewType) {
      super(view);
      view.setTag(this);
      itemView.setOnClickListener(this);
      if (viewType == 0) {
        ivLocationmakki = view.findViewById(R.id.ivLocationmakki);
        ivLocationmadani = view.findViewById(R.id.ivLocationmadani);
        ivSurahIcon = view.findViewById(R.id.ivSurahIcon);
        tvVerses = (TextView) view.findViewById(R.id.tvVerses);
        tvRukus = (TextView) view.findViewById(R.id.tvRukus);
        tvSura = (TextView) view.findViewById(R.id.tvSura);
        ivBismillah = view.findViewById(R.id.ivBismillah);

      } else {

        makkimadaniicon = view.findViewById(R.id.makkimadaniicon);

        jumpto = view.findViewById(R.id.jumpto);
        bismilla = view.findViewById(R.id.bismillah);
        quran_transliterationnote = view.findViewById(R.id.quran_transliterationnote);
        quran_jalalaynnote = view.findViewById(R.id.quran_jalalaynnote);
        translate_textViewnote = view.findViewById(R.id.translate_textViewnote);
        erab_textViewnote = view.findViewById(R.id.erab_textViewnote);
        quran_transliteration = view.findViewById(R.id.quran_transliteration);
        quran_jalalayn = view.findViewById(R.id.quran_jalalayn);
        surah_info = view.findViewById(R.id.chaptername);
        //    verse_idTextView = view.findViewById(R.id.verse_id_textView);

        flow_word_by_word = view.findViewById(R.id.flow_word_by_word);
        translate_textView = view.findViewById(R.id.translate_textView);
        erab_textView = view.findViewById(R.id.erab_textView);
        quran_textView = view.findViewById(R.id.quran_textView);
        bookmark = view.findViewById(R.id.bookmarkView);
        expandImageButton = view.findViewById(R.id.expandImageButton);
        quran_textView.setOnClickListener(this);
        bookmark.setOnClickListener(this);
        jumpto.setOnClickListener(this);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);


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
      }

    }

    @Override
    public void onClick(View v) {
      if (mItemClickListener != null) {
        mItemClickListener.onItemClick(v, getLayoutPosition());
      }
    }


    @Override
    public boolean onLongClick(View v) {
      mItemClickListener.onItemLongClick(getAdapterPosition(), v);
      return true;

    }
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  @Override
  public void onBindViewHolder(ItemViewAdapter holder, int position) {
    SharedPreferences sharedPreferences =
          androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
    isNightmode = sharedPreferences.getString("themepref", "dark");
    if (getItemViewType(position) == 0) {

      TypedArray imgs = this.context.getResources().obtainTypedArray(R.array.sura_imgs);
      // You have to set your header items values with the help of model class and you can modify as per your needs
      holder.tvRukus.setText("Ruku's :" + header.get(0));
      holder.tvVerses.setText("Aya's :" + header.get(1));
      holder.tvSura.setText(header.get(3));
      String chapterno = header.get(2);
      int tauba = Integer.parseInt(chapterno);
      if (tauba == 9) {
        holder.ivBismillah.setVisibility(View.GONE);
      }


      if (isMakkiMadani == 1) {
        holder.ivLocationmakki.setVisibility(View.VISIBLE);
        holder.ivLocationmadani.setVisibility(View.GONE);
      } else {
        holder.ivLocationmadani.setVisibility(View.VISIBLE);
        holder.ivLocationmakki.setVisibility(View.GONE);
      }
      final Drawable drawable = imgs.getDrawable(Integer.parseInt(chapterno) - 1);

      holder.ivSurahIcon.setImageDrawable(drawable);


      if (isNightmode.equals("dark") || isNightmode.equals("blue")) {

        holder.ivLocationmakki.setColorFilter(Color.CYAN);
        holder.ivSurahIcon.setColorFilter(Color.CYAN);

        holder.ivLocationmadani.setColorFilter(Color.CYAN);


      } else {
        holder.ivLocationmakki.setColorFilter(Color.BLACK);
        holder.ivSurahIcon.setColorFilter(Color.BLACK);
        holder.ivLocationmadani.setColorFilter(Color.BLACK);
      }


    } else {


      //   holder.flowwbw.setBackgroundColor(R.style.Theme_DarkBlue);

      String arabic_font_selection = sharedPreferences.getString("Arabic_Font_Selection", String.valueOf(MODE_PRIVATE));


      Typeface custom_font = Typeface.createFromAsset(context.getAssets(),
              arabic_font_selection);

      boolean showErab = sharedPreferences.getBoolean("showErabKey", true);
      boolean showWordColor = sharedPreferences.getBoolean("colortag", true);

      boolean showTransliteration = sharedPreferences.getBoolean("showtransliterationKey", true);
      boolean showJalalayn =sharedPreferences.getBoolean("showEnglishJalalayn", true);
      boolean showTranslation =sharedPreferences.getBoolean("showTranslationKey", true);
      boolean showWordByword = sharedPreferences.getBoolean("wordByWord", false);

      String whichtranslation = sharedPreferences.getString("selecttranslation", "en_sahih");
      QuranEntity entity = null;
      QuranEntity erabentity = null;
      String wbw = sharedPreferences.getString("wbw", String.valueOf(Context.MODE_PRIVATE));
      try {
        entity = allofQuran.get(position);
      } catch (IndexOutOfBoundsException e) {
        System.out.println("check");
      }


      final CorpusAyahWord ayahWord = ayahWordArrayList.get(position);
      storepreferences(entity);
      SpannableString quranverses = ayahWordArrayList.get(position).getSpannableverse();

      holder.quran_textView.setText(quranverses);
      //   holder.translate_textView.setText(ayahWord.getQuranTranslate());

      holder.quran_textView.setTextSize(SharedPref.SeekarabicFontsize());
      holder.quran_textView.setTypeface(custom_font);

      setChapterInfo(holder, ayahWord);

      setAdapterposition(position);


      wordBywordWithTranslation(holder, custom_font, showWordColor, wbw, ayahWord, showWordByword);


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
        if (whichtranslation.equals("en_sahih")) {

          holder.translate_textView.setText(entity.getTranslation());

          holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
          holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
          holder.translate_textView.setVisibility(View.VISIBLE);
          holder.translate_textViewnote.setVisibility(View.VISIBLE);
        }
        if (whichtranslation.equals("en_jalalayn")) {
          holder.translate_textView.setText(entity.getEn_jalalayn());

          holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
          holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
          holder.translate_textView.setVisibility(View.VISIBLE);
          holder.translate_textViewnote.setVisibility(View.VISIBLE);
        }
        if (whichtranslation.equals("ur_jalalayn")) {
          holder.translate_textView.setText(entity.getUr_jalalayn());

          holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
          holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
          holder.translate_textView.setVisibility(View.VISIBLE);
          holder.translate_textViewnote.setVisibility(View.VISIBLE);
        }
        if (whichtranslation.equals("ur_junagarhi")) {
          holder.translate_textView.setText(entity.getUr_junagarhi());

          holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
          holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
          holder.translate_textView.setVisibility(View.VISIBLE);
          holder.translate_textViewnote.setVisibility(View.VISIBLE);
        }


        holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
        holder.translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
        holder.translate_textView.setVisibility(View.VISIBLE);
        holder.translate_textViewnote.setVisibility(View.VISIBLE);

      }


showErab=false;
      if (showErab) {


        holder.erab_textView.setText(erabentity.getErabspnabble());
        holder.erab_textView.setTextSize(SharedPref.SeekarabicFontsize());
        holder.erab_textView.setTypeface(custom_font);
        holder.erab_textView.setVisibility(View.VISIBLE);
        holder.erab_textViewnote.setVisibility(View.VISIBLE);


        if (SharedPref.themePreferences().equals("dark")) {
          holder.erab_textView.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.white));

        } else {
          holder.erab_textView.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.burntamber));

        }

      }
    }

  }

  private void wordBywordWithTranslation(ItemViewAdapter holder, Typeface custom_font, boolean showWordColor, String wbw, CorpusAyahWord ayahWord, boolean showWbwTranslation) {
    final LayoutInflater inflater =
          (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    holder.flow_word_by_word.removeAllViews();
    for (final CorpusWbwWord word : ayahWord.getWord()) {
      final View view = inflater.inflate(R.layout.word_by_word, null);
      //      arabic.setOnLongClickListener((OnLongClickListener) this);

      //   final TextView arabic = view.findViewById(R.id.word_arabic_textView);
      arabic = view.findViewById(R.id.word_arabic_textView);
      final TextView translation = view.findViewById(R.id.word_trans_textView);

      if (showWordColor) {

        SpannableString spannedword = null;


        spannedword = getSpannedWords(word);
     //   arabic.setText(fixArabic(String.valueOf(spannedword)));
        spannedword.toString().replaceAll(" ", "");


        arabic.setText(spannedword);
      } else {


        arabic.setText(word.getWordsAr());
      }


      //  arabic.setTextSize(18);
      arabic.setTextSize(SharedPref.SeekarabicFontsize());
      arabic.setTypeface(custom_font);
      if (showWbwTranslation) {
        if (wbw.equals("en")) {
          translation.setText(word.getTranslateEn());
          translation.setPaintFlags(translation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        } else if (wbw.equals("bn")) {
          translation.setText(word.getTranslateBn());
          translation.setPaintFlags(translation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        } else if (wbw.equals("in")) {

          translation.setText(word.getTranslateIndo());
          translation.setPaintFlags(translation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        } else if (wbw.equals("ur")) {
          translation.setText(word.getTranslationUrdu());
          translation.setPaintFlags(translation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
        //  translation.setTextColor(context.getResources().getColor(R.color.neutral2));
      }
      //    translation.setTextSize(SharedPref.SeekarabicFontsize() + 4);
      translation.setTextSize(SharedPref.SeekarabicFontsize());
      holder.flow_word_by_word.addView(view);

      view.setLongClickable(true);
      view.setOnLongClickListener(new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

          WordMorphologyDetails qm = new WordMorphologyDetails(word);
          //   String ws= String.valueOf(qm.getWorkBreakDown());
          SpannableString workBreakDown = qm.getWorkBreakDown();
          int color = context.getResources().getColor(R.color.background_color_light_brown);
          switch (isNightmode) {
            case "dark":
            case "blue":
              color = context.getResources().getColor(R.color.background_color);
              break;
            case "brown":
              color = context.getResources().getColor(R.color.neutral0);
              break;
            case "white":
              color = context.getResources().getColor(R.color.background_color_light_brown);

              break;
          }
          Tooltip.Builder builder = new Tooltip.Builder(v, R.style.ayah_translation)
                .setCancelable(true)
                .setDismissOnClick(false)
                .setCornerRadius(20f)
                .setGravity(Gravity.TOP)
                .setArrowEnabled(true)

                .setBackgroundColor(color)

                .setText(workBreakDown);
          builder.show();
          return true;
        }
      });
      view.setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View view) {
          final Dialog dialog = new Dialog(context);
          //      dialog.setContentView(R.layout.corpus_layout);


          dialog.setTitle(fixArabic(word.getWordsAr()));


          Bundle dataBundle = new Bundle();
          dataBundle.putInt(SURAH_ID, word.getSurahId());
          dataBundle.putInt(AYAHNUMBER, Math.toIntExact(word.getVerseId()));

          dataBundle.putInt(WORDNUMBER, Math.toIntExact(word.getWordno()));
          dataBundle.putString(SURAH_ARABIC_NAME, SurahName);
          LoadItemList(dataBundle);

        }

        private void LoadItemList(Bundle dataBundle) {

          if (isfragmentlisting) {
            GrammerFragmentsBottomSheet item = new GrammerFragmentsBottomSheet();
            //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();

            item.setArguments(dataBundle);
            String[] data = {String.valueOf(word.getSurahId()), String.valueOf(word.getVerseId()), word.getTranslateEn(), String.valueOf((word.getWordno()))};
            FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
            //   transactions.show(item);
            GrammerFragmentsBottomSheet.newInstance(data).show(((AppCompatActivity) context).getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);

          } else if (issentence) {
            SentenceAnalysisBottomSheet item = new SentenceAnalysisBottomSheet();
            //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();

            item.setArguments(dataBundle);
            String[] data = {String.valueOf(word.getSurahId()), String.valueOf(word.getVerseId()), word.getTranslateEn(), String.valueOf((word.getWordno()))};
            FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
            //   transactions.show(item);
            SentenceAnalysisBottomSheet.newInstance(data).show(((AppCompatActivity) context).getSupportFragmentManager(), SentenceAnalysisBottomSheet.TAG);

          } else {
            WordAnalysisBottomSheet item = new WordAnalysisBottomSheet();
            //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();

            item.setArguments(dataBundle);
            String[] data = {String.valueOf(word.getSurahId()), String.valueOf(word.getVerseId()), word.getTranslateEn(), String.valueOf((word.getWordno())),SurahName};
            FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
            //   transactions.show(item);
            WordAnalysisBottomSheet.newInstance(data).show(((AppCompatActivity) context).getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);

          }

        }


      });

    }

    holder.flow_word_by_word.setVisibility(View.VISIBLE);

  }


  private SpannableString getSpannedWords(CorpusWbwWord corpus) {

    boolean b = corpus.getVerseId() == 20 && (corpus.getWordno() == 2 || corpus.getWordno() == 9);
    if (b) {
      System.out.println("check");
    }
    SpannableString spannableStringBuilder = CorpusUtilityorig.NewSetWordSpan(corpus.getTagone(), corpus.getTagtwo(), corpus.getTagthree(), corpus.getTagfour(), corpus.getTagfive(),
          corpus.getAraone(), corpus.getAratwo(), corpus.getArathree(), corpus.getArafour(), corpus.getArafive());


    return spannableStringBuilder;
  }


  private void storepreferences(QuranEntity entity) {


    SharedPreferences pref = context.getSharedPreferences("lastread", MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();
    editor.putInt("surah", entity.getSurah());
    editor.putInt("ayah", entity.getAyah());
    editor.putString("surahname",SurahName);

    editor.apply();
    editor.commit();

  }


  @RequiresApi(api = Build.VERSION_CODES.M)
  private void setChapterInfo(ItemViewAdapter holder, CorpusAyahWord verse) {


    StringBuilder surahInfo = new StringBuilder();
//        surahInfo.append(surahName+".");
    surahInfo.append(verse.getWord().get(0).getSurahId()).append(".");
    surahInfo.append(verse.getWord().get(0).getVerseId()).append("-");

    surahInfo.append(SurahName);
    SharedPreferences sharedPreferences =
          androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
    String isNightmode = sharedPreferences.getString("themepref", "dark");
    if (isMakkiMadani == 1) {
      holder.surah_info.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_makkah_48, 0, 0, 0);
      if (isNightmode.equals("dark") || isNightmode.equals("blue")) {
//TextViewCompat.setCompoundDrawableTintList()

        holder.surah_info.setCompoundDrawableTintList(ColorStateList.valueOf(Color.WHITE));
      } else {
        holder.surah_info.setCompoundDrawableTintList(ColorStateList.valueOf(Color.BLACK));
      }


    } else {
      holder.surah_info.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_madinah_48, 0, 0, 0);

      if (isNightmode.equals("dark") || isNightmode.equals("blue")) {
        holder.surah_info.setCompoundDrawableTintList(ColorStateList.valueOf(Color.WHITE));
      } else {
        holder.surah_info.setCompoundDrawableTintList(ColorStateList.valueOf(Color.BLACK));
      }
    }

    holder.surah_info.setText(surahInfo);
    //  holder.surah_info.setTextColor(context.getResources().getColor(R.color.colorOnPrimary));


  }


  private String fixArabic(String s) {
    // Add sukun on mem | nun
    s = s.replaceAll("\u0000", "");
    s = s.replaceAll("([\u0645\u0646])([ \u0627-\u064A]|$)", "$1\u0652$2");
    // Tatweel + Hamza Above (joining chairless hamza) => Yeh With Hamza Above
    s = s.replaceAll("\u0640\u0654", "\u0626");
    return s;
  }

  private int getStringInt(SharedPreferences sp, String key, int defValue) {
    return Integer.parseInt(sp.getString(key, Integer.toString(defValue)));
  }


  public void setListener(OnItemClickListenerOnLong listener) {

    mItemClickListener = listener;
  }

  public void getItem(int position) {
  }

  @Override
  public void onItemClick(View v, int position) {

  }

  @Override
  public void onItemLongClick(int position, View v) {

  }
//View.OnClickListener, View.OnLongClickListener


}























