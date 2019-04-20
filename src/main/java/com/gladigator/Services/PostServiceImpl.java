package com.gladigator.Services;

import java.time.LocalDate;
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
	public Integer countNumberOfPosts() {
		return postDao.findAll().size();
	}

	public PostServiceImpl(PostDao postDao, PostUtils utils) {
		this.postDao = postDao;
		this.utils = utils;
	}

	@Override
	public List<PostDto> getFivePostDtosAccordingToGivenPageNumber(String pageNumber, Locale currentLocale) {
		List<Post> allPosts = postDao.findAll();
		List<Post> fivePosts = getFivePostsAccordingToGivenPageNumber(allPosts, pageNumber);
		LOG.debug("Five posts according to page number {} = {}", pageNumber, fivePosts);
		List<PostDto> fivePostDtos = utils.prepareLanguageSpecificPostDtos(fivePosts, currentLocale);
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
	public void saveOrUpdate(PostDto postDto, Locale currentLocale) {
		if (postDto.getPostId() == null) {
			saveNewPost(postDto, currentLocale);
		} else {
			updatePost(postDto, currentLocale);
		}
	}

	private void saveNewPost(PostDto postDto, Locale currentLocale) {
		Post post = utils.prepareLanguageSpecificPostEntity(postDto, currentLocale);
		saveOrUpdate(post);
	}

	private void updatePost(PostDto postDto, Locale locale) {
		Post post = postDao.findById(postDto.getPostId());
		post.setLatestUpdate(LocalDate.parse(postDto.getLatestUpdate()));
		utils.updatePostTranslations(post, postDto.getContent(), locale);
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
