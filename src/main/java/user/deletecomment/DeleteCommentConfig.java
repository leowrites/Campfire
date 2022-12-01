package user.deletecomment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.dao.CommentDAO;
import service.dao.ReviewDAO;

@Configuration
public class DeleteCommentConfig {
    @Bean
    public IDeleteCommentInput inputDeleteCommentConfig(){
        return new DeleteCommentInteractor(new ReviewDAO(), new CommentDAO());
    }
}
