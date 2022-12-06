package com.app.flipprteachear.home.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.ResClassPagesRowBinding
import com.app.flipprteachear.home.ForClassPageChange
import com.app.flipprteachear.home.pojo.Detail

public class ClassPageAdapter(var classPage: ForClassPageChange) : RecyclerView.Adapter<ClassPageAdapter.custom>() {
    lateinit var context : Context
    var selectedPosition = 0
    var details: List<Detail>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        //  val view: View = LayoutInflater.from(parent.context).inflate(R.layout.res_class_pages_row, parent, false)
        val binding : ResClassPagesRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.res_class_pages_row, parent, false)
        binding.root.layoutParams = ViewGroup.LayoutParams((parent.width*0.94).toInt(), ViewGroup.LayoutParams.MATCH_PARENT)
        context =parent.context
        return custom(binding)
    }

    override fun onBindViewHolder(holder: custom, position: Int) {

        /* try {
             Glide.with(context)
                 .load( "")
                 .placeholder(R.drawable.image)
                 .into(holder.iv_image)
         } catch (e: Exception) {
             e.printStackTrace()
         }*/

        with(holder.binding){
            details!!.distinct()[position].let {
                Log.d("gggg", "onBindViewHolder: "+it.class_name)
                tvClass.text=it.class_name
                tvChapterName.text ="${it.subject_name+" | "+ it.students_count} Students"
                tvAvergPoints.text = "${it.avg_points}"
                tvSyllabus.text="${it.avg_syllabus_done}%"
                tvConfidenc.text=  "${it.avg_confid}/10"
                seekBar.progress = it.avg_syllabus_done!!
                tvHomework.text = "${it.avg_homework}%"
                tvSyllabMaster.text = "${it.avg_syllabus_mastered}%"
            }

        }

    }

    override fun getItemCount(): Int {
        Log.d("tttttyy", "setTeacherClassData: 1"+details?.size)
        return if(details!=null) details!!.size else 0
    }

    fun updateList(details: List<Detail>) {
        this.details =details.distinct()
        notifyDataSetChanged()
        Log.d("updaed", "updateList: "+details.size)
    }

    inner class custom(bind: ResClassPagesRowBinding) : RecyclerView.ViewHolder(bind.root) {

        val binding: ResClassPagesRowBinding
        init {
            binding = bind

        }
    }


}