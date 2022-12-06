package com.app.flipprteachear.home.view.fragments

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.FragmentStudentProfileBinding
import com.app.flipprteachear.home.pojo.Student
import com.app.flipprteachear.home.pojo.studentDetail.PojoStudentProfile
import com.app.flipprteachear.home.view.viewModel.MainViewModel
import com.app.flipprteachear.utillsa.PreferenceManager
import com.bumptech.glide.Glide
import com.hubwallet.utillss.ResultWrapper
import java.lang.Exception
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*


class StudentProfileFragment : Fragment() {

    lateinit var binding:FragmentStudentProfileBinding

    private var student: Student? = null
    lateinit var viewModel: MainViewModel
    private var pref: PreferenceManager?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_student_profile, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        pref = PreferenceManager(requireContext())

        student = arguments?.getSerializable("student") as Student

        setStudenProfile()


        return binding.root
    }

    private fun setStudenProfile() {
        binding.tvFirstname.setText(student?.first_name.toString())
        binding.tvLastname.setText(student?.last_name.toString())

        try {
            Glide.with(requireActivity())
                .load(student?.image)
                .placeholder(R.drawable.image)
                .into(binding.icUserimage)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (student?.total_points == null) {
            binding.tvTotalPoint.text = "0°P"
        } else {
            binding.tvTotalPoint.setText(String.format("%.0f",student?.total_points!!.toFloat())+"")
        }

        binding.tvHomework.text = String.format("%.1f", student?.homework_comp)  + "%"
        binding.tvSyllabus.text = String.format("%.0f", student?.syllabus_comp)  + "%"
        binding.tvSyllabusMaster.text = String.format("%.1f", student?.syllabus_mastered)  + "%"

        viewModel.studentProfileApi(pref!!.getValueString("token"),student?.user_id.toString()).observe(viewLifecycleOwner,{
            updateUI(it)
        })

//        binding.tvDescription.setText(student?..toString())
    }


    private fun updateUI(_state: ResultWrapper<Any>?) {
        when (_state) {

            is ResultWrapper.Loading -> {
                binding.progress.visibility = View.VISIBLE
            }

            is ResultWrapper.NetworkError -> {
                binding.progress.visibility = View.GONE
            }
            is ResultWrapper.GenericError -> {
                binding.progress.visibility = View.GONE
                _state.error?.error?.let {
                    Toast.makeText(requireActivity(), "err " + it, Toast.LENGTH_LONG).show()
                }
            }
            is ResultWrapper.Success -> {

                when(_state.data){
                    is PojoStudentProfile ->{
                        _state.data.let {
                            if (it.message.equals("Success")){
                                Log.d("Hello","I'm here")
                                val adapterView = studentProfileAdapter(it,activity)
                                binding.rvTopics.adapter = adapterView
                            }
                        }
                    }
                }
                binding.progress.visibility = View.GONE
            }
            else -> {}
        }
    }





    class studentProfileAdapter(
        var pojoStudentProfile: PojoStudentProfile,
        var activity: FragmentActivity?
    ) : RecyclerView.Adapter<studentProfileAdapter.custom>() {

        var sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()) //2021-08-18 07:14:59

        var cal = Calendar.getInstance()
        var currentdate = sdf.format(cal.time)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.res_learn_topics, parent, false)
            return custom(view)
        }

        override fun onBindViewHolder(holder: custom, position: Int) {

            holder.tv_subjectTopic.text = pojoStudentProfile.detail[0].recent_sessions[position].subject_name+": "+pojoStudentProfile.detail[0].recent_sessions[position].chapter_name
            holder.tv_topicname.text = pojoStudentProfile.detail[0].recent_sessions[position].chapter_name

            var point = pojoStudentProfile.detail[0].recent_sessions[position].total_points.toString() + ""
            point = String.format("%.0f", point.toFloat())

            var points = ""

            //  String points =  "earn <font color='#26e67a'>"+point+"°P</font> in 10 mins";
            val totalTime: Int = pojoStudentProfile.detail[0].recent_sessions[position].session_spend_time / 60
            val str = pojoStudentProfile.detail[0].recent_sessions[position].student_detail.completed_persentage

            if (pojoStudentProfile.detail[0].recent_sessions[position].session_type == "3") {
                points = "revised to <font color='#26e67a'>$str%</font> in $totalTime mins"

                holder.cardStatus.setCardBackgroundColor(
                    activity?.resources!!.getColor(R.color.revise)
                )
                holder.tv_status.setText("Revise")
            }

            if (pojoStudentProfile.detail[0].recent_sessions[position].session_type == "2") {
                points = "boosted to <font color='#26e67a'>$str%</font> in $totalTime mins"

                holder.cardStatus.setCardBackgroundColor(
                    activity?.resources!!.getColor(R.color.boost)
                )
                holder.tv_status.text = "Boost"
            }


            if (pojoStudentProfile.detail[0].recent_sessions[position].session_type == "1") {
                points = "earned <font color='#26e67a'>$point°P</font> in $totalTime mins"
                holder.tv_status.text = "Learn"

                holder.cardStatus.setCardBackgroundColor(
                    activity?.resources!!.getColor(R.color.learn)
                )
            }

            holder.tv_points.setText(Html.fromHtml(points), TextView.BufferType.SPANNABLE)


            // holder.tv_points.setText ( );
            try {
                Glide.with(activity!!)
                    .load(pojoStudentProfile.detail[0].recent_sessions[position].image)
                    .placeholder(R.drawable.image)
                    .into(holder.iv_topicImage)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val s = findDifference(pojoStudentProfile.detail[0].recent_sessions[position].updated_time, currentdate)

            holder.tv_timeAgo.text = s

        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

        override fun getItemCount(): Int {
             try {
                 return pojoStudentProfile.detail[0].recent_sessions.size
             }catch (e:Exception){
                 return 0;
             }

        }

        inner class custom(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var tv_subjectTopic:TextView
            var tv_topicname:TextView
            var tv_points:TextView
            var tv_timeAgo:TextView
            var tv_status:TextView
            var cardStatus:CardView
            var iv_topicImage:ImageView

            init {
                tv_subjectTopic = itemView.findViewById(R.id.tv_subjectTopic)
                tv_topicname = itemView.findViewById(R.id.tv_topicname)
                tv_points = itemView.findViewById(R.id.tv_points)
                tv_timeAgo = itemView.findViewById(R.id.tv_timeAgo)
                tv_status = itemView.findViewById(R.id.tv_status)
                cardStatus = itemView.findViewById(R.id.cardStatus)
                iv_topicImage = itemView.findViewById(R.id.iv_topicImage)
            }
        }

        fun findDifference(start_date: kotlin.String, end_date: kotlin.String): kotlin.String? {
            //  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
            // sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            val sdf1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            try {
                val d1 = sdf1.parse(start_date)
                // Date d2 = sdf1.parse(end_date);
                val d2 = sdf1.parse(sdf1.format(Date()))
                Log.d("HelloUtcdDate", "$start_date.........$end_date")
                Log.d("HelloDate", "$d1.....$d2")
                Log.d("HelloDate", d1.time.toString() + "....." + d2.time)

                // Calucalte time difference
                // in milliseconds
                val difference_In_Time = Math.abs(d2.time - d1.time)
                Log.d("HelloDate...", difference_In_Time.toString() + "")
                // Calucalte time difference in
                // seconds, minutes, hours, years,
                // and days
                val difference_In_Seconds = difference_In_Time / 1000 % 60
                val difference_In_Minutes = difference_In_Time / (1000 * 60) % 60
                val difference_In_Hours = difference_In_Time / (1000 * 60 * 60) % 24
                val difference_In_Years = difference_In_Time / (1000L * 60 * 60 * 24 * 365)
                val difference_In_Days = difference_In_Time / (1000 * 60 * 60 * 24) // % 365;

                // Print the date difference in
                // years, in days, in hours, in
                // minutes, and in seconds
                return if (difference_In_Years != 0L) {
                    val x = Math.abs(difference_In_Years)
                    Log.e("findDifference: ", "$difference_In_Years...$x years ago")
                    "$x years ago"
                } else if (difference_In_Days != 0L) {
                    val x = Math.abs(difference_In_Days)
                    Log.e("findDifference: ", "$difference_In_Days...$x days ago")
                    "$x days ago"
                } else if (difference_In_Hours != 0L) {
                    val x = Math.abs(difference_In_Hours)
                    Log.e("findDifference: ", "$difference_In_Hours...$x hours ago")
                    "$x hours ago"
                } else if (difference_In_Minutes != 0L) {
                    val x = Math.abs(difference_In_Minutes)
                    Log.e("findDifference: ", "$difference_In_Minutes...$x minutes ago")
                    "$x minutes ago"
                } else {
                    val x = Math.abs(difference_In_Seconds)
                    Log.e("findDifference: ", "$difference_In_Seconds...$x seconds ago")
                    "$x seconds ago"
                }
            } // Catch the Exception
            catch (e: Exception) {
                e.printStackTrace()
            }
            return "0 hour ago"
        }
    }
}