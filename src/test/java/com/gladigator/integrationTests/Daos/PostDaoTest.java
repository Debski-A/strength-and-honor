package com.gladigator.integrationTests.Daos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.PostDao;
import com.gladigator.Daos.PostDaoImpl;
import com.gladigator.Entities.Post;
import com.gladigator.Entities.PostTranslation;

@ContextConfiguration(locations = "classpath:com/gladigator/Configs/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PostDaoTest {

	@Autowired
	private PostDao dao;
	
	@Test
	public void shouldSaveDataToDatabase() throws Exception {
		//given
		Post newPost = new Post();
		PostTranslation postTranslationPL= new PostTranslation();
		postTranslationPL.setTranslatedContent("Kolejny polski post");
		postTranslationPL.setLanguage("pl-PL");
		postTranslationPL.setIsDefault(false);
		PostTranslation postTranslationENG= new PostTranslation();
		postTranslationENG.setTranslatedContent("Another English post");
		postTranslationENG.setLanguage("en-UK");
		postTranslationENG.setIsDefault(true);
		newPost.setPostTranslations(Arrays.asList(postTranslationENG, postTranslationPL));
		//when
		dao.saveOrUpdate(newPost);
		//then
		if (dao instanceof PostDaoImpl) {
			Session session = ((PostDaoImpl)dao).getSession();
			Post postFromDatabase = session.get(Post.class, 2); //2 bo jeden row byl juz dodany w import.sql
			boolean predicateResult = postFromDatabase.getTranslations().stream().anyMatch(e -> e.getTranslatedContent().equals("Kolejny polski post"));
			assertTrue(predicateResult);
		}
	}
	
	@Test
	public void shouldDeletePostFromDatabase() throws Exception {
		//when
		dao.deleteById(1);
		//then
		if (dao instanceof PostDaoImpl) {
			Session session = ((PostDaoImpl)dao).getSession();
			Post postFromDatabase = session.get(Post.class, 1);
			assertNull(postFromDatabase);
		}
	}
	
	@Test
	public void shouldGetFiveLatestPostsCountedFromGivenOffset() throws Exception {
		//given
		//post1 is inserted with import.sql
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
		Post post7 = new Post();
		dao.saveOrUpdate(post7);
		
		//when
		Integer offset = 6;
		List<Post> posts = dao.getFiveLatestPostsCountedFromGivenOffset(offset);
		HashSet<Post> postsSet = new HashSet<>(posts);
		
		//then
		assertTrue(postsSet.containsAll(Arrays.asList(post2, post3, post4, post5, post6)));
		assertFalse(postsSet.contains(post7));
	}
}
