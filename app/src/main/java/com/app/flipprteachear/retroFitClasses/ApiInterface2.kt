package com.app.flipprteachear.retroFitClasses

import com.app.flipprteachear.LoginRegistration.pojo.PojoLogin
import com.app.flipprteachear.LoginRegistration.pojo.signUp.AddedClasse
import com.app.flipprteachear.home.pojo.*
import com.app.flipprteachear.home.pojo.liveModel.PojoTeacherCreateLive
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojMcqQues
import com.app.flipprteachear.home.pojo.studentDetail.PojoStudentProfile
import com.app.flipprteachear.home.pojo.topicDetail.*
import com.app.flipprteachear.home.pojo.userDetail.PojoUserDetail
import org.json.JSONObject
import retrofit2.http.*
import java.util.HashMap


interface ApiInterface2 {
    @FormUrlEncoded
    @POST("teacher_home_classes.php")
    suspend fun teacher_home_classes(
        @Header("Authorization") authtoken: String,
        @Field("user_id") userID: String
    ): PojoTeacherClass

    // @FormUrlEncoded
    @POST("teacher_topics_sessions.php")
    suspend fun getTopicDetail(
        @Header("Authorization") authtoken: String,
        @Body model: ModelTopicDetail
    ): PojoTopicDetail

    // @FormUrlEncoded
    @POST("teacher_update_course_structure.php")
    suspend fun getTopicCheckBox(
        @Header("Authorization") authtoken: String,
        @Body model: ModelTopicCheckBox
    ): PojoAllUpdate

    @POST("teacher_create_live_session.php")
    suspend fun teacher_create_live_session(
        @Header("Authorization") authtoken: String,
        @Body model: ModelTeacherCreateLiveSession
    ): PojoTeacherCreateLive

    @POST("teacher_live_questions.php")
    suspend fun getLiveQues1(
        @Header("Authorization") authtoken: String,
        @Body model: ModelLiveDetail
    ): PojMcqQues

    @POST("get_users_details.php")
    suspend fun get_users_details(
        @Header("Authorization") token: String,
        @Body model: HashMap<String, String>
    ): PojoUserDetail

    @POST("live_submit_question_users.php")
    suspend fun live_submit_question_users(
        @Header("Authorization") token: String,
        @Body model: HashMap<String, String>
    ): PojoUserDetail

    @POST("logout.php")
    suspend fun logoutApi(
        @Header("Authorization") token: String,
        @Body model: HashMap<String, String>
    ): PojoUserDetail


    @FormUrlEncoded
    @POST("student_profile_based_teacher.php")
    suspend fun studentProfileApi(
        @Header("Authorization") authtoken: String,
        @Field("student_id") student_id: String
    ): PojoStudentProfile

    @GET("check_student_code.php")
    suspend fun checkStudentCode(
        @Query("code") code: String,
        @Query("account_type") accountType: String
    ): SchoolCode

    @GET("get_avail_teacher_subject.php")
    suspend fun getSubject(
        @Header("Authorization") token: String,
        @Query("school_id") schoolId: String
    ): TeacherClassSubject


    @FormUrlEncoded
    @POST("add_class_sub.php")
    suspend fun addClassSubject(
        @Header("Authorization") authtoken: String,
        @Field("added_classes") student_id: ArrayList<AddedClasse>
    ): JSONObject

    @GET("teacher_classes_subjects.php")
    suspend fun getTeacherSubject(
        @Header("Authorization") authtoken: String
    ): TecherSubJect

//    @FormUrlEncoded
    @POST("teacher_class_sub_detail.php")
    suspend fun teacherClassSubDetail(
    @Header("Authorization") authtoken: String,
    @Body teacher_added_classes_sub_id: TeacherClassSubjectsId
    ): PojoTeacherClass
}