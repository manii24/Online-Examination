package in.com.online.exam.form;

import java.util.Date;


import in.com.online.exam.dto.BaseDTO;
import in.com.online.exam.dto.ResultDTO;



public class ResultForm extends BaseForm {

	private String examinationName;
	private Date examinationDate;
	private String result;
	private long user_id;
	private String userName;
	private String count;
	private String size;
	
	
	
	
	
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

	@Override
	public BaseDTO getDTO() {
		ResultDTO dto=new ResultDTO();
		dto.setId(id);
		dto.setExaminationName(examinationName);
		dto.setExaminationDate(examinationDate);
		dto.setResult(result);
		dto.setUser_id(user_id);
		dto.setUserName(userName);
		dto.setCount(count);
		dto.setSize(size);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		return dto;
	}

	@Override
	public void populate(BaseDTO bdto) {
		ResultDTO dto=(ResultDTO)bdto;
		id=dto.getId();
		examinationName=dto.getExaminationName();
		examinationDate=dto.getExaminationDate();
		result=dto.getResult();
		user_id=dto.getUser_id();
		userName=dto.getUserName();
		count=dto.getCount();
		size=dto.getSize();
		createdBy=dto.getCreatedBy();
		modifiedBy=dto.getModifiedBy();
		createdDateTime=dto.getCreatedDatetime();
		modifiedDateTime=dto .getModifiedDatetime();
	}

}
