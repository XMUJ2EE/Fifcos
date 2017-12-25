package xmu.crms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class TimerServiceTest {
    @Autowired
    TimerService timerService;

    @Test
    public void testInsertEvent() throws JsonProcessingException {
        Integer insert = 0;
        List<Object> paramList=new ArrayList<>();
        paramList.add(new BigInteger("5"));
        paramList.add(new Date());
        timerService.insertEvent(new Date(),"TimerService","updateEvent",paramList);
    }

    @Test
    public void testUpdateEvent(){
        Integer update = 0;
        timerService.updateEvent(new BigInteger("11"),new Date());
    }

    @Test
    public void testScheduled() throws Exception {
        timerService.scheduled();
    }
}
