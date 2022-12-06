package com.app.flipprteachear.home.view.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.home.pojo.QuesIndicatorModel

public class QuesIndecatorAdapter(var arrayList: ArrayList<QuesIndicatorModel>) : RecyclerView.Adapter<QuesIndecatorAdapter.custom>() {
    lateinit var context : Context
    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.res_ques_indicator_row, parent, false)
        context =parent.context
        return custom(view)
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

         with(holder){

             if(selectedPosition==position){
                 iv_page.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_pink) )
                 iv_page.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context ,R.color.sky_blue_1DAFC6)) )

             }else{
                  iv_page.setBackground(ContextCompat.getDrawable(context,R.drawable.ic_circle_grey) )
                  iv_page.setBackgroundTintList(ColorStateList.valueOf( Color.parseColor("#5F5E5E")) )
             }

         }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun updateList(position: Int, arrayList: ArrayList<QuesIndicatorModel>) {
        selectedPosition=position
        this.arrayList=arrayList

        notifyDataSetChanged()
    }

    inner class custom(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_page: ImageView
        var cardNumber: RelativeLayout
        init {
            iv_page = itemView.findViewById(R.id.iv_page)
            cardNumber = itemView.findViewById(R.id.cardNumber)

        }
    }


}