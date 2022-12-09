package service;

import com.google.gson.Gson;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    /**
     *
     * Initializes an AuthenticationSuccess if the http request is valid
     * @param request an HttpServletRequest
     * @param response an HttpServletResponse that is set up after the Authentication
     * @param authentication the Authentication object which allows for the response to be made
     *
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {
        String json = new Gson().toJson(authentication);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
