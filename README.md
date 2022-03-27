# logger
a command line logger <br />
mvn clean install will build and test (unit and integration)<br />

with the artifact (logger jar) you can write you msg to myLogFile.txt<br />

e.g<br />
java -cp target\*  logger.MyLog e aaa<br />
java -cp target\*  logger.MyLog ERROR bbb<br />
java -cp target\*  logger.MyLog ccc<br />
java -cp target\*  logger.MyLog abc ddd<br />

will write to myLogFile.txt<br />
2022-03-27 15:56:41.217 ERROR aaa<br />
2022-03-27 15:56:41.317 ERROR bbb<br />
2022-03-27 15:56:41.417 INFO ccc<br />
2022-03-27 15:56:41.517 abc ddd<br />

to do:<br />
read  configuration from file<br />
update configuration in runtime <br />


