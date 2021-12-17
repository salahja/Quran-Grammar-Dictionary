package com.example.mushafconsolidated.Activity;

import static com.example.Constant.QURAN_VERB_ROOT;
import static com.example.Constant.QURAN_VERB_WAZAN;
import static com.example.Constant.QURAN_VOCUBALORY_ROOT;
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
import org.sj.conjugator.utilities.Constants;
import org.sj.conjugator.fragments.FragmentEnglish_lughat;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class LughatWordDetailsAct extends BaseActivity {
  private static final int NUM_PAGES_THULATHI = 7;
  private static final int NUM_PAGES_MAZEED = 6;
  private static final int NUM_PAGES_VOCABULARY = 4;

  private boolean isUnaugmentedWazan, isAugmentedWazan, isnoconjugation, isonlyarabicword;
  Bundle dataBundle;

  private String language;

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  // Arrey of strings FOR TABS TITLES
  private final String[] vocabularytitles = new String[]{"Hans Weir", "Lane Lexicon", "English lughat", "Urdu Lughat"};

  private final String[] thulathientitles = new String[]{"Hans Weir", "Lane Lexicon", "English lughat", "Urdu Lughat", "Verb Conjugaton", "Active/Passive Participle", "Noun Of Instrument", "Noun of Place n Time"};
  private final String[] thulathiartitles = new String[]{"لين معجم", "صرف صغير", "تصريف الأفعال ", "لاسم الفاعل/الاسم المفعول", "الاسم الآلة", "الاسم الظرف"};
  private final String[] mazeedentitles = new String[]{"Hans Weir", "Lane Lexicon", "English lughat", "Urdu Lughat", "Verb Conjugaton", "Active/Passive Participle"};
  private final String[] mazeedartitles = new String[]{"لين معجم", "صرف صغير", "تصريف الأفعال ", "لاسم الفاعل/الاسم المفعول"};

  private boolean ismujarrad;
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

    String arabicword=null;
    if(bundle!=null) {
      verbform = bundle.getString(QURAN_VERB_WAZAN);
      verbmood = bundle.getString(VERBMOOD);
      verbtype=bundle.getString(VERBTYPE);
      arabicword = bundle.getString("arabicword");
    }
    if(verbtype.equals("mujarrad")){
      ismujarrad = true;
    }else{
      ismujarrad =false;

    }



    if (arabicword.length() == 0) {
      try {

        dataBundle.putString( QURAN_VERB_ROOT, verbroot);
        dataBundle.putString( QURAN_VERB_WAZAN, verbform);
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


      dataBundle.putString("arabicword", arabicword);
      dataBundle.putString(QURAN_VERB_WAZAN, "");
      dataBundle.putSerializable(QURAN_VERB_ROOT, "");

      isnoconjugation = true;
      setLanguage("english");
    }

    // Up to here, we have working scrollable pages
    if (ismujarrad) {
      if (SharedPref.getLanguage().equals("en")) {

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(thulathientitles[position])).attach();


      } else {


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(thulathiartitles[position])).attach();


      }
    } else if (isAugmentedWazan) {

      if (SharedPref.getLanguage().equals("en")) {

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(mazeedentitles[position])).attach();


      } else {


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(mazeedartitles[position])).attach();


      }


    } else if (isnoconjugation) {
      new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(vocabularytitles[position])).attach();


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
        FragmentEnglish_lughat fragv = new FragmentEnglish_lughat(LughatWordDetailsAct.this, "hans");
        fragv.setArguments(dataBundle);
        return fragv.newInstance();
      } else
      if (position == 1) {
        FragmentEnglish_lughat fragv = new FragmentEnglish_lughat(LughatWordDetailsAct.this, "lanes");
        fragv.setArguments(dataBundle);
        return fragv.newInstance();
      } else if (position == 2) {
        FragmentEnglish_lughat fragv = new FragmentEnglish_lughat(LughatWordDetailsAct.this, "english");
        fragv.setArguments(dataBundle);
        return fragv.newInstance();
      } else if (position == 3) {
        FragmentEnglish_lughat fragv = new FragmentEnglish_lughat(LughatWordDetailsAct.this, "urdu");
        fragv.setArguments(dataBundle);
        return fragv.newInstance();


      } else if (position == 4) {
        org.sj.conjugator.fragments. FragmentVerb fragv = new org.sj.conjugator.fragments.FragmentVerb();
        fragv.setArguments(dataBundle);
        return fragv.newInstance();


      } else if (position == 5) {
        org.sj.conjugator.fragments. FragmentIsmfaelIsmMafools fragvs = new org.sj.conjugator.fragments.FragmentIsmfaelIsmMafools();
        fragvs.setArguments(dataBundle);
        return fragvs.newInstance();
      } else if (position == 6) {
        org.sj.conjugator.fragments. FragmentIsmIsmAla fragvsi = new org.sj.conjugator.fragments.FragmentIsmIsmAla();
        fragvsi.setArguments(dataBundle);
        return fragvsi.newInstance();
      } else if (position == 7) {
        FragmentIsmZarf fragvsi = new FragmentIsmZarf();
        fragvsi.setArguments(dataBundle);
        return fragvsi.newInstance();
      }

      org.sj.conjugator.fragments. FragmentVerb fragv = new org.sj.conjugator.fragments.FragmentVerb();
      fragv.setArguments(dataBundle);
      return fragv.newInstance();
    }


    @Override
    public int getItemCount() {
      if (isUnaugmentedWazan) {
        return NUM_PAGES_THULATHI;
      } else if (isAugmentedWazan) {
        return NUM_PAGES_MAZEED;
      } else if (isnoconjugation) {
        return NUM_PAGES_VOCABULARY;
      } else {
        return 0;
      }
    }
  }
}
