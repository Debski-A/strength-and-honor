package com.gladigator.integrationTests.Daos;

import org.hibernate.SessionFactory;
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
import com.gladigator.Exceptions.UserNotFound;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.validation.ConstraintViolationException;


@ActiveProfiles("test")
@ContextConfiguration(locations = {
		"classpath:com/gladigator/Configs/dao-context.xml",
		"classpath:com/gladigator/Configs/security-context.xml",
		"classpath:com/gladigator/Configs/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) // koteksty sa tworzone na nowo, a dataSource jest reinicjalizowane dla kazdej z testowych metod
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private User newUser;
	
	@Before
	public void before() {
		sessionFactory.getCurrentSession().createNativeQuery("ALTER TABLE users ALTER COLUMN id_user RESTART WITH 1").executeUpdate(); //RESETUJE ID AUTOINCREMENT
		newUser = new User("Roger", "rogeiro", "roger@gmail.com", true);
	}
	
	////////////// saveOrUpdateUser(User user)
	@Transactional
	@Test
	public void givenNewUser_WhenSaveOrUpdate_ThenNumberOfUsersIncreases() {
		int size = userDao.getAllUsers().size();
		userDao.saveOrUpdateUser(newUser);
		assertThat(userDao.getAllUsers(), hasSize(size + 1));
	}
	
	@Transactional
	@Test
	public void givenNewUser_WhenSaveOrUpdate_ThenNewUserIsUpdated() {
		userDao.saveOrUpdateUser(newUser);
		newUser.setUsername("Alberto");
		assertThat(userDao.getAllUsers().get(0).getUsername(), equalTo("Alberto"));
	}
	
	@Transactional
	@Test(expected = IllegalArgumentException.class)
	public void givenUserIsNull_WhenSaveOrUpdate_ThenThrowException() {
		newUser = null;
		userDao.saveOrUpdateUser(newUser);
	}
	
	@Transactional
	@Test(expected = ConstraintViolationException.class)
	public void givenUserWithNullField_WhenSaveOrUpdate_ThenThrowException() {
		newUser.setPassword(null);;
		userDao.saveOrUpdateUser(newUser);
	}
	
	////////////// getUserById(Integer id)
	@Transactional
	@Test
	public void givenUser_WhenGetUserById_ThenReturnUserFromDB() {
		userDao.saveOrUpdateUser(newUser);
		detachUserEntity(newUser);
		User userFromDB = userDao.getUserById(1);
		assertThat(userFromDB, equalTo(newUser));
	}
	
	@Transactional
	@Test(expected = UserNotFound.class)
	public void whenGetUserById_AndNoUserInDB_ThenThrowException() {
		userDao.getUserById(1);
	}
	
	@Transactional
	@Test(expected = IllegalArgumentException.class)
	public void whenGetUserByIdParameterIsNull_ThenThrowException() {
		userDao.getUserById(null);
	}
	
	////////////// deleteUserById(Integer id)
	@Transactional
	@Test
	public void givenUser_WhenDeleteUserById_ThenNumberOfUsersDecreases() {
		userDao.saveOrUpdateUser(newUser);
		int size = userDao.getAllUsers().size();
		userDao.deleteUserById(1);
		assertThat(userDao.getAllUsers(), hasSize(size - 1));
	}
	
	@Transactional
	@Test(expected = UserNotFound.class)
	public void whenDeleteUserById_AndNoUserInDB_ThenThrowException() {
		userDao.deleteUserById(1);
	}
	
	////////////// getAllUsers()
	@Transactional
	@Test
	public void givenTwoUsers_WhenGetAllUsers_ThenNumberOfUsersIsTwo() {
		userDao.saveOrUpdateUser(newUser);
		User secondUser = new User("Bolo", "kung-fu", "enterthedragon@gmail.com", true);
		userDao.saveOrUpdateUser(secondUser);
		assertThat(userDao.getAllUsers(), hasSize(2));
	}
	
	private void detachUserEntity(User user) {
		sessionFactory.getCurrentSession().detach(user);
	}
	

}
