package com.sparta.task.service;

import com.sparta.task.dto.PostPasswordCheckRequestDto;
import com.sparta.task.dto.PostRequestDto;
import com.sparta.task.dto.PostResponseDto;
import com.sparta.task.entity.Post;
import com.sparta.task.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);

        Post savePost = postRepository.save(post);

        PostResponseDto postResponseDto = new PostResponseDto(savePost);

        return postResponseDto;
    }

    @Transactional
    public Optional<Post> updatePost(long id, PostRequestDto postRequestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);

        if (post.getPassword().equals(postRequestDto.getPassword())) {
            // memo 내용 수정
            post.update(postRequestDto);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        };
        return getByIdPost(id);
    }

    public Optional<Post> getByIdPost(Long id) {
        Optional<Post> getPost = postRepository.findById(id);
        return getPost;
    }

    public Map<String, Boolean> deletePost(long id, PostPasswordCheckRequestDto passwordDto) {
        Post post = findPost(id);
        Map<String, Boolean> result = new HashMap<>();

        if (post.getPassword().equals(passwordDto.getPassword())) {
            postRepository.delete(post);

            result.put("success", true);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return result;
    }

    private Post findPost(long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}
