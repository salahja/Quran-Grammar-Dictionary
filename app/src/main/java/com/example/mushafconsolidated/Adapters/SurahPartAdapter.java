package com.example.mushafconsolidated.Adapters;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.utility.SharedPref;

import java.util.ArrayList;
import java.util.List;

//public class VerseDisplayAdapter extends RecyclerView.Adapter<VerseDisplayAdapter.ItemViewAdapter> {
//public class SurahPartAdap extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
public class SurahPartAdapter extends RecyclerView.Adapter<SurahPartAdapter.ItemViewAdapter> {

    private static final String TAG = "SurahPartAdap ";
    private List<SurahPartCombinedArray> finalarrayLists = new ArrayList<>();

    OnItemClickListener mItemClickListener;
    private Context context;
    private String surahname;
    //   private ArrayList<ChildModel> finalarrayLists = new ArrayList<>();


    public SurahPartAdapter(Context context) {
        this.context = context;
    }


    public SurahPartAdapter(Context context, List<SurahPartCombinedArray> soralist) {
        this.context = context;
        this.finalarrayLists = soralist;
    }


    @NonNull
    @Override
    public ItemViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_recyclerview_items, parent, false);
            return new ItemViewAdapter(view, viewType);
        } else {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chapter, parent, false);
            return new ItemViewAdapter(view, viewType);
        }


    }


    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull SurahPartAdapter.ItemViewAdapter holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        final SurahPartCombinedArray surah = finalarrayLists.get(position);
        final String isNightmode = SharedPref.themePreferences();
        TypedArray imgs = context.getResources().obtainTypedArray(R.array.sura_imgs);

        if (getItemViewType(position) == 0) {

            final int parts = surah.getDisplay_part();
            surah.getPname();
            StringBuilder sbs=new StringBuilder();
            sbs.append("Juz-");
            sbs.append(parts);
            sbs.append("        ");
            sbs.append(surah.getName());
            String p = "Part-";
            final String s = String.valueOf(parts);

            holder.part.setText(sbs.toString());
     ;

            int cno = surah.getChapter_no();
            final Drawable drawable = imgs.getDrawable((cno-1));
            imgs.recycle();
            holder.chapterArabicNameView.setImageDrawable((Drawable)drawable);
            if (isNightmode.equals("dark"))  {
                holder.chapterArabicNameView.setColorFilter(Color.WHITE);

         ;
            } else {
                holder.chapterArabicNameView.setColorFilter(Color.BLACK);

            }




            holder.chapterNoView.setText(String.valueOf(cno));
            StringBuilder sb=new StringBuilder();
            sb.append(surah.getAlpha_arabic_name());
            sb.append("(Total Verses-");
            sb.append(surah.getTotal_verses());
            sb.append(")");
            holder.chapterEnglishNameView.setText(surah.getEnglish_name());
            holder.chapterAlphaArabicNameView.setText(sb.toString());
            holder.chapterAlphaArabicNameView.   setTextSize(SharedPref.SeekarabicFontsize());

            holder.chapterEnglishNameView. setTextSize(SharedPref.SeekarabicFontsize());
        } else {

            StringBuilder sb=new StringBuilder();
            sb.append(surah.getAlpha_arabic_name());
            sb.append("(Total Verses-");
            sb.append(surah.getTotal_verses());
            sb.append(")");
            int cno = surah.getChapter_no();
     //       TypedArray imgs = context.getResources().obtainTypedArray(R.array.sura_imgs);

            final Drawable drawable = imgs.getDrawable(cno-1);
            holder.chapterArabicNameView.setImageDrawable((Drawable)drawable);
            if (isNightmode.equals("dark"))  {
                holder.chapterArabicNameView.setColorFilter(Color.WHITE);

                ;
            } else {
                holder.chapterArabicNameView.setColorFilter(Color.WHITE);
            }

            holder.chapterNoView.setText(String.valueOf(cno));
            holder.chapterEnglishNameView.setText(surah.getEnglish_name());
            holder.chapterAlphaArabicNameView.setText(sb.toString());
            holder.chapterAlphaArabicNameView.   setTextSize(SharedPref.SeekarabicFontsize());

            holder.chapterEnglishNameView. setTextSize(SharedPref.SeekarabicFontsize());
            holder.chapterNoView.setTextSize(SharedPref.SeekarabicFontsize());
        }

        ;


        //     holder.referenceview.setText(surah.getVerse_no()+".");


    }

    @Override
    public int getItemViewType(int position) {


        return finalarrayLists.get(position).getPart_no();

    }


    @Override
    public int getItemCount() {
        return finalarrayLists.size();
    }

    public Object getItem(int position) {

        return finalarrayLists.get(position);
    }


    public class ItemViewAdapter extends RecyclerView.ViewHolder
            implements View.OnClickListener // current clickListerner
    {

        public TextView chapterNoView, chapterEnglishNameView, chapterAlphaArabicNameView, part;
        public TextView overlayTypeChapterView,overlayTypePartView;
        public TextView surah_name_arabic;
        ImageView chapterArabicNameView;


        public TextView referenceview;


        public RelativeLayout row_surah;
        //   public ConstraintLayout surah_row_table;///for rnew_surah_row

        public LinearLayout surah_row_table;

        ItemViewAdapter(View layout, int viewType) {
            super(layout);

            if (viewType == 0) {
                part = itemView.findViewById(R.id.partno);
                chapterNoView = itemView.findViewById(R.id.chapterNoView);
                chapterEnglishNameView = itemView.findViewById(R.id.chapterEnglishNameView);
                chapterAlphaArabicNameView = itemView.findViewById(R.id.chapterAlphaArabicNameView);
                chapterArabicNameView=itemView.findViewById(R.id.chapterArabicNameView);
                overlayTypeChapterView=itemView.findViewById(R.id.overlayTypeChapterView);
                overlayTypePartView=itemView.findViewById(R.id.overlayTypePartView);
            //    overlayTypeChapterView.setOnClickListener(this);
              //  overlayTypePartView.setOnClickListener(this);
            } else {

                chapterNoView = itemView.findViewById(R.id.chapterNoView);
                chapterEnglishNameView = itemView.findViewById(R.id.chapterEnglishNameView);
                chapterAlphaArabicNameView = itemView.findViewById(R.id.chapterAlphaArabicNameView);
                chapterArabicNameView=itemView.findViewById(R.id.chapterArabicNameView);
                overlayTypeChapterView=itemView.findViewById(R.id.overlayTypeChapterView);
                overlayTypePartView=itemView.findViewById(R.id.overlayTypePartView);
              //  overlayTypeChapterView.setOnClickListener(this);
               // overlayTypePartView.setOnClickListener(this);
            }


            layout.setOnClickListener(this); // current clickListerner

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());

            }
        }
    }


    public class SurahViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener // current clickListerner
    {
        public TextView translateTextView;

        public TextView surahinfo;
        public TextView chapterNoView, chapterEnglishNameView, chapterAlphaArabicNameView, part;
        public TextView surah_name_arabic;


        public TextView referenceview;


        public RelativeLayout row_surah;
        //   public ConstraintLayout surah_row_table;///for rnew_surah_row

        public LinearLayout surah_row_table;

        public SurahViewHolder(View view) {
            super(view);
            chapterNoView = itemView.findViewById(R.id.chapterNoView);
            chapterEnglishNameView = itemView.findViewById(R.id.chapterEnglishNameView);
            chapterAlphaArabicNameView = itemView.findViewById(R.id.chapterAlphaArabicNameView);


            view.setOnClickListener(this); // current clickListerner

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());

            }
        }
    }

}
