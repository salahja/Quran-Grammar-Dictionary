package com.example.mushafconsolidated.fragments;

import static android.content.Context.MODE_PRIVATE;
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
import static com.example.Constant.VERBMOOD;
import static com.example.Constant.VERBTYPE;
import static com.example.Constant.WHOTPINK;
import static com.example.Constant.harfinnaspanDark;
import static com.example.Constant.harfismspanDark;
import static com.example.Constant.harfkhabarspanDark;
import static com.example.Constant.harfshartspanDark;
import static com.example.Constant.jawabshartspanDark;
import static com.example.Constant.mazeedsignificance;
import static com.example.Constant.mudhafspansDark;
import static com.example.Constant.prussianblue;
import static com.example.Constant.shartspanDark;
import static com.example.Constant.sifaspansDark;
import static com.example.utility.CorpusUtilityorig.getSpancolor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Activity.LughatWordDetailsAct;
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
import com.example.mushafconsolidated.Entities.lughat;
import com.example.mushafconsolidated.Entities.wbwentity;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.Utils;
import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.example.mushafconsolidated.model.SarfSagheerPOJO;
import com.example.utility.DarkThemeApplication;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.tooltip.Tooltip;

import org.sj.conjugator.activity.ConjugatorTabsActivity;
import org.sj.conjugator.fragments.SarfSagheer;
import org.sj.conjugator.utilities.GatherAll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class WordAnalysisBottomSheet extends BottomSheetDialogFragment {

    public static final String TAG = "bottom";
    int chapterid = 0;
    int ayanumber = 0;

    // TODO: Customize parameter argument names
    private static final String ARG_OPTIONS_DATA = "options_data";
    private SpannableStringBuilder spannableHarf;
    private SpannableStringBuilder spannable;
    boolean isMazeedSarfSagheer;
    private String themepreference;
    private boolean ismujarrad, ismazeed, isparticple;
    private boolean isconjugation;
    private String root;
    private String mujarradwazan, mazeedwazan;
    private String verbmood;

    public void setQuadrilateral(boolean quadrilateral) {
        this.quadrilateral = quadrilateral;
    }

    boolean isThulathiSarfSagheer;
    boolean isverbconjugaton;
    boolean participles, isVerb;
    boolean noun;
    private String HarfNasbAndZarf;
    private SpannableStringBuilder spannableShart;

    private SpannableStringBuilder spannablemousufmudhaf;

    public boolean isVerb() {
        return isVerb;
    }

    public void setVerb(boolean verb) {
        isVerb = verb;
    }

    List<MujarradVerbs> verbDictList;

    List<String> expandableListTitle;
    HashMap<String, List<SpannableStringBuilder>> expandableListDetail;

    HashMap<Integer, HashMap<String, SpannableStringBuilder>> wordetailsall = new HashMap<>();
    HashMap<Integer, HashMap<String, String>> verbdetailsall = new HashMap<>();
    HashMap<String, String> vbdetail = new HashMap<>();
    HashMap<String, SpannableStringBuilder> wordbdetail;

    boolean showGrammarFragments = false;
    private ArrayList<NewCorpusExpandWbwPOJO> SencorpusSurahWord;
    private ArrayList<NewCorpusExpandWbwPOJO> corpusSurahWord;
    private AlertDialog dialog;
    private View selectedview;

    public boolean isNoun() {
        return noun;
    }

    public void setNoun(boolean noun) {
        this.noun = noun;
    }

    private ArrayList<ArrayList> ismfaelmafool;
    private VerbWazan vb;
    private VerbWazan arabicword;
    protected RootWordDisplayAdapter rwAdapter;
    final ArrayList<SarfSagheer> sarfSagheerList=new ArrayList<>();

    public void setIsroot(boolean isroot) {
        this.isroot = isroot;
    }

    public void setIsarabicword(boolean isarabicword) {
        this.isarabicword = isarabicword;
    }

    final ArrayList<String> wazannumberslist = new ArrayList<>();
    SarfSagheerPOJO sarf;
    boolean isroot, isarabicword, quadrilateral,isnoun,ismujarradparticple;
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
    public static WordAnalysisBottomSheet newInstance(String[] data) {
        final WordAnalysisBottomSheet fragment = new WordAnalysisBottomSheet();
        final Bundle args = new Bundle();
        args.putStringArray(ARG_OPTIONS_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.root_dialog_fragment, container, false);

        SharedPreferences prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
        themepreference = prefs.getString("theme", "dark");
        RecyclerView recyclerView = view.findViewById(R.id.wordByWordRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        Bundle bundle = this.getArguments();
        String[] stringArray = bundle.getStringArray(ARG_OPTIONS_DATA);
        chapterid = Integer.parseInt(stringArray[0]);

        SharedPreferences shared =
                PreferenceManager.getDefaultSharedPreferences(getContext());
        showGrammarFragments = shared.getBoolean("fragments", false);

        ayanumber = Integer.parseInt(stringArray[1]);
        String wbwtranslation = stringArray[2];
        int wordno = Integer.parseInt(stringArray[3]);
        storepreferences(chapterid, ayanumber);
        Utils utils = new Utils(getActivity());
        ExecutorService ex = Executors.newSingleThreadExecutor();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        dialog = builder.create();
        rwAdapter = new RootWordDisplayAdapter(getContext());
        ex.execute(new Runnable() {
            @Override
            public void run() {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.show();
                    }
                });
                corpusSurahWord = utils.getCorpusWbwBySurahAyahWordid(chapterid, ayanumber, wordno);
                ArrayList<NounCorpus> corpusNounWord = utils.getQuranNouns(chapterid, ayanumber, wordno);
                ArrayList<VerbCorpus> verbCorpusRootWord = utils.getQuranRoot(chapterid, ayanumber, wordno);
                QuranMorphologyDetails am = new QuranMorphologyDetails(corpusSurahWord, corpusNounWord, verbCorpusRootWord, getContext());
           vb=new VerbWazan();
                wordbdetail = am.getWordDetails();
                if (verbCorpusRootWord.size() > 0 && verbCorpusRootWord.get(0).getTag().equals("V")) {
                    vbdetail = am.getVerbDetails();
                    setVerb(true);

                }
                //if any true..good for verb conjugation
                ismujarrad = vbdetail.get("wazan") != null;
                ismazeed = vbdetail.get("form") != null;

                isparticple = wordbdetail.get("particple") != null;
                isconjugation = ismujarrad || ismazeed || isparticple;
                isroot = wordbdetail.get("root") != null;
                if(isroot){
                    vb.setRoot(String.valueOf(wordbdetail.get("root")));
                }

               isnoun = wordbdetail.get("noun") != null;
                        if(isparticple){
                      if(wordbdetail.get("form").toString().equals("I")){
                          ismujarradparticple=true;
                      }else{
                          ismujarradparticple=false;
                      }

                        }
              root = vbdetail.get("root");
                if (ismujarrad) {
                    mujarradwazan = vbdetail.get("wazan");
                    verbmood = vbdetail.get("verbmood");
                } else if (ismazeed) {
                    mazeedwazan = vbdetail.get("form");
                    verbmood = vbdetail.get("verbmood");
                }


                //  vb.setRoot(String.valueOf(wordbdetail.get("root")));

                if (isroot && ismujarrad) {

                    vb = new VerbWazan();
                    vb.setRoot(root);
                    vb.setWazan(mujarradwazan);

                    ArrayList<ArrayList> listing = GatherAll.getInstance().getMujarradListing(verbmood, root, vb.getWazan());//     ThulathiMazeedConjugatonList = iniitThulathiQuerys(vbdetail.get("wazan"), vbdetail.get("root"));

                    SarfSagheer ss=new SarfSagheer();
                    ss.setWeakness(listing.get(0).get(0).toString());
                    ss.setWazanname(listing.get(0).get(1).toString());
                    ss.setVerbroot(listing.get(0).get(2).toString());

                    ss.setMadhi(listing.get(0).get(3).toString());
                    ss.setMadhimajhool(listing.get(0).get(4).toString());
                    ss.setMudharay(listing.get(0).get(5).toString());


                    ss.setMudharaymajhool(listing.get(0).get(6).toString());
                    ss.setAmrone(listing.get(0).get(7).toString());
                    ss.setNahiamrone(listing.get(0).get(8).toString());
                    ss.setIsmfael(listing.get(0).get(9).toString());
                    ss.setIsmmafool(listing.get(0).get(10).toString());
                    ss.setIsmalaone(listing.get(0).get(11).toString());
                    ss.setIsmalatwo(listing.get(0).get(12).toString());
                    ss.setIsmalathree(listing.get(0).get(13).toString());

                    ss.setZarfone(listing.get(0).get(14).toString());
                    ss.setZarftwo(listing.get(0).get(15).toString());
                    ss.setZarfthree(listing.get(0).get(16).toString());
                    ss.setVerbtype(listing.get(0).get(17).toString());
                    ss.setWazan(listing.get(0).get(18).toString());


                    sarfSagheerList.add(ss);
                    setIsroot(true);
                    setThulathiSarfSagheer(true);
                    setMazeedSarfSagheer(false);
                    setIsverbconjugaton(true);


                    boolean first = root.startsWith("أ");
                    int second = root.indexOf("أ");
                    if (first) {
                        root = root.replace("أ", "ء");
                    } else if (second != -1) {
                        root = root.replace("أ", "ء");
                    }


                    DatabaseUtils dutils = new DatabaseUtils(getActivity());
                    final ArrayList<MujarradVerbs> triVerb = dutils.getMujarradVerbs(root);
                    verbDictList = new ArrayList<>();
                    for (MujarradVerbs tri : triVerb) {
                        verbDictList.add(new MujarradVerbs(tri.getVerb(), tri.getRoot(), tri.getBabname(), tri.getVerbtype()));
                        wazannumberslist.add(tri.getBabname().concat("-").concat(tri.getVerbtype().concat("-")));
                    }


                } else if (isroot && ismazeed) {
                    setMazeedSarfSagheer(true);
                    setThulathiSarfSagheer(false);
                    setIsverbconjugaton(true);
                    ArrayList<ArrayList> listing = GatherAll.getInstance().getMazeedListing(verbmood, root, mazeedwazan);

                    SarfSagheer ss=new SarfSagheer();
                    ss.setWeakness(listing.get(0).get(0).toString());
                    ss.setWazanname(listing.get(0).get(1).toString());
                    ss.setVerbroot(listing.get(0).get(2).toString());

                    ss.setMadhi(listing.get(0).get(3).toString());
                    ss.setMadhimajhool(listing.get(0).get(4).toString());
                    ss.setMudharay(listing.get(0).get(5).toString());


                    ss.setMudharaymajhool(listing.get(0).get(6).toString());
                    ss.setAmrone(listing.get(0).get(7).toString());
                    ss.setNahiamrone(listing.get(0).get(8).toString());
                    ss.setIsmfael(listing.get(0).get(9).toString());
                    ss.setIsmmafool(listing.get(0).get(10).toString());
                    ss.setIsmalaone(listing.get(0).get(11).toString());
                    ss.setIsmalatwo(listing.get(0).get(12).toString());
                    ss.setIsmalathree(listing.get(0).get(13).toString());

                    ss.setZarfone(listing.get(0).get(14).toString());
                    ss.setZarftwo(listing.get(0).get(15).toString());
                    ss.setZarfthree(listing.get(0).get(16).toString());
                    ss.setVerbtype(listing.get(0).get(17).toString());
                    ss.setWazan(listing.get(0).get(18).toString());


                    sarfSagheerList.add(ss);


                    setIsroot(true);
                    vb = new VerbWazan();
                    vb.setRoot(vbdetail.get("root"));
                    vb.setWazan(vbdetail.get("form"));
                    String tafeel = mazeedsignificance.get("II");
                    vbdetail.put("mazeed", tafeel);


                } else if (isroot && isparticple) {
                    setIsroot(true);
                    setParticiples(true);
                    setIsverbconjugaton(false);
                    if(!ismujarradparticple) {
                        String form1 = String.valueOf(wordbdetail.get("form"));
                        String root = String.valueOf(wordbdetail.get("root"));
                        vb = new VerbWazan();
                        vb.setRoot(root);
                        vb.setWazan(form1);
                        ismfaelmafool = GatherAll.getInstance().buildAugmenteParticiples(root, form1);
                        setNoun(false);
                        String tafeel = mazeedsignificance.get("II");
                        vbdetail.put("mazeed", tafeel);
                    }else {

                      String root=vb.getRoot();
                        boolean first = root.startsWith("أ");
                        int second = root.indexOf("أ");
                        if (first) {
                            root = root.replace("أ", "ء");
                        } else if (second != -1) {
                            root = root.replace("أ", "ء");
                        }


                        DatabaseUtils databaseUtils=new DatabaseUtils(getActivity());

                         ArrayList<MujarradVerbs> triVerb = databaseUtils.getMujarradVerbs(root);
                        verbDictList = new ArrayList<>();
                        for (MujarradVerbs tri : triVerb) {
                            verbDictList.add(new MujarradVerbs(tri.getVerb(), tri.getRoot(), tri.getBabname(), tri.getVerbtype()));
                            wazannumberslist.add(tri.getBabname().concat("-").concat(tri.getVerbtype().concat("-")));
                        }

                        if (!triVerb.isEmpty()) {
                            setParticiples(true);
                            ismfaelmafool = GatherAll.getInstance().getMujarradParticiple( root, triVerb.get(0).getBab());



                            vb.setWazan(triVerb.get(0).getBab());



                        } else {
                            setParticiples(false);
                        }
                    }


                } else if (isroot && isnoun) {
                    vb=new VerbWazan();
                  vb.setRoot(root);
                  setIsroot(true);
                  setNoun(true);


                } else if (isroot && !isconjugation) {
                    String concat = corpusSurahWord.get(0).getAraone().concat("|").concat(corpusSurahWord.get(0).getAratwo());
                    arabicword = new VerbWazan();
                    ArrayList<lughat> arabicWord = utils.getArabicWord(String.valueOf(wordbdetail.get("arabicword")));
                    ArrayList<lughat> rootDictionary = utils.getRootDictionary(concat);
                    if (arabicWord.size() > 0) {
                        arabicword.setArabicword(arabicWord.get(0).getArabicword());
                        setIsroot(false);
                        setIsarabicword(true);
                    } else if (rootDictionary.size() > 0) {
                        setIsroot(false);
                        setIsarabicword(true);

                    } else {


                        setIsroot(false);
                        setIsarabicword(false);

                    }
                }


                corpusNounWord.size();
                try {
                    //   if (corpusNounWord.get(0).getTag().equals("ACC") || corpusNounWord.get(0).equals("T") || corpusNounWord.get(0).equals("LOC")) {
                //    vb = new VerbWazan();
                    if (corpusNounWord.size() > 0 && (corpusNounWord.get(0).getTag().equals("COND") || (corpusNounWord.get(0).getTag().equals("T") || corpusNounWord.get(0).getTag().equals("LOC")))) {
                        if (corpusNounWord.get(0).getTag().equals("COND")) {
                            setHarfNasbAndZarf("ACC");
                        } else if (corpusNounWord.get(0).getTag().equals("T")) {
                            setHarfNasbAndZarf("T");
                        }
                        if (corpusNounWord.get(0).getTag().equals("LOC")) {
                            setHarfNasbAndZarf("LOC");
                        }

                        setNoun(true);
                        setParticiples(false);


                    } else if (corpusNounWord.size() > 0 && (corpusNounWord.get(0).getTag().equals("ACC") || (corpusNounWord.get(0).getTag().equals("T") || corpusNounWord.get(0).getTag().equals("LOC")))) {
                        if (corpusNounWord.get(0).getTag().equals("ACC")) {
                            setHarfNasbAndZarf("ACC");
                        } else if (corpusNounWord.get(0).getTag().equals("T")) {
                            setHarfNasbAndZarf("T");
                        }
                        if (corpusNounWord.get(0).getTag().equals("LOC")) {
                            setHarfNasbAndZarf("LOC");
                        }
                        setNoun(true);
                        setParticiples(false);


                    }   else if (wordbdetail.get("root") != null && wordbdetail.get("root").length() == 4) {
                        setMazeedSarfSagheer(false);//form 10 and 11 return 0 to be done
                        setThulathiSarfSagheer(false);
                        setIsverbconjugaton(false);
                        setIsroot(true);
                        setQuadrilateral(true);
                        vb.setRoot(vbdetail.get("root"));



                    } else if (wordbdetail.get("root") != null && wordbdetail.get("noun") != null) {
                        vb.setRoot(String.valueOf(wordbdetail.get("root")));
                        setIsroot(true);
                        setNoun(true);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }

                if (wbwtranslation == null) {
                    ArrayList<wbwentity> allwords = utils.getwbwTranslatonbywordNew(chapterid, ayanumber, wordno);
                    wordbdetail.put("translation", SpannableStringBuilder.valueOf(allwords.get(0).getEn()));
                } else {
                    wordbdetail.put("translation", SpannableStringBuilder.valueOf(wbwtranslation));
                }
                if (!corpusSurahWord.isEmpty()) {
                    String quranverses = corpusSurahWord.get(0).getQurantext();
                    spannable = new SpannableStringBuilder(quranverses);

                    SetShart(utils, corpusSurahWord);
                    setHarfNasb(utils, corpusSurahWord);
                    SetMausoofSifa(utils, corpusSurahWord);
                    SetMudhaf(utils, corpusSurahWord);
                    SetKana(utils);


                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ex.shutdown();
                        dialog.dismiss();


                        if (showGrammarFragments) {
                            GrammarFragmentsListAdapter grammarFragmentsListAdapter;

                            grammarFragmentsListAdapter = new GrammarFragmentsListAdapter(getContext(), expandableListTitle, expandableListDetail);
                            selectedview = view;

                        } else {


                            rwAdapter.setRootWordsAndMeanings(isVerb(), wazannumberslist, spannable, sarf,
                                    spannableShart, spannableHarf, isNoun(), ismfaelmafool, isParticiples(),
                                    isIsverbconjugaton(), corpusSurahWord, wordbdetail, vbdetail,
                                    isMazeedSarfSagheer(), isThulathiSarfSagheer(), sarfSagheerList, getActivity());

                            recyclerView.setAdapter(rwAdapter);
                            selectedview = view;

                        }


                    }
                });

            }
        });


        return view;

    }

    private void storepreferences(int chapterid, int ayanumber) {

        SharedPreferences pref = getContext().getSharedPreferences("lastread", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("surah", chapterid);
        editor.putInt("ayah", ayanumber);
        editor.apply();
        editor.commit();

    }




    protected void setHarfNasb(Utils utils, ArrayList<NewCorpusExpandWbwPOJO> corpusSurahWord) {

        ArrayList<NewNasbEntity> harfnasb = utils.getHarfNasbIndSurahAyahSnew(chapterid, ayanumber);

        for (NewNasbEntity nasb : harfnasb) {

            if (themepreference.equals("dark") || themepreference.equals("blue")) {
                harfinnaspanDark = new ForegroundColorSpan(GREEN);
                harfismspanDark = new ForegroundColorSpan(BCYAN);
                harfkhabarspanDark = new ForegroundColorSpan(YELLOW);

            } else {
                harfinnaspanDark = new ForegroundColorSpan(WHOTPINK);
                harfismspanDark = new ForegroundColorSpan(prussianblue);
                harfkhabarspanDark = new ForegroundColorSpan(DeepPink);
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

    protected void SetMudhaf(Utils utils, ArrayList<NewCorpusExpandWbwPOJO> corpusSurahWord) {
        ArrayList<NewMudhafEntity> mudhafSurahAyah = utils.getMudhafSurahAyahNew(chapterid, ayanumber);

        String jumlashart = "جملة شرطية";


        for (NewMudhafEntity mudhafEntity : mudhafSurahAyah) {
            mudhafspansDark = getSpancolor(themepreference, true);
            spannable.setSpan(mudhafspansDark, mudhafEntity.getStartindex(), mudhafEntity.getEndindex(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        }
    }

    protected void SetMausoofSifa(Utils utils, ArrayList<NewCorpusExpandWbwPOJO> corpusSurahWord) {
        ArrayList<SifaEntity> sifabySurahAyah = utils.getSifabySurahAyah(chapterid, ayanumber);

        String quranverses = corpusSurahWord.get(0).getQurantext();
        for (SifaEntity shartEntity : sifabySurahAyah) {

            sifaspansDark = getSpancolor(themepreference, false);
            try {
                spannable.setSpan(sifaspansDark, shartEntity.getStartindex(), shartEntity.getEndindex(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(shartEntity.getSurah() + "  " + shartEntity.getAyah() + "  " + quranverses);
            }


        }
    }

    protected void SetShart(Utils utils, ArrayList<NewCorpusExpandWbwPOJO> corpusSurahWord) {
        ArrayList<NewShartEntity> shart = utils.getShartSurahAyahNew(chapterid, ayanumber);

        //  this.spannable = new SpannableStringBuilder(quranverses);

        for (NewShartEntity shartEntity : shart) {
            harfshartspanDark = new ForegroundColorSpan(GOLD);
            shartspanDark = new ForegroundColorSpan(GREEN);
            jawabshartspanDark = new ForegroundColorSpan(CYAN);
            if (themepreference.equals("dark") || themepreference.equals("blue")) {
                harfshartspanDark = new ForegroundColorSpan(GOLD);
                shartspanDark = new ForegroundColorSpan(ORANGE400);
                jawabshartspanDark = new ForegroundColorSpan(CYAN);
            } else {
                harfshartspanDark = new ForegroundColorSpan(FORESTGREEN);
                shartspanDark = new ForegroundColorSpan(GREENDARK);
                jawabshartspanDark = new ForegroundColorSpan(WHOTPINK);
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



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rwAdapter.SetOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                CharSequence text = null;
                CharSequence text2 = null;
                View viewVerbConjugation = v.findViewById(R.id.verbconjugationbtn);
                View verboccuranceid = v.findViewById(R.id.verboccurance);
                View nouns = v.findViewById(R.id.wordoccurance);
                View verse = v.findViewById(R.id.spannableverse);
                View wordview = v.findViewById(R.id.wordView);
                View formview = v.findViewById(R.id.mazeedmeaning);

                if (formview != null) {
                    final SpannableStringBuilder formnumber = wordbdetail.get("formnumber");
                    final String vbform = vbdetail.get("formnumber");
                    String mform = "";
                    if (formnumber == null) {
                        String verbform = vbdetail.get("formnumber");
                        mform = String.valueOf(verbform).replaceAll("[()]", "");
                    } else {
                        mform = String.valueOf(wordbdetail.get("formnumber"));
                        mform = mform.replaceAll("[()]", "");
                    }

                    int color = getResources().getColor(R.color.background_color_light_brown);
                    if (themepreference.equals("dark") || themepreference.equals("blue")) {
                        color = getResources().getColor(R.color.background_color);
                    } else if (themepreference.equals("brown")) {
                        color = getResources().getColor(R.color.neutral0);
                    } else if (themepreference.equals("white")) {
                        color = getResources().getColor(R.color.background_color_light_brown);

                    }
                    //        holder.quran_transliteration.setText(Html.fromHtml(entity.getTranslation(), Html.FROM_HTML_MODE_LEGACY));
                    String formDetails = getFormDetails(mform);
                    if(!(formDetails ==null)) {
                        Tooltip.Builder builder = new Tooltip.Builder(v, R.style.ayah_translation)
                                .setCancelable(true)
                                .setDismissOnClick(false)
                                .setCornerRadius(20f)
                                .setGravity(Gravity.TOP)
                                .setArrowEnabled(true)

                                .setBackgroundColor(color)


                                .setText(Html.fromHtml(getFormDetails(mform)));
                        builder.show();
                    }
                } else if (wordview != null) {
                    Bundle dataBundle = new Bundle();
                    if (quadrilateral) {
                        dataBundle.putString(QURAN_VERB_ROOT, vb.getRoot());
                        dataBundle.putString(QURAN_VERB_WAZAN, " ");
                        dataBundle.putString("arabicword", "");
                    } else if (isarabicword) {
                        dataBundle.putString("arabicword", arabicword.getArabicword());
                        dataBundle.putString(QURAN_VERB_WAZAN, " ");
                        dataBundle.putString(QURAN_VERB_ROOT, " ");
                    } else if (isroot) {
                        dataBundle.putString("arabicword", "");
                        dataBundle.putString(VERBMOOD, vbdetail.get("verbmood"));
                        if (!vbdetail.isEmpty()) {

                            dataBundle.putString(QURAN_VERB_WAZAN, vb.getWazan());
                            dataBundle.putString(QURAN_VERB_ROOT, vb.getRoot());
                        } else if (isParticiples()) {

                            dataBundle.putString(QURAN_VERB_WAZAN, vb.getWazan());
                            dataBundle.putString(QURAN_VERB_ROOT, vb.getRoot());

                        } else {
                            dataBundle.putString(QURAN_VERB_ROOT, vb.getRoot());
                            dataBundle.putString(QURAN_VERB_WAZAN, " ");
                        }


                    }
                    if (isroot || isarabicword) {
                        try {
                            if (ismujarrad) {
                                dataBundle.putString(VERBTYPE, "mujarrad");
                            } else if (ismazeed) {
                                dataBundle.putString(VERBTYPE, "mazeed");
                            } else {
                                dataBundle.putString(VERBTYPE, "");
                            }
                            Intent intent = new Intent(getActivity(), LughatWordDetailsAct.class);

                            intent.putExtras(dataBundle);
                            startActivity(intent);
                        } catch (NullPointerException e) {
                            System.out.println("null pointer");
                        }
                    } else {

                        Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
                    }

                } else if (verse != null) {
                    GrammerFragmentsBottomSheet item = new GrammerFragmentsBottomSheet();
                    //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Bundle dataBundle = new Bundle();

                    dataBundle.putInt(SURAH_ID, chapterid);
                    dataBundle.putInt(AYAHNUMBER, ayanumber);


                    item.setArguments(dataBundle);
                    String[] data = {String.valueOf(chapterid), String.valueOf(ayanumber)};
                    FragmentTransaction transactions = fragmentManager.beginTransaction().setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out);
                    //   transactions.show(item);
                    GrammerFragmentsBottomSheet.newInstance(data).show((getActivity().getSupportFragmentManager()), WordAnalysisBottomSheet.TAG);


                } else if (nouns != null) {
                    Bundle bundle = new Bundle();
                    //   Intent intent = new Intent(getActivity(), NounOccuranceAsynKAct.class);
                    Intent intent = new Intent(getActivity(), WordOccuranceAsynKAct.class);

                    try {
                        if (vb.getRoot() != null) {
                            bundle.putString(QURAN_VERB_ROOT, vb.getRoot());
                        } else if (getHarfNasbAndZarf() != null) {
                            bundle.putString(QURAN_VERB_ROOT, getHarfNasbAndZarf());
                        }
                    } catch (NullPointerException e1) {
                        bundle.putString(QURAN_VERB_ROOT, getHarfNasbAndZarf());
                        e1.printStackTrace();


                    }
                    intent.putExtras(bundle);
                    //   intent.putExtra(QURAN_VERB_ROOT,vb.getRoot());
                    startActivity(intent);


                } else if (viewVerbConjugation != null) {
                    text = ((MaterialButton) viewVerbConjugation).getText();
                    if (text.toString().equals("Click for Verb Conjugation")) {
                        if (isroot && isconjugation  ) {

                            Bundle dataBundle = new Bundle();
                            //      ArrayList arrayList = ThulathiMazeedConjugatonList.get(position);
                            //   arrayList.get(0).ge
                            if (ismujarrad) {
                                dataBundle.putString(VERBTYPE, "mujarrad");
                            } else if (ismazeed) {
                                dataBundle.putString(VERBTYPE, "mazeed");
                            }
                            dataBundle.putString(VERBMOOD, vbdetail.get("verbmood"));
                            dataBundle.putString(QURAN_VERB_WAZAN, vb.getWazan());
                            dataBundle.putString(QURAN_VERB_ROOT, vb.getRoot());
                            Intent intent = new Intent(getActivity(), ConjugatorTabsActivity.class);

                            intent.putExtras(dataBundle);
                            startActivity(intent);

                        }
                    }

                } else if (verboccuranceid != null) {
                    text2 = ((MaterialButton) verboccuranceid).getText();
                    if (text2.toString().equals("Click for Verb Occurance")) ;
                    {
                        if (!(vb == null)) {
                            Bundle bundle = new Bundle();
                            Intent intent = new Intent(getActivity(), WordOccuranceAsynKAct.class);
                            bundle.putString(QURAN_VERB_ROOT, vb.getRoot());
                            intent.putExtras(bundle);
                            //   intent.putExtra(QURAN_VERB_ROOT,vb.getRoot());
                            startActivity(intent);
                        }

                    }
                }

            }

            private String getFormDetails(String form) {

                return mazeedsignificance.get(form);
            }

        });


    }


}