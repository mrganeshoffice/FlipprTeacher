package com.app.flipprteachear.home.view.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.RotateAnimation
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.FragmentHome1Binding
import com.app.flipprteachear.home.ForClassPageChange
import com.app.flipprteachear.home.ForStartLive
import com.app.flipprteachear.home.daofile.DetailDao
import com.app.flipprteachear.home.databasefile.DatabaseBuilder
import com.app.flipprteachear.home.pojo.Detail
import com.app.flipprteachear.home.view.adapter.ClassPageAdapter
import com.app.flipprteachear.home.view.adapter.chapterAdapter
import com.app.flipprteachear.home.view.adapter.classStudentAdapter
import com.app.flipprteachear.home.pojo.PojoTeacherClass
import com.app.flipprteachear.home.pojo.Student
import com.app.flipprteachear.home.pojo.TecherSubJect
import com.app.flipprteachear.home.view.viewModel.MainViewModel
import com.app.flipprteachear.retroFitClasses.apiCalls
import com.app.flipprteachear.utillsa.Constants_All
import com.app.flipprteachear.utillsa.PreferenceManager
import com.app.flipprteachear.utillsa.utills
import com.hubwallet.utillss.ResultWrapper
import kotlinx.android.synthetic.main.fragment_home1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeFragment : Fragment(), ForClassPageChange {
    lateinit var viewModel: MainViewModel
    lateinit var viewBinding: FragmentHome1Binding
    private var pojoTeacherClass: PojoTeacherClass? = null
    private var pref: PreferenceManager? = null
    private var startLive: ForStartLive? = null
    var possn = 0
    var poss = 0

    var teacherSubjectDetail: java.util.ArrayList<Detail>? = arrayListOf()

    var detailsList: MutableList<Detail>? = null

    var dBuilder: DetailDao? = null
    var layoutManager = null

    private val studentAdapter by lazy {
        classStudentAdapter(1, startLive!!)
    }


    private val chapterAdapterr by lazy {
        chapterAdapter(this, requireActivity(), pref)
    }

    //        private val classPageAdapter by lazy {
//            ClassPageAdapter(this)
//        }
    private val classPageAdapter by lazy {
        ClassPageAdapter(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        startLive = context as ForStartLive
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View {
        // Inflate the layout for this fragment
        viewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_home1,
            container,
            false
        )

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        pref = PreferenceManager(requireContext())
        viewBinding.rvStudents.setLayoutManager(GridLayoutManager(activity, 4))
        viewBinding.rvStudents.adapter = studentAdapter
        viewBinding.rvUchapters.adapter = chapterAdapterr
        //viewBinding.rvClassPagesDots.adapter =classPageAdapter
        viewBinding.rvClassPages.adapter = classPageAdapter
        viewBinding.progressBar.visibility = View.GONE
        onClickedListners()
        //  getHomeApi()

        dBuilder = DatabaseBuilder.getInstance(requireActivity()).DetailDao()

        viewBinding.ivRefresh.setOnClickListener {

            viewBinding.ivRefresh.clearAnimation()
            val anim = RotateAnimation(
                30f,
                360f,
                (viewBinding.ivRefresh.getWidth() / 2).toFloat(),
                (viewBinding.ivRefresh.getHeight() / 2).toFloat()
            )
            anim.fillAfter = true
            anim.repeatCount = 0
            anim.duration = 1000
            viewBinding.ivRefresh.startAnimation(anim)
            // getHomeApi()
        }
        // observeOfflineData()
        return viewBinding.root
    }

    private fun observeOfflineData() {
        /* dBuilder?.getAllData()?.observe(viewLifecycleOwner) {
             if (!it.isNullOrEmpty()) {
                 detailsList = it
                 viewBinding.progressBar.visibility = View.GONE
                 setTeacherClassData(it)
             }
         }*/
    }

    private fun getHomeApi() {
        viewModel.teacher_home_classes(
            pref!!.getValueString("token"),
            pref!!.getValueString("userId")
        ).observe(viewLifecycleOwner, {
            //  updateUI(it)
        })

        //teacher_home_classes()
    }

    fun teacher_home_classes() {
        viewBinding.progressBar.setVisibility(View.VISIBLE)
        val api = apiCalls()
        val callApi: Call<PojoTeacherClass> = api.teacher_home_classes(
            pref!!.getValueString("token"),
            pref!!.getValueString("userId")
        )
        callApi.enqueue(object : Callback<PojoTeacherClass> {
            override fun onResponse(
                call: Call<PojoTeacherClass>,
                response: Response<PojoTeacherClass>
            ) {
                viewBinding.progressBar.setVisibility(View.GONE)
                if (response.body()!!.message.equals("success")) {
                    pojoTeacherClass = response.body()!!
                    setTeacherClassData(response.body()?.details)

                }
            }

            override fun onFailure(call: Call<PojoTeacherClass>, t: Throwable) {
                viewBinding.progressBar.setVisibility(View.GONE)
            }
        })
    }

    private fun onClickedListners() {
        with(viewBinding) {
            tvPoints.setOnClickListener { updateBtns_onType(1, tvPoints) }
            tvHomeWork.setOnClickListener { updateBtns_onType(2, tvHomeWork) }
            tvSyllabus.setOnClickListener { updateBtns_onType(3, tvSyllabus) }
            tvConfidence.setOnClickListener { updateBtns_onType(4, tvConfidence) }
            tvSyllMaster.setOnClickListener { updateBtns_onType(5, tvSyllMaster) }
            llSeeAll.visibility = View.GONE
            llSeeAll.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("pojoTeacherClass", pojoTeacherClass)
                bundle.putInt("poss_", possn)
                getFragemtent(ChapterListFragment(), bundle, "chapterList")
            }
        }
    }

    private fun updateBtns_onType(type: Int, tv: TextView) {
        with(viewBinding) {
            tvPoints.background =
                ResourcesCompat.getDrawable(resources, R.drawable.round_boundary_navi_new, null)
            tvHomeWork.background =
                ResourcesCompat.getDrawable(resources, R.drawable.round_boundary_navi_new, null)
            tvSyllabus.background =
                ResourcesCompat.getDrawable(resources, R.drawable.round_boundary_navi_new, null)
            tvConfidence.background =
                ResourcesCompat.getDrawable(resources, R.drawable.round_boundary_navi_new, null)
            tvSyllMaster.background =
                ResourcesCompat.getDrawable(resources, R.drawable.round_boundary_navi_new, null)
            tv.background = ResourcesCompat.getDrawable(resources, R.drawable.round_skyblue, null)
        }

        try {
            pojoTeacherClass?.details!![possn].let {
                if (type == 1) {
                    Collections.sort(it.students, StuPointComparator)
                } else if (type == 2) {
                    Collections.sort(it.students, StuPointComparator2)
                } else if (type == 3) {
                    Collections.sort(it.students, StuPointComparator3)
                } else if (type == 4) {
                    Collections.sort(it.students, StuPointComparator4)
                } else if (type == 5) {
                    Collections.sort(it.students, StuPointComparator5)
                }
//                studentAdapter.updateListType(it.students!!)
            }
            studentAdapter.updateType(type)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        studentAdapter.updateType(type)
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
                    is PojoTeacherClass -> {
                        _state.data.let {
                            if (it.message.equals("success")) {
                                pojoTeacherClass = it
                                it.details?.let { it1 -> teacherSubjectDetail!!.addAll(it1) }
                                detailsList = teacherSubjectDetail
                                viewBinding.progressBar.visibility = View.GONE
                                setTeacherClassData(teacherSubjectDetail)
                                tv_points.performClick()
                                /* CoroutineScope(Dispatchers.IO).launch {
                                     dBuilder?.insertAll(it.details)
                                 }*/


                            }
                        }
                    }
                    /*is PojoLogin->{
                        _state.data.let {
                            if (it.status.equals(1)){
                                Toast.makeText(activity,it.message, Toast.LENGTH_SHORT).show()
                          }
                        }
                    }*/
                    is TecherSubJect -> {
                        if (_state.data.message.equals("success", true)) {
                            val listofclass = _state.data.details
                            // val listofclass = listofclasss.distinct()
                            viewBinding.chooseClass.adapter = teacherClassAdapter(_state.data)


                            viewBinding.chooseClass.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {

                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long
                                    ) {
                                        if (listofclass != null) {
                                            val pos = position
                                            var flag = false
                                            var itemPositioninList = 0

                                            if (listofclass.size > 0) {
                                                if (detailsList?.isNotEmpty() == true && detailsList != null) {
                                                    if (pos > 0)
                                                        for (i in 0 until detailsList!!.distinct().size) {
                                                            if (detailsList!![i].subject_id!!.toString()
                                                                    .equals(listofclass[pos - 1].subjectId.toString())
                                                            ) {
                                                                flag = true
                                                                itemPositioninList = i
                                                                break;

                                                            }

                                                        }

                                                }

                                                if (flag) {
                                                    if (detailsList != null) {
                                                        val item =
                                                            detailsList?.get(itemPositioninList)
                                                        detailsList!!.removeAt(itemPositioninList)
                                                        if (item != null) {
                                                            detailsList!!.add(0, item)
                                                        }
                                                        setTeacherClassData(detailsList)
                                                    }
                                                } else
                                                {
                                                    if (pos == 0) {

                                                        viewModel.addTeacherClassSubjectIdApi(
                                                            pref!!.getValueString(
                                                                "token"
                                                            ),
                                                            listofclass[position].teacherAddedClassesSubId.toString()
                                                        )


                                                    } else if (pos > 0) {
                                                        viewModel.addTeacherClassSubjectIdApi(
                                                            pref!!.getValueString(
                                                                "token"
                                                            ),
                                                            listofclass[position - 1].teacherAddedClassesSubId.toString()
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                        // spinnerSubject.adapter = spSubjectAdapter(teacherClassSubject!!.arryclasses[position].subjects)
                                    }
                                    override fun onNothingSelected(parent: AdapterView<*>?) {

                                    }

                                }
                        }

                    }
                }
                viewBinding.progressBar.visibility = View.GONE
            }
            else -> {}
        }
    }

    private fun setTeacherClassData(details: List<Detail>?) {

        with(viewBinding) {
            rvClassPages.adapter = classPageAdapter
            try {
                PagerSnapHelper().attachToRecyclerView(rvClassPages)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val layoutManager = rvClassPages.layoutManager as LinearLayoutManager

            details!!.distinct().let {
                classPageAdapter.updateList(it.distinct())
                it.firstOrNull()?.students?.let { studentList ->
                    studentAdapter.updateListType(studentList.distinct())
                }
                it.firstOrNull()?.chapters?.let { chapterList ->
                    chapterAdapterr.updateListType(chapterList.distinct())
                }
                /*if(chapter==null){
                    chapterAdapterr.updateListType(null)
                }*/

            }
            // viewBinding.rvUchapters.adapter =chapterAdapterr
            rvClassPages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    try {
                        poss = layoutManager.findFirstCompletelyVisibleItemPosition()
                        Log.e("TAG", "onScrolled: $poss")
                        possn = poss.plus(1)
                        tvTitleValue.text = "(" + possn + " of " + details!!.distinct().size + ")"
                        if (poss != -1)
                            details.distinct().let {
                                if (!it[poss].students.isNullOrEmpty())
                                    studentAdapter.updateListType(it[poss].students!!)
                                if (!it[poss].chapters.isNullOrEmpty())
                                    chapterAdapterr.updateListType(it[poss].chapters!!)
                                tvClass.text = getString(
                                    R.string.class_performance_in_physics,
                                    it[poss].subject_name
                                )
                            }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }

            })
        }
    }

    override fun changeClass(poss: Int) {
        poss + 1
        viewBinding.tvTitleValue.text =
            "(" + possn + " of " + pojoTeacherClass?.details!!.distinct().size + ")"
    }

    override fun getTopicPage(schoolCourseStructureId: String) {
        val bundle = Bundle()
        bundle.putString("schoolCourseStructureId", schoolCourseStructureId)
        Log.d("schoolCourseStructureId", schoolCourseStructureId)
        getFragemtent(TopicListFragment(), bundle, "topicList")
//        val ft = activity?.supportFragmentManager!!.beginTransaction()
//        utills.replacefrag_withBackStack(ft, TopicListFragment(), Bundle(), "topicList")
    }

    fun getFragemtent(fr: Fragment, bundle: Bundle, name: String) {
        val ft = activity?.supportFragmentManager!!.beginTransaction()
        utills.replacefrag_withBackStack(ft, fr, bundle, "topicList")
    }

    var StuPointComparator: Comparator<Student> =
        Comparator<Student> { s1, s2 ->

            val point1 = s1.total_points ?: "0"
            val point2 = s2.total_points ?: "0"
            val d1 = java.lang.Double.valueOf(point1)
            val d2 = java.lang.Double.valueOf(point2)

            return@Comparator java.lang.Double.compare(d2, d1)
        }

    var StuPointComparator3: Comparator<Student> =
        Comparator<Student> { s1, s2 ->
            val d1 = s1.syllabus_comp!!
            val d2 = s2.syllabus_comp!!
            return@Comparator d2.compareTo(d1)
        }
    var StuPointComparator2: Comparator<Student> =
        Comparator<Student> { s1, s2 ->
            val d1 = s1.homework_comp!!
            val d2 = s2.homework_comp!!
            return@Comparator d2.compareTo(d1)
        }

    var StuPointComparator4: Comparator<Student> =
        Comparator<Student> { s1, s2 ->
            val d1 = java.lang.Double.valueOf(s1.avg_confid!!)
            val d2 = java.lang.Double.valueOf(s2.avg_confid!!)

            return@Comparator java.lang.Double.compare(d2, d1)

        }

    var StuPointComparator5: Comparator<Student> =
        Comparator<Student> { s1, s2 ->
            val d1 = s1.syllabus_mastered!!
            val d2 = s2.syllabus_mastered!!

            return@Comparator d2.compareTo(d1)

        }

    // pojoTeacherClass = response.body()!!
//                    setTeacherClassData(response.body()!!)
    override fun onResume() {
        super.onResume()
        Log.e("TAG", "onResume: ")
    }


    override fun onStart() {
        super.onStart()
        Log.e("TAG", "onResume:onStart: ")
        // if(pojoTeacherClass!=null)
        /* pojoTeacherClass?.let {
             setTeacherClassData(pojoTeacherClass?.details)
         }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.addTeacherClassApi(pref!!.getValueString("token")).observe(viewLifecycleOwner) {
            updateUI(it)
        }
        viewModel.liveTeacherClassSB.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun teacherClassAdapter(pojoclassSubject: TecherSubJect): ArrayAdapter<String> {

        val arrayList = arrayListOf<String>()
        arrayList.add("Add Class")
        pojoclassSubject.details.distinct().forEach {
            arrayList.add("${it.className} (${it.subjectName})")
        }
        /* for (i in pojoclassSubject!!.details.indices) {
             pojoclassSubject.details[i].className?.let { arrayList.add(it) }
         }*/

        return object :
            ArrayAdapter<String>(requireContext(), R.layout.simple_spinner_dropdown_item, arrayList) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }
        }
    }

}