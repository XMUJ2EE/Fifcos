package xmu.crms.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.mapper.SeminarMapper;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.SeminarService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YellowPure
 * @date 2017/12/19
 */
@Service
public class SeminarServiceImpl implements SeminarService {

    @Autowired(required = false)
    SeminarMapper seminarMapper;
    @Autowired(required = false)
    TimerServiceImpl timerService;
    /**
     * 按courseId获取Seminar.
     *
     * @param courseId 课程Id
     * @return List 讨论课列表
     * @throws IllegalArgumentException CourseId 格式错误、教师设置embedGrade为true时抛出
     * @throws CourseNotFoundException  未找到该课程时抛出
     * @author zhouzhongjun
     */
    @Override
    public List<Seminar> listSeminarByCourseId(BigInteger courseId)
            throws IllegalArgumentException, CourseNotFoundException {
        //未找到该课程
        if (seminarMapper.getCourseById(courseId) == null) {
            throw new CourseNotFoundException("未找到该课程");
        }
        return seminarMapper.listSeminarByCourseId(courseId);
    }


    /**
     * 按courseId删除Seminar.
     * <p>先根据CourseId获得所有的seminar的信息，然后根据seminar信息删除相关topic的记录，然后再根据SeminarId删除SeminarGroup表记录,最后再将seminar的信息删除<br>
     *
     * @param courseId 课程Id
     * @throws IllegalArgumentException CourseId 格式错误时抛出
     * @throws CourseNotFoundException  该课程不存在时抛出
     * @author zhouzhongjun
     * @see SeminarService #listSemiarByCourseId(BigInteger courseId)
     */
    @Override
    public void deleteSeminarByCourseId(BigInteger courseId) throws IllegalArgumentException,
            CourseNotFoundException {

        //未找到该课程
        if (seminarMapper.getCourseById(courseId) == null) {
            throw new CourseNotFoundException("未找到该课程");
        }

        //根据CourseId获得所有的seminar的信息
        List<Seminar> seminarList = listSeminarByCourseId(courseId);

        for (int i = 0; i < seminarList.size(); i++) {
            //根据seminar信息删除相关topic的记录
            seminarMapper.deleteTopicBySeminarId(seminarList.get(i).getId());
            //根据SeminarId删除SeminarGroup表记录
            seminarMapper.deleteSeminarGroupBySeminarId(seminarList.get(i).getId());

        }
        //将seminar的信息删除
        seminarMapper.deleteSeminarByCourseId(courseId);
    }

    /**
     * 用户通过讨论课id获得讨论课的信息.
     * <p>用户通过讨论课id获得讨论课的信息（包括讨论课名称、讨论课描述、分组方式、开始时间、结束时间）<br>
     *
     * @param seminarId 讨论课的id
     * @return 相应的讨论课信息
     * @throws IllegalArgumentException SeminarId 格式错误时抛出
     * @throws SeminarNotFoundException 该讨论课不存在时抛出
     * @author CaoXingmei
     */
    @Override
    public Seminar getSeminarBySeminarId(BigInteger seminarId) throws
            IllegalArgumentException, SeminarNotFoundException {
        //该讨论课不存在时抛出
        if (seminarMapper.getSeminarBySeminarId(seminarId) == null) {
            throw new SeminarNotFoundException("该讨论课不存在");
        }
        return seminarMapper.getSeminarBySeminarId(seminarId);
    }


    /**
     * finish
     * 按讨论课id修改讨论课.
     * <p>用户（老师）通过seminarId修改讨论课的相关信息<br>
     *
     * @param seminarId 讨论课的id
     * @param seminar   讨论课信息
     * @throws IllegalArgumentException SeminarId 格式错误时抛出
     * @throws SeminarNotFoundException 该讨论课不存在时抛出
     * @author CaoXingmei
     */
    @Override
    public void updateSeminarBySeminarId(BigInteger seminarId, Seminar seminar) throws
            IllegalArgumentException, SeminarNotFoundException {
        //该讨论课不存在时抛出
        if (seminarMapper.getSeminarBySeminarId(seminarId) == null) {
            throw new SeminarNotFoundException("该讨论课不存在");
        }
        seminar.setId(seminarId);
        seminarMapper.updateSeminarBySeminarId(seminar);
    }


    /**
     * 按讨论课id删除讨论课.
     * <p>用户（老师）通过seminarId删除讨论课<br>(包括删除讨论课包含的topic信息和小组信息).
     *
     * @param seminarId 讨论课的id
     * @throws IllegalArgumentException SeminarId 格式错误时抛出
     * @throws SeminarNotFoundException 该讨论课不存在时抛出
     * @author CaoXingmei
     */
    @Override
    public void deleteSeminarBySeminarId(BigInteger seminarId) throws
            IllegalArgumentException, SeminarNotFoundException {
        //该讨论课不存在时抛出
        if (seminarMapper.getSeminarBySeminarId(seminarId) == null) {
            throw new SeminarNotFoundException("该讨论课不存在");
        }
        //删除讨论课包含的topic信息和小组信息
        seminarMapper.deleteTopicBySeminarId(seminarId);
        seminarMapper.deleteSeminarGroupBySeminarId(seminarId);
        //通过seminarId删除讨论课
        seminarMapper.deleteSeminarById(seminarId);
    }


    /**
     * 新增讨论课.
     * <p>用户（老师）在指定的课程下创建讨论课<br>
     *
     * @author YeHongjie
     * @param courseId 课程的id
     * @param seminar 讨论课信息
     * @exception IllegalArgumentException CourseId 格式错误时抛出
     * @exception CourseNotFoundException 该课程不存在时抛出
     */
    SeminarGroupService seminarGroupService;

    @Override
    public BigInteger insertSeminarByCourseId(BigInteger courseId, Seminar seminar) throws
            IllegalArgumentException, CourseNotFoundException {
        //未找到该课程
        if (seminarMapper.getCourseById(courseId) == null) {
            throw new CourseNotFoundException("未找到该课程");
        }
        Course course = new Course();
        course.setId(courseId);
        seminar.setCourse(course);
        seminarMapper.insertSeminarByCourseId(seminar);
        //return seminarMapper.insertSeminarByCourseId(seminar);
        //return BigInteger.valueOf(seminarMapper.insertSeminarByCourseId(seminar));

        //如果是固定分组，在插入event计时器，随机分组在随机分组之后
//        @Test
//        public void testInsertEvent() throws JsonProcessingException {
//            Integer insert = 0;
//            List<Object> paramList=new ArrayList<>();
//            paramList.add(new BigInteger("11"));
//            paramList.add(new Date());
//            timerService.insertEvent(new Date(),"TimerService","updateEvent",paramList);
//        }

        List<Object> paramList = new ArrayList<Object>();
        System.out.println("seminar:" + seminar.getId());
        paramList.add(seminar.getId());
        try {
            timerService.insertEvent(seminar.getEndTime(), "GradeService", "countGroupGradeBySeminarId", paramList);
            return seminar.getId();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

