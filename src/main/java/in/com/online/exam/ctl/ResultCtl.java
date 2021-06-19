package in.com.online.exam.ctl;

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

import in.com.online.exam.dto.ResultDTO;
import in.com.online.exam.dto.UserDTO;
import in.com.online.exam.exception.DuplicateRecordException;
import in.com.online.exam.form.ResultForm;
import in.com.online.exam.service.ResultServiceInt;




@Controller
public class ResultCtl extends BaseCtl {

	private Logger log = Logger.getLogger(ResultCtl.class.getName());
	
	@Autowired
	private ResultServiceInt service;
	
	@GetMapping("/ctl/result")
	public String display(HttpSession session,@ModelAttribute("form") ResultForm form, Model model) {
		log.info("ResultCtl Result display method start");
	
		log.info("ResultCtl Result display method end");
		return "result";
	}
	
	@PostMapping("/ctl/result")
	public String submit(@RequestParam String operation, @Valid @ModelAttribute("form") ResultForm form,
			BindingResult bindingResult, Model model) {

		log.info("ResultCtl Result submit method start");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/result";
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "result";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				ResultDTO dto = (ResultDTO) form.getDTO();
				if (dto.getId() > 0) {
					service.update(dto);
					model.addAttribute("success", "Data Update Successfully!!!!");
				} else {
					service.add(dto);
					model.addAttribute("success", "Data Saved Successfully!!!!");
				}
				return "result";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "result";
		}

		log.info("ResultCtl Result submit method end");
		return "result";
	}
	
	
	@RequestMapping(value = "/ctl/result/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(HttpSession session,@ModelAttribute("form") ResultForm form,
			@RequestParam(required = false) String operation, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/result/search";
		}

		if (OP_NEW.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/result";
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
					ResultDTO dto = new ResultDTO();
					dto.setId(id);
					service.delete(dto);
				}

				Integer[] lengths = { form.getIds().length };
				model.addAttribute("success", "Record Deleted Successfully");
			} else {
				model.addAttribute("error","Select at least one record");
			}
		}
		ResultDTO dto=(ResultDTO)form.getDTO();
		
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		if(uDto.getRole_Id()==2) {
			dto.setUser_id(uDto.getId());
		}

		List<ResultDTO> list = service.search(dto, pageNo, pageSize);
		
		List<ResultDTO> totallist= service.search(dto);
		
		
		model.addAttribute("list", list);
		
		if (list.size() == 0) {
			model.addAttribute("error", "Record Not found");
		}
		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize=pageNo*pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		
		model.addAttribute("total", total);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("pagenosize", pageNoPageSize);
		return "resultList";
	}
	
	@GetMapping("/ctl/viewAns")
	public String displayANS(HttpSession session,@ModelAttribute("form") ResultForm form, Model model) {
		log.info("ResultCtl Result display method start");
		List list=(List)session.getAttribute("ansList");
		model.addAttribute("list",list);
		log.info("ResultCtl Result display method end");
		return "viewAns";
	}
}
