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

    /**
     * Constructs a review
     * @param reviewID the id of the review
     * @param userID the id of the user who wrote the review
     * @param company the company of the internship that the review is about
     * @param content the content of the review
     * @param rating the rating of the review 1-5
     */
    public Review(String reviewID, String userID, String company, String content, int rating) {
        this.id = reviewID;
        this.userID = userID;
        this.company = company;
        this.content = content;
        this.datePosted = new Date();
        this.numLikes = 0;
        this.numDislikes = 0;
        this.comments = new ArrayList<>();
        if (rating > 10){this.rating = 10;}
        else if (rating < 0){this.rating = 0;}
        else{this.rating = rating;}
    }

    /**
     * Contructs a review including the number of likes and dislikes, the date posted,
     * and a list of comments on the review
     * @param reviewID is the ID of the review
     * @param userID the ID of the user
     * @param content the content of the review
     * @param rating the rating of the review from 1-5
     * @param company the name of the company of the internship that the review is about
     * @param datePosted the date the review was posted
     * @param numLikes the number of likes the review has received
     * @param numDislikes the number of dislikes the review has received
     * @param comments a list of the comments about the review
     */
    public Review(String reviewID, String userID, String content, int rating, String company, Date datePosted, int numLikes, int numDislikes,
                  ArrayList<Comment> comments) {
        this.id = reviewID;
        this.userID = userID;
        this.content = content;
        if (rating > 10){this.rating = 10;}
        else if (rating < 0){this.rating = 0;}
        else{this.rating = rating;}
        this.company = company;
        this.datePosted = datePosted;
        this.numLikes = numLikes;
        this.numDislikes = numDislikes;
        this.comments = comments;
    }

    /**
     * returns the ID of the review
     * @return the ID of the review
     */
    public String getid() {
        return this.id;
    }

    public void setid(String newID) {
        this.id = newID;
    }

    /**
     * returns the ID of the user who wrote the ID
     * @return the ID of the user who wrote the ID
     */
    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String newID) {
        this.userID = newID;
    }

    /**
     * return the name of the company of the internship that the review is about
     * @return the name of the company of the internship that the review is about
     */
    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * return the content of the review
     * @return the content of the review
     */
    public String getContent() {
        return this.content;
    }

    public void setContent(String newContent) {
        this.content = newContent;
    }

    /**
     * return the rating of the review 1-5
     * @return the rating of the review 1-5
     */
    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        if (rating > 10){this.rating = 10;}
        else if (rating < 0){this.rating = 0;}
        else{this.rating = rating;}
    }

    /**
     * return the date that the review was posted
     * @return the date that the review was posted
     */
    public Date getDatePosted() {
        return this.datePosted;
    }

    public void setDatePosted(Date newDate) {
        this.datePosted = newDate;
    }

    /**
     * return the number of likes on the review
     * @return the number of likes on the review
     */
    public int getNumLikes() {
        return this.numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    /**
     * return the number of dislikes on the review
     * @return the number of dislikes on the review
     */
    public int getNumDislikes() {
        return this.numDislikes;
    }

    public void setNumDislikes(int numDislikes) {
        this.numDislikes = numDislikes;
    }

    /**
     * return the list of comments on the review
     * @return the list of comments on the review
     */
    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public void setComments(ArrayList<Comment> newComments) {
        this.comments = newComments;
    }

    /**
     * returns the number of likes in descending order
     * @param otherReview is the review who's number of likes are being compared to this reviews number of likes
     * @return the number of likes on the reviews in descending order
     */
    public int compareToHelpful(Review otherReview){
        /*
        Note: this sorts them in an order that is the opposite of most default compareTo methods
        in order to have the highest value come up first in the sort.
         */
        return Integer.compare(otherReview.getNumLikes(), this.numLikes);
    }

    /**
     * returns the ratings of the reviews in descending order
     * @param otherReview is the review who's rating is being compared to this reviews rating
     * @return the ratings of the reviews in descending order
     */
    public int compareToHighestRating(Review otherReview){
        /*
        Note: this sorts them in an order that is the opposite of most default compareTo methods
        in order to have the highest value come up first in the sort.
         */
        return Integer.compare(otherReview.getRating(), this.rating);
    }
    /**
     * returns the ratings in order of date posted
     * @param otherReview is the review who's date posted is being compared to this reviews date posted
     * @return the ratings in order of date posted
     */
    public int compareToNewest(Review otherReview){
        /*
        Note: this sorts them in an order that is the opposite of most default compareTo methods
        in order to have the highest value come up first in the sort.
         */
        return otherReview.getDatePosted().compareTo(this.datePosted);
    }
}
