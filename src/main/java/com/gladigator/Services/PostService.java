package com.gladigator.Services;

import java.util.List;
import java.util.Locale;

import com.gladigator.Entities.Post;

public interface PostService {
	
	List<Post> getFivePostsAccordingToGivenPageNumber(Integer pageNumber);
	void saveOrUpdate(Post post);
	void deleteById(Integer id);
	public Integer countNumberOfLanguageSpecificPosts(Locale locale);

}
