package com.app.flipprteachear.home.pojo.flag

import com.app.flipprteachear.home.pojo.TeacherSubjectDetails
import com.google.gson.annotations.SerializedName

data class QuesQueryResponse(
    @SerializedName("message") var message: String? = null,
    @SerializedName("details") var queryDetails: ArrayList<QueryDetails> = arrayListOf()
)