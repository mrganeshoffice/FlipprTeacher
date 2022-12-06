package com.app.flipprteachear.home.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.FragmentTopicList1Binding
import com.app.flipprteachear.home.ForLivePageChange
import com.app.flipprteachear.home.pojo.topicDetail.*
import com.app.flipprteachear.home.view.activity.HomeActivity
import com.app.flipprteachear.home.view.adapter.TopicListAdapter
import com.app.flipprteachear.home.view.viewModel.MainViewModel
import com.app.flipprteachear.utillsa.PreferenceManager
import com.app.flipprteachear.utillsa.utills
import com.hubwallet.utillss.ResultWrapper


class TopicListFragment : Fragment(), ForLivePageChange {

    lateinit var viewBinding: FragmentTopicList1Binding
    lateinit var viewModel: MainViewModel
    private var pojoTopic: PojoTopicDetail ?=null
    private var pref: PreferenceManager?=null
    private val topicListAdapter by lazy {
        TopicListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_topic_list1, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        pref = PreferenceManager(requireContext())
         with(viewBinding){
             rvChapters.adapter = topicListAdapter
             Back.setOnClickListener {
                 requireActivity().onBackPressed()
//                 (activity as HomeActivity).homepage()
             }

         }
        getHomeApi()
        return viewBinding.root
    }
    private fun getHomeApi() {
        var s = arguments?.getString("schoolCourseStructureId")

        viewModel.getTopicDetail(pref!!.getValueString("token"),ModelTopicDetail(s!!) ).observe(viewLifecycleOwner,{
            updateUI(it)
        })



        //teacher_home_classes()
    }
    private fun updateUI(_state: ResultWrapper<Any>?) {
        when (_state) {

            is ResultWrapper.Loading -> {
                viewBinding.progressBar.visibility = View.VISIBLE
            }

            is ResultWrapper.NetworkError -> {
                viewBinding.progressBar.visibility = View.GONE
            }
            is ResultWrapper.GenericError -> {
                viewBinding.progressBar.visibility = View.GONE
                _state.error?.error?.let {
                    Toast.makeText(requireActivity(), "err " + it, Toast.LENGTH_LONG).show()
                }
            }
            is ResultWrapper.Success -> {
                when(_state.data){
                    is PojoTopicDetail ->{
                        _state.data.let {
                            if (!it.topic_details.isNullOrEmpty()){
                                pojoTopic = it
                                setTopicDetailData(it)
                            }
                        }
                    }
                   is PojoAllUpdate->{
                        _state.data.let {
                            //if (it.message.equals("Success")){
                                Toast.makeText(activity,it.message, Toast.LENGTH_SHORT).show()
                          //}
                        }
                    }
                }
                viewBinding.progressBar.visibility = View.GONE
            }
            else -> {}
        }
    }

    private fun setTopicDetailData(it: PojoTopicDetail) {
        it.topic_details.let {
            topicListAdapter.updateList(it)
            if(!it.isNullOrEmpty())
            viewBinding.tvTopicTitle.text = it[0].chapter_name?:""
        }
    }

    override fun golivePage(topicDetail: TopicDetail) {
//        Log.d("HelloTopicId",topicDetail.topic_name)
//        Log.d("HelloTopicId",topicDetail.school_class_course_id)
        topicDetail?.let {
            val ft = activity?.supportFragmentManager!!.beginTransaction()
            val bundle =Bundle()
            bundle.putString("schoolCourseStructureId",it.school_course_structure_id?:"")
            bundle.putString("school_class_course_id",it.school_class_course_id?:"")
            bundle.putString("chapter_name",it.chapter_name?:"")
            bundle.putString("topic_name",it.topic_name?:"")
            utills.replacefrag_withBackStack(ft, LiveFragment(), bundle, "topicList")
        }

    }

    override fun endlivePage() {
        val ft = activity?.supportFragmentManager!!.beginTransaction()
        utills.replacefrag_withBackStack(ft, EndLive1Fragment(), Bundle(), "endLive")
    }

    override fun checkBoxUpdate(s: String, schoolCourseStructureId: String) {

        viewModel.getTopicCheckBox(pref!!.getValueString("token"), ModelTopicCheckBox(s,schoolCourseStructureId ) ).observe(viewLifecycleOwner,{
            updateUI(it)
        })
    }


}