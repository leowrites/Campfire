package entity;

public abstract class Page {
    String pageLabel;
    User pageOwner;

    /**
     * @param pageLabel is an identifier of a page
     * examples: "TD_CorporatePage", "Jason_ProfilePage"
     *
     * @param owner: A Page instance is "owned" by one or more users.
     * For example, TD's CorporatePage instance is owned by a TD CorporateRep user,
     * Jason's ProfilePage instance is owned by a student user called Jason;
     * A HomePage instance is owned by one or more moderator users.
     */

    public Page(String pageLabel, User owner){
        this.pageLabel = pageLabel;
        this.pageOwner = owner;
    }

    public String getPageLabel(){return pageLabel;}
    public void setPageLabel(String newLabel){this.pageLabel = newLabel;}

    public User getPageOwner(){return pageOwner;}
    public void setPageOwner(User owner){this.pageOwner = owner;}
}
