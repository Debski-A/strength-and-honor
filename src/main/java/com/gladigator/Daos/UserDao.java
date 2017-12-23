package com.gladigator.Daos;

import java.util.List;

import com.gladigator.Entities.User;

public interface UserDao {
	
	public void saveOrUpdateUser(User user);
	public void deleteUserById(Integer id);
	public List<User> getAllUsers();
	public User getUserById(Integer id);
	public User getUserByEmail(String email);
	public User getUserByToken(String token);
	public User getUserByUsername(String username);
	public boolean checkIfUsernameOrEmailAreTaken(String username, String Email);
	

}
