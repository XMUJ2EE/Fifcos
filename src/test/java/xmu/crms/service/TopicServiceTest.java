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
		Topic topic=topicService.getTopicByTopicId(new BigInteger("1"));
		Assert.assertNotNull(topic);
	}
	
	@Test
	public void testUpdateTopicByTopicId() throws IllegalArgumentException, TopicNotFoundException {
		Topic topic=topicService.getTopicByTopicId(new BigInteger("15"));
		topic.setName("话题15");
		topic.setDescription("话题15描述");
		topic.setGroupNumberLimit(9);
		topic.setGroupStudentLimit(5);
		//Assert.assertEquals(true,topicService.updateTopicByTopicId(new BigInteger("15"), topic));
	}
	
	@Test
	@Rollback(true)
	public void testDeleteTopicByTopicId() {
		//Assert.assertEquals(true,topicService.deleteTopicByTopicId(new BigInteger("4")));
	}
	
	@Test
	public void testListTopicBySeminarId() {
		List<Topic> topics=topicService.listTopicBySeminarId(new BigInteger("1"));
		Assert.assertNotNull(topics.get(0));	
	}
	
	@Test
	public void testInsertTopicBySeminarId() {
		Topic topic=new Topic();
		topic.setName("话题14");
		topic.setDescription("话题14描述");
		topic.setGroupNumberLimit(4);
		topic.setGroupStudentLimit(5);
		BigInteger insert=topicService.insertTopicBySeminarId(new BigInteger("1"), topic);
		System.out.println(topic.getId());
		System.out.println(insert);
		Assert.assertNotNull(insert);
	}
	
	@Test
	public void testDeleteTopicById() {
		//Assert.assertEquals(true,topicService.deleteTopicById(new BigInteger("36"), new BigInteger("6")));
	}
	
	@Test
	public void testDeleteSeminarGroupTopicByTopicId() {
		//Assert.assertEquals(true,topicService.deleteSeminarGroupTopicByTopicId(new BigInteger("6")));
	}
	
	@Test
	public void testGetSeminarGroupTopicById() {
		SeminarGroupTopic seminarGroupTopic=topicService.getSeminarGroupTopicById(new BigInteger("1"), new BigInteger("1"));
		Assert.assertNotNull(seminarGroupTopic);
	}
	
	@Test
	public void testListSeminarGroupTopicByGroupId() {
		List<SeminarGroupTopic> seminarGroupTopics=topicService.listSeminarGroupTopicByGroupId(new BigInteger("1"));
		Assert.assertNotNull(seminarGroupTopics);
	}
	
	@Test
	@Rollback(true)
	public void testDeleteTopicBySeminarId() {
		//Assert.assertEquals(true,topicService.deleteTopicBySeminarId(new BigInteger("2")));
	}
	
	
}
