package xmu.crms.service;

import java.math.BigInteger;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import xmu.crms.entity.SeminarGroupTopic;
import xmu.crms.entity.Topic;
import xmu.crms.exception.TopicNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class TopicServiceTest {
	@Autowired
	private TopicService topicService;
	
	@Test
	public void testGetTopicByTopicId() throws IllegalArgumentException, TopicNotFoundException {
		Topic topic=topicService.getTopicByTopicId(new BigInteger("3"));
		Assert.assertNotNull(topic);
		System.out.println(topic.toString());
	}
	
	@Test
	public void testUpdateTopicByTopicId() throws IllegalArgumentException, TopicNotFoundException {
		Topic topic=topicService.getTopicByTopicId(new BigInteger("3"));
		topic.setSerial("A");
		topic.setName("话题15");
		topic.setDescription("话题说明7");
		topic.setGroupNumberLimit(9);
		topic.setGroupStudentLimit(5);
		Assert.assertNotEquals(0,topicService.updateTopicByTopicId(new BigInteger("3"), topic));
	}
	
	@Test
	@Rollback(true)
	public void testDeleteTopicByTopicId() throws IllegalArgumentException, TopicNotFoundException {
		Assert.assertNotEquals(0,topicService.deleteTopicByTopicId(new BigInteger("3")));
	}
	
	@Test
	public void testListTopicBySeminarId() {
		List<Topic> topics=topicService.listTopicBySeminarId(new BigInteger("2"));
		Assert.assertNotNull(topics.get(0));
		System.out.println(topics.toString());
	}
	
	@Test
	public void testInsertTopicBySeminarId() {
		Topic topic=new Topic();
		topic.setSerial("B");
		topic.setName("话题7");
		topic.setDescription("话题7描述");
		topic.setGroupNumberLimit(4);
		topic.setGroupStudentLimit(5);
		BigInteger insert=topicService.insertTopicBySeminarId(new BigInteger("3"), topic);
		System.out.println(topic.getId());
		System.out.println(insert);
		Assert.assertNotNull(insert);
	}
	
	@Test
	public void testDeleteSeminarGroupTopicById() {
		Assert.assertNotEquals(0,topicService.deleteSeminarGroupTopicById(new BigInteger("18"), new BigInteger("2")));
	}
	
	@Test
	public void testDeleteSeminarGroupTopicByTopicId() {
		Assert.assertNotEquals(0,topicService.deleteSeminarGroupTopicByTopicId(new BigInteger("4")));
	}
	
	@Test
	public void testGetSeminarGroupTopicById() {
		SeminarGroupTopic seminarGroupTopic=topicService.getSeminarGroupTopicById(new BigInteger("3"), new BigInteger("19"));
		Assert.assertNotNull(seminarGroupTopic);
		System.out.println(seminarGroupTopic);
	}
	
	@Test
	public void testListSeminarGroupTopicByGroupId() {
		List<SeminarGroupTopic> seminarGroupTopics=topicService.listSeminarGroupTopicByGroupId(new BigInteger("1"));
		Assert.assertNotNull(seminarGroupTopics);
		System.out.println(seminarGroupTopics.toString());
	}
	
	@Test
	@Rollback(true)
	public void testDeleteTopicBySeminarId() {
		Assert.assertNotEquals(0,topicService.deleteTopicBySeminarId(new BigInteger("2")));
	}
	
}
