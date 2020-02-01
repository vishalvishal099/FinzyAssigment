# FinzyAssigment

Run the framework:  
Pre-requesite : User should have java & Maven installed in his machine. 

Configuration file location : 
To run  test at local :
     1. Check your local chrome,firefox,IE  version and download respective chorme,geko,IE driver 
     and place to src/main/resources/drivers  folder based on user local machine OS. 
     2. Open src/main/resources/configFile.properties and change  MODE=local and update the  desired browser example: BROWSER=firefox
     3. Open terminal and go to Finzy project and fire 
            mvn verify -Dtest=RunCucumberTest  
     4. It will run Test cases on  parallel . 
     5. Find the execution reports under targer folder. 
                               html reports  & Json report  here: target/cucumber-reports/cucumber-pretty/index.html 
                                                                  target/cucumber-reports/cucumber.json                                                        
     6. To manage running feature file and run : src/test/java/jobRunner/RunCucumberTest.java                                                    
         
To run test at remote :
     1. Setup the Hub and nodes(
        Quick setup of hub and node using docker:
        -Download docker
        - create HUB  
            docker run -d -p 4444:4444 --name selenium-hub selenium/hub:3.6.0
        -create chorme node and link with hub  
          docker run -d -P -p 5900:5900 --link selenium-hub:hub selenium/node-chrome-debug:3.6.0
        - create firefox node and link with hub
           docker run -d -P -p 5902:5900 --link selenium-hub:hub selenium/node-firefox-debug:3.6.0
        - To check running docker container 
            docker ps -a 
         )
     2. Open src/main/resources/configFile.properties and change  MODE=grid and update the  desired browser example: BROWSER=firefox
     3. Open terminal and go to Finzy project and fire  mvn verify -Dtest=RunCucumberTest  
     4. It will run Test cases on  parallel . 
     5. Find the execution reports under targer folder. 
                               html reports  & Json report  here: target/cucumber-reports/cucumber-pretty/index.html 
                                                                  target/cucumber-reports/cucumber.json                                                        
     6. To manage running feature file and run : src/test/java/jobRunner/RunCucumberTest.java  
         