package com.gladigator.Daos;

import java.util.List;
import java.util.Locale;

import com.gladigator.Entities.Post;

public interface PostDao {

	void saveOrUpdate(Post post);
	void deleteById(Integer id);
    List<Post> findAll();
    Post findById(Integer id);
}
