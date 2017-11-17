package com.gladigator.Services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.UserDao;
import com.gladigator.Entities.User;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Transactional
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	@Transactional
	public void saveOrUpdateUser(User user) {
		userDao.saveOrUpdateUser(user);
	}

	@Transactional
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
