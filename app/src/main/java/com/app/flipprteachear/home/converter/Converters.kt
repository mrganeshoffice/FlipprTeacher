package com.app.flipprteachear.home.converter

import androidx.room.TypeConverter
import com.app.flipprteachear.home.pojo.Chapter
import com.app.flipprteachear.home.pojo.Student
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromChapterList(activitywizard: List<Chapter?>?): String? {
        val type = object : TypeToken<List<Chapter>>() {}.type
        return Gson().toJson(activitywizard, type)
    }

    @TypeConverter
    fun toChapterList(activitywizard: String?): List<Chapter>? {
        val type = object : TypeToken<List<Chapter>>() {}.type
        return Gson().fromJson<List<Chapter>>(activitywizard, type)
    }

    @TypeConverter
    fun fromStudentList(activitywizard: List<Student?>?): String? {
        val type = object : TypeToken<List<Student>>() {}.type
        return Gson().toJson(activitywizard, type)
    }

    @TypeConverter
    fun toStudentList(activitywizard: String?): List<Student>? {
        val type = object : TypeToken<List<Student>>() {}.type
        return Gson().fromJson<List<Student>>(activitywizard, type)
    }

}