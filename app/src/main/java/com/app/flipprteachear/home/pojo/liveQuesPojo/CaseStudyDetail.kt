package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CaseStudyDetail : Serializable {
    @SerializedName("casestudy_id")
    @Expose
    var casestudyId: String? = null

    @SerializedName("case")
    @Expose
    var case: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null
    private val casestudyMcqs: List<CasestudyMcq>? = null
}