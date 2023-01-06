package service;

import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import service.dao.ICommentDAO;
import service.dao.IReviewDAO;
import usecases.deletecomment.DeleteCommentRequestModel;
import usecases.deletereview.DeleteReviewRequestModel;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
public class AdminOrOwnerFilter extends GenericFilterBean {


    private final IReviewDAO reviewDAO;
    private final ICommentDAO commentDAO;

    public AdminOrOwnerFilter(IReviewDAO reviewDAO, ICommentDAO commentDAO) {

        this.reviewDAO = reviewDAO;
        this.commentDAO = commentDAO;
    }
    /**
     * Determines whether the authenticated user is an admin or an owner for the target item.
     * Typically used by use cases such as "delete review" or "delete comment".
     * If the principal is not authorized, return the response.
     * Otherwise, proceed to the next filter.
     *
     * @param servletRequest  The request to process
     * @param servletResponse The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this
     *                 filter to pass the request and response to for further
     *                 processing
     * @throws IOException      if an I/O error occurs during this filter's
     *                          processing of the request
     * @throws ServletException if the processing fails for any other reason
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        String requestUrl = ((CachedBodyHttpServletRequest) servletRequest).getRequestURI();
        boolean isMethodDelete = ((CachedBodyHttpServletRequest) servletRequest).getMethod().equals("DELETE");
        if (requestUrl.matches("/corporates/.*/internships/.*/reviews/.*") && isMethodDelete
                || (requestUrl.matches("/corporates/.*/internships/.*/reviews/.*/comments/.*") && isMethodDelete)
        ) {
            boolean isDeleteComment = ((CachedBodyHttpServletRequest) servletRequest).getRequestURI().matches(".*/comments/.*");
            String ownerName;
            if (isDeleteComment) {
                DeleteCommentRequestModel requestModel = new Gson().fromJson(servletRequest.getReader(), DeleteCommentRequestModel.class);
                ownerName = commentDAO.getComment(requestModel.getCommentId()).getUser().getUsername();
            } else {
                DeleteReviewRequestModel requestModel = new Gson().fromJson(servletRequest.getReader(), DeleteReviewRequestModel.class);
                ownerName = reviewDAO.getReview(requestModel.getReviewId()).getUser().getUsername();
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            if (ownerName.equals(authentication.getName())
                    || authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ) {
                chain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletResponse.setStatus(401);
            }
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }
}
