package com.gladigator.Daos;

import java.util.List;

import javax.persistence.NoResultException;

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
	
	private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void saveOrUpdateUser(User user) throws RepositoryException {
		LOG.info("UserDao.saveOrUpdate(User user) START"); //TODO Zamienic logowanie metod START oraz END na aspect
		LOG.debug("User transient Entity = {}", user);
		try {
			getSession().saveOrUpdate(user);
		} catch (Exception ex) {
			throw new RepositoryException("Could not save User. User = " + user, ex);
		}
		LOG.info("User has been saved or update");
		LOG.info("UserDao.saveOrUpdate(User user) END");
	}

	public User getUserById(Integer id) throws RepositoryException {
		LOG.info("UserDao.getUserById(Integer id) START");
		LOG.debug("Id parameter = {}", id);
		User user = null;
		try {
			user = getSession().get(User.class, id);
			if (user == null) throw new RepositoryException("There is no User with ID = " + id);
		} catch (IllegalArgumentException ex) {
			throw new RepositoryException("There is no User with ID = " + id, ex);
		}
		
		LOG.debug("User from DB = {}", user);
		LOG.info("UserDao.getUserById(Integer id) END");
		return user;
	}
	
	@SuppressWarnings("rawtypes")
	public void deleteUserById(Integer id) throws RepositoryException {
		LOG.info("UserDao.deleteUserById(Integer id) START");
		LOG.debug("Id parameter = {} ", id);
		Query query = getSession().createQuery("delete from User where userId = :userId")
			.setParameter("userId", id);
		
		if (query.executeUpdate() == 0) throw new RepositoryException("Couldn't delete User with ID = " + id + ". No such Entity");
		LOG.info("Deleted User from database");
		LOG.info("UserDao.deleteUserById(Integer id) END");
	}

	public List<User> getAllUsers() {
		LOG.info("UserDao.getAllUsers() START");
		List<User> users = getSession().createQuery("from User", User.class).list();
		LOG.debug("List of all Users = {}", users);
		LOG.info("UserDao.getAllUsers() END");
		return users;
	}

	@Override
	public User getUserByEmail(String email) {
		if (email == null) throw new RepositoryException("Email field is null");
		LOG.info("UserDao.getUserByEmail(String email) START");
		LOG.debug("Email parameter = {}", email);
		User user = getSession().createQuery("from User where email = :email", User.class).setParameter("email", email).uniqueResult();
		
		LOG.debug("User from DB = {}", user);
		LOG.info("UserDao.getUserByEmail(String email) END");
		return user;
	}

	@Override
	public User getUserByToken(String token) {
		if (token == null) throw new RepositoryException("Token field is null");
		LOG.info("UserDao.getUserByToken(String email) START");
		LOG.debug("Token parameter = {}", token);
		User user = getSession().createQuery("from User where confirmationToken = :token", User.class).setParameter("token", token).uniqueResult();
		
		LOG.debug("User from DB = {}", user);
		LOG.info("UserDao.getUserByToken(String token) END");
		return user;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkIfUsernameOrEmailAreTaken(String username, String email) {
		LOG.info("UserDao.checkIfUsernameOrEmailAreTaken(String username, String email) START");
		LOG.debug("Username parameter = {}, Email parameter = {}", username, email);
		if (username == null || email == null) throw new RepositoryException("Username or Email cannot be null");
		Query query = getSession().createQuery("select count(*) from User user where user.username = :username or user.email = :email").setParameter("username", username).setParameter("email", email);
		Long result = (Long)query.uniqueResult();
		LOG.debug("Number of counted rows = {}", result);
		LOG.info("UserDao.checkIfUsernameOrEmailAreTaken(String username, String email) END");
		if (result == 1) return true;
		return false;
	}
	
	
	

}
