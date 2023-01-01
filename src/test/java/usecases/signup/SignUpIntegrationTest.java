package usecases.signup;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@RunWith(SpringRunner.class)
public class SignUpIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerWithCorrectInputs_shouldSucceedWith200() throws Exception {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstName", "Jack");
        requestBody.put("lastName", "Ryan");
        requestBody.put("username", "jackryan2023");
        requestBody.put("email", "jack@mail.utoronto.ca");
        requestBody.put("password", "veryStrongPassword!3");
        requestBody.put("confirmPassword", "veryStrongPassword!3");

        MvcResult result = mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualTo("{\"errorMessages\":[]}");
    }

    @Test
    public void registerWithInvalidEmailDomain_shouldFailWith400() throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstName", "Jack");
        requestBody.put("lastName", "Ryan");
        requestBody.put("username", "jackryan12023");
        requestBody.put("email", "jack@apple.com");
        requestBody.put("password", "veryStrongPassword!3");
        requestBody.put("confirmPassword", "veryStrongPassword!3");

        MvcResult result = mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualTo("{\"errorMessages\":[{\"field\":\"email\",\"message\":\"" +
                "Please enter a valid utoronto.ca email\"}]}");
    }

    @Test
    public void registerWithInsecurePassword_shouldFailWith400() throws Exception {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstName", "Jack");
        requestBody.put("lastName", "Ryan");
        requestBody.put("username", "jackryan12023");
        requestBody.put("email", "jack@mail.utoronto.ca");
        requestBody.put("password", "password");
        requestBody.put("confirmPassword", "password");

        MvcResult result = mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualTo("{\"errorMessages\":[" +
                "{\"field\":\"password\",\"message\":\"Please ensure at least one: capital, lowercase, number, " +
                "special characters, and a minimum length of 8 because we hate you :)\"}]}");
    }
}

