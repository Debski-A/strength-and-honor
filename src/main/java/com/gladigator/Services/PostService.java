package com.gladigator.Services;

import java.util.List;

import com.gladigator.Entities.Post;

public interface PostService {
	
	public List<Post> getFiveLatestPostsCountedFromGivenOffset(Integer offset);
	public void saveOrUpdate(Post post);
	public void deleteById(Integer id);

}
