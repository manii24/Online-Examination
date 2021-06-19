package in.com.online.exam.service;

import java.util.List;

import in.com.online.exam.dto.ExamDTO;
import in.com.online.exam.exception.DuplicateRecordException;




public interface ExamServiceInt {

	public long add(ExamDTO dto) throws DuplicateRecordException;

	public void delete(ExamDTO dto);

	public ExamDTO findBypk(long pk);

	public ExamDTO findByName(String name);

	public void update(ExamDTO dto) throws DuplicateRecordException;

	public List<ExamDTO> list();

	public List<ExamDTO> list(int pageNo, int pageSize);

	public List<ExamDTO> search(ExamDTO dto);

	public List<ExamDTO> search(ExamDTO dto, int pageNo, int pageSize);



}
