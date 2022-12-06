package com.app.flipprteachear.retroFitClasses;



import com.app.flipperparent.dashboard.ui.LoginRegistration.pojo.PojoForgotPasswrod;

import com.app.flipprteachear.LoginRegistration.pojo.GenrateParentCodePojo;
import com.app.flipprteachear.LoginRegistration.pojo.PojoAccountLinked.PojoAccountLinked;
import com.app.flipprteachear.LoginRegistration.pojo.PojoLogin;
import com.app.flipprteachear.LoginRegistration.pojo.PojoSignUp;
import com.app.flipprteachear.LoginRegistration.pojo.PojoULoginList;
import com.app.flipprteachear.LoginRegistration.pojo.PojoVerifyMobileNo;
import com.app.flipprteachear.LoginRegistration.pojo.PojoVerifyOtpNo;
import com.app.flipprteachear.LoginRegistration.pojo.generateCode.PojoGenrateCode;
import com.app.flipprteachear.LoginRegistration.pojo.pojoStudentCode.PojoClass_Subject;
import com.app.flipprteachear.LoginRegistration.pojo.pojoStudentCode.PojoStudentCode;
import com.app.flipprteachear.LoginRegistration.pojo.signUp.ModelSignUpBody;
import com.app.flipprteachear.home.pojo.PojoTeacherClass;
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojMcqQues;
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojoQuesQuries.PojoQuesQuries;
import com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted.PojoSubmittedMarks;
import com.app.flipprteachear.home.pojo.topicDetail.ModelLiveDetail;
import com.app.flipprteachear.home.pojo.userDetail.PojoUserDetail;
import com.app.flipprteachear.home.view.adapterMcq.PojoQuestions;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

//    @Header("Authorization")String authtoken,
    @FormUrlEncoded()
    @POST("verify_mobile.php")
    Call<PojoVerifyMobileNo> verify_mobile(@Field("mobile") String mobile, @Field("country_code") String county_code);
    @FormUrlEncoded()
    @POST("verify_otp.php")
    Call<PojoVerifyOtpNo> verify_otp(@Field("otp") String otp, @Field("mobile") String mobile,
                                     @Field("country_code") String county_code);
    @FormUrlEncoded()
    @POST("user_login.php")
    Call<PojoLogin> userlogin(@Field("mobile") String mobile, @Field("device_token") String device_token);

    @FormUrlEncoded()
    @POST("u_login.php")
    Call<PojoULoginList> user_u_login(@Field("username") String username,
                                      @Field("password") String password,
                                      @Field("user_device_token") String device_token,
                                      @Field("mobile") String mobile,
                                      @Field("account_type") String account_type,
                                      @Field("device_type") String device_type);

    @FormUrlEncoded()
    @POST("get_multiple_accounts.php")
    Call<PojoAccountLinked> user_account_linked(@Field("mobile") String mobile,
                                                @Field("account_type") String account_type);

    @FormUrlEncoded()
    @POST("verify_username.php")
    Call<GenrateParentCodePojo> verify_username(@Field("username") String username);

    ///signup_teacher.php
    @POST("signup_teacher.php") //@GET("student_profile.php")
    Call<PojoSignUp>register_SignUp(@Header("Authorization")String authtoken, @Body ModelSignUpBody body);

    @FormUrlEncoded()
    @POST("school_classes_based_class_id.php")
    Call<PojoClass_Subject> school_classes_SubjectApi(@Header("Authorization")String authtoken,
                                                      @Field("school_id") String school_id);
    @FormUrlEncoded()
    @POST("check_student_code.php")
    Call<PojoStudentCode> checkStudentCode(@Header("Authorization")String authtoken,
                                           @Field("code") String code,
                                           @Field("account_type") String account_type);

    @POST("get_users_details.php")
    Call<PojoUserDetail> get_users_details(@Header("Authorization")String token, @Body HashMap<String, String> model);

      /*
  @FormUrlEncoded()
    @POST("check_student_code.php")
    Call<PojoGetClassChild> check_student_code(@Header("Authorization")String authtoken, @Field("code") String code, @Field("user_id") String user_id);


     @FormUrlEncoded()
    @POST("join_child.php")
    Call<PojoGetClassChild> join_child(@Header("Authorization")String authtoken, @Field("student_id") String code, @Field("user_id") String user_id);
*/
    //===================

    @FormUrlEncoded()
    @POST("forgot_password.php")
    Call<PojoForgotPasswrod> forgot_passwrod(@Field("user_id") String user_id, @Field("password") String password);

   // @FormUrlEncoded()
    @POST("update_stu_image.php")
    Call<PojoSignUp> register_ImageUser(@Header("Authorization")String authtoken, @Body RequestBody body);

    @GET("get_generated_code.php")
    Call<PojoGenrateCode> getParentCode();

    @FormUrlEncoded
    @POST("teacher_home_classes.php")
    Call<PojoTeacherClass> teacher_home_classes(@Header("Authorization") String authtoken, @Field("user_id") String user_id );

    @POST("teacher_live_questions.php")
    Call<PojMcqQues> getLiveQues1(@Header("Authorization")String authtoken,
                                  @Body ModelLiveDetail model);
    @POST("live_submit_question_users.php")
    Call<PojoSubmittedMarks> get_LiveSubmittedDetails(@Header("Authorization")String authtoken,
                                                      @Body HashMap model);

    @GET("send_ques_report.php")
    Call<PojoQuesQuries> sendQuesReport(@Header("Authorization")String authtoken, @Query("query_type") String query_type,
                                        @Query("query_msg") String query_msg,
                                        @Query("user_id") String user_id,
                                        @Query("question_id") String question_id);
    @GET("get_ques_queries.php")
    Call<PojoQuesQuries> ques_quries(@Header("Authorization")String authtoken);

    @GET("logout.php")//?topic_id=1&subject_id=2
    Call<PojoUserDetail>get_logout(@Header("Authorization")String authtoken,@QueryMap HashMap<String, String> hashMap);

    //suspend fun teacher_home_classes(@Header("Authorization") authtoken: String, @Field("user_id") userID: String): PojoTeacherClass


//    @FormUrlEncoded()
//    @POST("teacher_home_classes.php")
//     PojoLogin teacher_home_classes(@Header("Authorization")String authtoken,@Field("user_id") String userID);

//
//    @FormUrlEncoded()
//    @POST("get_urgent_chapter.php")
//    Call<PojoAllChapterList> get_urgent_chapter(@Header("Authorization")String authorization, @Field("class_id") String class_id, @Field("student_id") String student_id);

  /*



    @FormUrlEncoded()
    @POST("forgot_password.php")
    Call<PojoAccountLinked> forgot_password(@Field("user_id") String user_id,
                                        @Field("password") String password);


    @FormUrlEncoded()
    @POST("verify_user.php")
    Call<PojoVerify> verify_user(@Field("username") String username,
                                 @Field("mobile") String mobile,
                                 @Field("dob") String dob);



    @FormUrlEncoded()
    @POST("gen_parent_code.php")
    Call<GenrateParentCodePojo> gen_parent_code(@Field("phone") String mobile);



    @FormUrlEncoded()
    @POST("verify_class_code.php")
    Call<PojoVerifyClassList> verify_class_code(@Field("class_code") String class_code);

    //@FormUrlEncoded()
    @POST("get_all_classes.php")
    Call<PojoGetClassCode> getClass_code( );

    @FormUrlEncoded()
    @POST("user_registration.php")
    Call<PojoLogin> user_registration(@Header("Authorization")String authtoken,@Field("first_name") String first_name,@Field("last_name") String last_name,
                                      @Field("image") String image, @Field("grade") String grade,
                                      @Field("board") String board,@Field("mobile") String mobile,
                                      @Field("father_name") String father_name,@Field("mother_name") String mother_name,
                                      @Field("father_mobile") String father_mobile,@Field("mother_mobile") String mother_mobile,
                                      @Field("father_email") String father_email,@Field("mother_email") String mother_email,@Field("device_token") String device_token,
                                      @Field("registration_date") String registration_date);

    @POST("listing_mcq.php")
    Call<PojoQuestions>listing_mcq(@Header("Authorization")String authtoken);

   // @GET("subject_session_listnew.php")
    @GET("home_clusters_subjects.php")
    Call<SubjectClusterDetailsPojo>allChaptersList(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id);


     @GET("home_chapters.php")
    Call<HomeChaptersDetailsPojo>getHomeChapters(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id,
                                                 @Query("subject_id")String subject_id,  @Query("cluster_id")String cluster_id);


 @GET("home_shortcut_sessions.php")
    Call<HomeShortCutPojo>homeShortcut_sessions(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id,
                                                @Query("subject_id") String subject_id, @Query("cluster_id") String cluster_id,
                                                @Query("course_id")String course_id, @Query("chapter_id") String chapter_id,
                                                @Query("session_type") String session_type);

    @GET("home_topics.php")
    Call<ChapterTopicDetailPojo>getHomeTopics(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id,
                                              @Query("subject_id") String subject_id, @Query("cluster_id") String cluster_id,
                                              @Query("chapter_id") String chapterId);


    @GET("get_urgent_chapter.php")
    Call<PojoAllChapterList>get_urgent_chapter(@Header("Authorization")String authtoken,@Query("class_id") String classID,@Query("student_id") String student_id);



    @GET("get_stu_profile.php") //@GET("student_profile.php")
    Call<PojoProfileModel>get_student_profile(@Header("Authorization")String authtoken,@Query("student_id") String student_id);
    @GET("student_profile.php") //@GET("student_profile.php")
    Call<PojoProfileModel>student_profile(@Header("Authorization")String authtoken,@Query("student_id") String student_id);

    @GET("get_class_profile.php") //@GET("student_profile.php")
    Call<PojoClass>get_class(@Header("Authorization")String authtoken,@Query("class_id") String classID,@Query("student_id") String student_id);

    @GET("get_another_student_profile.php") //@GET("student_profile.php")
    Call<PojoAnotherStudentProfile>get_another_student_profile(@Header("Authorization")String authtoken,@Query("student_id") String student_id,
                                                               @Query("a_student_id") String a_student_id);

    @GET("follow_user.php") //@GET("student_profile.php")
    Call<PojoFollowStatus>get_follow_status(@Header("Authorization")String authtoken,@Query("student_id") String student_id,
                                            @Query("a_student_id") String a_student_id,
                                            @Query("follow_status") String follow_status);



    @POST("edit_stu_profile.php") //@GET("student_profile.php")
    Call<ProfilePojo>edit_stu_profile(@Header("Authorization")String authtoken,@Body RequestBody body);

    @GET("get_pending_session.php")
    Call<GetPendingSessionPojo>get_pending_session(@Header("Authorization")String authtoken, @Query("student_id") String student_id);

    @GET("update_session.php")
    Call<GetPendingSessionPojo>get_update_session(@Header("Authorization")String authtoken,@QueryMap HashMap<String, String> hashMap);

    @GET("listing_session_level.php")
    Call<SessionLevelsPojo>listing_session_level(@Header("Authorization")String authtoken);

    @GET("listing_subject_classidbased.php")
    Call<SujectClassBasedPojo>listing_subject_classidbased(@Header("Authorization")String authtoken,@Query("class_id") String class_id);

    @GET("listing_duration.php")
    Call<ListingDurationPojo>listing_duration();

    @GET("start_jump_start_session.php")
    Call<PojoAllChapterList>start_jump_start_session(@Header("Authorization")String authtoken,@Query("student_id") String student_id,
                                              @Query("class_id") String class_id,
                                              @Query("subject_id") String subject_id,
                                              @Query("level_id") String level_id,
                                              @Query("duration_id") String duration_id,
                                              @Query("session_type") String sesstionType);

    @GET("update_session.php")
    Call<UpdateSessionPojo>update_session(@Header("Authorization")String authtoken,@Query("student_session_activity_id") String session_id,
                                          @Query("break_time") String break_time,
                                          @Query("is_going_for_break") String is_going_for_break,
                                          @Query("break_start_time") String break_start_time,
                                          @Query("session_status") String session_status,
                                          @Query("session_type") String sessionType    );


    @GET("listing_flips_topicidnew.php")
    Call<PojoQuestions>listing_flips_topicid(@Header("Authorization")String authtoken,@Query("topic_id") String topic_id,
                                             @Query("subject_id") String subject_id,
                                             @Query("student_id") String student_id,
                                             @Query("chapter_id") String chapter_id,
                                             @Query("course_id") String course_id,
                                             @Query("cluster_id") String cluster_id);


    @GET("send_ques_report.php")
    Call<PojoQuesQuries> sendQuesReport(@Header("Authorization")String authtoken,@Query("query_type") String query_type,
                                             @Query("query_msg") String query_msg,
                                             @Query("user_id") String user_id,
                                             @Query("question_id") String question_id);

    @GET("update_after_ques_submit.php")
    Call<PojoQuestions> update_after_submit(@Header("Authorization")String authtoken,@Query("stu_submit_ques_id") String stu_submit_ques_id);

    @GET("get_ques_queries.php")
    Call<PojoQuesQuries> ques_quries(@Header("Authorization")String authtoken);


 @GET("stu_submit_question.php")//?topic_id=1&subject_id=2 // stu_submit_question
    Call<StuSubmitQuestionPojo>stu_submit_question(@Header("Authorization")String authtoken,@QueryMap HashMap<String, String> hashMap);

 @GET("get_more_flips.php")//?topic_id=1&subject_id=2
    Call<PojoQuestions>get_more_flips(@Header("Authorization")String authtoken,@QueryMap HashMap<String, String> hashMap);

 @GET("get_session_updated_time.php")//?topic_id=1&subject_id=2
    Call<StuSubmitQuestionPojo>get_session_updated_time(@Header("Authorization")String authtoken,@QueryMap HashMap<String, String> hashMap);

     @GET("logout.php")//?topic_id=1&subject_id=2
    Call<StuSubmitQuestionPojo>get_logout(@Header("Authorization")String authtoken,@QueryMap HashMap<String, String> hashMap);

     @GET("get_help_queries.php")//?topic_id=1&subject_id=2
     Call<PojoGetHelp>getGetHelpApiData(@Header("Authorization")String authtoken);

    //send help query
    //https://flippr.in/api/
    //query_type, query_msg, user_id
    @GET("add_help_query.php")
     Call<PojoGetHelp>getAdd_help_query(@Header("Authorization")String authtoken,@Query("user_id") String user_id,
                                        @Query("query_type") String query_type,
                                        @Query("query_msg") String query_msg);

    @GET("user_active_status.php")
    Call<PojoULoginList>user_active_status(@Header("Authorization")String authtoken,
                                           @Query("user_id") String user_id
                                           ,@Query("is_active") String is_active );


    @GET("get_study_slots.php")
     Call<PojoGetStudySlots>get_study_slots(@Header("Authorization")String authtoken,@Query("student_id") String student_id);

    @GET("add_slot.php")
    Call<ListingDurationPojo>getAddSlotData(@Header("Authorization")String authtoken,@Query("student_id") String student_id,
                                             @Query("start_time") String start_time,
                                             @Query("days") String days,
                                             @Query("duration") String duration,
                                             @Query("level") String level);

    // ["level": "2", "duration": "2", "add_study_slot_id": "1", "student_id": "4",
    // "start_time": "09:00:AM", "days": "Mon,Tue,Wed,Thu,Fri,Sat"]
    @GET("update_slot.php")
    Call<ListingDurationPojo>getUpdateSlotData(@Header("Authorization")String authtoken,@Query("student_id") String topic_id,
                                             @Query("start_time") String subject_id,
                                             @Query("days") String student_id,
                                             @Query("duration") String chapter_id,
                                             @Query("level") String course_id,
                                             @Query("add_study_slot_id") String add_study_slot_id  );


     ///================= boost api
     @GET("boost_subject_sessions.php")
     Call<BoostSubjectdetailPojo>boost_subject_sessions(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id);

    @GET("boost_recommended.php")
    Call<BoostRecomendedPojo>boost_recommended(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id);



    @GET("listing_boost_skills_session.php")
    Call<BoostDimensionsSessionPojo>listing_boost_skills_session(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id);



    @GET("boost_chapters_list.php")
    Call<BoostChapterDetailPojo>boost_chapters_list(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id,
                                                    @Query("subject_id")String subject_id, @Query("cluster_id")String cluster_id);



    @GET("boost_topics_list.php")
    Call<BoostTopicDetailPojo>boost_topics_list(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id,
                                                @Query("subject_id") String subject_id, @Query("cluster_id") String cluster_id,
                                                @Query("chapter_id") String chapterId);

    @GET("get_boost_flips.php")
    Call<PojoQuestions>get_boost_flips(@Header("Authorization")String authtoken,@Query("topic_id") String topic_id,
                                       @Query("subject_id") String subject_id,
                                       @Query("student_id") String student_id,
                                       @Query("chapter_id") String chapter_id,
                                       @Query("course_id") String course_id,
                                       @Query("cluster_id") String cluster_id,
                                       @Query("skill_id") String skill_id);//, String skill_id


    //=========== Revise Api

    @GET("revise_recommended.php")
    Call<BoostRecomendedPojo>revise_recommended(@Header("Authorization")String authtoken,
                                                @Query("class_id") String classID,
                                                @Query("student_id") String student_id);

    @GET("revise_subject_sessions_list.php")
    Call<BoostSubjectdetailPojo>revise_subject_sessions_list(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id);

    @GET("revise_skill_sessions.php")
    Call<BoostDimensionsSessionPojo>revise_skill_sessions(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id);

    @GET("revise_chapters_list.php")
    Call<BoostChapterDetailPojo>revise_chapters_list(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id,
                                                     @Query("subject_id")String subject_id, @Query("cluster_id")String cluster_id);

    @GET("revise_topics_list.php")
    Call<BoostTopicDetailPojo>revise_topics_list(@Header("Authorization")String authtoken,@Query("class_id") String classID, @Query("student_id") String student_id,
                                                 @Query("subject_id") String subject_id, @Query("cluster_id") String cluster_id,
                                                 @Query("chapter_id") String chapterId);
    @GET("revise_ques_list.php")
    Call<PojoQuestions>revise_ques_list(@Header("Authorization")String authtoken,@Query("topic_id") String topic_id,
                                        @Query("subject_id") String subject_id,
                                        @Query("student_id") String student_id,
                                        @Query("chapter_id") String chapter_id,
                                        @Query("course_id") String course_id,
                                        @Query("cluster_id") String cluster_id,
                                        @Query("skill_id") String skill_id);

    @POST("signup.php") //@GET("student_profile.php")
    Call<PojoSignUp>register_SignUp(@Header("Authorization")String authtoken, @Body RequestBody body);
*/
}
