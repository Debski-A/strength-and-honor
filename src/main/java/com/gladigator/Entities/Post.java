package com.gladigator.Entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "posts")
public class Post implements Translationable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_post")
	@Getter
	@Setter
	private Integer postId;

	@OneToMany(cascade=CascadeType.ALL, mappedBy = "post", orphanRemoval = true, fetch = FetchType.EAGER)
	@Setter
	private List<PostTranslation> postTranslations;

	@Transient
	@Getter
	private String content;

	@Column(name="latest_update")
	@Getter
	@Setter
	private LocalDate latestUpdate;

	@Column(name="owner")
	@Getter
	@Setter
	private String owner;

	@Override
	public List<PostTranslation> getTranslations() {
		return postTranslations;
	}

	@Override
	public void setTranslatedContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("postId", postId)
				.append("content", content)
				.append("latestUpdate", latestUpdate)
				.append("owner", owner)
				.toString();
	}
}
