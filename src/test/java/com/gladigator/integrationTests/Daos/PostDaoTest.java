package com.gladigator.integrationTests.Daos;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.PostDao;
import com.gladigator.Daos.PostDaoImpl;
import com.gladigator.Entities.Post;

@ContextConfiguration(locations = "classpath:com/gladigator/Configs/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PostDaoTest {

	@Autowired
	private PostDao dao;

	@Before
	public void before() {
		((PostDaoImpl) dao).getSession().createNativeQuery("ALTER TABLE posts ALTER COLUMN id_post RESTART WITH 1")
				.executeUpdate();
	}

	@Test
	public void shouldSaveDataToDatabase() throws Exception {
		// given
		Post newPost = Post.builder().language("pl-PL").translatedContent("Kolejny polski post").build();
		// when
		dao.saveOrUpdate(newPost);
		// then
		if (dao instanceof PostDaoImpl) {
			Session session = ((PostDaoImpl) dao).getSession();
			Post postFromDatabase = session.get(Post.class, 1);
			assertThat(postFromDatabase.getTranslatedContent(), equalTo("Kolejny polski post"));
		}
	}

	@Test
	public void shouldDeletePostFromDatabase() throws Exception {
		//given
		Post newPost = new Post();
		dao.saveOrUpdate(newPost);
		// when
		dao.deleteById(1);
		// then
		if (dao instanceof PostDaoImpl) {
			Session session = ((PostDaoImpl) dao).getSession();
			session.flush();
			session.detach(newPost);
			Post postFromDatabase = session.get(Post.class, 1);
			assertNull(postFromDatabase);
		}
	}

	@Test
	public void shouldGetFiveLatestPostsCountedFromGivenOffset() throws Exception {
		// given
		Post post1 = new Post();
		dao.saveOrUpdate(post1);
		Post post2 = new Post();
		dao.saveOrUpdate(post2);
		Post post3 = new Post();
		dao.saveOrUpdate(post3);
		Post post4 = new Post();
		dao.saveOrUpdate(post4);
		Post post5 = new Post();
		dao.saveOrUpdate(post5);
		Post post6 = new Post();
		dao.saveOrUpdate(post6);

		// when
		Integer offset = 6;
		Integer numberOfPosts = 6;
		List<Post> posts = dao.getFiveLatestPostsCountedFromGivenOffset(offset, numberOfPosts);

		HashSet<Post> postsSet = new HashSet<>(posts);

		// then
		assertTrue(postsSet.containsAll(Arrays.asList(post1, post2, post3, post4, post5)));
		assertFalse(postsSet.contains(post6));
	}

	@Test
	public void shouldGetTwoPosts() throws Exception {
		// given
		Post post1 = new Post();
		Post post2 = new Post();
		dao.saveOrUpdate(post1);
		dao.saveOrUpdate(post2);

		// when
		Integer offset = 5;
		Integer numberOfPosts = 2;
		List<Post> posts = dao.getFiveLatestPostsCountedFromGivenOffset(offset, numberOfPosts);
		HashSet<Post> postsSet = new HashSet<>(posts);
		// then
		assertThat(postsSet, hasSize(2));
	}
	
	@Test
	public void countNumberOfPostsShouldReturn5() throws Exception {
		//given
		for (int i = 0; i < 5; i++) {
			dao.saveOrUpdate(new Post());
		}
		//when
		Integer result = dao.countNumberOfPosts();
		//then
		assertThat(result, equalTo(5));
	}
}
