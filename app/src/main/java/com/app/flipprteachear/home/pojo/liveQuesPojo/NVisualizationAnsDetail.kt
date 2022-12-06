package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NVisualizationAnsDetail : Serializable {
    @SerializedName("visualization_ans_id")
    @Expose
    var visualizationAnsId: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null

    @SerializedName("visualization_answers")
    @Expose
    var visualizationAnswers: String? = null

    @SerializedName("visualization_right_wrong")
    @Expose
    var visualizationRightWrong: String? = null

    @SerializedName("visualization_type")
    @Expose
    var visualizationType: String? = null
}