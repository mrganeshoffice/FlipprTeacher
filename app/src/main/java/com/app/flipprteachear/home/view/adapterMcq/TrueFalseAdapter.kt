package com.app.flipprteachear.home.view.adapterMcq

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.ResTruefalseBinding
import com.app.flipprteachear.home.pojo.liveQuesPojo.TruefalseAnswerDetail
import com.app.flipprteachear.utillsa.Constants_All
import java.util.*

class TrueFalseAdapter(var pojo: List<TruefalseAnswerDetail>?, var type: String, var selectedanswer: ArrayList<String>
) : RecyclerView.Adapter<TrueFalseAdapter.Custom>() {
    var selected = -1
    var arrayList = ArrayList<String>()
    private var context:Context?=null

    init {
        arrayList.clear()
        arrayList.add("True")
        arrayList.add("False")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrueFalseAdapter.Custom {
        val view: ResTruefalseBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.res_truefalse, parent, false)
        context = parent.context
        return  Custom(view)
    }

    override fun onBindViewHolder(holder: TrueFalseAdapter.Custom, position: Int) {
        with(holder.binding){
            formulaOne1.setTextSize(com.app.flipprteachear.utillsa.Constants_All.latetex_dim_15)
            if (type.equals("question", ignoreCase = true)){
                val ans = arrayList[position]
                formulaOne1.setDisplayText(Constants_All.getLatexSolvedQ_Str_Solution(ans));
                formulaOne1.setOnClickListener {  holder.itemView.performClick() }


                Card.setBackground(ContextCompat.getDrawable(context!!,R.drawable.round_boundary_navi))
                if (arrayList[position].equals("True")) {
                    tvSereas.setBackgroundColor(Color.parseColor("#26e67a"))
                    Card.setBackgroundResource(R.drawable.boundary_green)
                    tvSereas.setTextColor(ContextCompat.getColor(context!!,R.color.white))
                    icRight.setVisibility(View.VISIBLE)
                }

            }
            else{
                val ans = arrayList[position]
                formulaOne1.setDisplayText(Constants_All.getLatexSolvedQ_Str_Solution(ans));
                Card.setBackground(ContextCompat.getDrawable(context!!,R.drawable.round_boundary_navi))

                if (selectedanswer[0].equals(arrayList[position], ignoreCase = true)) {
                     Card.setBackgroundResource(R.drawable.round_boundary_yellow)
                }
                icRight.setVisibility(View.GONE)
                icCancel.setVisibility(View.VISIBLE)

                if (pojo?.get(0)!!.tf_type.equals(arrayList[position],ignoreCase = true)) {
                    icRight.setVisibility(View.VISIBLE)
                    icCancel.setVisibility(View.GONE)
                    //int marksRight =0;
                   // marksRight++
                    // only single ans true/false dso single time api hit
                    // setApii();
                } else {
                   // marksRight = 0
                    icRight.setVisibility(View.GONE)
                    // holder.ic_cancel.setVisibility(View.VISIBLE);
                    // only single ans true/false dso single time api hit
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size;
    }

    class Custom (v:ResTruefalseBinding):RecyclerView.ViewHolder(v.root){
         var binding :ResTruefalseBinding =v
    }
}