package com.xinyou.dome.service.impl;

import com.xinyou.dome.DomeApplication;
import com.xinyou.dome.entity.SequenceEntity;
import com.xinyou.dome.service.IdService;
import com.xinyou.dome.service.UserService;
import com.xinyou.dome.template.CallResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2018/11/917:33
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DomeApplication.class})
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private IdService idService;

    @Test
    public void queryUser() {
        CallResult<String> result = userService.queryUser("123");
        System.out.println(result.getMsg());
        System.out.println(result.getResultObject());
    }

    @Test
    public void queryId() throws Exception{
        for (int i = 0; i < 2500; i++) {
            System.out.println(idService.getId());
//            Thread.sleep(5);
        }
    }


    @Test
    public void test(){
        SequenceEntity sequenceEntity = new SequenceEntity();
        sequenceEntity.setId(1);
        sequenceEntity.setMaxValue(10);
        sequenceEntity.setStep(100);
        System.out.println("0:"+sequenceEntity.getMaxValue());
        SequenceEntity se = sequenceEntity;
        se.setMaxValue(200);
        System.out.println("1:"+se.getMaxValue());

        System.out.println("2:"+sequenceEntity.getMaxValue());
    }
}