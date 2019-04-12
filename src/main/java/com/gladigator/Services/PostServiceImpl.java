package com.gladigator.Services;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import com.gladigator.Controllers.Utils.PostUtils;
import com.gladigator.Models.PostDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.gladigator.Daos.PostDao;
import com.gladigator.Entities.Post;

@Transactional
@Service
public class PostServiceImpl implements PostService {

	private static final Logger LOG = LoggerFactory.getLogger(PostServiceImpl.class);
	
	private PostDao postDao;
	private PostUtils utils;
	
	@Override
	public Integer countNumberOfLanguageSpecificPosts(Locale locale) {
		return postDao.getAllPostsAccordingToLocale(LocaleContextHolder.getLocale()).size();
	}

	public PostServiceImpl(PostDao postDao, PostUtils utils) {
		this.postDao = postDao;
		this.utils = utils;
	}

	@Override
	public List<PostDto> getFivePostsAccordingToGivenPageNumber(String pageNumber) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		List<Post> allPosts = postDao.getAllPostsAccordingToLocale(currentLocale);
		LOG.debug("All post for {} locale = {}", currentLocale.toLanguageTag(), allPosts);
		List<Post> fivePosts = getFivePostsAccordingToGivenPageNumber(allPosts, pageNumber);
		LOG.debug("Five posts according to page number {} = {}", pageNumber, fivePosts);
		List<PostDto> fivePostDtos = utils.preparePostsDtos(fivePosts);
		return fivePostDtos;
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
	public void saveOrUpdate(PostDto postDto) {
		Post post = utils.prepareLanguageSpecificPostEntity(postDto, LocaleContextHolder.getLocale());
		saveOrUpdate(post);
	}

	@Override
	public void saveOrUpdate(Post post) {
		postDao.saveOrUpdate(post);
	}

	@Override
	public Post findById(Integer id) {
		return postDao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		postDao.deleteById(id);
	}

}
