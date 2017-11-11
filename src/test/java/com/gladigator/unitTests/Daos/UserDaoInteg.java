package com.gladigator.unitTests.Daos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.UserDao;
import com.gladigator.Entities.User;

@ActiveProfiles("test")
@ContextConfiguration(locations = {
		"classpath:com/gladigator/Configs/dao-context.xml",
		"classpath:com/gladigator/Configs/security-context.xml",
		"classpath:com/gladigator/Configs/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) // koteksty sa tworzone na nowo, a dataSource jest reinicjalizowane dla kazdej z testowych metod
public class UserDaoInteg {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void test() {
		System.out.println("TEST GET");
		User adam = userDao.getUser(1);
		System.out.println(adam);
	}
	
	@Test 
	public void whenSaveOrUpdateThenNumberOfUsersInDatabaseIncerases() {
		System.out.println("TEST UPDATE");
		userDao.deleteUser(1);
		System.out.println(userDao.getUser(1));
	}
	

}
