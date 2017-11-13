package com.gladigator.Daos;

import com.gladigator.Entities.UserDetails;

public interface UserDetailsDao {
	
	public void saveOrUpdateUserDetails(UserDetails user);
	public UserDetails getUserDetailsById(Integer id);
	public void deleteUserDetailsById(Integer id);

}
