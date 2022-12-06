package com.app.flipprteachear.LoginRegistration

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.flipprteachear.R
import com.app.flipprteachear.home.view.activity.HomeActivity
import com.app.flipprteachear.utillsa.Global
import com.app.flipprteachear.utillsa.PreferenceManager


class Splash : AppCompatActivity() {
    private var receiver: Global.NetworkChangeReceiver? = null
    var global: Global? = null
    var splash: RelativeLayout? = null

    private var preferences: PreferenceManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splash = findViewById(R.id.splash)
        global = applicationContext as Global
        preferences = PreferenceManager(applicationContext)

    //   preferences!!.sharedPreferences.edit().clear().apply()


        funCheckNetwork()
        val mCountDownTimer: CountDownTimer = object : CountDownTimer(2000, 100)
        {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                Log.e("TAG", "onFinish: ${preferences!!.getValueString("userId" )}......${preferences!!.getValueString("username" )}" )

                if (!preferences!!.getValueString("userId" ).isEmpty()
                    && !preferences!!.getValueString("username" ).isEmpty()
                ) {
                    //                   openLoginPage();
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    overridePendingTransition(R.animator.enter_from_right, R.animator.exit_to_left)
                    finish()
                } else {

                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                    overridePendingTransition(R.animator.enter_from_right, R.animator.exit_to_left)
                    finish()
                }
            }
        }
        mCountDownTimer.start()
    }

    fun funCheckNetwork() {
        //check Internet connected or not
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        receiver = Global.NetworkChangeReceiver(splash)
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        Log.e("catch_exception", "onDestory")
        super.onDestroy()
        unregisterReceiver(receiver)
    } /*  private void getFireBaseToken() {
        //final String token0 = FirebaseInstanceId.getInstance().getToken();
        // FirebaseApp.initializeApp(this);
        // String token = String.valueOf(FirebaseMessaging.getInstance().getToken());
        //String token1 = FirebaseMessaging.getInstance();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        String token = task.getResult();
                        Log.d("TAG....", token);
                    }
                });
        // Log.i("sdsadssadas", "FCM Registration Token: " + token +"...\n \n \n"+token0);
    }*/
}