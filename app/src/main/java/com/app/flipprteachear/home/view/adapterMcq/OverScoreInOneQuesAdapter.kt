package com.app.flipprteachear.home.view.adapterMcq

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.ResScoreGainer1RowBinding
import com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted.QuestionDetail
import com.bumptech.glide.Glide

public class OverScoreInOneQuesAdapter(var adapterType: String) : RecyclerView.Adapter<OverScoreInOneQuesAdapter.custom>() {
    lateinit var context : Context
    private var questionDetail : QuestionDetail?=null
    private var questionId :String?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        val binding : ResScoreGainer1RowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.res_score_gainer1_row, parent, false)
        context =parent.context
        return custom(binding)
    }

    override fun onBindViewHolder(holder: custom, position: Int) {

        with(holder.binding){
            tvNumber.text = "" + (position + 1)


//           if(adapterType.equals("inThis")){
//                ivStreakActive.visibility =View.GONE
//                ivDoubleConfidence.visibility =View.GONE
//            }
            questionDetail?.userDetail!![position].let {
               if(it.submitDetail.quesDetail.questionId.equals(questionId)){
                Glide.with(context).load(it.image).into(ivImage)
                if(it.submitDetail.quesDetail.isStreakActive.equals("0"))
                 ivStreakActive.visibility =View.GONE  else  ivStreakActive.visibility =View.VISIBLE

                if(it.submitDetail.quesDetail.isDoubleConfidence.equals("0"))
                    ivDoubleConfidence.visibility =View.GONE
                else  ivDoubleConfidence.visibility =View.VISIBLE

               // if(it.submitDetail.quesDetail.ansStatus.equals("1"))
                Log.w("TAG", "onBindViewHolder:anss1 "+ it.submitDetail.quesDetail.ansPoints)

                val str =String.format("%.1f", it.submitDetail.quesDetail.ansPoints
                    .replace("+","").replace("-","").toFloat())
                tvRank.text =""
                tvRank.text =  if(it.submitDetail.quesDetail.ansStatus.equals("0")) "-$str" else str
                   tvStudentName.text  = it.firstName
            }
            }


        }

    }

    override fun getItemCount(): Int {
         if(questionDetail!=null)
             if(questionDetail!!.userDetail!=null)
                 return questionDetail!!.userDetail.size
             else return 0
             else return 0
    }

    fun updateListType(questionDetail: QuestionDetail,questionId :String) {
        this.questionDetail = questionDetail
        this.questionId = questionId
        notifyDataSetChanged()
    }

    inner class custom(bind: ResScoreGainer1RowBinding) : RecyclerView.ViewHolder(bind.root) {
      var  binding:  ResScoreGainer1RowBinding

        init {
            binding =bind
        }
    }

    init {
        // this.students = students
       // this.type = type
    }
}