package com.app.flipprteachear.home.view.fragments

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.FragmentMcq2Binding
import com.app.flipprteachear.home.pojo.RoomsForDb
import com.app.flipprteachear.home.pojo.liveModel.fillUpPojo.AnswerRange
import com.app.flipprteachear.home.pojo.liveQuesPojo.ArrangeAnswerDetail
import com.app.flipprteachear.home.pojo.liveQuesPojo.Detail
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojMcqQues
import com.app.flipprteachear.home.pojo.topicDetail.ModelLiveDetail
import com.app.flipprteachear.home.pojo.topicDetail.PojoAllUpdate
import com.app.flipprteachear.home.view.adapter.OverAllScoreAdapter
import com.app.flipprteachear.home.view.adapterMcq.*
import com.app.flipprteachear.home.view.viewModel.LiveViewModel
import com.app.flipprteachear.utillsa.Constants_All
import com.app.flipprteachear.utillsa.Constants_All.getLatexSolvedQ_Str_Solution
import com.app.flipprteachear.utillsa.Constants_All.latetex_dim_13
import com.app.flipprteachear.utillsa.Constants_All.latetex_dim_15
import com.app.flipprteachear.utillsa.Global
import com.app.flipprteachear.utillsa.PreferenceManager
import com.app.flipprteachear.utillsa.utills
import com.bumptech.glide.Glide
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.hubwallet.utillss.ResultWrapper
import katex.hourglass.`in`.mathlib.MathView
import kotlinx.android.synthetic.main.activity_main1.*
import kotlinx.android.synthetic.main.activity_main1.rlLoader
import kotlinx.android.synthetic.main.end_live_question_layout.*

import kotlinx.android.synthetic.main.fragment_live1.*
import kotlinx.android.synthetic.main.fragment_mcq2.*
import kotlinx.android.synthetic.main.fragment_mcq2.Recycler_answer
import kotlinx.android.synthetic.main.fragment_mcq2.expanded_image
import kotlinx.android.synthetic.main.fragment_mcq2.fbv_content
import kotlinx.android.synthetic.main.fragment_mcq2.formula_one
import kotlinx.android.synthetic.main.fragment_mcq2.horizontal_fillup
import kotlinx.android.synthetic.main.fragment_mcq2.iv_quesimage
import kotlinx.android.synthetic.main.fragment_mcq2.llimage
import kotlinx.android.synthetic.main.fragment_mcq2.llnext
import kotlinx.android.synthetic.main.fragment_mcq2.llsolnext
import kotlinx.android.synthetic.main.fragment_mcq2.llsolution
import kotlinx.android.synthetic.main.fragment_mcq2.progress_bar
import kotlinx.android.synthetic.main.fragment_mcq2.scrollBar_
import kotlinx.android.synthetic.main.fragment_mcq2.scrollBar_solution
import kotlinx.android.synthetic.main.fragment_mcq2.tv_QuesDetail_sM
import kotlinx.android.synthetic.main.fragment_mcq2.tv_answer
import kotlinx.android.synthetic.main.fragment_mcq2.tv_answer1
import kotlinx.android.synthetic.main.fragment_mcq2.tv_apply
import kotlinx.android.synthetic.main.fragment_mcq2.tv_solutionbtn
import kotlinx.android.synthetic.main.fragment_mcq2.tv_solutions1M
import kotlinx.android.synthetic.main.res_case_study_qes1.*
import kotlinx.android.synthetic.main.res_case_study_qes1.formula_one1_res
import kotlinx.android.synthetic.main.res_case_study_qes1.tv_sereas_res
import kotlinx.android.synthetic.main.res_case_study_qes_title_row.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class Mcq2Fragment : Fragment() {
    lateinit var viewBinding: FragmentMcq2Binding
    private var lastClickTime: Long = 0
    private var i = 0
    private var isCountDownOnNext = true
    private var mCountDownTimer: CountDownTimer? = null
    lateinit var viewModel: LiveViewModel
    private var pref: PreferenceManager? = null
    private var pojMcqQues: PojMcqQues? = null
    private var schoolCourseStructureId = ""
    private var selectedDimensions = ""
    private var selectedLevels = ""
    private var selectedSubTopics = ""
    private var liveCode_ = ""
    private var schoolClassCourseId = ""
    private var questTypeMarks = ""
    private var totalRightAns = 0
    private var shortAnimationDuration = 0
    public val latetex_dim_15 = 15
    var count = 0
    var questionId = ""
    var youtTube_Fragment: YoutTube_Fragment? = null
    var correctAnswer: ArrayList<String>? = null
    var selectedanswer: ArrayList<String>? = null
    var caseStudyselectedanswer: ArrayList<String>? = null
    private var trueFalseAdapter: TrueFalseAdapter? = null
    private var mListEventListener: ListenerRegistration? = null
    private var db: FirebaseFirestore? = null
    private var userList: java.util.ArrayList<String>? = null
    var ansDetail_arng: ArrayList<String>? = null
    public var adapterA: applyAdapter? = null

    var adapter2: applyEditText? = null
    //private var adapter: arrangeAdapter? = null
    private val overAllScoreGainerAdapter by lazy {
        OverAllScoreAdapter("overAll")
    }
    private val quesScoreGainerAdapter by lazy {
        OverAllScoreAdapter("inThis")
    }

    /* private val  queOptionsAdapter  by lazy {
        QueOptionsAdapter(
            it.answer_details,
            "question",
            it.define_type,
            totalRightAns,
            expandedImage
        )
    }*/
    var caseStdyI = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_mcq2,
            container,
            false
        )
        pref = PreferenceManager(requireContext())
        viewModel = ViewModelProvider(this)[LiveViewModel::class.java]
        db = FirebaseFirestore.getInstance()
        userList = ArrayList()
        with(viewBinding) {
            rvOverAllScores.adapter = overAllScoreGainerAdapter
            rvQuesTopScores.adapter = quesScoreGainerAdapter

            rellSupSup.visibility = View.GONE
            //  RecyclerAnswer.adapter = queOptionsAdapter

            schoolCourseStructureId = arguments?.getString("schoolCourseStructureId").toString()
            selectedDimensions = arguments?.getString("selectedDimensions").toString()
            selectedLevels = arguments?.getString("selectedLevels").toString()
            selectedSubTopics = arguments?.getString("selectedSubTopics").toString()
            if (selectedSubTopics == null || selectedSubTopics.equals("null")) selectedSubTopics =
                ""
            if (selectedLevels == null || selectedLevels.equals("null")) selectedLevels = ""
            if (selectedDimensions == null || selectedDimensions.equals("null")) selectedDimensions =
                ""
            liveCode_ = arguments?.getString("liveCode_").toString()
            schoolClassCourseId = arguments?.getString("school_class_course_id").toString()
            pref = PreferenceManager(requireContext())
          //  fetchUsers(liveCode_)
        }
        getLiveFramentApi()
       // onClickedItemsNext_submit()
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        return viewBinding.root
    }

    private fun getLiveFramentApi() {

//        selectedDimensions= "1,2,3,4"
//        selectedLevels= "1,2,3,4"
//        selectedSubTopics=  ""
        selectedanswer = ArrayList()
//        viewModel.getLiveQues(pref!!.getValueString("token"), ModelLiveDetail("$schoolCourseStructureId",
//            "$selectedLevels","$selectedSubTopics",
//            "$selectedDimensions","$liveCode_") ).observe(viewLifecycleOwner,{
//             updateUI(it)
//        })
        //  var arrayModel = arrayListOf<ModelLiveDetail>()
        var model_ = ModelLiveDetail(
            "$schoolCourseStructureId",
            "$selectedLevels", "$selectedSubTopics",
            "$selectedDimensions", "$liveCode_"
        )
        //  arrayModel.add(model_)
        viewModel.getLiveQues(pref!!.getValueString("token"), model_).observe(viewLifecycleOwner, {
            updateUI(it)
        })
    }

    private fun onAnsCorrect_nextCounterStart() {
        with(viewBinding) {
            tvNextBtnCount.setVisibility(View.VISIBLE)
            tvNextBtnCount.setText("3")
            mCountDownTimer = object : CountDownTimer(3000, 100) {
                override fun onTick(millisUntilFinished: Long) {
                    tvNextBtnCount.setText(
                        "" + String.format(
                            " %d ",
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                        )
                    )
                }

                override fun onFinish() {
                    tvNextBtnCount.setVisibility(View.GONE)
                    if (isCountDownOnNext) llnext.performClick()
                    // else llsolution.performClick();
                }
            }
            mCountDownTimer!!.start()
        }
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
                when (_state.data) {
                    is PojMcqQues -> {
                        _state.data.let {
                            if (!it.message.equals("No Data Found!")) {
                                pojMcqQues = it
                               // setPojoData(it)
                            } else Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    is PojoAllUpdate -> {
                        _state.data.let {
                            //if (it.message.equals("Success")){
                            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                            //}
                        }
                    }
                }
                viewBinding.progressBar.visibility = View.GONE
            }
            else -> {}
        }
    }

  /*  private fun onClickedItemsNext_submit() {
        with(viewBinding) {
            llnext.setOnClickListener {
                if (SystemClock.elapsedRealtime() - lastClickTime < 2000) {
                    return@setOnClickListener
                }
                lastClickTime = SystemClock.elapsedRealtime()

                i++
                // setClickForNextData()
                with(viewBinding) {
                    RecyclerAnswer.layoutManager = LinearLayoutManager(requireContext())
                    pojMcqQues?.details!!.let {
                        try {
                            try {
                                getSet_FirebaseDatabaseEntery(i)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }

                            Log.e("TAG", "setClickForNextData:..replaceddd0..$i ... ..")
                            if (it[i].type.equals("Text MCQ") || it[i].type.equals("Image MCQ")) {
                                //System.out.println("pojoQuestions_size.."+pojoQuestions.getFlipsMarks().size());
                                for (k in 0 until it[i].answer_details.size) {
                                    if (it[i].answer_details[k].types.equals("Right")) {
                                        totalRightAns++
                                    }
                                }
                                tvMcqType.setText("Single Answer MCQ")
                                RecyclerAnswer.adapter = QueOptionsAdapter(
                                    it[i].answer_details,
                                    "question",
                                    it[i].define_type!!,
                                    totalRightAns,
                                    expandedImage,
                                    shortAnimationDuration,
                                    selectedanswer!!
                                )

                                println(
                                    "replaceddd0..${
                                        getLatexSolvedQ_Str(
                                            it[i].question!!,
                                            true
                                        )
                                    }...formulaOne.."
                                )
                                formulaOne.setDisplayText(
                                    getLatexSolvedQ_Str(
                                        it[i].question!!,
                                        true
                                    )
                                )
                                llimage.visibility = View.GONE
                                if (!it[i].image.equals("")) {
                                    llimage.visibility = View.VISIBLE
                                    Glide.with(requireContext()).load(it[i].image).into(ivQuesimage)
                                }
                                tvMcqType.setText(it[i].define_type)
                                //   tv_quetype_s.setText(it[i].getDefine_type());
                                rlLoader.setVisibility(View.GONE)
                                llsolution.visibility =
                                    if (it[i].solution_details == null) View.GONE else View.VISIBLE
                                if (it[i].define_type!!.lowercase().trim().contains("single"))
                                    getFlippMarks("single", it[i])
                                else if (it[i].define_type!!.lowercase().trim()
                                        .contains("multiple")
                                )
                                    getFlippMarks("multiple", it[i])

                            } else if (it[i].type.equals("Truefalse") || it[i].type.equals("Image Truefalse") || it[i].type.equals(
                                    "Text Truefalse"
                                )
                            ) {
                                RecyclerAnswer.layoutManager =
                                    GridLayoutManager(requireContext(), 2)
                                trueFalseAdapter = TrueFalseAdapter(
                                    it[i].truefalse_answer_details!!,
                                    "question",
                                    selectedanswer!!
                                )
                                RecyclerAnswer.adapter = trueFalseAdapter
                                tvMcqType.setText("True or False")
                                formulaOne.setDisplayText(
                                    getLatexSolvedQ_Str(
                                        it[i].question!!,
                                        true
                                    )
                                )

                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                            Toast.makeText(requireContext(), "Last question", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                }
            }
        }
        private fun setPojoData(itp: PojMcqQues) {
            with(viewBinding) {
                itp.details.let {
                    try {
                        i = 0
                        try {
                            if (itp.details.size > 0)
                                getSet_FirebaseDatabaseEntery(i)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        if (itp.message.equals("No list")) {
                            Toast.makeText(getActivity(), "No questions.. ", Toast.LENGTH_SHORT)
                                .show();
                        } else {
                            if (it[i].type.equals("Text MCQ") || it[i].type.equals("Image MCQ")) {
                                //System.out.println("pojoQuestions_size.."+pojoQuestions.getFlipsMarks().size());
                                for (k in 0 until it[i].answer_details.size) {
                                    if (it[i].answer_details[k].types.equals("Right")) {
                                        totalRightAns++
                                    }
                                }
                                tvMcqType.setText("Single Answer MCQ")


                                RecyclerAnswer.adapter = QueOptionsAdapter(
                                    it[i].answer_details,
                                    "question",
                                    it[i].define_type!!,
                                    totalRightAns,
                                    expandedImage,
                                    shortAnimationDuration,
                                    selectedanswer!!
                                )

//                            println("replaceddd0..${getLatexSolvedQ_Str(it[i].question!!, true)}...formulaOne..")
//                            formulaOne.setDisplayText(getLatexSolvedQ_Str(it[i].question!!, true))
                                llimage.visibility = View.GONE
                                if (!it[i].image.equals("")) {
                                    llimage.visibility = View.VISIBLE
                                    Glide.with(requireContext()).load(it[i].image).into(ivQuesimage)
                                }
                                tvMcqType.setText(it[i].define_type)
                                //   tv_quetype_s.setText(it[i].getDefine_type());
                                rlLoader.setVisibility(View.GONE)
                                llsolution.visibility =
                                    if (it[i].solution_details == null) View.GONE else View.VISIBLE
//                            if (it[i].define_type!!.lowercase().trim().contains("single"))
//                                getFlippMarks("single", it[i])
//                              else if (it[i].define_type!!.lowercase().trim().contains("multiple"))
//                                  getFlippMarks("multiple", it[i])

                            } else if (it[i].type.equals("Truefalse") || it[i].type.equals("Image Truefalse") || it[i].type.equals(
                                    "Text Truefalse"
                                )
                            ) {
                                trueFalseAdapter = TrueFalseAdapter(
                                    it[i].truefalse_answer_details!!,
                                    "question",
                                    selectedanswer!!
                                )
                                RecyclerAnswer.adapter = trueFalseAdapter

                                tvMcqType.setText("True or False")
                                // formulaOne.setDisplayText(getLatexSolvedQ_Str(it[i].question!!, true))

                            }


                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        *//*private fun setClickForNextData() {
        with(viewBinding) {
            RecyclerAnswer.layoutManager = LinearLayoutManager(requireContext())
                pojMcqQues?.details!!.let {
                try {
                    try {
                        getSet_FirebaseDatabaseEntery(i)
                    }catch (e:Exception){e.printStackTrace()}

                    Log.e("TAG", "setClickForNextData:..replaceddd0..$i ... ..")
                    if (it[i].type.equals("Text MCQ") || it[i].type.equals("Image MCQ"))
                        {
                            //System.out.println("pojoQuestions_size.."+pojoQuestions.getFlipsMarks().size());
                            for (k in 0 until it[i].answer_details.size) {
                                if (it[i].answer_details[k].types.equals("Right")) {
                                    totalRightAns++
                                }
                            }
                            tvMcqType.setText("Single Answer MCQ")
                            RecyclerAnswer.adapter = QueOptionsAdapter(it[i].answer_details,"question", it[i].define_type!!,totalRightAns, expandedImage, shortAnimationDuration, selectedanswer!! )

                            println("replaceddd0..${getLatexSolvedQ_Str(it[i].question!!, true)}...formulaOne..")
                            formulaOne.setDisplayText(getLatexSolvedQ_Str(it[i].question!!, true))
                            llimage.visibility = View.GONE
                            if (!it[i].image.equals("")) {
                                llimage.visibility = View.VISIBLE
                                Glide.with(requireContext()).load(it[i].image).into(ivQuesimage)
                            }
                            tvMcqType.setText(it[i].define_type)
                            //   tv_quetype_s.setText(it[i].getDefine_type());
                            rlLoader.setVisibility(View.GONE)
                            llsolution.visibility = if (it[i].solution_details == null) View.GONE else View.VISIBLE
                            if (it[i].define_type!!.lowercase().trim().contains("single"))
                                getFlippMarks("single", it[i])
                              else if (it[i].define_type!!.lowercase().trim().contains("multiple"))
                                  getFlippMarks("multiple", it[i])

                        }
                    else if (it[i].type.equals("Truefalse") || it[i].type.equals("Image Truefalse") || it[i].type.equals("Text Truefalse"))
                    {
                        RecyclerAnswer.layoutManager = GridLayoutManager(requireContext(),2)
                        trueFalseAdapter = TrueFalseAdapter(it[i].truefalse_answer_details!!, "question", selectedanswer!!)
                        RecyclerAnswer.adapter = trueFalseAdapter
                        tvMcqType.setText("True or False")
                        formulaOne.setDisplayText(getLatexSolvedQ_Str(it[i].question!!, true))

                    }

                } catch (e: Exception)
                {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Last question", Toast.LENGTH_SHORT).show()
                }
                   try {
                       if (it[i].type.equals("Text MCQ") || it[i].type.equals("Image MCQ")
                           || it[i].type.equals("Truefalse") || it[i].type.equals("Image Truefalse")
                           || it[i].type.equals("Text Truefalse")
                           || it[i].type.equals("Text Arrange") || it[i].type.equals("Image Arrange")
                           || it[i].type.equals("Fillup") || it[i].type.equals("Diagram")
                           || it[i].type.equals("Case Study") || it[i].type.equals("Assertion Reasoning")
                       ) {
                           llsolnext.visibility = View.GONE
                           selectedanswer = java.util.ArrayList()
                           correctAnswer = java.util.ArrayList()
                           i++
                           Log.e("TAG", "setClickForNextData:..replaceddd0..$i ... ..")
                           if (it[i].type.equals("Text MCQ") || it[i].type.equals("Image MCQ")) {
                               //System.out.println("pojoQuestions_size.."+pojoQuestions.getFlipsMarks().size());
                               for (k in 0 until it[i].answer_details.size) {
                                   if (it[i].answer_details[k].types.equals("Right")) {
                                       totalRightAns++
                                   }
                               }
                               tvMcqType.setText("Single Answer MCQ")
                               RecyclerAnswer.adapter = QueOptionsAdapter(
                                   it[i].answer_details,
                                   "question",
                                   it[i].define_type!!,
                                   totalRightAns,
                                   expandedImage,
                                   shortAnimationDuration,
                                   selectedanswer!!
                               )

                               println("replaceddd0..${getLatexSolvedQ_Str(it[i].question!!, true)}...formulaOne..")
                               formulaOne.setDisplayText(getLatexSolvedQ_Str(it[i].question!!, true))
                               llimage.visibility = View.GONE
                               if (!it[i].image.equals("")) {
                                   llimage.visibility = View.VISIBLE
                                   Glide.with(requireContext()).load(it[i].image).into(ivQuesimage)
                               }
                               tvMcqType.setText(it[i].define_type)
                               //   tv_quetype_s.setText(it[i].getDefine_type());
                               rlLoader.setVisibility(View.GONE)
                               llsolution.visibility =
                                   if (it[i].solution_details == null) View.GONE else View.VISIBLE
                               if (it[i].define_type!!.lowercase().trim().contains("single"))
                                   getFlippMarks("single", it[i])
                               else if (it[i].define_type!!.lowercase().trim().contains("multiple"))
                                   getFlippMarks("multiple", it[i])

                           } else if (it[i].type.equals("Truefalse") || it[i].type.equals("Image Truefalse") || it[i].type.equals(
                                   "Text Truefalse")
                           ) {
                               RecyclerAnswer.layoutManager = GridLayoutManager(requireContext(), 2)
                               trueFalseAdapter = TrueFalseAdapter(it[i].truefalse_answer_details!!,
                                   "question", selectedanswer!!)
                               RecyclerAnswer.adapter = trueFalseAdapter
                               tvMcqType.setText("True or False")
                               formulaOne.setDisplayText(getLatexSolvedQ_Str(it[i].question!!, true))
                           } else if (it[i].type.equals("Text Arrange")
                               || it[i].type.equals("Image Arrange")
                           ) {
                               llmcq.setVisibility(View.GONE)
                               lldescriptive.setVisibility(View.GONE)
                               llnumeric.setVisibility(View.GONE)
                               Recycler_answer.visibility = View.VISIBLE //tv_quePoints

                               tvMcqType.setText("Arrange")
                               // tv_quetype_s.setText("Arrange");
                               // tv_quetype_s.setText("Arrange");
                              viewBinding.horizontalFillup.setVisibility(View.GONE)

                               ansDetail_arng = ArrayList<String>()
                               ansDetail_arng!!.clear()
                               for (p in 0..it[i].arrangeAnswerDetail!!.size - 1) {
                                   ansDetail_arng!!.add(it[i].answer_details[p].types)
                               }

                               totalRightAns = it[i].arrangeAnswerDetail!!.size
                                 adapter = arrangeAdapter(it[i].arrangeAnswerDetail!!, "question",  it[i].type!!, expandedImage,shortAnimationDuration)
                               Recycler_answer.layoutManager = LinearLayoutManager(
                                   activity, LinearLayoutManager.VERTICAL, false)
                               Recycler_answer.adapter = adapter
                               val ques: String = it[i].question!!
                               llimage.visibility = View.GONE
                               if (!it[i].image.equals("")
                               ) {
                                   llimage.visibility = View.VISIBLE
                               }
                               //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                               //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                               formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                               if (it[i].type!!.lowercase().trim().contains("arrange")
                               ) {
                                   getFlippMarks("arrange",it[i])
                               }
                           }
                       }
                   } catch (e: Exception) {
                       e.printStackTrace()
                   }

                    try {
                        tv_solutionbtn.text = "Solution"
                        scrollBar_solution.visibility = View.GONE
                        //Log.e("catch_exception", "count:" + count + " " + it[i].getType());
                        if (it[i].type.equals("Text MCQ")
                            || it[i].type.equals("Image MCQ")
                            || it[i].type.equals("Truefalse")
                            || it[i].type.equals("Image Truefalse")
                            || it[i].type.equals("Text Truefalse")
                            || it[i].type.equals("Text Arrange")
                            || it[i].type.equals("Image Arrange")
                            || it[i].type.equals("Fillup")
                            || it[i].type.equals("Diagram")
                            || it[i].type.equals("Case Study")
                            || it[i].type.equals("Assertion Reasoning")
                        ) {
                            llsolnext.visibility = View.GONE
                            
                            selectedanswer = java.util.ArrayList()
                            correctAnswer = java.util.ArrayList()
                            i++
                            //System.out.println("llnext...." + i);
                            questionId = it[i].questions_all_types_id!!
                            //                        Log.d("QuestionId2",it[i].getQuestionsAllTypesId());
                            if (it[i].type.equals("Text MCQ")
                                || it[i].type.equals("Image MCQ")
                            ) {
                                // Log.e("catch_exception", "i:" + i);
                                llmcq.visibility = View.GONE
                                lldescriptive.visibility = View.GONE
                                llnumeric.visibility = View.GONE
                                horizontal_fillup.visibility = View.GONE
                                llsolnext.visibility = View.GONE
                                // llsolnext.setVisibility(View.VISIBLE);
                                Recycler_answer.visibility = View.VISIBLE
                                //
                                for (k in 0 until it[i].answer_details.size) {
                                    if (it[i].answer_details[k].types.equals("Right")
                                    ) {
                                        totalRightAns++
                                    }
                                }
                                getSetDefineType(it[i].define_type!!, totalRightAns)

                                val adapter =
                                    QueOptionsAdapter(it[i].answer_details, "question", it[i].define_type!!, totalRightAns, selectedanswer)
                                Recycler_answer.layoutManager = LinearLayoutManager(
                                    activity, LinearLayoutManager.VERTICAL, false
                                )
                                Recycler_answer.adapter = adapter
                                val ques: String = it[i].question!!
                                // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                //formula_one.setDisplayText(ques);
                                llimage.visibility = View.GONE
                                tvMcqType.setText(it[i].define_type)
                                // tv_quetype_s.setText(it[i].getdefine_type!!);
                                rlLoader.visibility = View.GONE
                                llimage.visibility = View.GONE
                                if (!it[i].image.equals("")
                                ) {
                                    llimage.visibility = View.VISIBLE
                                    getQues_Image()
                                }
                                if (it[i].define_type!!.lowercase().trim().contains("single")) {
                                    getFlippMarks("single",it[i])
                                } else if (it[i].define_type!!.lowercase().trim().contains("multiple")) {
                                    getFlippMarks("multiple",it[i])
                                }
                            } else if (it[i].type.equals("Truefalse")
                                || it[i].type.equals("Image Truefalse")
                                || it[i].type.equals("Text Truefalse")
                            ) {
                                llmcq.visibility = View.GONE
                                lldescriptive.visibility = View.GONE
                                llnumeric.visibility = View.GONE
                                Recycler_answer.visibility = View.VISIBLE
                                horizontal_fillup.visibility = View.GONE
                                llsolnext.visibility = View.GONE
                                
                                val adapter =
                                    TrueFalseAdapter(it[i].truefalseAnswerDetails, "question", selectedanswer)
                                Recycler_answer.layoutManager = GridLayoutManager(activity, 2)
                                Recycler_answer.adapter = adapter
                                val ques: String = it[i].question!!
                                llimage.visibility = View.GONE
                                if (!it[i].image.equals("")
                                ) {
                                    llimage.visibility = View.VISIBLE
                                    getQues_Image()
                                }
                                tvMcqType.setText("True or False")
                                // tv_quetype_s.setText("True or False");
                                //  Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                formula_one.visibility = View.VISIBLE
                                formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                //                            }
                                if (it[i].type!!.lowercase().trim().contains("truefalse")
                                ) {
                                    getFlippMarks("truefalse",it[i])
                                }
                            } else if (it[i].type.equals("Text Arrange")
                                || it[i].type.equals("Image Arrange")
                            ) {
                                llmcq.visibility = View.GONE
                                lldescriptive.visibility = View.GONE
                                llnumeric.visibility = View.GONE
                                Recycler_answer.visibility = View.VISIBLE //tv_quePoints
                                tvMcqType.setText("Arrange")
                                // tv_quetype_s.setText("Arrange");
                                horizontal_fillup.visibility = View.GONE

                                ansDetail_arng = java.util.ArrayList()
                                ansDetail_arng!!.clear()
                                for (p in 0..it[i].arrangeAnswerDetail.size() - 1) {
                                    ansDetail_arng!!.add(it[i].arrangeAnswerDetail!!.get(p).types)
                                }

                                totalRightAns = it[i].arrangeAnswerDetail.size
                                adapter = arrangeAdapter(
                                    it[i].arrangeAnswerDetail,
                                    "question",
                                    it[i].type!!, expandedImage, shortAnimationDuration)
                                Recycler_answer.layoutManager = LinearLayoutManager(
                                    activity, LinearLayoutManager.VERTICAL, false
                                )
                                Recycler_answer.adapter = adapter
                                val ques: String = it[i].question!!
                                llimage.visibility = View.GONE
                                if (!it[i].image.equals("")
                                ) {
                                    llimage.visibility = View.VISIBLE
                                }
                                //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                if (it[i].type!!.lowercase().trim().contains("arrange")
                                ) {
                                    getFlippMarks("arrange",it[i])
                                }
                            } else if (it[i].type.equals("Descriptive")
                            ) {
                                tvMcqType.setText("Descriptive")
                                // tv_quetype_s.setText("Descriptive");
                                lldescriptive.visibility = View.VISIBLE
                                llmcq.visibility = View.GONE
                                llnumeric.visibility = View.GONE
                                Recycler_answer.visibility = View.VISIBLE
                                horizontal_fillup.visibility = View.GONE
                                val adapter: descriptiveAdapter =
                                    descriptiveAdapter(it[i].descriptiveVisualizationDetails!!, "question")
                                Recycler_answer.layoutManager = LinearLayoutManager(
                                    activity, LinearLayoutManager.VERTICAL, false
                                )
                                Recycler_answer.adapter = adapter
                                val ques: String = it[i].question!!
                                llimage.visibility = View.GONE

                                //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                //  if(it[i].getDescriptiveVisualizationDetails().contains("")){

                                //}
//                            if(selectedanswer.size()==marksRight){
//                                // int marksRight =0;
//                                flipp_Obtained_Marks(marksRight, true);
//                            }
                                // =====
                            } else if (it[i].type.equals("Numerical")
                            ) {
                                tvMcqType.setText("Numerical")
                                // tv_quetype_s.setText("Numerical");
                                llnumeric.visibility = View.VISIBLE
                                llmcq.visibility = View.GONE
                                lldescriptive.visibility = View.GONE
                                horizontal_fillup.visibility = View.GONE
                                Recycler_answer.visibility = View.VISIBLE
                                getFlippMarks("interpretation",it[i])
                                for (k in 0..it[i].nInterpretationAnswerDetails!!.size - 1) {
                                    if (it[i].nInterpretationAnswerDetails!!.get(k).interpretationRightWrong.equals("Right")
                                    ) {
                                        totalRightAns++
                                    }
                                }
                                val adapter: nuricalAdapter =
                                    nuricalAdapter(it[i], "question", totalRightAns)
                                Recycler_answer.layoutManager = LinearLayoutManager(
                                    activity, LinearLayoutManager.VERTICAL, false
                                )
                                Recycler_answer.adapter = adapter
                                val ques: String = it[i].question!!
                                llimage.visibility = View.GONE

                                // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                //adapter.notifyDataSetChanged();
                            } else if (it[i].type.equals("Diagram")
                            ) {
                                tvMcqType.setText("Label The Diagram")
                                //  tv_quetype_s.setText("Label The Diagram");
                                llQues.visibility = View.GONE
                                llDiagram.visibility = View.VISIBLE
                                llnumeric.visibility = View.GONE
                                llmcq.visibility = View.GONE
                                lldescriptive.visibility = View.GONE
                                horizontal_fillup.visibility = View.GONE
                                Recycler_answer.visibility = View.GONE
                                llsolnext.visibility = View.GONE
                                rlLoader.visibility = View.VISIBLE

                                //   setTheDiagramLables(it[i]);
                                rlLoader.visibility = View.GONE
                                getFlippMarks("diagram",it[i])
                                //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                            } else if (it[i].type.equals("Fillup")
                            ) {
                                tvMcqType.setText("Fillup")
                                // tv_quetype_s.setText("Fillup");
                                llQues.visibility = View.GONE
                                llDiagram.visibility = View.GONE
                                llnumeric.visibility = View.GONE
                                llmcq.visibility = View.GONE
                                lldescriptive.visibility = View.GONE
                                horizontal_fillup.visibility = View.VISIBLE
                                Recycler_answer.visibility = View.GONE
                                Recycler_answer.visibility = View.GONE
                                llsolnext.visibility = View.GONE
                                llsolution.visibility = View.GONE
                                rlLoader.visibility = View.VISIBLE
                                
                                SetFillUpNewSpan(it[i])
                                // setTheFillUps_Blanks(it[i]);
                                rlLoader.visibility = View.GONE
                                getFlippMarks("fillup",it[i])

                                //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                            } else if (it[i].type.equals("Case Study")
                            ) {
                                llnumeric.visibility = View.GONE
                                llQues1.visibility = View.GONE
                                llQues.visibility = View.GONE
                                relMain_asrtion.visibility = View.GONE
                                tv_sereas_res.setText("Case")
                                tvMcqType.setText("Case Study")
                                // tv_quetype_s.setText("Case Study");
                                tvReadMore.visibility = View.VISIBLE
                                Recycler_answer.visibility = View.VISIBLE
                                recycler_Title.setVisibility(View.VISIBLE)
                                relCaseStdy.visibility = View.VISIBLE

                                var oi = 0
                                for (j in 0 until it[i].caseStudyQuesDetail!!.get(caseStdyI).casestudyMcqs)
                                    .size()) {
                                    if (it[i].caseStudyQuesDetail!!.get(caseStdyI)
                                            .casestudyMcqs.get(j).types.equals("Right")
                                    ) {
                                        oi++
                                    }
                                }
                                var questiontype = "single"
                                if (oi > 1) {
                                    questiontype = "multiple"
                                }
                                //caseStudyAnsSelectList.add(new CaseStudyAnsSelected(caseStdyI+"", "",oi+"",""));
                                val titleAdapter = CaseStudyTitleAdapter(requireContext(), it[i], caseStdyI.toString() + "")
                                recycler_Title.setAdapter(titleAdapter)
                                val adapter: caseStudyAnsAdapter =
                                    caseStudyAnsAdapter(
                                        it[i],
                                        "question",
                                        totalRightAns,
                                        questiontype
                                    )
                                Recycler_answer.layoutManager = LinearLayoutManager(
                                    activity, LinearLayoutManager.VERTICAL, false
                                )
                                Recycler_answer.adapter = adapter
                                val ques: String =
                                    it[i].getCaseStudyDetails().get(0)
                                        .getCase()
                                llimage.visibility = View.GONE
                                //                    if (!it[i].getImage().equals("")) {
//                        llimage.setVisibility(View.VISIBLE);
//                        Glide.with(getActivity())
//                                .load(it[i].getImage() )
//                                .into(iv_quesimage);
//                    }
                                // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                formula_one1_res.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                tvReadMore.setOnClickListener { v1: View? ->
                                    getCaseStudyReadMoreDialog(
                                        getLatexSolvedQ_Str(ques, true)
                                    )
                                }
                                if (it[i].solution_details == null
                                ) {
                                    llsolution.visibility = View.GONE
                                } else {
                                    llsolution.visibility = View.VISIBLE
                                }
                                if (questiontype.lowercase().trim ().contains("single")) {
                                    getFlippMarks("single",it[i])
                                } else if (questiontype.lowercase().trim ().contains("multiple")) {
                                    getFlippMarks("multiple",it[i])
                                }
                            } else if (it[i].type.equals("Assertion Reasoning")
                            ) {
                                llnumeric.visibility = View.GONE
                                llQues1.visibility = View.GONE
                                llQues.visibility = View.GONE
                                tvReadMore.visibility = View.GONE
                                recycler_Title.setVisibility(View.GONE)
                                tv_sereas_res.setText("Assertion")
                                tv_sereas_res_asrtion.setText("Reasoning")
                                tvMcqType.setText("Assertion Reasoning")
                                // tv_quetype_s.setText("Assertion Reasoning");
                                Recycler_answer.visibility = View.VISIBLE
                                relMain_asrtion.visibility = View.VISIBLE
                                relCaseStdy.visibility = View.VISIBLE


                                //int oi= 0;
                                for (k in 0 until it[i].answer_details.size) {
                                    if (it[i].answer_details.get(k).types.equals("Right")
                                    ) {
                                        totalRightAns++
                                    }
                                }
                                var questiontype = "single"
                                if (totalRightAns > 1) questiontype = "multiple"
                                getSetDefineType(
                                    it[i].define_type!!,
                                    totalRightAns
                                )
                                val adapter: queOptionsAdapter =
                                    queOptionsAdapter(
                                        it[i].getAnswerDetails(),
                                        "question",
                                        it[i].define_type!!,
                                        totalRightAns
                                    )
                                Recycler_answer.layoutManager =
                                    LinearLayoutManager(
                                        activity,
                                        LinearLayoutManager.VERTICAL,
                                        false
                                    )
                                Recycler_answer.adapter = adapter
                                val ques: String =
                                    it[i].getAssertionReasoningDetails()
                                        .get(0).getAssertion()
                                formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                val quesReason: String =
                                    it[i].getAssertionReasoningDetails()
                                        .get(0).getReasoning()
                                formulaOne1ResAsrtion.setDisplayText(
                                    getLatexSolvedQ_Str(
                                        quesReason,
                                        true
                                    )
                                )
                                llimage.visibility = View.GONE //
                                if (it[i].solution_details == null
                                ) {
                                    llsolution.visibility = View.GONE
                                } else {
                                    llsolution.visibility = View.VISIBLE
                                }
                                if (questiontype.toLowerCase().trim ().contains("single")) {
                                    getFlippMarks("single",it[i])
                                } else if (questiontype.lowercase().trim()                                        .contains("multiple")) {
                                    getFlippMarks("multiple",it[i])
                                }
                            }
                        } else if (it[i].type.equals("Diagram")
                        ) {
                            tvMcqType.setText("Label The Diagram")
                            // tv_quetype_s.setText("Label The Diagram");
                            llQues.visibility = View.GONE
                            llDiagram.visibility = View.VISIBLE
                            llnumeric.visibility = View.GONE
                            llmcq.visibility = View.GONE
                            lldescriptive.visibility = View.GONE
                            horizontal_fillup.visibility = View.GONE
                            Recycler_answer.visibility = View.GONE
                            llsolnext.visibility = View.GONE
                            rlLoader.visibility = View.VISIBLE

                            // setTheDiagramLables(it[i]);
                            rlLoader.visibility = View.GONE
                            getFlippMarks("diagram",it[i])
                            //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                        } else if (it[i].type.equals("Case Study")
                        ) {
                            llnumeric.visibility = View.GONE
                            llQues1.visibility = View.GONE
                            llQues.visibility = View.GONE
                            relMain_asrtion.visibility = View.GONE
                            tv_sereas_res.setText("Case")
                            tvMcqType.setText("Case Study")
                            // tv_quetype_s.setText("Case Study");
                            tvReadMore.visibility = View.VISIBLE
                            Recycler_answer.visibility = View.VISIBLE
                            recycler_Title.setVisibility(View.VISIBLE)
                            relCaseStdy.visibility = View.VISIBLE

                            var oi = 0
                            for (j in 0 until it[i]
                                .caseStudyQuesDetail.get(caseStdyI).getCasestudyMcqs()
                                .size()) {
                                if (it[i].getCaseStudyQuesDetails()
                                        .get(caseStdyI)
                                        .getCasestudyMcqs().get(j).getTypes()
                                        .equals("Right")
                                ) {
                                    oi++
                                }
                            }
                            var questiontype = "single"
                            if (oi > 1) {
                                questiontype = "multiple"
                            }
                            //caseStudyAnsSelectList.add(new CaseStudyAnsSelected(caseStdyI+"", "",oi+"",""));
                            val titleAdapter = CaseStudyTitleAdapter(
                                requireContext(),
                                it[i],
                                caseStdyI.toString() + ""
                            )
                            recycler_Title.setAdapter(titleAdapter)
                            val adapter: CaseStudyAnsAdapter =
                                CaseStudyAnsAdapter(
                                    it[i],
                                    "question",
                                    totalRightAns,
                                    questiontype
                                )
                            Recycler_answer.layoutManager = LinearLayoutManager(
                                activity, LinearLayoutManager.VERTICAL, false
                            )
                            Recycler_answer.adapter = adapter
                            val ques: String = it[i].caseStudyDetails.get(0).case!!
                            llimage.visibility = View.GONE
                            tvReadMore.setOnClickListener { v1: View? ->
                                getCaseStudyReadMoreDialog(
                                    getLatexSolvedQ_Str(ques, true)
                                )
                            }

//                    if (!it[i].getImage().equals("")) {
//                        llimage.setVisibility(View.VISIBLE);
//                        Glide.with(getActivity())
//                                .load(it[i].getImage() )
//                                .into(iv_quesimage);
//                    }
                            // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                            formula_one1_res.setDisplayText(getLatexSolvedQ_Str(ques, true))
                            if (it[i].solution_details == null) {
                                llsolution.visibility = View.GONE
                            } else {
                                llsolution.visibility = View.VISIBLE
                            }
                            if (questiontype.toLowerCase().trim { it <= ' ' }.contains("single")) {
                                getFlippMarks("single",it[i])
                            } else if (questiontype.lowercase().trim ()
                                    .contains("multiple")) {
                                getFlippMarks("multiple",it[i])
                            }
                        } else if (it[i].type.equals("Assertion Reasoning")
                        ) {
                            llnumeric.visibility = View.GONE
                            llQues1.visibility = View.GONE
                            llQues.visibility = View.GONE
                            tvReadMore.visibility = View.GONE
                            recycler_Title.setVisibility(View.GONE)
                            tv_sereas_res.setText("Assertion")
                            tv_sereas_res_asrtion.setText("Reasoning")
                            tvMcqType.setText("Assertion Reasoning")
                            //  tv_quetype_s.setText("Assertion Reasoning");
                            Recycler_answer.visibility = View.VISIBLE
                            relMain_asrtion.visibility = View.VISIBLE
                            relCaseStdy.visibility = View.VISIBLE


                            val oi = 0
                            for (k in 0 until it[i].answer_details.size) {
                                if (it[i].answer_details.get(k)
                                        .types.equals("Right")
                                ) {
                                    totalRightAns++
                                }
                            }
                            var questiontype = "single"
                            if (oi > 1) {
                                questiontype = "multiple"
                            }
                            getSetDefineType(
                                it[i].define_type!!,
                                totalRightAns
                            )
                            val adapter: QueOptionsAdapter =
                                QueOptionsAdapter(
                                    it[i].answer_details,
                                    "question",
                                    it[i].define_type!!,
                                    totalRightAns
                                )
                            Recycler_answer.layoutManager = LinearLayoutManager(
                                activity, LinearLayoutManager.VERTICAL, false
                            )
                            Recycler_answer.adapter = adapter
                            val ques: String =
                                it[i].assertionReasoningDetail
                                    .get(0).assertion
                            formula_one1_res.setDisplayText(getLatexSolvedQ_Str(ques, true))
                            val quesReason: String =
                                it[i].assertionReasoningDetail
                                    .get(0).reasoning
                            formula_one1_res_asrtion.setDisplayText(
                                getLatexSolvedQ_Str(
                                    quesReason,
                                    true
                                )
                            )
                            llimage.visibility = View.GONE //
                            if (it[i].solution_details == null) {
                                llsolution.visibility = View.GONE
                            } else {
                                llsolution.visibility = View.VISIBLE
                            }
                            if (questiontype.toLowerCase().trim().contains("single")) {
                                getFlippMarks("single",it[i])
                            } else if (questiontype.toLowerCase().trim()
                                    .contains("multiple")) {
                                getFlippMarks("multiple",it[i])
                            }
                        } else {
                            if (count == -10) {
                                count = 0
                                llsolnext.visibility = View.GONE
                                
                                selectedanswer = java.util.ArrayList()
                                correctAnswer = java.util.ArrayList()
                                horizontal_fillup.visibility = View.GONE
                                i++
                                if (it[i].type.equals("Text MCQ")
                                    || it[i].type.equals("Image MCQ")
                                ) {
                                    // Log.e("catch_exception", "i:" + i);
                                    llmcq.visibility = View.GONE
                                    lldescriptive.visibility = View.GONE
                                    llnumeric.visibility = View.GONE
                                    Recycler_answer.visibility = View.VISIBLE
                                    //                                if ((i + 1) >= pojoQuestions.getDetails().size()) {
//                                    Toast.makeText(getActivity(), "Last question", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(getActivity(), HomeActivty.class));
//                                } else {
                                    for (k in 0 until it[i]
                                        .answer_details.size) {
                                        if (it[i].answer_details.get(k).types.equals("Right")
                                        ) {
                                            totalRightAns++
                                        }
                                    }
                                    getSetDefineType(
                                        it[i].define_type!!,
                                        totalRightAns
                                    )
                                    val adapter: QueOptionsAdapter =
                                        QueOptionsAdapter(
                                            it[i].answer_details!!,
                                            "question",
                                            it[i].define_type!!,
                                            totalRightAns
                                        )
                                    Recycler_answer.layoutManager = LinearLayoutManager(
                                        activity, LinearLayoutManager.VERTICAL, false
                                    )
                                    Recycler_answer.adapter = adapter
                                    val ques: String =
                                        it[i].question
                                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                    formula_one.setDisplayText(ques)
                                    tvMcqType.setText(
                                        it[i].define_type!!
                                    )
                                    //   tv_quetype_s.setText(it[i].define_type!!);
                                    rlLoader.visibility = View.GONE
                                    llimage.visibility = View.GONE
                                    if (!it[i].image.equals("")
                                    ) {
                                        llimage.visibility = View.VISIBLE
                                        getQues_Image()
                                    }
                                    if (it[i].type.toLowerCase().trim().contains("single")
                                    ) {
                                        getFlippMarks("single",it[i])
                                    } else if (it[i].type.lowercase().trim().contains("multiple")
                                    ) {
                                        getFlippMarks("multiple",it[i])
                                    }
                                } else if (it[i].type.equals("Truefalse")
                                    || it[i].type.equals("Image Truefalse")
                                    || it[i].type.equals("Text Truefalse")
                                ) {
                                    llmcq.visibility = View.GONE
                                    lldescriptive.visibility = View.GONE
                                    llnumeric.visibility = View.GONE
                                    horizontal_fillup.visibility = View.GONE
                                    llsolnext.visibility = View.GONE
                                    
                                    //                                if ((i + 1) >= pojoQuestions.getDetails().size()) {
//                                    Toast.makeText(getActivity(), "Last question", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(getActivity(), HomeActivty.class));
//                                } else {
                                    val adapter: TrueFalseAdapter = TrueFalseAdapter(
                                            it[i].truefalseAnswerDetails, "question", selectedanswer)
                                    Recycler_answer.layoutManager = GridLayoutManager(activity, 2)
                                    Recycler_answer.adapter = adapter
                                    val ques: String =
                                        it[i].question!!
                                    llimage.visibility = View.GONE
                                    tvMcqType.setText("True or False")
                                    // tv_quetype_s.setText("True or False");
                                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                    //                                }
                                    if (it[i].type!!.toLowerCase()
                                            .trim().contains("truefalse")
                                    ) {
                                        getFlippMarks("truefalse",it[i])
                                    }
                                } else if (it[i].type!!.equals("Text Arrange")
                                    || it[i].type!!.equals("Image Arrange")
                                ) {
                                    llmcq.visibility = View.GONE
                                    lldescriptive.visibility = View.GONE
                                    llnumeric.visibility = View.GONE
                                    horizontal_fillup.visibility = View.GONE
                                    Recycler_answer.visibility = View.VISIBLE
                                    //                                if ((i + 1) >= pojoQuestions.getDetails().size()) {
//                                    startActivity(new Intent(getActivity(), HomeActivty.class));
//                                } else {
                                    tvMcqtype!!.setText("Arrange")
                                    //  tv_quetype_s.setText("Arrange");

                                    ansDetail_arng = java.util.ArrayList()
                                    ansDetail_arng!!.clear()
                                    for (p in 0..it[i].arrangeAnswerDetail!!.size - 1) {
                                        ansDetail_arng!!.add(it[i].arrangeAnswerDetail?.get(p).types!!)
                                    }

                                    totalRightAns =
                                        it[i].arrangeAnswerDetail!!.size
                                    adapter = arrangeAdapter(
                                        it[i].arrangeAnswerDetail,
                                        "question",
                                        it[i].type!!,expandedImage,shortAnimationDuration
                                    )
                                    Recycler_answer.layoutManager = LinearLayoutManager(
                                        activity, LinearLayoutManager.VERTICAL, false
                                    )
                                    Recycler_answer.adapter = adapter

                                    val ques: String =
                                        it[i].question!!
                                    llimage.visibility = View.GONE
                                    if (!it[i].image.equals("")
                                    ) {
                                        llimage.visibility = View.VISIBLE
                                        Glide.with(requireActivity())
                                            .load(it[i].image)
                                            .into(iv_quesimage)
                                    }
                                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                    //                                }
                                    if (it[i].type!!.lowercase().trim().contains("arrange")
                                    ) {
                                        getFlippMarks("arrange",it[i])
                                    }
                                } else if (it[i].type.equals("Descriptive")
                                ) {
                                    tvMcqType.setText("Descriptive")
                                    //  tv_quetype_s.setText("Descriptive");
                                    lldescriptive.visibility = View.VISIBLE
                                    Recycler_answer.visibility = View.VISIBLE
                                    llnumeric.visibility = View.GONE
                                    llmcq.visibility = View.GONE
                                    horizontal_fillup.visibility = View.GONE
                                    val adapter: descriptiveAdapter =
                                        descriptiveAdapter(it[i].descriptiveVisualizationDetails!!,
                                            "question"
                                        )
                                    Recycler_answer.layoutManager = LinearLayoutManager(
                                        activity, LinearLayoutManager.VERTICAL, false
                                    )
                                    Recycler_answer.adapter = adapter
                                    val ques: String =
                                        it[i].question!!
                                    llimage.visibility = View.GONE
                                    if (!it[i].image.equals("")
                                    ) {
                                        Glide.with(requireActivity())
                                            .load(it[i].image)
                                            .into(iv_quesimage)
                                    }
                                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))

                                } else if (it[i].type.equals("Numerical")
                                ) {
                                    tvMcqType.setText("Numerical")
                                    // tvMcqType_s.setText("Numerical");
                                    llnumeric.visibility = View.VISIBLE
                                    Recycler_answer.visibility = View.VISIBLE
                                    llmcq.visibility = View.GONE
                                    horizontal_fillup.visibility = View.GONE
                                    lldescriptive.visibility = View.GONE
                                    for (k in 0..it[i]
                                        .nInterpretationAnswerDetails.size - 1) {
                                        if (it[i]
                                                .nInterpretationAnswerDetails.get(k)
                                                .interpretationRightWrong
                                                .equals("Right")
                                        ) {
                                            totalRightAns++
                                        }
                                    }
                                    val adapter: nuricalAdapter =
                                        nuricalAdapter(
                                            it[i],
                                            "question",
                                            totalRightAns
                                        )
                                    Recycler_answer.layoutManager = LinearLayoutManager(
                                        activity, LinearLayoutManager.VERTICAL, false
                                    )
                                    Recycler_answer.adapter = adapter
                                    val ques: String =
                                        it[i].question!!
                                    llimage.visibility = View.GONE
                                    if (!it[i].image.equals("")
                                    ) {
                                        Glide.with(requireActivity())
                                            .load(it[i].image)
                                            .into(iv_quesimage)
                                    }

                                    adapter.notifyDataSetChanged()
                                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                    //                                if(it[i].getNInterpretationAnswerDetails().trim().contains("interpretation")){
//                                    getFlippMarks( "interpretation");
//                                }else if(it[i].getType().toLowerCase().trim().contains("visualization")){
//                                    getFlippMarks( "visualization");
//                                }
                                } else if (it[i].type.equals("Diagram")
                                ) {
                                    tvMcqType.setText("Label The Diagram")
                                    // tvMcqType_s.setText("Label The Diagram");
                                    llQues.visibility = View.GONE
                                    llDiagram.visibility = View.VISIBLE
                                    llnumeric.visibility = View.GONE
                                    llmcq.visibility = View.GONE
                                    lldescriptive.visibility = View.GONE
                                    Recycler_answer.visibility = View.GONE
                                    horizontal_fillup.visibility = View.GONE
                                    llsolnext.visibility = View.GONE
                                    rlLoader.visibility = View.VISIBLE

                                    //onDiagramLoadFrag.diagramLoad()
                                    // setTheDiagramLables(it[i]);
                                    rlLoader.visibility = View.GONE
                                    getFlippMarks("diagram",it[i])
                                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                } else if (it[i].type.equals("Fillup")
                                ) {
                                    tvMcqType.setText("Fillup")
                                    // tv_quetype_s.setText("Fillup");
                                    llQues.visibility = View.GONE
                                    llDiagram.visibility = View.VISIBLE
                                    llnumeric.visibility = View.GONE
                                    llmcq.visibility = View.GONE
                                    lldescriptive.visibility = View.GONE
                                    horizontal_fillup.visibility = View.VISIBLE
                                    Recycler_answer.visibility = View.GONE
                                    Recycler_answer.visibility = View.GONE
                                    llsolnext.visibility = View.GONE
                                    llsolution.visibility = View.GONE
                                    rlLoader.visibility = View.VISIBLE
                                    
                                    SetFillUpNewSpan(it[i])
                                    //setTheFillUps_Blanks(it[i]);
                                    rlLoader.visibility = View.GONE
                                    getFlippMarks("fillup".it[i])

                                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                } else if (it[i].type.equals("Case Study")
                                ) {
                                    llnumeric.visibility = View.GONE
                                    llQues1.visibility = View.GONE
                                    llQues.visibility = View.GONE
                                    tv_sereas_res.setText("Case")
                                    tvMcqType.setText("Case Study")
                                    //tvMcqType_s.setText("Case Study");
                                    Recycler_answer.visibility = View.VISIBLE
                                    recycler_Title.setVisibility(View.VISIBLE)
                                    relCaseStdy.visibility = View.VISIBLE


                                    var oi = 0
                                    for (j in 0 until it[i]
                                        .caseStudyQuesDetail.get(caseStdyI).casestudyMcqs.size) {
                                        if (it[i]
                                                .getCaseStudyQuesDetail().get(caseStdyI)
                                                .getCasestudyMcqs().get(j).getTypes()
                                                .equals("Right")
                                        ) {
                                            oi++
                                        }
                                    }
                                    var questiontype = "single"
                                    if (oi > 1) {
                                        questiontype = "multiple"
                                    }
                                    //caseStudyAnsSelectList.add(new CaseStudyAnsSelected(caseStdyI+"", "",oi+"",""));
                                    val titleAdapter = CaseStudyTitleAdapter(
                                        requireContext(),
                                        it[i],
                                        caseStdyI.toString() + ""
                                    )
                                    recycler_Title.setAdapter(titleAdapter)
                                    val adapter: CaseStudyAnsAdapter =
                                        CaseStudyAnsAdapter(
                                            it[i],
                                            "question",
                                            totalRightAns,
                                            questiontype
                                        )
                                    Recycler_answer.layoutManager = LinearLayoutManager(
                                        activity, LinearLayoutManager.VERTICAL, false
                                    )
                                    Recycler_answer.adapter = adapter
                                    val ques: String =
                                        it[i].caseStudyDetails.get(0).case
                                    llimage.visibility = View.GONE
                                  formula_one1_res.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                    tvReadMore.setOnClickListener { v1: View? ->
                                        getCaseStudyReadMoreDialog(
                                            getLatexSolvedQ_Str(ques, true)
                                        )
                                    }
                                    if (it[i].solution_details() == null
                                    ) {
                                        llsolution.visibility = View.GONE
                                    } else {
                                        llsolution.visibility = View.VISIBLE
                                    }
                                    if (questiontype.toLowerCase().trim().contains("single")) {
                                        getFlippMarks("single",it[i])
                                    } else if (questiontype.toLowerCase().trim().contains("multiple")) {
                                        getFlippMarks("multiple",it[i])
                                    }
                                } else if (it[i].type.equals("Assertion Reasoning")
                                ) {
                                    llnumeric.visibility = View.GONE
                                    llQues1.visibility = View.GONE
                                    tvReadMore.visibility = View.GONE
                                    recycler_Title.setVisibility(View.GONE)
                                    llQues.visibility = View.GONE
                                    tv_sereas_res.setText("Assertion")
                                    tv_sereas_res_asrtion.setText("Reasoning")
                                    tvMcqType.setText("Assertion Reasoning")

                                    Recycler_answer.visibility = View.VISIBLE
                                    relMain_asrtion.visibility = View.VISIBLE
                                    relCaseStdy.visibility = View.VISIBLE

                                    val oi = 0
                                    for (k in 0 until it[i].answer_details.size) {
                                        if (it[i].answer_details.get(k).types.equals("Right")
                                        ) {
                                            totalRightAns++
                                        }
                                    }
                                    var questiontype = "single"
                                    if (oi > 1) {
                                        questiontype = "multiple"
                                    }
                                    getSetDefineType(
                                        it[i].define_type!!,
                                        totalRightAns
                                    )
                                    val adapter: QueOptionsAdapter =
                                        QueOptionsAdapter(
                                            it[i].answer_details,
                                            "question",
                                            it[i].define_type!!,
                                            totalRightAns
                                        )
                                    Recycler_answer.layoutManager = LinearLayoutManager(
                                        activity, LinearLayoutManager.VERTICAL, false
                                    )
                                    Recycler_answer.adapter = adapter
                                    val ques: String = it[i].assertionReasoningDetail.get(0).assertion
                                    formula_one1_res.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                    val quesReason: String = it[i].assertionReasoningDetail.get(0).reasoning
                                    formula_one1_res_asrtion.setDisplayText(
                                        getLatexSolvedQ_Str(
                                            quesReason,
                                            true
                                        )
                                    )
                                    llimage.visibility = View.GONE //
                                    if (it[i]
                                            .solution_details() == null
                                    ) {
                                        llsolution.visibility = View.GONE
                                    } else {
                                        llsolution.visibility = View.VISIBLE
                                    }
                                    if (questiontype.toLowerCase().trim { it <= ' ' }
                                            .contains("single")) {
                                        getFlippMarks("single")
                                    } else if (questiontype.toLowerCase().trim { it <= ' ' }
                                            .contains("multiple")) {
                                        getFlippMarks("multiple")
                                    }
                                }
                            } else {
                                if (it[i].type.equals("Descriptive")
                                ) {
                                    lldescriptive.visibility = View.VISIBLE
                                    llnumeric.visibility = View.GONE
                                    llmcq.visibility = View.GONE
                                    rlspeaker.visibility = View.VISIBLE
                                    
                                    llnext.visibility = View.GONE
                                    Recycler_answer.visibility = View.GONE
                                    horizontal_fillup.visibility = View.GONE
                                    count++
                                    //                                Toast.makeText(getActivity(),"Last question",Toast.LENGTH_SHORT).show();
                                    tv_answer1.setBackgroundResource(R.drawable.round_boundary_green_new)
                                    viewAnswer1.setBackgroundColor(resources.getColor(R.color.parrotgreen))
                                    //  if(count<1){
                                    //speakfun()
                                    //  }
                                  //  submitSpeeak = 1

                                    // getFlippMarks("keyword");
                                    //  if(count>1){

//                                    llsolnext.setVisibility(View.GONE);
//                                    ;
//                                    rlspeaker.setVisibility(View.GONE);
//                                    Recycler_answer.setVisibility(View.VISIBLE);
//                                    rlLoader.setVisibility(View.GONE);
//                                }
                                } else if (it[i].type.equals("Numerical")
                                ) {
                                    llmcq.visibility = View.GONE
                                    lldescriptive.visibility = View.GONE
                                    llnumeric.visibility = View.VISIBLE
                                    Recycler_answer.visibility = View.VISIBLE
                                    horizontal_fillup.visibility = View.GONE
                                    if (count > 1) {
                                        llnext.callOnClick()
                                        //                                nikita
                                    } else if (count == 0 || count == -10) {
                                        count = 1
                                        selectedanswer = java.util.ArrayList()
                                        tv_answer.setBackgroundResource(R.drawable.round_boundary_green_new)
                                        viewAnswer.setBackgroundColor(resources.getColor(R.color.parrotgreen))
                                        llsolnext.visibility = View.GONE
                                        rlLoader.visibility = View.VISIBLE
                                        
                                        horizontal_fillup.visibility = View.GONE
                                        for (k in 0..it[i]
                                            .nVisualizationAnsDetails.size - 1) {
                                            if (it[i].nVisualizationAnsDetails.get(k).visualizationRightWrong
                                                    .equals("Right")) {
                                                totalRightAns++
                                            }
                                        }
                                        val adapter: nuricalAdapter =
                                            nuricalAdapter(
                                                it[i],
                                                "question1",
                                                totalRightAns
                                            )
                                        Recycler_answer.layoutManager = LinearLayoutManager(
                                            activity, LinearLayoutManager.VERTICAL, false
                                        )
                                        Recycler_answer.adapter = adapter
                                        val ques: String = it[i].question!!
                                        //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                        formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                        rlLoader.visibility = View.GONE


//                                    if(it[i].define_type!!.toLowerCase().trim().contains("interpretation")){
//                                        getFlippMarks( "interpretation");
//                                    }else if(it[i].define_type!!.toLowerCase().trim().contains("visualization")){
//
//                                    }
                                    }

                                } else if (it[i].type.equals("Diagram")
                                ) {
                                    tvMcqType.setText("Label The Diagram")

                                    llQues.visibility = View.GONE
                                    llDiagram.visibility = View.VISIBLE
                                    llnumeric.visibility = View.GONE
                                    llmcq.visibility = View.GONE
                                    lldescriptive.visibility = View.GONE
                                    Recycler_answer.visibility = View.GONE
                                    horizontal_fillup.visibility = View.GONE
                                    llsolnext.visibility = View.GONE
                                    rlLoader.visibility = View.VISIBLE
                                    

                                    //setTheDiagramLables(it[i]);
                                    rlLoader.setVisibility(View.GONE)
                                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                } else if (it[i].type.equals("Fillup")
                                ) {
                                    tvMcqType.setText("Fillup")
                                    // tv_quetype_s.setText("Fillup");
                                    llQues.visibility = View.GONE
                                    llDiagram.visibility = View.VISIBLE
                                    llnumeric.visibility = View.GONE
                                    llmcq.visibility = View.GONE
                                    lldescriptive.visibility = View.GONE
                                    horizontal_fillup.visibility = View.VISIBLE
                                    Recycler_answer.visibility = View.GONE
                                    Recycler_answer.visibility = View.GONE
                                    llsolnext.visibility = View.GONE
                                    llsolution.visibility = View.GONE
                                    rlLoader.visibility = View.VISIBLE
                                    
                                    SetFillUpNewSpan(it[i])
                                    // setTheFillUps_Blanks(it[i]);
                                    rlLoader.setVisibility(View.GONE)

                                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                } else if (it[i].type.equals("Case Study")
                                ) {
                                    llnumeric.visibility = View.GONE
                                    llQues1.visibility = View.GONE
                                    llQues.visibility = View.GONE
                                    tvser.setText("Case")
                                    tvMcqType.setText("Case Study")
                                    //  tv_quetype_s.setText("Case Study");
                                    Recycler_answer.visibility = View.VISIBLE
                                    recycler_Title.setVisibility(View.VISIBLE)
                                    relCaseStdy.visibility = View.VISIBLE
                                    recycler_Title.setVisibility(View.VISIBLE)

                                    caseStudyAnsSelectList =
                                        java.util.ArrayList<CaseStudyAnsSelected>()
                                    caseStudyAnsSelectList.clear()
                                    var oi = 0
                                    for (j in 0 until it[i]
                                        .getCaseStudyQuesDetails().get(caseStdyI).getCasestudyMcqs()
                                        .size()) {
                                        if (it[i]
                                                .getCaseStudyQuesDetails().get(caseStdyI)
                                                .getCasestudyMcqs().get(j).getTypes()
                                                .equals("Right")
                                        ) {
                                            oi++
                                        }
                                    }
                                    var questiontype = "single"
                                    if (oi > 1) {
                                        questiontype = "multiple"
                                    }
                                    val titleAdapter = CaseStudyTitleAdapter(
                                        requireContext(),
                                        it[i],
                                        caseStdyI.toString() + ""
                                    )
                                    recycler_Title.setAdapter(titleAdapter)
                                    val adapter: CaseStudyAnsAdapter =
                                        CaseStudyAnsAdapter(
                                            it[i],
                                            "question",
                                            totalRightAns,
                                            questiontype
                                        )
                                    Recycler_answer.layoutManager = LinearLayoutManager(
                                        activity, LinearLayoutManager.VERTICAL, false
                                    )
                                    Recycler_answer.adapter = adapter
                                    val ques: String =
                                        it[i].getCaseStudyDetails()
                                            .get(0).getCase()
                                    llimage.visibility = View.GONE
                                    formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                    tvReadMore.setOnClickListener { v1: View? ->
                                        getCaseStudyReadMoreDialog(
                                            getLatexSolvedQ_Str(ques, true)
                                        )
                                    }
                                    if (it[i]
                                            .getSolutionDetails() == null
                                    ) {
                                        llsolution.visibility = View.GONE
                                    } else {
                                        llsolution.visibility = View.VISIBLE
                                    }
                                    if (questiontype.toLowerCase().trim { it <= ' ' }
                                            .contains("single")) {
                                        getFlippMarks("single")
                                    } else if (questiontype.toLowerCase().trim { it <= ' ' }
                                            .contains("multiple")) {
                                        getFlippMarks("multiple")
                                    }
                                } else if (it[i].type.equals("Assertion Reasoning")
                                ) {
                                    llnumeric.visibility = View.GONE
                                    llQues1.visibility = View.GONE
                                    tvReadMore.visibility = View.GONE
                                    recycler_Title.setVisibility(View.GONE)
                                    llQues.visibility = View.GONE
                                    tv_sereas_res.setText("Assertion")
                                    tvSereasResAsrtion.setText("Reasoning")
                                    tvMcqType.setText("Assertion Reasoning")
                                    //   tv_quetype_s.setText("Assertion Reasoning");
                                    Recycler_answer.visibility = View.VISIBLE
                                    relMain_asrtion.visibility = View.VISIBLE
                                    relCaseStdy.visibility = View.VISIBLE

                                    caseStudyAnsSelectList =
                                        java.util.ArrayList<CaseStudyAnsSelected>()
                                    caseStudyAnsSelectList.clear()
                                    val oi = 0
                                    for (k in 0 until it[i].answer_details.size) {
                                        if (it[i].answer_details[k].types.equals("Right")
                                        ) {
                                            totalRightAns++
                                        }
                                    }
                                    var questiontype = "single"
                                    if (oi > 1) {
                                        questiontype = "multiple"
                                    }
                                    getSetDefineType(
                                        it[i].define_type,
                                        totalRightAns
                                    )
                                    val adapter: queOptionsAdapter =
                                        queOptionsAdapter(it[i].answer_details, "question", it[i].define_type, totalRightAns)
                                    Recycler_answer.layoutManager = LinearLayoutManager(
                                        activity, LinearLayoutManager.VERTICAL, false
                                    )
                                    Recycler_answer.adapter = adapter
                                    val ques: String = it[i].assertionReasoningDetail.get(0).assertion
                                    formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                    val quesReason: String = it[i]
                                        .getAssertionReasoningDetails().get(0).getReasoning()
                                    formulaOne1ResAsrtion.setDisplayText(
                                        getLatexSolvedQ_Str(
                                            quesReason,
                                            true
                                        )
                                    )
                                    llimage.visibility = View.GONE //
                                    if (it[i].solution_details == null
                                    ) 
                                        llsolution.visibility = View.GONE
                                    } else {
                                        llsolution.visibility = View.VISIBLE
                                    }
                                    if (questiontype.lowercase().trim().contains("single")) {
                                        getFlippMarks("single",it[i])
                                    } else if (questiontype.lowercase().trim().contains("multiple")) {
                                        getFlippMarks("multiple",it[i])
                                    }
                                } else {
                                    count++
                                    selectedanswer = java.util.ArrayList()
                                    editedValList = java.util.ArrayList<NumericalSpinerVal_ID>()
                                    selectedSpinerList =
                                        java.util.ArrayList<NumericalSpinerVal_ID>()
                                    editedValList.clear()
                                    selectedSpinerList.clear()
                                    viewapply.setBackgroundColor(resources.getColor(R.color.parrotgreen))
                                    tv_apply.setBackgroundResource(R.drawable.round_boundary_green_new)
                                    llsolnext.visibility = View.GONE
                                    rlLoader.visibility = View.VISIBLE
                                    

                                    n_apply_marks = questTypeMarks
                                    //  tolalResutSize = it[i].getNApplicationValDetails().size() + it[i].getNApplicationDetails().size();
                                    try {
                                        if (it[i]
                                                .getNApplicationValDetails() != null
                                        ) {
                                            tolalResutSize = it[i]
                                                .getNApplicationValDetails().size()
                                        }
                                        if (it[i]
                                                .getNApplicationDetails() != null
                                        ) {
                                            tolalResutSize = it[i]
                                                .getNApplicationDetails().size()
                                            for (kk in 0 until it[i]
                                                .getNApplicationDetails().size()) {
                                                editTagsList.add("")
                                                //System.out.println("setEquationtolalResutSize.." + EditTagsList.size());
                                            }
                                        }
                                        //tolalResutSize = it[i].getNApplicationValDetails().size()+it[i].getNApplicationDetails().size();
                                        // System.out.println("tolalResutSize.." + tolalResutSize + ".." + it[i].getNApplicationValDetails().size() + "...." + it[i].getNApplicationDetails().size());
                                    } catch (exc: java.lang.Exception) {
                                        exc.printStackTrace()
                                    }
                                    adapterA = applyAdapter(it[i], "question2")
                                    Recycler_answer.layoutManager = LinearLayoutManager(
                                        activity, LinearLayoutManager.VERTICAL, false
                                    )
                                    Recycler_answer.adapter = adapterA
                                    val ques: String =
                                        it[i].question!!
                                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true))
                                    rlLoader.setVisibility(View.GONE)
                                }
                            }
                        }
                    } catch (e: java.lang.Exception) {
                        Log.e("catch_exception 1554", "catch_exception:$e")
                        apiHit = 2
                        try {
                            isBackFromSession = false
                                requireActivity().onBackPressed()

                        } catch (exc: java.lang.Exception)
                        {
                            Log.e("catch_exception1588", "catch_exception:$exc")
                        }
                    }
            }
        }
    private fun getCaseStudyReadMoreDialog(latexSolvedQ_str: String) {
        try {
            val dialog = AlertDialog.Builder(requireContext()).create()
            val binding: LayoutAlertReadMoreRowBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.layout_alert_read_more_row, null, false
            )
            binding.tvQuetypeCase.setText("Case")
            binding.tvQuesDetailCase.setDisplayText(latexSolvedQ_str)
            binding.ivClose.setOnClickListener { v -> dialog.dismiss() }
            dialog.setView(binding.getRoot())
            // }
            dialog.show()
        } catch (exc: java.lang.Exception) {
            exc.printStackTrace()
            //   binding.progressbbar.setVisibility(View.GONE);
        }
    }

    public fun getLatexSolvedQ_Str(ques: String?, b: Boolean): String? {
        var ques = ques
        println("replaceddd0..$ques...rrr..")
        ques= Constants_All.getLatexSolvedQ_Str_Solution(ques!!)!!

        val textt = "<font color=#FFFFF>$ques</font> "
        formula_one.setTextSize(latetex_dim_15)
        tv_QuesDetail_sM.setTextSize(latetex_dim_15)
        tv_solutions1M.setTextSize(latetex_dim_15)
        if (b) {
            tv_QuesDetail_sM.setDisplayText(textt)
            try {
                if (pojMcqQues!!.details[i].solution_details != null)
                    tv_solutions1M.setDisplayText(
                        Constants_All.getLatexSolvedQ_Str_Solution(pojMcqQues!!.details[i].solution_details.ans_desc)
                    )
            } catch (exc: java.lang.Exception) {
                exc.printStackTrace()
            }
            SolutionClickedLinstner()
        }
        // if(isRv_Visible)
        Recycler_answer.setVisibility(View.VISIBLE)
        //    getYoutTubePalyer();
        return ques
    }
    private fun getFlippMarks(qes_Type: String, detail: Detail) {

        for (m in  detail.flips_marks!!.indices) {
            //if(pojoQuestions.getFlipsMarks().get(m).getMarks().equals("qes_Type")){
            if (detail.type!!.lowercase().trim().contains(qes_Type)
            ) {
                questTypeMarks = detail.flips_marks!!.get(m).flip_mar
                var s = String.format("%.2f", detail.flips_marks!!.get(m).flip_mar.toFloat())
                Log.e("getFlippMarks: ", "$s...$ ")
                if (totalRightAns != 0) s = (s.toFloat() * totalRightAns).toString()

            }
        }
    }

    private fun SolutionClickedLinstner() {
        llsolution.setOnClickListener {
            isCountDownOnNext = false

            llsolution.visibility = View.VISIBLE
            llsolnext.visibility = View.VISIBLE
            llnext.visibility = View.VISIBLE

            if (tv_solutionbtn.text.toString().equals("Solution", ignoreCase = true)) {
                scrollBar_solution.visibility = View.VISIBLE
                scrollBar_.setVisibility(View.GONE)
                tv_solutionbtn.text = "Back"
                  youtTube_Fragment = YoutTube_Fragment()
                //getYoutTubePalyer();
                if (pojMcqQues!!.details[i].solution_details  != null) {
                    if (pojMcqQues!!.details[i].solution_details.ans_link != null) {
                        val videoId: String =
                            pojMcqQues!!.details[i].solution_details.ans_link
                                .substring(pojMcqQues!!.details[i].solution_details.ans_link
                                    .lastIndexOf("/") + 1)
                        try {
                            llsolution.visibility = View.VISIBLE
                            llsolnext.visibility = View.VISIBLE
                            llnext.visibility = View.VISIBLE

                            Log.e("onClick:solution ", "$videoId........" + pojMcqQues!!.details[i].solution_details.ans_link)
                            if (!videoId.isEmpty()) {
                                val ft = requireActivity().supportFragmentManager.beginTransaction()
                                val bundle = Bundle()
                                bundle.putString("video_Id", videoId)
                                youtTube_Fragment!!.setArguments(bundle)
                                ft.replace(R.id.youtube_fragment, youtTube_Fragment!!)
                                // ft.addToBackStack("youtTube_Fragment1");
                                ft.commit()
                            }
                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            } else {
                //getActivity().recreate();
                setScrollbarMcqVisibility()
            }
        }
    }
    fun setScrollbarMcqVisibility() {
        tv_solutionbtn.text = "Solution"
        scrollBar_solution.visibility = View.GONE
        scrollBar_.visibility = View.VISIBLE

       if (youtTube_Fragment != null) youtTube_Fragment!!.onDestroyView()
        if (youtTube_Fragment != null) {
            val ft = requireActivity().supportFragmentManager.beginTransaction()
            ft.remove(youtTube_Fragment!!).commitAllowingStateLoss()
        }

    }*//*

        override fun onDestroy() {
            super.onDestroy()
            if (mListEventListener != null) mListEventListener!!.remove()
        }

        override fun onDestroyView() {
            super.onDestroyView()
            if (mListEventListener != null) mListEventListener!!.remove()

        }

        private fun fetchUsers(liveCode: String) {
            mListEventListener =
                db!!.collection(getString(R.string.collectionName))
                    .addSnapshotListener(object : EventListener<QuerySnapshot> {
                        override fun onEvent(
                            snapshots: QuerySnapshot?,
                            e: FirebaseFirestoreException?
                        ) {
                            if (e != null) {
                                progress_bar.visibility = View.GONE
                                Log.w("TAG", "listen:error", e)
                                return
                            }
                            for (d in snapshots?.documentChanges!!) {
                                try {
                                    var roomsForDb = d.document.toObject(RoomsForDb::class.java)
                                    if (liveCode == roomsForDb.liveCode) {
                                        userList = roomsForDb.users
                                        getUserParticipateList()
                                    }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }

                            }
                        }
                    })
        }

        public class arrangeAdapter(
            pojo: List<ArrangeAnswerDetail?>?,
            type: String,
            questiontype: String,
            var expandedImage: ImageView,
            var shortAnimationDuration: Int
        ) :
            RecyclerView.Adapter<arrangeAdapter.ViewHolder>() {
            var pojo: List<ArrangeAnswerDetail?>
            var type: String
            var questiontype: String
            var marksRight = 0
            var checkArange = 0
            var context: Context? = null
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.res_choosans_arrange, parent, false)
                context = parent.context
                return ViewHolder(view)
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                if (type.equals("question", ignoreCase = true)) {
                    if (questiontype.equals("Image Arrange", ignoreCase = true)) {
                        holder.iv_image.visibility = View.VISIBLE
                        holder.cardViewIMage.visibility = View.VISIBLE
                        // holder.Tv_text.setVisibility(View.GONE);
                        holder.formula_one1.setVisibility(View.GONE)
                        Glide.with(context!!)
                            .load(pojo[position]!!.ansImg)
                            .into(holder.iv_image)
                        getLongPressItemImage(holder, pojo[position]!!.ansImg!!)
                    } else {
                        holder.iv_image.visibility = View.GONE
                        holder.cardViewIMage.visibility = View.GONE
                        // holder.Tv_text.setVisibility(View.VISIBLE);
                        holder.formula_one1.setVisibility(View.VISIBLE)
                        val ans: String = pojo[position]?.rightTxtAns!!
                        // holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                        holder.formula_one1.setTextSize(latetex_dim_13)
                        holder.formula_one1.setDisplayText(getLatexSolvedQ_Str_Solution(ans))
                    }
                    holder.tv_sereas.text = "" + (position + 65).toChar()
                } else {
                    if (questiontype.equals("Image Arrange", ignoreCase = true)) {
                        holder.iv_image.visibility = View.VISIBLE
                        holder.cardViewIMage.visibility = View.VISIBLE
                        holder.Tv_text.visibility = View.GONE
                        holder.formula_one1.setVisibility(View.GONE)
                        Glide.with(context!!)
                            .load(pojo[position]!!.ansImg)
                            .into(holder.iv_image)
                        getLongPressItemImage(holder, pojo[position]?.ansImg!!)
                    } else {
                        holder.cardViewIMage.visibility = View.GONE
                        holder.iv_image.visibility = View.GONE
                        //  holder.Tv_text.setVisibility(View.VISIBLE);
                        holder.formula_one1.setTextSize(latetex_dim_15)
                        holder.formula_one1.setVisibility(View.VISIBLE)
                        val ans: String = pojo[position]?.rightTxtAns!!
                        //holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                        holder.formula_one1.setDisplayText(getLatexSolvedQ_Str_Solution(ans))
                    }
                    holder.tv_sereas.text = (position + 65).toChar().toString()
                    ContextCompat.getColor(context!!, R.color.navicolor_2)
                    holder.Card.background =
                        ContextCompat.getDrawable(context!!, R.drawable.round_boundary_navi)
                    try {
                        holder.ic_cancel.visibility = View.VISIBLE
                        holder.ic_right.visibility = View.GONE
                        holder.ic_cancel.visibility = View.VISIBLE
                        holder.ic_right.visibility = View.GONE
                    } catch (e: java.lang.Exception) {
                    }

                }

            }

            override fun getItemCount(): Int {
                return pojo.size
            }

            private fun getLongPressItemImage(
                holder: arrangeAdapter.ViewHolder,
                imageString: String
            ) {
                holder.itemView.setOnLongClickListener({ v1: View? ->
                    expandedImage.setVisibility(View.VISIBLE)
                    Global.zoomRecyclerImage(
                        holder.iv_image,
                        imageString,
                        context,
                        expandedImage,
                        holder.relMain,
                        shortAnimationDuration
                    )
                    true
                })
            }

            inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                var cardoption: CardView
                var cardViewIMage: CardView
                var recycler_Text: RecyclerView
                var tv_sereas: TextView
                var Card: LinearLayout
                var Tv_text: TextView
                var ic_right: ImageView
                var ic_cancel: ImageView
                var ic_swap: ImageView
                var iv_image: ImageView
                var relMain: RelativeLayout
                var formula_one1: MathView

                init {
                    relMain = itemView.findViewById(R.id.relMain)
                    cardViewIMage = itemView.findViewById(R.id.cardViewIMage)
                    iv_image = itemView.findViewById(R.id.iv_image)
                    ic_swap = itemView.findViewById(R.id.ic_swap)
                    ic_right = itemView.findViewById(R.id.ic_right)
                    ic_cancel = itemView.findViewById(R.id.ic_cancel)
                    cardoption = itemView.findViewById(R.id.cardoption)
                    recycler_Text = itemView.findViewById(R.id.recycler_Text)
                    tv_sereas = itemView.findViewById(R.id.tv_sereas)
                    Card = itemView.findViewById(R.id.Card)
                    Tv_text = itemView.findViewById(R.id.Tv_text)
                    formula_one1 = itemView.findViewById(R.id.formula_one1)
                }
            }

            init {
                this.pojo = pojo!!
                this.type = type
                this.questiontype = questiontype

            }
        }


        //for view drag and drop

        public fun getLongPressItemImage_queOption(
            holder: QueOptionsAdapter.custom, imageString: String, expandedImage: ImageView
        ) {
            holder.itemView.setOnLongClickListener({ v1: View? ->
                expandedImage.setVisibility(View.VISIBLE)
                Global.zoomRecyclerImage(
                    holder.binding.ivImage, imageString, context,
                    expandedImage, holder.binding.relM, shortAnimationDuration
                )
                true
            })
        }

        private fun getUserParticipateList() {
            val mp = HashMap<String, String>()
            try {
                val userid: String = utills.convertStrigbuilderCaseStdy(userList!!, ",")!!
                mp.put("users", userid)
                viewModel.get_users_details(pref!!.getValueString("token"), mp)
                    .observe(viewLifecycleOwner, {
                        updateUI(it)
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        private fun getSet_FirebaseDatabaseEntery(index: Int) {
            val db: FirebaseFirestore = FirebaseFirestore.getInstance()
            val mp = HashMap<String?, Any?>()
            mp.put("currentQuestionIndex", index)
            db.collection(getString(R.string.collectionName)).document(liveCode_).update(mp)
        }

        public fun getSetDefineType(define_type: String, totalRightAns: Int) {
            // "Image MCQ", "Multiple Image MCQ", "Single Image MCQ", "Multiple Text MCQ",  "Single Text MCQ"
            var type_Define = define_type
            if (define_type.equals("Text MCQ", ignoreCase = true)) {
                type_Define = if (totalRightAns > 1) "Multiple Text MCQ" else "Single Text MCQ"
            } else if (define_type.equals("Single Text MCQ", ignoreCase = true)) {
                if (totalRightAns > 1) type_Define = "Multiple Text MCQ"
            } else if (define_type.equals("Image MCQ", ignoreCase = true)) {
                type_Define = if (totalRightAns > 1) "Multiple Image MCQ" else "Single Image MCQ"
            } else if (define_type.equals("Single Image MCQ", ignoreCase = true)) {
                if (totalRightAns > 1) type_Define = "Multiple Image MCQ"
            }
            //pojMcqQues!!.details[i].define_type(type_Define)

        }

        private fun getQues_Image() {
            try {
                expanded_image.visibility = View.GONE
                if (!pojMcqQues!!.details[i].image.equals("")) {
                    llimage.visibility = View.VISIBLE
                    Glide.with(requireActivity()).load(pojMcqQues!!.details[i].image)
                        .into(iv_quesimage)
                    expanded_image.visibility = View.GONE
                    iv_quesimage.setOnClickListener { v1: View? ->
                        expanded_image.visibility = View.VISIBLE
//                    Global.zoomTitleImage(iv_quesimage,
//                        pojMcqQues?.details!![i].image, activity, expanded_image, relMain!!, shortAnimationDuration
//                    )
                    }
                }
            } catch (exc: java.lang.Exception) {
                exc.printStackTrace()
            }
        }

        private fun SetFillUpNewSpan(detail: Detail) {
            val content3: String = detail.question!!.replace(".", " .")
            fbv_content.setVisibility(View.VISIBLE)
            var lines: MutableList<String> = java.util.ArrayList()
            val rangeList: MutableList<AnswerRange> = java.util.ArrayList<AnswerRange>()
            lines.clear()
            var words = arrayOf<String?>()
            words = content3.split(" ".toRegex()).toTypedArray()
            lines = Arrays.asList(*words)
            var count = 1
            var charCount = 0
            for (k in lines.indices) {
                if (lines[k].equals("##$count", ignoreCase = true)) {
                    val sss = lines[k].replace("##$count", "_____")
                    //System.out.print("targett.." + " ##" + count + "replacement+.. " + "-----" + sss);
                    lines[k] = sss
                    val chr = content3.trim { it <= ' ' }.indexOf("##$count")
                    rangeList.add(
                        AnswerRange(
                            chr + count - 1 + charCount,
                            chr + 4 + count + charCount
                        )
                    )
                    count++
                    charCount++
                }
            }
//        lines2 = lines
//        val ansfillup: String = convertStrigbuilder2(lines, " ")
            // System.out.println("spanArrayswords011.." + ansfillup);

//        fbv_content.setData(ansfillup, rangeList)
        }

    }*/
}