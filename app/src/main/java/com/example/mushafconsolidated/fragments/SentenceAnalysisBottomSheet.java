package com.example.mushafconsolidated.fragments;

import static android.graphics.Color.CYAN;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.YELLOW;
import static com.example.Constant.AYAHNUMBER;
import static com.example.Constant.BCYAN;
import static com.example.Constant.DeepPink;
import static com.example.Constant.FORESTGREEN;
import static com.example.Constant.GOLD;
import static com.example.Constant.GREENDARK;
import static com.example.Constant.ORANGE400;
import static com.example.Constant.QURAN_VERB_ROOT;
import static com.example.Constant.QURAN_VERB_WAZAN;
import static com.example.Constant.SURAH_ID;
import static com.example.Constant.WHOTPINK;
import static com.example.Constant.harfinnaspanDark;
import static com.example.Constant.harfismspanDark;
import static com.example.Constant.harfkhabarspanDark;
import static com.example.Constant.harfshartspanDark;
import static com.example.Constant.jawabshartspanDark;
import static com.example.Constant.mudhafspansDark;
import static com.example.Constant.prussianblue;
import static com.example.Constant.shartspanDark;
import static com.example.Constant.sifaspansDark;
import static com.example.utility.CorpusUtilityorig.getSpancolor;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Activity.WordOccuranceAsynKAct;
import com.example.mushafconsolidated.Adapters.RootWordDisplayAdapter;
import com.example.mushafconsolidated.Adapters.SentenceRootWordDisplayAdapter;
import com.example.mushafconsolidated.Entities.NewCorpusExpandWbwPOJO;
import com.example.mushafconsolidated.Entities.NewKanaEntity;
import com.example.mushafconsolidated.Entities.NewMudhafEntity;
import com.example.mushafconsolidated.Entities.NewNasbEntity;
import com.example.mushafconsolidated.Entities.NewShartEntity;
import com.example.mushafconsolidated.Entities.NounCorpus;
import com.example.mushafconsolidated.Entities.SifaEntity;
import com.example.mushafconsolidated.Entities.VerbCorpus;
import com.example.mushafconsolidated.Entities.VerbWazan;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.Utils;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.mushafconsolidated.model.SarfSagheerPOJO;
import com.example.utility.DarkThemeApplication;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import org.sj.conjugator.activity.ConjugatorTabsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import database.DatabaseUtils;
import database.entity.MujarradVerbs;


/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class SentenceAnalysisBottomSheet extends BottomSheetDialogFragment {

    public static final String TAG = "bottom";
    int chapterid = 0;
    int ayanumber = 0;

    // TODO: Customize parameter argument names
    private static final String ARG_OPTIONS_DATA = "options_data";
    private   String preferences;
    private SharedPreferences prefs;

    boolean isMazeedSarfSagheer;
    boolean isThulathiSarfSagheer;
    boolean isverbconjugaton;
    boolean participles;
    boolean noun;
    private String HarfNasbAndZarf;
    private SpannableStringBuilder spannableShart;
    private SpannableStringBuilder spannableHarf;
    private SpannableStringBuilder spannablemousufmudhaf;
    private SpannableStringBuilder spannable;

    HashMap<Integer, HashMap<String, SpannableStringBuilder>> wordetailsall = new HashMap<>();
    HashMap<Integer, HashMap<String, String>> verbdetailsall = new HashMap<>();
    HashMap<String, String> vbdetail = new HashMap<>();
    HashMap<String, SpannableStringBuilder> wordbdetail;

    boolean issentence = false;
    private ArrayList<NewCorpusExpandWbwPOJO> SencorpusSurahWord;
    private ArrayList<NewCorpusExpandWbwPOJO> corpusSurahWord;
    private AlertDialog dialog;

    public boolean isNoun() {
        return noun;
    }

    public void setNoun(boolean noun) {
        this.noun = noun;
    }

    private ArrayList<ArrayList> ismfaelmafool;
    private VerbWazan vb;

    private RootWordDisplayAdapter rwAdapter;
    ArrayList<ArrayList> ThulathiMazeedConjugatonList;
    SarfSagheerPOJO sarf;
    private SentenceRootWordDisplayAdapter sentenceRootWordDisplayAdapter;

    public boolean isParticiples() {
        return participles;
    }

    public void setParticiples(boolean participles) {
        this.participles = participles;
    }

    public boolean isIsverbconjugaton() {
        return isverbconjugaton;
    }

    public void setIsverbconjugaton(boolean isverbconjugaton) {
        this.isverbconjugaton = isverbconjugaton;
    }

    public boolean isMazeedSarfSagheer() {
        return isMazeedSarfSagheer;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public String getHarfNasbAndZarf() {
        return HarfNasbAndZarf;
    }

    public void setHarfNasbAndZarf(String harfNasbAndZarf) {
        HarfNasbAndZarf = harfNasbAndZarf;
    }

    public void setMazeedSarfSagheer(boolean mazeedSarfSagheer) {
        isMazeedSarfSagheer = mazeedSarfSagheer;
    }

    public boolean isThulathiSarfSagheer() {
        return isThulathiSarfSagheer;
    }

    public void setThulathiSarfSagheer(boolean thulathiSarfSagheer) {
        isThulathiSarfSagheer = thulathiSarfSagheer;
    }


    // TODO: Customize parameters
    @NonNull
    public static SentenceAnalysisBottomSheet newInstance(String[] data) {
        final SentenceAnalysisBottomSheet fragment = new SentenceAnalysisBottomSheet();
        final Bundle args = new Bundle();
        args.putStringArray(ARG_OPTIONS_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sentence_root_dialog_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recview);
        Bundle bundle = this.getArguments();
        String[] stringArray = bundle.getStringArray(ARG_OPTIONS_DATA);
        chapterid = Integer.parseInt(stringArray[0]);
        prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
        preferences = prefs.getString("theme", "dark");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        dialog = builder.create();


      //  recyclerView.setLayoutParams(new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        sentenceRootWordDisplayAdapter = new SentenceRootWordDisplayAdapter(getContext());
        SharedPreferences shared =
              PreferenceManager.getDefaultSharedPreferences(getContext());
        issentence = shared.getBoolean("grammarsentence", false);

        ayanumber = Integer.parseInt(stringArray[1]);
        String wbwtranslation = stringArray[2];
        int wordno = Integer.parseInt(stringArray[3]);

        Utils utils = new Utils(getActivity());
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.execute(() -> {
            getActivity().runOnUiThread(() -> {

                dialog.show();
            });

            //  setAyanSpans();
            NewsetAyanSpans();

            getActivity().runOnUiThread(() -> {

                dialog.dismiss();
                HashMap<Integer, HashMap<String, String>> sorted = new HashMap<Integer, HashMap<String, String>>();

                // Copy all data from hashMap into TreeMap
                sorted.putAll(verbdetailsall);

                sentenceRootWordDisplayAdapter.setRootWordsAndMeanings(corpusSurahWord, spannableShart, spannableHarf, spannable, wordetailsall, sorted, getActivity());
                recyclerView.setAdapter(sentenceRootWordDisplayAdapter);
              //  recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));


            });

        });
        ex.shutdown();


        return view;


    }

    private void NewsetAyanSpans() {
        HashMap<String, String> Sbdetail = new HashMap<>();
        HashMap<String, SpannableStringBuilder> Swordbdetail = new HashMap<>();
        Utils utils = new Utils(getActivity());
        corpusSurahWord = utils.getCorpusWbwBySurahAyah(chapterid, ayanumber);
        ArrayList<VerbCorpus> verbCorpuses = utils.getQuranRootaAyah(chapterid, ayanumber);
        int index = 1;
        ArrayList<NounCorpus> nounCorpuses = utils.getQuranNounAyah(chapterid, ayanumber);
        for (NewCorpusExpandWbwPOJO corpusExpandWbwPOJO : corpusSurahWord) {


            SentenceQuranMorphologyDetails qm = new SentenceQuranMorphologyDetails(index, corpusSurahWord, nounCorpuses, verbCorpuses, getContext());


            Swordbdetail = qm.getWordDetails();

            if (!Swordbdetail.isEmpty()) {
                wordetailsall.put(index, Swordbdetail);
                //  this. Swordbdetail.clear();
            }
            if (!Sbdetail.isEmpty()) {
                //         verbdetailsall.put(index, Sbdetail);
                //    Sbdetail.clear();
            }
            index++;
        }
        index = 1;
        for (NounCorpus noun : nounCorpuses) {

            SentenceQuranMorphologyDetails qm = new SentenceQuranMorphologyDetails(index, corpusSurahWord, nounCorpuses, verbCorpuses, getContext());

            Swordbdetail = qm.getNoundetails();


            if (!Swordbdetail.isEmpty()) {
                if (wordetailsall.containsKey(noun.getWordno())) {
                    wordetailsall.get(noun.getWordno()).put("noun", Swordbdetail.get("noun"));
                    wordetailsall.get(noun.getWordno()).put("PCPL", Swordbdetail.get("pcpl"));
              //      wordetailsall.get(noun.getWordno()).put("form", Swordbdetail.get("form"));
                    wordetailsall.get(noun.getWordno()).put("PART", Swordbdetail.get("PART"));
                }
            }

            //    if (!Swordbdetail.isEmpty()) {
            //       wordetailsall.put(index, Swordbdetail);
            //  this. Swordbdetail.clear();
            //    }
            if (!Sbdetail.isEmpty()) {
                //         verbdetailsall.put(index, Sbdetail);
                //    Sbdetail.clear();
            }
            index++;
        }

        index = 1;
        for (VerbCorpus noun : verbCorpuses) {

            SentenceQuranMorphologyDetails qm = new SentenceQuranMorphologyDetails(index, corpusSurahWord, nounCorpuses, verbCorpuses, getContext());



            Sbdetail = qm.getVerbDetails();




            if (!Sbdetail.isEmpty()) {
                //      verbdetailsall.put(index, Sbdetail);
                verbdetailsall.put(noun.getWordno(), Sbdetail);
             //   wordetailsall.put(noun.getWordno(),Sbdetail);
            }
            index++;
        }

        TreeMap<Integer, HashMap<String, String>> sorted = new TreeMap<>();

        sorted.putAll(verbdetailsall);


        if (!corpusSurahWord.isEmpty()) {
            String quranverses = corpusSurahWord.get(0).getQurantext();
            spannable = new SpannableStringBuilder(quranverses);
            SetShart(utils);

            setHarfNasb(utils);
            SetMausoofSifa(utils);
            SetMudhaf(utils);
            SetKana(utils);

        }
    }

    protected void setHarfNasb(Utils utils) {

        ArrayList<NewNasbEntity> harfnasb = utils.getHarfNasbIndSurahAyahSnew(chapterid, ayanumber);

        for (NewNasbEntity nasb : harfnasb) {
            //   harfinnaspanDark = new ForegroundColorSpan(GREEN);
            //    harfismspanDark = new ForegroundColorSpan(BCYAN);
            //   harfkhabarspanDark = new ForegroundColorSpan(YELLOW);

            if(preferences.equals("dark")||preferences.equals("blue"))
            {
                harfinnaspanDark=new ForegroundColorSpan(GREEN);
                harfismspanDark=new ForegroundColorSpan(BCYAN);
                harfkhabarspanDark=new ForegroundColorSpan(YELLOW);

            }else{
                harfinnaspanDark=new ForegroundColorSpan(WHOTPINK);
                harfismspanDark=new ForegroundColorSpan(prussianblue);
                harfkhabarspanDark=new ForegroundColorSpan(DeepPink);
            }

            String harfofverse = "";
            String ismofverse = "";
            String khabarofinna = "";
            int start = nasb.getIndexstart();
            int end = nasb.getIndexend();
            int ismstart = nasb.getIsmstart();
            int ismend = nasb.getIsmend();
            int khabarstart = nasb.getKhabarstart();
            int khabarend = nasb.getKhabarend();
            this.spannable.setSpan(harfinnaspanDark, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.spannable.setSpan(harfismspanDark, ismstart, ismend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.spannable.setSpan(harfkhabarspanDark, khabarstart, khabarend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
    }

    protected void SetMudhaf(Utils utils) {
        ArrayList<NewMudhafEntity> mudhafSurahAyah = utils.getMudhafSurahAyahNew(chapterid, ayanumber);

        String jumlashart = "جملة شرطية";


        //  this.spannable = new SpannableStringBuilder(quranverses);
        //  this.spannableHarf = new SpannableStringBuilder(quranverses);

        for (NewMudhafEntity mudhafEntity : mudhafSurahAyah) {
            mudhafspansDark=getSpancolor(preferences,true);
            spannable.setSpan(mudhafspansDark, mudhafEntity.getStartindex(), mudhafEntity.getEndindex(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        }
    }

    protected void SetMausoofSifa(Utils utils) {
        ArrayList<SifaEntity> sifabySurahAyah = utils.getSifabySurahAyah(chapterid, ayanumber);

        //  String quranverses = corpusSurahWord.get(0).getQurantext();
        for (SifaEntity shartEntity : sifabySurahAyah) {

            sifaspansDark = getSpancolor(preferences, false);
            try {
                spannable.setSpan(sifaspansDark, shartEntity.getStartindex(), shartEntity.getEndindex(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } catch (IndexOutOfBoundsException e) {
                //           System.out.println(shartEntity.getSurah() + "  " + shartEntity.getAyah() + "  " + quranverses);
            }


        }
    }

    protected void SetShart(Utils utils) {
        ArrayList<NewShartEntity> shart = utils.getShartSurahAyahNew(chapterid, ayanumber);

        //  this.spannable = new SpannableStringBuilder(quranverses);
        SpannableStringBuilder spannable;
        for (NewShartEntity shartEntity : shart) {
            prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
            preferences = prefs.getString("theme", "dark");
            harfshartspanDark = new ForegroundColorSpan(GOLD);
            shartspanDark = new ForegroundColorSpan(GREEN);
            jawabshartspanDark = new ForegroundColorSpan(CYAN);
            if(preferences.equals("dark")||preferences.equals("blue"))
            {
                harfshartspanDark=new ForegroundColorSpan(GOLD);
                shartspanDark=new ForegroundColorSpan(ORANGE400);
                jawabshartspanDark=new ForegroundColorSpan(CYAN);
            }else{
                harfshartspanDark=new ForegroundColorSpan(FORESTGREEN);
                shartspanDark=new ForegroundColorSpan(GREENDARK);
                jawabshartspanDark=new ForegroundColorSpan(WHOTPINK);
            }
            this.spannable.setSpan(harfshartspanDark, shartEntity.getIndexstart(), shartEntity.getIndexend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.spannable.setSpan(shartspanDark, shartEntity.getShartindexstart(), shartEntity.getShartindexend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.spannable.setSpan(jawabshartspanDark, shartEntity.getJawabshartindexstart(), shartEntity.getJawabshartindexend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        }
    }

    protected void SetKana(Utils utils) {
        ArrayList<NewKanaEntity> kana = utils.getKanaSurahAyahnew(chapterid, ayanumber);

        //  this.spannable = new SpannableStringBuilder(quranverses);

        for (NewKanaEntity kanaEntity : kana) {
            harfshartspanDark = new ForegroundColorSpan(GOLD);
            shartspanDark = new ForegroundColorSpan(GREEN);
            jawabshartspanDark = new ForegroundColorSpan(CYAN);

            this.spannable.setSpan(harfshartspanDark, kanaEntity.getIndexstart(), kanaEntity.getIndexend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.spannable.setSpan(shartspanDark, kanaEntity.getIsmkanastart(), kanaEntity.getIsmkanaend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.spannable.setSpan(jawabshartspanDark, kanaEntity.getKhabarstart(), kanaEntity.getKhabarend(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        }
    }






    /*

     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sentenceRootWordDisplayAdapter.SetOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {


                CharSequence text = null;
                CharSequence text2 = null;
                View viewVerbConjugation = v.findViewById(R.id.verbconjugationbtn);
                View verbOccuranceView = v.findViewById(R.id.verboccurance);
                View wordoccurance = v.findViewById(R.id.wordoccurance);
                View verse = v.findViewById(R.id.spannableverse);
                View wordview = v.findViewById(R.id.wordView);
                SpannableStringBuilder root = wordetailsall.get(position + 1).get("root");
                vb = new VerbWazan();
                vb.setRoot(String.valueOf(root));
                if(wordview!=null) {
                    SpannableStringBuilder tag = wordetailsall.get(position + 1).get("tag");
                    if(tag.equals("V")){

                        verbdetailsall.get("root");

                    }else if (wordetailsall.get(position+1).get("root") != null && (wordetailsall.get(position+1).get("PCPL") != null)) {


                        String roots = String.valueOf(wordbdetail.get("root"));
                        vb = new VerbWazan();
                        vb.setRoot(roots);
                        //    vb.setWazan("N");
                        char[] chars = roots.toCharArray();

                        boolean first = roots.startsWith("أ");
                        int second = roots.indexOf("أ");
                        if (first) {
                            roots = roots.replace("أ", "ء");
                        } else if (second != -1) {
                            roots = roots.replace("أ", "ء");
                        }
                        Character C1 = chars[0];
                        Character C2 = chars[1];
                        Character C3 = chars[2];
                        C1.toString();

                        String madhiharakah;
                        String muhdarayharakah;
                        ArrayList<String> harakah = new ArrayList<>();
                        if (harakah.size() > 0)

                            harakah.clear();
                        DatabaseUtils utils = new DatabaseUtils(getActivity());
                        final ArrayList<MujarradVerbs> triVerb = utils.getMujarradVerbs(roots);
                        List<MujarradVerbs> verbDictList= new ArrayList<>();
                        ArrayList<String> wazannumberslist = new ArrayList<>();
                        for (MujarradVerbs tri : triVerb) {
                            verbDictList.add(new MujarradVerbs(tri.getVerb(), tri.getRoot(), tri.getBabname(), tri.getVerbtype()));
                            wazannumberslist.add(tri.getBabname().concat("-").concat(tri.getVerbtype().concat("-")));
                        }

                        if (!triVerb.isEmpty()) {
                            setParticiples(true);
                          //  harakah = Harakah.parseHarakah(triVerb.get(0).getBabname());


                            madhiharakah = harakah.get(0);
                            muhdarayharakah = harakah.get(1);
                            vb.setWazan(harakah.get(2));
                            //SarfKabeerMujarrad sk = new SarfKabeerMujarrad(madhiharakah, muhdarayharakah, getContext());
                          //  ismfaelmafool = sk.SarfKabeerThulathi(roots);

                        } else {
                            setParticiples(false);
                        }


                    }


                }else  if (verse != null) {
                    GrammerFragmentsBottomSheet item = new GrammerFragmentsBottomSheet();
                    //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
                    FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
                    Bundle dataBundle = new Bundle();

                    dataBundle.putInt(SURAH_ID, chapterid);dataBundle.putInt(AYAHNUMBER, ayanumber);


                    //   dataBundle.putInt(WORDNUMBER, Math.toIntExact(word.getWordno()));
                    //   dataBundle.putString(SURAH_ARABIC_NAME, SurahName);
                    item.setArguments(dataBundle);
                    String data[] = {String.valueOf(chapterid), String.valueOf(ayanumber)};
                    FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
                    //   transactions.show(item);
                    GrammerFragmentsBottomSheet.newInstance(data).show((getActivity().getSupportFragmentManager()), WordAnalysisBottomSheet.TAG);


                } else if (!(wordoccurance == null) && !(root == null)) {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(getActivity(), WordOccuranceAsynKAct.class);

                    try {
                        bundle.putString(QURAN_VERB_ROOT, vb.getRoot());
                    } catch (NullPointerException e1) {
                        e1.printStackTrace();
                        bundle.putString(QURAN_VERB_ROOT, getHarfNasbAndZarf());

                    }
                    intent.putExtras(bundle);
                    //   intent.putExtra(QURAN_VERB_ROOT,vb.getRoot());
                    startActivity(intent);


                } else if (viewVerbConjugation != null) {
                    text = ((MaterialButton) viewVerbConjugation).getText();
                    if (text.toString().equals("Click for Verb Conjugation")) {
                        if (vb.getRoot() != null && !vb.getWazan().equals("null") && !vb.getWazan().equals("0")) {

                            Bundle dataBundle = new Bundle();
                            //      ArrayList arrayList = ThulathiMazeedConjugatonList.get(position);
                            //   arrayList.get(0).ge
                            dataBundle.putString(QURAN_VERB_WAZAN, vb.getWazan());
                            dataBundle.putString(QURAN_VERB_ROOT, vb.getRoot());
                            Intent intent = new Intent(getActivity(), ConjugatorTabsActivity.class);

                            intent.putExtras(dataBundle);
                            startActivity(intent);

                        }
                    }

                }

            }
        });


    }





}