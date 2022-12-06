package com.app.flipprteachear.home.view.adapterMcq

import com.app.flipprteachear.home.pojo.liveQuesPojo.SolutionDetails
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PojoQuestions : Serializable {
    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("student_session_activity")
    @Expose
    var studentSessionActivity: StudentSessionActivity? = null

    @SerializedName("details")
    @Expose
    var details: List<Detail>? = null

    inner class AnswerDetail : Serializable {
        @SerializedName("right_txt_ans_id")
        @Expose
        var rightTxtAnsId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("right_txt_ans1")
        @Expose
        var rightTxtAns1: String? = null

        @SerializedName("types")
        @Expose
        var types: String? = null

        @SerializedName("question_img")
        @Expose
        var questionImg: String? = null
    }

    inner class ArrangeAnswerDetail : Serializable {
        @SerializedName("arrange_right_ans_id")
        @Expose
        var arrangeRightAnsId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("right_txt_ans")
        @Expose
        var rightTxtAns: String? = null

        @SerializedName("types")
        @Expose
        var types: String? = null

        @SerializedName("ans_img")
        @Expose
        var ansImg: String? = null
    }

    inner class ArrangeSolutionDetail : Serializable {
        @SerializedName("arrange_solution_id")
        @Expose
        var arrangeSolutionId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("arrange_ans_desc")
        @Expose
        var arrangeAnsDesc: String? = null

        @SerializedName("arrange_ans_link")
        @Expose
        var arrangeAnsLink: String? = null
    }

    inner class DescriptiveAnswerDetail : Serializable {
        @SerializedName("right_txt_desc_id")
        @Expose
        var rightTxtDescId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("right_txt_ans")
        @Expose
        var rightTxtAns: String? = null

        @SerializedName("txt_type")
        @Expose
        var txtType: String? = null
    }

    inner class DescriptiveVisualizationDetail : Serializable {
        @SerializedName("right_img_desc_id")
        @Expose
        var rightImgDescId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("desc_images")
        @Expose
        var descImages: String? = null

        @SerializedName("img_type")
        @Expose
        var imgType: String? = null
    }

    inner class Detail : Serializable {
        @SerializedName("flips_marks")
        @Expose
        var flipsMarks: List<FlipsMark>? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("question")
        @Expose
        var question: String? = null

        @SerializedName("image")
        @Expose
        var image: String? = null

        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("segment_id")
        @Expose
        var segmentId: String? = null

        @SerializedName("level_id")
        @Expose
        var levelId: String? = null

        @SerializedName("dimensions_id")
        @Expose
        var dimensionsId: String? = null

        @SerializedName("source_id")
        @Expose
        var sourceId: String? = null

        @SerializedName("exam_importance_id")
        @Expose
        var examImportanceId: String? = null

        @SerializedName("date_time")
        @Expose
        var dateTime: String? = null

        @SerializedName("define_type")
        @Expose
        var define_type: String? = null

        @SerializedName("answer_details")
        @Expose
        var answerDetails: List<AnswerDetail>? = null

        @SerializedName("solution_details")
        @Expose
        var solutionDetails: SolutionDetails? = null

        @SerializedName("arrange_answer_details")
        @Expose
        var arrangeAnswerDetails: List<ArrangeAnswerDetail>? = null

        @SerializedName("arrange_solution_details")
        @Expose
        var arrangeSolutionDetails: List<ArrangeSolutionDetail>? = null

        @SerializedName("truefalse_answer_details")
        @Expose
        var truefalseAnswerDetails: List<TruefalseAnswerDetail>? = null

        @SerializedName("truefalse_solution_details")
        @Expose
        var truefalseSolutionDetails: List<TruefalseSolutionDetail>? = null

        @SerializedName("n_interpretation_answer_details")
        @Expose
        var nInterpretationAnswerDetails: List<NInterpretationAnswerDetail>? = null

        @SerializedName("n_visualization_ans_details")
        @Expose
        var nVisualizationAnsDetails: List<NVisualizationAnsDetail>? = null

        @SerializedName("n_application_val_details")
        @Expose
        var nApplicationValDetails: List<NApplicationValDetail>? = null

        @SerializedName("n_application_details")
        @Expose
        var nApplicationDetails: List<NApplicationDetail>? = null

        @SerializedName("numerical_solution_details")
        @Expose
        var numericalSolutionDetails: List<NumericalSolutionDetail>? = null

        @SerializedName("descriptive_visualization_details")
        @Expose
        var descriptiveVisualizationDetails: List<DescriptiveVisualizationDetail>? = null

        @SerializedName("descriptive_answer_details")
        @Expose
        var descriptiveAnswerDetails: List<DescriptiveAnswerDetail>? = null

        @SerializedName("descriptive_chapterkeyword_details")
        @Expose
        var descriptiveChapterkeywordDetails: List<DescriptiveChapterkeywordDetail>? = null

        @SerializedName("diagram_answer_details")
        @Expose
        var diagramAnswerDetails: List<DiagramAnswerDetail>? = null

        //        @SerializedName("define_type")
        //        @Expose
        //        private String defineType;
        @SerializedName("ans_screenshort")
        @Expose
        var ansScreenshort: String? = null

        @SerializedName("cluster_name")
        @Expose
        var clusterName: String? = null

        @SerializedName("chapter_name")
        @Expose
        var chapterName: String? = null

        @SerializedName("topic_name")
        @Expose
        var topicName: String? = null

        @SerializedName("topic_image")
        @Expose
        var topicImage: String? = null

        @SerializedName("subtopic_name")
        @Expose
        var subtopicName: String? = null

        @SerializedName("segment_name")
        @Expose
        var segmentName: String? = null

        @SerializedName("subtopic_id")
        @Expose
        var subtopic_id: String? = null

        @SerializedName("fillup_answer_details")
        @Expose
        var fillupAnswerDetails: List<FillupAnswerDetail>? = null

        @SerializedName("case_study_details")
        @Expose
        var caseStudyDetails: List<CaseStudyDetail>? = null

        @SerializedName("case_study_ques_details")
        @Expose
        var caseStudyQuesDetails: List<CaseStudyQuesDetail>? = null

        @SerializedName("assertion_reasoning_details")
        @Expose
        var assertionReasoningDetails: List<AssertionReasoningDetail>? = null

        @SerializedName("diagram_image")
        @Expose
        var diagramImage: String? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("content_creator_id")
        @Expose
        var contentCreatorId: String? = null

        @SerializedName("course_id")
        @Expose
        var courseId: String? = null

        @SerializedName("subject_id")
        @Expose
        var subjectId: String? = null

        @SerializedName("chapter_id")
        @Expose
        var chapterId: String? = null
    }

    inner class NApplicationValDetail : Serializable {
        @SerializedName("application_ans_val_id")
        @Expose
        var applicationAnsValId: String? = null

        @SerializedName("numerical_qty_val_id")
        @Expose
        var numericalQtyValId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("quantity1")
        @Expose
        var quantity1: String? = null

        @SerializedName("correct_option")
        @Expose
        var correctOption: String? = null

        @SerializedName("wrong_option")
        @Expose
        var wrongOption: String? = null
    }

    inner class NApplicationDetail : Serializable {
        @SerializedName("numerical_qty_val_id")
        @Expose
        var numericalQtyValId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("quantity")
        @Expose
        var quantity: String? = null

        @SerializedName("value")
        @Expose
        var value: String? = null

        @SerializedName("unit")
        @Expose
        var unit: String? = null

        @SerializedName("quantity1")
        @Expose
        var quantity1: String? = null

        @SerializedName("correct_option")
        @Expose
        var correctOption: String? = null

        @SerializedName("wrong_option")
        @Expose
        var wrongOption: String? = null

        @SerializedName("application_type")
        @Expose
        var applicationType: String? = null
    }

    inner class NInterpretationAnswerDetail : Serializable {
        @SerializedName("interpretation_ans_id")
        @Expose
        var interpretationAnsId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("interpretation_answers")
        @Expose
        var interpretationAnswers: String? = null

        @SerializedName("interpretation_right_wrong")
        @Expose
        var interpretationRightWrong: String? = null

        @SerializedName("interp_type")
        @Expose
        var interpType: String? = null
    }

    inner class NVisualizationAnsDetail : Serializable {
        @SerializedName("visualization_ans_id")
        @Expose
        var visualizationAnsId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("visualization_answers")
        @Expose
        var visualizationAnswers: String? = null

        @SerializedName("visualization_right_wrong")
        @Expose
        var visualizationRightWrong: String? = null

        @SerializedName("visualization_type")
        @Expose
        var visualizationType: String? = null
    }

    inner class NumericalSolutionDetail : Serializable {
        @SerializedName("numerical_solution_id")
        @Expose
        var numericalSolutionId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("numerical_ans_desc")
        @Expose
        var numericalAnsDesc: String? = null

        @SerializedName("numerical_ans_link")
        @Expose
        var numericalAnsLink: String? = null

        @SerializedName("numerical_solution_type")
        @Expose
        var numericalSolutionType: String? = null
    }

    inner class SolutionDetail : Serializable {
        @SerializedName("mcq_solution_id")
        @Expose
        var mcqSolutionId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("mcq_ans_desc")
        @Expose
        var mcqAnsDesc: String? = null

        @SerializedName("mcq_ans_link")
        @Expose
        var mcqAnsLink: String? = null
    }

    inner class TruefalseAnswerDetail : Serializable {
        @SerializedName("right_truefalse_ans_id")
        @Expose
        var rightTruefalseAnsId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("right_txt_ans")
        @Expose
        var rightTxtAns: String? = null

        @SerializedName("tf_type")
        @Expose
        var tfType: String? = null

        @SerializedName("truefalse_img")
        @Expose
        var truefalseImg: String? = null
    }

    inner class TruefalseSolutionDetail : Serializable {
        @SerializedName("truefalse_solution_id")
        @Expose
        var truefalseSolutionId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("truefalsel_ans_desc")
        @Expose
        var truefalselAnsDesc: String? = null

        @SerializedName("truefalse_ans_link")
        @Expose
        var truefalseAnsLink: String? = null
    }

    inner class DescriptiveChapterkeywordDetail : Serializable {
        @SerializedName("chapter_id")
        @Expose
        var chapterId: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("chapter_keyword")
        @Expose
        var chapterKeyword: String? = null
    }

    inner class DiagramAnswerDetail {
        @SerializedName("diagrams_id")
        @Expose
        var diagramsId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("value")
        @Expose
        var value: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("x_value")
        @Expose
        var xValue: String? = null

        @SerializedName("y_value")
        @Expose
        var yValue: String? = null

        @SerializedName("type")
        @Expose
        var type: String? = null
    }

    inner class FlipsMark {
        @SerializedName("flip_marks_id")
        @Expose
        var flipMarksId: String? = null

        @SerializedName("marks")
        @Expose
        var marks: String? = null

        @SerializedName("type")
        @Expose
        var type: String? = null
    }

    inner class FillupAnswerDetail : Serializable {
        @SerializedName("fillup_ans_id")
        @Expose
        var fillupAnsId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("blank_id")
        @Expose
        var blankId: String? = null

        @SerializedName("fillup_answers")
        @Expose
        var fillupAnswers: String? = null
    }

    inner class CaseStudyDetail : Serializable {
        @SerializedName("casestudy_id")
        @Expose
        var casestudyId: String? = null

        @SerializedName("case")
        @Expose
        var case: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null
    }

    inner class CaseStudyQuesDetail : Serializable {
        @SerializedName("casestudy_ques_id")
        @Expose
        var casestudyQuesId: String? = null

        @SerializedName("case_study")
        @Expose
        var caseStudy: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("casestudy_id")
        @Expose
        var casestudyId: String? = null

        @SerializedName("casestudy_mcqs")
        @Expose
        var casestudyMcqs: List<CasestudyMcq>? = null
    }

    inner class CasestudyMcq : Serializable {
        @SerializedName("right_txt_ans_id")
        @Expose
        var rightTxtAnsId: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null

        @SerializedName("right_txt_ans1")
        @Expose
        var rightTxtAns1: String? = null

        @SerializedName("types")
        @Expose
        var types: String? = null

        @SerializedName("question_img")
        @Expose
        var questionImg: String? = null

        @SerializedName("casestudy_ques_id")
        @Expose
        var casestudyQuesId: String? = null
    }

    inner class AssertionReasoningDetail : Serializable {
        @SerializedName("assertion_reasoning_ques_id")
        @Expose
        var assertionReasoningQuesId: String? = null

        @SerializedName("assertion")
        @Expose
        var assertion: String? = null

        @SerializedName("reasoning")
        @Expose
        var reasoning: String? = null

        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("questions_all_types_id")
        @Expose
        var questionsAllTypesId: String? = null
    }

    inner class StudentSessionActivity : Serializable {
        @SerializedName("student_session_activity_id")
        @Expose
        var studentSessionActivityId: String? = null

        @SerializedName("student_id")
        @Expose
        var studentId: String? = null

        @SerializedName("topic_id")
        @Expose
        var topicId: String? = null

        @SerializedName("break_time")
        @Expose
        var breakTime: String? = null

        @SerializedName("is_topic_done")
        @Expose
        var isTopicDone: String? = null

        @SerializedName("streak_saver_used_before")
        @Expose
        var streakSaverUsedBefore: String? = null

        @SerializedName("ans_correct_series")
        @Expose
        var ansCorrectSeries: String? = null

        @SerializedName("subject_id")
        @Expose
        var subjectId: String? = null

        @SerializedName("session_total_time")
        @Expose
        var sessionTotalTime: String? = null

        @SerializedName("chapter_id")
        @Expose
        var chapterId: String? = null

        @SerializedName("break_start_time")
        @Expose
        var breakStartTime: String? = null

        @SerializedName("is_going_for_break")
        @Expose
        var isGoingForBreak: String? = null

        @SerializedName("cluster_id")
        @Expose
        var cluster_id: String? = null

        @SerializedName("student_boost_activity_id")
        @Expose
        var student_boost_activity_id: String? = null

        @SerializedName("student_revise_activity_id")
        @Expose
        var student_revise_activity_id: String? = null

        @SerializedName("course_id")
        @Expose
        var course_id: String? = null
    }
}