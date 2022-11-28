package entity;

import java.util.ArrayList;
import java.util.Date;

public class Comment {
    private String id;
    private String userId;
    private String content;
    private Date datePosted;
    private ArrayList<Comment> comments;

    public Comment(){
    }

    public Comment(String id, String userID, String content, ArrayList<Comment> comments) {
        this.id = id;
        this.userId = userID;
        this.content = content;
        this.datePosted = new Date();
        this.comments = comments;
    }

    public Comment(String id, String userID, String content, Date datePosted) {
        this.id = id;
        this.userId = userID;
        this.content = content;
        this.datePosted = datePosted;
    }

    public String getID(){
        return this.id;
    }

    public void setID(String newID) {
        this.id = newID;
    }
    public String getUserId() {
        return this.userId;
    }

    public void setUserID(String newID) {
        this.userId = newID;
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


    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public void setComments(ArrayList<Comment> newComments) {
        this.comments = newComments;
    }

}
