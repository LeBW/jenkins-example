package com.leaveword.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.leaveword.domain.Word;
import com.leaveword.utils.CommonTools;
import com.leaveword.utils.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class WordServiceImplTest {

    @Autowired
    private WordServiceImpl wordService;

    @Test
    @Rollback
    public void getWords() {
        assertEquals(new Response("0", JSONArray.toJSONString(wordService.wordRepository.findAll())),
                wordService.getWords(1));

        wordService.wordRepository.deleteAll();
        assertEquals(new Response("0", "没有留言"),
                wordService.getWords(1));
    }

    @Test
    public void leaveWord() {
        assertEquals(new Response("-1", "用户不存在"),
                wordService.leaveWord(999, "asd", "hehe"));

        assertEquals(new Response("-1", "标题不能为空"),
                wordService.leaveWord(1, "", "f"));
        assertEquals(new Response("-1", "标题不能为空"),
                wordService.leaveWord(1, "", ""));
        assertEquals(new Response("-1", "标题不能为空"),
                wordService.leaveWord(1, "", null));
        assertEquals(new Response("-1", "标题不能为空"),
                wordService.leaveWord(1, null, "f"));
        assertEquals(new Response("-1", "标题不能为空"),
                wordService.leaveWord(1, null, ""));
        assertEquals(new Response("-1", "标题不能为空"),
                wordService.leaveWord(1, null, null));

        assertEquals(new Response("-1", "内容不能为空"),
                wordService.leaveWord(1, "asd", ""));
        assertEquals(new Response("-1", "内容不能为空"),
                wordService.leaveWord(1, "asd", null));

        assertNotNull(wordService.leaveWord(1, "dfs", "nihao"));

    }
}