package com.gladigator.unitTests.Controllers;

import com.gladigator.Controllers.Utils.PostUtils;
import com.gladigator.Entities.Post;
import com.gladigator.Entities.PostTranslation;
import com.gladigator.Models.PostDto;
import com.gladigator.Services.TranslationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PostUtilsTest {

    private TranslationService translationService = Mockito.mock(TranslationService.class);
    private PostUtils utils = new PostUtils(translationService);

    private PostTranslation ptPL1;
    private PostTranslation ptEN1;
    private PostTranslation ptPL2;
    private PostTranslation ptEN2;
    private LocalDate date;

    @Before
    public void setUp() {
        ptPL1 = PostTranslation.builder().language("pl-PL").translatedContent("Post po polsku").build();
        ptEN1 = PostTranslation.builder().language("en-GB").translatedContent("Post in english").build();
        ptPL2 = PostTranslation.builder().language("pl-PL").translatedContent("Drugi post po polsku").build();
        ptEN2 = PostTranslation.builder().language("en-GB").translatedContent("Second post in english").build();
        date = LocalDate.now();
    }

    @Test
    public void shouldPrepareDtosWithPolishContent() {
        //given
        Post post = Post.builder().latestUpdate(date).owner("xyz").postTranslations(new ArrayList<>() {{add(ptPL1); add(ptEN1);}}).build();
        Post post2 = Post.builder().latestUpdate(date).owner("xyz").postTranslations(new ArrayList<>() {{add(ptPL2); add(ptEN2);}}).build();
        Locale locale = Locale.forLanguageTag("pl-PL");
        //when
        List<PostDto> postDtos = utils.prepareLanguageSpecificPostDtos(new ArrayList<>() {{add(post); add(post2);}}, locale);
        //then
        assertThat(postDtos.get(0).getContent(), equalTo("Post po polsku"));
        assertThat(postDtos.get(1).getContent(), equalTo("Drugi post po polsku"));
    }

    @Test
    public void shouldPrepareOneDtoWithPolishContent() {
        //given
        Post post = Post.builder().latestUpdate(date).owner("xyz").postTranslations(new ArrayList<>() {{add(ptPL1); add(ptEN1);}}).build();
        Post post2 = Post.builder().latestUpdate(date).owner("xyz").postTranslations(new ArrayList<>() {{add(ptEN2);}}).build();
        Locale locale = Locale.forLanguageTag("pl-PL");
        //when
        List<PostDto> postDtos = utils.prepareLanguageSpecificPostDtos(new ArrayList<>() {{add(post); add(post2);}}, locale);
        //then
        assertThat(postDtos, hasSize(1));
    }

    @Test
    public void shouldReturnEmptyList() {
        //given
        Post post = Post.builder().latestUpdate(date).owner("xyz").postTranslations(new ArrayList<>() {{add(ptEN1);}}).build();
        Post post2 = Post.builder().latestUpdate(date).owner("xyz").postTranslations(new ArrayList<>() {{add(ptEN2);}}).build();
        Locale locale = Locale.forLanguageTag("pl-PL");
        //when
        List<PostDto> postDtos = utils.prepareLanguageSpecificPostDtos(new ArrayList<>() {{add(post); add(post2);}}, locale);
        //then
        assertThat(postDtos, equalTo(Collections.emptyList()));
    }

    @Test
    public void shouldReturnLanguageSpecificPostBasedOnPostDto() {
        //given
        Locale locale = Locale.forLanguageTag("pl-PL");
        LocalDate date = LocalDate.now();
        Post post = Post.builder().postTranslations(new ArrayList<>() {{add(ptPL1); add(ptEN1);}}).postId(1).latestUpdate(date).owner("admin").build();
        PostDto expectedDto = PostDto.builder().content("Post po polsku").latestUpdate(date.toString()).owner("admin").postId(1).build();
        //when
        PostDto dto = utils.prepareLanguageSpecificPostDto(post, locale);
        //then
        assertThat(dto, equalTo(expectedDto));
    }

    @Test
    public void shouldReturnDtoWithoutContent() {
        //given
        Locale locale = Locale.forLanguageTag("pl-PL");
        LocalDate date = LocalDate.now();
        Post post = Post.builder().postTranslations(new ArrayList<>() {{add(ptEN1);}}).postId(1).latestUpdate(date).owner("admin").build();
        PostDto expectedDto = PostDto.builder().content(null).latestUpdate(date.toString()).owner("admin").postId(1).build();
        //when
        PostDto dto = utils.prepareLanguageSpecificPostDto(post, locale);
        //then
        assertThat(dto, equalTo(expectedDto));
    }

    @Test
    public void shouldReturnLanguageSpecificPostBasedOnPostDto2() {
        //given
        Locale locale = Locale.forLanguageTag("pl-PL");
        LocalDate date = LocalDate.now();
        Post post = Post.builder().postTranslations(new ArrayList<>() {{add(ptPL1);}}).postId(1).latestUpdate(date).owner("admin").build();
        PostDto expectedDto = PostDto.builder().content("Post po polsku").latestUpdate(date.toString()).owner("admin").postId(1).build();
        //when
        PostDto dto = utils.prepareLanguageSpecificPostDto(post, locale);
        //then
        assertThat(dto, equalTo(expectedDto));
    }

    @Test
    public void shouldReturnOppositeLocale() {
        //given
        Locale pl = Locale.forLanguageTag("pl-PL");
        Locale en = Locale.forLanguageTag("en-GB");
        //when then
        assertThat(utils.getOppositeLocale(pl), equalTo(en));
        assertThat(utils.getOppositeLocale(en), equalTo(pl));
    }

    @Test
    public void shouldReturnPostEntityWithTranslation(){
        //given
        Locale currentLocale = Locale.forLanguageTag("pl-PL");
        Locale oppositeLocale = utils.getOppositeLocale(currentLocale);
        LocalDate date = LocalDate.now();
        PostDto dto = PostDto.builder().content("Post po polsku").latestUpdate(date.toString()).build();
        Mockito.when(translationService.translate("Post po polsku", currentLocale.getLanguage(), oppositeLocale.getLanguage())).thenReturn("Post in english");
        //when
        Post post = utils.prepareLanguageSpecificPostEntity(dto, currentLocale);
        //then
        assertThat(post.getTranslations(), hasItem(hasProperty("translatedContent", is("Post in english"))));
    }

    @Test
    public void shouldSetPostTranslationsInPost() {
        //given
        Post post = Post.builder().postTranslations(new ArrayList<>() {{add(ptPL2);add(ptEN2);}}).build();
        String content = "Post po polsku";
        Locale currentLocale = Locale.forLanguageTag("pl-PL");
        Locale oppositeLocale = utils.getOppositeLocale(currentLocale);
        Mockito.when(translationService.translate("Post po polsku", currentLocale.getLanguage(), oppositeLocale.getLanguage())).thenReturn("Post in english");
        //when
        utils.updatePostTranslations(post, content, currentLocale);
        //then
        assertThat(post.getTranslations(), hasItems(hasProperty("translatedContent", is("Post in english")), hasProperty("translatedContent", is("Post po polsku"))));
    }
}
