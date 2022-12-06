package com.app.flipprteachear.home.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.app.flipprteachear.R;
import com.app.flipprteachear.home.pojo.ModelCheckedActivities;

import java.util.ArrayList;

public class CheckBoxAdapter2 extends RecyclerView.Adapter<CheckBoxAdapter2.Customm> {
    Context context;
    int durations =1;
    ArrayList<ModelCheckedActivities> mlist;
    int counter = 0;
    String  daysStr;

    public CheckBoxAdapter2(Context context, ArrayList<ModelCheckedActivities> list) {
        this.context = context;
        mlist = list;
    }
    public void updateDuration(String s_Duration) {
       // System.out.println("counter...."+  " ..n... "+durations);

        this.durations = Integer.parseInt(s_Duration);
        System.out.println("counter...."+  " ..n... "+durations);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Customm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item_multiple_choic, parent , false);
        return new Customm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Customm holder, int position) {
        holder.CheckedTextView.setText(mlist.get(position).getActivities_s());

        holder.CheckedTextView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    holder.CheckedTextView.setChecked(true);
                    holder.CheckedTextView.setFocusable(true);
                    mlist.get(position).setSelected(true);
                    counter++;
                }else {
                    counter--;
                    mlist.get(position).setSelected(false);
                }
                System.out.println("counter...."+counter+" ..... "+durations);
               /* if(counter > durations){
                    holder.CheckedTextView.setFocusable(false);
                    holder.CheckedTextView.setChecked(false);
                    mlist.get(position).setSelected(false);
                    counter--;
                }*/
               // Toast.makeText(context,""+mlist.get(position), Toast.LENGTH_SHORT).show();

            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                (holder.CheckedTextView.setChecked(
////                        !(holder.CheckedTextView.isChecked());
//                if (holder.CheckedTextView.isChecked()) {
//                    onItemClick.onItemCheck(mlist.get(position));
//                } else {
//                    onItemClick.onItemUncheck(mlist.get(position));
//                }
//            }
//        });
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
