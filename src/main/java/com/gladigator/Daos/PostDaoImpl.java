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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Locale;

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
		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery("delete from Post where postId = :postId").setParameter("postId", id);
		if (query.executeUpdate() == 0)
			throw new RepositoryException("Couldn't delete Post with ID = " + id + ". No such Entity");
		LOG.info("Deleted Post from database");
	}

	@Override
	public List<Post> getAllPostsAccordingToLocale(Locale locale) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Post> cQuery = cb.createQuery(Post.class);
		Root<Post> root = cQuery.from(Post.class);
		cQuery.select(root).where(cb.equal(root.get("language"), locale.toLanguageTag()));
		Query<Post> query = getSession().createQuery(cQuery);
		return query.getResultList();
	}

	@Override
	public Post findById(Integer id) {
		Post post = getSession().get(Post.class, id);
		LOG.info("Found post with id = {}", id);
		return post;
	}

}
