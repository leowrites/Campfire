package user.comment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("service")
public class CommentConfig {

    @Bean
    public ICommentInputBoundary commentInput(ICommentDataAccess dataAccess) {
        return new CommentInteractor(dataAccess);
    }

    @Bean
    public ICommentDataAccess commentDataAccess() {
        return new CommentDataAccess();
    }
}