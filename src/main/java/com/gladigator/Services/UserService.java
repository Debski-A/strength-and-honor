package com.gladigator.Services;

import java.util.List;

import com.gladigator.Entities.User;

public interface UserService {

	public User getUserById(Integer id);
	public void saveOrUpdateUser(User user);
	public void deleteUserById(Integer id);
	public List<User> getAllUsers();
	public User getUserByEmail(String email);
	public User getUserByToken(String token);
}
