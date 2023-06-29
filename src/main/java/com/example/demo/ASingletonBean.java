package com.example.demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ASingletonBean {

    @Autowired
    private BeanFactory beanFactory;

    public void doIt() {
        final APrototypeBean prototypeBean = beanFactory.getBean(APrototypeBean.class);
        final long value = prototypeBean.doIt();
    }
}
