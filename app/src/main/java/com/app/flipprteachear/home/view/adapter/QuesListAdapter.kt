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
import com.app.flipprteachear.home.ForClassPageChange

public class QuesListAdapter(var quesList: ForClassPageChange) : RecyclerView.Adapter<QuesListAdapter.custom>() {
    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.res_ques_list_row, parent, false)
        context =parent.context
        return custom(view)
    }

    override fun onBindViewHolder(holder: custom, position: Int) {
        holder.tv_number.text = "" + (position + 1)


       /* try {
            Glide.with(context)
                .load( "")
                .placeholder(R.drawable.image)
                .into(holder.iv_image)
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
        /* if (type == 1) {
             if (students[position].getTotalPoints() == null) {
                 holder.tv_number2.text = "0°P"
             } else {
                 holder.tv_number2.setText(
                     students[position].getTotalPoints().toDouble() as Int.toString() + "°P"
                 )
             }
         } else if (type == 2) {
             holder.tv_number2.text =
                 String.format("%.1f", students[position].getSyllabusComp()).toString() + "% S"
         } else {
             holder.tv_number2.text =
                 String.format("%.1f", students[position].getAvg_confid()).toString() + " C"
         }
         holder.tv_studentName.setText(students[position].getFirstName().toString() + " ")*/



    }

    override fun getItemCount(): Int {
        return 30
    }



    fun updateListType(sType: Int) {
        //this.type = sType
        notifyDataSetChanged()
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
       // this.type = type
    }
}