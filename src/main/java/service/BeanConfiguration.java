package service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.dao.*;
import user.acceptconnect.AcceptConnectionInteractor;
import user.acceptconnect.IAcceptConnectionInput;
import user.comment.CommentInteractor;
import user.comment.ICommentInputBoundary;
import user.deletecomment.DeleteCommentInteractor;
import user.deletecomment.IDeleteCommentInput;
import user.deletereview.DeleteReviewInteractor;
import user.deletereview.IDeleteReviewInput;
import user.postreview.IPostReview;
import user.postreview.PostReview;
import user.requestconnect.IRequestConnectionInput;
import user.requestconnect.RequestConnectionInteractor;
import user.signup.SignUpInputBoundary;
import user.signup.SignUpInteractor;
import user.votehelpful.HelpfulInteractor;
import user.votehelpful.IHelpfulInputBoundary;

@Configuration
public class BeanConfiguration {

    @Bean
    public ICommentDAO postgresCommentDAO() {
        return new CommentDAO();
    }

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

    @Bean
    public ICommentInputBoundary commentInput(IReviewDAO reviewDAO, ICommentDAO commentDAO) {
        return new CommentInteractor(reviewDAO, commentDAO);
    }

    @Bean
    public IRequestConnectionInput connectionInput(IUserDAO dataAccess) {
        return new RequestConnectionInteractor(dataAccess);
    }

    @Bean
    public SignUpInputBoundary SignUpInput(IUserDAO dataAccess){
        return new SignUpInteractor(dataAccess);
    }

    @Bean
    public IAcceptConnectionInput acceptConnectionInput(IUserDAO dataAccess) {
        return new AcceptConnectionInteractor(dataAccess);
    }

    @Bean
    public IHelpfulInputBoundary helpfulInput(IReviewDAO reviewDAO) {
        return new HelpfulInteractor(reviewDAO);
    }

    @Bean
    public IDeleteCommentInput inputDeleteCommentConfig(ICommentDAO commentDAO, IReviewDAO reviewDAO, IUserDAO userDAO){
        return new DeleteCommentInteractor(reviewDAO, commentDAO, userDAO);
    }

    @Bean
    public IDeleteReviewInput inputDeleteReviewConfig(IReviewDAO reviewDAO, IInternshipDAO internshipDAO){
        return new DeleteReviewInteractor(reviewDAO, internshipDAO);
    }
}
