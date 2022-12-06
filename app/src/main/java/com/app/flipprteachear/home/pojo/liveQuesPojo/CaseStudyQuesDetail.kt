package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CaseStudyQuesDetail : Serializable {
    @SerializedName("casestudy_ques_id")
    @Expose
    var casestudyQuesId: String? = null

    @SerializedName("case_study")
    @Expose
    var caseStudy: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null

    @SerializedName("casestudy_id")
    @Expose
    var casestudyId: String? = null

    @SerializedName("casestudy_mcqs")
    @Expose
    var casestudyMcqs: List<CasestudyMcq>? =
        null

    @SerializedName("case")
    @Expose
    private val _case: String? = null
}