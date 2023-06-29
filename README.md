# sb3cce
Demo app to reproduce ClassCastException on beanFactory race condition

## How to reproduce

- Build it: `mvn clean package`
- Run it: `./runForver.sh | grep java.lang.ClassCastException`

## What is happening

Spring will fail eventually with:

```
java.lang.ClassCastException: class java.lang.Class cannot be cast to class java.lang.String (java.lang.Class and java.lang.String are in module java.base of loader 'bootstrap')
....
```

## Analyzes so far

This bug is reproducable in Spring-Boot-Versions 3.0.3 till 3.0.8 and even 3.1.1.

This Bug will not happen in versions prior to 3.

## What the demo does

The app starts several threads all accessing the beanFactory the get the same bean.
