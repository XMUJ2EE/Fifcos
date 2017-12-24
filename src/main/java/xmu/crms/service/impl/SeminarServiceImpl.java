package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.mapper.SeminarMapper;
import xmu.crms.service.SeminarService;

import java.math.BigInteger;
import java.util.List;

/**
 * @author YellowPure
 * @date 2017/12/19
 */
@Service
public class SeminarServiceImpl implements SeminarService {

    @Autowired(required = false)
    SeminarMapper seminarMapper;

    /**
     * 按courseId获取Seminar.
     * @author zhouzhongjun
     * @param courseId 课程Id
     * @return List 讨论课列表
     * @exception IllegalArgumentException CourseId 格式错误、教师设置embedGrade为true时抛出
     * @exception CourseNotFoundException 未找到该课程时抛出
     */
    @Override
    public List<Seminar> listSeminarByCourseId(BigInteger courseId)
            throws IllegalArgumentException,CourseNotFoundException {
        //未找到该课程
        if(seminarMapper.getCourseById(courseId)==null){
            throw new CourseNotFoundException("未找到该课程");
        }
        return seminarMapper.listSeminarByCourseId(courseId);
    }


    /**
     * 按courseId删除Seminar.
     * <p>先根据CourseId获得所有的seminar的信息，然后根据seminar信息删除相关topic的记录，然后再根据SeminarId删除SeminarGroup表记录,最后再将seminar的信息删除<br>
     * @author zhouzhongjun
     * @param courseId 课程Id
     * @see SeminarService #listSemiarByCourseId(BigInteger courseId)
     * @exception IllegalArgumentException CourseId 格式错误时抛出
     * @exception CourseNotFoundException 该课程不存在时抛出
     */
    @Override
    public void deleteSeminarByCourseId(BigInteger courseId) throws IllegalArgumentException,
            CourseNotFoundException{

        //未找到该课程
        if(seminarMapper.getCourseById(courseId)==null){
            throw new CourseNotFoundException("未找到该课程");
        }

        //根据CourseId获得所有的seminar的信息
        List<Seminar> seminarList = listSeminarByCourseId(courseId);

        for(int i = 0 ; i < seminarList.size() ; i++) {
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
     * @author CaoXingmei
     * @param seminarId 讨论课的id
     * @return 相应的讨论课信息
     * @exception IllegalArgumentException SeminarId 格式错误时抛出
     * @exception SeminarNotFoundException 该讨论课不存在时抛出
     */
    @Override
    public Seminar getSeminarBySeminarId(BigInteger seminarId) throws
            IllegalArgumentException,SeminarNotFoundException{
        //该讨论课不存在时抛出
        if(seminarMapper.getSeminarBySeminarId(seminarId)==null){
            throw new SeminarNotFoundException("该讨论课不存在");
        }
        return seminarMapper.getSeminarBySeminarId(seminarId);
    }


    /** finish
     * 按讨论课id修改讨论课.
     * <p>用户（老师）通过seminarId修改讨论课的相关信息<br>
     * @author CaoXingmei
     * @param seminarId 讨论课的id
     * @param seminar 讨论课信息
     * @exception IllegalArgumentException SeminarId 格式错误时抛出
     * @exception SeminarNotFoundException 该讨论课不存在时抛出
     */
    @Override
    public void updateSeminarBySeminarId(BigInteger seminarId, Seminar seminar) throws
            IllegalArgumentException,SeminarNotFoundException{
        //该讨论课不存在时抛出
        if(seminarMapper.getSeminarBySeminarId(seminarId)==null){
            throw new SeminarNotFoundException("该讨论课不存在");
        }
        seminar.setId(seminarId);
        seminarMapper.updateSeminarBySeminarId(seminar);
    }


    /**
     * 按讨论课id删除讨论课.
     * <p>用户（老师）通过seminarId删除讨论课<br>(包括删除讨论课包含的topic信息和小组信息).
     * @author CaoXingmei
     * @param seminarId 讨论课的id
     * @exception IllegalArgumentException SeminarId 格式错误时抛出
     * @exception SeminarNotFoundException 该讨论课不存在时抛出
     */
    @Override
    public void deleteSeminarBySeminarId(BigInteger seminarId) throws
            IllegalArgumentException,SeminarNotFoundException{
        //该讨论课不存在时抛出
        if(seminarMapper.getSeminarBySeminarId(seminarId)==null){
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
     * @author YeHongjie
     * @param courseId 课程的id
     * @param seminar 讨论课信息
     * @exception IllegalArgumentException CourseId 格式错误时抛出
     * @exception CourseNotFoundException 该课程不存在时抛出
     */
    @Override
    public void insertSeminarByCourseId(BigInteger courseId, Seminar seminar) throws
            IllegalArgumentException,CourseNotFoundException{
        //未找到该课程
        if(seminarMapper.getCourseById(courseId)==null){
            throw new CourseNotFoundException("未找到该课程");
        }
        Course course = new Course();
        course.setId(courseId);
        seminar.setCourse(course);
        //return seminarMapper.insertSeminarByCourseId(seminar);
        seminarMapper.insertSeminarByCourseId(seminar);
    }
}
