package com.gladigator.Daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.gladigator.Entities.User;
import com.gladigator.Exceptions.RepositoryException;


@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{
	
	public UserDaoImpl() {
		super(User.class);
	}

	private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);

	@SuppressWarnings("rawtypes")
	public void deleteUserById(Integer id) throws RepositoryException {
		LOG.info("UserDao.deleteUserById(Integer id) START");
		Query query = getSession().createQuery("delete from User where userId = :userId")
			.setParameter("userId", id);
		
		if (query.executeUpdate() == 0) throw new RepositoryException("Couldn't delete User with ID = " + id + ". No such Entity");
		LOG.info("Deleted User from database");
		LOG.info("UserDao.deleteUserById(Integer id) END");
	}

	@Override
	public User getUserByEmail(String email) {
		if (email == null) throw new RepositoryException("Email field is null");
		LOG.info("UserDao.getUserByEmail(String email) START");
		User user = getSession().createQuery("from User where email = :email", User.class).setParameter("email", email).uniqueResult();
		
		LOG.debug("User from DB = {}", user);
		LOG.info("UserDao.getUserByEmail(String email) END");
		return user;
	}

	@Override
	public User getUserByToken(String token) {
		if (token == null) throw new RepositoryException("Token field is null");
		LOG.info("UserDao.getUserByToken(String token) START");
		User user = getSession().createQuery("from User where confirmationToken = :token", User.class).setParameter("token", token).uniqueResult();
		
		LOG.debug("User from DB = {}", user);
		LOG.info("UserDao.getUserByToken(String token) END");
		return user;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkIfUsernameOrEmailAreTaken(String username, String email) {
		LOG.info("UserDao.checkIfUsernameOrEmailAreTaken(String username, String email) START");
		if (username == null || email == null) throw new RepositoryException("Username or Email cannot be null");
		Query query = getSession().createQuery("select count(*) from User user where user.username = :username or user.email = :email").setParameter("username", username).setParameter("email", email);
		Long result = (Long)query.uniqueResult();
		LOG.debug("Number of counted rows = {}, User exists = {}", result, result > 0);
		LOG.info("UserDao.checkIfUsernameOrEmailAreTaken(String username, String email) END");
		if (result == 1) return true;
		return false;
	}

	@Override
	public User getUserByUsername(String username) {
		if (username == null) throw new RepositoryException("Username field is null");
		LOG.info("UserDao.getUserByUsername(String username) START");
		User user = getSession().createQuery("from User where username = :username", User.class).setParameter("username", username).uniqueResult();
		
		LOG.debug("User from DB = {}", user);
		LOG.info("UserDao.getUserByUsername(String username) END");
		return user;
	}
	
	public void throwRepositoryException() throws RepositoryException {
	    throw new RepositoryException("exception from repositorylayer");
	}

}
