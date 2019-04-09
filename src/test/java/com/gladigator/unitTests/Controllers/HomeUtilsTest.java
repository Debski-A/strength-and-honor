package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
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
		// given
		PostDto postDto = new PostDto("Some content");
		postDto.setLatestUpdate("2019-03-04");
		postDto.setOwner("Admin");
		Locale locale = Locale.UK;
		// when
		Post preparedPostEntity = homeUtils.prepareLanguageSpecificPostEntity(postDto, locale);
		// then
		Post expectedPostEntity = Post.builder().translatedContent("Some content").language(Locale.UK.toLanguageTag())
				.latestUpdate(LocalDate.parse("2019-03-04")).owner("Admin").build();
		assertThat(preparedPostEntity, is(equalTo(expectedPostEntity)));

	}

	@Test
	public void shouldPreparePostDtos() throws Exception {
		// given
		Post englishPost = Post.builder().postId(1).language("en-GB").translatedContent("English post")
				.latestUpdate(LocalDate.parse("2019-03-04")).build();
		Post englishPost2 = Post.builder().postId(2).language("en-GB").translatedContent("Another post")
				.latestUpdate(LocalDate.parse("2019-03-04")).build();
		// when
		List<PostDto> preparedDtos = homeUtils
				.preparePostsDtos(Arrays.asList(englishPost, englishPost2));
		PostDto expectedDto1 = PostDto.builder().postId(1).content("English post").latestUpdate("2019-03-04").build();
		PostDto expectedDto2 = PostDto.builder().postId(2).content("Another post").latestUpdate("2019-03-04").build();
		// then
		assertThat(preparedDtos, hasSize(2));
		assertThat(preparedDtos, contains(equalTo(expectedDto1), equalTo(expectedDto2)));
	}
}
