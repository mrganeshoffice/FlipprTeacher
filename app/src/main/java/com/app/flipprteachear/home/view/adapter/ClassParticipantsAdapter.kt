package com.app.flipprteachear.home.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.home.pojo.liveModel.Student
import com.app.flipprteachear.home.pojo.userDetail.Detail
import com.app.flipprteachear.utillsa.PreferenceManager
import com.bumptech.glide.Glide

public class ClassParticipantsAdapter(var type: Int, var pref: PreferenceManager?) : RecyclerView.Adapter<ClassParticipantsAdapter.custom>() {
    lateinit var context : Context
    private var studentList: List<Detail>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.res_participants_class_row, parent, false)
        context =parent.context
        return custom(view)
    }

    override fun onBindViewHolder(holder: custom, position: Int) {
        holder.tv_number.text = "" + (position + 1)
        studentList!![position].let {
            try {
                Glide.with(context).load( it.image)
                    .placeholder(R.drawable.image).into(holder.iv_image)
            } catch (e: Exception) { e.printStackTrace() }
            holder.tv_studentName.text = it.firstName
        }
    }

    override fun getItemCount(): Int {
        if(studentList!=null) return studentList!!.size
        else return 0
    }



    fun updateListType(studentList: MutableList<Detail>) {
        this.studentList = studentList
        notifyDataSetChanged()
    }

    fun updateList() {

    }

    inner class custom(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_number: TextView
       // var tv_number2: TextView
        var tv_studentName: TextView
        var cardimage: CardView
        var iv_image: ImageView

        init {
            tv_number = itemView.findViewById(R.id.tv_number)
         //   tv_number2 = itemView.findViewById(R.id.tv_number2)
            tv_studentName = itemView.findViewById(R.id.tv_studentName)
            iv_image = itemView.findViewById(R.id.iv_image)
            cardimage = itemView.findViewById(R.id.cardimage)
        }
    }

    init {
        // this.students = students
        this.type = type
    }
}