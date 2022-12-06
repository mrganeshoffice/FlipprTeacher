package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AssertionReasoningDetail : Serializable {
    @SerializedName("assertion_reasoning_ques_id")
    @Expose
    var assertionReasoningQuesId: String? = null

    @SerializedName("assertion")
    @Expose
    var assertion: String? = null

    @SerializedName("reasoning")
    @Expose
    var reasoning: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null
}