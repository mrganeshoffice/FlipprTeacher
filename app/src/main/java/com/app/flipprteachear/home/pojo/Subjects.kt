package com.app.flipprteachear.home.pojo

import com.google.gson.annotations.SerializedName

data class Subjects (

    @SerializedName("subject_id"            ) var subjectId           : String? = null,
    @SerializedName("name"                  ) var name                : String? = null,
    @SerializedName("teacher_activities_id" ) var teacherActivitiesId : String? = null,
    @SerializedName("status"                ) var status              : String? = null,
    @SerializedName("subject_image"         ) var subjectImage        : String? = null

)
