package com.gladigator.integrationTests.Daos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static org.hamcrest.Matchers.*;
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

@ContextConfiguration(locations = "classpath:com/gladigator/Configs/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PostDaoTest {

	@Autowired
	private PostDao dao;
	
	@Test
	public void shouldSaveDataToDatabase() throws Exception {
		//given
		Post newPost = Post.builder().language("pl-PL").translatedContent("Kolejny polski post").build();
		//when
		dao.saveOrUpdate(newPost);
		//then
		if (dao instanceof PostDaoImpl) {
			Session session = ((PostDaoImpl)dao).getSession();
			Post postFromDatabase = session.get(Post.class, 3); //2 bo jeden row byl juz dodany w import.sql
			assertThat(postFromDatabase.getTranslatedContent(), equalTo("Kolejny polski post"));
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
	
	@Test
	public void shouldGetTwoPosts() throws Exception {
		//given
		//post1 is inserted with import.sql
		Post post2 = new Post();
		dao.saveOrUpdate(post2);
		
		//when
		Integer offset = 5;
		List<Post> posts = dao.getFiveLatestPostsCountedFromGivenOffset(offset);
		HashSet<Post> postsSet = new HashSet<>(posts);
		
		//then
		assertThat(postsSet, hasSize(2));
	}
}
