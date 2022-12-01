package user.deletereview;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.dao.IInternshipDAO;
import service.dao.IReviewDAO;

@Configuration
public class DeleteReviewConfig {

    @Bean
    public IDeleteReviewInput inputDeleteReviewConfig(IReviewDAO reviewDAO, IInternshipDAO internshipDAO){

        return new DeleteReviewInteractor(reviewDAO, internshipDAO);
    }
}
