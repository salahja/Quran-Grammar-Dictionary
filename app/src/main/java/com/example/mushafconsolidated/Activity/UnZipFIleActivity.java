package com.example.mushafconsolidated.Activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mushafconsolidated.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.tonyodev.fetch2.Fetch;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipFIleActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    private String str2;
    private RelativeLayout LL;
    int mPageNo = 0;
    private MaterialToolbar materialToolbar;
    ImageView imgPg;
    private MenuItem jumpto;
    private static final int REQUEST_WRITE_STORAGE = 112;
    private static final int REQUEST_WRITE_Settings = 113;
    private Fetch fetch;
    TextView link;
    private ProgressBar progressBar1;
    private File childfile;
    private String databasepath;
    private String databasefiledirec;
    private File mainDatabasesZIP;
    int counter = 0;
    private File targetDirectory;
    private FileInputStream is;
    private ZipInputStream zis;
    private ZipEntry ze;
    private String filename;

    public int getFetchtwice() {
        return fetchtwice;
    }

    public void setFetchtwice(int fetchtwice) {
        this.fetchtwice = fetchtwice;
    }

    private int fetchtwice;
    TextView tv;
    ProgressBar progressBar;
    private ProgressDialog progressBarDD;
    private int progressBarStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);
        //     mintent.putExtra("downloaded", "downloaded");
        //   mintent.putExtra("zipfile", mainDatabasesZIP.toString());
        progressBarDD = new ProgressDialog(this, R.style.MyAlertDialogStyle);


        progressBarDD.setCancelable(true);
        progressBarDD.setMessage("Unzipping files...");
        progressBarDD.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBarDD.setProgress(0);
        progressBarDD.setMax(100);
        progressBarDD.show();
        progressBarStatus = 0;
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            databasefiledirec = bundle.getString("zipfile");

        }
        targetDirectory = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + getApplicationContext().getResources().getString(R.string.app_folder_path));
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tv = findViewById(R.id.link);

        mainDatabasesZIP = new File(databasefiledirec);


        new UnzipFile().execute();

    }

    private class UnzipFile extends AsyncTask<String, Integer, Boolean> {


        @Override
        protected Boolean doInBackground(String... strings) {
            ZipInputStream zis = null;
            int progress = 1;
            try {
                zis = new ZipInputStream(
                        new BufferedInputStream(new FileInputStream(mainDatabasesZIP)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                AlertDialog.Builder dialog = new  AlertDialog.Builder(UnZipFIleActivity.this);
                dialog.setMessage(e.getCause().toString());
                AlertDialog alertDialog = dialog.create();
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
                    FileOutputStream fout = new FileOutputStream(file);
                    try {
                        progress += 1;

                        while ((count = zis.read(buffer)) != -1) {
                            fout.write(buffer, 0, count);
                            progress += 1;
                            progressBarDD.setProgress(progress);
                        }
                    } finally {
                        fout.close();
                    }
            /* if time should be restored as well
            long time = ze.getTime();
            if (time > 0)
                file.setLastModified(time);
            */
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    zis.close();
                    mainDatabasesZIP.delete();
                    progressBarDD.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                //    Intent mainintent=new Intent(context,MainActivity.class);
                //   context.startActivity(mainintent);
                tv.setText(R.string.Done);
                Intent intent = new Intent();
                intent.putExtra("result", 1);
                setResult(RESULT_OK, intent);
       //         Intent fetechintent = new Intent(UnZipFIleActivity.this, HomeActivity.class);

                Intent homeactivity = new Intent(UnZipFIleActivity.this, newreadactivity.class);
                startActivity(homeactivity);

              //  startActivity(fetechintent);
                UnZipFIleActivity.this.finish();
                finish();

                //   downloadDialog();
            } else {
                tv.setText(R.string.failed);
                Intent intent = new Intent();
                intent.putExtra("result", 0);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}















