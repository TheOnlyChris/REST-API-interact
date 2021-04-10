# REST-API-interact
Java program to exhibit interaction with a RESTful API.

This program prompts a user to enter a country, at which point it will pull Covid-19 information related to that country from a RESTful API, and then output to the console.

Confirmed to run on Windows, using Windows command line

Extra Java Libraries utilized:
- JSON.Simple

---

To test:
- Download the JSON.Simple library from the following maven dependency. Note - version 1.1 is confirmed to work with this program, but a link to the newest version, 1.1.1 is
  included below.
  
	https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple/1.1
	https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple/1.1.1
  
  Store the .jar file somewhere and make note of the path to it.
  
- Compile with the -cp flag pointing to where the json-simple-1.1.jar (or json-simple-1.1.1.jar) is stored

- Run the program with the -cp flag pointing to two locations: where the json-simple jar file is located, and the folder where the api_interact.class file is located.  Should be
  of the form:

	java -cp "<path to json-simple-1.1>;<path to folder containing api_interact.class>" api_interact

- Console will prompt user to input a country, then if the country can be found, it will look up Covid 19 statistics for that country and display them to the console.  You will
  then be asked if you would like to continue (yes or no).  Repeat until you want to end the program.

---

Author: Christian Gutierrez
