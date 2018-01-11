package com.gladigator.Daos;

import com.gladigator.Entities.UserDetails;

public interface UserDetailsDao extends GenericDao<UserDetails>{
	
	public void deleteUserDetailsById(Integer id);
}
