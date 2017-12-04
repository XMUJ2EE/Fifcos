package xmu.crms.service;

import xmu.crms.entity.Group;

public interface GroupService {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Group getGroupById(int id, boolean embedTopics, boolean embedGrade);

	/**
	 * 队长辞职
	 * @param groupid
	 * @param studentId
	 * @return 操作是否成功
	 */
	Boolean resignGroupLeader(int groupid, int studentId);

	/**
	 * 指定队长
	 * @param groupid
	 * @param studentId
	 * @return 是否成功
	 */
	Boolean assignGroupLeader(int groupid, int studentId);

	/**
	 * 添加组员
	 * @param groupid
	 * @param studentId
	 * @return 是否成功
	 */
	Boolean addGroupMember(int groupid, int studentId);

	/**
	 * 移除组员
	 * @param groupid
	 * @param studentId
	 * @return 是否成功
	 */
	Boolean removeGroupMember(int groupid, int studentId);
	
	/**
	 * 选择话题
	 * @param id
	 * @return 是否成功
	 */
	Boolean selectTopic(int id);
	
	/**
	 * 取消选择话题
	 * @param id
	 * @return 是否成功
	 */
	Boolean deselectTopic(int id);
	
	/**
	 * 按Id获取小组讨论课成绩
	 * @param id
	 * @return
	 */
	int getGradeByGroupId(int id);
	
	/**
	 * 设置小组报告分
	 * @param grade
	 * @return
	 */
	int finalGradeByGroupId(int grade);
	
	
}
