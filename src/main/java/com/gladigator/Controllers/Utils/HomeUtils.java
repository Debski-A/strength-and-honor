package com.gladigator.Controllers.Utils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gladigator.Entities.Post;
import com.gladigator.Models.PostDto;

@Component
public class HomeUtils {

	private static final Logger LOG = LoggerFactory.getLogger(HomeUtils.class);

	public Post prepareLanguageSpecificPostEntity(PostDto body, Locale locale) {
		Post post = Post.builder().postId(body.getPostId()).language(locale.toLanguageTag())
				.translatedContent(body.getContent()).latestUpdate(LocalDate.parse(body.getLatestUpdate()))
				.owner(body.getOwner()).build();
		LOG.debug("Prepared post entity = {}", post);
		return post;
	}

	public List<PostDto> prepareLanguageSpecificPostsDtos(List<Post> posts, Locale locale) {
		List<Post> languageSpecificPosts = posts.stream()
				.filter(post -> post.getLanguage().equals(locale.toLanguageTag())).collect(Collectors.toList());
		List<PostDto> postsDtos = languageSpecificPosts.stream()
				.map(post -> PostDto.builder().postId(post.getPostId()).content(post.getTranslatedContent())
						.latestUpdate(post.getLatestUpdate().toString()).owner(post.getOwner()).build())
				.collect(Collectors.toList());
		Collections.reverse(postsDtos);
		return postsDtos;
	}

}