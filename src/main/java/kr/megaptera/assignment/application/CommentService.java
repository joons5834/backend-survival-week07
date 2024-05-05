package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> getAllComments(String postId) {
        return commentRepository.findAllByPostId(postId)
                .stream().map(CommentDto::new)
                .toList();
    }

    public Comment createAComment(String postId, CommentDto commentDto) {
        commentDto.setId(CommentId.generate());
        Comment newComment = new Comment(commentDto);
        newComment.setPostId(PostId.of(postId));
        return commentRepository.save(newComment);
    }

    public void updateAComment(String id, String postId, CommentDto commentDto) {
        Comment commentFound = commentRepository.findById(CommentId.of(id))
                .orElseThrow();
        commentFound.setContent(commentDto.getContent());
    }

    public void deleteAComment(String id, String postId) {
        commentRepository.deleteById(CommentId.of(id));
    }
}
