package com.app.flipprteachear.LoginRegistration

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.app.flipprteachear.LoginRegistration.pojo.PojoULoginList
import com.app.flipprteachear.R
import com.app.flipprteachear.home.view.activity.HomeActivity
import com.app.flipprteachear.retroFitClasses.apiCalls
import com.app.flipprteachear.utillsa.PreferenceManager
import com.app.flipprteachear.LoginRegistration.pojo.PojoAccountLinked.PojoAccountLinked
import com.app.flipprteachear.databinding.ActivityLinkedAccount2Binding
import com.mukesh.OnOtpCompletionListener
import com.mukesh.OtpView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LinkedAccount_Activity : AppCompatActivity() {
    private var pref: SharedPreferences? = null
    private var token=""
    lateinit var binding: ActivityLinkedAccount2Binding
    lateinit var pojoAccountLinked: PojoAccountLinked
    private var preferenceManager: PreferenceManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            /*        ButterKnife.bind(this);
            setContentView(R.layout.activity_linked_account);*/
            binding =
                DataBindingUtil.setContentView(this, R.layout.activity_linked_account2)
            pref = getSharedPreferences("flipprLogins", MODE_PRIVATE)

        preferenceManager = PreferenceManager(applicationContext)
            binding.rvLinkedAccount.layoutManager = LinearLayoutManager(this)


        aacountLinkedApi();
       // getFireBaseToken();
            binding.ivBack.setOnClickListener {
                startActivity(Intent(this@LinkedAccount_Activity, LoginActivity::class.java))
                //signUp_Api();
                finishAffinity()
            }
            binding.tvAddAccount.setOnClickListener {
                startActivity(Intent(this@LinkedAccount_Activity, SignUpActivity::class.java))
                //signUp_Api();
                // finish();
            }
//        if(pojoAccountLinked!=null)
//        val accountLinkedAdapter = accountLinkedAdapter(pojoAccountLinked)
//        binding.rvLinkedAccount.adapter = accountLinkedAdapter
        }

          private fun aacountLinkedApi() {
              var mobile = preferenceManager!!.getValueString("mobile")
            binding.tvNo.setText(mobile);
            var api =  apiCalls();

              val callApi: retrofit2.Call<PojoAccountLinked> = api.user_account_linked(mobile, "2")
              callApi.enqueue(object : Callback<PojoAccountLinked?> {
                  override fun onResponse(
                      call: retrofit2.Call<PojoAccountLinked?>,
                      response: Response<PojoAccountLinked?>
                  ) {
                      if (response.isSuccessful) {
                          assert(response.body() != null)
                          if (response.body()!!.detail == null) {
                               binding.progressBar.setVisibility(
                                  View.GONE
                              )
                              binding.tvNodata.setVisibility(
                                  View.VISIBLE
                              )
                             binding.rvLinkedAccount.setVisibility(
                                  View.GONE
                              )
                          } else {
                              val pojoAccountLinked = response.body()
                               binding.tvNodata.setVisibility(
                                  View.GONE
                              )
                              binding.progressBar.setVisibility(
                                  View.GONE
                              )
                              val accountLinkedAdapter = accountLinkedAdapter(pojoAccountLinked!!)
                              binding.rvLinkedAccount.setAdapter(
                                  accountLinkedAdapter
                              )
                          }
                      }
                  }

                  override fun onFailure(call: retrofit2.Call<PojoAccountLinked?>, t: Throwable) {
                       binding.progressBar.setVisibility(
                          View.GONE
                      )
                      Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
                  }
              })

          }
    fun loginApi(username: String?, pass: String?) {

/*        Toast.makeText(getApplicationContext(),"Hello"+pass,Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),token,Toast.LENGTH_LONG).show();*/
        val mobile = preferenceManager!!.getValueString("mobile")
        val api = apiCalls()
        val callApi: Call<PojoULoginList> =
            api.u_loginAPi(username, pass, "" + token, mobile, "2", "android")
        callApi.enqueue(object : Callback<PojoULoginList> {
            override fun onResponse(
                call: Call<PojoULoginList>,
                response: Response<PojoULoginList>
            ) {
                 binding.progressBar.setVisibility(
                    View.GONE
                )
                if (response.isSuccessful()) {
                    val pojoLogin: PojoULoginList? = response.body()
                    if (response.body()!!.getDetail() != null) {
                        try {
                            preferenceManager!!.putValueString("userId", pojoLogin!!.getDetail().get(0).getUserId())
                            preferenceManager!!.putValueString("username", pojoLogin.getDetail().get(0).getUsername())
                            preferenceManager!!.putValueString("Name_", pojoLogin.getDetail().get(0).getFirstName())
                            preferenceManager!!.putValueString("token", pojoLogin.getDetail().get(0).getToken())
                            preferenceManager!!.putValueString("parentCode", pojoLogin.getDetail().get(0).parentCode)
                            preferenceManager!!.putValueString("user_image", pojoLogin.getDetail().get(0).image)
                            Log.d("AuthorisationTokenis", pojoLogin.getDetail().get(0).getToken())

                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                        }

//
                        // Log.e("ashucatchchk294","student_id:- "+pref.getString("student_id","")+"..."+pref.getString("class_id","")+"..."+pref.getString("username",""));
                        try {
                            startActivity(Intent(this@LinkedAccount_Activity, HomeActivity::class.java))
                            finish()
                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                        }

                        //  Toast.makeText(getApplicationContext(),""+pojoLogin.getMessage(),Toast.LENGTH_LONG).show();
                    } else {
                        binding.progressBar.setVisibility(View.GONE)
                        Toast.makeText(applicationContext, "Wrong Pin", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<PojoULoginList>, t: Throwable) {
                binding.progressBar.setVisibility(View.GONE)
                // if(t.getMessage().equals("You have entered wrong password pin."))
                Toast.makeText(applicationContext, "Entered pin is not matched", Toast.LENGTH_LONG).show()
            }
        })
    }
        internal inner class accountLinkedAdapter(var pojoAccountLinked: PojoAccountLinked) :
            RecyclerView.Adapter<accountLinkedAdapter.holder>() {
            var itemStateArray = SparseBooleanArray()
            override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): holder {
                val view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.layout_account_linked, viewGroup, false)
                return holder(view)
            }

            override fun onBindViewHolder(holder: holder, position: Int) {
                holder.tv_username.text = pojoAccountLinked.detail[position].username + " "
                try {
//                    Glide.with(applicationContext)
//                        .load(pojoAccountLinked.detail[position].image)
//                        .placeholder(R.drawable.image)
//                        .into(holder.iv_userprofile)
                    holder.iv_userprofile.load(pojoAccountLinked.detail[position].image){
                        crossfade(true)
                        placeholder(R.drawable.image)
                        transformations()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                holder.tv_edit.setOnClickListener {
                    val intent = Intent(this@LinkedAccount_Activity, ForgotPasswordActivity::class.java)
                    intent.putExtra("user_id", pojoAccountLinked.detail[position].userId)
                    startActivity(intent)
                }
                holder.ll_main.setOnClickListener {
                    if (itemStateArray[position, false]) {
                        holder.ll_enterpin.visibility = View.GONE
                        itemStateArray.put(position, false)
                    } else {
                        holder.ll_enterpin.visibility = View.VISIBLE
                        itemStateArray.put(position, true)
                    }
                    /*holder.ll_enterpin.setVisibility(View.VISIBLE);*/
                }

                holder.otp_view.setOtpCompletionListener(OnOtpCompletionListener { otp ->
                    if(otp.length>3) {
                        binding.progressBar.visibility = View.VISIBLE
                        loginApi(pojoAccountLinked.detail[position].username, otp)
                        val imm =
                            applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(binding!!.root.windowToken, 0)
                    }
                })

            }

            override fun getItemCount(): Int {
                return pojoAccountLinked.detail.size
            }

            inner class holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                var iv_userprofile: ImageView
                var tv_username: TextView
                var tv_edit: TextView
                var ll_main: LinearLayout
                var ll_enterpin: LinearLayout
                var otp_view: OtpView

                /*EditText otp_edit_box1;
                EditText otp_edit_box2;
                EditText otp_edit_box3;
                EditText otp_edit_box4;*/
                var cardView: CardView? = null

                init {
                    iv_userprofile = itemView.findViewById(R.id.iv_userprofile)
                    tv_username = itemView.findViewById(R.id.tv_username)
                    tv_edit = itemView.findViewById(R.id.tv_edit)
                    ll_main = itemView.findViewById(R.id.ll_main)
                    ll_enterpin = itemView.findViewById(R.id.ll_enterpin)
                    otp_view = itemView.findViewById(R.id.otp_view)
                    /* otp_edit_box1 = itemView.findViewById(R.id.otp_edit_box1);
                    otp_edit_box2 = itemView.findViewById(R.id.otp_edit_box2);
                    otp_edit_box3 = itemView.findViewById(R.id.otp_edit_box3);
                    otp_edit_box4 = itemView.findViewById(R.id.otp_edit_box4);*/
                }
            }
        }


}