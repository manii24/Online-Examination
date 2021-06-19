package in.com.online.exam.service;

import java.util.List;

import in.com.online.exam.dto.QuestionDTO;
import in.com.online.exam.exception.DuplicateRecordException;




public interface QuestionServiceInt {

	public long add(QuestionDTO dto) throws DuplicateRecordException;

	public void delete(QuestionDTO dto);

	public QuestionDTO findBypk(long pk);

	public QuestionDTO findByName(String name);

	public void update(QuestionDTO dto) throws DuplicateRecordException;

	public List<QuestionDTO> list();

	public List<QuestionDTO> list(int pageNo, int pageSize);

	public List<QuestionDTO> search(QuestionDTO dto);

	public List<QuestionDTO> search(QuestionDTO dto, int pageNo, int pageSize);



}
