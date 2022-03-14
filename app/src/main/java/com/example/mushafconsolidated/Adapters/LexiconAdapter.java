package com.example.mushafconsolidated.Adapters;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.utility.QuranGrammarApplication;

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



        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dictionary_layout, parent, false);

        return new ItemViewAdapter(view);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewAdapter holder, int position) {
        //  Typeface typeface = Typeface.createFromAsset(context.getAssets(), quranfont);
        //   Typeface mequran = Typeface.createFromAsset(DarkThemeApplication.getContext().getAssets(), "Roboto.ttf");
        Typeface mequran= Typeface.createFromAsset(QuranGrammarApplication.getContext().getAssets(), "Taha.ttf");
        String lanes = lanesdictionary.get(position);
        holder.wordDictionary.requestFocus();
        holder.wordDictionary.getSettings().setLightTouchEnabled(true);
        holder.wordDictionary.getSettings().setBuiltInZoomControls(true);
        holder.  wordDictionary.getSettings().setBuiltInZoomControls(true);
        holder.wordDictionary.getSettings().setSupportZoom(true);

        if(language.equals("genetivenoun")||language.equals("accusativenoun")||language.equals("nominativenoun")||language.equals("accusative")||language.equals("preposition")||language.equals("conditonal")||language.equals("relative")
                ||language.equals("dem")||language.equals("Jussive")||language.equals("Subjunctive")){
           // webView.loadDataWithBaseURL(htmlData, "text/html", "utf-8", null);

            holder. wordDictionary.loadDataWithBaseURL("file:///android_asset/", lanes.toString(), "text/html", "utf-8", null);
            holder.  wordDictionary.getSettings().setBuiltInZoomControls(true);

        }
        else  if(language.equals("lanes")){



            //   wv.loadDataWithBaseURL(null,myHtmlString, "text/html", "UTF-8", null);



            StringBuilder data = new StringBuilder();

            //   data .append("<HTML><HEAD><LINK href=\"entry.css\" type=\"text/css\" rel=\"stylesheet\"/></HEAD><body>");
            data .append(lanes);

            //    holder. wordDictionary.loadDataWithBaseURL("file:///android_asset/", data .toString(), "text/html", "utf-8", null);



            holder. wordDictionary.loadDataWithBaseURL(null, data.toString(), "text/html", "utf-8", null);
            holder.  wordDictionary.getSettings().setBuiltInZoomControls(true);

        }else  if(language.equals("hans")) {
            StringBuilder data = new StringBuilder();
            //   data .append("<HTML><HEAD><LINK href=\"entry.css\" type=\"text/css\" rel=\"stylesheet\"/></HEAD><body>");
            data .append(lanes.toString());
            //  data .append("</body></HTML>");
            //  holder. wordDictionary.loadDataWithBaseURL("file:///android_asset/", data .toString(), "text/html", "utf-8", null);
            holder. wordDictionary.loadDataWithBaseURL(null, data .toString(), "text/html", "utf-8", null);

            holder.  wordDictionary.getSettings().setBuiltInZoomControls(true);

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


        public  final TextView wordDictionaryUrdu,meaning,rootwowrd,arabicword;
        TweakedWebView wordDictionary;

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


