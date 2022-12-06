package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NApplicationDetail : Serializable {
    @SerializedName("numerical_qty_val_id")
    @Expose
    var numericalQtyValId: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null

    @SerializedName("quantity")
    @Expose
    var quantity: String? = null

    @SerializedName("value")
    @Expose
    var value: String? = null

    @SerializedName("unit")
    @Expose
    var unit: String? = null

    @SerializedName("quantity1")
    @Expose
    var quantity1: String? = null

    @SerializedName("correct_option")
    @Expose
    var correctOption: String? = null

    @SerializedName("wrong_option")
    @Expose
    var wrongOption: String? = null

    @SerializedName("application_type")
    @Expose
    var applicationType: String? = null
}