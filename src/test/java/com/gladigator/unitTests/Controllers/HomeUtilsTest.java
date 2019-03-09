package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.gladigator.Controllers.Utils.HomeUtils;
import com.gladigator.Entities.Post;
import com.gladigator.Models.PostDto;
@RunWith(MockitoJUnitRunner.class)
public class HomeUtilsTest {

	@InjectMocks
	private HomeUtils homeUtils;
	
	@Before
	public void before() {
		homeUtils = new HomeUtils();
	}
	
	@Test
	public void shouldPreparePostEntityWithEnglishContent() throws Exception {
		//given
		PostDto postDto = new PostDto("Some content");
		Locale locale = Locale.UK;
		//when	
		Post preparedPostEntity = homeUtils.prepareLanguageSpecificPostEntity(postDto, locale);
		//then
		Post expectedPostEntity = Post.builder().translatedContent("Some content").language(Locale.UK.toLanguageTag()).build();
		assertThat(preparedPostEntity, is(equalTo(expectedPostEntity)));
	
	}
	
	@Test
	public void shouldPreparePostDtosOnlyForProvidedLocale() throws Exception {
		//given
		Locale providedLocale = Locale.UK;
		Post englishPost = Post.builder().postId(1).language("en-GB").translatedContent("English post").build();
		Post englishPost2 = Post.builder().postId(2).language("en-GB").translatedContent("Another post").build();
		Post polishPost = Post.builder().postId(3).language("pl-PL").translatedContent("Post po polsku").build();
		//when
		List<PostDto> preparedDtos = homeUtils.prepareLanguageSpecificPostsDtos(Arrays.asList(englishPost, englishPost2, polishPost), providedLocale);
		PostDto expectedDto1 = PostDto.builder().postId(1).content("English post").build();
		PostDto expectedDto2 = PostDto.builder().postId(2).content("Another post").build();
		//then
		assertThat(preparedDtos, hasSize(2));
		assertThat(preparedDtos, contains(equalTo(expectedDto2), equalTo(expectedDto1)));
	}
}
