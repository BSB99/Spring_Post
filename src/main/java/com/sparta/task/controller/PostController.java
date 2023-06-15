package com.sparta.task.controller;

import com.sparta.task.dto.PostPasswordCheckRequestDto;
import com.sparta.task.dto.PostRequestDto;
import com.sparta.task.dto.PostResponseDto;
import com.sparta.task.entity.Post;
import com.sparta.task.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
    }

    @GetMapping("/post/{id}")
    public Optional<Post> getByIdPost(@PathVariable Long id) {
        return postService.getByIdPost(id);
    }

    @PutMapping("/post/{id}")
    public Optional<Post> updatePost(@PathVariable long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(id, postRequestDto);
    }

    @DeleteMapping("/post/{id}")
    public Map<String, Boolean> deletePost(@PathVariable long id, @RequestBody PostPasswordCheckRequestDto password) {
        return postService.deletePost(id, password);
    }
}
