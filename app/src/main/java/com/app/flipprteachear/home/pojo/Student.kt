package com.app.flipprteachear.home.pojo

import java.io.Serializable

data class Student(
    val account_type: String?=null,
    val active_status: String?=null,
    val avg_confid: Double?=null,
    val class_code: String?=null,
    val class_id: String?=null,
    val device_token: String?=null,
    val device_type: String?=null,
    val dob: String?=null,
    val email: String?=null,
    val email_varification: String?=null,
    val first_name: String?=null,
    val gender_type: Any?=null,
    val image: String?=null,
    val last_name: String?=null,
    val mobile: String?=null,
    val parent_code: String?=null,
    val parent_id: String?=null,
    val password: Any?=null,
    val registration_date: String?=null,
    val short_des: String?=null,
    val syllabus_comp: Float?=null,
    val homework_comp: Float?=null,
    val total_points: String?=null,
    val user_id: String?=null,
    val username: String?=null,
    val syllabus_mastered: Float?=null

):Serializable