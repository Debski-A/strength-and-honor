package com.gladigator.Daos;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gladigator.Entities.User;
import com.gladigator.Exceptions.RepositoryException;


@Repository
public class UserDaoImpl implements UserDao{
	
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void saveOrUpdateUser(User user) throws RepositoryException {
		logger.debug("User transient Entity: " + user);
		try {
			getSession().saveOrUpdate(user);
		} catch (Exception ex) {
			throw new RepositoryException("Could not save User. User = " + user, ex);
		}
		logger.info("User has been saved or update");
	}

	public User getUserById(Integer id) throws RepositoryException {
		logger.debug("Id parameter: " + id);
		User user = null;
		try {
			user = getSession().get(User.class, id);
			if (user == null) throw new RepositoryException("There is no User with ID = " + id);
		} catch (IllegalArgumentException ex) {
			throw new RepositoryException("There is no User with ID = " + id, ex);
		}
		
		logger.info("User retrieved from database");
		return user;
	}
	
	@SuppressWarnings("rawtypes")
	public void deleteUserById(Integer id) throws RepositoryException {
		logger.debug("Id parameter: " + id);
		Query query = getSession().createQuery("delete from User where userId = :userId")
			.setParameter("userId", id);
		
		if (query.executeUpdate() == 0) throw new RepositoryException("Couldn't delete User with ID = " + id + ". No such Entity");
		logger.info("Deleted User from database");
	}

	public List<User> getAllUsers() {
		return getSession().createQuery("from User", User.class).list();
	}
	
	
	

}
