package com.app.flipprteachear.home.view.viewModel

import com.app.flipprteachear.LoginRegistration.pojo.signUp.AddedClasse
import com.app.flipprteachear.home.pojo.*
import com.app.flipprteachear.home.pojo.studentDetail.PojoStudentProfile
import com.app.flipprteachear.home.pojo.topicDetail.*
import com.app.flipprteachear.home.pojo.userDetail.PojoUserDetail
import com.app.flipprteachear.retroFitClasses.ApiInterface2
import org.json.JSONObject
import java.util.HashMap


class MainRepostitory(var api: ApiInterface2) {

    suspend fun school_code_student_Code(code: String): SchoolCode {
        return  api.checkStudentCode(code,"4")
    }
    suspend fun getSubject(token: String,schoolId:String): TeacherClassSubject {
        return api.getSubject(token,schoolId)
    }



    suspend fun teacher_home_classes(token: String, userID: String): PojoTeacherClass {
       return api.teacher_home_classes(token, userID)
    }

    suspend fun getTopicDetail(token: String, model: ModelTopicDetail): PojoTopicDetail {
        return api.getTopicDetail(token, model)
    }

    suspend fun getTopicCheckBox(token: String, model: ModelTopicCheckBox): PojoAllUpdate {
        return api.getTopicCheckBox(token, model)
    }

    suspend fun logoutApi(token: String, model: HashMap<String, String>): PojoUserDetail {
        return api.logoutApi(token, model)
    }

    suspend fun studentProfileApi(token: String, student_id: String): PojoStudentProfile {
        return api.studentProfileApi(token, student_id)
    }

    suspend fun addClassSubject(token: String, list: ArrayList<AddedClasse>): JSONObject {
        return api.addClassSubject(token,list)

    }

    suspend fun getTecherClassSubject(token: String): TecherSubJect {
        return api.getTeacherSubject(token)
    }

    suspend fun teacherClassSubDetail(token: String, teacherSubId: String): PojoTeacherClass {
        val teacherClassSubjectsId = TeacherClassSubjectsId(teacherSubId)
        return api.teacherClassSubDetail(token,teacherClassSubjectsId)
    }

  /*  suspend fun getLiveQues1(token: String, model: ModelLiveDetail): Pojo {
        return api.getLiveQues1(token, model)
    }*/


}