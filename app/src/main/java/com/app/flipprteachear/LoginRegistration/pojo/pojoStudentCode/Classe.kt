package com.app.flipprteachear.LoginRegistration.pojo.pojoStudentCode

data class  Classe(
    val academy_name: String,
    val class_code: String,
    val class_id: String,
    val class_image: String,
    val class_name: String,
    val course_id: String,
    val course_name: String,
    val school_class_course_id: String,
    val status: String,
    val subjects: List<Subject>
)