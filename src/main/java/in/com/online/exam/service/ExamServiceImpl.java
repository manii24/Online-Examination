package in.com.online.exam.service;
import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.com.online.exam.dao.ExamDAOInt;
import in.com.online.exam.dto.ExamDTO;
import in.com.online.exam.exception.DuplicateRecordException;



@Service
public class ExamServiceImpl implements ExamServiceInt {

	private static Logger log=Logger.getLogger(ExamServiceImpl.class.getName());
	
	@Autowired
	private ExamDAOInt dao;
	
	private JavaMailSenderImpl mailSender;
	
	@Override
	@Transactional
	public long add(ExamDTO dto) throws DuplicateRecordException {
		log.info("ExamServiceImpl Add method start");
		ExamDTO existdto=dao.findByName(dto.getExamName());
		if(existdto !=null)
			throw new DuplicateRecordException("Name is Already Exist");
		long pk=dao.add(dto);
		log.info("ExamServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(ExamDTO dto) {
		log.info("ExamServiceImpl Delete method start");
		dao.delete(dto);
		log.info("ExamServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public ExamDTO findBypk(long pk) {
		log.info("ExamServiceImpl findBypk method start");
		ExamDTO dto=dao.findBypk(pk);
		log.info("ExamServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public ExamDTO findByName(String name) {
		log.info("ExamServiceImpl findByExamName method start");
		ExamDTO dto=dao.findByName(name);
		log.info("ExamServiceImpl findByExamName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(ExamDTO dto) throws DuplicateRecordException {
		log.info("ExamServiceImpl update method start");
		ExamDTO existdto=dao.findByName(dto.getExamName());
		if(existdto !=null && dto.getId()!=existdto.getId())
			throw new DuplicateRecordException("Exam Name id already Exist");
		dao.update(dto);
		log.info("ExamServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<ExamDTO> list() {
		log.info("ExamServiceImpl list method start");
		List<ExamDTO> list=dao.list();
		log.info("ExamServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ExamDTO> list(int pageNo, int pageSize) {
		log.info("ExamServiceImpl list method start");
		List<ExamDTO> list=dao.list(pageNo, pageSize);
		log.info("ExamServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ExamDTO> search(ExamDTO dto) {
		log.info("ExamServiceImpl search method start");
		List<ExamDTO> list=dao.search(dto);
		log.info("ExamServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<ExamDTO> search(ExamDTO dto, int pageNo, int pageSize) {
		log.info("ExamServiceImpl search method start");
		List<ExamDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("ExamServiceImpl search method end");
		return list;
	}

	

	
}
