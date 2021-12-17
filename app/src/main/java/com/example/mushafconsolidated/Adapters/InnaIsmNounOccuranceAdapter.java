package com.example.mushafconsolidated.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.DAO.HarfNasbIndexLen;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.utility.DarkThemeApplication;
import com.example.utility.SharedPref;

import java.util.ArrayList;

public class InnaIsmNounOccuranceAdapter extends RecyclerView.Adapter<InnaIsmNounOccuranceAdapter.ItemViewAdapter> {
    private static final String TAG = "VerseDisplayAdapter";
    private static final String ROOTWORDSTRING = "Root Word:-";
    private static final String LEMMA = "Lemma/Derivative-";
    private   Activity activity;
    OnItemClickListener mItemClickListener;
    private Context context;
    private boolean conjugate;

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
    private ArrayList<HarfNasbIndexLen> list;

    public InnaIsmNounOccuranceAdapter() {

    }


    public boolean isSarfSagheerMazeed() {
        return isSarfSagheerMazeed;
    }

    boolean isSarfSagheerMazeed;
    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();


    private ArrayList<ArrayList> sarfsagheer;


    public InnaIsmNounOccuranceAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ItemViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noun_occurance, parent, false);

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


        final HarfNasbIndexLen str = list.get(position);
        StringBuilder cp=new StringBuilder();
        cp.append(str.getSurah()).append("   ").append("-").append(str.getAyah());
         holder.chapterverse.setText(cp.toString());
               holder.ayah.setText(str.getVersedetails());
               holder.translation.setText(str.getVerse());
      //  holder.translation.setText(list.get(position+1).toString());
      //  holder.arabicword.setText(list.get(position+2).toString());

        //      holder.chapterverse.setTypeface(arabicTypeface);
          //    holder.arabicword.setTypeface(arabicTypeface);
             // holder.ayah.setTypeface(arabicTypeface);


              holder.chapterverse.setTextSize(fontsize);
              holder.ayah.setTextSize(fontsize);
             holder.translation.setTextSize(fontsize);
            //  holder.arabicword.setTextSize(fontsize);

              //     holder.wazan.setTextSize(fontsize);
              //    holder.occurance.setTextSize(fontsize);



    }


    private void SetTypeFace(ItemViewAdapter holder, ArrayList ismfaelmafoolarray) {
        //  final Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), "Pdms.ttf");
        Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());

        //   String s = SharedPref.arabicFontSelection();
        boolean isTraditional = true;


    }


    private void FontSizeSelection(@NonNull ItemViewAdapter holder) {
        SharedPreferences sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
        final Integer fontsize = sharedPreferences.getInt("pref_font_arabic_key",20);

        holder.chapterverse.setTextSize(fontsize);


    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public Object getItem(int position) {

        return list.get(position);
    }


    public void setUp(ArrayList<HarfNasbIndexLen> list, Context context) {

        this.list = list;
        this.context = context;
    }


    public class ItemViewAdapter extends RecyclerView.ViewHolder
            implements OnClickListener   {
        TextView chapterverse, arabicword, meaning, wazan, gendernumber, cases, nounname, participles, ayah,lengths, translation;
        ImageView more;
        EditText stringlength;

        public ItemViewAdapter(@NonNull View view) {
            super(view);
            translation = view.findViewById(R.id.translation);
            stringlength=view.findViewById(R.id.stringlength);
            ayah = view.findViewById(R.id.ayah);
            more = view.findViewById(R.id.more);
            participles = view.findViewById(R.id.participles);
            chapterverse = view.findViewById(R.id.chapterverse);
            arabicword = view.findViewById(R.id.arabicword);
            wazan = view.findViewById(R.id.wazan);
            meaning = view.findViewById(R.id.meaning);
            gendernumber = view.findViewById(R.id.gendernumber);
            cases = view.findViewById(R.id.cases);
            nounname = view.findViewById(R.id.nounname);
            lengths=view.findViewById(R.id.lengths);
            more.setOnClickListener(this);
            view.setOnClickListener(this);
            stringlength.setOnClickListener(this);


            stringlength.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Editable text = stringlength.getText();
                    final int length = text.length();
                    StringBuilder sb=new StringBuilder();
                    sb.append(length);
                    lengths.setText(sb.toString());

                }
            });
        


        }


        int i = ContextCompat.getColor(context, R.color.kashmirigreen);


        @Override
        public void onClick(View v) {

        }
    }
}


