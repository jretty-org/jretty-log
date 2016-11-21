jretty-log
==========

The JrettyLog is a simple, generic and flexible logging library for Java, which can work with other logging frameworks, like log4j or logback. And it is a facade or abstraction for various logging frameworks (e.g. java.util.logging, logback, log4j) allowing the end user to plug in the desired logging framework at deployment time. It is not only a facade, but also provides some useful decorations for common logging frameworks, such as log monitoring, online log viewer, configuration dynamic refreshing (See it's Extended library [log-monitor](http://www.zollty.com/log-monitor)).

[http://www.jretty.com/jretty-log/](http://www.jretty.com/jretty-log/)

### Easy to use

For example:

```java
  public static final Logger LOG = LogFactory.getLogger(); // Concise definition

  LOG.error("Hello {}, welcome to {}", "GUYS", "JrettyLog Demo"); // use placeholder

```

###  Some advanced usages

See the [manual](http://blog.zollty.com/b/archive/2014/07/zollty-log-use-manual.html) and [docs](http://www.jretty.com/jretty-log/apidocs/).
