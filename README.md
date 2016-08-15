# ixigo_backend_task

ixigo : hosted on port 8081 of tomcat. contains the cxf restful web service code for the 2 pages. you can host this code on server and run it as java application by calling the  web service from main class ixigo/src/com/flights/main/FlightSearchData.
  Main webservice class is ixigo/src/com/flights/service/bridge/FlightDataServiceBridge
        
ixigoServiceProviders: hosted on port 8080,contains static json flight data of 4 service providers: Makemytrip, Cleartrip, Easemytrip and Musafir, it is called from 'ixigo' to get flight data from respective service providers. Host it on port 8080 and start the server.

Rest of the code is properly commented and you can understand it by going through comments
