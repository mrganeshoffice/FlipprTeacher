package com.app.flipprteachear.home.pojo

import com.google.gson.annotations.SerializedName

data class TecherSubJect(
    @SerializedName("message" ) var message : String?            = null,
    @SerializedName("details" ) var details : ArrayList<TeacherSubjectDetails> = arrayListOf()
) {
}