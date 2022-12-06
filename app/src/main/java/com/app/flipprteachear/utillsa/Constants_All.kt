package com.app.flipprteachear.utillsa

import kotlinx.android.synthetic.main.fragment_mcq2.*

object Constants_All {
    var latetex_dim_15 =15
    var latetex_dim_13:Int = 13
    var latetex_dim_16:Int = 16
    var liveCode_c:String = ""
    var moveChapetDetail = 0

     var youTubeKey="AIzaSyBVPggvHfymhcETOyE_viqMEoWkRhZ_zr4"

     fun getLatexSolvedQ_Str_Solution(ques: String): String? {
        var ques = ques
        println("replaceddd0..$ques...rrr..")
        ques = ques.replace("[Math]", "$")
        ques = ques.replace("[/Math]", "$")
        ques = ques.replace("[math]", "$")
        ques = ques.replace("[/math]", "$")
        ques = ques.replace("\"frac", "\\frac")
        ques = ques.replace("\"", "\\")
       // val textt = "<font color=#FFFFF>$ques</font> "

        //    getYoutTubePalyer();
        return ques
    }
    
}