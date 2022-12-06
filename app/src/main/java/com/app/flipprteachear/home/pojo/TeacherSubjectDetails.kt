package com.app.flipprteachear.home.pojo

import com.google.gson.annotations.SerializedName

data class TeacherSubjectDetails(
    @SerializedName("class_name"                   ) var className                : String? = null,
    @SerializedName("academy_name"                 ) var academyName              : String? = null,
    @SerializedName("subject_name"                 ) var subjectName              : String? = null,
    @SerializedName("subject_id"                   ) var subjectId                : String? = null,
    @SerializedName("teacher_added_classes_sub_id" ) var teacherAddedClassesSubId : String? = null
) {

}
