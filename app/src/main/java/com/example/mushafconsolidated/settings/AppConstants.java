package com.example.mushafconsolidated.settings;


/**
 * Interface for the app constants
 */
public interface AppConstants {


    interface General {
        String SEARCH_TEXT = "SearchText",
                TRANSLITERATION="en_transliteration",
                 IRAB="ar_irab",
        JALALAYN="en_jalalayn",
                PAGE_NUMBER = "PageNumber",
                PAGE = "Page",
                BOOK_MARK = "bookmark",
                BOOK_MARK_TAFSEER = "bookmark_tafseer",
                AYA_ID = "aya_id",
                SURA_ID = "sura_id";
    }

    /**
     * File and folder paths constants
     */
    interface Path {
        String VERB_DATABASE_PATH = "/Mushafapplication/Conjugator.db",
                TAFSEER_DATABASE_PATH = "/quran_fekra_computers/tafaseer",
                TAFSEER_LINK = "http://quran.islam-db.com/data/tafaseer/tafseer";
        ;
    }


    interface Paths {
        String MAIN_DATABASE_PATH = "/Mushafapplication/newquran.db",
                TAFSEER_DATABASE_PATH = "/quran_fekra_computers/tafaseer",
                TAFSEER_LINK = "http://quran.islam-db.com/data/tafaseer/tafseer";
        ;
    }










}
