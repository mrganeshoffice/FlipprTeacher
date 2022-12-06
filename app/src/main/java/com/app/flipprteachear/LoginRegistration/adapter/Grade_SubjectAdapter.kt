package com.app.flipprteachear.LoginRegistration.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.LoginRegistration.GradeSubjectModel
import com.app.flipprteachear.R

public class Grade_SubjectAdapter(var gradeList: ArrayList<GradeSubjectModel>) : RecyclerView.Adapter<Grade_SubjectAdapter.custom>() {
    lateinit var context : Context
    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.res_grade_subject_row, parent, false)
        context =parent.context
        return custom(view)
    }

    override fun onBindViewHolder(holder: custom, position: Int) {

        with(holder){
             tv_grade.setText(gradeList[position].grade)
             tv_subject.setText(gradeList[position].subject)
            iv_remove.setOnClickListener{
                try {
                    gradeList.removeAt(position)
                    notifyDataSetChanged()
                }catch (e:Exception){
                    e.printStackTrace()
                }

            }


         }

    }

    override fun getItemCount(): Int {
        return gradeList.size
    }

    fun updateList(gradeList: java.util.ArrayList<GradeSubjectModel>) {
        this.gradeList =gradeList
        notifyDataSetChanged()
    }

    inner class custom(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_grade: TextView
        var tv_subject: TextView
        var iv_remove: ImageView
        init {
            tv_subject = itemView.findViewById(R.id.tv_subject)
            tv_grade = itemView.findViewById(R.id.tv_grade)
            iv_remove = itemView.findViewById(R.id.iv_remove)

        }
    }


}