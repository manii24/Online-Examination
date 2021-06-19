package in.com.online.exam.ctl;

import java.util.ArrayList;
import java.util.List;
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

import in.com.online.exam.dto.ExamDTO;
import in.com.online.exam.dto.QuestionDTO;
import in.com.online.exam.dto.ResultDTO;
import in.com.online.exam.dto.UserDTO;
import in.com.online.exam.exception.DuplicateRecordException;
import in.com.online.exam.form.QuestionForm;
import in.com.online.exam.service.ExamServiceInt;
import in.com.online.exam.service.QuestionServiceInt;
import in.com.online.exam.service.ResultServiceInt;
import in.com.online.exam.util.DataUtility;




@Controller
public class QuestionCtl extends BaseCtl {

	private Logger log = Logger.getLogger(QuestionCtl.class.getName());
	
	@Autowired
	private QuestionServiceInt service;
	
	@Autowired
	private ExamServiceInt examService;
	
	@Autowired
	private ResultServiceInt resultService;
	
	@GetMapping("/ctl/question")
	public String display(@ModelAttribute("form") QuestionForm form, Model model) {
		log.info("QuestionCtl Question display method start");
		if(form.getId()>0) {
			form.populate(service.findBypk(form.getId()));
		}
		log.info("QuestionCtl Question display method end");
		return "question";
	}
	
	@ModelAttribute
	public void preload(Model model) {		

		model.addAttribute("examList",examService.list());
		
	}
	
	@PostMapping("/ctl/question")
	public String submit(@RequestParam String operation, @Valid @ModelAttribute("form") QuestionForm form,
			BindingResult bindingResult, Model model) {

		log.info("QuestionCtl Question submit method start");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/question";
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "question";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				QuestionDTO dto = (QuestionDTO) form.getDTO();
				dto.setExamName(examService.findBypk(dto.getExamId()).getExamName());
				if (dto.getId() > 0) {
					service.update(dto);
					model.addAttribute("success", "Data Update Successfully!!!!");
				} else {
					service.add(dto);
					model.addAttribute("success", "Data Saved Successfully!!!!");
				}
				return "question";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "question";
		}

		log.info("QuestionCtl Question submit method end");
		return "question";
	}
	
	
	@RequestMapping(value = "/ctl/question/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(HttpServletRequest request ,HttpSession session,@ModelAttribute("form") QuestionForm form,
			@RequestParam(required = false) String operation, Model model) {
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		int count=DataUtility.getInt(request.getParameter("count"));
		
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 1 : pageSize;

		String examName=DataUtility.getString(request.getParameter("examName"));
		
		String result=null;
		List ansList=(List)request.getSession().getAttribute("ansList");
		try {
		if (OP_NEXT.equalsIgnoreCase(operation)) {
			String ans=request.getParameter("ans");
			String question=request.getParameter("question");
			QuestionDTO bean1=service.findByName(question);
			if(ansList==null) {
				ansList=new ArrayList();
			}
			ansList.add(new QuestionDTO(bean1.getQuestionName(),bean1.getCorrectAns(), ans,pageNo));
				if(bean1.getCorrectAns().equalsIgnoreCase(ans)) {
						count++;
				}
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(operation)) {
			pageNo--;
			ansList=(List)request.getSession().getAttribute("ansList");
			ansList.remove(pageNo-1);
		}else if(OP_SUBMIT.equalsIgnoreCase(operation)) {
			String ans=request.getParameter("ans");
			String question=request.getParameter("question");
			String exam=request.getParameter("examName");
			
			QuestionDTO bean1=service.findByName(question);
			System.out.println(bean1);
			ansList=(List)request.getSession().getAttribute("ansList");
			System.out.println("onSubmit"+ansList);
			ansList.add(new QuestionDTO(bean1.getQuestionName(),bean1.getCorrectAns(), ans,pageNo));
				if(bean1.getCorrectAns().equalsIgnoreCase(ans)) {
						count++;
				}
			
				System.out.println("Submit----------------");
				System.out.println("pageSize"+ pageSize +"count"+ count);
			ResultDTO rBean=new ResultDTO();
			rBean.setExaminationName(exam);
			ExamDTO exBean=examService.findByName(exam);
			rBean.setExaminationDate(exBean.getExamDate());
			QuestionDTO qDto=new QuestionDTO();
			qDto.setExamName(exam);
			int size= service.search(qDto, pageNo, pageSize).size();
			
			if(count>(size/30)) {
				result="Pass";
			}else {
				result="Fail";
			}
			UserDTO uDto=(UserDTO)session.getAttribute("user");
			rBean.setResult(result);
			rBean.setUser_id(uDto.getId());
			
		
			rBean.setCount(String.valueOf(count));
			rBean.setSize(String.valueOf(ansList.size()));
			rBean.setUserName(uDto.getfName().toUpperCase()+" "+uDto.getLName().toUpperCase());
				resultService.add(rBean);
			
			
			session.setAttribute("rBean",rBean);
			return"redirect:/ctl/result";
		}else {
			
			QuestionDTO qqDto=new QuestionDTO();
			qqDto.setExamName(examName);
			List qlList=service.search(qqDto);
			
			if(pageNo==qlList.size()) {
				String ans=request.getParameter("ans");
				String question=request.getParameter("question");
				String exam=request.getParameter("examName");
				
				QuestionDTO bean1=service.findByName(question);
				System.out.println(bean1);
				ansList=(List)request.getSession().getAttribute("ansList");
				System.out.println("onSubmit"+ansList);
				ansList.add(new QuestionDTO(bean1.getQuestionName(),bean1.getCorrectAns(), ans,pageNo));
					if(bean1.getCorrectAns().equalsIgnoreCase(ans)) {
							count++;
					}
				
					System.out.println("Submit----------------");
					System.out.println("pageSize"+ pageSize +"count"+ count);
				ResultDTO rBean=new ResultDTO();
				rBean.setExaminationName(exam);
				ExamDTO exBean=examService.findByName(exam);
				rBean.setExaminationDate(exBean.getExamDate());
				QuestionDTO qDto=new QuestionDTO();
				qDto.setExamName(exam);
				int size= service.search(qDto, pageNo, pageSize).size();
				
				if(count>(size/30)) {
					result="Pass";
				}else {
					result="Fail";
				}
				UserDTO uDto=(UserDTO)session.getAttribute("user");
				rBean.setResult(result);
				rBean.setUser_id(uDto.getId());
				
			
				rBean.setCount(String.valueOf(count));
				rBean.setSize(String.valueOf(ansList.size()));
				rBean.setUserName(uDto.getfName().toUpperCase()+" "+uDto.getLName().toUpperCase());
					resultService.add(rBean);
				
				
				session.setAttribute("rBean",rBean);
				return"redirect:/ctl/result";
			}else {
			
			String ans=request.getParameter("ans");
			String question=request.getParameter("question");
			QuestionDTO bean1=service.findByName(question);
			if(ansList==null) {
				ansList=new ArrayList();
			}
			ansList.add(new QuestionDTO(bean1.getQuestionName(),bean1.getCorrectAns(), ans,pageNo));
				if(bean1.getCorrectAns().equalsIgnoreCase(ans)) {
						count++;
				}
			pageNo++;
			}
		}

		
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
		
		QuestionDTO dto=(QuestionDTO)form.getDTO();
		dto.setExamName(examName);

		List<QuestionDTO> list = service.search(dto, pageNo, pageSize);
		
		List<QuestionDTO> totallist= service.search(dto);
		
		
		model.addAttribute("list", list);
		
		if (list.size() == 0) {
			model.addAttribute("error", "Record Not found");
		}
		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize=pageNo*pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		session.setAttribute("ansList",ansList);
		model.addAttribute("count",count);
		model.addAttribute("size",service.search(dto).size());
		model.addAttribute("total", total);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("pagenosize", pageNoPageSize);
		return "questionList";
	}
}
