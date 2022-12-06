package service;

import org.hibernate.event.spi.PostCollectionRecreateEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import service.dao.*;
import user.acceptconnect.AcceptConnectionInteractor;
import user.acceptconnect.IAcceptConnectionInput;
import user.comment.CommentFactory;
import user.comment.CommentInteractor;
import user.comment.ICommentInputBoundary;
import user.createcorporate.CorporateFactory;
import user.createcorporate.CorporateGenerateInteractor;
import user.createcorporate.ICorporateGenerateInput;
import user.deletecomment.DeleteCommentInteractor;
import user.deletecomment.IDeleteCommentInput;
import user.deletereview.DeleteReviewInteractor;
import user.deletereview.IDeleteReviewInput;
import user.postreview.IPostReview;
import user.postreview.PostReview;
import user.postreview.PostReviewFactory;
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
    public IPostReview createPostReviewInteractor(IReviewDAO reviewDAO, IInternshipDAO internshipDAO, PostReviewFactory reviewFactory) {
        return new PostReview(reviewDAO, internshipDAO, reviewFactory);
    }

    @Bean
    public PostReviewFactory createPostReviewFactory() {
        return new PostReviewFactory();
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
    public ICommentInputBoundary commentInput(IReviewDAO reviewDAO, ICommentDAO commentDAO, CommentFactory commentFactory) {
        return new CommentInteractor(reviewDAO, commentDAO, commentFactory);
    }

    @Bean
    public CommentFactory createCommentFactory() {
        return new CommentFactory();
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
    public IDeleteReviewInput inputDeleteReviewConfig(IReviewDAO reviewDAO, IInternshipDAO internshipDAO, IUserDAO userDAO){
        return new DeleteReviewInteractor(reviewDAO, internshipDAO, userDAO);
    }

    @Bean
    public ICorporateDAO postgresCorporateDAO() {
        return new CorporateDAO();
    }

    @Bean
    public ICorporateGenerateInput createCorporateInput(ICorporateDAO corporateDAO, IUserDAO userDAO, CorporateFactory corporateFactory){
        return new CorporateGenerateInteractor(corporateDAO, userDAO, corporateFactory);
    }

    @Bean CorporateFactory createCorporateFactory() {
        return new CorporateFactory();
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }
}
