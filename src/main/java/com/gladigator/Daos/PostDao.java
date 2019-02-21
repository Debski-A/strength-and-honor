package com.gladigator.Daos;

import java.util.List;

import com.gladigator.Entities.Post;

public interface PostDao {

	public List<Post> getFiveLatestPostsCountedFromGivenOffset(Integer offset);
	public void saveOrUpdate(Post post);
	public void deleteById(Integer id);
}
