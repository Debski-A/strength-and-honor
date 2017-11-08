package com.gladigator.Daos;

import java.util.List;

import com.gladigator.Entities.User;

public interface UserDao {
	
	public void saveOrUpdateUser(User user);
	public User getUser(Integer id);
	public void deleteUser(Integer id);
	public List<User> getAllUsers();
	

}
