package com.app.flipprteachear.home.view.adapterMcq

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.home.pojo.liveQuesPojo.Detail
import java.util.ArrayList

class applyAdapter(pojo: Detail, type: String, var adapter2:applyEditText,
                   var rellSupSup: RelativeLayout? = null, var editTagsList: ArrayList<String>) :
    RecyclerView.Adapter<applyAdapter.ViewHolder>() {
    private val mContext: Context? = null
    var pojoQuestions: Detail? = null
    var pojo: Detail
    var type: String
    var marksRight = 0
    var context: Context?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_n_application_detail_row, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        rellSupSup?.setVisibility(View.GONE)
        if (type.equals("question2", ignoreCase = true)) {
            adapter2 = applyEditText(pojo, "question2", marksRight, editTagsList)
            holder.rv_editText.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            holder.rv_editText.adapter = adapter2
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    //        public void setEquation(String sup_SubTag) {
    //            System.out.println("setEquation..." +ans_n_aplly +".."+ sup_SubTag);
    //        }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rv_editText: RecyclerView
        var rv_spinner: RecyclerView

        init {
            rv_editText = itemView.findViewById(R.id.rv_editText)
            rv_spinner = itemView.findViewById(R.id.rv_spinner)
        }
    }

    init {
        this.pojo = pojo
        this.type = type
    }
}