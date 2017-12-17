package xmu.crms.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import xmu.crms.bo.AttendanceBO;
import xmu.crms.bo.UserBO;

/**
 * @author mads
 * @version 1.00
 */
public class UserService {
	/**
	 * 微信登录.
	 * @author mads
	 * @param userId 用户Id
	 * @param code 微信小程序/OAuth2授权的Code
	 * @param state 微信OAuth2授权的state。对于小程序，值恒为 MiniProgram
	 * @param successUrl 微信OAuth2授权后跳转到的网址
	 * @return user 该用户信息
	 */
	public UserBO signInWeChat(BigInteger userId,String code,String state,String successUrl) {

		UserBO user = new UserBO();		
		
		return user;
		
	}
	
	/**
	 * 用户解绑.
	 * 教师解绑账号
	 * @author mads
	 * @param userId 用户id
	 * @return true 解绑成功 false 解绑失败
	 * @see CourseService#listCourseByUserId(BigInteger userId)
	 * @see CourseService#deleteCourseByCourseId(BigInteger courseId)
	 */
	public boolean deleteTeacherAccount(BigInteger userId) {
		
		boolean isDeleted = true;
		
		return isDeleted;
	}	
	
	
	/**
	 * 用户解绑.
	 * 学生解绑账号
	 * @author mads 
	 * @param userId 用户id
	 * @return true 解绑成功 false 解绑失败
	 * @see ClassService#deleteCourseSelectionById(BigInteger userId,BigInteger classId)
	 */		
	public boolean deleteStudentAccount(BigInteger userId) {
	
		boolean isDeleted = true;
		
		return isDeleted;
	}
	
	
	/**
	 * 根据用户Id获取用户的信息.
	 * 根据用户Id获取用户的信息
	 * @author mads
	 * @param userId 用户Id
	 * @return user 用户信息
	 * @see SchoolService#getSchoolBySchoolId(BigInteger schoolId)
	 */
	public UserBO getUserByUserId(BigInteger userId) {

		UserBO user = new UserBO();
		
		return user;
		
	}

	/**
	 * 根据用户名获取用户ID.
	 * @author mads
	 * @param userName 用户名
	 * @return userId 用户ID
	 */
	public BigInteger getUserIdByUserName(String userName) {
		BigInteger userId=new BigInteger("100");
		return userId;
	}
	
	/**
	 * 根据用户ID修改用户信息.
	 * @author mads
	 * @param userId 用户Id
	 * @param user 用户信息
	 * @return true 修改成功 false 修改失败
	 */
	public boolean updateUserByUserId(BigInteger userId, UserBO user) {
		
		boolean isDeleted = true;
		
		return isDeleted;
	}
	

	/**
	 * 按班级ID、学号开头、姓名开头获取学生列表.
	 * @author mads
	 * @param classId 班级ID
	 * @param numBeginWith 学号开头
	 * @param nameBeginWith 姓名开头
	 * @return list 用户列表
	 */	
	public List listUserByClassId(BigInteger classId,String numBeginWith,String nameBeginWith) {

		List<UserBO> list = new ArrayList<UserBO>(); 
		
		return list;
		
	}
	

	/**
	 * 根据用户名获取用户列表.
	 * @author mads
	 * @param userName 用户名
	 * @return list 用户列表
	 */	
	public List listUserByUserName(String userName) {

		List<UserBO> list = new ArrayList<UserBO>(); 
		
		return list;
		
	}

	
}
