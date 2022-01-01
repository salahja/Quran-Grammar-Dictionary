package org.sj.conjugator.adapter;


import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import org.jetbrains.annotations.NotNull;
import org.sj.conjugator.interfaces.OnItemClickListener;
import org.sj.conjugator.utilities.SharedPref;
import com.example.mushafconsolidated.R;

import java.util.ArrayList;

public class MujarradSarfSagheerListingAdapter extends RecyclerView.Adapter<MujarradSarfSagheerListingAdapter.ViewHolder> {

    int rootcolor, weaknesscolor, wazancolor;
    private final Context context;
    int bookmarkpostion;
    OnItemClickListener mItemClickListener;
    //    private final Integer arabicTextColor;
    Context mycontext;
    private ArrayList<String> madhi = new ArrayList<>();
    private boolean mazeedregular;
    private int bookChapterno;
    private int bookVerseno;
    private Integer ayahNumber;
    private String urdu_font_selection;
    private int quran_arabic_font;

    String ismzaftitle = "(الْظَرْف:)";
    String ismalatitle = "( الآلَة:)";
    String alaheader = "اِسْم الآلَة";
    String zarfheader = "اِسْم الْظَرفْ";
    private int urdu_font_size;
    private String arabic_font_selection;
    private ArrayList<ArrayList> sarfSagheer = new ArrayList<>();


    public MujarradSarfSagheerListingAdapter(ArrayList<ArrayList> lists, Context context) {
        this.context = context;
        this.sarfSagheer = lists;


    }


    public MujarradSarfSagheerListingAdapter(boolean mazeedregular, ArrayList sarfSagheer, FragmentActivity activity) {
        this.context = activity;
        this.sarfSagheer = sarfSagheer;
        this.mazeedregular = mazeedregular;

    }

    public MujarradSarfSagheerListingAdapter(ArrayList<String> madhi, ArrayList<ArrayList> skabeer, FragmentActivity activity) {
        this.context = activity;
        this.sarfSagheer = skabeer;
        this.madhi = madhi;

    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        //      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sarfkabeercolumn, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thulathitable, parent, false);
        //    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thulathisarfsagheer, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        //  final List sarf = sarfSagheer.get(position);
//        final String[] array = (String[]) sarfSagheer.get(position).toArray();

        SharedPreferences shared =getDefaultSharedPreferences(context);

        String preferences = shared.getString("theme", "dark");
        int cweakness = 0;
        int crootword = 0;
        final int cbabcolor;

        if (preferences.equals("dark")) {
            rootcolor = Color.CYAN;
            weaknesscolor = Color.YELLOW;
            wazancolor = Color.BLUE;



        } else {
            rootcolor = Color.RED;
            weaknesscolor = Color.BLACK;
            wazancolor = Color.RED;



        }

        StringBuilder zarf = new StringBuilder();
        StringBuilder ismala = new StringBuilder();
        StringBuilder amr = new StringBuilder();
        StringBuilder nahiamr = new StringBuilder();
        final Object[] toArray = sarfSagheer.get(position).toArray();
        final Typeface mequran = Typeface.createFromAsset(context.getAssets(), SharedPref.arabicFontSelection());

        final int length = toArray.length;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Integer arabicFontsize = prefs.getInt("arabicFontSizeEntryArray", 20);
        //   Integer arabicFontsize = SharedPref.arabicFontsize();
        //   arabicFontsize=45
        holder.ismalaheader.setTextSize(arabicFontsize);
        holder.ismzarfheader.setTextSize(arabicFontsize);
        holder.mamaroof.setTextSize(arabicFontsize);
        ;
        holder.mumaroof.setTextSize(arabicFontsize);
        ;
        holder.masdaro.setTextSize(arabicFontsize);
        ;
        holder.masdart.setTextSize(arabicFontsize);
        ;
        holder.ismfail.setTextSize(arabicFontsize);
        ;

        holder.mamajhool.setTextSize(arabicFontsize);
        ;

        holder.mumajhool.setTextSize(arabicFontsize);
        ;

        holder.ismmafool.setTextSize(arabicFontsize);
        ;

        holder.amr.setTextSize(arabicFontsize);
        ;

        holder.nahiamr.setTextSize(arabicFontsize);
        ;
        holder.babname.setTextSize(arabicFontsize);
        ;

        holder.rootword.setTextSize(arabicFontsize);
        ;


        holder.ismzarf.setTextSize(arabicFontsize);
        ;
        holder.ismala.setTextSize(arabicFontsize);
        ;
        holder.weaknessname.setTextSize(arabicFontsize);
        ;

        holder.wazan.setTextSize(arabicFontsize);


        holder.mamaroof.setTypeface(mequran);
        holder.mumaroof.setTypeface(mequran);
        //   holder.masdaro.setTypeface(mequran);
        // holder.masdart.setTypeface(mequran);
        holder.ismfail.setTypeface(mequran);

        holder.mamajhool.setTypeface(mequran);

        holder.mumajhool.setTypeface(mequran);

        holder.ismmafool.setTypeface(mequran);

        holder.amr.setTypeface(mequran);

        holder.nahiamr.setTypeface(mequran);
        holder.babname.setTypeface(mequran);
        //  holder.babname.setTextColor(Color.YELLOW);
        holder.rootword.setTypeface(mequran);
        //  holder.rootword.setTextColor(Color.BLUE);

        holder.ismzarf.setTypeface(mequran);
        holder.ismala.setTypeface(mequran);
        holder.weaknessname.setTypeface(mequran);
        //  holder.weaknessname.setTextColor(Color.GREEN);

        holder.babname.setTextColor(wazancolor);
        holder.rootword.setTextColor(rootcolor);
        holder.weaknessname.setTextColor(weaknesscolor);





        if (length == 13) {
            holder.mamaroof.setText((CharSequence) toArray[0]);
            holder.mumaroof.setText((CharSequence) toArray[1]);
            holder.ismfail.setText((CharSequence) toArray[2]);
            holder.mamajhool.setText((CharSequence) toArray[3]);
            holder.mumajhool.setText((CharSequence) toArray[4]);
            holder.ismmafool.setText((CharSequence) toArray[5]);
            holder.amr.setText((CharSequence) toArray[6]);
            holder.nahiamr.setText((CharSequence) toArray[7]);
            holder.ismzarfheader.setText(zarfheader);
            holder.ismalaheader.setText(alaheader);
            zarf.append((CharSequence) toArray[8]);

            holder.ismzarf.setText(zarf);


            holder.ismala.setText(ismalatitle);


            holder.weaknessname.setText((CharSequence) toArray[11]);

            holder.rootword.setText((CharSequence) toArray[10]);
            holder.babname.setText((CharSequence) toArray[9]);

        }
        if (length == 14) {
            holder.mamaroof.setText((CharSequence) toArray[0]);
            holder.mumaroof.setText((CharSequence) toArray[1]);
            holder.ismfail.setText((CharSequence) toArray[2]);
            holder.mamajhool.setText((CharSequence) toArray[3]);
            holder.mumajhool.setText((CharSequence) toArray[4]);
            holder.ismmafool.setText((CharSequence) toArray[5]);
            holder.amr.setText((CharSequence) toArray[6]);
            holder.nahiamr.setText((CharSequence) toArray[7]);
            holder.ismzarfheader.setText(zarfheader);
            holder.ismalaheader.setText(alaheader);
            zarf.append((CharSequence) toArray[8]);

            holder.ismzarf.setText(zarf);
            holder.ismalaheader.setText(alaheader);
            ismala.append((CharSequence) toArray[9]);


            holder.ismala.setText(ismala);


            holder.weaknessname.setText((CharSequence) toArray[10]);

            holder.rootword.setText((CharSequence) toArray[11]);
            holder.babname.setText((CharSequence) toArray[12]);
            holder.verify.setText((CharSequence) toArray[13]);
        }
        if (length == 15) {
            holder.mamaroof.setText((CharSequence) toArray[0]);
            holder.mumaroof.setText((CharSequence) toArray[1]);
            holder.ismfail.setText((CharSequence) toArray[2]);
            holder.mamajhool.setText((CharSequence) toArray[3]);
            holder.mumajhool.setText((CharSequence) toArray[4]);
            holder.ismmafool.setText((CharSequence) toArray[5]);
            holder.amr.setText((CharSequence) toArray[6]);
            holder.nahiamr.setText((CharSequence) toArray[7]);
            holder.ismzarfheader.setText(zarfheader);
            zarf.append((CharSequence) toArray[8]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[9]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[10]);
            holder.ismzarf.setText(zarf);
            holder.ismalaheader.setText(alaheader);
            ismala.append((CharSequence) toArray[11]);
            ismala.append(",");

            ismala.append((CharSequence) toArray[12]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[13]);

            holder.ismala.setText(ismala);

            holder.rootword.setText((CharSequence) toArray[14]);

        }

        if (length == 17) {
            holder.mamaroof.setText((CharSequence) toArray[0]);
            holder.mumaroof.setText((CharSequence) toArray[1]);
            holder.ismfail.setText((CharSequence) toArray[2]);
            holder.mamajhool.setText((CharSequence) toArray[3]);
            holder.mumajhool.setText((CharSequence) toArray[4]);
            holder.ismmafool.setText((CharSequence) toArray[5]);
            holder.amr.setText((CharSequence) toArray[6]);
            holder.nahiamr.setText((CharSequence) toArray[7]);
            holder.ismzarfheader.setText(zarfheader);
            holder.ismalaheader.setText(alaheader);
            zarf.append((CharSequence) toArray[8]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[9]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[10]);
            holder.ismzarf.setText(zarf);
            ismala.append((CharSequence) toArray[11]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[12]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[13]);
            holder.ismala.setText(ismala);
            holder.weaknessname.setText((CharSequence) toArray[14]);

            holder.rootword.setText((CharSequence) toArray[15]);
            holder.wazan.setText((CharSequence) toArray[16]);
            //     holder.verify.setText((CharSequence) toArray[16]);
        }
        if (length == 18) {
            holder.mamaroof.setText((CharSequence) toArray[0]);
            holder.mumaroof.setText((CharSequence) toArray[1]);
            holder.ismfail.setText((CharSequence) toArray[2]);
            holder.mamajhool.setText((CharSequence) toArray[3]);
            holder.mumajhool.setText((CharSequence) toArray[4]);
            holder.ismmafool.setText((CharSequence) toArray[5]);
            holder.amr.setText((CharSequence) toArray[6]);
            holder.nahiamr.setText((CharSequence) toArray[7]);
            holder.ismzarfheader.setText(zarfheader);
            holder.ismalaheader.setText(alaheader);
            zarf.append((CharSequence) toArray[8]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[9]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[10]);
            holder.ismzarf.setText(zarf);
            ismala.append((CharSequence) toArray[11]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[12]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[13]);
            holder.ismala.setText(ismala);
            holder.weaknessname.setText((CharSequence) toArray[14]);

            holder.rootword.setText((CharSequence) toArray[15]);
            holder.wazan.setText((CharSequence) toArray[16]);
            holder.babno.setText((CharSequence) toArray[16]);
            holder.verify.setText((CharSequence) toArray[17]);
        }
        //thulathi regular
        if (length == 22) {
            holder.mamaroof.setText((CharSequence) toArray[0]);
            holder.mumaroof.setText((CharSequence) toArray[1]);
            holder.ismfail.setText((CharSequence) toArray[2]);
            holder.mamajhool.setText((CharSequence) toArray[3]);
            holder.mumajhool.setText((CharSequence) toArray[4]);
            holder.ismmafool.setText((CharSequence) toArray[5]);
            amr.append((CharSequence) toArray[6]);
            amr.append(",");
            amr.append((CharSequence) toArray[7]);
            amr.append(",");
            amr.append((CharSequence) toArray[8]);
            holder.amr.setText(amr);
            nahiamr.append((CharSequence) toArray[9]);
            nahiamr.append(",");
            nahiamr.append((CharSequence) toArray[10]);
            nahiamr.append(",");
            nahiamr.append((CharSequence) toArray[11]);
            holder.nahiamr.setText(nahiamr);
            holder.ismzarfheader.setText(zarfheader);
            holder.ismalaheader.setText(alaheader);
            zarf.append((CharSequence) toArray[12]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[13]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[14]);
            holder.ismzarf.setText(zarf);
            ismala.append((CharSequence) toArray[15]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[16]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[17]);
            holder.weaknessname.setText((CharSequence) toArray[18]);
            holder.rootword.setText((CharSequence) toArray[19]);
            holder.ismala.setText(ismala);
            holder.babname.setText((CharSequence) toArray[20]);
            holder.verify.setText((CharSequence) toArray[21]);
            //  holder.rootword.setText((CharSequence) toArray[15]);

        }
        //mahmooz mithal and lafeef originall 18
        if (length == 19) {
            holder.mamaroof.setText((CharSequence) toArray[0]);
            holder.mumaroof.setText((CharSequence) toArray[1]);
            holder.ismfail.setText((CharSequence) toArray[2]);
            holder.mamajhool.setText((CharSequence) toArray[3]);
            holder.mumajhool.setText((CharSequence) toArray[4]);
            holder.ismmafool.setText((CharSequence) toArray[5]);
            holder.amr.setText((CharSequence) toArray[6]);
            holder.nahiamr.setText((CharSequence) toArray[7]);
            holder.ismzarfheader.setText(zarfheader);
            holder.ismalaheader.setText(alaheader);
            zarf.append((CharSequence) toArray[8]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[9]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[10]);
            holder.ismzarf.setText(zarf);
            ismala.append((CharSequence) toArray[11]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[12]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[13]);

            holder.ismala.setText(ismala);
            //  holder.masdaro.setText((CharSequence) toArray[14]);
            //  holder.masdart.setText((CharSequence) toArray[14]);
            holder.rootword.setText((CharSequence) toArray[15]);
            holder.babname.setText((CharSequence) toArray[16]);
            holder.weaknessname.setText((CharSequence) toArray[14]);
            holder.verify.setText((CharSequence) toArray[18]);
        }
        if (length == 1) {
            holder.mamaroof.setText((CharSequence) toArray[0]);
            holder.mumaroof.setText((CharSequence) toArray[1]);
            holder.ismfail.setText((CharSequence) toArray[2]);
            holder.mamajhool.setText((CharSequence) toArray[3]);
            holder.mumajhool.setText((CharSequence) toArray[4]);
            holder.ismmafool.setText((CharSequence) toArray[5]);
            holder.amr.setText((CharSequence) toArray[6]);
            holder.nahiamr.setText((CharSequence) toArray[7]);
            holder.ismzarfheader.setText(zarfheader);
            holder.ismalaheader.setText(alaheader);
            zarf.append((CharSequence) toArray[8]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[9]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[10]);
            holder.ismzarf.setText(zarf);
            ismala.append((CharSequence) toArray[11]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[12]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[13]);

            holder.ismala.setText(ismala);

            holder.weaknessname.setText((CharSequence) toArray[14]);

            holder.rootword.setText((CharSequence) toArray[15]);


            holder.rootword.setText((CharSequence) toArray[15]);
            holder.verify.setText((CharSequence) toArray[16]);


        }
        //mudhaf
        if (length == 23) {
            holder.mamaroof.setText((CharSequence) toArray[0]);
            holder.mumaroof.setText((CharSequence) toArray[1]);
            holder.ismfail.setText((CharSequence) toArray[2]);
            holder.mamajhool.setText((CharSequence) toArray[3]);
            holder.mumajhool.setText((CharSequence) toArray[4]);
            holder.ismmafool.setText((CharSequence) toArray[5]);
            amr.append((CharSequence) toArray[6]);
            amr.append(",");
            amr.append((CharSequence) toArray[7]);
            amr.append(",");
            amr.append((CharSequence) toArray[8]);
            holder.amr.setText(amr);
            nahiamr.append((CharSequence) toArray[9]);
            nahiamr.append(",");
            nahiamr.append((CharSequence) toArray[10]);
            nahiamr.append(",");
            nahiamr.append((CharSequence) toArray[11]);
            holder.nahiamr.setText(nahiamr);

            holder.ismzarfheader.setText(zarfheader);
            holder.ismalaheader.setText(alaheader);
            zarf.append((CharSequence) toArray[12]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[13]);
            zarf.append(",");
            zarf.append((CharSequence) toArray[14]);
            holder.ismzarf.setText(zarf);
            ismala.append((CharSequence) toArray[15]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[16]);
            ismala.append(",");
            ismala.append((CharSequence) toArray[17]);

            holder.ismala.setText(ismala);
            holder.masdaro.setText((CharSequence) toArray[18]);
            holder.masdart.setText((CharSequence) toArray[18]);
            holder.rootword.setText((CharSequence) toArray[19]);
            holder.babname.setText((CharSequence) toArray[20]);
            holder.weaknessname.setText((CharSequence) toArray[21]);
            holder.wazan.setText((CharSequence) toArray[22]);
        }


        //     holder.masdaro.setText((CharSequence) toArray[12]);
        //     holder.masdart.setText((CharSequence) toArray[12]);

        //     TextView textView = (TextView) findViewById(R.id.textView);
        //     Spannable spanDark = new SpannableString(textView.getText());
        //     span.setSpan(new RelativeSizeSpan(0.8f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //     textView.setText(span);


        //     ismfail,masdaro,mumaroof,mamaroof,ismmafool,masdart,mumajhool,mamajhool,ismzarf,ismala;
        //  holder.ismfail.setText(o.);


    }


    @Override
    public long getItemId(int position) {
        //  Surah surah = surahArrayList.get(position);

        return sarfSagheer.size();
    }

    public Object getItem(int position) {

        return sarfSagheer.get(position);
    }

    @Override
    public int getItemCount() {
        return sarfSagheer.size();

    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void setVerbArrayList(ArrayList<ArrayList> sarfsagheer) {
        this.sarfSagheer = sarfsagheer;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
          implements View.OnClickListener // current clickListerner
    {
        public final Chip amr, nahiamr, ismfail,   mumaroof,
              mamaroof, ismmafool,  mumajhool, mamajhool,
              ismzarf, ismala,   verify;
        public final TextView babno,ismalaheader,ismzarfheader,masdart,masdaro,babname,rootword,weaknessname,wazan;


        public ViewHolder(View view) {
            super(view);


            //    itemView.setTag(this);
            //     itemView.setOnClickListener(onItemClickListener);
            babno = view.findViewById(R.id.babno);
            ismfail = view.findViewById(R.id.ismfail);
            masdaro = view.findViewById(R.id.masdar);
            mumaroof = view.findViewById(R.id.mumaroof);
            mamaroof = view.findViewById(R.id.mamaroof);
            ismmafool = view.findViewById(R.id.ismmafool);
            masdart = view.findViewById(R.id.masdar2);
            mumajhool = view.findViewById(R.id.mumajhool);
            mamajhool = view.findViewById(R.id.mamajhool);
            amr = view.findViewById(R.id.amr);
            nahiamr = view.findViewById(R.id.nahiamr);
            ismala = view.findViewById(R.id.ismaalatable);
            ismzarf = view.findViewById(R.id.zarftable);
            babname = view.findViewById(R.id.babno);
            rootword = view.findViewById(R.id.rootword);
            weaknessname = view.findViewById(R.id.weknessname);
            ismzarfheader = view.findViewById(R.id.ismzarfheader);
            ismalaheader = view.findViewById(R.id.ismalaheader);
            wazan = view.findViewById(R.id.wazan);
            verify = view.findViewById(R.id.conjugateall);





            mumajhool.setTooltipText("Click for Verb Conjugation");

            view.setOnClickListener(this);
            ismfail.setOnClickListener(this);//R.id.ismfail);

            mumaroof.setOnClickListener(this);//R.id.mumaroof);
            mamaroof.setOnClickListener(this);//R.id.mamaroof);
            ismmafool.setOnClickListener(this);//R.id.ismmafool);

            mumajhool.setOnClickListener(this);//R.id.mumajhool);
            mamajhool.setOnClickListener(this);//R.id.mamajhool);
            amr.setOnClickListener(this);//R.id.amr);
            nahiamr.setOnClickListener(this);//R.id.nahiamr);
            ismala.setOnClickListener(this);//R.id.ismaalatable);
            ismzarf.setOnClickListener(this);//R.id.zarftable);

            rootword.setOnClickListener(this);//R.id.weaknesstype);

            ;

            verify.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }

    }


}
