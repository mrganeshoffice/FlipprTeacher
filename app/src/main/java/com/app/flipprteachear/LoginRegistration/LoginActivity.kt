package com.app.flipprteachear.LoginRegistration

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.telephony.TelephonyManager
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.flipprteachear.LoginRegistration.pojo.LoginModel
import com.app.flipprteachear.LoginRegistration.pojo.PojoVerifyMobileNo
import com.app.flipprteachear.LoginRegistration.pojo.PojoVerifyOtpNo
import com.app.flipprteachear.R

import com.app.flipprteachear.databinding.ActivityLogin2Binding
import com.app.flipprteachear.retroFitClasses.apiCalls
import com.app.flipprteachear.utillsa.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityLogin2Binding

    private var pojoVerifyMobileNo: PojoVerifyMobileNo?=null
    private var preferenceManager: PreferenceManager?=null
    var mCountDownTimer: CountDownTimer? = null
    private var registered = "N/A"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // viewBinding= DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_login2)
        preferenceManager = PreferenceManager(applicationContext)
        viewBinding.rlOtp.setVisibility(View.GONE)
        viewBinding.rlLoginview.setVisibility(View.VISIBLE)

        viewBinding.tvLoginOtp.setOnClickListener{
//            viewBinding.rlLoginview.visibility = View.GONE
//            viewBinding.rlOtp.visibility = View.VISIBLE
            viewBinding.progress.setVisibility(View.VISIBLE)
            loginApi(
                LoginModel(
                    viewBinding.etMobilenumber .getText().toString(),
                    viewBinding.tvCountryCode.getText().toString()
            )
            )

        }
        viewBinding.progressotp.setVisibility(View.GONE)
        viewBinding.tvTems.setMovementMethod(LinkMovementMethod.getInstance())
        viewBinding.tvVerify.setOnClickListener {
            viewBinding.progressotp.setVisibility(View.VISIBLE)
            verifyOtpApi(
                viewBinding.etOtp.getText().toString(),
                viewBinding.etMobilenumber.getText().toString(),
                viewBinding.tvCountryCode.getText().toString()
            )
        }

        viewBinding.tvResendotp.setOnClickListener{
            viewBinding.etOtp.setText("")
            viewBinding.progressotp.setVisibility(View.VISIBLE)
            loginApi(LoginModel(viewBinding.etMobilenumber .getText().toString(),
                viewBinding.tvCountryCode.getText().toString())
            )
        }
        viewBinding.llCHangenumber.setOnClickListener{

            viewBinding.rlLoginview.visibility = View.VISIBLE
            viewBinding.rlOtp.visibility = View.GONE
        }
        viewBinding.ivEditNumber.setOnClickListener{

            viewBinding.rlLoginview.visibility = View.VISIBLE
            viewBinding.rlOtp.visibility = View.GONE
        }

        getCounteryPhoneCode()

    }
    fun loginApi(model: LoginModel?) {
        viewBinding. tvChangenumber.setText(
            resources.getString(
                R.string.sms_sent_to,
                viewBinding.tvCountryCode.getText().toString(),
                viewBinding.etMobilenumber.getText().toString()
            )
        )
        val api = apiCalls()
        val callApi: Call<PojoVerifyMobileNo> = api.verify_mobile(model)
        callApi.enqueue(object : Callback<PojoVerifyMobileNo> {
            override fun onResponse(
                call: Call<PojoVerifyMobileNo>,
                response: Response<PojoVerifyMobileNo>
            ) {
                viewBinding.progressotp.setVisibility(View.VISIBLE)
                viewBinding.progress.setVisibility(View.GONE)
                if (response.body()!!.getType().equals("success")) {
                    try {
                        // pojoLogin = new PojoLogin();
                        pojoVerifyMobileNo = response.body()
                        //if(pojoLogin.getLogin().equalsIgnoreCase("true")){
                        registered = "yes"
                        viewBinding.progress.setVisibility(View.GONE)
                        viewBinding.etMobilenumber.setEnabled(true)

                        preferenceManager!!.putValueString("mobile", viewBinding.etMobilenumber.getText().toString())
                        preferenceManager!!.putValueString("request_id", pojoVerifyMobileNo!!.getRequestId())
                        // sendVerificationCode(et_mobilenumber.getText().toString());
                        viewBinding.rlOtp.visibility = View.VISIBLE
                        viewBinding.rlLoginview.visibility = View.GONE

                        if (mCountDownTimer != null) mCountDownTimer!!.cancel()
                        viewBinding. tvChangenumber.setText(
                            resources.getString(
                                R.string.sms_sent_to,
                                viewBinding.tvCountryCode.getText().toString(),
                                preferenceManager!!.getValueString("mobile" )
                            )
                        )
                        countDounVerifyOtpTimer()
                        val imm =
                            applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(viewBinding.etMobilenumber.getWindowToken(), 0)
                    } catch (e: Exception) {
                        Log.e("catch_exception", "catch_login:$e")
                        viewBinding.etMobilenumber.setEnabled(true)
                        viewBinding.progress.setVisibility(View.GONE)
                        Snackbar.make(
                            viewBinding.rlLogin,
                            "Mobile number doesnot exist, try again with other number.",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<PojoVerifyMobileNo>, t: Throwable) {
                viewBinding.progressotp.setVisibility(View.VISIBLE)
                viewBinding.progress.setVisibility(View.GONE)
                viewBinding.etMobilenumber.setEnabled(true)
                Snackbar.make(
                    viewBinding.rlLogin,
                    "Something went wrong, try again later.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
    }
    fun verifyOtpApi(otp: String?, mobile: String?, s: String?) {
        viewBinding.progressotp.setVisibility(View.VISIBLE)
        val api = apiCalls()
        val callApi: Call<PojoVerifyOtpNo> = api.verify_mobileOtp(otp, mobile, s)
        callApi.enqueue(object : Callback<PojoVerifyOtpNo> {
            override fun onResponse(
                call: Call<PojoVerifyOtpNo>,
                response: Response<PojoVerifyOtpNo>
            ) {
                viewBinding.progressotp.setVisibility(View.GONE)
                if (response.body()!!.getType().equals("success")) {
                    viewBinding.rlOtp.visibility = View.GONE
                    viewBinding.rlLoginview.visibility = View.VISIBLE
                    viewBinding.etOtp.setText("")
                     startActivity(Intent(applicationContext, LinkedAccount_Activity::class.java))
                    finish()
                }else if(response.body()!!.getType().equals("error")){
                    Snackbar.make(viewBinding.rlLogin,"Wrong OTP entered", Snackbar.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PojoVerifyOtpNo>, t: Throwable) {
                viewBinding.progressotp.setVisibility(View.GONE)
                viewBinding.etMobilenumber.setEnabled(true)
                Snackbar.make(viewBinding.rlLogin,
                    "Something went wrong, try again later.", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
    private fun countDounVerifyOtpTimer() {

        with(viewBinding){
            tvTimer.setVisibility(View.VISIBLE)
        tvVerify.setVisibility(View.VISIBLE)
        tvResendotp.visibility = View.GONE
            progressotp.setVisibility(View.GONE)
        mCountDownTimer = object : CountDownTimer(61000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.setText("" + String.format("in 00:%d ", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)))
            }

            override fun onFinish() {
                tvResendotp.visibility = View.VISIBLE
                tvVerify.setVisibility(View.GONE)
                tvTimer.setVisibility(View.GONE)

            }
        }
        mCountDownTimer!!.start()
    }
    }

    private fun getCounteryPhoneCode() {
        var contryCode = ""
        val tm = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        contryCode = tm.networkCountryIso.toUpperCase()
        if (contryCode.equals("", ignoreCase = true)) {
            contryCode = "IN"
        }
        Log.d("CounteryCode", contryCode.toUpperCase())
        val country2phone   = HashMap<String, String>()
        country2phone["AF"] = "+93"
        country2phone["AL"] = "+355"
        country2phone["DZ"] = "+213"
        country2phone["AD"] = "+376"
        country2phone["AO"] = "+244"
        country2phone["AG"] = "+1-268"
        country2phone["AR"] = "+54"
        country2phone["AM"] = "+374"
        country2phone["AU"] = "+61"
        country2phone["AT"] = "+43"
        country2phone["AZ"] = "+994"
        country2phone["BS"] = "+1-242"
        country2phone["BH"] = "+973"
        country2phone["BD"] = "+880"
        country2phone["BB"] = "+1-246"
        country2phone["BY"] = "+375"
        country2phone["BE"] = "+32"
        country2phone["BZ"] = "+501"
        country2phone["BJ"] = "+229"
        country2phone["BT"] = "+975"
        country2phone["BO"] = "+591"
        country2phone["BA"] = "+387"
        country2phone["BW"] = "+267"
        country2phone["BR"] = "+55"
        country2phone["BN"] = "+673"
        country2phone["BG"] = "+359"
        country2phone["BF"] = "+226"
        country2phone["BI"] = "+257"
        country2phone["KH"] = "+855"
        country2phone["CM"] = "+237"
        country2phone["CA"] = "+1"
        country2phone["CV"] = "+238"
        country2phone["CF"] = "+236"
        country2phone["TD"] = "+235"
        country2phone["CL"] = "+56"
        country2phone["CN"] = "+86"
        country2phone["CO"] = "+57"
        country2phone["KM"] = "+269"
        country2phone["CD"] = "+243"
        country2phone["CG"] = "+242"
        country2phone["CR"] = "+506"
        country2phone["CI"] = "+225"
        country2phone["HR"] = "+385"
        country2phone["CU"] = "+53"
        country2phone["CY"] = "+357"
        country2phone["CZ"] = "+420"
        country2phone["DK"] = "+45"
        country2phone["DJ"] = "+253"
        country2phone["DM"] = "+1-767"
        country2phone["DO"] = "+1-809and1-829"
        country2phone["EC"] = "+593"
        country2phone["EG"] = "+20"
        country2phone["SV"] = "+503"
        country2phone["GQ"] = "+240"
        country2phone["ER"] = "+291"
        country2phone["EE"] = "+372"
        country2phone["ET"] = "+251"
        country2phone["FJ"] = "+679"
        country2phone["FI"] = "+358"
        country2phone["FR"] = "+33"
        country2phone["GA"] = "+241"
        country2phone["GM"] = "+220"
        country2phone["GE"] = "+995"
        country2phone["DE"] = "+49"
        country2phone["GH"] = "+233"
        country2phone["GR"] = "+30"
        country2phone["GD"] = "+1-473"
        country2phone["GT"] = "+502"
        country2phone["GN"] = "+224"
        country2phone["GW"] = "+245"
        country2phone["GY"] = "+592"
        country2phone["HT"] = "+509"
        country2phone["HN"] = "+504"
        country2phone["HU"] = "+36"
        country2phone["IS"] = "+354"
        country2phone["IN"] = "+91"
        country2phone["ID"] = "+62"
        country2phone["IR"] = "+98"
        country2phone["IQ"] = "+964"
        country2phone["IE"] = "+353"
        country2phone["IL"] = "+972"
        country2phone["IT"] = "+39"
        country2phone["JM"] = "+1-876"
        country2phone["JP"] = "+81"
        country2phone["JO"] = "+962"
        country2phone["KZ"] = "+7"
        country2phone["KE"] = "+254"
        country2phone["KI"] = "+686"
        country2phone["KP"] = "+850"
        country2phone["KR"] = "+82"
        country2phone["KW"] = "+965"
        country2phone["KG"] = "+996"
        country2phone["LA"] = "+856"
        country2phone["LV"] = "+371"
        country2phone["LB"] = "+961"
        country2phone["LS"] = "+266"
        country2phone["LR"] = "+231"
        country2phone["LY"] = "+218"
        country2phone["LI"] = "+423"
        country2phone["LT"] = "+370"
        country2phone["LU"] = "+352"
        country2phone["MK"] = "+389"
        country2phone["MG"] = "+261"
        country2phone["MW"] = "+265"
        country2phone["MY"] = "+60"
        country2phone["MV"] = "+960"
        country2phone["ML"] = "+223"
        country2phone["MT"] = "+356"
        country2phone["MH"] = "+692"
        country2phone["MR"] = "+222"
        country2phone["MU"] = "+230"
        country2phone["MX"] = "+52"
        country2phone["FM"] = "+691"
        country2phone["MD"] = "+373"
        country2phone["MC"] = "+377"
        country2phone["MN"] = "+976"
        country2phone["ME"] = "+382"
        country2phone["MA"] = "+212"
        country2phone["MZ"] = "+258"
        country2phone["MM"] = "+95"
        country2phone["NA"] = "+264"
        country2phone["NR"] = "+674"
        country2phone["NP"] = "+977"
        country2phone["NL"] = "+31"
        country2phone["NZ"] = "+64"
        country2phone["NI"] = "+505"
        country2phone["NE"] = "+227"
        country2phone["NG"] = "+234"
        country2phone["NO"] = "+47"
        country2phone["OM"] = "+968"
        country2phone["PK"] = "+92"
        country2phone["PW"] = "+680"
        country2phone["PA"] = "+507"
        country2phone["PG"] = "+675"
        country2phone["PY"] = "+595"
        country2phone["PE"] = "+51"
        country2phone["PH"] = "+63"
        country2phone["PL"] = "+48"
        country2phone["PT"] = "+351"
        country2phone["QA"] = "+974"
        country2phone["RO"] = "+40"
        country2phone["RU"] = "+7"
        country2phone["RW"] = "+250"
        country2phone["KN"] = "+1-869"
        country2phone["LC"] = "+1-758"
        country2phone["VC"] = "+1-784"
        country2phone["WS"] = "+685"
        country2phone["SM"] = "+378"
        country2phone["ST"] = "+239"
        country2phone["SA"] = "+966"
        country2phone["SN"] = "+221"
        country2phone["RS"] = "+381"
        country2phone["SC"] = "+248"
        country2phone["SL"] = "+232"
        country2phone["SG"] = "+65"
        country2phone["SK"] = "+421"
        country2phone["SI"] = "+386"
        country2phone["SB"] = "+677"
        country2phone["SO"] = "+252"
        country2phone["ZA"] = "+27"
        country2phone["ES"] = "+34"
        country2phone["LK"] = "+94"
        country2phone["SD"] = "+249"
        country2phone["SR"] = "+597"
        country2phone["SZ"] = "+268"
        country2phone["SE"] = "+46"
        country2phone["CH"] = "+41"
        country2phone["SY"] = "+963"
        country2phone["TJ"] = "+992"
        country2phone["TZ"] = "+255"
        country2phone["TH"] = "+66"
        country2phone["TL"] = "+670"
        country2phone["TG"] = "+228"
        country2phone["TO"] = "+676"
        country2phone["TT"] = "+1-868"
        country2phone["TN"] = "+216"
        country2phone["TR"] = "+90"
        country2phone["TM"] = "+993"
        country2phone["TV"] = "+688"
        country2phone["UG"] = "+256"
        country2phone["UA"] = "+380"
        country2phone["AE"] = "+971"
        country2phone["GB"] = "+44"
        country2phone["US"] = "+1"
        country2phone["UY"] = "+598"
        country2phone["UZ"] = "+998"
        country2phone["VU"] = "+678"
        country2phone["VA"] = "+379"
        country2phone["VE"] = "+58"
        country2phone["VN"] = "+84"
        country2phone["YE"] = "+967"
        country2phone["ZM"] = "+260"
        country2phone["ZW"] = "+263"
        country2phone["GE"] = "+995"
        country2phone["TW"] = "+886"
        country2phone["AZ"] = "+374-97"
        country2phone["CY"] = "+90-392"
        country2phone["MD"] = "+373-533"
        country2phone["SO"] = "+252"
        country2phone["GE"] = "+995"
        country2phone["CX"] = "+61"
        country2phone["CC"] = "+61"
        country2phone["NF"] = "+672"
        country2phone["NC"] = "+687"
        country2phone["PF"] = "+689"
        country2phone["YT"] = "+262"
        country2phone["GP"] = "+590"
        country2phone["GP"] = "+590"
        country2phone["PM"] = "+508"
        country2phone["WF"] = "+681"
        country2phone["CK"] = "+682"
        country2phone["NU"] = "+683"
        country2phone["TK"] = "+690"
        country2phone["GG"] = "+44"
        country2phone["IM"] = "+44"
        country2phone["JE"] = "+44"
        country2phone["AI"] = "+1-264"
        country2phone["BM"] = "+1-441"
        country2phone["IO"] = "+246"
        country2phone[""] = "+357"
        country2phone["VG"] = "+1-284"
        country2phone["KY"] = "+1-345"
        country2phone["FK"] = "+500"
        country2phone["GI"] = "+350"
        country2phone["MS"] = "+1-664"
        country2phone["SH"] = "+290"
        country2phone["TC"] = "+1-649"
        country2phone["MP"] = "+1-670"
        country2phone["PR"] = "+1-787and1-939"
        country2phone["AS"] = "+1-684"
        country2phone["GU"] = "+1-671"
        country2phone["VI"] = "+1-340"
        country2phone["HK"] = "+852"
        country2phone["MO"] = "+853"
        country2phone["FO"] = "+298"
        country2phone["GL"] = "+299"
        country2phone["GF"] = "+594"
        country2phone["GP"] = "+590"
        country2phone["MQ"] = "+596"
        country2phone["RE"] = "+262"
        country2phone["AX"] = "+358-18"
        country2phone["AW"] = "+297"
        country2phone["AN"] = "+599"
        country2phone["SJ"] = "+47"
        country2phone["AC"] = "+247"
        country2phone["TA"] = "+290"
        country2phone["CS"] = "+381"
        country2phone["PS"] = "+970"
        country2phone["EH"] = "+212"
        if (!country2phone[contryCode].toString()
                .isEmpty()
        ) viewBinding.tvCountryCode.setText(country2phone[contryCode].toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}