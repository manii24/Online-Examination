package in.com.online.exam.form;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import in.com.online.exam.dto.BaseDTO;
import in.com.online.exam.dto.QuestionDTO;
import in.com.online.exam.util.DataUtility;



public class StartExamForm extends BaseForm {

	
	@Min(value = 1,message ="Exam Name is required")
	private long examId;
	
	
	
	public long getExamId() {
		return examId;
	}

	public void setExamId(long examId) {
		this.examId = examId;
	}

	

	@Override
	public BaseDTO getDTO() {
		QuestionDTO dto=new QuestionDTO();
		dto.setId(id);
		dto.setExamId(examId);
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {
		QuestionDTO dto=(QuestionDTO)bdto;
		id=dto.getId();
		examId=dto.getExamId();
	}

}
