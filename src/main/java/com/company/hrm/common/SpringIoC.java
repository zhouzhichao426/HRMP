package com.company.hrm.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIoC {
    public static ApplicationContext getContext(){
        return new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
