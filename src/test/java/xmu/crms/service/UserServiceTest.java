package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import xmu.crms.FifcosApplication;
import xmu.crms.entity.School;
import xmu.crms.entity.User;

import java.math.BigInteger;
import java.util.BitSet;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = FifcosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void testGetUser(){
        try{
            User user = userService.getUserByUserId(BigInteger.valueOf(1));
            System.out.println(user.toString());
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    @Test
    public void testUpdateUser(){
        User user = new User(BigInteger.valueOf(1), "18156812606","testWeChatId",
                "testOpenId", "/img/mq.png",null, null,null,
                null,null,null,null,null,null);
        try{

            userService.updateUserByUserId(BigInteger.valueOf(1), user);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @Test
    public void testInsertUser(){
        School school = new School(BigInteger.valueOf(1), "重庆大学", "直辖市", "重庆");
        User user = new User(null, "18156812606", null, null, "/img/mads.png", "123456", "mads",
                school, 0, 1, "24320152202784", 3, null, "mad.s@qq.com");

        System.out.println(userService.signUpPhone(user).toString());
    }
}
