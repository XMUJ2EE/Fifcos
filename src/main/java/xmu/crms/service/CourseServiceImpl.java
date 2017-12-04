package xmu.crms.service;

import org.springframework.stereotype.Service;
import xmu.crms.entity.Class;
import xmu.crms.entity.Course;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.SeminarGradeDetail;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {
    @Override
    public List<Course> getCourseListById(int userId) {
        return null;
    }

    @Override
    public int addCourse(int userId, Course course) {
        return 0;
    }

    @Override
    public Course getCourseById(int courseId) {
        return null;
    }

    @Override
    public Boolean updateCourseById(int courseId, Course course) {
        return null;
    }

    @Override
    public Boolean deleteCourseById(int userId, int courseId) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getClassListByCourseId(int courseId) {
        return null;
    }

    @Override
    public int addClassByCourseId(int courseId, Class myClass) {
        return 0;
    }

    @Override
    public List<Seminar> getSeminarListByCourseId(int courseId) {
        return null;
    }

    @Override
    public int addSeminarByCourseId(int courseId, Seminar seminar) {
        return 0;
    }

    @Override
    public List<SeminarGradeDetail> getAllSeminarGradeByCourseId(int courseId, int userId) {
        return null;
    }

    @Override
    public Seminar getCurrentSeminarByCourseId(int courseId) {
        return null;
    }
}
