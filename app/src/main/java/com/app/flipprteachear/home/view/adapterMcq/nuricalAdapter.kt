package com.app.flipprteachear.home.view.adapterMcq

import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.home.pojo.liveQuesPojo.Detail
import com.app.flipprteachear.utillsa.Constants_All.latetex_dim_13
import com.bumptech.glide.Glide
import katex.hourglass.`in`.mathlib.MathView
import java.util.*

class nuricalAdapter(pojo: Detail, type: String, totalRightAns: Int) :
    RecyclerView.Adapter<nuricalAdapter.ViewHolder>() {
    //PojoQuestions.Detail pojoQuestions;

    var type: String
    var selectedid = -1
    var marksRight = 0
    var totalRightAns: Int
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.res_choosans, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  getFlippMarks( "interpretation");
        holder.formula_one1.setTextSize(latetex_dim_13)
        /*if (type.equals("question", ignoreCase = true)) {
            // getFlippMarks( "interpretation");
            println("totalRightAns.size..$totalRightAns")
            holder.iv_image.visibility = View.GONE
            holder.cardViewIMage.visibility = View.GONE
            //holder.Tv_text.setVisibility(View.VISIBLE);
            holder.formula_one1.setVisibility(View.VISIBLE)
            val ans: String =
                pojo.nInterpretationAnswerDetails.get(position).interpretationAnswers
            holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE)
            holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false))
            holder.tv_sereas.text = "" + (position + 65).toChar()
            holder.formula_one1.setOnClickListener(View.OnClickListener { v: View? -> holder.itemView.performClick() })
            holder.itemView.setOnClickListener {
                ans_n_interpt = "0"
                ans_n_interpt =
                    pojo.nInterpretationAnswerDetails.get(position).interpretationAnsId
                selectedid = position
                n_interpt_marks = questTypeMarks
                //                        selectedanswer.add(pojo.getNInterpretationAnswerDetails().get(position).getInterpretationAnsId());
                notifyDataSetChanged()
            }
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
            //  holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
            if (session_type.equalsIgnoreCase("2")) {
                holder.Card.background = getResources().getDrawable(R.drawable.round_boundary_boost)
                holder.cardoption.setCardBackgroundColor(getResources().getColor(R.color.navicolor_2))
            } else if (session_type.equalsIgnoreCase("3")) {
                holder.Card.background =
                    getResources().getDrawable(R.drawable.round_boundary_revise)
                holder.cardoption.setCardBackgroundColor(getResources().getColor(R.color.navicolor_3))
            } else holder.Card.background =
                getResources().getDrawable(R.drawable.round_boundary_navi)
            if (selectedid == position) {
                selectedanswer = ArrayList<String>()
                selectedanswer.add(
                    pojo.nInterpretationAnswerDetails.get(position).interpretationAnsId
                )
                holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"))
                holder.Card.setBackgroundResource(R.drawable.round_boundary_yellow)
                holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor))
            }
        }
        else if (type.equals("question1", ignoreCase = true)) {
            // getFlippMarks( "visualization");
            println("totalRightAns.size..$totalRightAns")
            holder.iv_image.visibility = View.VISIBLE
            holder.cardViewIMage.visibility = View.VISIBLE
            holder.cardViewIMage.visibility = View.VISIBLE
            holder.Tv_text.visibility = View.GONE
            holder.formula_one1.setVisibility(View.GONE)
            //                String ans = pojo.getNInterpretationAnswerDetails().get(position).getInterpretationAnswers();
            // holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
            Glide.with(getActivity())
                .load(pojo.nVisualizationAnsDetails.get(position).visualizationAnswers)
                .into(holder.iv_image)
            holder.tv_sereas.text = "" + (position + 65).toChar()
            holder.itemView.setOnClickListener { //totalRightAns=0;
                n_visual_marks = questTypeMarks
                ans_n_visual =
                    pojo.nVisualizationAnsDetails.get(position).visualizationAnsId
                selectedid = position
                //                        selectedanswer.add(pojo.getNVisualizationAnsDetails().get(position).getVisualizationAnsId());
                notifyDataSetChanged()
            }
            holder.itemView.setOnLongClickListener { v1: View? ->
                expanded_image.setVisibility(View.VISIBLE)
                Global.zoomRecyclerImage(
                    holder.iv_image,
                    pojo.nVisualizationAnsDetails.get(position).visualizationAnswers,
                    getActivity(),
                    expanded_image,
                    relMain,
                    shortAnimationDuration
                )
                true
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
            holder.tv_sereas.setTextColor(getResources().getColor(R.color.white))
            //  holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
            if (session_type.equalsIgnoreCase("2")) {
                holder.Card.background = getResources().getDrawable(R.drawable.round_boundary_boost)
                holder.cardoption.setCardBackgroundColor(getResources().getColor(R.color.navicolor_2))
            } else if (session_type.equalsIgnoreCase("3")) {
                holder.Card.background =
                    getResources().getDrawable(R.drawable.round_boundary_revise)
                holder.cardoption.setCardBackgroundColor(getResources().getColor(R.color.navicolor_3))
            } else holder.Card.background =
                getResources().getDrawable(R.drawable.round_boundary_navi)
            if (selectedid == position) {
                selectedanswer = ArrayList<String>()
                selectedanswer.add(
                    pojo.nInterpretationAnswerDetails.get(position).interpretationAnsId
                )
                holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"))
                holder.Card.setBackgroundResource(R.drawable.round_boundary_yellow)
                holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor))
            }
        }
        else if (type.equals("answer", ignoreCase = true)) {
            holder.iv_image.visibility = View.GONE
            holder.cardViewIMage.visibility = View.GONE
            // holder.Tv_text.setVisibility(View.VISIBLE);
            holder.formula_one1.setVisibility(View.VISIBLE)
            val ans: String =
                pojo.nInterpretationAnswerDetails.get(position).interpretationAnswers
            holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE)
            holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false))
            holder.tv_sereas.text = "" + (position + 65).toChar()
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
            if (pojo.nInterpretationAnswerDetails.get(position).interpretationRightWrong
                    .equalsIgnoreCase("Right")
            ) {
                holder.ic_right.visibility = View.VISIBLE
                holder.ic_cancel.visibility = View.GONE
            }
            for (i in selectedanswer.indices) {
                if (pojo.nInterpretationAnswerDetails.get(position).interpretationAnsId
                        .equalsIgnoreCase(selectedanswer.get(i))
                ) {
                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"))
                    holder.Card.setBackgroundResource(R.drawable.round_boundary_yellow)
                    holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor))
                    holder.ic_right.visibility = View.GONE
                    holder.ic_cancel.visibility = View.VISIBLE
                    if (pojo.nInterpretationAnswerDetails.get(position)
                            .interpretationRightWrong.equalsIgnoreCase("Right")
                    ) {
                        holder.ic_cancel.visibility = View.GONE
                        holder.ic_right.visibility = View.VISIBLE
                        marksRight++
                    } else {
                        marksRight = 0
                    }
                    if (doubleConfidence == 1) {
                        if (totalRightAns == selectedanswer.size) {
                            if (selectedanswer.size == marksRight) {
                                NumercalObtained_Marks(marksRight, true, 0, 0.0)
                                marksRight = 0
                            } else {
                                tv_quePoints.setTextColor(getResources().getColor(R.color.litered))
                            }
                        } else {
                            tv_quePoints.setTextColor(getResources().getColor(R.color.litered))
                        }
                    } else if (doubleConfidence == 2) {
                        if (totalRightAns == 0) {
                            totalRightAns = 1
                        }
                        if (totalRightAns == selectedanswer.size) {
                            marksRight = if (selectedanswer.size == marksRight) {
                                NumercalObtained_Marks(marksRight, true, 0, 0.0)
                                0
                            } else {
                                NumercalObtained_Marks(totalRightAns, false, 1, 0.0)
                                0
                            }
                        } else {
                            tv_TotalMarks.setText(String.format("%.1f", totalMarks.toFloat()))
                            NumercalObtained_Marks(totalRightAns, false, 0, 0.0)
                            //   marksRight=0;
                        }
                    }
                }
            }
        }
        else if (type.equals("answer1", ignoreCase = true)) {
            holder.iv_image.visibility = View.GONE
            holder.cardViewIMage.visibility = View.VISIBLE
            holder.iv_image.visibility = View.VISIBLE
            holder.Tv_text.visibility = View.GONE
            //                String ans = pojo.getNInterpretationAnswerDetails().get(position).getInterpretationAnswers();
//                holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
            Glide.with(getActivity())
                .load(pojo.nVisualizationAnsDetails.get(position).visualizationAnswers)
                .into(holder.iv_image)
            holder.tv_sereas.text = "" + (position + 65).toChar()
            if (session_type.equalsIgnoreCase("2")) holder.tv_sereas.setBackgroundColor(
                getResources().getColor(
                    R.color.navicolor_2
                )
            ) else if (session_type.equalsIgnoreCase("3")) holder.tv_sereas.setBackgroundColor(
                getResources().getColor(R.color.navicolor_3)
            ) else holder.tv_sereas.setBackgroundColor(
                Color.parseColor("#05334C")
            )
            holder.tv_sereas.setTextColor(getResources().getColor(R.color.white))
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
            if (pojo.nVisualizationAnsDetails.get(position).visualizationRightWrong
                    .equalsIgnoreCase("Right")
            ) {
                holder.ic_right.visibility = View.VISIBLE
                holder.ic_cancel.visibility = View.GONE
            }
            for (i in selectedanswer.indices) {
                if (pojo.nVisualizationAnsDetails.get(position).visualizationAnsId
                        .equalsIgnoreCase(selectedanswer.get(i))
                ) {
                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"))
                    holder.Card.setBackgroundResource(R.drawable.round_boundary_yellow)
                    holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor))
                    holder.ic_right.visibility = View.GONE
                    holder.ic_cancel.visibility = View.VISIBLE
                    if (pojo.nVisualizationAnsDetails.get(position)
                            .visualizationRightWrong.equalsIgnoreCase("Right")
                    ) {
                        holder.ic_cancel.visibility = View.GONE
                        holder.ic_right.visibility = View.VISIBLE
                        marksRight++
                    } else {
                        marksRight = 0
                    }
                    if (doubleConfidence == 1) {
                        if (totalRightAns == selectedanswer.size) {
                            if (selectedanswer.size == marksRight) {
                                NumercalObtained_Marks(marksRight, true, 0, 0.0)
                                marksRight = 0
                            } else {
                                tv_quePoints.setTextColor(getResources().getColor(R.color.litered))
                            }
                        } else {
                            tv_quePoints.setTextColor(getResources().getColor(R.color.litered))
                        }
                    } else if (doubleConfidence == 2) {
                        if (totalRightAns == 0) {
                            totalRightAns = 1
                        }
                        if (totalRightAns == selectedanswer.size) {
                            marksRight = if (selectedanswer.size == marksRight) {
                                NumercalObtained_Marks(marksRight, true, 0, 0.0)
                                0
                            } else {
                                NumercalObtained_Marks(totalRightAns, false, 1, 0.0)
                                0
                            }
                        } else {
                            tv_TotalMarks.setText(String.format("%.1f", totalMarks.toFloat()))
                            NumercalObtained_Marks(totalRightAns, false, 0, 0.0)
                            //   marksRight=0;
                        }
                    }
                }
            }
        }*/
        holder.tv_sereas.text = "" + (position + 65).toChar()
    }

    override fun getItemCount(): Int {
        return 0//pojo.nInterpretationAnswerDetails!!.size()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardoption: CardView
        var cardViewIMage: CardView
        var recycler_Text: RecyclerView
        var RELAtive: RelativeLayout
        var tv_sereas: TextView
        var Card: LinearLayout
        var Tv_text: TextView
        var iv_image: ImageView
        var ic_right: ImageView
        var ic_cancel: ImageView
        var formula_one1: MathView

        init {
            cardViewIMage = itemView.findViewById(R.id.cardViewIMage)
            iv_image = itemView.findViewById(R.id.iv_image)
            ic_right = itemView.findViewById(R.id.ic_right)
            ic_cancel = itemView.findViewById(R.id.ic_cancel)
            cardoption = itemView.findViewById(R.id.cardoption)
            recycler_Text = itemView.findViewById(R.id.recycler_Text)
            RELAtive = itemView.findViewById(R.id.RELAtive)
            tv_sereas = itemView.findViewById(R.id.tv_sereas)
            Card = itemView.findViewById(R.id.Card)
            Tv_text = itemView.findViewById(R.id.Tv_text)
            formula_one1 = itemView.findViewById(R.id.formula_one1)
        }
    }

    init {

        this.type = type
        this.totalRightAns = totalRightAns
    }
}