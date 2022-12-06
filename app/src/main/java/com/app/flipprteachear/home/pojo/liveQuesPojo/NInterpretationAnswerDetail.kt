package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NInterpretationAnswerDetail : Serializable {
    @SerializedName("interpretation_ans_id")
    @Expose
    var interpretationAnsId: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null

    @SerializedName("interpretation_answers")
    @Expose
    var interpretationAnswers: String? = null

    @SerializedName("interpretation_right_wrong")
    @Expose
    var interpretationRightWrong: String? = null

    @SerializedName("interp_type")
    @Expose
    var interpType: String? = null
}