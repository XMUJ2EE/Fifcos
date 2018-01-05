package xmu.crms.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import xmu.crms.dao.TimerServiceDao;
import xmu.crms.dao.impl.TimerServiceDaoImpl;
import xmu.crms.entity.Event;
import xmu.crms.service.TimerService;
import xmu.crms.util.SpringContextsUtil;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;

/**
 * @author YangYouran
 * @date 12.24
 */
@Service
@Component
@Lazy(false)
public class TimerServiceImpl implements TimerService {

    @Autowired
    TimerServiceDao timerServiceDao;

    @Autowired
    private SpringContextsUtil springContextsUtil;

    /**
     * 向Event表插入数据
     * @param time 事件的时间
     * @param beanName 对象名
     * @param methodName 方法名
     * @param paramList 方法参数
     * @throws JsonProcessingException 异常
     */
    @Override
    public void insertEvent(Date time, String beanName, String methodName, List<Object> paramList) throws JsonProcessingException {
        System.out.println(time.toString()+beanName+methodName+paramList.toString());
        timerServiceDao.insertEvent(time,beanName,methodName,paramList);
    }

    /**
     * 更新Event表
     * @param eventId 事件的ID
     * @param newTime 需要修改的时间
     */
    @Override
    public void updateEvent(BigInteger eventId, Date newTime) {
        timerServiceDao.updateEvent(eventId, newTime);
    }

    /**
     * 每十分钟检查一次Event实体的状况
     * 查找要执行的方法
     * 执行
     * 删除数据库中该记录
     */
    @Override
    @Scheduled(fixedRate = 1000*60*10)
    public void scheduled() {
        List<Event> eventList=timerServiceDao.selectEvent();
        System.out.println(eventList);
        for (Event e:eventList) {
            ObjectMapper m = new ObjectMapper();
            m.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, "type");
            try {
                Object[] args= m.readValue(e.getParameter(), Object[].class);
                Class[] argsClass=new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    argsClass[i]=args[i].getClass();
                }
                Method method = ReflectionUtils.findMethod(springContextsUtil.getBean(e.getBeanName()).getClass(), e.getMethodName(), argsClass);
                ReflectionUtils.invokeMethod(method, springContextsUtil.getBean(e.getBeanName()), args);
                timerServiceDao.deleteEvent(e.getId());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
