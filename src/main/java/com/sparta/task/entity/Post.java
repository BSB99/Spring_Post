package com.sparta.task.entity;

import com.sparta.task.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "post") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "content", nullable = false, length = 500)
    private String content;
    @Column(name = "password", nullable = false)
    private String password;

    public Post(PostRequestDto post) {
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
        this.password = post.getPassword();
    }

    public void update(PostRequestDto post) {
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
        this.password = post.getPassword();
    }
}