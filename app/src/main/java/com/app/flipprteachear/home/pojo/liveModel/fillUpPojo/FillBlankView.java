package com.app.flipprteachear.home.pojo.liveModel.fillUpPojo;

import android.content.Context;
import android.graphics.drawable.PaintDrawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.flipprteachear.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 填空题
 * Created by yangle on 2017/9/2.
 */

public class FillBlankView extends RelativeLayout {

    private TextView tvContent;
    private Context context;
    private int inputMode =0;
    // 答案集合 Answer set
    private List<String> answerList;
    // 答案范围集合 Answer range set
    private List<AnswerRange> rangeList;
    // 填空题内容 Fill in the blanks
    private SpannableStringBuilder content;

    public FillBlankView(Context context) {
        this(context, null);
    }

    public FillBlankView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FillBlankView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.layout_fill_blank, this);

        tvContent = (TextView) findViewById(R.id.tv_content);
    }

    /**
     * 设置数据  设置数据
     *
     * @param originContent   源数据 source data
     * @param answerRangeList 答案范围集合 Answer range set
     */
    public void setData(String originContent, List<AnswerRange> answerRangeList) {
        if (TextUtils.isEmpty(originContent) || answerRangeList == null
                || answerRangeList.isEmpty()) {
            return;
        }

        // 获取课文内容 Get text content
        content = new SpannableStringBuilder(originContent);
        // 答案范围集合 Answer range set
        rangeList = answerRangeList;

        // 设置下划线颜色 Set the underline color
        for (AnswerRange range : rangeList) {
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.parrotgreen));
            Log.d("RangeListSize", range.start+"----------"+range.end);
            content.setSpan(colorSpan, range.start, range.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        // 答案集合 Answer set
        answerList = new ArrayList<>();
        for (int i = 0; i < rangeList.size(); i++) {
            answerList.add("");
        }

        // 设置填空处点击事件  / Set fill-in click event

        for (int i = 0; i < rangeList.size(); i++) {
            AnswerRange range = rangeList.get(i);
            BlankClickableSpan blankClickableSpan = new BlankClickableSpan(i);
            content.setSpan(blankClickableSpan, range.start, range.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }



        // 设置此方法后，点击事件才能生效 After setting this method, the click event will take effect
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        tvContent.setText(content);
       // tvContent.setFontFeatureSettings(R.font.robotothin);
    }

    /**
     * 点击事件 Click event
     */
    class BlankClickableSpan extends ClickableSpan {

        private int position;

        public BlankClickableSpan(int position) {
            this.position = position;
        }

        @Override
        public void onClick(final View widget) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_input, null);
             EditText etInput = (EditText) view.findViewById(R.id.et_answer);
            Button btnFillBlank = (Button) view.findViewById(R.id.btn_fill_blank);
            inputMode=1;
            // 显示原有答案 Show original answer
            String oldAnswer = answerList.get(position);
            if (!TextUtils.isEmpty(oldAnswer)) {
                etInput.setText(oldAnswer);
                etInput.setSelection(oldAnswer.length());
                System.out.println("etInput>>>>...."+etInput.getText()+" ... "+oldAnswer+" .... "+oldAnswer.length());
            }
            etInput.requestFocus();
            final PopupWindow popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT, dp2px(40));
            // 获取焦点 Get focus
            popupWindow.setFocusable(true);
            // 为了防止弹出菜单获取焦点之后，点击Activity的其他组件没有响应
           //In order to prevent the pop-up menu from gaining focus, other components of the Activity do not respond after clicking
            popupWindow.setBackgroundDrawable(new PaintDrawable());
            // 设置PopupWindow在软键盘的上方
            //Set PopupWindow above the soft keyboard
            popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            // 弹出PopupWindow
            popupWindow.showAtLocation(tvContent, Gravity.BOTTOM, 0, 0);

            btnFillBlank.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 填写答案
                    //Fill in the answer
                    String answer = etInput.getText().toString();
                    fillAnswer(answer, position);
                    popupWindow.dismiss();
                }
            });

            // 显示软键盘
            //Show soft keyboard
            InputMethodManager inputMethodManager =
                    (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//            inputMethodManager.showSoftInput(etInput, InputMethodManager.SHOW_FORCED);
            inputMethodManager.toggleSoftInput(1, InputMethodManager.SHOW_FORCED);

        }

        @Override
        public void updateDrawState(TextPaint ds) {
            // 不显示下划线
            //Don't show underline
            try {
                ds.setUnderlineText(false);
            }catch (Exception exc){

            }

//            inputMode++;
//            if(inputMode==2){
//                InputMethodManager inputMethodManagerr =
//                        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                //inputMethodManagerr.showSoftInput(etInput, inputMethodManagerr.SHOW_FORCED);
//                inputMethodManagerr.toggleSoftInput(0, inputMethodManagerr.SHOW_FORCED);
//            }
        }
    }

    /**
     * 填写答案
     *
     * @param answer   当前填空处答案
     * @param position 填空位置
     */
    private void fillAnswer(String answer, int position) {
        answer = " " + answer + " ";

        // 替换答案 Replace answer
        AnswerRange range = rangeList.get(position);
        content.replace(range.start, range.end, answer);

        // 更新当前的答案范围 Update the current answer range
        AnswerRange currentRange = new AnswerRange(range.start, range.start + answer.length());
        rangeList.set(position, currentRange);

        // 答案设置下划线 The answer is underlined
        content.setSpan(new UnderlineSpan(),
                currentRange.start, currentRange.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 将答案添加到集合中 Add answers to the collection
        answerList.set(position, answer.replace(" ", " "));

        // 更新内容 update content
        tvContent.setText(content);

        for (int i = 0; i < rangeList.size(); i++) {
            if (i > position) {
                // 获取下一个答案原来的范围
                //Get the original range of the next answer
                AnswerRange oldNextRange = rangeList.get(i);
                int oldNextAmount = oldNextRange.end - oldNextRange.start;
                // 计算新旧答案字数的差值
                //Calculate the difference between the number of words in the old answer and the new answer
                int difference = currentRange.end - range.end;

                // 更新下一个答案的范围
                //Update the scope of the next answer
                AnswerRange nextRange = new AnswerRange(oldNextRange.start + difference,
                        oldNextRange.start + difference + oldNextAmount);
                rangeList.set(i, nextRange);


            }
        }
    }

    /**
     * 获取答案列表 Get a list of answers
     *
     * @return 答案列表
     */
    public List<String> getAnswerList() {
        return answerList;
    }
    public List<AnswerRange> getRangeAnswerList() {
        return rangeList;
    }

    /**
     * dp转px
     *
     * @param dp dp值
     * @return px值
     */
    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
