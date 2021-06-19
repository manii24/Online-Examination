package in.com.online.exam.dao;

import java.util.List;
import java.util.logging.Logger;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.com.online.exam.dto.QuestionDTO;


@Repository
public class QuestionDAOImpl implements QuestionDAOInt {

	private static Logger log = Logger.getLogger(QuestionDAOImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long add(QuestionDTO dto) {
		log.info("QuestionDAOImpl Add method Start");
		Session session = sessionFactory.getCurrentSession();
		long pk = (long) session.save(dto);
		log.info("QuestionDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(QuestionDTO dto) {
		log.info("QuestionDAOImpl Delete method Start");
		Session session = sessionFactory.getCurrentSession();
		session.delete(dto);
		log.info("QuestionDAOImpl Delete method End");
		
	}

	@Override
	public QuestionDTO findBypk(long pk) {
		log.info("QuestionDAOImpl FindByPk method Start");
		Session session = sessionFactory.getCurrentSession();
		QuestionDTO dto = (QuestionDTO) session.get(QuestionDTO.class, pk);
		log.info("QuestionDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public QuestionDTO findByName(String name) {
		log.info("QuestionDAOImpl FindByLogin method Start");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(QuestionDTO.class);
		criteria.add(Restrictions.eq("questionName", name));
		QuestionDTO dto = (QuestionDTO) criteria.uniqueResult();
		log.info("QuestionDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(QuestionDTO dto) {
		log.info("QuestionDAOImpl Update method Start");
		Session session = sessionFactory.getCurrentSession();
		session.merge(dto);
		log.info("QuestionDAOImpl update method End");
	}

	@Override
	public List<QuestionDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<QuestionDTO> list(int pageNo, int pageSize) {
		log.info("QuestionDAOImpl List method Start");
		Session session = sessionFactory.getCurrentSession();
		Query<QuestionDTO> query = session.createQuery("from QuestionDTO", QuestionDTO.class);
		List<QuestionDTO> list = query.getResultList();
		log.info("QuestionDAOImpl List method End");
		return list;
	}

	@Override
	public List<QuestionDTO> search(QuestionDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<QuestionDTO> search(QuestionDTO dto, int pageNo, int pageSize) {
		log.info("QuestionDAOImpl Search method Start");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(QuestionDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getExamId() > 0) {
				criteria.add(Restrictions.eq("examId", dto.getExamId()));
			}
			if (dto.getExamName() != null && dto.getExamName().length() > 0) {
				criteria.add(Restrictions.like("examName", dto.getExamName() + "%"));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("QuestionDAOImpl Search method End");
		return criteria.list();
	}

	

}
