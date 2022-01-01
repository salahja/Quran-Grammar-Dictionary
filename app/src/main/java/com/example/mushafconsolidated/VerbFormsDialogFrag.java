package com.example.mushafconsolidated;

import static androidx.webkit.WebSettingsCompat.DARK_STRATEGY_USER_AGENT_DARKENING_ONLY;
import static androidx.webkit.WebSettingsCompat.FORCE_DARK_AUTO;
import static androidx.webkit.WebSettingsCompat.FORCE_DARK_ON;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import com.example.mushafconsolidated.intrface.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     FontQuranListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class VerbFormsDialogFrag extends BottomSheetDialogFragment {


    private static final String ARG_OPTIONS_DATA = "item_count";
    OnItemClickListener mItemClickListener;
    RadioGroup radioGroup;
    private String form;


    public static VerbFormsDialogFrag newInstance(String[] data) {
        final VerbFormsDialogFrag fragment = new VerbFormsDialogFrag();
        final Bundle args = new Bundle();
        args.putStringArray(ARG_OPTIONS_DATA, data);
        fragment.setArguments(args);
        return fragment;

    }
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
     //  return inflater.inflate(R.layout.verb_forms, container, false);
        View view = inflater.inflate(R.layout.verb_forms, container, false);
        Bundle bundle = this.getArguments();
        String[] stringArray = bundle.getStringArray(ARG_OPTIONS_DATA);
        form =  (stringArray[0]);
  //    WebView wv = (WebView) view.findViewById(R.id.webview);
    //    wv.loadUrl("file:///android_asset/form10.html");
       // WebSettingsCompat.setForceDark(webView.settings, FORCE_DARK_ON)



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       final RecyclerView recyclerView = view.findViewById(R.id.list);
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        ArrayList<String> details=new ArrayList<>();
        String sample="بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ";

        FontQuranAdapter fontQuranAdapter = new FontQuranAdapter(form);
       recyclerView.setAdapter(fontQuranAdapter);
      fontQuranAdapter.SetOnItemClickListener(new OnItemClickListener() {
          @Override
          public void onItemClick(View v, int position) {
              int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
              //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
          }
      });





    }

    private class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
         WebView wv;


        ViewHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.webview, parent, false));

            wv =  itemView.findViewById(R.id.webview);

            WebSettingsCompat.setForceDark(wv.getSettings(), FORCE_DARK_ON);



                itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }

    private class FontQuranAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final String mItemCount;
        private OnItemClickListener mItemClickListener;

        FontQuranAdapter(String itemCount) {
            mItemCount = itemCount;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String sample="بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ";
            Typeface mequran = Typeface.createFromAsset(getContext().getAssets(), "me_quran.ttf");
            Typeface qalam = Typeface.createFromAsset(getContext().getAssets(), "AlQalam.ttf");
            Typeface amiri = Typeface.createFromAsset(getContext().getAssets(), "Pdms.ttf");
            holder.wv.loadUrl("file:///android_asset/form10.html");
            if(form.equals("II")){
                holder.wv.loadUrl("file:///android_asset/form2.html");
            } else  if(form.equals("III")) {
                holder.wv.loadUrl("file:///android_asset/form3.html");
            }else  if(form.equals("IV")) {
                holder.wv.loadUrl("file:///android_asset/form4.html");
            }else  if(form.equals("V")) {
                holder.wv.loadUrl("file:///android_asset/form5.html");
            }else  if(form.equals("VI")) {
                holder.wv.loadUrl("file:///android_asset/form6.html");
            }else  if(form.equals("VII")) {
                holder.wv.loadUrl("file:///android_asset/form6.html");
            }else  if(form.equals("VIII")) {
                holder.wv.loadUrl("file:///android_asset/form8.html");
            }else  if(form.equals("X")) {
                holder.wv.loadUrl("file:///android_asset/form10.html");
            }

        }

        @Override
        public int getItemCount() {
            return 2;
        }

        public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
            this.mItemClickListener = mItemClickListener;
        }
    }

}