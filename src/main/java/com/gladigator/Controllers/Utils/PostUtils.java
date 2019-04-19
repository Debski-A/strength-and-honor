package com.gladigator.Controllers.Utils;

import com.gladigator.Entities.Post;
import com.gladigator.Entities.PostTranslation;
import com.gladigator.Models.PostDto;
import com.gladigator.Services.TranslationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class PostUtils {

    private static final Logger LOG = LoggerFactory.getLogger(PostUtils.class);

    private TranslationService translationService;

    public PostUtils(TranslationService translationService) {
        this.translationService = translationService;
    }

    /**
     * Converts List of posts to List of language specific post Dtos
     * @param posts - posts with inner PostTranslations List -> one translation per every maintained language @link ApplicationLocaleResolver
     * @param currentLocale
     * @return language specific list of PostDtos (shaped for currenLocale)
     */
    public List<PostDto> prepareLanguageSpecificPostDtos(List<Post> posts, Locale currentLocale) {
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(prepareLanguageSpecificPostDto(post, currentLocale));
        }
        //TODO probably to delete
//        List<PostDto> postsDtos = posts.stream()
//                .map(post -> PostDto.builder()
//                        .postId(post.getPostId())
//                        .content(prepareContentForDto(post.getTranslations(), currentLocale))
//                        .latestUpdate(post.getLatestUpdate().toString())
//                        .owner(post.getOwner())
//                        .build()).collect(Collectors.toList());
        postDtos = removeDtosWithNullContent(postDtos);
        return postDtos;
    }

    public PostDto prepareLanguageSpecificPostDto(Post post, Locale currentLocale) {
        PostDto dto = PostDto.builder().postId(post.getPostId()).latestUpdate(post.getLatestUpdate().toString()).owner(post.getOwner()).build();
        dto.setContent(prepareContentForDto(post.getTranslations(), currentLocale));
        return dto;
    }

    private String prepareContentForDto(List<PostTranslation> translations, Locale locale) {
        List<PostTranslation> filteredTranslations = filterPostTranslationsByLocale(translations, locale);
        // this if - else prevents situation when post somehow will have only one translation.
        if (filteredTranslations.size() == 1) {
            return filteredTranslations.get(0).getTranslatedContent();
        } else {
            return null;
        }
    }

    private List<PostTranslation> filterPostTranslationsByLocale(List<PostTranslation> translations, Locale locale) {
        return translations.stream().filter(p -> locale.toLanguageTag().equals(p.getLanguage())).collect(Collectors.toList());
    }

    private List<PostDto> removeDtosWithNullContent(List<PostDto> postsDtos) {
        postsDtos = postsDtos.stream().filter(p -> p.getContent() != null).collect(Collectors.toList());
        return postsDtos;
    }

    public Post prepareLanguageSpecificPostEntity(PostDto postDto, Locale locale) {
        Post post = Post.builder()
                .postId(postDto.getPostId())
                .latestUpdate(LocalDate.parse(postDto.getLatestUpdate()))
                .owner(postDto.getOwner())
                .build();

        Locale oppositeLocale = getOppositeLocale(locale);
        String translatedContent = translationService.translate(postDto.getContent(), locale.getLanguage(), oppositeLocale.getLanguage());

        List<PostTranslation> translations = new ArrayList<>();
        translations.add(PostTranslation.builder().translatedContent(postDto.getContent()).language(locale.toLanguageTag()).post(post).build());
        translations.add(PostTranslation.builder().translatedContent(translatedContent).language(oppositeLocale.toLanguageTag()).post(post).build());

        post.setPostTranslations(translations);
        return post;
    }

    public Locale getOppositeLocale(Locale locale) {
        if (locale.toLanguageTag().equals("pl-PL")) {
            return Locale.forLanguageTag("en-GB");
        } else {
            return Locale.forLanguageTag("pl-PL");
        }
    }

    public void updatePostTranslations(Post post, String content, Locale locale) {
        Locale oppositeLocale = getOppositeLocale(locale);
        String translatedContent = translationService.translate(content, locale.getLanguage(), oppositeLocale.getLanguage());

        post.getTranslations().stream().forEach(pt -> {
            if (locale.toLanguageTag().equals(pt.getLanguage())) {
                pt.setTranslatedContent(content);
            } else if (oppositeLocale.toLanguageTag().equals(pt.getLanguage())) {
                pt.setTranslatedContent(translatedContent);
            }
        });
    }

}
