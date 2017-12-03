package com.gladigator.unitTests.Services;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.gladigator.Daos.UserDao;
import com.gladigator.Entities.User;
import com.gladigator.Exceptions.RepositoryException;
import com.gladigator.Exceptions.ServiceException;
import com.gladigator.Services.UserService;
import com.gladigator.Services.UserServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService = new UserServiceImpl();
	
	@Mock
	private UserDao userDao;
	
	private User testUser;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void before() {
		String token = UUID.randomUUID().toString();
		testUser = new User("adam", "adam1234", "adam@gmail.com", token, true);
	}
	
	
//	public User getUserById(Integer id);
	@Test
	public void whenGetUserById_ThenReturnTestUser() throws Exception {
		Mockito.when(userDao.getUserById(1)).thenReturn(testUser);
		assertThat(userService.getUserById(1), equalTo(testUser));
		Mockito.verify(userDao, Mockito.times(1)).getUserById(1);
	}
	
	@Test
	public void whenGetUserById_AndUserDaoIsNull_ThenThrowServiceException() throws Exception {
		Mockito.doThrow(NullPointerException.class).when(userDao).getUserById(1);
		exception.expect(ServiceException.class);
		exception.expectMessage("Exception occured");
		userService.getUserById(1);
	}
	
	@Test
	public void whenGetUserById_AndRepositroyExceptionOccured_ThenThrowServiceException() throws Exception {
		Mockito.when(userDao.getUserById(Mockito.any(Integer.class))).thenThrow(RepositoryException.class);
		exception.expect(ServiceException.class);
		exception.expectMessage("RepositoryException occured");
		userService.getUserById(3);
	}
	
//	public void saveOrUpdateUser(User user);
	@Test
	public void whenSaveOrUpdateUser_ThenDaoMethodIsInvoked() throws Exception {
		userService.saveOrUpdateUser(testUser);
		Mockito.verify(userDao, Mockito.times(1)).saveOrUpdateUser(testUser);
	}
	
	@Test
	public void whenSaveOrUpdateUser_AndRepositoryExceptionOccured_ThenThrowServiceException() throws Exception {
		Mockito.doThrow(RepositoryException.class).when(userDao).saveOrUpdateUser(testUser);
		exception.expect(ServiceException.class);
		exception.expectMessage("RepositoryException occured");
		userService.saveOrUpdateUser(testUser);
	}
	
	@Test
	public void whenSaveOrUpdateUser_AndUserDaoIsNull_ThenThrowServiceException() throws Exception {
		Mockito.doThrow(NullPointerException.class).when(userDao).saveOrUpdateUser(testUser);
		exception.expect(ServiceException.class);
		exception.expectMessage("Exception occured");
		userService.saveOrUpdateUser(testUser);
	}
	
//	public void deleteUserById(Integer id);
	@Test
	public void whenDeleteUserById_ThenDaoMethodIsInvoked() throws Exception {
		userService.deleteUserById(3);
		Mockito.verify(userDao, Mockito.times(1)).deleteUserById(3);
	}
	
	@Test
	public void whenDeleteUserById_AndUserDaoIsNull_ThenThrowServiceException() throws Exception {
		Mockito.doThrow(NullPointerException.class).when(userDao).deleteUserById(1);
		exception.expect(ServiceException.class);
		exception.expectMessage("Exception occured");
		userService.deleteUserById(1);
	}
	
	@Test
	public void whenDeleteUserById_AndRepositoryExceptionOccured_ThenThrowServiceException() throws Exception {
		Mockito.doThrow(RepositoryException.class).when(userDao).deleteUserById(Mockito.any(Integer.class));
		exception.expect(ServiceException.class);
		exception.expectMessage("RepositoryException occured");
		userService.deleteUserById(45);
	}
	
//	public List<User> getAllUsers();
	@Test
	public void givenTwoUsers_WhenGetAllUsers_ThenSizeOfReturnedListIs2() throws Exception {
		String token = UUID.randomUUID().toString();
		User testUser2 = new User("Mario", "mario1234", "mario@gmail.com", token, true);
		List<User> users = new ArrayList<>(Arrays.asList(testUser, testUser2));
		Mockito.when(userDao.getAllUsers()).thenReturn(users);
		assertThat(userService.getAllUsers(), hasSize(2));
		Mockito.verify(userDao, Mockito.times(1)).getAllUsers();
	}
	
	@Test
	public void whenGetAllUsers_AndUserDaoIsNull_ThenThrowServiceException() throws Exception {
		Mockito.doThrow(NullPointerException.class).when(userDao).getAllUsers();
		exception.expect(ServiceException.class);
		exception.expectMessage("Exception occured");
		userService.getAllUsers();
	}
	
}
