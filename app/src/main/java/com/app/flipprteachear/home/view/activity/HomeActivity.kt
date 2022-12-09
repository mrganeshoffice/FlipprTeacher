package com.app.flipprteachear.home.view.activity

import android.app.Activity
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.flipprteachear.LoginRegistration.LinkedAccount_Activity
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.ActivityMain1Binding
import com.app.flipprteachear.home.view.fragments.HomeFragment
import com.app.flipprteachear.home.ForStartLive
import com.app.flipprteachear.home.daofile.DetailDao
import com.app.flipprteachear.home.databasefile.DatabaseBuilder
import com.app.flipprteachear.home.pojo.userDetail.PojoUserDetail
import com.app.flipprteachear.home.view.fragments.*
import com.app.flipprteachear.home.view.viewModel.MainViewModel
import com.app.flipprteachear.retroFitClasses.apiCalls
import com.app.flipprteachear.utillsa.Constants_All.liveCode_c
import com.app.flipprteachear.utillsa.PreferenceManager
import com.app.flipprteachear.utillsa.utills
import com.app.flipprteachear.utillsa.utills.Companion.replacefrag_noBackStack
import com.app.flipprteachear.utillsa.utills.Companion.replacefrag_withBackStack
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main1.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

class HomeActivity : AppCompatActivity(), ForStartLive, AllMcq_Fragment.OnDiagramLoadFrag{
    lateinit var viewBinding: ActivityMain1Binding
    lateinit var viewModel: MainViewModel
    private var sidebararray: ArrayList<String>? = null
    private var shortAnimationDuration = 0
     var pref: PreferenceManager?=null
    private val RC_APP_UPDATE = 11
    private var mAppUpdateManager : AppUpdateManager?=null;
    var installStateUpdatedListener: InstallStateUpdatedListener? = null
    var dBuilder: DetailDao?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main1)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewBinding.llSideBar.visibility = View.GONE
        dBuilder = DatabaseBuilder.getInstance(this).DetailDao()
        homepage()
        pref = PreferenceManager(this@HomeActivity)
        with(viewBinding){
             //getAnsList()
            shortAnimationDuration = resources.getInteger(
                android.R.integer.config_shortAnimTime
            )
            llSideBar.visibility = View.GONE
            sidebararray = ArrayList<String>()
            sidebararray!!.add("Home")
            sidebararray!!.add("Add Class Subject")
            sidebararray!!.add("Logout")

            val ft =  supportFragmentManager!!.beginTransaction()
            rv_profile.layoutManager =
                LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

            val  profile = sideAdapter(sidebararray!!, viewBinding, this@HomeActivity, ft, pref!!,
                dBuilder!!
            )

            rvProfile.setAdapter(profile)
            rlProfile.setOnClickListener {
                if ( llSideBar.getVisibility() == View.VISIBLE) {
                    llSideBar.setVisibility(View.GONE)
                } else {
                    llSideBar.setVisibility(View.VISIBLE)
                }
            }

            llChallange.setOnClickListener {
                homepage();
            }
            mainRelSideBar.setOnClickListener { v: View? ->
                llSideBar.setVisibility(View.GONE)
            }
            Log.e("TAG", "onCreate: ${pref!!.getValueString("user_image")}")
            try {
                if( pref!!.getValueString("user_image") != ""){
                    ivUserImage.load(   pref!!.getValueString("user_image")){
                        // crossfade(true)
                        placeholder(R.drawable.ic_user)
                        // transformations()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }

            try{

                val imageUriString = pref!!.getValueString("user_image1")

                val imageUri: Uri = Uri.parse(imageUriString)
                var bitmap: Bitmap? = null
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                viewBinding.ivUserImage.setImageBitmap(bitmap)

            }catch (e:Exception){
                e.printStackTrace()
            }

//            if(pref!!.getValueString("user_image").isNullOrEmpty())
//                Glide.with(this@HomeActivity).load(  "").placeholder(R.drawable.ic_user) .into(ivUserImage)
               // ivUserImage.setImageBitmap(pref!!.getValueString("user_image1").toBima)

            /* else
                 ivUserImage.load(   pref!!.getValueString("user_image1")){
                // crossfade(true)
                placeholder(R.drawable.ic_user)
                // transformations()
            }*/

        }

    }

    private fun getAnsList() {
        var sttAnsList = "2889=57916/2890=57920/2891=57926/2892=57931"
        var s1 =sttAnsList .replace("/",",").replace("=",",")
        var words = s1.split(",")
        var optionsList= arrayListOf<String>()
        var ansList= arrayListOf<String>()

        for(i in 0 until words.size){
            if(i%2==1)
                ansList.add(words[i])
            else  optionsList.add(words[i])

        }

        Log.e("TAG", "getAnsList: $sttAnsList...$s1, $words...$optionsList...$ansList")

    }

    fun homepage() {
        val ft = supportFragmentManager.beginTransaction()
        replacefrag_withBackStack(ft, HomeFragment(), Bundle(),"home" );
        // replacefrag_withBackStack(ft, LiveFragment(), Bundle(),"home" )
       // replacefrag_withBackStack(ft, TopicListFragment(), Bundle(),"home" )
       // replacefrag_withBackStack(ft, EndLive1Fragment(), Bundle(),"home" )
         // replacefrag_withBackStack(ft, Mcq2Fragment(), Bundle(),"home" )
        //getStudentProfile();
    }

     fun hide_Visible_TopBotomBar(tru: Boolean){
       with(viewBinding){
        if(tru){
            rlHeader.visibility =View.GONE
            llBottom.visibility =View.GONE
        }else {
            rlHeader.visibility =View.VISIBLE
            llBottom.visibility =View.VISIBLE
        }
        }
    }

    override fun onBackPressed() {
        try {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.framehoome)
            if (currentFragment?.javaClass == LiveFragment().javaClass) {
                FirebaseFirestore.getInstance().collection(getString(R.string.collectionName)).document(liveCode_c).delete()
                super.onBackPressed()
            }else   if (currentFragment?.javaClass == AllMcq_Fragment().javaClass) {

            }
           /* else   if (currentFragment?.javaClass == TopicListFragment().javaClass){
                homepage()
            }*/
//            else   if (currentFragment?.javaClass == HomeFragment().javaClass){
////                homepage()
//
//            }
            else
            {
                super.onBackPressed()
            }
            hide_Visible_TopBotomBar(false)
        }catch (ex :Exception){ (ex.printStackTrace())}
    }

    fun testCheck() {

    }


    override fun startLive(s: String, bundle: Bundle) {
        with(viewBinding) {
            rlHeader.visibility = View.GONE
            llBottom.visibility = View.GONE
        }
        val ft =  supportFragmentManager!!.beginTransaction()
        if (s.equals("from_LiveFrag")) {
           // utills.replacefrag_withBackStack(ft, Mcq2Fragment(), bundle, "mcq2")
            utills.replacefrag_withBackStack(ft,
                AllMcq_Fragment(), bundle, "mcq2")
        }else{
            rlHeader.visibility = View.VISIBLE
            Log.d("Hello","ProfileFragment")
            replacefrag_withBackStack(ft, StudentProfileFragment(), bundle,"home")
        }
}

    override fun diagramLoad() {
        val fragment: Fragment =
            AllMcq_Fragment()
        val bundle = Bundle()
        val ft = supportFragmentManager.beginTransaction()
        utills.replacefrag_noBackStack(ft, fragment, bundle, "mcq_allChapter")
        try {
             viewBinding.rlHeader.setVisibility(View.GONE)
            viewBinding.llBottom.setVisibility(View.GONE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun isStreakInFo_orMcq(b: Boolean) {

    }

    public fun onEndlive_Back(){
        hide_Visible_TopBotomBar(false)
        super.onBackPressed()
    }


   public class sideAdapter(
       var array: ArrayList<String>,
       var viewBinding: ActivityMain1Binding,
       var context: Activity?,
       var ft: FragmentTransaction,
       var pref: PreferenceManager,
       var dBuilder: DetailDao
   ) :
        RecyclerView.Adapter<sideAdapter.custom>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.res_sidebar, parent, false)
            return custom(view)
        }

        override fun onBindViewHolder(holder: custom, position: Int) {
            holder.tv_name.text = array[position]
            if (position == 0) {
                /*              holder.tv_badge.setVisibility(View.VISIBLE);*/
            }
            if (position + 1 == array.size) {
                holder.view.visibility = View.GONE
            }
            holder.llview.setOnClickListener(null)
            holder.llview.setOnClickListener {

                if (position == 0) {
                    viewBinding. llSideBar.setVisibility(View.GONE)
                    viewBinding.llBottom.visibility = View.VISIBLE
                    replacefrag_withBackStack(ft, HomeFragment(),Bundle(),"HomeFrag")
                }
                else if (position == 1) {
                    viewBinding. llSideBar.setVisibility(View.GONE)
                    viewBinding.llBottom.visibility = View.VISIBLE
                    var fragmentManager = (it.context as FragmentActivity).supportFragmentManager

                    replacefrag_withBackStack(fragmentManager.beginTransaction(), AddClassSubject(),Bundle(),"HomeFrag")

                }else if (position == 2) {
                    logout()
                }

            }
        }

        override fun getItemCount(): Int {
            return array.size
        }

        fun logout() {
           val hashMap = java.util.HashMap<String, String>()
           hashMap["user_id"] =   pref!!.getValueString("userId")
           hashMap["device_token"] = "123"
           viewBinding.rlLoader.setVisibility(View.VISIBLE)
           val api = apiCalls() //getArguments().getString("topicId")
           val callApi: Call<PojoUserDetail> = api.get_logout(pref!!.getValueString("token"), hashMap)
           callApi.enqueue(object : Callback<PojoUserDetail?> {
               override fun onResponse(call: Call<PojoUserDetail?>, response: Response<PojoUserDetail?>
               ) {
                   viewBinding. rlLoader.setVisibility(View.GONE)
                   if (response.isSuccessful()) {
                       if (response.body() != null) {

                           CoroutineScope(Dispatchers.IO).launch {
                               dBuilder.deleteAllData()
                           }

                           Toast.makeText(context, "Logout Successfully", Toast.LENGTH_SHORT).show()
                           val mobile: String = pref!!.getValueString("mobile")
                           pref!!.sharedPreferences.edit().clear().apply()
                           pref!!.putValueString("mobile", mobile)

                           context!!.startActivity( Intent(context!!, LinkedAccount_Activity::class.java))
                          context!!. overridePendingTransition(R.animator.enter_from_right, R.animator.exit_to_left)
                           context!!.finish()
                       }
                   }
               }

               override fun onFailure(call: Call<PojoUserDetail?>, t: Throwable) {
                   Log.e("response:---", "" + t.toString())
                   viewBinding.rlLoader.setVisibility(View.GONE)
               }
           })
       }

          class custom(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tv_name: TextView
            var tv_badge: TextView
            var llview: LinearLayout
            var view: View

            init {
                view = itemView.findViewById(R.id.view)
                llview = itemView.findViewById(R.id.llview)
                tv_name = itemView.findViewById(R.id.tv_name)
                tv_badge = itemView.findViewById(R.id.tv_badge)
                tv_badge.visibility = View.GONE
            }
        }
    }


    private fun getCheckUpDatedCode_onPlayStore() {
        mAppUpdateManager = AppUpdateManagerFactory.create(this)
        mAppUpdateManager!!.registerListener(installStateUpdatedListener_())
        mAppUpdateManager!!.getAppUpdateInfo().addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() === UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE /*AppUpdateType.IMMEDIATE*/)
            ) {
                try {
                    mAppUpdateManager!!.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.FLEXIBLE /*AppUpdateType.IMMEDIATE*/,
                        this@HomeActivity,
                         RC_APP_UPDATE
                    )
                } catch (e: SendIntentException) {
                    e.printStackTrace()
                }
            } else if (appUpdateInfo.installStatus() === InstallStatus.DOWNLOADED) {
                //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                popupSnackbarForCompleteUpdate()
            } else {
                Log.e("TAG", "checkForAppUpdateAvailability: something else")
            }
        }
    }

    private fun installStateUpdatedListener_(): InstallStateUpdatedListener? {
        installStateUpdatedListener = object : InstallStateUpdatedListener {
           override fun onStateUpdate(state: InstallState) {
                if (state.installStatus() === InstallStatus.DOWNLOADED) {
                    //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                    popupSnackbarForCompleteUpdate()
                } else if (state.installStatus() === InstallStatus.INSTALLED) {
                    if (mAppUpdateManager != null) {
                        mAppUpdateManager!!.unregisterListener(installStateUpdatedListener)
                    }
                } else {
                    Log.i("tag", "InstallStateUpdatedListener: state: " + state.installStatus())
                }
            }
        }
        return installStateUpdatedListener
    }

    private fun popupSnackbarForCompleteUpdate() {
        val snackbar = Snackbar.make(
            findViewById(R.id.relMain),
            "New app is ready!",
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction("Install") { view: View? ->
            if (mAppUpdateManager != null) {
                mAppUpdateManager!!.completeUpdate()
            }
        }
        try {
            snackbar.setActionTextColor(resources.getColor(R.color.navicolor))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        snackbar.show()
    }

    override fun onStop() {
        super.onStop()
        try {
            if (mAppUpdateManager != null) if (installStateUpdatedListener != null) {
                mAppUpdateManager!!.unregisterListener(installStateUpdatedListener_())
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

   /* override fun onStart() {
        super.onStart()
        getCheckUpDatedCode_onPlayStore()
       // Log.e("TAG", "onResume:onStart:HH ", )
    }
    override fun onResume() {
        super.onResume()
        Log.e("TAG", "onResume:HH ", )
    }*/

}