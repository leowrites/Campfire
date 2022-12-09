package service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import service.dao.*;
import usecases.acceptconnect.AcceptConnectionInteractor;
import usecases.acceptconnect.IAcceptConnectionInput;
import usecases.comment.CommentFactory;
import usecases.comment.CommentInteractor;
import usecases.comment.ICommentInputBoundary;
import usecases.createcorporate.CorporateFactory;
import usecases.createcorporate.CorporateGenerateInteractor;
import usecases.createcorporate.ICorporateGenerateInput;
import usecases.deletecomment.DeleteCommentInteractor;
import usecases.deletecomment.IDeleteCommentInput;
import usecases.deletereview.DeleteReviewInteractor;
import usecases.deletereview.IDeleteReviewInput;
import usecases.postreview.IPostReview;
import usecases.postreview.PostReview;
import usecases.postreview.PostReviewFactory;
import usecases.requestconnect.IRequestConnectionInput;
import usecases.requestconnect.RequestConnectionInteractor;
import usecases.signup.ISignUp;
import usecases.signup.SignUpInteractor;
import usecases.votehelpful.HelpfulInteractor;
import usecases.votehelpful.IHelpfulInputBoundary;
import usecases.sort.SortAlgorithmFactory;
import usecases.sort.ISortInput;
import usecases.sort.SortInteractor;

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
    public SortAlgorithmFactory createSortFactory() {
        return new SortAlgorithmFactory();
    }

    @Bean
    public ISortInput sortInput(IReviewDAO reviewDAO, SortAlgorithmFactory sortAlgorithmFactory){
        return new SortInteractor(reviewDAO, sortAlgorithmFactory);
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
    public ISignUp SignUpInput(IUserDAO dataAccess){
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
