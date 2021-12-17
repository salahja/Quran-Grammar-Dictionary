package com.example.utility;

import android.content.Context;

import com.example.mushafconsolidated.Entities.BuckWater;
import com.example.mushafconsolidated.Entities.VerbCorpus;
import com.example.mushafconsolidated.settings.DatabaseAccess;

import java.util.ArrayList;
import java.util.HashMap;



public class BuckWaterUtility {
    public String verbroot;
    HashMap<Integer, Character> hashmap = new HashMap<Integer, Character>();
    boolean first, second, third;
    boolean lafeef;
    private HashMap<Integer, Character> check = new HashMap<Integer, Character>();
    private HashMap<String, String> weakness = new HashMap<>();
    private HashMap<Character, Character> buckwaterToArabic;
    private HashMap<Character, Character> arabicToBuckwater;
   Context context;

    public BuckWaterUtility(Context context) {
        this.context = context;
    }

    boolean lafeefmaqroonajwaf;


    public boolean isLafeefmaqroonajwaf() {
        return lafeefmaqroonajwaf;
    }

    public void setLafeefmaqroonajwaf(boolean lafeefmaqroonajwaf) {
        this.lafeefmaqroonajwaf = lafeefmaqroonajwaf;
    }

    public void setLafeef(boolean lafeef) {
        this.lafeef = lafeef;
    }


    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isSecond() {
        return second;
    }

    public void setSecond(boolean second) {
        this.second = second;
    }



    public void gatherKeys() {
        final ArrayList<BuckWater> entitiy = new DatabaseAccess().getBuckwater();

        arabicToBuckwater = new HashMap<Character, Character>();
        buckwaterToArabic = new HashMap<Character, Character>();



        for (BuckWater buckWater : entitiy) {
            final String arabic = buckWater.getArabic();
            final String ascii = buckWater.getAscii();
            arabicToBuckwater.put(arabic.charAt(0), ascii.charAt(0));
            buckwaterToArabic.put(ascii.charAt(0), arabic.charAt(0));
        }

    }

    public String arabic2buck1(String arabicword, Context context) {
        gatherKeys();
        StringBuffer strbf = new StringBuffer();
        char[] charArr = arabicword.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            char toReplace = charArr[i];
            if (arabicToBuckwater.containsKey(toReplace)) {
                strbf.append(arabicToBuckwater.get(toReplace).toString());
            } else {
                strbf.append(toReplace);
            }
        }
        System.out.println(" arabic to buck:" + strbf.toString());
        return strbf.toString();

    }

    public ArrayList<String> buck12Arabic(ArrayList<VerbCorpus> buck12Arabic) {

        gatherKeys();
        ArrayList<String> Arabic = new ArrayList<>();

        ArrayList<ArrayList> Arabicbuck = new ArrayList<>();


        for (VerbCorpus s : buck12Arabic) {
            StringBuilder root = new StringBuilder();
            StringBuilder lemma = new StringBuilder();
            String s1 = s.getRoot_b();
            String s2 = s.getLemma_b();
            char[] charArr = s1.toCharArray();
            char[] charArrlem = s2.toCharArray();
            for (int i = 0; i < charArr.length; i++) {
                char toReplace = charArr[i];
                if (buckwaterToArabic.containsKey(toReplace)) {
                    root.append(buckwaterToArabic.get(toReplace).toString());

                } else {
                    root.append(toReplace);

                }
            }
         //   Arabic.add(root.toString());
            for (int i = 0; i < charArrlem.length; i++) {
                char toReplaces = charArrlem[i];
                if (buckwaterToArabic.containsKey(toReplaces)) {
                  ;
                    lemma.append(buckwaterToArabic.get(toReplaces).toString());
                } else {

                    lemma.append(toReplaces);
                }
            }
            if(lemma.length()==0){
                lemma.append("none");
            }
            if(root.length()==0){
                root.append("none");
            }
          StringBuilder sb=new StringBuilder();
            sb.append(root);
            sb.append(",");
            sb.append(lemma);
            Arabic.add(sb.toString());
        }

//
        // System.out.println("buck to arabic:"+strbf.toString());
        return Arabic;

    }

    public ArrayList<String> buck12Arabic(ArrayList<String> buck12Arabic, Context context) {
        gatherKeys();

        ArrayList<String> Arabic = new ArrayList<>();




        for (String s : buck12Arabic) {
            StringBuilder strbf = new StringBuilder();
            char[] charArr = s.toCharArray();
            for (int i = 0; i < charArr.length; i++) {
                char toReplace = charArr[i];
                if (buckwaterToArabic.containsKey(toReplace)) {
                    strbf.append(buckwaterToArabic.get(toReplace).toString());
                } else {
                    strbf.append(toReplace);
                }
            }
            Arabic.add(strbf.toString());
        }

//
        // System.out.println("buck to arabic:"+strbf.toString());
        return Arabic;

    }

}


