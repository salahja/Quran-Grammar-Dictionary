package com.example.mushafconsolidated.Activity;


import static com.example.Constant.mazeedsignificance;
import static com.example.mushafconsolidated.settings.Constants.DATABASENAME;
import static com.example.mushafconsolidated.settings.Constants.DATABASEZIP;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mushafconsolidated.R;

import com.example.utility.DarkThemeApplication;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class MainActivity extends BaseActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;



    boolean isUserInteracting;


    private static final int REQUEST_WRITE_STORAGE = 112;
    private static final int REQUEST_WRITE_Settings = 113;
    private static final int READ_EXTERNAL_STORAGE = 114;



    private File newquran;




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

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        int SPL = 1;
        if (sp.getInt("spl", 0) != SPL)
        {
            PreferenceManager.setDefaultValues(this, R.xml.preferences, true);
          //  PreferenceManager.setDefaultValues(this, R.xml.prefs2, true);
            sp.edit().putInt("spl", SPL).apply();
        }


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












    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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



        if (!newquran.exists()) {
            // first install copy newquran.db.zip and unzip
         //   new CopyDatabase().execute();
            CopyDatbases();


        } else {

        Intent homeactivity = new Intent(MainActivity.this, newreadactivity.class);
         //   Intent homeactivity = new Intent(MainActivity.this, ReadingSurahPartActivity.class);
           startActivity(homeactivity);
        MainActivity.this.finish();
        }
    }

    private void CopyDatbases() {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        AlertDialog     dialog = builder.create();
           ex.execute(new Runnable() {
               @Override
               public void run() {
                   runOnUiThread(() -> dialog.show());
                   boolean canWrie = canWriteInSDCard();
                   if(canWrie) {
                       try {


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
                           //   publishProgress(0, fileSize);
                           int copylength = 0;
                           byte[] buffer = new byte[1024];
                           while (true) {
                               int read = inputStream.read(buffer);
                               if (read == -1) break;
                               copylength += read;
                               //   publishProgress(copylength, fileSize);
                               outputStream.write(buffer, 0, read);
                           }
                           outputStream.flush();
                           outputStream.close();
                           inputStream.close();
                       } catch (IOException e1) {
                           e1.printStackTrace();

                       }
                   }else{
//todo
                   }
                   //post

                   runOnUiThread(() -> {

                       File zipfile = new File(Environment.getExternalStorageDirectory().
                               getAbsolutePath() + getString(R.string.app_folder_path) + File.separator + DATABASEZIP);

                       File mainDatabasesZIP = new File(String.valueOf(zipfile));
                    File   targetDirectory = new File(Environment.getExternalStorageDirectory()
                               .getAbsolutePath() + "/" + getApplicationContext().getResources().getString(R.string.app_folder_path));
                       ZipInputStream zis = null;
                       int progress = 1;
                       try {
                           zis = new ZipInputStream(
                                   new BufferedInputStream(new FileInputStream(mainDatabasesZIP)));
                       } catch (FileNotFoundException e) {
                           e.printStackTrace();
                           AlertDialog.Builder dialog1 = new  AlertDialog.Builder(MainActivity.this);
                           dialog1.setMessage(e.getCause().toString());
                           AlertDialog alertDialog = dialog1.create();
                           alertDialog.show();

                       }
                       try {
                           ZipEntry ze;
                           int count;
                           byte[] buffer = new byte[8192];
                           while ((ze = zis.getNextEntry()) != null) {
                               File file = new File(targetDirectory, ze.getName());
                               File dir = ze.isDirectory() ? file : file.getParentFile();
                               if (!dir.isDirectory() && !dir.mkdirs())
                                   throw new FileNotFoundException("Failed to ensure directory: " +
                                           dir.getAbsolutePath());
                               if (ze.isDirectory())
                                   continue;
                               try (FileOutputStream fout = new FileOutputStream(file)) {
                                   progress += 1;

                                   while ((count = zis.read(buffer)) != -1) {
                                       fout.write(buffer, 0, count);
                                       progress += 1;
                                       //   progressBarDD.setProgress(progress);
                                   }
                               }

                           }
                       } catch (IOException e) {
                           e.printStackTrace();
                       } finally {
                           try {
                               zis.close();
                               mainDatabasesZIP.delete();
                          //     progressBarDD.dismiss();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                       ex.shutdown();
                       dialog.dismiss();

                       Intent zipintent = new Intent(MainActivity.this, newreadactivity.class);

                       startActivity(zipintent);
                       MainActivity.this.finish();

                   });


               }

               private boolean canWriteInSDCard() {
                   String state = Environment.getExternalStorageState();
                   return Environment.MEDIA_MOUNTED.equals(state);
               }
           });

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





}


