package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import xmu.crms.FifcosApplication;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.User;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.mapper.GradeMapper;
import xmu.crms.service.impl.GradeServiceImpl;
import xmu.crms.service.impl.UserServiceImpl;

import java.math.BigInteger;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringBootTest(classes = FifcosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class GradeServiceTest {
    @Autowired
    GradeService gradeService;


    @Test
    public void testDeleteStudentScoreGroupByTopicId() {
        gradeService.deleteStudentScoreGroupByTopicId(BigInteger.valueOf(1));
    }

    @Test
    public void testListSeminarGradeBySeminarGroupId() {
        SeminarGroup list = null;
        try {
            list = gradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(1));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(list.toString());
    }

    @Test
    public void testInsertGroupGradeByUserId() {
        gradeService.insertGroupGradeByUserId(BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(5));
    }

    @Test
    public void testUpdateGroupByGroupId() {
        try {
            gradeService.updateGroupByGroupId(BigInteger.valueOf(1), BigInteger.valueOf(4));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testlistSeminarGradeByUserId() {
        List<SeminarGroup> list = gradeService.listSeminarGradeByUserId(BigInteger.valueOf(3));
        System.out.println(list);
    }

    @Test
    public void testlistSeminarGradeByCourseId() {
        List<SeminarGroup> list = gradeService.listSeminarGradeByCourseId(BigInteger.valueOf(3), BigInteger.valueOf(1));
        System.out.println(list);
    }

    @Test
    public void testCountPresentationGrade() {
        gradeService.countPresentationGrade(BigInteger.valueOf(1));
    }

    @Test
    public void testCountGroupGradeBySeminarId() {
        gradeService.countGroupGradeBySeminarId(BigInteger.valueOf(1));
    }
}
