package in.com.online.exam.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EXAM")
public class ExamDTO extends BaseDTO{
	
	@Column(name="EXAM_NAME",length = 225)
	private String examName;
	@Column(name="EXAM_DATE",length = 225)
	private Date examDate;
	
	
	

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return examName;
	}

}
