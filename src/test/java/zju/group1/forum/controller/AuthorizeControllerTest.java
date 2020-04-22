package zju.group1.forum.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import zju.group1.forum.ForumBackendApplication;
import zju.group1.forum.dto.Message;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForumBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class AuthorizeControllerTest {
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @org.junit.Test
//    public void email_login() {
//        MultiValueMap multiValueMap = new LinkedMultiValueMap();
//        multiValueMap.add("email","279902745@qq.com");
//        multiValueMap.add("password","123456");
//        Message message = testRestTemplate.postForObject("/login", multiValueMap, Message.class);
//        assertEquals(message.getMessage(),"登陆成功！");
//    }
}
