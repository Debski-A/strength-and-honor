package com.gladigator.Controllers;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView showHomePage(@RequestParam(required = false) Integer pageNumber, Locale locale) {
		List<Post> posts = postService.getFiveLatestPostsCountedFromGivenOffset(5);
		List<PostDto> postModels = utils.prepareLanguageSpecificPostsDtos(posts, locale);
		ModelAndView modelAndView = new ModelAndView("homepage");
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

	@PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveDivContentToDatabase(@RequestBody PostDto body, Locale locale) {
		Post preparePostEntity = utils.prepareLanguageSpecificPostEntity(body, locale);
		postService.saveOrUpdate(preparePostEntity);
		
		return "homepage";
	}

}
