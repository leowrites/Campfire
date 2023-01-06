package entity;
import usecases.votehelpful.VoteDecision;
import jakarta.persistence.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


@Entity
@Table(name="reviews")
public class Review implements IUserPost{

    @OneToOne
    private User user;
    private Date datePosted;
    private int numLikes;
    private int numDislikes;
    private String content;

    @OneToMany
    private List<Comment> comments;
    private int rating;
    private UUID internshipId;
    @Id
    @GeneratedValue
    private UUID id;

    @Transient
    private HashMap<String, VoteDecision> votedUsers;

    public Review() {
    }

    public Review(String content, int rating) {
        this.content = content;
        this.datePosted = new Date();
        this.numLikes = 0;
        this.numDislikes = 0;
        this.comments = new ArrayList<>();
        if (rating > 5){this.rating = 5;}
        else this.rating = Math.max(rating, 0);
        this.votedUsers = new HashMap<>();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(UUID internshipId) {
        this.internshipId = internshipId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return this.content;
    }

    public int getRating() {
        return this.rating;
    }

    public Date getDatePosted() {
        return this.datePosted;
    }

    public void setDatePosted(Date newDate) {
        this.datePosted = newDate;
    }

    public int getNumLikes() {
        return this.numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumDislikes() {
        return this.numDislikes;
    }

    public void setNumDislikes(int numDislikes) {
        this.numDislikes = numDislikes;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public HashMap<String, VoteDecision> getVotedUsers() {
        return this.votedUsers;
    }

    public void setVotedUsers(HashMap<String, VoteDecision> votedUsers) {
        this.votedUsers = votedUsers;
    }
}
