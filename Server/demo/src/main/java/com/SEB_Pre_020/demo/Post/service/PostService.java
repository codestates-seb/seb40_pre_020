package com.SEB_Pre_020.demo.Post.service;

import com.SEB_Pre_020.demo.Post.entity.Post;
import com.SEB_Pre_020.demo.Post.repository.PostRepository;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /** 게시글(답글) 생성 */
    @Transactional
    public Post createPost(Post post) {
        int memberId = post.getMember().getId();

        if (!postRepository.existsById(post.getId())) {
            Post savePost = postRepository.save(post);
            return savePost;
        }
        return null;
    }

    /** 게시글(답글) 수정 */
    @Transactional
    public Post updatePost(Post post) {
        Post findPost = findVerifiedPost(post.getId());

        // 제목, 내용 뷰만 수정 가능
        findPost.setPostTitle(post.getPostTitle());
        findPost.setPostContent(post.getPostContent());
        findPost.setPostView(post.getPostView());

        return postRepository.save(findPost);
    }

    /** 특정 게시글(답글) 하나만 get */
    @Transactional(readOnly = true)
    public Post findPost(int postId) {
        return findVerifiedPost(postId);
    }

    /** 게시글의 모든 답변 get */
    @Transactional
    public Page<Post> findPostPosts(int postId, int page, int size) {
        List<Post> postList = postRepository.findByParentId(postId);

        Pageable pageable = PageRequest.of(page, size);
        PagedListHolder pagedListHolder = new PagedListHolder(postList);
        pagedListHolder.setPageSize(size);
        pagedListHolder.setPage(page);

        return new PageImpl<>(pagedListHolder.getPageList(), pageable, postList.size());
    }

    // 멤버 get 현재 미구현
    @Transactional
    public List<Post> findMemberPosts(int postId, int page, int size) {
        List<Post> postList = postRepository.findByParentId(postId);
        return postList;
    }

    /** 게시글 삭제 */
    @Transactional
    public void deletePost(int postId) {
        Post findPost = findVerifiedPost(postId);

        postRepository.delete(findPost);
    }

    /** id로 게시글(답글) get */
    @Transactional(readOnly = true)
    public Post findVerifiedPost(int postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        Post findPost = optionalPost.orElseThrow();     // Exception code 추가 필요
        return findPost;
    }


}
