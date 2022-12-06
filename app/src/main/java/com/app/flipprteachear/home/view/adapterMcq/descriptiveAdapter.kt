package com.app.flipprteachear.home.view.adapterMcq

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.bumptech.glide.Glide
import java.util.*

class descriptiveAdapter(pojo: List<PojoQuestions.DescriptiveVisualizationDetail>, type: String) :
    RecyclerView.Adapter<descriptiveAdapter.ViewHolder>() {
    var pojoQuestions: PojoQuestions.Detail? = null
    var pojo: List<PojoQuestions.DescriptiveVisualizationDetail>
    var type: String
    var selectedid = -1
    var marksRight = 0
    var totalRightAns = 0
    var context: Context?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.res_vlsualistion, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (pojo[position].imgType.equals("Right")) {
            totalRightAns++
        }
      /*  if (type.equals("question", ignoreCase = true)) {
            Glide.with()
                .load(pojo[position].descImages)
                .into(holder.Image_concave)
            holder.tv_sereas.text = "" + (position + 65).toChar()
            getLongPressItemImage_descriptive(holder, pojo[position].descImages)
            holder.itemView.setOnClickListener { view: View? ->
                selectedid = position
                desc_visualMarks = "0"
                desc_visualMarks = questTypeMarks
                notifyDataSetChanged()
            }
            if (session_type.equalsIgnoreCase("2")) holder.tv_sereas.setBackgroundColor(
                getResources().getColor(
                    R.color.navicolor_2
                )
            ) else if (session_type.equalsIgnoreCase("3")) holder.tv_sereas.setBackgroundColor(
                getResources().getColor(R.color.navicolor_3)
            ) else holder.tv_sereas.setBackgroundColor(
                Color.parseColor("#05334C")
            )
            // holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
            if (session_type.equalsIgnoreCase("2")) {
                holder.Card.background = getResources().getDrawable(R.drawable.round_boundary_boost)
                holder.cardoption.setCardBackgroundColor(getResources().getColor(R.color.navicolor_2))
            } else if (session_type.equalsIgnoreCase("3")) {
                holder.Card.background =
                    getResources().getDrawable(R.drawable.round_boundary_revise)
                holder.cardoption.setCardBackgroundColor(getResources().getColor(R.color.navicolor_3))
            } else holder.Card.background =
                getResources().getDrawable(R.drawable.round_boundary_navi)
            holder.tv_sereas.setTextColor(getResources().getColor(R.color.white))
            if (selectedid == position) {
                selectedanswer = ArrayList<String>()
                des_visual_ = pojo[position].rightImgDescId
                selectedanswer.add(pojo[position].rightImgDescId)
                holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"))
                holder.Card.setBackgroundResource(R.drawable.round_boundary_yellow)
                holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor))
            }
        }
        else {
            Glide.with(getActivity())
                .load(pojo[position].descImages)
                .into(holder.Image_concave)
            getLongPressItemImage_descriptive(holder, pojo[position].descImages)
            holder.tv_sereas.text = "" + (position + 65).toChar()
            holder.tv_sereas.setTextColor(getResources().getColor(R.color.white))
            if (session_type.equalsIgnoreCase("2")) holder.tv_sereas.setBackgroundColor(
                getResources().getColor(
                    R.color.navicolor_2
                )
            ) else if (session_type.equalsIgnoreCase("3")) holder.tv_sereas.setBackgroundColor(
                getResources().getColor(R.color.navicolor_3)
            ) else holder.tv_sereas.setBackgroundColor(
                Color.parseColor("#05334C")
            )
            holder.Card.setBackgroundResource(R.drawable.round_boundary_whiteonly)
            if (pojo[position].imgType.equalsIgnoreCase("Right")) {
                holder.ic_right.visibility = View.VISIBLE
                holder.ic_cancel.visibility = View.GONE
            }
            for (i in selectedanswer.indices) {
                if (pojo[position].rightImgDescId.equalsIgnoreCase(selectedanswer.get(i))) {
                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"))
                    holder.Card.setBackgroundResource(R.drawable.round_boundary_yellow_with_white)
                    holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor))
                    holder.ic_right.visibility = View.GONE
                    holder.ic_cancel.visibility = View.VISIBLE
                    if (pojo[position].imgType.equalsIgnoreCase("Right")) {
                        holder.ic_cancel.visibility = View.GONE
                        holder.ic_right.visibility = View.VISIBLE
                        marksRight++
                    } else {
                        marksRight = 0
                    }

                    *//*textMcqAnsStatus ="1";
                                OnSingleTextMcq(true, marksRight, 0);
//                                flipp_Obtained_Marks(marksRight, true, 0);
//                                marksRight=0;
                            }else {
                                textMcqAnsStatus ="0";
                                OnSingleTextMcq(false, marksRight, 0);
                            }
                        }else {
                            textMcqAnsStatus ="0";
                            OnSingleTextMcq(false, marksRight, 0);
                        }*//*if (doubleConfidence == 1) {
                        if (totalRightAns == selectedanswer.size) {
                            if (selectedanswer.size == marksRight) {
                                textMcqAnsStatus = "1"
                                NumercalObtained_Marks(marksRight, true, 0, 0.0)
                                marksRight = 0
                            } else {
                                textMcqAnsStatus = "0"
                                tv_quePoints.setTextColor(getResources().getColor(R.color.litered))
                                //NumercalObtained_Marks(marksRight, false, 0,0);
                            }
                        } else {
                            textMcqAnsStatus = "0"
                            tv_quePoints.setTextColor(getResources().getColor(R.color.litered))
                            //NumercalObtained_Marks(marksRight, false, 0,0);
                        }
                    } else if (doubleConfidence == 2) {
                        if (totalRightAns == 0) {
                            totalRightAns = 1
                        }
                        if (totalRightAns == selectedanswer.size) {
                            if (selectedanswer.size == marksRight) {
                                textMcqAnsStatus = "1"
                                NumercalObtained_Marks(marksRight, true, 0, 0.0)
                                marksRight = 0
                            } else {
                                textMcqAnsStatus = "0"
                                NumercalObtained_Marks(marksRight, false, 1, 0.0)
                                marksRight = 0
                            }
                        } else {
                            textMcqAnsStatus = "0"
                            tv_TotalMarks.setText(String.format("%.1f", totalMarks.toFloat()))
                            NumercalObtained_Marks(marksRight, false, 0, 0.0)
                            //marksRight=0;
                        }
                    }
                }
            }
            //                if(selectedanswer.size()==marksRight){
//                   // int marksRight =0;
//                    flipp_Obtained_Marks(marksRight, true, 1);
//                    marksRight=0;
//                }
        }*/
    }

    override fun getItemCount(): Int {
        return pojo.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_sereas: TextView
        var Card: LinearLayout
        var Image_concave: ImageView
        var ic_right: ImageView
        var ic_cancel: ImageView
        var cardoption: CardView

        init {
            ic_right = itemView.findViewById(R.id.ic_right)
            ic_cancel = itemView.findViewById(R.id.ic_cancel)
            tv_sereas = itemView.findViewById(R.id.tv_sereas)
            Card = itemView.findViewById(R.id.Card)
            Image_concave = itemView.findViewById(R.id.Image_concave)
            cardoption = itemView.findViewById(R.id.cardoption)
        }
    }

    init {
        this.pojo = pojo!!
        this.type = type
    }
}
