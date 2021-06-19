package in.com.online.exam.service;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.com.online.exam.dao.QuestionDAOInt;
import in.com.online.exam.dto.QuestionDTO;
import in.com.online.exam.exception.DuplicateRecordException;
import in.com.online.exam.util.EmailBuilder;



@Service
public class QuestionServiceImpl implements QuestionServiceInt {

	private static Logger log=Logger.getLogger(QuestionServiceImpl.class.getName());
	
	@Autowired
	private QuestionDAOInt dao;
	
	private JavaMailSenderImpl mailSender;
	
	@Override
	@Transactional
	public long add(QuestionDTO dto) throws DuplicateRecordException {
		log.info("QuestionServiceImpl Add method start");
		QuestionDTO existdto=dao.findByName(dto.getQuestionName());
		if(existdto !=null)
			throw new DuplicateRecordException("Name is Already Exist");
		long pk=dao.add(dto);
		log.info("QuestionServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(QuestionDTO dto) {
		log.info("QuestionServiceImpl Delete method start");
		dao.delete(dto);
		log.info("QuestionServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public QuestionDTO findBypk(long pk) {
		log.info("QuestionServiceImpl findBypk method start");
		QuestionDTO dto=dao.findBypk(pk);
		log.info("QuestionServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public QuestionDTO findByName(String name) {
		log.info("QuestionServiceImpl findByQuestionName method start");
		QuestionDTO dto=dao.findByName(name);
		log.info("QuestionServiceImpl findByQuestionName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(QuestionDTO dto) throws DuplicateRecordException {
		log.info("QuestionServiceImpl update method start");
		QuestionDTO existdto=dao.findByName(dto.getQuestionName());
		if(existdto !=null && dto.getId()!=existdto.getId())
			throw new DuplicateRecordException("Question Name id already Exist");
		dao.update(dto);
		log.info("QuestionServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<QuestionDTO> list() {
		log.info("QuestionServiceImpl list method start");
		List<QuestionDTO> list=dao.list();
		log.info("QuestionServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<QuestionDTO> list(int pageNo, int pageSize) {
		log.info("QuestionServiceImpl list method start");
		List<QuestionDTO> list=dao.list(pageNo, pageSize);
		log.info("QuestionServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<QuestionDTO> search(QuestionDTO dto) {
		log.info("QuestionServiceImpl search method start");
		List<QuestionDTO> list=dao.search(dto);
		log.info("QuestionServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<QuestionDTO> search(QuestionDTO dto, int pageNo, int pageSize) {
		log.info("QuestionServiceImpl search method start");
		List<QuestionDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("QuestionServiceImpl search method end");
		return list;
	}

	

	
}
