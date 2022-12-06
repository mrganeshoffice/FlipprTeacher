package com.app.flipprteachear.home.view.adapterMcq

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.ResChoosansRowBinding
import com.app.flipprteachear.home.pojo.liveQuesPojo.AnswerDetail
import com.app.flipprteachear.utillsa.Constants_All
import com.app.flipprteachear.utillsa.Global
import com.bumptech.glide.Glide
import java.util.*
public class QueOptionsAdapter(
    var pojo: List<AnswerDetail>, val type: String, val questiontype: String, var totalRightAns: Int,
    var expandedImage: ImageView, var shortAnimationDuration: Int, var selectedanswer: ArrayList<String>
) : RecyclerView.Adapter<QueOptionsAdapter.custom>() {
    lateinit var context : Context
    private var isRvImageFull = false
    var selectedid = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custom {
        val layoutQuesOption : ResChoosansRowBinding= DataBindingUtil
            .inflate(LayoutInflater.from(parent.context),R.layout.res_choosans_row, parent, false)
        context =parent.context
        return custom(layoutQuesOption)
    }

    override fun onBindViewHolder(holder: custom, position: Int) {

       with(holder.binding){
           tvSereas.text =((position + 65).toChar()).toString()
       formulaOne1.setTextSize(Constants_All.latetex_dim_15)
          // rvImage.adapter = getAdapterAttemptedImages()
           rvImageFull.adapter = getAdapterAttempted_fullImages()
           rvImageFull.visibility = View.GONE
           if (type.equals("question", ignoreCase = true)){
               if (questiontype.contains("Image MCQ")) {
                   cardViewIMage.setVisibility(View.VISIBLE)
                   ivImage.setVisibility(View.VISIBLE)
                   TvText.setVisibility(View.GONE)
                   formulaOne1.setVisibility(View.GONE)
                   Glide.with(context).load(pojo.get(position).question_img).into(ivImage)
                   getLongPressItemImage_queOption(holder, pojo.get(position).question_img, expandedImage)
               } else
               {
                   cardViewIMage.setVisibility(View.GONE)
                   ivImage.setVisibility(View.GONE)
                   //holder.Tv_text.setVisibility(View.VISIBLE);
                   val ans: String = pojo.get(position).right_txt_ans1
                   TvText.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE)
                   //getLatexSolvedQ_Str(ans);
                   formulaOne1.setDisplayText(Constants_All.getLatexSolvedQ_Str_Solution(ans))
               }
               relM.setOnClickListener {
                   isRvImageFull = !isRvImageFull
                   rvImageFull.visibility = if(isRvImageFull)View.VISIBLE else View.GONE
                   Toast.makeText(context, "dsfsdfs", Toast.LENGTH_SHORT).show()

                   //totalRightAns=0;

               }
               Card.setBackground(ContextCompat.getDrawable(context,R.drawable.round_boundary_navi))

               if (pojo[position].types.equals("Right")) {
                   tvSereas.setBackgroundColor(Color.parseColor("#26e67a"))
                   Card.setBackgroundResource(R.drawable.boundary_green)
                   icRight.visibility=View.VISIBLE
                   tvSereas.setTextColor(ContextCompat.getColor(context,R.color.white))
               }
           }else{
               tvSereas.setTextColor(ContextCompat.getColor(context,R.color.white));
                if (questiontype.equals("Image MCQ", ignoreCase = true)) {
                    ivImage.setVisibility(View.VISIBLE);
                    cardViewIMage.setVisibility(View.VISIBLE);
                    TvText.setVisibility(View.GONE);
                     formulaOne1.setVisibility(View.GONE);

                    Glide.with(context).load(pojo.get(position).question_img).into(holder.binding.ivImage);
                    getLongPressItemImage_queOption(holder, pojo.get(position).question_img, expandedImage);
                }
                else {
                    ivImage.setVisibility(View.GONE);
                     cardViewIMage.setVisibility(View.GONE);
                    TvText.setVisibility(View.GONE);
                    var ans = pojo.get(position).right_txt_ans1;
                    TvText.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                    //getLatexSolvedQ_Str(ans);
                    formulaOne1.setDisplayText(Constants_All.getLatexSolvedQ_Str_Solution(ans));
                }
               if (pojo[position].types.equals("Right")) {
                   tvSereas.setBackgroundColor(Color.parseColor("#26e67a"))
                   Card.setBackgroundResource(R.drawable.boundary_green)
                   icRight.visibility=View.VISIBLE
                   tvSereas.setTextColor(ContextCompat.getColor(context,R.color.white))
               }
               tvSereas.setText("" + Char.apply { (position + 65) }  );
           }

       }
    }



//    private fun getAdapterAttemptedImages(): AttemptByAdapter {
//     //var attmptAdapter =AttemptByAdapter()
//        return null//attmptAdapter
//    }
    private fun getAdapterAttempted_fullImages(): AttemptByFull_imagesAdapter {
     var attmptAdapter =AttemptByFull_imagesAdapter()
        return attmptAdapter
    }

    override fun getItemCount(): Int {
        return try {
            if (pojo == null) 0 else pojo.size
        } catch (e: Exception) { 0 }
    }

    private fun getLongPressItemImage_queOption(holder: custom, imageString: String, expandedImage: ImageView
    ) {
        holder.itemView.setOnLongClickListener(OnLongClickListener { v1: View? ->
            expandedImage.setVisibility(View.VISIBLE)
            Global.zoomRecyclerImage(holder.binding.ivImage, imageString, context,
                expandedImage, holder.binding.relM, shortAnimationDuration)
            true
        })
    }

    fun updateListType(sType: Int) {
        //this.type = sType
        notifyDataSetChanged()
    }

    inner class custom(bind: ResChoosansRowBinding) : RecyclerView.ViewHolder(bind.root) {

         var binding: ResChoosansRowBinding
        init {
            binding = bind
        }
    }

    init {
        // this.students = students
       // this.type = type
    }
}