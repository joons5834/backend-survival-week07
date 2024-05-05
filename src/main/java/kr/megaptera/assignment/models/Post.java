package kr.megaptera.assignment.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.megaptera.assignment.dtos.PostDto;

@Entity
public class Post {
    @Id
    PostId id;
    String title;
    String author;
    String content;

    public Post(PostId postId, String title, String author, String content) {
        this.id = postId;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(PostDto postDto) {
        this.id = PostId.of(postDto.getId());
        this.title = postDto.getTitle();
        this.author = postDto.getAuthor();
        this.content = postDto.getContent();
    }

    protected Post() {

    }

    public PostId id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
