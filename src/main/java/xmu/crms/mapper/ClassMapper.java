package xmu.crms.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Location;

public interface ClassMapper {
	public void deleteClassSelectionByClassId(BigInteger classId);
	
    public List<ClassInfo> listClassByName(@Param("courseName")String courseName, @Param("teacherName")String teacherName);
    
    public List<ClassInfo> listClassByCourseId(@Param("courseId")BigInteger courseId);
    
    public ClassInfo getClassByClassId(@Param("classId")BigInteger classId);
    
    public void updateClassByClassId(@Param("classId")BigInteger classId, @Param("newClass")ClassInfo newClass);
    
    public void deleteClassByClassId(@Param("classId")BigInteger classId);
    
    public int insertCourseSelectionById(@Param("userId")BigInteger userId, @Param("classId")BigInteger classId);
    
    public void deleteCourseSelectionById(@Param("userId")BigInteger userId, @Param("classId")BigInteger classId);
    
    public Location getCallStatusById(@Param("classId")BigInteger classId, @Param("seminarId")BigInteger seminarId);
    
    public int insertClassById(@Param("userId")BigInteger userId, @Param("courseId")BigInteger courseId,@Param("classInfo")ClassInfo classInfo);
    
    public void deleteClassByCourseId(@Param("courseId")BigInteger courseId);
    
    public void deleteScoreRuleById(@Param("classId")BigInteger classId);
    
    public ClassInfo getScoreRule(@Param("classId")BigInteger classId);
    
    public int insertScoreRule(@Param("classId")BigInteger classId, @Param("proportions")ClassInfo proportions);
    
    public void updateScoreRule(@Param("classId")BigInteger classId, @Param("proportions")ClassInfo proportions);
    
    public int CallInRollById(@Param("location")Location location);
}
