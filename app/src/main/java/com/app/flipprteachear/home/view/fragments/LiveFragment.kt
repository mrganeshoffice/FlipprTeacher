package com.app.flipprteachear.home.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.FragmentLive1Binding
import com.app.flipprteachear.home.ForStartLive
import com.app.flipprteachear.home.pojo.ModelCheckedActivities
import com.app.flipprteachear.home.pojo.RoomsForDb
import com.app.flipprteachear.home.pojo.liveModel.Details
import com.app.flipprteachear.home.pojo.liveModel.PojoTeacherCreateLive
import com.app.flipprteachear.home.pojo.liveModel.Student
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojMcqQues
import com.app.flipprteachear.home.pojo.topicDetail.ModelLiveDetail
import com.app.flipprteachear.home.pojo.topicDetail.ModelTeacherCreateLiveSession
import com.app.flipprteachear.home.pojo.topicDetail.PojoAllUpdate
import com.app.flipprteachear.home.pojo.userDetail.PojoUserDetail
import com.app.flipprteachear.home.view.adapter.CheckBoxAdapter2
import com.app.flipprteachear.home.view.adapter.CheckBoxAdapterLevels
import com.app.flipprteachear.home.view.adapter.CheckBoxAdapterSubTopics
import com.app.flipprteachear.home.view.adapter.ClassParticipantsAdapter
import com.app.flipprteachear.home.view.viewModel.LiveViewModel
import com.app.flipprteachear.utillsa.Constants_All.liveCode_c
import com.app.flipprteachear.utillsa.PreferenceManager
import com.app.flipprteachear.utillsa.utills.Companion.convertStrigbuilderCaseStdy
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.hubwallet.utillss.ResultWrapper
import kotlinx.android.synthetic.main.fragment_live1.*
import java.util.*
import kotlin.collections.ArrayList


class LiveFragment : Fragment() {

    lateinit var viewBinding:FragmentLive1Binding
    private var pref: PreferenceManager?=null
    private var pojoCreateLive: PojoTeacherCreateLive?=null
    lateinit var viewModel: LiveViewModel
    private var getVesselName: ArrayList<ModelCheckedActivities>? = null
    private var getLevelsName: ArrayList<ModelCheckedActivities>? = null
    private var getSubTopicName: ArrayList<ModelCheckedActivities>? = null
    private var mListEventListener: ListenerRegistration? =null
    private var db :FirebaseFirestore?=null
    private var studentList: List<Student>?=null
    private var userList: java.util.ArrayList<String>? = null
    private var startLive : ForStartLive?=null
    private var pojMcqQues: PojMcqQues ? =null
    var selectedDimensions= ""
    var selectedLevels= ""
    var selectedSubTopics= ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        startLive =   context as ForStartLive
    }

    private val  classAdapter  by lazy {
        ClassParticipantsAdapter(0, pref )
    }

    private val dimensionsArrayAdapter by lazy {
        val arraylistt = arrayListOf<String>()
          arraylistt.add("-Dimensions-")
        pojoCreateLive?.details!!.dimensions.let {
            for (i in 0 until it.size) {
                arraylistt.add(it[i].name)
            }
        }

        ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item, arraylistt)
    }

    private val levelsArrayAdapter  by lazy {
        val arraylistt = arrayListOf<String>()
          arraylistt.add("-Level-")
        pojoCreateLive?.details!!.levels.let {
            for (i in 0 until it!!.size) {
                arraylistt.add(it[i].name)
            }
        }

        ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item, arraylistt)
    }

    private val subTopicsArrayAdapter by lazy {
        val arraylistt = arrayListOf<String>()
          arraylistt.add("-Sub Topics-")
        pojoCreateLive?.details!!.sub_topics.let {
            for (i in 0 until it!!.size) {
                arraylistt.add(it[i].name!!)
            }
        }
        ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item, arraylistt)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_live1, container, false)
        pref = PreferenceManager(requireContext())
        userList = ArrayList()
        viewModel = ViewModelProvider(this)[LiveViewModel::class.java]
        with(viewBinding){
           db= FirebaseFirestore.getInstance()
            rvParticipants.setLayoutManager(GridLayoutManager(activity, 4))
            rvParticipants.adapter =classAdapter

//            rlNext.setOnClickListener {
//                val ft = activity?.supportFragmentManager!!.beginTransaction()
//                utills.replacefrag_withBackStack(ft, Mcq2Fragment(), Bundle(), "mcqPage")
//                (activity as HomeActivity).hide_Visible_TopBotomBar(true)
//            }

            viewBinding.tvWelcome.setText(arguments?.getString("chapter_name") +" > "+ arguments?.getString("topic_name"))

           getLiveFramentApi()

           tvDimensionSelcected.setText(getString(R.string.selected_, "0"))
              tvLevelsSelcected.setText(getString(R.string.selected_, "0"))
             tvSubTopicSelcected.setText(getString(R.string.selected_, "0"))

            viewBinding.tvParticipants.setVisibility(View.VISIBLE)
            viewBinding.tvParticipants.setText(resources.getString(R.string._participants,  "0"))

        }

        return viewBinding.root
    }

    private fun fetchUsers(liveCode: String) {
        mListEventListener =
            db!!.collection(getString(R.string.collectionName)).addSnapshotListener(object :
                EventListener<QuerySnapshot> {
            override fun onEvent(snapshots: QuerySnapshot?, e: FirebaseFirestoreException?) {
                if (e != null) {
                    progress_bar.visibility = View.GONE
                    Log.w("TAG", "listen:error", e)
                    return
                }
                for ( d in snapshots?.documentChanges!!) {
                    try {
                        var roomsForDb = d.document.toObject(RoomsForDb::class.java)

                        if(liveCode==roomsForDb.liveCode) {
                            userList!!.clear()
                            userList = roomsForDb.users
                            Log.w("TAG", "listen:userList"+userList!!+"...."+roomsForDb.users)
                            if(userList!!.size>0)
                            getUserParticipateList()
                        }else{
                            viewBinding.tvParticipants.setText(resources.getString(R.string._participants,  "0"))
                            viewBinding.rvParticipants.visibility =View.GONE
                           // getUserParticipateList()
                        }
                    }catch (e:Exception){e.printStackTrace()}

                }
            }
        })
    }

    private fun getUserParticipateList() {

        val mp  = HashMap<String, String>()
        try {
            val userid: String = convertStrigbuilderCaseStdy(userList!!, ",")!!
            mp.put("users", userid)
            viewModel.get_users_details(pref!!.getValueString("token"), mp).observe(viewLifecycleOwner,{
                updateUI(it)
            })
        }catch (e:Exception){
            e.printStackTrace()
            viewBinding.rvParticipants.visibility =View.GONE
            viewBinding.tvParticipants.setText(resources.getString(R.string._participants,  "0"))

        }


    }

    private fun getLiveFramentApi() {
        var s = arguments?.getString("schoolCourseStructureId")
        var sCourseId = arguments?.getString("school_class_course_id")
        viewModel.getCreateLive(pref!!.getValueString("token"), ModelTeacherCreateLiveSession("$sCourseId",s!!) ).observe(viewLifecycleOwner,{
             updateUI(it)
        })

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
                    is PojoTeacherCreateLive ->{
                        _state.data.let {
                                pojoCreateLive = it
                                setPojoData(it)

                        }
                    }
                    is PojoAllUpdate ->{
                        _state.data.let {
                            //if (it.message.equals("Success")){
                            Toast.makeText(activity,it.message, Toast.LENGTH_SHORT).show()
                            //}
                        }
                    }
                    is PojoUserDetail->{
                        _state.data.let {
                           // Toast.makeText(activity,it.message, Toast.LENGTH_SHORT).show()
                            viewBinding.rvParticipants.visibility = View.GONE
                            if(it.details!=null)

                                it.details.let {
                                   if( it.size>0 )viewBinding.rvParticipants.visibility = View.VISIBLE
                                   viewBinding.tvParticipants.setText(resources.getString(
                                            R.string._participants, it.size.toString() + ""))
                                    classAdapter.updateListType(it)
                                }
                        }
                    }
                    is PojMcqQues ->{
                        _state.data.let {
                             pojMcqQues = it
                            if(it.details!=null){
                                viewBinding.tvQuesCount.text = if(it.details.size.toString().isEmpty()) "0" else it.details.size.toString() +" Questions"

                            } else  viewBinding.tvQuesCount.text =   "0" +" Questions"
                        }
                    }
                }
                viewBinding.progressBar.visibility = View.GONE
            }
            else -> {}
        }
    }

    private fun setPojoData(it: PojoTeacherCreateLive) {
      with(viewBinding){
//          spDimention.adapter = dimensionsArrayAdapter
//          spLevels.adapter = levelsArrayAdapter
//          spSubTopics.adapter = subTopicsArrayAdapter
          if(it.details!=null)
              tvLiveCodeValue.text= if(it.details.live_code!=null) it.details.live_code else ""
           getListingActivities1("")

          try {
              if(userList!!.size>0)  viewBinding.rvParticipants.visibility =View.VISIBLE
              else  {
                  viewBinding.rvParticipants.visibility =View.GONE
                  viewBinding.tvParticipants.setText(resources.getString(R.string._participants,  "0"))
              }

              viewBinding.tvQuesCount.text =   "0" +" Questions"
              setFirebaseDatabase_entry(it.details!!)
          }catch (e :Exception){e.printStackTrace()}

      }
    }

    private fun setFirebaseDatabase_entry(details: Details) {

        fetchUsers(details.live_code!!)
        details.let {
            liveCode_c ="${it.live_code}"
            var rooms = RoomsForDb("${it.live_code}",false,"${arguments?.getString("school_class_course_id")}","${arguments?.getString("schoolCourseStructureId")}",
                false,"${pref!!.getValueString("userId")}" , ArrayList(), "${arguments?.getString("chapter_name")}", "${arguments?.getString("topic_name")}")

            db!!.collection(getString(R.string.collectionName)).document(details.live_code).set(rooms)


        }
    }

    private fun getListingActivities1(duration_: String?) {
        pojoCreateLive?.details!!.let {
            with(viewBinding) {
                var isDimention = false
                var isLevels = false
                var isSubTopics = false
                var bundle = Bundle()

                getVesselName = ArrayList()
                getVesselName!!.clear()

                it.dimensions?.let {
                        for (i in it.indices) {
                            getVesselName!!.add(
                                ModelCheckedActivities(
                                    false,
                                    it[i].name,
                                    it[i].dimensions_id
                                )
                            )
                        }

                }
                llActivities.visibility = View.INVISIBLE
                tvDimensions.setOnClickListener {
                    isDimention = !isDimention
                    llActivities.visibility = if (isDimention) View.VISIBLE else View.GONE
                    llLevels.visibility = View.GONE
                    llSubTopic.visibility = View.GONE
                    //spDimention.performClick()
                    //  Toast.makeText(requireContext(),"dc", Toast.LENGTH_SHORT).show()
                }

                var checkBoxAdapter2 = CheckBoxAdapter2(requireContext(), getVesselName)
                rvList.adapter = checkBoxAdapter2

                myListSave.setOnClickListener({ v: View? ->
                    selectedDimensions = ""

                    selectedDimensions = convertStrigbuilderCaseStdy2(getVesselName!!, ",", "D")!!
                    if (selectedDimensions == null || selectedDimensions.equals("null")) selectedDimensions =
                        ""
                    //println("levelIddimension....$selectedDimensions....$selectedLevels...$selectedSubTopics... ")
                    bundle.putString("selectedDimensions", "$selectedDimensions")
                    llActivities.setVisibility(View.INVISIBLE)
                    getQuestionCountApi()
                })
                //=============================
                getLevelsName = ArrayList()
                getLevelsName!!.clear()
                it.levels?.let {
                    for (i in it.indices) {
                        getLevelsName!!.add(
                            ModelCheckedActivities(
                                false,
                                it[i].name,
                                it[i].level_id
                            )
                        )
                    }
                }
                llLevels.visibility = View.INVISIBLE
                tvLevels.setOnClickListener {
                    isLevels = !isLevels
                    llLevels.visibility = if (isLevels) View.VISIBLE else View.GONE
                    llActivities.visibility = View.GONE
                    llSubTopic.visibility = View.GONE
                    //spDimention.performClick()
                    //  Toast.makeText(requireContext(),"dc", Toast.LENGTH_SHORT).show()
                }

                var checkBoxAdapterLevel = CheckBoxAdapterLevels(requireContext(), getLevelsName)
                rvListLevels.adapter = checkBoxAdapterLevel

                myListSaveLevels.setOnClickListener(View.OnClickListener { v: View? ->
                    selectedLevels = ""

                    selectedLevels = convertStrigbuilderCaseStdy2(getLevelsName!!, ",", "L")!!
                    if (selectedLevels == null || selectedLevels.equals("null")) selectedLevels = ""
                    bundle.putString("selectedLevels", "$selectedLevels")
                    //println("levelIddll....$selectedDimensions....$selectedLevels...$selectedSubTopics... ")

                    llLevels.setVisibility(View.INVISIBLE)
                    getQuestionCountApi()
                })
                ///// //=============================  ==========================
                getSubTopicName = ArrayList()
                getSubTopicName!!.clear()

                it.sub_topics?.let {
                        for (i in it.indices) {
                            getSubTopicName!!.add(
                                ModelCheckedActivities(
                                    true,
                                    it[i].name,
                                    it[i].sub_topic_id
                                )
                            )
                        }

                }
                llSubTopic.visibility = View.INVISIBLE
                tvSubTopic.setOnClickListener {
                    isSubTopics = !isSubTopics
                    llSubTopic.visibility = if (isSubTopics) View.VISIBLE else View.GONE
                    llActivities.visibility = View.GONE
                    llLevels.visibility = View.GONE

                }

                var checkBoxAdapterTopic =
                    CheckBoxAdapterSubTopics(requireContext(), getSubTopicName)
                rvListTopics.adapter = checkBoxAdapterTopic

                addSetSubtopics(bundle)
                myListSaveTopic.setOnClickListener(View.OnClickListener { v: View? ->
                    addSetSubtopics(bundle)
                    llSubTopic.setVisibility(View.INVISIBLE)
                    getQuestionCountApi()
                })

                bundle.putString(
                    "schoolCourseStructureId",
                    arguments?.getString("schoolCourseStructureId")
                )
                bundle.putString(
                    "school_class_course_id",
                    "${arguments?.getString("school_class_course_id")}"
                )

                bundle.putString("liveCode_",
                    "${if (it.live_code == null) "" else it.live_code}"
                )
                tvStart.setOnClickListener { v->

                    if (selectedDimensions.isNullOrEmpty() || selectedDimensions.equals(" ") && selectedLevels.isNullOrEmpty() || selectedLevels.equals(
                            " "))
                        Toast.makeText(requireContext(),
                            "please select dimensions and levels", Toast.LENGTH_SHORT).show()
                    else if (selectedSubTopics.isNullOrEmpty() || selectedSubTopics.equals(" "))
                        Toast.makeText(requireContext(),
                            "please select subTopics", Toast.LENGTH_SHORT).show()
                    else {
                        viewBinding.tvQuesCount.text = "0" + " Questions"
                        val index: Int = 0
                        try {
                            if (pojMcqQues != null) {
                                if (pojMcqQues?.details!!.size > 0) {
                                    bundle.putSerializable("pojomcq", pojMcqQues)

                                    val mp = HashMap<String?, Any?>()
                                    mp.put("dimension", selectedDimensions)
                                    mp.put("level", selectedLevels)
                                    mp.put("liveCode",it.live_code)
                                    mp.put("subtopics", selectedSubTopics)
                                    mp.put("currentQuestionIndex", index)
                                    mp.put("startStatus", true)
                                    mp.put("nextTapped", false)
                                    mp.put("nextTappedCount", 0)


                                    db!!.collection(getString(R.string.collectionName))
                                        .document(it.live_code!!)
                                        .update(mp)
                                    liveCode_c = it.live_code!!
                                    startLive?.startLive("from_LiveFrag", bundle)
                                    viewBinding.rvParticipants.setVisibility(View.GONE)

                                    viewBinding.tvQuesCount.text = "0" + " Questions"

                                    selectedDimensions = ""
                                    selectedLevels = ""
                                    selectedSubTopics = ""
                                } else showToast()
                            } else showToast()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }
    fun addSetSubtopics(bundle: Bundle) {
        selectedSubTopics = ""
        selectedSubTopics = convertStrigbuilderCaseStdy2(getSubTopicName!!, ",", "S")!!
        if(selectedSubTopics==null || selectedSubTopics.equals("null") ) selectedSubTopics =""
        //println("levelIddTopic.subTopic...$selectedDimensions....$selectedLevels...$selectedSubTopics... ")
        bundle.putString("selectedSubTopics", "$selectedSubTopics")
    }
    private fun getQuestionCountApi() {

        var model_  = ModelLiveDetail("${arguments?.getString("schoolCourseStructureId")}",
            "$selectedLevels","$selectedSubTopics",
            "$selectedDimensions","${if(pojoCreateLive?.details!!.live_code==null) "" else pojoCreateLive?.details!!.live_code}")

        viewModel.getLiveQues(pref!!.getValueString("token"), model_ ).observe(viewLifecycleOwner,{
            updateUI(it)
        })
    }
    private fun convertStrigbuilderCaseStdy2(se: ArrayList<ModelCheckedActivities>, s: String, type: String
    ): String?
    {
        val sb = StringBuilder()
        val sbTV = StringBuilder()
        var count =0
        for (ss in se) {
            if (ss.isSelected) sbTV.append(ss.ids).append(s)// sbTV.append(ss.activities_s).append(s)
        }
        for (i in se.indices) {

            if (se[i].isSelected) {
                count++
                sb.append(i + 1).append(s)
            }
        }
       // sbTV.substring(0, sbTV.length - 1)


        if(type.equals("D")) tvDimensionSelcected.setText(getString(R.string.selected_, count.toString()))
        else if (type.equals("L"))   tvLevelsSelcected.setText(getString(R.string.selected_, count.toString()))
        else if (type.equals("S"))   tvSubTopicSelcected.setText(getString(R.string.selected_, count.toString()))
          if(sbTV.length>1)  return sbTV.substring(0, sbTV.length - 1)
        else return sbTV.toString()
    }
    fun showToast(){
        Toast.makeText(activity, "No questions available", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mListEventListener != null) mListEventListener!!.remove()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mListEventListener != null) mListEventListener!!.remove()

    }

    override fun onDetach() {
        super.onDetach()
        if (mListEventListener != null) mListEventListener!!.remove()

    }
}