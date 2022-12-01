package user.deletecomment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;

@Configuration
public class DeleteCommentConfig {
    @Bean
    public IDeleteCommentInput inputDeleteCommentConfig(
            ICommentDAO commentDAO, IReviewDAO reviewDAO
    ){
        return new DeleteCommentInteractor(reviewDAO, commentDAO);
    }
}
