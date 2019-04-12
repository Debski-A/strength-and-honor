package com.gladigator.Daos;

import java.util.List;
import java.util.Locale;

import com.gladigator.Entities.Post;

public interface PostDao {

	public void saveOrUpdate(Post post);
	public void deleteById(Integer id);
    List<Post> getAllPostsAccordingToLocale(Locale locale);

    Post findById(Integer id);
}
