package com.gladigator.unitTests.Services;

import com.gladigator.Daos.PostDao;
import com.gladigator.Entities.Post;
import com.gladigator.Services.PostService;
import com.gladigator.Services.PostServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PostServiceTest {

    private PostDao daoMock = Mockito.mock(PostDao.class);
    private PostService postService = new PostServiceImpl(daoMock);

    @Before
    public void setUp() {
        prepare12PostsForMockReturn();
        LocaleContextHolder.setLocale(Locale.forLanguageTag("pl-PL"));
    }

    private void prepare12PostsForMockReturn() {
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            posts.add(Post.builder().postId(i).language("pl-PL").build());
        }
        Locale pl = Locale.forLanguageTag("pl-PL");

        Mockito.when(daoMock.getAllPostsAccordingToLocale(pl)).thenReturn(posts);
    }

    @Test
    public void shouldReturnPostsFrom7To12ForPageNumber1() {
        //when
        List<Post> postsFrom7to12 = postService.getFivePostsAccordingToGivenPageNumber(1);

        // then
        assertThat(postsFrom7to12.size(), equalTo(5));
        assertTrue(postsFrom7to12.stream().allMatch(e -> {
            Integer id = e.getPostId();
            return id == 8 || id == 9 || id == 10 || id == 11 || id == 12;
        }));
    }

    @Test
    public void shouldReturnPostsFrom3To7ForPageNumber2() {
        //when
        List<Post> postsFrom3to7 = postService.getFivePostsAccordingToGivenPageNumber(2);

        // then
        assertThat(postsFrom3to7.size(), equalTo(5));
        assertTrue(postsFrom3to7.stream().allMatch(e -> {
            Integer id = e.getPostId();
            return id == 3 || id == 4 || id == 5 || id == 6 || id == 7;
        }));
    }

    @Test
    public void shouldReturnPostsFrom1To2ForPageNumber3() {
        //when
        List<Post> postsFrom1To2 = postService.getFivePostsAccordingToGivenPageNumber(3);

        // then
        assertThat(postsFrom1To2.size(), equalTo(2));
        assertTrue(postsFrom1To2.stream().allMatch(e -> {
            Integer id = e.getPostId();
            return id == 1 || id == 2;
        }));
    }
}
