package xmu.crms.service;

import java.util.List;

import xmu.crms.entity.Topic;

import xmu.crms.view.VO.TopicPartVO;
import xmu.crms.view.vo.GroupVO;


public interface TopicService {
	
	/**
	 * 根据topicId获取topic的内容
	 * @param topicId
	 * @return 查询到的Topic对象
	 */
	Topic getTopicById(int topicId);
	
	/**
	 * 
	 * 根据topicId更新topic
	 * @param topicId
	 * @return 更新结果的bool值
	 */
	
	Boolean updateTopicById(int topicId, TopicPartVO topicPartVO);
	
	/**
	 * 
	 * 根据topicId删除topic
	 * @param topicId
	 * @return 删除结果的bool值
	 */
	
	Boolean deleteTopicById(int topicId);
	
	/**
	 * 根据topicId获取分组列表
	 * @param topicId
	 * @return 查询到的groupList
	 */
	List<GroupVO> getGroupsByTopicId(int topicId);
}
