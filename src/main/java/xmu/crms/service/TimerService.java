package xmu.crms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * 定时器
 *
 * @author qinlingyun liuaiqi
 * @version 2.10
 */
public interface TimerService {

    /**
     * 向Event表插入数据.
     * @author qinlingyun
     * @param time 事件的时间
     * @param beanName 对象名
     * @param methodName 方法名
     * @param paramList 方法参数
     * @throws JsonProcessingException 异常
     */
    void insertEvent(Date time, String beanName, String methodName, List<Object> paramList) throws JsonProcessingException;

    /**
     * 更新Event表.
     * @author qinlingyun
     * @param eventId 事件的ID
     * @param newTime 需要修改的时间
     */
    void updateEvent(BigInteger eventId, Date newTime);


    /**
     * 每十分钟检查一次Event实体的状况
     * @author qinlingyun
     * 注解：@Scheduled(fixedRate = 1000*60*10)
     */

    void scheduled();

}