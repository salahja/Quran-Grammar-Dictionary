package com.example.mushafconsolidated.Activity;


import static com.example.Constant.mazeedsignificance;
import static com.example.mushafconsolidated.settings.Constants.DATABASENAME;
import static com.example.mushafconsolidated.settings.Constants.DATABASEZIP;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Adapters.HarbNasbMarkupFlowAyahWordAdapter;
import com.example.mushafconsolidated.Adapters.NewSurahDisplayAdapter;
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity;
import com.example.mushafconsolidated.Entities.NasbPOJO;
import com.example.mushafconsolidated.Entities.ShartPOJO;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.Utils;
import com.example.utility.DarkThemeApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    //implements OnItemSelectedListener, ColorPickerDialogListener {
    private static String defaultSaveRootPath;
    public boolean isMakkiMadani;
    boolean isUserInteracting;

    private int indigo, cyan, yellow, green;
    private static final int REQUEST_WRITE_STORAGE = 112;
    private static final int REQUEST_WRITE_Settings = 113;
    private static final int READ_EXTERNAL_STORAGE = 114;

    private HarbNasbMarkupFlowAyahWordAdapter harbNasbMarkupFlowAyahWordAdapter;

    private File newquran;


    public boolean isUserInteracting() {
        return isUserInteracting;
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        isUserInteracting = true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        switchTheme("brown");
        super.onCreate(savedInstanceState);
        boolean hasPermission = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

       setContentView(R.layout.fragment_reading);
        final String tafeel = mazeedsignificance.get("II");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        int SPL = 1;
        if (sp.getInt("spl", 0) != SPL)
        {
            PreferenceManager.setDefaultValues(this, R.xml.preferences, true);
          //  PreferenceManager.setDefaultValues(this, R.xml.prefs2, true);
            sp.edit().putInt("spl", SPL).apply();
        }



      //  extracted(utils);
    //  kanaextracted(utils);
   //   nasbextracted(utils);


        newquran = new File(Environment.getExternalStorageDirectory().
                getAbsolutePath() + getString(R.string.app_folder_path) + File.separator + DATABASENAME);


        if (!hasPermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        } else {

            try {
                validateFilesAndDownload();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


      //  PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        boolean hasPermissions = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);


    }



    private void nasbextracted(Utils utils) {
        ArrayList<String> arr = new ArrayList<>();
        ArrayList<NasbPOJO> kanaPojo = utils.getNasabs();
        for (NasbPOJO kana : kanaPojo) {
            StringBuilder sb = new StringBuilder();
            String verser = String.valueOf(kana.getQurantext());
            int harfwordno = 0, ismstartword = 0,ismendword=0, khabarwordno = 0, khabarendwordno = 0;
            String[] words = verser.split("\\s+");
            int lastindex = 0;

            for (int i = 0; i < words.length; i++) {

                String ss = words[i];
                int indexof;
                if (i == 0) {
                    indexof = verser.indexOf(ss);
                } else {

                    indexof = verser.indexOf(ss, lastindex + 1);

                }


                lastindex = indexof;
                if (indexof == kana.getIndexstart()) {
                    harfwordno = i+1;

                } else if (indexof == kana.getIsmstart()) {
                    ismstartword = i + 1;
                    try {
                        String substring = verser.substring(indexof, kana.getIsmend());
                        String[] split = substring.split("\\s+");
                        ismendword = i + split.length;
                    }catch (StringIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                    }
                } else if (indexof == kana.getKhabarstart()) {
                    khabarwordno = i+1;
                    try {
                        String substring = verser.substring(indexof, kana.getKhabarend());
                        String[] split = substring.split("\\s+");
                        khabarendwordno = i + split.length;
                    }catch (StringIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                    }


                }


            }
            sb.append(kana.getSurah()).append(",").append(kana.getAyah()).append(",").append(kana.getIndexstart())
                    .append(",").append(kana.getIndexend()).append(",")
                    .append(kana.getIsmstart()).append(",").append(kana.getIsmend()).append(",").
                    append(kana.getKhabarstart()).append(",").append(kana.getKhabarend()).append(",").append(harfwordno)
                    .append(",").append(ismstartword).append(",").append(ismendword).append(",").append(khabarwordno).append(",").append(khabarendwordno)
                    .append(",").append(kana.getMahdoof()).append(",").append(kana.getComment());
            arr.add(sb.toString());

            //System.out.println("CHECK");
        }

        writetofile(arr,"newnasb");
        for (String s : arr) {
            System.out.println(s.toString());

        }
    }

    private void kanaextracted(Utils utils) {
        ArrayList<String> arr = new ArrayList<>();
        ArrayList<ShartPOJO> kanaPojo = utils.getSharts();
        for (ShartPOJO kana : kanaPojo) {
            StringBuilder sb = new StringBuilder();
            String verser = String.valueOf(kana.getQurantext());
            int harfwordno = 0, ismstartword = 0,ismendword=0, khabarwordno = 0, khabarendwordno = 0;
            String[] words = verser.split("\\s+");
            int lastindex = 0;

            for (int i = 0; i < words.length; i++) {

                String ss = words[i];
                int indexof;
                if (i == 0) {
                    indexof = verser.indexOf(ss);
                } else {

                    indexof = verser.indexOf(ss, lastindex + 1);

                }


                lastindex = indexof;
                if (indexof == kana.getIndexstart()) {
                    harfwordno = i+1;

                } else if (indexof == kana.getShartindexstart()) {
                    ismstartword = i + 1;
                    try {
                        String substring = verser.substring(indexof, kana.getShartindexend());
                        String[] split = substring.split("\\s+");
                        ismendword = i + split.length;
                    }catch (StringIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                    }
                } else if (indexof == kana.getJawabshartindexstart()) {
                    khabarwordno = i+1;
                    try {
                        String substring = verser.substring(indexof, kana.getJawabshartindexend());
                        String[] split = substring.split("\\s+");
                        khabarendwordno = i + split.length;
                    }catch (StringIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                    }


                } else if (indexof == kana.getJawabshartindexend()) {

                }


            }
            sb.append(kana.getSurah()).append(",").append(kana.getAyah()).append(",").append(kana.getIndexstart())
                    .append(",").append(kana.getIndexend()).append(",")
                    .append(kana.getShartindexstart()).append(",").append(kana.getShartindexend()).append(",").
                    append(kana.getJawabshartindexstart()).append(",").append(kana.getJawabshartindexend()).append(",").append(harfwordno)
                    .append(",").append(ismstartword).append(",").append(ismendword).append(",").append(khabarwordno).append(",").append(khabarendwordno)
            .append(",").append(kana.getSharttype()).append(",").append(kana.getComment());
            arr.add(sb.toString());

            //System.out.println("CHECK");
        }

        writetofile(arr, "newnasb");
        for (String s : arr) {
            System.out.println(s.toString());

        }
    }


    private void writetofile(ArrayList<String> arr, String filename) {
        ArrayList<String> ammended = new ArrayList<>();
        String state = Environment.getExternalStorageState();
        StringBuilder sb = new StringBuilder();
        sb.append("surah").append("|").append("ayah").append("|").append("startindex").append("|").append("endindex")
                .append("|").append("wordfrom").append("|").append("wordo").append("|")
                .append("disconnected").append("|").append("blank");
        ammended.add(sb.toString());


        if (Environment.MEDIA_MOUNTED.equals(state)) {
            FileWriter myWriter;
            String s;
            if (checkPermission()) {
                File sdcard = Environment.getExternalStorageDirectory();
                File dir = new File(sdcard.getAbsolutePath() + "/text/");
                dir.mkdir();
                File file = new File(dir, filename);
                FileOutputStream os;
                try {
                    os = new FileOutputStream(file, true);
                    //      os.write(harfNasbIndexArrayList.toString().getBytes());

                    for (Object str : arr) {
                        os.write(str.toString().getBytes());
                        String newline = "\n";
                        os.write(newline.getBytes());
                    }

                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                requestPermission(); // Code for permission
            }
        }


    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(DarkThemeApplication.getContext(), android.
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) DarkThemeApplication.getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText((Activity) DarkThemeApplication.getContext(), "Write External Storage permission allows us to create files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions((Activity) DarkThemeApplication.getContext(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }









    private void NewSurahDisplay() {
        RecyclerView recview = findViewById(R.id.wordByWordRecyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recview.setLayoutManager(mLayoutManager);

        Utils utils = new Utils(MainActivity.this);
        ArrayList<ChaptersAnaEntity> allAnaChapters = utils.getAllAnaChapters();

        recview.setHasFixedSize(true);
        recview.setLayoutManager(mLayoutManager);
        NewSurahDisplayAdapter newSurahDisplayAdapter = new NewSurahDisplayAdapter(this, allAnaChapters);

        ArrayList<ChaptersAnaEntity> listone = new ArrayList<>();
        ArrayList<ChaptersAnaEntity> listwo = new ArrayList<>();

        for (int index = 0; index < 114; index++) {
            if (index % 2 == 0)
                listone.add(allAnaChapters.get(index));
            else

                listwo.add(allAnaChapters.get(index));


        }
        newSurahDisplayAdapter.setUp(listone, listwo);
        recview.setAdapter(newSurahDisplayAdapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //check if permission had taken or not
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //valid to download or not
                try {
                    validateFilesAndDownload();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, getString(R.string.permission), Toast.LENGTH_LONG).show();
                MainActivity.this.finish();
            }
        }
    }

    private void validateFilesAndDownload() throws IOException {
        boolean isexternalstorageMounted = getDefaultSaveRootPath();
        File imgfile = new File(getCacheDir().getAbsolutePath()
                + getString(R.string.app_folder_path) + "/img_000.png");


        if (!newquran.exists()) {
            // first install copy newquran.db.zip and unzip
            new CopyDatabase().execute();


        } else {





        //   FragmentTransaction transactions = getSupportFragmentManager().beginTransaction()
       //         .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
       //    transactions.replace(R.id.frame_container, frag).addToBackStack("mujarrad");
       //   transactions.commit();
        Intent homeactivity = new Intent(MainActivity.this, newreadactivity.class);
         //   Intent homeactivity = new Intent(MainActivity.this, ReadingSurahPartActivity.class);
           startActivity(homeactivity);
        MainActivity.this.finish();
        }
    }


    public boolean getDefaultSaveRootPath() {


        boolean useExternalStorage = false;
        File externalCacheDir = getExternalCacheDir();
        boolean mounted = Environment.getExternalStorageState().equals("mounted");
        final long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();


        if (mounted) {
            if (freeSpace > 0) {
                useExternalStorage = true;

            }
        }

        return useExternalStorage;
    }


    private class CopyDatabase extends AsyncTask<String, Integer, Void> {
        private ProgressDialog progressBarDD;

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBarDD.dismiss();
            File zipfile = new File(Environment.getExternalStorageDirectory().
                    getAbsolutePath() + getString(R.string.app_folder_path) + File.separator + DATABASEZIP);

            Intent zipintent = new Intent(MainActivity.this, UnZipFIleActivity.class);
            zipintent.putExtra("zipfile", zipfile.toString());
            startActivity(zipintent);
            MainActivity.this.finish();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();

        }

        private void showProgressDialog() {
            if (progressBarDD == null) {
                progressBarDD = new ProgressDialog(MainActivity.this);
                //    progressBarDD.setMessage("Please wait"+file.toString()+"being updated");
                progressBarDD.setMessage("Please wait translation being updated");
                progressBarDD.setIndeterminate(false);
                progressBarDD.setCancelable(false);
                progressBarDD.show();
            }
            progressBarDD.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBarDD.setProgress(values[0]);
            //   tv.setText(values[0]);

        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                boolean canWrie = canWriteInSDCard();
                File databaseDirectory = new File(Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/" + DarkThemeApplication.getContext().getResources().getString(R.string.app_folder_path));
                if (!databaseDirectory.exists()) {
                    databaseDirectory.mkdirs();
                }
                //        File databaseFile = new File(databaseDirectory,"newquran.db");
                File databaseFile = new File(databaseDirectory, DATABASEZIP);
                boolean b = databaseFile.getParentFile().mkdirs();
                if (!databaseFile.exists()) {
                    boolean createFile = databaseFile.createNewFile();
                }
                //    InputStream inputStream = getApplicationContext().getAssets().open("newquran.db");
                InputStream inputStream = getApplicationContext().getAssets().open(DATABASEZIP);
                FileOutputStream outputStream = new FileOutputStream(databaseFile);
                int fileSize = inputStream.available();
                publishProgress(0, fileSize);
                int copylength = 0;
                byte[] buffer = new byte[1024];
                while (true) {
                    int read = inputStream.read(buffer);
                    if (read == -1) break;
                    copylength += read;
                    publishProgress(copylength, fileSize);
                    outputStream.write(buffer, 0, read);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            }
            return null;
        }

        private boolean canWriteInSDCard() {
            String state = Environment.getExternalStorageState();
            return Environment.MEDIA_MOUNTED.equals(state);
        }


    }


}


