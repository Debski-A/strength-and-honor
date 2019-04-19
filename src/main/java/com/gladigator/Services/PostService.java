package com.gladigator.Services;

import java.util.List;
import java.util.Locale;

import com.gladigator.Entities.Post;
import com.gladigator.Models.PostDto;

public interface PostService {

    Integer countNumberOfPosts();
	void saveOrUpdate(Post post);
    List<PostDto> getFivePostDtosAccordingToGivenPageNumber(String pageNumber, Locale currentLocale);
    void saveOrUpdate(PostDto postDto, Locale locale);
	void deleteById(Integer id);
    Post findById(Integer id);
}
