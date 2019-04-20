package com.gladigator.Daos;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gladigator.Entities.Role;
import com.gladigator.Exceptions.RepositoryException;

@Repository
public class RoleDaoImpl implements RoleDao{
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role getRoleById(Integer id) {
		Role role = null;
		try {
			role = sessionFactory.getCurrentSession().get(Role.class, id);
			if (role == null) {
				throw new RepositoryException("There is no Role with such id");
			}
		} catch (IllegalArgumentException ex) {
			throw new RepositoryException("Argument is invalid. Role = " + role, ex);
		}
		
		LOG.debug("Role from DB = {}", role);
		return role;
	}

}
