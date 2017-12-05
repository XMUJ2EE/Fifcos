package xmu.crms.service;

import org.springframework.stereotype.Service;
import xmu.crms.entity.Topic;
import xmu.crms.view.VO.TopicPartVO;
import xmu.crms.view.vo.GroupVO;


import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Override
    public Topic getTopicById(int topicId) {
        Topic topic = new Topic(257, "A", "领域模型与模块",
                "Domain model与模块划分", 5, 6, 2);
        return topic;
    }

    @Override
    public Boolean updateTopicById(int topicId, TopicPartVO topicPartVO) {
        return true;
    }

    @Override
    public Boolean deleteTopicById(int topicId) {
        return true;
    }

    @Override
    public List<GroupVO> getGroupsByTopicId(int topicId) {

        List<GroupVO> groupVOS = new ArrayList<GroupVO>();
        GroupVO groupVO = new GroupVO(23, "1A1");
        groupVOS.add(groupVO);

        groupVO.setId(26);
        groupVO.setName("2A2");

        groupVOS.add(groupVO);

        return groupVOS;
    }
}