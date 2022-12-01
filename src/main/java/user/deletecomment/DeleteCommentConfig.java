package user.deletecomment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.dao.CommentDAO;

@Configuration
public class DeleteCommentConfig {
    @Bean
    public IDeleteCommentInput inputDeleteCommentConfig(){
        return new DeleteCommentInteractor(new CommentDAO());
    }
}
