package in.com.online.exam.dao;

import java.util.List;

import in.com.online.exam.dto.QuestionDTO;


public interface QuestionDAOInt {

	public long add(QuestionDTO dto);
	
	public void delete(QuestionDTO dto);
	
	public QuestionDTO findBypk(long pk);
	
	public QuestionDTO findByName(String name);
	
	public void update(QuestionDTO dto);
	
	public List<QuestionDTO> list();
	
	public List<QuestionDTO>list(int pageNo,int pageSize);
	
	public List<QuestionDTO> search(QuestionDTO dto);
	
	public List<QuestionDTO> search(QuestionDTO dto,int pageNo,int pageSize);
	
}
