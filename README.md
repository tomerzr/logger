# logger
a command line logger 
mvn clean install will build and test (unit and integration)

with the artifact (logger jar) you can write you msg to myLogFile.txt

e.g
java -cp target\*  logger.MyLog e aaa
java -cp target\*  logger.MyLog ERROR bbb
java -cp target\*  logger.MyLog ccc
java -cp target\*  logger.MyLog abc ddd

will write to myLogFile.txt
2022-03-27 15:56:41.217 ERROR aaa
2022-03-27 15:56:41.317 ERROR bbb
2022-03-27 15:56:41.417 INFO ccc
2022-03-27 15:56:41.517 abc ddd

to do:
read  configuration from file
update configuration in runtime 


