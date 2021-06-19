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

import in.com.online.exam.dto.ExamDTO;
import in.com.online.exam.util.DataUtility;


@Repository
public class ExamDAOImpl implements ExamDAOInt {

	private static Logger log = Logger.getLogger(ExamDAOImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long add(ExamDTO dto) {
		log.info("ExamDAOImpl Add method Start");
		Session session = sessionFactory.getCurrentSession();
		long pk = (long) session.save(dto);
		log.info("ExamDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(ExamDTO dto) {
		log.info("ExamDAOImpl Delete method Start");
		Session session = sessionFactory.getCurrentSession();
		session.delete(dto);
		log.info("ExamDAOImpl Delete method End");
		
	}

	@Override
	public ExamDTO findBypk(long pk) {
		log.info("ExamDAOImpl FindByPk method Start");
		Session session = sessionFactory.getCurrentSession();
		ExamDTO dto = (ExamDTO) session.get(ExamDTO.class, pk);
		log.info("ExamDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public ExamDTO findByName(String name) {
		log.info("ExamDAOImpl FindByLogin method Start");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ExamDTO.class);
		criteria.add(Restrictions.eq("examName", name));
		ExamDTO dto = (ExamDTO) criteria.uniqueResult();
		log.info("ExamDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(ExamDTO dto) {
		log.info("ExamDAOImpl Update method Start");
		Session session = sessionFactory.getCurrentSession();
		session.merge(dto);
		log.info("ExamDAOImpl update method End");
	}

	@Override
	public List<ExamDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<ExamDTO> list(int pageNo, int pageSize) {
		log.info("ExamDAOImpl List method Start");
		Session session = sessionFactory.getCurrentSession();
		Query<ExamDTO> query = session.createQuery("from ExamDTO", ExamDTO.class);
		List<ExamDTO> list = query.getResultList();
		log.info("ExamDAOImpl List method End");
		return list;
	}

	@Override
	public List<ExamDTO> search(ExamDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<ExamDTO> search(ExamDTO dto, int pageNo, int pageSize) {
		log.info("ExamDAOImpl Search method Start");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ExamDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getExamName() != null && dto.getExamName().length() > 0) {
				criteria.add(Restrictions.like("examName", dto.getExamName() + "%"));
			}
			if (dto.getExamDate() != null && dto.getExamDate().getTime() > 0) {
				
				criteria.add(Restrictions.eq("examDate",DataUtility.getDate(DataUtility.getDateString(dto.getExamDate()))));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("ExamDAOImpl Search method End");
		return criteria.list();
	}

	

}
