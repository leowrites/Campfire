package user.deletecomment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteCommentConfig {
    @Bean
    public IDeleteCommentInput inputDeleteCommentConfig(){
        return new DeleteCommentInteractor(new DeleteCommentDataAccess());
    }
}
