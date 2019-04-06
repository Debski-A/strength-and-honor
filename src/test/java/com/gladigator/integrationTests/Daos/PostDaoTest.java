package com.gladigator.integrationTests.Daos;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.*;

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
	public void shouldReturnAllPolishPosts() {
		//given
		List<Post> postsToSave = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			postsToSave.add(Post.builder().language("pl-PL").build());
			postsToSave.add(Post.builder().language("en-GB").build());
		}
		Collections.shuffle(postsToSave);
		for (Post post : postsToSave) {
			dao.saveOrUpdate(post);
		}
		Locale pl = Locale.forLanguageTag("pl-PL");

		//when
		List<Post> allPolishPosts = dao.getAllPostsAccordingToLocale(pl);

		//then
		assertThat(allPolishPosts.size(), equalTo(5));
		assertTrue(allPolishPosts.stream().allMatch(e -> "pl-PL".equals(e.getLanguage())));
	}


}
