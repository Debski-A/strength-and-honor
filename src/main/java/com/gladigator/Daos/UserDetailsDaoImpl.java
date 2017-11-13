package com.gladigator.Daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gladigator.Entities.UserDetails;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void saveOrUpdateUserDetails(UserDetails userDetails) {
		getSession().saveOrUpdate(userDetails);
	}

	public UserDetails getUserDetailsById(Integer id) {
		return getSession().get(UserDetails.class, id);
	}

	@SuppressWarnings("rawtypes")
	public void deleteUserDetailsById(Integer id) {
		Query query = getSession().createQuery("delete from UserDetails where userId = :userId")
			.setParameter("userId", id);
		
		query.executeUpdate();
		
	}

}
