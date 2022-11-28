package user.postreview;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.*;

@Configuration
public class PostReviewConfig {
    @Bean
    public IPostReview createPostReviewInteractor(IReviewDAO reviewDAO, IInternshipDAO internshipDAO) {
        return new PostReview(reviewDAO, internshipDAO);
    }

    @Bean
    public IReviewDAO createPostReviewPostgresDAO() {
        return new ReviewDAO();
    }

    @Bean
    public IInternshipDAO createInternshipPostgresDAO() {
        return new InternshipDAO();
    }
}
