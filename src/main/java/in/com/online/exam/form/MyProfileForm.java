package in.com.online.exam.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import in.com.online.exam.dto.BaseDTO;
import in.com.online.exam.dto.UserDTO;
import in.com.online.exam.util.DataUtility;

	
public class MyProfileForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$", message = "First Name is Invalid")
	private String firstName;

	@NotEmpty(message = "LastName is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$", message = "Last Name is Invalid")
	private String lastName;

	@NotEmpty(message = "Login is required")
	private String login;

	@NotEmpty(message = "Mobile No is required")
	@Pattern(regexp = "(^[7-9][0-9]{9})*$", message = "MobileNo is Invalid")
	private String mobileNo;

	@NotEmpty(message = "Email is required")
	@Pattern(regexp = "(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))*$", message = "Email id is invalid")
	private String email;

	@NotEmpty(message = "Gender is required")
	private String gender;

	@NotEmpty(message = "Date of Birth is required")
	@Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$", message = "Date Of Birth is Invalid")
	private String dob;
	
	@NotEmpty(message = "Skills is required")
	private String skills;
	
	@NotEmpty(message = "Qualification is required")
	private String qualification;
	 
	@NotEmpty(message = "Work Experience is required")
	private String workExperience;
	
	@NotEmpty(message = "Address is required")
	private String address;

	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public BaseDTO getDTO() {
		UserDTO dto = new UserDTO();
		dto.setMobileNo(mobileNo);
		dto.setLogin(login);
		dto.setFName(firstName);
		dto.setLName(lastName);
		dto.setDob(DataUtility.getDate(dob));
		dto.setGender(gender);
		dto.setAddress(address);
		dto.setSkills(skills);
		dto.setQualification(qualification);
		dto.setWorkExperience(workExperience);
		return dto;
	}

	public void populate(BaseDTO bDto) {
		UserDTO dto = (UserDTO) bDto;
		mobileNo = dto.getMobileNo();
		login = dto.getLogin();
		firstName = dto.getFName();
		lastName = dto.getLName();
		gender = dto.getGender();
		dob = DataUtility.getDateString(dto.getDob());
		address=dto.getAddress();
		skills=dto.getSkills();
		qualification=dto.getQualification();
		workExperience=dto.getWorkExperience();

	}

}
