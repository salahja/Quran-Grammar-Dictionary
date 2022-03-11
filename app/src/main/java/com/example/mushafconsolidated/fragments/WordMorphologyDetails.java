package com.example.mushafconsolidated.fragments;

import static com.example.utility.CorpusConstants.verbfeaturesenglisharabic.ACT;
import static com.example.utility.CorpusConstants.verbfeaturesenglisharabic.IMPF;
import static com.example.utility.CorpusConstants.verbfeaturesenglisharabic.IMPV;
import static com.example.utility.CorpusConstants.verbfeaturesenglisharabic.JUS;
import static com.example.utility.CorpusConstants.verbfeaturesenglisharabic.PASS;
import static com.example.utility.CorpusConstants.verbfeaturesenglisharabic.PERF;
import static com.example.utility.CorpusConstants.verbfeaturesenglisharabic.SUBJ;

import android.text.SpannableString;

import androidx.annotation.NonNull;

import com.example.mushafconsolidated.model.CorpusWbwWord;
import com.example.utility.CorpusConstants;
import com.example.utility.CorpusUtilityorig;

public class WordMorphologyDetails extends QuranMorphologyDetails {


    private CorpusWbwWord word;

    public WordMorphologyDetails(CorpusWbwWord word) {
        this.word = word;

    }

    public WordMorphologyDetails() {

    }


    public SpannableString getWorkBreakDown() {
        //   SpannableStringBuilder tagspannable = null;
        SpannableString tagspannable = null;
        int wordcount = word.getWordcount();
        if (wordcount == 1) {

            String tagone = word.getTagone();

            String expandTagsone = expandTags(tagone);
            if (tagone.equals("V")) {

                expandTagsone = getVerbDetails(expandTagsone, word.getDetailsone());
            }


            tagspannable = new SpannableString(expandTagsone);
            //   tagspannable.setSpan(new ForegroundColorSpan(CYAN), 0, expandTagsone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            tagspannable = CorpusUtilityorig.NewSetWordSpanTag(word.getTagone(), word.getTagtwo(), word.getTagthree(),
                    word.getTagfour(), word.getTagfive(), " ", " ", "", "", expandTagsone);
        } else if (wordcount == 2) {


            String tagtwo = word.getTagtwo();
            String tagone = word.getTagone();
            String expandTagsone = expandTags(tagone);
            String expandTagstwo = expandTags(tagtwo);
            if (tagone.equals("V")) {

                expandTagsone = getVerbDetails(expandTagsone, word.getDetailsone());
            } else if (tagtwo.equals("V")) {

                expandTagstwo = getVerbDetails(expandTagstwo, word.getDetailstwo());
            }

            //   tagspannable = CorpusUtilityorig.SetWordSpanNew(word.getTagone(), word.getTagtwo(), word.getTagthree(),
            //   word.getTagfour(), word.getTagfive(), expandTagsone, expandTagstwo, "", "", "");

            tagspannable = CorpusUtilityorig.NewSetWordSpanTag(word.getTagone(), word.getTagtwo(), word.getTagthree(),
                    word.getTagfour(), word.getTagfive(), " ", " ", "", expandTagstwo, expandTagsone);


        } else if (wordcount == 3) {
            StringBuilder sb = new StringBuilder();


            String expandTagsone = expandTags(word.getTagone());
            String expandTagstwo = expandTags(word.getTagtwo());
            String expandTagsthree = expandTags(word.getTagthree());

            if (word.getTagone().equals("V")) {
                expandTagsone = getVerbDetails(expandTagsone, word.getDetailsone());

            } else if (word.getTagtwo().equals("V")) {
                expandTagstwo = getVerbDetails(expandTagstwo, word.getDetailstwo());

            }
            if (word.getTagthree().equals("V")) {
                expandTagsthree = getVerbDetails(expandTagsthree, word.getDetailsthree());

            }


            sb.append(word.getTagthree());
            sb.append("|");
            sb.append(word.getTagtwo());
            sb.append("|");
            sb.append(word.getTagone());
            tagspannable = CorpusUtilityorig.NewSetWordSpanTag(word.getTagone(), word.getTagtwo(), word.getTagthree(),
                    word.getTagfour(), word.getTagfive(), " ", " ", expandTagsthree, expandTagstwo, expandTagsone);


        } else if (wordcount == 4) {
            StringBuilder sb = new StringBuilder();


            String expandTagsone = expandTags(word.getTagone());
            String expandTagstwo = expandTags(word.getTagtwo());
            String expandTagsthree = expandTags(word.getTagthree());
            String expandTagsfour = expandTags(word.getTagfour());

            if (word.getTagone().equals("V")) {
                expandTagsone = getVerbDetails(expandTagsone, word.getDetailsone());

            } else if (word.getTagtwo().equals("V")) {
                expandTagstwo = getVerbDetails(expandTagstwo, word.getDetailstwo());

            } else if (word.getTagthree().equals("V")) {
                expandTagsthree = getVerbDetails(expandTagsthree, word.getDetailsthree());

            } else if (word.getTagfour().equals("V")) {
                expandTagsfour = getVerbDetails(expandTagsfour, word.getDetailsfour());

            }


            sb.append(word.getTagfour());
            sb.append("|");
            sb.append(word.getTagthree());
            sb.append("|");
            sb.append(word.getTagtwo());
            sb.append("|");
            sb.append(word.getTagone());
            tagspannable = CorpusUtilityorig.NewSetWordSpanTag(word.getTagone(), word.getTagtwo(), word.getTagthree(),
                    word.getTagfour(), word.getTagfive(), " ", expandTagsfour, expandTagsthree, expandTagstwo, expandTagsone);


        } else if (wordcount == 5) {

            StringBuilder sb = new StringBuilder();


            String expandTagsone = expandTags(word.getTagone());
            String expandTagstwo = expandTags(word.getTagtwo());
            String expandTagsthree = expandTags(word.getTagthree());
            String expandTagsfour = expandTags(word.getTagfour());
            String expandTagsfive = expandTags(word.getTagfive());
            if (word.getTagone().equals("V")) {
                expandTagsone = getVerbDetails(expandTagsone, word.getDetailsone());

            } else if (word.getTagtwo().equals("V")) {
                expandTagstwo = getVerbDetails(expandTagstwo, word.getDetailstwo());

            } else if (word.getTagthree().equals("V")) {
                expandTagsthree = getVerbDetails(expandTagsthree, word.getDetailsthree());

            } else if (word.getTagfour().equals("V")) {
                expandTagsfour = getVerbDetails(expandTagsfour, word.getDetailsfour());

            } else if (word.getTagfive().equals("V")) {
                expandTagsfive = getVerbDetails(expandTagsfive, word.getDetailsfour());

            }


            sb.append(word.getTagfour());
            sb.append("|");
            sb.append(word.getTagthree());
            sb.append("|");
            sb.append(word.getTagtwo());
            sb.append("|");
            sb.append(word.getTagone());
            tagspannable = CorpusUtilityorig.NewSetWordSpanTag(word.getTagone(), word.getTagtwo(), word.getTagthree(),
                    word.getTagfour(), word.getTagfive(), expandTagsone, expandTagstwo, expandTagsthree, expandTagsfour, expandTagsfive);


            sb.append("|");
            sb.append(word.getTagfour());
            sb.append("|");
            sb.append(word.getTagthree());
            sb.append("|");
            sb.append(word.getTagtwo());
            sb.append("|");
            sb.append(word.getTagone());


        }


        return tagspannable;
    }


    @NonNull
    private String getVerbDetails(String expandTagsone, String wordetails) {
        String[] split = wordetails.split("\\|");
        String s = split[split.length - 1];
        if (!s.contains("MOOD")) {
            StringBuilder genderNumberdetails = getGenderNumberdetails(s);
            expandTagsone = expandTagsone.concat(genderNumberdetails.toString());
            String verbTense = expandVerbTense(word.getDetailsone());
            expandTagsone = expandTagsone.concat("(").concat(verbTense).concat(")");
            expandTagsone = expandTagsone.concat(CorpusConstants.verbfeaturesenglisharabic.IND);

        } else {
            String ss = split[split.length - 2];
            StringBuilder genderNumberdetails = getGenderNumberdetails(ss);
            expandTagsone = expandTagsone.concat(genderNumberdetails.toString());
            String verbTense = expandVerbTense(word.getDetailsone());
            expandTagsone = expandTagsone.concat("(").concat(verbTense).concat(")");

        }

        if (!s.contains("PASS")) {
            expandTagsone = expandTagsone.concat(ACT);

        } else {
            expandTagsone = expandTagsone.concat(PASS);
        }


        return expandTagsone;
    }

    private String expandVerbTense(String verbdetails) {
        String tense;
        if (verbdetails.contains("IMPF")) {
            tense = IMPF;
        } else if (verbdetails.contains("IMPV")) {

            tense = IMPV;
        } else if (verbdetails.contains("PERF")) {
            tense = PERF;

        } else {

            tense = "";
        }
        if (verbdetails.contains("SUBJ")) {

            tense = tense.concat(SUBJ);
        } else if (verbdetails.contains("JUS")) {
            tense = tense.concat(JUS);
        }

        return tense;
    }
}
