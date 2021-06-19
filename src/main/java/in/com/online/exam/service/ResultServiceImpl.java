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

import in.com.online.exam.dao.ResultDAOInt;
import in.com.online.exam.dto.ResultDTO;
import in.com.online.exam.exception.DuplicateRecordException;
import in.com.online.exam.util.EmailBuilder;



@Service
public class ResultServiceImpl implements ResultServiceInt {

	private static Logger log=Logger.getLogger(ResultServiceImpl.class.getName());
	
	@Autowired
	private ResultDAOInt dao;
	
	private JavaMailSenderImpl mailSender;
	
	@Override
	@Transactional
	public long add(ResultDTO dto) throws DuplicateRecordException {
		log.info("ResultServiceImpl Add method start");
		
		long pk=dao.add(dto);
		log.info("ResultServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(ResultDTO dto) {
		log.info("ResultServiceImpl Delete method start");
		dao.delete(dto);
		log.info("ResultServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public ResultDTO findBypk(long pk) {
		log.info("ResultServiceImpl findBypk method start");
		ResultDTO dto=dao.findBypk(pk);
		log.info("ResultServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public ResultDTO findByName(String name) {
		log.info("ResultServiceImpl findByResultName method start");
		ResultDTO dto=dao.findByName(name);
		log.info("ResultServiceImpl findByResultName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(ResultDTO dto) throws DuplicateRecordException {
		log.info("ResultServiceImpl update method start");
		
		dao.update(dto);
		log.info("ResultServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<ResultDTO> list() {
		log.info("ResultServiceImpl list method start");
		List<ResultDTO> list=dao.list();
		log.info("ResultServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ResultDTO> list(int pageNo, int pageSize) {
		log.info("ResultServiceImpl list method start");
		List<ResultDTO> list=dao.list(pageNo, pageSize);
		log.info("ResultServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ResultDTO> search(ResultDTO dto) {
		log.info("ResultServiceImpl search method start");
		List<ResultDTO> list=dao.search(dto);
		log.info("ResultServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<ResultDTO> search(ResultDTO dto, int pageNo, int pageSize) {
		log.info("ResultServiceImpl search method start");
		List<ResultDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("ResultServiceImpl search method end");
		return list;
	}

	

	
}
