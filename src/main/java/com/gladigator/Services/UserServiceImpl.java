package com.gladigator.Services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.UserDao;
import com.gladigator.Entities.User;
import com.gladigator.Exceptions.RepositoryException;
import com.gladigator.Exceptions.ServiceException;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Transactional
	public User getUserById(Integer id) {
		User user = null;
		LOG.debug("Id parameter = {}", id);
		try {
			user = userDao.getUserById(id);
		} catch (RepositoryException ex) {
			throw new ServiceException("RepositoryException occured", ex);
		} catch (Exception ex) {
			throw new ServiceException("Exception occured", ex);
		}
		LOG.debug("User from userDao: " + user);
		return user;
	}

	@Transactional
	public void saveOrUpdateUser(User user) {
		LOG.debug("User parameter = {}", user);
		try {
			userDao.saveOrUpdateUser(user);
		} catch (RepositoryException ex) {
			throw new ServiceException("RepositoryException occured", ex);
		} catch (Exception ex) {
			throw new ServiceException("Exception occured", ex);
		}
	}

	@Transactional
	public void deleteUserById(Integer id) {
		LOG.debug("ID parameter = " + id);
		try {
			userDao.deleteUserById(id);
		} catch (RepositoryException ex) {
			throw new ServiceException("RepositoryException occured", ex);
		} catch (Exception ex) {
			throw new ServiceException("Exception occured", ex);
		}
	}

	@Transactional
	public List<User> getAllUsers() {
		List<User> users = null;
		try {
			users = userDao.getAllUsers();
		} catch (Exception ex) {
			throw new ServiceException("Exception occured", ex);
		}
		return users;
	}

	@Transactional
	public User getUserByEmail(String email) {
		User user = null;
		LOG.debug("Email parameter = {}", email);
		try {
			user = userDao.getUserByEmail(email);
		} catch (RepositoryException ex) {
			throw new ServiceException("RepositoryException occured", ex);
		} catch (Exception ex) {
			throw new ServiceException("Exception occured", ex);
		}
		LOG.debug("User from userDao: " + user);
		return user;
	}

	@Transactional
	public User getUserByToken(String token) {
		User user = null;
		LOG.debug("Token parameter = {}", token);
		try {
			user = userDao.getUserByToken(token);
		} catch (RepositoryException ex) {
			throw new ServiceException("RepositoryException occured", ex);
		} catch (Exception ex) {
			throw new ServiceException("Exception occured", ex);
		}
		LOG.debug("User from userDao: " + user);
		return user;
	}

}
