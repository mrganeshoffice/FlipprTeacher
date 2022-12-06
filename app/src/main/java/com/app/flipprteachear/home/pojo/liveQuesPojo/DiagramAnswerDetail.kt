package com.app.flipprteachear.home.pojo.liveQuesPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DiagramAnswerDetail {
    @SerializedName("diagrams_id")
    @Expose
    var diagramsId: String? = null

    @SerializedName("questions_all_types_id")
    @Expose
    var questionsAllTypesId: String? = null

    @SerializedName("value")
    @Expose
    var value: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("x_value")
    @Expose
    var xValue: String? = null

    @SerializedName("y_value")
    @Expose
    var yValue: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null
}