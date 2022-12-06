package com.app.flipprteachear.home.pojo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "FlipperTeacher")
data class Detail(
    @PrimaryKey
    var subject_id: Int?=null ?: 0,
    var avg_confid: Int?=null,
    var academy_name: String?=null,
    var avg_grit: Int?=null,
    var avg_points: Int?=null,
    var avg_syllabus_done: Int?=null,
    var chapters: List<Chapter>?=null,
    var class_name: String?=null,
    var students: List<Student>?=null,
    var students_count: Int?=null,
    var avg_homework: String?=null,
    var avg_syllabus_mastered: String?=null,
    var subject_name: String?=null
)