package in.com.online.exam.service;

import java.util.List;

import in.com.online.exam.dto.ResultDTO;
import in.com.online.exam.exception.DuplicateRecordException;




public interface ResultServiceInt {

	public long add(ResultDTO dto) throws DuplicateRecordException;

	public void delete(ResultDTO dto);

	public ResultDTO findBypk(long pk);

	public ResultDTO findByName(String name);

	public void update(ResultDTO dto) throws DuplicateRecordException;

	public List<ResultDTO> list();

	public List<ResultDTO> list(int pageNo, int pageSize);

	public List<ResultDTO> search(ResultDTO dto);

	public List<ResultDTO> search(ResultDTO dto, int pageNo, int pageSize);



}
