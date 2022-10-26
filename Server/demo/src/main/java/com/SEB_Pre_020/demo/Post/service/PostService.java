package com.SEB_Pre_020.demo.Post.service;

import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.Post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post createPost(Post post) {
//        get memberid
        if (postRepository.existsById(post.getId())) {
            Post savePost = postRepository.save(post);
            return savePost;
        }
        return null;
    }

    @Transactional
    public Post updatePost(Post post) {
        Post findPost = findVerifiedPost(post.getId());
        findPost.setPostTitle(post.getPostTitle());
        findPost.setPostContent(post.getPostContent());
        findPost.setPostView(post.getPostView());
        return postRepository.save(findPost);
    }

    @Transactional(readOnly = true)
    public Post findPost(int postId) {
        return findVerifiedPost(postId);
    }

    // 게시글의 모든 답변
//    @Transactional
//    public Page<Post> findPostPosts(int postId, int page, int size) {
//
//    }

    @Transactional(readOnly = true)
    public Post findVerifiedPost(int postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        Post findPost = optionalPost.orElseThrow();     // Exception code 추가 필요
        return findPost;
    }

}
