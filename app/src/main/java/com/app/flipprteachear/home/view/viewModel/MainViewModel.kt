package com.app.flipprteachear.home.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.flipprteachear.LoginRegistration.pojo.signUp.AddedClasse
import com.app.flipprteachear.home.pojo.PojoTeacherClass
import com.app.flipprteachear.home.pojo.SchoolCode
import com.app.flipprteachear.home.pojo.TeacherClassSubject
import com.app.flipprteachear.home.pojo.TecherSubJect
import com.app.flipprteachear.home.pojo.studentDetail.PojoStudentProfile
import com.app.flipprteachear.home.pojo.topicDetail.*
import com.app.flipprteachear.home.pojo.userDetail.PojoUserDetail
import com.app.flipprteachear.retroFitClasses.ApiInterface2
import com.app.flipprteachear.retroFitClasses.Retrofit
import com.hubwallet.utillss.ErrorResponse
import com.hubwallet.utillss.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.FileNotFoundException
import java.io.IOException
import java.util.HashMap

class MainViewModel : ViewModel() {

    lateinit var repostitory: MainRepostitory
    var api = Retrofit.getRetrofit().create(ApiInterface2::class.java)

    init {
        repostitory = MainRepostitory(api)
    }

    private val liveDataHomeClass = MutableLiveData<ResultWrapper<PojoTeacherClass>>()
    fun teacher_home_classes(
        token: String,
        userID: String
    ): MutableLiveData<ResultWrapper<PojoTeacherClass>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveDataHomeClass.postValue(ResultWrapper.Loading)
                val response = repostitory.teacher_home_classes(token, userID)
                liveDataHomeClass.postValue(ResultWrapper.Success(response))

            } catch (e: Exception) {
                e.printStackTrace()

                if (e is IOException) liveDataHomeClass.postValue(ResultWrapper.NetworkError) else liveDataHomeClass.postValue(
                    ResultWrapper.GenericError(
                        ErrorResponse(-1, e.message)
                    )
                )
                if (e is FileNotFoundException) liveDataHomeClass.postValue(ResultWrapper.NetworkError)

            }
        }
        return liveDataHomeClass
    }

    private val liveDataTopicDetail = MutableLiveData<ResultWrapper<PojoTopicDetail>>()
    fun getTopicDetail(
        token: String,
        model: ModelTopicDetail
    ): MutableLiveData<ResultWrapper<PojoTopicDetail>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveDataTopicDetail.postValue(ResultWrapper.Loading)
                val response = repostitory.getTopicDetail(token, model)
                liveDataTopicDetail.postValue(ResultWrapper.Success(response))

            } catch (e: Exception) {
                e.printStackTrace()

                if (e is IOException) liveDataTopicDetail.postValue(ResultWrapper.NetworkError) else liveDataTopicDetail.postValue(
                    ResultWrapper.GenericError(
                        ErrorResponse(-1, e.message)
                    )
                )
                if (e is FileNotFoundException) liveDataTopicDetail.postValue(ResultWrapper.NetworkError)

            }
        }


        return liveDataTopicDetail
    }


    private val liveDataCheckBox = MutableLiveData<ResultWrapper<PojoAllUpdate>>()
    fun getTopicCheckBox(
        token: String,
        model: ModelTopicCheckBox
    ): MutableLiveData<ResultWrapper<PojoAllUpdate>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveDataCheckBox.postValue(ResultWrapper.Loading)
                val response = repostitory.getTopicCheckBox(token, model)
                liveDataCheckBox.postValue(ResultWrapper.Success(response))

            } catch (e: Exception) {
                e.printStackTrace()

                if (e is IOException) liveDataCheckBox.postValue(ResultWrapper.NetworkError) else liveDataCheckBox.postValue(
                    ResultWrapper.GenericError(
                        ErrorResponse(-1, e.message)
                    )
                )
                if (e is FileNotFoundException) liveDataCheckBox.postValue(ResultWrapper.NetworkError)

            }
        }


        return liveDataCheckBox
    }

    private val liveDataLogout = MutableLiveData<ResultWrapper<PojoUserDetail>>()
    fun logoutApi(
        token: String,
        userID: HashMap<String, String>
    ): MutableLiveData<ResultWrapper<PojoUserDetail>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveDataLogout.postValue(ResultWrapper.Loading)
                val response = repostitory.logoutApi(token, userID)
                liveDataLogout.postValue(ResultWrapper.Success(response))

            } catch (e: Exception) {
                e.printStackTrace()

                if (e is IOException) liveDataLogout.postValue(ResultWrapper.NetworkError) else liveDataLogout.postValue(
                    ResultWrapper.GenericError(
                        ErrorResponse(-1, e.message)
                    )
                )
                if (e is FileNotFoundException) liveDataLogout.postValue(ResultWrapper.NetworkError)

            }
        }
        return liveDataLogout
    }

    private val liveStudentProfile = MutableLiveData<ResultWrapper<PojoStudentProfile>>()
    fun studentProfileApi(
        token: String,
        userID: String
    ): MutableLiveData<ResultWrapper<PojoStudentProfile>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveStudentProfile.postValue(ResultWrapper.Loading)
                val response = repostitory.studentProfileApi(token, userID)
                liveStudentProfile.postValue(ResultWrapper.Success(response))

            } catch (e: Exception) {
                e.printStackTrace()

                if (e is IOException) liveStudentProfile.postValue(ResultWrapper.NetworkError) else liveStudentProfile.postValue(
                    ResultWrapper.GenericError(
                        ErrorResponse(-1, e.message)
                    )
                )
                if (e is FileNotFoundException) liveStudentProfile.postValue(ResultWrapper.NetworkError)

            }
        }
        return liveStudentProfile
    }

    private val liveSchoolCode = MutableLiveData<ResultWrapper<SchoolCode>>()
    fun schoolCode(code: String): MutableLiveData<ResultWrapper<SchoolCode>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveSchoolCode.postValue(ResultWrapper.Loading)
                val response = repostitory.school_code_student_Code(code)
                liveSchoolCode.postValue(ResultWrapper.Success(response))

            } catch (e: Exception) {
                e.printStackTrace()

                if (e is IOException) liveSchoolCode.postValue(ResultWrapper.NetworkError) else liveSchoolCode.postValue(
                    ResultWrapper.GenericError(
                        ErrorResponse(-1, e.message)
                    )
                )
                if (e is FileNotFoundException) liveSchoolCode.postValue(ResultWrapper.NetworkError)

            }
        }
        return liveSchoolCode
    }

    private val liveClassSubjectTeacher = MutableLiveData<ResultWrapper<TeacherClassSubject>>()
    fun getSubjectClass(
        token: String,
        schoolid: String
    ): MutableLiveData<ResultWrapper<TeacherClassSubject>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveClassSubjectTeacher.postValue(ResultWrapper.Loading)
                val response = repostitory.getSubject(token, "1")
                liveClassSubjectTeacher.postValue(ResultWrapper.Success(response))

            } catch (e: Exception) {
                e.printStackTrace()

                if (e is IOException) liveClassSubjectTeacher.postValue(ResultWrapper.NetworkError) else liveClassSubjectTeacher.postValue(
                    ResultWrapper.GenericError(
                        ErrorResponse(-1, e.message)
                    )
                )
                if (e is FileNotFoundException) liveClassSubjectTeacher.postValue(ResultWrapper.NetworkError)

            }
        }
        return liveClassSubjectTeacher
    }

    private val liveClassAddSubject = MutableLiveData<ResultWrapper<JSONObject>>()
    fun addSubjectApi(
        token: String,
        list: ArrayList<AddedClasse>
    ): MutableLiveData<ResultWrapper<JSONObject>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveClassAddSubject.postValue(ResultWrapper.Loading)
                val response = repostitory.addClassSubject(token, list)
                liveClassAddSubject.postValue(ResultWrapper.Success(response))

            } catch (e: Exception) {
                e.printStackTrace()

                if (e is IOException) liveClassAddSubject.postValue(ResultWrapper.NetworkError) else liveClassAddSubject.postValue(
                    ResultWrapper.GenericError(
                        ErrorResponse(-1, e.message)
                    )
                )
                if (e is FileNotFoundException) liveClassAddSubject.postValue(ResultWrapper.NetworkError)

            }
        }
        return liveClassAddSubject
    }

    private val liveTeacherClassAddSubject = MutableLiveData<ResultWrapper<TecherSubJect>>()
    fun addTeacherClassApi(token: String): MutableLiveData<ResultWrapper<TecherSubJect>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveTeacherClassAddSubject.postValue(ResultWrapper.Loading)
                val response = repostitory.getTecherClassSubject(token)
                liveTeacherClassAddSubject.postValue(ResultWrapper.Success(response))

            } catch (e: Exception) {
                e.printStackTrace()

                if (e is IOException) liveTeacherClassAddSubject.postValue(ResultWrapper.NetworkError) else liveTeacherClassAddSubject.postValue(
                    ResultWrapper.GenericError(
                        ErrorResponse(-1, e.message)
                    )
                )
                if (e is FileNotFoundException) liveTeacherClassAddSubject.postValue(ResultWrapper.NetworkError)

            }
        }
        return liveTeacherClassAddSubject
    }

    private val liveTeacherClassSubjectId = MutableLiveData<ResultWrapper<PojoTeacherClass>>()
    val liveTeacherClassSB: LiveData<ResultWrapper<PojoTeacherClass>>
        get() = liveTeacherClassSubjectId

    fun addTeacherClassSubjectIdApi(
        token: String,
        teacherSubId: String
    ): MutableLiveData<ResultWrapper<PojoTeacherClass>>? {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveTeacherClassSubjectId.postValue(ResultWrapper.Loading)
                val response = repostitory.teacherClassSubDetail(token, teacherSubId)
                liveTeacherClassSubjectId.postValue(ResultWrapper.Success(response))

            } catch (e: Exception) {
                e.printStackTrace()

                if (e is IOException) liveTeacherClassSubjectId.postValue(ResultWrapper.NetworkError) else liveTeacherClassSubjectId.postValue(
                    (ResultWrapper.GenericError(
                        ErrorResponse(-1, e.message)
                    )
                            ))

                if (e is FileNotFoundException) liveTeacherClassSubjectId.postValue(

                    ResultWrapper.NetworkError

                )

            }
        }
        return liveTeacherClassSubjectId
    }


    /* private val liveDataLiveQues= MutableLiveData<ResultWrapper<PojoTopicDetail>>()
     fun getLiveQues(token: String, model: ModelLiveDetail): MutableLiveData<ResultWrapper<PojoTopicDetail>> {
         viewModelScope.launch(Dispatchers.IO){
             try {
                 liveDataLiveQues.postValue(ResultWrapper.Loading)
                 val response = repostitory.getLiveQues1(token, model)
                 liveDataLiveQues.postValue(ResultWrapper.Success(response))

             }catch (e : Exception){
                 e.printStackTrace()

                 if(e is IOException)  liveDataLiveQues.postValue(ResultWrapper.NetworkError)  else liveDataLiveQues.postValue(
                     ResultWrapper.GenericError(
                         ErrorResponse(-1, e.message)
                     ))
                 if(e is FileNotFoundException) liveDataLiveQues.postValue(ResultWrapper.NetworkError)

             }
         }
         return liveDataLiveQues
     }
 */
}