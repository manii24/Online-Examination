package in.com.online.exam.ctl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import in.com.online.exam.dto.QuestionDTO;
import in.com.online.exam.exception.DuplicateRecordException;
import in.com.online.exam.form.QuestionForm;
import in.com.online.exam.form.StartExamForm;
import in.com.online.exam.service.ExamServiceInt;
import in.com.online.exam.service.QuestionServiceInt;

@Controller
public class StartExamCtl extends BaseCtl {

	private Logger log = Logger.getLogger(StartExamCtl.class.getName());

	@Autowired
	private QuestionServiceInt service;

	@Autowired
	private ExamServiceInt examService;

	@GetMapping("/ctl/startExam")
	public String display(@ModelAttribute("form") StartExamForm form, Model model) {
		log.info("StartExamCtl Question display method start");
		if (form.getId() > 0) {
			form.populate(service.findBypk(form.getId()));
		}
		log.info("StartExamCtl Question display method end");
		return "startExam";
	}

	@ModelAttribute
	public void preload(Model model) {
		ExamDTO dto=new ExamDTO();
		dto.setExamDate(new Date());
		model.addAttribute("examList",examService.search(dto));

	}

	@PostMapping("/ctl/startExam")
	public String searchList(HttpSession session, @ModelAttribute("form") StartExamForm form,

			@RequestParam(required = false) String operation, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/startExam";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		
		pageNo = 1;
		pageSize =1;

		QuestionDTO dto = (QuestionDTO) form.getDTO();

		List<QuestionDTO> list = service.search(dto, pageNo, pageSize);

		List<QuestionDTO> totallist = service.search(dto);

		model.addAttribute("list", list);

		if (list.size() == 0) {
			model.addAttribute("error", "Record Not found");
		}
		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		session.setAttribute("ansList",new ArrayList());
		model.addAttribute("total", total);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("pagenosize", pageNoPageSize);
		return "questionList";
	}

}
