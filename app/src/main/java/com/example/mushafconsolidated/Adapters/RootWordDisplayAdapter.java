package com.example.mushafconsolidated.Adapters;


import static android.view.View.GONE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Constant;
import com.example.mushafconsolidated.Entities.NewCorpusExpandWbwPOJO;
import com.example.mushafconsolidated.Entities.lughat;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.mushafconsolidated.model.SarfSagheerPOJO;
import com.example.utility.QuranGrammarApplication;
import com.example.utility.SharedPref;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;

import org.sj.conjugator.fragments.SarfSagheer;

import java.util.ArrayList;
import java.util.HashMap;

public class RootWordDisplayAdapter extends RecyclerView.Adapter<RootWordDisplayAdapter.ItemViewAdapter> {
    private static final String TAG = "VerseDisplayAdapter";
    private static final String ROOTWORDSTRING = "Root Word:-";
    private static final String LEMMA = "Lemma/Derivative-";
    OnItemClickListener mItemClickListener;
    private Context context;
    private HashMap<String, SpannableStringBuilder> worddetails;
    private HashMap<String, String> vbdetail;
    private ArrayList<NewCorpusExpandWbwPOJO> corpusexpand;
    String ismalatitle = "( الآلَة:)";
    String alaheader = "اِسْم الآلَة";
    String zarfheader = "اِسْم الْظَرفْ";
    private boolean isSarfSagheerThulahi;
    private boolean isverbconjugation;
    private boolean particples;
    private ArrayList<ArrayList> ismfaelmafool;
    private boolean isnoun;
    ListView verblist;
    Integer arabicFontsize;
    private ArrayAdapter<String> verwazanadapter;
    int rootcolor, weaknesscolor, wazancolor;
    private SpannableStringBuilder spannalbeShart;
    private SpannableStringBuilder spannableHarf;
    private SarfSagheerPOJO sarf;
    private SpannableStringBuilder spannable;
    private ArrayList<lughat> worddictorary;
    private ArrayList<String> wazannumberslist;
    private boolean isverb;
    private SarfSagheer sagheer;

    public boolean isSarfSagheerMazeed() {
        return isSarfSagheerMazeed;
    }

    boolean isSarfSagheerMazeed;
    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();


    private ArrayList<SarfSagheer> sarfsagheer;


    public RootWordDisplayAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ItemViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (isverbconjugation) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qaris_view_word_details, parent, false);

        } else if (particples) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordbottomsheetismfaelmafoolsktraditional, parent, false);
            //    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qaris_view_word_details, parent, false);


        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qaris_view_word_details, parent, false);

        }
        return new ItemViewAdapter(view);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewAdapter holder, int position) {
        // verblist = new ListView(context);
        //  VerbsTriliteralDictEntity dictEntity = wazannumberslist.get(position);
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        String quranFont = sharedPreferences.getString("quranFont", "kitab.ttf");
        String theme = sharedPreferences.getString("theme", "dark");

        String width = sharedPreferences.getString("width", "compactWidth");
        if(width.equals("mediumWidth")||width.equals("expandedWidth")) {
            arabicFontsize = sharedPreferences.getInt("pref_font_arabic_key",20);

        }else{
            arabicFontsize = 18;
        }
        if (theme.equals("dark")) {
            //    holder.darkThemeBacground.setBackground(context.getResources().getDrawable(R.drawable.activatedbackgroundblack));
            holder.darkThemeBacground.setCardBackgroundColor(context.getResources().getColor(R.color.odd_item_bg_black));


        } else if (theme.equals("blue")) {
            holder.darkThemeBacground.setCardBackgroundColor(context.getResources().getColor(R.color.background_color_light_darkBlue));

        }
        if (!particples && !isnoun && !isverb) {

            holder.nounoccurancebtn.setVisibility(GONE);
            //  holder.verbOccurancebtn.setVisibility(GONE);
            holder.verbconjugationbtn.setVisibility(GONE);
            holder.verbdetails.setVisibility(GONE);
            holder.noundetails.setVisibility(GONE);

        }
        if (particples || isverb) {
            holder.verbconjugationbtn.setVisibility(View.VISIBLE);
            if (!(worddetails.get("formnumber") == null)) {
                holder.mazeedmeaning.setText(worddetails.get("formnumber"));
                holder.mazeedmeaning.setVisibility(View.VISIBLE);
                holder.mazeedmeaning.setTextSize(arabicFontsize);
            }
            verblist = new ListView(context);
            // ArrayAdapter<String> madapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, wazannumberslist);
            //  holder. list.setAdapter(madapter);
            if (wazannumberslist.size() == 1) {
                holder.rdone.setText(wazannumberslist.get(position));
                holder.rdone.setVisibility(View.VISIBLE);
                holder.rdone.setChecked(true);
                holder.rdone.setTextSize(arabicFontsize);
            }
            if (wazannumberslist.size() == 2) {
                holder.rdone.setText(wazannumberslist.get(position));
                holder.rdone.setVisibility(View.VISIBLE);
                holder.rdone.setChecked(true);
                holder.rdtwo.setText(wazannumberslist.get(position + 1));
                holder.rdtwo.setVisibility(View.VISIBLE);
                holder.rdtwo.setTextSize(arabicFontsize);
                holder.rdone.setTextSize(arabicFontsize);
            }
            if (wazannumberslist.size() == 3) {
                holder.rdone.setText(wazannumberslist.get(position));
                holder.rdone.setVisibility(View.VISIBLE);
                holder.rdone.setChecked(true);
                holder.rdtwo.setText(wazannumberslist.get(position + 1));
                holder.rdtwo.setVisibility(View.VISIBLE);
                holder.rdthree.setText(wazannumberslist.get(position + 2));
                holder.rdthree.setVisibility(View.VISIBLE);
                holder.rdthree.setTextSize(arabicFontsize);
                holder.rdone.setTextSize(arabicFontsize);
                holder.rdtwo.setTextSize(arabicFontsize);
            }
            if (wazannumberslist.size() == 4) {
                holder.rdone.setText(wazannumberslist.get(position));
                holder.rdone.setVisibility(View.VISIBLE);
                holder.rdone.setChecked(true);
                holder.rdtwo.setText(wazannumberslist.get(position + 1));
                holder.rdtwo.setVisibility(View.VISIBLE);

                holder.rdfour.setText(wazannumberslist.get(position + 3));
                holder.rdfour.setVisibility(View.VISIBLE);
                holder.rdfour.setTextSize(arabicFontsize);
                holder.rdthree.setTextSize(arabicFontsize);
                holder.rdone.setTextSize(arabicFontsize);
                holder.rdtwo.setTextSize(arabicFontsize);
                holder.rdfour.setTextSize(arabicFontsize);

            }

        }
        Typeface mequran = Typeface.createFromAsset(context.getAssets(), quranFont);
        Log.d(TAG, "onBindViewHolder: called");

        if (theme.equals("dark") || theme.equals("blue")) {
            rootcolor = Constant.BCYAN;
            weaknesscolor = Constant.BYELLOW;
            wazancolor = Constant.BBLUE;


        } else {
            rootcolor = Constant.WBURNTUMBER;
            weaknesscolor = Constant.KASHMIRIGREEN;
            wazancolor = Constant.WMIDNIHTBLUE;


        }

        int sarfsagheerlen = 0;
        int length = 0;
        Object[] toArray = new Object[0];
        Object[] mazeedArray = new Object[0];
        holder.quranverseShart.setEllipsize(TruncateAt.MARQUEE);
        holder.spannableverse.setEllipsize(TruncateAt.MARQUEE);


        //   holder.verblist.setText(Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY));

/*
        if (null != spannalbeShart) {
            Object[] spans = spannalbeShart.getSpans(0, spannalbeShart.length(), Object.class);
            if (spans.length > 0) {
                holder.quranverseShart.setText(spannalbeShart);
                holder.quranverseShart.setTypeface(mequran);
            }
        }

        if (null != spannableHarf) {
            Object[] spans = spannableHarf.getSpans(0, spannableHarf.length(), Object.class);
            if (spans.length > 0) {
                holder.spannableverse.setText(spannableHarf);
                holder.spannableverse.setTypeface(mequran);
            }
        }
*/
        if (null != spannable) {
            Object[] spans = spannable.getSpans(0, spannable.length(), Object.class);
            if (spans.length > 0) {
                holder.spannableverse.setText(spannable);
                holder.spannableverse.setTypeface(mequran);
                holder.spannableverse.setTextSize(arabicFontsize);
            }
        }

        ArrayList ismfaelmafoolarray = new ArrayList();
        if (isSarfSagheerMazeed || isSarfSagheerThulahi) {

            sagheer = sarfsagheer.get(position);


            sarfsagheerlen = mazeedArray.length;
            holder.mazeedmeaning.setText(vbdetail.get("mazeed"));
            holder.mazeedmeaning.setText(vbdetail.get("formnumber"));
            //    holder.mazeedmeaning.setText(Html.fromHtml(vbdetail.get("mazeed")));
            holder.mazeedmeaning.setVisibility(View.VISIBLE);
            holder.mazeedmeaning.setTextSize(arabicFontsize);
        }

        if (isnoun && !particples) {
            holder.verbconjugationbtn.setVisibility(GONE);
        }



        holder.translationView.setText(worddetails.get("translation"));
        // holder.wordView.setText(word.getWord());
        SpannableStringBuilder word = worddetails.get("word");
        //  String replace = word.toString().replace("\n", "<br/>").replace("\\n", "<br/>");
        holder.translationView.setTextSize(arabicFontsize);

        //    holder.wordView.chipBackgroundColor = getColorStateList
        holder.wordView.setText(worddetails.get("word"));
        StringBuilder vb = new StringBuilder();
        StringBuilder pron = new StringBuilder();
        holder.lemma.setText(vbdetail.get("lemma"));
        holder.wordView.setTextSize(arabicFontsize);
        holder.lemma.setTextSize(arabicFontsize);
        worddetails.get("noun");
        worddetails.get("PRON");
        try {


            if (!worddictorary.isEmpty()) {
                String en_lughat = worddictorary.get(0).getEn_lughat();
                String replace1 = en_lughat.replace("\\n", "<br><p>");
                //.replace("\\n", "<br>");
                //     holder.wordDictionary.setText(Html.fromHtml(replace1));
            }
        } catch (NullPointerException e) {
            System.out.println("root word not found");

        }
        if (worddetails.get("noun") != null) {
            holder.noundetails.setVisibility(View.VISIBLE);

            holder.noundetails.setText(worddetails.get("noun"));
            holder.noundetails.setTextSize(arabicFontsize);
        }
        if (worddetails.get("PRON") != null) {
            holder.pronoundetails.setVisibility(View.VISIBLE);
            pron.append("Pronoun:");
            pron.append(worddetails.get("PRON"));
            holder.pronoundetails.setText(pron.toString());
            holder.pronoundetails.setTextSize(arabicFontsize);
        }

        vb.append("V-");
        if (vbdetail.get("thulathi") != null) {
            vb.append(vbdetail.get("thulathi"));
        }
        if (vbdetail.get("png") != null) {
            vb.append(vbdetail.get("png"));
        }
        if (vbdetail.get("tense") != null) {
            vb.append(vbdetail.get("tense"));
        }
        if (vbdetail.get("voice") != null) {
            vb.append(vbdetail.get("voice"));
        }
        if (vbdetail.get("mood") != null) {
            vb.append(vbdetail.get("mood"));
        }
        if(vbdetail.get("verbmood")!=null){
            holder.moodrules.setVisibility(View.VISIBLE);
            holder.moodrules.setText(vbdetail.get("verbmood"));
            holder.moodrules.setTextSize(arabicFontsize);
        }

        if (vb.length() > 2) {
            holder.verbdetails.setVisibility(View.VISIBLE);
            holder.verbdetails.setText(vb.toString());
            holder.verbdetails.setTextSize(arabicFontsize);
        }

        holder.referenceView.setText(corpusexpand.get(0).getSurah() + ":" + corpusexpand.get(0).getAyah() + ":" + corpusexpand.get(0).getWordno());
        SpannableStringBuilder worddetail = this.worddetails.get("worddetails");
        holder.wdetailstv.setText(worddetail, TextView.BufferType.SPANNABLE);
        holder.referenceView.setTextSize(arabicFontsize);
        holder.wdetailstv.setTextSize(arabicFontsize);

        if (worddetails.get("lemma") != null || worddetails.get("lemma").length() != 0) {
            holder.lemma.setVisibility(View.VISIBLE);
            holder.lemma.setText(LEMMA + this.worddetails.get("lemma"));
            holder.lemma.setTextSize(arabicFontsize);
        }


        if (worddetails.get("root") != null) {

            holder.rootView.setText(ROOTWORDSTRING + worddetails.get("root"));
            holder.rootView.setTextSize(arabicFontsize);
        } else {

            //    holder.rootView.setText(ROOTWORDSTRING);

        }

        if (vbdetail.get("root") != null) {

            holder.rootView.setText(ROOTWORDSTRING + vbdetail.get("root"));
            holder.rootView.setTextSize(arabicFontsize);

        } else {

            //      holder.rootView.setText(ROOTWORDSTRING);

        }
        //   holder.englishGrammarView.setTextSize(fontsize);
        if (isSarfSagheerMazeed || isSarfSagheerThulahi) {

            SharedPreferences prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
            String fontCategory = prefs.getString("arabic_font_category", "kitab.ttf");
            mequran = Typeface.createFromAsset(QuranGrammarApplication.getContext().getAssets(), fontCategory);
            //  final Typeface meqruans = Typeface.createFromAsset(DarkThemeApplication.getContext().getAssets(), SharedPref.arabicFontSelection());


          //  Integer arabicFontsize = prefs.getInt("arabicFontSizeEntryArray", 20);

            StringBuilder zarf = new StringBuilder();
            StringBuilder ismala = new StringBuilder();
            StringBuilder amr = new StringBuilder();
            StringBuilder nahiamr = new StringBuilder();

            holder.mamaroof.setText((CharSequence) sagheer.getMadhi());
            holder.mumaroof.setText((CharSequence) sagheer.getMudharay());
            holder.ismfail.setText((CharSequence) sagheer.getIsmfael());
            holder.mamajhool.setText((CharSequence) sagheer.getMadhimajhool());
            holder.mumajhool.setText((CharSequence) sagheer.getMudharaymajhool());
            holder.ismmafool.setText((CharSequence) sagheer.getIsmmafool());
            holder.amr.setText((CharSequence) sagheer.getAmrone());
            holder.nahiamr.setText((CharSequence) sagheer.getNahiamrone());
            holder.ismzarfheader.setText(zarfheader);
            holder.ismalaheader.setText(alaheader);
            zarf.append((CharSequence) sagheer.getIsmalaone()).append(", ").append(sagheer.getIsmalatwo()).append(", ").append(sagheer.getIsmalathree());
            ismala.append((CharSequence) sagheer.getZarfone()).append(", ").append(sagheer.getZarftwo()).append(", ").append(sagheer.getZarfthree());
            holder.ismzarf.setText(zarf);
            holder.ismala.setText(ismala);
            holder.weaknessname.setText((CharSequence) sagheer.getWeakness());
            holder.rootword.setText((CharSequence) sagheer.getVerbroot());
            holder.babname.setText((CharSequence) sagheer.getWazanname());


        }
        FontSizeSelection(holder);
        Fonttypeface(holder);
        //   VerbHeader(holder);
        if (particples) {

            SetTypeFace(holder, ismfaelmafoolarray);
            IsmFael(holder, 0, ismfaelmafoolarray);
            IsmFaelFem(holder, 1, ismfaelmafoolarray);
            IsmMafool(holder, 0, ismfaelmafoolarray);
            IsmMafoolFem(holder, 1, ismfaelmafoolarray);
            gcase(holder, ismfaelmafoolarray);
            ismfaelmafoolnumbers(holder, ismfaelmafoolarray);
            FontSIzeSelection(holder, ismfaelmafoolarray);
            String[] array;
            SharedPref sf = new SharedPref(context);
            String language = sf.getLanguage();

            if (language.equals("en")) {
                array = QuranGrammarApplication.getContext().getResources().getStringArray(R.array.enismfaelmafoolheadings);

            } else {
                array = QuranGrammarApplication.getContext().getResources().getStringArray(R.array.arismfaelmafoolheadings);
            }

            holder.apmas.setText(array[0]);
            holder.apfem.setText(array[1]);
            holder.ppmas.setText(array[2]);
            holder.ppfem.setText(array[3]);
            holder.apmas.setTextSize(arabicFontsize);
            holder.apfem.setTextSize(arabicFontsize);
            holder.ppmas.setTextSize(arabicFontsize);
            holder.ppfem.setTextSize(arabicFontsize);


        }


    }

    @Override
    public int getItemCount() {
        return corpusexpand.size();
    }

    private void VerbHeader(ItemViewAdapter holder) {

        //  holder.babname.setTextColor(wazancolor);
        //  holder.rootword.setTextColor(rootcolor);
        // holder.weaknessname.setTextColor(weaknesscolor);


    }


    private void gcase(ItemViewAdapter holder, ArrayList ismfaelmafoolarray) {
        SharedPref sf = new SharedPref(context);
        String language = sf.getLanguage();
        boolean isTraditional = true;
        String[] array;
        if (language.equals("en"))
            array = QuranGrammarApplication.getContext().getResources().getStringArray(R.array.encase);
        else {
            array = QuranGrammarApplication.getContext().getResources().getStringArray(R.array.arcase);
        }
        if (isTraditional) {
            holder.nom.setText(array[0]);
            holder.acc.setText(array[1]);
            holder.gen.setText(array[2]);

            holder.nom1.setText(array[0]);
            holder.acc1.setText(array[1]);
            holder.gen1.setText(array[2]);

            holder.nom2.setText(array[0]);
            holder.acc2.setText(array[1]);
            holder.gen2.setText(array[2]);

            holder.nom3.setText(array[0]);
            holder.acc3.setText(array[1]);
            holder.gen3.setText(array[2]);
        } else {
            holder.nom.setText(array[0]);
            holder.acc.setText(array[1]);
            holder.gen.setText(array[2]);

        }
    }

    private void ismfaelmafoolnumbers(ItemViewAdapter holder, ArrayList ismfaelmafoolarray) {

        SharedPref sf = new SharedPref(context);
        String language = sf.getLanguage();
        boolean isTraditional = true;
        String[] array;
        if (language.equals("en"))
            array = context.getResources().getStringArray(R.array.ennumbers);
        else {
            array = context.getResources().getStringArray(R.array.arnumbers);
        }
        if (isTraditional) {

            holder.sin1.setText(array[0]);
            holder.dual1.setText(array[1]);
            holder.plu1.setText(array[2]);

            holder.sin2.setText(array[0]);
            holder.dual2.setText(array[1]);
            holder.plu2.setText(array[2]);

            holder.sin3.setText(array[0]);
            holder.dual3.setText(array[1]);
            holder.plu3.setText(array[2]);

            holder.sin4.setText(array[0]);
            holder.dual4.setText(array[1]);
            holder.plu4.setText(array[2]);

        } else {
            holder.sin1.setText(array[0]);
            holder.dual1.setText(array[1]);
            holder.plu1.setText(array[2]);

            holder.sin2.setText(array[0]);
            holder.dual2.setText(array[1]);
            holder.plu2.setText(array[2]);

            holder.sin3.setText(array[0]);
            holder.dual3.setText(array[1]);
            holder.plu3.setText(array[2]);
        }


    }


    private void IsmFael(ItemViewAdapter holder, int position, ArrayList ismfaelmafoolarray) {
        String iisone = ismfaelmafool.get(position).get(0).toString();//isone);
        String iistwo = ismfaelmafool.get(position).get(2).toString();//istwo);
        String iisthree = ismfaelmafool.get(position).get(4).toString();//isthree);
        String iisfour = ismfaelmafool.get(position).get(6).toString();//isfour);
        String iisfive = ismfaelmafool.get(position).get(8).toString();//isfive);
        String iissix = ismfaelmafool.get(position).get(10).toString();//issix);
        String iisseven = ismfaelmafool.get(position).get(12).toString();//isseven);
        String iiseight = ismfaelmafool.get(position).get(14).toString();//iseight);
        String iisnine = ismfaelmafool.get(position).get(16).toString();//isnine);
        FontSIzeSelection(holder, ismfaelmafoolarray);
        SetTypeFace(holder, ismfaelmafoolarray);
        holder.isone.setText(iisone);
        holder.istwo.setText(iistwo);
        holder.isthree.setText(iisthree);

        holder.isfour.setText(iisfour);
        holder.isfive.setText(iisfive);
        holder.issix.setText(iissix);

        holder.isseven.setText(iisseven);
        holder.iseight.setText(iiseight);
        holder.isnine.setText(iisnine);

    }

    private void IsmFaelFem(ItemViewAdapter holder, int position, ArrayList ismfaelmafoolarray) {

        String iisone = ismfaelmafool.get(position).get(1).toString();//isone);
        String iistwo = ismfaelmafool.get(position).get(3).toString();//istwo);
        String iisthree = ismfaelmafool.get(position).get(5).toString();//isthree);
        String iisfour = ismfaelmafool.get(position).get(7).toString();//isfour);
        String iisfive = ismfaelmafool.get(position).get(9).toString();//isfive);
        String iissix = ismfaelmafool.get(position).get(11).toString();//issix);
        String iisseven = ismfaelmafool.get(position).get(13).toString();//isseven);
        String iiseight = ismfaelmafool.get(position).get(15).toString();//iseight);
        String iisnine = ismfaelmafool.get(position).get(17).toString();//isnine);

        FontSIzeSelection(holder, ismfaelmafoolarray);
        SetTypeFace(holder, ismfaelmafoolarray);
        holder.ismfemone.setText(iisone);
        holder.ismfemtwo.setText(iistwo);
        holder.ismfemthree.setText(iisthree);

        holder.ismfemfour.setText(iisfour);
        holder.ismfemfive.setText(iisfive);
        holder.ismfemsix.setText(iissix);

        holder.ismfemseven.setText(iisseven);
        holder.ismfemeight.setText(iiseight);
        holder.ismfemnine.setText(iisnine);


    }

    private void IsmMafoolFem(ItemViewAdapter holder, int position, ArrayList ismfaelmafoolarray) {


        String smafone = ismfaelmafool.get(position).get(1).toString();
        String smaftwo = ismfaelmafool.get(position).get(3).toString();//imaftwo);
        String smafthree = ismfaelmafool.get(position).get(5).toString();//imafthree);

        String smaffour = ismfaelmafool.get(position).get(7).toString();//imaffour);
        String smaffive = ismfaelmafool.get(position).get(9).toString();//imaffive);
        String smafsix = ismfaelmafool.get(position).get(11).toString();//imafseven);

        String smafseven = ismfaelmafool.get(position).get(13).toString();//imafseven);
        String smafeight = ismfaelmafool.get(position).get(15).toString();//imafeight);
        String smafnine = ismfaelmafool.get(position).get(17).toString();//imafnine);
        FontSIzeSelection(holder, ismfaelmafool);
        SetTypeFace(holder, ismfaelmafool);
        holder.imafoolfemone.setText(smafone);
        holder.imafoolfemtwo.setText(smaftwo);
        holder.imafoolfemthree.setText(smafthree);
        holder.imafoolfemfour.setText(smaffour);
        holder.imafoolfemfive.setText(smaffive);
        holder.imafoolfemsix.setText(smafsix);
        holder.imafoolfemseven.setText(smafseven);
        holder.imafoolfemeight.setText(smafeight);
        holder.imafoolfemnine.setText(smafnine);
    }

    private void IsmMafool(ItemViewAdapter holder, int position, ArrayList ismfaelmafoolarray) {


        String smafone = ismfaelmafool.get(position).get(0).toString();
        String smaftwo = ismfaelmafool.get(position).get(2).toString();//imaftwo);
        String smafthree = ismfaelmafool.get(position).get(4).toString();//imafthree);

        String smaffour = ismfaelmafool.get(position).get(6).toString();//imaffour);
        String smaffive = ismfaelmafool.get(position).get(8).toString();//imaffive);
        String smafsix = ismfaelmafool.get(position).get(10).toString();//imafseven);

        String smafseven = ismfaelmafool.get(position).get(12).toString();//imafseven);
        String smafeight = ismfaelmafool.get(position).get(14).toString();//imafeight);
        String smafnine = ismfaelmafool.get(position).get(16).toString();//imafnine);
        FontSIzeSelection(holder, ismfaelmafool);
        SetTypeFace(holder, ismfaelmafoolarray);
        holder.imafone.setText(smafone);
        holder.imaftwo.setText(smaftwo);
        holder.imafthree.setText(smafthree);
        holder.imaffour.setText(smaffour);
        holder.imaffive.setText(smaffive);
        holder.imafsix.setText(smafsix);
        holder.imafseven.setText(smafseven);
        holder.imafeight.setText(smafeight);
        holder.imafnine.setText(smafnine);


    }


    private void SetTypeFace(ItemViewAdapter holder, ArrayList ismfaelmafoolarray) {
        //  final Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), "Pdms.ttf");
        Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());

        //   String s = SharedPref.arabicFontSelection();
        boolean isTraditional = true;
        if (isTraditional) {
            holder.nom.setTypeface(arabicTypeface);// (array[0]);
            holder.acc.setTypeface(arabicTypeface);// (array[1]);
            holder.gen.setTypeface(arabicTypeface);// (array[2]);

            holder.nom1.setTypeface(arabicTypeface);// (array[0]);
            holder.acc1.setTypeface(arabicTypeface);// (array[1]);
            holder.gen1.setTypeface(arabicTypeface);// (array[2]);

            holder.nom2.setTypeface(arabicTypeface);// (array[0]);
            holder.acc2.setTypeface(arabicTypeface);// (array[1]);
            holder.gen2.setTypeface(arabicTypeface);// (array[2]);

            holder.nom3.setTypeface(arabicTypeface);// (array[0]);
            holder.acc3.setTypeface(arabicTypeface);// (array[1]);
            holder.gen3.setTypeface(arabicTypeface);// (array[2]);

            holder.sin1.setTypeface(arabicTypeface);//(array[0]);
            holder.dual1.setTypeface(arabicTypeface);//(array[1]);
            holder.plu1.setTypeface(arabicTypeface);//(array[2]);

            holder.sin2.setTypeface(arabicTypeface);//(array[0]);
            holder.dual2.setTypeface(arabicTypeface);//(array[1]);
            holder.plu2.setTypeface(arabicTypeface);//(array[2]);

            holder.sin3.setTypeface(arabicTypeface);//(array[0]);
            holder.dual3.setTypeface(arabicTypeface);//(array[1]);
            holder.plu3.setTypeface(arabicTypeface);//(array[2]);

            holder.sin4.setTypeface(arabicTypeface);//(array[0]);
            holder.dual4.setTypeface(arabicTypeface);//(array[1]);
            holder.plu4.setTypeface(arabicTypeface);//(array[2]);


        } else {
            holder.nom.setTypeface(arabicTypeface);//(array[0]);
            holder.acc.setTypeface(arabicTypeface);//(array[1]);
            holder.gen.setTypeface(arabicTypeface);//(array[2]);
            holder.sin1.setTypeface(arabicTypeface);// (array[0]);
            holder.dual1.setTypeface(arabicTypeface);// (array[1]);
            holder.plu1.setTypeface(arabicTypeface);// (array[2]);

            holder.sin2.setTypeface(arabicTypeface);// (array[0]);
            holder.dual2.setTypeface(arabicTypeface);// (array[1]);
            holder.plu2.setTypeface(arabicTypeface);// (array[2]);

            holder.sin3.setTypeface(arabicTypeface);// (array[0]);
            holder.dual2.setTypeface(arabicTypeface);// (array[1]);
            holder.dual3.setTypeface(arabicTypeface);// (array[2]);
        }
        holder.imafone.setTypeface(arabicTypeface);//;//smafone);
        holder.imaftwo.setTypeface(arabicTypeface);//;//smaftwo);
        holder.imafthree.setTypeface(arabicTypeface);//;//smafthree);
        holder.imaffour.setTypeface(arabicTypeface);//;//smaffour);
        holder.imaffive.setTypeface(arabicTypeface);//;//smaffive);
        holder.imafsix.setTypeface(arabicTypeface);//;//smafsix);
        holder.imafseven.setTypeface(arabicTypeface);//;//smafseven);
        holder.imafeight.setTypeface(arabicTypeface);//;//smafeight);
        holder.imafnine.setTypeface(arabicTypeface);//;//smafnine);

        //

        holder.imafoolfemone.setTypeface(arabicTypeface);//;//smafone);
        holder.imafoolfemtwo.setTypeface(arabicTypeface);//;//smaftwo);
        holder.imafoolfemthree.setTypeface(arabicTypeface);//;//smafthree);
        holder.imafoolfemfour.setTypeface(arabicTypeface);//;//smaffour);
        holder.imafoolfemfive.setTypeface(arabicTypeface);//;//smaffive);
        holder.imafoolfemsix.setTypeface(arabicTypeface);//;//smafsix);
        holder.imafoolfemseven.setTypeface(arabicTypeface);//;//smafseven);
        holder.imafoolfemeight.setTypeface(arabicTypeface);//;//smafeight);
        holder.imafoolfemnine.setTypeface(arabicTypeface);//;//smafnine);
        //

        holder.ismfemone.setTypeface(arabicTypeface);//;//iismfemone);
        holder.ismfemtwo.setTypeface(arabicTypeface);//;//iismfemtwo);
        holder.ismfemthree.setTypeface(arabicTypeface);//;//iismfemthree);

        holder.ismfemfour.setTypeface(arabicTypeface);//;//iismfemfour);
        holder.ismfemfive.setTypeface(arabicTypeface);//;//iismfemfive);
        holder.ismfemsix.setTypeface(arabicTypeface);//;//iismfemsix);

        holder.ismfemseven.setTypeface(arabicTypeface);//;//iismfemseven);
        holder.ismfemeight.setTypeface(arabicTypeface);//;//iismfemeight);
        holder.ismfemnine.setTypeface(arabicTypeface);//;//iismfemnine);


        holder.isone.setTypeface(arabicTypeface);//;//iisone);
        holder.istwo.setTypeface(arabicTypeface);//;//iistwo);
        holder.isthree.setTypeface(arabicTypeface);//;//iisthree);

        holder.isfour.setTypeface(arabicTypeface);//;//iisfour);
        holder.isfive.setTypeface(arabicTypeface);//;//iisfive);
        holder.issix.setTypeface(arabicTypeface);//;//iissix);

        holder.isseven.setTypeface(arabicTypeface);//;//iisseven);
        holder.iseight.setTypeface(arabicTypeface);//;//iiseight);
        holder.isnine.setTypeface(arabicTypeface);//;//iisnine);


    }

    private void FontSIzeSelection(ItemViewAdapter holder, ArrayList ismfaelmafoolarray) {

        SharedPreferences sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.getContext());
        final Integer arabicFontsize = sharedPreferences.getInt("pref_font_arabic_key", 20);


        boolean isTraditional = true;
        if (isTraditional) {
            holder.nom.setTextSize(arabicFontsize);//(array[0]);
            holder.acc.setTextSize(arabicFontsize);//(array[1]);
            holder.gen.setTextSize(arabicFontsize);//(array[2]);

            holder.nom1.setTextSize(arabicFontsize);//(array[0]);
            holder.acc1.setTextSize(arabicFontsize);//(array[1]);
            holder.gen1.setTextSize(arabicFontsize);//(array[2]);

            holder.nom2.setTextSize(arabicFontsize);//(array[0]);
            holder.acc2.setTextSize(arabicFontsize);//(array[1]);
            holder.gen2.setTextSize(arabicFontsize);//(array[2]);

            holder.nom3.setTextSize(arabicFontsize);//(array[0]);
            holder.acc3.setTextSize(arabicFontsize);//(array[1]);
            holder.gen3.setTextSize(arabicFontsize);//(array[2]);


            holder.sin1.setTextSize(arabicFontsize);//(array[0]);
            holder.dual1.setTextSize(arabicFontsize);//(array[1]);
            holder.plu1.setTextSize(arabicFontsize);//(array[2]);

            holder.sin2.setTextSize(arabicFontsize);//(array[0]);
            holder.dual2.setTextSize(arabicFontsize);//(array[1]);
            holder.plu2.setTextSize(arabicFontsize);//(array[2]);

            holder.sin3.setTextSize(arabicFontsize);//(array[0]);
            holder.dual3.setTextSize(arabicFontsize);//(array[1]);
            holder.plu3.setTextSize(arabicFontsize);//(array[2]);

            holder.sin4.setTextSize(arabicFontsize);//(array[0]);
            holder.dual4.setTextSize(arabicFontsize);//(array[1]);
            holder.plu4.setTextSize(arabicFontsize);//(array[2]);
        } else {
            holder.nom.setTextSize(arabicFontsize);//(array[0]);
            holder.acc.setTextSize(arabicFontsize);//(array[1]);
            holder.gen.setTextSize(arabicFontsize);//(array[2]);

            holder.sin1.setTextSize(arabicFontsize);//(array[0]);
            holder.dual1.setTextSize(arabicFontsize);//(array[1]);
            holder.plu1.setTextSize(arabicFontsize);//(array[2]);

            holder.sin2.setTextSize(arabicFontsize);//(array[0]);
            holder.dual2.setTextSize(arabicFontsize);//(array[1]);
            holder.plu2.setTextSize(arabicFontsize);//(array[2]);

            holder.sin3.setTextSize(arabicFontsize);//(array[0]);
            holder.dual2.setTextSize(arabicFontsize);//(array[1]);
            holder.dual3.setTextSize(arabicFontsize);//(array[2]);

        }


        holder.imafone.setTextSize(arabicFontsize);//smafone);
        holder.imaftwo.setTextSize(arabicFontsize);//smaftwo);
        holder.imafthree.setTextSize(arabicFontsize);//smafthree);
        holder.imaffour.setTextSize(arabicFontsize);//smaffour);
        holder.imaffive.setTextSize(arabicFontsize);//smaffive);
        holder.imafsix.setTextSize(arabicFontsize);//smafsix);
        holder.imafseven.setTextSize(arabicFontsize);//smafseven);
        holder.imafeight.setTextSize(arabicFontsize);//smafeight);
        holder.imafnine.setTextSize(arabicFontsize);//smafnine);

        //

        holder.imafoolfemone.setTextSize(arabicFontsize);//smafone);
        holder.imafoolfemtwo.setTextSize(arabicFontsize);//smaftwo);
        holder.imafoolfemthree.setTextSize(arabicFontsize);//smafthree);
        holder.imafoolfemfour.setTextSize(arabicFontsize);//smaffour);
        holder.imafoolfemfive.setTextSize(arabicFontsize);//smaffive);
        holder.imafoolfemsix.setTextSize(arabicFontsize);//smafsix);
        holder.imafoolfemseven.setTextSize(arabicFontsize);//smafseven);
        holder.imafoolfemeight.setTextSize(arabicFontsize);//smafeight);
        holder.imafoolfemnine.setTextSize(arabicFontsize);//smafnine);
        //

        holder.ismfemone.setTextSize(arabicFontsize);//iismfemone);
        holder.ismfemtwo.setTextSize(arabicFontsize);//iismfemtwo);
        holder.ismfemthree.setTextSize(arabicFontsize);//iismfemthree);

        holder.ismfemfour.setTextSize(arabicFontsize);//iismfemfour);
        holder.ismfemfive.setTextSize(arabicFontsize);//iismfemfive);
        holder.ismfemsix.setTextSize(arabicFontsize);//iismfemsix);

        holder.ismfemseven.setTextSize(arabicFontsize);//iismfemseven);
        holder.ismfemeight.setTextSize(arabicFontsize);//iismfemeight);
        holder.ismfemnine.setTextSize(arabicFontsize);//iismfemnine);


        holder.isone.setTextSize(arabicFontsize);//iisone);
        holder.istwo.setTextSize(arabicFontsize);//iistwo);
        holder.isthree.setTextSize(arabicFontsize);//iisthree);

        holder.isfour.setTextSize(arabicFontsize);//iisfour);
        holder.isfive.setTextSize(arabicFontsize);//iisfive);
        holder.issix.setTextSize(arabicFontsize);//iissix);

        holder.isseven.setTextSize(arabicFontsize);//iisseven);
        holder.iseight.setTextSize(arabicFontsize);//iiseight);
        holder.isnine.setTextSize(arabicFontsize);//iisnine);


    }


    private void Fonttypeface(ItemViewAdapter holder) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        String quranFont = sharedPreferences.getString("quranFont", "kitab.ttf");
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), quranFont);
        if (isverbconjugation) {
            holder.mamaroof.setTypeface(typeface);
            holder.mumaroof.setTypeface(typeface);
            holder.masdaro.setTypeface(typeface);
            holder.masdart.setTypeface(typeface);
            holder.ismfail.setTypeface(typeface);

            holder.mamajhool.setTypeface(typeface);

            holder.mumajhool.setTypeface(typeface);

            holder.ismmafool.setTypeface(typeface);

            holder.amr.setTypeface(typeface);

            holder.nahiamr.setTypeface(typeface);
            holder.babdetails.setTypeface(typeface);
            holder.babdetails.setTextColor(wazancolor);
            holder.weaknesstype.setTypeface(typeface);
            holder.weaknesstype.setTextColor(weaknesscolor);
            holder.weaknessname.setTypeface(typeface);
            holder.weaknessname.setTextColor(rootcolor);


        }

    }

    private void FontSizeSelection(ItemViewAdapter holder) {
        SharedPreferences sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.getContext());
        final Integer fontsize = sharedPreferences.getInt("pref_font_arabic_key", 20);

        if (isverbconjugation) {
            holder.mamaroof.setTextSize(fontsize);
            holder.mumaroof.setTextSize(fontsize);
            holder.masdaro.setTextSize(fontsize);
            holder.masdart.setTextSize(fontsize);
            holder.ismfail.setTextSize(fontsize);

            holder.mamajhool.setTextSize(fontsize);

            holder.mumajhool.setTextSize(fontsize);

            holder.ismmafool.setTextSize(fontsize);

            holder.amr.setTextSize(fontsize);

            holder.nahiamr.setTextSize(fontsize);
            holder.babdetails.setTextSize(fontsize);
            holder.babdetails.setTextColor(Color.YELLOW);
            holder.weaknesstype.setTextSize(fontsize);
            holder.weaknesstype.setTextColor(Color.BLUE);
            holder.weaknessname.setTextSize(fontsize);
            holder.weaknessname.setTextColor(Color.GREEN);
        }

    }


    public void setRootWordsAndMeanings(boolean verb, ArrayList<String> wazannumberslist,
                                        SpannableStringBuilder spannableStringBuilder,

                                        boolean noun, ArrayList<ArrayList> ismfaelmafool, boolean participles, boolean isverbconjugation, ArrayList<NewCorpusExpandWbwPOJO> corpusSurahWord, HashMap<String, SpannableStringBuilder> wordbdetail, HashMap<String, String> vbdetail, boolean isSarfSagheer, boolean isSarfSagheerThulahi, ArrayList<SarfSagheer> sarfsagheer, Context context) {
        this.isverb = verb;
        this.wazannumberslist = wazannumberslist;
        this.spannable = spannableStringBuilder;

        this.isnoun = noun;
        this.ismfaelmafool = ismfaelmafool;


        this.particples = participles;
        this.isverbconjugation = isverbconjugation;
        this.corpusexpand = corpusSurahWord;
        this.worddetails = wordbdetail;
        this.vbdetail = vbdetail;
        this.isSarfSagheerMazeed = isSarfSagheer;
        this.sarfsagheer = sarfsagheer;
        this.isSarfSagheerThulahi = isSarfSagheerThulahi;


        this.context = context;

    }


    public class ItemViewAdapter extends RecyclerView.ViewHolder
            implements View.OnClickListener // current clickListerner
    {
        final TextView referenceView;
        final TextView wdetailstv;
        final TextView lemma;
        final TextView verbdetails;
        final TextView noundetails;
        final TextView pronoundetails;
        final TextView translationView, mazeedmeaning;
        final TextView rootView, quranverseShart, spannableverse;
        final Chip wordView;
        final TextView babname, rootword, wazan, ismzarfheader, ismalaheader, masdaro, masdart, babno, babdetails;
        final TextView weaknessname, weaknesstype;
        MaterialCardView darkThemeBacground;
        //  ListView list;
        RadioGroup radioGroup;
        RadioButton rdone, rdtwo, rdthree, rdfour;
        ImageView dismissview;
        final View sheet;

        public final TextView amr, nahiamr, ismfail, mumaroof, mamaroof, ismala,
                ismmafool, mumajhool, mamajhool, ismzarf;

        //ISMFAEL
        public final TextView isone, istwo, isthree, isfour, isfive, issix, isseven, iseight, isnine;

        public final TextView ismfemone, ismfemtwo, ismfemthree, ismfemfour, ismfemfive, ismfemsix, ismfemseven, ismfemeight, ismfemnine;


        public final TextView imafone, imaftwo, imafthree, imaffour, imaffive, imafsix, imafseven,
                imafeight, imafnine;

        public final TextView imafoolfemone, imafoolfemtwo, imafoolfemthree, imafoolfemfour, imafoolfemfive, imafoolfemsix, imafoolfemseven,
                imafoolfemeight, imafoolfemnine;

        public final TextView mifalone, mifaltwo, mifalthree, mifalfour, mifalfive, mifalsix, mifalseven, mifaleight, mifalnine;

        public final TextView mifalatunone, mifalatuntwo, mifalatunthree, mifalatunfour, mifalatunfive, mifalatunsix, mifalatunseven, mifalatuneight, mifalatunnine;


        public final TextView mifaalone, mifaaltwo, mifaalthree, mifaalfour, mifaalfive, mifaalsix, mifaalseven,
                mifaaleight, mifaalnine;

        public final TextView mafalunone, mafaluntwo, mafalunthree, mafalunfour, mafalunfive, mafalunsix, mafalunseven,
                mafaluneight, mafalunnine;

        TextView apmas, apfem, ppmas, ppfem, verbconjugationbtn, nounoccurancebtn;
        public final TextView sin1, dual1, plu1, sin2, dual2, plu2, sin3, dual3, plu3;
        public final TextView sin4, dual4, plu4;
        public final TextView nom, acc, gen;
        public final TextView nom1, acc1, gen1;
        public final TextView nom2, acc2, gen2;
        public final TextView nom3, acc3, gen3;
        public final TextView wordDictionary,moodrules;
        public final Chip triroot, paradigm, rootdetails, verb;
        ConstraintLayout expandable;

        public ItemViewAdapter(View view) {
            super(view);
            moodrules= itemView.findViewById(R.id.moodrules);
            mazeedmeaning = itemView.findViewById(R.id.mazeedmeaning);
            darkThemeBacground = itemView.findViewById(R.id.grammar);
            rdone = view.findViewById(R.id.rdone);
            rdtwo = view.findViewById(R.id.rdtwo);
            rdthree = view.findViewById(R.id.rdthree);
            rdfour = view.findViewById(R.id.rdfour);
            radioGroup = view.findViewById(R.id.rdgroup);
            expandable = view.findViewById(R.id.wazanlist);
            //   list=view.findViewById(R.id.verbrootlist);
            verb = view.findViewById(R.id.verb);
            triroot = view.findViewById(R.id.triroot);
            paradigm = view.findViewById(R.id.paradigm);
            rootdetails = view.findViewById(R.id.rootdetails);
            wordDictionary = view.findViewById(R.id.wordDictionary);
            spannableverse = view.findViewById(R.id.spannableverse);
            quranverseShart = view.findViewById(R.id.quranverseShart);
            verbconjugationbtn = view.findViewById(R.id.verbconjugationbtn);
            //   verbOccurancebtn = view.findViewById(R.id.verboccurance);
            nounoccurancebtn = view.findViewById(R.id.wordoccurance);
            babname = view.findViewById(R.id.babno);
            rootword = view.findViewById(R.id.weaknesstype);
            ismzarfheader = view.findViewById(R.id.ismzarfheader);
            pronoundetails = view.findViewById(R.id.pronoundetails);
            noundetails = view.findViewById(R.id.noundetails);
            sheet = view.findViewById(R.id.sheet);
            wdetailstv = view.findViewById(R.id.wordDetails);
            lemma = view.findViewById(R.id.lemma);
            verbdetails = view.findViewById(R.id.verbdetails);

            dismissview = view.findViewById(R.id.dismissView);

            referenceView = view.findViewById(R.id.referenceView);
            wordView = view.findViewById(R.id.wordView);
            translationView = view.findViewById(R.id.translationView);
            rootView = view.findViewById(R.id.rootView);


            //   if(!particples) {
            //      dismissview.setOnClickListener(this);
            //  }
            //     view.setOnClickListener(this); // current clickListerner

            ismalaheader = view.findViewById(R.id.ismalaheader);
            ismala = view.findViewById(R.id.ismaalatable);
            wazan = view.findViewById(R.id.wazan);
            ismfail = view.findViewById(R.id.ismfail);
            masdaro = view.findViewById(R.id.masdar);
            mumaroof = view.findViewById(R.id.mumaroof);
            mamaroof = view.findViewById(R.id.mamaroof);
            ismmafool = view.findViewById(R.id.ismmafool);
            masdart = view.findViewById(R.id.masdar2);
            mumajhool = view.findViewById(R.id.mumajhool);
            mamajhool = view.findViewById(R.id.mamajhool);
            amr = view.findViewById(R.id.amr);
            nahiamr = view.findViewById(R.id.nahiamr);
            babno = view.findViewById(R.id.babno);
            ismzarf = view.findViewById(R.id.zarftable);
            babdetails = view.findViewById(R.id.babno);
            weaknesstype = view.findViewById(R.id.weaknesstype);
            weaknessname = view.findViewById(R.id.weknessname);
            ImageView listcollapse;
            spannableverse.setOnClickListener(this);
            wordView.setOnClickListener(this);
            if (isverbconjugation || particples) {
                verbconjugationbtn.setOnClickListener(this);
                //     verbOccurancebtn.setOnClickListener(this);
                nounoccurancebtn.setOnClickListener(this);
                mazeedmeaning.setOnClickListener(this);


            } else if (isnoun) {
                //  verbOccurancebtn.setEnabled(false);
                verbconjugationbtn.setOnClickListener(this);
                //          verbOccurancebtn.setOnClickListener(this);
                nounoccurancebtn.setOnClickListener(this);
            }


            sin4 = view.findViewById(R.id.singular4);
            dual4 = view.findViewById(R.id.dual4);
            plu4 = view.findViewById(R.id.plural4);
            //    }

            nom = view.findViewById(R.id.nominative);
            acc = view.findViewById(R.id.accusative);
            gen = view.findViewById(R.id.genitive);


            nom1 = view.findViewById(R.id.nominative1);
            acc1 = view.findViewById(R.id.accusative1);
            gen1 = view.findViewById(R.id.genitive1);


            nom2 = view.findViewById(R.id.nominative2);
            acc2 = view.findViewById(R.id.accusative2);
            gen2 = view.findViewById(R.id.genitive2);


            nom3 = view.findViewById(R.id.nominative3);
            acc3 = view.findViewById(R.id.accusative3);
            gen3 = view.findViewById(R.id.genitive3);


            sin1 = view.findViewById(R.id.singular1);
            dual1 = view.findViewById(R.id.dual1);
            plu1 = view.findViewById(R.id.plural1);


            sin2 = view.findViewById(R.id.singular2);
            dual2 = view.findViewById(R.id.dual2);
            plu2 = view.findViewById(R.id.plural2);


            sin3 = view.findViewById(R.id.singular3);
            dual3 = view.findViewById(R.id.dual3);
            plu3 = view.findViewById(R.id.plural3);

            apmas = view.findViewById(R.id.apmas);
            apfem = view.findViewById(R.id.apfem);
            ppmas = view.findViewById(R.id.ppmas);
            ppfem = view.findViewById(R.id.ppfem);


            ismfemone = view.findViewById(R.id.ismfemone);

            if (particples) {
                ismfemone.setText(R.string.faelmazi);
            }
            ismfemtwo = view.findViewById(R.id.ismfemtwo);
            ismfemthree = view.findViewById(R.id.ismfemthree);
            ismfemfour = view.findViewById(R.id.ismfemfour);
            ismfemfive = view.findViewById(R.id.ismfemfive);
            ismfemsix = view.findViewById(R.id.ismfemsix);
            ismfemseven = view.findViewById(R.id.ismfemseven);
            ismfemeight = view.findViewById(R.id.ismfemeight);
            ismfemnine = view.findViewById(R.id.ismfemnine);

            //
            isone = view.findViewById(R.id.isone);
            istwo = view.findViewById(R.id.istwo);
            isthree = view.findViewById(R.id.isthree);
            isfour = view.findViewById(R.id.isfour);
            isfive = view.findViewById(R.id.isfive);
            issix = view.findViewById(R.id.issix);
            isseven = view.findViewById(R.id.isseven);
            iseight = view.findViewById(R.id.iseight);
            isnine = view.findViewById(R.id.isnine);


//ismmafoolmasculine
            imafone = view.findViewById(R.id.imafone);
            imaftwo = view.findViewById(R.id.imaftwo);
            imafthree = view.findViewById(R.id.imafthree);

            imaffour = view.findViewById(R.id.imaffour);
            imaffive = view.findViewById(R.id.imaffive);
            imafsix = view.findViewById(R.id.imafsix);

            imafseven = view.findViewById(R.id.imafseven);
            imafeight = view.findViewById(R.id.imafeight);
            imafnine = view.findViewById(R.id.imafnine);


            //ismmafoolfeb
            imafoolfemone = view.findViewById(R.id.imafoolfemone);
            imafoolfemtwo = view.findViewById(R.id.imafoolfemtwo);
            imafoolfemthree = view.findViewById(R.id.imafoolfemthree);

            imafoolfemfour = view.findViewById(R.id.imafoolfemfour);
            imafoolfemfive = view.findViewById(R.id.imafoolfemfive);
            imafoolfemsix = view.findViewById(R.id.imafoolfemsix);

            imafoolfemseven = view.findViewById(R.id.imafoolfemseven);
            imafoolfemeight = view.findViewById(R.id.imafoolfemeight);
            imafoolfemnine = view.findViewById(R.id.imafoolfemnine);


            mifalone = view.findViewById(R.id.mifalone);
            mifaltwo = view.findViewById(R.id.mifaltwo);
            mifalthree = view.findViewById(R.id.mifalthree);
            mifalfour = view.findViewById(R.id.mifalfour);
            mifalfive = view.findViewById(R.id.mifalfive);
            mifalsix = view.findViewById(R.id.mifalsix);
            mifalseven = view.findViewById(R.id.mifalseven);
            mifaleight = view.findViewById(R.id.mifaleight);
            mifalnine = view.findViewById(R.id.mifalnine);

            mifalatunone = view.findViewById(R.id.mifalatunone);
            mifalatuntwo = view.findViewById(R.id.mifalatuntwo);
            mifalatunthree = view.findViewById(R.id.mifalatunthree);

            mifalatunfour = view.findViewById(R.id.mifalatunfour);
            mifalatunfive = view.findViewById(R.id.mifalatunfive);
            mifalatunsix = view.findViewById(R.id.mifalatunsix);

            mifalatunseven = view.findViewById(R.id.mifalatunseven);
            mifalatuneight = view.findViewById(R.id.mifalatuneight);
            mifalatunnine = view.findViewById(R.id.mifalatunnine);


            mifaalone = view.findViewById(R.id.mifaalone);
            mifaaltwo = view.findViewById(R.id.mifaaltwo);
            mifaalthree = view.findViewById(R.id.mifaalthree);

            mifaalfour = view.findViewById(R.id.mifaalfour);
            mifaalfive = view.findViewById(R.id.mifaalfive);
            mifaalsix = view.findViewById(R.id.mifaalsix);

            mifaalseven = view.findViewById(R.id.mifaalseven);
            mifaaleight = view.findViewById(R.id.mifaaleight);
            mifaalnine = view.findViewById(R.id.mifaalnine);


            mafalunone = view.findViewById(R.id.mafalunone);
            mafaluntwo = view.findViewById(R.id.mafaluntwo);
            mafalunthree = view.findViewById(R.id.mafalunthree);

            mafalunfour = view.findViewById(R.id.mafalunfour);
            mafalunfive = view.findViewById(R.id.mafalunfive);
            mafalunsix = view.findViewById(R.id.mafalunsix);

            mafalunseven = view.findViewById(R.id.mafalunseven);
            mafaluneight = view.findViewById(R.id.mafaluneight);
            mafalunnine = view.findViewById(R.id.mafalunnine);


        }

        int i = ContextCompat.getColor(context, R.color.kashmirigreen);

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());

            }
        }
    }
}

