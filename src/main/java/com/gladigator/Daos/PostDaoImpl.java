package com.gladigator.Daos;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gladigator.Entities.Post;
import com.gladigator.Exceptions.RepositoryException;

@Repository
public class PostDaoImpl implements PostDao {

	private static final Logger LOG = LoggerFactory.getLogger(PostDaoImpl.class);

	@Autowired
	protected SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	/**
	 * Offset liczony jest od konca agregacji, czyli np jak mamy post1, post2, post3
	 * to offset = 2 zwroci post2 i post 3, offset = 3 zwroci post1, post2 i post3 
	 * a offset=0 zwroci pusta liste
	 */
	public List<Post> getFiveLatestPostsCountedFromGivenOffset(Integer offset) {
		Integer start = calculateStartInterval(offset);
		TypedQuery<Post> query = getSession().createQuery("from Post", Post.class);
		query.setFirstResult(start);
		query.setMaxResults(5);
		List<Post> posts = query.getResultList();
		
		LOG.info("Got {} last entities starting from row {}", posts.size(), start);
		LOG.debug("Posts list: {}", posts);
		return posts;
	}

	private Integer calculateStartInterval(Integer offset) {
		String countQ = "select count (postId) from Post";
		TypedQuery<Long> countQuery = getSession().createQuery(countQ, Long.class);
		Integer countResults = countQuery.getSingleResult().intValue();
		int result = countResults - offset;
		if (result < 0) return 0;
		return result;
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
