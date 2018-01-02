package com.gladigator.integrationTests.Daos;

import org.hibernate.SessionFactory;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.GenericDao;
import com.gladigator.Exceptions.RepositoryException;

@ActiveProfiles("test")
@ContextConfiguration(locations = "classpath:com/gladigator/Configs/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class GenericDaoTestUtils<T> {
	
	@Autowired
	protected SessionFactory sessionFactory;

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	protected void whenGetAll_AndAnyExceptionOccurs_ThenThrowRepositoryException(GenericDao<T> dao) throws Exception {
		exception.expect(RepositoryException.class);
		exception.expectMessage("An Exception occurred");
		sessionFactory.close();
		
		dao.getAll();
	}
	
	protected T whenFindById_ThenReturnEntity(GenericDao<T> dao, Integer id) {
		return dao.findById(id);
	}
	
	protected void whenFindById_AndExceptionOccurrs_ThenThrowRepositoryException(GenericDao<T> dao, Integer id) throws Exception{
		exception.expect(RepositoryException.class);
		exception.expectMessage("An Exception occurred");
		
		dao.findById(id);
	}
	
	protected void whenFindById_AndBodyTypeIsNull_ThenThrowRepositoryException(GenericDao<T> dao, Integer id, Class<T> entityClass) throws Exception{
		exception.expect(RepositoryException.class);
		exception.expectMessage("No " + entityClass.getSimpleName() + " entity with such id");
		
		dao.findById(id);
	}

}
