package com.app.flipprteachear.LoginRegistration

import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.flipperparent.dashboard.ui.LoginRegistration.pojo.PojoForgotPasswrod
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.ActivityForgotPassword1Binding

import com.app.flipprteachear.retroFitClasses.apiCalls
import com.mukesh.OnOtpCompletionListener
import retrofit2.Response
import java.util.*

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityForgotPassword1Binding
    private var pref: SharedPreferences? = null
    var setListener: OnDateSetListener? = null

    var accountPin1 = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password1)
        /*    pref = getSharedPreferences("flipprLogins", MODE_PRIVATE)
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val day = calendar[Calendar.DAY_OF_MONTH]*/
        with(viewBinding) {
            /* tvDob.setOnClickListener(View.OnClickListener {
                 val datePickerDialog = DatePickerDialog(
                     this@ForgotPasswordActivity, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                     setListener, year, month, day
                 )
                 datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
                 datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                 datePickerDialog.show()
             })
             setListener = OnDateSetListener { view, year, month, dayOfMonth ->
                 var month = month
                 month = month + 1
                 val date = "$dayOfMonth/$month/$year"
                 val originalStringFormat = "dd/MM/yyy"
                 val desiredStringFormat = "dd MMM, yyyy"
                 val readingFormat = SimpleDateFormat(originalStringFormat)
                 val outputFormat = SimpleDateFormat(desiredStringFormat)
                 var dateformat: String? = ""
                 try {
                     val newDate = readingFormat.parse(date)
                     dateformat = outputFormat.format(newDate)
                     println(outputFormat.format(newDate))
                 } catch (e: ParseException) {
                     e.printStackTrace()
                 }
                 tvDob.setText(dateformat)
             }
             tvConfirm.setOnClickListener(View.OnClickListener {
                 if (tvDob.getText().toString().isEmpty()) {
                     Toast.makeText(
                         this@ForgotPasswordActivity,
                         "Please select date of birth",
                         Toast.LENGTH_LONG
                     ).show()
                 } else {
                     progressBar.setVisibility(View.VISIBLE)
                     //VerifyUserApi()
                 }
             })*/
            otpViewPassword.setOtpCompletionListener(OnOtpCompletionListener { otp ->
                accountPin1 = otp

            })
            tvSignin.setOnClickListener(View.OnClickListener {
                startActivity(Intent(this@ForgotPasswordActivity, LinkedAccount_Activity::class.java))
                //signUp_Api();
                finish()
            })
            tvForgotPassword.setOnClickListener(View.OnClickListener {
                if (accountPin1.length == 4 ){
                    forgotPasswordApi()
                }else{
                    Toast.makeText(applicationContext,"Please enter valid password", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
    private fun forgotPasswordApi() {
        val api =  apiCalls();

        val callApi: retrofit2.Call<PojoForgotPasswrod> = api.forgot_passwrod(intent.getStringExtra("user_id"),accountPin1)
        callApi.enqueue(object : retrofit2.Callback<PojoForgotPasswrod?> {
            override fun onResponse(call: retrofit2.Call<PojoForgotPasswrod?>, response: Response<PojoForgotPasswrod?>) {
                if (response.isSuccessful){
                    if (response.body()!!.login.equals("true"))
                        Toast.makeText(applicationContext, "Password Change Successfully", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<PojoForgotPasswrod?>, t: Throwable) {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }
    /*
        private fun VerifyUserApi() {
            val username = intent.getStringExtra("username")
            val mobile = pref!!.getString("mobile", "")
            val api = apiCalls()
            val callApi: Call<PojoVerify> =
                api.verify_user(username, mobile, tv_dob.getText().toString())
            callApi.enqueue(object : Callback<PojoVerify?>() {
                fun onResponse(call: Call<PojoVerify?>?, response: Response<PojoVerify?>) {
                    if (response.isSuccessful()) {
                        if (response.body().getMessage().equalsIgnoreCase("Valid")) {
                            Log.d("HelloUser", "Api Work")
                            progress_bar.setVisibility(View.GONE)
                            userid = response.body().getDetails().getUserId()
                            linear1.setVisibility(View.GONE)
                            linear2.setVisibility(View.VISIBLE)
                            tv_signin.setVisibility(View.GONE)
                        } else {
                            progress_bar.setVisibility(View.GONE)
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                "Invalid date of birth",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                fun onFailure(call: Call<PojoVerify?>?, t: Throwable?) {
                    progress_bar.setVisibility(View.GONE)
                    Log.d("Error", "Something went wrong")
                }
            })
        }*/
}