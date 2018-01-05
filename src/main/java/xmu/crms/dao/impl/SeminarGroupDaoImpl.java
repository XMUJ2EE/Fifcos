package xmu.crms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.crms.dao.SeminarGroupDao;
import xmu.crms.entity.*;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.mapper.SeminarGroupMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author mads
 * @date 2018/1/5 12:00
 */
@Repository("SeminarGroupDao")
public class SeminarGroupDaoImpl implements SeminarGroupDao {

    @Autowired(required = false)
    SeminarGroupMapper seminarGroupMapper;

    @Override
    public void insertSeminarGroupMemberById(BigInteger userId, BigInteger groupId) {
        seminarGroupMapper.insertSeminarGroupMemberById(userId, groupId);
    }

    @Override
    public int insertSeminarGroupMemberByGroupId(BigInteger groupId, SeminarGroupMember seminarGroupMember) {
        return seminarGroupMapper.insertSeminarGroupMemberByGroupId(groupId, seminarGroupMember);
    }

    @Override
    public List<SeminarGroupTopic> listGroupByTopicId(BigInteger topicId) throws IllegalArgumentException, GroupNotFoundException {
        return seminarGroupMapper.listGroupByTopicId(topicId);
    }

    @Override
    public String insertTopicByGroupId(BigInteger groupId, BigInteger topicId) throws IllegalArgumentException, GroupNotFoundException {
        return seminarGroupMapper.insertTopicByGroupId(groupId, topicId);
    }

    @Override
    public boolean resignLeaderById(BigInteger groupId, BigInteger userId) throws IllegalArgumentException, GroupNotFoundException {
        return seminarGroupMapper.resignLeaderById(groupId, userId);
    }

    @Override
    public boolean deleteTopicByGroupId(BigInteger groupId) throws GroupNotFoundException {
        return seminarGroupMapper.deleteTopicByGroupId(groupId);
    }

    @Override
    public void assignLeaderById(BigInteger groupId, BigInteger userId) {
        seminarGroupMapper.assignLeaderById(groupId, userId);
    }

    @Override
    public void automaticallyGrouping(BigInteger seminarId, BigInteger classId) {
        seminarGroupMapper.automaticallyGrouping(seminarId, classId);
    }

    @Override
    public void deleteSeminarGroupByGroupId(BigInteger seminarGroupId) {
        seminarGroupMapper.deleteSeminarGroupByGroupId(seminarGroupId);
    }

    @Override
    public void deleteSeminarGroupBySeminarId(BigInteger seminarId) {
        seminarGroupMapper.deleteSeminarGroupBySeminarId(seminarId);
    }

    @Override
    public void deleteSeminarGroupMemberBySeminarGroupId(BigInteger seminarGroupId) {
        seminarGroupMapper.deleteSeminarGroupMemberBySeminarGroupId(seminarGroupId);
    }

    @Override
    public SeminarGroup getSeminarGroupByGroupId(BigInteger groupId) {
        return seminarGroupMapper.getSeminarGroupByGroupId(groupId);
    }

    @Override
    public BigInteger getSeminarGroupIdBySeminarIdAndUserId(BigInteger seminarId, BigInteger userId) {
        return seminarGroupMapper.getSeminarGroupIdBySeminarIdAndUserId(seminarId, userId);
    }

    @Override
    public BigInteger getSeminarGroupLeaderByGroupId(BigInteger groupId) {
        return seminarGroupMapper.getSeminarGroupLeaderByGroupId(groupId);
    }

    @Override
    public List<SeminarGroupMember> listSeminarGroupMemberByGroupId(BigInteger groupId) {
        return seminarGroupMapper.listSeminarGroupMemberByGroupId(groupId);
    }

    @Override
    public int insertSeminarGroupBySeminarId(SeminarGroup seminarGroup) {
        return seminarGroupMapper.insertSeminarGroupBySeminarId(seminarGroup);
    }

    @Override
    public List<SeminarGroup> listSeminarGroupBySeminarId(BigInteger seminarId) {
        return seminarGroupMapper.listSeminarGroupBySeminarId(seminarId);
    }

    @Override
    public BigInteger getUserIdByUserId(BigInteger userId) {
        return seminarGroupMapper.getUserIdByUserId(userId);
    }

    @Override
    public void deleteSeminarGroupMemberByuId(BigInteger grouId, BigInteger userId) {
        seminarGroupMapper.deleteSeminarGroupMemberByuId(grouId,userId);
    }

    @Override
    public BigInteger getSeminarIdBySeminarId(BigInteger seminarId) {
        return seminarGroupMapper.getSeminarIdBySeminarId(seminarId);
    }

    @Override
    public List<BigInteger> listStudentIdBySeminarIdAndClassId(BigInteger seminarId, BigInteger classId) {
        return seminarGroupMapper.listStudentIdBySeminarIdAndClassId(seminarId, classId);
    }

    @Override
    public List<SeminarGroupMember> listSeminarGroupIdByStudentId(BigInteger studentId) {
        return seminarGroupMapper.listSeminarGroupIdByStudentId(studentId);
    }

    @Override
    public List<BigInteger> listGroupIdByTopicId(BigInteger topicId) {
        return seminarGroupMapper.listGroupIdByTopicId(topicId);
    }

    @Override
    public ClassInfo getSeminarById(BigInteger id) {
        return seminarGroupMapper.getSeminarById(id);
    }

    @Override
    public ClassInfo getClassInfoById(BigInteger id) {
        return seminarGroupMapper.getClassInfoById(id);
    }

    @Override
    public Course getCourseById(BigInteger id) {
        return seminarGroupMapper.getCourseById(id);
    }

    @Override
    public School getSchoolById(BigInteger id) {
        return seminarGroupMapper.getSchoolById(id);
    }

    @Override
    public Topic getTopicById(BigInteger id) {
        return seminarGroupMapper.getTopicById(id);
    }

    @Override
    public SeminarGroup getSeminarGroupById(BigInteger id) {
        return seminarGroupMapper.getSeminarGroupById(id);
    }

    @Override
    public User getUserById(BigInteger id) {
        return seminarGroupMapper.getUserById(id);
    }

    @Override
    public SeminarGroupMember getSeminarGroupMemberByStudentIdAndSeminarGroupId(BigInteger studentId, BigInteger seminarGroupId) {
        return seminarGroupMapper.getSeminarGroupMemberByStudentIdAndSeminarGroupId(studentId, seminarGroupId);
    }

    @Override
    public void insertSeminarGroupTopicByTopicIdAndSeminarGroupId(BigInteger topicId, BigInteger seminarGroupId) {
        seminarGroupMapper.insertSeminarGroupTopicByTopicIdAndSeminarGroupId(topicId, seminarGroupId);
    }
}
