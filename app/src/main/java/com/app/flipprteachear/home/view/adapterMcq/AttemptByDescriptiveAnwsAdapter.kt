package com.app.flipprteachear.home.view.adapterMcq

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted.UserDetail
import com.app.flipprteachear.home.view.fragments.AllMcq_Fragment
import java.util.*

public class AttemptByDescriptiveAnwsAdapter(

    var holders: AllMcq_Fragment.descriptiveAnswerAdapter.ViewHolder
) : RecyclerView.Adapter<AttemptByDescriptiveAnwsAdapter.custom>() {
    lateinit var context : Context
    private var userDetail: ArrayList<UserDetail>?=null
    private var isRvImageFull = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.res_attempt_row, parent, false)
        context =parent.context
        return custom(view)
    }

    override fun onBindViewHolder(holder: custom, position: Int) {
        if(userDetail!=null)
         holder.tvNumber.text = userDetail!!.size.toString()//"" + (position + 1)
        else holder.tvNumber.text = "0"
       /* try {
            Glide.with(context)
                .load( "")
                .placeholder(R.drawable.image)
                .into(holder.iv_image)
               ff .iv_image.load(""){
                    crossfade(true)
                    placeholder(R.drawable.image)
                    transformations()
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
        holder.itemView.setOnClickListener {
            isRvImageFull = !isRvImageFull
            if (isRvImageFull) holders.rvImageFull.setVisibility(View.VISIBLE) else holders.rvImageFull.setVisibility(
                View.GONE
            )
        }

    }

    override fun getItemCount(): Int {
        return 1;//if(userDetail.isNullOrEmpty()) 0 else userDetail!!.size
    }
    fun updateListType(UserDetail: ArrayList<UserDetail>) {
         this.userDetail = UserDetail
        notifyDataSetChanged()
    }

    inner class custom(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNumber: TextView

        init {
            tvNumber = itemView.findViewById(R.id.tvNumber)
         //   tv_number2 = itemView.findViewById(R.id.tv_number2)
//            tv_studentName = itemView.findViewById(R.id.tv_studentName)
//            iv_image = itemView.findViewById(R.id.iv_image)
//            cardimage = itemView.findViewById(R.id.cardimage)
        }
    }

    init {
        // this.students = students
       // this.type = type
    }
}