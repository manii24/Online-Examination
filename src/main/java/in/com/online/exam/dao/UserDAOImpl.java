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

import in.com.online.exam.dto.UserDTO;


@Repository
public class UserDAOImpl implements UserDAOInt {

	private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long add(UserDTO dto) {
		log.info("UserDAOImpl Add method Start");
		Session session = sessionFactory.getCurrentSession();
		long pk = (long) session.save(dto);
		log.info("UserDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(UserDTO dto) {
		log.info("UserDAOImpl Delete method Start");
		Session session = sessionFactory.getCurrentSession();
		session.delete(dto);
		log.info("UserDAOImpl Delete method End");
		
	}

	@Override
	public UserDTO findBypk(long pk) {
		log.info("UserDAOImpl FindByPk method Start");
		Session session = sessionFactory.getCurrentSession();
		UserDTO dto = (UserDTO) session.get(UserDTO.class, pk);
		log.info("UserDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public UserDTO findByLogin(String login) {
		log.info("UserDAOImpl FindByLogin method Start");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserDTO.class);
		criteria.add(Restrictions.eq("login", login));
		UserDTO dto = (UserDTO) criteria.uniqueResult();
		log.info("UserDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(UserDTO dto) {
		log.info("UserDAOImpl Update method Start");
		Session session = sessionFactory.getCurrentSession();
		session.merge(dto);
		log.info("UserDAOImpl update method End");
	}

	@Override
	public List<UserDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<UserDTO> list(int pageNo, int pageSize) {
		log.info("UserDAOImpl List method Start");
		Session session = sessionFactory.getCurrentSession();
		Query<UserDTO> query = session.createQuery("from UserDTO", UserDTO.class);
		List<UserDTO> list = query.getResultList();
		log.info("UserDAOImpl List method End");
		return list;
	}

	@Override
	public List<UserDTO> search(UserDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		log.info("UserDAOImpl Search method Start");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getFName() != null && dto.getFName().length() > 0) {
				criteria.add(Restrictions.like("fName", dto.getFName() + "%"));
			}
			if (dto.getLName() != null && dto.getLName().length() > 0) {
				criteria.add(Restrictions.like("lName", dto.getLName() + "%"));
			}
			if (dto.getLogin() != null && dto.getLogin().length() > 0) {
				criteria.add(Restrictions.like("login", dto.getLogin() + "%"));
			}
			if (dto.getRole_Id() > 0) {
				criteria.add(Restrictions.eq("role_Id", dto.getRole_Id()));
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("UserDAOImpl Search method End");
		return criteria.list();
	}

	@Override
	public UserDTO authentication(UserDTO dto) {
		log.info("UserDAOImpl Authentication method Start");
		Session session = sessionFactory.getCurrentSession();
		Query<UserDTO> query = session
				.createQuery("from UserDTO where login=:login and password=:password", UserDTO.class);
		query.setParameter("login",dto.getLogin());
		query.setParameter("password",dto.getPassword());
		dto = null;
		try {
			dto = query.getSingleResult();
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}
		log.info("UserDAOImpl Authentication method End");
		return dto;
	}

}
