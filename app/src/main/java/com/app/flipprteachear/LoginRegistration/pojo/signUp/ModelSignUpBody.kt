package com.app.flipprteachear.LoginRegistration.pojo.signUp

import org.json.JSONObject

data class ModelSignUpBody(
    var account_type: String,
    var added_classes:ArrayList<AddedClasse>,//var added_classes:ArrayList<JSONObject>,
    var device_token: String,
    var device_type: String,
    var dob: String,
    var email: String,
    var email_varification: String,
    var first_name: String,
    var gender_type: String,
    var last_name: String,
    var mobile: String,
    var parent_code: String,
    var parent_id: String,
    var password: String,
    var username: String,

)