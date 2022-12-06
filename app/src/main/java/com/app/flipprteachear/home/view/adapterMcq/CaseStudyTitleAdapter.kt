package com.app.flipprteachear.home.view.adapterMcq

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.ResCaseStudyQesTitleRowBinding
import com.app.flipprteachear.home.pojo.liveQuesPojo.Detail
import com.app.flipprteachear.utillsa.Constants_All.latetex_dim_15

public class CaseStudyTitleAdapter(context: Context, detail: Detail, type: String) :
    RecyclerView.Adapter<CaseStudyTitleAdapter.ViewHolder>() {
    private val context: Context
    private val detail: Detail
    private var type: String
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val qesBinding: ResCaseStudyQesTitleRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.res_case_study_qes_title_row,
            parent,
            false
        )
        val view: View = qesBinding.getRoot()
        return ViewHolder(qesBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindng.formulaOne1Res.setVisibility(View.VISIBLE)
        holder.bindng.formulaOne1Res.setTextSize(latetex_dim_15)
        holder.bindng.tvSereasRes.setText("" + (position + 65).toChar())
        holder.bindng.tvSereasRes.setBackgroundColor(Color.parseColor("#05334C"))
        holder.bindng.Card.setBackgroundResource(R.drawable.round_boundary_navi)

        // holder.bindng.Card.getLayoutParams().width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        holder.bindng.tvSereasRes.setTextColor(context.getResources().getColor(R.color.white))
        val ans: String = detail.case_study_ques_details!!.get(position).caseStudy!!
        //holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
        //holder.bindng.formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ans, false))
        val sType = type.toInt()
        if (sType == 4) type = "3"
        if (type.equals(position.toString() + "", ignoreCase = true)) {
            holder.bindng.tvSereasRes.setBackgroundColor(Color.parseColor("#F8AD34"))
            holder.bindng.Card.setBackgroundResource(R.drawable.round_boundary_yellow)
            // holder.bindng.Card.getLayoutParams().width = RelativeLayout.LayoutParams.WRAP_CONTENT;
            holder.bindng.tvSereasRes.setTextColor(context.getResources().getColor(R.color.white))
        }
        //
    }

    override fun getItemCount(): Int {
        return detail.case_study_ques_details!!.size
    }

    inner class ViewHolder(bindng: ResCaseStudyQesTitleRowBinding) :
        RecyclerView.ViewHolder(bindng.getRoot()) {
        var bindng: ResCaseStudyQesTitleRowBinding

        init {
            this.bindng = bindng
        }
    }

    init {
        this.detail = detail
        this.context = context
        this.type = type
    }
}
