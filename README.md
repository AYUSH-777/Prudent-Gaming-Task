# Prudent-Gaming-Task
Created a Spring Boot Web Application that can insert data into a MySQL database from a JSON file containing betting summary data

I have executed the following steps to complete this task which has been listed as follows:

Step 1: Installed the MySQL and Tomcat server on the local machine.

Step 2: Created a database table in MySQL to store data from the JSON file. The table has columns for id, numbets, game, stake, returns, clientid, and date.Also I indexed the clientid and date columns to optimize select queries.

Step 3: Wrote a Java class to read the JSON file and inserted it into the database table. Used the Spring Boot framework to make database connections and inserted the queries. After each successful insert, a Kafka message to the "bet_detail" topic was produced.

Step 4: Wrote a Kafka consumer class to listen to the "bet_detail" topic. If the "returns" amount is >= 1500.00, then a notification message will be printed.

Step 5: Wrote a Spring Boot web application to search the table with filters based on the game, clientid, and date fields. Created an HTML form to take filter inputs and display the search results on the same page.

Step 6: Wrote a dockerfile and docker composer to have all required services running as a container such that the application will be deployed in one of the containers.






