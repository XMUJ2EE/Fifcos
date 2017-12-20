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
import xmu.crms.entity.*;
import xmu.crms.exception.InvalidOperationException;
import xmu.crms.exception.LocationNotFoundException;
import xmu.crms.exception.UserDuplicatedException;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
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
    public void testInsertUser() throws UserDuplicatedException{
        School school = new School(BigInteger.valueOf(1), "重庆大学", "直辖市", "重庆");
        User user = new User(null, "18156812606", null, null, "/img/mads.png", "123456", "邱明",
                school, 0, 1, "24522345", 3, null, "mad.s@qq.com");
        try{
            User fakeUser = userService.signUpPhone(user);
            System.out.println(fakeUser.toString());
        }catch (UserDuplicatedException e){
            System.out.println(e.toString());
        }
    }

    @Test
    public void testGetUsersByName(){
        try{
            List<User> users = userService.listUserByUserName("邱明");
            System.out.println(users.toString());
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @Test
    public void testListCourseByTeacherName(){

        try{
            List<Course> courses = userService.listCourseByTeacherName("邱明");
            for(Course course:courses){
                System.out.println(course.toString());
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @Test
    public void testGetStudentListByClassId(){
        try{
            List<User> users = userService.listUserByClassId(BigInteger.valueOf(1), null, null);
            System.out.println(users.toString());
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @Test
    public void testInsertAttendanceById(){
        try{
            userService.insertAttendanceById(BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(94), 23,123);
        }catch (LocationNotFoundException e){
            System.out.println(e.toString());
        }catch (InvalidOperationException e){
            System.out.println(e.toString());
        }
    }

    @Test
    public void testGetAbsenceStudents() throws LocationNotFoundException{
        try{
            List<User> users = userService.listAbsenceStudent(BigInteger.ONE,BigInteger.ONE);
            System.out.println(users.toString());
        }catch (LocationNotFoundException e){
            System.out.println(e.toString());
        }
    }

    @Test
    public void testGetAttendanceById() throws LocationNotFoundException{
        try{
            List<Attendance> attendances = userService.listAttendanceById(BigInteger.ONE, BigInteger.ONE);
            System.out.println(attendances.toString());
        }catch (LocationNotFoundException e){
            System.out.println(e.toString());
        }
    }

    @Test
    public void testGetPresentStudents() throws LocationNotFoundException{
        try{
            List<User> users = userService.listPresentStudent(BigInteger.valueOf(4),BigInteger.ONE);
            System.out.println(users.toString());
        }catch (LocationNotFoundException e){
            System.out.println(e.toString());
        }
    }
}
