package com.gladigator.Services;

import java.util.List;

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
	public Integer countNumberOfPosts() {
		return postDao.countNumberOfPosts();
	}

	@Override
	public List<Post> getFiveLatestPostsCountedFromGivenOffset(Integer offset, Integer numberOfPosts) {
		return postDao.getFiveLatestPostsCountedFromGivenOffset(offset, numberOfPosts);
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
