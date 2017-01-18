jretty-log
==========

The JrettyLog is a simple, generic and flexible logging library for Java, which can work with other logging frameworks, like log4j or logback. And it is a facade or abstraction for various logging frameworks (e.g. java.util.logging, logback, log4j) allowing the end user to plug in the desired logging framework at deployment time. It is not only a facade, but also provides some useful decorations for common logging frameworks, such as log monitoring, online log viewer, configuration dynamic refreshing (See it's Extended library [log-monitor](http://www.zollty.com/log-monitor)).

[http://www.jretty.com/jretty-log/](http://www.jretty.com/jretty-log/)


##	Characteristic

1.  fast, small and has no dependencies.
2.  concise api, easy to use.
3.  support all frequently-used loggers. like log4j, logback, commong-logging, jdk logger.
4.  support custom extensions. custum-made your own logger.
5.  support using with LogMonitor (a Web console， dynamic configuration and online view).


### Easy to use

For example:

```java
  public Logger logger = LogFactory.getLogger(); // Concise API

  // use placeholder, and it's more efficient than other loggers (log4j, logback...)
  logger.error("hello {}, now time is {}. ", "guys", new java.util.Date());
  
  if (logger.isTraceEnabled()) { // control code block
     int count = records.count();
     logger.trace("this poll got {} records.", count);
  }
  
  int id = 0;
  try {
    int b = 100 / id;
  } catch (Exception e) {
    // add additional tips for exception.
    logger.error(e, "Some additional tips: Unexpected exception occurred. The param id = {}.", id);
  }
  
```

###  Some advanced usages

See the [manual](http://blog.zollty.com/b/archive/2014/07/zollty-log-use-manual.html) and [docs](http://www.jretty.com/jretty-log/apidocs/).
