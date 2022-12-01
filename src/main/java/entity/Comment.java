package entity;

import java.util.ArrayList;
import java.util.Date;

public class Comment implements IUserPost {
    private String id; // tim - remove once you have fixed
    private String userId;
    private String content;
    private Date datePosted;
    private ArrayList<Integer> comments;

    public Comment(){
    }

    public Comment(String id, String userId, String content, ArrayList<Integer> comments) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.datePosted = new Date();
        this.comments = comments;
    }

    public Comment(String userId, String content) {
        this.userId = userId;
        this.content = content;
        this.datePosted = new Date();
        this.comments = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String newId) {
        this.userId = newId;
    }

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


    public ArrayList<Integer> getComments() {
        return this.comments;
    }

    public void setComments(ArrayList<Integer> comments) {
        this.comments = comments;
    }

}
