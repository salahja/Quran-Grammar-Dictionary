package org.sj.conjugator.fragments;



import static com.example.Constant.*;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Activity.LughatWordDetailsAct;
import com.example.mushafconsolidated.Adapters.LexiconAdapter;
import com.example.mushafconsolidated.Adapters.WordLughatAdapter;
import com.example.mushafconsolidated.Entities.hanslexicon;
import com.example.mushafconsolidated.Entities.lanelexicon;
import com.example.mushafconsolidated.Entities.lughat;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.Utils;
import com.example.utility.DarkThemeApplication;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;


public class FragmentEnglish_lughat extends Fragment {
  private static final int WRITE_REQUEST_CODE = 101;
  private static final String TAG = "PermissionDemo";


  RecyclerView recyclerView;
  private String verbweakness;
  private String callingfragment;
  private ArrayList<ArrayList> skabeer = new ArrayList<>();
  private Context context;
  private int verbformmazeed;
  private String verbform;
  private String verbroot, arabicword;
  private ArrayList<lughat> dictionary;
  private ArrayList<lanelexicon> lanesdictionary;
  private ArrayList<hanslexicon> hansdictionary;
  ArrayList<String> worddifinition = new ArrayList<>();
  String language;
  private String vocabroot;

  public FragmentEnglish_lughat(LughatWordDetailsAct lughatWordDetailsAct, String language) {
    this.context = context;
    this.language = language;

  }

  public FragmentEnglish_lughat newInstance() {

    FragmentEnglish_lughat f = new FragmentEnglish_lughat((LughatWordDetailsAct) context, language);

    Bundle dataBundle = getArguments();
    assert dataBundle != null;
    if (null != dataBundle) {
      //  Parcelable[] dictionaries = dataBundle.getParcelableArray("dictionary");

      String string = dataBundle.getString(QURAN_VERB_ROOT);
      verbform = dataBundle.getString(QURAN_VERB_WAZAN);
      verbweakness = dataBundle.getString(SARFKABEERWEAKNESS);
      callingfragment = dataBundle.getString(MUJARRADVERBTAG);
      vocabroot = dataBundle.getString(QURAN_VERB_ROOT);
    }

    f.setArguments(dataBundle);

    return f;

  }


  @Override
  public void onDetach() {
    super.onDetach();
    recyclerView.removeAllViews();
    Log.d("TAG", "verb fragment Detached");
  }


  @Override
  public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    setHasOptionsMenu(true);

    View view = inflater.inflate(R.layout.sarfkabeerheader, container, false);
    FloatingTextButton callButton = view.findViewById(R.id.action_buttons);
    Bundle dataBundle = getArguments();
    if (dataBundle != null) {
      String callingfragment = dataBundle.getString(MUJARRADVERBTAG);
      if (callingfragment != null) {
        if (callingfragment.equals("tverblist")) {
          callButton.setVisibility(View.VISIBLE);
        } else {
          callButton.setVisibility(View.GONE);
        }
      } else {
        callButton.setVisibility(View.GONE);
      }
    }
    //   callButton.setVisibility(View.VISIBLE);
    callButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //     FragmentManager fragmentManager = null;
        //   fragmentManager.popBackStack("mujarrad", FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentManager fm = getActivity()
              .getSupportFragmentManager();
        //   fm.popBackStack ("mujarrad", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        //    getFragmentManager().popBackStack();
        //   FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
        //     fm.popBackStack();

      }
    });


    assert dataBundle != null;
    Utils utils = new Utils(DarkThemeApplication.getContext());
    verbroot = dataBundle.getString(QURAN_VERB_ROOT);
    arabicword = dataBundle.getString("arabicword");
    vocabroot = dataBundle.getString(QURAN_VERB_ROOT);
    if (null != arabicword) {
      dictionary = utils.getArabicWord(arabicword);

    } else   if (language.equals("lanes")) {
      char[] chars = verbroot.toCharArray();

      StringBuilder difinitionbuilder = new StringBuilder();

        lanesdictionary = utils.getLanesDifinition(vocabroot);
        for (lanelexicon lanes : lanesdictionary) {
          //  <p style="margin-left:200px; margin-right:50px;">
          difinitionbuilder.append("<p style=\"margin-left:200px; margin-right:50px;\">");
          difinitionbuilder.append(lanes.getDefinition()).append("</p>");
        }
        worddifinition.add(difinitionbuilder.toString());
      }

    else   if (language.equals("hans")) {
    char[] chars = verbroot.toCharArray();

    StringBuilder lanessb = new StringBuilder();

      hansdictionary = utils.getHansDifinition(vocabroot);
    for (hanslexicon lanes : hansdictionary) {
      //  <p style="margin-left:200px; margin-right:50px;">
      lanessb.append("<p style=\"margin-left:200px; margin-right:50px;\">");
      lanessb.append(lanes.getDefinition()).append("</p>");
    }
    worddifinition.add(lanessb.toString());
  }



    else {
        dictionary = utils.getRootDictionary(vocabroot.trim());
      }





    recyclerView = view.findViewById(R.id.sarfrecview);
    WordLughatAdapter ska = null;
    LexiconAdapter lanesLexiconAdapter;
    if (language.equals("lanes")||language.equals("hans")) {
      if (worddifinition.isEmpty()) {
        worddifinition.add("Word not Updated");
      }
      lanesLexiconAdapter = new LexiconAdapter(worddifinition, getContext(), language);
      recyclerView.setAdapter(lanesLexiconAdapter);
    } else {

      ska = new WordLughatAdapter(dictionary, getContext(), language);
      recyclerView.setAdapter(ska);
    }

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    return view;
  }


  @Override
  public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    RecyclerView recyclerView;
    recyclerView = view.findViewById(R.id.sarfrecview);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);


  }

}
