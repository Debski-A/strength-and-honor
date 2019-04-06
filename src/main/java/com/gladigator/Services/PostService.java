package com.gladigator.Services;

import java.util.List;

import com.gladigator.Entities.Post;

public interface PostService {
	

	List<Post> getFivePostsAccordingToGivenPageNumber(Integer pageNumber);
	void saveOrUpdate(Post post);
	void deleteById(Integer id);

}
