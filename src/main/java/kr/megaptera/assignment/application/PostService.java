package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostDto::new)
                .toList();
    }

    public PostDto getPost(String id) {
        Post post = postRepository.findById(PostId.of(id)).orElseThrow();
        return new PostDto(post);
    }

    public void createPost(PostDto postDto) {
        postDto.setId(PostId.generate().toString());
        Post postToCreate = new Post(postDto);
        postRepository.save(postToCreate);

    }

    public void updatePost(String id, PostDto postDto) {
        Post postFound = postRepository.findById(PostId.of(id))
                .orElseThrow();
        postFound.setTitle(postDto.getTitle());
        postFound.setContent(postDto.getContent());
    }

    public void deletePost(String id) {
        postRepository.deleteById(PostId.of(id));
    }
}
