package com.gladigator.unitTests.Daos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.gladigator.Daos.UserDao;
import com.gladigator.Entities.BodyType;
import com.gladigator.Entities.FrequencyOfActivity;
import com.gladigator.Entities.Role;
import com.gladigator.Entities.Sex;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;

@ActiveProfiles("test")
@ContextConfiguration(locations = {
		"classpath:com/gladigator/Configs/dao-context.xml",
		"classpath:com/gladigator/Configs/security-context.xml",
		"classpath:com/gladigator/Configs/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) // koteksty sa tworzone na nowo, a dataSource jest reinicjalizowane dla kazdej z testowych metod
@Transactional	
public class UserDaoInteg {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private User user1 = new User(1, "inzo", "herflik1", "inzo555@o2.pl", true,
			new UserDetails.UserDetailsBuilder()
			.setUserId(1)
			.setAge(27)
			.setHeight(193)
			.setWeight(97)
			.setBmi(666)
			.setBmr(666)
			.setSex(new Sex(1))  //male
			.setBodyType(new BodyType(2))  //mezomorphic
			.setFrequencyOfActivity(new FrequencyOfActivity(5)).build(),  //very high
			new ArrayList<>(Arrays.asList(new Role(2)))); //ROLE_ADMIN
	
	private User user2 = new User(2, "isia", "herflik2", "justysiek89@wp.pl", true,
			new UserDetails.UserDetailsBuilder()
			.setUserId(2)
			.setAge(27)
			.setHeight(158)
			.setWeight(50)
			.setBmi(15)
			.setBmr(15)
			.setSex(new Sex(2))  //female
			.setBodyType(new BodyType(1))  //ectomorphic
			.setFrequencyOfActivity(new FrequencyOfActivity(4)).build(),  //high
			new ArrayList<>(Arrays.asList(new Role(1)))); //ROLE_USER
	
	private User user3 = new User(3, "zbyszek", "boniek", "zbyszek@gmail.com", true,
			new UserDetails.UserDetailsBuilder()
			.setUserId(3)
			.setAge(52)
			.setHeight(180)
			.setWeight(82)
			.setBmi(333)
			.setBmr(333)
			.setSex(new Sex(1))  //male
			.setBodyType(new BodyType(3))  //endomorphic
			.setFrequencyOfActivity(new FrequencyOfActivity(1)).build(), //very low
			new ArrayList<>(Arrays.asList(new Role(1), new Role(2)))); //ROLE_USER && ROLE_ADMIN
	
	@Before
	public void init() {
		//TODO
	}
	
	@Test 
	public void whenSaveOrUpdateThenNumberOfUsersInDatabaseIncerases() {
		userDao.saveOrUpdateUser(user1);
		System.out.println(user1);
		System.out.println(userDao.getUser(1));
		userDao.saveOrUpdateUser(user2);
		//assertThat(userDao.getAllUsers().size(), equalTo(2));
	}
	

}
