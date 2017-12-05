package xmu.crms.service;

import xmu.crms.entity.Seminar;

/**
 * seminar 服务接口
 * @author Xuezhang.Liu
 */
public interface SeminarService {

    /**
     * 根据id获取讨论课详情
     * @param seminarId 讨论课id
     * @return seminar 对象
     */
    Seminar getSeminarById(int seminarId);

    /**
     * 根据id修改讨论课
     * @param seminarId 讨论课id
     * @param seminar 讨论课对象
     * @return 执行结果
     */
    Boolean updateSeminarById(int seminarId, Seminar seminar);

    /**
     * 根据id删除讨论课
     * @param seminarId 讨论课id
     * @return 执行结果
     */
    Boolean deleteSeminarById(int seminarId);

    
}
