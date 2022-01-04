package org.sj.conjugator.fragments;


import static com.example.Constant.*;
import static org.sj.conjugator.utilities.ArabicLiterals.AlifHamzaAbove;
import static org.sj.conjugator.utilities.ArabicLiterals.AlifMaksuraString;
import static org.sj.conjugator.utilities.ArabicLiterals.Hamza;
import static org.sj.conjugator.utilities.ArabicLiterals.LALIF;
import static org.sj.conjugator.utilities.ArabicLiterals.Ya;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
import com.example.utility.QuranGrammarApplication;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import Utility.ArabicLiterals;
import ru.dimorinny.floatingtextbutton.FloatingTextButton;


public class Dictionary_frag extends Fragment {
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


    public Dictionary_frag(LughatWordDetailsAct lughatWordDetailsAct, String language) {
        this.context = context;
        this.language = language;

    }

    public Dictionary_frag newInstance() {

        Dictionary_frag f = new Dictionary_frag((LughatWordDetailsAct) context, language);

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


                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();

                fm.popBackStack();


            }
        });



        Utils utils = new Utils(QuranGrammarApplication.getContext());
        verbroot = dataBundle.getString(QURAN_VERB_ROOT);
        arabicword = dataBundle.getString("arabicword");
        vocabroot = dataBundle.getString(QURAN_VERB_ROOT);
        //for lughat convert hamaza to alif
        int starts = vocabroot.indexOf(ArabicLiterals.Hamza);
        String hamza = "ء";
        if (starts != -1) {
            vocabroot = vocabroot.replace(ArabicLiterals.Hamza, LALIF.trim());
        }
        if (null != arabicword) {
            dictionary = utils.getArabicWord(arabicword);

        } else if (language.equals("lanes")) {

            int indexOfHamza = verbroot.indexOf(Hamza);
            if (indexOfHamza != -1) {
                verbroot = verbroot.replaceAll(Hamza, LALIF);
            }
            Character C1 = verbroot.charAt(0);
            Character C2 = verbroot.charAt(1);
            Character C3 = verbroot.charAt(2);
            if (C3.toString().equals(Ya)) {
                verbroot = verbroot.replace(Ya, AlifMaksuraString);
            } else if (C3.toString().equals(LALIF)) {
                verbroot = verbroot.replace(LALIF, AlifHamzaAbove);
            } else if (C2.toString().equals(C3.toString())) {
                verbroot = verbroot.substring(0, 2);
            }
            StringBuilder difinitionbuilder = new StringBuilder();

            lanesdictionary = utils.getLanesDifinition(verbroot);

            //  <p style="margin-left:200px; margin-right:50px;">
            difinitionbuilder.append(html);
            String replaced = "";
            for (lanelexicon lanes : lanesdictionary) {
            //  replaced = getString(lanes);
              replaced=lanes.getDefinition();
                int indexOf = replaced.indexOf("<orth extent=\"full\" lang=\"ar\">ذ</orth>");
                int indexofForm=replaced.indexOf("<form>");
                int indexofFormclose=replaced.indexOf("</form>");
                int indexofforminfl=replaced.indexOf("<form n=\"infl\">");
                difinitionbuilder.append("<p dir='ltr' style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">");

                if(indexOf!=-1){
                   replaced=replaced.replaceAll("<orth extent=\"full\" lang=\"ar\">ذ</orth>","");
              //     difinitionbuilder.append(replaced);
               }
                if(indexofForm!=-1){
                    replaced=replaced.replaceAll("<form>","");
               //     difinitionbuilder.append(replaced);
                }
                if(indexofFormclose!=-1){
                    replaced=replaced.replaceAll("</form>","");
                //    difinitionbuilder.append(replaced);
                }
                if(indexofforminfl!=-1){
                    replaced=replaced.replaceAll("<form n=\"infl\">","");
                //    difinitionbuilder.append(replaced);
                }

                difinitionbuilder.append(replaced);

                difinitionbuilder.append("</p>");
                difinitionbuilder.append("<hr size=\"1\" width=\"100%\" color=\"red\">  ");

            }

            worddifinition.add(difinitionbuilder.toString());
        } else if (language.equals("hans")) {



            String probableRoot = verbroot;


            Character C1 = verbroot.charAt(0);
            Character C2 = verbroot.charAt(1);
            Character C3 = verbroot.charAt(2);
            if (C3.toString().equals(Ya)) {
                verbroot = verbroot.replace(Ya, AlifMaksuraString);
            } else if (C3.toString().equals(LALIF)) {
                verbroot = verbroot.replace(LALIF, Hamza);//change alif to hamza
            } else if (C2.toString().equals(C3.toString())) {
                verbroot = verbroot.substring(0, 2);//contract the double at end
            }
            StringBuilder hanssb = new StringBuilder();

            hansdictionary = utils.getHansDifinition(verbroot);
            if (hansdictionary.isEmpty()) {
                hansdictionary = utils.getHansDifinition(probableRoot);
            }
            hanssb.append(html);
            for (hanslexicon lanes : hansdictionary) {
                //  <p style="margin-left:200px; margin-right:50px;">
                hanssb.append("<p style=\"margin-left:10px; margin-right:10px;\">");
                hanssb.append(lanes.getDefinition()).append("</p>");
                hanssb.append("<hr size=\"1\" width=\"100%\" color=\"red\">  ");
            }
            hanssb.append("</html");
            worddifinition.add(hanssb.toString());
        } else {


            dictionary = utils.getRootDictionary(vocabroot.trim());
        }


        recyclerView = view.findViewById(R.id.sarfrecview);
        WordLughatAdapter ska = null;
        LexiconAdapter lanesLexiconAdapter;
        if (language.equals("lanes") || language.equals("hans")) {
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

    @NonNull
    private String getString(lanelexicon lanes) {
        String replaced;
        replaced = lanes.getDefinition();
        int indexOf = lanes.getDefinition().indexOf("<entryFree");
        int indexOfclose = lanes.getDefinition().indexOf("</entryFree>");
        if (indexOf != -1) {
            replaced = replaced.replaceAll("<entryFree", "<p>");
        }
        if (indexOfclose != -1) {
            replaced = replaced.replaceAll("</entryFree>", "</p>");
        }
        return replaced;
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
