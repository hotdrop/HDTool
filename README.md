## Overview
It reads the stackTraceLog output by log4j, date and exception type and exception location the exception number of occurrences of the day to CSV output.

## Requirement
* Java8(build 1.8.0_60)

## output example
2016-2-23,IOException,jp.ojt.test.TestFile.<init>(TestFile.java:24),XXX message,4  
2016-2-23,NullPointerException,jp.ojt.test.TestFile.createFile(TestFile.java:24),YYY message,1
