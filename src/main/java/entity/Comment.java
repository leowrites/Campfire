package entity;

import java.util.ArrayList;
import java.util.Date;

public class Comment implements IUserPost {
    private String userId;
    private String content;
    private Date datePosted;
    private ArrayList<Integer> comments;
    private int parentId;
    private int id;

    public Comment(){
    }

    public Comment(String userId, String content) {
        this.userId = userId;
        this.content = content;
        this.datePosted = new Date();
        this.comments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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
