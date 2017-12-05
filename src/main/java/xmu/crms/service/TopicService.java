package xmu.crms.service;

import java.util.List;

import xmu.crms.entity.Topic;
import xmu.crms.view.vo.GroupVO;

public interface TopicService {
	
	/**
	 * 根据topicId获取topic的内容
	 * @param id
	 * @return 查询到的Topic对象
	 */
	Topic getTopicById(int id);
	
	/**
	 * 
	 * 根据topicId更新topic
	 * @param id
	 * @return 更新结果的bool值
	 */
	
	Boolean updateTopicById(int id);
	
	/**
	 * 
	 * 根据topicId删除topic
	 * @param id
	 * @return 删除结果的bool值
	 */
	
	Boolean deleteTopicById(int id);
	
	/**
	 * 根据topicId获取分组列表
	 * @param id
	 * @return 查询到的groupList
	 */
	List<GroupVO> getGroupsByTopicId(int id);
}
