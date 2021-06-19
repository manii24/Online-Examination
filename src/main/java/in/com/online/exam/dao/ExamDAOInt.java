package in.com.online.exam.dao;

import java.util.List;

import in.com.online.exam.dto.ExamDTO;


public interface ExamDAOInt {

	public long add(ExamDTO dto);
	
	public void delete(ExamDTO dto);
	
	public ExamDTO findBypk(long pk);
	
	public ExamDTO findByName(String name);
	
	public void update(ExamDTO dto);
	
	public List<ExamDTO> list();
	
	public List<ExamDTO>list(int pageNo,int pageSize);
	
	public List<ExamDTO> search(ExamDTO dto);
	
	public List<ExamDTO> search(ExamDTO dto,int pageNo,int pageSize);
	
}
