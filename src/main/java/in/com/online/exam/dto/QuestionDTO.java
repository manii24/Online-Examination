package in.com.online.exam.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTIONS")
public class QuestionDTO extends BaseDTO {

		@Column(name="EXAM_NAME",length = 225)
		private String examName;
		@Column(name="QUESTION_NAME",length = 225)
		private String questionName;
		@Column(name="OPTION1",length = 225)
		private String option1;
		@Column(name="OPTION2",length = 225)
		private String option2;
		@Column(name="OPTION3",length = 225)
		private String option3;
		@Column(name="OPTION4",length = 225)
		private String option4;
		@Column(name="CORRECT_ANS",length = 225)
		private String correctAns;
		private String myAns;
		@Column(name="EXAM_ID")
		private long examId;
		
		private int pageNo;
		
		
		
	
		
		public int getPageNo() {
			return pageNo;
		}


		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}


		public long getExamId() {
			return examId;
		}


		public void setExamId(long examId) {
			this.examId = examId;
		}


		public QuestionDTO() {
		}
		
	
	public QuestionDTO(String questionName, String correctAns, String myAns,int pageNo) {
			
			this.questionName = questionName;
			this.correctAns = correctAns;
			this.myAns = myAns;
			this.pageNo=pageNo;
		}

	public String getMyAns() {
			return myAns;
		}

		public void setMyAns(String myAns) {
			this.myAns = myAns;
		}

	@Override
		public String toString() {
			return "QuestionBean [examName=" + examName + ", questionName=" + questionName + ", option1=" + option1
					+ ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4 + ", correctAns="
					+ correctAns + "]";
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

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
