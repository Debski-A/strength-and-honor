package com.gladigator.Daos;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gladigator.Entities.UserDetails;
import com.gladigator.Exceptions.RepositoryException;

@Repository
public class UserDetailsDaoImpl extends GenericDaoImpl<UserDetails> implements UserDetailsDao {
	
	public UserDetailsDaoImpl() {
		super(UserDetails.class);
	}

	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsDaoImpl.class);
	
	@SuppressWarnings("rawtypes")
	public void deleteUserDetailsById(Integer id) {
		LOG.info("UserDaoDetails.deleteUserDetailsById(Integer id) START");
		Query query = getSession().createQuery("delete from UserDetails where userId = :userId")
			.setParameter("userId", id);
		
		if (query.executeUpdate() == 0) throw new RepositoryException("Couldn't delete UserDetails with ID = " + id + ". No such Entity");
		LOG.info("Deleted UserDetails Entity from database");
		LOG.info("UserDaoDetails.deleteUserDetailsById(Integer id) END");
	}

}
