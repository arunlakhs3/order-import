Please follow the below instructions to run the services as containers in K8 cluster. 

Tech stack:
--------------------------------------------------------------------------------
Front-end : AngularJS
Back-end : Spring boot 2.2.6
DB : Mongo 
Java version : 1.8


Inputs: 
--------------------------------------------------------------------------------
1.There are 2 files building the containers. 
	a. Dockerfile --> For service order import.
	b. docker-compose.yml --> For containerizing the service with mongodb
2. The source code has been pushed to github with public access. URL :  https://github.com/arunlakhs3/order-import.git



Steps to run the application: 
--------------------------------------------------------------------------------
1. Checkout the project dir. The above mentioned docker related files will be shown. 
2. In the server where the docker and K8 runs, issue the below command. Make sure to have target/OrderData.jar in the current working directory
	# docker-compose up
	This will create the docker image and run as containers. 
3. Hit the URL http://localhost:8995/ to access the Order Import UI.
