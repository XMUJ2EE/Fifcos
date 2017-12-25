package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.dao.CourseDao;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.entity.User;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.service.impl.ClassServiceImpl;
import xmu.crms.service.impl.UserServiceImpl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhangyuping
 * @Description:
 * @Data: 2017/12/24 5:04
 */

// test done ! 20171225 17.10
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceImplTest {
    @Autowired
    CourseDao courseDao;
    @Autowired
    ClassServiceImpl classServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    CourseService courseService;
    @Test
    public void listCourseByUserId() throws Exception {
        BigInteger userId = new BigInteger("1");
        List<Course> courseList = courseDao.listCourseByUserId(userId);
        for(int i = 0; i < courseList.size(); i++)
            System.out.println(courseList.get(i));
    }

    @Test
    public void insertCourseByUserId() throws Exception {
        BigInteger userId = new BigInteger("4");
        Course course = new Course();
        course.setName("OOAD2");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = format.parse("2017-09-10");
            Date endDate = format.parse("2018-02-01");
            course.setStartDate(startDate);
            course.setEndDate(endDate);
            User teacher = new User();
            teacher.setId(userId);
            course.setTeacher(teacher);
            course.setDescription("xxx");
            course.setReportPercentage(50);
            course.setPresentationPercentage(50);
            course.setFivePointPercentage(10);
            course.setFourPointPercentage(30);
            course.setThreePointPercentage(60);
            BigInteger t = courseDao.insertCourseByUserId(userId,course);
            System.out.println(t);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    @Test
    public void getCourseByCourseId() throws Exception {
        BigInteger courseId = new BigInteger("1");
        Course course = courseDao.getCourseByCourseId(courseId);
        System.out.println(course);
    }

    @Test
    public void updateCourseByCourseId() throws Exception {
        BigInteger courseId = new BigInteger("1");
        BigInteger userId = new BigInteger("1");
        Course course = new Course();
        course.setName("OOAD");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            course.setId(courseId);
            Date startDate = format.parse("2017-09-10");
            Date endDate = format.parse("2018-02-01");
            course.setStartDate(startDate);
            course.setEndDate(endDate);
            User teacher = new User();
            teacher.setId(userId);
            course.setTeacher(teacher);
            course.setDescription("xxx");
            course.setReportPercentage(50);
            course.setPresentationPercentage(50);
            course.setFivePointPercentage(10);
            course.setFourPointPercentage(30);
            course.setThreePointPercentage(60);
            Integer t = courseDao.updateCourseByCourseId(courseId,course);
            System.out.println(t);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void deleteCourseByCourseId() throws Exception {
        courseService.deleteCourseByCourseId(BigInteger.valueOf(4));
    }

    @Test
    public void listCourseByCourseName() throws Exception {
        String courseName = "课程1";
        List<Course> courseList = courseDao.listCourseByCourseName(courseName);
        for(int i = 0; i < courseList.size(); i++)
            System.out.println(courseList.get(i));
    }

    @Test
    public void listClassByCourseName() throws Exception {
        String courseName = "课程1";
        List<Course> courseList = courseDao.listCourseByCourseName(courseName);
        List<ClassInfo> classInfoList = new LinkedList<>();
        for(int i = 0; i < courseList.size(); i++)
            classInfoList.addAll(classServiceImpl.listClassByCourseId(courseList.get(i).getId()));
        for(int i = 0; i < classInfoList.size(); i++)
            System.out.println(classInfoList.get(i));
    }

    @Test
    public void listClassByTeacherName() throws Exception {
        String teacherName = "邱明";
        List<Course> courseList = userServiceImpl.listCourseByTeacherName(teacherName);
        try {
            if (courseList == null) {
                throw new CourseNotFoundException("没有找到课程！");
            }
            List<ClassInfo> classInfoList = new LinkedList<>();
            for (int i = 0; i < courseList.size(); i++)
                classInfoList.addAll(classServiceImpl.listClassByCourseId(courseList.get(i).getId()));
            for (int i = 0; i < classInfoList.size(); i++)
                System.out.println(classInfoList.get(i));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}