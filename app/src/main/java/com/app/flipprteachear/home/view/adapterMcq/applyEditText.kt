package com.app.flipprteachear.home.view.adapterMcq


import android.content.Context
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.home.pojo.liveQuesPojo.Detail
import java.util.*
import com.app.flipprteachear.R
class applyEditText(pojo: Detail, type: String, marksRight: Int,
                    var editTagsList: ArrayList<String>) :
    RecyclerView.Adapter<applyEditText.ViewHolder>() {
    var pojo: Detail
    var type: String
    var selectedId = -1
    var marksRight: Int
    var supSubTag: String? = null
    var context: Context?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_n_application_detail_row_next, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  System.out.print("lleditText..oo."+holder.et_value1.getText());
        //  System.out.print("lleditText..oo."+holder.et_value1.getText());
        holder.rell_SupSup.visibility = View.GONE
        if (type.equals("question2", ignoreCase = true)) {


            if (!pojo!!.n_application_details?.get(position)!!.quantity.equals("")) {
                holder.llSpinner.visibility = View.GONE
                holder.lleditText.visibility = View.VISIBLE
                holder.tv_value1.setText(
                    pojo.n_application_details?.get(position)!!.quantity.toString() + "   = "
                )
                holder.tv_meter1.setText(Html.fromHtml(" " + pojo.n_application_details?.get(position)!!.unit), TextView.BufferType.SPANNABLE)

                //System.out.println("setEquation1aa..." +".."+ holder.et_value1.getText().toString()+" ......"+supSubTag);
                if (supSubTag == null) {
                    supSubTag = ""
                }
                val sAns: String = editTagsList.get(position)
                //println("setEquation1aa.....$putvalue1 ......$supSubTag-------sAns $sAns")
                holder.et_value1.setText(Html.fromHtml(sAns), TextView.BufferType.SPANNABLE)
                //holder.et_value1.onTouchEvent(new MotionEvent());


            }

        }


    }

    override fun getItemCount(): Int {
        return if (pojo.n_application_details != null) {
            pojo.n_application_details!!.size
        } else {
            0
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lleditText: LinearLayout
        var llSpinner: LinearLayout
        var et_value1: EditText

        /*, et_value2*/
        var etValue2: EditText
        var iv_tick1: ImageView
        var iv_tick3: ImageView
        var iv_cross1: ImageView
        var iv_cross3 // iv_tick2,iv_cross2, iv_cross4 , iv_tick4
                : ImageView
        var tv_nature: TextView
        var tv_meter1: TextView
        var tv_value1: TextView
        var tvSub: TextView
        var tvSup: TextView
        var tvDone: TextView
        var Card: LinearLayout
        var spinner1: Spinner
        var spinner2: Spinner? = null
        var rell_SupSup: RelativeLayout

        init {
            et_value1 = itemView.findViewById(R.id.et_value1)
            tv_value1 = itemView.findViewById(R.id.tv_value1)
            iv_cross1 = itemView.findViewById(R.id.iv_cross1)
            iv_cross3 = itemView.findViewById(R.id.iv_cross3)
            iv_tick3 = itemView.findViewById(R.id.iv_tick3)
            iv_tick1 = itemView.findViewById(R.id.iv_tick1)
            lleditText = itemView.findViewById(R.id.lleditText)
            llSpinner = itemView.findViewById(R.id.llSpinner)
            spinner1 = itemView.findViewById(R.id.spinner1)
            //  tv_sereas = itemView.findViewById(R.id.tv_sereas);
            // tv_qustion = itemView.findViewById(R.id.tv_qustion);
            tv_meter1 = itemView.findViewById(R.id.tv_meter1)
            tvSub = itemView.findViewById(R.id.tv_sub)
            tvSup = itemView.findViewById(R.id.tv_sup)
            etValue2 = itemView.findViewById(R.id.et_value2)
            tvDone = itemView.findViewById(R.id.tv_Done)
            rell_SupSup = itemView.findViewById(R.id.rellSupSup)
            tv_nature = itemView.findViewById(R.id.tv_nature)
            Card = itemView.findViewById(R.id.Card)
        }
    }

    init {
        this.pojo = pojo
        this.type = type
        this.marksRight = marksRight
    }
}