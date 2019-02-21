package com.gladigator.integrationTests.Daos;


import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.RoleDao;
import com.gladigator.Exceptions.RepositoryException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;

@ContextConfiguration(locations = "classpath:com/gladigator/Configs/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RoleDaoTest {
	
	@Autowired
	private RoleDao roleDao;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void whenGetRoleById1_ThenReturnRoleUser() throws Exception {
		assertThat(roleDao.getRoleById(1).getRole(), equalTo("ROLE_USER"));
	}
	
	@Test
	public void whenGetRoleById2_ThenReturnRoleAdmin() throws Exception {
		assertThat(roleDao.getRoleById(2).getRole(), equalTo("ROLE_ADMIN"));
	}
	
	@Test
	public void whenGetRoleById_AndNoSuchId_ThenThrowRepositoryException() throws Exception {
		exception.expect(RepositoryException.class);
		exception.expectMessage("There is no Role with such id");
		roleDao.getRoleById(3);
	}
	
	@Test 
	public void whenGetRoleById_AndArgumentIsInvalid_ThenThrowRepositoryException() throws Exception {
		exception.expect(RepositoryException.class);
		exception.expectMessage("Argument is invalid. Role = " + null);
		roleDao.getRoleById(null);
	}
}
