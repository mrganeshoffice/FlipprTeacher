package com.app.flipprteachear.home.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.home.ForClassPageChange
import com.app.flipprteachear.home.pojo.Chapter
import com.app.flipprteachear.utillsa.PreferenceManager
import com.bumptech.glide.Glide


public class chapterAdapter(var classPage: ForClassPageChange, var requireActivity: FragmentActivity,
                           var pref: PreferenceManager?
): RecyclerView.Adapter<chapterAdapter.custom>() {


     private var chapters: List<Chapter>?=null
    //private int seekBarTime;
    private var totalTimeSpent: Double? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_chapter_topic_row, parent, false)
        return custom(view)
    }

    override fun onBindViewHolder(holder: custom, position: Int) {
       /* try {
            holder.iv_image.load(it.image){
                // crossfade(true)
                placeholder(R.drawable.image)
                // transformations()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
        chapters!![position].let {
            with(holder){
        var titleName = ""
        var title = ""
        try {

            Log.d("HelloException","Hello Exception")
            if (it.subject_id == "0") {
                titleName = "C"
                title = "Course"
            } else if (it.cluster_id == "0") {
                titleName = "S"
                title = "Subject"
            } else if (it.chapter_id == "0") {
                titleName = "C"
                title = "Cluster"
            } else if (it.topic_id == "0") {
                titleName = "C"
                title = "Chapter"
            } else {
                titleName = "T"
                title = "Topic"
            }
        } catch (e: Exception) {
            Log.d("HelloException","Hello Exception"+e)
            e.printStackTrace()
        }
        holder.tv_chapterStatus.text = titleName
        holder.tv_chaptername.text = title
        //  var time = String.format("%.1f",it.session_total_time!!/60f)
                if(it.session_total_time!=null){
                    holder.tv_Duration .text = "${String.format("%.0f",it.session_total_time!!/60f)} min to finish"
                    Log.d("timings", "onBindViewHolder: ${it.session_total_time!!/60f}")
                }else{
                    holder.tv_Duration.visibility=View.GONE
                }

       /* if (topicDetails!![position].getSessionType() != null) {
            if (topicDetails!![position].getSessionType().equals("3")) {
                holder.tv_status.text =
                    "Revise to " + topicDetails!![position].getReviseTo().toString() + "%"
                session_type = "3"
            } else if (topicDetails!![position].getSessionType().equals("2")) {
                holder.tv_status.text =
                    "Boost to " + topicDetails!![position].getBoostTo().toString() + "%"
                session_type = "2"
            } else {
                session_type = "1"
                if (topicDetails!![position].getTeacherActivitiesDetails() != null) {
                    if (topicDetails!![position].getTeacherActivitiesDetails().getIsStarted()
                            .equalsIgnoreCase("1")
                    ) {
//                        holder.llStatus.setBackgroundColor(getResources().getColor(R.color.Yellow));
//                        holder.tv_chapterStatus.setText("O");
                        holder.tv_status.text = "Ongoing in class"
                    } else if (topicDetails!![position].getTeacherActivitiesDetails().getIsStarted()
                            .equalsIgnoreCase("0")
                    ) {
                        if (topicDetails!![position].getStudentDetail()
                                .getIsStudentDone() != null
                        ) {
                            if (topicDetails!![position].getStudentDetail().getIsStudentDone()
                                    .equalsIgnoreCase("1")
                            ) {
//                                 holder.llStatus.setBackgroundColor(getResources().getColor(R.color.green_botom));
//                                holder.tv_chapterStatus.setText("D");
                                holder.tv_status.text = "Done by you & class"
                            } else {
//                                 holder.llStatus.setBackgroundColor(getResources().getColor(R.color.copyPink));
//                                holder.tv_chapterStatus.setText("D");
                                holder.tv_status.text = "Done in class"
                            }
                        }
                        if (topicDetails!![position].getStudentDetail().getIsBoostDone() != null) {
                            if (topicDetails!![position].getStudentDetail().getIsBoostDone()
                                    .equalsIgnoreCase("1")
                            ) {
//                                 holder.llStatus.setBackgroundColor(getResources().getColor(R.color.green_botom));
//                                holder.tv_chapterStatus.setText("D");
                                holder.tv_status.text = "Done by you & class"
                            } else {
//                                 holder.llStatus.setBackgroundColor(getResources().getColor(R.color.copyPink));
//                                holder.tv_chapterStatus.setText("D");
                                holder.tv_status.text = "Done in class"
                            }
                        }
                    }
                }
            }
        } */

                    if (it.teacher_activity_status.equals("1", false))
                        holder.tv_status.text = "Ongoing in class"
                     else if (it.teacher_activity_status.equals("0",false))
                        holder.tv_status.text = "Done in class"
                    else if (it.teacher_activity_status!!.isEmpty())
                        holder.tv_status.text = "Not started in class"

                try {
                    if (!it.chapter_image.equals("", false)) {
                        Glide.with(requireActivity)
                            .load(it.chapter_image)
                            .into(holder.iv_topicImage)
                    }
                } catch (exc: Exception) {
                    exc.printStackTrace()
                }

         holder.itemView.setOnClickListener { v->
             //..frameFullhoome
             //SharedPreferences.Editor = pref.edit()

             pref!!.putValueString("chapter_ID", it.chapter_id?:"")
             pref!!.putValueString("duration_mins", "")


            //   screenStart.screenName("mcq_allChapter", topicDetails.get(position).getTopicId(), subject_Id);
           /* chapterList.chapterDetail(
                pojoUrgentChapters.getDetails().get(position),
                topicDetails!![position].getTopicId()
            )*/
        }


            reMain.setOnClickListener {v->
                Log.d("HelloChapterId",it.chapter_id?:"")
                classPage.getTopicPage(it.school_course_structure_id?:"")
            }
        }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return   if (chapters != null) chapters!!.size else 0
    }

    fun updateListType(chapters: List<Chapter>) {
         this.chapters  =chapters
        notifyDataSetChanged()
    }

    inner class custom(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var reMain: RelativeLayout
         var llStatus: LinearLayout
        var tv_status: TextView
        var tv_chapterStatus: TextView
        var tv_Duration: TextView
        var tv_chaptername: TextView
        var iv_topicImage: ImageView
        var rv_topicDetail: RecyclerView? = null
        var seekBar: ProgressBar

        init {
            reMain = itemView.findViewById(R.id.reMain)
           tv_status = itemView.findViewById(R.id.tv_participants)
            iv_topicImage = itemView.findViewById(R.id.iv_topicImage)
            llStatus = itemView.findViewById(R.id.llStatus)
            tv_chapterStatus = itemView.findViewById(R.id.tv_chapterStatus)
            seekBar = itemView.findViewById(R.id.seekBar)
            tv_Duration = itemView.findViewById(R.id.tv_Duration)
            tv_chaptername = itemView.findViewById(R.id.tv_chaptername)
        }
    }
}