package com.app.flipprteachear.LoginRegistration

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.flipprteachear.LoginRegistration.adapter.Grade_SubjectAdapter
import com.app.flipprteachear.LoginRegistration.pojo.PojoSignUp
import com.app.flipprteachear.LoginRegistration.pojo.generateCode.PojoGenrateCode
import com.app.flipprteachear.LoginRegistration.pojo.pojoStudentCode.PojoClass_Subject
import com.app.flipprteachear.LoginRegistration.pojo.pojoStudentCode.PojoStudentCode
import com.app.flipprteachear.LoginRegistration.pojo.pojoStudentCode.Subject
import com.app.flipprteachear.LoginRegistration.pojo.signUp.AddedClasse
import com.app.flipprteachear.LoginRegistration.pojo.signUp.ModelSignUpBody
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.ActivitySignUp2Binding
import com.app.flipprteachear.home.view.activity.HomeActivity
import com.app.flipprteachear.retroFitClasses.apiCalls
import com.app.flipprteachear.utillsa.PreferenceManager
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.snackbar.Snackbar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.mukesh.OnOtpCompletionListener
import kotlinx.android.synthetic.main.activity_sign_up2.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException

class SignUpActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivitySignUp2Binding
       var userName = "n/a"
       var  pictureFilePath = "N/A"
    private var preferenceManager: PreferenceManager?=null
    var accountPin1 = ""
    var accountPin = ""
    var PICK_IMAGE_REQUEST = 9
    private var mImageUri: Uri? = null
    var list = arrayListOf<AddedClasse>()
    private var gradeList = arrayListOf<GradeSubjectModel>()
    private var pojoclassSubject: PojoClass_Subject?=null
    private var pojoStudentCode: PojoStudentCode?=null

    private val gradeSubjectAdapter by lazy{
        gradeList.clear()
        Grade_SubjectAdapter(gradeList)
    }

    private fun spGradeAdapter(pojoclassSubject: PojoClass_Subject?):ArrayAdapter<String>{
      val arrayList = arrayListOf<String>()
        for(i in pojoclassSubject!!.classes.indices){
            arrayList.add(pojoclassSubject.classes[i].class_name)
        }
     return ArrayAdapter(this,R.layout.simple_spinner_dropdown_item,arrayList)
    }
    private fun spSubjectAdapter(subjects: List<Subject>):ArrayAdapter<String>{
        val arrayList = arrayListOf<String>()
        for(i in subjects.indices){
            arrayList.add(subjects[i].name)
        }
        return ArrayAdapter(this,R.layout.simple_spinner_dropdown_item,arrayList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       viewBinding = DataBindingUtil. setContentView(this, R.layout.activity_sign_up2)
        preferenceManager = PreferenceManager(applicationContext)

        with(viewBinding){
            otpView.setOtpCompletionListener(OnOtpCompletionListener { otp ->
                accountPin = otp
                Log.e("rajat.....", "pictureFilePath: otp")
                if(otp.length>4) checkStudentCode(otp)
            })
            otpViewPassword.setOtpCompletionListener(OnOtpCompletionListener { otp ->
                accountPin1 = otp

            })
            ivChangeimage.setOnClickListener(View.OnClickListener { documents() })

            lldone.setOnClickListener{ validations() }
            rvGradeSubject.adapter = gradeSubjectAdapter
            ivAddSubject.setOnClickListener {
                var grade =""
                var subject =""
                var subject_id =""
                Log.d("HelloGrade",gradeList.size.toString());
                var flag = false




                try {
                    pojoclassSubject!!.classes[spinnerGrade.selectedItemPosition].let {
                        grade = it.class_name
                        subject = it.subjects[spinnerSubject.selectedItemPosition].name
                        subject_id = it.subjects[spinnerSubject.selectedItemPosition].subject_id

                        if(gradeList.size > 0){
                            var flag = false
                            for (i in 0 until gradeList.size){
                                if((gradeList[i].grade.trim() == grade.trim()) && (gradeList[i].subject_id.trim() == subject_id.trim())  ){
                                    flag = true
                                    break
                                }
                            }
                            if(!flag){
                                gradeList.add(GradeSubjectModel("$grade","$subject","${it.class_id}",
                                    "${it.course_id}","$subject_id","${pojoStudentCode!!.details?.user_id}","${it.school_class_course_id}"))
                                gradeSubjectAdapter.updateList(gradeList)
                            }else{
                                Toast.makeText(this@SignUpActivity,"Please select new grade and subject",Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            gradeList.add(GradeSubjectModel("$grade","$subject","${it.class_id}",
                                "${it.course_id}","$subject_id","${pojoStudentCode!!.details?.user_id}","${it.school_class_course_id}"))
                            gradeSubjectAdapter.updateList(gradeList)
                        }




                    }
                      }catch (e:Exception){e.printStackTrace()}


            }

            spinnerGrade.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                  if(pojoclassSubject!=null) if(pojoclassSubject!!.classes!=null)
                      spinnerSubject.adapter = spSubjectAdapter(pojoclassSubject!!.classes[position].subjects)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
            viewBinding.tvAlreadyAcount.setOnClickListener {
                super.onBackPressed()
                finish()
            }
            getParentCode()
        }
    }

    private fun validations() {
        var parentCode =""
        try {
            parentCode = pojoStudentCode!!.details?.parent_code!!
        }catch (e:Exception){e.printStackTrace()}

       list.clear()
        try {
           for(i in gradeList.indices){
               list.add(AddedClasse(
                   "${gradeList[i].classId}","${gradeList[i].course_id}",
                   "${gradeList[i].school_class_course_id}",
                   "${gradeList[i].school_id}", "${gradeList[i].subject_id}"
               ))
           }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            parentCode = pojoStudentCode!!.details!!.parent_code!!
        }catch (e:Exception){e.printStackTrace()}

        with(viewBinding) {
            Log.e("rajat.....", "pictureFilePath: $pictureFilePath")
            Log.e("rajat.....", "name..: ${etFirstName.getText().toString()}")

            etFirstName.setError(null)
            etLastName.setError(null)

            if (etFirstName.getText().toString().isNullOrEmpty()) {
            etFirstName.setError("")
            etFirstName.requestFocus()
                Toast.makeText(this@SignUpActivity, "First name field is empty", Toast.LENGTH_SHORT).show()
            }else if (etUserName.getText().toString().isNullOrEmpty()) {
                etUserName.setError("")
                etUserName.requestFocus()
                Toast.makeText(this@SignUpActivity, "Username name field is empty", Toast.LENGTH_SHORT).show()
            } else if (accountPin.length <= 3) {
                Toast.makeText(this@SignUpActivity, "Please enter valid pasword", Toast.LENGTH_SHORT).show()
            } else if (pictureFilePath.isEmpty() || pictureFilePath.equals("N/A", ignoreCase = true)) {
                Toast.makeText(this@SignUpActivity, "Please upload profile picture", Toast.LENGTH_SHORT).show()
            }
            else  if (parentCode.isNullOrEmpty()) {
                otpView.requestFocus()
                Toast.makeText(this@SignUpActivity, "Please enter school code", Toast.LENGTH_SHORT).show()
            } else  if (gradeList.isNullOrEmpty()) {

                Toast.makeText(this@SignUpActivity, "Please select grade and subject", Toast.LENGTH_SHORT).show()
            }  else {

                updateProfile(parentCode)
//                if (userName.equals("1", ignoreCase = true) /*&& classCode.equalsIgnoreCase("1")*/) {
//                    updateProfile(classCode)
//                } else {
//                    Toast.makeText(this@SignUpActivity, " Enter an valid UserName", Toast.LENGTH_SHORT).show()
//                }
            }
        }
    }

    private fun updateProfile(parentCode: String) {
        Log.e("rajat.....", "pictureFilePath: $pictureFilePath")
        val photo: File = File(pictureFilePath)
        var modelSignUp = ModelSignUpBody(
            "2", list ,"",
            "android","","","",
            "${viewBinding.etFirstName.getText()}",
            "${viewBinding.spSection.selectedItem}",
            "${viewBinding.etLastName.getText()}",
            "${preferenceManager!!.getValueString("mobile" )}",
            "${viewBinding.tvAccountCode.getText()}",
            "${pojoStudentCode!!.details!!.parent_id}",
            "$accountPin1",
            "${viewBinding.etUserName.getText() }"
        )
        viewBinding.progressbar.setVisibility(View.VISIBLE)
        val api = apiCalls()
        val callApi: Call<PojoSignUp> =
            api.register_SignUp(preferenceManager!!.getValueString("token" ), modelSignUp)
        callApi.enqueue(object : Callback<PojoSignUp?> {
            override fun onResponse(call: Call<PojoSignUp?>, response: Response<PojoSignUp?>) {
                viewBinding.progressbar.setVisibility(View.GONE)
                if (response.body() != null) {
                    if (!response.body()!!.detail.isNullOrEmpty()) {
                        try {
                            val pojoSignUp: PojoSignUp? = response.body()
                            preferenceManager!!.putValueString("userId", pojoSignUp!!.getDetail().get(0).getUserId())
                            preferenceManager!!.putValueString("username", pojoSignUp.getDetail().get(0).getUsername())
                            preferenceManager!!.putValueString("studentName", pojoSignUp.getDetail().get(0).getFirstName())
                            preferenceManager!!.putValueString("token", pojoSignUp.getDetail().get(0).getToken())
                            preferenceManager!!.putValueString("parentCode", pojoSignUp.getDetail().get(0).parentCode)
                            preferenceManager!!.putValueString("user_image", pojoSignUp.getDetail().get(0).image)
                        } catch (e: java.lang.Exception) {
                            Log.e("ashucatchchk294", "catch:$e")
                        }
                        try {
                            imageUser()
                        } catch (e: java.lang.Exception) {
                            Log.e("ashucatchchk294", "catch:$e")
                        }
                    }
                }
            }
            override fun onFailure(call: Call<PojoSignUp?>, t: Throwable) {
                viewBinding.progressbar.setVisibility(View.GONE)
                Log.e("onFailure", "PojoAllChapterList: $t")
            }
        })
    }
    private fun imageUser(){
        val photo: File = File(pictureFilePath)
       val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("student_id", preferenceManager!!.getValueString("userId" ))
            .addFormDataPart("filename", photo.getName(), RequestBody.create(MediaType.parse("image/*"), photo))
            .build();
        viewBinding.progressbar.setVisibility(View.VISIBLE)
        val api = apiCalls()
        // if (binding.nameField.getText().toString() != null && binding.groupMemberField.getText().toString() != null && binding.descriptionField.getText().toString() != null) {
        val callApi: Call<PojoSignUp> =
            api.register_ImageUser(preferenceManager!!.getValueString("token" ), requestBody)
        callApi.enqueue(object : Callback<PojoSignUp?> {
            override fun onResponse(call: Call<PojoSignUp?>, response: Response<PojoSignUp?>) {
                viewBinding.progressbar.setVisibility(View.GONE)
                if (response.body() != null) {
                    if (!response.body()!!.detail.isNullOrEmpty()) {

                    }
                    try {
                        startActivity(Intent(this@SignUpActivity, HomeActivity::class.java))
                        finish()
                    } catch (e: java.lang.Exception) {
                        Log.e("ashucatchchk294", "catch:$e")
                    }
                }
            }

            override fun onFailure(call: Call<PojoSignUp?>, t: Throwable) {
                viewBinding.progressbar.setVisibility(View.GONE)
                Log.e("onFailure", "PojoAllChapterList: $t")
            }
        })
    }

    private fun getParentCode(){

        viewBinding.progressbar.setVisibility(View.VISIBLE)
        val api = apiCalls()
        // if (binding.nameField.getText().toString() != null && binding.groupMemberField.getText().toString() != null && binding.descriptionField.getText().toString() != null) {
        val callApi: Call<PojoGenrateCode> = api.getParentCode()
        callApi.enqueue(object : Callback<PojoGenrateCode> {
            override fun onResponse(call: Call<PojoGenrateCode>, response: Response<PojoGenrateCode>) {
                viewBinding.progressbar.setVisibility(View.GONE)
                if (response.body() != null) {
                    try {
                        if(response.body()!!.detail !=null || !response.body()!!.detail.isEmpty())
                            tv_accountCode.setText(response.body()!!.detail[0].code)
                    } catch (e: java.lang.Exception) {
                        Log.e("ashucatchchk294", "catch:$e")
                    }
                }
            }

            override fun onFailure(call: Call<PojoGenrateCode>, t: Throwable) {
                viewBinding.progressbar.setVisibility(View.GONE)
                Log.e("onFailure", "PojoAllChapterList: $t")
            }
        })
    }

    fun checkStudentCode(code: String) {
        viewBinding.progressbar.setVisibility(View.VISIBLE)
        viewBinding.tvSchoolName.setVisibility(View.GONE)
        val api = apiCalls()
        val callApi: Call<PojoStudentCode> = api.checkStudentCode("", "$code", "4")
        callApi.enqueue(object : Callback<PojoStudentCode> {
            override fun onResponse(call: Call<PojoStudentCode>, response: Response<PojoStudentCode>
            ) {
                viewBinding.progressbar.setVisibility(View.GONE)
                try {
                    if(response.isSuccessful)
                    if(response.body()!=null)
                    {    pojoStudentCode =response.body()
                        viewBinding.tvSchoolName.setVisibility(View.VISIBLE)
                        viewBinding.tvSchoolName.text =response.body()!!.details?.first_name
                        school_classes_Subject(response.body()!!.details?.user_id!!)

                    }

                } catch (e: Exception) {
                    Log.e("catch_exception", "catch_login:$e")
                    viewBinding.progressbar.setVisibility(View.GONE)

                }
            }

            override fun onFailure(call: Call<PojoStudentCode>, t: Throwable) {
                viewBinding.progressbar.setVisibility(View.GONE)
                Snackbar.make(viewBinding.relTitleLayout, "Something went wrong, try again later.", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    fun school_classes_Subject(school_id: String) {
        viewBinding.progressbar.setVisibility(View.VISIBLE)
        val api = apiCalls()
        val callApi: Call<PojoClass_Subject> = api.school_classes_SubjectApi("", school_id)
        callApi.enqueue(object : Callback<PojoClass_Subject> {
            override fun onResponse(
                call: Call<PojoClass_Subject>,
                response: Response<PojoClass_Subject>
            ) {
                viewBinding.progressbar.setVisibility(View.GONE)
                try {
                    pojoclassSubject = response.body()
                    with(viewBinding){
                        spinnerGrade.adapter =spGradeAdapter(pojoclassSubject)
                        if(pojoclassSubject!=null) if(pojoclassSubject!!.classes!=null || !pojoclassSubject!!.classes.isEmpty() )
                        spinnerSubject.adapter =spSubjectAdapter(pojoclassSubject!!.classes[0].subjects)
                    }

                } catch (e: Exception) {
                    Log.e("catch_exception", "catch_login:$e")
                    viewBinding.progressbar.setVisibility(View.GONE)
                    Snackbar.make(viewBinding.relTitleLayout, "Something went wrong, try again later.", Snackbar.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PojoClass_Subject>, t: Throwable) {
                viewBinding.progressbar.setVisibility(View.GONE)
                Snackbar.make(viewBinding.relTitleLayout, "Something went wrong, try again later.", Snackbar.LENGTH_SHORT).show()
            }
        })
    }



    fun documents() {
        val dialog = Dialog(this)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.res_document)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val gallery = dialog.findViewById<LinearLayout>(R.id.gallery)
        val camera = dialog.findViewById<LinearLayout>(R.id.camera)
        gallery.setOnClickListener {
            val permissionlistener: PermissionListener = object : PermissionListener {
                override fun onPermissionGranted() {
                    run {
                        val intent = Intent()
                        intent.type = "image/*"
                        intent.action = Intent.ACTION_PICK
                        startActivityForResult(intent, PICK_IMAGE_REQUEST)
                        dialog.dismiss()
                    }
                }

                override  fun onPermissionDenied(deniedPermissions: List<String?>) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Permission Denied\n$deniedPermissions", Toast.LENGTH_SHORT
                    ).show()
                }
            }
            TedPermission.with(this@SignUpActivity)
                .setPermissionListener(permissionlistener)
                .setRationaleConfirmText("Permission")
                .setRationaleTitle("Permission required.")
                .setRationaleMessage("We need this permission to access camera and device storage.")
                .setDeniedTitle("Permission denied")
                .setDeniedMessage("Without this permission,receiver couldn't hear you.\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setGotoSettingButtonText("Settings")
                .setPermissions(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .check()
        }
        camera.setOnClickListener {
            val permissionlistener: PermissionListener = object : PermissionListener {
               override fun onPermissionGranted() {
                 //  with(this).cameraOnly().compress(40).start()
                   ImagePicker. with(this@SignUpActivity).cameraOnly().compress(40).start()
               }

             override   fun onPermissionDenied(deniedPermissions: List<String?>) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Permission Denied\n$deniedPermissions", Toast.LENGTH_SHORT
                    ).show()
                }
            }
            dialog.dismiss()
            TedPermission.with(this@SignUpActivity)
                .setPermissionListener(permissionlistener)
                .setRationaleConfirmText("Permission")
                .setRationaleTitle("Permission required.")
                .setRationaleMessage("We need this permission to access camera and device storage.")
                .setDeniedTitle("Permission denied")
                .setDeniedMessage("Without this permission,receiver couldn't hear you.\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setGotoSettingButtonText("Settings")
                .setPermissions(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .check()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && data != null) {
            mImageUri = data.data
            var bitmap: Bitmap? = null
            try {

                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data.data)

            } catch (e: IOException) {
                e.printStackTrace()
            }
            pictureFilePath = getRealPathFromURI(mImageUri)!!
            viewBinding.image.setImageBitmap(bitmap)

          /*  val stream = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray: ByteArray = stream.toByteArray()*/

            Log.d("HelloUri",mImageUri.toString())

            preferenceManager!!.putValueString("user_image1",mImageUri.toString())

        } else {

               try {
                   // assert(data != null)
                    val fileUri = data!!.data
                   Log.d("HelloUri",fileUri.toString())
                   preferenceManager!!.putValueString("user_image1",fileUri.toString())
                   val imgFile: File = ImagePicker.Companion.getFile(data)!!
                   val bmOptions = BitmapFactory.Options()
                   val bitmap = BitmapFactory.decodeFile(imgFile.absolutePath, bmOptions)
                   pictureFilePath = imgFile.absolutePath

                   viewBinding.image.setImageBitmap(bitmap)
               }catch (e:java.lang.Exception){e.printStackTrace()}
        }
    }
    private fun getRealPathFromURI(contentUri: Uri?): String? {

        // can post image
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = managedQuery(
            contentUri,
            proj,  // Which columns to return
            null,  // WHERE clause; which rows to return (all rows)
            null,  // WHERE clause selection arguments (none)
            null
        ) // Order-by clause (ascending by name)
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }
}