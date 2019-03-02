package com.gladigator.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	public PostDto(String content) {
		this.content = content;
	}
	
	private String content;
	
	private Integer postId;

}
