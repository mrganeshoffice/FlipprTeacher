package com.app.flipprteachear.home.pojo

import com.google.gson.annotations.SerializedName

data class Classes (

    @SerializedName("class_id"               ) var classId             : String?             = null,
    @SerializedName("class_name"             ) var className           : String?             = null,
    @SerializedName("status"                 ) var status              : String?             = null,
    @SerializedName("class_code"             ) var classCode           : String?             = null,
    @SerializedName("class_image"            ) var classImage          : String?             = null,
    @SerializedName("academy_name"           ) var academyName         : String?             = null,
    @SerializedName("course_name"            ) var courseName          : String?             = null,
    @SerializedName("course_id"              ) var courseId            : String?             = null,
    @SerializedName("school_class_course_id" ) var schoolClassCourseId : String?             = null,
    @SerializedName("subjects"               ) var subjects            : ArrayList<Subjects> = arrayListOf()

)
