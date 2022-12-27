package entity;
import java.util.List;

public interface IUserPost {
    List<Comment> getComments();

    void setComments(List<Comment> comments);
}
