package com.app.flipprteachear.retroFitClasses;

import com.app.flipperparent.dashboard.ui.LoginRegistration.pojo.PojoForgotPasswrod;
import com.app.flipprteachear.LoginRegistration.pojo.GenrateParentCodePojo;
import com.app.flipprteachear.LoginRegistration.pojo.LoginModel;
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


import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class apiCalls {
     ApiInterface api = Retrofit.getRetrofit().create(ApiInterface.class);
    //login api
    public Call<PojoLogin> loginAPi(LoginModel model){
        Call<PojoLogin> callApi = api.userlogin(model.getMobile(),model.getDevice());
        return callApi;
    }
    public Call<PojoVerifyMobileNo> verify_mobile(LoginModel model){
        Call<PojoVerifyMobileNo> callApi = api.verify_mobile(model.getMobile(),model.getDevice());
        return callApi;
    }
    public Call<PojoVerifyOtpNo> verify_mobileOtp(String otp, String mobile, String s){
        Call<PojoVerifyOtpNo> callApi = api.verify_otp(otp,mobile, s);
        return callApi;
    }

    public Call<PojoULoginList> u_loginAPi(String username, String password, String user_u_login, String mobile, String account_type, String device_type){
        Call<PojoULoginList> callApi = api.user_u_login(username, password, user_u_login,mobile,account_type,device_type);
        return callApi;
    }
    public Call<PojoAccountLinked> user_account_linked(String mobile, String account_type){
        Call<PojoAccountLinked> callApi = api.user_account_linked(mobile, account_type);

        return callApi;
    }
    public Call<PojoForgotPasswrod> forgot_passwrod(String user_id, String password){
        Call<PojoForgotPasswrod> callApi = api.forgot_passwrod(user_id,password);
        return callApi;
    }
    public Call<PojoSignUp>register_SignUp(String authtoken, ModelSignUpBody requestBody){
        Call<PojoSignUp> callApi = api.register_SignUp(authtoken,requestBody);
        return callApi;
    }
    public Call<PojoSignUp>register_ImageUser(String authtoken, MultipartBody requestBody){
        Call<PojoSignUp> callApi = api.register_ImageUser(authtoken,requestBody);
        return callApi;
    }
    public Call<GenrateParentCodePojo> verify_username(String username){
        Call<GenrateParentCodePojo> callApi = api.verify_username(username);

        return callApi;
    }
    public Call<PojoClass_Subject> school_classes_SubjectApi(String token,String school_id){
        Call<PojoClass_Subject> callApi = api.school_classes_SubjectApi(token,school_id);

        return callApi;
    }

    public Call<PojoStudentCode> checkStudentCode(String token, String code, String accountType){
        Call<PojoStudentCode> callApi = api.checkStudentCode(token,code, accountType);

        return callApi;
    }

    public Call<PojoGenrateCode>getParentCode( ){
        Call<PojoGenrateCode> callApi = api.getParentCode( );
        return callApi;
    }
    public Call<PojoTeacherClass> teacher_home_classes(String token, String userId){
        Call<PojoTeacherClass> callApi = api.teacher_home_classes(token, userId);
        return callApi;
    }
    public Call<PojMcqQues> getLiveQues1(String auth, ModelLiveDetail model){
        Call<PojMcqQues> callApi = api.getLiveQues1(auth, model);
        return callApi;
    }

    public Call<PojoSubmittedMarks> get_LiveSubmittedDetails(String auth , HashMap model){
        Call<PojoSubmittedMarks> callApi = api.get_LiveSubmittedDetails(auth, model);
        return callApi;
    }

    public Call<PojoQuesQuries> sendQuesReport(String authtoken, String query_type, String query_msg, String user_id, String question_id){
        Call<PojoQuesQuries> callApi = api.sendQuesReport(authtoken,query_type,query_msg,user_id,question_id);
        return callApi;
    }
    public Call<PojoQuesQuries>ques_quries(String authtoken){
        Call<PojoQuesQuries> callApi = api.ques_quries(authtoken);
        return callApi;
    }

    public Call<PojoUserDetail>get_logout(String authtoken,HashMap<String, String> hashMap){
        Call<PojoUserDetail> callApi = api.get_logout(authtoken,hashMap);
        return callApi;
    }
    public Call<PojoUserDetail>get_user_List(String authtoken,HashMap<String, String> hashMap){
        Call<PojoUserDetail> callApi = api.get_users_details(authtoken,hashMap);
        return callApi;
    }
   /*
 public Call<PojoVerify> verify_user(String username, String mobile, String dob){
        Call<PojoVerify> callApi = api.verify_user(username,mobile,dob);

        return callApi;
    }
    public Call<PojoGetClassCode> get_parent_childs(String token , String code, String student_id  ){
        Call<PojoGetClassCode> callApi = api.get_parent_childs(token,code, student_id );
        return callApi;
    }
    public Call<PojoGetClassChild> check_student_code(String token , String code, String student_id  ){
        Call<PojoGetClassChild> callApi = api.check_student_code(token,code, student_id );
        return callApi;
    }
    public Call<PojoGetClassChild> join_child(String token , String student_id , String code ){
        Call<PojoGetClassChild> callApi = api.join_child(token, student_id,code );
        return callApi;
    }



    public Call<PojoAllChapterList> get_urgent_chapter(String auth, String class_id, String student_id){
        Call<PojoAllChapterList> callApi = api.get_urgent_chapter(auth,class_id,student_id);
        return callApi;
    }*/

/*
    public Call<PojoAccountLinked> forgot_password(String user_id,String password){
        Call<PojoAccountLinked> callApi = api.forgot_password(user_id, password);
        return callApi;
    }



    public Call<PojoAccountLinked> user_account_linked(String mobile){
        Call<PojoAccountLinked> callApi = api.user_account_linked(mobile);

        return callApi;
    }




    public Call<GenrateParentCodePojo> gen_parent_code(String mobile){
        Call<GenrateParentCodePojo> callApi = api.gen_parent_code(mobile);

        return callApi;
    }

    public Call<GenrateParentCodePojo> verify_username(String username){
        Call<GenrateParentCodePojo> callApi = api.verify_username(username);

        return callApi;
    }
    public Call<PojoVerifyClassList> verify_class_code(String class_code){
        Call<PojoVerifyClassList> callApi = api.verify_class_code(class_code);
        return callApi;
    }
    public Call<PojoGetClassCode> getClass_code( ){
        Call<PojoGetClassCode> callApi = api.getClass_code( );
        return callApi;
    }
    //registration api
    public Call<PojoLogin> registration(String authtoken,registrationModel model, String mobile,String token,String date){
        Call<PojoLogin> callApi = api.user_registration(authtoken,model.getFirst_name(),model.getLast_name(), model.getImage(),model.getGrade(),
                model.getBoard(),mobile,model.getFather_name(),model.getMother_name(),
                model.getFather_mobile(),model.getMother_mobile(),model.getFather_email(),model.getMother_email(),
                token,date);
        return callApi;
    }
    public Call<PojoQuestions>listing_mcq(String authtoken){
        Call<PojoQuestions> callApi = api.listing_mcq(authtoken);
        return callApi;
    }
    public Call<SubjectClusterDetailsPojo>allChaptersList(String authtoken,String classID, String student_id){
        Call<SubjectClusterDetailsPojo> callApi = api.allChaptersList(authtoken,classID, student_id);
        return callApi;
    }
    public Call<BoostSubjectdetailPojo>boost_subject_sessions(String authtoken,String classID, String student_id){
        Call<BoostSubjectdetailPojo> callApi = api.boost_subject_sessions(authtoken,classID, student_id);
        return callApi;
    }
    public Call<BoostSubjectdetailPojo>revise_subject_sessions_list(String authtoken,String classID, String student_id){
        Call<BoostSubjectdetailPojo> callApi = api.revise_subject_sessions_list(authtoken,classID, student_id);
        return callApi;
    }
    public Call<HomeChaptersDetailsPojo>getHomeChapters(String authtoken,String classID, String student_id, String subjectID, String cluster_id){
        Call<HomeChaptersDetailsPojo> callApi = api.getHomeChapters(authtoken,classID, student_id, subjectID, cluster_id);
        return callApi;
    }
    public Call<BoostChapterDetailPojo>boost_chapters_list(String authtoken,String classID, String student_id, String subjectID, String cluster_id){
        Call<BoostChapterDetailPojo> callApi = api.boost_chapters_list(authtoken,classID, student_id, subjectID, cluster_id);
        return callApi;
    }
    public Call<BoostChapterDetailPojo>revise_chapters_list(String authtoken,String classID, String student_id, String subjectID, String cluster_id){
        Call<BoostChapterDetailPojo> callApi = api.revise_chapters_list(authtoken,classID, student_id, subjectID, cluster_id);
        return callApi;
    }
    public Call<HomeShortCutPojo>homeShortcut_sessions(String authtoken,String classID, String student_id, String subjectID, String cluster_id, String course_id, String chapter_id, String session_type){
        Call<HomeShortCutPojo> callApi = api.homeShortcut_sessions(authtoken,classID, student_id, subjectID, cluster_id, course_id, chapter_id,session_type);
        return callApi;
    }
    public Call<ChapterTopicDetailPojo>getHomeTopics(String authtoken,String classID, String student_id, String subjectID, String cluster_id, String chapterId){
        Call<ChapterTopicDetailPojo> callApi = api.getHomeTopics(authtoken,classID, student_id, subjectID, cluster_id, chapterId);
        return callApi;
    }
    public Call<BoostTopicDetailPojo>boost_topics_list(String authtoken,String classID, String student_id, String subjectID, String cluster_id, String chapterId){
        Call<BoostTopicDetailPojo> callApi = api.boost_topics_list(authtoken,classID, student_id, subjectID, cluster_id, chapterId);
        return callApi;
    }
    public Call<BoostTopicDetailPojo>revise_topics_list(String authtoken,String classID, String student_id, String subjectID, String cluster_id, String chapterId){
        Call<BoostTopicDetailPojo> callApi = api.revise_topics_list(authtoken,classID, student_id, subjectID, cluster_id, chapterId);
        return callApi;
    }
    public Call<PojoAllChapterList>get_urgent_chapter(String authtoken,String classID,String student_id){
        Call<PojoAllChapterList> callApi = api.get_urgent_chapter(authtoken,classID, student_id);
        return callApi;
    }
    public Call<BoostRecomendedPojo>boost_recommended(String authtoken,String classID, String student_id){
        Call<BoostRecomendedPojo> callApi = api.boost_recommended(authtoken,classID, student_id);
        return callApi;
    }
    public Call<BoostRecomendedPojo>revise_recommended(String authtoken,String classID, String student_id){
        Call<BoostRecomendedPojo> callApi = api.revise_recommended(authtoken,classID, student_id);
        return callApi;
    }
    public Call<BoostDimensionsSessionPojo>listing_boost_skills_session(String authtoken,String classID, String student_id){
        Call<BoostDimensionsSessionPojo> callApi = api.listing_boost_skills_session(authtoken,classID, student_id);
        return callApi;
    }
    public Call<BoostDimensionsSessionPojo>revise_skill_sessions(String authtoken,String classID, String student_id){
        Call<BoostDimensionsSessionPojo> callApi = api.revise_skill_sessions(authtoken,classID, student_id);
        return callApi;
    }

    public Call<PojoProfileModel>get_student_profile(String authtoken,String student_id){
        Call<PojoProfileModel> callApi = api.get_student_profile(authtoken, student_id);
        return callApi;

    }
    public Call<PojoProfileModel>student_profile(String authtoken,String student_id){
        Call<PojoProfileModel> callApi = api.student_profile(authtoken, student_id);
        return callApi;

    }

    public Call<PojoClass>get_class(String authtoken,String classID, String student_id){
        Call<PojoClass> callApi = api.get_class(authtoken,classID,student_id);
        return callApi;

    }

    public Call<PojoAnotherStudentProfile>get_another_student_profile(String authtoken,String student_id, String a_student_id){
        Call<PojoAnotherStudentProfile> callApi = api.get_another_student_profile(authtoken,student_id,a_student_id);
        return callApi;

    }

    public Call<PojoFollowStatus>get_follow_status(String authtoken,String student_id, String a_student_id, String follow_status){
        Call<PojoFollowStatus> callApi = api.get_follow_status(authtoken,student_id,a_student_id,follow_status);
        return callApi;

    }

    public Call<ProfilePojo>edit_stu_profile(String authtoken,RequestBody requestBody){
        Call<ProfilePojo> callApi = api.edit_stu_profile(authtoken,requestBody);
        return callApi;
    }

    public Call<GetPendingSessionPojo>get_pending_session(String authtoken, String student_id){
        Call<GetPendingSessionPojo> callApi = api.get_pending_session(authtoken,student_id);
        return callApi;
    }
    public Call<GetPendingSessionPojo>get_update_session(String  authtoken,HashMap<String, String> hashMap){
        Call<GetPendingSessionPojo> callApi = api.get_update_session(authtoken,hashMap);
        return callApi;
    }
public Call<SessionLevelsPojo>listing_session_level(String authtoken){
        Call<SessionLevelsPojo> callApi = api.listing_session_level(authtoken);
        return callApi;
    }
   public Call<SujectClassBasedPojo>listing_subject_classidbased(String authtoken,String class_id){
        Call<SujectClassBasedPojo> callApi = api.listing_subject_classidbased(authtoken,class_id);
        return callApi;
    }
public Call<ListingDurationPojo>listing_duration(){
        Call<ListingDurationPojo> callApi = api.listing_duration( );
        return callApi;
    }
    public Call<UpdateSessionPojo>update_session(String authtoken,String session_id, String break_time, String is_going_for_break, String break_start_time, String session_status, String session_type){
        Call<UpdateSessionPojo> callApi = api.update_session( authtoken,session_id, break_time, is_going_for_break, break_start_time, session_status, session_type);
        return callApi;
    }


    public Call<StuSubmitQuestionPojo>get_logout(String authtoken,HashMap<String, String> hashMap){
        Call<StuSubmitQuestionPojo> callApi = api.get_logout(authtoken,hashMap);
        return callApi;
    }

    public Call<PojoGetHelp>getGetHelpApiData(String authtoken){
        Call<PojoGetHelp> callApi = api.getGetHelpApiData(authtoken);
        return callApi;
    }

    public Call<PojoGetHelp>getAdd_help_query(String authtoken,String student_id, String queryType, String message){
        Call<PojoGetHelp> callApi = api.getAdd_help_query(authtoken,student_id,queryType,message);
        return callApi;
    }
    public Call<PojoULoginList>user_active_status(String authtoken,String user_id,String is_active){
        Call<PojoULoginList> callApi = api.user_active_status(authtoken,user_id, is_active);
        return callApi;
    }

    public Call<PojoGetStudySlots>get_study_slotsData(String authtoken,String student_id){
        Call<PojoGetStudySlots> callApi = api.get_study_slots(authtoken,student_id);
        return callApi;
    }
    public Call<ListingDurationPojo>getAddSlotData(String authtoken,String studentId, String startTimeSelected, String daysSelected, String duration, String level){
        Call<ListingDurationPojo> callApi = api.getAddSlotData(authtoken,studentId,startTimeSelected,daysSelected,duration, level);
        return callApi;
    }
    public Call<ListingDurationPojo>getUpdateSlotData(String authtoken,String studentId, String startTimeSelected, String daysSelected, String duration, String level, String add_study_slot_id){
        Call<ListingDurationPojo> callApi = api.getUpdateSlotData(authtoken,studentId,startTimeSelected,daysSelected,duration, level, add_study_slot_id);
        return callApi;
    }
*/


}
