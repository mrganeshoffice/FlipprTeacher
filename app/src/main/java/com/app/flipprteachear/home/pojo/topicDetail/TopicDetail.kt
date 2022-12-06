package com.app.flipprteachear.home.pojo.topicDetail

data class TopicDetail(
    val chapter_id: String?=null,
    val chapter_image: String?=null,
    val chapter_name: String?=null,
    val cluster_id: String?=null,
    val ques_count: Int?=null,
    val school_class_course_id: String?=null,
    val school_course_structure_id: String?=null,
    val school_id: String?=null,
    var session_total_time: Float?=null,
    var spend_session_time: Float?=null,
    val subject_id: String?=null,
    val subject_name: String?=null,
    val teacher_activity_status: String?=null,
    val topic_id: String?=null,
    val topic_image: String?=null,
    val topic_name: String?=null
)