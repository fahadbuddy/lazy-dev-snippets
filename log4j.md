#Log4J Configuration

##Samples

###Multiple file configuration - log4j.properties

The following sample will
 * log everything in INFO level,
 * 2 packages in DEBUG level,
 * 2 packages to separate files,
 * everything else to STDOUT.

```Bash

log4j.rootCategory=INFO, R
log4j.logger.com.my.package=DEBUG, F
log4j.logger.com.your.package=DEBUG, G

#------------------------------------------------------------------------------
#  STDOUT
#------------------------------------------------------------------------------
log4j.appender.S = org.apache.log4j.ConsoleAppender
log4j.appender.S.layout = org.apache.log4j.PatternLayout
log4j.appender.S.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss.SSS} %-5p [%c{1}] %m%n

#------------------------------------------------------------------------------
#  FILE1
#------------------------------------------------------------------------------
log4j.appender.F = org.apache.log4j.DailyRollingFileAppender
log4j.appender.F.File = logs/log-file-1.log
log4j.appender.F.Append = true
log4j.appender.F.layout = org.apache.log4j.PatternLayout
log4j.appender.F.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss.SSS} %-5p (%t) [%c{1}] %m%n

#------------------------------------------------------------------------------
#  FILE2
#------------------------------------------------------------------------------
log4j.appender.G = org.apache.log4j.DailyRollingFileAppender
log4j.appender.G.File = logs/log-file-2.log
log4j.appender.G.Append = true
log4j.appender.G.layout = org.apache.log4j.PatternLayout
log4j.appender.G.layout.ConversionPattern = %d{yyyy-MM-dd HH:mm:ss.SSS} %-5p (%t) [%c{1}] %m%n
```

###Simple Java configuration

The following sample will
 * Configure most simple logger and not show a warning message

If you've seen

```Text
log4j:WARN No appenders could be found for logger (org.zeroturnaround.Akt2).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
```

then the following sample will help you.

```Java
public class MyClass {
  private static final Logger log = Logger.getLogger(MyClass.class);
  static {
    BasicConfigurator.configure();
  }
}
```
