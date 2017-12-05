package xmu.crms.service;

import xmu.crms.entity.Grade;
import xmu.crms.view.VO.GroupDetailsVO;

public interface GroupService {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	GroupDetailsVO getGroupById(int id, boolean embedTopics, boolean embedGrade);

	/**
	 * 队长辞职
	 * @param groupId
	 * @param studentId
	 * @return 操作是否成功
	 */
	Boolean resignGroupLeader(int groupId, int studentId);

	/**
	 * 指定队长
	 * @param groupId
	 * @param studentId
	 * @return 是否成功
	 */
	Boolean assignGroupLeader(int groupId, int studentId);

	/**
	 * 添加组员
	 * @param groupId
	 * @param studentId
	 * @return 是否成功
	 */
	Boolean addGroupMember(int groupId, int studentId);

	/**
	 * 移除组员
	 * @param groupId
	 * @param studentId
	 * @return 是否成功
	 */
	Boolean removeGroupMember(int groupId, int studentId);
	
	/**
	 * 选择话题
	 * @param groupId topicId
	 * @return 是否成功
	 */
	String selectTopic(int groupId, int topicId);
	
	/**
	 * 取消选择话题
	 * @param groupId
	 * @return 是否成功
	 */
	Boolean deselectTopic(int groupId, int topicId);
	
	/**
	 * 按Id获取小组讨论课成绩
	 * @param groupId
	 * @return
	 */
	Grade getGradeByGroupId(int groupId);
	
	/**
	 * 设置小组报告分
	 * @param grade
	 * @return
	 */
	Boolean finalGradeByGroupId(int groupId, int grade);

	/**
	 * 提交对其他小组的打分
	 * @param groupId
	 * @param studentId
	 * @return
	 */
	Boolean submitGradeByGroupId(int groupId, int studentId);
}
