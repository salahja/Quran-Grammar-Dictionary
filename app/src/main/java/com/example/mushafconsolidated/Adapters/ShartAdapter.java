package com.example.mushafconsolidated.Adapters;



import static com.example.utility.SharedPref.arabicFontSelection;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Entities.ShartPOJO;
import com.example.mushafconsolidated.Entities.TameezEnt;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.utility.FlowLayout;
import com.example.utility.SharedPref;

import java.util.ArrayList;

public class ShartAdapter extends RecyclerView.Adapter<ShartAdapter.ItemViewAdapter> {
    private static final String TAG = "VerseDisplayAdapter";
    private static final String ROOTWORDSTRING = "Root Word:-";
    private static final String LEMMA = "Lemma/Derivative-";

    private Activity activity;
    OnItemClickListener mItemClickListener;
    private Context context;
    private boolean conjugate;
    private Integer fontSizeTranslation;



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
    private ArrayList<ShartPOJO> list;



    public boolean isSarfSagheerMazeed() {
        return isSarfSagheerMazeed;
    }

    boolean isSarfSagheerMazeed;
    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();


    private ArrayList<ArrayList> sarfsagheer;


    public ShartAdapter(Context context, ArrayList<ShartPOJO> sifaEntities) {
        this.context = context;
        this.list=sifaEntities;
    }


    @NonNull
    @Override
    public ItemViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phrases, parent, false);

        return new ItemViewAdapter(view);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewAdapter holder, int position) {
     TextView arabic;
        Log.d(TAG, "onBindViewHolder: called");
        final int fontsize = SharedPref.SeekarabicFontsize();
        Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());
        boolean showWordByword=true;
        final ShartPOJO sifa = list.get(position);
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),
                SharedPref.arabicFontSelection());
        fontSizeTranslation = SharedPref.arabicFontsize();
        final Boolean showTranslation = SharedPref.showTranslation();


        final String arabicFontSelection = arabicFontSelection();


        try {
            if (sifa != null) {

                StringBuilder sb = new
                        StringBuilder();
                sb.append(sifa.getSurah()).append(":").append(sifa.getAyah());
                holder.chapterverse.setText(sb.toString());



            //    holder.translation.setText(sifa.getTranslation());
                holder.ayah.setText(sifa.getSpannedverse());
                if (showTranslation) {

                    holder.translation.setText(sifa.getTranslation());
                }
                holder.chapterverse.setTypeface(arabicTypeface);
                holder.arabicword.setTypeface(arabicTypeface);



                holder.ayah.setTypeface(custom_font);


                holder.chapterverse.setTextSize(fontsize);
                holder.ayah.setTextSize(fontsize);
            //    holder.translation.setTextSize(fontsize);
                holder.arabicword.setTextSize(fontsize);



            }
        } catch (NullPointerException e) {

            System.out.println("Exception occurred . . . . . . . . ");

          //  this.activity.finish();
        }
    }


    private void SetTypeFace(ItemViewAdapter holder, ArrayList ismfaelmafoolarray) {
        //  final Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), "Pdms.ttf");
        Typeface arabicTypeface = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());

        //   String s = SharedPref.arabicFontSelection();
        boolean isTraditional = true;


    }


    private void FontSizeSelection(@NonNull ItemViewAdapter holder) {
        SharedPreferences sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        final Integer fontsize = sharedPreferences.getInt("pref_font_arabic_key",20);
   //     final int fontsize = SharedPref.SeekarabicFontsize();
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


    public void setUp(ArrayList<ShartPOJO> list, Context context) {

        this.list = list;
        this.context = context;
    }


    public class ItemViewAdapter extends RecyclerView.ViewHolder
            implements OnClickListener  {
        TextView  chapterverse,arabicword, meaning, wazan, gendernumber, cases, nounname, participles,  translation;
        TextView ayah;
        ImageView more;
        public FlowLayout flow_word_by_word;

        public ItemViewAdapter(@NonNull View view) {
            super(view);
            flow_word_by_word = view.findViewById(R.id.flow_word_by_word);
            translation = view.findViewById(R.id.translation);
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
            more.setOnClickListener(this);
            view.setOnClickListener(this);








        }


        @Override
        public void onClick(View v) {

        }
    }
}


