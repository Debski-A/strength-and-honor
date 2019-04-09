package com.gladigator.Controllers;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gladigator.Controllers.Utils.HomeUtils;
import com.gladigator.Entities.Post;
import com.gladigator.Models.PostDto;
import com.gladigator.Services.PostService;

@Controller
public class HomeController {

	@Autowired
	private PostService postService;

	@Autowired
	private HomeUtils utils;

	@GetMapping("/")
	public ModelAndView showHomePage(@RequestParam(required = false, defaultValue = "1", name = "pageNumber") String pageNumber, Locale locale) {
		List<Post> posts = postService.getFivePostsAccordingToGivenPageNumber(pageNumber);
		List<PostDto> postModels = utils.preparePostsDtos(posts);

		Integer numberOfPostsInSpecificLang = postService.countNumberOfLanguageSpecificPosts(locale);

		ModelAndView modelAndView = new ModelAndView("homepage");
		modelAndView.addObject("numberOfPosts", numberOfPostsInSpecificLang);
		modelAndView.addObject("posts", postModels);
		
		return modelAndView;
	}

	@GetMapping("/weather")
	public String showWeatherPage() {

		return "weatherpage";
	}

	@GetMapping("/post")
	public String showPostPage(Model model) {
		model.addAttribute("postInvoked", true);

		return "homepage";
	}

	@PostMapping(value = "/post")
	public @ResponseBody PostDto savePostToDatabase(@RequestBody PostDto body, Principal principal, Locale locale) {
		String authenticatedUser = principal.getName();
		body.setOwner(authenticatedUser);
		Post preparePostEntity = utils.prepareLanguageSpecificPostEntity(body, locale);
		postService.saveOrUpdate(preparePostEntity);
		
		//You can't redirect from AJAX to different PAGE. You need to handle it via Script only. 
		return null;
	}
	
	@DeleteMapping(value = "/post")
	public @ResponseBody Object deletePost(@RequestParam("postId") String postId) {
		postService.deleteById(Integer.valueOf(postId));
		
		//j.w.
		return null;
	}

}
