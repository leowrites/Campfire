package user.deletereview;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.dao.InternshipDAO;
import service.dao.ReviewDAO;

@Configuration
public class DeleteReviewConfig {

    @Bean
    public IDeleteReviewInput inputDeleteReviewConfig(){

        return new DeleteReviewInteractor(new ReviewDAO(), new InternshipDAO());
    }
}
