package com.gladigator.Controllers;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import com.gladigator.Controllers.Utils.PostUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gladigator.Entities.Post;
import com.gladigator.Models.PostDto;
import com.gladigator.Services.PostService;

@Controller
public class HomeController {

	@Autowired
	private PostService postService;

	@Autowired
	private PostUtils utils;

	@GetMapping("/")
	public ModelAndView showHomePage(@RequestParam(required = false, defaultValue = "1", name = "pageNumber") String pageNumber, Locale locale) {
		// 5 posts per page
		List<PostDto> posts = postService.getFivePostDtosAccordingToGivenPageNumber(pageNumber, locale);
		// numberOfPosts is used in UI for grouping posts (5 per page) and page navigation logic
		Integer numberOfPosts = postService.countNumberOfPosts();

		ModelAndView modelAndView = new ModelAndView("homepage");
		modelAndView.addObject("numberOfPosts", numberOfPosts);
		modelAndView.addObject("posts", posts);
		
		return modelAndView;
	}

	@GetMapping("/weather")
	public String showWeatherPage() {

		return "weatherpage";
	}

	@GetMapping("/post")
	public String showPostPage(@RequestParam(required = false, name = "postId") String postId, Model model, Locale locale) {
		if (isEditPostRequest(postId)) {
			Post post = postService.findById(Integer.valueOf(postId));
			PostDto postDto = utils.prepareLanguageSpecificPostDto(post, locale);
			model.addAttribute("postToEdit" , postDto);
		}
		model.addAttribute("postInvoked", true);

		return "homepage";
	}

	private boolean isEditPostRequest(@RequestParam(required = false, name = "postId") String postId) {
		return StringUtils.isNotEmpty(postId);
	}

	@PostMapping(value = "/post")
	public @ResponseBody Object savePostToDatabase(@RequestBody PostDto postDto, Principal principal, Locale locale) {
		String authenticatedUser = principal.getName();
		postDto.setOwner(authenticatedUser);
		postService.saveOrUpdate(postDto, locale);
		
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
