package com.gladigator.Services;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.gladigator.Daos.PostDao;
import com.gladigator.Entities.Post;

@Transactional
@Service
public class PostServiceImpl implements PostService {
	
	private PostDao postDao;
	
	@Override
	public Integer countNumberOfLanguageSpecificPosts(Locale locale) {
		return postDao.getAllPostsAccordingToLocale(LocaleContextHolder.getLocale()).size();
	}

	public PostServiceImpl(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
	public List<Post> getFivePostsAccordingToGivenPageNumber(String pageNumber) {
		// get all posts for current locale LocaleContextHolder.getLocale();
		List<Post> allPosts = postDao.getAllPostsAccordingToLocale(LocaleContextHolder.getLocale());
		// get five posts according to pageNumber
		List<Post> fivePosts = getFivePostsAccordingToGivenPageNumber(allPosts, pageNumber);
		return fivePosts;
	}

	private List<Post> getFivePostsAccordingToGivenPageNumber(List<Post> allPosts, String pageNumber) {
		// reverse to get posts from youngest to oldest
		Collections.reverse(allPosts);

		int pageNumberInt = Integer.valueOf(pageNumber);
		int bottomIndex = pageNumberInt * 5 - 5;
		int topIndex = pageNumberInt * 5;
		if (topIndex > allPosts.size()) {
			topIndex = allPosts.size();
		}

		return allPosts.subList(bottomIndex, topIndex);
	}

	@Override
	public void saveOrUpdate(Post post) {
		postDao.saveOrUpdate(post);
	}

	@Override
	public void deleteById(Integer id) {
		postDao.deleteById(id);
	}

}
