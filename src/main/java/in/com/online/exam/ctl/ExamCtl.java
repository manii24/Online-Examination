package in.com.online.exam.ctl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

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

import in.com.online.exam.dto.ExamDTO;
import in.com.online.exam.dto.UserDTO;
import in.com.online.exam.exception.DuplicateRecordException;
import in.com.online.exam.form.ExamForm;
import in.com.online.exam.service.ExamServiceInt;

@Controller
public class ExamCtl extends BaseCtl {

	private Logger log = Logger.getLogger(ExamCtl.class.getName());

	@Autowired
	private ExamServiceInt service;

	@GetMapping("/ctl/exam")
	public String display(@ModelAttribute("form") ExamForm form, Model model) {
		log.info("ExamCtl Exam display method start");
		if (form.getId() > 0) {
			form.populate(service.findBypk(form.getId()));
		}
		log.info("ExamCtl Exam display method end");
		return "exam";
	}

	@PostMapping("/ctl/exam")
	public String submit(@RequestParam String operation, @Valid @ModelAttribute("form") ExamForm form,
			BindingResult bindingResult, Model model) {

		log.info("ExamCtl Exam submit method start");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/exam";
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "exam";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				ExamDTO dto = (ExamDTO) form.getDTO();
				if (dto.getId() > 0) {
					service.update(dto);
					model.addAttribute("success", "Data Update Successfully!!!!");
				} else {
					service.add(dto);
					model.addAttribute("success", "Data Saved Successfully!!!!");
				}
				return "exam";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "exam";
		}

		log.info("ExamCtl Exam submit method end");
		return "exam";
	}

	@RequestMapping(value = "/ctl/exam/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(HttpSession session, @ModelAttribute("form") ExamForm form,
			@RequestParam(required = false) String operation, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/exam/search";
		}

		if (OP_NEW.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/exam";
		}
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equalsIgnoreCase(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(operation)) {
			pageNo--;
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equalsIgnoreCase(operation)) {

			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					ExamDTO dto = new ExamDTO();
					dto.setId(id);
					service.delete(dto);
				}

				Integer[] lengths = { form.getIds().length };
				model.addAttribute("success", "Record Deleted Successfully");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		ExamDTO dto = (ExamDTO) form.getDTO();
		
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		if(uDto.getRole_Id()==2) {
			dto.setExamDate(new Date());
		}
		List<ExamDTO> list = service.search(dto, pageNo, pageSize);

		List<ExamDTO> totallist = service.search(dto);

		model.addAttribute("list", list);

		if (list.size() == 0) {
			model.addAttribute("error", "Record Not found");
		}
		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);

		model.addAttribute("total", total);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("pagenosize", pageNoPageSize);
		return "examList";
	}
}
