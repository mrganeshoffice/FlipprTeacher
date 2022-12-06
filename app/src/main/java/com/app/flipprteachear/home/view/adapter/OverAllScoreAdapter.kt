package com.app.flipprteachear.home.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.ResScoreGainer1RowBinding
import com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted.UserDetail
import com.bumptech.glide.Glide

public class OverAllScoreAdapter(var adapterType: String) : RecyclerView.Adapter<OverAllScoreAdapter.custom>() {
    lateinit var context : Context
    var userDetail: MutableList<UserDetail>?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        val binding : ResScoreGainer1RowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.res_score_gainer1_row, parent, false)
        context =parent.context
        return custom(binding)
    }

    override fun onBindViewHolder(holder: custom, position: Int) {
        with(holder.binding){
            tvNumber.text = "" + (position + 1)


                ivStreakActive.visibility =View.GONE
                ivDoubleConfidence.visibility =View.GONE


            userDetail!![position].let {

                Glide.with(context).load(it.image).into(ivImage)
                Log.w("TAG", "onBindViewHolder:anssAll.. "+ it.submitDetail.quesDetail.ansPoints)
                val str =String.format("%.1f", it.submitDetail.quesDetail.ansPoints.toFloat())
                   // .replace("+","").replace("-",""))
                tvRank.text =""
                tvRank.text = str// if(it.submitDetail.quesDetail.ansStatus.equals("0")) "-$str" else str
               tvStudentName.text  = it.firstName



            }
        }
    }

    override fun getItemCount(): Int {
        if(userDetail!=null) return userDetail!!.size
            else return 0
    }
    fun updateListType(userDetail: MutableList<UserDetail>) {
        this.userDetail = userDetail
        notifyDataSetChanged()
    }

    inner class custom(bind: ResScoreGainer1RowBinding) : RecyclerView.ViewHolder(bind.root) {
      var  binding:  ResScoreGainer1RowBinding
      init {
            binding =bind
        }
    }


}