package com.leaveword.controller;

import com.leaveword.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    UserService userService;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void userRegister() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders.post("/userRegister")
                                               .accept(MediaType.APPLICATION_JSON)
                                               .param("userName", "Jack")
                                               .param("userPassword", "Jack001"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(userService.userRegister("Jack","Jack001").getStatus()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(userService.userRegister("Jack","Jack001").getContent()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getUser() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/getUser")
                                              .accept(MediaType.APPLICATION_JSON)
                                              .param("userId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(userService.getUser(1).getStatus()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(userService.getUser(1).getContent()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    public void userLogin() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/userLogin")
                                      .accept(MediaType.APPLICATION_JSON)
                                      .param("userName", "Jack")
                                      .param("userPassword", "Jack001"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(userService.userLogin("Jack","Jack001").getStatus()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(userService.userLogin("Jack","Jack001").getContent()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}