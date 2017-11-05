package com.gladigator.Daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Domains.User;


@Repository
public class UserDao {

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
	
	
	

}
