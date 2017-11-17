package com.gladigator.integrationTests.Daos;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.UserDao;
import com.gladigator.Entities.User;
import com.gladigator.Exceptions.RepositoryException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@ActiveProfiles("test")
@ContextConfiguration(locations = "classpath:com/gladigator/Configs/datasource.xml")
@RunWith(SpringJUnit4ClassRunner.class)
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) // koteksty sa tworzone na nowo, a dataSource jest reinicjalizowane dla kazdej z testowych metod
@Transactional
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private User newUser;
	
	@Before
	public void before() {
		sessionFactory.getCurrentSession().createNativeQuery("ALTER TABLE users ALTER COLUMN id_user RESTART WITH 1").executeUpdate(); //RESETUJE ID AUTOINCREMENT
		newUser = new User("Roger", "rogeiro", "roger@gmail.com", true);
	}
	
	////////////// saveOrUpdateUser(User user)
	@Test
	public void givenNewUser_WhenSaveOrUpdate_ThenNumberOfUsersIncreases() throws Exception {
		int size = userDao.getAllUsers().size();
		userDao.saveOrUpdateUser(newUser);
		assertThat(userDao.getAllUsers(), hasSize(size + 1));
	}
	
	@Test
	public void givenNewUser_WhenSaveOrUpdate_ThenNewUserIsUpdated() throws Exception {
		userDao.saveOrUpdateUser(newUser);
		newUser.setUsername("Alberto");
		assertThat(userDao.getAllUsers().get(0).getUsername(), equalTo("Alberto")); //executing a query will trigger a flush if required --getAllUsers uzywa HQL--
	}
	
	@Test
	public void givenUserIsNull_WhenSaveOrUpdate_ThenThrowRepositoryException() throws Exception {
		newUser = null;
		exception.expect(RepositoryException.class);
		exception.expectMessage("Could not save User. User = " + newUser);
		userDao.saveOrUpdateUser(newUser);
	}
	
	@Test
	public void givenUserWithNullField_WhenSaveOrUpdate_ThenThrowRepositoryException() throws Exception {
		newUser.setPassword(null);;
		exception.expect(RepositoryException.class);
		exception.expectMessage("Could not save User. User = " + newUser);
		userDao.saveOrUpdateUser(newUser);
	}
	
	////////////// getUserById(Integer id)
	@Test
	public void givenUser_WhenGetUserById_ThenReturnUserFromDB() throws Exception {
		userDao.saveOrUpdateUser(newUser);
		sessionFactory.getCurrentSession().detach(newUser); //czemu detach? Patrz na podobna metode testowa w UserDetailsDaoTest
		User userFromDB = userDao.getUserById(1);
		assertThat(userFromDB, equalTo(newUser));
	}
	
	@Test
	public void whenGetUserById_AndNoUserInDB_ThenThrowRepositoryException() throws Exception {
		Integer id = 1;
		exception.expect(RepositoryException.class);
		exception.expectMessage("There is no User with ID = " + id);
		userDao.getUserById(id);
	}
	
	@Test
	public void whenGetUserByIdParameterIsNull_ThenThrowRepositoryException() throws Exception {
		Integer id = null;
		exception.expect(RepositoryException.class);
		exception.expectMessage("There is no User with ID = " + id);
		userDao.getUserById(id);
	}
	
	////////////// deleteUserById(Integer id)
	@Test
	public void givenUserWithId1_WhenDeleteUserById_ThenUserWithId1IsDeleted() throws Exception {
		userDao.saveOrUpdateUser(newUser);
		userDao.deleteUserById(1);
		assertThat(userDao.getAllUsers(), hasSize(0));
	}
	
	@Test
	public void whenDeleteUserById_AndThereIsNoSuchUser_ThenThrowRepositoryException() throws Exception {
		Integer id = 1;
		exception.expect(RepositoryException.class);
		exception.expectMessage("Couldn't delete User with ID = "+ id + ". No such Entity");
		userDao.deleteUserById(id);
	}
	
	////////////// getAllUsers()
	@Test
	public void givenTwoUsers_WhenGetAllUsers_ThenNumberOfUsersIsTwo() throws Exception {
		userDao.saveOrUpdateUser(newUser);
		User secondUser = new User("Bolo", "kung-fu", "enterthedragon@gmail.com", true);
		userDao.saveOrUpdateUser(secondUser);
		assertThat(userDao.getAllUsers(), hasSize(2));
	}
	

}
