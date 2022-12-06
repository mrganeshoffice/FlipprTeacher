package com.app.flipprteachear.home.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted.UserDetail
import com.bumptech.glide.Glide
import java.util.ArrayList

class BottomDialogAdapterAnswersError:
    RecyclerView.Adapter<BottomDialogAdapterAnswersError.custom>() {
    lateinit var context : Context
    private var userDetail: ArrayList<UserDetail>?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        //  val view: View = LayoutInflater.from(parent.context).inflate(R.layout.res_attempt_full_row, parent, false)
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.res_score_gainer1_row, parent, false)

        context =parent.context
        return custom(view)
    }

    override fun onBindViewHolder(holder: custom, position: Int) {
        holder.tv_number.text = "" + (position + 1)
        userDetail!![position].let {
            try {
                Glide.with(context).load( it.image+"")
                    .placeholder(R.drawable.image).into(holder.iv_image)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            try {
                holder.tv_studentName.setText(if(it.firstName==null)"" else it.firstName)
                holder.tv_studentName.setTextColor(ContextCompat.getColor(context,R.color.Black))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            try {
                if(it.submitDetail.quesDetail.isStreakActive.equals("0"))
                    holder.iv_Streak_active.visibility = View.GONE
                else holder.iv_Streak_active.visibility = View.VISIBLE

                if(it.submitDetail.quesDetail.isDoubleConfidence.equals("0"))
                    holder.iv_doubleConfidence.visibility = View.GONE
                else holder.iv_doubleConfidence.visibility = View.VISIBLE

            } catch (e: Exception) {
                e.printStackTrace()
            }

            Log.w("TAG", "onBindViewHolder: "+ it.submitDetail.quesDetail.ansPoints)
            val str =String.format("%.1f", it.submitDetail.quesDetail.ansPoints
                .replace("+","").replace("-","").toFloat())

            holder.tvRank.text =  if(it.submitDetail.quesDetail.ansStatus.equals("0")) "-$str" else str

        }



    }

    override fun getItemCount(): Int {
        return if(userDetail.isNullOrEmpty()) 0 else userDetail!!.size
    }
    fun updateListType(UserDetail: ArrayList<UserDetail>) {
        this.userDetail = UserDetail
        notifyDataSetChanged()
    }

    inner class custom(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_number: TextView

        var tv_studentName: TextView
        var tvRank: TextView
        var cardimage: CardView
        var iv_image: ImageView
        var iv_doubleConfidence: ImageView
        var iv_Streak_active: ImageView

        init {
            tv_number = itemView.findViewById(R.id.tv_number)
            tvRank = itemView.findViewById(R.id.tv_rank)
            tv_studentName = itemView.findViewById(R.id.tv_studentName)
            iv_image = itemView.findViewById(R.id.iv_image)
            cardimage = itemView.findViewById(R.id.cardimage)
            iv_doubleConfidence = itemView.findViewById(R.id.iv_doubleConfidence)
            iv_Streak_active = itemView.findViewById(R.id.iv_Streak_active)
        }
    }

    init {
        // this.students = students
        // this.type = type
    }
}
