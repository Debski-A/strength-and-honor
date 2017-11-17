package com.gladigator.Daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gladigator.Entities.UserDetails;
import com.gladigator.Exceptions.RepositoryException;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {
	
	private static final Logger logger = LogManager.getLogger(UserDetailsDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void saveOrUpdateUserDetails(UserDetails userDetails) throws RepositoryException {
		try {
			logger.debug("UserDetails transient Entity: " + userDetails);
			getSession().saveOrUpdate(userDetails);
			logger.info("UserDetails has been saved into database");
		} catch (Exception ex) {
			throw new RepositoryException("Could not save UserDetails", ex);
		}
	}

	public UserDetails getUserDetailsById(Integer id) throws RepositoryException {
		logger.debug("ID parameter = " + id);
		UserDetails userDetails = null;
		try {
			userDetails = getSession().get(UserDetails.class, id);
		} catch (Exception ex) {
			throw new RepositoryException("There is no UserDetails entity with such ID = " + id);
		}
		if (userDetails == null) throw new RepositoryException("There is no UserDetails entity with such ID = " + id);
		logger.info("Received UserDetails Entity from database");
		return userDetails;
	}

	@SuppressWarnings("rawtypes")
	public void deleteUserDetailsById(Integer id) {
		logger.debug("ID parameter = " + id);
		Query query = getSession().createQuery("delete from UserDetails where userId = :userId")
			.setParameter("userId", id);
		
		if (query.executeUpdate() == 0) throw new RepositoryException("Couldn't delete UserDetails with ID = " + id + ". No such Entity");
		logger.info("Deleted UserDetails Entity from database");
	}

}
