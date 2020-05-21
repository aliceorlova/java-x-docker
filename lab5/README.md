Prerequisites:  
docker  
docker-compose up   

To run:  
cd lab5   
docker-compose up --scale consumer=3   
If there are any problems running this command, chances are it's due to network connection. Try running it again.  
   
Eureka Server URL: http://localhost:8761  
Service URL: http://localhost:8081  
Api-gateway: http://localhost:8080  
Grafana UI(username/password:admin/admin): http://localhost:3000  
To get valid response, add /games to any service or api-gateway URL, ie http://localhost:8080/games  

To see consumer's logs:   
docker logs -f {{docker container id}}     
