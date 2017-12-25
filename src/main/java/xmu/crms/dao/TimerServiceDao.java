package xmu.crms.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xmu.crms.entity.Event;
import xmu.crms.mapper.TimerMapper;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * @author YangYouran
 * @date 12.23
 */
@Component
public class TimerServiceDao {

    @Autowired(required = false)
    TimerMapper timerMapper;

    public void insertEvent(Date time, String beanName, String methodName, List<Object> paramList) {
        System.out.println("DAO"+time.toString()+beanName+methodName+paramList.toString());
        ObjectMapper m = new ObjectMapper();
        m.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, "type");
        String param = null;
        try {
            param = m.writeValueAsString(paramList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        timerMapper.insertEvent(time,beanName,methodName,param);
    }

    public void updateEvent(BigInteger eventId, Date newTime){
        timerMapper.updateEvent(eventId,newTime);
    }

    public List<Event> selectEvent(){
        return timerMapper.selectEvent(new Timestamp(System.currentTimeMillis()));
    }

    public void deleteEvent(BigInteger id){
        timerMapper.deleteEvent(id);
    }
}
