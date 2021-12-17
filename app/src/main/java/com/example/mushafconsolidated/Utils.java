
package com.example.mushafconsolidated;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.sqlite.db.SimpleSQLiteQuery;

import com.example.mushafconsolidated.Entities.BookMarks;
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity;
import com.example.mushafconsolidated.Entities.CorpusEntity;
import com.example.mushafconsolidated.Entities.CorpusExpandWbwPOJO;
import com.example.mushafconsolidated.Entities.CorpusNounWbwGrouping;
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance;
import com.example.mushafconsolidated.Entities.CorpusVerbWbwOccurance;
import com.example.mushafconsolidated.Entities.KanaPOJO;
import com.example.mushafconsolidated.Entities.MudhafPOJO;
import com.example.mushafconsolidated.Entities.NasbPOJO;
import com.example.mushafconsolidated.Entities.NewCorpusExpandWbwPOJO;
import com.example.mushafconsolidated.Entities.NewKanaEntity;
import com.example.mushafconsolidated.Entities.NewMudhafEntity;
import com.example.mushafconsolidated.Entities.NewNasbEntity;
import com.example.mushafconsolidated.Entities.NewShartEntity;
import com.example.mushafconsolidated.Entities.NounCorpus;
import com.example.mushafconsolidated.Entities.NounCorpusBreakup;
import com.example.mushafconsolidated.Entities.QuranEntity;
import com.example.mushafconsolidated.Entities.ShartPOJO;
import com.example.mushafconsolidated.Entities.SifaEntity;
import com.example.mushafconsolidated.Entities.SifaEntityPojo;
import com.example.mushafconsolidated.Entities.SifaPOJO;
import com.example.mushafconsolidated.Entities.VerbCorpus;
import com.example.mushafconsolidated.Entities.VerbCorpusBreakup;
import com.example.mushafconsolidated.Entities.hanslexicon;
import com.example.mushafconsolidated.Entities.lanelexicon;
import com.example.mushafconsolidated.Entities.lughat;
import com.example.mushafconsolidated.Entities.qurandictionary;
import com.example.mushafconsolidated.Entities.wbwentity;
import com.example.mushafconsolidated.model.wordbyword;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;

public class Utils {

    private static final String TAG = "Utils";
    private static QuranAppDatabase database;
    public ArrayList<wordbyword> getUserViaQuery;

    final Context thiscontext;
    private String tid;
    private int id;

    public Utils(Context context) {
        database = QuranAppDatabase.getInstance(context);
        this.thiscontext = context;

    }



    public ArrayList<NewMudhafEntity> getMudhafAllNew() {
        Log.d(TAG, "getAllAnaChapters: started");
        ArrayList<NewMudhafEntity> items = (ArrayList<NewMudhafEntity>) database.NewMudhafDao().getMudhafAll();
        return items;


    }
    public ArrayList<NewMudhafEntity> getMudhafSurahNew(int id){

        ArrayList<NewMudhafEntity> items = (ArrayList<NewMudhafEntity>) database.NewMudhafDao().getMudhafSurah(id);
        return items;


    }

    public ArrayList<NewMudhafEntity> getMudhafSurahAyahNew(int id,int aid){

        ArrayList<NewMudhafEntity> items = (ArrayList<NewMudhafEntity>) database.NewMudhafDao().getMudhafSurahAyah(id,aid);
        return items;


    }





    public ArrayList<ChaptersAnaEntity> getAllAnaChapters() {
        Log.d(TAG, "getAllAnaChapters: started");
        ArrayList<ChaptersAnaEntity> items = (ArrayList<ChaptersAnaEntity>) database.AnaQuranChapterDao().getChapters();
        return items;


    }



    public ArrayList<ChaptersAnaEntity> getSingleChapter(int id) {
        Log.d(TAG, "getSingleChapter: started");
        ArrayList<ChaptersAnaEntity> singleChapter = (ArrayList<ChaptersAnaEntity>) database.AnaQuranChapterDao().getSingleChapters(id);
        return singleChapter;


    }



/*
    public ArrayList<Juzentity> getSurahPartList() {
            Log.d(TAG, "getVerses: started");
            ArrayList<Juzentity> JuzA = (ArrayList<Juzentity>) database.JuzDao().getSurahPartList();
            return JuzA;
        }
*/






    public ArrayList<BookMarks> getBookMarks() {
        Log.d(TAG, "getAllItems: started");
        ArrayList<BookMarks> bookMarks = (ArrayList<BookMarks>) database.BookMarkDao().getBookMarks();

        return bookMarks;
    }



    public ArrayList<QuranEntity> getVersesByPartQuranEntity(int start, int end) {
        Log.d(TAG, "getVersesByPartNewVerseEntity: started");
        ArrayList<QuranEntity> partVerses = (ArrayList<QuranEntity>) database.QuranDao().getVersesByPart(start, end);

        return partVerses;
    }












    public ArrayList<CorpusEntity> getCorpusSurah(int id) {
        Log.d(TAG, "getCorpusSurah: started");
        ArrayList<CorpusEntity> surahVerses = (ArrayList<CorpusEntity>) database.CorpusExpandedDao().getVersesBySurah(id);

        return surahVerses;
    }

    public ArrayList<CorpusEntity> getVersesBySurahAndAya(int id,int ayd) {
        Log.d(TAG, "getCorpusSurah: started");
        ArrayList<CorpusEntity> surahVerses = (ArrayList<CorpusEntity>) database.CorpusExpandedDao().getVersesBySurahAndAya(id,ayd);

        return surahVerses;
    }


    public ArrayList<CorpusEntity> getCorpusSurahWord(int id, int verseid, int wordid) {
        Log.d(TAG, "getCorpusSurahWord: started");
        ArrayList<CorpusEntity> surahVerses = (ArrayList<CorpusEntity>) database.CorpusExpandedDao().getCorpusWord(id, verseid, wordid);

        return surahVerses;
    }

    public ArrayList<VerbCorpus> getQuranRoot(int id, int verseid, int wordid) {
        Log.d(TAG, "getQuranRoot: started");
        ArrayList<VerbCorpus> surahVerses = (ArrayList<VerbCorpus>) database.VerbCorpusDao().getQuranRoot(id, verseid, wordid);

        return surahVerses;
    }
    public ArrayList<VerbCorpus> getQuranRootaAyah(int id, int verseid ) {
        Log.d(TAG, "getQuranRoot: getQuranRootaAyah");
        ArrayList<VerbCorpus> surahVerses = (ArrayList<VerbCorpus>) database.VerbCorpusDao().getQuranRootaAyah(id, verseid );

        return surahVerses;
    }


    public ArrayList<NounCorpus> getQuranNouns(int id, int verseid, int wordid) {
        Log.d(TAG, "getQuranNouns: started");
        ArrayList<NounCorpus> qurannouns = (ArrayList<NounCorpus>) database.NounCorpusDao().getQuranNouns(id, verseid, wordid);

        return qurannouns;
    }
    public ArrayList<NounCorpus> getQuranNounAyah(int id, int verseid ) {
        Log.d(TAG, "getQuranNouns: started");
        ArrayList<NounCorpus> qurannouns = (ArrayList<NounCorpus>) database.NounCorpusDao().getQuranNounAyah(id, verseid );

        return qurannouns;
    }




    public ArrayList<VerbCorpus> getQuranRootbyString(String root) {
        Log.d(TAG, "getQuranRoot: started");
        ArrayList<VerbCorpus> surahVerses = (ArrayList<VerbCorpus>) database.VerbCorpusDao().getQuranRootbyString(root);

        return surahVerses;
    }





    public ArrayList<NounCorpus> getAllNouns() {
        Log.d(TAG, "getAllNouns: started");
        ArrayList<NounCorpus> qurannouns = (ArrayList<NounCorpus>) database.NounCorpusDao().getAllnouns();

        return qurannouns;
    }


    public ArrayList<CorpusEntity> getCorpusSurahAyhCount(int id) {
        Log.d(TAG, "getCorpusSurahnew: started");
        String sqltwo = " SELECT VerseNew.chapter_no, VerseNew.verse_no, Translation.author_name, TranslationData.translation FROM TranslationData INNER JOIN Translation ON Translation.translation_id = TranslationData.translation_id INNER JOIN VerseNew ON VerseNew.verseID = TranslationData.verse_id \n" +
                "    WHERE TranslationData.translation_id == \""
                + tid + "\""
                + " and VerseNew.chapter_no= "
                + id;
        // select count(ayah)from corpusexpand where corpusexpand.surah=1 group by ayah
        String sql = "select count(ayah)as wordcount from corpusexpand where corpusexpand.surah=\""
                + id + "\"" + " group by ayah";


        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sql);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusEntity> translationEntityArrayList = (ArrayList<CorpusEntity>) database.RawDao().getayahcount(query);


        return translationEntityArrayList;
    }




    public ArrayList<wbwentity> getwbwTranslatonbywordNew(int id, int ayaid, int wordid) {
        Log.d(TAG, "getwbwTranslatonbyword: started");
        ArrayList<wbwentity> bywordallwords = (ArrayList<wbwentity>) database.wbwDao().getwbwTranslationbywordno(id, ayaid, wordid);

        return bywordallwords;
    }











    public static void insertBookMarks(BookMarks entity) {
        final String chapterno = entity.getChapterno();
        final String verseno = entity.getVerseno();
        final String surahname = entity.getSurahname();
        final String timestamp = entity.getDatetime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        // String sql="INSERT INTO bookmark entity";
        String sql = "\"insert into `bookmark`('chapterno','verseno','surahname','datetime')values (" + chapterno + " , '" + verseno + " , '" + surahname + "' '" + sdf + "'  )";
        //    String sqlt="insert into `bookmark`('chapterno','verseno','surahname','datetime') values (" + chapterno + " , " + verseno +" , "+ '"surahname"'+ " , '" + timestamp+ "')";
        //   String sql = "insert into `bookmarks`   values (" + chapterno + " , '" + verseno +" , '"+surahname+" , '"+timestamp+ "')";
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sql);
        final int records = database.RawDao().insertRecords(query);


    }

    public static List<BookMarks> getBookMarksNew() {
        final List<BookMarks> marks = database.BookMarkDao().getBookMarks();
        return marks;

    }


    public static List<QuranEntity> getQuranbySurah(int id) {
        final List<QuranEntity> marks = database.QuranDao().getQuranVersesBySurah(id);
        return marks;

    }

    public static List<QuranEntity> getQuranSurahayah(int id,int ayahid) {
        final List<QuranEntity> marks = database.QuranDao().getsurahayahVerses(id,ayahid);
        return marks;

    }




    public void insertBookMark(BookMarks entity) {
        //    database.BookMarkDao().deleteAllBookMakrs();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        // and deleting
                        database.BookMarkDao().insertBookmark(entity);
                        //  runOnUiThread(new Runnable() {
                        //  public void run() {
                        //   itemTextView.setText("item deleted");
                        //    }
                        //  });
                    }
                });
            }
        });


    }


    public void deleteAllBookMarks() {
        //    database.BookMarkDao().deleteAllBookMakrs();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        // and deleting
                        database.BookMarkDao().deleteAllBookMakrs();
                        //  runOnUiThread(new Runnable() {
                        //  public void run() {
                        //   itemTextView.setText("item deleted");
                        //    }
                        //  });
                    }
                });
            }
        });


    }

    public static void deleteBookMarks(BookMarks bookMarks) {


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.BookMarkDao().deletebookmark(bookMarks);
                return null;
            }
        }.execute();

    }


    public ArrayList<CorpusExpandWbwPOJO> getCorpusWbwByPart(int tid) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                "       CorpusExpand.surah,\n" +
                "       CorpusExpand.ayah,\n" +
                "       CorpusExpand.wordno,\n" +
                "       CorpusExpand.wordcount,\n" +
                "       Qurans.translation,\n" +
                "       qurans.qurantext,\n" +
                "       CorpusExpand.araone,\n" +
                "       CorpusExpand.aratwo,\n" +
                "       CorpusExpand.arathree,\n" +
                "       CorpusExpand.arafour,\n" +
                "       CorpusExpand.arafive,\n" +
                "       CorpusExpand.tagone,\n" +
                "       CorpusExpand.tagtwo,\n" +
                "       CorpusExpand.tagthree,\n" +
                "       CorpusExpand.tagfour,\n" +
                "       CorpusExpand.tagfive,\n" +
                "       CorpusExpand.detailsone,\n" +
                "       CorpusExpand.detailstwo,\n" +
                "       CorpusExpand.detailsthree,\n" +
                "       CorpusExpand.detailsfour,\n" +
                "       CorpusExpand.detailsfive,\n" +
                "       wbw.en,\n" +
                "       wbw.bn\n" +

                "  FROM corpusexpand,\n" +
                "       wbw,qurans\n" +
                " WHERE CorpusExpand.juz == \""
                + tid + "\""
                + "AND \n" +
                "       corpusexpand.surah = wbw.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno AND\n" +
                "       corpusexpand.surah = qurans.surah AND \n" +
                "       corpusexpand.ayah = qurans.ayah  \n" +
                " ORDER BY corpusexpand.surah,\n" +
                "          corpusexpand.ayah";




        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusExpandWbwPOJO> verbWbw = (ArrayList<CorpusExpandWbwPOJO>) database.RawDao().getCorpusWbw(query);

        return verbWbw;
    }

    public ArrayList<CorpusExpandWbwPOJO> getConditionalTimeAdverb( ) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                "       CorpusExpand.surah,\n" +
                "       CorpusExpand.ayah,\n" +
                "       CorpusExpand.wordno,\n" +
                "       CorpusExpand.wordcount,\n" +
                "       Qurans.translation,\n" +

                "       CorpusExpand.araone,\n" +
                "       CorpusExpand.aratwo,\n" +
                "       CorpusExpand.arathree,\n" +
                "       CorpusExpand.arafour,\n" +
                "       CorpusExpand.arafive,\n" +
                "       CorpusExpand.tagone,\n" +
                "       CorpusExpand.tagtwo,\n" +
                "       CorpusExpand.tagthree,\n" +
                "       CorpusExpand.tagfour,\n" +
                "       CorpusExpand.tagfive,\n" +
                "       CorpusExpand.detailsone,\n" +
                "       CorpusExpand.detailstwo,\n" +
                "       CorpusExpand.detailsthree,\n" +
                "       CorpusExpand.detailsfour,\n" +
                "       CorpusExpand.detailsfive,\n" +
                "       wbw.en,\n" +
                "       wbw.bn,\n" +
                "       wbw.[in],\n" +
              "       wbw.ur,\n" +
                "       qurans.qurantext\n" +
                "  FROM corpusexpand,\n" +
                "       qurans,\n" +
                "       wbw\n" +
                " WHERE (CorpusExpand.araone  like  \"إِذَا\" OR  CorpusExpand.ARATWO LIKE \"إِذَا\"   OR CorpusExpand.ARATWO LIKE \"إِذَا\") and\n" +
                "       corpusexpand.surah = wbw.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno AND\n" +
                "       corpusexpand.surah = qurans.surah AND \n" +
                "       corpusexpand.ayah = qurans.ayah  \n" +
                " ORDER BY corpusexpand.surah,\n" +
                "          corpusexpand.ayah";




        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusExpandWbwPOJO> verbWbw = (ArrayList<CorpusExpandWbwPOJO>) database.RawDao().getCorpusWbw(query);

        return verbWbw;
    }
    public ArrayList<NewCorpusExpandWbwPOJO> getCorpusWbwBySurahAyahWordid(int tid, int aid, int wid) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                "       CorpusExpand.surah,\n" +
                "       CorpusExpand.ayah,\n" +
                "       CorpusExpand.wordno,\n" +
                "       CorpusExpand.wordcount,\n" +
                "       Qurans.translation,\n" +
              "       Qurans.ur_junagarhi,\n" +
                "       CorpusExpand.araone,\n" +
                "       CorpusExpand.aratwo,\n" +
                "       CorpusExpand.arathree,\n" +
                "       CorpusExpand.arafour,\n" +
                "       CorpusExpand.arafive,\n" +

                "       CorpusExpand.rootaraone,\n" +
                "       CorpusExpand.rootaratwo,\n" +
                "       CorpusExpand.rootarathree,\n" +
                "       CorpusExpand.rootarafour,\n" +
                "       CorpusExpand.rootarafive,\n" +
                "       CorpusExpand.lemaraone,\n" +
                "       CorpusExpand.lemaratwo,\n" +
                "       CorpusExpand.lemrathree,\n" +
                "       CorpusExpand.lemarafour,\n" +
                "       CorpusExpand.lemarafive,\n" +

        "            CorpusExpand.form_one,\n" +
                "       CorpusExpand.form_two,\n" +
                "       CorpusExpand.form_three,\n" +
                "       CorpusExpand.form_four,\n" +
                "       CorpusExpand.form_five,\n" +


                "       CorpusExpand.tagone,\n" +
                "       CorpusExpand.tagtwo,\n" +
                "       CorpusExpand.tagthree,\n" +
                "       CorpusExpand.tagfour,\n" +
                "       CorpusExpand.tagfive,\n" +
                "       CorpusExpand.detailsone,\n" +
                "       CorpusExpand.detailstwo,\n" +
                "       CorpusExpand.detailsthree,\n" +
                "       CorpusExpand.detailsfour,\n" +
                "       CorpusExpand.detailsfive,\n" +
                "       wbw.en,\n" +
                "       wbw.bn,\n" +
                "       wbw.[in],\n" +
              "       wbw.ur,\n" +
                "       qurans.qurantext\n" +
                "  FROM corpusexpand,\n" +
                "       qurans,\n"+
                "       wbw\n" +
                " WHERE CorpusExpand.surah == \""
                + tid + "\""
                + "AND CorpusExpand.ayah== \""
                + aid + "\""
                + "AND CorpusExpand.wordno==\""
                + wid + "\""
                + "AND \n" +


                "       corpusexpand.surah = wbw.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno AND\n" +
                "       corpusexpand.surah = qurans.surah AND \n" +
                "       corpusexpand.ayah = qurans.ayah  \n" +
                " ORDER BY corpusexpand.surah,\n" +
                "          corpusexpand.ayah";





        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<NewCorpusExpandWbwPOJO> verbWbw = (ArrayList<NewCorpusExpandWbwPOJO>) database.RawDao().getNewCorpusWbw(query);

        return verbWbw;
    }
    public ArrayList<NewCorpusExpandWbwPOJO> getCorpusWbwBySurahAyah(int tid, int aid) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                "       CorpusExpand.surah,\n" +
                "       CorpusExpand.ayah,\n" +
                "       CorpusExpand.wordno,\n" +
                "       CorpusExpand.wordcount,\n" +
                "       Qurans.translation,\n" +

                "       CorpusExpand.araone,\n" +
                "       CorpusExpand.aratwo,\n" +
                "       CorpusExpand.arathree,\n" +
                "       CorpusExpand.arafour,\n" +
                "       CorpusExpand.arafive,\n" +

                "       CorpusExpand.rootaraone,\n" +
                "       CorpusExpand.rootaratwo,\n" +
                "       CorpusExpand.rootarathree,\n" +
                "       CorpusExpand.rootarafour,\n" +
                "       CorpusExpand.rootarafive,\n" +
                "       CorpusExpand.lemaraone,\n" +
                "       CorpusExpand.lemaratwo,\n" +
                "       CorpusExpand.lemrathree,\n" +
                "       CorpusExpand.lemarafour,\n" +
                "       CorpusExpand.lemarafive,\n" +

                "            CorpusExpand.form_one,\n" +
                "       CorpusExpand.form_two,\n" +
                "       CorpusExpand.form_three,\n" +
                "       CorpusExpand.form_four,\n" +
                "       CorpusExpand.form_five,\n" +


                "       CorpusExpand.tagone,\n" +
                "       CorpusExpand.tagtwo,\n" +
                "       CorpusExpand.tagthree,\n" +
                "       CorpusExpand.tagfour,\n" +
                "       CorpusExpand.tagfive,\n" +
                "       CorpusExpand.detailsone,\n" +
                "       CorpusExpand.detailstwo,\n" +
                "       CorpusExpand.detailsthree,\n" +
                "       CorpusExpand.detailsfour,\n" +
                "       CorpusExpand.detailsfive,\n" +
                "       wbw.en,\n" +
                "       wbw.bn,\n" +
                "       wbw.[in],\n" +
              "       wbw.ur,\n" +
                "       qurans.qurantext\n" +
                "  FROM corpusexpand,\n" +
                "       qurans,\n"+
                "       wbw\n" +
                " WHERE CorpusExpand.surah == \""
                + tid + "\""
                + "AND CorpusExpand.ayah== \""
                + aid + "\""

                + "AND \n" +


                "       corpusexpand.surah = wbw.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno AND\n" +
                "       corpusexpand.surah = qurans.surah AND \n" +
                "       corpusexpand.ayah = qurans.ayah  \n" +
                " ORDER BY corpusexpand.surah,\n" +
                "          corpusexpand.ayah";





        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<NewCorpusExpandWbwPOJO> verbWbw = (ArrayList<NewCorpusExpandWbwPOJO>) database.RawDao().getNewCorpusWbw(query);

        return verbWbw;
    }
    public ArrayList<CorpusExpandWbwPOJO> getCorpusWbwBySurah(int tid) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                "       CorpusExpand.surah,\n" +
                "       CorpusExpand.ayah,\n" +
                "       CorpusExpand.wordno,\n" +
                "       CorpusExpand.wordcount,\n" +
                "       Qurans.translation,\n" +

                "       CorpusExpand.araone,\n" +
                "       CorpusExpand.aratwo,\n" +
                "       CorpusExpand.arathree,\n" +
                "       CorpusExpand.arafour,\n" +
                "       CorpusExpand.arafive,\n" +
                "       CorpusExpand.tagone,\n" +
                "       CorpusExpand.tagtwo,\n" +
                "       CorpusExpand.tagthree,\n" +
                "       CorpusExpand.tagfour,\n" +
                "       CorpusExpand.tagfive,\n" +
                "       CorpusExpand.detailsone,\n" +
                "       CorpusExpand.detailstwo,\n" +
                "       CorpusExpand.detailsthree,\n" +
                "       CorpusExpand.detailsfour,\n" +
                "       CorpusExpand.detailsfive,\n" +
                "       wbw.en,\n" +
                "       wbw.bn,\n" +
                "       wbw.[in],\n" +
              "       wbw.ur,\n" +
                "       qurans.qurantext\n" +
                "  FROM corpusexpand,\n" +
                "       qurans,\n"+
                "       wbw\n" +
                " WHERE CorpusExpand.surah == \""
                + tid + "\""
                + "AND \n" +
                "       corpusexpand.surah = wbw.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno AND\n" +
                "       corpusexpand.surah = qurans.surah AND \n" +
                "       corpusexpand.ayah = qurans.ayah  \n" +
                " ORDER BY corpusexpand.surah,\n" +
                "          corpusexpand.ayah";





        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusExpandWbwPOJO> verbWbw = (ArrayList<CorpusExpandWbwPOJO>) database.RawDao().getCorpusWbw(query);

        return verbWbw;
    }

    public ArrayList<CorpusVerbWbwOccurance> newgetverboccurancebyall(String tid) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                "       CorpusExpand.surah,\n" +
                "       CorpusExpand.ayah,\n" +
                "       CorpusExpand.wordno,\n" +
                "       CorpusExpand.wordcount,\n" +
                "       Qurans.qurantext,\n" +
                "       Qurans.translation,\n" +
                "       CorpusExpand.araone,\n" +
                "       CorpusExpand.aratwo,\n" +
                "       CorpusExpand.arathree,\n" +
                "       CorpusExpand.arafour,\n" +
                "       CorpusExpand.arafive,\n" +
                "       CorpusExpand.tagone,\n" +
                "       CorpusExpand.tagtwo,\n" +
                "       CorpusExpand.tagthree,\n" +
                "       CorpusExpand.tagfour,\n" +
                "       CorpusExpand.tagfive,\n" +

                "       CorpusExpand.detailsone,\n" +
                "       CorpusExpand.detailstwo,\n" +
                "       CorpusExpand.detailsthree,\n" +
                "       CorpusExpand.detailsfour,\n" +
                "       CorpusExpand.detailsfive,\n" +
                "       verbcorpus.voice,\n" +
                "       verbcorpus.form ,\n" +
                "       verbcorpus.thulathibab,\n" +
                "       verbcorpus.tense,\n" +
                "       verbcorpus.gendernumber,\n" +
                "       verbcorpus.mood_kananumbers,\n" +
                "       verbcorpus.kana_mood,\n" +
                "       wbw.en\n" +
                "      FROM corpusexpand,verbcorpus,\n" +
                "       wbw,qurans\n" +
                " WHERE (corpusexpand.tagone = \"V\" OR \n" +
                "        corpusexpand.tagtwo = \"V\" OR \n" +
                "        corpusexpand.tagthree = \"V\" OR \n" +
                "        Corpusexpand.tagfour = \"V\" OR \n" +
                "        corpusexpand.tagfive = \"V\") AND \n" +
                "       CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive LIKE \""
                + tid + "\""
                + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = verbcorpus.chapterno AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = verbcorpus.verseno AND \n" +
                "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = verbcorpus.wordno and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order by corpusexpand.surah,corpusexpand.ayah";





        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusVerbWbwOccurance> verbWbw = (ArrayList<CorpusVerbWbwOccurance>) database.RawDao().getCombinedCorpusVerbWbw(query);

        return verbWbw;
    }
    public ArrayList<CorpusNounWbwOccurance> geAdjectives() {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                "       CorpusExpand.surah,\n" +
                "       CorpusExpand.ayah,\n" +
                "       CorpusExpand.wordno,\n" +
                "       CorpusExpand.wordcount,\n" +
                "       Qurans.qurantext,\n" +
                "       qurans.translation,\n" +
                "       CorpusExpand.araone,\n" +
                "       CorpusExpand.aratwo,\n" +
                "       CorpusExpand.arathree,\n" +
                "       CorpusExpand.arafour,\n" +
                "       CorpusExpand.arafive,\n" +
                "       CorpusExpand.tagone,\n" +
                "       CorpusExpand.tagtwo,\n" +
                "       CorpusExpand.tagthree,\n" +
                "       CorpusExpand.tagfour,\n" +
                "       CorpusExpand.tagfive,\n" +
                "       nouncorpus.tag,\n" +
                "       nouncorpus.propone,\n" +
                "       nouncorpus.proptwo,\n" +
                "       nouncorpus.form,\n" +
                "       nouncorpus.gendernumber,\n" +
                "       nouncorpus.type,\n" +
                "       nouncorpus.cases,\n" +
                "       wbw.en\n" +
                "  FROM corpusexpand,\n" +
                "       nouncorpus,\n" +
                "       wbw,qurans\n" +
                " WHERE (CorpusExpand.tagone = \"ADJ\" OR \n" +
                "        CorpusExpand.tagtwo = \"ADJ\" OR \n" +
                "        CorpusExpand.tagthree = \"ADJ\" OR \n" +
                "        CorpusExpand.tagfive = \"ADJ\" OR \n" +
                "        CorpusExpand.tagfour = \"ADJ\") AND \n" +
                "       \n" +
                "       corpusexpand.surah = wbw.surah AND \n" +
                "       corpusexpand.surah = nouncorpus.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND \n" +
                "       corpusexpand.ayah = nouncorpus.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno AND \n" +
                "       corpusexpand.wordno = nouncorpus.wordno and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order \n" +
                " ORDER BY corpusexpand.surah,\n" +
                "          corpusexpand.ayah";





        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusNounWbwOccurance> verbWbw = (ArrayList<CorpusNounWbwOccurance>) database.RawDao().getnounoccurance(query);

        return verbWbw;
    }
    public ArrayList<CorpusNounWbwOccurance> getnounoccuranceHarfNasbZarf(String tid) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                "       CorpusExpand.surah,\n" +
                "       CorpusExpand.ayah,\n" +
                "       CorpusExpand.wordno,\n" +
                "       CorpusExpand.wordcount,\n" +
                "         Qurans.qurantext,\n" +
                "       qurans.translation,\n" +
                "       CorpusExpand.araone,\n" +
                "       CorpusExpand.aratwo,\n" +
                "       CorpusExpand.arathree,\n" +
                "       CorpusExpand.arafour,\n" +
                "       CorpusExpand.arafive,\n" +
                "       CorpusExpand.tagone,\n" +
                "       CorpusExpand.tagtwo,\n" +
                "       CorpusExpand.tagthree,\n" +
                "       CorpusExpand.tagfour,\n" +
                "       CorpusExpand.tagfive,\n" +
                "       CorpusExpand.detailsone,\n" +
                "       CorpusExpand.detailstwo,\n" +
                "       CorpusExpand.detailsthree,\n" +
                "       CorpusExpand.detailsfour,\n" +
                "       CorpusExpand.detailsfive,\n" +
                "       nouncorpus.tag,\n" +
                "       nouncorpus.propone ,\n" +
                "       nouncorpus.proptwo,\n" +
                "       nouncorpus.form,\n" +
                "       nouncorpus.gendernumber,\n" +
                "       nouncorpus.type,\n" +
                "       nouncorpus.cases,\n" +
                "       wbw.en\n" +
                "      FROM corpusexpand,nouncorpus,\n" +
                "       wbw,qurans\n" +

                "    where   nouncorpus.tag = \""
                + tid + "\""
                + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = nouncorpus.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = nouncorpus.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = nouncorpus.wordno " +
                "and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah  order by corpusexpand.surah,corpusexpand.ayah";





        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusNounWbwOccurance> verbWbw = (ArrayList<CorpusNounWbwOccurance>) database.RawDao().getnounoccurance(query);

        return verbWbw;
    }


    public ArrayList<CorpusNounWbwOccurance> getnounoccurance(String tid) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                "       CorpusExpand.surah,\n" +
                "       CorpusExpand.ayah,\n" +
                "       CorpusExpand.wordno,\n" +
                "       CorpusExpand.wordcount,\n" +
                "       Qurans.qurantext,\n" +
                "       qurans.translation,\n" +
                "       CorpusExpand.araone,\n" +
                "       CorpusExpand.aratwo,\n" +
                "       CorpusExpand.arathree,\n" +
                "       CorpusExpand.arafour,\n" +
                "       CorpusExpand.arafive,\n" +
                "       CorpusExpand.tagone,\n" +
                "       CorpusExpand.tagtwo,\n" +
                "       CorpusExpand.tagthree,\n" +
                "       CorpusExpand.tagfour,\n" +
                "       CorpusExpand.tagfive,\n" +
                "       CorpusExpand.detailsone,\n" +
                "       CorpusExpand.detailstwo,\n" +
                "       CorpusExpand.detailsthree,\n" +
                "       CorpusExpand.detailsfour,\n" +
                "       CorpusExpand.detailsfive,\n" +
                "       nouncorpus.tag,\n" +
                "       nouncorpus.propone ,\n" +
                "       nouncorpus.proptwo,\n" +
                "       nouncorpus.form,\n" +
                "       nouncorpus.gendernumber,\n" +
                "       nouncorpus.type,\n" +
                "       nouncorpus.cases,\n" +
                    "       wbw.en\n" +
                "      FROM corpusexpand,nouncorpus,\n" +
                "       wbw,qurans\n" +

                "    where   CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive LIKE \""
                + tid + "\""
                + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = nouncorpus.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = nouncorpus.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = nouncorpus.wordno" +
                " and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order by corpusexpand.surah,corpusexpand.ayah";





        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusNounWbwOccurance> verbWbw = (ArrayList<CorpusNounWbwOccurance>) database.RawDao().getnounoccurance(query);

        return verbWbw;
    }
    public ArrayList<CorpusVerbWbwOccurance> getVerbOccuranceBreakVerses(String tid) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
              "       CorpusExpand.surah,\n" +
              "       CorpusExpand.ayah,\n" +
              "       CorpusExpand.wordno,\n" +
              "       CorpusExpand.wordcount,\n" +
              "       Qurans.qurantext,\n" +
              "       Qurans.translation,\n" +
              "       Qurans.ur_jalalayn,\n" +
              "       Qurans.en_jalalayn,\n" +

              "       CorpusExpand.araone,\n" +
              "       CorpusExpand.aratwo,\n" +
              "       CorpusExpand.arathree,\n" +
              "       CorpusExpand.arafour,\n" +
              "       CorpusExpand.arafive,\n" +
              "       CorpusExpand.tagone,\n" +
              "       CorpusExpand.tagtwo,\n" +
              "       CorpusExpand.tagthree,\n" +
              "       CorpusExpand.tagfour,\n" +
              "       CorpusExpand.tagfive,\n" +

              "       CorpusExpand.detailsone,\n" +
              "       CorpusExpand.detailstwo,\n" +
              "       CorpusExpand.detailsthree,\n" +
              "       CorpusExpand.detailsfour,\n" +
              "       CorpusExpand.detailsfive,\n" +
              "       verbcorpus.voice,\n" +
              "       verbcorpus.form ,\n" +
              "       verbcorpus.thulathibab,\n" +
              "       verbcorpus.tense,\n" +
              "       verbcorpus.gendernumber,\n" +
              "       verbcorpus.mood_kananumbers,\n" +
              "       verbcorpus.kana_mood,\n" +
              "       wbw.en\n" +
              "      FROM corpusexpand,verbcorpus,\n" +
              "       wbw,qurans\n" +
              " WHERE (corpusexpand.tagone = \"V\" OR \n" +
              "        corpusexpand.tagtwo = \"V\" OR \n" +
              "        corpusexpand.tagthree = \"V\" OR \n" +
              "        Corpusexpand.tagfour = \"V\" OR \n" +
              "        corpusexpand.tagfive = \"V\") AND \n" +
              "       corpusexpand.lemaraone||corpusexpand.lemaratwo||corpusexpand.lemrathree||corpusexpand.lemarafour||corpusexpand.lemarafive=  \""
              + tid + "\""
              + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = verbcorpus.chapterno AND \n" +
              "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = verbcorpus.verseno AND \n" +
              "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = verbcorpus.wordno and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order by corpusexpand.surah,corpusexpand.ayah";





        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusVerbWbwOccurance> verbWbw = (ArrayList<CorpusVerbWbwOccurance>) database.RawDao().getVerbOccuranceBreakVerses(query);

        return verbWbw;
    }
    public ArrayList<CorpusNounWbwOccurance> getNounOccuranceBreakVerses(String tid) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
              "       CorpusExpand.surah,\n" +
              "       CorpusExpand.ayah,\n" +
              "       CorpusExpand.wordno,\n" +
              "       CorpusExpand.wordcount,\n" +
              "       Qurans.qurantext,\n" +
              "       qurans.translation,\n" +
              "       Qurans.ur_jalalayn,\n" +
              "       Qurans.en_jalalayn,\n" +


              "       CorpusExpand.araone,\n" +
              "       CorpusExpand.aratwo,\n" +
              "       CorpusExpand.arathree,\n" +
              "       CorpusExpand.arafour,\n" +
              "       CorpusExpand.arafive,\n" +
              "       CorpusExpand.tagone,\n" +
              "       CorpusExpand.tagtwo,\n" +
              "       CorpusExpand.tagthree,\n" +
              "       CorpusExpand.tagfour,\n" +
              "       CorpusExpand.tagfive,\n" +
              "       CorpusExpand.detailsone,\n" +
              "       CorpusExpand.detailstwo,\n" +
              "       CorpusExpand.detailsthree,\n" +
              "       CorpusExpand.detailsfour,\n" +
              "       CorpusExpand.detailsfive,\n" +
              "       nouncorpus.tag,\n" +
              "       nouncorpus.propone ,\n" +
              "       nouncorpus.proptwo,\n" +
              "       nouncorpus.form,\n" +
              "       nouncorpus.gendernumber,\n" +
              "       nouncorpus.type,\n" +
              "       nouncorpus.cases,\n" +
              "       wbw.en\n" +
              "      FROM corpusexpand,nouncorpus,\n" +
              "       wbw,qurans\n" +

              "    where  corpusexpand.lemaraone||corpusexpand.lemaratwo||corpusexpand.lemrathree||corpusexpand.lemarafour||corpusexpand.lemarafive=  \""
              + tid + "\""
              + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = nouncorpus.surah AND \n" +
              "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = nouncorpus.ayah AND \n" +
              "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = nouncorpus.wordno" +
              " and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order by corpusexpand.surah,corpusexpand.ayah";





        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusNounWbwOccurance> verbWbw = (ArrayList<CorpusNounWbwOccurance>) database.RawDao().getnounoccurance(query);

        return verbWbw;
    }


    public ArrayList<CorpusNounWbwOccurance> getnounoccurancebysurahayah(int tid, int vid) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                "       CorpusExpand.surah,\n" +
                "       CorpusExpand.ayah,\n" +
                "       CorpusExpand.wordno,\n" +
                "       CorpusExpand.wordcount,\n" +
                "       Qurans.qurantext,\n" +
                "       qurans.translation,\n" +
              "       qurans.en_jalalayn,\n" +
              "       qurans.ur_jalalayn,\n" +

                "       CorpusExpand.araone,\n" +
                "       CorpusExpand.aratwo,\n" +
                "       CorpusExpand.arathree,\n" +
                "       CorpusExpand.arafour,\n" +
                "       CorpusExpand.arafive,\n" +
                "       CorpusExpand.tagone,\n" +
                "       CorpusExpand.tagtwo,\n" +
                "       CorpusExpand.tagthree,\n" +
                "       CorpusExpand.tagfour,\n" +
                "       CorpusExpand.tagfive,\n" +
                "       nouncorpus.tag,\n" +
                "       nouncorpus.propone ,\n" +
                "       nouncorpus.proptwo,\n" +
                "       nouncorpus.form,\n" +
                "       nouncorpus.gendernumber,\n" +
                "       nouncorpus.type,\n" +
                "       nouncorpus.cases,\n" +
                "       wbw.en\n" +
                "      FROM corpusexpand,nouncorpus,\n" +
                "       wbw,qurans\n" +

                "    where   CorpusExpand.surah = \""
                + tid + "\""
                + "and Corpusexpand.ayah=\""
                + vid     + "\""
                + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = nouncorpus.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = nouncorpus.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = nouncorpus.wordno " +
                "and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order by corpusexpand.surah,corpusexpand.ayah";





        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusNounWbwOccurance> verbWbw = (ArrayList<CorpusNounWbwOccurance>) database.RawDao().getnounoccurancebysurahayah(query);

        return verbWbw;
    }


    public ArrayList<CorpusNounWbwOccurance> getnounoccurancebysurah(String tid, int vid) {


        String sqlverb = "SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                "       CorpusExpand.surah,\n" +
                "       CorpusExpand.ayah,\n" +
                "       CorpusExpand.wordno,\n" +
                "       CorpusExpand.wordcount,\n" +
                "       Qurans.qurantext,\n" +
                "       qurans.translation,\n" +
                "       CorpusExpand.araone,\n" +
                "       CorpusExpand.aratwo,\n" +
                "       CorpusExpand.arathree,\n" +
                "       CorpusExpand.arafour,\n" +
                "       CorpusExpand.arafive,\n" +
                "       CorpusExpand.tagone,\n" +
                "       CorpusExpand.tagtwo,\n" +
                "       CorpusExpand.tagthree,\n" +
                "       CorpusExpand.tagfour,\n" +
                "       CorpusExpand.tagfive,\n" +
                "       nouncorpus.tag,\n" +
                "       nouncorpus.propone ,\n" +
                "       nouncorpus.proptwo,\n" +
                "       nouncorpus.form,\n" +
                "       nouncorpus.gendernumber,\n" +
                "       nouncorpus.type,\n" +
                "       nouncorpus.cases,\n" +
                "       wbw.en\n" +
                "      FROM corpusexpand,nouncorpus,\n" +
                "       wbw,qurans\n" +

                "    where   nouncorpus.tag= \""
                + tid + "\""
                + "and Corpusexpand.surah=\""
                + vid     + "\""
                + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = nouncorpus.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = nouncorpus.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = nouncorpus.wordno " +
                "and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order by corpusexpand.surah,corpusexpand.ayah";





        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusNounWbwOccurance> verbWbw = (ArrayList<CorpusNounWbwOccurance>) database.RawDao().getnounoccurancebysurahayah(query);

        return verbWbw;
    }
    public ArrayList<CorpusNounWbwGrouping> GetNounGrouping(String tid) {


        String sqlverb = "SELECT \"N\",CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                "       corpusexpand.surah,\n" +
                "       corpusexpand.ayah,\n" +
                "       corpusexpand.wordno,\n" +
                "       corpusexpand.wordcount,\n" +
                "Corpusexpand.lemaraone || lemaratwo || lemrathree || lemarafour || lemarafive as lemma,\n" +
                "       count(Corpusexpand.lemaraone || lemaratwo || lemrathree || lemarafour || lemarafive) AS lemmacount,\n" +
                "       CorpusExpand.araone || CorpusExpand.aratwo || CorpusExpand.arathree || CorpusExpand.arafour || CorpusExpand.arafive as arabicword,\n" +
                "       wbw.en\n" +
                "  FROM corpusexpand,\n" +
                "       nouncorpus,\n" +
                "       wbw\n" +
                " WHERE CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive LIKE \""
                + tid + "\""
               + " AND corpusexpand.surah = wbw.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno AND \n" +
                "       corpusexpand.surah = nouncorpus.surah AND \n" +
                "       corpusexpand.ayah = nouncorpus.ayah AND \n" +
                "       corpusexpand.wordno = nouncorpus.wordno\n" +
                " GROUP BY Corpusexpand.lemaraone || lemaratwo || lemrathree || lemarafour || lemarafive;";



        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusNounWbwGrouping> verbWbw = (ArrayList<CorpusNounWbwGrouping>) database.RawDao().getNounGroups(query);

        return verbWbw;
    }

    public ArrayList<CorpusNounWbwGrouping> GetVerbGrouping(String tid) {


        String sqlverb = "SELECT \"V\", CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                "       corpusexpand.surah,\n" +
                "       corpusexpand.ayah,\n" +
                "       corpusexpand.wordno,\n" +
                "       corpusexpand.wordcount,\n" +
                "Corpusexpand.lemaraone || lemaratwo || lemrathree || lemarafour || lemarafive as lemma,\n" +
                "       count(Corpusexpand.lemaraone || lemaratwo || lemrathree || lemarafour || lemarafive) AS lemmacount,\n" +
                "       CorpusExpand.araone || CorpusExpand.aratwo || CorpusExpand.arathree || CorpusExpand.arafour || CorpusExpand.arafive  as arabicword,\n" +
                "       wbw.en\n" +
                "  FROM corpusexpand,\n" +
                "       verbcorpus,\n" +
                "       wbw\n" +
                " WHERE CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive LIKE \""
                + tid + "\""

             +   "  AND corpusexpand.surah = wbw.surah AND \n" +
                "       corpusexpand.ayah = wbw.ayah AND \n" +
                "       corpusexpand.wordno = wbw.wordno AND \n" +
                "       corpusexpand.surah = verbcorpus.chapterno AND \n" +
                "       corpusexpand.ayah = verbcorpus.verseno AND \n" +
                "       corpusexpand.wordno = verbcorpus.wordno\n" +
                " GROUP BY Corpusexpand.lemaraone || lemaratwo || lemrathree || lemarafour || lemarafive";



        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusNounWbwGrouping> verbWbw = (ArrayList<CorpusNounWbwGrouping>) database.RawDao().getNounGroups(query);

        return verbWbw;
    }









    public ArrayList<NewNasbEntity> getHarfNasbIndexesnew(int id) {
        ArrayList<NewNasbEntity> harfNasbIndices = (ArrayList<NewNasbEntity>) database.NewNasbDao().getHarfNasbIndices(id);
        return harfNasbIndices;

    }

    public ArrayList<NewNasbEntity> getHarfNasbIndAllSnew( ) {
        ArrayList<NewNasbEntity> harfNasbIndices = (ArrayList<NewNasbEntity>) database.NewNasbDao().getHarfNasbIndAll();
        return harfNasbIndices;

    }

    public ArrayList<NewNasbEntity> getHarfNasbIndSurahAyahSnew(int id,int aid) {
        ArrayList<NewNasbEntity> harfNasbIndices = (ArrayList<NewNasbEntity>) database.NewNasbDao().getHarfNasbIndicesSurahAyah(id,aid);
        return harfNasbIndices;

    }




    public ArrayList<SifaPOJO> getMousufSIfaSurah(int sid) {

        String sqlverb="select sifa.surah,sifa.ayah,sifa.wordno,sifa.startindex,sifa.endindex,sifa.phrasetype,sifa.ismousufconnected,\n" +
                "sifa.comment\n" +
                ",qurans.qurantext,qurans.translation from sifa,qurans where sifa.surah=qurans.surah and \n" +
                "sifa.ayah=qurans.ayah";

        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<SifaPOJO> sifa = (ArrayList<SifaPOJO>) database.RawDao().getMousufSifa(query);

        return sifa;
    }
    public ArrayList<MudhafPOJO> getMudhaf() {

        String sqlverb="select mudhaf.surah,mudhaf.ayah,mudhaf.wordno,mudhaf.startindex,mudhaf.endindex,mudhaf.status,  \n" +
                "mudhaf.comment\n" +
                ",qurans.qurantext,qurans.translation from mudhaf,qurans where mudhaf.surah=qurans.surah and \n" +
                "mudhaf.ayah=qurans.ayah";

        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<MudhafPOJO> mudhaf = (ArrayList<MudhafPOJO>) database.RawDao().getMudhaf(query);

        return mudhaf;
    }


    public ArrayList<SifaPOJO> getMousufSIfa() {

        String sqlverb="select sifa.surah,sifa.ayah,sifa.wordno,sifa.startindex,sifa.endindex,sifa.phrasetype,sifa.ismousufconnected,\n" +
                "sifa.comment\n" +
                ",qurans.qurantext,qurans.translation from sifa,qurans where sifa.surah=qurans.surah and \n" +
                "sifa.ayah=qurans.ayah";

        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<SifaPOJO> sifa = (ArrayList<SifaPOJO>) database.RawDao().getMousufSifa(query);

        return sifa;
    }
    public ArrayList<ShartPOJO> getSharts() {

        String sqlverb="select shart.surah,shart.ayah,shart.indexstart,shart.indexend,shart.shartindexstart,shart.shartindexend,\n" +
                "shart.jawabshartindexstart,shart.jawabshartindexend,shart.sharttype,shart.comment\n" +
                ",qurans.qurantext,qurans.translation from shart,qurans where shart.surah=qurans.surah and \n" +
                "shart.ayah=qurans.ayah  order by shart.surah,shart.ayah";
//     "shart.ayah=qurans.ayah and shart.sharttype=\"laula\" order by shart.surah";
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<ShartPOJO> sifa = (ArrayList<ShartPOJO>) database.RawDao().getShart(query);

        return sifa;
    }

    public ArrayList<NasbPOJO> getNasabs() {

        String sqlverb="select nasb.surah,nasb.ayah,nasb.indexstart,nasb.indexend,nasb.ismstart,nasb.ismend,\n" +
                "nasb.khabarstart,nasb.khabarend,nasb.mahdoof,nasb.comment\n" +
                ",qurans.qurantext,qurans.translation from nasb,qurans where nasb.surah=qurans.surah and \n" +
                "nasb.ayah=qurans.ayah  order by nasb.surah,nasb.ayah";
//     "shart.ayah=qurans.ayah and shart.sharttype=\"laula\" order by shart.surah";
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<NasbPOJO> sifa = (ArrayList<NasbPOJO>) database.RawDao().getNasb(query);

        return sifa;
    }



    public ArrayList<KanaPOJO> getKanaPojo() {

        String sqlverb="select newkana.surah,newkana.ayah,newkana.indexstart,newkana.indexend,newkana.khabarstart,newkana.khabarend,\n" +
                "newkana.ismkanastart,newkana.ismkanaend,newkana.mahdoof,newkana.comment\n" +
                ",qurans.qurantext,qurans.translation from newkana,qurans where newkana.surah=qurans.surah and \n" +
                "newkana.ayah=qurans.ayah    order by newkana.surah,newkana.ayah ";
//     "shart.ayah=qurans.ayah and shart.sharttype=\"laula\" order by shart.surah";
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<KanaPOJO> kana = (ArrayList<KanaPOJO>) database.RawDao().getKana(query);

        return kana;
    }



    public ArrayList<SifaEntityPojo> GetSIfaindexes() {


        String sqlverb = "select sifa.surah,sifa.ayah,sifa.wordno,sifa.startindex,sifa.endindex, qurans.qurantext,translationData.translation\n" +
                "from sifa,qurans,translationData where sifa.surah=qurans.surah and sifa.ayah=qurans.ayah and \n" +
                "sifa.ayah=translationdata.verse_id and translation_id=\"en_sahih\" order by sifa.surah,sifa.ayah,sifa.wordno";



        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<SifaEntityPojo> sifa = (ArrayList<SifaEntityPojo>) database.RawDao().getSifaIndexes(query);

        return sifa;
    }


    public ArrayList<SifaEntity> getSifabySurah(int id) {
        ArrayList<SifaEntity> sifasurah = (ArrayList<SifaEntity>) database.SifaDao().getSifaindexesBySurah(id);
        return sifasurah;

    }
    public ArrayList<SifaEntity> getSifabySurahAyah(int id,int aid) {
        ArrayList<SifaEntity> sifasurah = (ArrayList<SifaEntity>) database.SifaDao().getSifaindexesBySurahAyah(id,aid);
        return sifasurah;

    }


    public ArrayList<SifaEntity> getSifabySurahAll() {
        ArrayList<SifaEntity> sifasurah = (ArrayList<SifaEntity>) database.SifaDao().getSifaindexesAll();
        return sifasurah;

    }



    public ArrayList<NewShartEntity> getShartSurahNew(int id) {
        ArrayList<NewShartEntity> shartsurah = (ArrayList<NewShartEntity>) database.NewShartDAO().getShartBySurah(id);
        return shartsurah;

    }

    public ArrayList<lughat> getRootDictionary(String id) {
        ArrayList<lughat> shartsurah = (ArrayList<lughat>) database.LughatDao().getRootWordDictionary(id.trim());
        return shartsurah;

    }

    public ArrayList<lanelexicon> getLanesDifinition(String id) {
        ArrayList<lanelexicon> lanelexicon = (ArrayList<lanelexicon>) database.LaneDao().getLanesDefinition(id.trim());
        return lanelexicon;

    }

    public ArrayList<hanslexicon> getHansDifinition(String id) {
        ArrayList<hanslexicon> hanslexicon = (ArrayList<hanslexicon>) database.HansDao().getHansDefinition(id.trim());
        return hanslexicon;

    }
    public ArrayList<lughat> getArabicWord(String id) {
        ArrayList<lughat> shartsurah = (ArrayList<lughat>) database.LughatDao().getArabicWord(id);
        return shartsurah;

    }
    public ArrayList<qurandictionary> getQuranDictionary( ) {
        ArrayList<qurandictionary> shartsurah = (ArrayList<qurandictionary>) database.qurandictionaryDao().getDictionary();
        return shartsurah;

    }




    public ArrayList<NewKanaEntity> getKananew(int id ) {
        ArrayList<NewKanaEntity> shartsurah = (ArrayList<NewKanaEntity>) database.NewKanaDao().getkanabysurah(id);
        return shartsurah;

    }
    public ArrayList<NewKanaEntity> getKanaSurahAyahnew(int id,int aid ) {
        ArrayList<NewKanaEntity> shartsurah = (ArrayList<NewKanaEntity>) database.NewKanaDao().getkanabysurahAyah(id,aid);
        return shartsurah;

    }

    public ArrayList<NewShartEntity> getShartSurahAyahNew(int id,int ayah) {
        ArrayList<NewShartEntity> shartsurah = (ArrayList<NewShartEntity>) database.NewShartDAO().getShartBySurahAyah(id,ayah);
        return shartsurah;

    }

    public ArrayList<NewShartEntity> getShartAllNew( ) {
        ArrayList<NewShartEntity> shartsurah = (ArrayList<NewShartEntity>) database.NewShartDAO().getShartAll();
        return shartsurah;

    }

    public ArrayList<wbwentity> getwbwQuranBySurahAyahWord(int id,int aid,int wordno){

        ArrayList<wbwentity> items = (ArrayList<wbwentity>) database.wbwDao().getwbwQuranBySurahAyahWord(id,aid,wordno);
        return items;


    }

    public ArrayList<wbwentity> getwbwQuranBySurahAyah(int id,int aid){

        ArrayList<wbwentity> items = (ArrayList<wbwentity>) database.wbwDao().getwbwQuranBySurahAyah(id,aid);
        return items;


    }


    public ArrayList<wbwentity> getwbwQuran(int id) {
        Log.d(TAG, "getVerses: started");
        ArrayList<wbwentity> getwbwQuranList = (ArrayList<wbwentity>) database.wbwDao().getwbwQuran(id);
        return getwbwQuranList;
    }

    public ArrayList<wbwentity> getwbwQuranbypart(int chapterno) {

        ArrayList<wbwentity> getwbwQuranJuzList = (ArrayList<wbwentity>) database.wbwDao().getwbwQuranByjuz(chapterno);
        return getwbwQuranJuzList;
    }


    public ArrayList<wbwentity> getwbwQuranbTranslation(int sid,int aid,int firstwordindex, int lastwordindex) {

        ArrayList<wbwentity> ayah = (ArrayList<wbwentity>) database.wbwDao().getwbwQuranbTranslation(sid,aid,firstwordindex,lastwordindex);

        return ayah;
    }



    public ArrayList<NounCorpusBreakup> getNounBreakup(String tid) {


        String sqlverb = "SELECT count(root_a) as count,surah,ayah, lemma_a,form,araword,tag,propone,proptwo FROM nouncorpus where root_a =\""
              + tid + "\""
              + " group by lemma_a,root_a,tag,propone,proptwo order by surah,ayah";


        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<NounCorpusBreakup> verbOccurances = (ArrayList<NounCorpusBreakup>) database.RawDao().getNounBreakup(query);

        return verbOccurances;
    }


    public ArrayList<VerbCorpusBreakup> getVerbBreakUp(String tid) {


        String sqlverb = "SELECT count(root_a) as count,root_a,lemma_a,form,thulathibab,tense,voice FROM verbcorpus where root_a =\""
              + tid + "\""
              + " group by root_a,form order by chapterno,verseno";


        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<VerbCorpusBreakup> verbOccurances = (ArrayList<VerbCorpusBreakup>) database.RawDao().getVerbBreakup(query);

        return verbOccurances;
    }
    public ArrayList<CorpusNounWbwOccurance> getNounBreakupVerses(String tid) {


        String sqlverb = "select a.surah,a.ayah ,b.qurantext,b.translation from corpusexpand  a,qurans b where\n" +
              "    a.lemaraone||a.lemaratwo||a.lemrathree||a.lemarafour||a.lemarafive =\""
              + tid + "\""
              + " and a.surah=b.surah and a.ayah=b.ayah order by chapterno,verseno";


        SimpleSQLiteQuery query = new SimpleSQLiteQuery(sqlverb);
        //  List<Book> result = booksDao.getBooks(query);
        ArrayList<CorpusNounWbwOccurance> verbOccurances;
        verbOccurances = (ArrayList<CorpusNounWbwOccurance>) database.RawDao().getnounoccurance(query);

        return verbOccurances;
    }






}







