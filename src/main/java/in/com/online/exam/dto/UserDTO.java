package in.com.online.exam.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USER")
public class UserDTO extends BaseDTO {
	
	@Column(name="F_NAME",length = 225)
	private String fName;
	@Column(name="L_NAME",length = 225)
	private String lName;
	@Column(name="LOGIN",length = 225)
	private String login;
	@Column(name="PASSWORD",length = 225)
	private String password;
	private String confirmPassword;
	@Column(name="MOBILE_NO",length = 225)
	private String mobileNo;
	@Column(name="DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;
	@Column(name="ADDRESS",length = 225)
	private String address;
	@Column(name="GENDER",length = 225)
	private String gender;
	@Column(name="ROLE_ID")
	private long role_Id;
	@Column(name="ROLE_NAME",length = 225)
	private String roleName;
	
	@Column(name="SKILLS",length = 225)
	private String skills;
	
	@Column(name="QUALIFICATION",length = 225)
	private String qualification;
	 
	@Column(name="WORK_EXPERIENCE",length = 225)
	private String workExperience;
	
	@Column(name="SUB_START_DATE")
	@Temporal(TemporalType.DATE)
	private Date subStartDate;
	
	@Column(name="SUB_END_DATE")
	@Temporal(TemporalType.DATE)
	private Date subEndDate;
	
	@Column(name="STATUS",length = 225)
	private String status;
	
	@Column(name="SUB_TYPE",length = 225)
	private String subType;
	
	
	
	
	public Date getSubStartDate() {
		return subStartDate;
	}
	public void setSubStartDate(Date subStartDate) {
		this.subStartDate = subStartDate;
	}
	public Date getSubEndDate() {
		return subEndDate;
	}
	public void setSubEndDate(Date subEndDate) {
		this.subEndDate = subEndDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public long getRole_Id() {
		return role_Id;
	}
	public void setRole_Id(long role_Id) {
		this.role_Id = role_Id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getFName() {
		return fName;
	}
	public void setFName(String fName) {
		this.fName = fName;
	}
	public String getLName() {
		return lName;
	}
	public void setLName(String lName) {
		this.lName = lName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String getKey() {
		return id+"";
	}
	@Override
	public String getValue() {
		return fName;
	}
	
	
	
	

}
