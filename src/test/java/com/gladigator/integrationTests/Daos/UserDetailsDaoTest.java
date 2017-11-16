package com.gladigator.integrationTests.Daos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hibernate.SessionFactory;
import org.hibernate.id.IdentifierGenerationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.UserDetailsDao;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;

@ActiveProfiles("test")
@ContextConfiguration(locations = { "classpath:com/gladigator/Configs/dao-context.xml",
		"classpath:com/gladigator/Configs/security-context.xml", "classpath:com/gladigator/Configs/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserDetailsDaoTest {

	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private SessionFactory sessionFactory;

	private UserDetails userDetails;

	@Before
	public void before() {
		User user = new User("Roger", "rogeiro", "roger@gmail.com", true);
		userDetails = new UserDetails.UserDetailsBuilder()
				.setAge(27)
				.setHeight(193)
				.setWeight(97)
				.setBmi(333)
				.setBmr(444)
				.build();
		userDetails.setUser(user);
	}

	// public void saveOrUpdateUserDetails(UserDetails user);
	// public UserDetails getUserDetailsById(Integer id);
	// public void deleteUserDetailsById(Integer id);

	@Test
	public void givenUserDetails_WhenSaveOrUpdateUserDetails_ThenUserDetailsArePersisted() {
		userDetailsDao.saveOrUpdateUserDetails(userDetails);
		sessionFactory.getCurrentSession().flush(); //flush aby zapelnic tabele danymi. Bez tego INSERT bedzie tylko w tabeli users. NIE WIEM CZEMU TAK JEST TODO
		sessionFactory.getCurrentSession().detach(userDetails); //detach aby "odlaczyc" userDetails od Persistence context. clear odlacza wszystkie encje bedace w kontekscie
		UserDetails userDetailsFromDB = userDetailsDao.getUserDetailsById(1); //BEZ DETACH usersDetails wciaz jest w cache i przez to nie jest wykonywany SELECT w bazie danych. getUserDetailsById po prostu przypisuje referencje z cache do zmiennej userDetailsFromDB
		assertThat(userDetailsFromDB, equalTo(userDetails));
	}
	
	@Test(expected = IdentifierGenerationException.class)
	public void givenUserDetailsWithUserFieldSettedToNull_WhenSaveOrUpdateDetails_ThenThrowException() {
		userDetails.setUser(null);
		userDetailsDao.saveOrUpdateUserDetails(userDetails);
	}
	
	@Test
	public void givenUserDetailsIsNull_WhenSaveOrUpdate_ThenThrowException() {
		userDetailsDao.saveOrUpdateUserDetails(null);
	}	
	
	

}
