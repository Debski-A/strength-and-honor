package com.gladigator.Services;

import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gladigator.Daos.PostDao;
import com.gladigator.Entities.Post;

@Transactional
@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDao postDao;
	
	@Override
	public Integer countNumberOfLanguageSpecificPosts(Locale locale) {
		return postDao.countNumberOfLanguageSpecificPosts(locale);
	}

	@Override
	public List<Post> getFiveLatestLanguageSpecificPostsCountedFromGivenOffset(Integer offset, Locale locale) {
		return postDao.getFiveLatestLanguageSpecificPostsCountedFromGivenOffset(offset, locale);
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
