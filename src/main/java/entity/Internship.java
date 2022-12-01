package entity;

import java.util.ArrayList;

public class Internship {
    private ArrayList<Integer> reviews;

    public Internship(ArrayList<Integer> reviews){
        this.reviews = reviews;
    }

    public Internship(){
    }

    public ArrayList<Integer> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Integer> reviews) {
        this.reviews = reviews;
    }
}
