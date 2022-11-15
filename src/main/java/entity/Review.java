package entity;

import java.util.ArrayList;
import java.util.Date;

public class Review {
    private String id;
    private String userID;
    private String company;
    private Date datePosted;
    private int numLikes;
    private int numDislikes;
    private String content;
    private ArrayList<Comment> comments;

    public Review() {
    }

    public Review(String reviewID, String userID, String company, String content) {
        this.id = reviewID;
        this.userID = userID;
        this.company = company;
        this.content = content;
        this.datePosted = new Date();
        this.numLikes = 0;
        this.numDislikes = 0;
        this.comments = new ArrayList<>();
    }

    public Review(String reviewID, String userID, String content, String company, Date datePosted, int numLikes, int numDislikes,
                  ArrayList<Comment> comments) {
        this.id = reviewID;
        this.userID = userID;
        this.content = content;
        this.company = company;
        this.datePosted = datePosted;
        this.numLikes = numLikes;
        this.numDislikes = numDislikes;
        this.comments = comments;
    }

    public String getid() {
        return this.id;
    }

    public void setid(String newID) {
        this.id = newID;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String newID) {
        this.userID = newID;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String newContent) {
        this.content = newContent;
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

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public void setComments(ArrayList<Comment> newComments) {
        this.comments = newComments;
    }
}
