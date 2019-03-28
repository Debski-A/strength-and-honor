package com.gladigator.Services;

import java.util.List;
import java.util.Locale;

import com.gladigator.Entities.Post;

public interface PostService {
	
	public List<Post> getFiveLatestLanguageSpecificPostsCountedFromGivenOffset(Integer offset, Locale locale);
	public void saveOrUpdate(Post post);
	public void deleteById(Integer id);
	public Integer countNumberOfLanguageSpecificPosts(Locale locale);

}
