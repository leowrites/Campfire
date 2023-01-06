package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import jakarta.servlet.ServletException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;
import service.dao.IUserDAO;
import usecases.requestconnect.exceptions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private IUserDAO userDAO;
    /**
     *
     * Initializes an AuthenticationSuccess if the http request is valid
     * @param request an HttpServletRequest
     * @param response an HttpServletResponse that is set up after the Authentication
     * @param authentication the Authentication object which allows for the response to be made
     *
     */
    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        User user;
        try {
            user = userDAO.getUser(authentication.getName());
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        AuthenticatedUserDto authenticatedUserDto = new ModelMapper().map(user, AuthenticatedUserDto.class);
        String json = new ObjectMapper().writeValueAsString(authenticatedUserDto);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
