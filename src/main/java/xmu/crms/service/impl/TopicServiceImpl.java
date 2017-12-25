package xmu.crms.service.impl;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmu.crms.dao.TopicDao;
import xmu.crms.entity.SeminarGroupTopic;
import xmu.crms.entity.Topic;
import xmu.crms.exception.TopicNotFoundException;
import xmu.crms.mapper.TopicMapper;
import xmu.crms.service.GradeService;
import xmu.crms.service.TopicService;

/**
 * @author HuXingBo
 */
@Service("TopicService")
public class TopicServiceImpl implements TopicService{
	@Autowired
	private TopicDao topicDao;

	/**
     * 按topicId获取topic.
     * <p>按topicId获取topic<br>
     *
     * @param topicId 要获取的topic的topicId
     * @return 该topic
     * @throws TopicNotFoundException   无此小组或Id错误
     * @throws IllegalArgumentException Id格式错误时抛出
     * @author aixing
     */
	@Override
	public Topic getTopicByTopicId(BigInteger topicId) throws TopicNotFoundException, IllegalArgumentException {
		return topicDao.getTopicByTopicId(topicId);
	}

	/**
     * 根据topicId修改topic.
     * <p>根据topicId修改topic<br>
     *
     * @param topicId 讨论课的ID
     * @param topic   修改后的讨论课
     * @throws TopicNotFoundException   无此小组或Id错误
     * @throws IllegalArgumentException Id格式错误或topic格式错误时抛出
     * @author aixing
     */
	@Override
	public int updateTopicByTopicId(BigInteger topicId, Topic topic)
			throws TopicNotFoundException, IllegalArgumentException {
		return topicDao.updateTopicByTopicId(topicId, topic);
	}

	/**
     * 删除topic.
     * <p>删除topic表中相应讨论课的topic<br>
     * 
     * @param topicId 要删除的topic的topicId
     * @throws IllegalArgumentException Id格式错误时抛出
     * @throws TopicNotFoundException 未找到该话题
     * @author xingb
     */
	@Override
	public int deleteTopicByTopicId(BigInteger topicId) throws IllegalArgumentException, TopicNotFoundException {
		return topicDao.deleteTopicByTopicId(topicId);
	}

	/**
     * 按seminarId获取Topic.
     * <p>按seminarId获取Topic<br>
     *
     * @param seminarId 课程Id
     * @return null
     * @throws IllegalArgumentException Id格式错误时抛出
     * @author zhouzhongjun
     */
	@Override
	public List<Topic> listTopicBySeminarId(BigInteger seminarId) throws IllegalArgumentException {
		return topicDao.listTopicBySeminarId(seminarId);
	}

	/**
     * 根据讨论课Id和topic信息创建一个话题.
     * <p>根据讨论课Id和topic信息创建一个话题<br>
     *
     * @param seminarId 话题所属讨论课的Id
     * @param topic     话题
     * @return 新建话题后给topic分配的Id
     * @throws IllegalArgumentException Id格式错误或topic格式错误时抛出
     * @author aixing
     */
	@Override
	public BigInteger insertTopicBySeminarId(BigInteger seminarId, Topic topic) throws IllegalArgumentException {
		return topicDao.insertTopicBySeminarId(seminarId, topic);
	}

	/**
     * 小组取消选择话题.
     * <p>小组取消选择话题  <br>
     * <p>删除seminar_group_topic表的一条记录<br>
     *
     * @param groupId 小组Id
     * @param topicId 话题Id
     * @throws IllegalArgumentException groupId格式错误或topicId格式错误时抛出
     * @author zhouzhongjun
     */
	@Override
	public int deleteSeminarGroupTopicById(BigInteger groupId, BigInteger topicId) throws IllegalArgumentException {
		return topicDao.deleteSeminarGroupTopicById(groupId, topicId);
	}

	/**
     * 按topicId删除SeminarGroupTopic表信息.
     * <p>删除seminar_group_topic表中选择了某个话题的全部记录<br>
     * @param topicId 讨论课Id
     * @throws IllegalArgumentException topicId格式错误
     * @author zhouzhongjun
     */
	@Override
	public int deleteSeminarGroupTopicByTopicId(BigInteger topicId) throws IllegalArgumentException {
		return topicDao.deleteSeminarGroupTopicByTopicId(topicId);
	}

	/**
     * 按话题id和小组id获取讨论课小组选题信息（包括该小组该话题展示成绩）
     * <p>按话题id和小组id获取讨论课小组选题信息（包括该小组该话题展示成绩）<br>
     * @param topicId
     * @param groupId
     * @return SeminarGroupTopic
     * @throws IllegalArgumentException
     * @author xingb
     */
	@Override
	public SeminarGroupTopic getSeminarGroupTopicById(BigInteger topicId, BigInteger groupId)
			throws IllegalArgumentException {
		return topicDao.getSeminarGroupTopicById(topicId, groupId);
	}

	/**
     * 根据小组id获取该小组该堂讨论课所有选题信息
     * <p>根据小组id获取该小组该堂讨论课所有选题信息<br>
     * @param groupId
     * @return List<SeminarGroupTopic> 该小组该堂讨论课选题列表
     * @throws IllegalArgumentException groupId格式错误
     * @author xingb
     */
	@Override
	public List<SeminarGroupTopic> listSeminarGroupTopicByGroupId(BigInteger groupId) throws IllegalArgumentException {
		return topicDao.listSeminarGroupTopicByGroupId(groupId);
	}

	/**
     * 按seminarId删除话题.
     * <p>删除某讨论课下的所有Topic<br>
     * <p>根据seminarId获得topic信息，然后再根据topic删除seninargrouptopic信息和根据seminarGroupTopicId删除StudentScoreGroup信息，最后再根据删除topic信息<br>
     *
     * @param seminarId 讨论课Id
     * @throws IllegalArgumentException seminarId格式错误
     * @author zhouzhongjun
     * @see TopicService #listTopicBySeminarId(BigInteger seminarId)
     * @see TopicService #deleteSeminarGroupTopicByTopicId(BigInteger topicId)
     * @see GradeService   #deleteStudentScoreGroupByTopicId(BigInteger seminarGroupTopicId)
     */
	@Override
	public int deleteTopicBySeminarId(BigInteger seminarId) throws IllegalArgumentException {
		return topicDao.deleteTopicBySeminarId(seminarId);
	}
	
}
