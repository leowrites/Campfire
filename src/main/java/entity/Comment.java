package entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="comments")
public class Comment implements IUserPost {

    @OneToOne
    private User user;

    @Column
    private String content;

    @Column
    private Date datePosted;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Column
    private UUID parentId;

    @Id
    @GeneratedValue
    private UUID id;

    public Comment(){
    }

    public Comment(String content) {
        this.content = content;
        this.datePosted = new Date();
        this.comments = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getParent() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatePosted() {
        return this.datePosted;
    }

    public void setDatePosted(Date date) {
        this.datePosted = date;
    }


    public List<Comment> getComments() {
        return this.comments;
    }

    @Override
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
