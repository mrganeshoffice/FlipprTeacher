package com.app.flipprteachear.home.view.adapterMcq;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.flipprteachear.R;
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojoQuesQuries.Detail;
import com.app.flipprteachear.home.view.InHelpSelectedItemsView;

import java.util.List;

public class Query_Adapter extends RecyclerView.Adapter<Query_Adapter.Customm> {
    Context context;
    InHelpSelectedItemsView selectedItems;
    List<Detail>  mlist;
   // int counter = 0;

    public Query_Adapter(List<Detail> details, InHelpSelectedItemsView selectedItems) {
        mlist = details;
       this.selectedItems =selectedItems;
    }
    private SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
    @NonNull
    @Override
    public Customm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item_multiple_choic, parent , false);
        context =parent.getContext();
        return new Customm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Customm holder, int position) {
       // holder.CheckedTextView.setTextColor(Color.WHITE);
        holder.CheckedTextView.setText(mlist.get(position).getQueryName());
        mlist.get(position).setSelected(false);
        holder.CheckedTextView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    holder.CheckedTextView.setChecked(true);
                    holder.CheckedTextView.setFocusable(true);
                    mlist.get(position).setSelected(true);
                    selectedItems.getInSelectedItemss(mlist);
                }else {
                    mlist.get(position).setSelected(false);
                    selectedItems.getInSelectedItemss(mlist);
                }
               /* System.out.println("counter...."+counter+" ..... "+durations);
                if(counter > durations){
                    holder.CheckedTextView.setFocusable(false);
                    holder.CheckedTextView.setChecked(false);
                    mlist.get(position).setSelected(false);
                    counter--;
                }*/
               // Toast.makeText(context,""+mlist.get(position), Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public class Customm extends RecyclerView.ViewHolder {
        CheckBox CheckedTextView;
        View itemView;
        public Customm(@NonNull View view) {
            super(view);
            itemView = view;
            CheckedTextView =  view.findViewById(R.id.text1Checked);
        }

    }

}
