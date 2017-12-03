package xmu.crms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import xmu.crms.entity.Course;

@Service
public class CourseService {
	//这里用List存放数据,以避免数据库的连接操作
	private List<Course> courses = new ArrayList<Course>();

	//由于可以使用增加课程来添加数据,这里不需要再默认添加数据了

	public CourseService() { }


	//一个简单的通过id来获取课程的函数
	public Course getCourseNameById(int id) {

		return courses.get(id);
	}

	//添加课程
	public Course addCourse(Course course) {

		courses.add(course);
		return course;

	}
	//以List形式,获取所有的课程
	public List<Course> getAllCourses(){

		return courses;

	}
	//删除课程
	public Course deleteCourse(int id) {
		
		return courses.remove(id);
	}
}
