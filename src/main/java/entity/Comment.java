package entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Construct a comment, each comment stores an id, the userID, the content of the comment, and the date posted
 */
public class Comment {
    private String id;
    private String userID;
    private String content;
    private Date datePosted;
    private ArrayList<Comment> comments;

    public Comment(){
    }

    /**
     * Constructing a comment using the following parameters
     * @param id of the comment
     * @param userID is the id of the user writing the comment
     * @param content is the content of the comment
     * @param comments a list of comments
     */
    public Comment(String id, String userID, String content, ArrayList<Comment> comments) {
        this.id = id;
        this.userID = userID;
        this.content = content;
        this.datePosted = new Date();
        this.comments = comments;
    }

    public Comment(String id, String userID, String content, Date datePosted) {
        this.id = id;
        this.userID = userID;
        this.content = content;
        this.datePosted = datePosted;
    }
    /**
     * returns the id of the comment
     * @return the id of the comment
     */
    public String getid(){
        return this.id;
    }

    /**
     * returns the ID of the user who wrote the comment
     * @return the ID of the user
     */
    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String newID) {
        this.userID = newID;
    }

    /**
     * returns the content of the comment
     * @return the content of the comment
     */
    public String getContent() {
        return this.content;
    }

    public void setContent(String newContent) {
        this.content = newContent;
    }

    /**
     * returns the date the comment was posted
     * @return the date the comment was posted
     */
    public Date getDatePosted() {
        return this.datePosted;
    }

    public void setDatePosted(Date newDate) {
        this.datePosted = newDate;
    }

    /**
     * returns the list of comments
     * @return the list of comments
     */
    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public void setComments(ArrayList<Comment> newComments) {
        this.comments = newComments;
    }

}
