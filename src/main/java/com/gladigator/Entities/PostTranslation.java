package com.gladigator.Entities;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "post_translations")
public class PostTranslation implements Translation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_post_translation")
    private Integer postTranslationId;

    @Column(name="language")
    private String language;

    @Column(name="content")
    private String translatedContent;

    @ManyToOne
    @JoinColumn(name = "id_post")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Post post;

}
