#Log4J Configuration

##Samples

###Simple log4j.properties

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