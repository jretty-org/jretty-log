zollty-log
==========

The ZolltyLog is a simple, generic and flexible logging library for Java, which can work with other logging frameworks, like log4j or logback.

### Easy to use 
	For example:
```java
  public static final Logger LOG = LogFactory.getLogger(); // Concise

  LOG.error("Hello {}, welcome to {}", "GUYS", "ZolltyLog Demo"); // use placeholder
```
### Enhancements 
```java
  if( LogFactory.isEnableFor(THIS_PLACE_OR_METHOD_OR_CLASS) ) { // Local log level control
    LOG.error(e, "Some additional tips....");
  }
```
###  Some advanced usages 
	See it's document later.   