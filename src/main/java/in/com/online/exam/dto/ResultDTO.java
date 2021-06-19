package in.com.online.exam.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="RESULT")
public class ResultDTO  extends BaseDTO{
	
	@Column(name="EXAM_NAME",length = 225)
	private String examinationName;
	@Column(name="EXAM_DATE")
	@Temporal(TemporalType.DATE)
	private Date examinationDate;
	@Column(name="RESULT",length = 225)
	private String result;
	@Column(name="USER_ID")
	private long user_id;
	
	@Column(name="USER_NAME",length = 225)
	private String userName;
	
	@Column(name="COUNT",length = 225)
	private String count;
	
	@Column(name="SIZE",length = 225)
	private String size;
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "ResultDTO [examinationName=" + examinationName + ", examinationDate=" + examinationDate + ", result="
				+ result + ", user_id=" + user_id + ", userName=" + userName + ", count=" + count + ", size=" + size
				+ "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getExaminationName() {
		return examinationName;
	}
	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}
	public Date getExaminationDate() {
		return examinationDate;
	}
	public void setExaminationDate(Date examinationDate) {
		this.examinationDate = examinationDate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
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
