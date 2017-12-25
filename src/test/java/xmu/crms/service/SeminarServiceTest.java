package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.*;
import xmu.crms.service.impl.SeminarServiceImpl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author YellowPure
 * @date 2017/12/21
 */
// done 20171225 18.53
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarServiceTest {

    @Autowired
    SeminarService seminarServiceImpl;

    @Test
    public void insertSeminarByCourseId() throws Exception{
        Seminar seminar = new Seminar();
        seminar.setDescription("讨论课介绍");
        seminar.setEndTime(new Date());
        seminar.setStartTime(new Date());
        seminar.setFixed(true);
        seminar.setName("讨论课4");
        seminarServiceImpl.insertSeminarByCourseId(new BigInteger("2"),seminar);
    }

    @Test
    public void listSeminarByCourseId() throws Exception{
        List<Seminar> list = seminarServiceImpl.listSeminarByCourseId(new BigInteger("1"));
        System.out.println(list);
    }

    @Test
    public void deleteSeminarByCourseId() throws Exception{
        seminarServiceImpl.deleteSeminarByCourseId(new BigInteger("2"));
    }
    @Test
    public void getSeminarBySeminarId() throws Exception{
        Seminar seminar = seminarServiceImpl.getSeminarBySeminarId(new BigInteger("1"));
        System.out.print(seminar);
    }
    @Test
    public void deleteSeminarBySeminarId() throws Exception{
        seminarServiceImpl.deleteSeminarBySeminarId(new BigInteger("4"));
    }
    @Test
    public void updateSeminarBySeminarId() throws Exception{
        Seminar seminar = new Seminar();
        seminar.setDescription("讨论课介绍");
        seminar.setEndTime(new Date());
        seminar.setStartTime(new Date());
        seminar.setFixed(true);
        Course course = new Course();
        course.setId(new BigInteger("2"));
        seminar.setCourse(course);
        seminar.setName("讨论课名称");
        seminarServiceImpl.updateSeminarBySeminarId(new BigInteger("1"),seminar);
    }
}
