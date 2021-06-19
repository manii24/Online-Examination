package in.com.online.exam.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import in.com.online.exam.dao.ExamDAOInt;
import in.com.online.exam.dto.BaseDTO;
import in.com.online.exam.dto.ExamDTO;
import in.com.online.exam.util.DataUtility;

public class ExamForm extends BaseForm {

	@NotEmpty(message = "Exam Name is required")
	private String examName;
	
	@NotEmpty(message = "Exam Date is required")
	@Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$",message = "Date is Invalid")
	private String examDate;
	
	
	
	
	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	@Override
	public BaseDTO getDTO() {
		ExamDTO dto=new ExamDTO();
		dto.setId(id);
		dto.setExamName(examName);
		dto.setExamDate(DataUtility.getDate(examDate));
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {
		ExamDTO dto=(ExamDTO)bdto;
		id=dto.getId();
		examDate=DataUtility.getDateString(dto.getExamDate());
		examName=dto.getExamName();
		createdBy=dto.getCreatedBy();
		modifiedBy=dto.getModifiedBy();
		createdDateTime=dto.getCreatedDatetime();
		modifiedDateTime=dto .getModifiedDatetime();
	}

}
