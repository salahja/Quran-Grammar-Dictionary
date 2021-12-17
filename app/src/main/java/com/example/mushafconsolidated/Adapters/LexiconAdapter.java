package com.example.mushafconsolidated.Adapters;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.utility.DarkThemeApplication;
import com.example.utility.SharedPref;

import java.util.ArrayList;

public class LexiconAdapter extends RecyclerView.Adapter<LexiconAdapter.ItemViewAdapter> {


  OnItemClickListener mItemClickListener;
    private Context context;



    private ArrayList<String> lanesdictionary;
    public boolean isSarfSagheerMazeed() {
        return isSarfSagheerMazeed;
    }

    boolean isSarfSagheerMazeed;
    // private ArrayList<GrammarWordEntity> grammarArayList = new ArrayList<>();


    private ArrayList<ArrayList> sarfsagheer;
    String language;


    public LexiconAdapter(ArrayList<String> lanesdictionary, Context context, String language) {
      this.lanesdictionary=lanesdictionary;

        this.context = context;
        this.language=language;
    }


    @NonNull
    @Override
    public ItemViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;



            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.enlish_lughat, parent, false);

        return new ItemViewAdapter(view);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewAdapter holder, int position) {
      //  Typeface typeface = Typeface.createFromAsset(context.getAssets(), quranfont);
   //   Typeface mequran = Typeface.createFromAsset(DarkThemeApplication.getContext().getAssets(), "Roboto.ttf");
      Typeface mequran= Typeface.createFromAsset(DarkThemeApplication.getContext().getAssets(), "Taha.ttf");
      String lanes = lanesdictionary.get(position);


      if(language.equals("lanes")){

        //    String hansweirreplace = lanes.replace(">,", "><br><p>");
         //   String finalreplace = hansweirreplace.replace("),", ")<br><p>");
      //      holder.wordDictionary.setText(Html.fromHtml(lanes));
        holder.wordDictionary.setText(HtmlCompat.fromHtml(lanes, 0));
        holder.wordDictionary.setTypeface(mequran);

          }else  if(language.equals("hans")) {
        holder.wordDictionary.setText(HtmlCompat.fromHtml(lanes, 0));
        holder.wordDictionary.setTypeface(mequran);

      }





    }







    @Override
    public long getItemId(int position) {


        return lanesdictionary.get(position).length() ;
    }

    @Override
    public int getItemCount() {
        return lanesdictionary.size();
    }

    public Object getItem(int position) {

        return lanesdictionary.get(position);
    }




    public class ItemViewAdapter extends RecyclerView.ViewHolder
            implements View.OnClickListener // current clickListerner
    {
        final TextView referenceView;

        ImageView dismissview;


        public  final TextView wordDictionary,wordDictionaryUrdu,meaning,rootwowrd,arabicword;

        public ItemViewAdapter(View view) {
            super(view);
            rootwowrd=view.findViewById(R.id.rootward);
            arabicword=view.findViewById(R.id.arabicword);
            wordDictionary=view.findViewById(R.id.wordDictionary);
            wordDictionaryUrdu=view.findViewById(R.id.wordDictionaryUrdu);
            meaning=view.findViewById(R.id.meaning);
            referenceView = view.findViewById(R.id.referenceView);



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


