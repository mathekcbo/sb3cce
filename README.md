# sb3cce
Demo app to reproduce ClassCastException on beanFactory race condition

## How to reproduce

- Build it: `mvn clean package`
- Run it: `./runForver.sh | grep java.lang.ClassCastException`

## What is happening

Spring will fail eventually with:

```
Exception in thread "Thread-2" java.lang.ClassCastException: class java.lang.Class cannot be cast to class java.lang.String (java.lang.Class and java.lang.String are in module java.base of loader 'bootstrap')
        at org.springframework.beans.factory.support.AbstractBeanDefinition.getBeanClassName(AbstractBeanDefinition.java:393)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doResolveBeanClass(AbstractBeanFactory.java:1535)
        at org.springframework.beans.factory.support.AbstractBeanFactory.resolveBeanClass(AbstractBeanFactory.java:1502)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:492)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:344)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:225)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveNamedBean(DefaultListableBeanFactory.java:1310)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveNamedBean(DefaultListableBeanFactory.java:1271)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveBean(DefaultListableBeanFactory.java:484)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:339)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:332)
....
```

## Analyzes so far

This bug is reproducable in Spring-Boot-Versions 3.0.3 till 3.0.8 and even 3.1.1.

This Bug will not happen in versions prior to 3.

## What the demo does

The app starts several threads all accessing the beanFactory the get the same bean.
