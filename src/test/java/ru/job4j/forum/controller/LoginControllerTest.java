package ru.job4j.forum.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenIncorrectLogin() throws Exception {
        this.mockMvc.perform(formLogin().user("hello").password("hello"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=true"));
    }

    @Test
    public void whenRedirectWithIncorrectLogin() throws Exception {
        this.mockMvc.perform(get("/login?error=true"))
                .andDo(print())
                .andExpect(model().attribute("errorMessage", "Username or Password is incorrect !!!"));
    }

    @Test
    public void whenLoggedOut() throws Exception {
        this.mockMvc.perform(get("/login?logout=true"))
                .andDo(print())
                .andExpect(model().attribute("errorMessage", "You have been successfully logged out !!!"));
    }

}