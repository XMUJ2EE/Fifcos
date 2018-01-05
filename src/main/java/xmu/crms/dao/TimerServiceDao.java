package xmu.crms.dao;

import xmu.crms.entity.Event;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author mads
 * @date 2018/1/5 11:29
 */
public interface TimerServiceDao {

    /**
     * 插入计时器事件
     * @param time
     * @param beanName
     * @param methodName
     * @param paramList
     */
    void insertEvent(Date time, String beanName, String methodName, List<Object> paramList);

    /**
     * 更新事件
     * @param eventId
     * @param newTime
     */
    void updateEvent(BigInteger eventId, Date newTime);


    /**
     * 选择事件
     * @return
     */
    List<Event> selectEvent();

    /**
     * 删除事件
     * @param id
     */
    void deleteEvent(BigInteger id);

}
