package com.leaveword.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.leaveword.domain.User;
import com.leaveword.repository.UserRepository;
import com.leaveword.utils.CommonTools;
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

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void getUser() {

        assertEquals(new Response("0","{\"registerTime\":\"2019-10-14 22:18:09\",\"userId\":1,\"userName\":\"123\",\"userPassword\":\"123\"}"),userService.getUser(1));

        assertEquals(new Response("-1", "用户不存在"),userService.getUser(99999));
    }

    @Test
    public void userRegister() {
//        User user = new User();
//        user.setUserName("1031");
//        user.setUserPassword("1031");
//        user.setRegisterTime(CommonTools.getCurrentTime());
//        userService.userRepository.save(user);


        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userRegister("","123"));
        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userRegister("",""));
        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userRegister(null,""));
        assertEquals(new Response("-1","用户密码不能为空"),new UserServiceImpl().userRegister("test",""));
        assertEquals(new Response("-1","用户密码不能为空"),new UserServiceImpl().userRegister("test",null));
        assertEquals(new Response("-1", "插入用户异常"),new UserServiceImpl().userRegister("test","test"));
        assertEquals(new Response("-1","此用户名已经存在"),userService.userRegister("123","123"));
        assertNotNull(userService.userRegister(String.valueOf(System.currentTimeMillis()),"123"));
    }

    @Test
    public void userLogin(){
        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userLogin("","asd"));
        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userLogin("",""));
        assertEquals(new Response("-1","用户名不能为空"),new UserServiceImpl().userLogin(null,""));
        assertEquals(new Response("-1","用户密码不能为空"),new UserServiceImpl().userLogin("test",""));
        assertEquals(new Response("-1","用户密码不能为空"),new UserServiceImpl().userLogin("test",null));
        assertEquals(new Response("-1","密码错误"),userService.userLogin("123","2222"));
        assertEquals(new Response("0","{\"registerTime\":\"2019-10-14 22:18:09\",\"userId\":1,\"userName\":\"123\",\"userPassword\":\"\"}"),userService.userLogin("123","123"));
        assertEquals(new Response("-1","用户不存在"),userService.userLogin("asifdsafdf","2222"));

    }
}