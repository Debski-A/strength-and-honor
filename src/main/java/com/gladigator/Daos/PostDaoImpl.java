package com.gladigator.Daos;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gladigator.Entities.Post;
import com.gladigator.Exceptions.RepositoryException;

@Repository
public class PostDaoImpl implements PostDao {

	private static final Logger LOG = LogManager.getLogger(PostDaoImpl.class);

	@Autowired
	protected SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Post> getFiveLatestPostsCountedFromGivenOffset(Integer offset) {
		Integer start = calculateStartInterval(offset);
		TypedQuery<Post> query = getSession().createQuery("from Post", Post.class);
		query.setFirstResult(start);
		query.setMaxResults(5);
		List<Post> posts = query.getResultList();
		
		LOG.info("Got {} last entities starting from row {}", posts.size(), start);
		return posts;
	}

	private Integer calculateStartInterval(Integer offset) {
		String countQ = "select count (postId) from Post";
		TypedQuery<Long> countQuery = getSession().createQuery(countQ, Long.class);
		Integer countResults = countQuery.getSingleResult().intValue();
		return countResults - offset;
	}

	@Override
	public void saveOrUpdate(Post post) {
		try {
			getSession().saveOrUpdate(post);
		} catch (Exception ex) {
			throw new RepositoryException("An Exception occurred", ex);
		}
		LOG.info("{} entity has been saved", Post.class.getName());
	}

	@Override
	public void deleteById(Integer id) {
		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery("delete from Post where postId = :postId").setParameter("postId", id);
		if (query.executeUpdate() == 0)
			throw new RepositoryException("Couldn't delete Post with ID = " + id + ". No such Entity");
		LOG.info("Deleted Post from database");
	}

}
