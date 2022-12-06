package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CasestudyMcq : Serializable {
    @SerializedName("right_txt_ans_id")
    @Expose
    var rightTxtAnsId: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null

    @SerializedName("right_txt_ans1")
    @Expose
    var rightTxtAns1: String? = null

    @SerializedName("types")
    @Expose
    var types: String? = null

    @SerializedName("question_img")
    @Expose
    var questionImg: String? = null

    @SerializedName("casestudy_ques_id")
    @Expose
    var casestudyQuesId: String? = null
}