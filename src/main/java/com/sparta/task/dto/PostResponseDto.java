package com.sparta.task.dto;

import com.sparta.task.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class PostResponseDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public PostResponseDto(Optional<Post> post) {
        this.id = post.get().getId();
        this.title = post.get().getTitle();
        this.author = post.get().getAuthor();
        this.content = post.get().getContent();
        this.createdAt = post.get().getCreatedAt();
        this.modifiedAt = post.get().getModifiedAt();
    }
}
