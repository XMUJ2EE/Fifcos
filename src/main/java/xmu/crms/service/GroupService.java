package xmu.crms.service;

import xmu.crms.entity.Group;

public interface GroupService {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Group getGroupById(int id);
	
	Boolean resignGroupLeader(int groupid, int studentId);
	
	Boolean assignGroupLeader(int groupid, int studentId);
	
	Boolean addGroupMember(int groupid, int studentId);
	
	Boolean removeGroupMember(int groupid, int studentId);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Boolean selectTopic(int id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Boolean deselectTopic(int id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	int getGradeByGroupId(int id);
	
	/**
	 * 
	 * @param grade
	 * @return
	 */
	int finalGradeByGroupId(int grade);
	
	
}
