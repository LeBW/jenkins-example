package com.leaveword.service;

import com.leaveword.utils.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
public class UserServiceImplTest {

    @Test
    public void getUser() {
    }

    @Test
    public void userRegister() {
        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userRegister("","123"));
        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userRegister("",""));
        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userRegister(null,""));
        assertEquals(new Response("-1","用户密码不能为空"),new UserServiceImpl().userRegister("test",""));
        assertEquals(new Response("-1","用户密码不能为空"),new UserServiceImpl().userRegister("test",null));
        //assertEquals(new Response("-1", "插入用户异常"),new UserServiceImpl().userRegister("test","test"));
    }

    @Test
    public void userLogin(){
        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userLogin("","asd"));
        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userLogin("",""));
        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userLogin(null,""));
        assertEquals(new Response("-1","用户密码不能为空"),new UserServiceImpl().userLogin("test",""));
        assertEquals(new Response("-1","用户密码不能为空"),new UserServiceImpl().userLogin("test",null));

    }
}