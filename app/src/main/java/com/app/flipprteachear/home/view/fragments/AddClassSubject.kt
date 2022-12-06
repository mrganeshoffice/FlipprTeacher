package com.app.flipprteachear.home.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.flipprteachear.LoginRegistration.GradeSubjectModel
import com.app.flipprteachear.LoginRegistration.adapter.Grade_SubjectAdapter
import com.app.flipprteachear.LoginRegistration.pojo.signUp.AddedClasse
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.FragmentAddClassSubjectBinding
import com.app.flipprteachear.home.pojo.SchoolCode
import com.app.flipprteachear.home.pojo.Subjects
import com.app.flipprteachear.home.pojo.TeacherClassSubject
import com.app.flipprteachear.home.view.viewModel.MainViewModel
import com.app.flipprteachear.utillsa.PreferenceManager
import com.hubwallet.utillss.ResultWrapper
import org.json.JSONObject


class AddClassSubject : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var teacherClassSubject:TeacherClassSubject
    lateinit var binding:FragmentAddClassSubjectBinding
    lateinit var viewModel:MainViewModel
    private var pref: PreferenceManager?=null
    var schoolID=""
    var list = arrayListOf<AddedClasse>()
    private var gradeList = arrayListOf<GradeSubjectModel>()
    private val gradeSubjectAdapter by lazy{
        gradeList.clear()
        Grade_SubjectAdapter(gradeList)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = PreferenceManager(requireContext())
        teacherClassSubject=TeacherClassSubject()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()),R.layout.fragment_add_class_subject, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            saveSubjectsClasses.setOnClickListener{
                if(gradeList.isNullOrEmpty()){
                    Toast.makeText(requireContext(),"Please select grade and subject",Toast.LENGTH_SHORT).show()
                }
                else{
                    list.clear()
                    try {
                        for(i in gradeList.indices){
                            list.add(AddedClasse(
                                "${gradeList[i].classId}","${gradeList[i].course_id}",
                                "${gradeList[i].school_class_course_id}",
                                "${gradeList[i].school_id}", "${gradeList[i].subject_id}"
                            ))
                        }

                        viewModel.addSubjectApi(pref!!.getValueString("token"),list).observe(viewLifecycleOwner){
                            updateUI(it)
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            }
            rvGradeSubject.adapter = gradeSubjectAdapter
            otpView.setOtpCompletionListener { otp ->
                // accountPin = otp
                Log.e("rajat.....", "pictureFilePath: otp")
                if (otp.length > 4) checkStudentCode(otp)
            }

            ivAddSubject.setOnClickListener {
                var classId=""
                var courseId=""
                var schoolClassCourseId=""
                var schoolId=""
                var subjectId=""
                var gradName=""
                var subjectName=""
                var flag=false
                try {
                    teacherClassSubject!!.arryclasses[spinnerGrade.selectedItemPosition].let {
                        classId = it.classId.toString()
                        courseId = it.courseId.toString()
                        schoolClassCourseId=it.schoolClassCourseId.toString()
                        schoolId=schoolID
                        gradName=it.className.toString()
                        subjectName = it.subjects[spinnerSubject.selectedItemPosition].name.toString()
                        subjectId = it.subjects[spinnerSubject.selectedItemPosition].subjectId.toString()

                        if(gradeList.size > 0){
                            var flag = false
                            for (i in 0 until gradeList.size){
                                if((gradeList[i].grade.trim() == gradName.trim()) && (gradeList[i].subject.trim() == subjectName.trim())  ){
                                    flag = true
                                    break
                                }
                            }
                            if(!flag){
                                gradeList.add(
                                    GradeSubjectModel("$gradName","$subjectName","${it.classId}",
                                        "${it.courseId}","$subjectId","${schoolId}","${it.schoolClassCourseId}")
                                )
                                gradeSubjectAdapter.updateList(gradeList)
                            }else{
                                Toast.makeText(requireContext(),"Please select new grade and subject",Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            gradeList.add(
                                GradeSubjectModel("$gradName","$subjectName","${it.classId}",
                                    "${it.courseId}","$subjectId","${schoolId}","${it.schoolClassCourseId}")
                            )
                            gradeSubjectAdapter.updateList(gradeList)
                        }




                    }
                }catch (e:Exception){e.printStackTrace()}




            }



        }


    }



    private fun checkStudentCode(otp: String?) {
        if (otp != null) {
            viewModel.schoolCode(otp).observe(viewLifecycleOwner){


                updateUI(it)
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateUI(_state: ResultWrapper<Any>) {

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
                binding.progress.visibility = View.GONE
                when(_state.data){
                    is SchoolCode ->{
                        _state.data.let {

                            val detailSchool=it.details
                            binding.tvSchoolName.text=detailSchool?.firstName
                            detailSchool?.userId?.let { it1 ->
                                schoolID=it1
                                viewModel.getSubjectClass(pref!!.getValueString("token"),
                                    it1
                                ).observe(viewLifecycleOwner){
                                    updateUI(it)
                                }
                            }

                        }

                    }
                    is TeacherClassSubject->{
                        val clsses= arrayListOf<String>()
                        Log.e("success",clsses.toString())
                        val mapClassesSubject:HashMap<String,ArrayList<String>> = hashMapOf()
                        _state.data.let {
                            teacherClassSubject=it
                            with(binding){

                                spinnerGrade.adapter = spGradeAdapter(it)
                                spinnerGrade.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                        if(teacherClassSubject!=null) if(teacherClassSubject!!.arryclasses!=null)
                                            spinnerSubject.adapter = spSubjectAdapter(teacherClassSubject!!.arryclasses[position].subjects)
                                    }

                                    override fun onNothingSelected(parent: AdapterView<*>?) {

                                    }

                                }
                            }



                            /*  val listofClass=it.arryclasses
                              for (i in listofClass){
                                  i.className?.let { it1 -> clsses.add(it1) }

                              }
                              if(listofClass.isNotEmpty()){
                                 val singleitem =listofClass.get(0)
                                  val subjects=singleitem.subjects



                              }*/
                        }

                    }
                    is JSONObject->{
                        Log.e("success","upload")
                        Toast.makeText(requireContext(),"succesfullyUpload",Toast.LENGTH_SHORT).show()
                        gradeList.clear()
                        gradeSubjectAdapter.notifyDataSetChanged()

                    }
                }
            }
        }

    }
    private fun spGradeAdapter(pojoclassSubject: TeacherClassSubject?): ArrayAdapter<String> {
        val arrayList = arrayListOf<String>()
        for(i in pojoclassSubject!!.arryclasses.indices){
            pojoclassSubject.arryclasses[i].className?.let { arrayList.add(it) }
        }
        return ArrayAdapter(requireContext(),R.layout.simple_spinner_dropdown_item,arrayList)
    }
    private fun spSubjectAdapter(subjects: ArrayList<Subjects>):ArrayAdapter<String>{
        val arrayList = arrayListOf<String>()
        for(i in subjects.indices){
            subjects[i].name?.let { arrayList.add(it) }
        }
        return ArrayAdapter(requireContext(),R.layout.simple_spinner_dropdown_item,arrayList)
    }

    override fun onResume() {
        super.onResume()
    }

}