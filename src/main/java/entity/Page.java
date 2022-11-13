package entity;

public abstract class Page {
    // pageLabel is an identifier of a page
    // examples: "TD_CorporatePage", "Jason_ProfilePage"
    String pageLabel;

    // A Page instance is "owned" by one or more users.
    // For example, TD's CorporatePage instance is owned by a TD CorporateRep user,
    // Jason's ProfilePage instance is owned by a student user called Jason;
    // A HomePage instance is owned by one or more moderator users.
    User pageOwner;

    public Page(String pageName, User owner){
        this.pageLabel = pageName;
        this.pageOwner = owner;
    }

    public String getPageLabel(){return pageLabel;}
    public void setPageLabel(String newLabel){this.pageLabel = newLabel;}

    public User getPageOwner(){return pageOwner;}
    public void setPageOwner(User owner){this.pageOwner = owner;}
}
