package com.gladigator.integrationTests.Daos;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gladigator.Daos.UserDetailsDao;
import com.gladigator.Entities.UserDetails;

@ActiveProfiles("test")
@ContextConfiguration(locations = { "classpath:com/gladigator/Configs/dao-context.xml",
		"classpath:com/gladigator/Configs/security-context.xml", "classpath:com/gladigator/Configs/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailsTest {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	private SessionFactory sessionFactory;

	private UserDetails userDetails;

	@Before
	public void before() {
		sessionFactory.getCurrentSession()
				.createNativeQuery("ALTER TABLE users_details ALTER COLUMN id_user RESTART WITH 1").executeUpdate();
	}
	
//	public void saveOrUpdateUserDetails(UserDetails user);
//	public UserDetails getUserDetailsById(Integer id);
//	public void deleteUserDetailsById(Integer id);
	
	@Transactional
	@Test
	public void 

}
