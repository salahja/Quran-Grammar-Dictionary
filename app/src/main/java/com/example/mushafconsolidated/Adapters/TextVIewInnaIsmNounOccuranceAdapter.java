package com.example.mushafconsolidated.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.DAO.HarfNasbIndexLen;
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.utility.DarkThemeApplication;
import com.example.utility.SharedPref;

import java.util.ArrayList;

public class TextVIewInnaIsmNounOccuranceAdapter extends RecyclerView.Adapter<TextVIewInnaIsmNounOccuranceAdapter.ItemViewAdapter> {
    private static final String TAG = "VerseDisplayAdapter";
    private static final String ROOTWORDSTRING = "Root Word:-";
    private static final String LEMMA = "Lemma/Derivative-";
    private   Activity activity;
    OnItemClickListener mItemClickListener;
    private Context context;
    private boolean conjugate;
    ArrayList<TextView[]> arr = new ArrayList<>();
    ArrayList<ArrayList<TextView[]>> arrofarray = new ArrayList<ArrayList<TextView[]>>();
    private LinearLayout myLinearLayout;

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public boolean isConjugate() {
        return conjugate;
    }

    public void setConjugate(boolean conjugate) {
        this.conjugate = conjugate;
    }

    private int form;
    private ArrayList<CorpusNounWbwOccurance> list;

    public TextVIewInnaIsmNounOccuranceAdapter() {

    }


    public boolean isSarfSagheerMazeed() {
        return isSarfSagheerMazeed;
    }

    boolean isSarfSagheerMazeed;
    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();


    private ArrayList<ArrayList> sarfsagheer;


    public TextVIewInnaIsmNounOccuranceAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ItemViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linearview, parent, false);

        return new ItemViewAdapter(view);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewAdapter holder, int position) {

        Log.d(TAG, "onBindViewHolder: called");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
       // final int fontsize = SharedPref.SeekarabicFontsize();

        final int fontsize = prefs.getInt("pref_font_arabic_key", 22);
        final String string = prefs.getString("Arabic_Font_Selection", "me_quran");
        Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), string);


        final ArrayList<TextView[]> textViews = arrofarray.get(position);
        StringBuilder cp=new StringBuilder();
        holder.harf.setText((CharSequence) textViews);
boolean showWordByword=true;
        TextView tv;

        if (showWordByword) {
            final LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             final View view = inflater.inflate(R.layout.linearview, null);
                //   final TextView arabic = view.findViewById(R.id.word_arabic_textView);
           tv = view.findViewById(R.id.tv_download_info);

                final TextView translation = view.findViewById(R.id.word_trans_textView);
                //  arabic.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeArabic);
                //  translation.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeTranslation);
                 //  final String arabicFontSelection = arabicFontSelection();




            holder.myLinearLayout.removeAllViews();
                  for (CorpusNounWbwOccurance entity : list) {
            String verse = String.valueOf(entity.getQurantext());
            HarfNasbIndexLen hf = new HarfNasbIndexLen();
            String[] words = verse.split("\\s+");
            int lastindex = 0;
            hf.setSurah(entity.getSurah());
            hf.setAyah(entity.getAyah());
            hf.setVerse(verse);
            ArrayList<String> word = new ArrayList<>();
            ArrayList<String> wind = new ArrayList<>();
            ArrayList<String> len = new ArrayList<>();
            final int c = words.length;


            final TextView[] myTextViews = new TextView[c];
            arr = new ArrayList<>();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);//Setting the above params to our TextView
            for (int i = 0; i < words.length; i++) {


                TextView rowtxt = new TextView(context);
                rowtxt.setText(words[i] + i);
                tv.setText((CharSequence) rowtxt);
                myLinearLayout.addView(rowtxt);
                myTextViews[i] = rowtxt;
            //    myTextViews[i].setOnClickListener(onclicklistener);
                String ss = words[i];
                myTextViews[i] .setLayoutParams(params);


            }
            arr.add(myTextViews);
            arrofarray.add(arr);



        }



            ;
            //    holder.arabic_textView.setText(ayahWord.getQuranArabic());

        }





    }


    private void SetTypeFace(ItemViewAdapter holder, ArrayList ismfaelmafoolarray) {
        //  final Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), "Pdms.ttf");
        Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());

        //   String s = SharedPref.arabicFontSelection();
        boolean isTraditional = true;


    }






    @Override
    public int getItemCount() {
        return list.size();
    }

    public Object getItem(int position) {

        return list.get(position);
    }


    public void setUp(ArrayList<CorpusNounWbwOccurance> list, Context context) {

        this.list = list;
        this.context = context;

    }


    private void CheckStringLENGTHS(View view) {
      //  Utils utils = new Utils(this);
     //   final ArrayList<harfnasbindices> harfNasbInd = utils.getHarfNasbInd(2);
      //  final ArrayList<CorpusNounWbwOccurance> corpusSurah = utils.getnounoccurancebysurah("ACC", 5);

     //   ArrayList<HarfNasbIndexLen> harf = new ArrayList<>();
      //  ArrayList<ArrayList<String>> fin = new ArrayList<>();
        myLinearLayout=view.findViewById(R.id.linear_layout);

        for (CorpusNounWbwOccurance entity : list) {
            String verse = String.valueOf(entity.getQurantext());
            HarfNasbIndexLen hf = new HarfNasbIndexLen();
            String[] words = verse.split("\\s+");
            int lastindex = 0;
            hf.setSurah(entity.getSurah());
            hf.setAyah(entity.getAyah());
            hf.setVerse(verse);
            ArrayList<String> word = new ArrayList<>();
            ArrayList<String> wind = new ArrayList<>();
            ArrayList<String> len = new ArrayList<>();
            final int c = words.length;

            TextView tv;
            final TextView[] myTextViews = new TextView[c];
            arr = new ArrayList<>();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);//Setting the above params to our TextView
            for (int i = 0; i < words.length; i++) {


                TextView rowtxt = new TextView(context);
                rowtxt.setText(words[i] + i);
                myLinearLayout.addView(rowtxt);
                myTextViews[i] = rowtxt;
            //    myTextViews[i].setOnClickListener(onclicklistener);
                String ss = words[i];
                myTextViews[i] .setLayoutParams(params);


            }
            arr.add(myTextViews);
            arrofarray.add(arr);



        }

    }


    public class ItemViewAdapter extends RecyclerView.ViewHolder
            implements OnClickListener   {
        public ViewGroup myLinearLayout;
        TextView chapterverse, arabicword, meaning, wazan, gendernumber, cases, nounname, participles, ayah, translation,harf;
        ImageView more;

        public ItemViewAdapter(@NonNull View view) {
            super(view);
            harf=view.findViewById(R.id.harftv);
           myLinearLayout=view.findViewById(R.id.linear_layout);

            view.setOnClickListener(this);
       //     CheckStringLENGTHS(view);
            /*
             arabicword.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpanIndex verbWbwOccurance = list.get(getAdapterPosition());
                    Bundle dataBundle = new Bundle();
                    if (verbWbwOccurance.getForm().equals("I")) {
                        Utils util = new Utils(DarkThemeApplication.getContext());
                        ArrayList<VerbCorpus> rootbyString = util.getQuranRootbyString(verbWbwOccurance.getRoot_a());

                        if (rootbyString.size() > 0) {

                            String thulathibab = rootbyString.get(0).getThulathibab();
                            if (thulathibab.length() == 1) {
                                VerbWazan vb = new VerbWazan();
                                String wazan = vb.getWazan(thulathibab);

                                dataBundle.putString(QURAN_VERB_WAZAN, wazan);

                            }

                        }


                    } else if (!verbWbwOccurance.getForm().equals("null")) {
                        String wazan = verbWbwOccurance.getForm();

                        final String ss = wazan.replaceAll("\\(|\\)", "");
                        convertForms(ss);
                        String root_a = verbWbwOccurance.getRoot_a();
                        dataBundle.putString(QURAN_VERB_ROOT, root_a);
                        if (getForm() == 0) {
                            dataBundle.putString(QURAN_VERB_WAZAN, ss);
                        } else {
                            dataBundle.putString(QURAN_VERB_WAZAN, String.valueOf(getForm()));
                        }

                        //   arrayList.get(0).ge
                    }
                    if (dataBundle.getString(QURAN_VERB_ROOT) != null && dataBundle.getString(QURAN_VERB_WAZAN) != null) {

                        dataBundle.putString(QURAN_VERB_ROOT, verbWbwOccurance.getRoot_a());
                        Intent intent = new Intent(DarkThemeApplication.getContext(), NewTabsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtras(dataBundle);
                        DarkThemeApplication.getContext().startActivity(intent);

                        Log.d(TAG, "click display" + getAdapterPosition());

                    }


                }
            });

             */


        }


        int i = ContextCompat.getColor(context, R.color.kashmirigreen);


        @Override
        public void onClick(View v) {

        }
    }
}


