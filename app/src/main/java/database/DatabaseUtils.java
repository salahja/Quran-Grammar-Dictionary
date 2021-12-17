package database;

import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.util.DisplayMetrics;


import database.entity.BuckwaterEntitiy;
import database.entity.Mazeed;
import database.entity.MujarradVerbs;
import database.entity.QuranVerbsEntity;
import database.entity.QuranicVerbsEntity;
import database.entity.kov;
import database.entity.verbcorpus;
import database.entity.VerbsTriMazeedDictEntity;

import java.util.ArrayList;

public class DatabaseUtils {


    private static VerbDatabase database;


    // {
    //     LiveData<List<wordbyword>> liveUsers = WordbywordPojoDao.getWords(new
    //             SimpleSQLiteQuery("SELECT * FROM "));
    // }


    public DatabaseUtils(Context context) {
        database = VerbDatabase.getInstance(context);

    }

    //  public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    //   @Override
    //    public void migrate(SupportSQLiteDatabase database) {
    //       database.execSQL("CREATE TABLE IF NOT EXISTS `quranicverbs` (`verb` TEXT, `root` TEXT, `thulathibab` TEXT, `form` INTEGER NOT NULL, `chaptername` TEXT, `frequency` INTEGER NOT NULL, `meaning` TEXT, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
    //  }
    //  };


    public static String getSizeName(Context context) {
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "large";
            case 4: // Configuration.SCREENLAYOUT_SIZE_XLARGE is API >= 9
                return "xlarge";
            default:
                return "undefined";
        }
    }

    public static String getDeviceDensity(Context mContext) {
        int density = mContext.getResources().getDisplayMetrics().densityDpi;
        switch (density) {
            case DisplayMetrics.DENSITY_MEDIUM:
                return "MDPI";
            case DisplayMetrics.DENSITY_HIGH:
                return "HDPI";
            case DisplayMetrics.DENSITY_LOW:
                return "LDPI";
            case DisplayMetrics.DENSITY_XHIGH:
                return "XHDPI";
            case DisplayMetrics.DENSITY_TV:
                return "TV";
            case DisplayMetrics.DENSITY_XXHIGH:
                return "XXHDPI";
            case DisplayMetrics.DENSITY_XXXHIGH:
                return "XXXHDPI";
            default:
                return "Unknown";
        }
    }

    public ArrayList<BuckwaterEntitiy> getBuckwater() {
        ArrayList<BuckwaterEntitiy> buckwater = (ArrayList<BuckwaterEntitiy>) database.BuckwaterDao().getArabic();
        return buckwater;

    }

    public ArrayList<QuranVerbsEntity> getQuranVerbsbyForm(int sort) {
        ArrayList<QuranVerbsEntity> quranverbs = (ArrayList<QuranVerbsEntity>) database.QuranVerbsDao().getverbsbyForm(sort);
        return quranverbs;

    }
    public ArrayList<kov> getKov() {
        ArrayList<kov> kovs = (ArrayList<kov>) database.kovDao().getRules();
        return kovs;

    }

    public ArrayList<QuranVerbsEntity> getQuranVerbsbyFrequency(int sort) {
        ArrayList<QuranVerbsEntity> quranverbs = (ArrayList<QuranVerbsEntity>) database.QuranVerbsDao().getverbsbyFrequency(sort);
        return quranverbs;

    }
    public ArrayList<QuranicVerbsEntity> getQuranicVerbsMazeed() {
        ArrayList<QuranicVerbsEntity> quranicverbs = (ArrayList<QuranicVerbsEntity>) database.QuranicVerbsDao().getverbsMazeed();
        return quranicverbs;

    }

    public ArrayList<QuranicVerbsEntity> getQuranicVerbsbyForm() {
        ArrayList<QuranicVerbsEntity> quranicverbs = (ArrayList<QuranicVerbsEntity>) database.QuranicVerbsDao().getverbsbyForm();
        return quranicverbs;

    }

    public int updateTrimRoots(String nroot, int id) {
        int updadateRoots = database.QuranicVerbsDao().updadateRoots(nroot, id);

return updadateRoots;
    }
    public int updateThulathibab(String nroot, int id) {
        int updadateThulathi= database.QuranicVerbsDao().updadateThulathibab(nroot, id);

        return updadateThulathi;
    }


    public ArrayList<MujarradVerbs> getMujarradVerbs(String root) {
        ArrayList<MujarradVerbs> mujarrad = (ArrayList<MujarradVerbs>) database.mujarradDao().getverbTri(root);
        return mujarrad;

    }

    public ArrayList<MujarradVerbs> getMujarradAall( ) {
        ArrayList<MujarradVerbs> triverbsall = (ArrayList<MujarradVerbs>) database.mujarradDao().getverbTriAll();
                return triverbsall;
    }
    public ArrayList<MujarradVerbs> getMujarradBYWeakness(String kov ) {
        ArrayList<MujarradVerbs> triverbsall = (ArrayList<MujarradVerbs>) database.mujarradDao().getMujarradWeakness(kov);
        return triverbsall;
    }
    public ArrayList<Mazeed> getMazeedWeakness(String kov ) {
        ArrayList<Mazeed> triverbsall = (ArrayList<Mazeed>) database.mazeedDao().getMazeedWeakness(kov);
        return triverbsall;
    }
    public ArrayList<VerbsTriMazeedDictEntity> getTriVerbMazeed(String root) {
        ArrayList<VerbsTriMazeedDictEntity> triverbsmazeed = (ArrayList<VerbsTriMazeedDictEntity>) database.VerbsTriMazeedDictDao().getverbMazeedTri(root);
        return triverbsmazeed;

    }


    public ArrayList<VerbsTriMazeedDictEntity> getTriVerbMazeedAll() {
        ArrayList<VerbsTriMazeedDictEntity> triverbsmazeed = (ArrayList<VerbsTriMazeedDictEntity>) database.VerbsTriMazeedDictDao().getverbMazeedTriAll();
        return triverbsmazeed;

    }

    public ArrayList<Mazeed> getMazeedAll() {
        ArrayList<Mazeed> triverbsmazeed = (ArrayList<Mazeed>) database.mazeedDao().getMazeedAll();
        return triverbsmazeed;

    }
    public ArrayList<Mazeed> getMazeedRoot(String root) {
        ArrayList<Mazeed> triverbsmazeed = (ArrayList<Mazeed>) database.mazeedDao().getMazeedRoot(root);
        return triverbsmazeed;

    }

    public static void insertBookMarks(MujarradVerbs entity) {


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.mujarradDao().insert(entity);
                return null;
            }
        }.execute();

    }

    public ArrayList<verbcorpus> verbcorpuses() {


        ArrayList<verbcorpus> forms = (ArrayList<verbcorpus>) database.verbcorpusDao().getmazeedform("I");
        return forms;
    }
}










