package com.gladigator.unitTests.Services;

import com.gladigator.Controllers.Utils.PostUtils;
import com.gladigator.Daos.PostDao;
import com.gladigator.Entities.Post;
import com.gladigator.Entities.PostTranslation;
import com.gladigator.Models.PostDto;
import com.gladigator.Services.PostService;
import com.gladigator.Services.PostServiceImpl;
import com.gladigator.Services.TranslationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PostServiceTest {

    private PostDao daoMock = Mockito.mock(PostDao.class);
    private TranslationService translationMock  = Mockito.mock(TranslationService.class);
    private PostService postService = new PostServiceImpl(daoMock, new PostUtils(translationMock));
    private PostTranslation ptPL1;
    private PostTranslation ptEN1;
    private Locale locale;

    @Before
    public void setUp() {
        ptPL1 = PostTranslation.builder().language("pl-PL").translatedContent("Post po polsku").build();
        ptEN1 = PostTranslation.builder().language("en-GB").translatedContent("Post in english").build();
        prepare12PostsForMockReturn();
        //LocaleContextHolder.setLocale(Locale.forLanguageTag("pl-PL"));
        locale = Locale.forLanguageTag("pl-PL");
    }

    private void prepare12PostsForMockReturn() {
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            posts.add(Post.builder().postId(i).postTranslations(new ArrayList<>() {{add(ptPL1); add(ptEN1);}}).latestUpdate(LocalDate.now()).build());
        }
        Mockito.when(daoMock.findAll()).thenReturn(posts);
    }

    @Test
    public void shouldReturnPostsFrom7To12ForPageNumber1() {
        //when
        List<PostDto> postsFrom7to12 = postService.getFivePostDtosAccordingToGivenPageNumber("1", locale);

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
        List<PostDto> postsFrom3to7 = postService.getFivePostDtosAccordingToGivenPageNumber("2", locale);

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
        List<PostDto> postsFrom1To2 = postService.getFivePostDtosAccordingToGivenPageNumber("3", locale);

        // then
        assertThat(postsFrom1To2.size(), equalTo(2));
        assertTrue(postsFrom1To2.stream().allMatch(e -> {
            Integer id = e.getPostId();
            return id == 1 || id == 2;
        }));
    }
}
