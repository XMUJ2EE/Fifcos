package xmu.crms.service;

import java.math.BigInteger;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import xmu.crms.entity.*;
import xmu.crms.exception.*;

// test done! 17.08 20171215
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class ClassServiceTest {
	@Autowired
	private ClassService classService;
	
	/**
	 * 测试select语句
	 */
	
	@Test
	public void testListClassByName() throws UserNotFoundException, CourseNotFoundException {
		List<ClassInfo> classInfos=classService.listClassByName("课程1", "邱明");
		System.out.println(classInfos);
	}
	
	@Test
	public void testListClassByCourseId() throws CourseNotFoundException {
		List<ClassInfo> classInfos=classService.listClassByCourseId(new BigInteger("1"));
		System.out.println(classInfos);
	}
	
	@Test
	public void testGetClassByClassId() throws ClazzNotFoundException {
		ClassInfo classInfo=classService.getClassByClassId(new BigInteger("1"));
		System.out.println(classInfo.toString());
	}

	@Test
	public void testGetCallStatusById(){
		try{
			Location location=classService.getCallStatusById(new BigInteger("1"), new BigInteger("3"));
			System.out.println(location.toString());
		}catch (SeminarNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetScoreRule() throws ClazzNotFoundException {
		ClassInfo classInfo=classService.getScoreRule(new BigInteger("1"));
		Assert.assertNotNull(classInfo);
	}
	
	/**
	 * 测试insert语句
	 */
	
	@Test
	public void testInsertCourseSelectionById() throws UserNotFoundException, ClazzNotFoundException {
		BigInteger userId=new BigInteger("89");
		BigInteger classId=new BigInteger("3");
		BigInteger insert=classService.insertCourseSelectionById(userId, classId);
		Assert.assertNotEquals(new BigInteger("0"),insert);
	}

	@Test
	public void testInsertClassById() throws CourseNotFoundException
	{
		BigInteger courseId=new BigInteger("3");
		ClassInfo classInfo=new ClassInfo();
		classInfo.setName("222");
		classInfo.setReportPercentage(5);
		classInfo.setFivePointPercentage(5);
		classInfo.setFourPointPercentage(5);
		classInfo.setThreePointPercentage(5);
		classInfo.setPresentationPercentage(5);
		BigInteger insert=classService.insertClassById(courseId,classInfo);
		Assert.assertNotEquals(new BigInteger("0"),insert);
	}
	
	@Test
	public void testCallInRollById()
	{
		ClassInfo classInfo=new ClassInfo();
		classInfo.setId(new BigInteger("1"));
		Seminar seminar=new Seminar();
		seminar.setId(new BigInteger("3"));
		Location location=new Location();
		location.setLongitude(100.0);
		location.setLatitude(100.0);
		location.setClassInfo(classInfo);
		location.setSeminar(seminar);
		location.setStatus(1);
		try{
			BigInteger insert=classService.callInRollById(location);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试update语句
	 */
	
	@Test
	public void testInsertScoreRule() throws ClassNotFoundException, InvalidOperationException, ClazzNotFoundException
	{
		BigInteger classId=new BigInteger("7");
		ClassInfo proportions=new ClassInfo();
		proportions.setName("cccc");
		proportions.setReportPercentage(4);
		proportions.setFivePointPercentage(4);
		proportions.setFourPointPercentage(4);
		proportions.setThreePointPercentage(4);
		proportions.setReportPercentage(4);
		int insert=classService.insertScoreRule(classId, proportions);
		Assert.assertNotEquals(0,insert);
	}
	
	@Test
	public void testUpdateClassByClassId() throws ClazzNotFoundException
	{
		BigInteger classId=new BigInteger("4");
		ClassInfo proportions=new ClassInfo();
		proportions.setName("111");
		proportions.setReportPercentage(4);
		proportions.setFivePointPercentage(4);
		proportions.setFourPointPercentage(4);
		proportions.setThreePointPercentage(4);
		proportions.setReportPercentage(4);
		int update=classService.updateClassByClassId(classId, proportions);
		Assert.assertNotEquals(0,update);
	}
	
	@Test
	public void testUpdateScoreRule() throws InvalidOperationException, ClazzNotFoundException
	{
		BigInteger classId=new BigInteger("7");
		ClassInfo proportions=new ClassInfo();
		proportions.setFivePointPercentage(4);
		int update=classService.updateScoreRule(classId, proportions);
		Assert.assertNotEquals(0,update);
	}
	
	@Test
	public void testEndCallRollById() throws SeminarNotFoundException, ClazzNotFoundException
	{
		int update=classService.endCallRollById(new BigInteger("3"), new BigInteger("1"));
		Assert.assertNotEquals(0,update);
	}

	/**
	 * 其实本质是update
	 * @throws ClazzNotFoundException 
	 */
	@Test
	public void testDeleteScoreRuleById() throws ClazzNotFoundException
	{
		BigInteger classId=new BigInteger("7");
		int update=classService.deleteScoreRuleById(classId);
		Assert.assertNotEquals(0,update);
	}
	
	/**
	 * 测试delete语句
	 * @throws ClazzNotFoundException 
	 */

	@Test
	public void testDeleteClassSelectionByClassId() throws ClazzNotFoundException
	{
		int delete=0;
		delete=classService.deleteClassSelectionByClassId(new BigInteger("3"));
		Assert.assertNotEquals(0,delete);
	}

	@Test
	public void testDeleteClassByClassId() throws ClazzNotFoundException
	{
		int delete=0;
		delete=classService.deleteClassByClassId(new BigInteger("2"));
		Assert.assertNotEquals(0,delete);
	}

	@Test
	public void testDeleteCourseSelectionById() throws UserNotFoundException, ClazzNotFoundException
	{
		int delete=0;
		BigInteger userId=new BigInteger("89");
		BigInteger classId=new BigInteger("2");
		delete=classService.deleteCourseSelectionById(userId, classId);
		Assert.assertNotEquals(0,delete);
	}

	@Test
	public void testDeleteClassByCourseId() throws CourseNotFoundException
	{
		int delete=0;
		BigInteger courseId=new BigInteger("3");
		delete=classService.deleteClassByCourseId(courseId);
		Assert.assertNotEquals(0,delete);
	}

}
