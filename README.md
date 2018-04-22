# Mass Transit Simulation Web App

This project is a mass transit simulation web app that supports bus and train systems. The riders can plan their trip by searching available bus or train options and the system will accurately return total trip distance, travel time and arrival time of next coming bus or train. Administrators are granted options to run simulation, monitor rider flow, traffic condition, as well as control to modify the underlying elements (riders, bus, train, road, stops) in the metro system. 


## Authors Group 32

Di Wang (dwang383)
Bo Chen ()
Mythili An ()
Xiaoyi An ()


### Prerequisites

IDE - Eclipse (Spring tools plugin)
Browser - Chrome, Firefox etc.
OS - Deployed in Ubuntu VM

### Running the test


#### On VM 
Open project mass_system 
Click run application ...
Start browser, open [H2 console] (https://localhost8080:h2-console) import data schema and data from sql files
Open the [UI] (https://localhost8080)
Click client to search routes
Click adminstrator to simulate and modify the system 

#### Running a new machine
Open eclipse - Spring tools plugin recommended to install
Import Project as Existing Maven Project and select source code (mass_system) 
Configure run application as new Spring Boot App, set main class as application.Application
Run application ...
Start browser, open [H2 console] (https://localhost8080:h2-console) import data schema and data from sql files
Open the [UI] (https://localhost8080)
Click client to search routes
Click adminstrator to simulate and modify the system 

For ease of use, we package the file into jar file



