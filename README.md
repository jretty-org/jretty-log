jretty-log
==========

The JrettyLog is a simple, generic and flexible logging library for Java, which can work with other logging frameworks, like log4j or logback. And it is a facade or abstraction for various logging frameworks (e.g. java.util.logging, logback, log4j) allowing the end user to plug in the desired logging framework at deployment time. It is not only a facade, but also provides some useful decorations for common logging frameworks, such as log monitoring, online log viewer, configuration dynamic refreshing (See it's Extended library [log-monitor](http://www.zollty.com/log-monitor)).


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
  
  int id = 100;
  try {
      queryById(id);
  } catch (Exception e) {
      // add additional tips for exception.
      logger.error(e, 
        "Additional tips: unexpected exception occurred. The param id = {}.", id);
  }
  
```

###  Some advanced usages

See the [wiki](https://github.com/jretty-org/jretty-log/wiki) and [docs](http://www.jretty.com/jretty-log/apidocs/).




--------------------------

Jretty-log设计初衷
--------------------------

#### 1、在嵌入式项目中，需要独立的日志接口，不依赖于具体的日志实现


我们的项目，不能直接依赖第三方日志库（比如log4j、logback）。正是由于类似的需求，log4j的创始人于2012年又创造了日志门面工具slf4j，但当时由于slf4j使用得不多以及一些其他原因，我们没有选用它。


#### 2、我们需要动态的控制、监控日志的输出

需要在日志的入口处进行监控，做成像阿里数据源连接池Druid那样的在线监控。当时的方法一是直接改造log4j，然后重新打包。方法二是在log4j的基础上再包一层，不改动log4j就能控制所有的日志。我们选择了后者。这就是jretty-log的原型。jretty-log做出来后，首先兼容了log4j，并且在配套的log-monitor中，能够对log4j进行全面的监控，比如，能够看到整个项目运行时的所有logger及状态，能够对这些logger的输出级别进行精确控制，能够统计日志输出的次数、频率等。

#### 3、提高日志库的易用性和性能

时值2013年，日志记录工具仍然以log4j 1.2为主流，但是logback+slf4j崛起，主打性能的提升。同样，作为一个全新的日志工具，jretty-log也在前辈log4j的基础上，尽最大力量提高性能。要知道，日志API作为使用非常频繁的API，即便有丝微的性能提高，都是很可观的。同样，这么高频使用的API，易用性更是非常重要。

  1. jretty-log改进了日志API，记录方式更易用，比如 Logger log = LogFactory.getLogger()，通过反射自动获取类名作为loggre名称；比如log.error(e, msgPattern, args...)，这个API是全新设计的，能够给异常堆栈信息添加额外的说明，比如记录发生异常时的参数信息，实用指数5颗星。
  2. jretty-log改进了日志API，记录方式更高效，比如 logger.debug(msgPattern, arg...) 配合 logger.debug(msgPattern, arg1, arg2)，让2个参数的调用无需再转换成数组形式，有一定性能优势。
  3. jretty-log配置简单，支持自动识别项目中的日志框架（log4j、logback），可以零配置，支持配置的扩展（比如从数据库，或者远程的zk配置中心获取配置）
  4. jretty-log扩展性极强，不但天生支持日志监控，还支持自定义logger wrapper，日志拦截、过滤之类的功能非常灵活。





