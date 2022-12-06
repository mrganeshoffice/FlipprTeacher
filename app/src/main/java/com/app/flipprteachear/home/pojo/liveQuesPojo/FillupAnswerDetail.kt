package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FillupAnswerDetail : Serializable {
    @SerializedName("fillup_ans_id")
    @Expose
    var fillupAnsId: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null

    @SerializedName("blank_id")
    @Expose
    var blankId: String? = null

    @SerializedName("fillup_answers")
    @Expose
    var fillupAnswers: String? = null
}