package com.app.flipprteachear.utillsa

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.flipprteachear.R

class utills {

    companion object{

        fun replacefrag_withBackStack(ft: FragmentTransaction, fragment: Fragment, bundle: Bundle?, screenName: String?
        ) {
            ft.replace(R.id.framehoome, fragment)
            fragment.arguments = bundle
              ft.addToBackStack(screenName);
            ft.commit()
        }
        fun replacefrag_noBackStack(ft: FragmentTransaction, fragment: Fragment, bundle: Bundle?, screenName: String?
        ) {
            ft.replace(R.id.framehoome, fragment)
            fragment.arguments = bundle
            // ft.addToBackStack(screenName);
            ft.commit()
        }
        fun convertStrigbuilderCaseStdy(se: List<String?>, s: String?): String? {
            val sb = StringBuilder()
            //int cont = 0;
            for (ss in se) {
                sb.append(ss).append(s)
            }
            return sb.substring(0, sb.length - 1)
        }
    }
}