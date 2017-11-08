package com.gladigator.Daos;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Entities.User;


@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;

	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void printAllUsers() {
		
		List<User> allUsers = getSession().createQuery("from User", User.class).list();
		System.out.println(allUsers.get(0).toString());
	}
	
	@Transactional
	public void saveOrUpdateUser(User user) {
		getSession().saveOrUpdate(user);
	}

	@Transactional
	public User getUser(Integer id) {
		return getSession().get(User.class, id);
	}
	
	@Transactional
	public void deleteUser(Integer id) {
		getSession().createQuery("delete from User where id=:userId")
			.setParameter("userId", id);
	}

	@Transactional
	public List<User> getAllUsers() {
		return getSession().createQuery("from User", User.class).list();
		
	}
	
	
	

}
