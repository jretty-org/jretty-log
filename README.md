zollty-log
==========

The ZolltyLog is a simple, generic and flexible logging library for Java, which can work with other logging frameworks, like log4j or logback.

1. Easy to use
	For example:

	public static final Logger LOG = LogFactory.getLogger(); // Concise
	
	LOG.error("Hello {}, welcome to {}", "GUYS", "ZolltyLog Demo"); // use placeholder

2. Enhancements

	if( LogFactory.isEnableFor(THIS_METHOD_OR_CLASS_OR_PLACE) ){
		LOG.error(e, "Some additional tips....");
	}

3. Some advanced usages

	See it's document later.
