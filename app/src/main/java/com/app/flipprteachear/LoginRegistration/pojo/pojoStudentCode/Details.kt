package com.app.flipprteachear.LoginRegistration.pojo.pojoStudentCode

data class Details(
    val account_type: String,
    val active_status: String,
    val class_code: String,
    val class_id: String,
    val device_token: String,
    val device_type: String,
    val dob: String,
    val email: String,
    val email_varification: String,
    val first_name: String?=null,
    val gender_type: Any,
    val image: String,
    val last_name: String,
    val mobile: String,
    val parent_code: String?=null,
    val parent_id: String,
    val password: Any,
    val registration_date: String,
    val short_des: String,
    val user_id: String,
    val username: String
)