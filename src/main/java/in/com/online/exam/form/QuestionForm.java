package in.com.online.exam.form;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import in.com.online.exam.dto.BaseDTO;
import in.com.online.exam.dto.QuestionDTO;
import in.com.online.exam.util.DataUtility;



public class QuestionForm extends BaseForm {

	private String examName;
	@NotEmpty(message = "Question Name is required")
	private String questionName;
	@NotEmpty(message = "Option 1 is required")
	private String option1;
	@NotEmpty(message = "Option 2 is required")
	private String option2;
	@NotEmpty(message = "Option 3 is required")
	private String option3;
	@NotEmpty(message = "Option 4 is required")
	private String option4;
	@NotEmpty(message = "Correct Ans is required")
	private String correctAns;
	
	@Min(value = 1,message ="Exam Name is required")
	private long examId;
	
	private String myAns;
	
	
	public long getExamId() {
		return examId;
	}

	public void setExamId(long examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}

	public String getMyAns() {
		return myAns;
	}

	public void setMyAns(String myAns) {
		this.myAns = myAns;
	}

	@Override
	public BaseDTO getDTO() {
		QuestionDTO dto=new QuestionDTO();
		dto.setId(id);
		dto.setExamId(examId);
		dto.setExamName(examName);
		dto.setQuestionName(questionName);
		dto.setOption1(option1);
		dto.setOption2(option2);
		dto.setOption3(option3);
		dto.setOption4(option4);
		dto.setCorrectAns(correctAns);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {
		QuestionDTO dto=(QuestionDTO)bdto;
		id=dto.getId();
		examName=dto.getExamName();
		questionName=dto.getQuestionName();
		examId=dto.getExamId();
		option1=dto.getOption1();
		option2=dto.getOption2();
		option3=dto.getOption3();
		option4=dto.getOption4();
		correctAns=dto.getCorrectAns(); 
		createdBy=dto.getCreatedBy();
		modifiedBy=dto.getModifiedBy();
		createdDateTime=dto.getCreatedDatetime();
		modifiedDateTime=dto .getModifiedDatetime();
	}

}
