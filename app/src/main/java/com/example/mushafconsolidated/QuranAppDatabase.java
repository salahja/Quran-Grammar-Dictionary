package com.example.mushafconsolidated;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mushafconsolidated.DAO.AnaQuranChapterDao;
import com.example.mushafconsolidated.DAO.BookMarkDao;
import com.example.mushafconsolidated.DAO.CorpusExpandedDao;
import com.example.mushafconsolidated.DAO.HansDao;
import com.example.mushafconsolidated.DAO.LaneDao;
import com.example.mushafconsolidated.DAO.LughatDao;
import com.example.mushafconsolidated.DAO.NewKanaDao;
import com.example.mushafconsolidated.DAO.NewMudhafDao;
import com.example.mushafconsolidated.DAO.NewNasbDao;
import com.example.mushafconsolidated.DAO.NewShartDAO;
import com.example.mushafconsolidated.DAO.NounCorpusDao;
import com.example.mushafconsolidated.DAO.QuranDao;
import com.example.mushafconsolidated.DAO.RawDao;
import com.example.mushafconsolidated.DAO.SifaDao;
import com.example.mushafconsolidated.DAO.VerbCorpusDao;
import com.example.mushafconsolidated.DAO.grammarRulesDao;
import com.example.mushafconsolidated.DAO.qurandictionaryDao;
import com.example.mushafconsolidated.DAO.wbwDao;
import com.example.mushafconsolidated.Entities.BookMarks;
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity;
import com.example.mushafconsolidated.Entities.CorpusEntity;
import com.example.mushafconsolidated.Entities.GrammarRules;
import com.example.mushafconsolidated.Entities.NewKanaEntity;
import com.example.mushafconsolidated.Entities.NewMudhafEntity;
import com.example.mushafconsolidated.Entities.NewNasbEntity;
import com.example.mushafconsolidated.Entities.NewShartEntity;
import com.example.mushafconsolidated.Entities.NounCorpus;
import com.example.mushafconsolidated.Entities.QuranEntity;
import com.example.mushafconsolidated.Entities.SifaEntity;
import com.example.mushafconsolidated.Entities.VerbCorpus;
import com.example.mushafconsolidated.Entities.hanslexicon;
import com.example.mushafconsolidated.Entities.lanelexicon;
import com.example.mushafconsolidated.Entities.lughat;
import com.example.mushafconsolidated.Entities.qurandictionary;
import com.example.mushafconsolidated.Entities.wbwentity;

import java.io.File;



//@Database(entities= {VerseEntit.class,ErabEntity.class,ChaptersAnaEntity.class},version= 1)
@Database(entities = {GrammarRules.class,hanslexicon.class, qurandictionary.class,lanelexicon.class, lughat.class,NewNasbEntity.class,NewShartEntity.class, NewKanaEntity.class, NewMudhafEntity.class,   SifaEntity.class,   wbwentity.class,NounCorpus.class,VerbCorpus.class,QuranEntity.class, CorpusEntity.class,BookMarks.class,      ChaptersAnaEntity.class }, version = 1)
public abstract class QuranAppDatabase extends RoomDatabase {

    public static QuranAppDatabase quranAppDatabaseInstance;
    public static QuranAppDatabase quranAppDatabaseInstanceasset;
    //  public static Builder<QuranAppDatabase> quranAppDatabaseInstance;
    private static final RoomDatabase.Callback initialCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //  new InitialAsyncTask(quranAppDatabaseInstance).execute();
        }
    };

    public static synchronized QuranAppDatabase getInstance() {
        return getInstance();
    }

    public static synchronized QuranAppDatabase getInstance(Context context) {
        if (null == quranAppDatabaseInstance) {
            File mainDatabase = new File(Environment.getExternalStorageDirectory().
                    getAbsolutePath() + "/Mushafapplication"+ "/qurangrammar.db");

 quranAppDatabaseInstanceasset = Room.databaseBuilder(context, QuranAppDatabase.class, "qurangrammar.db")
                  .createFromAsset("databases/qurangrammar.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(initialCallBack)
                    .allowMainThreadQueries()
              //   .openHelperFactory(factory)
                     .build();











            quranAppDatabaseInstance=     Room.databaseBuilder(context, QuranAppDatabase.class, "qurangrammar.db")

                    .createFromFile(mainDatabase)
                    //          .fallbackToDestructiveMigration()
                    .addCallback(initialCallBack)
                    .allowMainThreadQueries()
                    .build();










        }
   //     return     quranAppDatabaseInstance;
    return quranAppDatabaseInstanceasset;
    }


    public abstract AnaQuranChapterDao AnaQuranChapterDao();













    // public abstract WordbywordPojoDao WordbywordPojoDao();


    public abstract BookMarkDao BookMarkDao();



    public abstract RawDao RawDao();
    public abstract CorpusExpandedDao CorpusExpandedDao();

    public abstract QuranDao QuranDao();
    public abstract VerbCorpusDao VerbCorpusDao();
    public abstract NounCorpusDao NounCorpusDao();
    public abstract wbwDao wbwDao();




    ;
    public abstract SifaDao SifaDao();
    //  public abstract ShartDAO ShartDAO();
    public abstract NewShartDAO NewShartDAO();
    //  public abstract KanaDao KanaDao();

    //public abstract NasbDao NasbDao();
    public abstract NewNasbDao NewNasbDao();

    public abstract NewMudhafDao NewMudhafDao();
    public abstract NewKanaDao NewKanaDao();
    public abstract LughatDao LughatDao();
    public abstract LaneDao LaneDao();
    public abstract HansDao HansDao();
    public abstract qurandictionaryDao qurandictionaryDao();
    public abstract grammarRulesDao grammarRulesDao();

}
