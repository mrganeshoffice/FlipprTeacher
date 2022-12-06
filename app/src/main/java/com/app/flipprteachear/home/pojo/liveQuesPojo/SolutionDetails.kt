package com.app.flipprteachear.home.pojo.liveQuesPojo

import java.io.Serializable

data class SolutionDetails(
    val all_type_question_solution_id: String,
    val ans_desc: String,
    val ans_img: String,
    val ans_link: String,
    val questions_all_types_id: String
): Serializable