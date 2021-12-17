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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.mushafconsolidated.intrface.OnItemClickListenerOnLong;
import com.example.mushafconsolidated.model.CorpusAyahWord;
import com.example.mushafconsolidated.model.CorpusWbwWord;
import com.example.utility.AnimationUtility;
import com.example.utility.CorpusUtilityorig;
import com.example.utility.DarkThemeApplication;
import com.example.utility.PreferenceUtil;
import com.example.utility.SharedPref;
import com.tooltip.Tooltip;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemClickListenerOnLong {

  private static final int TYPE_HEADER = 0;
  private static final int TYPE_ITEM = 1;
  private final ArrayList<String> header;


  private Context context;

  long surah_id;
  private ArrayList<CorpusAyahWord> ayahWordArrayList;
  List<QuranEntity> allofQuran;
  PreferenceUtil pref;
  private String SurahName;
  private int isMakkiMadani;
  public TextView arabic;
  static Typeface corpusTypeface;
  private  boolean issentence, isfragmentlisting;
  OnItemClickListenerOnLong mItemClickListener;;

  public CustomAdapter(ArrayList<String> header, ArrayList<QuranEntity> colorerab, List<QuranEntity> allofQuran,


                       ArrayList<CorpusAyahWord> ayahWordArrayList, Context context, long surah_id, String surahName, int ismakki, OnItemClickListenerOnLong listener) {
   this.header=header;
    this.allofQuran = allofQuran;
    boolean issentence, isfragmentlisting;
    this.ayahWordArrayList = ayahWordArrayList;
    this.context = context;
    this.surah_id = surah_id;
    this.SurahName = surahName;
    this.isMakkiMadani = ismakki;
    OnItemClickListenerOnLong mItemClickListener;
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    //   showTranslation =
    //        sharedPreferences.getBoolean(Config.SHOW_TRANSLATION, Config.defaultShowTranslation);
    sharedPreferences.getBoolean(Config.SHOW_Erab, Config.defaultShowErab);

    issentence = sharedPreferences.getBoolean("grammarsentence", false);
    isfragmentlisting = sharedPreferences.getBoolean("fragments", false);
    corpusTypeface = Typeface.createFromAsset(DarkThemeApplication.getInstance().getResources().getAssets(), "amiri.ttf");


    SharedPreferences sharedPref =
          androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);

    mItemClickListener = listener;

  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_ITEM) {
      // Here Inflating your recyclerview item layout
      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ayah_word, parent, false);
      return new ItemViewHolder(itemView);
    } else if (viewType == TYPE_HEADER) {
      // Here Inflating your header view
      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_header, parent, false);
      return new HeaderViewHolder(itemView);
    } else return null;
  }

  @Override
  public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
    CorpusAyahWord ayahWord;
    SpannableString quranverses;
    if (position==0) {
      //   setheadersdata_flag = true;
      HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
      TypedArray imgs = this.context.getResources().obtainTypedArray(R.array.sura_imgs);
      // You have to set your header items values with the help of model class and you can modify as per your needs
      headerViewHolder.tvRukus.setText("Ruku's :"+header.get(0));
      headerViewHolder.tvVerses.setText("Aya's :"+header.get(1));
      headerViewHolder.tvSura.setText(header.get(3));
      String chapterno = header.get(2);

      final String isNightmode = SharedPref.themePreferences();
       if(isMakkiMadani==1)
        {
          headerViewHolder. ivLocationmakki.setVisibility(View.VISIBLE);
          headerViewHolder.  ivLocationmadani.setVisibility(View.GONE);
        }else{
         headerViewHolder.   ivLocationmadani.setVisibility(View.VISIBLE);
         headerViewHolder.   ivLocationmakki.setVisibility(View.GONE);
       }
      final Drawable drawable = imgs.getDrawable(Integer.parseInt(chapterno) - 1);

      headerViewHolder.ivSurahIcon.setImageDrawable(drawable);
      if (isNightmode.equals("dark")) {

        headerViewHolder.ivLocationmakki.setColorFilter(Color.CYAN);
        headerViewHolder.ivSurahIcon.setColorFilter(Color.CYAN);

        headerViewHolder.ivLocationmadani.setColorFilter(Color.CYAN);

        ;
      } else {
        headerViewHolder.ivLocationmakki.setColorFilter(Color.BLUE);
        headerViewHolder.ivSurahIcon.setColorFilter(Color.BLACK);
        headerViewHolder.ivLocationmadani.setColorFilter(Color.CYAN);
      }






    } else  {

      final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      ayahWord = ayahWordArrayList.get(position-1);
      //   storepreferences(entity);
      quranverses = ayahWordArrayList.get(position-1).getSpannableverse();
      Typeface custom_font = Typeface.createFromAsset(context.getAssets(),
            SharedPref.arabicFontSelection());
      boolean showErab = SharedPref.showErab();
      boolean showWordColor = SharedPref.showWordColor();

      boolean showTransliteration = SharedPref.showTransliteration();
      boolean showJalalayn = SharedPref.showJalalayn();
      boolean showTranslation = SharedPref.showTranslation();
      boolean showWordByword = SharedPref.showWordByword();
      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
      String whichtranslation = prefs.getString("selecttranslation", "en_sahih");
      QuranEntity entity = null;
      QuranEntity erabentity = null;
      String wbw = SharedPref.getWbwLanguage();
      try {
        entity = allofQuran.get(position);
      } catch (IndexOutOfBoundsException e) {
        System.out.println("check");
      }




   //   final CorpusAyahWord ayahWord = ayahWordArrayList.get(position);
    //  storepreferences(entity);
    //  SpannableString quranverses = ayahWordArrayList.get(position).getSpannableverse();

      ((ItemViewHolder) holder).quran_textView.setText(quranverses);
      //   holder.translate_textView.setText(ayahWord.getQuranTranslate());

      ((ItemViewHolder) holder).quran_textView.setTextSize(SharedPref.SeekarabicFontsize());
      ((ItemViewHolder) holder).quran_textView.setTypeface(custom_font);

     // setChapterInfo(ItemViewHolder, ayahWord);

    //  setAdapterposition(position);
     // setStartText(holder, ayahWord);



      wordBywordWithTranslation( ((ItemViewHolder) holder), custom_font, showWordColor, wbw, ayahWord,showWordByword);



        ((ItemViewHolder) holder).arabic_textView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          Toast.makeText(context, "check", Toast.LENGTH_SHORT).show();
          return true;
        }
      });
      ((ItemViewHolder) holder).arabic_textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


          Bundle dataBundle = new Bundle();
          //    dataBundle.putInt(SURAH_ID, allofQuran.get());dataBundle.putInt(AYAHNUMBER, Math.toIntExact(word.getVerseId()));

          //  dataBundle.putInt(WORDNUMBER, Math.toIntExact(word.getWordno()));
          dataBundle.putString(SURAH_ARABIC_NAME, SurahName);
          //  LoadItemList(dataBundle);

        }
      });

      if (showTransliteration) {
        ((ItemViewHolder) holder).quran_transliteration.setText(Html.fromHtml(entity.getTranslation(), Html.FROM_HTML_MODE_LEGACY));
          ((ItemViewHolder) holder).quran_transliteration.setTextSize(SharedPref.SeekarabicFontsize());
          ((ItemViewHolder) holder).quran_transliteration.setTextSize(SharedPref.SeekarabicFontsize());
          ((ItemViewHolder) holder).quran_transliteration.setVisibility(View.VISIBLE);


      }
      if (showJalalayn) {
        //     ((ItemViewHolder) holder).quran_jalalaynnote.setText(enjalalayn.getAuthor_name());
          ((ItemViewHolder) holder).quran_jalalayn.setText(entity.getTranslation());
          ((ItemViewHolder) holder).quran_jalalayn.setTextSize(SharedPref.SeekarabicFontsize());
          ((ItemViewHolder) holder).quran_jalalayn.setTextSize(SharedPref.SeekarabicFontsize());
          ((ItemViewHolder) holder).quran_jalalayn.setVisibility(View.VISIBLE);
          ((ItemViewHolder) holder).quran_jalalaynnote.setVisibility(View.VISIBLE);
      }
      if (showTranslation) {
        if (whichtranslation.equals("en_sahih")) {

            ((ItemViewHolder) holder).translate_textView.setText(entity.getTranslation());

            ((ItemViewHolder) holder).translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            ((ItemViewHolder) holder).translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            ((ItemViewHolder) holder).translate_textView.setVisibility(View.VISIBLE);
            ((ItemViewHolder) holder).translate_textViewnote.setVisibility(View.VISIBLE);
        }
        if (whichtranslation.equals("en_jalalayn")) {
            ((ItemViewHolder) holder).translate_textView.setText(entity.getEn_jalalayn());

            ((ItemViewHolder) holder).translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            ((ItemViewHolder) holder).translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            ((ItemViewHolder) holder).translate_textView.setVisibility(View.VISIBLE);
            ((ItemViewHolder) holder).translate_textViewnote.setVisibility(View.VISIBLE);
        }
        if (whichtranslation.equals("ur_jalalayn")) {
            ((ItemViewHolder) holder).translate_textView.setText(entity.getUr_jalalayn());

            ((ItemViewHolder) holder).translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            ((ItemViewHolder) holder).translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            ((ItemViewHolder) holder).translate_textView.setVisibility(View.VISIBLE);
            ((ItemViewHolder) holder).translate_textViewnote.setVisibility(View.VISIBLE);
        }
        if (whichtranslation.equals("ur_junagarhi")) {
            ((ItemViewHolder) holder).translate_textView.setText(entity.getUr_junagarhi());

            ((ItemViewHolder) holder).translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            ((ItemViewHolder) holder).translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
            ((ItemViewHolder) holder).translate_textView.setVisibility(View.VISIBLE);
            ((ItemViewHolder) holder).translate_textViewnote.setVisibility(View.VISIBLE);
        }


          ((ItemViewHolder) holder).translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
          ((ItemViewHolder) holder).translate_textView.setTextSize(SharedPref.SeekarabicFontsize());
          ((ItemViewHolder) holder).translate_textView.setVisibility(View.VISIBLE);
          ((ItemViewHolder) holder).translate_textViewnote.setVisibility(View.VISIBLE);

      }


      showErab=false;
      if (showErab) {



          ((ItemViewHolder) holder).erab_textView.setText(erabentity.getErabspnabble());
          ((ItemViewHolder) holder).erab_textView.setTextSize(SharedPref.SeekarabicFontsize());
          ((ItemViewHolder) holder).erab_textView.setTypeface(custom_font);
          ((ItemViewHolder) holder).erab_textView.setVisibility(View.VISIBLE);
          ((ItemViewHolder) holder).erab_textViewnote.setVisibility(View.VISIBLE);


        if (SharedPref.themePreferences().equals("dark")) {
            ((ItemViewHolder) holder).erab_textView.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.white));

        } else {
            ((ItemViewHolder) holder).erab_textView.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.burntamber));

        }

      }


    }
  }

  private void wordBywordWithTranslation(ItemViewHolder holder, Typeface custom_font, boolean showWordColor, String wbw, CorpusAyahWord ayahWord, boolean showWbwTranslation) {
    final LayoutInflater inflater =
          (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    holder.flow_word_by_word.removeAllViews();
    for (final CorpusWbwWord word : ayahWord.getWord()) {
      final View view = inflater.inflate(R.layout.word_by_word, null);
      //      arabic.setOnLongClickListener((OnLongClickListener) this);

      //   final TextView arabic = view.findViewById(R.id.word_arabic_textView);
      arabic = view.findViewById(R.id.word_arabic_textView);


      if (showWordColor) {

        SpannableString spannedword = null;


        spannedword = getSpannedWords(word);

        spannedword.toString().replaceAll(" ", "");


        arabic.setText(spannedword);
      } else {


        arabic.setText(word.getWordsAr());
      }


      final TextView translation = view.findViewById(R.id.word_trans_textView);
      //  arabic.setTextSize(18);
      arabic.setTextSize(SharedPref.SeekarabicFontsize());
      arabic.setTypeface(custom_font);
      if(showWbwTranslation) {
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
      }
      //    translation.setTextSize(SharedPref.SeekarabicFontsize() + 4);
      translation.setTextSize(SharedPref.SeekarabicFontsize() );
      holder.flow_word_by_word.addView(view);

      view.setLongClickable(true);
      view.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

          WordMorphologyDetails qm = new WordMorphologyDetails(word);
          //   String ws= String.valueOf(qm.getWorkBreakDown());
          SpannableString workBreakDown = qm.getWorkBreakDown();
          int color = context.getResources().getColor(R.color.background_color);
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
            String[] data = {String.valueOf(word.getSurahId()), String.valueOf(word.getVerseId()), word.getTranslateEn(), String.valueOf((word.getWordno()))};
            FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
            //   transactions.show(item);
            WordAnalysisBottomSheet.newInstance(data).show(((AppCompatActivity) context).getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);

          }

        }


      });

    }

    holder.flow_word_by_word.setVisibility(View.VISIBLE);
    holder.arabic_textView.setVisibility(View.GONE);
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
    editor.apply();
    editor.commit();

  }


  private void setStartText(OrignalFlowAyahWordAdapter.ItemViewAdapter holder, CorpusAyahWord ayahWord) {

    //if (ayahWord.getWord().get(0).getVerseId() == 1) {//|| ayahWord.getWord().get(0).getSurahId() == 9 ||ayahWord.getWord().get(0).getVerseId() != 1) {
    if ((ayahWord.getWord().get(0).getSurahId() == 9)) {
      holder.bismilla.setVisibility(View.GONE);

    } else if (ayahWord.getWord().get(0).getVerseId() > 1) {

      //  holder.bismilla.setVisibility(View.GONE);

    } else {
//      holder.bismilla.setVisibility(View.VISIBLE);

    }
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void setChapterInfo(OrignalFlowAyahWordAdapter.ItemViewAdapter holder, CorpusAyahWord verse) {


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
    //  holder.surah_info.setTextColor(context.getResources().getColor(R.color.colorOnPrimary));


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


  // getItemCount increasing the position to 1. This will be the row of header
  @Override
  public int getItemCount() {
    return ayahWordArrayList.size() + 1;
  }

  @Override
  public void onItemClick(View v, int position) {

  }

  @Override
  public void onItemLongClick(int position, View v) {

  }


  private class HeaderViewHolder extends RecyclerView.ViewHolder {
    TextView tvSura, tvRukus, tvVerses;
    ImageView ivSurahIcon,ivLocationmakki,ivLocationmadani;



    public HeaderViewHolder(View view) {
      super(view);
      ivLocationmakki=    view.findViewById(R.id.ivLocationmakki);
      ivLocationmadani=    view.findViewById(R.id.ivLocationmadani);
      ivSurahIcon = view.findViewById(R.id.ivSurahIcon);
      tvVerses = (TextView) view.findViewById(R.id.tvVerses);
      tvRukus = (TextView) view.findViewById(R.id.tvRukus);
      tvSura = (TextView) view.findViewById(R.id.tvSura);
    }
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


    public TextView verse_idTextView;
    public final TextView quran_jalalayn;
    public final TextView quran_transliteration;
    //  public final com.nex3z.flowlayout.FlowLayout  flow_word_by_word;
    com.example.utility.FlowLayout flow_word_by_word;

    public final TextView translate_textView;
    public final TextView arabic_textView;
    public final TextView erab_textView;
    public final TextView surah_info;
    public final TextView bismilla;
    public final TextView quran_textView;
    public final TextView quran_transliterationnote;
    public final TextView quran_jalalaynnote;
    public final TextView erab_textViewnote;
    public final TextView translate_textViewnote;
    public final ImageView bookmark,bookmarkView;
    public final ImageView expandImageButton;
    public ItemViewHolder(View view) {
      super(view);
      view.setTag(this);
      bookmarkView=view.findViewById(R.id.bookmarkView);
      bismilla = view.findViewById(R.id.bismillah);
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
      bookmark.setOnClickListener(this);
      view.setOnClickListener(this);
      view.setOnLongClickListener(this);

      arabic_textView.setOnClickListener(this);

      bookmark.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          Toast.makeText(context.getApplicationContext(), "long", Toast.LENGTH_SHORT).show();
          return false;
        }
      });
      arabic_textView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          Toast.makeText(context.getApplicationContext(), "long", Toast.LENGTH_SHORT).show();
          return false;
        }
      });


      arabic_textView.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {


          Bundle dataBundle = new Bundle();
          //    dataBundle.putInt(SURAH_ID, allofQuran.get());dataBundle.putInt(AYAHNUMBER, Math.toIntExact(word.getVerseId()));

          //  dataBundle.putInt(WORDNUMBER, Math.toIntExact(word.getWordno()));
          dataBundle.putString(SURAH_ARABIC_NAME, SurahName);
          //  LoadItemList(dataBundle);

        }
      });


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


}
