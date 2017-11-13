package com.gladigator.Daos;

import java.util.List;

import com.gladigator.Entities.User;

public interface UserDao {
	
	public void saveOrUpdateUser(User user);
	public User getUserById(Integer id);
	public void deleteUserById(Integer id);
	public List<User> getAllUsers();
	

}
