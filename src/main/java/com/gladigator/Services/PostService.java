package com.gladigator.Services;

import java.util.List;
import java.util.Locale;

import com.gladigator.Entities.Post;
import com.gladigator.Models.PostDto;

public interface PostService {
	
	List<PostDto> getFivePostsAccordingToGivenPageNumber(String pageNumber);
	void saveOrUpdate(Post post);
	void saveOrUpdate(PostDto postDto);
	void deleteById(Integer id);
	Integer countNumberOfLanguageSpecificPosts(Locale locale);

    Post findById(Integer id);
}
