package in.com.online.exam.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.com.online.exam.dto.ResultDTO;


@Repository
public class ResultDAOImpl implements ResultDAOInt {

	private static Logger log = Logger.getLogger(ResultDAOImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long add(ResultDTO dto) {
		log.info("ResultDAOImpl Add method Start");
		Session session = sessionFactory.getCurrentSession();
		long pk = (long) session.save(dto);
		log.info("ResultDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(ResultDTO dto) {
		log.info("ResultDAOImpl Delete method Start");
		Session session = sessionFactory.getCurrentSession();
		session.delete(dto);
		log.info("ResultDAOImpl Delete method End");
		
	}

	@Override
	public ResultDTO findBypk(long pk) {
		log.info("ResultDAOImpl FindByPk method Start");
		Session session = sessionFactory.getCurrentSession();
		ResultDTO dto = (ResultDTO) session.get(ResultDTO.class, pk);
		log.info("ResultDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public ResultDTO findByName(String name) {
		log.info("ResultDAOImpl FindByLogin method Start");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ResultDTO.class);
		criteria.add(Restrictions.eq("name", name));
		ResultDTO dto = (ResultDTO) criteria.uniqueResult();
		log.info("ResultDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(ResultDTO dto) {
		log.info("ResultDAOImpl Update method Start");
		Session session = sessionFactory.getCurrentSession();
		session.merge(dto);
		log.info("ResultDAOImpl update method End");
	}

	@Override
	public List<ResultDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<ResultDTO> list(int pageNo, int pageSize) {
		log.info("ResultDAOImpl List method Start");
		Session session = sessionFactory.getCurrentSession();
		Query<ResultDTO> query = session.createQuery("from ResultDTO", ResultDTO.class);
		List<ResultDTO> list = query.getResultList();
		log.info("ResultDAOImpl List method End");
		return list;
	}

	@Override
	public List<ResultDTO> search(ResultDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<ResultDTO> search(ResultDTO dto, int pageNo, int pageSize) {
		log.info("ResultDAOImpl Search method Start");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ResultDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getUser_id() > 0) {
				criteria.add(Restrictions.eq("user_id", dto.getUser_id()));
			}
			if (dto.getExaminationName() != null && dto.getExaminationName().length() > 0) {
				criteria.add(Restrictions.like("examinationName", dto.getExaminationName() + "%"));
			}
			if (dto.getUserName()!= null && dto.getUserName().length() > 0) {
				criteria.add(Restrictions.like("userName", dto.getUserName() + "%"));
			}
			
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("ResultDAOImpl Search method End");
		return criteria.list();
	}

	

}
