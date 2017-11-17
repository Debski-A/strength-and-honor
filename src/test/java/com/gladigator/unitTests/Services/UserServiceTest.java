package com.gladigator.unitTests.Services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.gladigator.Daos.UserDao;
import com.gladigator.Entities.User;
import com.gladigator.Services.UserService;
import com.gladigator.Services.UserServiceImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService = new UserServiceImpl();
	
	@Mock
	private UserDao userDao;
	
	private User testUser;
	
	@Before
	public void before() {
		testUser = new User("adam", "adam1234", "adam@gmail.com", true);
	}
	
//	public User getUserById(Integer id);
//	public void saveOrUpdateUser(User user);
//	public void deleteUserById(Integer id);
//	public List<User> getAllUsers();
	
//	public User getUserById(Integer id);
	@Test
	public void whenGetUserById_ThenReturnTestUser() throws Exception {
		Mockito.when(userDao.getUserById(1)).thenReturn(testUser);
		assertThat(userService.getUserById(1), equalTo(testUser));
	}
}
