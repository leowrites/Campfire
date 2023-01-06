package service;

import jakarta.servlet.ServletException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class AuthFailureHandler implements AuthenticationFailureHandler {
    /**
     *
     * Initialize an instance of AuthenticationFailure
     * @param request the request during which the authentication attempt occurred.
     * @param exception the exception which was thrown to reject the authentication
     * request
     *
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
