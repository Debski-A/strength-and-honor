package com.gladigator.Daos;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gladigator.Entities.User;
import com.gladigator.Exceptions.UserNotFound;


@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

//	public void printAllUsers() {
//		
//		List<User> allUsers = getSession().createQuery("from User", User.class).list();
//		System.out.println(allUsers.get(0).toString());
//	}
	
	public void saveOrUpdateUser(User user) {
		getSession().saveOrUpdate(user);
	}

	public User getUserById(Integer id) {
		User user = getSession().get(User.class, id);
		if (user == null) throw new UserNotFound();
		return user;
	}
	
	@SuppressWarnings("rawtypes")
	public void deleteUserById(Integer id) {
		Query query = getSession().createQuery("delete from User where userId = :userId")
			.setParameter("userId", id);
		
		if (query.executeUpdate() == 0) throw new UserNotFound(); 
	}

	public List<User> getAllUsers() {
		return getSession().createQuery("from User", User.class).list();
	}
	
	
	

}
