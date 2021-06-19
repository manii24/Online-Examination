package in.com.online.exam.ctl;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.com.online.exam.dto.UserDTO;
import in.com.online.exam.exception.DuplicateRecordException;
import in.com.online.exam.form.ChangePasswordForm;
import in.com.online.exam.form.ForgetPasswordForm;
import in.com.online.exam.form.LoginForm;
import in.com.online.exam.form.MyProfileForm;
import in.com.online.exam.form.UserRegistrationForm;
import in.com.online.exam.service.UserServiceInt;
import in.com.online.exam.util.DataUtility;

@Controller
public class LoginCtl extends BaseCtl {

	private Logger log = Logger.getLogger(LoginCtl.class.getName());

	protected static final String OP_SIGNIN = "SignIn";
	protected static final String OP_SIGNUP = "SignUp";
	protected static final String OP_LOGOUT = "Logout";

	@Autowired
	private UserServiceInt service;

	@GetMapping("/login")
	public String display(@ModelAttribute("form") LoginForm form, HttpSession session, Model model) {
		log.info("LoginCtl login display method start");
		if (session.getAttribute("user") != null) {
			session.invalidate();
			model.addAttribute("success", "You have logout Successfully!!!");
		}
		log.info("LoginCtl login display method End");
		return "login";
	}

	@ModelAttribute
	public void preload(Model model) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Male", "Male");
		map.put("Female", "Female");
		model.addAttribute("gender", map);

	}

	@PostMapping("/login")
	public String submit(@RequestParam String operation, HttpSession session,
			@Valid @ModelAttribute("form") LoginForm form, BindingResult result, Model model) {
		log.info("LoginCtl login submit method start");
		System.out.println("In dopost  LoginCtl");

		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {
			return "redirect:signUp";
		}

		if (result.hasErrors()) {
			System.out.println(result);
			return "login";
		}

		UserDTO dto = service.authentication((UserDTO) form.getDTO());
		try {
			if (dto != null) {
				if(dto.getRole_Id()==2) {
				System.out.println(dto.toString());
				if (dto.getStatus().equalsIgnoreCase("Approved")) {
					if (dto.getSubType().endsWith("One Day")) {
						if (DataUtility.getDateString(new Date()) == DataUtility
										.getDateString(dto.getSubEndDate())) {
							dto.setStatus("UnApproved");
							session.setAttribute("user", dto);
							service.update(dto);
							return "redirect:/ctl/subscription";
						}
					} else if (dto.getStatus().equalsIgnoreCase("One month")) {
						if (DataUtility.getDateString(new Date()) == DataUtility.getDateString(dto.getSubEndDate())) {
							dto.setStatus("UnApproved");
							session.setAttribute("user", dto);
							service.update(dto);
							return "redirect:/ctl/subscription";
						}
					} else if (dto.getStatus().equalsIgnoreCase("Three month")) {
						if (DataUtility.getDateString(new Date()) == DataUtility.getDateString(dto.getSubEndDate())) {
							dto.setStatus("UnApproved");
							session.setAttribute("user", dto);
							service.update(dto);
							return "redirect:/ctl/subscription";
						}
					} else if (dto.getStatus().equalsIgnoreCase("Six month")) {
						if (DataUtility.getDateString(new Date()) == DataUtility.getDateString(dto.getSubEndDate())) {
							dto.setStatus("UnApproved");
							session.setAttribute("user", dto);
							service.update(dto);
							return "redirect:/ctl/subscription";
						}
					}
				}else {
					session.setAttribute("user", dto);
					return "redirect:/ctl/subscription";
				}
					
				}
				session.setAttribute("user", dto);
				return "redirect:/welcome";
			}
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

		model.addAttribute("error", "Login Id Password Invalid");
		log.info("LoginCtl login submit method End");
		return "login";
	}

	@GetMapping("/signUp")
	public String display(@ModelAttribute("form") UserRegistrationForm form, Model model) {
		log.info("LoginCtl signUp display method start");
		log.info("LoginCtl signUp display method End");
		return "signUp";
	}

	@PostMapping("/signUp")
	public String submit(@RequestParam String operation, @Valid @ModelAttribute("form") UserRegistrationForm form,
			BindingResult bindingResult, Model model) {

		log.info("LoginCtl signUp submit method start");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:signUp";
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "signUp";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				UserDTO entity = (UserDTO) form.getDTO();
				entity.setRole_Id(2L);
				entity.setStatus("UnApproved");
				service.add(entity);
				model.addAttribute("success", "User Registerd Successfully!!!!");
				return "signUp";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "signUp";
		}
		log.info("LoginCtl signUp submit method end");
		return "signUp";
	}

	@RequestMapping(value = "/ctl/profile", method = RequestMethod.GET)
	public String displayProfile(HttpSession session, @ModelAttribute("form") MyProfileForm form, Model model) {
		UserDTO dto = (UserDTO) session.getAttribute("user");
		form.populate(dto);
		System.out.println("/Myprofile");
		return "myprofile";
	}

	@RequestMapping(value = "/ctl/profile", method = RequestMethod.POST)
	public String submitProfile(HttpSession session, @ModelAttribute("form") @Valid MyProfileForm form,
			BindingResult bindingResult, @RequestParam(required = false) String operation, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/profile";
		}

		if (bindingResult.hasErrors()) {
			return "myprofile";
		}
		UserDTO dto = (UserDTO) session.getAttribute("user");
		dto = service.findBypk(dto.getId());
		dto.setFName(form.getFirstName());
		dto.setLName(form.getLastName());
		dto.setMobileNo(form.getMobileNo());
		dto.setGender(form.getGender());
		dto.setDob(DataUtility.getDate(form.getDob()));
		dto.setAddress(form.getAddress());
		dto.setSkills(form.getSkills());
		dto.setQualification(form.getQualification());
		dto.setWorkExperience(form.getWorkExperience());
		try {
			service.update(dto);
			session.setAttribute("user", dto);
		} catch (DuplicateRecordException e) {

		}
		model.addAttribute("success", "Profile Update successfully");

		return "myprofile";
	}

	@RequestMapping(value = "/ctl/changepassword", method = RequestMethod.GET)
	public String displayChangePassword(@ModelAttribute("form") ChangePasswordForm form, Model model) {
		return "changePassword";
	}

	@RequestMapping(value = "/ctl/changepassword", method = RequestMethod.POST)
	public String submitChangePassword(HttpSession session, @ModelAttribute("form") @Valid ChangePasswordForm form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "changePassword";
		}
		if (form.getNewPassword().equalsIgnoreCase(form.getConfirmPassword())) {

			UserDTO dto = (UserDTO) session.getAttribute("user");
			dto = service.findBypk(dto.getId());

			if (service.changePassword(dto.getId(), form.getOldPassword(), form.getNewPassword())) {
				model.addAttribute("success", "Password changed Successfully");
			} else {
				model.addAttribute("error", "Old Passowors Does not Matched");
			}
		} else {
			model.addAttribute("error", "New Password and confirm password does not matched");
		}
		return "changePassword";
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") ForgetPasswordForm form, HttpSession session, Model model) {

		System.out.println("In doget LoginCtl forgetpassword");

		return "forgetPassword";

	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public String display(@ModelAttribute("form") @Valid ForgetPasswordForm form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "forgetPassword";
		}

		UserDTO dto = service.findByLogin(form.getLogin());

		if (dto == null) {
			model.addAttribute("error", "Login Id does not exist");
		}

		if (dto != null) {
			service.forgetPassword(form.getLogin());
			model.addAttribute("success", "Password has been sent to your registered Email ID!!");

			// model.addAttribute("success", "Password has been sent to your registered
			// Email ID!!");
		}
		return "forgetPassword";
	}

	@RequestMapping(value = "/ctl/subscription", method = { RequestMethod.GET, RequestMethod.POST })
	public String oneMonthSub(HttpSession session, HttpServletRequest request, Model model) {
		try {
			UserDTO dto = (UserDTO) session.getAttribute("user");
			int sub = DataUtility.getInt(request.getParameter("sub"));
			String op = DataUtility.getString(request.getParameter("operation"));

			if (OP_CONFIRM_PAYMENT.equalsIgnoreCase(op)) {
				UserDTO uDto = (UserDTO) session.getAttribute("subUser");
				service.update(uDto);
				session.setAttribute("user", uDto);
				return "success";
			}

			if (sub == 2) {
				dto.setSubStartDate(DataUtility.getDate(DataUtility.getDateString(new Date())));
				dto.setSubEndDate(DataUtility.getDate(DataUtility.getDayDate(1)));
				dto.setStatus("Approved");
				dto.setSubType("One Day");
				service.update(dto);
				session.setAttribute("user", dto);
				return "success";
			} else if (sub == 1) {
				dto.setSubStartDate(DataUtility.getDate(DataUtility.getDateString(new Date())));
				dto.setSubEndDate(DataUtility.getDate(DataUtility.getSubDate(1)));
				dto.setStatus("Approved");
				dto.setSubType("One month");
				session.setAttribute("subUser", dto);
				return "payment";
			} else if (sub == 3) {
				dto.setSubStartDate(DataUtility.getDate(DataUtility.getDateString(new Date())));
				dto.setSubEndDate(DataUtility.getDate(DataUtility.getSubDate(3)));
				dto.setStatus("Approved");
				dto.setSubType("Three month");
				session.setAttribute("subUser", dto);
				return "payment";
			} else if (sub == 6) {
				dto.setSubStartDate(DataUtility.getDate(DataUtility.getDateString(new Date())));
				dto.setSubEndDate(DataUtility.getDate(DataUtility.getSubDate(6)));
				dto.setStatus("Approved");
				dto.setSubType("Six month");
				session.setAttribute("subUser", dto);
				return "payment";
			}
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

		return "subView";
	}

}
