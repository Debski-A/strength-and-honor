package com.gladigator.integrationTests.Daos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;
import java.util.UUID;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.UserDetailsDao;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Exceptions.RepositoryException;

@ContextConfiguration(locations = "classpath:com/gladigator/Configs/test-dao-context.xml" )
@RunWith(SpringRunner.class)
@Transactional
public class UserDetailsDaoTest {

	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	private UserDetails userDetails;

	@Before
	public void before() {
		sessionFactory.getCurrentSession().createNativeQuery("ALTER TABLE users ALTER COLUMN id_user RESTART WITH 1").executeUpdate(); //RESETUJE ID AUTOINCREMENT
		String token = UUID.randomUUID().toString();
		User user = new User("Roger", "rogeiro", "roger@gmail.com", token, true);
		userDetails = new UserDetails.UserDetailsBuilder()
				.setAge(27)
				.setHeight(193)
				.setWeight(97)
				.setBmi(333D)
				.setBmr(444D)
				.build();
		userDetails.setUser(user); // musze dodac zaleznosc od User poniewaz ID w UserDetails pobierane jest z pola User. Jesli ustawie id recznie to saveOrUpdate bedzie zawsze wywolywac update
	}

	// public void saveOrUpdateUserDetails(UserDetails user);
	@Test
	public void givenUserDetails_WhenSaveOrUpdateUserDetails_ThenUserDetailsArePersisted() throws Exception {
		userDetailsDao.saveOrUpdate(userDetails);
		sessionFactory.getCurrentSession().flush(); //flush aby zapelnic tabele danymi. Bez tego INSERT bedzie tylko w tabeli users. 
		sessionFactory.getCurrentSession().detach(userDetails); //detach aby "odlaczyc" userDetails od Persistence context. clear odlacza wszystkie encje bedace w kontekscie
		UserDetails userDetailsFromDB = userDetailsDao.findById(1); //BEZ DETACH usersDetails wciaz jest w cache i przez to nie jest wykonywany SELECT w bazie danych. getUserDetailsById po prostu przypisuje referencje z cache do zmiennej userDetailsFromDB
		assertThat(userDetailsFromDB, equalTo(userDetails));
	}
	
	@Test
	public void givenUserDetailsWithUserFieldSettedToNull_WhenSaveOrUpdateDetails_ThenThrowRepositoryException() throws Exception {
		userDetails.setUser(null);
		exception.expect(RepositoryException.class);
		exception.expectMessage("An Exception occurred");
		userDetailsDao.saveOrUpdate(userDetails);
	}
	
	@Test
	public void givenUserDetailsIsNull_WhenSaveOrUpdate_ThenThrowRepositoryException() throws Exception {
		exception.expect(RepositoryException.class);
		exception.expectMessage("An Exception occurred");
		userDetailsDao.saveOrUpdate(null);
	}	
	
	@Test
	public void givenUserDetailsWithId_WhenSaveOrUpdate_ThenUserDetailsAreUpdated() throws Exception {
		userDetailsDao.saveOrUpdate(userDetails);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().detach(userDetails);
		userDetails.setAge(99);
		userDetailsDao.saveOrUpdate(userDetails);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().detach(userDetails);
		assertThat(userDetailsDao.findById(1).getAge(), equalTo(99));
	}
	
	
	// public UserDetails getUserDetailsById(Integer id);
	@Test
	public void givenEmptyDB_WhenGetUserDetailsById_ThenThrowRepositoryException() throws Exception {
		Integer id = 1;
		exception.expect(RepositoryException.class);
		exception.expectMessage("No UserDetails entity with such id");
		userDetailsDao.findById(id);
	}
	
	@Test
	public void givenIdIsNull_getUserDetailsById_ThenThrowRepositoryException() throws Exception {
		Integer id = null;
		exception.expect(RepositoryException.class);
		exception.expectMessage("An Exception occurred");
		userDetailsDao.findById(id);
	}
	
	
	// public void deleteUserDetailsById(Integer id);
	@Test
	public void givenUserDetailsWithId1_WhenDeleteUserDetailsById_ThenUserDetailsWithId1AreDeleted() throws Exception {
		userDetailsDao.saveOrUpdate(userDetails);
		sessionFactory.getCurrentSession().flush();
		userDetailsDao.deleteUserDetailsById(1);
		@SuppressWarnings("unchecked")
		List<UserDetails> allUserDetailsFromDB = sessionFactory.getCurrentSession().createQuery("from UserDetails").list();
		assertThat(allUserDetailsFromDB, hasSize(0));
	}
	
	@Test
	public void whenDeleteUserDetailsById_AndNoSuchUserDetails_ThenThrowRepositoryException() throws Exception {
		Integer id = 1;
		exception.expect(RepositoryException.class);
		exception.expectMessage("Couldn't delete UserDetails with ID = " + id + ". No such Entity");
		userDetailsDao.deleteUserDetailsById(id);
	}
	
//	@Test
//	public void whenGetAllBodyTypes_ThenReturnListOfBodyTypes() throws Exception {
//		BodyType ectomorphic = new BodyType();
//		ectomorphic.setBodyTypeId(1);
//		ectomorphic.setBodyTypeType("ectomorphic");
//		BodyType mesomorphic = new BodyType();
//		mesomorphic.setBodyTypeId(2);
//		mesomorphic.setBodyTypeType("mesomorphic");
//		BodyType endomorphic = new BodyType();
//		endomorphic.setBodyTypeId(3);
//		endomorphic.setBodyTypeType("endomorphic");
//		
//		assertThat(userDetailsDao.getAllBodyTypes(), equalTo(Arrays.asList(ectomorphic, mesomorphic, endomorphic)));
//	}
//	
//	@DirtiesContext
//	@Test
//	public void whenGetAllBodyTypes_AndAnyExceptionOccurs_ThenThrowRepositoryException() throws Exception {
//		exception.expect(RepositoryException.class);
//		exception.expectMessage("An Exception occurred");
//		sessionFactory.close();
//		
//		userDetailsDao.getAllBodyTypes();
//	}
	
	

}
