package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TruefalseSolutionDetail : Serializable {
    @SerializedName("truefalse_solution_id")
    @Expose
    var truefalseSolutionId: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null

    @SerializedName("truefalsel_ans_desc")
    @Expose
    var truefalselAnsDesc: String? = null

    @SerializedName("truefalse_ans_link")
    @Expose
    var truefalseAnsLink: String? = null
}