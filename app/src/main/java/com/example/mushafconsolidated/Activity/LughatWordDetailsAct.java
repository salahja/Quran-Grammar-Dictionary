package com.example.mushafconsolidated.Activity;

import static com.example.Constant.ACCUSATIVE;
import static com.example.Constant.CONDITIONAL;
import static com.example.Constant.DEMONSTRATIVE;
import static com.example.Constant.PREPOSITION;
import static com.example.Constant.QURAN_VERB_ROOT;
import static com.example.Constant.QURAN_VERB_WAZAN;
import static com.example.Constant.QURAN_VOCUBALORY_ROOT;
import static com.example.Constant.RELATIVE;
import static com.example.Constant.VERBMOOD;
import static com.example.Constant.VERBTYPE;

import static Utility.ArabicLiterals.LALIF;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mushafconsolidated.R;
import com.example.utility.SharedPref;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.sj.conjugator.fragments.FragmentIsmZarf;
import com.example.mushafconsolidated.fragments.Dictionary_frag;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class LughatWordDetailsAct extends BaseActivity {
  private static final int NUM_PAGES_THULATHI=8;
  private static final int NUM_PAGES_THULATHI_MAJZOOMMANSUB = 9;
  private static final int NUM_PAGES_MAZEED = 6;
  private static final int NUM_PAGES_MAZEED_MAJZOOMMANSUB = 7;
  private static final int NUM_PAGES_VOCABULARY = 4;
  private static final int NUM_PAGES_ARABICWORD_HARFNASB = 3;

  private boolean isUnaugmentedWazan, isAugmentedWazan, isnoconjugation, isonlyarabicword, isMajzoom, isMansub;
  Bundle dataBundle;

  private String language;

  private boolean isrelative, isdem, isprep, isharfnasab, isShart, isHarf;

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  // Arrey of strings FOR TABS TITLES
  private final String[] arabicwordharfnasb = new String[]{"English lughat", "Urdu Lughat", "Harf "};

  private final String[] vocabularytitles = new String[]{"Hans Weir", "Lane Lexicon", "English lughat", "Urdu Lughat"};

  private final String[] thulathientitles = new String[]{"Hans Weir", "Lane Lexicon", "English lughat", "Urdu Lughat", "Verb Conjugaton", "Active/Passive PCPL", "N. Instrument", "N.Place/Time"};
  private final String[] thulathientitlesmansub = new String[]{"Hans Weir", "Lane Lexicon", "English lughat", "Urdu Lughat", "Subjunctive", "Verb Conjugaton", "Active/Passive PCPL", "N. Instrument", "N.Place/Time"};
  private final String[] thulathientitlesmajzoom = new String[]{"Hans Weir", "Lane Lexicon", "English lughat", "Urdu Lughat", "Jussive", "Verb Conjugaton", "Active/Passive PCPL", "N. Instrument", "N.Place/Time"};

  private final String[] thulathiartitles = new String[]{"قاموس هانز", "لين معجم", "English lughat", "Urdu Lughat", "صرف صغير", "تصريف الأفعال ", "لاسم الفاعل/الاسم المفعول", "الاسم الآلة", "الاسم الظرف"};
  private final String[] mazeedentitles = new String[]
          {"Hans Weir", "Lane Lexicon", "English lughat", "Urdu Lughat", "Verb Conjugaton", "Active/Passive Participle"};

  private final String[] mazeedentitlesmajzoom = new String[]
          {"Hans Weir", "Lane Lexicon", "English lughat", "Urdu Lughat", "Jussive", "Verb Conjugaton", "Active/Passive Participle"};
  private final String[] mazeedentitlesmansub = new String[]
          {"Hans Weir", "Lane Lexicon", "English lughat", "Urdu Lughat", "Subjunctive", "Verb Conjugaton", "Active/Passive Participle"};
  private final String[] getMazeedentitles= new String[]
          {"Hans Weir", "Lane Lexicon", "English lughat", "Urdu Lughat", "Verb Conjugaton", "Active/Passive Participle"};

  private final String[] mazeedartitles = new String[]{"قاموس هانز", "لين معجم", "English lughat", "Urdu Lughat", "صرف صغير", "تصريف الأفعال ", "لاسم الفاعل/الاسم المفعول"};
  private String[] languages = new String[10];
  private boolean ismujarrad, ismazeed;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_newtabs);
    //   setContentView(R.layout.activity_tabs);
    FloatingTextButton callButton = findViewById(R.id.action_button);

    callButton.setOnClickListener(view -> {

      // viewPager.adapters=null;
      finish();
      //      Snackbar.make(viewById, "Call button clicked", Snackbar.LENGTH_SHORT).show();
    });
    FragmentManager fm = getSupportFragmentManager();

    ViewStateAdapter sa = new ViewStateAdapter(fm, getLifecycle());
    final ViewPager2 viewPager = findViewById(R.id.pager);
    viewPager.setAdapter(sa);
    final TabLayout tabLayout = findViewById(R.id.tabLayout);
    dataBundle = new Bundle();
    Bundle bundle = getIntent().getExtras();
    bundle.getParcelableArray("dictionary");

    String conjugationroot = bundle.getString(QURAN_VERB_ROOT);
    String vocubaluryroot = bundle.getString(QURAN_VERB_ROOT);
    String verbformthulathi = bundle.getString(QURAN_VERB_WAZAN);
    String verbtype = bundle.getString(VERBTYPE);

    final String ss = conjugationroot.replaceAll("[\\[\\]]", "");
    String verbroot = ss.replaceAll("[,;\\s]", "");
    int starts = conjugationroot.indexOf(LALIF);
    String hamza = "ء";
    if (starts != -1) {
      conjugationroot = conjugationroot.replace(LALIF, hamza.trim());
    }

    int firstalifwithhamza = vocubaluryroot.indexOf(LALIF);
    String verbform = null;
    String verbmood = null;

    String arabicword = null;
    if (bundle != null) {
      verbform = bundle.getString(QURAN_VERB_WAZAN);
      verbmood = bundle.getString(VERBMOOD);
      verbtype = bundle.getString(VERBTYPE);
      arabicword = bundle.getString("arabicword");
      isharfnasab = bundle.getBoolean(ACCUSATIVE, false);
      isdem = bundle.getBoolean(DEMONSTRATIVE, false);
      isrelative = bundle.getBoolean(RELATIVE, false);
      isShart = bundle.getBoolean(CONDITIONAL, false);
      isprep = bundle.getBoolean(PREPOSITION, false);
      isHarf = isShart == isrelative == isharfnasab == isprep == isdem;
      try {
        if (verbmood.equals("Jussive")) {
          isMajzoom = true;
        } else if (verbmood.equals("Subjunctive")) {
          isMansub = true;
        }
      }catch (NullPointerException e){
        System.out.println(e.getMessage());

      }
    }
    if (verbtype.equals("mujarrad")) {
      ismujarrad = true;
    } else if (verbtype.equals("mazeed")) {
      ismazeed = true;

    } else {
      ismujarrad = false;
      ismazeed = false;
    }


    if (arabicword.length() == 0) {
      try {

        dataBundle.putString(QURAN_VERB_ROOT, verbroot);
        dataBundle.putString(QURAN_VERB_WAZAN, verbform);
        dataBundle.putString(VERBMOOD, verbmood);
        dataBundle.putString(VERBTYPE, verbtype);

        if (verbtype.equals("mujarrad")) {
          isUnaugmentedWazan = true;
          setLanguage("lanes");
        } else if (verbtype.equals("mazeed")) {


          isAugmentedWazan = true;
          setLanguage("lanes");
        } else {
          isnoconjugation = true;
          setLanguage("english");
        }
      } catch (Exception e) {
        dataBundle.putString(QURAN_VOCUBALORY_ROOT, vocubaluryroot);
        dataBundle.putString(QURAN_VERB_WAZAN, verbformthulathi);
        dataBundle.putSerializable(QURAN_VERB_ROOT, conjugationroot);
        isUnaugmentedWazan = true;
        setLanguage("lanes");

      }
    } else {

      isonlyarabicword = true;
      dataBundle.putString("arabicword", arabicword);
      dataBundle.putString(QURAN_VERB_WAZAN, "");
      dataBundle.putSerializable(QURAN_VERB_ROOT, "");


      isnoconjugation = false;
      setLanguage("english");
    }

    // Up to here, we have working scrollable pages
    if (ismujarrad) {
      if (SharedPref.getLanguage().equals("en") && verbmood.equals("Jussive")) {
        languages[0] = "lanes";
        languages[1] = "hans";

        languages[2] = "english";
        languages[3] = "urdu";
        languages[4] = "Jussive";
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(thulathientitlesmajzoom[position])).attach();


      } else if (SharedPref.getLanguage().equals("en") && verbmood.equals("Subjunctive")) {
        languages[0] = "lanes";
        languages[1] = "hans";

        languages[2] = "english";
        languages[3] = "urdu";
        languages[4] = "Subjunctive";
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(thulathientitlesmansub[position])).attach();
      } else if (SharedPref.getLanguage().equals("en") && verbmood.equals("Indicative")) {
        languages[0] = "lanes";
        languages[1] = "hans";

        languages[2] = "english";
        languages[3] = "urdu";

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(thulathientitles[position])).attach();

      } else if (ismazeed) {


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(thulathiartitles[position])).attach();


      }


    } else if (isAugmentedWazan) {

      if (SharedPref.getLanguage().equals("en") && verbmood.equals("Jussive")) {
        languages[0] = "lanes";
        languages[1] = "hans";

        languages[2] = "english";
        languages[3] = "urdu";
        languages[4] = "Jussive";

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(mazeedentitlesmajzoom[position])).attach();

      } else if (SharedPref.getLanguage().equals("en") && verbmood.equals("Subjunctive")) {
        languages[0] = "lanes";
        languages[1] = "hans";

        languages[2] = "english";
        languages[3] = "urdu";
        languages[4] = "Subjunctive";
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(mazeedentitlesmansub[position])).attach();
      } else {


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(getMazeedentitles[position])).attach();
        languages[0] = "lanes";
        languages[1] = "hans";

        languages[2] = "english";
        languages[3] = "urdu";

      }



    }


    else if (isonlyarabicword && isrelative) {
      new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(arabicwordharfnasb[position])).attach();

      languages[0] = "english";
      languages[1] = "urdu";
      languages[2] = "relative";
    }

    else if (isonlyarabicword && isdem) {
      new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(arabicwordharfnasb[position])).attach();

      languages[0] = "english";
      languages[1] = "urdu";
      languages[2] = "demonstrative";
    }



    else if (isonlyarabicword && isharfnasab) {
      new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(arabicwordharfnasb[position])).attach();

      languages[0] = "english";
      languages[1] = "urdu";
      languages[2] = "accusative";
    } else if (isonlyarabicword && isShart) {
      new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(arabicwordharfnasb[position])).attach();

      languages[0] = "english";
      languages[1] = "urdu";
      languages[2] = "conditonal";
    } else if (isonlyarabicword && isprep) {
      new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(arabicwordharfnasb[position])).attach();

      languages[0] = "english";
      languages[1] = "urdu";
      languages[2] = "preposition";
    } else if (isnoconjugation) {
      new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(vocabularytitles[position])).attach();
      languages[0] = "lanes";
      languages[1] = "hans";

      languages[2] = "english";
      languages[3] = "urdu";

    }


    // Now we have tabs, NOTE: I am hardcoding the order, you'll want to do something smarter

    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });
    viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageSelected(int position) {
        tabLayout.selectTab(tabLayout.getTabAt(position));
      }
    });

    // And now we have tabs that, when clicked, navigate to the correct page
  }

  private class ViewStateAdapter extends FragmentStateAdapter {

    public ViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
      super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
      // Hardcoded in this order, you'll want to use lists and make sure the titles match
      String ss = "";

      if (position == 0) {
        //hanes
        Dictionary_frag fragv = new Dictionary_frag(LughatWordDetailsAct.this, languages[0]);
        fragv.setArguments(dataBundle);
        return fragv.newInstance();
      } else if (position == 1) {
        //kabes
        Dictionary_frag fragv = new Dictionary_frag(LughatWordDetailsAct.this, languages[1]);
        fragv.setArguments(dataBundle);
        return fragv.newInstance();
      } else if (position == 2) {
        //english
        Dictionary_frag fragv = new Dictionary_frag(LughatWordDetailsAct.this, languages[2]);
        fragv.setArguments(dataBundle);
        return fragv.newInstance();
      } else if (position == 3) {
        //urudu
        Dictionary_frag fragv = new Dictionary_frag(LughatWordDetailsAct.this, languages[3]);
        fragv.setArguments(dataBundle);
        return fragv.newInstance();


      } else if (position == 4) {
        org.sj.conjugator.fragments.FragmentVerb fragv = new org.sj.conjugator.fragments.FragmentVerb();
        fragv.setArguments(dataBundle);
        return fragv.newInstance();


      } else if (position == 5) {
        org.sj.conjugator.fragments.FragmentIsmfaelIsmMafools fragvs = new org.sj.conjugator.fragments.FragmentIsmfaelIsmMafools();
        fragvs.setArguments(dataBundle);
        return fragvs.newInstance();
      } else if (position == 6) {
        org.sj.conjugator.fragments.FragmentIsmIsmAla fragvsi = new org.sj.conjugator.fragments.FragmentIsmIsmAla();
        fragvsi.setArguments(dataBundle);
        return fragvsi.newInstance();
      } else if (position == 7) {
        FragmentIsmZarf fragvsi = new FragmentIsmZarf();
        fragvsi.setArguments(dataBundle);
        return fragvsi.newInstance();
      }

      org.sj.conjugator.fragments.FragmentVerb fragv = new org.sj.conjugator.fragments.FragmentVerb();
      fragv.setArguments(dataBundle);
      return fragv.newInstance();
    }


    @Override
    public int getItemCount() {
      if (isHarf) {
        return NUM_PAGES_ARABICWORD_HARFNASB;
      } else if (isUnaugmentedWazan && (isMajzoom || isMansub)) {
        return NUM_PAGES_THULATHI_MAJZOOMMANSUB;
      } else if (isUnaugmentedWazan  ) {
        return NUM_PAGES_THULATHI;
      } else if (isAugmentedWazan && (isMansub || isMajzoom)) {
        return NUM_PAGES_MAZEED_MAJZOOMMANSUB;

      } else if (isAugmentedWazan  ) {
        return NUM_PAGES_MAZEED;

      } else if (isnoconjugation) {
        return NUM_PAGES_VOCABULARY;
      } else {
        return 0;
      }
    }
  }
}
