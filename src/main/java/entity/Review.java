package entity;

public class Review {

    private int id;

    private String body;

    public Review(int id, String text){
        this.id = id;
        this.body = text;
    }

    public Review(int id){
        this.id = id;
        this.body = "";
    }

    public int getid(){
        return this.id;
    }

    public String getbody(){
        return this.body;
    }

    public void changebody(String new_body){
        this.body = new_body;
    }
}
