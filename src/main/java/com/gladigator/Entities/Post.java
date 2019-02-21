package com.gladigator.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "posts")
public class Post implements Translationable<PostTranslation> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_post")
	private Integer postId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@OnDelete(action =OnDeleteAction.CASCADE) //hibernate specific
	@JoinColumn(name="id_post")
	private List<PostTranslation> postTranslations;
	
	@Transient
	private String content;
	
	@Override
	public List<PostTranslation> getTranslations() {
		return postTranslations;
	}

	@Override
	public void setContent(String content) {
		this.content = content; 
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public void setPostTranslations(List<PostTranslation> postTranslations) {
		this.postTranslations = postTranslations;
	}

	public String getContent() {
		return content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((postId == null) ? 0 : postId.hashCode());
		result = prime * result + ((postTranslations == null) ? 0 : postTranslations.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (postId == null) {
			if (other.postId != null)
				return false;
		} else if (!postId.equals(other.postId))
			return false;
		if (postTranslations == null) {
			if (other.postTranslations != null)
				return false;
		} else if (!postTranslations.equals(other.postTranslations))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Post [postId=");
		builder.append(postId);
		builder.append(", postTranslations=");
		builder.append(postTranslations);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}

	
}
