package com.gladigator.Daos;

import com.gladigator.Entities.User;

public interface UserDao extends GenericDao<User>{
	
	public void deleteUserById(Integer id);
	public User getUserByEmail(String email);
	public User getUserByToken(String token);
	public User getUserByUsername(String username);
	public boolean checkIfUsernameOrEmailAreTaken(String username, String Email);
	

}
