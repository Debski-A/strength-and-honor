package com.gladigator.Services;

import com.gladigator.Entities.Role;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;

public interface UserService extends GenericService<User>{

	public void deleteUserById(Integer id);
	public User getUserByEmail(String email);
	public User getUserByToken(String token);
	public User getUserByUsername(String username);
	public boolean checkIfUsernameOrEmailAreTaken(String username, String email);
	public Role getRoleById(int id);
	public void saveOrUpdateUserDetails(UserDetails userDetails);
	public void throwServiceException();
	public void throwRepositoryException();
}
