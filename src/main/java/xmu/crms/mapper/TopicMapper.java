package xmu.crms.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import xmu.crms.entity.SeminarGroupTopic;
import xmu.crms.entity.Topic;

/**
 * @author HuXingBo
 */
public interface TopicMapper {
	
	/**
	 * ServiceFun:getTopicByTopicId
	 * 根据topicId获取Topic实体
	 * @param topicId
	 * @return Topic
	 * @author xingb
	 */
	public Topic getTopicByTopicId(BigInteger topicId);
	
	/**
	 * ServiceFun:updateTopicByTopicId
	 * 根据topicId更新topic表
	 * @param topicId
	 * @param topic
	 * @return int 更新的行数
	 * @author xingb
	 */
	public int updateTopicByTopicId(@Param("topicId")BigInteger topicId,@Param("topic")Topic topic);
	
	/**
	 * ServiceFun:deleteTopicByTopicId
	 * Step:1
	 * 执行deleteTopicByTopicId删除topic时，要将选了该topic的小组的打分记录删除，此前需要查找需要删哪些
	 * 使用deleteStudentScoreGroupById前，需要查询要删除的seminarGroupTopicId
	 * @param topicId
	 * @return List<BigInteger>
	 * @author xingb
	 */
	public List<BigInteger> getSeminarGroupTopicIdByTopicId(@Param("topicId")BigInteger topicId);
	
	/**
	 * ServiceFun:deleteTopicByTopicId
	 * Step:2
	 * 执行deleteTopicByTopicId删除topic时，要将选了该topic的小组的打分记录删除
	 * 根据查找到的seminarGroupTopicIds删除
	 * @param seminarGroupTopicIds
	 * @return Boolean
	 * @author xingb
	 */
	public int deleteStudentScoreGroupById(@Param("seminarGroupTopicIds")List<BigInteger> seminarGroupTopicIds);
	
	/**
	 * ServiceFun:deleteTopicByTopicId
	 * Step:3
	 * 执行deleteTopicByTopicId删除topic时，要将选了该topic的小组的记录删除
	 * 使用ServiceFun:deleteSeminarGroupTopicByTopicId即可
	 */
	
	/**
	 * ServiceFun:deleteTopicByTopicId
	 * Step:4
	 * 执行deleteTopicByTopicId删除topic时，还要进行其它相关表记录的删除
	 * 进行完上述删除后再删除topic
	 * @param topicId
	 * @return Boolean
	 * @author xingb
	 */
	public int deleteTopicByTopicId(@Param("topicId")BigInteger topicId);
	
	/**
	 * ServiceFun:listTopicBySeminarId
	 * 根据seminarId获取Topic实体列表
	 * @param seminarId
	 * @return List<Topic>
	 * @author xingb
	 */
	public List<Topic> listTopicBySeminarId(BigInteger seminarId);
	
	/**
	 * ServiceFun:insertTopicBySeminarId
	 * 根据seminarId增加记录
	 */
	
	/**
	 * ServiceFun:insertTopicBySeminarId
	 * 根据seminarId增加记录
	 * @param seminarId
	 * @param topic
	 * @return int 插入的行数
	 * @author xingb
	 */
	public int insertTopicBySeminarId(@Param("seminarId")BigInteger seminarId,@Param("topic")Topic topic);
	
	/**
	 * ServiceFun:deleteSeminarGroupTopicById
	 * 删除seminar_group_topic表的记录
	 * @param groupId
	 * @param topicId
	 * @return Boolean
	 * @author xingb
	 */
	public int deleteSeminarGroupTopicById(@Param("groupId")BigInteger groupId,@Param("topicId")BigInteger topicId);
	
	/**
	 * ServiceFun:deleteTopicById
	 * 按topicId删除SeminarGroupTopic表信息
	 * @param topicId
	 * @return Boolean
	 * @author xingb
	 */
	public int deleteSeminarGroupTopicByTopicId(BigInteger topicId);
	
	/**
	 * ServiceFun:listSeminarGroupTopicByGroupId
	 * 根据小组id获取该小组该堂讨论课所有选题信息
	 * @param groupId
	 * @return List<SeminarGroupTopic>
	 * @author xingb
	 */
	public List<SeminarGroupTopic> listSeminarGroupTopicByGroupId(BigInteger groupId);
	
	/**
	 * ServiceFun:getSeminarGroupTopicById
	 * 按话题id和小组id获取讨论课小组选题信息
	 * @param topicId
	 * @param groupId
	 * @return SeminarGroupTopic
	 * @author xingb
	 */
	public SeminarGroupTopic getSeminarGroupTopicById(@Param("topicId")BigInteger topicId, @Param("groupId")BigInteger groupId);
	
	/**
	 * ServiceFun:deleteTopicBySeminarId
	 * 即public Boolean deleteTopicBySeminarId(BigInteger seminarId);
	 * 按seminarId删除话题，可以先找到要删除的话题，然后再一个一个删除
	 * getTopicBySeminarId获取要找的topicIds
	 */

}
