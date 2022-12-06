package com.app.flipprteachear.home.view.fragments;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.app.flipprteachear.R;
import com.app.flipprteachear.databinding.ResCaseStudyQesTitleRowBinding;
import com.app.flipprteachear.home.pojo.RoomsForDb;
import com.app.flipprteachear.home.pojo.liveModel.fillUpPojo.AnswerRange;
import com.app.flipprteachear.home.pojo.liveModel.fillUpPojo.FillBlankView;
import com.app.flipprteachear.home.pojo.liveQuesPojo.AnswerDetail;
import com.app.flipprteachear.home.pojo.liveQuesPojo.ArrangeAnswerDetail;
import com.app.flipprteachear.home.pojo.liveQuesPojo.CaseStudyAnsSelected;
import com.app.flipprteachear.home.pojo.liveQuesPojo.DescriptiveAnswerDetail;
import com.app.flipprteachear.home.pojo.liveQuesPojo.DescriptiveVisualizationDetail;
import com.app.flipprteachear.home.pojo.liveQuesPojo.Detail;
import com.app.flipprteachear.home.pojo.liveQuesPojo.NumericalSpinerVal_ID;
import com.app.flipprteachear.home.pojo.liveQuesPojo.PojMcqQues;
import com.app.flipprteachear.home.pojo.liveQuesPojo.TruefalseAnswerDetail;
import com.app.flipprteachear.home.pojo.liveQuesPojo.blanksModel;
import com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted.PojoSubmittedMarks;
import com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted.QuestionDetail;
import com.app.flipprteachear.home.pojo.liveQuesPojo.pojoSubmitted.UserDetail;
import com.app.flipprteachear.home.pojo.topicDetail.ModelLiveDetail;
import com.app.flipprteachear.home.pojo.userDetail.PojoUserDetail;
import com.app.flipprteachear.home.view.CaseStudyView;
import com.app.flipprteachear.home.view.activity.HomeActivity;
import com.app.flipprteachear.home.view.adapter.BottomDialogAdapterAnswersError;
import com.app.flipprteachear.home.view.adapter.ClassParticipantsAdapter;
import com.app.flipprteachear.home.view.adapter.OverAllScoreAdapter;
import com.app.flipprteachear.home.view.adapterMcq.AttemptByAdapter;
import com.app.flipprteachear.home.view.adapterMcq.AttemptByAdapterTruefalse;
import com.app.flipprteachear.home.view.adapterMcq.AttemptByCaseStudyAdapter;
import com.app.flipprteachear.home.view.adapterMcq.AttemptByDescriptiveAdapter;
import com.app.flipprteachear.home.view.adapterMcq.AttemptByDescriptiveAnwsAdapter;
import com.app.flipprteachear.home.view.adapterMcq.AttemptByFull_imagesAdapter;
import com.app.flipprteachear.home.view.adapterMcq.AttemptByNumericalAdapter;
import com.app.flipprteachear.home.view.adapterMcq.OverScoreInOneQuesAdapter;
import com.app.flipprteachear.retroFitClasses.apiCalls;
import com.app.flipprteachear.utillsa.Global;
import com.app.flipprteachear.utillsa.PreferenceManager;
import com.app.flipprteachear.utillsa.utills;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import katex.hourglass.in.mathlib.MathView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AllMcq_Fragment extends Fragment implements Animation.AnimationListener, CaseStudyView {

    Spinner spinnerSpeak;
    CardView CardTypeAns;
    ScrollView scrollBar_solution, scrollBar_;
    FrameLayout youtubeFrameLayout;
    ImageView iv_right_showallData, ivWrongAttemtUser, ivNotAttemptUser;                  // Step 1
    ArrayList<UserDetail> rightAnsListUser = new ArrayList();          // Step
    ArrayList<UserDetail> wrongAnsListUser = new ArrayList();          // Step 6
    ArrayList<com.app.flipprteachear.home.pojo.userDetail.Detail> notAttemptedByUser;          // Step 6
    ImageView iv_quesimage, iv_speaker, backBtn, expanded_image, iv_quesimageCase, ivRefresh;
    TextView tv_spinner, tv_quetype, tv_quetype_s1, tv_visualize, tv_Interpret, Tv_Qustion, tv_quePoints,
            tv_solutionbtn, tvnext, tv_apply, tv_visualize1, tv_answer1, tv_time2, tv_anserstatus,
            tv_chapterPages, tv_chapterPages1, tv_sub, tv_sup, tv_Done, tvFillUpAns, tv_learn,
            tv_quePoints1, tvNextBtnCount, tvReadMore, tvSereasRes, tvSereasResAsrtion, tv_highLow_ques,
            tv_highLow, tvUpdate;
    LinearLayout llmcq, llQues, llnumeric, llQues1, llDiagram, llsolution, llnext, llanser, llimage, llimage_case,
            lldescriptive, Card, llEndLive, llsolnext;
    RelativeLayout relDiagText, llMainLay, rlLoader, horizontal_fillup,
            rlanswerframe, rlspeaker, rellSupSup, relMain, relCaseStdy, relMain_asrtion;
    ConstraintLayout Topbar;
    ImageView iv_Diaggram, iv_quries;
    View viewAnswer, viewapply, viewAnswer1;
    EditText et_answer, et_value1;
    apiCalls api;
    Chronometer cmTimer;
    MathView formula_one, tv_solutions1M, tv_QuesDetail_sM, formulaOne1Res, formulaOne1ResAsrtion;
    FillBlankView fbvContent;
    FrameLayout framehoome1;
    ConstraintLayout constraintParticipents;
    RecyclerView rvOverAllScores, rvQuesTopScores, recyclerTitle;
    OverAllScoreAdapter overallAdapter;
    OverScoreInOneQuesAdapter overScoreInOneQuesAdapter;
    public static boolean isEndLive = false;
    private int seconds = 0, secondsForRefresh = 0;
    private boolean running;
    String electedvaluee1 = "", putvalue1 = "";

    ArrayList<EditText> editTextsList;
    ArrayList<String> editTagsList;
    List<String> lines2, editAns;
    int value1, editClicked = 0;

    MediaPlayer mp;
    private CountDownTimer mCountDownTimer, mCountDownTimerRefresh;
    //YouTubePlayerFragment youtube_fragment;
    YoutTube_Fragment youtTube_Fragment;
    com.google.android.youtube.player.YouTubePlayer youTubePlayerComM;

    long totalSeconds;
    List<String> ansDetail_arng;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    // String selectestr = "The enzyme present in Saliva is Salivary Amylase. The role of this enzyme id to convert complex carbohydrate starch into simple sugar maltose.";
    List<String> selectEditDiagrm;
    //private ArrayList<blanksModel> fillInTheBlanks;

    //String studentId="",chapterID="",courseId="",clusterId="", subjectID = "", topicId = "", skill_id = "" ;
    // fromNotification = f_N
    int i = 0, lk = 0, hkk = 0, f_N = 0;
    int totalRightAns = 0, applyResultCount = 0, tolalResutSize, confid = 0, count = 0, applyPositon = 0;
    int apiHit = -1, latetex_dim_15 = 15, latetex_dim_13 = 13, latetex_dim_16 = 16;
    int submitSpeeak = -1, count5_SteakActive = 0, count5_SteakSaver_Active = 0, doubleConfidence = 1, streakSaverActive = 0;
    String quePoints = "", questTypeMarks = "", totalMarks = "", streak_actvMrks = "1", caseStdyMarks = "";
    String ans_n_interpt = "", ans_n_visual = "", ans_n_aplly = "", des_visual_ = "", des_ansmcq_ = "", ansfillup = "",
            ansMcq = "", anstrueFalse = "", arrange_rowid = "", case_studyParams = "";
    String n_interpt_marks, n_visual_marks, n_apply_marks, desc_visualMarks = "0", des_keywordsMarks = "0";
    String textMcqAnsStatus = "0", total_partsMarks = "0", sup_SubStr = "", sup_SubTag = "";
    static Boolean isPausedForced = false;
    boolean isHigh = false, isHigh2 = false;
    // Case Study
    int caseStdyI = 0, totalRightAnws = 0;
    List<CaseStudyAnsSelected> caseStudyAnsSelectList;
    boolean supTag = true, subTag = true, isQuesAnsCorrect = false, isCountDownOnNext = true;
    applyAdapter adapterA;
    queOptionsAdapter queadapter;
    trueFalseAdapter adapterTrueFalse;
    applyEditText adapter2;
    View v;
    private arrangeAdapter adapter;
    CaseStudyAnsAdapter adapterCase;
    descriptiveAdapter adapterDescrip;
    descriptiveAnswerAdapter answerAdapter;
    nuricalAdapter nuricalAdapter;
    private RecyclerView Recycler_answer;
    TextView tv_confidence;
    PojMcqQues pojoQuestions;
    private PreferenceManager pref;

    int tapNext = 0;
    OnDiagramLoadFrag onDiagramLoadFrag;
    private int shortAnimationDuration;
    RoomsForDb roomDb;
    String questionId = "";
    QuestionDetail qsData;
    static Activity context;
    private String schoolCourseStructureId = "", selectedDimensions = "", selectedLevels = "", selectedSubTopics = "", liveCode_ = "";

    private String schoolClassCourseId = "";
    private ListenerRegistration usersListEventsListner;
    private FirebaseFirestore db;
    TextView rightVlaue, wrongVlaue, neutralValue;
    ArrayList<String> correctAnswer, selectedanswer, caseStudyselectedanswer, userList;

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {

        // tv_quePoints.startAnimation(anim_AtEnd);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    public interface OnDiagramLoadFrag {
        void diagramLoad();

        void isStreakInFo_orMcq(boolean b);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onDiagramLoadFrag = (OnDiagramLoadFrag) context;

    }

    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    private long lastClickTime = 0;
    View view, youTubeView;
    //   Animation animTvQuepoints, anim_TotalMarks, anim_AtEnd;

    //private FragmentAllMcqBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        try {
            view = inflater.inflate(R.layout.fragment_all_mcq, container, false);
            // viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_all_mcq_, container, false);
        } catch (InflateException e) {
            e.printStackTrace();
        }

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db = FirebaseFirestore.getInstance();
        context = getActivity();
        pref = new PreferenceManager(requireContext());
        schoolCourseStructureId = getArguments().getString("schoolCourseStructureId").toString();
        try {
            selectedDimensions = getArguments().getString("selectedDimensions").toString() == null ? "" : getArguments().getString("selectedDimensions").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            selectedLevels = getArguments().getString("selectedLevels").toString() == null ? "" : getArguments().getString("selectedLevels").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            selectedSubTopics = getArguments().getString("selectedSubTopics").toString() == null ? "" : getArguments().getString("selectedSubTopics").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (selectedSubTopics == null || selectedSubTopics.equals("null")) selectedSubTopics = "";
        if (selectedLevels == null || selectedLevels.equals("null")) selectedLevels = "";
        if (selectedDimensions == null || selectedDimensions.equals("null"))
            selectedDimensions = "";
        liveCode_ = getArguments().getString("liveCode_").toString();
        schoolClassCourseId = getArguments().getString("school_class_course_id").toString();
        initViews();
        setPointAnimaion();
        fetchUserCount();
        rellSupSup.setVisibility(View.GONE);
        editTagsList = new ArrayList<>();
        editTagsList.clear();
        overallAdapter = new OverAllScoreAdapter("overAll");
        rvOverAllScores.setAdapter(overallAdapter);
        overScoreInOneQuesAdapter = new OverScoreInOneQuesAdapter("");
        rvQuesTopScores.setAdapter(overScoreInOneQuesAdapter);

        //  mp =  MediaPlayer.create();
        //Log.e("onCreateView: ","courseId:-"+courseId +"....clusterId:"+clusterId );
        api = new apiCalls();
        cmTimer.stop();

        // Step 3
        iv_right_showallData.setOnClickListener(v -> {
            showBottomDialog(1);
        });

        ivWrongAttemtUser.setOnClickListener(v -> {
            showBottomDialog(0);
        });

        ivNotAttemptUser.setOnClickListener(v -> {
            showBottomDialog(2);
        });

        tv_learn.setText("New");
        questionListing_liveSession();
//        setAdapterOnQuestions((PojMcqQues) getArguments().get("pojomcq"));
        llMainLay.setBackgroundColor(getResources().getColor(R.color.navicolor));
        Topbar.setBackgroundColor(getResources().getColor(R.color.headercolor));
        horizontal_fillup.setBackground(getResources().getDrawable(R.drawable.round_boundary_navi));
        llQues.setBackground(getResources().getDrawable(R.drawable.round_boundary_navi));

        //  llnext.setVisibility(View.GONE);
        running = true;
        runTimer();
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        linearLayoutManager = new LinearLayoutManager(getActivity()
                , LinearLayoutManager.VERTICAL, false);
        llnext.setOnClickListener(view -> {
            // preventing double, using threshold of 1000 ms
            if (SystemClock.elapsedRealtime() - lastClickTime < 2000) {
                return;
            }
            lastClickTime = SystemClock.elapsedRealtime();
            tapNext++;
            if (tapNext <= 1) {
                running = false;
                constraintParticipents.setVisibility(View.VISIBLE);
                onAnsCorrect_nextCounterStart(false);
            } else {
                tapNext = 0;
                constraintParticipents.setVisibility(View.GONE);
                onAnsCorrect_nextCounterStart(true);
            }
        });

        llEndLive.setOnClickListener(v -> {
            if (SystemClock.elapsedRealtime() - lastClickTime < 2000) {
                return;
            }
            lastClickTime = SystemClock.elapsedRealtime();

            updateCurentIndexForStudentQues(false);
            ((HomeActivity) getActivity()).onEndlive_Back();
        });

        countDounRefreshTimer();
        ivRefresh.setOnClickListener(v -> {
            ivRefresh.clearAnimation();
            RotateAnimation anim = new RotateAnimation(30f, 360f, Float.valueOf(ivRefresh.getWidth() / 2), Float.valueOf(ivRefresh.getHeight() / 2));
            anim.setFillAfter(true);
            anim.setRepeatCount(0);
            anim.setDuration(1000);
            ivRefresh.startAnimation(anim);
            db.collection(getString(R.string.collectionName)).document(liveCode_).get()
                    .addOnSuccessListener(d -> {
                        try {
                            roomDb = d.toObject(RoomsForDb.class);
                            Log.w("TAG", "listen:sub1.." + roomDb.getLiveCode() + "..." + liveCode_ + "..." + roomDb.getSubmittedBy());

                            if (liveCode_.equalsIgnoreCase(roomDb.getLiveCode())) {
                                //Log.w("TAG", "listen:sub.."+roomsForDb.getSubmittedBy());
                                userList = roomDb.getUsers();
                                neutralValue.setText(userList.size() + "");
                                roomDb.setNextTapped(d.getBoolean("nextTapped"));
                                roomDb.setDimension(d.getString("dimension"));
                                roomDb.setLevel(d.getString("level"));
                                roomDb.setSubtopics(d.getString("subtopics"));
                                //roomDb.setSubmittedBy(d.getDocument().("nextTapped"));
                                getUserParticipateList();
                            }
                        } catch (Exception ex) {
                        }
                    });
            if (mCountDownTimerRefresh != null) mCountDownTimerRefresh.cancel();
            mCountDownTimerRefresh.start();
            secondsForRefresh = 0;
            tvUpdate.setText("Just Updated");
        });


        setSpeekSpinner();
        cmTimer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer arg0) {
                totalSeconds = totalSeconds + 1;
                //  Log.d("TAGdddddddd", "onChronometerTick: " + "..:.." + totalSeconds);
            }
        });

        iv_quries.setOnClickListener(v -> {
            Fragment fragment = new QuriesFragment();
            Bundle bundle = new Bundle();
            bundle.putString("questionId", questionId);
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            fragment.setArguments(bundle);
            ft.add(R.id.framehoome, fragment);
            ft.addToBackStack("quries");
            ft.commit();
        });

        tv_answer1.setOnClickListener(view -> {
            lldescriptive.setVisibility(View.VISIBLE);
            llnumeric.setVisibility(View.GONE);
            llmcq.setVisibility(View.GONE);
            llanser.setVisibility(View.GONE);
            spinnerSpeak.setVisibility(View.GONE);
            CardTypeAns.setVisibility(View.GONE);
            horizontal_fillup.setVisibility(View.GONE);
            tv_answer1.setBackgroundResource(R.drawable.round_boundary_green_new);
            viewAnswer1.setBackgroundColor(getResources().getColor(R.color.parrotgreen));
            selectedanswer = new ArrayList<>();
            rlspeaker.setVisibility(View.GONE);
            Recycler_answer.setVisibility(View.VISIBLE);
            Recycler_answer.setAdapter(null);
            answerAdapter = new descriptiveAnswerAdapter(pojoQuestions.getDetails().get(i).getDescriptive_answer_details(), "question2");
            Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                    , LinearLayoutManager.VERTICAL, false));
            Recycler_answer.setAdapter(answerAdapter);
            String ques = pojoQuestions.getDetails().get(i).getQuestion();
            Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
            llimage.setVisibility(View.GONE);
            if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                Log.d("getQues_Image", "getQues_Image: 446");
                Glide.with(getActivity())
                        .load(pojoQuestions.getDetails().get(i).getImage())
                        .into(iv_quesimage);

            }
            rlLoader.setVisibility(View.GONE);
            ivRefresh.performClick();
        });

        tv_visualize1.setOnClickListener(view -> {

            try {
                if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Descriptive")) {
                    tv_quetype.setText("Descriptive");

                    lldescriptive.setVisibility(View.VISIBLE);
                    Recycler_answer.setVisibility(View.VISIBLE);
                    llanser.setVisibility(View.GONE);
                    spinnerSpeak.setVisibility(View.GONE);
                    CardTypeAns.setVisibility(View.GONE);
                    llnumeric.setVisibility(View.GONE);
                    llmcq.setVisibility(View.GONE);
                    horizontal_fillup.setVisibility(View.GONE);
                    adapterDescrip = new descriptiveAdapter(pojoQuestions.getDetails().get(i).getDescriptive_visualization_details(), "question");
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(adapterDescrip);
                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    llimage.setVisibility(View.GONE);
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        Log.d("getQues_Image", "getQues_Image: 476");
                        Glide.with(getActivity())
                                .load(pojoQuestions.getDetails().get(i).getImage())
                                .into(iv_quesimage);

                    }
                    count++;
                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));

                }
            } catch (IndexOutOfBoundsException inEx) {
                inEx.printStackTrace();
            }

            ivRefresh.performClick();
        });

        tv_Interpret.setOnClickListener(v -> {
            interPret_Numerical();
            ivRefresh.performClick();
        });
        tv_visualize.setOnClickListener(v -> {
            count = 0;
            n_Visualisation_ques();
            ivRefresh.performClick();
        });
        tv_apply.setOnClickListener(v -> {
            count = 1;
            n_Visualisation_ques();
            ivRefresh.performClick();
        });

        return view;
    }

    // Step 4

   /* private void showBottomDialog(int i) {

        View bottomLayoutview = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_layout, null);
        BottomSheetDialog bootomDialog = new BottomSheetDialog(requireActivity(), R.style.BottomSheetDialogTheme);
        //   bootomDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bootomDialog.setContentView(bottomLayoutview);
        RecyclerView bottomRecyleView = bottomLayoutview.findViewById(R.id.bottom_recyle_view);
        Button hide_btn = bottomLayoutview.findViewById(R.id.back_hide);
        TextView tv_attemption = bottomLayoutview.findViewById(R.id.tv_attempt_user);
        bottomRecyleView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        BottomDialogAdapterAnswersError attemptByUsers = new BottomDialogAdapterAnswersError();
        bottomRecyleView.setAdapter(attemptByUsers);
        hide_btn.setOnClickListener(v -> {
            bootomDialog.dismiss();
        });
        if (i == 1) {
            if (rightAnsListUser.size() == 0) {
            } else {
                tv_attemption.setText(getString(R.string.correct_attempt));
                attemptByUsers.updateListType(rightAnsListUser);
                bootomDialog.show();
            }
        } else if (i == 0) {
            if (wrongAnsListUser.size() == 0) {
            } else {
                tv_attemption.setText(getString(R.string.wrong_attempt));
                attemptByUsers.updateListType(wrongAnsListUser);
                bootomDialog.show();
            }
        } else {
            if (notAttemptedByUser.size() == 0) {
                Log.e("emptyList", "nothing is here");
            } else {
                filterwrongList();
                filterWrightList();
                Log.e("showBottomDialog", "show" + notAttemptedByUser.size());
                if (notAttemptedByUser.size() == 0) {
                } else {
                    tv_attemption.setText(getString(R.string.not_attempt));
                    ClassParticipantsAdapter adapter = new ClassParticipantsAdapter(0, pref);
                    adapter.updateListType(notAttemptedByUser);
                    bottomRecyleView.setAdapter(adapter);
                    bootomDialog.show();
                }
            }
        }
    }*/

    private void showBottomDialog(int i) {

        View bottomLayoutview = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_layout, null);
        BottomSheetDialog bootomDialog = new BottomSheetDialog(requireActivity(),R.style.BottomSheetDialogTheme);
        //   bootomDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        bootomDialog.setContentView(bottomLayoutview);
        RecyclerView bottomRecyleView = bottomLayoutview.findViewById(R.id.bottom_recyle_view);
        Button hide_btn=bottomLayoutview.findViewById(R.id.back_hide);
        TextView tv_attemption=bottomLayoutview.findViewById(R.id.tv_attempt_user);
        bottomRecyleView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        BottomDialogAdapterAnswersError attemptByUsers = new BottomDialogAdapterAnswersError();
        bottomRecyleView.setAdapter(attemptByUsers);
        hide_btn.setOnClickListener(v->{
            bootomDialog.dismiss();
        });


        if(i==1){
            if(rightAnsListUser!=null){
                if(rightAnsListUser.size()==0){

                }else {
                    tv_attemption.setText(getString(R.string.correct_attempt));
                    attemptByUsers.updateListType(rightAnsListUser);
                    bootomDialog.show();
                }
            }



        }else if(i==0){
            if(wrongAnsListUser!=null){

                if(wrongAnsListUser.size()==0){

                }else {
                    tv_attemption.setText(getString(R.string.wrong_attempt));
                    attemptByUsers.updateListType(wrongAnsListUser);
                    bootomDialog.show();
                }
            }


        }else {
            if(notAttemptedByUser!=null){
                if(notAttemptedByUser.size()==0){
                    Log.e("emptyList","nothing is here");
                }else {
                    filterwrongList();
                    filterWrightList();

                    Log.e("showBottomDialog","show"+notAttemptedByUser.size());

                    if(notAttemptedByUser.size()==0){

                    }else{
                        tv_attemption.setText(getString(R.string.not_attempt));
                        ClassParticipantsAdapter adapter =new ClassParticipantsAdapter(0,pref);
                        adapter.updateListType(notAttemptedByUser);
                        bottomRecyleView.setAdapter(adapter);
                        bootomDialog.show();
                    }

                }
            }

        }

    }

    private void filterWrightList() {
        if (rightAnsListUser.size() != 0) {
            for (int i = 0; i < notAttemptedByUser.size(); i++) {
                for (int j = 0; j < rightAnsListUser.size(); j++) {
                    if (notAttemptedByUser.get(i).getUserId().equalsIgnoreCase(rightAnsListUser.get(j).getUserId())) {
                        Log.e("remove ", "right");
                        notAttemptedByUser.remove(i);
                        filterWrightList();
                    }
                }
            }
        }
    }

    private void filterwrongList() {
        Log.e("notAttemptsize", "" + notAttemptedByUser.size());
        if (wrongAnsListUser.size() != 0) {
            for (int i = 0; i < notAttemptedByUser.size(); i++) {
                for (int j = 0; j < wrongAnsListUser.size(); j++) {
                    if (notAttemptedByUser.get(i).getUserId().equalsIgnoreCase(wrongAnsListUser.get(j).getUserId())) {
                        {
                            Log.e("remove ", "wrong");
                            notAttemptedByUser.remove(i);
                            filterwrongList();
                        }
                    }
                }
            }
        }
    }

    void interPret_Numerical() {
        try {
            if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Numerical")) {
                tv_quetype.setText("Numerical");
                // tv_quetype_s.setText("Numerical");
                llnumeric.setVisibility(View.VISIBLE);
                Recycler_answer.setVisibility(View.VISIBLE);
                llmcq.setVisibility(View.GONE);
                horizontal_fillup.setVisibility(View.GONE);
                lldescriptive.setVisibility(View.GONE);
                for (int k = 0; k <= pojoQuestions.getDetails().get(i).getN_interpretation_answer_details().size() - 1; k++) {
                    if (pojoQuestions.getDetails().get(i).getN_interpretation_answer_details().get(k).getInterpretationRightWrong().equalsIgnoreCase("Right")) {
                        totalRightAns++;
                    }
                }
                nuricalAdapter = new nuricalAdapter(pojoQuestions.getDetails().get(i), "question", totalRightAns);
                Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                        , LinearLayoutManager.VERTICAL, false));
                Recycler_answer.setAdapter(nuricalAdapter);
                String ques = pojoQuestions.getDetails().get(i).getQuestion();
                llimage.setVisibility(View.GONE);
                if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                    Log.d("getQues_Image", "getQues_Image: 533");
                    Glide.with(getActivity())
                            .load(pojoQuestions.getDetails().get(i).getImage())
                            .into(iv_quesimage);
                }

                nuricalAdapter.notifyDataSetChanged();
                //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));

            }
        } catch (IndexOutOfBoundsException inEx) {
            inEx.printStackTrace();
        }

    }

    void setonNextClicked() {
        rightAnsListUser.clear();  // Step 7
        wrongAnsListUser.clear();

        tvnext.setText("Stop Q");
        // totalRightAns =0;
        llNextClicked_objects();
        try {
            tv_solutionbtn.setText("Solution");
            scrollBar_solution.setVisibility(View.GONE);
            scrollBar_.setVisibility(View.VISIBLE);
            //Log.e("catch_exception", "count:" + count + " " + getDetails().get(i).getType());
            if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text MCQ")
                    || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image MCQ")
                    || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Truefalse")
                    || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image Truefalse")
                    || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text Truefalse")
                    || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text Arrange")
                    || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image Arrange")
                    || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Fillup")
                    || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Diagram")
                    || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Case Study")
                    || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Descriptive")
                    || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Assertion Reasoning")) {
                selectedanswer = new ArrayList<>();
                correctAnswer = new ArrayList<>();
                i++;
                System.out.println("llnext...." + i);
                updateCurentIndexForStudentQues(true);
                questionId = pojoQuestions.getDetails().get(i).getQuestions_all_types_id();
                Log.e("TAG", "setonNextClicked:questionId.. " + questionId);
//                        Log.d("QuestionId2",pojoQuestions.getDetails().get(i).getQuestionsAllTypesId());
                if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text MCQ")
                        || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image MCQ")) {
                    // Log.e("catch_exception", "i:" + i);
                    llmcq.setVisibility(View.GONE);
                    lldescriptive.setVisibility(View.GONE);
                    llnumeric.setVisibility(View.GONE);
                    horizontal_fillup.setVisibility(View.GONE);

                    // llsolnext.setVisibility(View.VISIBLE);
                    Recycler_answer.setVisibility(View.VISIBLE);
//
                    for (int k = 0; k < pojoQuestions.getDetails().get(i).getAnswer_details().size(); k++) {
                        if (pojoQuestions.getDetails().get(i).getAnswer_details().get(k).getTypes().equalsIgnoreCase("Right")) {
                            totalRightAns++;
                        }
                    }
                    getSetDefineType(pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);

                    queadapter = new queOptionsAdapter(pojoQuestions.getDetails().get(i).getAnswer_details(), "question", pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                    Recycler_answer.setLayoutManager(linearLayoutManager);
                    Recycler_answer.setAdapter(queadapter);
                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    //formula_one.setDisplayText(ques);
                    llimage.setVisibility(View.GONE);

                    tv_quetype.setText(pojoQuestions.getDetails().get(i).getDefine_type());
                    // tv_quetype_s.setText(pojoQuestions.getDetails().get(i).getDefine_type());
                    rlLoader.setVisibility(View.GONE);
                    llimage.setVisibility(View.GONE);
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        llimage.setVisibility(View.VISIBLE);
                        getQues_Image();
                    }

                    if (pojoQuestions.getDetails().get(i).getDefine_type().toLowerCase().trim().contains("single")) {
                        getFlippMarks("single");
                    } else if (pojoQuestions.getDetails().get(i).getDefine_type().toLowerCase().trim().contains("multiple")) {
                        getFlippMarks("multiple");
                    }
                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Truefalse")
                        || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image Truefalse")
                        || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text Truefalse")) {
                    llmcq.setVisibility(View.GONE);
                    lldescriptive.setVisibility(View.GONE);
                    llnumeric.setVisibility(View.GONE);
                    Recycler_answer.setVisibility(View.VISIBLE);

                    horizontal_fillup.setVisibility(View.GONE);
                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    formula_one.setVisibility(View.VISIBLE);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    llimage.setVisibility(View.GONE);
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        llimage.setVisibility(View.VISIBLE);
                        getQues_Image();
                    }
                    adapterTrueFalse = new trueFalseAdapter(pojoQuestions.getDetails().get(i).getTruefalse_answer_details(), "question");

                    Recycler_answer.setAdapter(adapterTrueFalse);

                    tv_quetype.setText("True or False");

                    Recycler_answer.setVisibility(View.INVISIBLE);
                    // adapterTrueFalse.notifyDataSetChanged();
                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text Arrange")
                        || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image Arrange")) {
                    llmcq.setVisibility(View.GONE);
                    lldescriptive.setVisibility(View.GONE);
                    llnumeric.setVisibility(View.GONE);
                    Recycler_answer.setVisibility(View.VISIBLE);   //tv_quePoints
                    tv_quetype.setText("Arrange");
                    // tv_quetype_s.setText("Arrange");
                    horizontal_fillup.setVisibility(View.GONE);

                    ansDetail_arng = new ArrayList<>();
                    ansDetail_arng.clear();
                    for (int p = 0; p <= pojoQuestions.getDetails().get(i).getArrange_answer_details().size() - 1; p++) {
                        ansDetail_arng.add(pojoQuestions.getDetails().get(i).getArrange_answer_details().get(p).getTypes());
                    }
                    Collections.sort(pojoQuestions.getDetails().get(i).getArrange_answer_details(),
                            (Comparator) (o1, o2) ->
                            {
                                ArrangeAnswerDetail p1 = (ArrangeAnswerDetail) o1;
                                ArrangeAnswerDetail p2 = (ArrangeAnswerDetail) o2;
                                return p1.getTypes().compareToIgnoreCase(p2.getTypes());
                            });

                    totalRightAns = pojoQuestions.getDetails().get(i).getArrange_answer_details().size();
                    adapter = new arrangeAdapter(pojoQuestions.getDetails().get(i).getArrange_answer_details(), "question", pojoQuestions.getDetails().get(i).getType());
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(adapter);

                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    llimage.setVisibility(View.GONE);
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        llimage.setVisibility(View.VISIBLE);

                    }
                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    if (pojoQuestions.getDetails().get(i).getType().toLowerCase().trim().contains("arrange")) {
                        getFlippMarks("arrange");
                    }
                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Descriptive")) {
                    tv_quetype.setText("Descriptive");
                    // tv_quetype_s.setText("Descriptive");
                    lldescriptive.setVisibility(View.VISIBLE);
                    llmcq.setVisibility(View.GONE);
                    llnumeric.setVisibility(View.GONE);
                    Recycler_answer.setVisibility(View.VISIBLE);
                    horizontal_fillup.setVisibility(View.GONE);
                    adapterDescrip = new descriptiveAdapter(pojoQuestions.getDetails().get(i).getDescriptive_visualization_details(), "question");
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(adapterDescrip);
                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    llimage.setVisibility(View.GONE);

                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    //  if(pojoQuestions.getDetails().get(i).getDescriptiveVisualizationDetails().contains("")){
                    count++;
                    //}
//                            if(selectedanswer.size()==marksRight){
//                                // int marksRight =0;
//                                flipp_Obtained_Marks(marksRight, true);
//                            }
                    // =====

                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Numerical")) {
                    tv_quetype.setText("Numerical");
                    // tv_quetype_s.setText("Numerical");
                    llnumeric.setVisibility(View.VISIBLE);
                    llmcq.setVisibility(View.GONE);
                    lldescriptive.setVisibility(View.GONE);
                    horizontal_fillup.setVisibility(View.GONE);
                    Recycler_answer.setVisibility(View.VISIBLE);
                    getFlippMarks("interpretation");

                    for (int k = 0; k <= pojoQuestions.getDetails().get(i).getN_interpretation_answer_details().size() - 1; k++) {
                        if (pojoQuestions.getDetails().get(i).getN_interpretation_answer_details().get(k).getInterpretationRightWrong().equalsIgnoreCase("Right")) {
                            totalRightAns++;
                        }
                    }

                    nuricalAdapter = new nuricalAdapter(pojoQuestions.getDetails().get(i), "question", totalRightAns);
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(nuricalAdapter);

                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    llimage.setVisibility(View.GONE);

                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    //adapter.notifyDataSetChanged();
                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Diagram")) {
                    tv_quetype.setText("Label The Diagram");
                    //  tv_quetype_s.setText("Label The Diagram");
                    llQues.setVisibility(View.GONE);
                    llDiagram.setVisibility(View.VISIBLE);
                    llnumeric.setVisibility(View.GONE);
                    llmcq.setVisibility(View.GONE);
                    lldescriptive.setVisibility(View.GONE);
                    horizontal_fillup.setVisibility(View.GONE);
                    Recycler_answer.setVisibility(View.GONE);

                    rlLoader.setVisibility(View.VISIBLE);

                    onDiagramLoadFrag.diagramLoad();
                    //   setTheDiagramLables(pojoQuestions.getDetails().get(i));

                    rlLoader.setVisibility(View.GONE);
                    getFlippMarks("diagram");
                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Fillup")) {
                    tv_quetype.setText("Fillup");
                    // tv_quetype_s.setText("Fillup");
                    llQues.setVisibility(View.GONE);
                    llDiagram.setVisibility(View.GONE);
                    llnumeric.setVisibility(View.GONE);
                    llmcq.setVisibility(View.GONE);
                    lldescriptive.setVisibility(View.GONE);
                    horizontal_fillup.setVisibility(View.VISIBLE);
                    Recycler_answer.setVisibility(View.GONE);
                    Recycler_answer.setVisibility(View.GONE);

                    llsolution.setVisibility(View.GONE);
                    rlLoader.setVisibility(View.VISIBLE);

                    SetFillUpNewSpan(pojoQuestions.getDetails().get(i));
                    // setTheFillUps_Blanks(pojoQuestions.getDetails().get(i));
                    rlLoader.setVisibility(View.GONE);
                    getFlippMarks("fillup");

                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);

                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Case Study")) {
                    llnumeric.setVisibility(View.GONE);
                    llQues1.setVisibility(View.GONE);
                    llQues.setVisibility(View.GONE);
                    relMain_asrtion.setVisibility(View.GONE);
                    tvSereasRes.setText("Case");
                    tv_quetype.setText("Case Study");
                    // tv_quetype_s.setText("Case Study");
                    tvReadMore.setVisibility(View.VISIBLE);
                    Recycler_answer.setVisibility(View.VISIBLE);
                    recyclerTitle.setVisibility(View.VISIBLE);
                    relCaseStdy.setVisibility(View.VISIBLE);

                    caseStudyAnsSelectList = new ArrayList<>();
                    caseStudyAnsSelectList.clear();
                    int oi = 0;
                    for (int j = 0; j < pojoQuestions.getDetails().get(i).getCase_study_ques_details().get(caseStdyI).getCasestudyMcqs().size(); j++) {
                        if (pojoQuestions.getDetails().get(i).getCase_study_ques_details().get(caseStdyI)
                                .getCasestudyMcqs().get(j).getTypes().equalsIgnoreCase("Right")) {
                            oi++;
                        }
                    }
                    String questiontype = "single";
                    if (oi > 1) {
                        questiontype = "multiple";
                    }
                    //caseStudyAnsSelectList.add(new CaseStudyAnsSelected(caseStdyI+"", "",oi+"",""));

                    CaseStudyTitleAdapter titleAdapter = new CaseStudyTitleAdapter(requireContext(), pojoQuestions.getDetails().get(i), caseStdyI + "", this);
                    recyclerTitle.setAdapter(titleAdapter);

                    adapterCase = new CaseStudyAnsAdapter(pojoQuestions.getDetails().get(i), "question", totalRightAns, questiontype);
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(adapterCase);
                    String ques = pojoQuestions.getDetails().get(i).getCase_study_details().get(0).getCase();
                    llimage.setVisibility(View.GONE);
                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    tvReadMore.setOnClickListener(v1 -> getCaseStudyReadMoreDialog(getLatexSolvedQ_Str(ques, true)));

                    if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                        llsolution.setVisibility(View.GONE);
                    } else {
                        llsolution.setVisibility(View.VISIBLE);
                    }

                    if (questiontype.toLowerCase().trim().contains("single")) {
                        getFlippMarks("single");
                    } else if (questiontype.toLowerCase().trim().contains("multiple")) {
                        getFlippMarks("multiple");
                    }
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        Log.d("getQues_Image", "getQues_Image: 842");
                        llimage_case.setVisibility(View.VISIBLE);
                        Glide.with(getActivity())
                                .load(pojoQuestions.getDetails().get(i).getImage())
                                .into(iv_quesimageCase);
                    }
                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Assertion Reasoning")) {
                    llnumeric.setVisibility(View.GONE);
                    llQues1.setVisibility(View.GONE);
                    llQues.setVisibility(View.GONE);
                    tvReadMore.setVisibility(View.GONE);
                    recyclerTitle.setVisibility(View.GONE);

                    tvSereasRes.setText("Assertion");
                    tvSereasResAsrtion.setText("Reasoning");
                    tv_quetype.setText("Assertion Reasoning");
                    // tv_quetype_s.setText("Assertion Reasoning");
                    Recycler_answer.setVisibility(View.VISIBLE);
                    relMain_asrtion.setVisibility(View.VISIBLE);
                    relCaseStdy.setVisibility(View.VISIBLE);
                    caseStudyAnsSelectList = new ArrayList<>();
                    caseStudyAnsSelectList.clear();
                    //int oi= 0;
                    for (int k = 0; k < pojoQuestions.getDetails().get(i).getAnswer_details().size(); k++) {
                        if (pojoQuestions.getDetails().get(i).getAnswer_details().get(k).getTypes().equalsIgnoreCase("Right")) {
                            totalRightAns++;
                        }
                    }

                    String questiontype = "single";
                    if (totalRightAns > 1) questiontype = "multiple";

                    getSetDefineType(pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                    queadapter = new queOptionsAdapter(pojoQuestions.getDetails().get(i).getAnswer_details(), "question", pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(queadapter);
                    String ques = pojoQuestions.getDetails().get(i).getAssertion_reasoning_details().get(0).getAssertion();
                    formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    String quesReason = pojoQuestions.getDetails().get(i).getAssertion_reasoning_details().get(0).getReasoning();
                    formulaOne1ResAsrtion.setDisplayText(getLatexSolvedQ_Str(quesReason, true));

                    llimage.setVisibility(View.GONE);//
                    if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                        llsolution.setVisibility(View.GONE);
                    } else {
                        llsolution.setVisibility(View.VISIBLE);
                    }

                    if (questiontype.toLowerCase().trim().contains("single")) {
                        getFlippMarks("single");
                    } else if (questiontype.toLowerCase().trim().contains("multiple")) {
                        getFlippMarks("multiple");
                    }
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        llimage_case.setVisibility(View.VISIBLE);
                        Log.d("getQues_Image", "getQues_Image: 898");
                        Glide.with(getActivity())
                                .load(pojoQuestions.getDetails().get(i).getImage())
                                .into(iv_quesimageCase);
                    }
                }
            } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Diagram")) {
                tv_quetype.setText("Label The Diagram");
                // tv_quetype_s.setText("Label The Diagram");
                llQues.setVisibility(View.GONE);
                llDiagram.setVisibility(View.VISIBLE);
                llnumeric.setVisibility(View.GONE);
                llmcq.setVisibility(View.GONE);
                lldescriptive.setVisibility(View.GONE);
                horizontal_fillup.setVisibility(View.GONE);
                Recycler_answer.setVisibility(View.GONE);

                rlLoader.setVisibility(View.VISIBLE);

                onDiagramLoadFrag.diagramLoad();
                // setTheDiagramLables(pojoQuestions.getDetails().get(i));
                rlLoader.setVisibility(View.GONE);
                getFlippMarks("diagram");
                //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
            } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Case Study")) {
                llnumeric.setVisibility(View.GONE);
                llQues1.setVisibility(View.GONE);
                llQues.setVisibility(View.GONE);
                relMain_asrtion.setVisibility(View.GONE);
                tvSereasRes.setText("Case");
                tv_quetype.setText("Case Study");
                // tv_quetype_s.setText("Case Study");
                tvReadMore.setVisibility(View.VISIBLE);
                Recycler_answer.setVisibility(View.VISIBLE);
                recyclerTitle.setVisibility(View.VISIBLE);
                relCaseStdy.setVisibility(View.VISIBLE);
                caseStudyAnsSelectList = new ArrayList<>();
                caseStudyAnsSelectList.clear();
                int oi = 0;
                for (int j = 0; j < pojoQuestions.getDetails().get(i).getCase_study_ques_details().get(caseStdyI).getCasestudyMcqs().size(); j++) {
                    if (pojoQuestions.getDetails().get(i).getCase_study_ques_details().get(caseStdyI)
                            .getCasestudyMcqs().get(j).getTypes().equalsIgnoreCase("Right")) {
                        oi++;
                    }
                }
                String questiontype = "single";
                if (oi > 1) {
                    questiontype = "multiple";
                }
                //caseStudyAnsSelectList.add(new CaseStudyAnsSelected(caseStdyI+"", "",oi+"",""));

                CaseStudyTitleAdapter titleAdapter = new CaseStudyTitleAdapter(requireContext(), pojoQuestions.getDetails().get(i), caseStdyI + "", this);
                recyclerTitle.setAdapter(titleAdapter);

                adapterCase = new CaseStudyAnsAdapter(pojoQuestions.getDetails().get(i), "question", totalRightAns, questiontype);
                Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                        , LinearLayoutManager.VERTICAL, false));
                Recycler_answer.setAdapter(adapterCase);
                String ques = pojoQuestions.getDetails().get(i).getCase_study_details().get(0).getCase();
                llimage.setVisibility(View.GONE);
                tvReadMore.setOnClickListener(v1 -> getCaseStudyReadMoreDialog(getLatexSolvedQ_Str(ques, true)));


                // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true));
                if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                    llsolution.setVisibility(View.GONE);
                } else {
                    llsolution.setVisibility(View.VISIBLE);
                }

                if (questiontype.toLowerCase().trim().contains("single")) {
                    getFlippMarks("single");
                } else if (questiontype.toLowerCase().trim().contains("multiple")) {
                    getFlippMarks("multiple");
                }
                if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                    Log.d("getQues_Image", "getQues_Image: 977");
                    llimage_case.setVisibility(View.VISIBLE);
                    Glide.with(getActivity())
                            .load(pojoQuestions.getDetails().get(i).getImage())
                            .into(iv_quesimageCase);
                }
            } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Assertion Reasoning")) {

                llnumeric.setVisibility(View.GONE);
                llQues1.setVisibility(View.GONE);
                llQues.setVisibility(View.GONE);
                tvReadMore.setVisibility(View.GONE);
                recyclerTitle.setVisibility(View.GONE);

                tvSereasRes.setText("Assertion");
                tvSereasResAsrtion.setText("Reasoning");
                tv_quetype.setText("Assertion Reasoning");
                //  tv_quetype_s.setText("Assertion Reasoning");
                Recycler_answer.setVisibility(View.VISIBLE);
                relMain_asrtion.setVisibility(View.VISIBLE);
                relCaseStdy.setVisibility(View.VISIBLE);
                caseStudyAnsSelectList = new ArrayList<>();
                caseStudyAnsSelectList.clear();
                int oi = 0;
                for (int k = 0; k < pojoQuestions.getDetails().get(i).getAnswer_details().size(); k++) {
                    if (pojoQuestions.getDetails().get(i).getAnswer_details().get(k).getTypes().equalsIgnoreCase("Right")) {
                        totalRightAns++;
                    }
                }
                String questiontype = "single";
                if (oi > 1) {
                    questiontype = "multiple";
                }
                getSetDefineType(pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                queadapter = new queOptionsAdapter(pojoQuestions.getDetails().get(i).getAnswer_details(), "question", pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                        , LinearLayoutManager.VERTICAL, false));
                Recycler_answer.setAdapter(queadapter);
                String ques = pojoQuestions.getDetails().get(i).getAssertion_reasoning_details().get(0).getAssertion();
                formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true));
                String quesReason = pojoQuestions.getDetails().get(i).getAssertion_reasoning_details().get(0).getReasoning();
                formulaOne1ResAsrtion.setDisplayText(getLatexSolvedQ_Str(quesReason, true));

                llimage.setVisibility(View.GONE);//
                if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                    llsolution.setVisibility(View.GONE);
                } else {
                    llsolution.setVisibility(View.VISIBLE);
                }

                if (questiontype.toLowerCase().trim().contains("single")) {
                    getFlippMarks("single");
                } else if (questiontype.toLowerCase().trim().contains("multiple")) {
                    getFlippMarks("multiple");
                }
                if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                    Log.d("getQues_Image", "getQues_Image: 1034");
                    llimage_case.setVisibility(View.VISIBLE);
                    Glide.with(getActivity())
                            .load(pojoQuestions.getDetails().get(i).getImage())
                            .into(iv_quesimageCase);
                }
            } else {

                if (count == -10) {
                    count = 0;
                    selectedanswer = new ArrayList<>();
                    correctAnswer = new ArrayList<>();
                    horizontal_fillup.setVisibility(View.GONE);
                    i++;
                    updateCurentIndexForStudentQues(true);
                    questionId = pojoQuestions.getDetails().get(i).getQuestions_all_types_id();
                    if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text MCQ")
                            || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image MCQ")) {
                        // Log.e("catch_exception", "i:" + i);
                        llmcq.setVisibility(View.GONE);
                        lldescriptive.setVisibility(View.GONE);
                        llnumeric.setVisibility(View.GONE);
                        Recycler_answer.setVisibility(View.VISIBLE);
//                                if ((i + 1) >= pojoQuestions.getDetails().size()) {
//                                    Toast.makeText(getActivity(), "Last question", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(getActivity(), HomeActivty.class));
//                                } else {
                        for (int k = 0; k < pojoQuestions.getDetails().get(i).getAnswer_details().size(); k++) {
                            if (pojoQuestions.getDetails().get(i).getAnswer_details().get(k).getTypes().equalsIgnoreCase("Right")) {
                                totalRightAns++;
                            }
                        }
                        getSetDefineType(pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);

                        queadapter = new queOptionsAdapter(pojoQuestions.getDetails().get(i).getAnswer_details(), "question", pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                        Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                                , LinearLayoutManager.VERTICAL, false));
                        Recycler_answer.setAdapter(queadapter);
                        String ques = pojoQuestions.getDetails().get(i).getQuestion();
                        // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                        formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                        formula_one.setDisplayText(ques);


                        tv_quetype.setText(pojoQuestions.getDetails().get(i).getDefine_type());
                        //   tv_quetype_s.setText(pojoQuestions.getDetails().get(i).getDefine_type());
                        rlLoader.setVisibility(View.GONE);
                        llimage.setVisibility(View.GONE);
                        if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                            llimage.setVisibility(View.VISIBLE);
                            getQues_Image();
                        }
                        if (pojoQuestions.getDetails().get(i).getType().toLowerCase().trim().contains("single")) {
                            getFlippMarks("single");
                        } else if (pojoQuestions.getDetails().get(i).getType().toLowerCase().trim().contains("multiple")) {
                            getFlippMarks("multiple");
                        }
                    } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Truefalse")
                            || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image Truefalse")
                            || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text Truefalse")) {
                        llmcq.setVisibility(View.GONE);
                        lldescriptive.setVisibility(View.GONE);
                        llnumeric.setVisibility(View.GONE);
                        horizontal_fillup.setVisibility(View.GONE);


//                                if ((i + 1) >= pojoQuestions.getDetails().size()) {
//                                    Toast.makeText(getActivity(), "Last question", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(getActivity(), HomeActivty.class));
//                                } else {
                        adapterTrueFalse = new trueFalseAdapter(pojoQuestions.getDetails().get(i).getTruefalse_answer_details(), "question");
                        //Recycler_answer.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                        Recycler_answer.setAdapter(adapterTrueFalse);
                        String ques = pojoQuestions.getDetails().get(i).getQuestion();
                        llimage.setVisibility(View.GONE);

                        tv_quetype.setText("True or False");
                        // tv_quetype_s.setText("True or False");
                        // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                        formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
//                                }
                        if (pojoQuestions.getDetails().get(i).getType().toLowerCase().trim().contains("truefalse")) {
                            getFlippMarks("truefalse");
                        }
                        adapterTrueFalse.notifyDataSetChanged();
                    } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text Arrange")
                            || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image Arrange")) {
                        llmcq.setVisibility(View.GONE);
                        lldescriptive.setVisibility(View.GONE);
                        llnumeric.setVisibility(View.GONE);
                        horizontal_fillup.setVisibility(View.GONE);
                        Recycler_answer.setVisibility(View.VISIBLE);
//                                if ((i + 1) >= pojoQuestions.getDetails().size()) {
//                                    startActivity(new Intent(getActivity(), HomeActivty.class));
//                                } else {
                        tv_quetype.setText("Arrange");
                        //  tv_quetype_s.setText("Arrange");

                        ansDetail_arng = new ArrayList<>();
                        ansDetail_arng.clear();

                        for (int p = 0; p <= pojoQuestions.getDetails().get(i).getArrange_answer_details().size() - 1; p++) {
                            ansDetail_arng.add(pojoQuestions.getDetails().get(i).getArrange_answer_details().get(p).getTypes());

                        }
                        Collections.sort(pojoQuestions.getDetails().get(i).getArrange_answer_details(),
                                (Comparator) (o1, o2) ->
                                {
                                    ArrangeAnswerDetail p1 = (ArrangeAnswerDetail) o1;
                                    ArrangeAnswerDetail p2 = (ArrangeAnswerDetail) o2;
                                    return p1.getTypes().compareToIgnoreCase(p2.getTypes());
                                });
                        totalRightAns = pojoQuestions.getDetails().get(i).getArrange_answer_details().size();
                        adapter = new arrangeAdapter(pojoQuestions.getDetails().get(i).getArrange_answer_details(), "question", pojoQuestions.getDetails().get(i).getType());
                        Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                                , LinearLayoutManager.VERTICAL, false));
                        Recycler_answer.setAdapter(adapter);
                        String ques = pojoQuestions.getDetails().get(i).getQuestion();
                        llimage.setVisibility(View.GONE);
                        if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                            Log.d("getQues_Image", "getQues_Image: 1156");
                            llimage.setVisibility(View.VISIBLE);
                            Glide.with(getActivity())
                                    .load(pojoQuestions.getDetails().get(i).getImage())
                                    .into(iv_quesimage);

                        }
                        //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                        formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
//                                }
                        if (pojoQuestions.getDetails().get(i).getType().toLowerCase().trim().contains("arrange")) {
                            getFlippMarks("arrange");
                        }
                    } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Numerical"))
                        interPret_Numerical();
                    else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Diagram")) {
                        tv_quetype.setText("Label The Diagram");
                        // tv_quetype_s.setText("Label The Diagram");
                        llQues.setVisibility(View.GONE);
                        llDiagram.setVisibility(View.VISIBLE);
                        llnumeric.setVisibility(View.GONE);
                        llmcq.setVisibility(View.GONE);
                        lldescriptive.setVisibility(View.GONE);
                        Recycler_answer.setVisibility(View.GONE);
                        horizontal_fillup.setVisibility(View.GONE);

                        rlLoader.setVisibility(View.VISIBLE);

                        onDiagramLoadFrag.diagramLoad();
                        // setTheDiagramLables(pojoQuestions.getDetails().get(i));
                        rlLoader.setVisibility(View.GONE);
                        getFlippMarks("diagram");
                        //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Fillup")) {
                        tv_quetype.setText("Fillup");
                        // tv_quetype_s.setText("Fillup");
                        llQues.setVisibility(View.GONE);
                        llDiagram.setVisibility(View.VISIBLE);
                        llnumeric.setVisibility(View.GONE);
                        llmcq.setVisibility(View.GONE);
                        lldescriptive.setVisibility(View.GONE);
                        horizontal_fillup.setVisibility(View.VISIBLE);
                        Recycler_answer.setVisibility(View.GONE);
                        Recycler_answer.setVisibility(View.GONE);

                        llsolution.setVisibility(View.GONE);
                        rlLoader.setVisibility(View.VISIBLE);

                        SetFillUpNewSpan(pojoQuestions.getDetails().get(i));
                        //setTheFillUps_Blanks(pojoQuestions.getDetails().get(i));
                        rlLoader.setVisibility(View.GONE);
                        getFlippMarks("fillup");

                        //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Case Study")) {
                        llnumeric.setVisibility(View.GONE);
                        llQues1.setVisibility(View.GONE);
                        llQues.setVisibility(View.GONE);
                        tvSereasRes.setText("Case");
                        tv_quetype.setText("Case Study");
                        //tv_quetype_s.setText("Case Study");
                        Recycler_answer.setVisibility(View.VISIBLE);
                        recyclerTitle.setVisibility(View.VISIBLE);
                        relCaseStdy.setVisibility(View.VISIBLE);
                        caseStudyAnsSelectList = new ArrayList<>();
                        caseStudyAnsSelectList.clear();
                        int oi = 0;
                        for (int j = 0; j < pojoQuestions.getDetails().get(i).getCase_study_ques_details().get(caseStdyI).getCasestudyMcqs().size(); j++) {
                            if (pojoQuestions.getDetails().get(i).getCase_study_ques_details().get(caseStdyI)
                                    .getCasestudyMcqs().get(j).getTypes().equalsIgnoreCase("Right")) {
                                oi++;
                            }
                        }
                        String questiontype = "single";
                        if (oi > 1) {
                            questiontype = "multiple";
                        }
                        //caseStudyAnsSelectList.add(new CaseStudyAnsSelected(caseStdyI+"", "",oi+"",""));

                        CaseStudyTitleAdapter titleAdapter = new CaseStudyTitleAdapter(requireContext(), pojoQuestions.getDetails().get(i), caseStdyI + "", this);
                        recyclerTitle.setAdapter(titleAdapter);

                        adapterCase = new CaseStudyAnsAdapter(pojoQuestions.getDetails().get(i), "question", totalRightAns, questiontype);
                        Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                                , LinearLayoutManager.VERTICAL, false));
                        Recycler_answer.setAdapter(adapterCase);
                        String ques = pojoQuestions.getDetails().get(i).getCase_study_details().get(0).getCase();
                        llimage.setVisibility(View.GONE);
                        // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                        formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true));
                        tvReadMore.setOnClickListener(v1 -> getCaseStudyReadMoreDialog(getLatexSolvedQ_Str(ques, true)));

                        if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                            llsolution.setVisibility(View.GONE);
                        } else {
                            llsolution.setVisibility(View.VISIBLE);
                        }
                        if (questiontype.toLowerCase().trim().contains("single")) {
                            getFlippMarks("single");
                        } else if (questiontype.toLowerCase().trim().contains("multiple")) {
                            getFlippMarks("multiple");
                        }
                        if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                            Log.d("getQues_Image", "getQues_Image: 1263");
                            llimage_case.setVisibility(View.VISIBLE);
                            Glide.with(getActivity())
                                    .load(pojoQuestions.getDetails().get(i).getImage())
                                    .into(iv_quesimageCase);
                        }
                    } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Assertion Reasoning")) {
                        llnumeric.setVisibility(View.GONE);
                        llQues1.setVisibility(View.GONE);
                        tvReadMore.setVisibility(View.GONE);
                        recyclerTitle.setVisibility(View.GONE);
                        llQues.setVisibility(View.GONE);
                        tvSereasRes.setText("Assertion");
                        tvSereasResAsrtion.setText("Reasoning");
                        tv_quetype.setText("Assertion Reasoning");
                        //   tv_quetype_s.setText("Assertion Reasoning");
                        Recycler_answer.setVisibility(View.VISIBLE);
                        relMain_asrtion.setVisibility(View.VISIBLE);
                        relCaseStdy.setVisibility(View.VISIBLE);
                        caseStudyAnsSelectList = new ArrayList<>();
                        caseStudyAnsSelectList.clear();
                        int oi = 0;
                        for (int k = 0; k < pojoQuestions.getDetails().get(i).getAnswer_details().size(); k++) {
                            if (pojoQuestions.getDetails().get(i).getAnswer_details().get(k).getTypes().equalsIgnoreCase("Right")) {
                                totalRightAns++;
                            }
                        }
                        String questiontype = "single";
                        if (oi > 1) {
                            questiontype = "multiple";
                        }
                        getSetDefineType(pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);

                        queadapter = new queOptionsAdapter(pojoQuestions.getDetails().get(i).getAnswer_details(), "question", pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                        Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                                , LinearLayoutManager.VERTICAL, false));
                        Recycler_answer.setAdapter(queadapter);
                        String ques = pojoQuestions.getDetails().get(i).getAssertion_reasoning_details().get(0).getAssertion();
                        formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true));
                        String quesReason = pojoQuestions.getDetails().get(i).getAssertion_reasoning_details().get(0).getReasoning();
                        formulaOne1ResAsrtion.setDisplayText(getLatexSolvedQ_Str(quesReason, true));

                        llimage.setVisibility(View.GONE);//
                        if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                            llsolution.setVisibility(View.GONE);
                        } else {
                            llsolution.setVisibility(View.VISIBLE);
                        }

                        if (questiontype.toLowerCase().trim().contains("single")) {
                            getFlippMarks("single");
                        } else if (questiontype.toLowerCase().trim().contains("multiple")) {
                            getFlippMarks("multiple");
                        }
                        if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                            Log.d("getQues_Image", "getQues_Image: 1319");
                            llimage_case.setVisibility(View.VISIBLE);
                            Glide.with(getActivity())
                                    .load(pojoQuestions.getDetails().get(i).getImage())
                                    .into(iv_quesimageCase);
                        }
                    }

                } else {

                    // else
                    i++;
                    questionId = pojoQuestions.getDetails().get(i).getQuestions_all_types_id();
                    updateCurentIndexForStudentQues(true);
                    if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Numerical"))
                        n_Visualisation_ques();
                    else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Diagram")) {
                        tv_quetype.setText("Label The Diagram");
                        //   tv_quetype_s.setText("Label The Diagram");
                        llQues.setVisibility(View.GONE);
                        llDiagram.setVisibility(View.VISIBLE);
                        llnumeric.setVisibility(View.GONE);
                        llmcq.setVisibility(View.GONE);
                        lldescriptive.setVisibility(View.GONE);
                        Recycler_answer.setVisibility(View.GONE);
                        horizontal_fillup.setVisibility(View.GONE);

                        rlLoader.setVisibility(View.VISIBLE);

                        onDiagramLoadFrag.diagramLoad();
                        //setTheDiagramLables(pojoQuestions.getDetails().get(i));
                        rlLoader.setVisibility(View.GONE);
                        //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Fillup")) {
                        tv_quetype.setText("Fillup");
                        // tv_quetype_s.setText("Fillup");
                        llQues.setVisibility(View.GONE);
                        llDiagram.setVisibility(View.VISIBLE);
                        llnumeric.setVisibility(View.GONE);
                        llmcq.setVisibility(View.GONE);
                        lldescriptive.setVisibility(View.GONE);
                        horizontal_fillup.setVisibility(View.VISIBLE);
                        Recycler_answer.setVisibility(View.GONE);
                        Recycler_answer.setVisibility(View.GONE);

                        llsolution.setVisibility(View.GONE);
                        rlLoader.setVisibility(View.VISIBLE);

                        SetFillUpNewSpan(pojoQuestions.getDetails().get(i));
                        // setTheFillUps_Blanks(pojoQuestions.getDetails().get(i));
                        rlLoader.setVisibility(View.GONE);

                        //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);

                    } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Case Study")) {
                        llnumeric.setVisibility(View.GONE);
                        llQues1.setVisibility(View.GONE);
                        llQues.setVisibility(View.GONE);
                        tvSereasRes.setText("Case");
                        tv_quetype.setText("Case Study");
                        //  tv_quetype_s.setText("Case Study");
                        Recycler_answer.setVisibility(View.VISIBLE);
                        recyclerTitle.setVisibility(View.VISIBLE);
                        relCaseStdy.setVisibility(View.VISIBLE);
                        recyclerTitle.setVisibility(View.VISIBLE);

                        caseStudyAnsSelectList = new ArrayList<>();
                        caseStudyAnsSelectList.clear();
                        int oi = 0;
                        for (int j = 0; j < pojoQuestions.getDetails().get(i).getCase_study_ques_details().get(caseStdyI).getCasestudyMcqs().size(); j++) {
                            if (pojoQuestions.getDetails().get(i).getCase_study_ques_details().get(caseStdyI)
                                    .getCasestudyMcqs().get(j).getTypes().equalsIgnoreCase("Right")) {
                                oi++;
                            }
                        }
                        String questiontype = "single";
                        if (oi > 1) {
                            questiontype = "multiple";
                        }

                        CaseStudyTitleAdapter titleAdapter = new CaseStudyTitleAdapter(requireContext(), pojoQuestions.getDetails().get(i), caseStdyI + "", this);
                        recyclerTitle.setAdapter(titleAdapter);

                        adapterCase = new CaseStudyAnsAdapter(pojoQuestions.getDetails().get(i), "question", totalRightAns, questiontype);
                        Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                                , LinearLayoutManager.VERTICAL, false));
                        Recycler_answer.setAdapter(adapterCase);
                        String ques = pojoQuestions.getDetails().get(i).getCase_study_details().get(0).getCase();
                        llimage.setVisibility(View.GONE);

                        formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true));
                        tvReadMore.setOnClickListener(v1 -> getCaseStudyReadMoreDialog(getLatexSolvedQ_Str(ques, true)))
                        ;

                        if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                            llsolution.setVisibility(View.GONE);
                        } else {
                            llsolution.setVisibility(View.VISIBLE);
                        }
                        if (questiontype.toLowerCase().trim().contains("single")) {
                            getFlippMarks("single");
                        } else if (questiontype.toLowerCase().trim().contains("multiple")) {
                            getFlippMarks("multiple");
                        }
                        if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                            Log.d("getQues_Image", "getQues_Image: 1427");
                            llimage_case.setVisibility(View.VISIBLE);
                            Glide.with(getActivity())
                                    .load(pojoQuestions.getDetails().get(i).getImage())
                                    .into(iv_quesimageCase);
                        }
                    } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Assertion Reasoning")) {
                        llnumeric.setVisibility(View.GONE);
                        llQues1.setVisibility(View.GONE);
                        tvReadMore.setVisibility(View.GONE);
                        recyclerTitle.setVisibility(View.GONE);
                        llQues.setVisibility(View.GONE);
                        tvSereasRes.setText("Assertion");
                        tvSereasResAsrtion.setText("Reasoning");
                        tv_quetype.setText("Assertion Reasoning");
                        //   tv_quetype_s.setText("Assertion Reasoning");
                        Recycler_answer.setVisibility(View.VISIBLE);
                        relMain_asrtion.setVisibility(View.VISIBLE);
                        relCaseStdy.setVisibility(View.VISIBLE);
                        caseStudyAnsSelectList = new ArrayList<>();
                        caseStudyAnsSelectList.clear();
                        int oi = 0;
                        for (int k = 0; k < pojoQuestions.getDetails().get(i).getAnswer_details().size(); k++) {
                            if (pojoQuestions.getDetails().get(i).getAnswer_details().get(k).getTypes().equalsIgnoreCase("Right")) {
                                totalRightAns++;
                            }
                        }
                        String questiontype = "single";
                        if (oi > 1) {
                            questiontype = "multiple";
                        }
                        getSetDefineType(pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                        queadapter = new queOptionsAdapter(pojoQuestions.getDetails().get(i).getAnswer_details(), "question", pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                        Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                                , LinearLayoutManager.VERTICAL, false));
                        Recycler_answer.setAdapter(queadapter);
                        String ques = pojoQuestions.getDetails().get(i).getAssertion_reasoning_details().get(0).getAssertion();
                        formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true));
                        String quesReason = pojoQuestions.getDetails().get(i).getAssertion_reasoning_details().get(0).getReasoning();
                        formulaOne1ResAsrtion.setDisplayText(getLatexSolvedQ_Str(quesReason, true));

                        llimage.setVisibility(View.GONE);//
                        if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                            llsolution.setVisibility(View.GONE);
                        } else {
                            llsolution.setVisibility(View.VISIBLE);
                        }

                        if (questiontype.toLowerCase().trim().contains("single")) {
                            getFlippMarks("single");
                        } else if (questiontype.toLowerCase().trim().contains("multiple")) {
                            getFlippMarks("multiple");
                        }
                        if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                            Log.d("getQues_Image", "getQues_Image: 1482");
                            llimage_case.setVisibility(View.VISIBLE);
                            Glide.with(getActivity())
                                    .load(pojoQuestions.getDetails().get(i).getImage())
                                    .into(iv_quesimageCase);
                        }
                    } else {
                        count++;
                        selectedanswer = new ArrayList<>();

                        viewapply.setBackgroundColor(getResources().getColor(R.color.parrotgreen));
                        tv_apply.setBackgroundResource(R.drawable.round_boundary_green_new);

                        rlLoader.setVisibility(View.VISIBLE);

                        n_apply_marks = questTypeMarks;
                        //  tolalResutSize = getDetails().get(i).getN_application_val_details().size() + getDetails().get(i).getN_application_details().size();
                        try {
                            if (pojoQuestions.getDetails().get(i).getN_application_val_details() != null) {
                                tolalResutSize = pojoQuestions.getDetails().get(i).getN_application_val_details().size();

                            }
                            if (pojoQuestions.getDetails().get(i).getN_application_details() != null) {
                                tolalResutSize = pojoQuestions.getDetails().get(i).getN_application_details().size();

                                for (int kk = 0; kk < pojoQuestions.getDetails().get(i).getN_application_details().size(); kk++) {
                                    editTagsList.add("");
                                    //System.out.println("setEquationtolalResutSize.." + EditTagsList.size());
                                }
                            }
                            //tolalResutSize = getDetails().get(i).getN_application_val_details().size()+getDetails().get(i).getN_application_details().size();
                            // System.out.println("tolalResutSize.." + tolalResutSize + ".." + getDetails().get(i).getN_application_val_details().size() + "...." + getDetails().get(i).getN_application_details().size());
                        } catch (Exception exc) {
                            exc.printStackTrace();
                        }

                        adapterA = new applyAdapter(pojoQuestions.getDetails().get(i), "question2");
                        Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                                , LinearLayoutManager.VERTICAL, false));
                        Recycler_answer.setAdapter(adapterA);
                        String ques = pojoQuestions.getDetails().get(i).getQuestion();
                        // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                        formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                        rlLoader.setVisibility(View.GONE);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("catch_exception 1554", "catch_exception:" + e.toString());
            apiHit = 2;

        }
    }

    private void n_Visualisation_ques() {
        try {
            if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Numerical")) {
                llmcq.setVisibility(View.GONE);
                lldescriptive.setVisibility(View.GONE);
                llnumeric.setVisibility(View.VISIBLE);
                Recycler_answer.setVisibility(View.VISIBLE);
                horizontal_fillup.setVisibility(View.GONE);

                if (count == 0 || count == -10) {
                    count = 1;
                    selectedanswer = new ArrayList<>();
                    tv_visualize.setBackgroundResource(R.drawable.round_boundary_green_new);
                    viewAnswer.setBackgroundColor(getResources().getColor(R.color.parrotgreen));

                    rlLoader.setVisibility(View.VISIBLE);

                    horizontal_fillup.setVisibility(View.GONE);
                    for (int k = 0; k <= pojoQuestions.getDetails().get(i).getN_visualization_ans_details().size() - 1; k++) {
                        if (pojoQuestions.getDetails().get(i).getN_visualization_ans_details().get(k).getVisualizationRightWrong().equalsIgnoreCase("Right")) {
                            totalRightAns++;
                        }
                    }
                    nuricalAdapter = new nuricalAdapter(pojoQuestions.getDetails().get(i), "question1", totalRightAns);
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(nuricalAdapter);
                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    rlLoader.setVisibility(View.GONE);


                } else {
                    count++;
                    selectedanswer = new ArrayList<>();

                    horizontal_fillup.setVisibility(View.GONE);
                    n_apply_marks = questTypeMarks;
                    viewapply.setBackgroundColor(getResources().getColor(R.color.parrotgreen));
                    tv_apply.setBackgroundResource(R.drawable.round_boundary_green_new);

                    rlLoader.setVisibility(View.VISIBLE);

                    try {
                        if (pojoQuestions.getDetails().get(i).getN_application_val_details() != null) {
                            tolalResutSize = pojoQuestions.getDetails().get(i).getN_application_val_details().size();

                        }
                        if (pojoQuestions.getDetails().get(i).getN_application_details() != null) {
                            tolalResutSize = pojoQuestions.getDetails().get(i).getN_application_details().size();
                            for (int kk = 0; kk < pojoQuestions.getDetails().get(i).getN_application_details().size(); kk++) {
                                editTagsList.add("");
                                System.out.println("setEquationtolalResutSize.." + editTagsList.size());
                            }

                        }
                        //tolalResutSize = pojoQuestions.getDetails().get(i).getN_application_val_details().size()+pojoQuestions.getDetails().get(i).getN_application_details().size();
                        // System.out.println("tolalResutSize.." + tolalResutSize + ".." + pojoQuestions.getDetails().get(i).getN_application_val_details().size() + "...." + pojoQuestions.getDetails().get(i).getN_application_details().size());
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }

                    adapterA = new applyAdapter(pojoQuestions.getDetails().get(i), "question2");
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(adapterA);

                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    rlLoader.setVisibility(View.GONE);

                }
            }
        } catch (IndexOutOfBoundsException inEx) {
            inEx.printStackTrace();
        }
    }


    private void getSetDefineType(String define_type, int totalRightAns) {
        // "Image MCQ", "Multiple Image MCQ", "Single Image MCQ", "Multiple Text MCQ",  "Single Text MCQ"
        String type_Define = define_type;
        if (define_type.equalsIgnoreCase("Text MCQ")) {
            if (totalRightAns > 1) type_Define = "Multiple Text MCQ";
            else type_Define = "Single Text MCQ";
        } else if (define_type.equalsIgnoreCase("Single Text MCQ")) {
            if (totalRightAns > 1) type_Define = "Multiple Text MCQ";
        } else if (define_type.equalsIgnoreCase("Image MCQ")) {
            if (totalRightAns > 1) type_Define = "Multiple Image MCQ";
            else type_Define = "Single Image MCQ";
        } else if (define_type.equalsIgnoreCase("Single Image MCQ")) {
            if (totalRightAns > 1) type_Define = "Multiple Image MCQ";
        }
        pojoQuestions.getDetails().get(i).setDefine_type(type_Define);
        Log.e("getSetDefineType: ", "" + pojoQuestions.getDetails().get(i).getDefine_type() + "..." + type_Define + "....." + define_type);
    }

    private void getCaseStudyReadMoreDialog(String latexSolvedQ_str) {
        try {
            AlertDialog dialog = new AlertDialog.Builder(requireContext()).create();
            View binding = LayoutInflater.from(getContext()).inflate(
                    R.layout.layout_alert_read_more_row, null, false);

            MathView tvQuesDetailCase = binding.findViewById(R.id.tv_QuesDetail_case);
            ImageView ivClose = binding.findViewById(R.id.iv_close);
            TextView tvQuetypeCase = binding.findViewById(R.id.tv_quetype_case);
            tvQuetypeCase.setText("Case");
            tvQuesDetailCase.setDisplayText(latexSolvedQ_str);
            ivClose.setOnClickListener(v -> dialog.dismiss());
            dialog.setView(binding);
            // }
            dialog.show();
        } catch (Exception exc) {
            exc.printStackTrace();
            //   binding.progressbbar.setVisibility(View.GONE);
        }
    }

    private void initViews() {
        userList = new ArrayList<>(); //rvOverAllScores, rvQuesTopScores
        constraintParticipents = view.findViewById(R.id.constraintParticipents);
        tvUpdate = view.findViewById(R.id.tvUpdate);
        tv_highLow = view.findViewById(R.id.tv_highLow);
        tv_highLow_ques = view.findViewById(R.id.tv_highLow_ques);
        rvOverAllScores = view.findViewById(R.id.rvOverAllScores);
        rvQuesTopScores = view.findViewById(R.id.rvQuesTopScores);
        tvnext = view.findViewById(R.id.tvnext);
        llEndLive = view.findViewById(R.id.llEndLive);
        rightVlaue = view.findViewById(R.id.tvRightValue);
        wrongVlaue = view.findViewById(R.id.tvWrongValue);
        neutralValue = view.findViewById(R.id.tvWrongValue_skyblue);
        tv_confidence = view.findViewById(R.id.tv_confidence);
        rellSupSup = view.findViewById(R.id.rellSupSup);
        cmTimer = view.findViewById(R.id.cTimer);
        tv_learn = view.findViewById(R.id.tv_learn);
        relCaseStdy = view.findViewById(R.id.relCaseStdy);
        ivRefresh = view.findViewById(R.id.ivRefresh);
        Recycler_answer = view.findViewById(R.id.Recycler_answer);
        Tv_Qustion = view.findViewById(R.id.Tv_Qustion);
        tv_quePoints = view.findViewById(R.id.tv_quePoints);
        tv_quePoints = view.findViewById(R.id.tv_quePoints);
        tv_solutionbtn = view.findViewById(R.id.tv_solutionbtn);

        iv_right_showallData = view.findViewById(R.id.ivRightValue);    // Step 2
        ivWrongAttemtUser = view.findViewById(R.id.ivWrongValue);
        ivNotAttemptUser = view.findViewById(R.id.ivWrongValue_skyblue);

        rlLoader = view.findViewById(R.id.rlLoader);
        llsolnext = view.findViewById(R.id.llsolnext);
        llsolution = view.findViewById(R.id.llsolution);
        llnext = view.findViewById(R.id.llnext);
        llanser = view.findViewById(R.id.llanser);
        spinnerSpeak = view.findViewById(R.id.spinnerSpeak);
        tv_spinner = view.findViewById(R.id.tv_spinner);
        CardTypeAns = view.findViewById(R.id.CardTypeAns);
        scrollBar_solution = view.findViewById(R.id.scrollBar_solution);
        scrollBar_ = view.findViewById(R.id.scrollBar_);
        iv_quesimage = view.findViewById(R.id.iv_quesimage);
        iv_quesimageCase = view.findViewById(R.id.iv_quesimageCase);
        llimage = view.findViewById(R.id.llimage);
        llimage_case = view.findViewById(R.id.llimage_case);
        youtubeFrameLayout=view.findViewById(R.id.youtube_fragment);
        tv_quetype = view.findViewById(R.id.tv_quetype);
        tv_quetype_s1 = view.findViewById(R.id.tv_quetype_s);
//        llmcq = view.findViewById(R.id.llmcq);
        llmcq = view.findViewById(R.id.llmcq);
        llnumeric = view.findViewById(R.id.llnumeric);
        llQues = view.findViewById(R.id.llQues);
        llQues1 = view.findViewById(R.id.llQues1);
        llDiagram = view.findViewById(R.id.llDiagram);
        relDiagText = view.findViewById(R.id.rel_DiagText);
        llMainLay = view.findViewById(R.id.llMainLay);
        Topbar = view.findViewById(R.id.Topbar);
        iv_Diaggram = view.findViewById(R.id.iv_Diaggram);
        tv_visualize = view.findViewById(R.id.tv_visualize);
        tv_Interpret = view.findViewById(R.id.tv_Interpret);
        viewAnswer = view.findViewById(R.id.viewAnswer);
        viewapply = view.findViewById(R.id.viewapply);
        tv_apply = view.findViewById(R.id.tv_apply);
        lldescriptive = view.findViewById(R.id.lldescriptive);
        horizontal_fillup = view.findViewById(R.id.horizontal_fillup);
        tv_visualize1 = view.findViewById(R.id.tv_visualize1);
        tv_answer1 = view.findViewById(R.id.tv_answer1);
        tv_time2 = view.findViewById(R.id.tv_time2);
        viewAnswer1 = view.findViewById(R.id.viewAnswer1);
        rlanswerframe = view.findViewById(R.id.rlanswerframe);
        rlspeaker = view.findViewById(R.id.rlspeaker);
        iv_speaker = view.findViewById(R.id.iv_speaker);
        et_answer = view.findViewById(R.id.et_answer);
        tv_anserstatus = view.findViewById(R.id.tv_anserstatus);
        tv_chapterPages = view.findViewById(R.id.tv_chapterPages);
        rlspeaker = view.findViewById(R.id.rlspeaker);
        tv_chapterPages1 = view.findViewById(R.id.tv_chapterPages1);
        backBtn = view.findViewById(R.id.Back);
        formula_one = view.findViewById(R.id.formula_one);
        tv_solutions1M = view.findViewById(R.id.tv_solutions1M);
        tv_QuesDetail_sM = view.findViewById(R.id.tv_QuesDetail_sM);
        tv_sub = view.findViewById(R.id.tv_sub);
        tv_sup = view.findViewById(R.id.tv_sup);
        tv_Done = view.findViewById(R.id.tv_Done);
        et_value1 = view.findViewById(R.id.et_value2);
        fbvContent = view.findViewById(R.id.fbv_content);
        tvFillUpAns = view.findViewById(R.id.tvFillUpAns);
        framehoome1 = view.findViewById(R.id.framehoome1);
        tv_learn = view.findViewById(R.id.tv_learn);
        tv_quePoints1 = view.findViewById(R.id.tv_quePoints1);
        tvNextBtnCount = view.findViewById(R.id.tvNextBtnCount);
        expanded_image = view.findViewById(R.id.expanded_image);
        relMain = view.findViewById(R.id.relMain);
        Card = view.findViewById(R.id.Card);
        formulaOne1Res = view.findViewById(R.id.formula_one1_res);
        formulaOne1ResAsrtion = view.findViewById(R.id.formula_one1_res_asrtion);
        tvReadMore = view.findViewById(R.id.tvReadMore);
        tvSereasRes = view.findViewById(R.id.tv_sereas_res);
        recyclerTitle = view.findViewById(R.id.recycler_Title);
        tvSereasResAsrtion = view.findViewById(R.id.tv_sereas_res_asrtion);
        relMain_asrtion = view.findViewById(R.id.relMain_asrtion);
        iv_quries = view.findViewById(R.id.iv_quries);
        relMain.setBackgroundColor(getResources().getColor(R.color.navicolor));
        formula_one.setTextColor(getResources().getColor(R.color.navicolor));
        try {
            tv_time2.setText(" " + liveCode_);
            // String marks = pref.getString("total_marks_s", "").isEmpty()? "0" :pref.getString("total_marks_s", "") ;
            // tv_TotalMarks.setText(String.format("%.1f", Float.parseFloat(marks)));

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        // tv_time.setText();
    }

    private void setPointAnimaion() {
        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

    }

    private void getQues_Image() {
        Log.d("getQues_Image", "getQues_Image: 1763");
        try {
            expanded_image.setVisibility(View.GONE);
            if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                llimage.setVisibility(View.VISIBLE);
                Glide.with(getActivity())
                        .load(pojoQuestions.getDetails().get(i).getImage())
                        .into(iv_quesimage);
                expanded_image.setVisibility(View.GONE);

                iv_quesimage.setOnClickListener(v1 -> {
                    Log.d("getQues_Image", "getQues_Image: 1872");
                    expanded_image.setVisibility(View.VISIBLE);
                    Global.zoomTitleImage(iv_quesimage, pojoQuestions.getDetails().get(i).getImage(), getActivity(), expanded_image, relMain, shortAnimationDuration);
                });

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    private void setSpeekSpinner() {
        final List<String> getVesselNames = new ArrayList<String>();
        getVesselNames.add("Type Answer");
        getVesselNames.add("Speak Answer");
        tv_spinner.setText("Type Answer");
        ArrayAdapter<String> dataAd1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getVesselNames);
        dataAd1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpeak.setAdapter(dataAd1);
        // spinnerSpeak.setVisibility(View.INVISIBLE);
        tv_spinner.setVisibility(View.INVISIBLE);
        tv_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerSpeak.performClick();
            }
        });
        spinnerSpeak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) view).setTextColor(Color.WHITE);
                ((TextView) view).setTextSize(latetex_dim_15);
                tv_spinner.setText(adapterView.getItemAtPosition(i) + "");
                if (tv_spinner.getText().toString().equalsIgnoreCase("Speak Answer")) {
                    llanser.setVisibility(View.VISIBLE);
                    iv_speaker.setVisibility(View.VISIBLE);
                    CardTypeAns.setVisibility(View.VISIBLE);
                } else {
                    llanser.setVisibility(View.VISIBLE);
                    CardTypeAns.setVisibility(View.VISIBLE);
                    iv_speaker.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        tv_anserstatus.setText("Start Speaking your answer...");
        tv_anserstatus.setTextColor(getActivity().getResources().getColor(R.color.white));
    }

    private void llNextClicked_objects() {
        try {
            if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                llimage.setVisibility(View.VISIBLE);
                getQues_Image();
            }

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
//             getActivity().onBackPressed();
            ((HomeActivity) getActivity()).onEndlive_Back();
        }

        try {
            if (mCountDownTimer != null) mCountDownTimer.cancel();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        try {
            formula_one.setTextColor(getResources().getColor(R.color.navicolor));
            tvNextBtnCount.setVisibility(View.GONE);
            rvQuesTopScores.setVisibility(View.GONE);
            isPausedForced = false;
            isCountDownOnNext = false;
            isQuesAnsCorrect = false;
            setScrollbarMcqVisibility();
            tvFillUpAns.setText("");
            editTagsList.clear();
            totalRightAns = 0;
            cmTimer.stop();
            totalSeconds = 0;
            cmTimer.start();
            et_answer.setText("");
            setSpeekSpinner();

            llQues.setVisibility(View.VISIBLE);
            llQues1.setVisibility(View.VISIBLE);
            llDiagram.setVisibility(View.GONE);
            relCaseStdy.setVisibility(View.GONE);
            horizontal_fillup.setVisibility(View.GONE);
            rlspeaker.setVisibility(View.GONE);
            llsolution.setVisibility(View.GONE);

        } catch (Exception exc) {
            exc.printStackTrace();
        }


    }

    private void SetFillUpNewSpan(Detail detail) {
        try {

            String content3 = detail.getQuestion().replace(".", " .");


            if (!(content3.charAt(content3.length() - 1) + "").equals(".")) {
                content3 = content3 + " .";
            }

            Log.d("RangeListSize", content3);

            fbvContent.setVisibility(View.VISIBLE);
            List<String> lines = new ArrayList<String>();

            List<AnswerRange> rangeList = new ArrayList<>();
            lines.clear();
            // String[] words = {};
            String[] words = content3.split(" ");
            lines = Arrays.asList(words);
            int count = 1, charCount = 0;
            Log.d("RangeListSize", "HElloLoope" + lines.size() + "");
            for (int k = 0; k < lines.size(); k++) {

                if (lines.get(k).equalsIgnoreCase("##" + count)) {
                    String sss = lines.get(k).replace("##" + count, "" + detail.getFillup_answer_details().get(count - 1).getFillupAnswers());
                    //System.out.print("targett.." + " ##" + count + "replacement+.. " + "-----" + sss);
                    lines.set(k, sss);
                    int chr = content3.trim().indexOf("##" + count);
                    rangeList.add(new AnswerRange(chr + count - 1 + charCount, chr + detail.getFillup_answer_details().get(count - 1).getFillupAnswers().length() + count + charCount));
                    count++;
                    charCount++;
                }
            }
            lines2 = lines;
            String ansfillup = convertStrigbuilder2(lines, " ");
            // System.out.println("spanArrayswords011.." + ansfillup);
            submitSpeeak = 3;
            Log.d("RangeListSize", "HElloRangelist" + rangeList.size() + "");
            fbvContent.setData(ansfillup, rangeList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String convertStrigbuilder2(List<String> se, String s) {
        StringBuilder sb = new StringBuilder();
        for (String ss : se)
            sb.append(ss).append(s);
        return sb.substring(0, sb.length() - 1);
    }

    private String convertStrigbuilder3(List<String> se, String s, String s1) {
        StringBuilder sb = new StringBuilder();
        int cont = 0;
        for (String ss : se) {
            cont++;
            //sb.append(s1 + cont + "=").append(ss).append(s);
            sb.append(cont + "=").append(ss).append(s);
        }
        return sb.substring(0, sb.length() - 1);
    }


    private void setTheDiagramLables(Detail detail) {
        //relDiagText.removeView(v);
        editTextsList = new ArrayList<>();
        editTextsList.clear();
        editClicked = 0;
        if (v != null) {
            // relDiagText.removeView(v);
            relDiagText.removeView(v);
            editTextsList.clear();
            System.out.println("relDiagText.removeView(v);.......");
            // relDiagText.requestLayout();
        }

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        System.out.println("m_heightWidth,,," + width + " height.." + height);
        if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
            Glide.with(getActivity())
                    .load(detail.getImage())
                    .into(iv_Diaggram);
        }
        RelativeLayout.LayoutParams paramss =
                new RelativeLayout.LayoutParams(width, width);
        iv_Diaggram.setLayoutParams(paramss);
        LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < detail.getDiagram_answer_details().size(); i++) {

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);

            Double x = (Double.parseDouble(detail.getDiagram_answer_details().get(i).getXValue()) * width) / 100;
            Double y = (Double.parseDouble(detail.getDiagram_answer_details().get(i).getYValue()) * width) / 100;
            int xs = (int) Math.round(x);
            int ys = (int) Math.round(y);
            params.setMargins(xs, ys, 0, 0);

            v = vi.inflate(R.layout.edit_tv_layout, null);
            RelativeLayout relMainLay = v.findViewById(R.id.mainLay);
            EditText edittTxt = v.findViewById(R.id.idicatorView);
            EditText edittTxt2 = v.findViewById(R.id.etText);
            ImageView ic_cancel = v.findViewById(R.id.ic_cancel);
            ImageView ic_right = v.findViewById(R.id.ic_right);
            edittTxt2.setVisibility(View.INVISIBLE);
            edittTxt.setEnabled(true);
            edittTxt2.setText("");
            relMainLay.setLayoutParams(params);
            edittTxt.setMaxWidth(350);
            edittTxt.setMinWidth(200);

            edittTxt.setBackgroundColor(getResources().getColor(R.color.navicolor_light_edittext));

            if (editClicked == 1) {
                edittTxt.setEnabled(false);
            } else {
                editTextsList.add(edittTxt2);
            }

            edittTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edittTxt2.setVisibility(View.VISIBLE);
                    edittTxt2.callOnClick();
                    edittTxt2.requestFocus();
                    edittTxt2.setFocusableInTouchMode(true);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(edittTxt2, InputMethodManager.SHOW_FORCED);
                    editClicked = 1;
                }
            });

            selectEditDiagrm = new ArrayList<>();
            selectEditDiagrm.clear();

            System.out.println("editTextsList..size.." + editTextsList.size());

            relDiagText.addView(v);
            //}
        }

    }

    private void speakfun() {

        try {
            String ans = "";
            String[] keywords = pojoQuestions.getDetails().get(i).getDescriptive_chapterkeyword_details().get(0).getChapterKeyword().toLowerCase().split(", ");
            for (int ii = 0; ii < pojoQuestions.getDetails().get(i).getDescriptive_answer_details().size(); ii++) {
                if (pojoQuestions.getDetails().get(i).getDescriptive_answer_details().get(ii).getTxt_type().equalsIgnoreCase("Right")) {
                    ans = pojoQuestions.getDetails().get(i).getDescriptive_answer_details().get(ii).getRight_txt_ans().replace("<p>", "").replace("</p>", "").toLowerCase();
                }
            }
            et_answer.setEnabled(false);
            et_answer.setText(pojoQuestions.getDetails().get(i).getDescriptive_chapterkeyword_details().get(0).getChapterKeyword());
            String[] answer = ans.split(" ");
            System.out.println("answer...." + ans + "...." + answer.length + "..." + pojoQuestions.getDetails().get(i).getDescriptive_chapterkeyword_details().get(0).getChapterKeyword() + "..." + keywords.toString());
            /* to remove duplicate values from array*/
            HashSet hsnameanswer = new HashSet();

            HashSet hsname = new HashSet();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                hsnameanswer.addAll(Arrays.stream(answer).collect(Collectors.toList()));
                Arrays.stream(answer).collect(Collectors.toList()).clear();
                Arrays.stream(answer).collect(Collectors.toList()).addAll(hsnameanswer);


                hsname.addAll(Arrays.stream(keywords).collect(Collectors.toList()));
                Arrays.stream(keywords).collect(Collectors.toList()).clear();
                Arrays.stream(keywords).collect(Collectors.toList()).addAll(hsname);
            }

            Set<String> finalKeywords = new HashSet<String>(hsname);
            finalKeywords.retainAll(hsnameanswer);
            Log.e("catch_exception", "resultt:545:" + finalKeywords);
            et_answer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int ii, int i1, int i2) {
                    try {

                        String[] written = charSequence.toString().split(" ");

                        /* to remove duplicate values from array of written words*/
                        HashSet hwritten = new HashSet();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            hwritten.addAll(Arrays.stream(written).collect(Collectors.toList()));
                            Arrays.stream(written).collect(Collectors.toList()).clear();
                            Arrays.stream(written).collect(Collectors.toList()).addAll(hwritten);
                        }


                        ArrayList<String> arywrittn = new ArrayList<>();
                        arywrittn.addAll(hwritten);

                        // Prepare an intersection
                        Set<String> intersection = new HashSet<String>(finalKeywords);
                        intersection.retainAll(arywrittn);

                        //todo: find the percentage
                        int total = finalKeywords.size();
                        int writtenper = intersection.size();
                        float per = (writtenper * 100) / total;
                        Log.e("catch_exception", "value:176:" + " " + intersection.size() + " " + finalKeywords.size() + " " + String.format("%.2f", Float.parseFloat(per + "")));
                        if (per >= 80) {
                            submitSpeeak = -1;
                            CardTypeAns.setVisibility(View.VISIBLE);
                            iv_speaker.setVisibility(View.GONE);
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(et_answer.getWindowToken(), 0);
                            selectedanswer = new ArrayList<>();
                            rlspeaker.setVisibility(View.GONE);
                            Recycler_answer.setVisibility(View.VISIBLE);
                            Recycler_answer.setAdapter(null);
                            answerAdapter = new descriptiveAnswerAdapter(pojoQuestions.getDetails().get(i).getDescriptive_answer_details(), "question2");
                            Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                                    , LinearLayoutManager.VERTICAL, false));
                            Recycler_answer.setAdapter(answerAdapter);
                            String ques = pojoQuestions.getDetails().get(i).getQuestion();
                            Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                            llimage.setVisibility(View.GONE);
                            if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                                Log.d("getQues_Image", "getQues_Image: 2112");
                                Glide.with(getActivity())
                                        .load(pojoQuestions.getDetails().get(i).getImage())
                                        .into(iv_quesimage);

                            }
                            rlLoader.setVisibility(View.GONE);

                        } else if (per > 1 && per < 80) {
                            tv_anserstatus.setText("Looks good, Keep going...");
                            tv_anserstatus.setTextColor(getResources().getColor(R.color.white));
                        } else {
                            tv_anserstatus.setText("Umm are you sure...");
                            tv_anserstatus.setTextColor(getResources().getColor(R.color.litered));
                        }
                    } catch (Exception e) {
                        Log.e("catch_exception", "catch_" + e.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            iv_speaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

        } catch (Exception exc) {
            exc.printStackTrace();
        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    et_answer.append(result.get(0) + " ");
                }
                break;
            }

        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getActivity().getPackageName()));
                startActivity(intent);
                getActivity().finish();
            }
        }
    }

    public void questionListing_liveSession() {
        // Log.e("ashucatchchk294", "student_id:- " + pref.getString("student_id", "")+","+pref.getString("class_id", ""));
        relCaseStdy.setVisibility(View.GONE);
        editTagsList.clear();
        selectedanswer = new ArrayList<>();
        correctAnswer = new ArrayList<>();
        Recycler_answer.setVisibility(View.VISIBLE);
        rlLoader.setVisibility(View.GONE);

        ModelLiveDetail model_ = new ModelLiveDetail(schoolCourseStructureId + "", selectedLevels + ""
                , selectedSubTopics + "", selectedDimensions + "", liveCode_ + "");

        setAdapterOnQuestions((PojMcqQues) getArguments().get("pojomcq"));


    }


    private void setAdapterOnQuestions(PojMcqQues response) {
        try {

            i = 0;//20; //

            pojoQuestions = response;
            if (response.getMessage().equals("No list")) {
                //Toast.makeText(getActivity(), "No questions.. ", Toast.LENGTH_SHORT).show();
                // when comming from notification
                getActivity().onBackPressed();
            } else {
                if (tv_solutionbtn.getText().toString().equalsIgnoreCase("Back")) {
                    setScrollbarMcqVisibility();
                }
                llsolution.setVisibility(View.GONE);
                iv_quries.setVisibility(View.VISIBLE);
                questionId = response.getDetails().get(i).getQuestions_all_types_id();
                Log.d("QuestionId", response.getDetails().get(i).getQuestions_all_types_id());
                Log.e("TAG", "setonNextClicked:questionId.. " + questionId);

                tv_chapterPages.setText("1" + " of " + response.getDetails().size());
                //tv_chapterPages.setText(Global.getTopic_subTopicnameOnTop(topicId, response.getDetails().get(0)));
                tv_chapterPages1.setText(tv_chapterPages.getText().toString());
                getQues_Image();
                cmTimer.start();
                if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Descriptive")) {
                    lldescriptive.setVisibility(View.VISIBLE);
                    adapterDescrip = new descriptiveAdapter(pojoQuestions.getDetails().get(i).getDescriptive_visualization_details(), "question");
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(adapterDescrip);
                    String ques = pojoQuestions.getDetails().get(i).getQuestion();

                    llimage.setVisibility(View.GONE);
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        Log.d("getQues_Image", "getQues_Image: 2234");
                        llimage.setVisibility(View.VISIBLE);
                        Glide.with(getActivity())
                                .load(pojoQuestions.getDetails().get(i).getImage())
                                .into(iv_quesimage);
                    }
                    getFlippMarks("visualization");
                    tv_quetype.setText("Descriptive");
                    //  tv_quetype_s.setText("Descriptive");
                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formula_one.setTextColor(getResources().getColor(R.color.navicolor));
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                        llsolution.setVisibility(View.GONE);
                    } else {
                        llsolution.setVisibility(View.VISIBLE);
                    }

                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text MCQ")
                        || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image MCQ")) {
                    llmcq.setVisibility(View.GONE);

                    //System.out.println("pojoQuestions_size.."+getFlipsMarks().size());
                    for (int k = 0; k < pojoQuestions.getDetails().get(i).getAnswer_details().size(); k++) {
                        if (pojoQuestions.getDetails().get(i).getAnswer_details().get(k).getTypes().equalsIgnoreCase("Right")) {
                            totalRightAns++;
                        }
                    }


                    getSetDefineType(pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                    /*         Collections.shuffle(pojoQuestions.getDetails().get(i).getAnswer_details());*/
                    queadapter = new queOptionsAdapter(pojoQuestions.getDetails().get(i).getAnswer_details(), "question", pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(queadapter);
                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    //getLatexSolvedQ_Str(ques, true);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));

                    llimage.setVisibility(View.GONE);
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        Log.d("getQues_Image", "getQues_Image: 2277");
                        llimage.setVisibility(View.VISIBLE);
                        Glide.with(getActivity())
                                .load(pojoQuestions.getDetails().get(i).getImage())
                                .into(iv_quesimage);
                    }

                    tv_quetype.setText(pojoQuestions.getDetails().get(i).getDefine_type());
                    //   tv_quetype_s.setText(pojoQuestions.getDetails().get(i).getDefine_type());
                    rlLoader.setVisibility(View.GONE);


                    if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                        llsolution.setVisibility(View.GONE);
                    } else {
                        llsolution.setVisibility(View.VISIBLE);
                    }

                    if (pojoQuestions.getDetails().get(i).getDefine_type().toLowerCase().trim().contains("single")) {
                        getFlippMarks("single");
                    } else if (pojoQuestions.getDetails().get(i).getDefine_type().toLowerCase().trim().contains("multiple")) {
                        getFlippMarks("multiple");
                    }

                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Truefalse")
                        || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image Truefalse")
                        || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text Truefalse")) {
                    getFlippMarks("truefalse");


                    adapterTrueFalse = new trueFalseAdapter(pojoQuestions.getDetails().get(i).getTruefalse_answer_details(), "question");
                    //Recycler_answer.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    Recycler_answer.setAdapter(adapterTrueFalse);
                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    llimage.setVisibility(View.GONE);
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        Log.d("getQues_Image", "getQues_Image: 2313");
                        llimage.setVisibility(View.VISIBLE);
                        Glide.with(getActivity())
                                .load(pojoQuestions.getDetails().get(i).getImage())
                                .into(iv_quesimage);
                    }
                    tv_quetype.setText("True or False");
                    //  tv_quetype_s.setText("True or False");

                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                        llsolution.setVisibility(View.GONE);
                    } else {
                        llsolution.setVisibility(View.VISIBLE);
                    }
                    adapterTrueFalse.notifyDataSetChanged();
                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Text Arrange")
                        || pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Image Arrange")) {
                    tv_quetype.setText("Arrange");
                    //  tv_quetype_s.setText("Arrange");


                    Recycler_answer.setVisibility(View.VISIBLE);

                    ansDetail_arng = new ArrayList<>();
                    ansDetail_arng.clear();

                    for (int p = 0; p <= pojoQuestions.getDetails().get(i).getArrange_answer_details().size() - 1; p++) {
                        ansDetail_arng.add(pojoQuestions.getDetails().get(i).getArrange_answer_details().get(p).getTypes());
                    }
                    Collections.sort(pojoQuestions.getDetails().get(i).getArrange_answer_details(),
                            (Comparator) (o1, o2) ->
                            {
                                ArrangeAnswerDetail p1 = (ArrangeAnswerDetail) o1;
                                ArrangeAnswerDetail p2 = (ArrangeAnswerDetail) o2;
                                return p1.getTypes().compareToIgnoreCase(p2.getTypes());
                            });
                    totalRightAns = pojoQuestions.getDetails().get(i).getArrange_answer_details().size();
                    getFlippMarks("arrange");
                    adapter = new arrangeAdapter(pojoQuestions.getDetails().get(i).getArrange_answer_details(), "question", pojoQuestions.getDetails().get(i).getType());
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(adapter);

                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    llimage.setVisibility(View.GONE);
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        Log.d("getQues_Image", "getQues_Image: 2361");
                        llimage.setVisibility(View.VISIBLE);
                        Glide.with(getActivity())
                                .load(pojoQuestions.getDetails().get(i).getImage())
                                .into(iv_quesimage);
                    }
                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                        llsolution.setVisibility(View.GONE);
                    } else {
                        llsolution.setVisibility(View.VISIBLE);
                    }
                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Numerical")) {
                    llnumeric.setVisibility(View.VISIBLE);
                    tv_quetype.setText("Numerical");
                    // tv_quetype_s.setText("Numerical");


                    Recycler_answer.setVisibility(View.VISIBLE);

                    for (int k = 0; k <= pojoQuestions.getDetails().get(i).getN_interpretation_answer_details().size() - 1; k++) {
                        if (pojoQuestions.getDetails().get(i).getN_interpretation_answer_details().get(k).getInterpretationRightWrong().equalsIgnoreCase("Right")) {
                            totalRightAns++;
                        }
                    }
                    nuricalAdapter = new nuricalAdapter(pojoQuestions.getDetails().get(i), "question", totalRightAns);
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(nuricalAdapter);
                    String ques = pojoQuestions.getDetails().get(i).getQuestion();
                    llimage.setVisibility(View.GONE);
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        Log.d("getQues_Image", "getQues_Image: 2395");
                        llimage.setVisibility(View.VISIBLE);
                        Glide.with(getActivity())
                                .load(pojoQuestions.getDetails().get(i).getImage())
                                .into(iv_quesimage);
                    }
                    // Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);
                    formula_one.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                        llsolution.setVisibility(View.GONE);
                    } else {
                        llsolution.setVisibility(View.VISIBLE);
                    }
                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Diagram")) {
                    if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                        llsolution.setVisibility(View.GONE);
                    } else {
                        llsolution.setVisibility(View.VISIBLE);
                    }
                    horizontal_fillup.setVisibility(View.GONE);
                    tv_quetype.setText("Label The Diagram");
                    //   tv_quetype_s.setText("Label The Diagram");
                    llQues.setVisibility(View.GONE);
                    llDiagram.setVisibility(View.VISIBLE);
                    llnumeric.setVisibility(View.GONE);
                    llmcq.setVisibility(View.GONE);


                    lldescriptive.setVisibility(View.GONE);
                    Recycler_answer.setVisibility(View.GONE);
                    getFlippMarks("diagram");
                    setTheDiagramLables(pojoQuestions.getDetails().get(i));

                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Fillup")) {
                    tv_quetype.setText("Fillup");
                    // tv_quetype_s.setText("Fillup");
                    llQues.setVisibility(View.GONE);
                    llDiagram.setVisibility(View.GONE);
                    llnumeric.setVisibility(View.GONE);
                    llmcq.setVisibility(View.GONE);
                    lldescriptive.setVisibility(View.GONE);
                    llsolution.setVisibility(View.GONE);


                    horizontal_fillup.setVisibility(View.VISIBLE);
                    Recycler_answer.setVisibility(View.GONE);
                    getFlippMarks("fillup");
                    SetFillUpNewSpan(pojoQuestions.getDetails().get(i));

                    // setTheFillUps_Blanks(pojoQuestions.getDetails().get(i));
                    //Tv_Qustion.setText(Html.fromHtml(ques), TextView.BufferType.SPANNABLE);

                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Case Study")) {
                    llnumeric.setVisibility(View.GONE);
                    llQues1.setVisibility(View.GONE);
                    llQues.setVisibility(View.GONE);
                    relMain_asrtion.setVisibility(View.GONE);
                    tvSereasRes.setText("Case");
                    tv_quetype.setText("Case Study");
                    //tv_quetype_s.setText("Case Study");
                    tvReadMore.setVisibility(View.VISIBLE);
                    Recycler_answer.setVisibility(View.VISIBLE);
                    recyclerTitle.setVisibility(View.VISIBLE);
                    relCaseStdy.setVisibility(View.VISIBLE);


                    caseStudyAnsSelectList = new ArrayList<>();
                    caseStudyAnsSelectList.clear();
                    int oi = 0;
                    for (int j = 0; j < pojoQuestions.getDetails().get(i).getCase_study_ques_details().get(caseStdyI).getCasestudyMcqs().size(); j++) {
                        if (pojoQuestions.getDetails().get(i).getCase_study_ques_details().get(caseStdyI)
                                .getCasestudyMcqs().get(j).getTypes().equalsIgnoreCase("Right")) {
                            oi++;
                        }
                    }
                    String questiontype = "single";
                    if (oi > 1) {
                        questiontype = "multiple";
                    }
                    //caseStudyAnsSelectList.add(new CaseStudyAnsSelected(caseStdyI+"", "",oi+"",""));

                    CaseStudyTitleAdapter titleAdapter = new CaseStudyTitleAdapter(requireContext(), pojoQuestions.getDetails().get(i), caseStdyI + "", this);
                    recyclerTitle.setAdapter(titleAdapter);

                    adapterCase = new CaseStudyAnsAdapter(pojoQuestions.getDetails().get(i), "question", totalRightAns, questiontype);
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(adapterCase);
                    String ques = pojoQuestions.getDetails().get(i).getCase_study_details().get(0).getCase();
                    llimage.setVisibility(View.GONE);

                    formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    tvReadMore.setOnClickListener(v1 -> getCaseStudyReadMoreDialog(getLatexSolvedQ_Str(ques, true)));

                    if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                        llsolution.setVisibility(View.GONE);
                    } else {
                        llsolution.setVisibility(View.VISIBLE);
                    }

                    if (questiontype.toLowerCase().trim().contains("single")) {
                        getFlippMarks("single");
                    } else if (questiontype.toLowerCase().trim().contains("multiple")) {
                        getFlippMarks("multiple");
                    }
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        Log.d("getQues_Image", "getQues_Image: 2501");
                        llimage_case.setVisibility(View.VISIBLE);
                        Glide.with(getActivity())
                                .load(pojoQuestions.getDetails().get(i).getImage())
                                .into(iv_quesimageCase);
                    }
                } else if (pojoQuestions.getDetails().get(i).getType().equalsIgnoreCase("Assertion Reasoning")) {
                    llnumeric.setVisibility(View.GONE);
                    llQues1.setVisibility(View.GONE);
                    tvReadMore.setVisibility(View.GONE);
                    recyclerTitle.setVisibility(View.GONE);
                    llQues.setVisibility(View.GONE);


                    tvSereasRes.setText("Assertion");
                    tvSereasResAsrtion.setText("Reasoning");
                    tv_quetype.setText("Assertion Reasoning");
                    //  tv_quetype_s.setText("Assertion Reasoning");
                    Recycler_answer.setVisibility(View.VISIBLE);
                    relMain_asrtion.setVisibility(View.VISIBLE);
                    relCaseStdy.setVisibility(View.VISIBLE);
                    caseStudyAnsSelectList = new ArrayList<>();
                    caseStudyAnsSelectList.clear();
                    int oi = 0;
                    for (int k = 0; k < pojoQuestions.getDetails().get(i).getAnswer_details().size(); k++) {
                        if (pojoQuestions.getDetails().get(i).getAnswer_details().get(k).getTypes().equalsIgnoreCase("Right")) {
                            totalRightAns++;
                        }
                    }
                    String questiontype = "single";
                    if (oi > 1) {
                        questiontype = "multiple";
                    }
                    getSetDefineType(pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                    queadapter = new queOptionsAdapter(pojoQuestions.getDetails().get(i).getAnswer_details(), "question", pojoQuestions.getDetails().get(i).getDefine_type(), totalRightAns);
                    Recycler_answer.setLayoutManager(new LinearLayoutManager(getActivity()
                            , LinearLayoutManager.VERTICAL, false));
                    Recycler_answer.setAdapter(queadapter);
                    String ques = pojoQuestions.getDetails().get(i).getAssertion_reasoning_details().get(0).getAssertion();
                    formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ques, true));
                    String quesReason = pojoQuestions.getDetails().get(i).getAssertion_reasoning_details().get(0).getReasoning();
                    formulaOne1ResAsrtion.setDisplayText(getLatexSolvedQ_Str(quesReason, true));

                    llimage.setVisibility(View.GONE);//
                    if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                        llsolution.setVisibility(View.GONE);
                    } else {
                        llsolution.setVisibility(View.VISIBLE);
                    }

                    if (questiontype.toLowerCase().trim().contains("single")) {
                        getFlippMarks("single");
                    } else if (questiontype.toLowerCase().trim().contains("multiple")) {
                        getFlippMarks("multiple");
                    }
                    if (!pojoQuestions.getDetails().get(i).getImage().equalsIgnoreCase("")) {
                        Log.d("getQues_Image", "getQues_Image: 2557");
                        llimage_case.setVisibility(View.VISIBLE);
                        iv_quesimageCase.setVisibility(View.VISIBLE);
                        Glide.with(getActivity())
                                .load(pojoQuestions.getDetails().get(i).getImage())
                                .into(iv_quesimageCase);
                    }
                }
                apiHit = 1;
                // System.out.println("pojoQuestionshhh....." + getStudentSessionActivity().getStudentSessionActivityId());

            }


        } catch (Exception e) {
            Log.e("catch_exception4001", "catch_listing: " + e.toString());
        }
    }

    private String getLatexSolvedQ_Str(String ques, boolean b) {

        System.out.println("replaceddd0.." + ques + "...rrr..");
        if (ques != null) {
            ques = ques.replace("[Math]", "$");
            ques = ques.replace("[/Math]", "$");
            ques = ques.replace("[math]", "$");
            ques = ques.replace("[/math]", "$");
            ques = ques.replace("\frac", "\\frac");
            ques = ques.replace("\"", "\\");

            //\u00e2\u20ac\u02dcA\u00e2\u20ac\u2122
            ques = ques.replace("\u00e2\u20ac\u02dcA\u00e2\u20ac\u2122", "").replace("\u00e2\u20ac\u02dcA\u00e2\u20ac\u2122", "")
                    .replace("\u02dcA", "").replace("\u00e2", "")
                    .replace("\\u00e2\\u20ac\\u2122", "").replace("\u2122", "");
        }
        String textt = "<font color=#FFFFF>" + ques + "</font> ";

        formula_one.setTextSize(latetex_dim_15);
        tv_QuesDetail_sM.setTextSize(latetex_dim_15);
        tv_solutions1M.setTextSize(latetex_dim_15);
        if (b) {
            tv_QuesDetail_sM.setDisplayText(textt);
            try {
                if (pojoQuestions.getDetails().get(i).getSolution_details() != null)
                    tv_solutions1M.setDisplayText(getLatexSolvedQ_Str_Solution(pojoQuestions.getDetails().get(i).getSolution_details().getAns_desc()));
            } catch (Exception exc) {
                exc.printStackTrace();
            }

            SolutionClickedLinstner();
        }
        // if(isRv_Visible)
        Recycler_answer.setVisibility(View.VISIBLE);
        //    getYoutTubePalyer();
        return textt;
    }

    private String getLatexSolvedQ_Str_Solution(String ques) {
        System.out.println("replaceddd0.." + ques + "...rrr..");
        ques = ques.replace("[Math]", "$");
        ques = ques.replace("[/Math]", "$");
        ques = ques.replace("[math]", "$");
        ques = ques.replace("[/math]", "$");
        ques = ques.replace("\frac", "\\frac");
        ques = ques.replace("\"", "\\");
        String textt = "<font color=#FFFFF>" + ques + "</font> ";
        formula_one.setTextSize(latetex_dim_15);
        tv_QuesDetail_sM.setTextSize(latetex_dim_15);
        //    getYoutTubePalyer();
        return textt;
    }

    private void SolutionClickedLinstner() {
        llsolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCountDownOnNext = false;
                if (doubleConfidence == 2)
                    tv_quePoints1.setText(tv_quePoints.getText().toString().replace("+", "-"));
                else
                    tv_quePoints1.setText(tv_quePoints.getText().toString().replace("+", " ").replace("-", " "));
                llsolution.setVisibility(View.VISIBLE);
                llsolnext.setVisibility(View.VISIBLE);


                if (tv_solutionbtn.getText().toString().equalsIgnoreCase("Solution")) {
                    scrollBar_solution.setVisibility(View.VISIBLE);
                    scrollBar_.setVisibility(View.GONE);
                    tv_solutionbtn.setText("Back");
                    youtTube_Fragment = new YoutTube_Fragment();
                    //getYoutTubePalyer();
                    if (pojoQuestions.getDetails().get(i).getSolution_details() != null) {
                        if (pojoQuestions.getDetails().get(i).getSolution_details().getAns_link() != null) {
                            String videoId = pojoQuestions.getDetails().get(i).getSolution_details().getAns_link()
                                    .substring(pojoQuestions.getDetails().get(i).getSolution_details().getAns_link().lastIndexOf("/") + 1);

                            /*    UpdateSubmitApi(pojoQuestions.getDetails().get(i).getQuestionsAllTypesId());*/

                            //   String videoId="etX_YGDvIpk";
                            try {
                                llsolution.setVisibility(View.VISIBLE);
                                llsolnext.setVisibility(View.VISIBLE);


                                Log.e("onClick:solution ", "" + videoId + "........" + pojoQuestions.getDetails().get(i).getSolution_details().getAns_link());

                                if (!videoId.isEmpty()) {
                                    youtubeFrameLayout.setVisibility(View.VISIBLE);
                                    FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("video_Id", videoId);
                                    youtTube_Fragment.setArguments(bundle);
                                    ft.add(R.id.youtube_fragment, youtTube_Fragment);
                                    // ft.addToBackStack("youtTube_Fragment1");
                                    ft.commit();
                                }else{
                                    youtubeFrameLayout.setVisibility(View.GONE);

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                } else {
                    //getActivity().recreate();
                    setScrollbarMcqVisibility();
                }
            }
        });

    }

    void setScrollbarMcqVisibility() {
        tv_solutionbtn.setText("Solution");
        scrollBar_solution.setVisibility(View.GONE);
        scrollBar_.setVisibility(View.VISIBLE);

        /*if (youTubePlayerComM != null) {
            youTubePlayerComM.pause();
            youTubePlayerComM.release();
        }*/
        if (youtTube_Fragment != null)
            youtTube_Fragment.onDestroyView();
        if (youtTube_Fragment != null) {
            FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
            ft.remove(youtTube_Fragment).commitAllowingStateLoss();
        }

    }

    public class queOptionsAdapter extends RecyclerView.Adapter<queOptionsAdapter.ViewHolder> {
        // Detail pojoQuestions;
        List<AnswerDetail> pojo;
        String type;
        String questiontype;
        AttemptByAdapter attemptByAdapter;
        AttemptByFull_imagesAdapter attemptByUsers;
        List<UserDetail> userDetail;
        ArrayList<UserDetail> tappedList;
        private boolean isRvImageFull = false;
        int totalRightAns;

        public queOptionsAdapter(List<AnswerDetail> pojo, String type, String questiontype, int totalRightAns) {
            this.pojo = pojo;
            this.type = type;
            this.questiontype = questiontype;
            this.totalRightAns = totalRightAns;
        }

        public queOptionsAdapter() {
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_choosans_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            // System.out.println("totalRightAns.size.." + totalRightAns);

            holder.formula_one1.setTextSize(latetex_dim_13);

            if (questiontype.contains("Image MCQ")) {

                holder.cardViewIMage.setVisibility(View.VISIBLE);
                holder.iv_image.setVisibility(View.VISIBLE);
                holder.Tv_text.setVisibility(View.GONE);
                holder.formula_one1.setVisibility(View.GONE);
                Glide.with(getActivity())
                        .load(pojo.get(position).getQuestion_img())
                        .into(holder.iv_image);
                getLongPressItemImage_queOption(holder, pojo.get(position).getQuestion_img());
            } else {
                holder.cardViewIMage.setVisibility(View.GONE);
                holder.iv_image.setVisibility(View.GONE);
                //holder.Tv_text.setVisibility(View.VISIBLE);
                String ans = pojo.get(position).getRight_txt_ans1();
                holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                //getLatexSolvedQ_Str(ans);

                holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false));
            }
            holder.formula_one1.setOnClickListener(v -> holder.itemView.performClick());
            holder.tv_sereas.setText("" + (char) (position + 65));


            holder.tv_sereas.setTextColor(getResources().getColor(R.color.white));
            holder.tv_sereas.setBackgroundColor(Color.parseColor("#05334C"));
            // holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
            // holder.Card.setBackground(getResources().getDrawable(R.drawable.round_boundary_navi));

            if (pojo.get(position).getTypes().equalsIgnoreCase("Right")) {
                holder.tv_sereas.setBackgroundColor(Color.parseColor("#26e67a"));
                // Card.setBackground(getResources().getDrawable(R.drawable.round_boundary_navi));
                holder.Card.setBackgroundResource(R.drawable.boundary_green);
                holder.ic_right.setVisibility(View.VISIBLE);
                holder.ic_cancel.setVisibility(View.GONE);
                holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor));
            }

            attemptByAdapter = new AttemptByAdapter(holder);
            attemptByUsers = new AttemptByFull_imagesAdapter();
            holder.rv_Image.setAdapter(attemptByAdapter);
            holder.rvImageFull.setAdapter(attemptByUsers);
            holder.rvImageFull.setVisibility(View.GONE);
            tappedList = new ArrayList<>();
            if (userDetail != null)
                for (int j = 0; j < userDetail.size(); j++) {
                    if (pojo.get(position).getRight_txt_ans_id().equalsIgnoreCase(userDetail.get(j).getSubmitDetail().getMcqAction())) {
                        tappedList.add(userDetail.get(j));
                        attemptByAdapter.updateListType(tappedList);
                        attemptByUsers.updateListType(tappedList);
                    }
                }
            holder.itemView.setOnClickListener(v -> {
                isRvImageFull = !isRvImageFull;
                if (isRvImageFull) holder.rvImageFull.setVisibility(View.VISIBLE);
                else holder.rvImageFull.setVisibility(View.GONE);
            });


        }

        @Override
        public int getItemCount() {
            try {
                return pojo == null ? 0 : pojo.size();
            } catch (Exception e) {
                return 0;
            }

        }

        public void updateSubmitted(List<UserDetail> us) {
            this.userDetail = us;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardoption, cardViewIMage;
            RecyclerView recycler_Text;
            public RecyclerView rv_Image, rvImageFull;
            RelativeLayout RELAtive;
            TextView tv_sereas;
            LinearLayout Card;
            TextView Tv_text;
            ImageView iv_image;
            ImageView ic_right, ic_cancel;
            MathView formula_one1;

            public ViewHolder(View itemView) {
                super(itemView);
                rvImageFull = itemView.findViewById(R.id.rv_ImageFull);
                rv_Image = itemView.findViewById(R.id.rv_Image);
                cardViewIMage = itemView.findViewById(R.id.cardViewIMage);
                iv_image = itemView.findViewById(R.id.iv_image);
                ic_right = itemView.findViewById(R.id.ic_right);
                ic_cancel = itemView.findViewById(R.id.ic_cancel);
                cardoption = itemView.findViewById(R.id.cardoption);
                recycler_Text = itemView.findViewById(R.id.recycler_Text);
                RELAtive = itemView.findViewById(R.id.RELAtive);
                tv_sereas = itemView.findViewById(R.id.tv_sereas);
                Card = itemView.findViewById(R.id.Card);
                Tv_text = itemView.findViewById(R.id.Tv_text);
                formula_one1 = itemView.findViewById(R.id.formula_one1);
            }
        }
    }

    public class trueFalseAdapter extends RecyclerView.Adapter<trueFalseAdapter.ViewHolder> {
        //Detail pojoQuestions;
        List<TruefalseAnswerDetail> pojo;
        String type;
        ArrayList<String> arrayList = new ArrayList<>();
        List<UserDetail> userDetail;
        ArrayList<UserDetail> tappedList;
        AttemptByFull_imagesAdapter attemptByUsers;
        AttemptByAdapterTruefalse attemptByAdapter;
        private boolean isRvImageFull = false;

        public trueFalseAdapter(List<TruefalseAnswerDetail> pojo, String type) {
            this.pojo = pojo;
            this.type = type;
            arrayList.add("True");
            arrayList.add("False");
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_truefalse, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            try {
                holder.formula_one1.setTextSize(latetex_dim_13);
                // if (type.equalsIgnoreCase("question")) {
                String ans = arrayList.get(position);
                Log.e("TAG", "onBindViewHolder: " + ans);
                // holder.tv_type.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false));
//                holder.formula_one1.setOnClickListener(v -> {
//                    holder.itemView.performClick();
//                });

                //   holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
                holder.Card.setBackground(getResources().getDrawable(R.drawable.round_boundary_navi_dark));
                Log.e("TAG", "onBindViewHolder:trufasle.. " + arrayList.get(position) + "...." + pojo.get(0).getTf_type());
                if (arrayList.get(position).trim().toLowerCase().equals(pojo.get(0).getTf_type().trim().toLowerCase())) {
                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#26e67a"));
                    holder.Card.setBackgroundResource(R.drawable.boundary_green);
                    holder.tv_sereas.setTextColor(ContextCompat.getColor(context, R.color.white));
                    holder.ic_right.setVisibility(View.VISIBLE);
                }

                try {
                    attemptByAdapter = new AttemptByAdapterTruefalse(holder);
                    attemptByUsers = new AttemptByFull_imagesAdapter();
                    holder.rvImage.setAdapter(attemptByAdapter);
                    holder.rvImageFull.setAdapter(attemptByUsers);
                    holder.rvImageFull.setVisibility(View.GONE);
                    if (userDetail != null) {
                        //holder.tvNumber.setText(userDetail.size() + "");
                        tappedList = new ArrayList<>();

                        for (int j = 0; j < userDetail.size(); j++) {
                            String k = userDetail.get(j).getSubmitDetail().getQuesDetail().getAnsStatus();

                            if (k.equalsIgnoreCase("0")) {
                                if (!arrayList.get(position).trim().toLowerCase().equals(pojo.get(0).getTf_type().trim().toLowerCase())) {
                                    tappedList.add(userDetail.get(j));
                                    attemptByAdapter.updateListType(tappedList);
                                    attemptByUsers.updateListType(tappedList);
                                }
                            } else if (arrayList.get(position).equalsIgnoreCase(pojo.get(0).getTf_type().trim().toLowerCase())) {
                                tappedList.add(userDetail.get(j));
                                attemptByAdapter.updateListType(tappedList);
                                attemptByUsers.updateListType(tappedList);
                            }
                        }
                    }
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

                // }

                holder.itemView.setOnClickListener(v -> {
                    isRvImageFull = !isRvImageFull;
                    if (isRvImageFull)
                        holder.rvImageFull.setVisibility(View.VISIBLE);
                    else holder.rvImageFull.setVisibility(View.GONE);
                });

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public void updateList(List<UserDetail> us) {
            this.userDetail = us;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            LinearLayout Card;
            TextView tv_type, tv_sereas, tvNumber;
            ImageView ic_right, ic_cancel;
            MathView formula_one1;
            CardView cardoption;
            public RecyclerView rvImageFull, rvImage;

            public ViewHolder(View itemView) {
                super(itemView);
                rvImage = itemView.findViewById(R.id.rv_Image);
                rvImageFull = itemView.findViewById(R.id.rv_ImageFull);
                tvNumber = itemView.findViewById(R.id.tvNumber);
                ic_right = itemView.findViewById(R.id.ic_right);
                ic_cancel = itemView.findViewById(R.id.ic_cancel);
                Card = itemView.findViewById(R.id.Card);
                tv_type = itemView.findViewById(R.id.tv_type);
                tv_sereas = itemView.findViewById(R.id.tv_sereas);
                formula_one1 = itemView.findViewById(R.id.formula_one1);
                cardoption = itemView.findViewById(R.id.cardoption);
            }
        }
    }

    public class descriptiveAdapter extends RecyclerView.Adapter<descriptiveAdapter.ViewHolder> {

        List<DescriptiveVisualizationDetail> pojo;
        String type;

        int totalRightAns = 0;
        List<UserDetail> userDetail;
        ArrayList<UserDetail> tappedList;
        AttemptByFull_imagesAdapter attemptByUsers;
        AttemptByDescriptiveAdapter attemptByAdapter;
        private boolean isRvImageFull = false;

        public descriptiveAdapter(List<DescriptiveVisualizationDetail> pojo, String type) {
            this.pojo = pojo;
            this.type = type;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_vlsualistion, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {

                if (pojo.get(position).getImg_type().equalsIgnoreCase("Right")) {
                    totalRightAns++;
                }

                if (type.equalsIgnoreCase("question")) {
                    Glide.with(getActivity())
                            .load(pojo.get(position).getDesc_images())
                            .into(holder.Image_concave);
                    holder.tv_sereas.setText("" + (char) (position + 65));
                    getLongPressItemImage_descriptive(holder, pojo.get(position).getDesc_images());


                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#05334C"));
                    // holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
                    holder.Card.setBackground(getResources().getDrawable(R.drawable.round_boundary_navi));
                    holder.tv_sereas.setTextColor(getResources().getColor(R.color.white));
                    if (pojo.get(position).getImg_type().equals("Right")) {
                        selectedanswer = new ArrayList<>();
                        des_visual_ = pojo.get(position).getRight_img_desc_id();
                        selectedanswer.add(pojo.get(position).getRight_img_desc_id());
                        holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"));
                        holder.Card.setBackgroundResource(R.drawable.boundary_green);
                        holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor));
                        holder.ic_right.setVisibility(View.VISIBLE);
                    }
                    attemptByAdapter = new AttemptByDescriptiveAdapter(holder);
                    attemptByUsers = new AttemptByFull_imagesAdapter();
                    holder.rvImage.setAdapter(attemptByAdapter);
                    holder.rvImageFull.setAdapter(attemptByUsers);
                    holder.rvImageFull.setVisibility(View.GONE);
                    tappedList = new ArrayList<>();
                    try {
                        if (userDetail != null)
                            for (int j = 0; j < userDetail.size(); j++) {
                                if (pojo.get(position).getRight_img_desc_id().equalsIgnoreCase(userDetail.get(j).getSubmitDetail().getDesVisualAction())) {
                                    tappedList.add(userDetail.get(j));
                                    attemptByAdapter.updateListType(tappedList);
                                    attemptByUsers.updateListType(tappedList);
                                }
                            }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    Glide.with(getActivity())
                            .load(pojo.get(position).getDesc_images())
                            .into(holder.Image_concave);
                    getLongPressItemImage_descriptive(holder, pojo.get(position).getDesc_images());
                    holder.tv_sereas.setText("" + (char) (position + 65));
                    holder.tv_sereas.setTextColor(getResources().getColor(R.color.white));
                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#05334C"));
                    holder.Card.setBackgroundResource(R.drawable.round_boundary_whiteonly);
                    if (pojo.get(position).getImg_type().equalsIgnoreCase("Right")) {
                        holder.ic_right.setVisibility(View.VISIBLE);
                        holder.ic_cancel.setVisibility(View.GONE);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void updateSubmitted(List<UserDetail> us) {
            this.userDetail = us;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return pojo.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_sereas;
            LinearLayout Card;
            ImageView Image_concave;
            ImageView ic_right, ic_cancel;
            CardView cardoption;
            public RecyclerView rvImageFull, rvImage;

            public ViewHolder(View itemView) {
                super(itemView);
                rvImage = itemView.findViewById(R.id.rv_Image);
                rvImageFull = itemView.findViewById(R.id.rv_ImageFull);
                ic_right = itemView.findViewById(R.id.ic_right);
                ic_cancel = itemView.findViewById(R.id.ic_cancel);
                tv_sereas = itemView.findViewById(R.id.tv_sereas);
                Card = itemView.findViewById(R.id.Card);
                Image_concave = itemView.findViewById(R.id.Image_concave);
                cardoption = itemView.findViewById(R.id.cardoption);
            }
        }
    }

    public class descriptiveAnswerAdapter extends RecyclerView.Adapter<descriptiveAnswerAdapter.ViewHolder> {
        Detail pojoQuestions;
        List<DescriptiveAnswerDetail> pojo;
        String type;

        int totalRightAns = 0;
        List<UserDetail> userDetail;
        ArrayList<UserDetail> tappedList;
        AttemptByFull_imagesAdapter attemptByUsers;
        AttemptByDescriptiveAnwsAdapter attemptByAdapter;
        private boolean isRvImageFull = false;

        public descriptiveAnswerAdapter(List<DescriptiveAnswerDetail> pojo, String type) {
            this.pojo = pojo;
            this.type = type;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_choosans, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {
                if (pojo.get(position).getTxt_type().equalsIgnoreCase("Right")) {
                    totalRightAns++;
                }
                holder.formula_one1.setTextSize(latetex_dim_13);


                holder.iv_image.setVisibility(View.GONE);
                holder.cardViewIMage.setVisibility(View.GONE);
                //holder.Tv_text.setVisibility(View.VISIBLE);
                String ans = pojo.get(position).getRight_txt_ans();
                holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                holder.formula_one1.setVisibility(View.VISIBLE);
                holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false));
                holder.tv_sereas.setText("" + (char) (position + 65));

                holder.formula_one1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.itemView.performClick();
                    }
                });


                holder.tv_sereas.setBackgroundColor(Color.parseColor("#05334C"));
                // holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
                holder.Card.setBackground(getResources().getDrawable(R.drawable.round_boundary_navi));
                if (pojo.get(position).getTxt_type().equalsIgnoreCase("Right")) {
                    selectedanswer = new ArrayList<>();
                    des_ansmcq_ = pojo.get(position).getRight_txt_desc_id();
                    selectedanswer.add(pojo.get(position).getRight_txt_desc_id());
                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"));
                    holder.Card.setBackgroundResource(R.drawable.boundary_green);
                    holder.ic_right.setVisibility(View.VISIBLE);
                }

                attemptByAdapter = new AttemptByDescriptiveAnwsAdapter(holder);
                attemptByUsers = new AttemptByFull_imagesAdapter();
                holder.rvImage.setAdapter(attemptByAdapter);
                holder.rvImageFull.setAdapter(attemptByUsers);
                holder.rvImageFull.setVisibility(View.GONE);
                tappedList = new ArrayList<>();
                try {
                    if (userDetail != null)
                        for (int j = 0; j < userDetail.size(); j++) {
                            if (pojo.get(position).getRight_txt_desc_id().equalsIgnoreCase(userDetail.get(j).getSubmitDetail().getDesAnsmcqAction())) {
                                tappedList.add(userDetail.get(j));
                                attemptByAdapter.updateListType(tappedList);
                                attemptByUsers.updateListType(tappedList);
                            }
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void updateSubmitted(List<UserDetail> us) {
            this.userDetail = us;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return pojo.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardoption, cardViewIMage;
            RecyclerView recycler_Text;
            RelativeLayout RELAtive;
            TextView tv_sereas;
            LinearLayout Card;
            TextView Tv_text;
            ImageView iv_image;
            ImageView ic_right, ic_cancel;
            MathView formula_one1;
            public RecyclerView rvImageFull, rvImage;

            public ViewHolder(View itemView) {
                super(itemView);

                rvImage = itemView.findViewById(R.id.rv_Image);
                rvImageFull = itemView.findViewById(R.id.rv_ImageFull);
                cardViewIMage = itemView.findViewById(R.id.cardViewIMage);
                iv_image = itemView.findViewById(R.id.iv_image);
                ic_right = itemView.findViewById(R.id.ic_right);
                ic_cancel = itemView.findViewById(R.id.ic_cancel);
                cardoption = itemView.findViewById(R.id.cardoption);
                recycler_Text = itemView.findViewById(R.id.recycler_Text);
                RELAtive = itemView.findViewById(R.id.RELAtive);
                tv_sereas = itemView.findViewById(R.id.tv_sereas);
                Card = itemView.findViewById(R.id.Card);
                Tv_text = itemView.findViewById(R.id.Tv_text);
                formula_one1 = itemView.findViewById(R.id.formula_one1);
            }
        }
    }

    public class arrangeAdapter extends RecyclerView.Adapter<arrangeAdapter.ViewHolder> {
        List<ArrangeAnswerDetail> pojo;
        String type;

        String questiontype;
        int marksRight = 0;
        int checkArange = 0;

        public arrangeAdapter(List<ArrangeAnswerDetail> pojo, String type, String questiontype) {
            this.pojo = pojo;
            this.type = type;
            this.questiontype = questiontype;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_choosans_arrange, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            if (type.equalsIgnoreCase("question")) {
                if (questiontype.equalsIgnoreCase("Image Arrange")) {
                    holder.iv_image.setVisibility(View.VISIBLE);
                    holder.cardViewIMage.setVisibility(View.VISIBLE);
                    // holder.Tv_text.setVisibility(View.GONE);
                    holder.formula_one1.setVisibility(View.GONE);
                    Glide.with(getActivity())
                            .load(pojo.get(position).getAnsImg())
                            .into(holder.iv_image);
                    getLongPressItemImage(holder, pojo.get(position).getAnsImg());
                } else {
                    holder.iv_image.setVisibility(View.GONE);
                    holder.cardViewIMage.setVisibility(View.GONE);
                    // holder.Tv_text.setVisibility(View.VISIBLE);
                    holder.formula_one1.setVisibility(View.VISIBLE);
                    String ans = pojo.get(position).getRight_txt_ans();
                    // holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                    holder.formula_one1.setTextSize(latetex_dim_13);
                    holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false));
                }

                holder.tv_sereas.setText("" + (char) (position + 65));

            } else {
                if (questiontype.equalsIgnoreCase("Image Arrange")) {
                    holder.iv_image.setVisibility(View.VISIBLE);
                    holder.cardViewIMage.setVisibility(View.VISIBLE);
                    holder.Tv_text.setVisibility(View.GONE);
                    holder.formula_one1.setVisibility(View.GONE);
                    Glide.with(getActivity())
                            .load(pojo.get(position).getAnsImg())
                            .into(holder.iv_image);
                    getLongPressItemImage(holder, pojo.get(position).getAnsImg());


                } else {
                    holder.cardViewIMage.setVisibility(View.GONE);
                    holder.iv_image.setVisibility(View.GONE);
                    //  holder.Tv_text.setVisibility(View.VISIBLE);
                    holder.formula_one1.setTextSize(latetex_dim_13);
                    holder.formula_one1.setVisibility(View.VISIBLE);
                    String ans = pojo.get(position).getRight_txt_ans();
                    //holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                    holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false));
                }
                holder.tv_sereas.setText("" + (char) (position + 65));
                // for(int k =0; 0<pojo.size();k++){


                holder.tv_sereas.setBackgroundColor(Color.parseColor("#05334C"));
                //  holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
                holder.Card.setBackground(getResources().getDrawable(R.drawable.round_boundary_navi));

            }


        }

        @Override
        public int getItemCount() {
            return pojo.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardoption, cardViewIMage;
            RecyclerView recycler_Text;
            TextView tv_sereas;
            LinearLayout Card;
            TextView Tv_text;
            ImageView ic_right, ic_cancel;
            ImageView ic_swap, iv_image;
            MathView formula_one1;

            public ViewHolder(View itemView) {
                super(itemView);
                cardViewIMage = itemView.findViewById(R.id.cardViewIMage);
                iv_image = itemView.findViewById(R.id.iv_image);
                ic_swap = itemView.findViewById(R.id.ic_swap);
                ic_right = itemView.findViewById(R.id.ic_right);
                ic_cancel = itemView.findViewById(R.id.ic_cancel);
                cardoption = itemView.findViewById(R.id.cardoption);
                recycler_Text = itemView.findViewById(R.id.recycler_Text);
                tv_sereas = itemView.findViewById(R.id.tv_sereas);
                Card = itemView.findViewById(R.id.Card);
                Tv_text = itemView.findViewById(R.id.Tv_text);
                formula_one1 = itemView.findViewById(R.id.formula_one1);
            }
        }
    }

    public class nuricalAdapter extends RecyclerView.Adapter<nuricalAdapter.ViewHolder> {
        //Detail pojoQuestions;
        Detail pojo;
        String type;
        int selectedid = -1;
        int totalRightAns;
        List<UserDetail> userDetail;
        ArrayList<UserDetail> tappedList;
        AttemptByFull_imagesAdapter attemptByUsers;
        AttemptByNumericalAdapter attemptByAdapter;
        private boolean isRvImageFull = false;

        public nuricalAdapter(Detail pojo, String type, int totalRightAns) {
            this.pojo = pojo;
            this.type = type;
            this.totalRightAns = totalRightAns;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_choosans, parent, false);
            return new ViewHolder(view);
        }

        public void updateSubmitted(List<UserDetail> us) {
            this.userDetail = us;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            attemptByAdapter = new AttemptByNumericalAdapter(holder);
            attemptByUsers = new AttemptByFull_imagesAdapter();
            holder.rvImage.setAdapter(attemptByAdapter);
            holder.rvImageFull.setAdapter(attemptByUsers);
            holder.rvImageFull.setVisibility(View.GONE);
            tappedList = new ArrayList<>();
            holder.formula_one1.setTextSize(latetex_dim_13);
            if (type.equalsIgnoreCase("question")) {
                // getFlippMarks( "interpretation");
                System.out.println("totalRightAns.size.." + totalRightAns);
                holder.iv_image.setVisibility(View.GONE);
                holder.cardViewIMage.setVisibility(View.GONE);
                //holder.Tv_text.setVisibility(View.VISIBLE);
                holder.formula_one1.setVisibility(View.VISIBLE);
                String ans = pojo.getN_interpretation_answer_details().get(position).getInterpretationAnswers();
                holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false));
                holder.tv_sereas.setText("" + (char) (position + 65));

                holder.formula_one1.setOnClickListener(v -> {
                    holder.itemView.performClick();
                });


                holder.tv_sereas.setTextColor(getResources().getColor(R.color.white));
                holder.tv_sereas.setBackgroundColor(Color.parseColor("#05334C"));
                //  holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
                holder.Card.setBackground(getResources().getDrawable(R.drawable.round_boundary_navi));
                holder.ic_right.setVisibility(View.GONE);
                holder.ic_cancel.setVisibility(View.GONE);
                if (pojo.getN_interpretation_answer_details().get(position).getInterpretationRightWrong().equals("Right")) {
                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"));
                    holder.Card.setBackgroundResource(R.drawable.boundary_green);
                    holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor));
                    holder.ic_right.setVisibility(View.VISIBLE);
                }
                try {
                    if (userDetail != null)
                        for (int j = 0; j < userDetail.size(); j++) {
                            if (pojo.getN_interpretation_answer_details().get(position).getInterpretationAnsId().equalsIgnoreCase(userDetail.get(j).getSubmitDetail().getnInterAction())) {
                                tappedList.add(userDetail.get(j));
                                attemptByAdapter.updateListType(tappedList);
                                attemptByUsers.updateListType(tappedList);
                            }

                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (type.equalsIgnoreCase("question1")) {
                // getFlippMarks( "visualization");

                System.out.println("totalRightAns.size.." + totalRightAns);
                holder.iv_image.setVisibility(View.VISIBLE);
                holder.cardViewIMage.setVisibility(View.VISIBLE);
                holder.cardViewIMage.setVisibility(View.VISIBLE);
                holder.Tv_text.setVisibility(View.GONE);
                holder.formula_one1.setVisibility(View.GONE);
//                String ans = pojo.getN_interpretation_answer_details().get(position).getInterpretationAnswers();
                // holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                Glide.with(getActivity())
                        .load(pojo.getN_visualization_ans_details().get(position).getVisualizationAnswers())
                        .into(holder.iv_image);
                holder.tv_sereas.setText("" + (char) (position + 65));

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //totalRightAns=0;
                        n_visual_marks = questTypeMarks;
                        ans_n_visual = pojo.getN_visualization_ans_details().get(position).getVisualizationAnsId();
                        selectedid = position;
//                        selectedanswer.add(pojo.getN_visualization_ans_details().get(position).getVisualizationAnsId());
                        notifyDataSetChanged();
                    }
                });
                holder.itemView.setOnLongClickListener(v1 -> {
                    expanded_image.setVisibility(View.VISIBLE);
                    Global.zoomRecyclerImage(holder.iv_image, pojo.getN_visualization_ans_details().get(position).getVisualizationAnswers(), getActivity(), expanded_image, relMain, shortAnimationDuration);
                    return true;
                });
                holder.tv_sereas.setBackgroundColor(Color.parseColor("#05334C"));
                holder.tv_sereas.setTextColor(getResources().getColor(R.color.white));
                //  holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
                holder.Card.setBackground(getResources().getDrawable(R.drawable.round_boundary_navi));

                holder.ic_right.setVisibility(View.GONE);
                holder.ic_cancel.setVisibility(View.GONE);
                if (pojo.getN_visualization_ans_details().get(position).getVisualizationRightWrong().equals("Right")) {
                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"));
                    holder.Card.setBackgroundResource(R.drawable.boundary_green);
                    holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor));
                    holder.ic_right.setVisibility(View.VISIBLE);
                }

                try {
                    if (userDetail != null)
                        for (int j = 0; j < userDetail.size(); j++) {

                            if (pojo.getN_visualization_ans_details().get(position).getVisualizationAnsId().equalsIgnoreCase(userDetail.get(j).getSubmitDetail().getnVisualAction())) {
                                tappedList.add(userDetail.get(j));
                                attemptByAdapter.updateListType(tappedList);
                                attemptByUsers.updateListType(tappedList);
                            }
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            holder.tv_sereas.setText("" + (char) (position + 65));
        }

        @Override
        public int getItemCount() {
            return pojo.getN_interpretation_answer_details().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardoption, cardViewIMage;
            RecyclerView recycler_Text;
            RelativeLayout RELAtive;
            TextView tv_sereas;
            LinearLayout Card;
            TextView Tv_text;
            ImageView iv_image;
            ImageView ic_right, ic_cancel;
            MathView formula_one1;
            public RecyclerView rvImageFull, rvImage;

            public ViewHolder(View itemView) {
                super(itemView);
                rvImage = itemView.findViewById(R.id.rv_Image);
                rvImageFull = itemView.findViewById(R.id.rv_ImageFull);
                cardViewIMage = itemView.findViewById(R.id.cardViewIMage);
                iv_image = itemView.findViewById(R.id.iv_image);
                ic_right = itemView.findViewById(R.id.ic_right);
                ic_cancel = itemView.findViewById(R.id.ic_cancel);
                cardoption = itemView.findViewById(R.id.cardoption);
                recycler_Text = itemView.findViewById(R.id.recycler_Text);
                RELAtive = itemView.findViewById(R.id.RELAtive);
                tv_sereas = itemView.findViewById(R.id.tv_sereas);
                Card = itemView.findViewById(R.id.Card);
                Tv_text = itemView.findViewById(R.id.Tv_text);
                formula_one1 = itemView.findViewById(R.id.formula_one1);
            }
        }
    }

    public class applyAdapter extends RecyclerView.Adapter<applyAdapter.ViewHolder> {
        private Context mContext;

        Detail pojoQuestions;
        Detail pojo;
        String type;
        int marksRight = 0;

        public applyAdapter(Detail pojo, String type) {
            this.pojo = pojo;
            this.type = type;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_n_application_detail_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            rellSupSup.setVisibility(View.GONE);

            if (type.equalsIgnoreCase("question2")) {
                adapter2 = new applyEditText(pojo, "question2", marksRight);
                holder.rv_editText.setLayoutManager(new LinearLayoutManager(getActivity()
                        , LinearLayoutManager.VERTICAL, false));
                holder.rv_editText.setAdapter(adapter2);

            } else {
                adapter2 = new applyEditText(pojo, "", marksRight);
                holder.rv_editText.setLayoutManager(new LinearLayoutManager(getActivity()
                        , LinearLayoutManager.VERTICAL, false));
                holder.rv_editText.setAdapter(adapter2);
                System.out.println("marksRight_size.." + marksRight);
            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            RecyclerView rv_editText, rv_spinner;

            public ViewHolder(View itemView) {
                super(itemView);
                rv_editText = itemView.findViewById(R.id.rv_editText);
                rv_spinner = itemView.findViewById(R.id.rv_spinner);
            }
        }
    }

    public class applyEditText extends RecyclerView.Adapter<applyEditText.ViewHolder> {

        Detail pojo;
        String type;
        int selectedId = -1;
        int marksRight;
        String supSubTag;

        public applyEditText(Detail pojo, String type, int marksRight) {
            this.pojo = pojo;
            this.type = type;
            this.marksRight = marksRight;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_n_application_detail_row_next, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            //  System.out.print("lleditText..oo."+holder.et_value1.getText());
            //  System.out.print("lleditText..oo."+holder.et_value1.getText());
            holder.rell_SupSup.setVisibility(View.GONE);


            if (!pojo.getN_application_details().get(position).getQuantity().equalsIgnoreCase("")) {
                holder.llSpinner.setVisibility(View.GONE);
                holder.lleditText.setVisibility(View.VISIBLE);
                holder.tv_value1.setText(pojo.getN_application_details().get(position).getQuantity() + "   = ");
                holder.tv_meter1.setText(Html.fromHtml(" " + pojo.getN_application_details().get(position).getUnit()), TextView.BufferType.SPANNABLE);

                //System.out.println("setEquation1aa..." +".."+ holder.et_value1.getText().toString()+" ......"+supSubTag);
                if (supSubTag == null) {
                    supSubTag = "";
                }
                String sAns = pojo.getN_application_details().get(position).getValue();
                System.out.println("setEquation1aa..." + ".." + putvalue1 + " ......" + supSubTag + "-------sAns " + sAns);

                holder.et_value1.setText(Html.fromHtml(sAns), TextView.BufferType.SPANNABLE);
                //holder.et_value1.onTouchEvent(new MotionEvent());

            } else {
                holder.rell_SupSup.setVisibility(View.GONE);
                holder.llSpinner.setVisibility(View.VISIBLE);
                holder.lleditText.setVisibility(View.GONE);

                holder.tv_nature.setText(pojo.getN_application_details().get(position).getQuantity1() + " =   ");

                final List<String> getVesselName = new ArrayList<String>();
                getVesselName.add("");
                getVesselName.add(pojo.getN_application_details().get(position).getCorrectOption());
                getVesselName.add(pojo.getN_application_details().get(position).getWrongOption());

                ArrayAdapter<String> dataAd1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getVesselName);
                dataAd1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.spinner1.setAdapter(dataAd1);


                holder.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        ((TextView) view).setTextColor(Color.WHITE);
                        ((TextView) view).setTextSize(latetex_dim_15);
                        electedvaluee1 = adapterView.getItemAtPosition(i) + "";
                        value1 = i;

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                holder.spinner1.setSelection(1);
            }


        }

        @Override
        public int getItemCount() {

            if (pojo.getN_application_details() != null) {
                return pojo.getN_application_details().size();
            } else {
                return 0;
            }
        }

        public void setEquation(String sup_subTag) {
            this.supSubTag = sup_subTag;
            System.out.println("setEquation1..." + ".." + sup_SubTag);
            et_value1.setText("");
            Global.popUpImpputKeyboard(getActivity());
            rellSupSup.setVisibility(View.GONE);
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            LinearLayout lleditText, llSpinner;
            EditText et_value1,/*, et_value2*/
                    etValue2;
            ImageView iv_tick1, iv_tick3, iv_cross1, iv_cross3;// iv_tick2,iv_cross2, iv_cross4 , iv_tick4
            TextView tv_nature, tv_meter1, tv_value1, tvSub, tvSup, tvDone;
            LinearLayout Card;
            Spinner spinner1, spinner2;
            RelativeLayout rell_SupSup;

            public ViewHolder(View itemView) {
                super(itemView);
                et_value1 = itemView.findViewById(R.id.et_value1);
                tv_value1 = itemView.findViewById(R.id.tv_value1);
                iv_cross1 = itemView.findViewById(R.id.iv_cross1);
                iv_cross3 = itemView.findViewById(R.id.iv_cross3);
                iv_tick3 = itemView.findViewById(R.id.iv_tick3);
                iv_tick1 = itemView.findViewById(R.id.iv_tick1);
                lleditText = itemView.findViewById(R.id.lleditText);
                llSpinner = itemView.findViewById(R.id.llSpinner);
                spinner1 = itemView.findViewById(R.id.spinner1);
                //  tv_sereas = itemView.findViewById(R.id.tv_sereas);
                // tv_qustion = itemView.findViewById(R.id.tv_qustion);
                tv_meter1 = itemView.findViewById(R.id.tv_meter1);
                tvSub = itemView.findViewById(R.id.tv_sub);
                tvSup = itemView.findViewById(R.id.tv_sup);
                etValue2 = itemView.findViewById(R.id.et_value2);
                tvDone = itemView.findViewById(R.id.tv_Done);
                rell_SupSup = itemView.findViewById(R.id.rellSupSup);
                tv_nature = itemView.findViewById(R.id.tv_nature);
                Card = itemView.findViewById(R.id.Card);
            }
        }
    }

    private void initView_apply(applyEditText.ViewHolder holder, int position) {
        supTag = true;
        subTag = true;
//        tv_sub.setText(Html.fromHtml("  Sub  eq: 10<sub>2 </sub>"));
//        tv_sup.setText(Html.fromHtml("  Sup  eq: 10<sup>2</sup> "));
        tv_sub.setText("<" + "sub" + ">");
        tv_sup.setText("<" + "sup" + ">");
        // rellSupSup.setVisibility(View.GONE);

        tv_sub.setOnClickListener(v1 -> {
            if (subTag) {
                subTag = false;
                sup_SubTag = "<" + "sub" + ">";
                tv_sub.setText("</" + "sub" + ">");

            } else {
                subTag = true;
                sup_SubTag = "</" + "sub" + ">";
                tv_sub.setText("<" + "sub" + ">");
            }
            sup_SubStr = "";
            putvalue1 = et_value1.getText().toString();
            sup_SubStr = putvalue1 + sup_SubTag;
            et_value1.setText(sup_SubStr);
            int poss = et_value1.length();
            et_value1.setSelection(poss);
            System.out.println("setEquationttv_sub" + et_value1.getText());
        });

        tv_sup.setOnClickListener(v1 -> {
            if (supTag) {
                supTag = false;
                sup_SubTag = "<" + "sup" + ">";
                tv_sup.setText("</" + "sup" + ">");

            } else {
                supTag = true;
                sup_SubTag = "</" + "sup" + ">";
                tv_sup.setText("<" + "sup" + ">");
            }//sup_SubTag
            sup_SubStr = "";

            sup_SubStr = putvalue1 + sup_SubTag;
            et_value1.setText(sup_SubStr);
            int poss = et_value1.length();
            et_value1.setSelection(poss);
            System.out.println("setEquationtv_sup" + et_value1.getText());
        });

        tv_Done.setOnClickListener(v1 -> {


            //adapterA.setEquation(sup_SubTag);
            // sup_SubTag= et_value1.getText().toString();
            Log.d("TAG", "initView_apply: " + position);
            sup_SubStr = et_value1.getText().toString();
            editTagsList.set(applyPositon, sup_SubStr);
            Log.d("TAG", "initView_apply: " + applyPositon + ".." + editTagsList.toString());
            adapter2.setEquation(sup_SubStr);
            //adapter2.notifyDataSetChanged();
        });

        et_value1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                putvalue1 = et_value1.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void getFlippMarks(String qes_Type) {

       /* for (int m = 0; m <= pojoQuestions.getDetails().get(i).getFlips_marks().size() - 1; m++) {
            //if(pojoQuestions.getFlipsMarks().get(m).getMarks().equalsIgnoreCase("qes_Type")){
            if (pojoQuestions.getDetails().get(i).getFlips_marks().get(m).getType().toLowerCase().trim().contains(qes_Type)) {
                questTypeMarks = pojoQuestions.getDetails().get(i).getFlips_marks().get(m).getMarks() + "";

                String s = String.format("%.2f", Float.parseFloat(pojoQuestions.getDetails().get(i).getFlips_marks().get(m).getMarks() + ""));
                Log.e("getFlippMarks: ", "" + s + "..." + streak_actvMrks);
                if (totalRightAns != 0) s = String.valueOf(Float.parseFloat(s) * totalRightAns);
                try {

                    if (!streak_actvMrks.equalsIgnoreCase("1"))
                        s = String.valueOf(Float.parseFloat(s) * Float.parseFloat(streak_actvMrks));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                pojoQuestions.getDetails().get(i).getFlips_marks().get(m).setMarks(Integer.parseInt(String.format("%.2f", Float.parseFloat(s))));
                tv_quePoints.setText("+ " + pojoQuestions.getDetails().get(i).getFlips_marks().get(m).getMarks());
                quePoints = pojoQuestions.getDetails().get(i).getFlips_marks().get(m).getMarks() + "";

                System.out.println("fliped_points.." + quePoints + "..." + tv_quePoints.getText().toString() + "....." + pojoQuestions.getDetails().get(i).getFlips_marks().get(m).getMarks());
            }
        }
      */
    }

    private class CaseStudyTitleAdapter extends RecyclerView.Adapter<CaseStudyTitleAdapter.ViewHolder> {
        private Context context;
        private Detail detail;
        private String type;
        private CaseStudyView studyView;

        public CaseStudyTitleAdapter(Context context, Detail detail, String type, CaseStudyView studyView) {
            this.detail = detail;
            this.context = context;
            this.type = type;
            this.studyView = studyView;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ResCaseStudyQesTitleRowBinding qesBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.res_case_study_qes_title_row, parent, false);
            View view = qesBinding.getRoot();
            return new ViewHolder(qesBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bindng.formulaOne1Res.setVisibility(View.VISIBLE);
            holder.bindng.formulaOne1Res.setTextSize(latetex_dim_15);
            holder.bindng.tvSereasRes.setText("" + (char) (position + 65));

            holder.bindng.tvSereasRes.setBackgroundColor(Color.parseColor("#05334C"));
            holder.bindng.Card.setBackgroundResource(R.drawable.round_boundary_navi);
            // holder.bindng.Card.getLayoutParams().width = RelativeLayout.LayoutParams.WRAP_CONTENT;
            holder.bindng.tvSereasRes.setTextColor(getResources().getColor(R.color.white));

            String ans = detail.getCase_study_ques_details().get(position).getCaseStudy();
            //holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
            holder.bindng.formulaOne1Res.setDisplayText(getLatexSolvedQ_Str(ans, false));
            int sType = Integer.parseInt(type);
//            if (sType == detail.getCase_study_ques_details().size())
//                type = (detail.getCase_study_ques_details().size()-1)+"";
            holder.bindng.tvSereasRes.setBackgroundColor(Color.parseColor("#05334C"));
            holder.bindng.Card.setBackgroundResource(R.drawable.round_boundary_navi);
            // holder.bindng.Card.getLayoutParams().width = RelativeLayout.LayoutParams.WRAP_CONTENT;
            holder.bindng.tvSereasRes.setTextColor(getResources().getColor(R.color.white));
            if (type.equalsIgnoreCase(position + "")) {
                holder.bindng.tvSereasRes.setBackgroundColor(Color.parseColor("#F8AD34"));
                holder.bindng.Card.setBackgroundResource(R.drawable.round_boundary_yellow);
                // holder.bindng.Card.getLayoutParams().width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                holder.bindng.tvSereasRes.setTextColor(getResources().getColor(R.color.white));

            }

            holder.bindng.formulaOne1Res.setOnClickListener(v -> {

                type = position + "";
                studyView.caseStudyOption_Ans(position);
                notifyDataSetChanged();
            });
//
        }

        @Override
        public int getItemCount() {
            return detail.getCase_study_ques_details().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ResCaseStudyQesTitleRowBinding bindng;

            public ViewHolder(@NonNull ResCaseStudyQesTitleRowBinding bindng) {
                super(bindng.getRoot());
                this.bindng = bindng;
            }
        }
    }

    @Override
    public void caseStudyOption_Ans(Integer item) {
        adapterCase.updateAnsList(item);
    }

    private String getAnsList(String sttAnsList) {
        // sttAnsList = "2889=57916/2890=57920/2891=57926/2892=57931";
        String s1 = sttAnsList.replace("/", ",").replace("=", ",");
        String[] wordss = s1.split(",");
        List words = new ArrayList<String>();
        ArrayList optionsList = new ArrayList<String>();
        ArrayList ansList = new ArrayList<String>();
        words = Arrays.asList(wordss);
        try {
            for (int i = 0; i <= words.size(); i++) {
                if (i % 2 == 1)
                    ansList.add(words.get(i));
                else optionsList.add(words.get(i));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.e("TAG", "getAnsList: " + sttAnsList + "..." + s1 + "..." + words + "..." + optionsList + "..." + ansList);
        return ansList.get(caseStdyI).toString();
    }

    public class CaseStudyAnsAdapter extends RecyclerView.Adapter<CaseStudyAnsAdapter.ViewHolder> {
        //Detail pojoQuestions;
        Detail pojo;
        String type;
        String questiontype;
        int selectedid = -1;
        int marksRight = 0;
        int totalRightAns;
        int first = 0;
        List<UserDetail> userDetail;
        ArrayList<UserDetail> tappedList;
        AttemptByCaseStudyAdapter attemptByAdapter;
        AttemptByFull_imagesAdapter attemptByUsers;
        private boolean isRvImageFull = false;

        public CaseStudyAnsAdapter(Detail pojo, String type, int totalRightAns, String questiontype) {
            this.pojo = pojo;
            this.type = type;
            this.questiontype = questiontype;
            this.totalRightAns = totalRightAns;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_choosans, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            //  getFlippMarks( "interpretation");
            holder.formula_one1.setTextSize(latetex_dim_13);
            if (type.equalsIgnoreCase("question")) {

                holder.iv_image.setVisibility(View.GONE);
                holder.cardViewIMage.setVisibility(View.GONE);
                //holder.Tv_text.setVisibility(View.VISIBLE);
                holder.formula_one1.setVisibility(View.VISIBLE); //caseStudyAnsSelectList
                String ans = "";
                if (caseStdyI == pojo.getCase_study_ques_details().size())
                    ans = pojo.getCase_study_ques_details().get(caseStdyI - 1).getCasestudyMcqs().get(position).getRightTxtAns1();
                else
                    ans = pojo.getCase_study_ques_details().get(caseStdyI).getCasestudyMcqs().get(position).getRightTxtAns1();
                holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false));
                holder.tv_sereas.setText("" + (char) (position + 65));

                holder.tv_sereas.setTextColor(getResources().getColor(R.color.white));
                holder.tv_sereas.setBackgroundColor(Color.parseColor("#05334C"));
                holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);

                holder.ic_right.setVisibility(View.GONE);
                if (pojo.getCase_study_ques_details().get(caseStdyI).getCasestudyMcqs().get(position).getTypes().equalsIgnoreCase("Right")) {
                    holder.tv_sereas.setBackgroundColor(getResources().getColor(R.color.green_botom));
                    holder.tv_sereas.setTextColor(getResources().getColor(R.color.white));
                    holder.Card.setBackgroundResource(R.drawable.boundary_green);
                    holder.ic_right.setVisibility(View.VISIBLE);
                    holder.ic_cancel.setVisibility(View.GONE);
                }
                attemptByAdapter = new AttemptByCaseStudyAdapter(holder);
                attemptByUsers = new AttemptByFull_imagesAdapter();
                holder.rv_Image.setAdapter(attemptByAdapter);
                holder.rvImageFull.setAdapter(attemptByUsers);
                holder.rvImageFull.setVisibility(View.GONE);

                tappedList = new ArrayList<>();
                if (userDetail != null)
                    for (int j = 0; j < userDetail.size(); j++) {
                        //Log.e("TAG", "onBindViewHolder:  "+pojo.getCase_study_ques_details( ).get(caseStdyI).getCasestudyMcqs().get(position).getRightTxtAnsId()+"..==..."+userDetail.get(j).getSubmitDetail().getCaseStudyQues() );
                        Log.e("TAG", "onBindViewHolder:  " + pojo.getCase_study_ques_details().get(caseStdyI).getCasestudyMcqs().get(position).getRightTxtAnsId() + "..==..." + getAnsList(userDetail.get(j).getSubmitDetail().getCaseStudyQues()));
                        if (pojo.getCase_study_ques_details().get(caseStdyI).getCasestudyMcqs().get(position).getRightTxtAnsId().equalsIgnoreCase(getAnsList(userDetail.get(j).getSubmitDetail().getCaseStudyQues()))) {
                            tappedList.add(userDetail.get(j));
                            attemptByAdapter.updateListType(tappedList);
                            attemptByUsers.updateListType(tappedList);
                        }
                    }

            } else {


                holder.formula_one1.setVisibility(View.VISIBLE); //caseStudyAnsSelectList
                String ans = "";
                if (caseStdyI == pojo.getCase_study_ques_details().size())
                    ans = pojo.getCase_study_ques_details().get(caseStdyI - 1).getCasestudyMcqs().get(position).getRightTxtAns1();
                else
                    ans = pojo.getCase_study_ques_details().get(caseStdyI).getCasestudyMcqs().get(position).getRightTxtAns1();
                holder.Tv_text.setText(Html.fromHtml(ans), TextView.BufferType.SPANNABLE);
                holder.formula_one1.setDisplayText(getLatexSolvedQ_Str(ans, false));

                holder.tv_sereas.setTextColor(getResources().getColor(R.color.white));
                holder.tv_sereas.setText("" + (char) (position + 65));
                holder.tv_sereas.setBackgroundColor(Color.parseColor("#05334C"));

                //   holder.Card.setBackgroundResource(R.drawable.round_boundary_navi);
                holder.Card.setBackground(getResources().getDrawable(R.drawable.round_boundary_navi));
                if (pojo.getCase_study_ques_details().get(caseStdyI - 1).getCasestudyMcqs().get(position).getTypes().equalsIgnoreCase("Right")) {
                    holder.tv_sereas.setBackgroundColor(Color.parseColor("#F8AD34"));
                    holder.tv_sereas.setTextColor(getResources().getColor(R.color.navicolor));
                    holder.Card.setBackgroundResource(R.drawable.boundary_green);
                    holder.ic_right.setVisibility(View.VISIBLE);
                    holder.ic_cancel.setVisibility(View.GONE);
                }
                tappedList = new ArrayList<>();
                if (userDetail != null)
                    for (int j = 0; j < userDetail.size(); j++) {
                        if (pojo.getCase_study_ques_details().get(caseStdyI).getCasestudyMcqs().get(position).getRightTxtAnsId().equalsIgnoreCase(userDetail.get(j).getSubmitDetail().getMcqAction())) {
                            tappedList.add(userDetail.get(j));
                            attemptByAdapter.updateListType(tappedList);
                            attemptByUsers.updateListType(tappedList);
                        }
                    }

                Object[] mStringArray = caseStudyselectedanswer.toArray();
                for (int i = 0; i < mStringArray.length; i++) {
                    ansMcq = (String) mStringArray[i];
                    Log.d("ansMcq_string is", (String) mStringArray[i] + "..." + ansMcq);
                }

                for (int i = 0; i < caseStudyselectedanswer.size(); i++) {

                }
            }
            holder.itemView.setOnClickListener(v -> {
                isRvImageFull = !isRvImageFull;
                if (isRvImageFull) holder.rvImageFull.setVisibility(View.VISIBLE);
                else holder.rvImageFull.setVisibility(View.GONE);
            });

            holder.tv_sereas.setText("" + (char) (position + 65));
        }

        void updateAnsList(Integer caseStdy) {
            caseStdyI = caseStdy;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return pojo.getCase_study_ques_details().get(0).getCasestudyMcqs().size();
        }

        public void updateSubmitted(List<UserDetail> us) {
            this.userDetail = us;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardoption, cardViewIMage;
            RecyclerView recycler_Text;
            RelativeLayout RELAtive;
            TextView tv_sereas;
            LinearLayout Card;
            TextView Tv_text;
            ImageView iv_image;
            ImageView ic_right, ic_cancel;
            MathView formula_one1;
            public RecyclerView rv_Image, rvImageFull;

            public ViewHolder(View itemView) {
                super(itemView);
                rvImageFull = itemView.findViewById(R.id.rv_ImageFull);
                rv_Image = itemView.findViewById(R.id.rv_Image);
                cardViewIMage = itemView.findViewById(R.id.cardViewIMage);
                iv_image = itemView.findViewById(R.id.iv_image);
                ic_right = itemView.findViewById(R.id.ic_right);
                ic_cancel = itemView.findViewById(R.id.ic_cancel);
                cardoption = itemView.findViewById(R.id.cardoption);
                recycler_Text = itemView.findViewById(R.id.recycler_Text);
                RELAtive = itemView.findViewById(R.id.RELAtive);
                tv_sereas = itemView.findViewById(R.id.tv_sereas);
                Card = itemView.findViewById(R.id.Card);
                Tv_text = itemView.findViewById(R.id.Tv_text);
                formula_one1 = itemView.findViewById(R.id.formula_one1);
            }
        }
    }


    private void getLongPressItemImage(@NotNull arrangeAdapter.ViewHolder holder, String imageString) {
        holder.itemView.setOnLongClickListener(v1 -> {

            expanded_image.setVisibility(View.VISIBLE);
            Global.zoomRecyclerImage(holder.iv_image, imageString, getActivity(), expanded_image, relMain, shortAnimationDuration);
            return true;
        });
    }

    private void getLongPressItemImage_descriptive(@NotNull descriptiveAdapter.ViewHolder holder, String imageString) {
        holder.itemView.setOnLongClickListener(v1 -> {
            expanded_image.setVisibility(View.VISIBLE);
            Global.zoomRecyclerImage(holder.Image_concave, imageString, getActivity(), expanded_image, relMain, shortAnimationDuration);
            return true;
        });
    }

    private void getLongPressItemImage_queOption(@NotNull queOptionsAdapter.ViewHolder holder, String imageString) {
        holder.itemView.setOnLongClickListener(v1 -> {

            expanded_image.setVisibility(View.VISIBLE);
            Global.zoomRecyclerImage(holder.iv_image, imageString, getActivity(), expanded_image, relMain, shortAnimationDuration);
            return true;
        });
    }

    @Override
    public void onDestroy() {
        if (cmTimer != null)
            cmTimer.stop();
        if (youTubePlayerComM != null) {
            youTubePlayerComM.pause();
            youTubePlayerComM.release();
        }
        updateCurentIndexForStudentQues(false);
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // youTubePlayerFragment.onDestroyView();
        if (cmTimer != null)
            cmTimer.stop();
        if (mCountDownTimer != null) mCountDownTimer.cancel();
        if (mCountDownTimerRefresh != null) mCountDownTimerRefresh.cancel();
    }


    public Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
    }

    private void onAnsCorrect_nextCounterStart(boolean b) {

        Map map = new HashMap();
        map.put("nextTapped", true);
        map.put("nextTappedCount", tapNext);
        db.collection(getString(R.string.collectionName)).document(liveCode_).update(map);

        isCountDownOnNext = true;
        llEndLive.setVisibility(View.GONE);
        tvNextBtnCount.setVisibility(View.VISIBLE);
        tvNextBtnCount.setText("5");
        llnext.setEnabled(false);

        mCountDownTimer = new CountDownTimer(5000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

                tvNextBtnCount.setText("" + String.format(" %d ", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)));
                if (tvNextBtnCount.getText().toString().equalsIgnoreCase("3")) ;
                {
                    Map map = new HashMap();
                    map.put("nextTapped", false);
                    db.collection(getString(R.string.collectionName)).document(liveCode_).update(map);
                }
            }

            @Override
            public void onFinish() {
                tvNextBtnCount.setVisibility(View.GONE);
                llEndLive.setVisibility(View.VISIBLE);
                llnext.setEnabled(true);
                tvnext.setText("Next Q");
//                if (b) {
//                    if (isCountDownOnNext) {
//                        running = true;
//                        seconds = 0;
//                        count = 0;
//                        setonNextClicked();
//                    }
//                }
            }
        };

        if(b){
            Map maps = new HashMap();
            maps.put("nextTapped", false);
            db.collection(getString(R.string.collectionName)).document(liveCode_).update(maps);
            // OnFinish
            tvNextBtnCount.setVisibility(View.GONE);
            llEndLive.setVisibility(View.VISIBLE);
            llnext.setEnabled(true);
            tvnext.setText("Next Q");
            if (b) {
                if (isCountDownOnNext) {
                    running = true;
                    seconds = 0;
                    count = 0;
                    setonNextClicked();
                }
            }
        }else{
            mCountDownTimer.start();
        }

    }

    void stopTimer() {
        running = false;
        db.collection(getString(R.string.collectionName)).document(liveCode_).delete();
//        timer.cancel();
//        timer.purge();
//        tv_learn.setText("00:00:00");
    }

    private void updateCurentIndexForStudentQues(boolean b) {
        roomDb.setDimension(selectedDimensions);
        roomDb.setLevel(selectedLevels);
        roomDb.setSubtopics(selectedSubTopics);
        try {
            if (pojoQuestions.getDetails().get(i).getSolution_details() == null) {
                llsolution.setVisibility(View.GONE);
            } else {
                llsolution.setVisibility(View.VISIBLE);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        try {
            // Log.e("TAG", ":vvghhghfg.. " + roomDb.getLiveCode()+"...."+i);
            if (pojoQuestions.getDetails().get(i).getQuestion().isEmpty()) ;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            try {
                Toast.makeText(getActivity(), "Last question", Toast.LENGTH_SHORT).show();
                roomDb.setStartStatus(false);
                db.collection(getString(R.string.collectionName)).document(liveCode_).set(roomDb);
            } catch (Exception et) {
            }
            b = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            //Map map = new HashMap();
            if (b) {
                tv_chapterPages.setText((i + 1) + " of " + pojoQuestions.getDetails().size());
                tv_chapterPages1.setText(tv_chapterPages.getText().toString());
//                if (roomDb.getNextTapped()) roomDb.setNextTapped(false);
//                else
                roomDb.setNextTapped(false);
                roomDb.setCurrentQuestionIndex(i);
                roomDb.setSubmittedBy(new ArrayList<>());
                db.collection(getString(R.string.collectionName)).document(liveCode_).set(roomDb);

            } else {
                // map.put("startStatus", false);
                roomDb.setNextTapped(false);
                roomDb.setStartStatus(false);
                roomDb.setUsers(new ArrayList<>());
                db.collection(getString(R.string.collectionName)).document(liveCode_).set(roomDb);
                stopTimer();
                ;

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void fetchUserCount() {

        //userList.clear();
        usersListEventsListner =
                db.collection(getString(R.string.collectionName)).addSnapshotListener((snapshots, e) -> {
                    if (e != null) {
                        Log.w("TAG", "listen:error", e);
                        return;
                    }
                    for (DocumentChange d : snapshots.getDocumentChanges()) {
                        try {
                            roomDb = d.getDocument().toObject(RoomsForDb.class);
                            Log.w("TAG", "listen:sub1.." + roomDb.getLiveCode() + "..." + liveCode_ + "..." + roomDb.getSubmittedBy());

                            if (liveCode_.equalsIgnoreCase(roomDb.getLiveCode())) {
                                //Log.w("TAG", "listen:sub.."+roomsForDb.getSubmittedBy());
                                userList = roomDb.getUsers();
                                neutralValue.setText(userList.size() + "");
                                roomDb.setNextTapped(d.getDocument().getBoolean("nextTapped"));
                                roomDb.setDimension(d.getDocument().getString("dimension"));
                                roomDb.setLevel(d.getDocument().getString("level"));
                                roomDb.setSubtopics(d.getDocument().getString("subtopics"));
                                //roomDb.setSubmittedBy(d.getDocument().("nextTapped"));
                                if (roomDb.getStartStatus())
                                    getUserParticipateList();
                                else {
                                    stopTimer();
                                    //getActivity().onBackPressed();
                                    //((HomeActivity)getActivity()).onEndlive_Back();
                                }
                            }
                        } catch (Exception ex) {
                        }


                    }
                });
    }

    private void getUserParticipateList() {
//        HashMap mp = new HashMap<String, String>();
//        try {
//            //  String userid = convertStrigbuilderCaseStdy_q(userList, ",");
//            mp.put("question_id", pojoQuestions.getDetails().get(i).getQuestions_all_types_id());
//            mp.put("live_code", liveCode_);
//            apiCalls api = new apiCalls(); //getArguments().getString("topicId")
//            //pref!!.getValueString("token")
        HashMap mp = new HashMap<String, String>();
        HashMap mp2 = new HashMap<String, String>();
        apiCalls api = new apiCalls();
        try {
            //  String userid = convertStrigbuilderCaseStdy_q(userList, ",");

            mp.put("question_id", pojoQuestions.getDetails().get(i).getQuestions_all_types_id());
            mp.put("live_code", liveCode_);
            String useridstring = utills.Companion.convertStrigbuilderCaseStdy(userList, ",");

            mp2.put("users", useridstring);
            rlLoader.setVisibility(View.VISIBLE);
            Call<PojoUserDetail> calluselList = api.get_user_List(pref.getValueString("token"), mp2);
            calluselList.enqueue(new Callback<PojoUserDetail>() {
                @Override
                public void onResponse(Call<PojoUserDetail> call, Response<PojoUserDetail> response) {
                    rlLoader.setVisibility(View.GONE);
                    try {
                        if (response.isSuccessful()) {
                            Log.e("userlistparticipate", "" + response.body().getDetails().size());
                            notAttemptedByUser = new ArrayList<com.app.flipprteachear.home.pojo.userDetail.Detail>(response.body().getDetails());
                            Log.e("afterresult", "" + notAttemptedByUser.size());
                        } else {
                            Log.e("mssage", response.message());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<PojoUserDetail> call, Throwable t) {
                    rlLoader.setVisibility(View.GONE);
                    Log.e("userList", t.toString());
                }
            });
            Call<PojoSubmittedMarks> callApi = api.get_LiveSubmittedDetails(pref.getValueString("token"), mp);
            callApi.enqueue(new Callback<PojoSubmittedMarks>() {
                @Override
                public void onResponse(Call<PojoSubmittedMarks> call, Response<PojoSubmittedMarks> response) {
                    rlLoader.setVisibility(View.GONE);
                    try {
                        if (response.body() != null)
                            setAdapterForPoints(response.body());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<PojoSubmittedMarks> call, Throwable t) {
                    rlLoader.setVisibility(View.GONE);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAdapterForPoints(PojoSubmittedMarks body) {
        List<UserDetail> usersData = new ArrayList();
        qsData = new QuestionDetail();
        //overallAdapter.updateListType(); overallAdapter

        try {
            HashMap<String, UserDetail> hMap = new HashMap<>();
            int countRight = 0, countWrong = 0, countNetural = 0;

            for (QuestionDetail qs : body.getQuestionDetails()) {
                for (int j = 0; j < qs.getUserDetail().size(); j++) {
                    Float fPoint = 0f;
                    if (hMap.containsKey(qs.getUserDetail().get(j).getUserId())) {
                        Log.e("TAG", "setAdapterForPoints:if.. " + qs.getUserDetail().get(j).getSubmitDetail().getQuesDetail().getAnsPoints());

                        String sPoints1 = hMap.get(qs.getUserDetail().get(j).getUserId()).getSubmitDetail().getQuesDetail().getAnsPoints();//.replace("+", "").replace("-", "");
                        String sPoints2 = qs.getUserDetail().get(j).getSubmitDetail().getQuesDetail().getAnsPoints();//.replace("+", "").replace("-", "");
//                        int sStatus1 = hMap.get(us.getUserId()).getSubmitDetail().getQuesDetail().getAnsStatus().equalsIgnoreCase("0")? -1 : 1;//.replace("+", "").replace("-", "");
//                        int sStatus2 = us.getSubmitDetail().getQuesDetail().getAnsStatus().equalsIgnoreCase("0")? -1 : 1;//.replace("+", "").replace("-", "");
//                      if(!us.getSubmitDetail().getQuesDetail().getAnsStatus().equalsIgnoreCase("0"))
//                      {
                        fPoint = Float.valueOf(sPoints1) + Float.valueOf(sPoints2);
//                          fPoint = Math.abs(Float.valueOf(sPoints1)) *  sStatus1
//                                  + Math.abs(Float.valueOf(sPoints2))*sStatus2;
                        Log.e("TAG", "setAdapterForPoints:fff.. " + fPoint + "..." + Math.abs(Float.valueOf(sPoints1)) + "..." + Math.abs(Float.valueOf(sPoints2)));
                        hMap.get(qs.getUserDetail().get(j).getUserId()).getSubmitDetail().getQuesDetail().setAnsStatus(qs.getUserDetail().get(j).getSubmitDetail().getQuesDetail().getAnsStatus());

                        //}
                        //else  fPoint = Float.valueOf(sPoints1) - Float.valueOf(sPoints2);
                        hMap.get(qs.getUserDetail().get(j).getUserId()).getSubmitDetail().getQuesDetail().setAnsPoints(String.format("%.1f", fPoint));
                        hMap.get(qs.getUserDetail().get(j).getUserId()).getSubmitDetail().getQuesDetail().setAnsStatus(qs.getUserDetail().get(j).getSubmitDetail().getQuesDetail().getAnsStatus());

                    } else {
                        Log.e("TAG", "setAdapterForPoints:else.. " + qs.getUserDetail().get(j).getSubmitDetail().getQuesDetail().getAnsPoints());

                        hMap.put(qs.getUserDetail().get(j).getUserId(), qs.getUserDetail().get(j));
                    }

                }
            }

            ArrayList<UserDetail> tempArrayListWrong = new ArrayList<>();     // step 11
            ArrayList<UserDetail> tempArrayListRight = new ArrayList<>();

            for (QuestionDetail qs : body.getQuestionDetails()) {
                for (UserDetail us : qs.getUserDetail()) {

                    if (us.getSubmitDetail().getQuesDetail().getQuestionId().equalsIgnoreCase(questionId)) {
                        if (us.getSubmitDetail().getQuesDetail().getAnsStatus().equalsIgnoreCase("1")) {
                            countRight++;
                            tempArrayListRight.add(us);
                        }                // step22
                        else if (us.getSubmitDetail().getQuesDetail().getAnsStatus().equalsIgnoreCase("0")) {
                            countWrong++;
                            tempArrayListWrong.add(us);
                        }
                        try {
                            qsData = qs;
                            rvQuesTopScores.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    //  Log.e("TAG", "setAdapterForPoints: " + countNetural + ".." + countRight + "" + countWrong + "");
                    rightVlaue.setText(countRight + "");
                    wrongVlaue.setText(countWrong + "");

                    if (us.getSubmitDetail().getQuesDetail().getQuestionId().equalsIgnoreCase(questionId)) {
                        String defineType = pojoQuestions.getDetails().get(i).getDefine_type() == null ? pojoQuestions.getDetails().get(i).getType() : pojoQuestions.getDetails().get(i).getDefine_type();

                        getSetDefineType_Live(defineType, qs.getUserDetail());
                    }
                }

                try {
                    if (qsData != null)
                        if (qsData.getUserDetail() != null) {
                            Collections.sort(qsData.getUserDetail(), (o1, o2) -> Float.valueOf(o2.getSubmitDetail().getQuesDetail().getAnsPoints()).compareTo(
                                    Float.valueOf(o1.getSubmitDetail().getQuesDetail().getAnsPoints())
                            ));

                            tv_highLow_ques.setText(getString(R.string.high_low));
                            Log.e("TAG", "setAdapterForPoints:..... " + qsData.getUserDetail().size());
                            overScoreInOneQuesAdapter.updateListType(qsData, questionId);
                        }


                    tv_highLow_ques.setOnClickListener(v -> {
                        isHigh = !isHigh;
                        if (isHigh) tv_highLow_ques.setText(getString(R.string.high_low));
                        else tv_highLow_ques.setText(getString(R.string.low_high));
                        getLOwToHigh_List(qsData.getUserDetail(), !isHigh);
                        overScoreInOneQuesAdapter.updateListType(qsData, questionId);
                    });
                    //if(pojoQuestions.getDetails().get(i).getQuestions_all_types_id().equalsIgnoreCase(body.getQuestionDetails().get(i).getQuestionId()))

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            rightAnsListUser = tempArrayListRight;
            wrongAnsListUser = tempArrayListWrong;
            Log.e("TAG", "countRightWrong: " + userList.size() + ".." + countRight + ".." + countWrong);
            countNetural = userList.size() - (countRight + countWrong);
            neutralValue.setText(countNetural + "");
            usersData.addAll(hMap.values());
//            Collections.sort(usersData, (o1, o2) -> Float.valueOf(o1.getSubmitDetail().getQuesDetail().getAnsPoints()).compareTo(
//                    Float.valueOf(o2.getSubmitDetail().getQuesDetail().getAnsPoints())
//            ));
            Collections.sort(usersData, (o1, o2) -> Float.valueOf(o2.getSubmitDetail().getQuesDetail().getAnsPoints()).compareTo(
                    Float.valueOf(o1.getSubmitDetail().getQuesDetail().getAnsPoints())
            ));
            overallAdapter.updateListType(usersData);

            Log.e("TAG", "setAdapterForPoints:size.. " + hMap.size());
            tv_highLow.setText(getString(R.string.high_low));
            tv_highLow.setOnClickListener(v -> {
                isHigh2 = !isHigh2;
                if (!isHigh2) tv_highLow.setText(getString(R.string.high_low));
                else tv_highLow.setText(getString(R.string.low_high));
                getLOwToHigh_List(usersData, isHigh2);
                overallAdapter.updateListType(usersData);
            });
//            tv_highLow.performClick();
//            tv_highLow_ques.performClick();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getLOwToHigh_List(List<UserDetail> usersData, boolean isHigh) {
        if (isHigh)
            Collections.sort(usersData, (o1, o2) -> Float.valueOf(o1.getSubmitDetail().getQuesDetail().getAnsPoints()).compareTo(
                    Float.valueOf(o2.getSubmitDetail().getQuesDetail().getAnsPoints())
            ));
        else
            Collections.sort(usersData, (o1, o2) -> Float.valueOf(o2.getSubmitDetail().getQuesDetail().getAnsPoints()).compareTo(
                    Float.valueOf(o1.getSubmitDetail().getQuesDetail().getAnsPoints())
            ));
    }

    private void getSetDefineType_Live(String define_type, List<UserDetail> us) {
        // "Image MCQ", "Multiple Image MCQ", "Single Image MCQ", "Multiple Text MCQ",  "Single Text MCQ"
        try {
            String type_Define = define_type;
            if (define_type.equalsIgnoreCase("Text MCQ") || define_type.equalsIgnoreCase("Multiple Text MCQ") || define_type.equalsIgnoreCase("Single Text MCQ")) {
                queadapter.updateSubmitted(us);
            } else if (define_type.equalsIgnoreCase("Image MCQ")
                    || define_type.equalsIgnoreCase("Multiple Image MCQ")
                    || define_type.equalsIgnoreCase("Single Image MCQ")
                    || define_type.equalsIgnoreCase("Assertion Reasoning")

            ) {
                queadapter.updateSubmitted(us);
            } else if (define_type.equalsIgnoreCase("Truefalse")
                    || define_type.equalsIgnoreCase("Image Truefalse")
                    || define_type.equalsIgnoreCase("Text Truefalse")) {
                adapterTrueFalse.updateList(us);
            } else if (define_type.equalsIgnoreCase("Case Study")) {
                adapterCase.updateSubmitted(us);
            } else if (define_type.equalsIgnoreCase("Descriptive")) {
                adapterDescrip.updateSubmitted(us);
                answerAdapter.updateSubmitted(us);

            } else if (define_type.equalsIgnoreCase("Numerical")) {
                nuricalAdapter.updateSubmitted(us);
            }
            pojoQuestions.getDetails().get(i).setDefine_type(type_Define);
            Log.e("getSetDefineType: ", "" + pojoQuestions.getDetails().get(i).getDefine_type() + "..." + type_Define + "....." + define_type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //private static Animator currentAnimator;

    private void runTimer() {
        // Creates a new Handler
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

                // Set the text view text.
                tv_learn.setText(time);

                // If running is true, increment the
                // seconds variable.
                if (running) {
                    seconds++;

                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void countDounRefreshTimer() {
        mCountDownTimerRefresh = new CountDownTimer(61000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                secondsForRefresh++;
                if (secondsForRefresh >= 60) {
                    int hour = count / 60;
                    tvUpdate.setText("Updated " + hour + " hour ago");
                } else {
                    tvUpdate.setText("Updated " + secondsForRefresh + " minute ago");
                }
                mCountDownTimerRefresh.start();
            }

        };

        mCountDownTimerRefresh.start();
    }

    private void getSubmit_fillinTheBlanks(Detail detail) {
        int marksRight = 0;
        int totalRightAnss = 0;
        if (detail.getFillup_answer_details() != null) {
            totalRightAnss = detail.getFillup_answer_details().size();
        }
        Recycler_answer.setVisibility(View.GONE);
        fbvContent.setVisibility(View.GONE);
        editAns = fbvContent.getAnswerList();
        if (!editAns.isEmpty())
            ansfillup = convertStrigbuilder3(editAns, ",", "##");
        // System.out.println("Arrayswords000ansfillup.editAns:-." + editAns+"..."+ansfillup);
        for (int k = 0; k < detail.getFillup_answer_details().size(); k++) {
            if (detail.getFillup_answer_details().get(k).getFillupAnswers().trim().equalsIgnoreCase(fbvContent.getAnswerList().get(k).toString().trim())) {
                System.out.println("matched111......." + detail.getFillup_answer_details().get(k).getFillupAnswers() + ".." + fbvContent.getAnswerList().get(k));
                String text = "<font color=#26e67a>" + fbvContent.getAnswerList().get(k).toString() + "</font> ";
                fbvContent.getAnswerList().set(k, text);
                marksRight++;
            } else {
                if (fbvContent.getAnswerList().get(k).isEmpty()) {
                    String text = "<font color=#26e67a>" + detail.getFillup_answer_details().get(k).getFillupAnswers() + "</font> ";
                    System.out.println("matched111......." + detail.getFillup_answer_details() + ".." + fbvContent.getAnswerList().get(k));
                    fbvContent.getAnswerList().set(k, text);
                } else {
                    String text = "<font color=#cc0029>" + fbvContent.getAnswerList().get(k).toString() + "</font> " + " <font color=#26e67a>" + detail.getFillup_answer_details().get(k).getFillupAnswers() + "</font>";
                    System.out.println("matched000......." + detail.getFillup_answer_details().get(k).getFillupAnswers() + ".." + fbvContent.getAnswerList().get(k));
                    fbvContent.getAnswerList().set(k, text);
                }
                marksRight = 0;
            }
        }
        int count = 1, charCount = 0;
        for (int k = 0; k < lines2.size(); k++) {
            if (lines2.get(k).equalsIgnoreCase("_____")) {
                String sss = lines2.get(k).replace("_____", fbvContent.getAnswerList().get(charCount));
                // System.out.print("targett000.." + " ##" + count + "replacement+.. " + "-----");
                lines2.set(k, sss);
                charCount++;
            }
        }
        String ansfill = convertStrigbuilder2(lines2, " ");
        tvFillUpAns.setText(Html.fromHtml(ansfill));

        System.out.println("getAnswerList........" + fbvContent.getAnswerList().size() + " ..... " + fbvContent.getAnswerList());

        submitSpeeak = 3;
        llsolnext.setVisibility(View.VISIBLE);
        llnext.setVisibility(View.VISIBLE);

    }


}
