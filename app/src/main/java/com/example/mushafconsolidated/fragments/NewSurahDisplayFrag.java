package com.example.mushafconsolidated.fragments;


import static android.content.Context.MODE_PRIVATE;
import static com.example.Constant.AYAHNUMBER;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Activity.QuranGrammarAct;
import com.example.mushafconsolidated.Adapters.NewSurahDisplayAdapter;
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.Utils;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.mushafconsolidated.intrface.PassdataInterface;
import com.example.utility.QuranGrammarApplication;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewSurahDisplayFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewSurahDisplayFrag extends Fragment {


  //   implements FragmentCommunicator {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  //   private static final String ARG_PARAM1 = "param1";
  //   private static final String ARG_PARAM2 = "param2";



  private RecyclerView parentRecyclerView;
  //   private RecyclerView.Adapter ParentAdapter;
  private NewSurahDisplayAdapter ParentAdapter;
  //  SurahDisplayAdapter ParentAdapter;

  private OnItemClickListener mItemClickListener;


  private boolean isfragmentshowing = true;
  private ImageView drop;
  private TextView devIndicatorView;

  private PassdataInterface passdataInterface;
  private PassdataInterface datapasser;
  private int lastreadchapterno,lastreadverseno;


  public NewSurahDisplayFrag(PassdataInterface passdataInterface){

    this.setPassdataInterface(passdataInterface);
  }
  public NewSurahDisplayFrag() {
    // Required empty public constructor
  }


  /*


   */
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    setDatapasser((PassdataInterface) context);
  }



  public static NewSurahDisplayFrag newInstance( ) {
    NewSurahDisplayFrag fragment = new NewSurahDisplayFrag();
    Bundle args = new Bundle();
    //    args.putString(ARG_PARAM1, param1);
    //    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      // TODO: Rename and change types of parameters
      // String mParam1 = getArguments().getString(ARG_PARAM1);
      //     String mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {


    View view=inflater.inflate(R.layout.list_surah_juz, container, false);
    Utils utils=new Utils(getContext());
    ArrayList<ChaptersAnaEntity> allAnaChapters = utils.getAllAnaChapters();
    TypedArray imgs = getContext().getResources().obtainTypedArray(R.array.sura_imgs);
    GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
   // parentRecyclerView = view.findViewById(R.id.juzRecyclerView);
    parentRecyclerView = view.findViewById(R.id.wordByWordRecyclerView);


    MaterialButton lastread = view.findViewById(R.id.lastread);
    TextView kahaf = view.findViewById(R.id.kahaf);

    SharedPreferences pref = getContext().getSharedPreferences("lastread", MODE_PRIVATE);

    lastreadchapterno = pref.getInt("surah", 1);
    lastreadverseno = pref.getInt("ayah", 1);
    StringBuilder sb=new StringBuilder();
    sb.append("Last read").append(":").append("Surah:").append(lastreadchapterno).append(" ").append("Ayah:").append(lastreadverseno);
    lastread.setText(sb.toString());
    kahaf.setText(R.string.linkkahaf);
/*
 ImageView surahicon = view.findViewById(R.id.surahicon);
    ImageView makkimadaniIcon= view.findViewById(R.id.makkimadaniicon);
    ImageView kahaficon = view.findViewById(R.id.kahaficon);
    ImageView kahafmakkimadaniicon= view.findViewById(R.id.kahafmakkimadaniicon)    ;
    final Drawable drawable = imgs.getDrawable(lastreadchapterno - 1);
    final Drawable kahafdrawable = imgs.getDrawable(18 - 1);
    surahicon.setImageDrawable(drawable);
    surahicon.setColorFilter(Color.CYAN);

    kahaficon.setImageDrawable(kahafdrawable);
    kahaficon.setColorFilter(Color.CYAN);
    int ismakki = allAnaChapters.get(lastreadchapterno-1).getIsmakki();
   int iskhafmakki=    allAnaChapters.get(18).getIsmakki();
    if(ismakki==1) {


      makkimadaniIcon.setImageResource(R.drawable.ic_makkah_foreground);
    }else{
      makkimadaniIcon.setImageResource(R.drawable.ic_madinah_foreground);
    }
    makkimadaniIcon.setColorFilter(Color.CYAN);

    if(iskhafmakki==1) {


      kahafmakkimadaniicon.setImageResource(R.drawable.ic_makkah_foreground);
    }else{
      kahafmakkimadaniicon.setImageResource(R.drawable.ic_madinah_foreground);
    }
    kahafmakkimadaniicon.setColorFilter(Color.CYAN);
 */


    lastread.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
//
       Intent intent = new Intent(QuranGrammarApplication.getContext(), QuranGrammarAct.class);
     //  Intent intent = new Intent(DarkThemeApplication.getContext(), ReadingSurahPartActivity.class);
        intent.putExtra("chapter", lastreadchapterno);
        intent.putExtra("chapterorpart",true);
        intent.putExtra(  "partname",allAnaChapters.get(lastreadchapterno-1).getAbjadname());
        intent.putExtra("verseno",lastreadverseno);
        intent.putExtra(AYAHNUMBER,lastreadverseno);

        startActivity(intent);

      }
    });
    kahaf.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

     Intent intent = new Intent(QuranGrammarApplication.getContext(), QuranGrammarAct.class);
    //  Intent intent = new Intent(DarkThemeApplication.getContext(), ReadingSurahPartActivity.class);
        intent.putExtra("chapter", 18);
        intent.putExtra("chapterorpart",true);
        intent.putExtra(  "partname",allAnaChapters.get(18).getAbjadname());
        intent.putExtra("verseno",1);
        intent.putExtra(AYAHNUMBER,1);

        startActivity(intent);

      }
    });
    parentRecyclerView.setLayoutManager(mLayoutManager);



    parentRecyclerView.setHasFixedSize(true);
    parentRecyclerView.setLayoutManager(mLayoutManager);
    ParentAdapter = new NewSurahDisplayAdapter(getContext(), allAnaChapters);


    ParentAdapter.setUp(allAnaChapters);
    parentRecyclerView.setAdapter(ParentAdapter);



    return view;
  }




  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);












    ParentAdapter.SetOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(View v, int position) {

        ChaptersAnaEntity item = (ChaptersAnaEntity) ParentAdapter.getItem(position);
        ;
        item.getChapterid();


    //    Intent intent = new Intent(DarkThemeApplication.getContext(), ReadingSurahPartActivity.class);
     //   intent.putExtra("chapter", item.getChapterid());
      //  intent.putExtra("chapterorpart",true);
      //  intent.putExtra(  "partname",item.getAbjadname());
      //  startActivity(intent);
        passData(item.getChapterid(),item.getAbjadname(),item.getVersescount(),item.getRukucount(),item.getIsmakki());

      }

      private void passData(int chapterid, String abjadname, int versescount, int rukucount,int ismakki) {
        getDatapasser().ondatarecevied(chapterid, abjadname,versescount,rukucount,ismakki);

      }
    });

/*

  private void passData(int chapter_no,String partname,int versescount,int rukucount) {
    getDatapasser().ondatarecevied(chapter_no,true,partname);
  }

 */

  }




  public OnItemClickListener getmItemClickListener() {
    return mItemClickListener;
  }

  public void setmItemClickListener(OnItemClickListener mItemClickListener) {
    this.mItemClickListener = mItemClickListener;
  }



  public PassdataInterface getPassdataInterface() {
    return passdataInterface;
  }

  public void setPassdataInterface(PassdataInterface passdataInterface) {
    this.passdataInterface = passdataInterface;
  }

  public PassdataInterface getDatapasser() {
    return datapasser;
  }

  public void setDatapasser(PassdataInterface datapasser) {
    this.datapasser = datapasser;
  }
}