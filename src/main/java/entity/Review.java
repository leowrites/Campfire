package entity;

import user.sort.ISortComparator;
import java.util.ArrayList;
import java.util.Date;

public class Review implements ISortComparator, IUserPost{
    private String userId;
    private Date datePosted;
    private int numLikes;
    private int numDislikes;
    private String content;
    private ArrayList<Integer> comments;
    private int rating;

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
    }

    /*
    public Review(String userId, String content, int rating) {
        this.userId = userId;
        this.content = content;
        this.datePosted = new Date();
        this.numLikes = 0;
        this.numDislikes = 0;
        this.comments = new ArrayList<Integer>();
        if (rating > 10){this.rating = 10;}
        else this.rating = Math.max(rating, 0);
    }
    */

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

    public ArrayList<Integer> getComments() {
        return this.comments;
    }

    public void setComments(ArrayList<Integer> comments) {
        this.comments = comments;
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
