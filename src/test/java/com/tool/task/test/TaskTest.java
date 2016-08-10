package com.tool.task.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TaskTest {

    @Test
    public void testTask() throws InterruptedException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Thread.sleep(20000);
    }

}
