package com.gladigator.Daos;

import com.gladigator.Entities.Post;
import com.gladigator.Exceptions.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDaoImpl implements PostDao {

	private static final Logger LOG = LoggerFactory.getLogger(PostDaoImpl.class);

	@Autowired
	protected SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
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
		Query query2 = getSession().createQuery("delete from PostTranslation where post.postId = :postId").setParameter("postId", id);
		Query query = getSession().createQuery("delete from Post where postId = :postId").setParameter("postId", id);
		if (query2.executeUpdate() == 0 || query.executeUpdate() == 0)
			throw new RepositoryException("Couldn't delete Post with ID = " + id + ".");
		LOG.info("Deleted Post from database");
	}

	@Override
	public List<Post> findAll() {
		return getSession().createQuery("from Post").list();
	}

	@Override
	public Post findById(Integer id) {
		Post post = getSession().get(Post.class, id);
		LOG.info("Found post with id = {}", id);
		return post;
	}

}
