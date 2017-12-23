package com.gladigator.Services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.RoleDao;
import com.gladigator.Daos.UserDao;
import com.gladigator.Daos.UserDetailsDao;
import com.gladigator.Entities.Role;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Exceptions.RepositoryException;
import com.gladigator.Exceptions.ServiceException;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private RoleDao roleDao;

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
		return user;
	}

	@Transactional
	public boolean checkIfUsernameOrEmailAreTaken(String username, String email) {
		boolean result = false;
		LOG.debug("Username parameter = {}. Email parameter = {}", username, email);
		try {
			result = userDao.checkIfUsernameOrEmailAreTaken(username, email);
		} catch (RepositoryException ex) {
			throw new ServiceException("RepositoryException occured", ex);
		} catch (Exception ex) {
			throw new ServiceException("Exception occured", ex);
		}
		return result;
	}
	
	@Transactional
	public Role getRoleById(int id) {
		Role role = null;
		LOG.debug("Id parameter = {}", id);
		try {
			role = roleDao.getRoleById(id);
		} catch (RepositoryException ex) {
			throw new ServiceException("RepositoryException occured", ex);
		} catch (Exception ex) {
			throw new ServiceException("Exception occured", ex);
		}
		return role;
	}

	@Transactional
	public User getUserByUsername(String username) {
		User user = null;
		LOG.debug("Username parameter = {}", username);
		try {
			user = userDao.getUserByUsername(username);
		} catch (RepositoryException ex) {
			throw new ServiceException("RepositoryException occured", ex);
		} catch (Exception ex) {
			throw new ServiceException("Exception occured", ex);
		}
		return user;
	}

	@Transactional
	public void saveOrUpdateUserDetails(UserDetails userDetails) {
		LOG.debug("UserDetails parameter = {}", userDetails);
		try {
			userDetailsDao.saveOrUpdateUserDetails(userDetails);
		} catch (RepositoryException ex) {
			throw new ServiceException("RepositoryException occured", ex);
		} catch (Exception ex) {
			throw new ServiceException("Exception occured", ex);
		}
	}

}
