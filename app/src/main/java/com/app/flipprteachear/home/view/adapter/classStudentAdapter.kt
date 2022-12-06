package com.app.flipprteachear.home.view.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.flipprteachear.R
import com.app.flipprteachear.home.ForStartLive
import com.app.flipprteachear.home.pojo.Student

public class classStudentAdapter(var type: Int, var startLive: ForStartLive) : RecyclerView.Adapter<classStudentAdapter.custom>() {
    lateinit var context : Context
    private var students: List<Student>?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.res_students_class, parent, false)
        context =parent.context
        return custom(view)
    }

    override fun onBindViewHolder(holder: custom, position: Int) {
        holder.tv_number.text = "" + (position + 1)
        try {
            students!!.distinct()[position].let {
//            Glide.with(context)
//                .load( it.image)
//                .placeholder(R.drawable.image)
//                .into(holder.iv_image)

                try {
                    holder.iv_image.load(it.image){
                        // crossfade(true)
                        placeholder(R.drawable.image)
                        // transformations()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (type == 1) {
            if (students!!.sortedByDescending { it.total_points}[position].total_points == null) {
                holder.tv_number2.text = "0°P"
            } else {
                holder.tv_number2.text = String.format("%.1f",students!!.sortedByDescending { it.total_points }[position].total_points!!.toFloat())+"°P"
            }
        } else if (type == 2) {
            holder.tv_number2.text =
                String.format("%.1f", students!!.sortedByDescending { it.homework_comp }[position].homework_comp)  + "% H"
        }else if (type == 3) {
            holder.tv_number2.text =
                String.format("%.1f", students!!.sortedByDescending { it.syllabus_comp }[position].syllabus_comp)  + "% S"
        }else if (type == 4) {
            holder.tv_number2.text =
                String.format("%.1f", students!!.sortedByDescending { it.avg_confid }[position].avg_confid)  + " C"
        }else if (type == 5){
            holder.tv_number2.text =
                String.format("%.1f", students!!.sortedByDescending { it.syllabus_mastered }[position].syllabus_mastered)  + "% S"
        }
//         else {
//             holder.tv_number2.text =
//                 String.format("%.1f", students!![position].avg_confid).toString() + " C"
//         }
        holder.tv_studentName.setText(students!!.distinct()[position].first_name.toString() + " ")

        holder.cardimage.setOnClickListener{
            val bundle = Bundle()
            bundle.putSerializable("student", students!!.distinct()[position])
            startLive.startLive("from_home", bundle)
        }

    }

    override fun getItemCount(): Int {
        return if(students!=null) students!!.size else 0
    }
    fun updateListType(students: List<Student>) {
        this.students = students.distinct()
        notifyDataSetChanged()
    }
    fun updateType(type:Int) {
        this.type = type
        notifyDataSetChanged()
    }

    inner class custom(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_number: TextView
        var tv_number2: TextView
        var tv_studentName: TextView
        var cardimage: CardView
        var iv_image: ImageView

        init {
            tv_number = itemView.findViewById(R.id.tv_number)
            tv_number2 = itemView.findViewById(R.id.tv_number2)
            tv_studentName = itemView.findViewById(R.id.tv_studentName)
            iv_image = itemView.findViewById(R.id.iv_image)
            cardimage = itemView.findViewById(R.id.cardimage)
        }
    }
}