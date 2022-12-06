package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NumericalSolutionDetail : Serializable {
    @SerializedName("numerical_solution_id")
    @Expose
    var numericalSolutionId: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null

    @SerializedName("numerical_ans_desc")
    @Expose
    var numericalAnsDesc: String? = null

    @SerializedName("numerical_ans_link")
    @Expose
    var numericalAnsLink: String? = null

    @SerializedName("numerical_solution_type")
    @Expose
    var numericalSolutionType: String? = null
}