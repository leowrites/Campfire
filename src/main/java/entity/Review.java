package entity;
import usecases.votehelpful.VoteDecision;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Review implements IUserPost{
    private String userId;
    private Date datePosted;
    private int numLikes;
    private int numDislikes;
    private String content;
    private ArrayList<Integer> comments;
    private int rating;
    private int internshipId;
    private int id;
    private HashMap<String, VoteDecision> votedUsers;

    public Review() {
    }

    public Review(String userId, String content, int rating) {
        this.userId = userId;
        this.content = content;
        this.datePosted = new Date();
        this.numLikes = 0;
        this.numDislikes = 0;
        this.comments = new ArrayList<>();
        if (rating > 10){this.rating = 10;}
        else this.rating = Math.max(rating, 0);
        this.votedUsers = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(int internshipId) {
        this.internshipId = internshipId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public ArrayList<Integer> getComments() {
        return this.comments;
    }

    public void setComments(ArrayList<Integer> comments) {
        this.comments = comments;
    }

    public HashMap<String, VoteDecision> getVotedUsers() {
        return this.votedUsers;
    }

    public void setVotedUsers(HashMap<String, VoteDecision> votedUsers) {
        this.votedUsers = votedUsers;
    }
}
