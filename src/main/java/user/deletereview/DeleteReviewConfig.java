package user.deletereview;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteReviewConfig {

    @Bean
    public IDeleteReviewInput inputDeleteReviewConfig(){

        return new DeleteReviewInteractor(new DeleteReviewDataAccess());
    }
}
