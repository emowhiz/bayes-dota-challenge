bayes-dota
==========

This is the [task](TASK.md).

Any additional information about your solution goes here.

##How to build and run

Build the application using maven by executing the following command

``mvn clean install``

then start the application by using built jar

``java -jar test-0.0.1-SNAPSHOT.jar``

##Assumptions 
Following assumptions were made while developing the solution.

* only the damage instances done from a hero to another hero are considered when extracting and persisting damage events is required.

##Additional third party dependencies
ModelMapper is used as an eternal dependency to map one object type to others.

##Things to be improved

* Covering more areas with the tests and writing proper comprehensive tests.
* improving the logs


