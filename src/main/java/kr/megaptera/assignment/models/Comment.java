package kr.megaptera.assignment.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.megaptera.assignment.dtos.CommentDto;

@Entity
public class Comment {
    @Id
    CommentId id;

    String postId;
    String author;
    String content;

    public Comment(CommentId id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public Comment(CommentDto commentDto) {
        this.id = CommentId.of(commentDto.getId());
        this.author = commentDto.getAuthor();
        this.content = commentDto.getContent();
    }

    protected Comment() {

    }

    public CommentId id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public void setPostId(PostId postId) {
        this.postId = postId.toString();
    }

    public void setContent(String content) {
        this.content = content;
    }
}
