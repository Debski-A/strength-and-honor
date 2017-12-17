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

import com.gladigator.Daos.RoleDao;
import com.gladigator.Daos.UserDao;
import com.gladigator.Entities.Role;
import com.gladigator.Entities.User;
import com.gladigator.Exceptions.RepositoryException;
import com.gladigator.Exceptions.ServiceException;
import com.gladigator.Services.UserService;
import com.gladigator.Services.UserServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

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
	
	@Mock
	private RoleDao roleDao;
	
	private User testUser;
	private String token;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void before() {
		token = UUID.randomUUID().toString();
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
		Mockito.verify(userDao).getUserById(1);
	}
	
	@Test
	public void whenGetUserById_AndRepositroyExceptionOccured_ThenThrowServiceException() throws Exception {
		Mockito.when(userDao.getUserById(Mockito.any(Integer.class))).thenThrow(RepositoryException.class);
		exception.expect(ServiceException.class);
		exception.expectMessage("RepositoryException occured");
		userService.getUserById(3);
		Mockito.verify(userDao).getUserById(3);
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
		Mockito.verify(userDao).saveOrUpdateUser(testUser);
	}
	
	@Test
	public void whenSaveOrUpdateUser_AndUserDaoIsNull_ThenThrowServiceException() throws Exception {
		Mockito.doThrow(NullPointerException.class).when(userDao).saveOrUpdateUser(testUser);
		exception.expect(ServiceException.class);
		exception.expectMessage("Exception occured");
		userService.saveOrUpdateUser(testUser);
		Mockito.verify(userDao).saveOrUpdateUser(testUser);
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
		Mockito.verify(userDao).deleteUserById(1);
	}
	
	@Test
	public void whenDeleteUserById_AndRepositoryExceptionOccured_ThenThrowServiceException() throws Exception {
		Mockito.doThrow(RepositoryException.class).when(userDao).deleteUserById(Mockito.any(Integer.class));
		exception.expect(ServiceException.class);
		exception.expectMessage("RepositoryException occured");
		userService.deleteUserById(45);
		Mockito.verify(userDao).deleteUserById(45);
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
		Mockito.verify(userDao).getAllUsers();
	}
	
//	public User getUserByEmail(String email);
	@Test
	public void whenGetUserByEmail_ThenReturnTestUser() throws Exception {
		Mockito.when(userDao.getUserByEmail("adam@gmail.com")).thenReturn(testUser);
		assertThat(userService.getUserByEmail("adam@gmail.com"), equalTo(testUser));
		Mockito.verify(userDao, Mockito.times(1)).getUserByEmail("adam@gmail.com");
	}
	
	@Test
	public void whenGetUserByEmail_AndUserDaoIsNull_ThenThrowServiceException() throws Exception {
		Mockito.doThrow(NullPointerException.class).when(userDao).getUserByEmail("adam@gmail.com");
		exception.expect(ServiceException.class);
		exception.expectMessage("Exception occured");
		userService.getUserByEmail("adam@gmail.com");
		Mockito.verify(userDao).getUserByEmail("adam@gmail.com");
	}
	
	@Test
	public void whenGetUserByEmail_AndRepositroyExceptionOccured_ThenThrowServiceException() throws Exception {
		Mockito.when(userDao.getUserByEmail(Mockito.any(String.class))).thenThrow(RepositoryException.class);
		exception.expect(ServiceException.class);
		exception.expectMessage("RepositoryException occured");
		userService.getUserByEmail("xyz");
		Mockito.verify(userDao).getUserByEmail("xyz");
	}
	
//	public User getUserByToken(String token);
	@Test
	public void whenGetUserByToken_ThenReturnTestUser() throws Exception {
		Mockito.when(userDao.getUserByToken(token)).thenReturn(testUser);
		assertThat(userService.getUserByToken(token), equalTo(testUser));
		Mockito.verify(userDao, Mockito.times(1)).getUserByToken(token);
	}
	
	@Test
	public void whenGetUserByToken_AndUserDaoIsNull_ThenThrowServiceException() throws Exception {
		Mockito.doThrow(NullPointerException.class).when(userDao).getUserByToken(token);
		exception.expect(ServiceException.class);
		exception.expectMessage("Exception occured");
		userService.getUserByToken(token);
		Mockito.verify(userDao).getUserByToken(token);
	}
	
	@Test
	public void whenGetUserByToken_AndRepositroyExceptionOccured_ThenThrowServiceException() throws Exception {
		Mockito.when(userDao.getUserByToken(Mockito.any(String.class))).thenThrow(RepositoryException.class);
		exception.expect(ServiceException.class);
		exception.expectMessage("RepositoryException occured");
		userService.getUserByToken("xyz");
		Mockito.verify(userDao).getUserByToken("xyz");
	}
	
// checkIfUsernameOrEmailAreTaken(String username, String email)
	@Test
	public void whenCheckIfUsernameOrEmailAreTaken_AndDaoMethodReturnsFalse_ReturnFalse() {
		Mockito.when(userDao.checkIfUsernameOrEmailAreTaken(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
		assertThat(userService.checkIfUsernameOrEmailAreTaken("adam", "adam@gmail.com"), is(false));
		Mockito.verify(userDao).checkIfUsernameOrEmailAreTaken("adam", "adam@gmail.com");
	}
	
	@Test
	public void whenCheckIfUsernameOrEmailAreTaken_AndDaoMethodReturnsTrue_ReturnTrue() {
		Mockito.when(userDao.checkIfUsernameOrEmailAreTaken(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		assertThat(userService.checkIfUsernameOrEmailAreTaken("pablo", "pablo@gmail.com"), is(true));
		Mockito.verify(userDao).checkIfUsernameOrEmailAreTaken(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void whenCheckIfUsernameOrEmailAreTaken_AndUserDaoIsNull_ThenThrowServiceException() throws Exception {
		Mockito.doThrow(NullPointerException.class).when(userDao).checkIfUsernameOrEmailAreTaken(Mockito.anyString(), Mockito.anyString());
		exception.expect(ServiceException.class);
		exception.expectMessage("Exception occured");
		userService.checkIfUsernameOrEmailAreTaken("adam", "adam@gmail.com");
		Mockito.verify(userDao).checkIfUsernameOrEmailAreTaken("adam", "adam@gmail.com");
	}
	
	@Test
	public void whenCheckIfUsernameOrEmailAreTaken_AndRepositroyExceptionOccurs_ThenThrowServiceException() throws Exception {
		Mockito.when(userDao.checkIfUsernameOrEmailAreTaken(Mockito.anyString(), Mockito.anyString())).thenThrow(RepositoryException.class);
		exception.expect(ServiceException.class);
		exception.expectMessage("RepositoryException occured");
		userService.checkIfUsernameOrEmailAreTaken("adam", "adam@gmail.com");
		Mockito.verify(userDao).checkIfUsernameOrEmailAreTaken("adam", "adam@gmail.com");
	}
	
	@Test
	public void whenGetRoleById1_ThenReturnRoleUser() throws Exception {
		Role roleUser = mock(Role.class);
		Mockito.when(roleUser.getRole()).thenReturn("ROLE_USER");
		Mockito.when(roleDao.getRoleById(1)).thenReturn(roleUser);
		
		assertThat(userService.getRoleById(1).getRole(), equalTo("ROLE_USER"));
		Mockito.verify(roleUser).getRole();
		Mockito.verify(roleDao).getRoleById(1);
	}
	
	@Test 
	public void whenGetRoleById_AndRepositoryExpcetionOccurs_ThenThrowServiceException() throws Exception {
		Mockito.when(roleDao.getRoleById(Mockito.anyInt())).thenThrow(RepositoryException.class);
		exception.expect(ServiceException.class);
		exception.expectMessage("RepositoryException occured");
		
		userService.getRoleById(1);
		
		Mockito.verify(roleDao).getRoleById(Mockito.anyInt());
	}
	
	@Test
	public void whenGetRoleById_AndExpcetionOccurs_ThenThrowServiceException() throws Exception {
		Mockito.when(roleDao.getRoleById(Mockito.anyInt())).thenThrow(Exception.class);
		exception.expect(ServiceException.class);
		exception.expectMessage("Exception occured");
		
		userService.getRoleById(1);
		
		Mockito.verify(roleDao).getRoleById(Mockito.anyInt());
	}
}
