package com.example.mushafconsolidated.Adapters;


import static com.example.Constant.QURAN_VERB_ROOT;
import static com.example.Constant.QURAN_VERB_WAZAN;
import static com.example.mushafconsolidated.Adapters.NewAyahAdapter.fontSizeTranslation;
import static com.example.utility.SharedPref.arabicFontSelection;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Entities.CorpusVerbWbwOccurance;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.fragments.WordAnalysisBottomSheet;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.mushafconsolidated.model.WordSpan;
import com.example.utility.CorpusConstants;
import com.example.utility.DarkThemeApplication;
import com.example.utility.FlowLayout;
import com.example.utility.SharedPref;
import com.google.android.material.chip.Chip;

import org.sj.conjugator.activity.ConjugatorTabsActivity;

import java.util.ArrayList;

public class VerbOccuranceAdapter extends RecyclerView.Adapter<VerbOccuranceAdapter.ItemViewAdapter> implements OnItemClickListener {
    private static final String TAG = "VerseDisplayAdapter";
    private static final String ROOTWORDSTRING = "Root Word:-";
    private static final String LEMMA = "Lemma/Derivative-";
    OnItemClickListener mItemClickListener;
    private Context context;
    private int form;
    public TextView arabic;

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public int getAdapterposition() {
        return adapterposition;
    }

    public void setAdapterposition(int adapterposition) {
        this.adapterposition = adapterposition;
    }

    private ArrayList<CorpusVerbWbwOccurance> list;
    public int adapterposition;

    public VerbOccuranceAdapter() {

    }


    public boolean isSarfSagheerMazeed() {
        return isSarfSagheerMazeed;
    }

    boolean isSarfSagheerMazeed;
    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();


    private ArrayList<ArrayList> sarfsagheer;


    public VerbOccuranceAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ItemViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.verb_occurance, parent, false);

        return new ItemViewAdapter(view);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewAdapter holder, int position) {

        Log.d(TAG, "onBindViewHolder: called");
        final int fontsize = SharedPref.SeekarabicFontsize();
        Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());

        CorpusVerbWbwOccurance ayahWord = list.get(position);
        // CorpusVerbWbwOccurance ayahWord = list.get(position);
        StringBuilder sb = new
                StringBuilder();
        sb.append(ayahWord.getSurah()).append(":").append(ayahWord.getAyah());
        holder.chapterverse.setText(sb.toString());
        holder.arabicword.setText(ayahWord.getSpannedverb());
        holder.wazan.setText(getWazan(ayahWord.getForm(), ayahWord.getThulathibab()));
        holder.gendernumber.setText(ayahWord.getGendernumber());

        holder.tense.setText(ayahWord.getTense());
        //  holder.ayah.setText(ayahWord.getVerses());
        //  holder.ayah.setTextSize(fontsize);
        //  holder.ayah.setTypeface(arabicTypeface);
        holder.translation.setText(ayahWord.getTranslation());
        holder.translation.setTextSize(fontsize);
        sb = new StringBuilder();
        holder.meaning.setText(ayahWord.getEn());

        holder.chapterverse.setTextSize(fontsize);
        holder.arabicword.setTextSize(fontsize);
        holder.wazan.setTextSize(fontsize);

        holder.chapterverse.setTypeface(arabicTypeface);
        holder.arabicword.setTypeface(arabicTypeface);
        boolean showWordByword = true;
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),
                SharedPref.arabicFontSelection());
        fontSizeTranslation = SharedPref.arabicFontsize();
        setAdapterposition(position);
        if (showWordByword) {
            final LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder.flow_word_by_word.removeAllViews();
            // for (Word word: ayahWord.getWord()) {


            ;
            for (WordSpan word : ayahWord.getWordspan()) {
                final View view = inflater.inflate(R.layout.word_by_word, null);
                //   final TextView arabic = view.findViewById(R.id.word_arabic_textView);
                arabic = view.findViewById(R.id.word_arabic_textView);

                final TextView translation = view.findViewById(R.id.word_trans_textView);
                //  arabic.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeArabic);
                translation.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeTranslation);
                final String arabicFontSelection = arabicFontSelection();
                //   arabic.setText(fixArabic(word.getAraone()+word.getAratwo()+word.getArathree()+word.getArafour()+word.getArafive()));
                //   arabic.setText(word.getWordsAr());

                arabic.setText(word.getWordsAr());


                if (SharedPref.themePreferences().equals("dark")) {
                    arabic.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.white));
                    translation.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.white));

                } else {
                    arabic.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.burntamber));
                    translation.setTextColor(DarkThemeApplication.getContext().getResources().getColor(R.color.burntamber));
                }


                arabic.setTextSize(SharedPref.SeekarabicFontsize());
                arabic.setTypeface(custom_font);
                translation.setText(word.getTranslateEn());
                translation.setTextSize(SharedPref.SeekarabicFontsize());
                holder.flow_word_by_word.addView(view);

                view.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(context);
                        //      dialog.setContentView(R.layout.corpus_layout);


                        //  dialog.setTitle(fixArabic(word.getWordsAr()));


                        Bundle dataBundle = new Bundle();
                        //    dataBundle.putInt(SURAH_ID, Math.toIntExact(surah_id));dataBundle.putInt(AYAHNUMBER, Math.toIntExact(word.getSurahId()));
                        //   dataBundle.putInt(WORDNUMBER, Math.toIntExact(word.getWordno()));
                        //  dataBundle.putString(SURAH_ARABIC_NAME, SurahName);
                        LoadItemList(dataBundle);


                        //     ItemListDialogFragment.newInstance().show((AppCompatActivity)context).getSupportFragmentManager(), ItemListDialogFragment.TAG);
                        //   Intent wordintent=new Intent(context, WordWordActivity.class);
                        //  wordintent.putExtras(dataBundle);
                        //  context.startActivity(wordintent);
                        //   loaddialog(dialog, dataBundle);
                    }

                    private void LoadItemList(Bundle dataBundle) {
                        WordAnalysisBottomSheet item = new WordAnalysisBottomSheet();

                        //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
                        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();

                        item.setArguments(dataBundle);
                        String data[] = {String.valueOf(word.getSurahId()), String.valueOf(word.getVerseId()), word.getTranslateEn(), String.valueOf((word.getWordno()))};
                        FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
                        transactions.show(item);
                        WordAnalysisBottomSheet.newInstance(data).show(((AppCompatActivity) context).getSupportFragmentManager(), WordAnalysisBottomSheet.TAG);
                    }


                });

            }

            holder.flow_word_by_word.setVisibility(View.VISIBLE);
            //   holder.arabic_textView.setVisibility(View.GONE);
            ;
            //    holder.arabic_textView.setText(ayahWord.getQuranArabic());

        }


    }

    private String getWazan(String form, String thulathibab) {
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        //    sb.append(("Form I-"));
        switch (thulathibab) {
            case "N":
                sb.append(CorpusConstants.thulathi.NASARA);
                break;
            case "Z":
                sb.append(CorpusConstants.thulathi.ZARABA);
                break;
            case "F":
                sb.append(CorpusConstants.thulathi.FATAH);
                break;
            case "S":
                sb.append(CorpusConstants.thulathi.SAMIA);
                break;
            case "K":
                sb.append(CorpusConstants.thulathi.KARUMU);
                break;
            case "H":
                sb.append(CorpusConstants.thulathi.HASIBA);
                break;
            default:
                sb.append(form);
        }
        sb.append(")");
        return sb.toString();
    }


    private void SetTypeFace(ItemViewAdapter holder, ArrayList ismfaelmafoolarray) {
        //  final Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), "Pdms.ttf");
        Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());

        //   String s = SharedPref.arabicFontSelection();
        boolean isTraditional = true;


    }


    private void FontSizeSelection(ItemViewAdapter holder) {

        SharedPreferences sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
        final Integer fontsize = sharedPreferences.getInt("pref_font_arabic_key",20);;
        holder.chapterverse.setTextSize(fontsize);


    }

    @Override
    public long getItemId(int position) {


        return list.get(position).getSurah();
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public Object getItem(int position) {

        return list.get(position);
    }


    public void setUp(ArrayList<CorpusVerbWbwOccurance> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @Override
    public void onItemClick(View v, int position) {

    }


    public class ItemViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        public FlowLayout flow_word_by_word;
        TextView chapterverse,   meaning, wazan, gendernumber, tense, ayah, translation;
        Chip arabicword;
        ImageView more;
        public View view;
        public TextView arabic;

        public ItemViewAdapter(View view) {
            super(view);
            flow_word_by_word = view.findViewById(R.id.flow_word_by_word);
            translation = view.findViewById(R.id.translation);
            ayah = view.findViewById(R.id.ayah);
            chapterverse = view.findViewById(R.id.chapterverse);
            arabicword = view.findViewById(R.id.arabicword);
            wazan = view.findViewById(R.id.wazan);
            meaning = view.findViewById(R.id.meaning);
            gendernumber = view.findViewById(R.id.gendernumber);
            tense = view.findViewById(R.id.tense);
            more = view.findViewById(R.id.more);
            more.setOnClickListener(this);
            view.setOnClickListener(this);
            arabicword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CorpusVerbWbwOccurance verbWbwOccurance = list.get(getAdapterPosition());
                    String wazan = getWazan(verbWbwOccurance.getForm(), verbWbwOccurance.getThulathibab());
                    final String ss = wazan.replaceAll("\\(|\\)", "");
                    convertForms(ss);
                    String root_a = verbWbwOccurance.getRoot_a();
                    Bundle dataBundle = new Bundle();
                    if (!ss.equals("I") && verbWbwOccurance.getThulathibab().length() == 0) {

                        if (getForm() == 0) {
                            dataBundle.putString(QURAN_VERB_WAZAN, ss);
                        } else {
                            dataBundle.putString(QURAN_VERB_WAZAN, String.valueOf(getForm()));
                        }
                    } else {

                        dataBundle.putString(QURAN_VERB_WAZAN, ss);
                    }


                    dataBundle.putString(QURAN_VERB_ROOT, verbWbwOccurance.getRoot_a());
                    Intent intent = new Intent(DarkThemeApplication.getContext(), ConjugatorTabsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtras(dataBundle);
                    DarkThemeApplication.getContext().startActivity(intent);


                }
            });


        }

        int i = ContextCompat.getColor(context, R.color.kashmirigreen);

        @Override
        public void onClick(View v) {
            init();
            ///   popupInit();
            //   showPopup(view);

            //        showPopupMenu(v);
        }

        private void popupInit() {
        }

        private void init() {


        }

        public void showPopup(View view) {
            View popupView = LayoutInflater.from((AppCompatActivity) context).inflate(R.layout.view_word_details_alone, null);

            final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.MATCH_PARENT);
            //       Button btnDismiss = (Button) popupView.findViewById(R.id.btn_dismiss);
            popupWindow.setFocusable(true);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

            popupWindow.showAsDropDown(popupView, 0, 0);

        }


        private void showPopupMenu(View v) {
            PopupMenu pop = new PopupMenu(v.getContext(), v);
            pop.inflate(R.menu.fragment_menu_items);
            pop.setOnMenuItemClickListener(this);
            pop.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {

                case R.id.displayayahs:
                    Log.d(TAG, "click display" + getAdapterPosition());
                    return true;

                case R.id.conjugate:
                    CorpusVerbWbwOccurance verbWbwOccurance = list.get(getAdapterPosition());
                    String wazan = getWazan(verbWbwOccurance.getForm(), verbWbwOccurance.getThulathibab());
                    final String ss = wazan.replaceAll("\\(|\\)", "");
                    convertForms(ss);
                    String root_a = verbWbwOccurance.getRoot_a();
                    Bundle dataBundle = new Bundle();
                    if (!ss.equals("I") && verbWbwOccurance.getThulathibab().length() == 0) {

                        if (getForm() == 0) {
                            dataBundle.putString(QURAN_VERB_WAZAN, ss);
                        } else {
                            dataBundle.putString(QURAN_VERB_WAZAN, String.valueOf(getForm()));
                        }
                    } else {

                        dataBundle.putString(QURAN_VERB_WAZAN, ss);
                    }


                    dataBundle.putString(QURAN_VERB_ROOT, verbWbwOccurance.getRoot_a());
                    Intent intent = new Intent(DarkThemeApplication.getContext(), ConjugatorTabsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtras(dataBundle);
                    DarkThemeApplication.getContext().startActivity(intent);

                    Log.d(TAG, "click display" + getAdapterPosition());
                    return true;
                default:
                    return false;

            }

        }

        private void convertForms(String mform) {

            switch (mform) {
                case "IV":
                    setForm(1);
                    break;
                case "II":
                    setForm(2);
                    break;
                case "III":
                    setForm(3);
                    break;
                case "VII":
                    setForm(4);
                    break;
                case "VIII":
                    setForm(5);
                    break;
                case "VI":
                    setForm(7);
                    break;
                case "V":
                    setForm(8);
                    break;
                case "X":
                    setForm(9);
                    break;
                default:


                    break;
            }
        }
    }
}


