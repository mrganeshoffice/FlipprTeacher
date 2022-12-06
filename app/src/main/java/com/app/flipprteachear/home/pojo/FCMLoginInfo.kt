package com.app.flipprteachear.home.pojo

class FCMLoginInfo(
   var currentQuestionIndex :String,
   var dimension :String,
   var level :String,
   var liveCode :String,
   var nextTapped :String,
   var schoolClassCourseID :String,
   var schoolCourseStructureID :String,
   var startStatus :String,
   var submittedBy :List<FCMSubMittedByModel>,
   var users :List<String>
) {
}