HTTPWordCountUtility
====================
Gives the count of a word from a corpus of file

instuction to run

1)The project contains a wordcount.properties  in which we can specify the dir where all the files that has to be searched 
 will be present.please change the path to point to a dir which contains the text files to be searched.
2)Now Build the project using command
 mvn clean package
3)It will produce the packaged http-count-utility.war web application under http-count-utility\target dir
4)Please deploy this war file to tomcat or any other web server.
5)Now start the server and test the application using below url from browser
 
 http://<webserver>:<port>/http-count-utility/wordcount?query=<query string>
 
 6)It will return a json string in below format which will be displayed on browser
  {"count":<countof word>}
