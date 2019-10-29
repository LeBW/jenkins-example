package com.leaveword.controller;

import com.leaveword.service.WordService;
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
public class WordControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    WordService wordService;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void leaveWord() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/leaveWord")
                                              .accept(MediaType.APPLICATION_JSON)
                                              .param("userId", "1")
                                              .param("title", "Jack001")
                                              .param("content", "Jack001"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(wordService.leaveWord(1,"Jack001","Jack001").getStatus()))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.content").value(wordService.leaveWord(1,"Jack001","Jack001").getContent()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getWords() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/getWords")
                                      .accept(MediaType.APPLICATION_JSON)
                                      .param("userId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(wordService.getWords(1).getStatus()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(wordService.getWords(1).getContent()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}