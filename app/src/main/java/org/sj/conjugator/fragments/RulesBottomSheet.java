package org.sj.conjugator.fragments;

import static com.example.Constant.*;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utility.DarkThemeApplication;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;
import org.sj.conjugator.adapter.rulesbottomsheetadapter;
import org.sj.conjugator.interfaces.OnItemClickListener;
import org.sj.conjugator.utilities.VerbConjugaorApp;
import com.example.mushafconsolidated.R;

import java.util.ArrayList;

import database.DatabaseUtils;
import database.entity.kov;

public class RulesBottomSheet extends BottomSheetDialogFragment {
    public static final String TAG ="bottom" ;
    private static final String ARG_OPTIONS_DATA = "options_data";
    rulesbottomsheetadapter adapter;
    private ArrayList<kov> kovArrayList;
    Context context;
    private String[] verbtype;


    @NonNull
    public static RulesBottomSheet newInstance(String[] data) {
        final RulesBottomSheet fragment = new RulesBottomSheet();
        final Bundle args = new Bundle();
       args.putStringArray(ARG_OPTIONS_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    public RulesBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mujarrad_bottom_fragment, container, false);

        SharedPreferences prefs = android.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.getContext());

        RecyclerView recyclerView = view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        Bundle bundle = this.getArguments();
        verbtype = bundle.getStringArray(ARG_OPTIONS_DATA);
        String indictive = prefs.getString(VERBMOOD, "indicative");
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        SharedPreferences shared =
                PreferenceManager.getDefaultSharedPreferences(getContext());
        DatabaseUtils db=new DatabaseUtils(DarkThemeApplication.getContext());

      kovArrayList = db.getKov();

       adapter=new rulesbottomsheetadapter(kovArrayList,DarkThemeApplication.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.SetOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
               dismiss();
                kov entity = kovArrayList.get(position);
                Bundle dataBundle = new Bundle();
                dataBundle.putString(QURAN_VERB_WAZAN, entity.getKov());


                dataBundle.putString(VERBMOOD, "indicative");
                if(verbtype[0].equals("mujarrad")) {
                    RulesMujarradVerbList rulesFragment = new RulesMujarradVerbList(context);
                    rulesFragment.setArguments(dataBundle);

                    FragmentTransaction transactions = (getActivity().getSupportFragmentManager().beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE));
                    transactions.replace(R.id.frame_container, rulesFragment).addToBackStack("mujarrad");
                    transactions.commit();
                }else{
                    RulesMazeedVerbList rulesFragment = new RulesMazeedVerbList(context);
                    rulesFragment.setArguments(dataBundle);

                    FragmentTransaction transactions = (getActivity().getSupportFragmentManager().beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE));
                    transactions.replace(R.id.frame_container, rulesFragment).addToBackStack("mujarrad");
                    transactions.commit();

                }




            }
        });
        
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
