package com.gladigator.Services;

import java.util.List;

import com.gladigator.Entities.Role;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;

public interface UserService {

	public User getUserById(Integer id);
	public void saveOrUpdateUser(User user);
	public void deleteUserById(Integer id);
	public List<User> getAllUsers();
	public User getUserByEmail(String email);
	public User getUserByToken(String token);
	public User getUserByUsername(String username);
	public boolean checkIfUsernameOrEmailAreTaken(String username, String email);
	public Role getRoleById(int id);
	public void saveOrUpdateUserDetails(UserDetails userDetails);
}
