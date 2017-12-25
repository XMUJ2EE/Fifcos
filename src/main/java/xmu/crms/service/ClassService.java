package xmu.crms.service;

import java.math.BigInteger;
import java.util.List;

import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Location;
import xmu.crms.entity.User;
import xmu.crms.exception.ClazzNotFoundException;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.InvalidOperationException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.UserNotFoundException;

/**
 * @author aixing
 * @version 2.20
 */
public interface ClassService {
    /**
     * 按classId删除CourseSelection表的一条记录 .
     *
     * @param classId 班级Id
     * @author zhouzhongjun
     */
    int deleteClassSelectionByClassId(BigInteger classId) throws ClazzNotFoundException;

    /**
     * 按课程名称和教师名称获取班级列表.
     * <p>
     * 根据课程名和教师名获取课程ID，通过课程ID获取班级列表;若课程名和班级名均不为空，取交集<br>
     *
     * @param courseName  课程名称
     * @param teacherName 教师名称
     * @return List 班级列表
     * @throws UserNotFoundException   无此姓名的教师
     * @throws CourseNotFoundException 无此名称的课程
     * @author yexiaona
     * @see CourseService #listClassByCourseName(String courseName)
     * @see CourseService #listClassByTeacherName(String teacherName)
     */
    List<ClassInfo> listClassByName(String courseName, String teacherName) throws
            UserNotFoundException, CourseNotFoundException;

    /**
     * 根据课程ID获得班级列表.
     *
     * @param courseId 课程ID
     * @return list 班级列表
     * @throws CourseNotFoundException 无此课程Id
     * @author yexiaona
     */
    List<ClassInfo> listClassByCourseId(BigInteger courseId)
            throws CourseNotFoundException;

    /**
     * 按班级id获取班级详情.
     * <p>
     * 根据班级id获取班级<br>
     *
     * @param classId 班级ID
     * @return ClassBO 班级
     * @throws ClazzNotFoundException 无此班级Id
     * @author yexiaona
     */
    ClassInfo getClassByClassId(BigInteger classId)
            throws ClazzNotFoundException;

    /**
     * 按班级id和班级修改班级信息.
     * <p>
     * 根据班级id修改班级信息<br>
     *
     * @param classId  班级ID
     * @param newClass 修改后的班级
     * @throws ClazzNotFoundException 无此班级Id
     * @author yexiaona
     */
    int updateClassByClassId(BigInteger classId, ClassInfo newClass)
            throws ClazzNotFoundException;

    /**
     * 按班级id删除班级.
     * <p>
     * 根据班级id删除班级<br>
     *
     * @param classId 班级ID
     * @throws ClazzNotFoundException 无此班级Id
     * @author yexiaona
     * @see ClassService #deleteCourseSelectionById(BigInteger classId,User user)
     * @see FixGroupService #deleteFixGroupByClassId(BigInteger classId)
     * @see SeminarGroupService #deleteSeminarGroupByClaaId(BigInteger classId)
     */
    int deleteClassByClassId(BigInteger classId)
            throws ClazzNotFoundException;

    /**
     * 学生按班级id选择班级.
     * <p>
     * 根据班级id和用户id新增选课记录<br>
     *
     * @param userId  用户id
     * @param classId 班级id
     * @return courseSelectionId 选课记录id
     * @throws UserNotFoundException  无此姓名的教师
     * @throws ClazzNotFoundException 无此Id的班级
     * @author yexiaona
     */
    BigInteger insertCourseSelectionById(BigInteger userId, BigInteger classId) throws
            UserNotFoundException, ClazzNotFoundException;

    /**
     * 学生按班级id取消选择班级.
     * <p>
     * 根据班级id和用户id删除选课记录及与该班级相关的信息<br>
     *
     * @param userId  用户id
     * @param classId 班级id
     * @throws UserNotFoundException  无此姓名的教师
     * @throws ClazzNotFoundException 无此Id的班级
     * @author yexiaona
     */
    int deleteCourseSelectionById(BigInteger userId, BigInteger classId) throws
            UserNotFoundException, ClazzNotFoundException;

    /**
     * 老师获取该班级签到状态.
     * <p>
     * 根据讨论课id及班级id，获得老师所在位置经纬度和该班级的签到状态<br>
     *
     * @param classId   班级id
     * @param seminarId 讨论课id
     * @return location 该班级签到状态
     * @throws SeminarNotFoundException 无此Id的讨论课
     * @author yexiaona
     */
    Location getCallStatusById(BigInteger classId, BigInteger seminarId)
            throws SeminarNotFoundException;

    /**
     * 新建班级.
     * <p>
     * 根据课程id新建班级<br>
     *
     * @param courseId  课程id
     * @param classInfo 班级信息
     * @return classId 班级Id
     * @throws CourseNotFoundException 无此Id的课程
     * @author ixing
     */
    BigInteger insertClassById(BigInteger courseId, ClassInfo classInfo) throws
            CourseNotFoundException;

    /**
     * 按courseId删除Class.
     * <p>
     * 先根据CourseId获得所有的class的信息，然后根据class信息删除courseSelection表的记录，然后再根据courseId和classId删除ScoureRule表记录，再调用根据classId删除固定分组，最后再将班级的信息删除<br>
     *
     * @param courseId 课程Id
     * @throws CourseNotFoundException 无此Id的课程
     * @author zhouzhongjun
     * @see ClassService #listClassByCourseId(BigInteger courseId)
     * @see ClassService #deleteClasssSelectionByClassId(BigInteger classId)
     * @see FixGroupService #deleteFixGroupByClassId(BigInteger ClassId)
     */
    int deleteClassByCourseId(BigInteger courseId) throws
            CourseNotFoundException;

    /**
     * 按classId删除ScoreRule.
     *
     * @param classId 班级Id
     * @throws ClazzNotFoundException 无此Id的班级
     * @author zhouzhongjun
     */
    int deleteScoreRuleById(BigInteger classId) throws ClazzNotFoundException;

    /**
     * 查询评分规则.
     * <p>
     * 按id查询指定的评分规则<br>
     *
     * @param classId 班级id
     * @return ProportionBO 返回评分规则，若未找到对应评分规则返回空（null)
     * @throws ClazzNotFoundException 无此Id的班级
     * @author YeHongjie
     */
    ClassInfo getScoreRule(BigInteger classId) throws ClazzNotFoundException;

    /**
     * 新增评分规则.
     * <p>
     * 新增评分规则<br>
     *
     * @param classId     班级Id
     * @param proportions 评分规则
     * @return scoreRuleId 若创建成功则返回该评分规则的id，失败则返回-1
     * @throws InvalidOperationException 班级信息不合法
     * @throws ClazzNotFoundException    无此Id的班级
     * @author YeHongjie
     */
    int insertScoreRule(BigInteger classId, ClassInfo proportions)
            throws InvalidOperationException, ClazzNotFoundException;

    /**
     * 修改评分规则.
     * <p>
     * 修改指定的评分规则<br>
     *
     * @param classId     班级id
     * @param proportions 评分规则
     * @throws InvalidOperationException 班级信息不合法
     * @throws ClazzNotFoundException    无此Id的班级
     * @author YeHongjie
     */
    int updateScoreRule(BigInteger classId, ClassInfo proportions)
            throws InvalidOperationException, ClazzNotFoundException;

    /**
     * 老师发起签到.
     * <p>往location表插入一条当前讨论课班级的签到状态<br>
     * 
     * @param location 当前讨论课班级的签到状态记录
     * @return 返回location表的新记录的id
     * @throws SeminarNotFoundException 讨论课没有找到
     * @throws ClazzNotFoundException   无此Id的班级
     */
    BigInteger callInRollById(Location location)
            throws SeminarNotFoundException, ClazzNotFoundException;

    /**
     * 新增老师结束签到.
     * <p>老师结束签到,修改当前讨论课班级的签到状态为已结束<br>
     * 
     * @author qinlingyun
     * @param location 当前讨论课班级的签到状态记录
     * @throws SeminarNotFoundException 讨论课没有找到
     * @throws ClazzNotFoundException   无此Id的班级
     */
    int endCallRollById(BigInteger seminarId, BigInteger classId)
            throws SeminarNotFoundException, ClazzNotFoundException;
    
    
    
    /**
     * 根据学生ID获取班级列表.
     * <p>根据学生ID获取班级列表<br>
     *
     * @param userId 学生ID
     * @return list 班级列表
     * @throws IllegalArgumentException userId格式错误时抛出或courseId格式错误时抛出
     * @throws ClazzNotFoundException   未找到班级
     * @author YeXiaona
     * @see ClassService #getClassByClassId(BigInteger classId)
     */
    List<ClassInfo> listClassByUserId(BigInteger userId) throws IllegalArgumentException, ClazzNotFoundException;
}
