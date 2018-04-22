# Mass Transit Simulation Web App

This project is a mass transit simulation web app that supports bus and train systems. The riders can plan their trip by searching available bus or train options and the system will accurately return total trip distance, travel time and arrival time of next coming bus or train. Administrators are granted options to run simulation, monitor rider flow, traffic condition, as well as control to modify the underlying elements (riders, bus, train, road, stops) in the metro system. 


## Authors Group 32

Di Wang (dwang383)
Bo Chen (bchen354)
Mythili Rajaraman (mrajarman3)
Xiaoyi An (xan31)


### Prerequisites

IDE - Eclipse, IntelliJ (Spring tools plug-in), STS
Browser - Chrome, Firefox etc.
OS - Deployed in Ubuntu VM

### Running the test


#### On VM 
Open project mass_system 
Click run application ...
Start browser, open [H2 console] (https://localhost8080:h2-console) import data schema and data from sql files
Open the [UI] (https://localhost8080)
Click Client to search routes
Click Administrator to simulate and modify the system 

#### Running a new machine
Open eclipse - Spring tools plug-in recommended to install
Import Project as Existing Maven Project and select source code (mass_system) 
Configure run application as new Spring Boot App, set main class as application.Application
Run application ...
Start browser, open [H2 console] (https://localhost8080:h2-console) import data schema and data from sql files
Open the [UI] (https://localhost8080)
Click client to search routes
Click Administrator to simulate and modify the system 

For ease of use, we package the file into jar file


### Deploy the application.
The application can be deployed as a artifact. With the embedded Tomcat, you don't need to deploy the application to 
Tomcat, instead you can just run it as a application.

You can package the artifact with command : mvn clean install

Then you can find the artifact under target folder

You can run java -jar artifactname.jar to launch the application.



