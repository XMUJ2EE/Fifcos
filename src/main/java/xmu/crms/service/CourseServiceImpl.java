package xmu.crms.service;

import org.springframework.stereotype.Service;
import xmu.crms.entity.Course;
import xmu.crms.entity.Seminar;

import java.util.List;

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
    public List<Class> getClassListByCourseId(int courseId) {
        return null;
    }

    @Override
    public Boolean addClassByCourseId(int courseId) {
        return null;
    }

    @Override
    public List<Seminar> getSeminarListByCourseId(int courseId) {
        return null;
    }
}
