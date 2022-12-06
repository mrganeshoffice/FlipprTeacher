package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NApplicationValDetail {
    @SerializedName("application_ans_val_id")
    @Expose
    var applicationAnsValId: String? = null

    @SerializedName("numerical_qty_val_id")
    @Expose
    var numericalQtyValId: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null

    @SerializedName("quantity1")
    @Expose
    var quantity1: String? = null

    @SerializedName("correct_option")
    @Expose
    var correctOption: String? = null

    @SerializedName("wrong_option")
    @Expose
    var wrongOption: String? = null
}