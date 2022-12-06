package com.app.flipprteachear.home.pojo.studentDetail

data class RecentSession(
    val chapter_id: String,
    val chapter_name: String,
    val cluster_id: String,
    val confidence: Int,
    val course_id: String,
    val image: String,
    val name: String,
    val session_spend_time: Int,
    val session_total_time: Double,
    val session_type: String,
    val student_detail: StudentDetail,
    val subject_id: String,
    val subject_name: String,
    val teacher_activities_details: TeacherActivitiesDetails,
    val teacher_activities_id: String,
    val topic_id: String,
    val total_points: Int,
    val updated_time: String
)