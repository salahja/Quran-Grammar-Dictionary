package com.example.utility;

import static android.graphics.Color.CYAN;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;
import static com.example.Constant.BBLUE;
import static com.example.Constant.BCYAN;
import static com.example.Constant.CYANLIGHTEST;
import static com.example.Constant.FORESTGREEN;
import static com.example.Constant.GOLD;
import static com.example.Constant.GREENDARK;
import static com.example.Constant.GREENYELLOW;
import static com.example.Constant.MIDNIGHTBLUE;
import static com.example.Constant.ORANGE400;
import static com.example.Constant.OVAL;
import static com.example.Constant.RECKT;
import static com.example.Constant.WBURNTUMBER;
import static com.example.Constant.WHOTPINK;
import static com.example.Constant.adjectivespanDark;
import static com.example.Constant.adjectivespanLight;
import static com.example.Constant.ammendedparticle;
import static com.example.Constant.answerspanDark;
import static com.example.Constant.answerspanLight;
import static com.example.Constant.certainityspanDark;
import static com.example.Constant.certainityspanLight;
import static com.example.Constant.deepburnsienna;
import static com.example.Constant.demonstrativespanDark;
import static com.example.Constant.demonstrativespanLight;
import static com.example.Constant.determinerspanDark;
import static com.example.Constant.determinerspanLight;
import static com.example.Constant.emphspanDark;
import static com.example.Constant.emphspanLight;
import static com.example.Constant.eqspanlight;
import static com.example.Constant.halspanDark;
import static com.example.Constant.harfinnaspanDark;
import static com.example.Constant.harfismspanDark;
import static com.example.Constant.harfkhabarspanDark;
import static com.example.Constant.harfsababiaspanDark;
import static com.example.Constant.harfsababiaspanLight;
import static com.example.Constant.harfshartspanDark;
import static com.example.Constant.inceptivepartile;
import static com.example.Constant.interrogativespanDark;
import static com.example.Constant.interrogativespanLight;
import static com.example.Constant.jawabshartspanDark;
import static com.example.Constant.lamimpvspanDark;
import static com.example.Constant.lamimpvspanLight;
import static com.example.Constant.lamtaleelspandDark;
import static com.example.Constant.lamtaleelspandLight;
import static com.example.Constant.locationadverspanDark;
import static com.example.Constant.locationadverspanLight;
import static com.example.Constant.masdariaspanDark;
import static com.example.Constant.masdariaspanLight;
import static com.example.Constant.mudhafspansDark;
import static com.example.Constant.nasabspanDark;
import static com.example.Constant.nasabspanLight;
import static com.example.Constant.negativespanDark;
import static com.example.Constant.negativespanLight;
import static com.example.Constant.nounspanDark;
import static com.example.Constant.nounspanLight;
import static com.example.Constant.particlespanDark;
import static com.example.Constant.particlespanLight;
import static com.example.Constant.prepositionspanDark;
import static com.example.Constant.prepositionspanLight;
import static com.example.Constant.prohibitionspanDark;
import static com.example.Constant.prohibitionspanLight;
import static com.example.Constant.pronounspanDark;
import static com.example.Constant.pronounspanLight;
import static com.example.Constant.propernounspanDark;
import static com.example.Constant.propernounspanLight;
import static com.example.Constant.prussianblue;
import static com.example.Constant.relativespanDark;
import static com.example.Constant.relativespanLight;
import static com.example.Constant.restrictivespanDark;
import static com.example.Constant.restrictivespanLight;
import static com.example.Constant.resultparticlespanDark;
import static com.example.Constant.resultparticlespanLight;
import static com.example.Constant.resumtionspanDark;
import static com.example.Constant.shartspanDark;
import static com.example.Constant.sifaspansDark;
import static com.example.Constant.supplementspanLight;
import static com.example.Constant.supplementspoanDark;
import static com.example.Constant.surprisespanDark;
import static com.example.Constant.surprisespanLight;
import static com.example.Constant.timeadverbspanDark;
import static com.example.Constant.timeadverbspanLight;
import static com.example.Constant.verbalnounspanDark;
import static com.example.Constant.verbalnounspanLight;
import static com.example.Constant.verbspanDark;
import static com.example.Constant.verbspanLight;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import com.example.Constant;
import com.example.JustJava.FrameSpan;
import com.example.JustJava.TextBorderSpan;
import com.example.mushafconsolidated.Entities.MousufSifa;
import com.example.mushafconsolidated.Entities.NewKanaEntity;
import com.example.mushafconsolidated.Entities.NewMudhafEntity;
import com.example.mushafconsolidated.Entities.NewNasbEntity;
import com.example.mushafconsolidated.Entities.NewShartEntity;
import com.example.mushafconsolidated.Entities.SifaEntity;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.Utils;
import com.example.mushafconsolidated.model.CorpusAyahWord;
import com.example.mushafconsolidated.model.HarfNasbIndex;
import com.example.mushafconsolidated.model.IdhafaIntegarsPairs;
import com.example.mushafconsolidated.model.SpanIndex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class CorpusUtilityorig {

  //todo harfnasm 2nd surah verfified
  //todo shart done till 4th
  private static final int PERMISSION_REQUEST_CODE = 100;
  private final String preferences;
  private final String preferencess;

  private Context context;
  private ArrayList<IdhafaIntegarsPairs> idhafaarraylist;
  SpanIndex spanDark;
  private IdhafaIntegarsPairs indexsPairs;
  int suracounter;
  private HashMap<String, List<String>> hm;
  // ScaleXSpan sifaspans = new ScaleXSpan( 1.5f);
  //  RelativeSizeSpan sifaspans =   new RelativeSizeSpan(1.4f);
  //   MaskFilterSpan sifaspans = new MaskFilterSpan(new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f));

  private SpannableString spannableversemudhaf;

  public SpannableString setSpanMudhafSifaShartHarfNasb(int surah, int ayah, ArrayList<NewMudhafEntity> mudhafAll, ArrayList<SifaEntity> sifall, ArrayList<NewShartEntity> shartAll, ArrayList<NewNasbEntity> nasbIndAll, String qurantext) {

    SpannableString str = new SpannableString(qurantext);


    // extracted(surah, ayah, mudhafAll, sifall, shartAll, nasbIndAll, qurantext, str);


    return str;


  }

  private void extracted(int surah, int ayah, ArrayList<NewMudhafEntity> mudhafAll, ArrayList<SifaEntity> sifall, ArrayList<NewShartEntity> shartAll, ArrayList<NewNasbEntity> nasbIndAll, String qurantext, SpannableString str) {
    for (NewMudhafEntity mudhaf : mudhafAll) {
      int ayah1 = mudhaf.getAyah();
      int surah1 = mudhaf.getSurah();
      boolean b = surah1 == surah && ayah1 == ayah;
      if (b) {

        str.setSpan(mudhafspansDark, mudhaf.getStartindex(), mudhaf.getEndindex(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

      }

    }
    for (SifaEntity sifa : sifall) {
      int ayah1 = sifa.getAyah();
      int surah1 = sifa.getSurah();
      boolean b = surah1 == surah && ayah1 == ayah;
      if (b) {
        try {
          str.setSpan(sifaspansDark, sifa.getStartindex(), sifa.getEndindex(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (IndexOutOfBoundsException e) {
          //  System.out.println(surah + "  " + ayah + "  " + qurantext);
        }

      }

    }

    for (NewShartEntity shartEntity : shartAll) {
      int ayah1 = shartEntity.getAyah();
      int surah1 = shartEntity.getSurah();
      boolean b = surah1 == surah && ayah1 == ayah;
      if (b) {
        str.setSpan(new ForegroundColorSpan(GOLD), shartEntity.getIndexstart(), shartEntity.getIndexend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        try {
          str.setSpan(new ForegroundColorSpan(GREEN), shartEntity.getShartindexstart(), shartEntity.getShartindexend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (IndexOutOfBoundsException e) {
          //  System.out.println(surah + "  " + ayah + "  " + qurantext);
        }


        try {
          str.setSpan(new ForegroundColorSpan(CYAN), shartEntity.getJawabshartindexstart(), shartEntity.getJawabshartindexend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (IndexOutOfBoundsException e) {
          //  System.out.println(surah + "  " + ayah + "  " + qurantext);
        }


      }

    }


    for (NewNasbEntity nasb : nasbIndAll) {
      String harfofverse = "";
      String ismofverse = "";
      String khabarofinna = "";
      int start = nasb.getIndexstart();
      int end = nasb.getIndexend();
      int ismstart = nasb.getIsmstart();
      int ismend = nasb.getIsmend();
      int khabarstart = nasb.getKhabarstart();
      int khabarend = nasb.getKhabarend();
      try {
        str.setSpan(harfinnaspanDark, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      } catch (IndexOutOfBoundsException e) {

        //System.out.println(nasb.getSurah() + "," + nasb.getAyah());
      }
      try {
        str.setSpan(harfismspanDark, ismstart, ismend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      } catch (IndexOutOfBoundsException e) {

        //System.out.println(nasb.getSurah() + "," + nasb.getAyah());
      }
      try {
        str.setSpan(harfkhabarspanDark, khabarstart, khabarend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      } catch (IndexOutOfBoundsException e) {

        //System.out.println(nasb.getSurah() + "," + nasb.getAyah());
      }

    }
  }

  public void highLightVerbs(ArrayList<CorpusAyahWord> corpusayahWordArrayList, int surah_id) {

    for (CorpusAyahWord corpus : corpusayahWordArrayList) {

      SpannableString verse = corpus.getSpannableverse();
      corpus.getWord().size();
      Paint p = new Paint();
      p.setColor(RED);


      for (int index = 0; index < corpus.getWord().size(); index++) {
        FrameSpan frameshart = new FrameSpan(CYAN, 2f, RECKT);
        FrameSpan framejawabshart = new FrameSpan(YELLOW, 2f, RECKT);
        if (corpus.getWord().get(index).getTagone().equals("V") ||
              corpus.getWord().get(index).getTagtwo().equals("V") ||
              corpus.getWord().get(index).getTagthree().equals("V") ||
              corpus.getWord().get(index).getTagfour().equals("V") ||
              corpus.getWord().get(index).getTagfive().equals("V")) {

          String ara = corpus.getWord().get(index).getAraone() + corpus.getWord().get(index).getAratwo() +
                corpus.getWord().get(index).getArathree() +
                corpus.getWord().get(index).getArafour() +
                corpus.getWord().get(index).getArafive();

          int indexof = verse.toString().indexOf(ara);


          if (indexof != -1) {
            verse.setSpan(verbspanDark, indexof, indexof + ara.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          }
        } else if (corpus.getWord().get(index).getTagone().equals("N") ||
              corpus.getWord().get(index).getTagtwo().equals("N") ||
              corpus.getWord().get(index).getTagthree().equals("N") ||
              corpus.getWord().get(index).getTagfour().equals("N") ||
              corpus.getWord().get(index).getTagfive().equals("N")) {

          String ara = corpus.getWord().get(index).getAraone() + corpus.getWord().get(index).getAratwo() +
                corpus.getWord().get(index).getArathree() +
                corpus.getWord().get(index).getArafour() +
                corpus.getWord().get(index).getArafive();
          int indexof = verse.toString().indexOf(ara);
          if (indexof != -1) {
            verse.setSpan(nounspanDark, indexof, indexof + ara.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          }

        }


      }

    }
  }


  enum SpanType {BULLET, QUOTE, UNDERLINE, STRIKETHROUGH, BGCOLOR, FGCOLOR, MASKFILTER_EMBOSS, SUBSCRIPT, STYLE, ABSOLUTE_SIZE_spanDark, RELATIVE_SIZE_spanDark, TEXTAPPEARANCE_spanDark, SUPERSCRIPT, LOCALE_spanDark, SCALEX_spanDark, TYPEFACE_spanDark, IMAGE_spanDark, MASKFILTER_BLUR, ALIGNMENT_STANDARD}

  ;

  public int getSuracounter() {
    return suracounter;
  }

  public void setSuracounter(int suracounter) {
    this.suracounter = suracounter;
  }

  private ArrayList<String> values;
  boolean ismausoofbrokenplural, ismousoofsifa;

  final int mudhafColoragainstBlack;
  final int mausofColoragainstBlack;
  final int sifatColoragainstBlack;
  final int brokenPlurarColoragainstBlack;

  private ArrayList<String> tags;

  public int getSuraid() {
    return suraid;
  }

  public void setSuraid(int suraid) {
    this.suraid = suraid;
  }

  public int getAyahid() {
    return ayahid;
  }

  public void setAyahid(int ayahid) {
    this.ayahid = ayahid;
  }

  private String secondkhabar;
  ArrayList<String> mousufverses = new ArrayList<>();
  public int suraid, ayahid;

  public String getSecondkhabar() {
    return secondkhabar;
  }

  public void setSecondkhabar(String secondkhabar) {
    this.secondkhabar = secondkhabar;
  }

  MousufSifa mousufSifa = new MousufSifa();

  final ArrayList<MousufSifa> mousufSifaArrayList = new ArrayList<>();
  final ArrayList<MousufSifa> NEWmousufSifaArrayList = new ArrayList<>();

  final ArrayList<MousufSifa> MUDHAFSPANS = new ArrayList<>();

  private SpannableStringBuilder verses;
  HashMap<String, Integer> phrases;
  LinkedHashMap<String, Integer> LinkedPhrases;

  LinkedHashMap<String, Integer> RefLinkedPhrases;
  ArrayList<HarfNasbIndex> harfNasbIndexArrayList;

  ArrayList<SpanIndex> spanIndexArrayList = new ArrayList<>();
  private StringBuilder harf;
  private String regex;

  public boolean isIsmousoofsifa() {
    return ismousoofsifa;
  }

  public void setIsmousoofsifa(boolean ismousoofsifa) {
    this.ismousoofsifa = ismousoofsifa;
  }

  public boolean isIsmausoofbrokenplural() {
    return ismausoofbrokenplural;
  }

  public void setIsmausoofbrokenplural(boolean ismausoofbrokenplural) {
    this.ismausoofbrokenplural = ismausoofbrokenplural;
  }

  public CorpusUtilityorig(Context context) {
    this.context = context;
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    preferences = prefs.getString("theme", "dark");
    preferencess = SharedPref.themePreferences();
    /*
    String preferences = SharedPref.themePreferences();
    if (preferences.equals("dark")) {
      maincolortag = Constant.BYELLOW;
      pronouncolortag = BBLUE;
      fourcolortag = Constant.BYELLOW;


    } else {
      maincolortag = GOLD;
      pronouncolortag = WMIDNIHTBLUE;
      fourcolortag = Constant.GREENDARK;


    }
*/


    if (preferences.equals("dark")) {
      mausofColoragainstBlack = prefs.getInt("mausoofblack", RED);
      mudhafColoragainstBlack = prefs.getInt("mudhafblack", Color.CYAN);
      sifatColoragainstBlack = prefs.getInt("sifatblack", Color.YELLOW);
      brokenPlurarColoragainstBlack = prefs.getInt("brokenblack", Color.GREEN);


    } else {

      mudhafColoragainstBlack = prefs.getInt("mausoofwhite", context.getResources().getColor(R.color.burntamber));
      mausofColoragainstBlack = prefs.getInt("mudhafwhite", context.getResources().getColor(R.color.kashmirigreen));
      sifatColoragainstBlack = prefs.getInt("sifatwhite", context.getResources().getColor(R.color.darkgreen));
      brokenPlurarColoragainstBlack = prefs.getInt("brokenwhite", context.getResources().getColor(R.color.midnightblue));


    }
  }


  public static SpannableString NewSetWordSpanTag(String tagone, String tagtwo, String tagthree, String tagfour, String tagfive, String arafive, String arafour, String arathree, String aratwo, String araone) {
    SpannableString str = null;
    int tagcounter = 0;
    boolean b = tagone.length() > 0;
    boolean bb = tagtwo.length() > 0;

    boolean bbb = tagthree.length() > 0;
    boolean bbbb = tagfour.length() > 0;
    boolean bbbbb = tagfive.length() > 0;
    if (b && (!bb && !bbb && !bbbb && !bbbbb)) {
      tagcounter = 1;
    } else if (b && bb && (!bbb && !bbbb && !bbbbb)) {
      tagcounter = 2;
    } else if (b && bb && bbb && (!bbbb && !bbbbb)) {
      tagcounter = 3;
    } else if (b && bb && bbb && bbbb && (!bbbbb)) {
      tagcounter = 4;
    } else if (b && bb && bbb && bbbb && bbbbb) {
      tagcounter = 5;
    }
//
//
    araone = araone.trim();
    aratwo = aratwo.trim();
    arathree = arathree.trim();
    arafour = arafour.trim();
    arafive = arafive.trim();
//
    Map<String, ForegroundColorSpan> spanhash;
    spanhash = getStringForegroundColorSpanMap();
    if (tagcounter == 1) {
      Set<String> strings = spanhash.keySet();


      str = new SpannableString(araone.trim() + aratwo.trim() + arathree.trim());
      str.setSpan(spanhash.get(tagone), 0, araone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


    } else if (tagcounter == 2) {


      str = new SpannableString(aratwo.trim() + araone.trim());
      str.setSpan(spanhash.get(tagtwo), 0, aratwo.trim().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagone), aratwo.length(), araone.length() + aratwo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    } else if (tagcounter == 3) {
      Set<String> strings = spanhash.keySet();
      spanhash.get(tagone);

      str = new SpannableString(arathree.trim() + aratwo.trim() + araone.trim());


      str.setSpan(spanhash.get(tagthree), 0, arathree.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagtwo), arathree.length(), aratwo.length() + arathree.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagone), arathree.length() + aratwo.length(), araone.length() + aratwo.length() + arathree.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


    } else if (tagcounter == 4) {
      int five = arafive.trim().length();
      int four = arafour.trim().length();
      int three = arathree.trim().length();
      int two = aratwo.trim().length();
      int one = araone.trim().length();
      str = new SpannableString(arafour.trim() + arathree.trim() + aratwo.trim() + araone.trim());
     // str.setSpan(spanhash.get(tagfour), 0, arafour.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
     // str.setSpan(spanhash.get(tagthree), araone.length(), araone.length() + aratwo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    //  str.setSpan(spanhash.get(tagtwo), araone.length() + aratwo.length(), araone.length() + aratwo.length() + arathree.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    //  str.setSpan(spanhash.get(tagone), araone.length() + aratwo.length() + arathree.length(), araone.length() + aratwo.length() + arathree.length() + arafour.trim().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

      str.setSpan(spanhash.get(tagfour), 0, arafour.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagthree), arafour.length(), arathree.length()+arafour.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagtwo), arathree.length()+arafour.length(), aratwo.length() + arathree.length()+arafour.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagone), arafour.length()+arathree.length() + aratwo.length(), araone.length() + aratwo.length() + arathree.length()+arafour.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);








    } else if (tagcounter == 5) {


      int five = arafive.trim().length();
      int four = arafour.trim().length();
      int three = arathree.trim().length();
      int two = aratwo.trim().length();
      int one = araone.trim().length();

      str = new SpannableString(arafive.trim() + arafour.trim() + arathree.trim() + aratwo.trim() + araone.trim());
      str.setSpan(spanhash.get(tagfive), two + three + four + five, one + two + three + four + five, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagfour), three + four + five, two + three + four + five, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      //     str.setSpan(resultparticlespanDark, two + three + four, one + two + three + four, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagthree), four + five, three + four + five, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagtwo), five, four + five, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagone), 0, five, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }


    return str;
  }

  StyleSpan style = new StyleSpan(Typeface.BOLD);


  public static SpannableString NewSetWordSpan(String tagone, String tagtwo, String tagthree, String tagfour, String tagfive, String araone, String aratwo, String arathree, String arafour, String arafive) {


    SpannableString str = null;
    int tagcounter = 0;
    boolean b = tagone.length() > 0;
    boolean bb = tagtwo.length() > 0;

    boolean bbb = tagthree.length() > 0;
    boolean bbbb = tagfour.length() > 0;
    boolean bbbbb = tagfive.length() > 0;
    if (b && (!bb && !bbb && !bbbb && !bbbbb)) {
      tagcounter = 1;
    } else if (b && bb && (!bbb && !bbbb && !bbbbb)) {
      tagcounter = 2;
    } else if (b && bb && bbb && (!bbbb && !bbbbb)) {
      tagcounter = 3;
    } else if (b && bb && bbb && bbbb && (!bbbbb)) {
      tagcounter = 4;
    } else if (b && bb && bbb && bbbb && bbbbb) {
      tagcounter = 5;
    }


    araone = araone.trim();
    aratwo = aratwo.trim();
    arathree = arathree.trim();
    arafour = arafour.trim();
    arafive = arafive.trim();
    Map<String, ForegroundColorSpan> spanhash;
    //   SharedPreferences sharedPreferences =
    //      androidx.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
    //   String isNightmode = sharedPreferences.getString("themepref", "dark" );
    //   if (isNightmode.equals("dark")||isNightmode.equals("blue")) {
    spanhash = getStringForegroundColorSpanMap();
    //  }else{
    //   spanhash = getColorSpanforPhrasesLight();
    //  }


    if (tagcounter == 1) {
      Set<String> strings = spanhash.keySet();

      str = new SpannableString(araone.trim());
      str.setSpan(spanhash.get(tagone), 0, araone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


    } else if (tagcounter == 2) {
      str = new SpannableString(araone.trim() + aratwo.trim());
      str.setSpan(spanhash.get(tagone), 0, araone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagtwo), araone.length(), araone.length() + aratwo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    } else if (tagcounter == 3) {
      Set<String> strings = spanhash.keySet();
      spanhash.get(tagone);
      str = new SpannableString(araone.trim() + aratwo.trim() + arathree.trim());
      str.setSpan(spanhash.get(tagone), 0, araone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagtwo), araone.length(), araone.length() + aratwo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagthree), araone.length() + aratwo.length(), araone.length() + aratwo.length() + arathree.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


    } else if (tagcounter == 4) {


      str = new SpannableString(araone.trim() + aratwo.trim() + arathree.trim() + arafour.trim());
      str.setSpan(spanhash.get(tagone), 0, araone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagtwo), araone.length(), araone.length() + aratwo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagthree), araone.length() + aratwo.length(), araone.length() + aratwo.length() + arathree.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagfour), araone.length() + aratwo.length() + arathree.length(), araone.length() + aratwo.length() + arathree.length() + arafour.trim().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      //    str.setSpan(attachedpronoun, araone.length() + aratwo.length() + arathree.length() + arafour.length(), araone.length() + aratwo.length() + arathree.length() + arafour.trim().length() + arafive.trim().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


    } else if (tagcounter == 5) {


      str = new SpannableString(araone.trim() + aratwo.trim() + arathree.trim() + arafour.trim() + arafive.trim());
      str.setSpan(spanhash.get(tagone), 0, araone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagtwo), araone.length(), araone.length() + aratwo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagthree), araone.length() + aratwo.length(), araone.length() + aratwo.length() + arathree.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagfour), araone.length() + aratwo.length() + arathree.length(), araone.length() + aratwo.length() + arathree.length() + arafour.trim().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      str.setSpan(spanhash.get(tagfive), araone.length() + aratwo.length() + arathree.length() + arafour.length(), araone.length() + aratwo.length() + arathree.length() + arafour.trim().length() + arafive.trim().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


    } else {
      str = new SpannableString(araone.trim() + aratwo.trim() + arathree.trim() + arafour.trim() + arafive.trim());
    }


    return str;
  }

  @NonNull
  private static Map<String, ForegroundColorSpan> getStringForegroundColorSpanMap() {
    Map<String, ForegroundColorSpan> spanhash = new HashMap<String,
          ForegroundColorSpan>();
    SharedPreferences sharedPreferences =
          androidx.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
    String isNightmode = sharedPreferences.getString("themepref", "dark");
    if (isNightmode.equals("dark") || isNightmode.equals("blue")) {
      spanhash.put("PN", propernounspanDark);
      spanhash.put("REL", relativespanDark);
      spanhash.put("DEM", demonstrativespanDark);
      spanhash.put("N", nounspanDark);
      spanhash.put("PRON", pronounspanDark);
      spanhash.put("DET", determinerspanDark);
      spanhash.put("V", verbspanDark);
      spanhash.put("P", prepositionspanDark);
      spanhash.put("T", timeadverbspanDark);
      spanhash.put("LOC", locationadverspanDark);
      spanhash.put("ADJ", adjectivespanDark);
      spanhash.put("VN", verbalnounspanDark);
      spanhash.put("EMPH", emphspanDark);// "Emphatic lām prefix(لام التوكيد) ",
      spanhash.put("IMPV", lamimpvspanDark);// "Imperative lāmprefix(لام الامر)",
      spanhash.put("PRP", lamtaleelspandDark);// "Purpose lāmprefix(لام التعليل)",
      spanhash.put("SUB", masdariaspanDark);// "	Subordinating conjunction(حرف مصدري)",
      spanhash.put("ACC", nasabspanDark);// "	Accusative particle(حرف نصب)",
      spanhash.put("ANS", answerspanDark);// "	Answer particle	(حرف جواب)",
      spanhash.put("CAUS", harfsababiaspanDark);// "Particle of cause	(حرف سببية)",
      spanhash.put("CERT", certainityspanDark);// "Particle of certainty	(حرف تحقيق)",
      spanhash.put("CIRC", halspanDark);// "Circumstantial particle	(حرف حال)",

      spanhash.put("CONJ", particlespanDark);// "Coordinating conjunction(حرف عطف)",
      spanhash.put("COND", harfshartspanDark);// "Conditional particle(حرف شرط)",
      spanhash.put("AMD", particlespanDark);// "	Amendment particle(حرف استدراك)	",
      spanhash.put("AVR", particlespanDark);// "	Aversion particle	(حرف ردع)",
      spanhash.put("COM", particlespanDark);// "	Comitative particle	(واو المعية)",
      spanhash.put("EQ", particlespanDark);// "	Equalization particle(حرف تسوية)",
      spanhash.put("EXH", particlespanDark);// "	Exhortation particle(حرف تحضيض)",
      spanhash.put("EXL", particlespanDark);// "	Explanation particle(حرف تفصيل)",
      spanhash.put("EXP", particlespanDark);// "	Exceptive particle	(أداة استثناء)",
      spanhash.put("FUT", particlespanDark);// "	Future particle	(حرف استقبال)",
      spanhash.put("INC", particlespanDark);// "	Inceptive particle	(حرف ابتداء)",
      spanhash.put("INT", particlespanDark);// "	Particle of interpretation(حرف تفسير)",
      spanhash.put("RET", particlespanDark);// "	Retraction particle	(حرف اضراب)",
      spanhash.put("PREV", particlespanDark);// "Preventive particle	(حرف كاف)",
      spanhash.put("VOC", particlespanDark);// "	Vocative particle	(حرف نداء)",
      spanhash.put("INL", particlespanDark);// "	Quranic initials(	(حروف مقطعة	";

      spanhash.put("INTG", interrogativespanDark);// "Interogative particle	(حرف استفهام)",
      spanhash.put("NEG", negativespanDark);// "	Negative particle(حرف نفي)",

      spanhash.put("PRO", prohibitionspanDark);// "	Prohibition particle(حرف نهي)",
      spanhash.put("REM", resumtionspanDark);// "	Resumption particle	(حرف استئنافية)",
      spanhash.put("RES", restrictivespanDark);// "	Restriction particle(أداة حصر)",

      spanhash.put("RSLT", resultparticlespanDark);// "Result particle(حرف واقع في جواب الشرط)",
      spanhash.put("SUP", supplementspoanDark);// "	Supplemental particle	(حرف زائد)",
      spanhash.put("SUR", surprisespanDark);// "	Surprise particle	(حرف فجاءة)",
    } else {

      spanhash.put("PN", propernounspanLight);
      spanhash.put("REL", relativespanLight);
      spanhash.put("DEM", demonstrativespanLight);
      spanhash.put("N", nounspanLight);
      spanhash.put("PRON", pronounspanLight);
      spanhash.put("DET", determinerspanLight);
      spanhash.put("V", verbspanLight);
      spanhash.put("P", prepositionspanLight);
      spanhash.put("T", timeadverbspanLight);
      spanhash.put("LOC", locationadverspanLight);
      spanhash.put("ADJ", adjectivespanLight);
      spanhash.put("VN", verbalnounspanLight);
      spanhash.put("EMPH", emphspanLight);// "Emphatic lām prefix(لام التوكيد) ",
      spanhash.put("IMPV", lamimpvspanLight);// "Imperative lāmprefix(لام الامر)",
      spanhash.put("PRP", lamtaleelspandLight);// "Purpose lāmprefix(لام التعليل)",
      spanhash.put("SUB", masdariaspanLight);// "	Subordinating conjunction(حرف مصدري)",
      spanhash.put("ACC", nasabspanLight);// "	Accusative particle(حرف نصب)",
      spanhash.put("ANS", answerspanLight);// "	Answer particle	(حرف جواب)",
      spanhash.put("CAUS", harfsababiaspanLight);// "Particle of cause	(حرف سببية)",
      spanhash.put("CERT", certainityspanLight);// "Particle of certainty	(حرف تحقيق)",
      spanhash.put("CIRC", particlespanLight);// "Circumstantial particle	(حرف حال)",

      spanhash.put("CONJ", particlespanLight);// "Coordinating conjunction(حرف عطف)",
      spanhash.put("COND", eqspanlight);// "Conditional particle(حرف شرط)",
      spanhash.put("AMD", ammendedparticle);// "	Amendment particle(حرف استدراك)	",
      spanhash.put("AVR", particlespanLight);// "	Aversion particle	(حرف ردع)",
      spanhash.put("COM", particlespanLight);// "	Comitative particle	(واو المعية)",
      spanhash.put("EQ", particlespanLight);// "	Equalization particle(حرف تسوية)",
      spanhash.put("EXH", particlespanLight);// "	Exhortation particle(حرف تحضيض)",
      spanhash.put("EXL", particlespanLight);// "	Explanation particle(حرف تفصيل)",
      spanhash.put("EXP", particlespanLight);// "	Exceptive particle	(أداة استثناء)",
      spanhash.put("FUT", particlespanLight);// "	Future particle	(حرف استقبال)",
      spanhash.put("INC", nasabspanLight);// "	Inceptive particle	(حرف ابتداء)",
      spanhash.put("INT", particlespanLight);// "	Particle of interpretation(حرف تفسير)",
      spanhash.put("RET", particlespanLight);// "	Retraction particle	(حرف اضراب)",
      spanhash.put("PREV", inceptivepartile);// "Preventive particle	(حرف كاف)",
      spanhash.put("VOC", particlespanLight);// "	Vocative particle	(حرف نداء)",
      spanhash.put("INL", particlespanLight);// "	Quranic initials(	(حروف مقطعة	";

      spanhash.put("INTG", interrogativespanLight);// "Interogative particle	(حرف استفهام)",
      spanhash.put("NEG", negativespanLight);// "	Negative particle(حرف نفي)",

      spanhash.put("PRO", prohibitionspanLight);// "	Prohibition particle(حرف نهي)",
      spanhash.put("REM", particlespanLight);// "	Resumption particle	(حرف استئنافية)",
      spanhash.put("RES", restrictivespanLight);// "	Restriction particle(أداة حصر)",

      spanhash.put("RSLT", resultparticlespanLight);// "Result particle(حرف واقع في جواب الشرط)",
      spanhash.put("SUP", supplementspanLight);// "	Supplemental particle	(حرف زائد)",
      spanhash.put("SUR", surprisespanLight);// "	Surprise particle	(حرف فجاءة)",
    }


    return spanhash;
  }

  @NonNull
  private static Map<String, ForegroundColorSpan> getColorSpanforPhrasesLight() {
    Map<String, ForegroundColorSpan> spanhash = new HashMap<String,
          ForegroundColorSpan>();

    spanhash.put("PN", propernounspanLight);
    spanhash.put("REL", relativespanLight);
    spanhash.put("DEM", demonstrativespanLight);
    spanhash.put("N", nounspanLight);
    spanhash.put("PRON", pronounspanLight);
    spanhash.put("DET", determinerspanLight);
    spanhash.put("V", verbspanLight);
    spanhash.put("P", prepositionspanLight);
    spanhash.put("T", timeadverbspanLight);
    spanhash.put("LOC", locationadverspanLight);
    spanhash.put("ADJ", adjectivespanLight);
    spanhash.put("VN", verbalnounspanLight);
    spanhash.put("EMPH", emphspanLight);// "Emphatic lām prefix(لام التوكيد) ",
    spanhash.put("IMPV", lamimpvspanLight);// "Imperative lāmprefix(لام الامر)",
    spanhash.put("PRP", lamtaleelspandLight);// "Purpose lāmprefix(لام التعليل)",
    spanhash.put("SUB", masdariaspanLight);// "	Subordinating conjunction(حرف مصدري)",
    spanhash.put("ACC", nasabspanLight);// "	Accusative particle(حرف نصب)",
    spanhash.put("ANS", answerspanLight);// "	Answer particle	(حرف جواب)",
    spanhash.put("CAUS", harfsababiaspanLight);// "Particle of cause	(حرف سببية)",
    spanhash.put("CERT", certainityspanLight);// "Particle of certainty	(حرف تحقيق)",
    spanhash.put("CIRC", particlespanLight);// "Circumstantial particle	(حرف حال)",

    spanhash.put("CONJ", particlespanLight);// "Coordinating conjunction(حرف عطف)",
    spanhash.put("COND", eqspanlight);// "Conditional particle(حرف شرط)",
    spanhash.put("AMD", ammendedparticle);// "	Amendment particle(حرف استدراك)	",
    spanhash.put("AVR", particlespanLight);// "	Aversion particle	(حرف ردع)",
    spanhash.put("COM", particlespanLight);// "	Comitative particle	(واو المعية)",
    spanhash.put("EQ", particlespanLight);// "	Equalization particle(حرف تسوية)",
    spanhash.put("EXH", particlespanLight);// "	Exhortation particle(حرف تحضيض)",
    spanhash.put("EXL", particlespanLight);// "	Explanation particle(حرف تفصيل)",
    spanhash.put("EXP", particlespanLight);// "	Exceptive particle	(أداة استثناء)",
    spanhash.put("FUT", particlespanLight);// "	Future particle	(حرف استقبال)",
    spanhash.put("INC", nasabspanLight);// "	Inceptive particle	(حرف ابتداء)",
    spanhash.put("INT", particlespanLight);// "	Particle of interpretation(حرف تفسير)",
    spanhash.put("RET", particlespanLight);// "	Retraction particle	(حرف اضراب)",
    spanhash.put("PREV", inceptivepartile);// "Preventive particle	(حرف كاف)",
    spanhash.put("VOC", particlespanLight);// "	Vocative particle	(حرف نداء)",
    spanhash.put("INL", particlespanLight);// "	Quranic initials(	(حروف مقطعة	";

    spanhash.put("INTG", interrogativespanLight);// "Interogative particle	(حرف استفهام)",
    spanhash.put("NEG", negativespanLight);// "	Negative particle(حرف نفي)",

    spanhash.put("PRO", prohibitionspanLight);// "	Prohibition particle(حرف نهي)",
    spanhash.put("REM", particlespanLight);// "	Resumption particle	(حرف استئنافية)",
    spanhash.put("RES", restrictivespanLight);// "	Restriction particle(أداة حصر)",

    spanhash.put("RSLT", resultparticlespanLight);// "Result particle(حرف واقع في جواب الشرط)",
    spanhash.put("SUP", supplementspanLight);// "	Supplemental particle	(حرف زائد)",
    spanhash.put("SUR", surprisespanLight);// "	Surprise particle	(حرف فجاءة)",

    return spanhash;
  }


  public void SetMousufSifaDB(ArrayList<CorpusAyahWord> corpusayahWordArrayList, int surah_id) {
    Utils utils = new Utils(DarkThemeApplication.getContext());
    final ArrayList<SifaEntity> surah = utils.getSifabySurah(surah_id);
    SpannableStringBuilder spannableverse = null;
    SpannableString spannableString = null;
//todo 2 188 iza ahudu
    //todo 9;92 UNCERTAIN
    //TODO 9:94 JAWABHARMAHDOOF 9 95 JAWABHSARMAHODFF
    //TO 9;118 IZA IN THE MEANING OF HEENA AND 9 122 IZA AS HEENA


    for (SifaEntity sifaEntity : surah) {

      int indexstart = sifaEntity.getStartindex();
      int indexend = sifaEntity.getEndindex();
      //  sifaspans = new BackgroundColorSpan(WBURNTUMBER);
      FrameSpan frameshartharf = new FrameSpan(GREEN, 2, RECKT);
      FrameSpan frameshart = new FrameSpan(CYAN, 2f, RECKT);
      FrameSpan framejawabshart = new FrameSpan(YELLOW, 2f, RECKT);

      boolean iscolored = true;

      SifaSpansSetup(iscolored, frameshartharf, frameshart, corpusayahWordArrayList, sifaEntity, indexstart, indexend);


    }
  }

  private void SifaSpansSetup(boolean iscolored, FrameSpan frameshartharf, FrameSpan frameshart, ArrayList<CorpusAyahWord> corpusayahWordArrayList, SifaEntity sifaEntity, int indexstart, int indexend) {
    SpannableString spannableverse;
    SpannableString spannableString;
    try {
      spannableverse = corpusayahWordArrayList.get(sifaEntity.getAyah() - 1).getSpannableverse();


      spannableString = SpannableString.valueOf(corpusayahWordArrayList.get(sifaEntity.getAyah() - 1).getSpannableverse());
      try {

        if (indexstart == 0 || indexstart > 0) {
          if (iscolored) {
         //   sifaspansDark = getSpancolor(preferences, false);
              if(preferences.equals("dark")||preferences.equals("blue"))
              {
               sifaspansDark=new BackgroundColorSpan(WBURNTUMBER);
              }else{
                sifaspansDark=new BackgroundColorSpan(CYANLIGHTEST);
             }


            spannableverse.setSpan(sifaspansDark, indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //   spannableverse.setSpan(new UnderlineSpan(),indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


          } else
            spannableverse.setSpan(frameshartharf, indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        }


      } catch (IndexOutOfBoundsException e) {
        //System.out.println(e.getMessage());
      }

    } catch (IndexOutOfBoundsException e) {
      //System.out.println(e.getMessage());
    }

  }


  public static BackgroundColorSpan getSpancolor(String preferences, boolean b) {
    BackgroundColorSpan sifaspansDark = Constant.sifaspansDark;
    BackgroundColorSpan mudhafspansDark = Constant.mudhafspansDark;
    if (b) {

      if (preferences.equals("dark") || preferences.equals("blue")) {
        Constant.mudhafspansDark = new BackgroundColorSpan(MIDNIGHTBLUE);
        return mudhafspansDark;
      } else {
        Constant.mudhafspansDark = new BackgroundColorSpan(GREENYELLOW);
        return mudhafspansDark;
      }

    } else {

      if (preferences.equals("dark") || preferences.equals("blue")) {
        Constant.sifaspansDark = new BackgroundColorSpan(WBURNTUMBER);
        return sifaspansDark;
      } else {
        Constant.sifaspansDark = new BackgroundColorSpan(CYANLIGHTEST);
        return sifaspansDark;
      }
    }


  }


  public void newnewHarfNasbDb(ArrayList<CorpusAyahWord> corpusayahWordArrayList, int surah_id) {


    SpanIndex spans;
    // NewNasbEntity harfNasbIndex = new NewNasbEntity();
    // spans = new SpanIndex();


    SpannableStringBuilder str = null;
    Utils utils = new Utils(DarkThemeApplication.getContext());

    final ArrayList<NewNasbEntity> harfnasb = utils.getHarfNasbIndexesnew(surah_id);
//TODO SURA10 7 INNA ISM INNALIZINA(0,5,6,9 AND KHABR IN 10;8 oolika(0,12,len33)
    if (surah_id == 2 || surah_id == 3 || surah_id == 4 || surah_id == 5 || surah_id == 6 || surah_id == 7 ||
          surah_id == 8 || surah_id == 9 || surah_id == 10 || surah_id == 59 || surah_id == 60 || surah_id == 61 || surah_id == 62 || surah_id == 63
          || surah_id == 64 || surah_id == 65 || surah_id == 66 || surah_id == 67 || surah_id == 68
          || surah_id == 69 || surah_id == 70 || surah_id == 71 || surah_id == 72 || surah_id == 73


          || surah_id == 74 || surah_id == 75 || surah_id == 76 || surah_id == 77 || surah_id == 78
          || surah_id > 78 && surah_id <= 114


    ) {
      SpannableString spannableverse = null;
      SpannableString spannableString = null;
      ArrayList<String> err = new ArrayList<>();
      for (NewNasbEntity nasb : harfnasb) {
        int surah = nasb.getSurah();
        int aya = nasb.getAyah();


        int indexstart = nasb.getIndexstart();
        int indexend = nasb.getIndexend();
        int ismstartindex = nasb.getIsmstart();
        int ismendindex = nasb.getIsmend();
        int khabarstart = nasb.getKhabarstart();
        int khabarend = nasb.getKhabarend();

        spannableverse = corpusayahWordArrayList.get(nasb.getAyah() - 1).getSpannableverse();
        boolean iscolored = true;

        //  SpannableString spannableverse = SpannableString.valueOf(corpusayahWordArrayList.get(nasb.getAyah() - 1).getSpannableverse());

        //   harfinnaspanDark=new ForegroundColorSpan(GREEN);

        //  harfkhabarspanDark=new ForegroundColorSpan(YELLOW);

        try {
          if (preferences.equals("dark") || preferences.equals("blue")) {
            harfinnaspanDark = new ForegroundColorSpan(GREEN);
          } else {
            harfinnaspanDark = new ForegroundColorSpan(GREENDARK);
          }

          //  harfinnaspanDark=new ForegroundColorSpan(GREEN);
          spannableverse.setSpan(harfinnaspanDark, indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        } catch (IndexOutOfBoundsException e) {
          //    System.out.println(nasb.getSurah() + ":" + nasb.getAyah());
          err.add(nasb.getSurah() + ":" + nasb.getAyah());
        }


        try {
          //    spannableverse.setSpan(new ForegroundColorSpan(GOLD), ismindexone, ismindexone + lenism1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          if (preferences.equals("dark") || preferences.equals("blue")) {
            harfismspanDark = new ForegroundColorSpan(BCYAN);
          } else {
            harfismspanDark = new ForegroundColorSpan(prussianblue);
          }


          spannableverse.setSpan(harfismspanDark, ismstartindex, ismendindex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        } catch (IndexOutOfBoundsException e) {
          //     System.out.println(nasb.getSurah() + ":" + nasb.getAyah());
          err.add(nasb.getSurah() + ":" + nasb.getAyah());
        }

        try {
          if (preferences.equals("dark") || preferences.equals("blue")) {
            harfkhabarspanDark = new ForegroundColorSpan(YELLOW);
          } else {
            harfkhabarspanDark = new ForegroundColorSpan(deepburnsienna);
          }


          spannableverse.setSpan(harfkhabarspanDark, khabarstart, khabarend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        } catch (IndexOutOfBoundsException e) {
          //   System.out.println(nasb.getSurah() + ":" + nasb.getAyah());
          err.add(nasb.getSurah() + ":" + nasb.getAyah());
        }


      }

      for (String s : err) {
        //    System.out.println(s);

      }

    } else {
      //    newHarfNasb(corpusayahWordArrayList);
    }


  }


  public void setMudhafFromDB(ArrayList<CorpusAyahWord> corpusayahWordArrayList, int surah_id) {
    Utils utils = new Utils(DarkThemeApplication.getContext());

    final ArrayList<NewMudhafEntity> surah = utils.getMudhafSurahNew(surah_id);
    SpannableStringBuilder spannableverse = null;
    SpannableString spannableString = null;
//todo 2 188 iza ahudu
    //todo 9;92 UNCERTAIN
    //TODO 9:94 JAWABHARMAHDOOF 9 95 JAWABHSARMAHODFF
    //TO 9;118 IZA IN THE MEANING OF HEENA AND 9 122 IZA AS HEENA


    for (NewMudhafEntity mudhafen : surah) {

      int indexstart = mudhafen.getStartindex();
      int indexend = mudhafen.getEndindex();

      FrameSpan frameshartharf = new FrameSpan(GREEN, 2, RECKT);
      FrameSpan frameshart = new FrameSpan(CYAN, 2f, RECKT);
      FrameSpan framejawabshart = new FrameSpan(YELLOW, 2f, RECKT);
      boolean iscolored = true;
      // spannableString = SpannableString.valueOf(corpusayahWordArrayList.get(mudhafen.getAyah() - 1).getSpannableverse());

      //   BackgroundColorSpan spanDark=new BackgroundColorSpan(BBLUE);
      //BackgroundColorSpan mudhafspans = new BackgroundColorSpan(MIDNIGHTBLUE);
      //   spanIt(SpanType.BGCOLOR,spannableString, shart, indexstart, indexend, shartsindex, sharteindex, jawabstartindex, jawabendindex);
      MudhafSpansSetup(iscolored, frameshartharf, frameshart, framejawabshart, corpusayahWordArrayList, mudhafen, indexstart, indexend);

    }


  }

  private void MudhafSpansSetup(boolean iscolored, FrameSpan frameshartharf, FrameSpan frameshart, FrameSpan framejawabshart, ArrayList<CorpusAyahWord> corpusayahWordArrayList, NewMudhafEntity mudhafen, int indexstart, int indexend) {
    SpannableString spannableverse;
    SpannableString spannableString;
    try {
      spannableverse = corpusayahWordArrayList.get(mudhafen.getAyah() - 1).getSpannableverse();


      spannableString = SpannableString.valueOf(corpusayahWordArrayList.get(mudhafen.getAyah() - 1).getSpannableverse());
      try {

        if (indexstart == 0 || indexstart > 0) {
          if (iscolored) {

            if (preferences.equals("dark") || preferences.equals("blue")) {
              mudhafspansDark = new BackgroundColorSpan(MIDNIGHTBLUE);

            } else {
              mudhafspansDark = new BackgroundColorSpan(GREENYELLOW);

            }

            spannableverse.setSpan(mudhafspansDark, indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //   spannableverse.setSpan(new UnderlineSpan(),indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


          } else
            spannableverse.setSpan(frameshartharf, indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        }


      } catch (IndexOutOfBoundsException e) {
        //System.out.println(e.getMessage());
      }

    } catch (IndexOutOfBoundsException e) {
      //System.out.println(e.getMessage());
    }

  }

  public void setShart(ArrayList<CorpusAyahWord> corpusayahWordArrayList, int surah_id) {
    Utils utils = new Utils(DarkThemeApplication.getContext());
    final ArrayList<NewShartEntity> surah = utils.getShartSurahNew(surah_id);
    //  final ArrayList<ShartEntity> surah = utils.getShartSurah(surah_id);
    SpannableStringBuilder spannableverse = null;
    SpannableString spannableString = null;
//todo 2 188 iza ahudu
    //todo 9;92 UNCERTAIN
    //TODO 9:94 JAWABHARMAHDOOF 9 95 JAWABHSARMAHODFF
    //TO 9;118 IZA IN THE MEANING OF HEENA AND 9 122 IZA AS HEENA
    if (surah_id > 1 && surah_id <= 10 || surah_id > 57 && surah_id <= 114) {

      for (NewShartEntity shart : surah) {
        if (shart.getSurah() == 3 && shart.getAyah() == 118) {
          //System.out.println("CHECK");
        }
        //    harfshartspanDark=new ForegroundColorSpan(GOLD);
        //  shartspanDark = new ForegroundColorSpan(GREEN);
        //  jawabshartspanDark = new ForegroundColorSpan(CYAN);
        int indexstart = shart.getIndexstart();
        int indexend = shart.getIndexend();
        int shartsindex = shart.getShartindexstart();
        int sharteindex = shart.getShartindexend();
        int jawabstartindex = shart.getJawabshartindexstart();
        int jawabendindex = shart.getJawabshartindexend();
        FrameSpan frameshartharf = new FrameSpan(GREEN, 2, RECKT);
        FrameSpan frameshart = new FrameSpan(CYAN, 2f, RECKT);
        FrameSpan framejawabshart = new FrameSpan(YELLOW, 2f, RECKT);
        boolean iscolored = true;
        try {
          spannableString = SpannableString.valueOf(corpusayahWordArrayList.get(shart.getAyah() - 1).getSpannableverse());
        } catch (ArrayIndexOutOfBoundsException e) {
          //    System.out.println(corpusayahWordArrayList.get(shart.getAyah()));
        }
        BackgroundColorSpan spanDark = new BackgroundColorSpan(BBLUE);

        //   spanIt(SpanType.BGCOLOR,spannableString, shart, indexstart, indexend, shartsindex, sharteindex, jawabstartindex, jawabendindex);
        ColoredShart(spanDark, iscolored, frameshartharf, frameshart, framejawabshart, corpusayahWordArrayList, shart, indexstart, indexend, shartsindex, sharteindex, jawabstartindex, jawabendindex);

      }

    }
  }


  private void ColoredShart(BackgroundColorSpan spanDark, boolean iscolored, FrameSpan frameshartharf, FrameSpan frameshart, FrameSpan framejawabshart, ArrayList<CorpusAyahWord> corpusayahWordArrayList, NewShartEntity shart, int indexstart, int indexend, int shartsindex, int sharteindex, int jawabstartindex, int jawabendindex) {
    SpannableString spannableverse;
    SpannableString spannableString;


    try {
      spannableverse = corpusayahWordArrayList.get(shart.getAyah() - 1).getSpannableverse();


      //   spannableString = SpannableString.valueOf(corpusayahWordArrayList.get(shart.getAyah() - 1).getSpannableverse());
      try {

        if (indexstart == 0 || indexstart > 0) {
          if (iscolored) {
            if (preferences.equals("dark") || preferences.equals("blue")) {
              harfshartspanDark = new ForegroundColorSpan(GOLD);
            } else {
              harfshartspanDark = new ForegroundColorSpan(FORESTGREEN);
            }
            spannableverse.setSpan(harfshartspanDark, indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableverse.setSpan(new UnderlineSpan(), indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


          } else
            spannableverse.setSpan(frameshartharf, indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        }

        if (shartsindex == 0 || shartsindex > 0) {
          if (iscolored) {
            if (preferences.equals("dark") || preferences.equals("blue")) {
              shartspanDark = new ForegroundColorSpan(ORANGE400);
            } else {
              shartspanDark = new ForegroundColorSpan(GREENDARK);
            }
            spannableverse.setSpan(shartspanDark, shartsindex, sharteindex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableverse.setSpan(new UnderlineSpan(), shartsindex, sharteindex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


          } else {
            spannableverse.setSpan(frameshart, shartsindex, sharteindex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

          }
        }


        if (jawabstartindex == 0 || jawabstartindex > 0) {


          Drawable drawable = context.getResources().getDrawable(R.drawable.oval_circle);
          ImageSpan imagespanDark = new ImageSpan(drawable);
          Drawable myDrawable = AppCompatResources.getDrawable(context, R.drawable.oval_circle);
          myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());

          ImageSpan myImagespanDark = new ImageSpan(myDrawable, ImageSpan.ALIGN_BASELINE);
          // mySpannableString.setSpan(myImagespanDark, 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

          if (iscolored) {
            if (preferences.equals("dark") || preferences.equals("blue")) {
              jawabshartspanDark = new ForegroundColorSpan(CYAN);
            } else {
              jawabshartspanDark = new ForegroundColorSpan(WHOTPINK);
            }
            spannableverse.setSpan(jawabshartspanDark, jawabstartindex, jawabendindex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableverse.setSpan(new UnderlineSpan(), jawabstartindex, jawabendindex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


          } else {
            spannableverse.setSpan(framejawabshart, jawabstartindex, jawabendindex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


          }

        }


      } catch (IndexOutOfBoundsException e) {
        //System.out.println(e.getMessage());
      }

    } catch (IndexOutOfBoundsException e) {
      //System.out.println(e.getMessage());
    }
  }

  public void setKana(ArrayList<CorpusAyahWord> corpusayahWordArrayList, int surah_id) {
    Utils utils = new Utils(context.getApplicationContext());
    ArrayList<NewKanaEntity> kanalist = utils.getKananew(surah_id);
    if (surah_id > 1 && surah_id <= 10 || surah_id > 58 && surah_id <= 114) {

      for (NewKanaEntity kana : kanalist) {
        if (kana.getSurah() == 3 && kana.getAyah() == 118) {
          //System.out.println("CHECK");
        }
        int indexstart = kana.getIndexstart();
        int indexend = kana.getIndexend();
        int shartsindex = kana.getKhabarstart();
        int sharteindex = kana.getKhabarend();
        int jawabstartindex = kana.getIsmkanastart();
        int jawabendindex = kana.getIsmkanaend();
        FrameSpan frameshartharf = new FrameSpan(GREEN, 2, OVAL);
        FrameSpan frameshart = new FrameSpan(CYAN, 2f, OVAL);
        FrameSpan framejawabshart = new FrameSpan(YELLOW, 2f, OVAL);
        TextBorderSpan tb = new TextBorderSpan();
        boolean iscolored = false;
        SpannableString spannableverse = corpusayahWordArrayList.get(kana.getAyah() - 1).getSpannableverse();
        try {


          spannableverse.setSpan(new ForegroundColorSpan(GOLD), kana.getIndexstart(), kana.getIndexend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          //    shart.setSpannedverse(spannableverse);


          spannableverse.setSpan(new ForegroundColorSpan(CYAN), kana.getKhabarstart(), kana.getKhabarend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          //   shart.setSpannedverse(spannableverse);


          spannableverse.setSpan(new ForegroundColorSpan(GREENDARK), kana.getIsmkanastart(), kana.getIsmkanaend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          //   shart.setSpannedverse(spannableverse);


        } catch (IndexOutOfBoundsException e) {
          //System.out.println(e.getMessage());
        }


      }

    }
  }


  public static Spannable getSpannableVerses(String arabicword, String quranverses, boolean isnoun) {

    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
    String preferences = prefs.getString("theme", "dark");

    int wordlen = arabicword.length();
    SpannableString str = null;


    int indexOf = quranverses.indexOf(arabicword);
    if (indexOf != -1) {
      str = new SpannableString(quranverses);
      if(preferences.equals("dark")|| preferences.equals("blue")){

        str.setSpan(new ForegroundColorSpan(CYAN), indexOf, indexOf + wordlen, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }else{
        str.setSpan(new ForegroundColorSpan(ContextCompat.getColor(DarkThemeApplication.getContext(), R.color.midnightblue)), indexOf, indexOf + wordlen, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

      }

    } else {
      str = new SpannableString(quranverses);
    }


    return str;


  }

}
