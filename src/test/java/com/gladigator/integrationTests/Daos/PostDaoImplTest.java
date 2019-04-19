package com.gladigator.integrationTests.Daos;

import com.gladigator.Daos.PostDao;
import com.gladigator.Entities.Post;
import com.gladigator.Entities.PostTranslation;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.Matchers.*;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:com/gladigator/Configs/test-dao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PostDaoImplTest {

    @Autowired
    private PostDao dao;

    @Autowired
    private SessionFactory sessionFactory;

    private List<PostTranslation> translations;
    private Post post;

    @Before
    public void setUp() {
        sessionFactory.getCurrentSession().createNativeQuery("ALTER TABLE posts ALTER COLUMN id_post RESTART WITH 1")
                .executeUpdate();
        post = Post.builder()
                .build();
        translations = List.of(PostTranslation.builder()
                .language("pl-PL")
                .translatedContent("post po polsku")
                .post(post)
                .build(),
                PostTranslation.builder()
                        .language("en-GB")
                        .translatedContent("post in english")
                        .post(post)
                        .build());
        post.setPostTranslations(translations);
    }

    @Test
    public void shouldSaveSpecificPostAndThenFindThisPost() {
        //when
        dao.saveOrUpdate(post);
        //then
        assertThat(dao.findById(1), equalTo(post));
    }

    @Test
    public void shouldDeletePostAndThenFindNull() {
        //given
        dao.saveOrUpdate(post);
        sessionFactory.getCurrentSession().detach(post);
        //when
        dao.deleteById(1);
        //then
        assertThat(dao.findById(1), equalTo(null));
    }

    @Test
    public void shouldFind2Posts() {
        //given
        Post post2 = Post.builder().postTranslations(translations).build();
        dao.saveOrUpdate(post);
        dao.saveOrUpdate(post2);
        //when
        List<Post> posts = dao.findAll();
        //then
        assertThat(posts, hasSize(2));
    }

}