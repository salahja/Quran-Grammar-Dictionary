package com.example.mushafconsolidated.Adapters;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Entities.BookMarks;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.utility.DarkThemeApplication;
import com.example.utility.PreferenceUtil;
import com.example.utility.SharedPref;

import java.util.List;

/**
 * RecyclerView.Adapter<BookmarksShowAdapter.ViewHolder>
 * Adapter class for the bookmarks list view
 */
public class BookmarksShowAdapter extends RecyclerView.Adapter<BookmarksShowAdapter.ViewHolder> {


  private static final String TAG = "BookmarksShowAdapter";
  OnItemClickListener mItemClickListener;


  Context BookmarksShowAdapterContext;
  private String SurahName;
  private int bookChapterno;
  private int bookVerseno;

  private int isMakkiMadani;
  private String urdu_font_selection;
  private int quran_arabic_font;
  int bookmarkpostion;
  //  private ArrayList<BookMarks> data;
  private BookMarks bookmarks;
  private List<BookMarks> bookMarkArrayList;


  public int getBookmarkpostion() {
    return bookmarkpostion;
  }

  public void setBookmarkpostion(int bookmarkpostion) {
    this.bookmarkpostion = bookmarkpostion;
  }

  PreferenceUtil pref;
  private int urdu_font_size;
  private String arabic_font_selection;
  private String isNightmode;
  private SharedPref prefs;
  int holderposition;
  int bookmarid;

  public int getBookmarid() {
    return bookmarid;
  }

  public void setBookmarid(int bookmarid) {
    this.bookmarid = bookmarid;
  }

  public int getHolderposition() {
    return holderposition;
  }

  public void setHolderposition(int holderposition) {
    this.holderposition = holderposition;
  }

  public int getBookChapterno() {
    return bookChapterno;
  }

  public void setBookChapterno(int bookChapterno) {
    this.bookChapterno = bookChapterno;
  }

  public int getBookVerseno() {
    return bookVerseno;
  }

  public void setBookVerseno(int bookVerseno) {
    this.bookVerseno = bookVerseno;
  }

  public BookmarksShowAdapter() {
  }

  public BookmarksShowAdapter(Context context) {
    this.BookmarksShowAdapterContext = context;
  }

  public void setSurahName(String surahName, Context context) {
    this.BookmarksShowAdapterContext = context;
    SharedPreferences sharedPreferences =
          PreferenceManager.getDefaultSharedPreferences(BookmarksShowAdapterContext);
    pref = new PreferenceUtil(sharedPreferences);
    //  PrefUtils(BookmarksShowAdapterContext);
    prefs = new SharedPref(BookmarksShowAdapterContext);
    this.SurahName = surahName;


  }


  public String getSurahName() {
    return SurahName;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bookmar_two, parent, false);
    ViewHolder holder = new ViewHolder(view);


    //   sendVerseClick=(SendVerseClick) getActivity();
    return holder;
  }


  public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
    this.mItemClickListener = mItemClickListener;

  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    final int d = Log.d(TAG, "onBindViewHolder: called");

    final BookMarks bookMark = bookMarkArrayList.get(position);
    setHolderposition(position);
    setBookmarid(bookMark.getId());
    TypedArray imgs = DarkThemeApplication.getContext().getResources().obtainTypedArray(R.array.sura_imgs);
    SharedPreferences shared = android.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
    String isNightmode = shared.getString("theme", "dark");
    String chapterno = bookMark.getChapterno();
    final Drawable drawable = imgs.getDrawable((Integer.parseInt(chapterno) - 1));
    holder.surahicon.setImageDrawable(drawable);
    if (isNightmode.equals("dark")||isNightmode.equals("blue")) {
      holder.surahicon.setColorFilter(Color.CYAN);

    }
    holder.datestamp.setText(bookMark.getDatetime());

    holder.suraName.setText(bookMark.getSurahname());
    holder.chapterno.setText(bookMark.getChapterno());
    holder.verseno.setText(bookMark.getVerseno() + "");



    holder.datestamp.setTextSize(SharedPref.SeekarabicFontsize());

    holder.suraName.setTextSize(SharedPref.SeekarabicFontsize());
    holder.verseno.setTextSize(SharedPref.SeekarabicFontsize());
    holder.chapterno.setTextSize(SharedPref.SeekarabicFontsize());
    if (isNightmode.equals("dark")) {
      holder.cardView.setCardBackgroundColor(DarkThemeApplication.getContext().getResources().getColor(R.color.color_background_overlay));


    } else if (isNightmode.equals("blue")) {
      holder.cardView.setCardBackgroundColor(DarkThemeApplication.getContext().getResources().getColor(R.color.solarizedBase02));

    }

  }


  @Override
  public int getItemCount() {
    return bookMarkArrayList.size();
  }


  public void setBookMarkList(List<BookMarks> bookmarks) {
    this.bookMarkArrayList = bookmarks;
  }


  public List<BookMarks> getBookMarkArrayList() {
    return bookMarkArrayList;
  }


  public void removeItem(int position) {
    bookMarkArrayList.remove(position);
    notifyItemRemoved(position);
  }

  public void setBookMarkArrayList(List<BookMarks> bookmarstringarray) {

    this.bookMarkArrayList = bookmarstringarray;
  }


  public void restoreItem(String item, int position) {
    //   data.add(position, item);
    notifyItemInserted(position);
  }

  public Object getItem(int position) {


    return bookMarkArrayList.get(position);


  }
  // public void restoreItem(ArrayList<BookMarks> item, int position) {
  //      data.add(position, item);
  //     notifyItemInserted(position);
  //  }

  public class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener // current clickListerner
  {

    public TextView datestamp, timestamp, chapterno, suraName, verseno ;
    ImageView surahicon;
    CardView cardView;

    public ViewHolder(View view) {
      super(view);
      surahicon = view.findViewById(R.id.surahicon);
      cardView=view.findViewById(R.id.cardview);
      datestamp = view.findViewById(R.id.date);

      chapterno = view.findViewById(R.id.chapterno);
      suraName = (TextView) view.findViewById(R.id.surahname);
      verseno = view.findViewById(R.id.verseno);
      chapterno = view.findViewById(R.id.chapterno);

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
