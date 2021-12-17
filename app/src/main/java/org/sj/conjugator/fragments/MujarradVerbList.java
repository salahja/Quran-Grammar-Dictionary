package org.sj.conjugator.fragments;

import static com.example.Constant.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utility.DarkThemeApplication;
import com.google.android.material.chip.Chip;

import org.jetbrains.annotations.NotNull;
import org.sj.conjugator.MainActivity;
import org.sj.conjugator.activity.ConjugatorTabsActivity;
import org.sj.conjugator.adapter.MujarradSarfSagheerListingAdapter;
import org.sj.conjugator.adapter.SarfMujarradSarfSagheerListingAdapter;
import org.sj.conjugator.utilities.GatherAll;
import org.sj.conjugator.utilities.VerbConjugaorApp;
//import  org.sj.conjugator.fragments.*;
import com.example.mushafconsolidated.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import database.DatabaseUtils;
import database.entity.MujarradVerbs;
import database.entity.kov;

public class MujarradVerbList extends Fragment {
  private static final int PERMISSION_REQUEST_CODE = 100;
  private static final String TAG = "PermissionDemo";


  PopupWindow popupWindowLists;
  Chip buttonShowDropDown;
  String verbmood,verbtype;

  public MujarradVerbList() {

  }

  public String getVerbtype() {
    return verbtype;
  }

  public void setVerbtype(String verbtype) {
    this.verbtype = verbtype;
  }

  ArrayList<SarfSagheer> ssagheer=new ArrayList<>();

  public String getVerbmood() {
    return verbmood;
  }

  public void setVerbmood(String verbmood) {
    this.verbmood = verbmood;
  }



Context context;
  private Spinner mujarradspinner;

  public MujarradVerbList(MainActivity context) {
    this.context=context;
  }








  RecyclerView recyclerView;
  // --Commented out by Inspection (13/6/21 6:51 PM):Button llPdf;

  // --Commented out by Inspection (13/6/21 6:52 PM):private NavigationView navigationView;
  // --Commented out by Inspection (13/6/21 6:51 PM):private BottomNavigationView bottomNavigationView;
  ///  private MazeedFihiAdapter mazeedFiHiAdapter;
  private MujarradSarfSagheerListingAdapter sarfsagheer;
  private SarfMujarradSarfSagheerListingAdapter sarfsagheerAdapter;
  //   private ArrayList sarfSagheerThulathiArray = new ArrayList();
  ArrayList<ArrayList> sarfSagheerThulathiArray=new ArrayList<>();
  private ArrayList sarfSagheerMazeedArray = new ArrayList();
  // --Commented out by Inspection (13/6/21 6:51 PM):private Bitmap bitmap;



  @Override
  public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    setHasOptionsMenu(true);
    View view = inflater.inflate(R.layout.thulathilistingnotoolbar, container, false);
    SharedPreferences sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());
    //  String theme = sharedPreferences.getString("theme", 1);
    String indictive = sharedPreferences.getString(VERBMOOD, "indicative");
    setVerbmood(indictive);
    recyclerView = view.findViewById(R.id.sarfrecview);






    setuptoolbarthulath();








    recyclerView = view.findViewById(R.id.sarfrecview);


    MujarradListing(ssagheer, String.valueOf(24));



   // setupOnItemClickThulathiAdapter();



   setuptoolbarthulath();

    return view;
  }




  @Override
  public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);


    //  recyclerView.setAdapter(sarfsagheerAdapter);
    recyclerView.setHasFixedSize(true);
    // use a linear layout manager
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);
/*
        sarfsagheerAdapter = new MujarradSarfSagheerListingAdapter(sarfSagheerThulathiArray, getActivity());
        //   recyclerView.setAdapter(sarfsagheerAdapter);
   //     setupOnItemClickThulathiAdapter();

*/




  }



  private void clearSarfsagheer() {
    if (sarfSagheerThulathiArray.size() > 0) {
      sarfSagheerThulathiArray.clear();
    }

  }

  private void setuptoolbarthulath() {

 DatabaseUtils utils=new DatabaseUtils(DarkThemeApplication.getContext());
    ArrayList<kov> entity = utils.getKov();
    ArrayList<String> list=new ArrayList<>();
    for (kov kov : entity) {
      list.add(kov.getRulename().concat(",").concat(" ").concat(kov.getExample()));

    }


    // initialize pop up window
    // ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_layout_larg, verblists);

    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
    mujarradspinner.setAdapter(spinnerAdapter);
    mujarradspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
      //   mujarradspinner.setBackgroundColor(getResources().getColor(R.color.background_color_light_brown));

       // ArrayList<SarfSagheer> ssagheer=new ArrayList<>();

          clearSarfsagheer();
        MujarradListing(ssagheer, String.valueOf(position));





      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }

    });
  }



  private void MujarradListing(ArrayList<SarfSagheer> ssagheer, String kov) {
    setVerbtype("mujarrad");
    AlertDialog dialog;
    AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);
  builder.setCancelable(false); // if you want user to wait for some process to finish,
     builder.setView(R.layout.layout_loading_dialog);
    dialog = builder.create();






    ExecutorService ex = Executors.newSingleThreadExecutor();
    ex.execute(new Runnable() {
      @Override
      public void run() {
        getActivity().runOnUiThread(new Runnable() {
          @Override
          public void run() {
            dialog.show();
          }
        });



        DatabaseUtils utils = new DatabaseUtils(DarkThemeApplication.getContext());
        ArrayList<MujarradVerbs> mujarradBYWeakness = utils.getMujarradBYWeakness(kov);
        listingMujarradWeakness(ssagheer, mujarradBYWeakness);
        setVerbtype("mujarrad");

        getActivity(). runOnUiThread(new Runnable() {
          @Override
          public void run() {
            ex.shutdown();

            sarfsagheerAdapter = new SarfMujarradSarfSagheerListingAdapter(ssagheer, getActivity());

            recyclerView.setAdapter(sarfsagheerAdapter);
            setupOnItemClickThulathiAdapter();
            dialog.dismiss();


          }
        });


      }
    });




 //   setupOnItemClickThulathiAdapter();
  }




  private void listingMujarradWeakness(ArrayList<SarfSagheer> ssagheer, ArrayList<MujarradVerbs> mujarradBYWeakness) {
    for (MujarradVerbs s : mujarradBYWeakness) {
      ArrayList<ArrayList> listing = GatherAll.getInstance().getMujarradListing(getVerbmood(), s.getRoot());
      SarfSagheer ss=new SarfSagheer();
      ss.setWeakness(listing.get(0).get(0).toString());
      ss.setWazan(listing.get(0).get(1).toString());
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

      ss.setZarfone(listing.get(0).get(13).toString());
      ss.setZarftwo(listing.get(0).get(15).toString());
      ss.setZarfthree(listing.get(0).get(16).toString());
      ss.setVerbtype(listing.get(0).get(17).toString());
      ssagheer.add(ss);
    }
  }










  private boolean checkPermission() {
    int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
    return result == PackageManager.PERMISSION_GRANTED;
  }

  private void requestPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
      Toast.makeText(DarkThemeApplication.getContext(), "Write External Storage permission allows us to create files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
    } else {
      ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
    if (requestCode == PERMISSION_REQUEST_CODE) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        Log.e("value", "Permission Granted, Now you can use local drive .");
      } else {
        Log.e("value", "Permission Denied, You cannot use local drive .");
      }
    }
  }

  public void writefile(String filename, ArrayList sarf) {
    String state = Environment.getExternalStorageState();

    if (Environment.MEDIA_MOUNTED.equals(state)) {
      if (checkPermission()) {
        File sdcard = Environment.getExternalStorageDirectory();
        File dir = new File(sdcard.getAbsolutePath() + "/text/");
        dir.mkdir();
        File file = new File(dir, filename);
        FileOutputStream os;
        try {
          os = new FileOutputStream(file);
          os.write(sarf.toString().getBytes());
          //   for (Object str : this.sarfSagheerThulathiArray) {
          //     os.write((str.toString().getBytes()));
          //  os.write(newline.getBytes());
          //  }
          //     os.write(enterText.getText().toString().getBytes());
          os.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        requestPermission(); // Code for permission
      }
    }
  }


  private void setupOnItemClickThulathiAdapter() {
    sarfsagheerAdapter.SetOnItemClickListener((v, position) -> {
        SarfSagheer arrayList = ssagheer.get(position);
      ArrayList<String> rootParadigm = new ArrayList<>();
      Bundle dataBundle = new Bundle();
      //      Verbform nasara
      //    verbweakness [الناقص واو]
      if (v.findViewById(R.id.conjugateall) != null) {
      //  rootParadigm = Harakah.getMujarradRootParadigm(arrayList);
       // dataBundle.putString(QURAN_VERB_WAZAN, arrayList.get(2));
        dataBundle.putString(QURAN_VERB_WAZAN, arrayList.getWazan());
        dataBundle.putString(QURAN_VERB_ROOT, arrayList.getVerbroot());
        dataBundle.putString(VERBTYPE,arrayList.getVerbtype());

        dataBundle.putString(VERBMOOD, getVerbmood());
          Intent intent = new Intent(getActivity(), ConjugatorTabsActivity.class);

          intent.putExtras(dataBundle);

        startActivity(intent);



      }

    });
  }


}