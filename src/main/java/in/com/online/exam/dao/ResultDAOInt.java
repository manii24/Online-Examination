package in.com.online.exam.dao;

import java.util.List;

import in.com.online.exam.dto.ResultDTO;


public interface ResultDAOInt {

	public long add(ResultDTO dto);
	
	public void delete(ResultDTO dto);
	
	public ResultDTO findBypk(long pk);
	
	public ResultDTO findByName(String name);
	
	public void update(ResultDTO dto);
	
	public List<ResultDTO> list();
	
	public List<ResultDTO>list(int pageNo,int pageSize);
	
	public List<ResultDTO> search(ResultDTO dto);
	
	public List<ResultDTO> search(ResultDTO dto,int pageNo,int pageSize);
	
}
