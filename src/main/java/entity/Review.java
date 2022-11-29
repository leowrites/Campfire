package entity;

import user.sort.ISortComparator;
import java.util.ArrayList;
import java.util.Date;

public class Review implements ISortComparator {
    private String id;
    private String userID;
    private String company;
    private Date datePosted;
    private int numLikes;
    private int numDislikes;
    private String content;
    private ArrayList<Comment> comments;
    private int rating;

    public Review() {
    }

    public Review(String reviewID, String userID, String content, int rating, String company, Date datePosted, int numLikes, int numDislikes,
                  ArrayList<Comment> comments) {
        this.id = reviewID;
        this.userID = userID;
        this.content = content;
        if (rating > 10){this.rating = 10;}
        else this.rating = Math.max(rating, 0);
        this.company = company;
        this.datePosted = datePosted;
        this.numLikes = numLikes;
        this.numDislikes = numDislikes;
        this.comments = comments;
    }

    public Review(String userId, String reviewContent, String corporateId) {
        this.userID = userId;
        this.content = reviewContent;
        this.company = corporateId;
        this.datePosted = new Date();
        this.numLikes = 0;
        this.numDislikes = 0;
        this.comments = new ArrayList<>();
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

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        if (rating > 10){this.rating = 10;}
        else this.rating = Math.max(rating, 0);
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

    public int compareToHelpful(Review otherReview){
        /*
        Note: this sorts them in an order that is the opposite of most default compareTo methods
        in order to have the highest value come up first in the sort.
         */
        return Integer.compare(otherReview.getNumLikes(), this.numLikes);
    }

    public int compareToHighestRating(Review otherReview){
        /*
        Note: this sorts them in an order that is the opposite of most default compareTo methods
        in order to have the highest value come up first in the sort.
         */
        return Integer.compare(otherReview.getRating(), this.rating);
    }

    public int compareToNewest(Review otherReview){
        /*
        Note: this sorts them in an order that is the opposite of most default compareTo methods
        in order to have the highest value come up first in the sort.
         */
        return otherReview.getDatePosted().compareTo(this.datePosted);
    }
}
