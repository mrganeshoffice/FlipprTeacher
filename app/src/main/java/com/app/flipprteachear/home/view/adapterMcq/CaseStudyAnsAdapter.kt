package com.app.flipprteachear.home.view.adapterMcq

import android.graphics.Color
import android.text.Html
import android.util.Log
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
import katex.hourglass.`in`.mathlib.MathView
import java.util.*

class CaseStudyAnsAdapter(
    pojo: Detail,
    type: String,
    totalRightAns: Int,
    questiontype: String
) :
    RecyclerView.Adapter<CaseStudyAnsAdapter.ViewHolder>() {
    //PojoQuestions.Detail pojoQuestions;
    var pojo: Detail
    var type: String
    var questiontype: String
    var selectedid = -1
    var marksRight = 0
    var totalRightAns: Int
    var first = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.res_choosans, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  getFlippMarks( "interpretation");
        holder.formula_one1.setTextSize(latetex_dim_13)
       /* if (type.equals("question", ignoreCase = true)) {
            holder.iv_image.visibility = View.GONE
            holder.cardViewIMage.visibility = View.GONE
            //holder.Tv_text.setVisibility(View.VISIBLE);
            holder.formula_one1.setVisibility(View.VISIBLE) //caseStudyAnsSelectList
            var ans = ""
            ans = if (caseStdyI == 4) pojo.getCaseStudyQuesDetails().get(caseStdyI - 1)
                .getCasestudyMcqs().get(position)
                .getRightTxtAns1() else pojo.getCaseStudyQuesDetails().get(caseStdyI)
                .getCasestudyMcqs().get(position).getRightTxtAns1()
            holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE)
            holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false))
            holder.tv_sereas.text = "" + (position + 65).toChar()
            holder.formula_one1.setOnClickListener(View.OnClickListener { v: View? -> holder.itemView.performClick() })
            holder.itemView.setOnClickListener {
                if (questiontype.equals("multiple", ignoreCase = true)) {
                    if (selectedanswer.contains(
                            pojo.getCaseStudyQuesDetails().get(caseStdyI).getCasestudyMcqs()
                                .get(position).getRightTxtAnsId()
                        )
                    ) {
                        selectedanswer.remove(
                            pojo.getCaseStudyQuesDetails().get(caseStdyI).getCasestudyMcqs()
                                .get(position).getRightTxtAnsId()
                        )
                        notifyDataSetChanged()
                    } else {
                        first = 2
                        totalRightAns = 1
                        selectedanswer.add(
                            pojo.getCaseStudyQuesDetails().get(caseStdyI).getCasestudyMcqs()
                                .get(position).getRightTxtAnsId()
                        )
                        notifyDataSetChanged()
                    }
                } else {
                    totalRightAns = 1
                    selectedid = position
                    notifyDataSetChanged()
                }
                // caseStudyAnsSelectList.add(new CaseStudyAnsSelected(caseStdyI+"", pojo.getCaseStudyQuesDetails().get(0).getCasestudyMcqs().get(position).getRightTxtAns1()));
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
            if (session_type.equalsIgnoreCase("2")) {
                holder.Card.background = getResources().getDrawable(R.drawable.round_boundary_boost)
                holder.cardoption.setCardBackgroundColor(getResources().getColor(R.color.navicolor_2))
            } else if (session_type.equalsIgnoreCase("3")) {
                holder.Card.background =
                    getResources().getDrawable(R.drawable.round_boundary_revise)
                holder.cardoption.setCardBackgroundColor(getResources().getColor(R.color.navicolor_3))
            }
            holder.Card.setBackgroundResource(R.drawable.round_boundary_navi)
            if (questiontype.contains("multiple")) {
                for (i in selectedanswer.indices) {
                    if (pojo.getCaseStudyQuesDetails().get(caseStdyI).getCasestudyMcqs()
                            .get(position).getRightTxtAnsId()
                            .equalsIgnoreCase(selectedanswer.get(i))
                    ) {
                        holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"))
                        holder.Card.setBackgroundResource(R.drawable.round_boundary_yellow)
                        holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor))
                    }
                }
            } else {
                if (selectedid == position) {
                    selectedanswer = ArrayList<String>()
                    selectedanswer.add(
                        pojo.getCaseStudyQuesDetails().get(caseStdyI).getCasestudyMcqs()
                            .get(position).getRightTxtAnsId()
                    )
                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"))
                    holder.Card.setBackgroundResource(R.drawable.round_boundary_yellow)
                    holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor))
                }
            }
        }
        else {
            holder.formula_one1.setVisibility(View.VISIBLE) //caseStudyAnsSelectList
            var ans = ""
            ans = if (caseStdyI == 4) pojo.getCaseStudyQuesDetails().get(caseStdyI - 1)
                .getCasestudyMcqs().get(position)
                .getRightTxtAns1() else pojo.getCaseStudyQuesDetails().get(caseStdyI)
                .getCasestudyMcqs().get(position).getRightTxtAns1()
            holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE)
            holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false))
            holder.tv_sereas.setTextColor(getResources().getColor(R.color.white))
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

            //   holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
            if (session_type.equalsIgnoreCase("2")) {
                holder.Card.background = getResources().getDrawable(R.drawable.round_boundary_boost)
                holder.cardoption.setCardBackgroundColor(getResources().getColor(R.color.navicolor_2))
            } else if (session_type.equalsIgnoreCase("3")) {
                holder.Card.background =
                    getResources().getDrawable(R.drawable.round_boundary_revise)
                holder.cardoption.setCardBackgroundColor(getResources().getColor(R.color.navicolor_3))
            } else holder.Card.background =
                getResources().getDrawable(R.drawable.round_boundary_navi)
            if (pojo.getCaseStudyQuesDetails().get(caseStdyI - 1).getCasestudyMcqs().get(position)
                    .getTypes().equalsIgnoreCase("Right")
            ) {
                holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"))
                holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor))
                holder.Card.setBackgroundResource(R.drawable.round_boundary_yellow)
                holder.ic_right.visibility = View.VISIBLE
                holder.ic_cancel.visibility = View.GONE
            }
            val mStringArray: Array<Any> = caseStudyselectedanswer.toTypedArray()
            for (i in mStringArray.indices) {
                ansMcq = mStringArray[i] as String
                Log.d("ansMcq_string is", mStringArray[i] as String + "..." + ansMcq)
            }
            for (i in caseStudyselectedanswer.indices) {
                if (pojo.getCaseStudyQuesDetails().get(caseStdyI - 1).getCasestudyMcqs()
                        .get(position).getRightTxtAnsId()
                        .equalsIgnoreCase(caseStudyselectedanswer.get(i))
                ) {
                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"))
                    holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor))
                    holder.Card.setBackgroundResource(R.drawable.round_boundary_yellow)
                    holder.ic_right.visibility = View.GONE
                    holder.ic_cancel.visibility = View.VISIBLE
                    if (pojo.getCaseStudyQuesDetails().get(caseStdyI - 1).getCasestudyMcqs()
                            .get(position).getTypes().equalsIgnoreCase("Right")
                    ) {
                        holder.ic_cancel.visibility = View.GONE
                        holder.ic_right.visibility = View.VISIBLE
                        marksRight++
                    } else {
                        marksRight = 0
                        //totalRightAns=0;
                        //flipp_Obtained_Marks(marksRight, false);
                    }
                    println("totalRightAns.sizelast.." + totalRightAns + "..." + selectedanswer.size)
                    if (doubleConfidence == 1) {
                        if (totalRightAns == selectedanswer.size) {
                            if (selectedanswer.size == marksRight) {
                                textMcqAnsStatus = "1"
                                On_CaseStudyTextMcq(true, marksRight, first)
                                //                                      flipp_Obtained_Marks(marksRight, true, 0);
//                                    marksRight=0;
                            } else {
                                textMcqAnsStatus = "0"
                                On_CaseStudyTextMcq(false, marksRight, first)
                            }
                        } else {
                            textMcqAnsStatus = "0"
                            On_CaseStudyTextMcq(false, marksRight, first)
                        }
                    } else if (doubleConfidence == 2) {
                        if (totalRightAns == 0) {
                            totalRightAns = 1
                        }
                        if (totalRightAns == selectedanswer.size) {
                            if (selectedanswer.size == marksRight) {
                                textMcqAnsStatus = "1"
                                On_CaseStudyTextMcq(true, marksRight, first)
                                //                                    tv_TotalMarks.setText(totalMarks);
//                                    flipp_Obtained_Marks(marksRight, true, 0);
//                                    marksRight=0;
                            } else {
                                if (i == totalRightAns - 1) {
                                    textMcqAnsStatus = "0"
                                    On_CaseStudyTextMcq(true, marksRight, first)
                                    // flipp_Obtained_Marks(totalRightAns, false, first);
                                }
                                //marksRight=0;
                            }
                        } else {
                            //if (i == totalRightAns - 1) {
                            textMcqAnsStatus = "0"
                            On_CaseStudyTextMcq(true, marksRight, first)
                            //                                    tv_TotalMarks.setText(totalMarks);
                            //flipp_Obtained_Marks(totalRightAns, false, first);
                            //marksRight=0;
                            // }
                        }
                    }
                }
            }
        }*/
        holder.tv_sereas.text = "" + (position + 65).toChar()
    }

    override fun getItemCount(): Int {
        return pojo.case_study_ques_details!!.get(0).casestudyMcqs!!.size
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
        this.pojo = pojo
        this.type = type
        this.questiontype = questiontype
        this.totalRightAns = totalRightAns
    }
}