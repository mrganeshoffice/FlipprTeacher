package com.app.flipprteachear.home.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.FragmentEndLive1Binding
import com.app.flipprteachear.home.ForClassPageChange
import com.app.flipprteachear.home.view.adapter.OverAllScoreAdapter
import com.app.flipprteachear.home.view.adapter.QuesIndecatorAdapter
import com.app.flipprteachear.home.view.adapter.QuesListAdapter
import com.app.flipprteachear.home.pojo.QuesIndicatorModel
import com.app.flipprteachear.utillsa.LinePagerIndicatorDecoration
import kotlinx.android.synthetic.main.end_live_question_layout.view.*


class EndLive1Fragment : Fragment(), ForClassPageChange {


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_end_live1, container, false)
//    }
//

    lateinit var viewBinding: FragmentEndLive1Binding
        private var arrayList= arrayListOf<QuesIndicatorModel>()

        private val  overAllScoreGainerAdapter  by lazy {
            OverAllScoreAdapter("")
        }
        private val  quesListAdapter  by lazy {
            QuesListAdapter(this)
        }
        private val  quesIndicatorListAdapter  by lazy {
            arrayList.clear()
            QuesIndecatorAdapter( arrayList)
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            // Inflate the layout for this fragment
            viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_end_live1, container, false)
            with(viewBinding){
                rvOverAllScores.adapter = overAllScoreGainerAdapter

                incEnd.rvQuesIndecatersList.adapter = quesIndicatorListAdapter
                val recylerViewLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                for(i in 0 until 30){
                    arrayList.add(QuesIndicatorModel("0",false))
                }
                incEnd.rvQuesList.layoutManager = recylerViewLayoutManager
                incEnd.rvQuesList.adapter = quesListAdapter

                incEnd.rvQuesList.addItemDecoration(LinePagerIndicatorDecoration(requireContext()))

                LinearSnapHelper().attachToRecyclerView(incEnd.rvQuesList)
                incEnd.rvQuesIndecatersList.adapter = quesIndicatorListAdapter
                incEnd.rvQuesList.adapter = quesListAdapter

                incEnd.rvQuesList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        if (newState === RecyclerView.SCROLL_STATE_DRAGGING) {
                            //Dragging
                        } else if (newState === RecyclerView.SCROLL_STATE_IDLE) {
                            val position: Int = recylerViewLayoutManager.findFirstVisibleItemPosition()
                            Log.e("position", position.toString())
                            for(i in arrayList.indices){
                                if(i.equals(position)) arrayList.set(i, QuesIndicatorModel(position.toString(),true))
                                else arrayList.set(i,QuesIndicatorModel("",true))
                            }
                            quesIndicatorListAdapter.updateList(position, arrayList)

                        }
                    }

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val position: Int = recylerViewLayoutManager.findFirstVisibleItemPosition()
                        //  Log.e("position_mmmmmmmmmmm", position.toString())
                    }
                }

                )

            }
            return viewBinding.root
        }

        override fun changeClass(position: Int) {

        }

        override fun getTopicPage(schoolCourseStructureId: String) {

        }



}