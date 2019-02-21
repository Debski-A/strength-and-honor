package com.gladigator.integrationTests.Daos;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.UserDao;
import com.gladigator.Entities.User;
import com.gladigator.Exceptions.RepositoryException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.UUID;

@ContextConfiguration(locations = "classpath:com/gladigator/Configs/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private User newUser;
	private String newUserToken;

	@Before
	public void before() {
		sessionFactory.getCurrentSession().createNativeQuery("ALTER TABLE users ALTER COLUMN id_user RESTART WITH 1")
				.executeUpdate(); // RESETUJE ID AUTOINCREMENT
		newUserToken = UUID.randomUUID().toString();
		newUser = new User("Roger", "rogeiro", "roger@gmail.com", newUserToken, true);
	}
	
	////////////// saveOrUpdateUser(User user)
	@Test
	public void givenNewUser_WhenSaveOrUpdate_ThenNumberOfUsersIncreases() throws Exception {
		int size = userDao.getAll().size();
		userDao.saveOrUpdate(newUser);
		assertThat(userDao.getAll(), hasSize(size + 1));
	}

	@Test
	public void givenNewUser_WhenSaveOrUpdate_ThenNewUserIsUpdated() throws Exception {
		userDao.saveOrUpdate(newUser);
		newUser.setUsername("Alberto");
		assertThat(userDao.getAll().get(0).getUsername(), equalTo("Alberto")); // executing a query will trigger a
																					// flush if required --getAllUsers
																					// uzywa HQL--
	}

	@Test
	public void givenUserIsNull_WhenSaveOrUpdate_ThenThrowRepositoryException() throws Exception {
		newUser = null;
		exception.expect(RepositoryException.class);
		exception.expectMessage("An Exception occurred");
		userDao.saveOrUpdate(newUser);
	}

	////////////// getUserById(Integer id)
	@Test
	public void givenUser_WhenGetUserById_ThenReturnUserFromDB() throws Exception {
		userDao.saveOrUpdate(newUser);
		sessionFactory.getCurrentSession().detach(newUser); 
		User userFromDB = userDao.findById(1);
		assertThat(userFromDB, equalTo(newUser));
	}

	@Test
	public void whenGetUserById_AndNoUserInDB_ThenThrowRepositoryException() throws Exception {
		Integer id = 1;
		exception.expect(RepositoryException.class);
		exception.expectMessage("No User entity with such id");
		userDao.findById(id);
	}

	@Test
	public void whenGetUserByIdParameterIsNull_ThenThrowRepositoryException() throws Exception {
		Integer id = null;
		exception.expect(RepositoryException.class);
		exception.expectMessage("An Exception occurred");
		userDao.findById(id);
	}

	////////////// deleteUserById(Integer id)
	@Test
	public void givenUserWithId1_WhenDeleteUserById_ThenUserWithId1IsDeleted() throws Exception {
		userDao.saveOrUpdate(newUser);
		userDao.deleteUserById(1);
		assertThat(userDao.getAll(), hasSize(0));
	}

	@Test
	public void whenDeleteUserById_AndThereIsNoSuchUser_ThenThrowRepositoryException() throws Exception {
		Integer id = 1;
		exception.expect(RepositoryException.class);
		exception.expectMessage("Couldn't delete User with ID = " + id + ". No such Entity");
		userDao.deleteUserById(id);
	}

	////////////// getAllUsers()
	@Test
	public void givenTwoUsers_WhenGetAllUsers_ThenNumberOfUsersIsTwo() throws Exception {
		userDao.saveOrUpdate(newUser);
		String token = UUID.randomUUID().toString();
		User secondUser = new User("Bolo", "kung-fu", "enterthedragon@gmail.com", token, true);
		userDao.saveOrUpdate(secondUser);
		assertThat(userDao.getAll(), hasSize(2));
	}

	/////////////// getUserByEmail(String email)
	@Test
	public void givenUser_WhenGetUserByEmail_ThenReturnUserFromDB() throws Exception {
		userDao.saveOrUpdate(newUser);
		User userFromDB = userDao.getUserByEmail("roger@gmail.com");
		assertThat(userFromDB, equalTo(newUser));
	}

	@Test
	public void whenGetUserByEmail_AndNoUserInDB_ThenReturnNull() throws Exception {
		assertThat(userDao.getUserByEmail("email@gmail.com"), equalTo(null));
	}

	@Test
	public void whenGetUserByEmailParameterIsNull_ThenThrowRepositoryException() throws Exception {
		String email = null;
		exception.expect(RepositoryException.class);
		exception.expectMessage("Email field is null");
		userDao.getUserByEmail(email);
	}

	/////////////// getUserByToken(String token)
	@Test
	public void givenUser_WhenGetUserByToken_ThenReturnUserFromDB() throws Exception {
		userDao.saveOrUpdate(newUser);
		User userFromDB = userDao.getUserByToken(newUserToken);
		assertThat(userFromDB, equalTo(newUser));
	}

	@Test
	public void whenGetUserByToken_AndNoUserInDB_ThenReturnNull() throws Exception {
		assertThat(userDao.getUserByToken("xyz"), equalTo(null));
	}

	@Test
	public void whenGetUserByTokenParameterIsNull_ThenThrowRepositoryException() throws Exception {
		String token = null;
		exception.expect(RepositoryException.class);
		exception.expectMessage("Token field is null");
		userDao.getUserByToken(token);
	}
	
	//////////////// checkIfUsernameOrEmailAreTaken(String username, String email)
	@Test
	public void whenCheckIfUsernameOrEmailAreTaken_AndStillAvailable_ThenReturnFalse() throws Exception {
		assertThat(userDao.checkIfUsernameOrEmailAreTaken("user44", "user44@gmail.com"), is(false));
	}
	
	@Test
	public void givenUserWithEmail_WhenCheckIfUsernameOrEmailAreTaken_AndEmailIsTaken_ThenReturnTrue() throws Exception {
		userDao.saveOrUpdate(newUser);
		assertThat(userDao.checkIfUsernameOrEmailAreTaken("user44", "roger@gmail.com"), is(true));
	}
	
	@Test
	public void givenUserWithEmail_WhenCheckIfUsernameOrEmailAreTaken_AndUsernameIsTaken_ThenReturnTrue() throws Exception {
		userDao.saveOrUpdate(newUser);
		assertThat(userDao.checkIfUsernameOrEmailAreTaken("Roger", "rxyz"), is(true));
	}
	
	@Test
	public void givenUserWithEmail_WhenCheckIfUsernameOrEmailAreTaken_AndBothAreTaken_ThenReturnTrue() throws Exception {
		userDao.saveOrUpdate(newUser);
		assertThat(userDao.checkIfUsernameOrEmailAreTaken("Roger", "roger@gmail.com"), is(true));
	}
	
	@Test
	public void whenCheckIfUsernameOrEmailAreTaken_AndParamsAreNull_ThrowRepositoryException() throws Exception {
		exception.expect(RepositoryException.class);
		exception.expectMessage("Username or Email cannot be null");
		userDao.checkIfUsernameOrEmailAreTaken(null, null);
	}
	 
	////////////////getUserByUsername(String username)
	@Test
	public void givenUser_WhenGetUserByUsername_ThenReturnUserFromDB() throws Exception {
		userDao.saveOrUpdate(newUser);
		User userFromDB = userDao.getUserByUsername("Roger");
		assertThat(userFromDB, equalTo(newUser));
	}

	@Test
	public void whenGetUserByUsername_AndNoUserInDB_ThenReturnNull() throws Exception {
		assertThat(userDao.getUserByUsername("xyz"), equalTo(null));
	}

	@Test
	public void whenGetUserByUsernameParameterIsNull_ThenThrowRepositoryException() throws Exception {
		String username = null;
		exception.expect(RepositoryException.class);
		exception.expectMessage("Username field is null");
		userDao.getUserByUsername(username);
	}

}
