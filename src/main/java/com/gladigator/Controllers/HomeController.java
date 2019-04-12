package com.gladigator.Controllers;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gladigator.Controllers.Utils.PostUtils;
import com.gladigator.Entities.Post;
import com.gladigator.Models.PostDto;
import com.gladigator.Services.PostService;

@Controller
public class HomeController {

	@Autowired
	private PostService postService;

	@GetMapping("/")
	public ModelAndView showHomePage(@RequestParam(required = false, defaultValue = "1", name = "pageNumber") String pageNumber, Locale locale) {
		List<PostDto> posts = postService.getFivePostsAccordingToGivenPageNumber(pageNumber);
		Integer numberOfPostsInSpecificLang = postService.countNumberOfLanguageSpecificPosts(locale);

		ModelAndView modelAndView = new ModelAndView("homepage");
		modelAndView.addObject("numberOfPosts", numberOfPostsInSpecificLang);
		modelAndView.addObject("posts", posts);
		
		return modelAndView;
	}

	@GetMapping("/weather")
	public String showWeatherPage() {

		return "weatherpage";
	}

	@GetMapping("/post")
	public String showPostPage(@RequestParam(required = false, name = "postId") String postId, Model model) {
		if (StringUtils.isNotEmpty(postId)) {
			Post post = postService.findById(Integer.valueOf(postId));
			PostDto postDto = PostDto.builder().postId(post.getPostId()).content(post.getTranslatedContent()).build();
			model.addAttribute("postToEdit" , postDto);
		}
		model.addAttribute("postInvoked", true);

		return "homepage";
	}

	@PostMapping(value = "/post")
	public @ResponseBody Object savePostToDatabase(@RequestBody PostDto postDto, Principal principal, Locale locale) {
		String authenticatedUser = principal.getName();
		postDto.setOwner(authenticatedUser);
		postService.saveOrUpdate(postDto);
		
		//You can't redirect from AJAX to different PAGE. You need to handle it via Script only. 
		return null;
	}

	@PutMapping("/post")
	public @ResponseBody Object editPost(@RequestBody PostDto body) {
		System.out.println("in editPost, body: " + body);

		return null;
	}
	
	@DeleteMapping(value = "/post")
	public @ResponseBody Object deletePost(@RequestParam("postId") String postId) {
		postService.deleteById(Integer.valueOf(postId));

		return null;
	}

}
