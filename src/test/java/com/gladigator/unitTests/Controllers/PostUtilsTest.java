package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.gladigator.Controllers.Utils.PostUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.gladigator.Entities.Post;
import com.gladigator.Models.PostDto;

@RunWith(MockitoJUnitRunner.class)
public class PostUtilsTest {

	@InjectMocks
	private PostUtils postUtils;

	@Before
	public void before() {
		postUtils = new PostUtils();
	}

	@Test
	public void shouldPreparePostEntityWithEnglishContent() throws Exception {
		// given
		PostDto postDto = new PostDto("Some content");
		postDto.setLatestUpdate("2019-03-04");
		postDto.setOwner("Admin");
		Locale locale = Locale.UK;
		// when
		Post preparedPostEntity = postUtils.prepareLanguageSpecificPostEntity(postDto, locale);
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
		List<PostDto> preparedDtos = postUtils
				.preparePostsDtos(Arrays.asList(englishPost, englishPost2));
		PostDto expectedDto1 = PostDto.builder().postId(1).content("English post").latestUpdate("2019-03-04").build();
		PostDto expectedDto2 = PostDto.builder().postId(2).content("Another post").latestUpdate("2019-03-04").build();
		// then
		assertThat(preparedDtos, hasSize(2));
		assertThat(preparedDtos, contains(equalTo(expectedDto1), equalTo(expectedDto2)));
	}

	@Test
	public void translationAPITest() throws Exception {
		String text = "Witaj przystojny kolego";
		String langTo = "en";
		String langFrom = "pl";
		String urlStr = "https://script.google.com/macros/s/AKfycbyLc7Dh2LoT1vbCr5c7Fa-TKaDqEDnND7o_PGITHMUwEDFXJU4/exec" +
				"?q=" + URLEncoder.encode(text, "UTF-8") +
				"&target=" + langTo +
				"&source=" + langFrom;
		URL url = new URL(urlStr);
		StringBuilder response = new StringBuilder();
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
	}
}
