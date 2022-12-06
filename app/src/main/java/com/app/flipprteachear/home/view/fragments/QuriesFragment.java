package com.app.flipprteachear.home.view.fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.app.flipprteachear.R;
import com.app.flipprteachear.databinding.FragmentQuriesBinding;
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojoQuesQuries.Detail;
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojoQuesQuries.PojoGetHelp;
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojoQuesQuries.PojoQuesQuries;
import com.app.flipprteachear.home.view.InHelpSelectedItemsView;
import com.app.flipprteachear.home.view.adapterMcq.Query_Adapter;
import com.app.flipprteachear.retroFitClasses.apiCalls;
import com.app.flipprteachear.utillsa.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuriesFragment extends Fragment implements InHelpSelectedItemsView {
//    @BindView(R.id.rlspinner) RelativeLayout rlspinner;
//    @BindView(R.id.sp_quries) Spinner sp_quries;
//   // @BindView(R.id.ll_spinnerback) LinearLayout ll_spinnerback;
//    @BindView(R.id.tv_sendquery) TextView tv_sendquery;
//    @BindView(R.id.rvList) RecyclerView rvList;
//    @BindView(R.id.edit_query) EditText edit_query;
//    @BindView(R.id.myListSave) TextView myListSave;
//    @BindView(R.id.ivDropDown) ImageView ivDropDown;
    Boolean isClicked =true;
    String selected= "";

    String query_type = "Wrong Content";

    ArrayList<String> spinnerlist=new ArrayList<String>();
    private PreferenceManager pref;
    String questionId = "";
    PojoQuesQuries pojoQuesQuries;
    FragmentQuriesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quries, container, false);
        View view =binding.getRoot();
        ButterKnife.bind(this,view);

        onClick();

        pref = new PreferenceManager(requireContext());
        questionId = getArguments().getString("questionId");
        getQueries();


        Log.d("HelloUserId",query_type);

        binding.spQuries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(getResources().getColor(R.color.navicolor)); //Change selected text color
                ((TextView) view).setTextSize(20); //Change selected text size
                query_type = binding.spQuries.getItemAtPosition(position).toString();
                Log.d("Selected item",query_type);
                //Change selected text color
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.tvSendquery .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.editQuery.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(),"Please enter your quries",Toast.LENGTH_LONG).show();
                }else {
                    sendQueryApi();
                }

            }
        });

        return  view;
    }

    private void onClick() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void sendQueryApi() {
        query_type =selected;
        apiCalls api = new apiCalls();
        Call<PojoQuesQuries> callApi = api.sendQuesReport(pref.getValueString("token"),query_type, binding.editQuery.getText().toString(),pref.getValueString("user_id"),questionId);

        callApi.enqueue(new Callback<PojoQuesQuries>() {
            @Override
            public void onResponse(Call<PojoQuesQuries> call, Response<PojoQuesQuries> response) {
                if (response.isSuccessful()){

                    Log.d("Api","Api working");
                    binding.editQuery.setText("");
                    Toast.makeText(getActivity(),response.body().getMessage(),Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<PojoQuesQuries> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });
    }


    private void getQueries() {
        apiCalls api = new apiCalls();
        Call<PojoQuesQuries> callApi = api.ques_quries(pref.getValueString("token"));

        callApi.enqueue(new Callback<PojoQuesQuries>() {
            @Override
            public void onResponse(Call<PojoQuesQuries> call, Response<PojoQuesQuries> response) {
                if (response.isSuccessful()){

                     pojoQuesQuries = response.body();
                    if (response.body() != null){
                        for (int i=0; i<response.body().getDetails().size(); i++){

                            getSet_QueryData(pojoQuesQuries.getDetails());
                           // spinnerlist.add(response.body().getDetails().get(i).getQueryName());
                            query_type = "Wrong Content";
                        }
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_row, spinnerlist);
                    binding.spQuries.setAdapter(arrayAdapter);
                    Log.d("Api",spinnerlist.toString());

                }else{
                    Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<PojoQuesQuries> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });
    }

    private void getSet_QueryData(List<Detail> details) {
        Query_Adapter adapter  =new Query_Adapter(details, this);
        binding.rvList.setAdapter(adapter);

        binding.rlspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClicked){
                    isClicked =false;
                    binding.rvList.setVisibility(View.VISIBLE);
                }else {
                    isClicked =true;
                    binding.rvList.setVisibility(View.GONE);
                }
            }
        });
        binding.myListSave.setOnClickListener(view -> {
            if(isClicked){
                isClicked =false;
                binding.rvList.setVisibility(View.VISIBLE);
            }else {
                isClicked =true;
                binding.rvList.setVisibility(View.GONE);
            }
        });

         /* binding.view11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClicked){
                    isClicked =false;
                    binding.rvList.setVisibility(View.VISIBLE);
                }else {
                    isClicked =true;
                    binding.rvList.setVisibility(View.GONE);
                }
            }
        });*/
    }

    @Override
    public void getInSelectedItems(List<PojoGetHelp.Detail_h> mlist) {

    }
    @Override
    public void getInSelectedItemss(List<Detail> mlist) {
            try {
                selected= convertStrigBuilder(mlist, ",");
                Log.e("getInSelectedItems: ", "" +selected);
            } catch (Exception e) {
                e.printStackTrace();
                binding.myListSave.setText(" Select ");
                selected ="";
            }

            for (int i = 0; i < mlist.size(); i++) {

                try {
                    Log.e("getInSelectedItems:1 ", "" + mlist.get(i).getQueryName());
                    Log.e("getInSelectedItems:2 ", "" + mlist.get(i).getQuesQueryTypesId());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

    }

    private String convertStrigBuilder(List<Detail> se, String s){
        StringBuilder sb = new StringBuilder();

        for(Detail ss : se){
            if(ss.getSelected()) sb.append(ss.getQueryName()).append(s);
        }
        binding.myListSave.setText(sb.substring(0, sb.length() - 1));
        return binding.myListSave.getText().toString();
    }
}