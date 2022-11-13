package entity;

public class Review {

    private String id;

    private String body;

    public Review(String id, String text){
        this.id = id;
        this.body = text;
    }

    public Review(String id){
        this.id = id;
        this.body = "";
    }

    public String getid(){
        return this.id;
    }

    public String getbody(){
        return this.body;
    }

    public void changebody(String new_body){
        this.body = new_body;
    }
}
