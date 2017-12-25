package xmu.crms.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.entity.CourseSelection;
import xmu.crms.entity.Location;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.User;

public interface ClassMapper {
	public int deleteClassSelectionByClassId(BigInteger classId);
	
	public List<BigInteger> getCourseIdByName(@Param("courseName")String courseName);
	
	public List<BigInteger> getUserIdByName(@Param("teacherName")String teacherName);
	
    public List<ClassInfo> listClassByName(@Param("courseName")String courseName, @Param("teacherName")String teacherName);
    
    public Course selectCourseByCourseId(BigInteger courseId);
    
    public List<ClassInfo> listClassByCourseId(@Param("courseId")BigInteger courseId);
    
    public ClassInfo getClassByClassId(@Param("classId")BigInteger classId);
    
    public int updateClassByClassId(@Param("classId")BigInteger classId, @Param("classInfo")ClassInfo newClass);
    
    /*ServiceFun:deleteClassByClassId*/
    public int deleteLocationByClassId(BigInteger classId);
    
    public List<BigInteger> listFixGroupIdByClassId(BigInteger classId);
    
    public int deleteFixGroupMemberByFixGroupId(@Param("fixGroupIds")List<BigInteger> fixGroupIds);
    
    public int deleteFixGroupTopicByFixGroupId(@Param("fixGroupIds")List<BigInteger> fixGroupIds);
    
    public int deleteFixGroupByClassId(BigInteger classId);
    
    public List<BigInteger> listSeminarGroupIdByClassId(BigInteger classId);
    
    public int deleteSeminarGroupMemberBySeminarGroupId(@Param("seminarGroupIds")List<BigInteger> seminarGroupIds);
    
    public List<BigInteger> listSeminarGroupTopicIdBySeminarGroupId(@Param("seminarGroupIds")List<BigInteger> seminarGroupIds);
    
    public int deleteStudentScoreGroupBySeminarGroupTopicId(@Param("seminarGroupTopicIds")List<BigInteger> seminarGroupTopicIds);
    
    public int deleteSeminarGroupTopicBySeminarGroupId(@Param("seminarGroupIds")List<BigInteger> seminarGroupIds);
    
    public int deleteSeminarGroupByClassId(BigInteger classId);
    
    public int deleteAttendanceByClassId(BigInteger classId);
    
    public int deleteClassByClassId(@Param("classId")BigInteger classId);
    /*End:deleteClassByClassId*/
    
    public User selectUserByUserId(BigInteger userId);
    
    public int insertCourseSelection(CourseSelection courseSelection);
    
    public int insertCourseSelectionById(@Param("userId")BigInteger userId, @Param("classId")BigInteger classId);
    
    public int deleteCourseSelectionById(@Param("userId")BigInteger userId, @Param("classId")BigInteger classId);
    
    public Seminar selectSeminarBySeminarId(BigInteger seminarId);
    
    public Location getCallStatusById(@Param("classId")BigInteger classId, @Param("seminarId")BigInteger seminarId);
    
    public int insertClassById(@Param("courseId")BigInteger courseId,@Param("classInfo")ClassInfo classInfo);
    
    public int deleteClassByCourseId(@Param("courseId")BigInteger courseId);
    
    public int deleteScoreRuleById(@Param("classId")BigInteger classId);
    
    public ClassInfo getScoreRule(@Param("classId")BigInteger classId);
    
    public int insertScoreRule(@Param("classId")BigInteger classId, @Param("proportions")ClassInfo proportions);
    
    public int updateScoreRule(@Param("classId")BigInteger classId, @Param("proportions")ClassInfo proportions);
    
    public int CallInRollById(@Param("location")Location location);
    
    public int endCallRollById(@Param("seminarId")BigInteger seminarId, @Param("classId")BigInteger classId);
    
    public List<BigInteger> listClassByUserId(BigInteger userId);
}
