package ee.taltech.iti02032020.backend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.iti02032020.backend.service.users.dto.LoginDto;
import ee.taltech.iti02032020.backend.service.users.dto.RegisterDto;
import ee.taltech.iti02032020.backend.service.users.dto.UpdateDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    @Order(1)
    public void registerUser() throws Exception {
        RegisterDto newUser = new RegisterDto();
        newUser.setUsername("NotUser");
        newUser.setHometown("Valga");
        newUser.setPassword("Hello");
        mvc.perform(MockMvcRequestBuilders.post("/users/register").contentType(APPLICATION_JSON)
                .content(asJsonString(newUser)).accept(APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void login() throws Exception {
        LoginDto login = new LoginDto();
        login.setUsername("user");
        login.setPassword("user");
        mvc.perform(MockMvcRequestBuilders.post("/users/login").contentType(APPLICATION_JSON)
                .content(asJsonString(login)).accept(APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    @Order(3)
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void updateUserNoTown() throws Exception {
        UpdateDto updateUser = new UpdateDto();
        updateUser.setUsername("Hello");
        updateUser.setHometown("Hello");
        updateUser.setOldusername("user");
        mvc.perform(MockMvcRequestBuilders.post("/users/update").contentType(APPLICATION_JSON)
                .content(asJsonString(updateUser)).accept(APPLICATION_JSON)).andExpect(status().isOk());
    }



    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}