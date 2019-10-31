package com.leaveword.service;

import com.alibaba.fastjson.JSONArray;
import com.leaveword.domain.Word;
import com.leaveword.utils.CommonTools;
import com.leaveword.utils.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WordServiceImplTest {

    @Autowired
    private WordServiceImpl wordService;

    @Test
    public void getWords() {
        wordService.wordRepository.deleteAll();;
        assertEquals(new Response("0", "没有留言"),
                wordService.getWords(1));

        List<String> words = new ArrayList<String>();
        Word word = new Word();
        word.setUserId(1);
        word.setTitle("asd");
        word.setContent("haha");
        word.setLeaveTime(CommonTools.getCurrentTime());
        wordService.wordRepository.save(word);
        words.add(JSONArray.toJSONString(word));

       //assertEquals(new Response("0", words),
              //wordService.getWords(1));

    }
}